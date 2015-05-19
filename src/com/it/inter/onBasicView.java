package com.it.inter;

public interface onBasicView<T> {
	void onSucess(T data);
	void onFail(String errorCode, String errorMsg);
}
