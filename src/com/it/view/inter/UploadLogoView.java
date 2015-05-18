package com.it.view.inter;

import com.it.bean.UserInfo;

/**
 * 上传回调接口
 * @author Administrator
 *
 */
public interface UploadLogoView {
	
	public void  UploadLogoSucess(UserInfo user);
	
	public void  UploadLogoFail(String errorCode, String errorMsg);
}
