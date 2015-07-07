package com.yingluo.Appraiser.utils.photo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtils {

	/**
	 * 计算 inSampleSize�?? 用于压缩图片
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// 源图片的 宽，�??
		int width = options.outWidth;
		int height = options.outHeight;

		int inSampleSize = 1;
		if (width > reqWidth && height > reqHeight) {
			int wRadio = Math.round((float) width / (float) reqWidth);
			int hRadio = Math.round((float) height / (float) reqHeight);
			inSampleSize = Math.max(wRadio, hRadio);
		}
		return inSampleSize;
	}

	/**
	 * 根据inSampleSize,压缩图片
	 */
	public static Bitmap decodeSampledBitmapFromResource(String path,
			int width, int height) {
		// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		// 调用上面定义的方法计算inSampleSize�??
		options.inSampleSize = calculateInSampleSize(options, width, height);
		// 使用获取到的inSampleSize值再次解析图�??
		options.inJustDecodeBounds = false;
		Bitmap bitmap = BitmapFactory.decodeFile(path, options);
		return bitmap;
	}

}
