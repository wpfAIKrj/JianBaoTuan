package com.it.utils;

import com.it.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class DialogUtil {
	
	 public static Dialog createLoadingDialog(Context context, String msg) {  
	        LayoutInflater inflater = LayoutInflater.from(context);  
	        View v = inflater.inflate(R.layout.loading_dialog, null);
	        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
	        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);  
	        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
	        AnimationDrawable animationDrawable = (AnimationDrawable) context.getResources().getDrawable(R.drawable.show_loading);
	        spaceshipImage.setBackgroundDrawable(animationDrawable);
	        
	        animationDrawable.start();
	        tipTextView.setText(msg); 
	        tipTextView.setTextColor(0xffffffff);
	        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);
	        loadingDialog.setCancelable(false);
	        loadingDialog.getWindow().setGravity(Gravity.CENTER);
	        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(  
	                LinearLayout.LayoutParams.WRAP_CONTENT,  
	                LinearLayout.LayoutParams.WRAP_CONTENT));
	        return loadingDialog;  
	  
	    }
}
