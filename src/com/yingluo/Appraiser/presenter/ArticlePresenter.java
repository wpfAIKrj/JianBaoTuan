package com.yingluo.Appraiser.presenter;

import java.util.ArrayList;

import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.inter.OnListDataLoadListener;
import com.yingluo.Appraiser.inter.onListView;
import com.yingluo.Appraiser.model.ArticleModel;

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
	/**
	 * 类型
	 * @param type 知识类型
	 * @param group_id 宝贝分类id
	 */
	public void getArticleList(String type,String group_id,int page){
		model=new ArticleModel();
		model.getArticleList(type, group_id, page, this);
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
