package com.it.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.it.R;
import com.it.ui.adapter.ArticleAdapter;
import com.it.ui.base.BaseFragment;
import com.it.view.listview.XListView;
import com.it.view.listview.XListView.IXListViewListener;

public class InformationFragment extends BaseFragment implements OnClickListener,IXListViewListener,OnItemClickListener{

	private XListView mlistview;
	private ArticleAdapter madapter;
	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.layout_info, container, false);
	}

	@Override
	protected void initViews(View view) {
		// TODO Auto-generated method stub
		mlistview=(XListView) view.findViewById(R.id.article_listview);
		Button bt=(Button)view.findViewById(R.id.button_category);
		bt.setOnClickListener(this);
		TextView tv=(TextView)view.findViewById(R.id.home_title);
		tv.setText(R.string.information);
		
	}

	@Override
	protected void initDisplay() {
		// TODO Auto-generated method stub
		madapter=new ArticleAdapter(LayoutInflater.from(mActivity));
		mlistview.setAdapter(madapter);
		mlistview.setXListViewListener(this);
		mlistview.setOnItemClickListener(this);
	}

	@Override
	public void lazyLoad() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_category:
			
			break;

		default:
			break;
		}
	}

	
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}

}
