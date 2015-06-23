package com.yingluo.Appraiser.utils;
 
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.lidroid.xutils.util.LogUtils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
 
/**
 * 图片压缩工具类
 * @author touch_ping
 * 2015-1-5 下午1:29:59
 */
public class BitmapCompressor {
    /**
     * 质量压缩
     * @author ping 2015-1-5 下午1:29:58
     * @param image
     * @param maxkb
     * @return
     */
    public static Bitmap compressBitmap(Bitmap image) {
        //L.showlog(压缩图片);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
//      Log.i(test,原始大小 + baos.toByteArray().length);
//        while (baos.toByteArray().length / 1024 > 500) { // 循环判断如果压缩后图片是否大于(maxkb)50kb,大于继续压缩
////          Log.i(test,压缩一次!);
//            baos.reset();// 重置baos即清空baos
//            options -= 10;// 每次都减少10
//            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
//        }
//      Log.i(test,压缩后大小 + baos.toByteArray().length);
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }
     
    /**
     * http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
     * 官网：获取压缩后的图片
     * 
     * @param res
     * @param resId
     * @param reqWidth
     *            所需图片压缩尺寸最小宽度
     * @param reqHeight
     *            所需图片压缩尺寸最小高度
     * @return
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res,
            int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
         
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
 
    /**
     * 官网：获取压缩后的图片
     * 
     * @param res
     * @param resId
     * @param reqWidth
     *            所需图片压缩尺寸最小宽度
     * @param reqHeight
     *            所需图片压缩尺寸最小高度
     * @return
     */
    public static Bitmap decodeSampledBitmapFromFile(String filepath,
            int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filepath, options);
 
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        options.inJustDecodeBounds = false;
        return  compressBitmap(BitmapFactory.decodeFile(filepath, options));
    }
 
    public static Bitmap decodeSampledBitmapFromBitmap(Bitmap bitmap,
            int reqWidth, int reqHeight) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
         
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        options.inJustDecodeBounds = false;
        return compressBitmap(BitmapFactory.decodeByteArray(data, 0, data.length, options));
    }
 
    
    /**
     * 计算压缩比例值(改进版 by touch_ping)
     * 
     * 原版2>4>8...倍压缩
     * 当前2>3>4...倍压缩
     * 
     * @param options
     *            解析图片的配置信息
     * @param reqWidth
     *            所需图片压缩尺寸最小宽度O
     * @param reqHeight
     *            所需图片压缩尺寸最小高度
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
            int reqWidth, int reqHeight) {
         
        final int picheight = options.outHeight;
        final int picwidth = options.outWidth;
        LogUtils.d("原尺寸:" +  picwidth +"*" +picheight);
         
        int targetheight = picheight;
        int targetwidth = picwidth;
        int inSampleSize = 1;
         
        if (targetheight > reqHeight || targetwidth > reqWidth) {
            while (targetheight  >= reqHeight
                    && targetwidth>= reqWidth) {
            	LogUtils.d("压缩: "+inSampleSize + "倍");
                inSampleSize += 1;
                targetheight = picheight/inSampleSize;
                targetwidth = picwidth/inSampleSize;
            }
        }
         
        LogUtils.d("最终压缩比例:" +inSampleSize + "倍");
        LogUtils.d("新尺寸:" +  targetwidth + "* "+targetheight);
        return inSampleSize;
    }
    
    /**
     * 在固定大小的ImageView中，保证图片不变形
     * 
     * @param bitmap
     * @param imageView
     * @param width
     * @param height
     */
    public static void setBsetBitmap(Bitmap bitmap, ImageView imageView, int width,
                    int height) {
            //计算最佳缩放倍数
//            float scaleX = (float) width / bitmap.getWidth();
//            float scaleY = (float) height / bitmap.getHeight();
//            float bestScale = scaleX < scaleY ? scaleX : scaleY;
            //计算最佳缩放倍数,以填充宽高为目标
            float scaleX = (float) width / bitmap.getWidth();
            float scaleY = (float) height / bitmap.getHeight();
            float bestScale = scaleX > scaleY ? scaleX : scaleY;
            //以填充高度的前提下，计算最佳缩放倍数
    //        float bestScale = (float) height / bitmap.getHeight();

            float subX = (width - bitmap.getWidth() * bestScale) / 2;
            float subY = (height - bitmap.getHeight() * bestScale) / 2;

            Matrix imgMatrix = new Matrix();
            imageView.setScaleType(ScaleType.MATRIX);
            //缩放最佳大小
            imgMatrix.postScale(bestScale, bestScale);
            //移动到居中位置显示
            imgMatrix.postTranslate(subX, subY);
            //设置矩阵
            imageView.setImageMatrix(imgMatrix);

            imageView.setImageBitmap(bitmap);
    }
}