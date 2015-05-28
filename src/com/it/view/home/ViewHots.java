package com.it.view.home;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;

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
import com.it.bean.CollectionEntity;
import com.it.bean.TreasureEntity;
import com.it.ui.activity.ActivityHotIdentiy;
import com.it.utils.BitmapsUtils;
import com.it.view.MyButton;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author ytmfdw 主页 [热门鉴定]下的选项 根据屏幕宽高来计算图片大小
 *
 */

public class ViewHots extends LinearLayout implements OnClickListener {

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

	@ViewInject(R.id.layout_menu)
	LinearLayout layout_menu;

	BitmapsUtils bitmapUtils;

	public ViewHots(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewHots(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewHots(Context context, AttributeSet attrs, int defStyleAttr) {
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

		ViewUtils.inject(this);

		// iv_big = (ImageView) findViewById(R.id.imageview_big_icon);
		// iv_small = (ImageView) findViewById(R.id.imageview_small_icon);
		// iv_grade = (ImageView) findViewById(R.id.imageview_grade);
		//
		// tv_name = (TextView) findViewById(R.id.textview_name);
		// tv_num = (TextView) findViewById(R.id.textview_num);

		iv_small.setClickable(true);

		iv_small.setOnClickListener(this);

	}

	public void setTeasure(TreasureEntity item) {
		if (item == null) {
			LogUtils.e("Hots  TreasureEntity is null");
			return;
		}

		if (bitmapUtils == null) {
			bitmapUtils = BitmapsUtils.getInstance();
		}
		// 设置大图片
		if (item.treasure_special_view_data != null
				&& item.treasure_special_view_data.length > 0) {

			bitmapUtils.display(iv_big, item.treasure_special_view_data[0]);
		}
		setSmallImage(item.treasure_special_view_data);
		// 设置头像
		// 设置等级
		// 设置名字
		try {
			bitmapUtils.display(iv_small, item.author_info.getString("avatar"));
			setGradeImage(item.author_info.getInt("user_level"));
			setName(item.author_info.getString("nickname"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 设置浏览量
		setNum(item.view_times + "");

	}

	public void setItem(CollectionEntity item) {
		if (item == null) {
			LogUtils.e("Hots  CollectionEntity is null");
			return;
		}

		if (bitmapUtils == null) {
			bitmapUtils = BitmapsUtils.getInstance();
		}
		// 设置大图片
		if (item.images != null && item.images.length > 0) {

			bitmapUtils.display(iv_big, item.images[0],BitmapsUtils.TYPE_YES);
		}
		setSmallImage(item.images);
		// 设置头像
		bitmapUtils.display(iv_small, item.authImage,BitmapsUtils.TYPE_YES);
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

	// 设置小图片
	public void setSmallImage(String[] urls) {
		if (urls == null || urls.length == 0) {
			return;
		}
		int count = urls.length;
		for (int i = 0; i < count; i++) {
			MyButton button = new MyButton(getContext(), iv_big, urls[i]);
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
