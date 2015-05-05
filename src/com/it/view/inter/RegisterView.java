package com.it.view.inter;

import com.it.bean.UserInfo;

/**
 * 登录回调接口
 * @author Administrator
 *
 */
public interface RegisterView {
	
	public void loginSucess(UserInfo user);
	
	public void loginFail(String errorCode, String errorMsg);
}
