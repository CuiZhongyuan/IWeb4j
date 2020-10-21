package com.iwebui.utils;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyan
 * @date 2019-02-23  下午 12:27
 *
 */
@Slf4j
public class AssertRewriteUtil {
    public static boolean flag = true;
    public static List<Error> errors = new ArrayList<>();

    public static void assertFalse(boolean condition, String message){
        try{
            Assert.assertFalse(condition,message);
        }catch(Error e){
            errors.add(e);
            flag=false;
        }
    }
    public static void assertFalse(boolean condition){
        try{
            Assert.assertFalse(condition);
        }catch(Error e){
            errors.add(e);
            flag=false;
        }
    }
    public static void assertTrue(boolean condition, String message){
        try{
            Assert.assertTrue(condition,message);
        }catch(Error e){
            errors.add(e);
            flag=false;
        }
    }
    public static void assertTrue(boolean condition){
        try{
            Assert.assertTrue(condition);
        }catch(Error e){
            errors.add(e);
            flag=false;
        }
    }
    public static void verifyEquals(Object actual,Object expected){
        try{
            Assert.assertEquals(actual,expected);
        }catch(Error e){
            errors.add(e);
            flag=false;
        }
    }

    public static void verifyEquals(Object actual,Object expected,String message){
        try{
            Assert.assertEquals(actual, expected, message);
        }catch(Error e){
            errors.add(e);
            flag=false;
        }
    }
    public static void verifyNulls(Object actual,String message){
        try{
            Assert.assertNull(actual,message);
        }catch(Error e){
            errors.add(e);
            flag=false;
        }
    }
}

