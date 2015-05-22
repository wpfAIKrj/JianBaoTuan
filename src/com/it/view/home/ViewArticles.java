package com.it.view.home;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it.R;
import com.it.bean.ArticlesEntity;
import com.it.utils.BitmapsUtils;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author ytmfdw 主页 [热门鉴定]下的选项 根据屏幕宽高来计算图片大小
 *
 */

public class ViewArticles extends LinearLayout {

	// screen height,and width,in px

	BitmapsUtils bitmapUtils;

	@ViewInject(R.id.iv_item3)
	ImageView iv;
	@ViewInject(R.id.tv_msg)
	TextView tv_msg;
	@ViewInject(R.id.tv_num)
	TextView tv_num;

	public ViewArticles(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewArticles(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewArticles(Context context, AttributeSet attrs, int defStyleAttr) {
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
		ViewUtils.inject(this);
	}

	public void setItem(ArticlesEntity item) {
		if (bitmapUtils == null) {
			bitmapUtils = BitmapsUtils.getInstance();
		}
		bitmapUtils.display(iv, item.image);
		tv_msg.setText(item.name);
		tv_num.setText(item.viewTimes);

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
