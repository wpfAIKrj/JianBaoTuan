package com.yingluo.Appraiser.inter;

import com.yingluo.Appraiser.bean.ContentInfo;

/**
 * listview删除的回调
 * @author Administrator
 *
 */
public interface deleteItemlistener<T> {

	public void ondeleteItem(T item,int id);
}
