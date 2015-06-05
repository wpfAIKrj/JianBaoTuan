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
import com.it.bean.KindEntity;
import com.it.model.CommonCallBack;
import com.it.model.getAllKind_X_Model;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class ActivityKindOfPrecious extends Activity {

	@ViewInject(R.id.lv_kind)
	ListView lv;

	KindAdpater adapter;

	@ViewInject(R.id.btn_back)
	ImageView btn_back;
	@ViewInject(R.id.layout_search)
	View search;
	@ViewInject(R.id.layout_all_kind)
	View all_kind;

	@OnClick({ R.id.btn_back, R.id.layout_search, R.id.layout_all_kind })
	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			onBackPressed();
			break;
		case R.id.layout_search: {
			Intent mIntent = new Intent(ActivityKindOfPrecious.this,
					ActivitySearch.class);
			startActivity(mIntent);
		}
			break;
		case R.id.layout_all_kind: {
			mAdpater.setData(first);
		}
			break;

		default:
			break;
		}
	}

	List<List<KindEntity>> list;
	List<KindEntity> first;
	List<KindEntity> second;
	List<KindEntity> third;

	getAllKind_X_Model model;

	private KindAdpater mAdpater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_kind_of_precious);
		ViewUtils.inject(this);
		mAdpater = new KindAdpater(this);
		lv.setAdapter(mAdpater);
		model = new getAllKind_X_Model();
		model.sendHttp(new CommonCallBack() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				list = model.getResult();
				first = list.get(0);
				second = list.get(1);
				third = list.get(2);
				mAdpater.setData(first);
				LogUtils.d(first.size() + "first");
				LogUtils.d(second.size() + "second");
				LogUtils.d(third.size() + "third");
			}

			@Override
			public void onError() {
				// TODO Auto-generated method stub

			}
		});

		// adapter = new KindAdpater(this, list);
		// lv.setAdapter(adapter);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				KindEntity tmp = mAdpater.getItem(position);
				List<KindEntity> list_tmp = new ArrayList<KindEntity>();
				if (tmp.type == KindEntity.TYPE_FIRST) {
					// 一级分类，进入二级分类
					for (KindEntity entity : second) {
						if (entity.parent_id == tmp.id) {
							list_tmp.add(entity);
						}
						mAdpater.setData(list_tmp);
					}
				} else if (tmp.type == KindEntity.TYPE_SECOND) {
					// 二级分类，进入三级分类
					for (KindEntity entity : third) {
						if (entity.parent_id == tmp.id) {
							list_tmp.add(entity);
						}
						mAdpater.setData(list_tmp);
					}
				}

			}
		});
	}

	class KindAdpater extends BaseAdapter {

		Context mContext;
		List<KindEntity> list;

		public KindAdpater(Context context) {
			mContext = context;
			this.list = new ArrayList<KindEntity>();
		}

		public KindAdpater(Context context, List<KindEntity> list) {
			// TODO Auto-generated constructor stub
			mContext = context;
			this.list = new ArrayList<KindEntity>();
			this.list.addAll(list);
		}

		public void setData(List<KindEntity> list) {
			this.list.clear();
			if (list == null) {
				notifyDataSetChanged();
				return;
			}
			this.list.addAll(list);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public KindEntity getItem(int position) {
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
						R.layout.item_kind_of_precious, null);
				vh = new ViewHolder();

				vh.iv = (ImageView) convertView.findViewById(R.id.iv_icon);
				vh.tv = (TextView) convertView
						.findViewById(R.id.tv_precious_name);
				convertView.setTag(vh);
			} else {
				vh = (ViewHolder) convertView.getTag();
			}

			vh.tv.setText(list.get(position).name);

			return convertView;
		}

		class ViewHolder {
			ImageView iv;
			TextView tv;
		}

	}

}
