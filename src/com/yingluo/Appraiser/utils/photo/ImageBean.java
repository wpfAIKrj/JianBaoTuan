package com.yingluo.Appraiser.utils.photo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * GridView的每个item的数据对象
 * 
 * @author len
 *
 */
public class ImageBean implements Serializable {
	private static final long serialVersionUID = -1632952908188161651L;
	/**
	 * 文件夹的第一张图片路径
	 */
	private String topImagePath;
	/**
	 * 文件夹名
	 */
	private String folderName; 
	/**
	 * 文件夹中的图片数
	 */
	private int imageCounts;
	/**
	 * 图片地址列表
	 */
	private ArrayList<String> pathList ;
	
	public String getTopImagePath() {
		return topImagePath;
	}
	public void setTopImagePath(String topImagePath) {
		this.topImagePath = topImagePath;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	public int getImageCounts() {
		return imageCounts;
	}
	public void setImageCounts(int imageCounts) {
		this.imageCounts = imageCounts;
	}
	public ArrayList<String> getPathList() {
		return pathList;
	}
	public void setPathList(ArrayList<String> pathList) {
		this.pathList = pathList;
	}
	
}
