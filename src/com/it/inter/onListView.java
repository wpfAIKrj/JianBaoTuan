package com.it.inter;

import java.util.ArrayList;

import android.R.array;
/**
 * 获取多数据对象
 * @author xy418
 *
 * @param <T>
 */
public interface onListView<T> {
	void onSucess(ArrayList<T> data);
	void onFail(String errorCode, String errorMsg);
}
