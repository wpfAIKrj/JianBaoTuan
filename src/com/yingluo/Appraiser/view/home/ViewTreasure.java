package com.yingluo.Appraiser.view.home;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.utils.BitmapsUtils;

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
		bitmapUtils.display(iv_icon, item.image, BitmapsUtils.TYPE_YES);
		tv_msg.setText(item.title);
		tv_status.setText(item.status == 1 ? "已鉴定" : "未鉴定");
		layout_kind.removeAllViews();
		List<com.yingluo.Appraiser.bean.TreasureEntity.Kind> kinds = item.kinds;
		if (kinds != null) {
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.MATCH_PARENT);
			params.setMargins(0, 0, 20, 0);
			params.gravity = Gravity.CENTER;

			int len = kinds.size();
			for (int i = 0; i < len; i++) {
				TextView view = new TextView(getContext());
				view.setTextSize(12f);
				view.setTextColor(getResources().getColor(R.color.wite));
				view.setBackgroundColor(getResources().getColor(
						R.color.number_color));
				view.setPadding(10, 0, 10, 0);
				view.setText(kinds.get(i).name);
				view.setTag(kinds.get(i).id);

				layout_kind.addView(view, params);
			}

		}
	}

	public class Kind {
		public long id;
		public String name;
	}
}
