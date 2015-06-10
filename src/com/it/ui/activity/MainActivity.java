package com.it.ui.activity;


import com.it.R;
import com.it.app.ItApplication;
import com.it.bean.InfoEvent;
import com.it.bean.MyEvent;
import com.it.bean.UserInfo;
import com.it.config.Const;
import com.it.config.NetConst;
import com.it.inter.onBasicView;
import com.it.presenter.ExitPresenter;
import com.it.presenter.UploadLogoPresenter;
import com.it.ui.dialog.SelectPhotoDialog;
import com.it.ui.fragment.HomeFragment;
import com.it.ui.fragment.IdentiyFragment;
import com.it.ui.fragment.InformationFragment;
import com.it.ui.fragment.MyFragment;
import com.it.utils.DialogUtil;
import com.it.utils.ImageUtils;
import com.it.utils.ToastUtils;
import com.it.view.MyTabWidget;
import com.it.view.MyTabWidget.OnTabSelectedListener;
import com.it.view.menu.ResideMenu;
import com.it.view.menu.ResideMenuItem;
import com.lidroid.xutils.util.LogUtils;

import de.greenrobot.event.EventBus;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * 主页面
 * @author Administrator
 *
 */
public class MainActivity extends FragmentActivity implements
OnTabSelectedListener ,OnClickListener{


	private FragmentManager mFragmentManager;
	private HomeFragment mHomeFragment;
	private IdentiyFragment mIdentiyFragment;
	private InformationFragment mInformationFragment;
	private MyFragment mMyFragment;
	private long mkeyTime=0;
	private MyTabWidget mTabWidget;
	private int mIndex=0;
	private ResideMenu resideMenu;
	private UploadLogoPresenter uplogopresenter;
	private SelectPhotoDialog photodialog;
	private ImageUtils imageUtils;
	private Dialog Logodialong;
	private ExitPresenter exitpresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSave(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
		init();
		initEvents();
		setUpMenu();
    }

	private void setUpMenu() {
		// TODO Auto-generated method stub
    	 resideMenu = new ResideMenu(this);
         resideMenu.setBackground(R.drawable.menu_background);
         resideMenu.attachToActivity(this);
         resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);
         resideMenu.setShadowVisible(false);
         resideMenu.setButtonOnClickListener(this);

	}




	private void init() {
		// TODO Auto-generated method stub
		mFragmentManager = getSupportFragmentManager();
		mTabWidget = (MyTabWidget) findViewById(R.id.tab_widget);
	     LinearLayout viewSnsLayout = (LinearLayout)findViewById(R.id.viewSnsLayout);
	     viewSnsLayout.setLongClickable(true);
	     Button bt=(Button)findViewById(R.id.circle_btn);
	     bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(((ItApplication)getApplication()).getCurrnUser()!=null){
					Intent intent=new Intent(MainActivity.this, PublishedActivity.class);
					startActivityForResult(intent, Const.TO_SEND_IDENTIY);
				}else{
					new ToastUtils(MainActivity.this, R.string.help_msg_02);
				}
			}
		});
	     uplogopresenter=new UploadLogoPresenter(listener);
	     exitpresenter=new ExitPresenter(listener2);
	     imageUtils=new ImageUtils(this);
	}
	private void initEvents() {
			// TODO Auto-generated method stub
	   mTabWidget.setOnTabSelectedListener(this);
	}
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	onTabSelected(mIndex);
		mTabWidget.setTabsDisplay(this, mIndex);
		mTabWidget.setIndicateDisplay(this, mIndex, true);
    }

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt(Const.MINDEX, mIndex);
		outState.putSerializable(Const.USER, ((ItApplication)getApplication()).getCurrnUser());
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		getSave(savedInstanceState);
	}


	private void getSave(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(savedInstanceState!=null){
		   	mIndex=savedInstanceState.getInt(Const.MINDEX,0);
	    	UserInfo user = (UserInfo) savedInstanceState.getSerializable(Const.USER);
	    	((ItApplication)getApplication()).setCurrnUser(user);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

//		if(keyCode==KeyEvent.KEYCODE_BACK){
//		     if (System.currentTimeMillis() - this.mkeyTime > 2000L){
//		         this.mkeyTime = System.currentTimeMillis();
//		         return false;
//		     }else{
//		    	 //
//		         return false;
//		     }
//
//		}
		return super.onKeyDown(keyCode, event);
	}





	@Override
	public void onTabSelected(int index) {
		// TODO Auto-generated method stub
		//
		if(index==3){//我的页面
			if(((ItApplication)getApplication()).getCurrnUser()==null){
				Intent intent=new Intent(MainActivity.this, LoginAcitivity.class);
				startActivityForResult(intent, Const.TO_LOGIN);
				return;
			}
		}
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		hideFragments(transaction);
		switch (index) {
		case 0:
			if (null == mHomeFragment) {
				mHomeFragment = new HomeFragment();
				transaction.add(R.id.center_layout, mHomeFragment);
			} else {
				transaction.show(mHomeFragment);
			}
			break;
		case 1:
			if (null == mIdentiyFragment) {
				mIdentiyFragment = new IdentiyFragment();
				transaction.add(R.id.center_layout, mIdentiyFragment);
			} else {
				transaction.show(mIdentiyFragment);
			}
			break;
		case 2:
			EventBus.getDefault().post(new InfoEvent(0, null));
			if (null == mInformationFragment) {
				mInformationFragment = new InformationFragment();
				transaction.add(R.id.center_layout, mInformationFragment);
			} else {
				transaction.show(mInformationFragment);
			}
			break;
		case 3:
			if (null == mMyFragment) {
				mMyFragment = new MyFragment();
				mMyFragment.setPopMenuListener(this);
				mMyFragment.setLogoListener(new OnLongClickListener() {

					@Override
					public boolean onLongClick(View v) {
						// TODO Auto-generated method stub
						if(photodialog==null){
							photodialog=new SelectPhotoDialog(MainActivity.this, ImageListner);
						}
						if(!photodialog.isShowing()){
							photodialog.show();
						}
						return false;
					}
				});
				transaction.add(R.id.center_layout, mMyFragment);
			} else {
				transaction.show(mMyFragment);
			}
			//EventBus.getDefault().post(new MyEvent(1, null));
			break;

		default:
			break;
		}
		mIndex = index;
		transaction.commitAllowingStateLoss();
	}



	private void hideFragments(FragmentTransaction transaction) {
		// TODO Auto-generated method stub
		if (null != mHomeFragment) {
			transaction.hide(mHomeFragment);
		}
		if (null != mIdentiyFragment) {
			transaction.hide(mIdentiyFragment);
		}
		if (null != mInformationFragment) {
			transaction.hide(mInformationFragment);
		}
		if(null !=mMyFragment){
			transaction.hide(mMyFragment);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if(v.getId()==R.id.my_bt_showmenu){
			resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
			return;
		}
		if(v.getId()==R.id.layout_item1){//跳转到个人资料
			startActivity(new Intent(MainActivity.this, ProfileActivity.class));
		}
		if(v.getId()==R.id.layout_item2){//修改密码
			startActivityForResult(new Intent(MainActivity.this, PasswordActivity.class),Const.TO_UPDATA_PWD);
		}
		if(v.getId()==R.id.layout_item3){//意见反馈
			startActivityForResult(new Intent(MainActivity.this, FeedbackActivity.class),Const.TO_FEEDBACK);
		}
		if(v.getId()==R.id.layout_item4){//检测更新
			startActivity(new Intent(MainActivity.this, UpdaateActivity.class));
		}
		if(v.getId()==R.id.layout_item5){//清楚缓存

		}
		if(v.getId()==R.id.layout_item1){//退出登陆
			
		}
		resideMenu.closeMenu();
	}


	@Override
	public void startActivity(Intent intent) {
		// TODO Auto-generated method stub
		super.startActivity(intent);
		overridePendingTransition(R.anim.left_in, R.anim.left_out);
	}


	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		if(mInformationFragment!=null){
			mInformationFragment.onActivityResult(arg0, arg1, arg2);
		}
		super.onActivityResult(arg0, arg1, arg2);
		

		if (arg1 == RESULT_CANCELED) {
			disshowPhoto();
			return;
		}
		if(arg0==Const.TO_LOGIN&&arg1==RESULT_OK){//登陆成功
			mIndex=3;
			onTabSelected(mIndex);
		}
		if(arg0==Const.TO_SEND_IDENTIY&&arg1==RESULT_OK){//发布成功


		}
		if(arg0==ImageUtils.GET_IMAGE_BY_CAMERA&&arg1==RESULT_OK){//我的页面，获取照片地址获取到图片（相机）
			if(imageUtils.PICPATH!=null){
				if(imageUtils.PICPATH!=null){
				Intent intent=new Intent(MainActivity.this, GetUserLogoActivity.class);
				intent.putExtra(Const.PICPATH, imageUtils.PICPATH);
				startActivityForResult(intent, ImageUtils.CROP_IMAGE);
					//uploadLogo(imageUtils.PICPATH);
				}
			}
		}
		if(arg0==ImageUtils.GET_IMAGE_FROM_PHONE&&arg1==Activity.RESULT_OK){//我的页面，获取照片地址获取到图片（相册）
			if(arg2 != null && arg2.getData() != null) {
				imageUtils.doPhoto( arg2);
				if(imageUtils.PICPATH!=null){
					Intent intent=new Intent(MainActivity.this, GetUserLogoActivity.class);
					intent.putExtra(Const.PICPATH, imageUtils.PICPATH);
					startActivityForResult(intent, ImageUtils.CROP_IMAGE);
					//uploadLogo(imageUtils.PICPATH);
				}
			}
		}
		if(arg0==ImageUtils.GET_IMAGE_FROM_PHONE_KITKAT&&arg1==RESULT_OK){//我的页面，获取照片地址获取到图片（相册）4.4系统，用谷歌的相册
			if(arg2 != null && arg2.getData() != null) {
				imageUtils.doPhotoKIKAT(arg2);
				if(imageUtils.PICPATH!=null){
					Intent intent=new Intent(MainActivity.this, GetUserLogoActivity.class);
					intent.putExtra(Const.PICPATH, imageUtils.PICPATH);
					startActivityForResult(intent, ImageUtils.CROP_IMAGE);
					//uploadLogo(imageUtils.PICPATH);
			}
		}
		}
		if(arg0==ImageUtils.CROP_IMAGE&&arg1==RESULT_OK){//获取剪切好的人物头像
			if(arg2!=null){
				String path=arg2.getStringExtra(Const.PICPATH);
				uploadLogo(path);
			}
		}

	}

	public void uploadLogo(String path){
		LogUtils.d("获取的头像路径："+path);
		disshowPhoto();
		UserInfo user=((ItApplication)getApplication()).getCurrnUser();
		if(user!=null){
			String qq=user.getQq();
			String email=user.getEmail();
			String name=user.getNickname();
			uplogopresenter.startUpLoadLogo(path,email,qq,name);
			Logodialong=DialogUtil.createLoadingDialog(this, "正在更新头像中");
			Logodialong.show();
		}
	}


	private void disshowPhoto() {
		// TODO Auto-generated method stub
		if(photodialog!=null){
			if(photodialog.isShowing()){
				photodialog.dismiss();
			}
		}
	}


	private onBasicView<UserInfo> listener=new onBasicView<UserInfo>() {

		@Override
		public void onSucess(UserInfo user) {
			// TODO Auto-generated method stub
			disshowPhoto();
			if(Logodialong!=null){
				Logodialong.dismiss();
			}
			UserInfo cunnt=((ItApplication)getApplication()).getCurrnUser();
			cunnt.setAvatar(user.getAvatar());

			EventBus.getDefault().post(new MyEvent(0,user));
		}

		@Override
		public void onFail(String errorCode, String errorMsg) {
			// TODO Auto-generated method stub
			disshowPhoto();
			if(Logodialong!=null){
				Logodialong.dismiss();
			}
			new ToastUtils(MainActivity.this, errorMsg);
		}
	};
	
	private onBasicView<String> listener2=new onBasicView<String>() {
		
		@Override
		public void onSucess(String data) {
			// TODO 自动生成的方法存根
			
		}
		
		@Override
		public void onFail(String errorCode, String errorMsg) {
			// TODO 自动生成的方法存根
			
		}
	};

	private OnClickListener ImageListner = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_take_photo://相机获取
				imageUtils.openCameraImage();
				break;
			case R.id.btn_pick_photo://相册获取
				imageUtils.openLocalImage();
				break;
			case R.id.btn_cancel://取消
				if(photodialog!=null){
					photodialog.dismiss();
				}
				break;
			default:
				break;
			}
		}
	};
}
