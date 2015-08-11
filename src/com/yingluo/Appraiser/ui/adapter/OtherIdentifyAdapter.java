package com.yingluo.Appraiser.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.http.ResponseMyIdentify.userIdentify;
import com.yingluo.Appraiser.view.viewholder.OtherIdentityHolder;

/**
 * @author 王亚立 他的鉴定
 *
 */
public class OtherIdentifyAdapter extends RecyclerView.Adapter<OtherIdentityHolder> {

	private List<userIdentify> lists;
	private Context context;
	
	public OtherIdentifyAdapter(Context context) {
		this.context = context;
		lists = new ArrayList<userIdentify>();
	}

	public void setData(List<userIdentify> list) {
		if (this.lists == null) {
			this.lists = list;
		} else {
			this.lists.clear();
			this.lists.addAll(list);
		}
		notifyDataSetChanged();
	}

	public List<userIdentify> getData() {
		return lists;
	}

	@Override
	public int getItemCount() {
		return lists.size();
	}

	@Override
	public void onBindViewHolder(OtherIdentityHolder holder, int position) {
		userIdentify item = lists.get(position);
		holder.setItem(item);
	}

	@Override
	public OtherIdentityHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_other_identify, viewGroup, false);
		// ViewHolder参数一定要是Item的Root节点.
		return new OtherIdentityHolder(context,view);
	}

}
