package com.yingluo.Appraiser.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

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
	private Context context;
	private List<CollectionTreasure> delist;
	
	public MyFootAdapter(Context context,OnClickListener lis, List<CollectionTreasure> list) {
		this.context = context;
		this.list = list;
		this.lis = lis;
	}

	public MyFootAdapter(Context context,OnClickListener lis) {
		this.context = context;
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
		if(delist == null) {
			delist = new ArrayList<CollectionTreasure>();
		} else {
			delist.clear();
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
	public void onBindViewHolder(ViewHolder arg0, int position) {
		if (arg0 instanceof AcrivleFootVIewholder) {// 文章
			AcrivleFootVIewholder av = (AcrivleFootVIewholder) arg0;
			CollectionTreasure entity = list.get(position);
			av.setItem(entity);
			av.setDataFromDelete(isScorll);
		}
		if (arg0 instanceof IdentityFootViewholder) {// 宝物
			IdentityFootViewholder vh = (IdentityFootViewholder) arg0;
			CollectionTreasure entity = list.get(position);
			vh.hotsView.setItem(entity);
			vh.hotsView.setDataFromDelete(isScorll);
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		if (arg1 == 1) {
			View view = LayoutInflater.from(arg0.getContext()).inflate(R.layout.item_foot_info, arg0, false);
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
					(int) arg0.getContext().getResources().getDimension(R.dimen.y568));
			view.setLayoutParams(params);
			return new AcrivleFootVIewholder(view, lis, delist,list);
		}
		if (arg1 == 0) {
			View view = LayoutInflater.from(arg0.getContext()).inflate(R.layout.item_identified, arg0, false);
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
					(int) arg0.getContext().getResources().getDimension(R.dimen.y568));
			view.setLayoutParams(params);
			// ViewHolder参数一定要是Item的Root节点.
			return new IdentityFootViewholder(view, delist,list);
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
		delist.clear();
		for(CollectionTreasure each:list) {
			each.isSelect = false;
		}
	}

	// 全选
	public void selectAll(boolean isSelect) {
		int len = list.size();
		delist.clear();
		if(isSelect) {
			delist.addAll(list);
		}
		
		for (int i = 0; i < len; i++) {
			list.get(i).isSelect = isSelect;
		}
	}

	public void deleteAll(final ActivityFootPrint footPrint, deleteMyFootPrintsModel delModel) {
		StringBuilder sb = new StringBuilder();
		if (delist.size() <= 0) {
			Toast.makeText(context, "请至少选择一个", Toast.LENGTH_SHORT).show();
			return;
		}
		for (final CollectionTreasure id : delist) {
			sb.append(id.delete_id).append(",");
//			for()
//			notifyItemRsemoved(position);
		}
		
		sb.deleteCharAt(sb.length() - 1);
		LogUtils.i("ids=" + sb.toString());
		delModel.sendHttp(new CommonCallBack() {

			@Override
			public void onSuccess() {
				LogUtils.i("delete id: +  success!!!");
				Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
				for (final CollectionTreasure id : delist) {
					for(CollectionTreasure ids : list) {
						if(ids.getArticle_id() == id.getArticle_id()) {
							list.remove(ids);
							break;
						}
					}
				}
				delist.clear();
				notifyDataSetChanged();
				footPrint.exitDelete();
			}

			@Override
			public void onError() {
				Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
			}
		}, sb.toString());

	}

}
