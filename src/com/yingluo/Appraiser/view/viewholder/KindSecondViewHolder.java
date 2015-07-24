package com.yingluo.Appraiser.view.viewholder;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.TreasureType;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 宝物分类
 * 
 * @author Administrator
 *
 */
public class KindSecondViewHolder extends ViewHolder {

	public TreasureType type;
	private ViewGroup layout_root;
	private ImageView iv_icon;
	private ImageView iv_enter;
	private TextView label;

	public KindSecondViewHolder(View itemView, final OnClickListener listener) {
		super(itemView);
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listener != null) {
					type.setPosition(0);
					v.setTag(type);
					listener.onClick(v);
				}
			}
		});
		layout_root = (ViewGroup) itemView.findViewById(R.id.layout_root);
		iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
		iv_enter = (ImageView) itemView.findViewById(R.id.btn_enter);
		label = (TextView) itemView.findViewById(R.id.tv_precious_name);
	}

	public void showData(TreasureType treasureType, TreasureType selectType) {
		this.type = treasureType;
		if (!type.isChild) {
			iv_enter.setVisibility(View.VISIBLE);
			iv_enter.setImageResource(R.drawable.next_bg);
		} else {
			if (selectType == null) {
				iv_enter.setVisibility(View.GONE);
			} else {
				if (selectType.id == treasureType.id) {
					iv_enter.setImageResource(R.drawable.selected);
					iv_enter.setVisibility(View.VISIBLE);
				} else {
					iv_enter.setVisibility(View.GONE);
				}
			}
		}
		label.setText(type.getName());
	}

}
