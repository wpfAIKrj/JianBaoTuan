package com.yingluo.Appraiser.view.viewholder;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.utils.BitmapsUtils;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class ViewTreasure extends ViewHolder {

	
	
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
	Button btn_result;

	TreasureEntity currn;
	
	private Context mContext;
	public ViewTreasure(View itemView,final OnClickListener lis) {
		super(itemView);
		// TODO Auto-generated constructor stub
		ViewUtils.inject(this,itemView);
		mContext=itemView.getContext();
		bitmapUtils = BitmapsUtils.getInstance();
		btn_result.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(lis!=null){
					v.setTag(currn);
					lis.onClick(v);
				}
			}
		});
	}

	public void setItem(TreasureEntity item) {
		if (item == null) {
			return;
		}
		currn=item;
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
				TextView view = new TextView(mContext);
				view.setTextSize(12f);
				view.setTextColor(mContext.getResources().getColor(R.color.wite));
				view.setBackgroundColor(mContext.getResources().getColor(
						R.color.number_color));
				view.setPadding(10, 0, 10, 0);
				view.setText(kinds.get(i).name);
				view.setTag(kinds.get(i).id);

				layout_kind.addView(view, params);
			}

		}
	}

}