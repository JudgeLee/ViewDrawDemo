package com.judgelee.viewdrawdemo.customdraw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.view.View;

/**
 * Author: judgelee
 * Date: 2019/3/29
 * Desc:
 */
public class LayerView extends View {

  private Paint mPaint;

  public LayerView(Context context) {
    super(context);
    init();
  }

  private void init() {
    mPaint = new Paint();
    mPaint.setColor(Color.BLUE);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.drawColor(Color.RED);
    canvas.saveLayer(100, 100, 1000, 800, mPaint);
    canvas.drawColor(Color.GRAY);
    canvas.drawColor(Color.BLUE, PorterDuff.Mode.DST);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setColor(Color.GREEN);
    mPaint.setStrokeWidth(10);
    // 超过指定图层区域的部分不会绘制出来
    canvas.drawRect(0, 0, 1000, 1000,mPaint);
    //canvas.drawRect(100, 100, 1000, 800, mPaint);

    canvas.restore();
    //canvas.drawColor(Color.BLACK);
  }
}
