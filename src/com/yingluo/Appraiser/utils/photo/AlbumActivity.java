package com.yingluo.Appraiser.utils.photo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.utils.photo.LocalImageAdapter.CheckChangeL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AlbumActivity extends Activity implements OnClickListener{
	//全部图片，单独放在列表中
	private ArrayList<String> imageList ; 
	
	private LinkedHashMap<String, ArrayList<String>> mGruopMap =
			new LinkedHashMap<String, ArrayList<String>>();
	public ArrayList<ImageBean> albumList = new ArrayList<ImageBean>();
	
	private final static int SCAN_OK = 1;
	private ProgressDialog mProgressDialog;
	private GridView imageGridView;
	private Button btn_image_cancel ;
	private LocalImageAdapter imageAdapter ;
	private RelativeLayout btn_submit,bt_preview;
	private TextView selectView ;
	public  ArrayList<String> selectList ;
	public int type;
	
	private Handler mHandler = new Handler(Looper.getMainLooper()){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case SCAN_OK:
				//关闭进度条
				mProgressDialog.dismiss();
				imageAdapter = new LocalImageAdapter(
							AlbumActivity.this, imageList,selectList) ;
				imageGridView.setAdapter(imageAdapter);
				imageAdapter.SetCheckChangeL(new CheckChangeL() {
					
					@Override
					public void onChange() {
						selectView.setText("完成（"+selectList.size()+"）");
					}
				});
				selectView.setText("完成（"+selectList.size()+"）");
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_select_pic);
		selectList=getIntent().getStringArrayListExtra("list");
		type=getIntent().getIntExtra("type", 0);
		findViews() ;
		getImages() ;
	}
	
	private void findViews(){
		imageList = new ArrayList<String>() ;
		btn_image_cancel = (Button)findViewById(
				R.id.select_pic_title_btn_cancel) ;
		imageGridView = (GridView)findViewById(
				R.id.select_pic_gridview);
		btn_submit = (RelativeLayout)findViewById(
				R.id.select_pic_submit) ;
		selectView = (TextView) findViewById(
				R.id.select_pic_submit_content) ;
		bt_preview=(RelativeLayout)findViewById(
				R.id.select_pic_private) ;
		selectView.setText("完成（0）");
		btn_image_cancel.setOnClickListener(this);
		btn_submit.setOnClickListener(this);
		bt_preview.setOnClickListener(this);
	}
	
	/**
	 * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中
	 */
	private void getImages() {
		if(!Environment.getExternalStorageState()
				.equals(Environment.MEDIA_MOUNTED)){
			Toast.makeText(this
					, "暂无外部存储", Toast.LENGTH_SHORT).show();
			return;
		}
		
		//显示进度条
		mProgressDialog = ProgressDialog.show(
				this, null, "正在加载...");
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				ContentResolver mContentResolver = getContentResolver();

				//只查询jpeg和png的图片
				Cursor mCursor = mContentResolver.query(mImageUri, null,
						MediaStore.Images.Media.MIME_TYPE + "=? or "
								+ MediaStore.Images.Media.MIME_TYPE + "=?",
						new String[] { "image/jpeg", "image/png" }
							, MediaStore.Images.Media.DATE_MODIFIED);
				
				while (mCursor.moveToNext()) {
					//获取图片的路径
					String path = mCursor.getString(mCursor
							.getColumnIndex(MediaStore.Images.Media.DATA));
					//获取该图片的父路径名
					String parentName = new File(path).getParentFile().getName();
					
					imageList.add(path) ;
					
					//根据父路径名将图片放入到mGruopMap中
					if (!mGruopMap.containsKey(parentName)) {
						ArrayList<String> chileList = new ArrayList<String>();
						chileList.add(path);
						mGruopMap.put(parentName, chileList);
					} else {
						mGruopMap.get(parentName).add(path);
					}
				}
				albumList = subGroupOfImage(mGruopMap) ;
				
				mCursor.close();
				
				//通知Handler扫描图片完成
				mHandler.sendEmptyMessage(SCAN_OK);
			}
		}).start();
	}
	
	/**
	 * 组装分组界面GridView的数据源，因为我们扫描手机的时候将图片信息放在HashMap中
	 * 所以需要遍历HashMap将数据组装成List
	 * 
	 * @param mGruopMap
	 * @return
	 */
	private ArrayList<ImageBean> subGroupOfImage(HashMap<String
			, ArrayList<String>> mGruopMap){
		if(mGruopMap.size() == 0){
			return null;
		}
		ArrayList<ImageBean> list = new ArrayList<ImageBean>();
		
		Iterator<Map.Entry<String, ArrayList<String>>>
				it = mGruopMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, ArrayList<String>> entry = it.next();
			ImageBean mImageBean = new ImageBean();
			String key = entry.getKey();
			ArrayList<String> value = entry.getValue();
			
			mImageBean.setFolderName(key);
			mImageBean.setImageCounts(value.size());
			mImageBean.setTopImagePath(value.get(0));//获取该组的第一张图片
			mImageBean.setPathList(value);
			
			list.add(mImageBean);
		}
		
		return list;
	}
	
	@Override
	public void onClick(View v) {
		Intent intent=null;
		switch(v.getId()){
		case R.id.select_pic_title_btn_cancel:
			setResult(RESULT_CANCELED, intent);
			finish();
			break;
		case R.id.select_pic_submit:
			selectList=imageAdapter.selectList;
			intent=getIntent();
			intent.putExtra("type", type);
			intent.putExtra("list", selectList);
			setResult(RESULT_OK, intent);
			finish();
			break ;
		case R.id.select_pic_private:
			selectList=imageAdapter.selectList;
			if(selectList.size()>0){
				intent=new Intent(AlbumActivity.this, GalleryActivity.class);
				intent.putExtra("type", type);
				intent.putExtra("list", selectList);
				startActivityForResult(intent, Const.TO_GALLERY);
			}
			break;
		}
	}
	
	/**
	 * 监听返回按钮
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			setResult(RESULT_CANCELED, getIntent());
			finish();
		}
		return true;
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Intent intent=null;
		switch (requestCode) {
		case Const.TO_GALLERY:
			if(resultCode==RESULT_OK){
				intent=getIntent();
				intent.putExtras(data.getExtras());
				setResult(RESULT_OK, intent);
				finish();
			}
			if(resultCode==RESULT_CANCELED){
				intent=getIntent();
				selectList=data.getStringArrayListExtra("list");
				imageAdapter.selectList=selectList;
				imageAdapter.notifyDataSetChanged();
			}
			break;
		default:
			break;
		}
	}
	
}
