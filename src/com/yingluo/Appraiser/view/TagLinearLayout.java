package com.yingluo.Appraiser.view;

import java.util.ArrayList;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.utils.DensityUtil;
import com.yingluo.Appraiser.utils.SqlDataUtil;

import de.greenrobot.dao.internal.SqlUtils;
import android.R.integer;
import android.content.Context;
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
 * @author xy418
 *
 */
public class TagLinearLayout extends ViewGroup {

	private int textcolor=R.color.wite;
	private int[] textbackgrounds={R.color.number_color,R.color.bt_color};
	
	public TagLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public TagLinearLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public TagLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		// TODO 自动生成的方法存根
		return new MarginLayoutParams(getContext(),attrs);
	}

	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int widthMode=MeasureSpec.getMode(widthMeasureSpec);
		int widthsize=MeasureSpec.getSize(widthMeasureSpec);
		
		int hightMode=MeasureSpec.getMode(heightMeasureSpec);
		int hightsize=MeasureSpec.getSize(heightMeasureSpec);
		
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		
		int count=getChildCount();
		int width=0;
		int height=0;
		for (int i = 0; i < count; i++) {
			View child=this.getChildAt(i);
			width=width+child.getMeasuredWidth()+20;
			height=Math.max(height, child.getMeasuredHeight());
		}
		
		setMeasuredDimension((widthMode==MeasureSpec.EXACTLY)?widthsize:width,
				(hightMode==MeasureSpec.EXACTLY)?hightsize:height);
	}
	
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO 自动生成的方法存根
		int left=0;
		int top=0;
		int right=0;
		int bottom=0;
		int count=getChildCount();
		for (int i = 0; i < count; i++) {
			View child=getChildAt(i);

			right=left+child.getMeasuredWidth();
			bottom=child.getMeasuredHeight();
			child.layout(left, top, right, bottom);
			left=left+child.getMeasuredWidth()+20;
		}
	}
	
	
	
	public void addTag(TreasureType type){
		this.removeAllViews();
		int tagsize=type.getType();
		int size=0;
		int width=0;
		int onewidth=DensityUtil.dip2px(getContext(), 36);
		TextView tv;
		String text=null;
		ArrayList<TreasureType> list=SqlDataUtil.getInstance().getTreasureTypeByChild(type);
		for (int i = list.size()-1; 0<=i; i--) {
			TreasureType cunrrnt=list.get(i);
			tv=new TextView(getContext());
			text=cunrrnt.name;
			size=text.length();
			width=(size/2)*90+(size%2)*90;
			tv.setWidth(width);
			tv.setGravity(Gravity.CENTER);
			tv.setTextSize(DensityUtil.sp2px(getContext(),8));
			tv.setText(text);
			tv.setTextColor(textcolor);
			tv.setBackgroundResource(textbackgrounds[cunrrnt.type%2]);
			addView(tv);
		}
		invalidate();
		
	}
}
