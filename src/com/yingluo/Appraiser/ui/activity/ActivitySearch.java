package com.yingluo.Appraiser.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.SqlDataUtil;

public class ActivitySearch extends BaseActivity {

	@ViewInject(R.id.listview_search_result)
	ListView lv;
	ArrayList<TreasureType> list;
	MyAdapter adapter;
	@ViewInject(R.id.home_title)
	View home_title;

	@ViewInject(R.id.btn_back)
	ImageView back;

	@ViewInject(R.id.edittext_search)
	EditText edittext_search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_search);
		ViewUtils.inject(this);

		list = new ArrayList<TreasureType>();

		adapter = new MyAdapter(this, list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				TreasureType type = adapter.list.get(position);
				Intent mIntent = new Intent(ActivitySearch.this, SearchActivity.class);
				mIntent.putExtra(Const.KIND_ID, type);
				startActivityForResult(mIntent, Const.TO_KIND_INDENTIFY);

			}
		});

		edittext_search.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
					String name = edittext_search.getText().toString();

					if (name != null && !TextUtils.isEmpty(name)) {
						adapter.list = SqlDataUtil.getInstance().getSelectTreasureType(name);
						adapter.notifyDataSetChanged();
					} else {
						Toast.makeText(ActivitySearch.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
					}
				}
				return false;
			}
		});
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(RESULT_CANCELED, getIntent());
				finish();
				overridePendingTransition(R.anim.right_in, R.anim.right_out);
			}
		});

		home_title.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String name = edittext_search.getText().toString();

				if (name != null && !TextUtils.isEmpty(name)) {
					adapter.list = SqlDataUtil.getInstance().getSelectTreasureType(name);
					adapter.notifyDataSetChanged();
				} else {
					Toast.makeText(ActivitySearch.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	class MyAdapter extends BaseAdapter {

		Context mContext;
		List<TreasureType> list;

		public MyAdapter(Context context, List<TreasureType> list) {
			this.mContext = context;
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public TreasureType getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder vh = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(R.layout.item_search, null);
				vh = new ViewHolder();
				vh.tv = (TextView) convertView.findViewById(R.id.tv);
				convertView.setTag(vh);
			} else {
				vh = (ViewHolder) convertView.getTag();
			}
			if (list.get(position) != null) {

				vh.tv.setText(list.get(position).name);
			}
			return convertView;
		}

		class ViewHolder {
			TextView tv;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == Const.TO_KIND_INDENTIFY && resultCode == RESULT_OK) {
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
		}
	}

}
