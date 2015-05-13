package com.it.view.inter;

import com.it.bean.UserInfo;
/**
 * 更新个人资料和人物头像
 * @author Administrator
 *
 */
public interface ProfileView {
	
	public void UpdataSucess(UserInfo user);
	
	public void UpdataFail(String errorCode, String errorMsg);

}
