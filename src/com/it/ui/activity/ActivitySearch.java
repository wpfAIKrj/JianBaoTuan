package com.it.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.it.R;

public class ActivitySearch extends Activity {

	ListView lv;
	ArrayList<String> list;
	MyAdapter adapter;
	View home_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_search);
		lv = (ListView) findViewById(R.id.listview_search_result);
		list = new ArrayList<String>();
		list.add("青花瓷");
		list.add("青花瓷");
		list.add("青花瓷");
		list.add("青花瓷");
		list.add("青花瓷");
		list.add("青花瓷");
		list.add("青花瓷");
		list.add("青花瓷");
		list.add("青花瓷");
		adapter = new MyAdapter(this, list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			}
		});
		home_title = findViewById(R.id.home_title);
		home_title.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
	}

	class MyAdapter extends BaseAdapter {

		Context mContext;
		List<String> list;

		public MyAdapter(Context context, List<String> list) {
			// TODO Auto-generated constructor stub
			this.mContext = context;
			this.list = list;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public String getItem(int position) {
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
			ViewHolder vh = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.item_search, null);
				vh = new ViewHolder();
				vh.tv = (TextView) convertView.findViewById(R.id.tv);
				convertView.setTag(vh);
			} else {
				vh = (ViewHolder) convertView.getTag();
			}
			if (list.get(position) != null) {

				vh.tv.setText(list.get(position));
			}
			return convertView;
		}

		class ViewHolder {
			TextView tv;
		}
	}

}
