package com.yingluo.Appraiser.ui.dialog;

import com.yingluo.Appraiser.R;
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

	public SelectPhotoDialog(Activity context, View.OnClickListener listener) {
		super(context, R.style.dialog_style);
		// TODO Auto-generated constructor stub
		this.listener = listener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_select_photo);
		Button bt = (Button) findViewById(R.id.btn_take_photo);
		bt.setOnClickListener(listener);
		bt = (Button) findViewById(R.id.btn_pick_photo);
		bt.setOnClickListener(listener);
		bt = (Button) findViewById(R.id.btn_cancel);
		bt.setOnClickListener(listener);
		windowDeploy();
		setCanceledOnTouchOutside(true);
	}

	// 设置窗口显示
	public void windowDeploy() {
		window = getWindow(); // 得到对话框
		window.getDecorView().setPadding(0, 0, 0, 0);
		window.setWindowAnimations(R.style.dialogWindowAnim); // 设置窗口弹出动画
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.width = WindowManager.LayoutParams.FILL_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		window.setAttributes(lp);
	}

}
