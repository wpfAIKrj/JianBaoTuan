package com.it.ui.dialog;

import com.it.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

public class SelectPhotoDialog extends Dialog {

	public SelectPhotoDialog(Context context) {
		super(context,R.style.DialogStyleBottom);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_select_photo);
	}

}
