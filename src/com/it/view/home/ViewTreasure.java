package com.it.view.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it.R;
import com.it.bean.TreasureEntity;
import com.it.utils.BitmapsUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * @author ytmfdw 我的宝贝
 *
 */

public class ViewTreasure extends LinearLayout {

	BitmapsUtils bitmapUtils;
	@ViewInject(R.id.iv_icon)
	ImageView iv_icon;
	@ViewInject(R.id.tv_msg)
	TextView tv_msg;
	@ViewInject(R.id.layout_kind)
	LinearLayout layout_kind;
	@ViewInject(R.id.tv_status)
	TextView tv_status;
	@ViewInject(R.id.btn_result)
	View btn_result;

	@OnClick(R.id.btn_result)
	public void doClick(View view) {
		// 跳转查看宝贝详情界面
	}

	public ViewTreasure(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewTreasure(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewTreasure(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();
	}

	private void init(Context context) {
		LayoutInflater.from(context).inflate(R.layout.view_my_treasure, this);
		ViewUtils.inject(this);
		bitmapUtils = BitmapsUtils.getInstance();
	}

	public void setItem(TreasureEntity item) {
		if (item == null) {
			return;
		}
		tv_msg.setText(item.treasure_description);
		tv_status.setText(item.status==1?"已鉴定":"未鉴定");
	}
}
