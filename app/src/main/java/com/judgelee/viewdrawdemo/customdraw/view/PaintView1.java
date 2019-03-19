package com.judgelee.viewdrawdemo.customdraw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import com.judgelee.viewdrawdemo.utils.UIUtils;

/**
 * Author: lijiajie
 * Date: 2019/3/7
 * Desc:
 */
public class PaintView1 extends View {

  private Paint mPaint;

  public PaintView1(Context context) {
    super(context);
    init();
  }

  private void init() {
    mPaint = new Paint();
    mPaint.setTextSize(UIUtils.dp2px(14));
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    int width = getWidth();

    mPaint.setColor(Color.BLACK);
    canvas.drawText("STYLE.FILL", 20, 50, mPaint);
    canvas.drawText("STYLE.STROE", 20 + width / 3, 50, mPaint);
    canvas.drawText("STYLE.FILL_AND_STROKE", 20 + width * 2 / 3, 50, mPaint);

    canvas.drawText("Cap.BUTT", 500, 350, mPaint);
    mPaint.setTextAlign(Paint.Align.CENTER);
    canvas.drawText("Cap.ROUND", 500, 550, mPaint);
    mPaint.setTextAlign(Paint.Align.RIGHT);
    canvas.drawText("Cap.SQUARE", 500, 750, mPaint);

    mPaint.setTextAlign(Paint.Align.LEFT);
    canvas.drawText("Join.MITER", 100, 900, mPaint);
    canvas.drawText("Join.BEVEL", 100 + width / 3, 900, mPaint);
    canvas.drawText("Join.ROUND", 100 + width * 2 / 3, 900, mPaint);

    mPaint.setColor(Color.RED);
    mPaint.setStyle(Paint.Style.FILL);
    canvas.drawCircle(100, 160, 80, mPaint);

    mPaint.setStyle(Paint.Style.STROKE);
    canvas.drawCircle(100 + width / 3, 160, 80, mPaint);

    mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    canvas.drawCircle(100 + width * 2 / 3, 160, 80, mPaint);

    mPaint.setStrokeWidth(UIUtils.dp2px(30));
    canvas.drawLine(300, 400, width - 300, 400, mPaint);

    mPaint.setStrokeCap(Paint.Cap.ROUND);
    canvas.drawLine(300, 600, width - 300, 600, mPaint);

    mPaint.setStrokeCap(Paint.Cap.SQUARE);
    canvas.drawLine(300, 800, width - 300, 800, mPaint);

    mPaint.reset();
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setColor(Color.RED);
    mPaint.setStrokeWidth(UIUtils.dp2px(30));

    mPaint.setStrokeJoin(Paint.Join.MITER);
    Path path1 = new Path();
    path1.moveTo(50, 1000);
    path1.lineTo(250, 1000);
    path1.lineTo(150, 1200);
    canvas.drawPath(path1, mPaint);

    mPaint.setStrokeJoin(Paint.Join.BEVEL);
    Path path2 = new Path();
    path2.moveTo(50 + width / 3, 1000);
    path2.lineTo(250 + width / 3, 1000);
    path2.lineTo(150 + width / 3, 1200);
    canvas.drawPath(path2, mPaint);

    mPaint.setStrokeJoin(Paint.Join.ROUND);
    Path path3 = new Path();
    path3.moveTo(50 + width * 2 / 3, 1000);
    path3.lineTo(250 + width * 2 / 3, 1000);
    path3.lineTo(150 + width * 2 / 3, 1200);
    canvas.drawPath(path3, mPaint);

    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(1);
    Path complexPath = new Path();
    Path tempPath = new Path();
    tempPath.moveTo(50, 1300);
    tempPath.lineTo(300, 1500);
    complexPath.addCircle(200, 1400, 100, Path.Direction.CW);
    complexPath.addPath(tempPath);
    canvas.drawPath(complexPath, mPaint);

    canvas.translate(500, 0);
    mPaint.setStyle(Paint.Style.FILL);
    canvas.drawPath(complexPath, mPaint);

  }
}
