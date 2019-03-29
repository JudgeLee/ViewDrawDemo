package com.judgelee.viewdrawdemo.customdraw.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.judgelee.viewdrawdemo.R;

/**
 * Author: lijiajie
 * Date: 2019/3/20
 * Desc:
 */
public class TestCustomView extends View {

  private Drawable mDr;

  public TestCustomView(Context context) {
    this(context, null);
  }

  public TestCustomView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TestCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    // 必须包含attrs
    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TestCustomView);
    mDr = a.getDrawable(R.styleable.TestCustomView_src);
    a.recycle();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (mDr != null) {
      mDr.setBounds(0, 0, mDr.getIntrinsicWidth(), mDr.getIntrinsicHeight());
      mDr.draw(canvas);
    }
  }
}
