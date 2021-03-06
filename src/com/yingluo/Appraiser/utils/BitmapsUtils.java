package com.yingluo.Appraiser.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.core.BitmapSize;
import com.lidroid.xutils.bitmap.factory.BitmapFactory;
import com.lidroid.xutils.cache.FileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.IoUtils.CopyListener;
import com.nostra13.universalimageloader.utils.StorageUtils;
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
//	private BitmapUtils utils = null;
//	private BitmapDisplayConfig config;// 显示使用的

	private BitmapsUtils(Context context) {

		//设置缓存的路径
        File cacheDir = new File(FileUtils.getInstance().getUpImage());
		if (!cacheDir.exists()) {
			cacheDir.mkdir();
		}
        
		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
		config.threadPriority(3);
		config.denyCacheImageMultipleSizesInMemory();
		config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
		config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
		config.diskCache(new UnlimitedDiskCache(cacheDir));
		config.memoryCache(new UsingFreqLimitedMemoryCache(2*1024*1024));
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		config.imageDownloader(new BaseImageDownloader(context, 5*1000, 10*1000));
		config.writeDebugLogs(); // Remove for release app
		
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		.showImageForEmptyUri(R.drawable.load_fail)
		.showImageOnFail(R.drawable.load_fail)
		.considerExifParams(true)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.resetViewBeforeLoading(true)
		.displayer(new FadeInBitmapDisplayer(1000))
		.build();
		
		config.defaultDisplayImageOptions(options);
		ImageLoader.getInstance().init(config.build());
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
		//utils.display(container, uri);
		if(uri.contains("http")){
			ImageLoader.getInstance().displayImage(uri, container);
		}else{
			ImageLoader.getInstance().displayImage("file://"+uri, container);
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
	public void display(ImageView container, String uri, int type) {
		if (type == TYPE_NO) {
			display(container, uri);
		} else {
//			BitmapDisplayConfig config = this.config.cloneNew();
//			BitmapSize bitmapMaxSize = new BitmapSize(container.getWidth(),
//					container.getHeight());
//			config.setBitmapMaxSize(bitmapMaxSize);
//			if(!(container instanceof CircleImageView)){
//				
//				container.setScaleType(ScaleType.FIT_XY);
//			}
			//utils.display(container, uri, config);
			if(!(container instanceof CircleImageView)){
			container.setScaleType(ScaleType.FIT_XY);
			}
			if(uri.contains("http")){
				ImageLoader.getInstance().displayImage(uri, container);
			}else{
				ImageLoader.getInstance().displayImage("file://"+uri, container);
			}
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
//			BitmapDisplayConfig config = this.config.cloneNew();
//			BitmapSize bitmapMaxSize = new BitmapSize(container.getWidth(),
//					container.getHeight());
//			config.setBitmapMaxSize(bitmapMaxSize);
			if(!(container instanceof CircleImageView)){
				
				container.setScaleType(ScaleType.CENTER_CROP);
			}
			
			if(uri.contains("http")){
				ImageLoader.getInstance().displayImage(uri, container);
			}else{
				ImageLoader.getInstance().displayImage("file://"+uri, container);
			}

		
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
		if(uri.contains("http")){
			ImageLoader.getInstance().displayImage(uri, container);
		}else{
			ImageLoader.getInstance().displayImage("file://"+uri, container);
		}
	}

	public static String makeQiNiuRrl(String url,int width,int height) {
		StringBuffer buffer = new StringBuffer(url);
		buffer.append("?imageView2/4/w/"+width+"/h/"+height);
		return buffer.toString();
	}
	
//	/**
//	 * 图片加载
//	 * 
//	 * @param container
//	 *            非imageview控件
//	 * @param uri
//	 *            地址
//	 * @param width
//	 *            显示的宽
//	 * @param height
//	 *            显示的高
//	 */
//	public void display(View container, String uri, int width, int height) {
////		config.setBitmapMaxSize(new BitmapSize(width, height));
////		utils.display(container, uri, config);
//		if(uri.contains("http")){
//			ImageLoader.getInstance().displayImage(uri, container);
//		}else{
//			ImageLoader.getInstance().displayImage("file://"+uri, container);
//		}
//	}

}
