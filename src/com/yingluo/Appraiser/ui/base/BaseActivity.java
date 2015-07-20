package com.yingluo.Appraiser.ui.base;


import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.utils.ActivityTaskManager;
import com.yingluo.Appraiser.utils.HelpUtils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;

public class BaseActivity extends Activity {
	//protected SystemBarTintManager mTintManager;
	private ActivityTaskManager taskManager;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		taskManager=ActivityTaskManager.getInstance();
		taskManager.putActivity(this.getClass().getName(), this);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		taskManager.removeActivity(this.getClass().getName());
		super.onDestroy();	
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
	

	
	/**
     * 隐藏软键盘
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();
            if (HelpUtils.isShouldHideInput(v, ev)) {
                InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                HelpUtils.hideSoftInput(v.getWindowToken(), im);
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
