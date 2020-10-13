package com.iwebui.base;

import com.iwebui.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.Set;

/**
 * 封装浏览器中界面上最基本操作
 *
 * @author czy
 * @date 2020/1/28
 */
@Slf4j
public class BaseBrowser {
    /**
     * 驱动
     */
    protected WebDriver driver;

    /**
     * 动作
     */
    protected Actions actions;

    /**
     * 脚本
     */
    protected JavascriptExecutor je;

    /**
     * 显示等待
     */
    protected WebDriverWait wait;

    /**
     * 构造器 1
     *
     * @param driver 驱动
     */
    public BaseBrowser(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.je = ((JavascriptExecutor) driver);
    }

    /*============================== 基本元素操作 ==============================*/

    /**
     * 通过元素定位拿到 WebElement 元素对象
     * 并且使用visibilityOfElementLocated方法是期望该元素存在于dom树上且可见后再返回该元素
     * @param locator By 类型元素定位
     * @return 定位到的元素
     */
    public WebElement locateElement(By locator) {
        try {
            wait = new WebDriverWait(driver, 20);
            WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return webElement;
        }catch (Exception  e) {
            System.out.println("================定位元素超时，请查看=================="+locator);
            return null;
        }
    }
    /**
     * 点击元素
     *
     * @param locator By 类型元素定位
     * @return 点击的元素
     */
    public WebElement clickButton(By locator) {
        try{
            long time1 = DateUtils.getCurrentMillisecond();
            WebElement buttonElement = locateElement(locator);
            buttonElement.click();
            log.info("该点击事件耗时："+(DateUtils.getCurrentMillisecond()-time1)+"ms");
            return buttonElement;
        }catch (NoSuchElementException | TimeoutException | NullPointerException  e) {
            System.out.println("================元素不存在或不可点击状态，请查看=================="+locator);
            return null;
        }
    }
    /**
     * 点击弹框元素
     *
     * @param locator By 类型元素定位
     * @return 点击弹框按钮，解决弹框按钮可见点击后由于弹框页面后续元素未渲染完成出现点击后没有弹框出现问题
     */
    public WebElement clickPop(By locator) {
        wait = new WebDriverWait(driver, 20);
        try {
            String jsToBeExecute = "return document.readyState =='complete'";
            boolean isReady = (boolean) wait.until(ExpectedConditions.jsReturnsValue(jsToBeExecute));
            if (isReady) {
                long time1 = DateUtils.getCurrentMillisecond();
                WebElement buttonPopElement = locateElement(locator);
                buttonPopElement.click();
                log.info("点击弹框事件耗时：" + (DateUtils.getCurrentMillisecond() - time1) + "ms");
                return buttonPopElement;
            }
        } catch (Exception e) {
            System.out.println("================元素不存在或不可点击状态，请查看=================="+locator);
        }
        return null;
    }
    /**
     * 输入框输入数据
     *
     * @param locator By 类型元素定位
     * @param content 输入的内容，支持多内容，可以键盘输入
     * @return 输入框元素
     */
    public WebElement sendInput(By locator, CharSequence... content) {
        WebElement inputElement = locateElement(locator);
        inputElement.clear();
        inputElement.sendKeys(content);
        return inputElement;
    }

    /**
     * 移动到指定元素
     *
     * @param locator 元素定位
     */
    public void moveToElement(By locator) {
        actions.moveToElement(locateElement(locator)).perform();
    }

    /**
     * 拖拽指定元素
     *
     * @param fromLocator 从...元素
     * @param toLocator   至...元素
     */
    public void dragAndDropElement(By fromLocator, By toLocator) {
        wait.until(ExpectedConditions.elementToBeClickable(fromLocator));
        actions.dragAndDrop(locateElement(fromLocator), locateElement(toLocator)).perform();
    }

    /**
     * 跳转页面
     *
     * @param url 网址
     */
    public void enterPage(String url) {
        try {
            driver.navigate().to(url);
        }catch (NoSuchElementException | TimeoutException  e) {
            System.out.println("================当前页面未捕获该元素，继续执行用例==================");
//            vdrier.navigate().refresh();
        }
    }

    /*============================== 切换窗口句柄 ==============================*/

    /**
     * 查找下一个句柄，若只有一个窗口则句柄不变
     *
     * @return 驱动
     */
    public WebDriver switchNextHandle() {
        // 当前窗口句柄
        String currentHandle = driver.getWindowHandle();
        // 所有窗口句柄
        Set<String> allHandlesSet = driver.getWindowHandles();
        // 寻找下一个句柄
        for (String handle : allHandlesSet) {
            if (!handle.equals(currentHandle)) {
                return driver.switchTo().window(handle);
            }
        }
        return driver;
    }
    /**
     * 多窗口切换句柄，依据传入的句柄号码
     *
     * @param num 号码从 1 开始
     * @return 驱动
     */
    public WebDriver switchHandleByNum(int num) {
        // 所有窗口句柄
        Set<String> allHandlesSet = driver.getWindowHandles();
        Object[] allHandlesArr = allHandlesSet.toArray();
        // 切换句柄
        return driver.switchTo().window(allHandlesArr[num - 1].toString());
    }

    /**
     * 多窗口切换句柄，依据传入的窗口标题
     *
     * @param title contains(窗口 title)
     * @return 驱动
     * @throws Exception 找不到指定窗口句柄异常
     */
    public WebDriver switchHandleByTitle(String title) throws Exception {
        // 当前窗口句柄
        String currentHandle = driver.getWindowHandle();
        // 所有窗口句柄
        Set<String> allHandlesSet = driver.getWindowHandles();
        // 寻找第一个 title 符合的句柄
        for (String handle : allHandlesSet) {
            driver.switchTo().window(handle);
            if (driver.getTitle().contains(title)) {
                return driver;
            }
        }
        driver.switchTo().window(currentHandle);
        throw new Exception(title + "窗口的句柄不存在");
    }

    /**
     * 多窗口切换句柄，依据传入的窗口 url
     *
     * @param url contains(窗口 url)
     * @return 驱动
     * @throws Exception 找不到指定窗口句柄异常
     */
    public WebDriver switchHandleByUrl(String url) throws Exception {
        // 当前窗口句柄
        String currentHandle = driver.getWindowHandle();
        // 所有窗口句柄
        Set<String> allHandlesSet = driver.getWindowHandles();
        // 寻找第一个 url 符合的句柄
        for (String handle : allHandlesSet) {
            driver.switchTo().window(handle);
            if (driver.getCurrentUrl().contains(url)) {
                return driver;
            }
        }
        driver.switchTo().window(currentHandle);
        throw new Exception(url + "窗口的句柄不存在");
    }

    /*============================== 切换 frame 结构 ==============================*/

    /**
     * 根据元素位置切换 frame 结构
     *
     * @param locator frame 定位
     * @return 驱动
     */
    public WebDriver switchFrame(By locator) {
        return driver.switchTo().frame(locateElement(locator));
    }

    /**
     * 切换父 frame 结构
     *
     * @return 驱动
     */
    public WebDriver switchParentFrame() {
        return driver.switchTo().parentFrame();
    }

    /**
     * 跳出 frame 结构
     *
     * @return 驱动
     */
    public WebDriver switchOutOfFrame() {
        return driver.switchTo().defaultContent();
    }

    /*============================== JS 操作 ==============================*/

    /**
     * 执行 JS 脚本
     *
     * @param script JS 脚本
     */
    public void executeScript(String script) {
        je.executeScript(script);
    }

    /**
     * 执行 JS 脚本
     *
     * @param script JS 脚本
     * @param args   对象元素数组
     */
    public void executeScript(String script, Object... args) {
        je.executeScript(script, args);
    }

    /**
     * 滑动到页面最顶上
     */
    public void scrollToTop() {
        executeScript("window.scrollTo(window.pageXOffset, 0)");
    }

    /**
     * 滑动到页面最低端
     */
    public void scrollToBottom() {
        executeScript("window.scrollTo(window.pageXOffset, document.body.scrollHeight)");
    }

    /**
     * 滑动使得元素和窗口顶端对齐
     *
     * @param by 需要和页面顶端对齐的元素
     */
    public void scrollElementTopToTop(By by) {
        executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
    }

    /**
     * 滑动使得元素和窗口底部对齐
     *
     * @param by 需要和页面底端对齐的元素
     */
    public void scrollElementBottomToBottom(By by) {
        executeScript("arguments[0].scrollIntoView(false);", driver.findElement(by));
    }

    /**
     * 滑动到页面最右边
     */
    public void scrollToRight() {
        executeScript("window.scrollTo(document.body.scrollWidth, window.pageYOffset)");
    }

    /**
     * 滑动到页面最左边
     */
    public void scrollToLeft() {
        executeScript("0, window.pageYOffset)");
    }

    // todo : 页面中其他的最基本操作，可自行封装
}
