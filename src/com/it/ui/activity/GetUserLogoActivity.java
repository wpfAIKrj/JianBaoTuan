package com.it.ui.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.it.R;
import com.it.config.Const;
import com.it.ui.base.BaseActivity;
import com.it.utils.BitmapCompressor;
import com.it.utils.FileUtils;
import com.it.utils.ImageUtils;
import com.it.utils.SystemUtils;
import com.it.view.CutView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
/**
 * 获取人物头像
 * @author Administrator
 *
 */
public class GetUserLogoActivity extends BaseActivity  {
	
	@ViewInject(R.id.cut_save)
	private Button cutBtn;
	
	@ViewInject(R.id.cut_cancel)
	private Button cancelBtn;
	
	
	private Bitmap bitmap = null;
	
	@ViewInject(R.id.myCutView)
	private CutView cutView;

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				Intent intent =getIntent();
				FileUtils.getInstance().deleteFile(path);
				intent.putExtra(Const.PICPATH, msg.obj.toString());
				setResult(Activity.RESULT_OK,intent);
				finish();
				break;
			}
		}

	};
	private int reqWidth;
	private int reqHeight;
	
	@ViewInject(R.id.home_title)
	private TextView title;

	private String path;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		ViewUtils.inject(this);
		reqHeight=this.getWindowManager().getDefaultDisplay().getHeight();
		reqWidth=this.getWindowManager().getDefaultDisplay().getWidth();
		path=getIntent().getStringExtra(Const.PICPATH);
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		try {
			LogUtils.d("压缩的宽："+reqWidth+"压缩的高："+reqHeight);
			bitmap =BitmapCompressor.decodeSampledBitmapFromFile(path, reqWidth, reqHeight);
			if (bitmap != null) {
				cutView.setBitmap(bitmap);
				cutView.setHandler(handler);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		try {
			if (bitmap != null && !bitmap.isRecycled())
				bitmap.recycle();
			System.gc();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@OnClick({R.id.cut_save,R.id.cut_cancel,R.id.button_category})
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.cut_save:
			cutView.setSave(true);
			break;
		case R.id.cut_cancel:
			FileUtils.getInstance().deleteFile(path);
			setResult(RESULT_CANCELED);
			finish();
			break;
		case R.id.button_category:
			FileUtils.getInstance().deleteFile(path);
			setResult(RESULT_CANCELED);
			finish();
			break;
		}
	}

}
