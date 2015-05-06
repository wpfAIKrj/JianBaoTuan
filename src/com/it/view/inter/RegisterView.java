package com.it.view.inter;

import com.it.bean.UserInfo;

/**
 * 登录回调接口
 * @author Administrator
 *
 */
public interface RegisterView {
	
	public void RegisterSucess(UserInfo user);
	
	public void RegisterFail(String errorCode, String errorMsg);
}
