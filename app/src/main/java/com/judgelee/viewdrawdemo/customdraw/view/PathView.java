package com.judgelee.viewdrawdemo.customdraw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorRes;
import android.view.View;
import android.widget.Toast;

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
    mPaint.setStrokeWidth(3);
    mPaint.setColor(Color.BLACK);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.drawLine(800, 0, 800, 400, mPaint);
    canvas.drawLine(600, 200, 1000, 200, mPaint);
    canvas.drawLine(0, 500, 1070, 500, mPaint);


    canvas.save();
    //canvas.translate(500, 500);


    Path path1 = new Path();
    path1.moveTo(100, 100);
    path1.arcTo(200, 200, 300, 300, 0, 180, false);

    //canvas.drawPath(path1, mPaint);

    Path path2 = new Path();
    //path2.moveTo(200, 400);
    //path2.addRoundRect(200, 400, 400, 600, 10, 10, Path.Direction.CW);
    path2.addRect(200, 400, 400, 600 , Path.Direction.CW);
    //path2.addRect(0, 0, 200, 200 , Path.Direction.CW);
    //path2.setLastPoint(500, 550);
    canvas.save();

    //Toast.makeText(getContext(), "isConvex" + path2.isConvex(), Toast.LENGTH_SHORT).show();
    canvas.drawPath(path2, mPaint);
    canvas.save();
    // 错切矩阵
    // 1 sy
    // sx 1
    //canvas.skew(1, 0);
    //canvas.skew(0, -1);
    canvas.skew(1, -1);
    //path2.addPath(path1, 100 , 200);

    mPaint.setColor(Color.RED);
    canvas.drawPath(path2, mPaint);

    canvas.restore();
    canvas.skew(1, -1);
    //canvas.skew(1, (float) Math.sqrt(3));
    //canvas.skew(1, 0);
    //canvas.skew(0, -1);
    mPaint.setColor(Color.GREEN);
    //mPaint.setStyle(Paint.Style.FILL);
    //canvas.drawPath(path2, mPaint);

    mPaint.setStyle(Paint.Style.STROKE);

    canvas.restore();
    canvas.restore();
    canvas.translate(0, 600);
    Path path3 = new Path();
    path3.arcTo(200, 100, 400, 300, 90, 180, true);
    path3.arcTo(250, 100, 350, 200, -90, 180, false);
    path3.arcTo(250, 200, 350, 300, -90, -180, true);
    mPaint.setStyle(Paint.Style.FILL);
    canvas.drawPath(path3, mPaint);

    canvas.translate(500, 0);

    Path path4 = new Path();
    Path path5 = new Path();
    Path path6 = new Path();
    Path path7 = new Path();

    path4.addCircle(200, 200, 200, Path.Direction.CW);
    path5.addRect(0, 0, 200, 400, Path.Direction.CW);
    path6.addCircle(200, 100, 100, Path.Direction.CW);
    path7.addCircle(200, 300, 100, Path.Direction.CW);
    path4.op(path5, Path.Op.INTERSECT);
    path4.op(path6, Path.Op.UNION);
    path4.op(path7, Path.Op.DIFFERENCE);

    canvas.save();
    canvas.scale(-1, -1, 200, 200);
    canvas.drawPath(path4, mPaint);

    canvas.restore();
    canvas.translate(-500, 500);
    Path path8 = new Path();
    Path path9 = new Path();
    path8.addCircle(200, 200, 200, Path.Direction.CW);
    path9.addCircle(200, 200, 100, Path.Direction.CW);
    //path9.addCircle(800, 200, 200, Path.Direction.CW);
    path8.addPath(path9);
    //path8.setFillType(Path.FillType.WINDING);
    //path8.setFillType(Path.FillType.INVERSE_EVEN_ODD);
    path8.setFillType(Path.FillType.WINDING);
    canvas.drawPath(path8, mPaint);

    canvas.translate(500, 0);
    path8.setFillType(Path.FillType.EVEN_ODD);
    canvas.drawPath(path8, mPaint);
  }
}
