import com.iwebui.dto.EasyPoiDatas;
import com.iwebui.utils.EasyPoiUtil;
import com.iwebui.utils.XmlParserUtil;
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
        List<EasyPoiDatas> loginDatas = EasyPoiUtil.importExcel(loginDatasPath,0,1, EasyPoiDatas.class);
        List<EasyPoiDatas> collect = loginDatas.stream().filter(loginData -> loginData.getName() != null || loginData.getDesc() != null || loginData.getFlag() != null || loginData.getPwd() != null).collect(Collectors.toList());
        for (EasyPoiDatas loginData : collect){
            System.out.println("["+loginData.getFlag()+loginData.getName()+loginData.getPwd()+loginData.getDesc()+"]");
        }

    }

    @Test
    public void xmlCase(){

        XmlParserUtil xmlParserUtil = new XmlParserUtil();

    }
}
