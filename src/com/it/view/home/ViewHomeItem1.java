package com.it.view.home;

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
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it.R;
import com.it.bean.HomeItem0;
import com.it.ui.activity.ActivityHotIdentiy;
import com.lidroid.xutils.BitmapUtils;

/**
 * @author ytmfdw 主页 [热门鉴定]下的选项 根据屏幕宽高来计算图片大小
 *
 */

public class ViewHomeItem1 extends LinearLayout implements OnClickListener {

	// screen height,and width,in px
	private ImageView iv_big;
	private ImageView iv_small;
	private ImageView iv_grade;
	private TextView tv_name, tv_num;

	BitmapUtils bitmapUtils;

	public ViewHomeItem1(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewHomeItem1(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewHomeItem1(Context context, AttributeSet attrs, int defStyleAttr) {
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
		LayoutInflater.from(context).inflate(R.layout.item_home_1, this);
		iv_big = (ImageView) findViewById(R.id.imageview_big_icon);
		iv_small = (ImageView) findViewById(R.id.imageview_small_icon);
		iv_grade = (ImageView) findViewById(R.id.imageview_grade);

		tv_name = (TextView) findViewById(R.id.textview_name);
		tv_num = (TextView) findViewById(R.id.textview_num);

		iv_small.setClickable(true);

		iv_small.setOnClickListener(this);

	}

	public void setItem(HomeItem0 item) {
		if (bitmapUtils == null) {
			bitmapUtils = new BitmapUtils(getContext());
		}
		// 设置大图片
		bitmapUtils.display(iv_big, item.image);
		// 设置头像
		bitmapUtils.display(iv_small, item.authImage);
		// 设置等级
		// setGradeImage(item.grade);
		// 设置名字
		setName(item.name);
		// 设置浏览量
		setNum(item.viewTimes);

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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent mIntent = new Intent(getContext(), ActivityHotIdentiy.class);
		// mIntent.putExtra("", "");
		getContext().startActivity((mIntent));
	}
}
