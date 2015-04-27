package com.it.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it.R;
import com.it.ui.activity.AuthenticateActivity;
import com.it.ui.activity.LevelActivity;
import com.it.ui.adapter.MyLoveAdapter;
import com.it.ui.base.BaseFragment;
import com.it.view.listview.HorizontalListView;

public class MyFragment extends BaseFragment implements OnClickListener{
	private OnClickListener listener;
	private HorizontalListView listView;
	private TextView tv_name,tv_authenticate,tv_collect_number;
	private ImageView iv_authenticate;
	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.layout_my, container, false);
	}

	@Override
	protected void initViews(View view) {
		// TODO Auto-generated method stub
		ImageView bt=(ImageView)view.findViewById(R.id.my_bt_showmenu);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(listener!=null){
					listener.onClick(v);
				}
			}
		});
		listView = (HorizontalListView) view.findViewById(R.id.horizontalListView1);
		MyLoveAdapter adapter=new MyLoveAdapter(mActivity);
		listView.setAdapter(adapter);
		tv_name=(TextView)view.findViewById(R.id.my_tv_name);
		tv_authenticate=(TextView)view.findViewById(R.id.my_tv_authenticate);
		tv_authenticate.setOnClickListener(this);
		iv_authenticate=(ImageView)view.findViewById(R.id.my_iv_authenticate);
		iv_authenticate.setOnClickListener(this);
		
		tv_collect_number=(TextView)view.findViewById(R.id.my_tv_collect_number);
		LinearLayout layout=(LinearLayout)view.findViewById(R.id.my_layout_collect);
		layout.setOnClickListener(this);
		layout=(LinearLayout)view.findViewById(R.id.my_layout_authenticate);
		layout.setOnClickListener(this);
	}

	@Override
	protected void initDisplay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lazyLoad() {
		// TODO Auto-generated method stub
		System.out.println(getClass().getName()+"正在加载数据");
	}

	
	
	public void  setPopMenuListener(OnClickListener lis){
		listener=lis;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.my_tv_authenticate://跳转到认证鉴定师
			mActivity.startActivity(new Intent(mActivity, AuthenticateActivity.class));
			break;
		case R.id.my_iv_authenticate://跳转到等级介绍
			mActivity.startActivity(new Intent(mActivity, LevelActivity.class));
			break;
		case R.id.my_layout_collect://跳转收集宝贝页面
			break;
		case R.id.my_layout_authenticate://跳转到认证鉴定师
			mActivity.startActivity(new Intent(mActivity, AuthenticateActivity.class));
			break;
		default:
			break;
		}
	}
	
	


}
