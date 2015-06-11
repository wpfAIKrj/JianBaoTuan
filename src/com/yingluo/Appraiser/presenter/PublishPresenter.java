package com.yingluo.Appraiser.presenter;

import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.model.publishModel;
/**
 * 发布藏品
 * @author xy418
 *
 */
public class PublishPresenter implements OnBasicDataLoadListener<String>{

	private onBasicView<String> mView;
	private publishModel model;

	
	public PublishPresenter(onBasicView<String> view) {
		// TODO 自动生成的构造函数存根
		this.mView=view;
	}
	
	
	
	@Override
	public void onBaseDataLoaded(String data) {
		// TODO 自动生成的方法存根
		mView.onSucess(data);
		
	}

	@Override
	public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg) {
		// TODO 自动生成的方法存根
		mView.onFail(errorCode, errorMsg);
	}


	/**
	 * 发布藏品的方法
	 * @param user 用户
	 * @param type 宝贝分类
	 * @param context 内容
	 * @param imageAll 全景图片
	 * @param imageTest 测试图片
	 */
	public void startSendTreasure(UserInfo user, TreasureType type,
			String context, String[] imageAll, String[] imageTest) {
		// TODO 自动生成的方法存根
		model=new publishModel();
		model.startSendTreasure(user,type,context,imageAll,imageTest,this);
	}

}
