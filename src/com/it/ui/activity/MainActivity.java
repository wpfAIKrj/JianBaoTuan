package com.it.ui.activity;


import com.it.R;
import com.it.ui.fragment.HomeFragment;
import com.it.ui.fragment.IdentiyFragment;
import com.it.ui.fragment.InformationFragment;
import com.it.ui.fragment.MyFragment;
import com.it.view.MyTabWidget;
import com.it.view.MyTabWidget.OnTabSelectedListener;
import com.it.view.menu.ResideMenu;
import com.it.view.menu.ResideMenuItem;

import android.annotation.TargetApi;
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
	//private SystemBarTintManager mTintManager;
	private ResideMenu resideMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		//setWindowStyle();
        setContentView(R.layout.activity_main);  
		init();
		initEvents();
		setUpMenu();
    }

//    @TargetApi(19)
//    private void setWindowStyle() {
//		// TODO Auto-generated method stub
//    	if (Build.VERSION.SDK_INT >= 19) {
//            setTranslucentStatus(true);
//            mTintManager = new SystemBarTintManager(this); 
//    		mTintManager.setStatusBarTintEnabled(true);
//    		mTintManager.setNavigationBarTintEnabled(true);
//    		int color=getResources().getColor(R.color.dialog_title_color);
//    		mTintManager.setTintColor(color);
//		}
//	}

	private void setUpMenu() {
		// TODO Auto-generated method stub
    	 resideMenu = new ResideMenu(this);
         resideMenu.setBackground(R.drawable.menu_background);
         resideMenu.attachToActivity(this);
         resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);
         resideMenu.setShadowVisible(false);
         resideMenu.setButtonOnClickListener(this);
         
	}
    
   

	@TargetApi(19)
	private void setTranslucentStatus(boolean b) {
		// TODO Auto-generated method stub
			Window win = getWindow();
	        WindowManager.LayoutParams winParams = win.getAttributes();
	        final int bits = 0x4000000;//WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
	        if (b) {
	            winParams.flags |= bits;
	        } else {
	            winParams.flags &= ~bits;
	        }
	        win.setAttributes(winParams);
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
				startActivity(new Intent(MainActivity.this, PublishedActivity.class));
			}
		});
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
		outState.putInt("index", mIndex);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		mIndex = savedInstanceState.getInt("index");
	}

    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
//		
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
				transaction.add(R.id.center_layout, mMyFragment);
			} else {
				transaction.show(mMyFragment);
			}
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
			startActivity(new Intent(MainActivity.this, PasswordActivity.class));
		}
		if(v.getId()==R.id.layout_item3){//意见反馈
			startActivity(new Intent(MainActivity.this, FeedbackActivity.class));
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


  
}
