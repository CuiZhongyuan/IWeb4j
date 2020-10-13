package com.iwebui.page.data;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;

/**
 * 被测页面的元素对象：元素定位
 * @autho czy
 * @date 2020/8/25
 */
public class YynCssData {
    /**
     * css定位
     */
    //通过css标签，类和属性定位---driver.findElement(By.cssSelector("tag.class[attribute=value]"))
    //CSS:<input autocomplete="off" spellcheck="false" type="text" placeholder="请输入用户名或手机号" class="ivu-input ivu-input-large" input="">
    public static final By NAME = By.cssSelector("input[placeholder='请输入用户名或手机号']");
    //<input autocomplete="off" spellcheck="false" type="password" placeholder="请输入密码" class="ivu-input ivu-input-large">
    public static final By PWD = By.cssSelector("input[placeholder='请输入密码']");
    //<button type="button" class="ivu-btn ivu-btn-primary ivu-btn-long ivu-btn-circle ivu-btn-large"><!----> <!----> <span>登 入</span></button>
    //public static final By CLICK = By.xpath("//span[contains(text(),'登 入')]");
    public static final By CLICK = By.cssSelector("button>span");
    //<a href="/company/main" target="_blank"><div class="xotongkuai"><img src="/common/h5/img/xt01.png" alt="" class="xttp"> <p>景区票务</p></div></a>
    public static final By ROLE = By.cssSelector("a[href='/company/main']");
    //<button class="btn btn-danger" id="btn_1" onclick="openEntrances('/company/proxy/index')"><i class="fa fa-tasks"></i>管理委托景区</button>
    public static final By SCENIC = By.cssSelector("button[id='btn_1']");
    //<div class="xotongkuai"><img src="/common/h5/img/xt02.png" alt="" class="xttp"> <p>分销业务</p></div>
    public static final By FSALE = By.cssSelector(".xotongkuai>img[src='/common/h5/img/xt02.png']");
    //<button class="btn btn-danger" id="btn_1" onclick="openEntrances('/fsale/proxy/index')"><i class="fa fa-tasks"></i>管理委托景区</button>
    public static final By FSALESCENIC = By.cssSelector("button[id='btn_1']");
    //<input type="text" style="height: auto;" placeholder="快速查找景区" class="input-sm form-control" name="companyName" id="companyName">
    public static final By SEARCH = By.cssSelector("input[placeholder='快速查找景区']");
    //<ul class="nav" id="company">
    //                        <li class="setings-item text-center selectedCompany" value="{&quot;id&quot;:&quot;67f22d58-e0f5-4a85-9131-f178f576f6c7&quot;,&quot;dataCreateTime&quot;:&quot;2018-12-13 21:40:08&quot;,&quot;dataUpdateTime&quot;:&quot;2018-12-13 21:40:08&quot;,&quot;dataVersion&quot;:1,&quot;enterprise&quot;:{&quot;id&quot;:&quot;40661e7d-3445-4443-84df-f1fb2936eac8&quot;,&quot;dataCreateTime&quot;:&quot;2018-12-13 21:39:18&quot;,&quot;dataUpdateTime&quot;:&quot;2018-12-13 21:39:18&quot;,&quot;dataVersion&quot;:1,&quot;name&quot;:&quot;默认企业&quot;,&quot;dataSort&quot;:1,&quot;address&quot;:&quot;&quot;,&quot;linkManName&quot;:&quot;默认&quot;},&quot;companyCode&quot;:&quot;JQ613086&quot;,&quot;companyName&quot;:&quot;昆明石林风景名胜区&quot;,&quot;pwd&quot;:&quot;670b14728ad9902aecba32e22fa4f6bd&quot;,&quot;appId&quot;:&quot;JQ613086&quot;,&quot;appKey&quot;:&quot;09b853efc554a5471933817e685d4b9e&quot;,&quot;mobile&quot;:&quot;&quot;,&quot;linkInfo&quot;:&quot;&quot;,&quot;address&quot;:&quot;昆明市石林彝族自治县石林镇&quot;,&quot;email&quot;:&quot;&quot;,&quot;useFlag&quot;:true,&quot;approveFlag&quot;:true,&quot;channelMaxNum&quot;:0,&quot;dataSort&quot;:151,&quot;threeFlag&quot;:false,&quot;selfFlag&quot;:false,&quot;tengyunId&quot;:&quot;e108aa08-f5f6-4d87-5c98-501b2983c615&quot;,&quot;tengyunCityId&quot;:&quot;385&quot;,&quot;tengyunCityName&quot;:&quot;昆明市&quot;}" selected="">昆明石林风景名胜区</li></ul>
    public static final By ENTERSHILIN = By.cssSelector("#company>li");
    public static final By PACKUP = By.cssSelector(".right-sidebar-toggle>i");
    //<i class="fa fa-desktop"></i>
//    public static final By SICNICSPOT = By.xpath("//*[@id='side-menu']/li[2]/a/span[1]");
    public static final By SICNICSPOT = By.cssSelector(".fa.fa-desktop");
    public static final By TICKET = By.cssSelector(".J_menuItem[href='/company/proxy/ticket/index']");
    public static final By TICKETCODE = By.cssSelector("#searchticketCode");
    public static final By CODESEARCH = By.cssSelector("button[onclick='searchTable()']");
    //如果有多个相同类型可以根据索引取出
//    public static final By TIME = By.xpath("//a[@class='btn btn-primary btn-xs'][3]");
    //也可以使用css标签(*:模糊)属性值匹配
    //<a href="#" class="btn btn-primary btn-xs" onclick="ticketTimePoint('77f2b46c-ce30-4f69-86d8-2a35e3798047')"><i class="fa fa-pencil"></i>时间点管理</a>
    public static final By TIME = By.cssSelector("a[onclick*='ticketTimePoint']");
    //定位iframe元素
    public static final By IFRAME = By.cssSelector(".layui-layer-setwin");
    public static final By ADD = By.cssSelector("#tablepager > tbody > tr");
    //删除按钮元素：//button[contains(text(),'删除')]由于会出现多个需要转成list集合遍历删除
    public static final By DELETE = By.xpath("//button[contains(text(),'删除')]");
    //最后点击保存按钮
    public static final By SAVE = By.cssSelector("#updateButton");
    //确定删除按钮
    public static final By SURE = By.cssSelector("div[id*='layui-layer']>div>a[class='layui-layer-btn0']");

}
