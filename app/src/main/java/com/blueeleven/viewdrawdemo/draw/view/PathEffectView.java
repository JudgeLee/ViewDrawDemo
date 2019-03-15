package com.blueeleven.viewdrawdemo.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.SumPathEffect;
import android.view.View;

/**
 * Author: lijiajie
 * Date: 2019/3/8
 * Desc:
 */
public class PathEffectView extends View {

  private Paint mPaint;
  private PathEffect[] mPathEffects;
  private Path mRandomPath;


  public PathEffectView(Context context) {
    super(context);
    init();
  }

  private void init() {
    mPaint = new Paint();
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setColor(Color.BLACK);
    mPaint.setStrokeWidth(3);



    mRandomPath = new Path();
    mRandomPath.moveTo(10, 50);
    for (int i = 3; i < 30; i++) {
      mRandomPath.lineTo(i * 35, (float) (Math.random() * 100));
      //mRandomPath.rLineTo(i * 35, (float) (Math.random() * 100));
    }

    mPathEffects = new PathEffect[7];
    mPathEffects[0] = new PathEffect();
    mPathEffects[1] = new CornerPathEffect(3000);
    mPathEffects[2] = new DiscretePathEffect(10, 10);
    mPathEffects[3] = new DashPathEffect(new float[] { 20, 5 }, 2000);
    Path trianglePath = new Path();
    trianglePath.addCircle(0, 0, 5, Path.Direction.CCW);
    mPathEffects[4] = new PathDashPathEffect(trianglePath, 30, 0, PathDashPathEffect.Style.TRANSLATE);
    mPathEffects[5] = new ComposePathEffect(mPathEffects[3], mPathEffects[1]);
    mPathEffects[6] = new SumPathEffect(mPathEffects[3], mPathEffects[1]);

  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    for (int i = 0; i < 7; i++) {
      mPaint.setPathEffect(mPathEffects[i]);
      canvas.translate(0, 100);

      if (i == 6) {
        mRandomPath.offset(0, 300);
        mRandomPath.lineTo(30, 20);
      }


      canvas.drawPath(mRandomPath, mPaint);

      if (i == 6) {
        RectF rectF = new RectF();
        mRandomPath.computeBounds(rectF, true);
        mPaint.setPathEffect(new PathEffect());
        canvas.drawRect(rectF, mPaint);
      }
    }

  }
}
