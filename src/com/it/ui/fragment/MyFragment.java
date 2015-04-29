package com.it.ui.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
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
import com.it.ui.activity.ActivityFootPrint;
import com.it.ui.activity.AuthenticateActivity;
import com.it.ui.activity.FavoriteArticlesActivity;
import com.it.ui.activity.IMListActivity;
import com.it.ui.activity.LevelActivity;
import com.it.ui.activity.SystemInfoActivity;
import com.it.ui.adapter.MyLoveAdapter;
import com.it.ui.base.BaseFragment;
import com.it.view.listview.HorizontalListView;

public class MyFragment extends BaseFragment implements OnClickListener {
	private OnClickListener listener;
	private HorizontalListView listView;
	private TextView tv_name, tv_authenticate, tv_collect_number;
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
		ImageView bt = (ImageView) view.findViewById(R.id.my_bt_showmenu);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (listener != null) {
					listener.onClick(v);
				}
			}
		});
		listView = (HorizontalListView) view
				.findViewById(R.id.horizontalListView1);
		MyLoveAdapter adapter = new MyLoveAdapter(mActivity);
		listView.setAdapter(adapter);
		tv_name = (TextView) view.findViewById(R.id.my_tv_name);
		tv_authenticate = (TextView) view.findViewById(R.id.my_tv_authenticate);
		tv_authenticate.setOnClickListener(this);

		tv_collect_number = (TextView) view
				.findViewById(R.id.my_tv_collect_number);
		LinearLayout layout = (LinearLayout) view
				.findViewById(R.id.my_layout_collect);
		layout.setOnClickListener(this);
		layout = (LinearLayout) view.findViewById(R.id.my_layout_foot);
		layout.setOnClickListener(this);
		layout = (LinearLayout) view.findViewById(R.id.my_layout_identif);
		layout.setOnClickListener(this);
		layout = (LinearLayout) view.findViewById(R.id.my_tab1);
		layout.setOnClickListener(this);
		layout = (LinearLayout) view.findViewById(R.id.my_tab2);
		layout.setOnClickListener(this);
		layout = (LinearLayout) view.findViewById(R.id.my_tab3);
		layout.setOnClickListener(this);
		layout = (LinearLayout) view.findViewById(R.id.my_tab4);
		layout.setOnClickListener(this);
		layout = (LinearLayout) view.findViewById(R.id.my_tab5);
		layout.setOnClickListener(this);
		layout = (LinearLayout) view.findViewById(R.id.my_tab6);
		layout.setOnClickListener(this);
	}

	@Override
	protected void initDisplay() {
		// TODO Auto-generated method stub

	}

	@Override
	public void lazyLoad() {
		// TODO Auto-generated method stub
		System.out.println(getClass().getName() + "正在加载数据");
	}

	public void setPopMenuListener(OnClickListener lis) {
		listener = lis;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.my_tv_authenticate:// 跳转到认证鉴定师
			mActivity.startActivity(new Intent(mActivity, LevelActivity.class));
			break;

		case R.id.my_layout_collect:// 跳转收集宝贝页面

			break;
		case R.id.my_layout_foot:// 跳转到足迹
			startActivity(new Intent(mActivity, ActivityFootPrint.class));
			break;
		case R.id.my_layout_identif:// 跳转到鉴定页面
			break;
		case R.id.my_tab1:// 跳转到收藏宝物

			break;
		case R.id.my_tab2:// 跳转到收藏文章
			mActivity.startActivity(new Intent(mActivity,
					FavoriteArticlesActivity.class));
			break;
		case R.id.my_tab3:// 跳转我的私信
			mActivity
					.startActivity(new Intent(mActivity, IMListActivity.class));
			break;
		case R.id.my_tab4:// 跳转到系统通知
			mActivity.startActivity(new Intent(mActivity,
					SystemInfoActivity.class));
			break;
		case R.id.my_tab5:// 跳转到认证鉴定师
			mActivity.startActivity(new Intent(mActivity,
					AuthenticateActivity.class));
			break;
		case R.id.my_tab6:// 跳转到人工客服
			String strMobile = "10086";
			// 此处应该对电话号码进行验证。。
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ strMobile));

			mActivity.startActivity(intent);
			break;
		default:
			break;
		}
	}

}
