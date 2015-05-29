package com.it.view.home;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
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
import com.it.bean.CollectionEntity;
import com.it.config.Const;
import com.it.ui.activity.ActivityHotIdentiy;
import com.it.ui.activity.ActivityUserDelails;
import com.it.utils.BitmapsUtils;
import com.lidroid.xutils.util.LogUtils;

/**
 * @author ytmfdw 主页 [精品藏品]下的选项 根据屏幕宽高来计算图片大小
 *
 */

public class ViewChoices extends LinearLayout implements OnClickListener {

	// screen height,and width,in px
	private ImageView iv_big;
	private ImageView iv_small;
	private ImageView iv_grade;
	private TextView tv_name, tv_num;

	BitmapsUtils bitmapUtils;

	public ViewChoices(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewChoices(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewChoices(Context context, AttributeSet attrs, int defStyleAttr) {
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
		LayoutInflater.from(context).inflate(R.layout.item_home_0, this);
		iv_big = (ImageView) findViewById(R.id.imageview_big_icon);
		iv_small = (ImageView) findViewById(R.id.imageview_small_icon);
		iv_grade = (ImageView) findViewById(R.id.imageview_grade);

		tv_name = (TextView) findViewById(R.id.textview_name);
		tv_num = (TextView) findViewById(R.id.textview_num);
		iv_big.setClickable(true);
		iv_big.setOnClickListener(this);
		iv_small.setClickable(true);
		iv_small.setOnClickListener(this);

	}

	public void setItem(CollectionEntity item) {
		if (item == null) {
			LogUtils.e("Choices  CollectionEntity is null");
			return;
		}
		if (bitmapUtils == null) {
			bitmapUtils = BitmapsUtils.getInstance();
		}
		iv_big.setTag(item);
		iv_small.setTag(item);
		// 设置大图片
		if (TextUtils.equals(item.image, "")) {

			bitmapUtils.display(iv_big, item.images[0], BitmapsUtils.TYPE_YES);
		} else {

			bitmapUtils.display(iv_big, item.image, BitmapsUtils.TYPE_YES);
		}
		// 设置头像
		bitmapUtils.display(iv_small, item.authImage, BitmapsUtils.TYPE_YES);
		// 设置等级
		setGradeImage(item.authLevel);
		// 设置名字
		setName(item.name);
		// 设置浏览量
		setNum(item.viewTimes + "");

	}

	public void setGradeImage(int grade) {
		if (grade < 1) {
			grade = 1;
		}
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
		CollectionEntity entity = (CollectionEntity) v.getTag();
		if (entity == null)
			return;
		switch (v.getId()) {
		case R.id.imageview_big_icon: {
			// 跳转到宝贝详情页
			Intent mIntent = new Intent(getContext(), ActivityUserDelails.class);
			mIntent.putExtra(Const.ENTITY, entity);
			getContext().startActivity(mIntent);

		}
			break;

		case R.id.imageview_small_icon: {
			// 跳转到用户详情页
			Intent mIntent = new Intent(getContext(), ActivityHotIdentiy.class);
			mIntent.putExtra(Const.ENTITY, entity);
			getContext().startActivity(mIntent);
		}
			break;
		}
	}

}
