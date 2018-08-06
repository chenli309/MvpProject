package com.lee.mvpstudy.view;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.lee.mvpstudy.R;

/**
 * Func:    通用标题栏封装.<br/>
 * Date:    2018/8/2 16:36<br/>
 * Author:  chenli@variflight.com<br/>
 * Version: 1.0.0<br/>
 */
public class VZTitleView extends FrameLayout implements View.OnClickListener {
//    private int toolbarStyle;
//    private boolean showBack;
//    private boolean showTitle;

    private VZToolbar toolbar;

    private ImageView iv_toolbar_back;
    private TextView tv_toolbar_back_text;

    private TextView tv_toolbar_title;
    private TextView tv_toolbar_sub_title;

    private TextView tv_toolbar_right;
    private ImageView iv_toolbar_right;
    private ImageView iv_toolbar_right_1;
    private ImageView iv_toolbar_right_2;
//    private TextView tv_toolbar_right_2_badge;

    private View v_toolbar_bot_line;

    private OnClickListener mOnClickListener;

    public void setViewOnClickListener(OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public VZTitleView(@NonNull Context context) {
        this(context, null);
    }

    public VZTitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        /*
        <declare-styleable name="VZTitleView">
            <attr name="toolbar_style" format="enum">
                <enum name="style_common" value="0" />
            </attr>
            <attr name="showBack" format="boolean" />
            <attr name="showTitle" format="boolean" />
        </declare-styleable>
         */

//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.VZTitleView);
//        try {
//            toolbarStyle = a.getInt(R.styleable.VZTitleView_toolbar_style, 0);
//            showBack = a.getBoolean(R.styleable.VZTitleView_showBack, true);
//            showTitle = a.getBoolean(R.styleable.VZTitleView_showTitle, true);
//        } finally {
//            a.recycle();
//        }

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.layout_base_title_view, this, true);

        toolbar = (VZToolbar) findViewById(R.id.toolbar);
        iv_toolbar_back = (ImageView) toolbar.findViewById(R.id.iv_toolbar_back);
        tv_toolbar_back_text = (TextView) toolbar.findViewById(R.id.tv_toolbar_back_text);

        iv_toolbar_back.setOnClickListener(this);
        tv_toolbar_back_text.setOnClickListener(this);

        tv_toolbar_title = (TextView) toolbar.findViewById(R.id.tv_toolbar_title);
        tv_toolbar_sub_title = (TextView) toolbar.findViewById(R.id.tv_toolbar_sub_title);

        tv_toolbar_right = (TextView) toolbar.findViewById(R.id.tv_toolbar_right);
        iv_toolbar_right = (ImageView) toolbar.findViewById(R.id.iv_toolbar_right);
        iv_toolbar_right_1 = (ImageView) toolbar.findViewById(R.id.iv_toolbar_right_1);
        iv_toolbar_right_2 = (ImageView) toolbar.findViewById(R.id.iv_toolbar_right_2);
//        tv_toolbar_right_2_badge = (TextView) toolbar.findViewById(R.id.tv_toolbar_right_2_badge);

        tv_toolbar_right.setOnClickListener(this);
        iv_toolbar_right.setOnClickListener(this);
        iv_toolbar_right_1.setOnClickListener(this);
        iv_toolbar_right_2.setOnClickListener(this);

        v_toolbar_bot_line = toolbar.findViewById(R.id.v_toolbar_bot_line);
    }

    /**
     * 设置标题栏背景色
     *
     * @param resId 资源id
     */
    public void setToolbarBackgroundResource(@DrawableRes int resId) {
        toolbar.setBackgroundResource(resId);
    }

    /**
     * 设置标题栏背景色
     *
     * @param color 背景色Color
     */
    public void setToolbarBackgroundColor(@ColorInt int color) {
        toolbar.setBackgroundColor(color);
    }

    /**
     * 设置后退按钮图标
     *
     * @param resId 资源id
     */
    public void setBackImageResource(@DrawableRes int resId) {
        iv_toolbar_back.setImageResource(resId);
    }

    /**
     * 设置后退按钮的padding，调整图片的大小
     *
     * @param padding 间距
     */
    public void setBackImagePadding(int padding) {
        int p = SizeUtils.dp2px(padding);
        iv_toolbar_back.setPadding(p, p, p, p);
    }

    /**
     * 是否显示后退按钮
     *
     * @param visibility 显示类型
     */
    public void setBackVisibility(int visibility) {
        iv_toolbar_back.setVisibility(visibility);
    }

    /**
     * 设置后退按钮后的文字
     *
     * @param resId 文案资源id
     */
    public void setBackText(@StringRes int resId) {
        tv_toolbar_back_text.setText(resId);
    }

    /**
     * 设置后退按钮后的文字颜色
     *
     * @param textColor 文字颜色
     */
    public void setBackTextColor(@ColorInt int textColor) {
        tv_toolbar_back_text.setTextColor(textColor);
    }

    /**
     * 是否显示后退按钮后的文字
     *
     * @param visibility 显示类型
     */
    public void setBackTextVisibility(int visibility) {
        tv_toolbar_back_text.setVisibility(visibility);
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitleText(String title) {
        tv_toolbar_title.setText(title);
    }

    /**
     * 设置标题
     *
     * @param resId 标题资源id
     */
    public void setTitleText(@StringRes int resId) {
        tv_toolbar_title.setText(resId);
    }

    /**
     * 设置标题字体大小
     *
     * @param textSize 字体大小
     */
    public void setTitleTextSize(@DimenRes int textSize) {
        tv_toolbar_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimensionPixelSize(textSize));
    }

    /**
     * 设置标题文字颜色
     *
     * @param textColor 文字颜色
     */
    public void setTitleTextColor(@ColorInt int textColor) {
        tv_toolbar_title.setTextColor(textColor);
    }

    /**
     * 是否显示标题
     *
     * @param visibility 显示类型
     */
    public void setTitleVisibility(int visibility) {
        tv_toolbar_title.setVisibility(visibility);
    }

    /**
     * 设置子标题
     *
     * @param title 标题
     */
    public void setSubTitleText(String title) {
        tv_toolbar_sub_title.setText(title);
    }

    /**
     * 设置子标题
     *
     * @param resId 标题资源id
     */
    public void setSubTitleText(@StringRes int resId) {
        tv_toolbar_sub_title.setText(resId);
    }

    /**
     * 设置子标题字体大小
     *
     * @param textSize 字体大小
     */
    public void setSubTitleTextSize(@DimenRes int textSize) {
        tv_toolbar_sub_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimensionPixelSize(textSize));
    }

    /**
     * 设置子标题的文字颜色
     *
     * @param textColor 文字颜色
     */
    public void setSubTitleTextColor(@ColorInt int textColor) {
        tv_toolbar_sub_title.setTextColor(textColor);
    }

    /**
     * 是否显示子标题
     *
     * @param visibility 显示类型
     */
    public void setSubTitleVisibility(int visibility) {
        tv_toolbar_sub_title.setVisibility(visibility);
    }

    /**
     * 设置右侧文字按钮文案
     *
     * @param resId 按钮文案资源id
     */
    public void setRightText(@StringRes int resId) {
        tv_toolbar_right.setText(resId);
    }

    /**
     * 设置右侧文字按钮文案
     *
     * @param text 按钮文案
     */
    public void setRightText(String text) {
        tv_toolbar_right.setText(text);
    }

    /**
     * 设置右侧文字按钮的文字颜色
     *
     * @param textColor 文字颜色
     */
    public void setRightTextColor(@ColorInt int textColor) {
        tv_toolbar_right.setTextColor(textColor);
    }

    /**
     * 设置右侧文字按钮的字体大小
     *
     * @param textSize 字体大小
     */
    public void setRightTextSize(@DimenRes int textSize) {
        tv_toolbar_right.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimensionPixelSize(textSize));
    }

    /**
     * 是否显示右侧文字按钮
     *
     * @param visibility 显示类型
     */
    public void setRightTextVisibility(int visibility) {
        tv_toolbar_right.setVisibility(visibility);
    }

    /**
     * 设置右侧图片按钮图标
     *
     * @param resId 图标资源id
     */
    public void setRightImageResource(@DrawableRes int resId) {
        iv_toolbar_right.setImageResource(resId);
    }

    /**
     * 是否显示右侧图片按钮
     *
     * @param visibility 显示类型
     */
    public void setRightImageVisibility(int visibility) {
        iv_toolbar_right.setVisibility(visibility);
    }

    /**
     * 设置右侧图片按钮1图标
     *
     * @param resId 图标资源id
     */
    public void setRight1ImageResource(@DrawableRes int resId) {
        iv_toolbar_right_1.setImageResource(resId);
    }

    /**
     * 是否显示右侧图片按钮1
     *
     * @param visibility 显示类型
     */
    public void setRight1ImageVisibility(int visibility) {
        iv_toolbar_right_1.setVisibility(visibility);
    }

    /**
     * 设置右侧图片按钮2图标
     *
     * @param resId 图标资源id
     */
    public void setRight2ImageResource(@DrawableRes int resId) {
        iv_toolbar_right_2.setImageResource(resId);
    }

    /**
     * 是否显示右侧图片按钮2
     *
     * @param visibility 显示类型
     */
    public void setRight2ImageVisibility(int visibility) {
        iv_toolbar_right_2.setVisibility(visibility);
    }

//    /**
//     * 设置右侧图片按钮2的角标
//     *
//     * @param text 按钮文案
//     */
//    public void setRight2BadgeText(String text) {
//        tv_toolbar_right_2_badge.setText(text);
//    }

//    /**
//     * 是否显示右侧图片按钮2的角标
//     *
//     * @param visibility 显示类型
//     */
//    public void setRight2BadgeVisibility(int visibility) {
//        tv_toolbar_right_2_badge.setVisibility(visibility);
//    }

    /**
     * 设置底部的线的颜色
     *
     * @param resId 颜色资源id
     */
    public void setBottomLineResource(@DrawableRes int resId) {
        v_toolbar_bot_line.setBackgroundResource(resId);
    }

    /**
     * 设置底部的线的颜色
     *
     * @param color 颜色Color
     */
    public void setBottomLineColor(@ColorInt int color) {
        v_toolbar_bot_line.setBackgroundColor(color);
    }

    /**
     * 是否显示底部的线
     *
     * @param visibility 显示类型
     */
    public void setBottomLineVisibility(int visibility) {
        v_toolbar_bot_line.setVisibility(visibility);
    }

    /**
     * 沉浸状态栏图片模式下，设置Toolbar的paddingTop，解决布局内容显示到状态栏的问题
     */
    public void setPaddingTop() {
        toolbar.setPaddingTop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_toolbar_back:
            case R.id.tv_toolbar_back_text:
            case R.id.tv_toolbar_right:
            case R.id.iv_toolbar_right:
            case R.id.iv_toolbar_right_1:
            case R.id.iv_toolbar_right_2:
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(v);
                }
                break;
            default:
                break;
        }
    }
}
