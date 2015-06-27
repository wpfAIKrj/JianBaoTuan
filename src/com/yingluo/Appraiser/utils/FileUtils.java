package com.yingluo.Appraiser.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.List;

import net.bither.util.NativeUtil;

import com.lidroid.xutils.util.LogUtils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.DateFormat;

/**
 * 文件工具类
 * @author XY-GJH
 *
 */
public class FileUtils {
	
	private static FileUtils mInstance=null;
	private Context mContext;
	public static final String DIRNAME="appraiser";
	public static final String UPLOADIMAGE="cachupload";//上传图片
	public static final String CACHEFILES="cachfiles";//文件缓存
	
	public static final String JSON_HOME="home.json";
	
	public static final String JSON_KINDS="kinds.json";
	
	private String root_path;//根目录
	
	
	
	private  FileUtils(Context context){
		mContext=context;
	}
	
	public static FileUtils getInstance(){
		return mInstance;
	}
	
	public static void init(Context context){
		mInstance=new FileUtils(context);
	}
	
	/**
	 * 获取存储路径
	 * @return
	 */
	public  String getExternalStoragePath(){
		String path=null;
		boolean ishava=Environment.getExternalStorageState()
				.equals(android.os.Environment.MEDIA_MOUNTED);
		if(ishava){
			path=Environment.getExternalStorageDirectory().getPath();
		}
		return path;
	}
	
	/**
	 * 初始文件目录
	 */
	public void initFile(){
		root_path=getExternalStoragePath();
		File root=new File(root_path, DIRNAME);
		if(!root.exists()){
			root.mkdir();
		}
		File upload=new File(root, UPLOADIMAGE);
		if(!upload.exists()){
			upload.mkdir();
		}
		File file=new File(root, CACHEFILES);
		if(!file.exists()){
			file.mkdir();
		}
	}
	

	/**
	 * 获取缓存数据的文件夹路径
	 * @return
	 */
	public String getCacheFile(){
		return root_path+File.separator+DIRNAME+File.separator+CACHEFILES;
	}
	
	/**
	 * 获取缓存文件的大小
	 * @return
	 */
	public String getCacheFileSize(){
		String str="0.0k";
		long size=getDirSize(new File(getUpImage()));
		str=formatFileSize(size);
		return str;
	}
	
	/**
	 * 获取缓存图片的文件夹路径
	 * @return
	 */
	public String getUpImage(){
		return root_path+File.separator+DIRNAME+File.separator+UPLOADIMAGE;
	}
	
	
	/**
	 * 转换文件大小
	 * 
	 * @param fileS
	 * @return B/KB/MB/GB
	 */
	public  String formatFileSize(long fileS) {
		if(fileS==0){
			return 0.0+"KB";
		}
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "MB";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}
	
	/**
	 * 获取目录文件大小
	 * 
	 * @param dir
	 * @return
	 */
	public  long getDirSize(File dir) {
		if (dir == null) {
			return 0;
		}
		if (!dir.isDirectory()) {
			return 0;
		}
		long dirSize = 0;
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				dirSize += file.length();
			} else if (file.isDirectory()) {
				dirSize += file.length();
				dirSize += getDirSize(file); // 递归调用继续统计
			}
		}
		return dirSize;
	}
	
	/**
	 * 获取目录文件个数
	 * 
	 * @param emojiFragment
	 * @return
	 */
	public long getFileList(File dir) {
		long count = 0;
		File[] files = dir.listFiles();
		count = files.length;
		for (File file : files) {
			if (file.isDirectory()) {
				count = count + getFileList(file);// 递归
				count--;
			}
		}
		return count;
	}
	/**
	 * 检查文件是否存在
	 * 
	 * @param name
	 * @return
	 */
	public static boolean checkFileExists(String name) {
		boolean status;
		if (!name.equals("")) {
			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(path.toString() + name);
			status = newPath.exists();
		} else {
			status = false;
		}
		return status;
	}

	/**
	 * 检查路径是否存在
	 * 
	 * @param path
	 * @return
	 */
	public static boolean checkFilePathExists(String path) {
		return new File(path).exists();
	}

	/**
	 * 计算SD卡的剩余空间
	 * 
	 * @return 返回-1，说明没有安装sd卡
	 */
	public  long getFreeDiskSpace() {
		String status = Environment.getExternalStorageState();
		long freeSpace = 0;
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			try {
				File path = Environment.getExternalStorageDirectory();
				StatFs stat = new StatFs(path.getPath());
				long blockSize = stat.getBlockSize();
				long availableBlocks = stat.getAvailableBlocks();
				freeSpace = availableBlocks * blockSize / 1024;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return -1;
		}
		return (freeSpace);
	}
	
	/**
	 * 删除目录(包括：目录里的所有文件)
	 * 
	 * @param type 1为上传，2为文件，3为全部 
	 * @return 
	 */
	public  boolean deleteDirectory(int type) {
		boolean status;
		SecurityManager checker = new SecurityManager();
		File newPath =null;
			switch (type) {
			case 1:
				newPath=new File(getUpImage());
				break;
			case 2:
				newPath=new File(getCacheFile());
				break;
			case 3:
				newPath=new File(root_path+File.separator+DIRNAME);
				break;
			default:
				break;
			}
			checker.checkDelete(newPath.toString());
			if (newPath.isDirectory()) {
				String[] listfile = newPath.list();
				try {
					for (int i = 0; i < listfile.length; i++) {
						File deletedFile = new File(newPath.toString() + "/"
								+ listfile[i].toString());
						deletedFile.delete();
					}
					newPath.delete();
					LogUtils.d("DirectoryManager deleteDirectory" +newPath.getAbsolutePath());
					status = true;
				} catch (Exception e) {
					e.printStackTrace();
					status = false;
				}

			} else
				status = false;
		return status;
	}
	
	/**
	 * 删除文件
	 * 
	 * @param fileName 文件路径
	 * @return
	 */
	public  boolean deleteFile(String fileName) {
		boolean status;
		SecurityManager checker = new SecurityManager();

		if (!fileName.equals("")) {
			File newPath = new File(fileName);
			checker.checkDelete(newPath.toString());
			if (newPath.isFile()) {
				if(newPath.getParent().equals(getUpImage())){//自己上传文件可以删除
					try {
						LogUtils.d("DirectoryManager deleteFile"+fileName);
						newPath.delete();
						status = true;
					} catch (SecurityException se) {
						se.printStackTrace();
						status = false;
					}	
				}else{//相册不允许删除
					status=true;
				}
			} else
				status = false;
		} else
			status = false;
		return status;
	}

	/**
	 * 删除空目录
	 * 
	 * 返回 0代表成功 ,1 代表没有删除权限, 2代表不是空目录,3 代表未知错误
	 * 
	 * @return
	 */
	public  int deleteBlankPath(String path) {
		File f = new File(path);
		if (!f.canWrite()) {
			return 1;
		}
		if (f.list() != null && f.list().length > 0) {
			return 2;
		}
		if (f.delete()) {
			return 0;
		}
		return 3;
	}
	
	
	/**
	 * 重命名
	 * @param oldName
	 * @param newName
	 * @return
	 */
	public static boolean reNamePath(String oldName, String newName) {
		File f = new File(oldName);
		return f.renameTo(new File(newName));
	}

	/**
	 * 上传保存图片，保存压缩后的图片,然后删除图片
	 * @param picpath 原图片路径
	 * @return 新图片路径
	 */
	public  String saveUpImageForCamera(String picpath) {
		// TODO Auto-generated method stub
		String path=null;
		try {
			path=NewUploadImagePath();
			int reqWidth=SystemUtils.getDisplaysWidth(mContext)/2;
			int reqHeight=SystemUtils.getDisplaysHeight(mContext)/2;
			Bitmap bitmap = BitmapCompressor.decodeSampledBitmapFromFile(picpath, reqWidth, reqHeight);
			saveImageToSD(mContext, path, bitmap, 50);
			deleteFile(picpath);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return path;
	}
	
	/**
	 * 生存上传缓存文件
	 * @return 返回缓存路径
	 */
	public String saceUpImage(String picpath){
		String path=null;
		try {
			path=NewUploadImageCachePath();
			int reqWidth=SystemUtils.getDisplaysWidth(mContext)/2;
			int reqHeight=SystemUtils.getDisplaysHeight(mContext)/2;
			Bitmap bitmap = BitmapCompressor.decodeSampledBitmapFromFile(picpath, reqWidth, reqHeight);
			saveImageToSD(mContext, path, bitmap, 50);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return path;
	}
	
	/**
     * 写图片文件到SD卡
     * 
     * @throws IOException
     */
    public static void saveImageToSD(Context ctx, String filePath,
            Bitmap bitmap, int quality) throws IOException {
        if (bitmap != null) {
            File file = new File(filePath.substring(0,
                    filePath.lastIndexOf(File.separator)));
            if (!file.exists()) {
               file.createNewFile();
            }
            NativeUtil.compressBitmap(bitmap, 50,filePath, true);
//            BufferedOutputStream bos = new BufferedOutputStream(
//                    new FileOutputStream(filePath));
//            bitmap.compress(CompressFormat.PNG, quality, bos);
//            bos.flush();
//            bos.close();
        }
    }
    
    /**
     * 让Gallery上能马上看到该图片
     */
    private static void scanPhoto(Context ctx, String imgFileName) {
        Intent mediaScanIntent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File file = new File(imgFileName);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        ctx.sendBroadcast(mediaScanIntent);
    }
	/**
	 * 生成新的上传图片
	 * @return  返回路径
	 */
	public  String NewUploadImagePath() {
		// TODO Auto-generated method stub
		String root = FileUtils.getInstance().getUpImage();
		String imagename=new DateFormat().format("yyyyMMDD_hhmmss", Calendar.getInstance())+".jpg";
		root=root+File.separator+imagename;
		return root;
	}
	
	/**
	 * 返回上传图片中使用的缓存文件
	 * @return 文件路径
	 */
	public String NewUploadImageCachePath(){
		String root = FileUtils.getInstance().getUpImage();
		String imagename=new DateFormat().format("imagechache", Calendar.getInstance())+".jpg";
		root=root+File.separator+imagename;
		return root;
	}

	/**
	 * 讲相册中的图片压缩并保存在上传目录下
	 * @param picpath 原图片
	 * @return 上传目录下的图片地址
	 */
	public String saveUpImageForPhone(String picpath) {
		// TODO Auto-generated method stub
		String path=null;
		try {
			path=NewUploadImagePath();
			File newImage=new File(path);
			int reqWidth=SystemUtils.getDisplaysWidth(mContext);
			int reqHeight=SystemUtils.getDisplaysHeight(mContext);
			Bitmap bitmap = BitmapCompressor.decodeSampledBitmapFromFile(picpath, reqWidth, reqHeight);
			saveImageToSD(mContext, path, bitmap, 50);
			deleteFile(picpath);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * 保存首页获取到的json数据
	 */
	public void saveFileForHomeJson(String json){
		File file=new File(getCacheFile(), JSON_HOME);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream out=new FileOutputStream(file);
			BufferedOutputStream writer=new BufferedOutputStream(out);
			writer.write(json.getBytes());
			writer.flush();
			writer.close();			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存宝贝分类的数据
	 */
	public void saveFileForKindJson(String json){
		File file=new File(getCacheFile(), JSON_KINDS);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream out=new FileOutputStream(file);
			BufferedOutputStream writer=new BufferedOutputStream(out);
			writer.write(json.getBytes());
			writer.flush();
			writer.close();			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取首页的json数据
	 * @return 存在则返回string，不存在则是null
	 */
	public String getFileForHomeJson(){
		File file=new File(getCacheFile(), JSON_HOME);
		StringBuffer content=new StringBuffer();
		if(file.exists()){
			 try {
				InputStream instream = new FileInputStream(file); 
				 if (instream != null) 
				 {
				     InputStreamReader inputreader = new InputStreamReader(instream);
				     BufferedReader buffreader = new BufferedReader(inputreader);
				     String line;
				     //分行读取
				     while (( line = buffreader.readLine()) != null) {
				         content.append(line);
				     }                
				     instream.close();
				 }
		         return content.toString(); 
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return null;
			} 

		}else{
			return null;
		}
	}
	/**
	 * 获取上传头像地址
	 * @return
	 */
	public File getLogoPath() {
		// TODO 自动生成的方法存根
		File file=new File(getUpImage(), "logo.jpg");
		return file;
	}
	
	
	/**
	 * 获取制定的string数据
	 * @param filename 文件名
 	 * @return string，不存在则为null
	 */
	public String getJsonStringForJson(String filename){
		File file=new File(getCacheFile(), JSON_HOME);
		if(file.exists()){//存在
			
			  try {
				InputStream in = new FileInputStream(file);
				  String jsonStr = "";
				    // ByteArrayOutputStream相当于内存输出流
				    ByteArrayOutputStream out = new ByteArrayOutputStream();
				    byte[] buffer = new byte[1024];
				    int len = 0;
				    // 将输入流转移到内存输出流中
				    while ((len = in.read(buffer, 0, buffer.length)) != -1)
				    {
				    	out.write(buffer, 0, len);
				    }
				 // 将内存流转换为字符串
				    jsonStr = new String(out.toByteArray());
				    return jsonStr;
			  	} catch (Exception e) {
				
			  		// TODO Auto-generated catch block
			  		e.printStackTrace();
					return null;
			  	} 
          
		}else{
			return null;
		}
		
	}
}
