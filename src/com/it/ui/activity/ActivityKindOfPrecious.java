package com.it.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.it.R;

public class ActivityKindOfPrecious extends Activity {

	ListView lv;

	KindAdpater adapter;

	ImageView btn_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_kind_of_precious);
		lv = (ListView) findViewById(R.id.lv_kind);
		ArrayList<String> list = new ArrayList<String>();
		list.add("籽");
		list.add("核");
		list.add("根");
		list.add("玉");
		list.add("石");
		list.add("金");
		list.add("书");
		list.add("瓷");

		adapter = new KindAdpater(this, list);
		lv.setAdapter(adapter);

		btn_back = (ImageView) findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (position == 0) {
					startActivity(new Intent(ActivityKindOfPrecious.this,
							ActivitySearch.class));
				} else if (position == 1) {
					onBackPressed();
				}

			}
		});
	}

	class KindAdpater extends BaseAdapter {

		Context mContext;
		ArrayList<String> list;

		public KindAdpater(Context context, List<String> list) {
			// TODO Auto-generated constructor stub
			mContext = context;
			this.list = new ArrayList<String>();
			this.list.add(null);
			this.list.add(null);
			this.list.addAll(list);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (position == 0) {
				return LayoutInflater.from(mContext).inflate(
						R.layout.item_kind_of_precios_first, null);
			} else if (position == 1) {
				return LayoutInflater.from(mContext).inflate(
						R.layout.item_kind_of_precios_second, null);
			} else {
				ViewHolder vh = null;
				if (convertView == null) {
					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.item_kind_of_precious, null);
					vh = new ViewHolder();

					vh.iv = (ImageView) convertView.findViewById(R.id.iv_icon);
					vh.tv = (TextView) convertView
							.findViewById(R.id.tv_precious_name);
					convertView.setTag(vh);
				} else {
					vh = (ViewHolder) convertView.getTag();
				}

				vh.tv.setText(list.get(position) == null ? "" : list
						.get(position));

				return convertView;
			}
		}

		class ViewHolder {
			ImageView iv;
			TextView tv;
		}

	}

}
