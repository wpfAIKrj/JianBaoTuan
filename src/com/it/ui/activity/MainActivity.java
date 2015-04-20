package com.it.ui.activity;


import com.it.R;
import com.it.ui.fragment.HomeFragment;
import com.it.ui.fragment.IdentiyFragment;
import com.it.ui.fragment.InformationFragment;
import com.it.ui.fragment.MyFragment;
import com.it.utils.SystemBarTintManager;
import com.it.view.MyTabWidget;
import com.it.view.MyTabWidget.OnTabSelectedListener;

import android.annotation.TargetApi;
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
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

/**
 * 主页面
 * @author Administrator
 *
 */
public class MainActivity extends FragmentActivity implements 
OnTabSelectedListener{
	
	
	private FragmentManager mFragmentManager;
	private HomeFragment mHomeFragment;
	private IdentiyFragment mIdentiyFragment;
	private InformationFragment mInformationFragment;
	private MyFragment mMyFragment;
	
	private long mkeyTime=0;
	private MyTabWidget mTabWidget;
	private int mIndex=0;
	private SystemBarTintManager mTintManager;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            mTintManager = new SystemBarTintManager(this); 
    		mTintManager.setStatusBarTintEnabled(true);
    		mTintManager.setNavigationBarTintEnabled(true);
    		int color=getResources().getColor(R.color.blacground_color);
    		mTintManager.setTintColor(color);
		}
        setContentView(R.layout.activity_main);  
		init();
		initEvents();
    }

    @TargetApi(19)
	private void setTranslucentStatus(boolean b) {
		// TODO Auto-generated method stub
			Window win = getWindow();
	        WindowManager.LayoutParams winParams = win.getAttributes();
	        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
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
		case 3:if (null == mMyFragment) {
				mMyFragment = new MyFragment();
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


  
}
