package com.yingluo.Appraiser.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.ui.activity.ActivityUserDelails;
import com.yingluo.Appraiser.ui.activity.InformationDetailsActivity;
import com.yingluo.Appraiser.utils.BitmapsUtils;

/**
 * @author ytmfdw 宝贝详情页面，同类宝物布局
 *
 */

public class ViewOtherTreasure extends LinearLayout {

	// screen height,and width,in px

	BitmapsUtils bitmapUtils;

	@ViewInject(R.id.layout_other_treasure)
	RelativeLayout iv;
	
	@ViewInject(R.id.tv_msg)
	TextView tv_msg;


	private CollectionTreasure currnt;

	public ViewOtherTreasure(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewOtherTreasure(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewOtherTreasure(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	private void init(Context context) {
		LayoutInflater.from(context).inflate(R.layout.layout_other_treasure_, this);
		ViewUtils.inject(this);
	}

	public void setItem(CollectionTreasure item) {
		if (item == null) {
			LogUtils.e("Articles  CollectionEntity is null");
			return;
		}
		currnt = item;
		if (bitmapUtils == null) {
			bitmapUtils = BitmapsUtils.getInstance();
		}
		bitmapUtils.display(iv, item.image, iv.getMeasuredWidth(), iv.getMeasuredHeight());
		tv_msg.setText(item.name);
	}

	/**
	 * 高亮显示
	 *
	 * @param all
	 *            全部文字
	 * @param target
	 *            需要加高亮文字内容
	 * @param color
	 *            高亮颜色
	 * @return
	 */
	private SpannableStringBuilder highlightText(String all, String target,
			int color) {
		SpannableStringBuilder spannable = new SpannableStringBuilder(all);
		CharacterStyle span = null;
		Pattern p = Pattern.compile(target);
		Matcher m = p.matcher(all);
		while (m.find()) {
			span = new ForegroundColorSpan(getContext().getResources()
					.getColor(color));// 需要重复！
			spannable.setSpan(span, m.start(), m.end(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		return spannable;
	}

	@OnClick({ R.id.iv_item3 })
	public void onclick(View v) {
		if (currnt == null) {
			return;
		}
		switch (v.getId()) {
		case R.id.iv_item3: {
//			ContentInfo contentInfo = (ContentInfo) v.getTag();
			Intent intent = new Intent(getContext(),
					ActivityUserDelails.class);
			intent.putExtra(Const.ENTITY, currnt);
			getContext().startActivity(intent);
		}
			break;

		default:
			break;
		}
	}
}
