package com.lee.mvpstudy.util;

/**
 * 判断是否快速点击
 */
public class ClickUtils {
    private static long lastClickTime = 0;
    private static long DIFF = 800;
    private static int lastButtonId = -1;

    /**
     * 判断两次点击的间隔，如果小于指定时间(800ms)，则认为是多次无效点击
     */
    public static boolean isFastDoubleClick() {
        return isFastDoubleClick(-1, DIFF);
    }

    /**
     * 判断两次点击的间隔，如果小于指定时间(800ms)，则认为是多次无效点击
     */
    public static boolean isFastDoubleClick(int buttonId) {
        return isFastDoubleClick(buttonId, DIFF);
    }

    /**
     * 判断两次点击的间隔，如果小于diff，则认为是多次无效点击
     */
    public static boolean isFastDoubleClick(int buttonId, long diff) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (lastButtonId == buttonId && lastClickTime > 0 && timeD < diff) {
            return true;
        }
        lastClickTime = time;
        lastButtonId = buttonId;
        return false;
    }
}
