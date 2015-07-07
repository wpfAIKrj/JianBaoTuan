package com.yingluo.Appraiser.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.inter.OnKindClickListener;
import com.yingluo.Appraiser.ui.activity.ActivityKindOfPrecious;
import com.yingluo.Appraiser.utils.SqlDataUtil;

public class KindTreasureAdapter extends BaseAdapter {

	protected LayoutInflater mInflater;
	private List<TreasureType> kind;
	private OnKindClickListener listener;

	private long currentType = 0;

	ActivityKindOfPrecious act;

	public KindTreasureAdapter(ActivityKindOfPrecious mContext) {
		act = mContext;
		mInflater = LayoutInflater.from(mContext);
		kind = new ArrayList<TreasureType>();
	}

	public void setData(List<TreasureType> list) {
		if (list != null) {
			if (list.size() > 0) {
				currentType = list.get(0).parent_id;
			}
			kind.clear();
			kind.addAll(list);
			notifyDataSetChanged();
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return kind.size();
	}

	@Override
	public TreasureType getItem(int position) {
		// TODO Auto-generated method stub
		return kind.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return kind.get(position).id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final TreasureType type = kind.get(position);

		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_kind_of_precious,
					parent, false);
			viewHolder = new ViewHolder();
			viewHolder.layout_root = (ViewGroup) convertView
					.findViewById(R.id.layout_root);
			viewHolder.iv_icon = (ImageView) convertView
					.findViewById(R.id.iv_icon);
			viewHolder.iv_enter = (ImageView) convertView
					.findViewById(R.id.btn_enter);
			viewHolder.label = (TextView) convertView
					.findViewById(R.id.tv_precious_name);
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		final List<TreasureType> child = SqlDataUtil.getInstance()
				.getChildTreasure(type.type + 1, type.id);
		final boolean hasChild = (child != null && child.size() > 0);// 判断是否有子类
		if (!hasChild) {
			// 没有子目录，显示红钩
			viewHolder.iv_enter.setImageResource(R.drawable.selected);
		} else {
			viewHolder.iv_enter.setImageResource(R.drawable.next_bg);
		}

		viewHolder.label.setText(type.getName());
		viewHolder.layout_root.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (hasChild) {
					// 设置标题
					act.setTitle(type.getName());
					// 隐藏一二
					act.hideSearchAndAll(true);
					ActivityKindOfPrecious.currentType++;
					KindTreasureAdapter.this.setData(child);
				} else {
					if (listener != null) {
						listener.onClick(type);
					}
				}

			}
		});

		return convertView;
	}

	private final class ViewHolder {
		ViewGroup layout_root;
		ImageView iv_icon;
		TextView label;
		ImageView iv_enter;
	}

	public void setOnClickListener(OnKindClickListener listener) {
		this.listener = listener;
	}

	public void onBackPress() {
		if (ActivityKindOfPrecious.currentType == 0) {
			// 一级目录时，显示全部分类
			act.hideSearchAndAll(false);
			act.setTitle("宝贝分类");
			setData(SqlDataUtil.getInstance().getFirstType());
		} else {
			TreasureType type = SqlDataUtil.getInstance().getTreasureTypeById(
					currentType);
			TreasureType parent = SqlDataUtil.getInstance()
					.getTreasureTypeById(type.getParent_id());
			act.setTitle(parent.getName());
			List<TreasureType> tmp = SqlDataUtil.getInstance()
					.getChildTreasure(ActivityKindOfPrecious.currentType,
							type.parent_id);
			setData(tmp);
		}
	}
}
