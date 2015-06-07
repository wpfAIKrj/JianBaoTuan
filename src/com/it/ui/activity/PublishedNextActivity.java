package com.it.ui.activity;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it.R;
import com.it.bean.UserInfo;
import com.it.presenter.RamdomAdasiterPresenter;
import com.it.ui.adapter.identiySelectAdapter;
import com.it.ui.base.BaseActivity;
import com.it.utils.DialogUtil;
import com.it.utils.ToastUtils;
import com.it.view.inter.onListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnCompoundButtonCheckedChange;

/**
 * 发布藏品的第二步
 * @author Administrator
 *
 */
public class PublishedNextActivity extends BaseActivity implements onListView<UserInfo>{

	@ViewInject(R.id.home_title)
	private TextView title;
	
	@ViewInject(R.id.ed_info)
	private EditText ed_info;
	
	@ViewInject(R.id.recyclerview1)
	private RecyclerView recyclerview;
	
	private RamdomAdasiterPresenter rampresenter;
	private identiySelectAdapter madapter;

	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publishednext);
		ViewUtils.inject(this);
		initView();
		rampresenter=new RamdomAdasiterPresenter(this);
	}

	private void initView() {
		// TODO Auto-generated method stub
		title.setText(R.string.publish_title);
		LinearLayoutManager layoutManager=new LinearLayoutManager(this);
		recyclerview.setLayoutManager(layoutManager);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		rampresenter.startGet();
		dialog=DialogUtil.createLoadingDialog(this, "获取鉴定师中。。。");
		dialog.show();
	}
	

	@OnClick({R.id.btn_back,R.id.send_identy})
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_back://返回上层
			setResult(RESULT_CANCELED, getIntent());
			finish();
			break;
		case R.id.send_identy:
			
			break;
			default:
				break;
		}
	}

	@Override
	public void onSucess(ArrayList<UserInfo> data) {
		// TODO Auto-generated method stub
		madapter=new identiySelectAdapter(data, click, check);
		recyclerview.setAdapter(madapter);
		if(dialog!=null){
			dialog.dismiss();
		}
	}

	@Override
	public void onFail(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		if(dialog!=null){
			dialog.dismiss();
		}
		new ToastUtils(this, errorMsg);
	}
	
	/**
	 * 点击重新刷新加载
	 */
	private OnClickListener click=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			rampresenter.startGet();
			dialog=DialogUtil.createLoadingDialog(PublishedNextActivity.this, "获取鉴定师中。。。");
			dialog.show();
		}
	};

	private OnCheckedChangeListener check=new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			
		}
	};
	
}
