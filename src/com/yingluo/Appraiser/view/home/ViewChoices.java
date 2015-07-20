package com.yingluo.Appraiser.view.home;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
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

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.ui.activity.ActivityHotIdentiy;
import com.yingluo.Appraiser.ui.activity.ActivityUserDelails;
import com.yingluo.Appraiser.utils.BitmapsUtils;

/**
 * @author ytmfdw 主页 [精品藏品]下的选项 根据屏幕宽高来计算图片大小
 *
 */

public class ViewChoices extends LinearLayout {

	// screen height,and width,in px
	@ViewInject(R.id.imageview_big_icon)
	private ImageView iv_big;
	@ViewInject(R.id.imageview_small_icon)
	private ImageView iv_small;
	@ViewInject(R.id.imageview_grade)
	private ImageView iv_grade;

	@ViewInject(R.id.textview_name)
	private TextView tv_name;

	@ViewInject(R.id.textview_num)
	private TextView tv_num;

	BitmapsUtils bitmapUtils;

	@ViewInject(R.id.layout_treasurs)
	private LinearLayout mylayout;
	
	private CollectionTreasure currnt = null;

	private Context mContext;
	
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
		this.mContext = context;
		LayoutInflater.from(context).inflate(R.layout.item_home_0, this);
		ViewUtils.inject(this);
		iv_big.setClickable(true);
		iv_small.setClickable(true);
	}

	public void setItem(CollectionTreasure item) {
		if (item == null) {
			LogUtils.e("Choices  CollectionEntity is null");
			return;
		}
		currnt = item;
		if (bitmapUtils == null) {
			bitmapUtils = BitmapsUtils.getInstance();
		}
		iv_big.setTag(currnt);
		iv_small.setTag(currnt);
		// 设置大图片
		if (item.image == null || TextUtils.equals(item.image, "")) {

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
	private SpannableStringBuilder highlightText(String all, String target, int color) {
		SpannableStringBuilder spannable = new SpannableStringBuilder(all);
		CharacterStyle span = null;
		Pattern p = Pattern.compile(target);
		Matcher m = p.matcher(all);
		while (m.find()) {
			span = new ForegroundColorSpan(getContext().getResources().getColor(color));// 需要重复！
			spannable.setSpan(span, m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		return spannable;
	}

	@OnClick({ R.id.imageview_small_icon, R.id.imageview_big_icon })
	public void onClick(View v) {
		// TODO Auto-generated method stub
		CollectionTreasure entity = (CollectionTreasure) v.getTag();
		if (entity == null)
			return;
		switch (v.getId()) {
		case R.id.imageview_big_icon: {
			// 跳转到宝贝详情页
			Intent mIntent = new Intent(getContext(), ActivityUserDelails.class);
			mIntent.putExtra(Const.ENTITY, entity);
			getContext().startActivity(mIntent);
			Activity act = (Activity)mContext;
			act.overridePendingTransition(R.anim.left_in, R.anim.left_out);
		}
			break;

		case R.id.imageview_small_icon: {
			// 跳转到用户详情页
			Intent mIntent = new Intent(getContext(), ActivityHotIdentiy.class);
			mIntent.putExtra(Const.ENTITY, entity);
			getContext().startActivity(mIntent);
			Activity act = (Activity)mContext;
			act.overridePendingTransition(R.anim.left_in, R.anim.left_out);
		}
			break;
		}
	}

}
