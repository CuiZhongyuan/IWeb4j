
import com.iwebui.dto.LoginDatas;
import com.iwebui.utils.BaseTestngInit;

import com.iwebui.utils.EasyPoiUtil;
import org.testng.annotations.Test;
import java.util.List;
import java.util.stream.Collectors;

public class RunCasesAll {

/**
 * 测试用例总入口
 * */
   @Test
    public void runCases(){
        //执行测试用例入口
        BaseTestngInit baseTestngInit = new BaseTestngInit();
        baseTestngInit.baseTestngInitCode();
    }
}
