package com.yingluo.Appraiser.view.viewholder;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DensityUtil;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
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
	@ViewInject(R.id.delete_checkbox)
	CheckBox cbDel;
	
	TreasureEntity currn;

	private Context mContext;

	public ViewTreasure(View itemView, final OnClickListener lis) {
		super(itemView);
		ViewUtils.inject(this, itemView);
		mContext = itemView.getContext();
		bitmapUtils = BitmapsUtils.getInstance();
		btn_result.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (lis != null) {
					v.setTag(currn);
					lis.onClick(v);
				}
			}
		});
	}

	public void setItem(TreasureEntity item,boolean isDel) {
		if (item == null) {
			return;
		}
		currn = item;
		if(iv_icon.getTag() == null) {
			bitmapUtils.display(iv_icon, item.image, BitmapsUtils.TYPE_YES);
			iv_icon.setTag(currn);
		}
		if(isDel) {
			cbDel.setVisibility(View.VISIBLE);
		} else {
			cbDel.setVisibility(View.GONE);
		}
		
		tv_msg.setText(item.title);
		switch (item.status) {
		case 1:
			tv_status.setText(R.string.identity_isno);
			break;
		case 2:
			tv_status.setText(R.string.identity_ishava);
			break;
		default:
			tv_status.setText(R.string.identity_ishava);
			break;
		}
		
		layout_kind.removeAllViews();
		List<com.yingluo.Appraiser.bean.TreasureEntity.Kind> kinds = item.kinds;

		if (kinds != null) {
			LayoutParams params = null;

			int size = 0;
			int width = 0;
			int onewidth = (int) mContext.getResources().getDimension(R.dimen.x60);
			int height = (int) mContext.getResources().getDimension(R.dimen.y30);
			int textsize = DensityUtil.px2sp(mContext, mContext.getResources().getDimension(R.dimen.x20));

			int len = kinds.size();
			for (int i = 0; i < len; i++) {
				size = kinds.get(i).name.length();
				if (size > 8) {
					size = 7;
				}
				width = (size / 2) * onewidth + (size % 2) * onewidth;
				params = new LayoutParams(width, height);
				params.setMargins(0, 0, 20, 0);
				TextView view = new TextView(mContext);
				view.setTextSize(textsize);
				view.setTextColor(mContext.getResources().getColor(R.color.wite));
				view.setBackgroundColor(mContext.getResources().getColor(R.color.number_color));
				// view.setPadding(10, 0, 10, 0);
				view.setGravity(Gravity.CENTER);
				view.setText(kinds.get(i).name);
				view.setTag(kinds.get(i).id);
				view.setLayoutParams(params);
				layout_kind.addView(view);
			}

		}
	}

	/**
	 * 选择删除模式
	 * @param conInfo
	 * @param isselect 
	 */
	public void showDataForDelete(TreasureEntity item, boolean isselect){
		cbDel.setChecked(isselect);
	}
}
