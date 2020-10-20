package com.iwebui.base;

/**
 * 艺术字展示
 *
 * @author czy
 * @version 1.0.0
 * @date 2020/7/30 13:46
 */
public class WordartDisplayer {
    /**
     * wordart
     */
    private static final String wordart =
            " ******   **       **       *  *****   ******   *    *      ***  " + "\n" +
            "   *       *     *  *     *    *       *    *   *    *        *   " + "\n" +
            "   *        *   *    *   *     *****   ******   **********    *   " + "\n" +
            "   *         * *      * *      *       *    *        *        *     " + "\n" +
            " ******       *        *       ******  ******        *     ****  " + "\n" +
            "\n" +
            "  *******  ******  ******  *******  " + "\n" +
            "     *     *       *          *     " + "\n" +
            "     *     *****   ******     *     " + "\n" +
            "     *     *            *     *     " + "\n" +
            "     *     ******  ******     *     ";

    /**
     * 不允许外部构造
     */
    private WordartDisplayer() {}

    /**
     * display
     */
    public static void display() {
        System.out.println(wordart);
    }
}
