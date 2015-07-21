package com.yingluo.Appraiser.view;

import java.util.ArrayList;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.utils.DensityUtil;
import com.yingluo.Appraiser.utils.SqlDataUtil;

import android.R.integer;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 线下布局
 * 
 * @author xy418
 *
 */
public class TagLinearLayout extends ViewGroup {

	private int textcolor = R.color.wite;
	private int[] textbackgrounds = { R.color.number_color, R.color.bt_color };

	public TagLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TagLinearLayout(Context context) {
		super(context);
	}

	public TagLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthsize = MeasureSpec.getSize(widthMeasureSpec);

		int hightMode = MeasureSpec.getMode(heightMeasureSpec);
		int hightsize = MeasureSpec.getSize(heightMeasureSpec);

		measureChildren(widthMeasureSpec, heightMeasureSpec);

		int count = getChildCount();
		int width = 0;
		int height = 0;
		for (int i = 0; i < count; i++) {
			View child = this.getChildAt(i);
			width = width + child.getMeasuredWidth() + 20;
			height = Math.max(height, child.getMeasuredHeight());
		}

		setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? widthsize : width,
				(hightMode == MeasureSpec.EXACTLY) ? hightsize : height);

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int left = 0;
		int top = (int) getResources().getDimension(R.dimen.y20);
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			child.layout(left, top, left + child.getMeasuredWidth(), top + child.getMeasuredHeight());
			left = left + child.getMeasuredWidth() + top;
		}
	}

	public void addTag(TreasureType type) {
		this.removeAllViews();
		int tagsize = type.getType();
		int size = 0;
		int width = 0;
		int onewidth = (int) getResources().getDimension(R.dimen.x76);
		int height = (int) getResources().getDimension(R.dimen.y40);
		int textsize = DensityUtil.px2sp(getContext(), getResources().getDimension(R.dimen.x24));
		TextView tv;
		String text = null;
		ArrayList<TreasureType> list = SqlDataUtil.getInstance().getTreasureTypeByChild(type);
		for (int i = list.size() - 1; 0 <= i; i--) {
			TreasureType cunrrnt = list.get(i);
			tv = new TextView(getContext());
			text = cunrrnt.name;
			size = text.length();
			width = (size / 2) * onewidth + (size % 2) * onewidth;
			tv.setWidth(width);
			tv.setHeight(height);
			tv.setGravity(Gravity.CENTER);
			tv.setTextSize(textsize);
			tv.setText(text);
			tv.setTextColor(Color.WHITE);
			tv.setBackgroundResource(textbackgrounds[cunrrnt.type % 2]);
			addView(tv);
		}
		invalidate();

	}
}
