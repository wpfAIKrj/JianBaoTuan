package com.yingluo.Appraiser.view.home;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.ui.activity.InformationDetailsActivity;
import com.yingluo.Appraiser.utils.BitmapsUtils;

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

	private ContentInfo currnt;

	private Context mContext;
	
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
		this.mContext = context;
		LayoutInflater.from(context).inflate(R.layout.item_home_3, this);
		ViewUtils.inject(this);
	}

	public void setItem(ContentInfo item) {
		if (item == null) {
			LogUtils.e("Articles  CollectionEntity is null");
			return;
		}
		currnt = item;
		if (bitmapUtils == null) {
			bitmapUtils = BitmapsUtils.getInstance();
		}
		bitmapUtils.display(iv, item.getImage(), BitmapsUtils.TYPE_YES);
		tv_msg.setText(item.getTitle());
		tv_num.setText(item.getView_times() + "");
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

	@OnClick({ R.id.iv_item3 })
	public void onclick(View v) {
		if (currnt == null) {
			return;
		}
		switch (v.getId()) {
		case R.id.iv_item3: {
			// ContentInfo contentInfo = (ContentInfo) v.getTag();
			Intent intent = new Intent(getContext(), InformationDetailsActivity.class);
			intent.putExtra(Const.ArticleId, currnt);
			getContext().startActivity(intent);
			Activity act = (Activity) mContext;
			act.overridePendingTransition(R.anim.left_in, R.anim.left_out);
		}
			break;

		default:
			break;
		}
	}
}
