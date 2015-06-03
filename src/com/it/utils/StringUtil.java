package com.it.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.TextView;

public class StringUtil {

	private static final String TAG = "StringUtil";


	public static String ToDBC(String input) {  
        char[] c = input.toCharArray();  
        for (int i = 0; i < c.length; i++) {  
            if (c[i] == 12288) {// 全角空格为12288，半角空格为32  
                c[i] = (char) 32;  
                continue;  
            }  
            if (c[i] > 65280 && c[i] < 65375)// 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248  
                c[i] = (char) (c[i] - 65248);  
        }  
        return new String(c);  
    }  
    
    public static String stringFilter(String str) {  
        str = str.replaceAll("【", "[").replaceAll("】", "]")  
                .replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号  
        String regEx = "[『』]"; // 清除掉特殊字符  
        Pattern p = Pattern.compile(regEx);  
        Matcher m = p.matcher(str);  
        return m.replaceAll("").trim();  
    }
    

    
	public static String MD5EnCode(String input) {
	    Log.v(TAG, "The String before MD5 Encode is:" +input);
		String result = input;
		if (input != null) {
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("MD5");
				md.update(input.getBytes());				
				result = toHexString(md.digest(), "");
//				result = getString(md.digest());
				Log.v(TAG, "The String after MD5 Encode is:" +result);				
				return result.toLowerCase();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} 
		}
		return result.toLowerCase();
	}
	

	

	private static String toHexString(byte[] bytes, String separator) {
	    StringBuilder hexString = new StringBuilder();
	    for (byte b : bytes) {
	        String hex = Integer.toHexString(0xFF & b);
	        if(hex.length()==1){
	            hexString.append('0');
	        }
	        hexString.append(hex).append(separator);
	    }
	    return hexString.toString().toUpperCase();
	}
	
	public static String getString(int id){
		String sd=String.valueOf(id);
		if(sd.isEmpty()){
			return "0";
		}
		return sd;
		
	}
	  /**
     * 返回是否为汉字
     * @param str 
     * @return
     */
    public static boolean getIsChinesecharacter(String str){
    	Pattern pa= Pattern.compile( "^[\u4E00-\u9FA5\uF900-\uFA2D]+$");
    	Matcher matcher = pa.matcher(str);
    	return matcher.find();     //true为全部是汉字，否则是false
    }
    
    /**
     * 验证身份证号是否符合规则
     * @param text 身份证号
     * @return
     */
     public static boolean personIdValidation(String text) {
          String regx = "[0-9]{17}x";
          String reg1 = "[0-9]{15}";
          String regex = "[0-9]{18}";
          return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }
	
}
