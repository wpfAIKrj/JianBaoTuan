package com.yingluo.Appraiser.utils;

import android.content.Context;  
import android.content.res.Resources;
import android.view.WindowManager;
  
public class DensityUtil {  
  
    /** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
    /**
     * sp装px
     * @param resources
     * @param sp
     * @return
     */
    public static float sp2px(Context context, float sp){
        final float scale =  context.getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }
    
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
    
    /**
     * 将px值转换为sp值，保证文字大小不变
     * 
     * @param pxValue
     * @param fontScale（DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context,float pxValue) {
    	 final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
     return (int) (pxValue / fontScale + 0.5f);
    }
    
    /**
     * 返回屏幕宽度
     * 
     * @Title: getScreenWidth
     * @param context
     * @return int
     */
    @SuppressWarnings("deprecation")
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }
    
}  