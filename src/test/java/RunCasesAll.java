
import com.iwebui.dto.LoginDatas;
import com.iwebui.utils.BaseTestngInit;

import com.iwebui.utils.EasyPoiUtil;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    public void case3() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
       //熟悉反射
        //1.首先获取字节码
        Class<Students> clazz = Students.class;
        //通过字节码创建对象
        Students students = clazz.newInstance();
        //通过反射获取需要调用的方法
        Method[] methods = clazz.getMethods();
        for (Method method:methods){
            System.out.println(method);
        }
        System.out.println("=============================");
        //通过反射完成方法调用
        Method setNameMethod = clazz.getMethod("setName", String.class);
        //操作对象，set参数值
        setNameMethod.invoke(students,"czy");
        //get参数
        Method getNameMethod = clazz.getMethod("getName");
        Object value = getNameMethod.invoke(students);
        System.out.println("name:"+value.toString());
        System.out.println(clazz.getFields());
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
