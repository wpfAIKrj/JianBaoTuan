package com.it.view.inter;

public interface onBasicView<T> {
	void onSucess(T data);
	void onFail(String errorCode, String errorMsg);
}
