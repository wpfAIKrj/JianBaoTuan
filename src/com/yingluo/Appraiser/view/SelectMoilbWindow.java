package com.yingluo.Appraiser.view;

import java.util.ArrayList;
import java.util.List;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.util.LogUtils;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.utils.DensityUtil;
import com.yingluo.Appraiser.utils.SqlDataUtil;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class SelectMoilbWindow extends PopupWindow {

	private View conentView;
	private ListView listView;
	private Context mContext;
	private MoilbAdapter maAdapter;
	private List<UserInfo> list;
	
	public SelectMoilbWindow(Context context, OnItemClickListener listener) {
		mContext = context;
		list = SqlDataUtil.getInstance().getUserList();
		Log.e("list_size", list.size()+"");
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.moilb_popup_dialog, null);
		listView = (ListView) conentView.findViewById(R.id.listview1);
		maAdapter = new MoilbAdapter(list);
		listView.setAdapter(maAdapter);
		listView.setOnItemClickListener(listener);

		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);

	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			int w = parent.getWidth();
			// 设置SelectPicPopupWindow弹出窗体的宽
			this.setWidth(w);
			// 设置SelectPicPopupWindow弹出窗体的高
			if(list.size()<5) {
				this.setHeight(LayoutParams.WRAP_CONTENT);
			} else {
				this.setHeight(5*DensityUtil.dip2px(mContext, 30));
			}
			// 设置SelectPicPopupWindow弹出窗体可点击
			this.setFocusable(true);
			this.setOutsideTouchable(true);
			// 刷新状态
			this.update();
			// 实例化一个ColorDrawable颜色为半透明
			ColorDrawable dw = new ColorDrawable(0000000000);
			// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
			this.setBackgroundDrawable(dw);
			// mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
			this.showAsDropDown(parent, 0, 18);
		} else {
			this.dismiss();
		}
	}

	public UserInfo getUserInfo(int position) {
		return maAdapter.getItem(position);
	}

	private class MoilbAdapter extends BaseAdapter {
		private List<UserInfo> users = null;

		public MoilbAdapter(List<UserInfo> list) {
			this.users = list;
			if (users == null) {
				users = new ArrayList<UserInfo>();
			}
		}

		@Override
		public int getCount() {
			return users.size();
		}

		@Override
		public UserInfo getItem(int position) {
			return users.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Viewhandler vh = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(R.layout.register_auto_complete_item, parent,
						false);
				vh = new Viewhandler();
				vh.tv = (TextView) convertView.findViewById(R.id.tv);
				convertView.setTag(vh);
			} else {
				vh = (Viewhandler) convertView.getTag();
			}
			vh.tv.setText(users.get(position).getMobile());
			return convertView;
		}

		public class Viewhandler {
			public TextView tv;
		}
	}
}
