package com.it.presenter;

import java.util.ArrayList;

import com.it.bean.ContentInfo;
import com.it.model.ArticleModel;
import com.it.view.inter.onListView;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.content.Context;
/**
 * 加载文章列表
 * @author Administrator
 *
 */
public class ArticlePresenter implements OnListDataLoadListener<ContentInfo> {

	private onListView<ContentInfo> mView;
	private ArticleModel model;
	public ArticlePresenter(onListView<ContentInfo> view) {
		// TODO Auto-generated constructor stub
		this.mView=view;
	}
	
	public void getArticleList(String type,int group_id){
		model=new ArticleModel();
		model.getArticleList(type, group_id, this);
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
