package com.yingluo.Appraiser.ui.adapter;

import java.util.List;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.utils.SqlDataUtil;
import com.yingluo.Appraiser.view.viewholder.KindAllViewHolder;
import com.yingluo.Appraiser.view.viewholder.KindSecondViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.view.ViewGroup;

/**
 * 显示宝物的页面
 * @author Administrator
 *
 */
public class KindAdapter extends RecyclerView.Adapter<ViewHolder> {

	private LayoutInflater mInflater;
	private Context context;
	
	private int load_type=2;
	private OnClickListener listener;
	private int type;//0为一级
	private List<TreasureType> list;
	public TreasureType treasureType=null;//当前宝物分类的父类
	public TreasureType selectType=null;
	
	public KindAdapter(Context context,OnClickListener listener,int type) {
		this.context=context;
		mInflater=LayoutInflater.from(context);
		this.listener=listener;
		this.type=type;
		treasureType=null;
		list=SqlDataUtil.getInstance().getTreasureType(type);
		if(list.size() == 0) {
			Toast.makeText(context, "获取数据失败", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public int getItemViewType(int position) {
		if(type==0){
			//一级页面，不现实所有类
			return 0;
		}else{
			if(position==0){
				return 1;
			}else{
				return 2;
			}
		}
	}
	
	
	@Override
	public int getItemCount() {
		if(type==0){
			return list.size();	
		}else{
			return list.size()+1;
		}
	}
	
	@Override
	public void onBindViewHolder(ViewHolder arg0, int arg1) {
		if(type==0){
			if(arg0 instanceof KindSecondViewHolder){
				KindSecondViewHolder view=(KindSecondViewHolder) arg0;
				view.showData(list.get(arg1),selectType);
			}
		}else{
			if(arg0 instanceof KindSecondViewHolder){
				KindSecondViewHolder view=(KindSecondViewHolder) arg0;
				view.showData(list.get(arg1-1),selectType);
			}
			if(arg0 instanceof KindAllViewHolder){
				KindAllViewHolder view=(KindAllViewHolder) arg0;
				view.showData(treasureType,selectType);
			}
		}
	}
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		if(arg1==0){
			return new KindSecondViewHolder(mInflater.inflate(R.layout.item_kind_of_precious, arg0, false),listener);
		}
		if(arg1==1){
			return new KindAllViewHolder(mInflater.inflate(R.layout.item_kind_of_alltitle, arg0, false),listener);
		}
		if(arg1==2){
			return new KindSecondViewHolder(mInflater.inflate(R.layout.item_kind_of_precious, arg0, false),listener);
		}
		return null;
	}

	
	/**
	 * 显示下一级
	 * @param currentType
	 */
	public void setNextDataType(TreasureType currentType) {
		// TODO Auto-generated method stub
		if(currentType==null){
			this.type=0;
			treasureType=null;
			list=SqlDataUtil.getInstance().getTreasureType(type);
		}else{
			this.type=currentType.getType()+1;
			treasureType=currentType;
			list=SqlDataUtil.getInstance().getChildTreasure(type,treasureType.id);
		}
		notifyDataSetChanged();
	}

	/**
	 * 显示上一级
	 * @param currentType
	 */
	public void setProDataType(TreasureType currentType) {
		// TODO Auto-generated method stub
		if(currentType==null){
			this.type=0;
			treasureType=null;
			list=SqlDataUtil.getInstance().getTreasureType(type);
		}else{
			this.type=currentType.getType()+1;
			treasureType=currentType;
			list=SqlDataUtil.getInstance().getNextChildTreasure(treasureType);
		}
		notifyDataSetChanged();
	}

	
	

}
