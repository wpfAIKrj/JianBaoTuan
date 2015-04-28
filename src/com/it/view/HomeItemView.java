package com.it.view;

import com.it.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeItemView extends LinearLayout {

	TextView title;
	GridView items;

	public HomeItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public HomeItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public HomeItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);
	}

	private void init(Context context) {
		LayoutInflater.from(context).inflate(R.layout.layout_home_item, this);
		title = (TextView) findViewById(R.id.title);
		items = (GridView) findViewById(R.id.home_list);
	}

	public void setTitle(String title) {
		this.title.setText(title);
	}

	public void setAdapter(BaseAdapter adapter) {
		items.setAdapter(adapter);
	}

	public void setItemOnClickListener(OnItemClickListener listener) {
		items.setOnItemClickListener(listener);
	}

}
