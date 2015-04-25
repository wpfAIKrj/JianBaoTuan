package com.it.ui.base;


import com.it.R;
import com.it.utils.SystemBarTintManager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class BaseActivity extends Activity {
	protected SystemBarTintManager mTintManager;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
			setWindowStyle();
	}
	
    @TargetApi(19)
    private void setWindowStyle() {
		// TODO Auto-generated method stub
    	if (Build.VERSION.SDK_INT >= 19) {
            setTranslucentStatus(true);
            mTintManager = new SystemBarTintManager(this); 
    		mTintManager.setStatusBarTintEnabled(true);
    		mTintManager.setNavigationBarTintEnabled(true);
		}
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
}
