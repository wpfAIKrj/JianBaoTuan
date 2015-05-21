package com.it.ui.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.R;
import com.it.bean.ContentInfo;
import com.it.config.Const;
import com.it.presenter.ArticlePresenter;
import com.it.ui.activity.InformationDetailsActivity;
import com.it.ui.adapter.ArticleAdapter;
import com.it.ui.base.BaseFragment;
import com.it.utils.ToastUtils;
import com.it.view.inter.onListView;
import com.it.view.listview.XListView;
import com.it.view.listview.XListView.IXListViewListener;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class InformationFragment extends BaseFragment implements onListView<ContentInfo>,IXListViewListener,OnItemClickListener{

	@ViewInject(R.id.article_listview)
	private XListView mlistview;
	
	private ArticleAdapter madapter;
	
	
	@ViewInject(R.id.button_category)
	private ImageView back;
	@ViewInject(R.id.home_title)
	private TextView title;
	
	private ArrayList<ContentInfo> list;
	
	private ArticlePresenter articlePresenter;
	
	private int length=0;
	
	private boolean isRefresh=false;
	
	private boolean isFiset=true;
 	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.layout_info, container, false);
	}

	@Override
	protected void initViews(View view) {
		// TODO Auto-generated method stub
		ViewUtils.inject(this, view);
		title.setText(R.string.information);
		list=new ArrayList<ContentInfo>();
		articlePresenter=new ArticlePresenter(this);
	}

	@Override
	protected void initDisplay() {
		// TODO Auto-generated method stub
		madapter=new ArticleAdapter(mActivity,list);
		mlistview.setAdapter(madapter);
		mlistview.setXListViewListener(this);
		mlistview.setOnItemClickListener(this);
		mlistview.setPullLoadEnable(true);
		mlistview.setPullRefreshEnable(true);
	}

	@Override
	public void lazyLoad() {
		// TODO Auto-generated method stub
		if(isFiset){
		onRefresh();
		isFiset=false;
		}
	}

	public void onLoad(){
		mlistview.stopRefresh();
		mlistview.stopLoadMore();
		SimpleDateFormat time=new SimpleDateFormat("HH:mm:ss");
		Date data=new Date(System.currentTimeMillis()) ;
		mlistview.setRefreshTime(time.format(data));
	}


	
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		isRefresh=true;
		length=0;
		articlePresenter.getArticleList("0", length);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		if(!isRefresh){
			length++;
			articlePresenter.getArticleList("0", length);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		if(!list.isEmpty()&&(position!=(list.size()+1))){
			position--;
			ContentInfo contentInfo=list.get(position);
			Intent intent=new Intent(mActivity, InformationDetailsActivity.class);
			intent.putExtra(Const.ArticleId, contentInfo);
			startActivity(intent);	
		}
	}

	@Override
	public void onSucess(ArrayList<ContentInfo> data) {
		// TODO Auto-generated method stub
		if(isRefresh){//
			if(!data.isEmpty()){
				madapter.clearData();
				madapter.addList(data);
			}
		}else{
			madapter.addList(data);
		}
		madapter.notifyDataSetChanged();
		onLoad();
	}

	
	
	@Override
	public void onFail(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		new ToastUtils(mActivity, errorMsg);
		onLoad();
	}

	

}
