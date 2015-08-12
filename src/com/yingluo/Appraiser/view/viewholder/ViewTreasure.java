package com.yingluo.Appraiser.view.viewholder;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.bean.TreasureEntity.Kind;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.http.ResponseNewHome.kinds;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DensityUtil;
import com.yingluo.Appraiser.view.TagLinearLayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout.LayoutParams;

public class ViewTreasure extends ViewHolder {

	BitmapsUtils bitmapUtils;
	@ViewInject(R.id.iv_icon)
	ImageView iv_icon;
	@ViewInject(R.id.tv_msg)
	TextView tv_msg;
	@ViewInject(R.id.layout_kind)
	TagLinearLayout layout_kind;
	@ViewInject(R.id.tv_status)
	TextView tv_status;
	@ViewInject(R.id.btn_result)
	TextView btn_result;
	@ViewInject(R.id.delete_checkbox)
	CheckBox cbDel;
	
	private TreasureEntity currn;

	private Context mContext;
	private List<TreasureEntity> all;
	
	public ViewTreasure(View itemView, final OnClickListener lis,final List<TreasureEntity> dels,List<TreasureEntity> allLists) {
		super(itemView);
		ViewUtils.inject(this, itemView);
		this.all = allLists;
		mContext = itemView.getContext();
		bitmapUtils = BitmapsUtils.getInstance();
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (lis != null) {
					v.setTag(currn);
					lis.onClick(v);
				}
			}
		});
		
		cbDel.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				for(TreasureEntity each :all) {
					if(each.treasure_id == currn.treasure_id) {
						each.isSelect = isChecked;
					}
				}
				
				if (isChecked) {
					dels.add(currn);
				} else {
					dels.remove(currn);
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
			cbDel.setChecked(item.isSelect);
		} else {
			cbDel.setVisibility(View.GONE);
		}
		cbDel.setTag(item);
		tv_msg.setText(item.title);
		switch (item.status) {
		case 0:
			tv_status.setText(R.string.identity_isno);
			btn_result.setVisibility(View.GONE);
			break;
		case 1:
			tv_status.setText(R.string.identity_ing);
			btn_result.setVisibility(View.GONE);
			break;
		case 2:
			tv_status.setText(R.string.identity_ishava);
			btn_result.setVisibility(View.VISIBLE);
			break;
		}
		
		layout_kind.removeAllViews();
		List<com.yingluo.Appraiser.bean.TreasureEntity.Kind> kinds = item.kinds;
		setKinds(kinds);
		
	}

	public void setKinds(List<com.yingluo.Appraiser.bean.TreasureEntity.Kind> kinds) {
		int length = kinds.size();
		TreasureType key = new TreasureType();
		for (int i = 0; i < length; i++) {
			Kind kind = kinds.get(i);
			if (i >= 1) {
				key.parent_id = key.id;
			} else {
				key.parent_id = 0;
			}
			key.id = Long.valueOf(kind.id);
			key.name = kind.name;
			key.type = i;
		}
		if(length != 0) {
			layout_kind.addTag(key);
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
