package com.yingluo.Appraiser.view.viewholder;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.utils.BitmapsUtils;

public class AcrivleFootVIewholder extends ViewHolder {

	BitmapsUtils bitmapUtils;

	@ViewInject(R.id.iv_item3)
	ImageView iv;
	@ViewInject(R.id.tv_msg)
	TextView tv_msg;
	@ViewInject(R.id.tv_num)
	TextView tv_num;
	@ViewInject(R.id.delete_checkbox)
	CheckBox delete_checkbox;

	CollectionTreasure currnt;

	private List<CollectionTreasure> list;
	private List<CollectionTreasure> allList;
	public AcrivleFootVIewholder(View itemView, final OnClickListener lis, List<CollectionTreasure> dels,List<CollectionTreasure> all) {
		super(itemView);
		list = dels;
		allList = all;
		ViewUtils.inject(this, itemView);
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (lis != null) {
					v.setTag(currnt);
					lis.onClick(v);
				}
			}
		});
		delete_checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (currnt != null) {
					for(CollectionTreasure each : allList) {
						if(each.getArticle_id() == currnt.getArticle_id()) {
							each.isSelect=isChecked;
						}
					}
					if (isChecked) {
						list.add(currnt);
					} else {
						list.remove(currnt);
					}
				}
			}
		});
	}

	
	public void setList(ArrayList<CollectionTreasure> list) {
		this.list = list;
	}


	public void setItem(CollectionTreasure arcitite) {

		currnt = arcitite;
		
		if (bitmapUtils == null) {
			bitmapUtils = BitmapsUtils.getInstance();
		}
		if (iv.getTag() == null) {
			bitmapUtils.displayForxy(iv, currnt.getImage());
		}
		iv.setTag(currnt);
		tv_msg.setText(currnt.msg);
		tv_num.setText(currnt.viewTimes + "");
		delete_checkbox.setChecked(arcitite.isSelect);

	}

	// add by ytmfdw
	public void setDataFromDelete(boolean isDelete) {
		delete_checkbox.setVisibility(isDelete ? View.VISIBLE : View.GONE);
	}

}
