package com.yingluo.Appraiser.view.home;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;

import android.app.Activity;
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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.ui.activity.ActivityHotIdentiy;
import com.yingluo.Appraiser.ui.activity.ActivityUserDelails;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.view.MyButton;

/**
 * @author ytmfdw 主页 [热门鉴定]下的选项 根据屏幕宽高来计算图片大小
 *
 */

public class ViewHots extends LinearLayout {

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
	@ViewInject(R.id.delete_checkbox)
	public CheckBox delete_checkbox;

	@ViewInject(R.id.tv_info)
	public TextView tv_title;
	@ViewInject(R.id.layout_menu)
	LinearLayout layout_menu;

	BitmapsUtils bitmapUtils;

	private Context mContext;

	private CollectionTreasure currnt;

	public ViewHots(Context context) {
		super(context);
		init(context);
	}

	public ViewHots(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ViewHots(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	private void init(Context context) {
		mContext = context;
		LayoutInflater.from(context).inflate(R.layout.item_home_1, this);
		ViewUtils.inject(this);
		iv_big.setClickable(true);
		iv_small.setClickable(true);
	}

	public void setItem(CollectionTreasure item) {
		if (item == null) {
			LogUtils.e("Hots  CollectionEntity is null");
			return;
		}

		currnt = item;

		

		if (bitmapUtils == null) {
			bitmapUtils = BitmapsUtils.getInstance();
		}
		// 设置大图片
		if (item.images != null && item.images.length > 0 && iv_big.getTag() == null) {
			bitmapUtils.display(iv_big, item.images[0], BitmapsUtils.TYPE_YES);
		}
		iv_big.setTag(item);
		setSmallImage(iv_big, item.images);
		// 设置头像
		if(iv_small == null) {
			bitmapUtils.display(iv_small, item.authImage, BitmapsUtils.TYPE_YES);
		}
		iv_small.setTag(item);
		// 设置等级
		setGradeImage(item.authLevel);
		// 设置名字
		setName(item.authName);
		setTitle(item.name);
		// 设置浏览量
		setNum(item.viewTimes + "");

		delete_checkbox.setChecked(item.isSelect);

	}

	public void setTitle(String name) {
		// TODO 自动生成的方法存根
		tv_title.setText(name);
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

	// 设置小图片
	public void setSmallImage(final ImageView iv, final String[] urls) {
		layout_menu.removeAllViews();
		if (urls == null || urls.length == 0) {
			return;
		}
		int count = urls.length;
		for (int i = 0; i < count; i++) {
			final String url = urls[i];
			MyButton button = new MyButton(getContext(), iv_big, urls[i]);
			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bitmapUtils.display(iv, url);
				}
			});
			layout_menu.addView(button);
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

	@OnClick({ R.id.imageview_big_icon, R.id.imageview_small_icon })
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (currnt == null)
			return;
		switch (v.getId()) {
		case R.id.imageview_big_icon: {
			Intent mIntent = new Intent(getContext(), ActivityUserDelails.class);
			mIntent.putExtra(Const.ENTITY, currnt);
			getContext().startActivity(mIntent);
			Activity act = (Activity) mContext;
			act.overridePendingTransition(R.anim.left_in, R.anim.left_out);

		}
			break;

		case R.id.imageview_small_icon: {
			Intent mIntent = new Intent(getContext(), ActivityHotIdentiy.class);
			mIntent.putExtra(Const.ENTITY, currnt);
			getContext().startActivity((mIntent));
			Activity act = (Activity) mContext;
			act.overridePendingTransition(R.anim.left_in, R.anim.left_out);
		}
			break;
		}
	}

	public void setDataFromDelete(boolean isDelete) {
		delete_checkbox.setVisibility(isDelete ? View.VISIBLE : View.GONE);
	}

	public CollectionTreasure getItem() {
		return currnt;
	}
}
