package com.yingluo.Appraiser.ui.dialog;

import com.yingluo.Appraiser.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

public class RegisterDialog extends Dialog {

	public RegisterDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_register);
		setCanceledOnTouchOutside(false);
	}

}
