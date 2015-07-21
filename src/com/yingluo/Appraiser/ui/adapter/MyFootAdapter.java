package com.yingluo.Appraiser.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.lidroid.xutils.util.LogUtils;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.model.CommonCallBack;
import com.yingluo.Appraiser.model.deleteMyFootPrintsModel;
import com.yingluo.Appraiser.ui.activity.ActivityFootPrint;
import com.yingluo.Appraiser.ui.adapter.IdentiyAdapter.MyViewHolder;
import com.yingluo.Appraiser.view.home.ViewHots;
import com.yingluo.Appraiser.view.viewholder.AcrivleFootVIewholder;
import com.yingluo.Appraiser.view.viewholder.IdentityFootViewholder;

public class MyFootAdapter extends RecyclerView.Adapter<ViewHolder> {

	private List<CollectionTreasure> list;
	private OnClickListener lis;
	private boolean isScorll = false;

	private ArrayList<CollectionTreasure> deleteIds = new ArrayList<CollectionTreasure>();

	public MyFootAdapter(OnClickListener lis, List<CollectionTreasure> list) {
		this.list = list;
		this.lis = lis;
	}

	public MyFootAdapter(OnClickListener lis) {
		list = new ArrayList<CollectionTreasure>();
		this.lis = lis;
	}

	public void setData(List<CollectionTreasure> list) {
		if (this.list == null) {
			this.list = list;
		} else {
			this.list.clear();
			this.list.addAll(list);
		}
		notifyDataSetChanged();
	}

	public List<CollectionTreasure> getData() {
		return list;
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	@Override
	public int getItemViewType(int position) {
		if (list.get(position).type == 0)
			return 0;
		if (list.get(position).type == 1)
			return 1;
		return super.getItemViewType(position);
	}

	@Override
	public void onBindViewHolder(ViewHolder arg0, int arg1) {
		if (arg0 instanceof AcrivleFootVIewholder) {// 文章
			AcrivleFootVIewholder av = (AcrivleFootVIewholder) arg0;
			CollectionTreasure entity = list.get(arg1);
			av.setItem(entity);
			av.setDataFromDelete(isScorll);
		}
		if (arg0 instanceof IdentityFootViewholder) {// 宝物
			IdentityFootViewholder vh = (IdentityFootViewholder) arg0;
			CollectionTreasure entity = list.get(arg1);
			vh.hotsView.setItem(entity);
			vh.hotsView.setDataFromDelete(isScorll);
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		if (arg1 == 1) {
			View view = LayoutInflater.from(arg0.getContext()).inflate(
					R.layout.item_foot_info, arg0, false);
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
					(int) arg0.getContext().getResources()
							.getDimension(R.dimen.y568));
			view.setLayoutParams(params);
			return new AcrivleFootVIewholder(view, lis, deleteIds);

		}
		if (arg1 == 0) {
			View view = LayoutInflater.from(arg0.getContext()).inflate(
					R.layout.item_identified, arg0, false);
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
					(int) arg0.getContext().getResources()
							.getDimension(R.dimen.y568));
			view.setLayoutParams(params);
			// ViewHolder参数一定要是Item的Root节点.
			return new IdentityFootViewholder(view, deleteIds);
		}
		return null;
	}

	public boolean isScorll() {
		return isScorll;
	}

	public void setScorll(boolean isScorll) {
		this.isScorll = isScorll;
	}

	public void exitDelete() {
		deleteIds.clear();
	}

	// 全选
	public void selectAll(boolean isSelect) {
		int len = list.size();
		for (int i = 0; i < len; i++) {
			list.get(i).isSelect = isSelect;
		}
		deleteIds.clear();
		if (isSelect) {
			deleteIds.addAll(list);
		}
		notifyDataSetChanged();
	}

	public void deleteAll(final ActivityFootPrint footPrint, deleteMyFootPrintsModel delModel) {
		StringBuilder sb = new StringBuilder();
		if (deleteIds.size() < 0)
			return;
		for (final CollectionTreasure id : deleteIds) {
			sb.append(id.delete_id).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		LogUtils.i("ids=" + sb.toString());
		delModel.sendHttp(new CommonCallBack() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				LogUtils.i("delete id: +  success!!!");
				for (final CollectionTreasure id : deleteIds) {
					list.remove(id);
				}
				notifyDataSetChanged();
				footPrint.exitDelete();
			}

			@Override
			public void onError() {
				// TODO Auto-generated method stub
				
			}
		}, sb.toString());

	}

}
