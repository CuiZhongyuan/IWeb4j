import com.iwebui.dto.LoginDatas;
import com.iwebui.utils.EasyPoiUtil;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

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
        List<LoginDatas> loginDatas = EasyPoiUtil.importExcel(loginDatasPath,0,1, LoginDatas.class);
        List<LoginDatas> collect = loginDatas.stream().filter(loginData -> loginData.getCode() != null || loginData.getDesc() != null || loginData.getFlag() != null || loginData.getPwd() != null).collect(Collectors.toList());
        for (LoginDatas loginData : collect){
            System.out.println("["+loginData.getFlag()+loginData.getCode()+loginData.getPwd()+loginData.getDesc()+"]");
        }

    }
}
