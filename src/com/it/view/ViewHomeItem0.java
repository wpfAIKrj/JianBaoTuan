package com.it.view;

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
import com.it.utils.LogUtils;

/**
 * @author ytmfdw 主页 [精品藏品]下的选项 根据屏幕宽高来计算图片大小
 *
 */

public class ViewHomeItem0 extends LinearLayout {

	// screen height,and width,in px
	int screen_h, screen_w;
	// iv_big-w:h=348:240
	// iv_small-w:h=1:1
	// iv_grade-w:h=1:1
	protected ImageView iv_big, iv_small, iv_grade;
	protected TextView tv_name, tv_num;

	public ViewHomeItem0(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewHomeItem0(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ViewHomeItem0(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();
		setViewsHW(getContext());
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	private void init(Context context) {
		LayoutInflater.from(context).inflate(R.layout.item_home_0, this);
		iv_big = (ImageView) findViewById(R.id.imageview_big_icon);
		iv_small = (ImageView) findViewById(R.id.imageview_small_icon);
		iv_grade = (ImageView) findViewById(R.id.imageview_grade);

		tv_name = (TextView) findViewById(R.id.textview_name);
		tv_num = (TextView) findViewById(R.id.textview_num);

	}

	private void setViewsHW(Context context) {
		// TODO Auto-generated method stub
		if (!(context instanceof Activity)) {
			return;
		}
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();// 屏幕分辨率容器
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(mDisplayMetrics);
		screen_w = mDisplayMetrics.widthPixels;
		screen_h = mDisplayMetrics.heightPixels;

		LogUtils.i("ytmfdw", "屏幕宽高：w=" + screen_w + ":h=" + screen_h);

	}

	/**
	 * 设置大图片，有两种：网络地址，本地
	 * */
	public void setBigImage(String url) {

	}

	public void setBigImage(Drawable drawable) {
		iv_big.setImageDrawable(drawable);
	}

	public void setSmallImage(String url) {

	}

	public void setSmallImage(Drawable drawable) {
		iv_small.setImageDrawable(drawable);
	}

	public void setGradeImage(int grade) {
		iv_grade.setImageResource(R.drawable.level01 + (grade - 1));
	}

	public void setName(String name) {
		tv_name.setText(name);
	}

	public void setNum(String num) {
		String all = num + "人浏览过";
		tv_num.setText(highlightText(all, num, R.color.home_head_color));
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
