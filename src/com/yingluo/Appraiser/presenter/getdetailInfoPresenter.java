package com.yingluo.Appraiser.presenter;

import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.inter.OnBasicDataLoadListener;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.model.getdetailInfoModel;
/**
 * 获取文章详情
 * @author Administrator
 *
 */
public class getdetailInfoPresenter implements OnBasicDataLoadListener<ContentInfo> {
	
	
	private onBasicView<ContentInfo> mview;
	
	private getdetailInfoModel model;
	public getdetailInfoPresenter(onBasicView<ContentInfo> listener1) {
		// TODO Auto-generated constructor stub
		mview=listener1;
	}
	
	public void getDetailInfo(long info_id){
		model=new getdetailInfoModel();
		model.getDetailInfo(info_id,this);
	}
	


	@Override
	public void onBaseDataLoaded(ContentInfo data) {
		// TODO Auto-generated method stub
		mview.onSucess(data);
	}

	@Override
	public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		mview.onFail(errorCode, errorMsg);
	}

}
