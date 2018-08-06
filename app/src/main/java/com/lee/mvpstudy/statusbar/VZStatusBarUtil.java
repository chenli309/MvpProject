package com.lee.mvpstudy.statusbar;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;

import com.jaeger.library.StatusBarUtil;
import com.lee.mvpstudy.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Func:    沉浸状态栏.<br/>
 * Date:    2018/6/4 14:47<br/>
 * Author:  chenli@variflight.com<br/>
 * Version: 1.0.0<br/>
 */
public class VZStatusBarUtil {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({VZStatusBarType.BAR_TYPE_NO, VZStatusBarType.BAR_TYPE_HOME, VZStatusBarType.BAR_TYPE_WHITE})
    public @interface VZStatusBarType {
        public static final int BAR_TYPE_NO = 0;      // 默认纯色背景
        public static final int BAR_TYPE_HOME = 1;    // 首页
        public static final int BAR_TYPE_WHITE = 2;   // 标题栏白色背景
        public static final int BAR_TYPE_IMAGE = 3;   // 标题栏图片背景
    }

    /**
     * 初始化栏态栏
     *
     * @param activity 当前页面上下文
     * @param barColor BAR_TYPE_NO:状态栏不改变, BAR_TYPE_HOME首页, BAR_TYPE_WHITE白色, BAR_TYPE_IMAGE图片
     */
    public static void setStatusBar(Activity activity, int barColor) {
        if (barColor == VZStatusBarType.BAR_TYPE_NO) { //状态栏不改变
            //什么也不做
        } else if (barColor == VZStatusBarType.BAR_TYPE_HOME) {
            setStatusBarHome(activity);
        } else if (barColor == VZStatusBarType.BAR_TYPE_WHITE) {
            setStatusBarWhite(activity);
        } else if (barColor == VZStatusBarType.BAR_TYPE_IMAGE) {
            setStatusBarImage(activity);
        }
    }

    /**
     * 初始化首页VZHomeActivity的状态栏
     *
     * @param activity 当前页面上下文
     */
    private static void setStatusBarHome(Activity activity) {
        int statusBarAlpha = StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA;
        if (isSupportStatusBarFontColor()) {
            statusBarAlpha = 0;
//            setStatusBarTextColor(activity, true);
        } else {
//            setStatusBarTextColor(activity, false);
        }
        StatusBarUtil.setTransparentForImageViewInFragment(activity, null);
    }

    /**
     * 初始化固定白色颜色的状态栏
     *
     * @param activity 当前页面上下文
     */
    private static void setStatusBarWhite(Activity activity) {
        int statusBarAlpha = StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA;
        if (isSupportStatusBarFontColor()) {
            statusBarAlpha = 0;
//            setStatusBarTextColor(activity, true);
        } else {
//            setStatusBarTextColor(activity, false);
        }
        StatusBarUtil.setColorForSwipeBack(activity, ContextCompat.getColor(activity, R.color.color_cw), statusBarAlpha);

        if (isSupportStatusBarFontColor()) {
            LightStatusBarCompat.setLightStatusBar(activity.getWindow(), true);
        } else {
            LightStatusBarCompat.setLightStatusBar(activity.getWindow(), false);
        }
    }

    /**
     * 初始化首页VZHomeActivity的状态栏
     *
     * @param activity 当前页面上下文
     */
    private static void setStatusBarImage(Activity activity) {
        int statusBarAlpha = StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA;
        if (isSupportStatusBarFontColor()) {
            statusBarAlpha = 0;
//            setStatusBarTextColor(activity, true);
        } else {
//            setStatusBarTextColor(activity, false);
        }
        StatusBarUtil.setTranslucentForImageView(activity, statusBarAlpha, null);
    }

    private static boolean isSupportStatusBarFontColor() {
        return RomUtil.isSmartisan() || RomUtil.isMiui() || RomUtil.isFlyme() || Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * 获取手机状态栏的高度
     *
     * @param context 上下文
     * @return 手机状态栏的高度
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return statusBarHeight;
        }

        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resourceId);
        }
        if (statusBarHeight <= 0) {
            statusBarHeight = resources.getDimensionPixelSize(R.dimen.statusbar_view_height);
        }
        return statusBarHeight;
    }
}
