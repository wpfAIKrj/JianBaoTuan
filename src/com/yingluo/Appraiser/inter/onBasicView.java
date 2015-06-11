package com.yingluo.Appraiser.inter;

public interface onBasicView<T> {
	void onSucess(T data);
	void onFail(String errorCode, String errorMsg);
}
