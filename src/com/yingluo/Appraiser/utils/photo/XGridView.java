package com.yingluo.Appraiser.utils.photo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class XGridView extends GridView {

	public XGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public XGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public XGridView(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
