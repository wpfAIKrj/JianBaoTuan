package com.yingluo.Appraiser.view.viewholder;

import java.util.ArrayList;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.view.home.ViewHots;

public class IdentityFootViewholder extends ViewHolder {

	public ViewHots hotsView;
	private ArrayList<CollectionTreasure> ids;

	public IdentityFootViewholder(View itemView, ArrayList<CollectionTreasure> list) {
		super(itemView);
		this.ids = list;
		hotsView = (ViewHots) itemView.findViewById(R.id.hot);
		hotsView.delete_checkbox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (hotsView.getItem() != null) {
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
