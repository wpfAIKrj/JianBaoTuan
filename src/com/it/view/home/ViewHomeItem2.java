package com.it.view.home;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore.Video;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it.R;
import com.it.bean.HomeItem0;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.util.LogUtils;

/**
 * @author ytmfdw 主页 [热门鉴定]下的选项 根据屏幕宽高来计算图片大小
 *
 */

public class ViewHomeItem2 extends LinearLayout {

	// screen height,and width,in px

	BitmapUtils bitmapUtils;

	public ViewHomeItem2(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewHomeItem2(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewHomeItem2(Context context, AttributeSet attrs, int defStyleAttr) {
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
		LayoutInflater.from(context).inflate(R.layout.item_home_3, this);

	}

	public void setItem(HomeItem0 item) {
		if (bitmapUtils == null) {
			bitmapUtils = new BitmapUtils(getContext());
		}

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

}
