package com.it.ui.dialog;

import com.it.R;
import com.lidroid.xutils.ViewUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class SelectPhotoDialog extends Dialog {

	private Window window;
	private View.OnClickListener listener;
	public SelectPhotoDialog(Activity context,View.OnClickListener listener) {
		super(context,R.style.DialogStyleBottom);
		// TODO Auto-generated constructor stub
		this.listener=listener;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_select_photo);
		Button bt=(Button)findViewById(R.id.btn_take_photo);
		bt.setOnClickListener(listener);
		bt=(Button)findViewById(R.id.btn_pick_photo);
		bt.setOnClickListener(listener);
		bt=(Button)findViewById(R.id.btn_cancel);
		bt.setOnClickListener(listener);
	}

	
	  public void showDialog(int layoutResID, int x, int y){  
          setContentView(layoutResID);  
          windowDeploy(x, y);  
          //设置触摸对话框意外的地方取消对话框  
          setCanceledOnTouchOutside(true);  
          show();  
      }  
        
      //设置窗口显示  
      public void windowDeploy(int x, int y){  
          window = getWindow(); //得到对话框  
          window.setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画  
          window.setBackgroundDrawableResource(android.R.color.transparent); //设置对话框背景为透明  
          WindowManager.LayoutParams wl = window.getAttributes();  
          //根据x，y坐标设置窗口需要显示的位置  
          wl.x = x; //x小于0左移，大于0右移  
          wl.y = y; //y小于0上移，大于0下移    
//          wl.alpha = 0.6f; //设置透明度  
//          wl.gravity = Gravity.BOTTOM; //设置重力  
          window.setAttributes(wl);  
      }  
   
}
