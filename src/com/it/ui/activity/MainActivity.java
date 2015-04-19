package com.it.ui.activity;


import com.it.R;
import com.it.ui.fragment.HomeFragment;
import com.it.ui.fragment.IdentiyFragment;
import com.it.ui.fragment.InformationFragment;
import com.it.ui.fragment.MyFragment;
import com.it.view.MyTabWidget;
import com.it.view.MyTabWidget.OnTabSelectedListener;

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
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

/**
 * 主页面
 * @author Administrator
 *
 */
public class MainActivity extends FragmentActivity implements 
OnTabSelectedListener,OnTouchListener, OnGestureListener{
	
	
	private FragmentManager mFragmentManager;
	private HomeFragment mHomeFragment;
	private IdentiyFragment mIdentiyFragment;
	private InformationFragment mInformationFragment;
	private MyFragment mMyFragment;
	
	private GestureDetector mGestureDetector;
	private long mkeyTime=0;
	private MyTabWidget mTabWidget;
	private int mIndex=0;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGestureDetector = new GestureDetector((OnGestureListener) this);  
		init();
		initEvents();
    }



	private void init() {
		// TODO Auto-generated method stub
		mFragmentManager = getSupportFragmentManager();
		mTabWidget = (MyTabWidget) findViewById(R.id.tab_widget);
	     LinearLayout viewSnsLayout = (LinearLayout)findViewById(R.id.viewSnsLayout);    
	     viewSnsLayout.setOnTouchListener(this);    
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
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
    private int verticalMinDistance = 40;
    private int minVelocity         = 0;

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		 if (e1.getX() - e2.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {


	            if(mIndex>=2){
	            	
	            }else{
	            	mIndex=mIndex+1;
	            onTabSelected(mIndex);
	    		mTabWidget.setTabsDisplay(this, mIndex);
	    		mTabWidget.setIndicateDisplay(this, mIndex, true);
	            }
	        } else if (e2.getX() - e1.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {

	   
	        	   if(mIndex>0){
	        		   mIndex=mIndex-1;
	        		      onTabSelected(mIndex);
	        	       		mTabWidget.setTabsDisplay(this, mIndex);
	        	       		mTabWidget.setIndicateDisplay(this, mIndex, true);
	               }else{
	               
	               }
	        }

	        return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		 return mGestureDetector.onTouchEvent(event); 
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
