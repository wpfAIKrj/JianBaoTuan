package com.it.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
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
import com.it.ui.adapter.KindAdpater;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class ActivityKindOfPrecious extends Activity {

	@ViewInject(R.id.lv_kind)
	RecyclerView lv;


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
			mAdpater.closeData();
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
		LinearLayoutManager layoutManager=new LinearLayoutManager(this);
		lv.setLayoutManager(layoutManager);
		
		mAdpater=new KindAdpater(this, listener);
		lv.setAdapter(mAdpater);

	}

	/**
	 * 选择3级跳转
	 */
	private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	};


}
