package com.blueeleven.viewdrawdemo.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Author: lijiajie
 * Date: 2019/3/15
 * Desc:
 */
public class PathView extends View {

  private Paint mPaint;



  public PathView(Context context) {
    super(context);
    init();

  }

  private void init() {
    mPaint = new Paint();
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(1);
    mPaint.setColor(Color.BLACK);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    Path path1 = new Path();
    path1.moveTo(100, 100);
    path1.arcTo(300,300, 300, 300, 0, 180, false);

    canvas.drawPath(path1, mPaint);
  }
}
