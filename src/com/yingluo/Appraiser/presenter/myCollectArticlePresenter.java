package com.yingluo.Appraiser.presenter;

import java.util.ArrayList;

import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.inter.OnListDataLoadListener;
import com.yingluo.Appraiser.inter.onListView;
import com.yingluo.Appraiser.model.ArticleModel;
import com.yingluo.Appraiser.model.getCollectArticleModel;

import android.content.Context;
/**
 * 加载文章列表
 * @author Administrator
 *
 */
public class myCollectArticlePresenter implements OnListDataLoadListener<ContentInfo> {

	private onListView<ContentInfo> mView;
	private getCollectArticleModel model;
	public myCollectArticlePresenter(onListView<ContentInfo> view) {
		// TODO Auto-generated constructor stub
		this.mView=view;
	}
	
	public void getArticleList(String type,int group_size){
		model=new getCollectArticleModel();
		model.getArticleList(type, group_size, this);
		model.addRequestParams();
		model.setHTTPMODE(HttpMethod.GET);
		model.sendHttp();
	}
	@Override
	public void onListDataLoaded(ArrayList<ContentInfo> data) {
		// TODO Auto-generated method stub
		mView.onSucess(data);
	}

	@Override
	public void onListDataLoadErrorHappened(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		mView.onFail(errorCode, errorMsg);
	}

}
