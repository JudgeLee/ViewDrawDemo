package com.judgelee.viewdrawdemo.customdraw.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.judgelee.viewdrawdemo.R;
import com.judgelee.viewdrawdemo.utils.UIUtils;

import java.math.BigInteger;

/**
 * Author: lijiajie
 * Date: 2019/3/19
 * Desc: Shader 着色器，Paint进行绘制时选择的颜色
 */
public class ShaderView extends View {

  private Paint mPaint;

  public ShaderView(Context context) {
    this(context, null);
  }

  public ShaderView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    mPaint = new Paint();
    //mPaint.setStyle(Paint.Style.STROKE); //只有描边效果
    //mPaint.setColor(Color.RED);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.save();
    canvas.scale(2, 2);
    canvas.translate(20, 20);

    Bitmap itunesBm = UIUtils.createBitmap(R.drawable.ic_itunes);
    mPaint.setShader(new BitmapShader(itunesBm, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)); // 右下角使用右下角颜色
    canvas.drawRect(0, 0, itunesBm.getWidth() * 15f, itunesBm.getHeight() * 1.5f, mPaint);

    canvas.translate(0, 120);
    mPaint.setShader(new BitmapShader(itunesBm, Shader.TileMode.CLAMP, Shader.TileMode.MIRROR));
    canvas.drawRect(0, 0, itunesBm.getWidth() * 1.5f, itunesBm.getHeight() * 1.5f, mPaint);

    canvas.translate(100, 0);
    canvas.drawCircle(itunesBm.getWidth() / 2, itunesBm.getWidth() / 2, itunesBm.getWidth() / 2, mPaint);
    canvas.drawCircle(50, 100, itunesBm.getWidth() / 2, mPaint);

    canvas.translate(100, 0);
    mPaint.setShader(new BitmapShader(itunesBm, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR));
    canvas.drawRect(0, 0, itunesBm.getWidth() * 1.5f, itunesBm.getHeight() * 1.5f, mPaint);

    // paint 主要是用来设置antiAlias和ColorFilter
    canvas.drawBitmap(UIUtils.createBitmap(R.drawable.ic_apple_music), 0, 0, mPaint);

    canvas.translate(-220, 150);
    //mPaint.setShader(new LinearGradient(0, 0, 100, 100, Color.BLUE, Color.GREEN, Shader.TileMode.CLAMP));
    mPaint.setShader(new LinearGradient(0, 0, 100, 100, Color.BLUE, Color.GREEN, Shader.TileMode.MIRROR));
    canvas.drawRect(0, 0, 150, 150, mPaint);


    canvas.translate(150, 0);
    mPaint.setShader(new LinearGradient(0, 0, 100, 100, Color.BLUE, Color.GREEN, Shader.TileMode.MIRROR));
    canvas.drawRect(0, 0, 200, 200, mPaint);
    //canvas.drawRect(0, 0, 300, 300, mPaint);
    //canvas.drawRect(-300, -300, 300, 300, mPaint);


    canvas.translate(200, 0);
    mPaint.setShader(new LinearGradient(0, 0, 100, 100, Color.BLUE, Color.GREEN, Shader.TileMode.REPEAT));
    //canvas.drawRect(0, 0, 200, 200, mPaint);
    canvas.drawRect(0, 0, 200, 200, mPaint);

    canvas.translate(-350, 200);
    mPaint.setShader(new RadialGradient(50, 50, 25, Color.BLUE, Color.GREEN, Shader.TileMode.CLAMP));
    canvas.drawRect(0, 0, 100, 100, mPaint);


    canvas.translate(100, 0);
    mPaint.setShader(new RadialGradient(50, 50, 25, Color.BLUE, Color.GREEN, Shader.TileMode.MIRROR));
    canvas.drawRect(0, 0, 100, 100, mPaint);


    canvas.translate(100, 0);
    mPaint.setShader(new RadialGradient(50, 50, 25, Color.BLUE, Color.GREEN, Shader.TileMode.REPEAT));
    canvas.drawRect(0, 0, 100, 100, mPaint);

    canvas.translate(-200, 100);
    mPaint.setShader(new SweepGradient(100, 100, Color.RED, Color.GREEN));
    canvas.drawRect(0, 0, 200, 200, mPaint);

    canvas.translate(200, 0);
    mPaint.setShader(new SweepGradient(100, 100, Color.RED, Color.GREEN));
    canvas.drawCircle(100, 100, 100,  mPaint);

    canvas.translate(200, 0);
    mPaint.setShader(new SweepGradient(100, 100, new int[]{Color.RED, Color.BLACK, Color.GREEN}, new float[]{0, 0.3f, 1}));
    canvas.drawCircle(100, 100, 100,  mPaint);

    //ComposeShader 混合Shader 跟XferMode相关
    canvas.restore();

    canvas.translate(500, 500);
    BitmapShader bitmapShader = new BitmapShader(UIUtils.createBitmap(R.drawable.ic_love), Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    LinearGradient linearGradient = new LinearGradient(0, 0, UIUtils.dp2px(24), UIUtils.dp2px(24), Color.RED, Color.YELLOW, Shader.TileMode.REPEAT);
    ComposeShader composeShader = new ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.MULTIPLY);
    mPaint.setShader(composeShader);
    canvas.drawRect(0, 0, UIUtils.dp2px(24), UIUtils.dp2px(24), mPaint);


  }



}
