package com.judgelee.viewdrawdemo.customdraw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

/**
 * Author: lijiajie
 * Date: 2019/3/17
 * Desc:
 */
public class CanvasView extends View {

  private Paint mPaint;

  public CanvasView(Context context) {
    super(context);
    init();
  }

  private void init() {
    mPaint = new Paint();
    //mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setColor(Color.RED);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    Path path = new Path();
    path.addOval(new RectF(100, 100, 1000, 500), Path.Direction.CW);
    Region region = new Region();
    region.setPath(path, new Region(0, 0, 1000, 1000));

    RegionIterator regionIterator = new RegionIterator(region);
    Rect rect = new Rect();
    Random random = new Random();
    while (regionIterator.next(rect)) {
      mPaint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
      canvas.drawRect(rect, mPaint);
    }

    canvas.save();
    canvas.translate(0, 500);
    canvas.drawRect(new Rect(0, -500, 500, 0), mPaint);

    canvas.save();
    canvas.clipRect(new Rect(250, 250, 750, 750));
    canvas.drawColor(Color.BLUE);

    int x = canvas.save(); //返回3
    canvas.clipRect(new Rect(300, 300, 700, 700));
    canvas.drawColor(Color.RED);
    Toast.makeText(getContext(), "x = " + x, Toast.LENGTH_LONG).show();

    canvas.save();
    canvas.clipRect(new Rect(350, 350, 650, 650));
    canvas.drawColor(Color.BLACK);

    canvas.drawText("绘制到区域之外", 100, 100, mPaint); //无效果
    //canvas.drawRoundRect(new RectF(100, 100, 500, 500), 80, 80, mPaint);
    canvas.drawRoundRect(new RectF(0, 0, 500, 500), 80, 80, mPaint);


    canvas.restoreToCount(10); // 大于栈顶，无效果
    //canvas.restore();
    //canvas.restore();
    //canvas.restore();
    //canvas.restore();
    //canvas.restore();
    //canvas.restoreToCount(10);
    //canvas.drawColor(Color.GREEN);

    Matrix matrix = new Matrix();
    matrix.preTranslate(2, 2); // M' = M * T(2, 2)
    matrix.postRotate(30, 2, 2); // M' = R(30, 2, 2) * M

  }
}
