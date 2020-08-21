package com.iwebui.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * 字符串工具类
 *
 * @author lgl
 */
public class StrUtils extends StringUtils {
    private static final String SEPARATOR_OF_MAC = "-";

    /**
     * 生成指定位数的随机数，只包含数字
     *
     * @param length 生成多少位
     * @return
     */
    public static String generateNumberCode(int length) {
        return RandomUtil.randomNumbers(length);
    }

    /**
     * 日期（YYYYMMDDHHMMSS）+指定位数随机数（只包含数字）
     *
     * @param length 生成多少位
     * @return
     */
    public static String dateAddGenerateNumber(int length) {
        return DateUtils.getTimeMillis() + generateNumberCode(length);
    }

    /**
     * 生成随机数字和字母混排的随机字符串
     *
     * @param length
     * @return
     */
    public static String generateStringRandom(int length) {
        return RandomUtil.randomString(length);
    }

    /**
     * @param numStr 是否数字字符串
     * @return
     */
    public static boolean validateNumber(String numStr) {
        return NumberUtil.isNumber(numStr);
    }

    /**
     * 返回首字母
     *
     * @param strChinese
     * @param bUpCase    1(返回大写字母) 2(返回小写字母)
     * @return
     */
    public static String getPyIndexStr(String strChinese, int bUpCase) {
        try {
            StringBuffer buffer = new StringBuffer();
            //把中文转化成byte数组
            byte[] b = strChinese.getBytes("GBK");
            for (int i = 0; i < b.length; i++) {
                if ((b[i] & 255) > 128) {
                    int char1 = b[i++] & 255;
                    //左移运算符用“<<”表示，是将运算符左边的对象，向左移动运算符右边指定的位数，并且在低位补零。其实，向左移n位，就相当于乘上2的n次方
                    char1 <<= 8;
                    int chart = char1 + (b[i] & 255);
                    buffer.append(getPyIndexChar((char) chart, bUpCase));
                    continue;
                }
                char c = (char) b[i];
                //确定指定字符是否可以是 Java 标识符中首字符以外的部分。
                if (!Character.isJavaIdentifierPart(c)) {
                    c = 'A';
                }
                buffer.append(c);
            }
            return buffer.toString();
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 得到首字母
     *
     * @param strChinese
     * @param bUpCase
     * @return
     */
    private static char getPyIndexChar(char strChinese, int bUpCase) {
        int charGbk = strChinese;
        char result;
        if (charGbk >= 45217 && charGbk <= 45252) {
            result = 'A';
        } else if (charGbk >= 45253 && charGbk <= 45760) {
            result = 'B';
        } else if (charGbk >= 45761 && charGbk <= 46317) {
            result = 'C';
        } else if (charGbk >= 46318 && charGbk <= 46825) {
            result = 'D';
        } else if (charGbk >= 46826 && charGbk <= 47009) {
            result = 'E';
        } else if (charGbk >= 47010 && charGbk <= 47296) {
            result = 'F';
        } else if (charGbk >= 47297 && charGbk <= 47613) {
            result = 'G';
        } else if (charGbk >= 47614 && charGbk <= 48118) {
            result = 'H';
        } else if (charGbk >= 48119 && charGbk <= 49061) {
            result = 'J';
        } else if (charGbk >= 49062 && charGbk <= 49323) {
            result = 'K';
        } else if (charGbk >= 49324 && charGbk <= 49895) {
            result = 'L';
        } else if (charGbk >= 49896 && charGbk <= 50370) {
            result = 'M';
        } else if (charGbk >= 50371 && charGbk <= 50613) {
            result = 'N';
        } else if (charGbk >= 50614 && charGbk <= 50621) {
            result = 'O';
        } else if (charGbk >= 50622 && charGbk <= 50905) {
            result = 'P';
        } else if (charGbk >= 50906 && charGbk <= 51386) {
            result = 'Q';
        } else if (charGbk >= 51387 && charGbk <= 51445) {
            result = 'R';
        } else if (charGbk >= 51446 && charGbk <= 52217) {
            result = 'S';
        } else if (charGbk >= 52218 && charGbk <= 52697) {
            result = 'T';
        } else if (charGbk >= 52698 && charGbk <= 52979) {
            result = 'W';
        } else if (charGbk >= 52980 && charGbk <= 53688) {
            result = 'X';
        } else if (charGbk >= 53689 && charGbk <= 54480) {
            result = 'Y';
        } else if (charGbk >= 54481 && charGbk <= 55289) {
            result = 'Z';
        } else {
            result = (char) (65 + (new Random()).nextInt(25));
        }
        if (2 == bUpCase) {
            result = Character.toLowerCase(result);
        }
        return result;
    }


    /**
     * 判断访问URI是否是静态文件请求
     *
     * @throws Exception
     */
    public static boolean isStaticFile(String uri) {
        // 静态文件后缀
        String[] staticFiles = {".css", ".js", ".png", ".jpg", ".gif", ".jpeg", ".bmp", ".ico", ".swf", ".psd", ".htc", ".crx", ".xpi", ".exe", ".ipa", ".apk"};
        String[] dt = {".jsp", ".java"};
        if (StringUtils.endsWithAny(uri, staticFiles) && !StringUtils.endsWithAny(uri, dt)) {
            return true;
        }
        return false;
    }

    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }


    /**
     * 首字母转大写
     *
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 去除掉横线的uuid串,32位的字符串
     *
     * @return
     */
    public static String simpleUuid() {
        return IdUtil.simpleUUID();
    }

    /**
     * 生成MAC地址
     *
     * @return
     */
    public static String randomMac4Qemu() {
        Random random = new Random();
        String[] mac = {
                String.format("%02x", 0x52),
                String.format("%02x", 0x54),
                String.format("%02x", 0x00),
                String.format("%02x", random.nextInt(0xff)),
                String.format("%02x", random.nextInt(0xff)),
                String.format("%02x", random.nextInt(0xff))
        };
        return String.join(SEPARATOR_OF_MAC, mac);
    }
}
