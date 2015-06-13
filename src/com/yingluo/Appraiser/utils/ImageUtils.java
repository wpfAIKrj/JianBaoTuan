package com.yingluo.Appraiser.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

public class ImageUtils {
	
	public static final int GET_IMAGE_BY_CAMERA = 5001;
	public static final int GET_IMAGE_FROM_PHONE = 5002;
	public static final int GET_IMAGE_FROM_PHONE_KITKAT = 5003;
	public static final int CROP_IMAGE = 5004;
	public static final int PHOTO_REQUEST_CUT = 5004;
	public  String PICPATH=null;
	private Activity activity;
	public ImageUtils(Activity activity) {
		// TODO Auto-generated constructor stub
		this.activity=activity;
	}

	/**
	 * 启动相机
	 * @param activity
	 */
	public  void openCameraImage() {
		PICPATH=FileUtils.getInstance().NewUploadImagePath();
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(PICPATH)));
		activity.startActivityForResult(intent, ImageUtils.GET_IMAGE_BY_CAMERA);
	}
	
	/**
	 * 打开本地相册
	 * @param activity
	 */
	public  void openLocalImage() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		if(android.os.Build.VERSION.SDK_INT>=19){  
			activity.startActivityForResult(intent, GET_IMAGE_FROM_PHONE_KITKAT);    
		}else{              
			activity.startActivityForResult(intent, GET_IMAGE_FROM_PHONE);   
		}   
	}
	
	/**
	 * 选择图片后，获取图片的路径(使用与4.4系统一下)
	 * @param requestCode
	 * @param data
	 */
	public  void doPhoto(Intent data) {
		// 从相册取图片，有些手机有异常情况，请注意
			if (data == null) {
				Toast.makeText(activity, "选择图片文件出错",
						Toast.LENGTH_SHORT).show();
				return;
			}
			Uri photoUri = data.getData();
			if (photoUri == null) {
				Toast.makeText(activity, "选择图片文件出错",
						Toast.LENGTH_SHORT).show();
				return;
			}
		String[] pojo = {MediaStore.Images.Media.DATA};
		Cursor cursor = activity.managedQuery(photoUri, pojo, null, null, null);
		if (cursor != null) {
			int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
			cursor.moveToFirst();
			PICPATH = cursor.getString(columnIndex);
			cursor.close();
		}
		if (PICPATH != null
				&& (PICPATH.endsWith(".png") || PICPATH.endsWith(".PNG")
						|| PICPATH.endsWith(".jpg") || PICPATH.endsWith(".JPG")|| PICPATH.endsWith(".WEBP")|| PICPATH.endsWith(".webp"))) {
		} else {
			Toast.makeText(activity, "选择图片文件不正确",
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 4.4以上系统处理图片的方法
	 * @param activity
	 * @param data
	 */
	@SuppressLint("NewApi")
	public  void doPhotoKIKAT( Intent data) {
		// TODO Auto-generated method stub
		if (data == null) {
			Toast.makeText(activity, "选择图片文件出错",
					Toast.LENGTH_SHORT).show();
			return;
		}
		Uri photoUri = data.getData();
		if (photoUri == null) {
			Toast.makeText(activity, "选择图片文件出错",
					Toast.LENGTH_SHORT).show();
			return;
		}
		if(DocumentsContract.isDocumentUri(activity, photoUri)){
            String wholeID = DocumentsContract.getDocumentId(photoUri);
            String id = wholeID.split(":")[1];
            String[] column = { MediaStore.Images.Media.DATA };
            String sel = MediaStore.Images.Media._ID + "=?";
            Cursor cursor = activity.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column,
                    sel, new String[] { id }, null);
            int columnIndex = cursor.getColumnIndex(column[0]);
            if (cursor.moveToFirst()) {
                PICPATH = cursor.getString(columnIndex);
            }
            cursor.close();
        }else{
            String[] projection = { MediaStore.Images.Media.DATA };
            Cursor cursor = activity.getContentResolver().query(photoUri, projection, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
           if( cursor.moveToFirst()){
        	   PICPATH = cursor.getString(column_index);
           }
           cursor.close();
        }
		if (PICPATH != null
				&& (PICPATH.endsWith(".png") || PICPATH.endsWith(".PNG")
						|| PICPATH.endsWith(".jpg") || PICPATH.endsWith(".JPG")|| PICPATH.endsWith(".WEBP")|| PICPATH.endsWith(".webp"))) {
		} else {
			Toast.makeText(activity, "选择图片文件不正确",
					Toast.LENGTH_SHORT).show();
		}
	}
	
	/*
     * 剪切图片(相册)
     */
    public void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");
        intent.setData(uri);
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        activity.startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
  
    private Uri getTempUri() {
    	if(PICPATH!=null){
    		deletePic();
    	}
        Uri tempPhotoUri = Uri.fromFile(FileUtils.getInstance().getLogoPath());
        return tempPhotoUri;
    }
     
   
	
	/**
	 * 删除原来的文件
	 */
	public  void deletePic() {
		// TODO Auto-generated method stub
		if(PICPATH!=null){
			try {
				File file=new File(PICPATH);
				if(file.exists()){
					file.delete();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
}
	





