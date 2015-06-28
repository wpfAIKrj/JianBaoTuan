package com.yingluo.Appraiser.ui.fragment;

import io.rong.imkit.RongIM;

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

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnLongClick;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.MainEvent;
import com.yingluo.Appraiser.bean.MyEvent;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.presenter.getUserInfoLPresenter;
import com.yingluo.Appraiser.ui.activity.ActivityFootPrint;
import com.yingluo.Appraiser.ui.activity.ActivityMyPrecious;
import com.yingluo.Appraiser.ui.activity.ActivityUserDelails;
import com.yingluo.Appraiser.ui.activity.AuthenticateActivity;
import com.yingluo.Appraiser.ui.activity.FavoriteArticlesActivity;
import com.yingluo.Appraiser.ui.activity.IMListActivity;
import com.yingluo.Appraiser.ui.activity.LevelActivity;
import com.yingluo.Appraiser.ui.activity.MainActivity;
import com.yingluo.Appraiser.ui.activity.SystemInfoActivity;
import com.yingluo.Appraiser.ui.adapter.MyLikeAdapter;
import com.yingluo.Appraiser.ui.base.BaseFragment;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.SqlDataUtil;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.CircleImageView;

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
	
	private ArrayList<CollectionTreasure> list=new ArrayList<CollectionTreasure>();
	
	@ViewInject(R.id.login_user_head)
	private CircleImageView user_logo;
	
	@ViewInject(R.id.my_type_msg)
	private TextView my_type_msg;
	


	
	private getUserInfoLPresenter  getPresenter;

	
	private int[] levels={R.drawable.level01,R.drawable.level02,R.drawable.level03,
			R.drawable.level04,R.drawable.level05,R.drawable.level06};
	private OnLongClickListener onlongListner;
	private Dialog dialog;
	

	private MyLikeAdapter madapter;

	private boolean isgete=false;
	private boolean isFirst=true;
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
			if(ItApplication.currnUser!=null){
				tv_name.setText(ItApplication.currnUser.getNickname());
				BitmapsUtils.getInstance().display(user_logo, ItApplication.currnUser.getAvatar());	
				
			}
			break;
		case 1://更新个人信息
			lazyLoad();
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
		initData();
		if(!isgete){
			getPresenter.getUserInfo();
			isgete=true;	
		}
	}


	@Override
	public void lazyLoad() {
		// TODO Auto-generated method stub
			initDisplay();
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
			if(ItApplication.currnUser!=null&&ItApplication.currnUser.getUser_type()==0){
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
			
			mActivity.startActivityForResult(new Intent(mActivity,
					FavoriteArticlesActivity.class),Const.TO_COLLECT_INFO);
			break;
		case R.id.my_tab3:// 跳转我的私信
//			mActivity
//					.startActivity(new Intent(mActivity, IMListActivity.class));
			RongIM.getInstance().startConversationList(mActivity);
			break;
		case R.id.my_tab4:// 跳转到系统通知
			mActivity.startActivityForResult(new Intent(mActivity,
					SystemInfoActivity.class),Const.TO_SYSTEM_INFO);
			break;
		case R.id.my_tab5:// 跳转到认证鉴定师
			if(ItApplication.currnUser!=null&&ItApplication.currnUser.getUser_type()==0){
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
		if(ItApplication.currnUser!=null&&isFirst){
			isFirst=false;
			tv_name.setText(ItApplication.currnUser.getNickname());
			BitmapsUtils.getInstance().display(user_logo, ItApplication.currnUser.getAvatar());
			if(ItApplication.currnUser.getUser_type()==0){
				tv_authenticate.setText(R.string.type_collect);	
				iv_level.setImageResource(levels[ItApplication.currnUser.getUser_level()]);
			}else{
				tv_authenticate.setText(R.string.type_identiy);	
			}
			madapter=new MyLikeAdapter(list, itemListner);
			listView.setAdapter(madapter);
			tv_collect_number.setText(""+ItApplication.currnUser.getTreasure_number());
			tv_fooler_number.setText(""+ItApplication.currnUser.getFoot_number());
			tv_identiy_number.setText(""+ItApplication.currnUser.getTreasure_record_number());
		}	
	}
	
	private onBasicView<String> viewlis=new onBasicView<String>() {
		
		@Override
		public void onSucess(String data) {
			// TODO Auto-generated method stub
			try {
				JSONObject obj=new JSONObject(data);
				ItApplication.currnUser.setTreasure_number(obj.getInt(NetConst.TREASURE_NUMBER));
				ItApplication.currnUser.setTreasure_record_number(obj.getInt(NetConst.TREASURE_RECORD_NUMBER));
				ItApplication.currnUser.setFoot_number(obj.getInt(NetConst.FOOT_NUMBER));
				ItApplication.currnUser.setUser_type(obj.getInt(NetConst.USER_TYPE));
				ItApplication.currnUser.setUser_level(obj.getInt(NetConst.USER_LEVE));
				SqlDataUtil.getInstance().saveUserInfo(ItApplication.currnUser);
				JSONArray arrays=obj.getJSONArray(NetConst.LIKES);
				list.clear();
				for (int i = 0; i < arrays.length(); i++) {
					CollectionTreasure entity=new CollectionTreasure();
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
				isgete=false;
				madapter=new MyLikeAdapter(list, itemListner);
				listView.setAdapter(madapter);
				if(ItApplication.currnUser.getUser_type()==0){
					tv_authenticate.setText(R.string.type_collect);	
					iv_level.setImageResource(levels[ItApplication.currnUser.getUser_level()]);
				}else{
					tv_authenticate.setText(R.string.type_identiy);	
				}
				tv_collect_number.setText(""+ItApplication.currnUser.getTreasure_number());
				tv_fooler_number.setText(""+ItApplication.currnUser.getFoot_number());
				tv_identiy_number.setText(""+ItApplication.currnUser.getTreasure_record_number());
				
			}
		}
		
		@Override
		public void onFail(String errorCode, String errorMsg) {
			// TODO Auto-generated method stub
			isgete=false;
			if(errorCode.equals(String.valueOf(NetConst.CODE_ERROR8))){
				EventBus.getDefault().post(new MainEvent(0, null));
			}
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
			CollectionTreasure entity=(CollectionTreasure) v.getTag();
			Intent mIntent = new Intent(mActivity, ActivityUserDelails.class);
			mIntent.putExtra(Const.ENTITY, entity);
			mActivity.startActivity(mIntent);
		}
	};
}
