package com.yingluo.Appraiser.view;

import com.yingluo.Appraiser.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class SpringProgressView extends View {

	/** 进度条最大值 */
	private float maxCount;
	/** 进度条当前值 */
	private float currentCount;
	/** 画笔 */
	private Paint mPaint;
	private int mWidth, mHeight;

	public SpringProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);
	}

	public SpringProgressView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public SpringProgressView(Context context) {
		super(context);
		initView(context);
	}

	private void initView(Context context) {
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		
		int round = mHeight / 2;
		
		mPaint.setColor(getResources().getColor(R.color.new_line_color));
		mPaint.setStyle(Paint.Style.STROKE);
		RectF rectBg = new RectF(0, 0, mWidth, mHeight);
		canvas.drawRoundRect(rectBg, round, round, mPaint);

		float section = currentCount / maxCount;
		RectF rectProgressBg = new RectF(2, 2, (mWidth - 2) * section, mHeight - 2);
		mPaint.setColor(getResources().getColor(R.color.new_line_color));
		mPaint.setStyle(Paint.Style.FILL);
		canvas.drawRoundRect(rectProgressBg, round, round, mPaint);
	}

	private int dipToPx(int dip) {
		float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
	}

	/***
	 * 设置最大的进度值
	 * 
	 * @param maxCount
	 */
	public void setMaxCount(float maxCount) {
		this.maxCount = maxCount;
	}

	/***
	 * 设置当前的进度值
	 * 
	 * @param currentCount
	 */
	public void setCurrentCount(float currentCount) {
		this.currentCount = currentCount > maxCount ? maxCount : currentCount;
		invalidate();
	}

	public float getMaxCount() {
		return maxCount;
	}

	public float getCurrentCount() {
		return currentCount;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
		if (widthSpecMode == MeasureSpec.EXACTLY || widthSpecMode == MeasureSpec.AT_MOST) {
			mWidth = widthSpecSize;
		} else {
			mWidth = 0;
		}
		if (heightSpecMode == MeasureSpec.AT_MOST || heightSpecMode == MeasureSpec.UNSPECIFIED) {
			mHeight = dipToPx(15);
		} else {
			mHeight = heightSpecSize;
		}
		setMeasuredDimension(mWidth, mHeight);
	}

}
