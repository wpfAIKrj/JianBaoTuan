package com.yingluo.Appraiser.view;

import com.yingluo.Appraiser.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ImageViewWithBorder extends ImageView {

	private int co;
	private int borderwidth;

	public ImageViewWithBorder(Context context) {
		super(context);
	}

	public ImageViewWithBorder(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ImageViewWithBorder);
		int width = a.getDimensionPixelSize(0, 1);
		setBorderWidth(width);
		int color = a.getColor(1,
				getResources().getColor(R.color.item_space_color));
		setColour(color);
		a.recycle();

	}

	public ImageViewWithBorder(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	// 设置颜色
	public void setColour(int color) {
		co = color;
	}

	// 设置边框宽度
	public void setBorderWidth(int width) {

		borderwidth = width;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 画边框
		Rect rec = canvas.getClipBounds();
		rec.bottom--;
		rec.right--;
		Paint paint = new Paint();
		// 设置边框颜色
		paint.setColor(co);
		paint.setStyle(Paint.Style.STROKE);
		// 设置边框宽度
		paint.setStrokeWidth(borderwidth);
		canvas.drawRect(rec, paint);
	}
}
