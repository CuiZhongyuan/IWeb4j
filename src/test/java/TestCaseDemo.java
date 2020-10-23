import com.iwebui.dto.LoginCaseDto;
import com.iwebui.dto.LoginUrlDto;
import com.iwebui.utils.EasyPoiUtil;
import com.iwebui.utils.ExcelTestResultOutputUtil;
import com.iwebui.utils.XmlParserUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestCaseDemo {
    //推荐使用
    @Test
    public void case1(){
        int[][] datas = new int[][]{{2,4,5,7},{2,4,8}};
        for (int[] data : datas){
            for (int object : data){
                System.out.print(object);
            }
            System.out.println();
        }
    }
    @Test
    public void case2(){
        int[][] datas = new int[][]{{2,4,5,7},{2,4,8}};
        for (int i =0;i<datas.length;i++){
            for (int j =0;j<datas[i].length;j++){
                System.out.print(datas[i][j]);
            }
            System.out.println();
        }
    }
    @Test
    public void loginTestCase() {
        String loginDatasPath = this.getClass().getClassLoader().getResource("loginTest.xlsx").getPath();
        List<LoginCaseDto> loginDatas = EasyPoiUtil.importExcel(loginDatasPath,0,1, LoginCaseDto.class);
        List<LoginCaseDto> collect = loginDatas.stream().filter(loginData -> loginData.getName() != null || loginData.getDesc() != null || loginData.getFlag() != null || loginData.getPwd() != null).collect(Collectors.toList());
        for (LoginCaseDto loginData : collect){
            System.out.println("["+loginData.getFlag()+loginData.getName()+loginData.getPwd()+loginData.getDesc()+"]");
        }
    }

    @Test
    public void xmlCase(){
        XmlParserUtil xmlParserUtil = new XmlParserUtil();
    }
    /**
     * 测试接口测试响应数据添加至allure的body里
     * */
    @Test
    @Story("测试接口测试响应数据添加至allure的test body里")
    public void test1() {
        try {
            String url = "www.baidu.com";
            String header = "accept: application/json";
            String body = "{t: 1585926656064}";
            String response = "{\"status\":200,\"message\":\"success\",\"data\":{\"session\":\"e6530619f00b6e27f58d5c251a9c7b07\",\"id\":\"bc5a637f976db9779cc8ec56dbc5f418\",\"challenge\":\"e6530619f00b6e27f58d5c251a9c7b07\",\"provider\":\"gt\",\"failback\":false},\"timestamp\":\"1585926656203\",\"version\":4}";
            caseStep(url,header,body,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试步骤
    public void caseStep(String url, String header, String body, String response)
    {
        Allure.addAttachment("请求地址", url);
        Allure.addAttachment("请求头", header);
        Allure.addAttachment("请求体", body);
        Allure.addAttachment("请求响应", response);
    }

    @Test
    public void case3(){
        String path = "C:\\Users\\Administrator\\Desktop\\11.xls";
        List<LoginCaseDto> loginDatas = EasyPoiUtil.importExcel(path,0,1,1, LoginCaseDto.class);
        List<LoginUrlDto> sheet1 = EasyPoiUtil.importExcel(path,1,1,1, LoginUrlDto.class);
        for (LoginCaseDto urlid : loginDatas){
            String id =  urlid.getUrlid();
            if (id.equalsIgnoreCase("1")) {
                for (LoginUrlDto url : sheet1) {
                    if (url.getId().equalsIgnoreCase("1")) {
                        urlid.setUrlpath(url.getUrl());
                    }
                }
            }
        }
        ExcelTestResultOutputUtil.exportSheet(loginDatas,sheet1);
    }


}
