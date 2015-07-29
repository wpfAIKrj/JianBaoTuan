package com.yingluo.Appraiser.view.viewholder;

import java.util.List;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.view.home.ViewHots;

public class IdentityFootViewholder extends ViewHolder {

	public ViewHots hotsView;
	private List<CollectionTreasure> ids;

	private List<CollectionTreasure> allList;
	public IdentityFootViewholder(View itemView, List<CollectionTreasure> list,List<CollectionTreasure> all) {
		super(itemView);
		ids = list;
		allList = all;
		hotsView = (ViewHots) itemView.findViewById(R.id.hot);
		hotsView.delete_checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (hotsView.getItem() != null) {
					
					for(CollectionTreasure each : allList) {
						if(each.getArticle_id() == hotsView.getItem().getArticle_id()) {
							each.isSelect=isChecked;
						}
					}
					
					if (isChecked) {
						ids.add(hotsView.getItem());
					} else {
						ids.remove(hotsView.getItem());
					}
				}
			}
		});
	}

	// add by ytmfdw
	public void setDataFromDelete(boolean isDelete) {
		hotsView.setDataFromDelete(isDelete);
	}

}
