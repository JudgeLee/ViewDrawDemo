package com.blueeleven.viewdrawdemo.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;
import com.blueeleven.viewdrawdemo.R;
import com.blueeleven.viewdrawdemo.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: lijiajie
 * Date: 2019/3/7
 * Desc:
 */
public class CobwebView extends View {

  private Paint mPaint;

  private float mHexagonradius;

  private Path mHexagonPath;
  private Path mLinePath;
  private Path mRegionPath;

  private List<Point> mPoints;
  private List<Point> mRegionPoints;

  private int[] mRegionPositions;

  public CobwebView(Context context) {
    this(context, new int[] { 0, 1, 2, 3, 4, 5 });
  }

  public CobwebView(Context context, int[] regionPositions) {
    super(context);
    mRegionPositions = regionPositions;
    init();
  }

  private void init() {
    mPaint = new Paint();
    mPaint.setColor(getContext().getResources().getColor(R.color.black));
    //mPaint.setStyle(Paint.Style.FILL);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeCap(Paint.Cap.ROUND);

    mPoints = new ArrayList<>();
    mRegionPoints = new ArrayList<>();

    mHexagonradius = UIUtils.dp2px(getContext(), 100);
    mHexagonPath = new Path();
    mHexagonPath.moveTo(mHexagonradius, 0);
    for (int i = 1; i <= 6; i++) {
      float pointX = (float) (mHexagonradius * Math.cos(i * Math.PI / 3));
      float pointY = (float) (mHexagonradius * Math.sin(i * Math.PI / 3));
      mPoints.add(new Point(((int) pointX), ((int) pointY)));
      mHexagonPath.lineTo(pointX, pointY);
    }
    mHexagonPath.close();

    mLinePath = new Path();
    mLinePath.moveTo(-mHexagonradius, 0);
    mLinePath.lineTo(mHexagonradius, 0);

    mRegionPath = new Path();
    for (int i = 0; i < mRegionPositions.length; i++) {
      Point point = mPoints.get(mRegionPositions[i]);
      mRegionPoints.add(point);
      if (i == 0) {
        mRegionPath.moveTo((float) point.x, (float) point.y);
      } else {
        mRegionPath.lineTo((float) point.x, (float) point.y);
      }
    }
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.translate(getWidth() / 2.0f, UIUtils.dp2px(getContext(), 200));
    canvas.save();
    canvas.drawPath(mHexagonPath, mPaint);

    for (int i = 0; i < 3; i++) {
      canvas.drawPath(mLinePath, mPaint);
      canvas.rotate(60);
    }

    canvas.restore();
    canvas.save();

    for (int i = 1; i <= 5; i++) {
      float scale = (1 - 0.2f * i) / (1 - 0.2f * (i - 1));
      canvas.scale(scale, scale);
      canvas.drawPath(mHexagonPath, mPaint);
    }

    canvas.restore();
    mPaint.setStrokeCap(Paint.Cap.ROUND);
    mPaint.setStrokeWidth(UIUtils.dp2px(getContext(), 6));
    mPaint.setColor(Color.BLACK);
    for (Point point : mRegionPoints) {
      canvas.drawPoint(point.x, point.y, mPaint);
    }

    mPaint.setColor(0xCC0000FF);
    mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    mPaint.setStrokeWidth(1);
    canvas.drawPath(mRegionPath, mPaint);
  }
}
