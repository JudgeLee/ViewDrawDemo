package com.blueeleven.viewdrawdemo.linearlayout.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.blueeleven.viewdrawdemo.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Author: lijiajie
 * Date: 2019/3/1
 * Desc: 尝试自定义线性布局
 */
public class CustomLinearLayout extends ViewGroup {

  public static final int HORIZONTAL = 0;
  public static final int VERTICAL = 1;

  @IntDef({ HORIZONTAL, VERTICAL })
  @Retention(RetentionPolicy.SOURCE)
  public @interface OrientationMode {

  }

  private static boolean sRemeasureWeightedChildren = true;

  private int mOrientation;
  private int mTotalLength;
  private int mGravity = Gravity.START | Gravity.TOP;

  private int mWeightSum;

  private boolean mUseLargestChild;

  public CustomLinearLayout(Context context) {
    this(context, null);
  }

  public CustomLinearLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CustomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    this(context, attrs, defStyleAttr, 0);
  }

  public CustomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);

    final TypedArray a =
        context.obtainStyledAttributes(attrs, R.styleable.CustomLinearLayout, defStyleAttr, defStyleRes);

    int orientation = a.getInt(R.styleable.CustomLinearLayout_orientation, -1);
    if (orientation >= 0) {
      setOrientation(orientation);
    }

    int gravity = a.getInt(R.styleable.CustomLinearLayout_gravity, -1);
    if (gravity >= 0) {
      setGravity(gravity);
    }

    mUseLargestChild = a.getBoolean(R.styleable.CustomLinearLayout_measureWithLargestChild, false);

    a.recycle();
  }

  public void setOrientation(@OrientationMode int orientation) {
    if (this.mOrientation != orientation) {
      this.mOrientation = orientation;
      requestLayout();
    }
  }

  private void setGravity(int gravity) {
    if (mGravity != gravity) {
      if ((gravity & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK) == 0) {
        gravity |= Gravity.START;
      }

      if ((gravity & Gravity.VERTICAL_GRAVITY_MASK) == 0) {
        gravity |= Gravity.TOP;
      }

      mGravity = gravity;
      requestLayout();
    }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    if (mOrientation == VERTICAL) {
      measureVertical(widthMeasureSpec, heightMeasureSpec);
    } else {
      measureHorizontal(widthMeasureSpec, heightMeasureSpec);
    }
  }

  private void measureVertical(int widthMeasureSpec, int heightMeasureSpec) {
    mTotalLength = 0;
    int maxWidth = 0;
    int alternativeMaxWidth = 0;
    int weightedMaxWidth = 0;
    float totalWeight = 0;
    int consumedExcessSpace = 0;
    boolean allFillParent = true;
    int childState = 0;

    final boolean useLargestChild = mUseLargestChild;

    int largestChildHeight = Integer.MIN_VALUE;

    boolean skippedMeasure = false;
    boolean matchWidth = false;

    int widthMode = MeasureSpec.getMode(widthMeasureSpec);
    int heightMode = MeasureSpec.getMode(heightMeasureSpec);

    int childCount = getChildCount();

    for (int i = 0; i < childCount; i++) {
      View child = getChildAt(i);

      if (child == null || child.getVisibility() == GONE) {
        continue;
      }

      LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) child.getLayoutParams();

      totalWeight += lp.weight;
      boolean useExcessSpace = lp.height == 0 && lp.weight > 0;
      if (heightMode == MeasureSpec.EXACTLY && useExcessSpace) {
        final int totalLength = mTotalLength;
        mTotalLength = Math.max(totalLength, totalLength + lp.topMargin + lp.bottomMargin);
        skippedMeasure = true;
      } else {
        if (useExcessSpace) {
          lp.height = LayoutParams.WRAP_CONTENT;
        }

        int usedHeight = totalWeight == 0 ? mTotalLength : 0;
        measureChildBeforeLayout(child, widthMeasureSpec, heightMeasureSpec, 0, usedHeight);

        int childHeight = child.getMeasuredHeight();

        if (useExcessSpace) {
          lp.height = 0;
          consumedExcessSpace += childHeight;
        }

        final int totalLength = mTotalLength;
        mTotalLength = Math.max(totalLength, totalLength + childHeight + lp.topMargin + lp.bottomMargin);

        if (useLargestChild) {
          largestChildHeight = Math.max(largestChildHeight, child.getMeasuredHeight());
        }
      }

      final int margin = lp.leftMargin + lp.rightMargin;
      final int measuredWidth = child.getMeasuredWidth() + margin;
      maxWidth = Math.max(maxWidth, measuredWidth);
      childState = combineMeasuredStates(childState, child.getMeasuredState());

      boolean matchWidthLocally = false;
      if (heightMode != MeasureSpec.EXACTLY && lp.width == LayoutParams.MATCH_PARENT) {
        matchWidth = true;
        matchWidthLocally = true;
      }

      if (lp.weight > 0) {
        weightedMaxWidth = Math.max(weightedMaxWidth, matchWidthLocally ? margin : measuredWidth);
      } else {
        alternativeMaxWidth = Math.max(alternativeMaxWidth, matchWidthLocally ? margin : measuredWidth);
      }

      allFillParent = allFillParent && lp.width == LayoutParams.MATCH_PARENT;
    }

    if (useLargestChild && heightMode != MeasureSpec.EXACTLY) {
      mTotalLength = 0;

      for (int i = 0; i < childCount; i++) {
        View child = getChildAt(i);
        if (child == null || child.getVisibility() == GONE) {
          continue;
        }
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) child.getLayoutParams();
        final int totalLength = mTotalLength;
        mTotalLength = Math.max(totalLength, totalLength + largestChildHeight + lp.topMargin + lp.bottomMargin);
      }
    }

    mTotalLength += getPaddingTop() + getPaddingBottom();

    int heightSize = mTotalLength;
    heightSize = Math.max(heightSize, getSuggestedMinimumHeight());

    int heightSizeAndState = resolveSizeAndState(heightSize, heightMeasureSpec, 0);
    heightSize = heightSizeAndState & MEASURED_SIZE_MASK;

    int remainingExcess = heightSize - mTotalLength + consumedExcessSpace;

    if (skippedMeasure || ((sRemeasureWeightedChildren || remainingExcess != 0) && totalWeight > 0)) {

      float remainingWeightSum = mWeightSum > 0 ? mWeightSum : totalWeight;
      mTotalLength = 0;

      for (int i = 0; i < childCount; i++) {
        View child = getChildAt(i);

        if (child == null || child.getVisibility() == GONE) {
          continue;
        }

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) child.getLayoutParams();
        final float childWeight = lp.weight;
        if (childWeight > 0) {
          int share = (int) (childWeight * remainingExcess / remainingWeightSum);
          int childHeight = child.getMeasuredHeight();

          if (useLargestChild && heightMode != MeasureSpec.EXACTLY) {
            childHeight = largestChildHeight;
          } else if (lp.height == 0) {
            childHeight = share;
          } else {
            childHeight += share;
          }

          int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(Math.max(childHeight, 0), MeasureSpec.EXACTLY);
          child.measure(widthMeasureSpec, childHeightMeasureSpec);
          childState = combineMeasuredStates(childState,
              child.getMeasuredState() & (MEASURED_STATE_MASK >> MEASURED_HEIGHT_STATE_SHIFT));
        }

        final int margin = lp.leftMargin + lp.rightMargin;
        final int measuredWidth = margin + child.getMeasuredWidth();

        maxWidth = Math.max(maxWidth, measuredWidth);
        boolean matchWidthLocally = widthMode != MeasureSpec.EXACTLY && lp.width == LayoutParams.MATCH_PARENT;
        alternativeMaxWidth = Math.max(alternativeMaxWidth, matchWidthLocally ? margin : measuredWidth);

        allFillParent = allFillParent && lp.width == LayoutParams.MATCH_PARENT;

        final int totalLength = mTotalLength;
        mTotalLength = Math.max(totalLength, totalLength + child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
      }

      mTotalLength += getPaddingTop() + getPaddingBottom();
    } else {
      alternativeMaxWidth = Math.max(alternativeMaxWidth, weightedMaxWidth);
    }

    if (!allFillParent && widthMode == MeasureSpec.EXACTLY) {
      maxWidth = alternativeMaxWidth;
    }

    maxWidth += getPaddingLeft() + getPaddingRight();

    maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());

    setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState),
        heightSizeAndState);

    if (matchWidth) {
      forceUniformWidth(childCount, heightMeasureSpec);
    }
  }

  private void forceUniformWidth(int count, int heightMeasureSpec) {
    int uniformMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY);

    for (int i = 0; i < count; i++) {
      View child = getChildAt(i);
      if (child == null || child.getVisibility() == GONE) {
        continue;
      }

      LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) child.getLayoutParams();
      if (lp.width == LayoutParams.MATCH_PARENT) {
        int oldHeight = lp.height;
        lp.height = child.getMeasuredHeight();
        measureChildWithMargins(child, uniformMeasureSpec, 0, heightMeasureSpec, 0);
        lp.height = oldHeight;
      }
    }
  }

  private void measureHorizontal(int widthMeasureSpec, int heightMeasureSpec) {

  }

  private void measureChildBeforeLayout(View child, int widthMeasureSpec, int heightMeasureSpec,
      int usedWidth, int usedHeight) {
    measureChildWithMargins(child, widthMeasureSpec, usedWidth, heightMeasureSpec, usedHeight);
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    if (mOrientation == VERTICAL) {
      layoutVertical(l, t, r, b);
    } else {
      layoutHorizontal(l, t, r, b);
    }
  }

  private void layoutVertical(int left, int top, int right, int bottom) {

    final int width = right - left;

    int childTop;
    int childLeft = getPaddingLeft();

    int childRight = width - getPaddingRight();

    int childSpace = childRight - getPaddingLeft();

    int majorGravity = mGravity & Gravity.VERTICAL_GRAVITY_MASK;
    int minorGravity = mGravity & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK;

    switch (majorGravity) {
      case Gravity.BOTTOM:
        // 保证LinearLayout的内容mTotalLength底部恰好与bottom对齐
        childTop = getPaddingTop() + bottom - top - mTotalLength;
        break;
      case Gravity.CENTER_VERTICAL:
        // 保证LinearLayout的内容mTotalLength中心线恰好和bottom与top的中心对齐
        childTop = getPaddingTop() + (bottom - top - mTotalLength) / 2;
        break;
      case Gravity.TOP:
      default:
        childTop = getPaddingTop();
        break;
    }


    int childCount = getChildCount();

    for (int i = 0; i < childCount; i++) {
      View child = getChildAt(i);

      if (child == null) {
        continue;
      }
      if (child.getVisibility() == GONE) {
        continue;
      }

      LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) child.getLayoutParams();
      int gravity = lp.gravity;
      if (gravity < 0) {
        gravity = minorGravity;
      }

      int childHeight = child.getMeasuredHeight();
      int childWidth = child.getMeasuredWidth();

      int layoutDirection = getLayoutDirection();
      int absoluteGravity = Gravity.getAbsoluteGravity(gravity, layoutDirection);

      // 暗含子View的layout_gravity的垂直方向重心并不会生效
      switch (absoluteGravity & Gravity.HORIZONTAL_GRAVITY_MASK) {
        case Gravity.CENTER_HORIZONTAL:
          // child内容中心线和right-left中心线对齐，此种情况下margin属性会让child内容中心线进行偏移
          childLeft = getPaddingLeft() + (childSpace - childWidth) / 2 + lp.leftMargin - lp.rightMargin;
          break;
        case Gravity.RIGHT:
          childLeft = childRight - childWidth - lp.rightMargin;
          break;
        case Gravity.LEFT:
          childLeft = getPaddingLeft() + lp.leftMargin;
      }

      childTop += lp.topMargin;
      setChildFrame(child, childLeft, childTop, childWidth, childHeight);

      childTop += childHeight + lp.bottomMargin;
    }
  }


  private void layoutHorizontal(int l, int t, int r, int b) {

  }


  private void setChildFrame(View child, int left, int top, int childWidth, int childHeight) {
    child.layout(left, top, left + childWidth, top + childHeight);
  }

  @Override
  public LayoutParams generateLayoutParams(AttributeSet attrs) {
    return new LinearLayout.LayoutParams(getContext(), attrs);
  }

  @Override
  protected LayoutParams generateDefaultLayoutParams() {
    if (mOrientation == VERTICAL) {
      return new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    } else {
      return new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
    }
  }

  @Override
  protected LayoutParams generateLayoutParams(LayoutParams p) {
    if (p instanceof LinearLayout.LayoutParams) {
      return new LinearLayout.LayoutParams(((LinearLayout.LayoutParams) p));
    } else if (p instanceof MarginLayoutParams) {
      return new LinearLayout.LayoutParams(((MarginLayoutParams) p));
    }
    return new LinearLayout.LayoutParams(p);
  }
}
