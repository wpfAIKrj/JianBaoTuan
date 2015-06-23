package com.yingluo.Appraiser.utils;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.core.BitmapSize;
import com.lidroid.xutils.bitmap.factory.BitmapFactory;
import com.lidroid.xutils.cache.FileNameGenerator;
import com.yingluo.Appraiser.view.CircleImageView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/**
 * 图片加载工具类
 * 
 * @author xy418
 *
 */
public class BitmapsUtils {
	public static final int TYPE_YES = 1;
	public static final int TYPE_NO = 0;

	private static BitmapsUtils mInstance = null;
	private BitmapUtils utils = null;
	private BitmapDisplayConfig config;// 显示使用的

	private BitmapsUtils(Context context) {
		utils = new BitmapUtils(context);
		utils.configDefaultConnectTimeout(10000);
		utils.configDefaultImageLoadAnimation(AnimationUtils.loadAnimation(
				context, R.anim.bitmap_show));
		utils.configDefaultLoadFailedImage(R.drawable.test3);
		utils.configDefaultLoadingImage(R.drawable.test3);
		utils.configDefaultReadTimeout(10000);
		utils.configDiskCacheEnabled(true);
		utils.configMemoryCacheEnabled(true);
		config = new BitmapDisplayConfig();
		config.setLoadingDrawable(context.getResources().getDrawable(
				R.drawable.test3));
		config.setLoadFailedDrawable(context.getResources().getDrawable(
				R.drawable.test3));
		config.setAnimation(AnimationUtils.loadAnimation(context,
				R.anim.bitmap_show));

	}

	public static BitmapsUtils getInstance() {
		return mInstance;
	}

	public static void init(Context context) {
		mInstance = new BitmapsUtils(context);
	}

	/**
	 * 图片加载
	 * 
	 * @param container
	 *            imageview
	 * @param uri
	 *            地址
	 */
	public void display(ImageView container, String uri) {
		utils.display(container, uri);
	}

	/**
	 * 图片加载
	 * 
	 * @param container
	 *            imageview
	 * @param uri
	 *            地址
	 * @param type
	 *            是否按指定大小加载
	 */
	public void display(ImageView container, String uri, int type) {
		if (type == TYPE_NO) {
			display(container, uri);
		} else {
			BitmapDisplayConfig config = this.config.cloneNew();
			BitmapSize bitmapMaxSize = new BitmapSize(container.getWidth(),
					container.getHeight());
			config.setBitmapMaxSize(bitmapMaxSize);
			if(!(container instanceof CircleImageView)){
				
				container.setScaleType(ScaleType.FIT_XY);
			}
			utils.display(container, uri, config);
		}
	}
	
	
	/**
	 * 图片加载
	 * 
	 * @param container
	 *            imageview
	 * @param uri
	 *            地址
	 * @param type
	 *            是否按指定大小加载
	 */
	public void displayForxy(ImageView container, String uri) {
			BitmapDisplayConfig config = this.config.cloneNew();
			BitmapSize bitmapMaxSize = new BitmapSize(container.getWidth(),
					container.getHeight());
			config.setBitmapMaxSize(bitmapMaxSize);
			if(!(container instanceof CircleImageView)){
				
				container.setScaleType(ScaleType.CENTER_CROP);
			}
			
			utils.display(container, uri);
			
//			utils.display(container, uri, config, new BitmapLoadCallBack<ImageView>(){
//
//				@Override
//				public void onLoadCompleted(ImageView container, String uri,
//						Bitmap bitmap, BitmapDisplayConfig config,
//						BitmapLoadFrom from) {
//					// TODO 自动生成的方法存根
//					BitmapCompressor.setBsetBitmap(bitmap, container, container.getMeasuredWidth(), container.getMeasuredHeight());
//				}
//
//				@Override
//				public void onLoadFailed(ImageView container, String uri,
//						Drawable drawable) {
//					// 
//					BitmapDrawable bd = (BitmapDrawable) drawable;
//					BitmapCompressor.setBsetBitmap(bd.getBitmap(), container, container.getMeasuredWidth(), container.getMeasuredHeight());
//
//				}
//				
//			});
		
	}
	
	
	

	/**
	 * 图片加载
	 * 
	 * @param container
	 *            imageview
	 * @param uri
	 *            地址
	 * @param width
	 *            显示的宽
	 * @param height
	 *            显示的高
	 */
	public void display(ImageView container, String uri, int width, int height) {
		config.setBitmapMaxSize(new BitmapSize(width, height));
		utils.display(container, uri, config);
	}

	/**
	 * 图片加载
	 * 
	 * @param container
	 *            非imageview控件
	 * @param uri
	 *            地址
	 * @param width
	 *            显示的宽
	 * @param height
	 *            显示的高
	 */
	public void display(View container, String uri, int width, int height) {
		config.setBitmapMaxSize(new BitmapSize(width, height));
		utils.display(container, uri, config);
	}

}
