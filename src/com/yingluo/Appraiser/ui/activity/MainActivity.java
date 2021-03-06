package com.yingluo.Appraiser.ui.activity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lidroid.xutils.util.LogUtils;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.bean.InfoEvent;
import com.yingluo.Appraiser.bean.MainEvent;
import com.yingluo.Appraiser.bean.MyEvent;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.config.UrlUtil;
import com.yingluo.Appraiser.http.AskNetWork;
import com.yingluo.Appraiser.http.ResponseIsDel;
import com.yingluo.Appraiser.http.AskNetWork.AskNetWorkCallBack;
import com.yingluo.Appraiser.im.RongImUtils;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.inter.onListView;
import com.yingluo.Appraiser.presenter.ExitPresenter;
import com.yingluo.Appraiser.presenter.UploadLogoPresenter;
import com.yingluo.Appraiser.ui.dialog.SelectPhotoDialog;
import com.yingluo.Appraiser.ui.fragment.ToolFragment;
import com.yingluo.Appraiser.ui.fragment.InformationFragment;
import com.yingluo.Appraiser.ui.fragment.MyFragment;
import com.yingluo.Appraiser.ui.fragment.NewHomeFragment;
import com.yingluo.Appraiser.utils.ActivityTaskManager;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.FileUtils;
import com.yingluo.Appraiser.utils.ImageUtils;
import com.yingluo.Appraiser.utils.SharedPreferencesUtils;
import com.yingluo.Appraiser.utils.SqlDataUtil;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.MyTabWidget;
import com.yingluo.Appraiser.view.MyTabWidget.OnTabSelectedListener;
import com.yingluo.Appraiser.view.menu.ResideMenu;

import de.greenrobot.event.EventBus;

/**
 * 主页面
 * 
 * @author Administrator
 *
 */
public class MainActivity extends FragmentActivity implements OnTabSelectedListener,AskNetWorkCallBack {

	private FragmentManager mFragmentManager;
	private NewHomeFragment mNewHomeFragment;
	
	private ToolFragment mToolFragment;
	private InformationFragment mInformationFragment;
	private MyFragment mMyFragment;
	private long mkeyTime = 0;
	private MyTabWidget mTabWidget;
	public int mIndex = 0;
	private UploadLogoPresenter uplogopresenter;
	private SelectPhotoDialog photodialog;
	private ImageUtils imageUtils;
	private Dialog Logodialong;
	
	// 用于判断第二次点击退出
	private long mExitTime;
	
	private AskNetWork askNewWork;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSave(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		ActivityTaskManager.getInstance().putActivity(this.getClass().getName(), this);
		
		String name = SharedPreferencesUtils.getInstance().getLoginUserName();
		if(name != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mobile", name);
			askNewWork = new AskNetWork(map, NetConst.IS_DEL, this);
			askNewWork.ask(null);
		}
		init();
		initEvents();
		EventBus.getDefault().register(this);
	}

	public void onEventMainThread(MainEvent event) {
		switch (event.type) {
		case 0:// 返回首页，切换到首页
			mIndex = 3;
			setOnTabselected();
			break;
		case 1:// 登录
			Intent intent = new Intent(MainActivity.this, LoginAcitivity.class);
			startActivityForResult(intent, Const.TO_LOGIN);
			overridePendingTransition(R.anim.toast_in, R.anim.hold);
			break;
		case 2:// 跳转到鉴定大厅
			mIndex = 1;
			setOnTabselected();
			break;
		case 3:// 跳转到知识学堂
			mIndex = 2;
			setOnTabselected();
			break;
		default:
			break;
		}
	}

	@Override
	protected void onDestroy() {
		EventBus.getDefault().unregister(this);
		ActivityTaskManager.getInstance().removeActivity(this.getClass().getName());
		super.onDestroy();
	}

	private void init() {
		mFragmentManager = getSupportFragmentManager();
		mTabWidget = (MyTabWidget) findViewById(R.id.tab_widget);
//		LinearLayout viewSnsLayout = (LinearLayout) findViewById(R.id.viewSnsLayout);
//		viewSnsLayout.setLongClickable(true);
		Button bt = (Button) findViewById(R.id.circle_btn);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (ItApplication.getcurrnUser() != null) {
					Intent intent = new Intent(MainActivity.this, PublishedActivity.class);
					startActivityForResult(intent, Const.TO_SEND_IDENTIY);
					overridePendingTransition(R.anim.toast_in, R.anim.hold);
				} else {
//					new ToastUtils(MainActivity.this, R.string.help_msg_02);
					Intent intent = new Intent(MainActivity.this, LoginAcitivity.class);
					startActivityForResult(intent, Const.TO_LOGIN);
					overridePendingTransition(R.anim.toast_in, R.anim.hold);
				}
			}
		});
		uplogopresenter = new UploadLogoPresenter(listener);

		imageUtils = new ImageUtils(this);
	}

	private void initEvents() {
		mTabWidget.setOnTabSelectedListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// if (ItApplication.currnUser == null) {
		// mIndex = 0;
		// }
		setOnTabselected();
	}

	public void setOnTabselected() {
		onTabSelected(mIndex);
		mTabWidget.setTabsDisplay(this, mIndex);
		mTabWidget.setIndicateDisplay(this, mIndex, true);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt(Const.MINDEX, mIndex);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		getSave(savedInstanceState);
	}

	private void getSave(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			mIndex = savedInstanceState.getInt(Const.MINDEX, 0);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
//				for(Activit act : ActivityTaskManager.getInstance())
//				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onTabSelected(int index) {
		if (index == 3) {
			// 我的页面
			if (ItApplication.getcurrnUser() == null) {
				mIndex = 0;
				Intent intent = new Intent(MainActivity.this, LoginAcitivity.class);
				startActivityForResult(intent, Const.TO_LOGIN);
				overridePendingTransition(R.anim.toast_in, R.anim.hold);
				return;
			}
		}
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		hideFragments(transaction);
		switch (index) {
		case 0:
			if (null == mNewHomeFragment) {
				mNewHomeFragment = new NewHomeFragment();
				transaction.add(R.id.center_layout, mNewHomeFragment);
			} else {
				transaction.show(mNewHomeFragment);
			}
			mNewHomeFragment.lazyLoad();

			break;
		case 1:
			EventBus.getDefault().post(new InfoEvent(0, null));
			if (null == mInformationFragment) {
				mInformationFragment = new InformationFragment();
				transaction.add(R.id.center_layout, mInformationFragment);
			} else {
				transaction.show(mInformationFragment);
			}
			break;
		case 2:
			EventBus.getDefault().post(new InfoEvent(0, null));
			if (null == mToolFragment) {
				mToolFragment = new ToolFragment();
				transaction.add(R.id.center_layout, mToolFragment);
			} else {
				transaction.show(mToolFragment);
			}
			break;
		case 3:
			if (null == mMyFragment) {
				mMyFragment = new MyFragment();
				mMyFragment.setLogoListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (photodialog == null) {
							photodialog = new SelectPhotoDialog(MainActivity.this, ImageListner);
						}
						if (!photodialog.isShowing()) {
							photodialog.show();
						}
					}
				});
				transaction.add(R.id.center_layout, mMyFragment);
			} else {
				transaction.show(mMyFragment);
			}
			EventBus.getDefault().post(new MyEvent(1, null));
			break;

		default:
			break;
		}
		mIndex = index;
		transaction.commitAllowingStateLoss();
	}

	private void hideFragments(FragmentTransaction transaction) {
		if (null != mNewHomeFragment) {
			transaction.hide(mNewHomeFragment);
		}
		if (null != mToolFragment) {
			transaction.hide(mToolFragment);
		}
		if (null != mInformationFragment) {
			transaction.hide(mInformationFragment);
		}
		if (null != mMyFragment) {
			transaction.hide(mMyFragment);
		}
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
		if (arg0 == Const.TO_USER_SET && arg1 == Const.TO_EXITS_USER) {
			new ToastUtils(MainActivity.this, "退出账户成功！");
			mIndex = 0;
			setOnTabselected();
		}
		if (mInformationFragment != null) {
			mInformationFragment.onActivityResult(arg0, arg1, arg2);
		}
		if (mToolFragment != null) {
			mToolFragment.onActivityResult(arg0, arg1, arg2);
		}
		if (mMyFragment != null) {
			mMyFragment.onActivityResult(arg0, arg1, arg2);
		}
		super.onActivityResult(arg0, arg1, arg2);

		if (arg1 == RESULT_CANCELED) {
			disshowPhoto();
			return;
		}
		if (arg0 == Const.TO_LOGIN && arg1 == RESULT_OK) {// 登陆成功
			mIndex = 3;
			setOnTabselected();
			EventBus.getDefault().post(new MyEvent(0, null));
		}
		if (arg0 == Const.TO_SEND_IDENTIY && arg1 == RESULT_OK) {// 发布成功

		}
		if (arg0 == ImageUtils.GET_IMAGE_BY_CAMERA && arg1 == RESULT_OK) {// 我的页面，获取照片地址获取到图片（相机）
			if (imageUtils.PICPATH != null) {
				imageUtils.crop(Uri.fromFile((new File(imageUtils.PICPATH))));
			}
		}
		if (arg0 == ImageUtils.GET_IMAGE_FROM_PHONE && arg1 == Activity.RESULT_OK) {// 我的页面，获取照片地址获取到图片（相册）
			if (arg2 != null && arg2.getData() != null) {
				imageUtils.crop(arg2.getData());
			}
		}
		if (arg0 == ImageUtils.GET_IMAGE_FROM_PHONE_KITKAT && arg1 == RESULT_OK) {// 我的页面，获取照片地址获取到图片（相册）4.4系统，用谷歌的相册
			if (arg2 != null && arg2.getData() != null) {
				imageUtils.doPhotoKIKAT(arg2);
				imageUtils.crop(Uri.fromFile(new File(imageUtils.PICPATH)));

			}
		}
		if (arg0 == ImageUtils.PHOTO_REQUEST_CUT && arg1 == RESULT_OK) {// 获取剪切好的人物头像
			if (arg2 != null) {

				uploadLogo(FileUtils.getInstance().getLogoPath().getAbsolutePath());
			}

		}
		disshowPhoto();

	}

	public void uploadLogo(String path) {
		LogUtils.d("获取的头像路径：" + path);
		disshowPhoto();
		if (ItApplication.getcurrnUser() != null) {
			String qq = ItApplication.getcurrnUser().getQq();
			String email = ItApplication.getcurrnUser().getEmail();
			String name = ItApplication.getcurrnUser().getNickname();
			uplogopresenter.startUpLoadLogo(path, email, qq, name);
			Logodialong = DialogUtil.createLoadingDialog(this, "正在更新头像中");
			Logodialong.show();
		}
	}

	private void disshowPhoto() {
		// TODO Auto-generated method stub
		if (photodialog != null) {
			if (photodialog.isShowing()) {
				photodialog.dismiss();
			}
		}
	}

	private onBasicView<UserInfo> listener = new onBasicView<UserInfo>() {

		@Override
		public void onSucess(UserInfo user) {
			// TODO Auto-generated method stub
			disshowPhoto();
			if (Logodialong != null) {
				Logodialong.dismiss();
			}
			if (ItApplication.getcurrnUser() != null) {
				ItApplication.getcurrnUser().setAvatar(user.getAvatar());
				SqlDataUtil.getInstance().saveUserInfo(ItApplication.getcurrnUser());
			}
			EventBus.getDefault().post(new MyEvent(0, user));
		}

		@Override
		public void onFail(String errorCode, String errorMsg) {
			// TODO Auto-generated method stub
			disshowPhoto();
			if (Logodialong != null) {
				Logodialong.dismiss();
			}
			new ToastUtils(MainActivity.this, errorMsg);
		}
	};

	private OnClickListener ImageListner = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_take_photo:// 相机获取
				imageUtils.openCameraImage();
				break;
			case R.id.btn_pick_photo:// 相册获取
				imageUtils.openLocalImage();
				break;
			case R.id.btn_cancel:// 取消
				if (photodialog != null) {
					photodialog.dismiss();
				}
				break;
			default:
				break;
			}
		}
	};

	@Override
	public void getNetWorkMsg(String msg, String param) throws JSONException {
		ResponseIsDel rr = new Gson().fromJson(msg, ResponseIsDel.class);
		if(rr.getCode()==110010) {
			String name = SharedPreferencesUtils.getInstance().getLoginUserName();
			SharedPreferencesUtils.getInstance().saveForIsLoginSave(name, false);
		}
	}

	@Override
	public void getNetWorkMsgError(String msg, String param) throws JSONException {
		
	}
	
	
}
