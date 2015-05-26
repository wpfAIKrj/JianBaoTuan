package com.it.ui.fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.R;
import com.it.app.ItApplication;
import com.it.bean.CollectionEntity;
import com.it.bean.MyEvent;
import com.it.bean.UserInfo;
import com.it.config.Const;
import com.it.config.NetConst;
import com.it.inter.onBasicView;
import com.it.presenter.getUserInfoLPresenter;
import com.it.ui.activity.ActivityFootPrint;
import com.it.ui.activity.ActivityMyPrecious;
import com.it.ui.activity.AuthenticateActivity;
import com.it.ui.activity.FavoriteArticlesActivity;
import com.it.ui.activity.IMListActivity;
import com.it.ui.activity.LevelActivity;
import com.it.ui.activity.SystemInfoActivity;
import com.it.ui.adapter.MyLikeAdapter;
import com.it.ui.base.BaseFragment;
import com.it.utils.BitmapsUtils;
import com.it.utils.DialogUtil;
import com.it.utils.ToastUtils;
import com.it.view.CircleImageView;
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
	

	private OnClickListener MenuListner;
	@ViewInject(R.id.horizontalListView1)
	private RecyclerView listView;
	
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
	
	private ArrayList<CollectionEntity> list=new ArrayList<CollectionEntity>();
	
	@ViewInject(R.id.login_user_head)
	private CircleImageView user_logo;
	
	@ViewInject(R.id.my_type_msg)
	private TextView my_type_msg;
	
	private UserInfo user=null;
	
	private getUserInfoLPresenter  getPresenter;

	
	private int[] levels={R.drawable.level01,R.drawable.level02,R.drawable.level03,
			R.drawable.level04,R.drawable.level05,R.drawable.level06};
	private OnLongClickListener onlongListner;
	private Dialog dialog;
	
	private boolean isFirst=true;

	private MyLikeAdapter madapter;

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
		case 1://更新个人信息
			if(isFirst){
				isFirst=false;
				initDisplay();
			}
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void initViews(View view) {
		// TODO Auto-generated method stub
		ViewUtils.inject(this,view);
		getPresenter=new getUserInfoLPresenter(viewlis);
		LinearLayoutManager manager=new LinearLayoutManager(view.getContext());
		manager.setOrientation(LinearLayoutManager.HORIZONTAL);
		listView.setLayoutManager(manager);
		
	}

	@Override
	protected void initDisplay() {
		// TODO Auto-generated method stub
		user=((ItApplication)(mActivity.getApplication())).getCurrnUser();
		tv_name.setText(user.getNickname());
		BitmapsUtils.getInstance().display(user_logo, user.getAvatar());
		//initData();
		dialog=DialogUtil.createLoadingDialog(mActivity, "加载个人信息中");
		dialog.show();
		getPresenter.getUserInfo();
	}


	@Override
	public void lazyLoad() {
		// TODO Auto-generated method stub
		if(isFirst){
			isFirst=false;
			initDisplay();
		}
	}

	public void setPopMenuListener(OnClickListener lis) {
		MenuListner = lis;
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
			if (MenuListner != null) {
				MenuListner.onClick(v);
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
		madapter=new MyLikeAdapter(list, itemListner);
		listView.setAdapter(madapter);
		tv_collect_number.setText(""+user.getTreasure_number());
		tv_fooler_number.setText(""+user.getFoot_number());
		tv_identiy_number.setText(""+user.getTreasure_record_number());
		
	}
	
	private onBasicView<String> viewlis=new onBasicView<String>() {
		
		@Override
		public void onSucess(String data) {
			// TODO Auto-generated method stub
			try {
				JSONObject obj=new JSONObject(data);
				user.setTreasure_number(obj.getInt(NetConst.TREASURE_NUMBER));
				user.setTreasure_record_number(obj.getInt(NetConst.TREASURE_RECORD_NUMBER));
				user.setFoot_number(obj.getInt(NetConst.FOOT_NUMBER));
				JSONArray arrays=obj.getJSONArray(NetConst.LIKES);
				list.clear();
				for (int i = 0; i < arrays.length(); i++) {
					CollectionEntity entity=new CollectionEntity();
					JSONObject json=arrays.getJSONObject(i);
					entity.treasure_id=json.getLong("treasure_id");
					entity.image=json.getString("image");
					list.add(entity);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
			if(dialog!=null){
				dialog.dismiss();
			}
				initData();
			}
		}
		
		@Override
		public void onFail(String errorCode, String errorMsg) {
			// TODO Auto-generated method stub
			new ToastUtils(mActivity, errorMsg);
			if(dialog!=null){
				dialog.dismiss();
			}
		}
	};
	private OnClickListener itemListner=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	};
}
