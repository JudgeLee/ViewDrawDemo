package com.judgelee.viewdrawdemo.customdraw.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Author: lijiajie
 * Date: 2019/3/19
 * Desc: 实现圆角矩形/椭圆/圆形 图片
 *    其他实现方式：图层覆盖
 */
public class ShapeImageView extends AppCompatImageView {

  public ShapeImageView(Context context) {
    this(context, null);
  }

  public ShapeImageView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ShapeImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }
}
