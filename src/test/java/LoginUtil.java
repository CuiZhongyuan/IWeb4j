import com.iwebui.dto.LoginDatas;
import com.iwebui.utils.EasyPoiUtil;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class LoginUtil {
//    public static String loginDatasPath = this.getClass().getClassLoader().getResource("loginTest.xlsx").getPath();
    public static List<LoginDatas> loginDatasList = new ArrayList<LoginDatas>();
    static {
        List<LoginDatas> list = EasyPoiUtil.importExcel("C:\\Users\\Administrator\\Desktop\\loginTest.xlsx",0,1,LoginDatas.class);
        loginDatasList.addAll(list);
        for (LoginDatas loginDatas : loginDatasList){
            System.out.println(loginDatas);
        }
    }
}
