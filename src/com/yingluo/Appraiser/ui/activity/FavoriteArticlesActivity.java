package com.yingluo.Appraiser.ui.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;

import android.R.integer;
import android.app.Dialog;
import android.content.Intent;
import android.net.NetworkInfo.DetailedState;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.daimajia.swipe.util.Attributes;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.R.string;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnCompoundButtonCheckedChange;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.inter.ListviewLoadListener;
import com.yingluo.Appraiser.inter.OnListDataLoadListener;
import com.yingluo.Appraiser.inter.deleteItemlistener;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.inter.onListView;
import com.yingluo.Appraiser.model.getCollectArticleModel;
import com.yingluo.Appraiser.presenter.ArticlePresenter;
import com.yingluo.Appraiser.presenter.deleteInfoPresenter;
import com.yingluo.Appraiser.presenter.myCollectArticlePresenter;
import com.yingluo.Appraiser.ui.adapter.ArticleAdapter;
import com.yingluo.Appraiser.ui.adapter.MyArticleAdapter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.ui.fragment.InformationFragment;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.ListLoadType;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.listview.XListView;
import com.yingluo.Appraiser.view.listview.XListView.IXListViewListener;
/**
 * 文章加载页面
 * @author Administrator
 *
 */
public class FavoriteArticlesActivity extends BaseActivity implements ListviewLoadListener,onListView<ContentInfo>{

	
	

	@ViewInject(R.id.home_title)
	private TextView title;
	
	
	private MyArticleAdapter madapter;
	private ArrayList<ContentInfo> list;
	
	@ViewInject(R.id.swipe_refresh_widget)
	private SwipeRefreshLayout mSwipeRefreshWidget;
	@ViewInject(R.id.recyclerview1)
	private RecyclerView mRecyclerView;
	
	
	private int length=30;
	
	protected boolean isLoadMore=false;
	private boolean isRefreshing=true;
	
	private boolean isFiset=true;
	
	protected int lastVisableView;
	protected int totalItemCount;
	protected int nextShow=1;

	private HashMap<String, Integer> deleteInfos;
	
	private ListLoadType currt=ListLoadType.Nomal;


	private myCollectArticlePresenter myPresenter;


	private Dialog dialogLoad;
	
	
	private deleteInfoPresenter deletePresenter;
	
	@ViewInject(R.id.layout_delet)
	private RelativeLayout layout_delet;
	
	@ViewInject(R.id.all_checkbox)
	private CheckBox allcheckbox;
	
	private boolean isFirest=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favoritearticles);
		ViewUtils.inject(this);
		initView();
		initData();
	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(isFirest){
			onRefresh();
		}
	}
	protected void initView() {
		// TODO Auto-generated method stub
		title.setText(R.string.collect_info_title);
		list=new ArrayList<ContentInfo>();
		myPresenter=new myCollectArticlePresenter(this);
		deletePresenter=new deleteInfoPresenter(netlistener);
		LayoutManager layoutManager=new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(layoutManager);
		mSwipeRefreshWidget.setDistanceToTriggerSync(200);
		mSwipeRefreshWidget.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {//刷新
				// TODO Auto-generated method stub
				if(isRefreshing){
					FavoriteArticlesActivity.this.onRefresh();
				}else{
					Log.d("helper","正在刷新中!");
					mSwipeRefreshWidget.setRefreshing(false);
				}
			}
		});

		mRecyclerView.setHasFixedSize(true);
		
		madapter=new MyArticleAdapter(this,list,listener,FavoriteArticlesActivity.this,deleteItemlistener);
		madapter.setMode(Attributes.Mode.Single);
		madapter.setScorll(true);
		
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setAdapter(madapter);
		dialogLoad=DialogUtil.createLoadingDialog(this, "正在删除");
	}

	protected void initData() {
		// TODO Auto-generated method stub

		deleteInfos=new HashMap<String, Integer>();
	}



	@OnClick({R.id.button_delect,R.id.button_category,R.id.delete_all_bt,R.id.cancle_all_bt})
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_category://跳转到
			setResult(RESULT_CANCELED, getIntent());
			finish();
			break;
		case R.id.button_delect://删除模式
			madapter.setScorll(false);
			madapter.notifyDataSetChanged();
			layout_delet.setVisibility(View.VISIBLE);
			break;
		case R.id.delete_all_bt://删除
			deleteInfos=madapter.getSelectForMap();
			if(deleteInfos.keySet().size()>0){
				dialogLoad.show();
				StringBuffer sb=new StringBuffer();
				Set<String> ids = deleteInfos.keySet();
				for (String string : ids) {
					sb.append(string).append(",");
				}
				sb.deleteCharAt(sb.length()-1);
				deletePresenter.deleteInfo(String.valueOf(sb.toString()));
			}else{
				new ToastUtils(this, "请选择后在点击删除按钮");
			}
			break;
		case R.id.cancle_all_bt://退出选择模式
			layout_delet.setVisibility(View.GONE);
			allcheckbox.setChecked(false);
			madapter.setScorll(true);
			madapter.exitSelectMode();
			
			break;
		default:
			break;
		}
	}

	private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ContentInfo info=(ContentInfo) v.getTag();
			info.setIsCollected(1);
			Intent intent=new Intent(FavoriteArticlesActivity.this, InformationDetailsActivity.class);
			intent.putExtra(Const.ArticleId, info);
			startActivity(intent);
		}
	};
	
	@Override
	public void onSucess(ArrayList<ContentInfo> data) {
		// TODO Auto-generated method stub
		if(currt==ListLoadType.LoadMore){//加载更多
			addList(data);
		}
		if(currt==ListLoadType.Refresh){//刷新
			if(!data.isEmpty()){ 
				list.clear();
				addList(data);
			}
		}
		madapter.setListData(list);
		currt=ListLoadType.Nomal;
		mSwipeRefreshWidget.setRefreshing(false);
		isLoadMore=true;
		isRefreshing=true;
		madapter.notifyDataSetChanged();
		madapter.setFootType(0);

	}

	
	
	@Override
	public void onFail(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		new ToastUtils(this, errorMsg);
		mSwipeRefreshWidget.setRefreshing(false);
		isLoadMore=true;
		isRefreshing=true;
		madapter.setFootType(0);
	}

	/**
	 * 
	 * @param data
	 */
	public void addList(ArrayList<ContentInfo> data) {
		// TODO Auto-generated method stub
		if(list.size()==0){
			list.addAll(data);	
		}else {
			for (int i = 0; i < data.size(); i++) {
				if(!list.contains(data.get(i))){
					list.add(data.get(i));
				}
			}
		}
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		currt=ListLoadType.Refresh;
		isRefreshing=false;
		isLoadMore=false;
		madapter.setFootType(2);
		madapter.notifyItemChanged(list.size());
		int foot=mRecyclerView.getChildCount();
		myPresenter.getArticleList("0", length);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		currt=ListLoadType.LoadMore;
		isLoadMore=false;
		isRefreshing=false;
		mSwipeRefreshWidget.setRefreshing(false);
		madapter.setFootType(1);
		madapter.notifyDataSetChanged();
		myPresenter.getArticleList("0", (length+1));
	}

	private deleteItemlistener deleteItemlistener=new deleteItemlistener() {
		
		@Override
		public void ondeleteItem(ContentInfo item, int id) {
			// TODO Auto-generated method stub
			dialogLoad.show();
			deleteInfos.put(String.valueOf(item.getId()), id);
			deletePresenter.deleteInfo(String.valueOf(item.getId()));
			
		}
	};
	/**
	 * 删除结果
	 */
	private onBasicView<String> netlistener=new onBasicView<String>() {
		
		@Override
		public void onSucess(String data) {
			// TODO Auto-generated method stub

				try {
					StringBuffer sb=new StringBuffer(data);
					sb.deleteCharAt(0);
					sb.deleteCharAt(sb.length()-1);
					String[] strs=sb.toString().split(",");
					for (int i = 0; i < strs.length; i++) {
							sb=new StringBuffer(strs[i]);
							sb.deleteCharAt(0);
							sb.deleteCharAt(sb.length()-1);
							int id=deleteInfos.get(sb.toString());
							
							madapter.deleteItem(id);	
					}		
					deleteInfos.clear();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					madapter.closeAllItems();
				} 
			deleteInfos.clear();
			if(dialogLoad!=null){
				dialogLoad.dismiss();
			}
			if(madapter.getItemCount()==1){
				layout_delet.setVisibility(View.GONE);
				allcheckbox.setChecked(false);
				madapter.setScorll(true);
				madapter.exitSelectMode();
			}
			
		}
		
		@Override
		public void onFail(String errorCode, String errorMsg) {
			// TODO Auto-generated method stub
			
			if(dialogLoad!=null){
				dialogLoad.dismiss();
			}
			new ToastUtils(FavoriteArticlesActivity.this,errorMsg);
		}
	};
	
	@OnCompoundButtonCheckedChange(R.id.all_checkbox)
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if(isChecked){
			madapter.setSelectDelete(true);
		}else{
			madapter.setSelectDelete(false);
		}
	}
	

}
