package com.it.utils;

import com.it.R;
import com.it.inter.DialogForResult;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * dialog工具
 * @author Administrator
 *
 */
public class DialogUtil {
	
	/**
	 * 创建加载过程中的dialog
	 * @param context
	 * @param msg
	 * @return
	 */
	 public static Dialog createLoadingDialog(Context context, String msg) {  
	        LayoutInflater inflater = LayoutInflater.from(context);  
	        View v = inflater.inflate(R.layout.loading_dialog, null);
	        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
	       // ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);  
	        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
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
	 
	 
	 public static Dialog createShowDialog(Context context,String title,final DialogForResult lis){
		 LayoutInflater inflater = LayoutInflater.from(context);  
	      View v = inflater.inflate(R.layout.dialog_layout, null);
	      TextView tv=(TextView) v.findViewById(R.id.title);
	      tv.setText(title);
	      Button bt=(Button)v.findViewById(R.id.dialog_sure);
	      bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(lis!=null){
					lis.onSucess();
				}
			}
	      });
	      bt=(Button)v.findViewById(R.id.dialog_cancle);
	      bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(lis!=null){
					lis.onCancel();
				}
			}}
	      );
		 Dialog dialog=new Dialog(context, R.style.dialog_style);
		 dialog.setCancelable(false);
		 dialog.getWindow().setGravity(Gravity.CENTER);
		 dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		 dialog.setContentView(v,new LayoutParams(SystemUtils.getDisplaysWidth(context)*2/3, SystemUtils.getDisplaysWidth(context)/4));
		 return dialog;
	 }
}
