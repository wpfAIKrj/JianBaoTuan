package com.it.ui.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it.R;
import com.it.app.ItApplication;
import com.it.bean.MyEvent;
import com.it.bean.UserInfo;
import com.it.config.Const;
import com.it.ui.activity.ActivityFootPrint;
import com.it.ui.activity.ActivityMyPrecious;
import com.it.ui.activity.AuthenticateActivity;
import com.it.ui.activity.FavoriteArticlesActivity;
import com.it.ui.activity.IMListActivity;
import com.it.ui.activity.LevelActivity;
import com.it.ui.activity.SystemInfoActivity;
import com.it.ui.adapter.MyLoveAdapter;
import com.it.ui.base.BaseFragment;
import com.it.ui.dialog.SelectPhotoDialog;
import com.it.utils.BitmapsUtils;
import com.it.utils.ImageUtils;
import com.it.utils.ToastUtils;
import com.it.view.CircleImageView;
import com.it.view.listview.HorizontalListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnLongClick;

import de.greenrobot.event.EventBus;
/**
 * 我的设置页面
 * @author Administrator
 *
 */
public class MyFragment extends BaseFragment{
	

	private OnClickListener listener;
	@ViewInject(R.id.horizontalListView1)
	private HorizontalListView listView;
	
	@ViewInject(R.id.my_tv_name)
	private TextView tv_name;
	
	@ViewInject(R.id.my_tv_authenticate)
	private TextView tv_authenticate;
	
	@ViewInject(R.id.my_tv_collect_number)
	private TextView tv_collect_number;
	
	@ViewInject(R.id.my_identiy_number)
	private TextView tv_identiy_number;
	
	@ViewInject(R.id.my_fooler_number)
	private TextView tv_fooler_number;
	
	@ViewInject(R.id.my_iv_level)
	private ImageView iv_level;
	

	
	@ViewInject(R.id.login_user_head)
	private CircleImageView user_logo;
	
	@ViewInject(R.id.my_type_msg)
	private TextView my_type_msg;
	
	private UserInfo user=null;
	
	

	
	private int[] levels={R.drawable.level01,R.drawable.level02,R.drawable.level03,
			R.drawable.level04,R.drawable.level05,R.drawable.level06};
	private OnLongClickListener onlongListner;
	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.layout_my, container, false);
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
	
	public void onEventMainThread(MyEvent event){
		switch (event.type) {
		case 0://更新人物头像
			user=((ItApplication)(mActivity.getApplication())).getCurrnUser();
			tv_name.setText(user.getNickname());
			BitmapsUtils.getInstance().display(user_logo, user.getAvatar());
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void initViews(View view) {
		// TODO Auto-generated method stub
		ViewUtils.inject(this,view);
	
	}

	@Override
	protected void initDisplay() {
		// TODO Auto-generated method stub
		user=((ItApplication)(mActivity.getApplication())).getCurrnUser();
		tv_name.setText(user.getNickname());
		BitmapsUtils.getInstance().display(user_logo, user.getAvatar());
		MyLoveAdapter adapter = new MyLoveAdapter(mActivity);
		listView.setAdapter(adapter);
		//initData();
	}




	@Override
	public void lazyLoad() {
		// TODO Auto-generated method stub
		System.out.println(getClass().getName() + "正在加载数据");
	}

	public void setPopMenuListener(OnClickListener lis) {
		listener = lis;
	}
	public void setLogoListener(OnLongClickListener onlongListner){
		this.onlongListner=onlongListner;
	}

	@OnLongClick(R.id.login_user_head)
	public boolean onLongClick(View v){
		switch (v.getId()) {
		case R.id.login_user_head://更新头像
			if(onlongListner!=null){
				onlongListner.onLongClick(v);
			}
			break;

		default:
			break;
		}
		return false;
	}
	
	
	@OnClick({R.id.my_iv_level,R.id.my_bt_showmenu,R.id.my_tv_authenticate,R.id.my_layout_collect,R.id.my_layout_foot,R.id.my_layout_identif
		,R.id.my_tab1,R.id.my_tab2,R.id.my_tab3,R.id.my_tab4,R.id.my_tab5,R.id.my_tab6})
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.my_iv_level://跳转到等级说明
			mActivity.startActivity(new Intent(mActivity, LevelActivity.class));	
			break;
		case R.id.my_bt_showmenu://右侧菜单
			if (listener != null) {
				listener.onClick(v);
			}
			break;
		case R.id.my_tv_authenticate:// 跳转到认证鉴定师
			if(user.getUser_type()==0){
				mActivity.startActivity(new Intent(mActivity,
						AuthenticateActivity.class));
			}
			break;

		case R.id.my_layout_collect:// 跳转收集宝贝页面
			{Intent mIntent=new Intent(mActivity, ActivityMyPrecious.class);
			mIntent.putExtra(Const.GOTO_MY_PRECIOUS, Const.PRECIOUS);
			startActivity(mIntent);}
			break;
		case R.id.my_layout_foot:// 跳转到足迹
			startActivity(new Intent(mActivity, ActivityFootPrint.class));
			break;
		case R.id.my_layout_identif:// 跳转到鉴定页面
		{Intent mIntent=new Intent(mActivity, ActivityMyPrecious.class);
		mIntent.putExtra(Const.GOTO_MY_PRECIOUS, Const.IDENTIFY);
		startActivity(mIntent);}
			break;
		case R.id.my_tab1:// 跳转到收藏宝物
		{Intent mIntent=new Intent(mActivity, ActivityMyPrecious.class);
		mIntent.putExtra(Const.GOTO_MY_PRECIOUS, Const.COLLECT);
		startActivity(mIntent);}
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
			if(user.getUser_type()==0){
				mActivity.startActivity(new Intent(mActivity,
						AuthenticateActivity.class));
			}else{
				new ToastUtils(mActivity,R.string.help_msg_01);
			}
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
	
	
	private void initData() {
		// TODO Auto-generated method stub
		tv_name.setText(user.getNickname());
		BitmapsUtils.getInstance().display(user_logo, user.getAvatar());
		tv_authenticate.setText(mActivity.getResources().getStringArray(R.array.my_user_type)[user.getUser_type()]);
		iv_level.setImageResource(levels[user.getUser_type()]);
	}
	

}
