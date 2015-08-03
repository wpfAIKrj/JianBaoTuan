package com.yingluo.Appraiser.view;

import java.util.ArrayList;
import java.util.List;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.util.LogUtils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 主界面tab
 * 
 * @author Gong
 * 
 */
public class MyTabWidget extends LinearLayout {

	private static final String TAG = "MyTabWidget";
	private int[] mDrawableIds = new int[] { R.drawable.tag_home, R.drawable.tag_wenxue, R.drawable.tag_tool,
			R.drawable.tag_my};
	private List<CheckedTextView> mCheckedList = new ArrayList<CheckedTextView>();
	private List<ImageView> mIndicateImgs = new ArrayList<ImageView>();
	private CharSequence[] mLabels;
	private View fatherview;

	@TargetApi(11)
	public MyTabWidget(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TabWidget, defStyle, 0);
		mLabels = context.getResources().getStringArray(R.array.bottom_bar_labels);

		if (null == mLabels || mLabels.length <= 0) {
			try {
				throw new Exception("异常提醒");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				LogUtils.i(MyTabWidget.class.getSimpleName() + "类名");
			}
			a.recycle();
			return;
		}

		a.recycle();

		init(context);
	}

	public MyTabWidget(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyTabWidget(Context context) {
		super(context);
		init(context);
	}

	private void init(final Context context) {

		// 父类
		fatherview = LinearLayout.inflate(context, R.layout.tab_layout, this);

		int size = mLabels.length;
		for (int i = 0; i < size; i++) {
			final int index = i;
			final View view = fatherview.findViewById(R.id.tabwidget01 + i);

			final CheckedTextView itemName = (CheckedTextView) view.findViewById(R.id.item_name);

			itemName.setCompoundDrawablesWithIntrinsicBounds(null, context.getResources().getDrawable(mDrawableIds[i]),
					null, null);

			itemName.setText(mLabels[i]);

			final ImageView indicateImg = (ImageView) view.findViewById(R.id.tab_item_img);
			itemName.setTag(index);
			mCheckedList.add(itemName);
			mIndicateImgs.add(indicateImg);

			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					setTabsDisplay(context, index);

					if (null != mTabListener) {

						mTabListener.onTabSelected(index);
					}
				}
			});

			if (i == 0) {
				itemName.setChecked(true);
				// 修改点击颜色
				itemName.setTextColor(getResources().getColor(R.color.home_head_color));
			} else {

				itemName.setChecked(false);
				// 没有选择的颜色
				itemName.setTextColor(Color.rgb(169, 169, 169));
			}
		}

	}

	public void setIndicateDisplay(Context context, int position, boolean visible) {
		int size = mIndicateImgs.size();
		if (size <= position) {
			return;
		}
		ImageView indicateImg = mIndicateImgs.get(position);
		indicateImg.setVisibility(visible ? View.VISIBLE : View.GONE);
	}

	public void setTabsDisplay(Context context, int index) {
		int size = mCheckedList.size();
		for (int i = 0; i < size; i++) {
			CheckedTextView checkedTextView = mCheckedList.get(i);
			if ((Integer) (checkedTextView.getTag()) == index) {
				LogUtils.d(mLabels[index] + " is selected...");
				checkedTextView.setChecked(true);
				checkedTextView.setTextColor(getResources().getColor(R.color.home_head_color));
			} else {
				checkedTextView.setChecked(false);
				checkedTextView.setTextColor(Color.rgb(169, 169, 169));
			}
		}
	}

	private OnTabSelectedListener mTabListener;

	public interface OnTabSelectedListener {
		void onTabSelected(int index);
	}

	public void setOnTabSelectedListener(OnTabSelectedListener listener) {
		this.mTabListener = listener;
	}

}
