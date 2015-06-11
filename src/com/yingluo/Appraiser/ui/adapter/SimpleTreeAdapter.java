package com.yingluo.Appraiser.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.tree.bean.Node;
import com.yingluo.Appraiser.tree.bean.TreeListViewAdapter;

public class SimpleTreeAdapter<T> extends TreeListViewAdapter<T>
{

	public SimpleTreeAdapter(ListView mTree, Context context, List<TreasureType> datas,
			int defaultExpandLevel) throws IllegalArgumentException,
			IllegalAccessException
	{
		super(mTree, context, datas, defaultExpandLevel);
	}

	@Override
	public View getConvertView(Node node , int position, View convertView, ViewGroup parent)
	{
		
		ViewHolder viewHolder = null;
		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.item_kind_of_precious, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.iv_icon=(ImageView) convertView.findViewById(R.id.iv_icon);
			viewHolder.iv_enter = (ImageView) convertView
					.findViewById(R.id.btn_enter);
			viewHolder.label = (TextView) convertView
					.findViewById(R.id.tv_precious_name);
			convertView.setTag(viewHolder);

		} else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if (node.getIcon() == -1)
		{
			viewHolder.iv_enter.setVisibility(View.INVISIBLE);
		} else
		{
			viewHolder.iv_enter.setVisibility(View.VISIBLE);
			viewHolder.iv_enter.setImageResource(node.getIcon());
		}

		viewHolder.label.setText(node.getName());
		
		
		return convertView;
	}

	private final class ViewHolder
	{
		ImageView iv_icon;
		TextView label;
		ImageView iv_enter;
	}

}
