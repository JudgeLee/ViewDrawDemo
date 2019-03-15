package com.blueeleven.viewdrawdemo.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;
import com.blueeleven.viewdrawdemo.utils.UIUtils;

/**
 * Author: lijiajie
 * Date: 2019/3/7
 * Desc:
 */
public class StopWatchView extends View {

  private Paint mPaint;
  private Paint mTextPaint;
  private Paint mThinDegreePaint;
  private Paint mThickDegreePaint;

  private float mRadius;

  private float shortDegreeLength;
  private float mediumDegreeLength;
  private float mLongDegreeLength;
  private float mTextEnd;

  private Path mClockHandPath;
  private int mClockHandRadius;
  private Path mShortDegreePath;
  private Path mMediumDegreePath;
  private Path mLongDegreePath;

  public StopWatchView(Context context) {
    super(context);
    init();
  }

  private void init() {
    mPaint = new Paint();
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setColor(Color.BLACK);
    mPaint.setAntiAlias(true);
    mPaint.setStrokeCap(Paint.Cap.ROUND);
    mPaint.setStrokeWidth(UIUtils.dp2px(getContext(), 1));

    mTextPaint = new Paint(mPaint);
    mTextPaint.setStyle(Paint.Style.FILL);
    mTextPaint.setTextSize(UIUtils.dp2px(getContext(), 14));
    mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);

    mThinDegreePaint = new Paint(mPaint);
    mThinDegreePaint.setColor(0xFF9297A6);

    mThickDegreePaint = new Paint(mPaint);
    mThickDegreePaint.setColor(0xFF4A4E59);

    mRadius = UIUtils.dp2px(getContext(), 150);
    mClockHandRadius = UIUtils.dp2px(getContext(), 2.5);
    shortDegreeLength = UIUtils.dp2px(getContext(), 10);
    mediumDegreeLength = UIUtils.dp2px(getContext(), 15);
    mLongDegreeLength = UIUtils.dp2px(getContext(), 20);
    mTextEnd = mRadius - mLongDegreeLength - UIUtils.dp2px(getContext(), 15);

    mShortDegreePath = new Path();
    mShortDegreePath.moveTo(mRadius, 0);
    mShortDegreePath.lineTo(mRadius - shortDegreeLength, 0);

    mMediumDegreePath = new Path();
    mMediumDegreePath.moveTo(mRadius, 0);
    mMediumDegreePath.lineTo(mRadius - mediumDegreeLength, 0);

    mLongDegreePath = new Path();
    mLongDegreePath.moveTo(mRadius, 0);
    mLongDegreePath.lineTo(mRadius - mLongDegreeLength, 0);

    mClockHandPath = new Path();
    mClockHandPath.moveTo(0, -mClockHandRadius);
    mClockHandPath.lineTo(0, -mClockHandRadius - UIUtils.dp2px(getContext(), 3));
    Path path1 = new Path();
    path1.addCircle(0, 0, mClockHandRadius, Path.Direction.CCW);
    Path path2 = new Path();
    path2.moveTo(0, mClockHandRadius);
    path2.lineTo(0, mRadius);
    mClockHandPath.addPath(path1);
    mClockHandPath.addPath(path2);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.translate(getWidth() / 2.0f, UIUtils.dp2px(getContext(), 200));


    canvas.save();
    canvas.translate(UIUtils.dp2px(getContext(), -75), UIUtils.dp2px(getContext(), -75));
    Path path = new Path();
    path.addArc(new RectF(0, 0, UIUtils.dp2px(getContext(), 150), UIUtils.dp2px(getContext(), 150)), -180,360);
    canvas.drawPath(path, mPaint);

    Paint textPaint = new Paint(mPaint);
    textPaint.setTextSize(UIUtils.dp2px(getContext(), 20));
    textPaint.setStyle(Paint.Style.FILL);
    textPaint.setColor(Color.BLACK);
    textPaint.setTextAlign(Paint.Align.CENTER);
    canvas.drawTextOnPath("MyXYZgLjk", path, 0, 0, textPaint);
    canvas.restore();


    Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();

    for (int i = 1; i <= 12; i++) {
      String text = String.valueOf(i * 5);
      float textWidth = mTextPaint.measureText(text);
      float textX = (float) (mTextEnd * Math.cos((-90 + i * 360.0 / 12.0) / 180 * Math.PI)) - textWidth / 2;
      float textY = (float) (mTextEnd * Math.sin((-90 + i * 360.0 / 12.0) / 180 * Math.PI))
          - (fontMetrics.ascent + fontMetrics.descent) / 2;
      canvas.drawText(text, textX, textY, mTextPaint);
    }


    canvas.rotate(-90, 0, 0);

    for (int i = 1; i <= 300; i++) {
      canvas.rotate(360.0f / 300, 0, 0);
      if (i % 25 == 0) {
        canvas.drawPath(mLongDegreePath, mThickDegreePaint);
      } else if (i % 5 == 0) {
        canvas.drawPath(mMediumDegreePath, mThinDegreePaint);
      } else {
        canvas.drawPath(mShortDegreePath, mThinDegreePaint);
      }
    }

    mPaint.setColor(0xFF00BFFF);
    canvas.drawPath(mClockHandPath, mPaint);



  }
}
