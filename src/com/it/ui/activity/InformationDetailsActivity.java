package com.it.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.R;
import com.it.app.ItApplication;
import com.it.bean.ContentInfo;
import com.it.bean.UserInfo;
import com.it.config.Const;
import com.it.inter.DialogForResult;
import com.it.inter.onBasicView;
import com.it.presenter.collectInfoPresenter;
import com.it.presenter.getdetailPresenter;
import com.it.ui.base.BaseActivity;
import com.it.utils.BitmapsUtils;
import com.it.utils.DialogUtil;
import com.it.utils.ToastUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
/**
 * 文章详细页面
 * @author Administrator
 *
 */
public class InformationDetailsActivity extends BaseActivity {

	
	@ViewInject(R.id.detail_share)
	private ImageView share;
	
	@ViewInject(R.id.detail_collect)
	private ImageView collect;
	
	
	@ViewInject(R.id.title)
	private TextView title;
	
	
	@ViewInject(R.id.logo)
	private ImageView logo;
	
	
	@ViewInject(R.id.context)
	private TextView context;
	
	private ContentInfo info;
	
	private UserInfo user;

	private Dialog dialog1;

	private collectInfoPresenter collectPresenter;

	protected Dialog loaddialog;

	private getdetailPresenter getdetailPresenter;
	
	private boolean isFirest=true;
	
	private Dialog loaddialog2;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_informationdetail);
		ViewUtils.inject(this);
		user=((ItApplication)getApplication()).getCurrnUser();
		info=(ContentInfo) getIntent().getSerializableExtra(Const.ArticleId);
		collectPresenter=new collectInfoPresenter(listener);
		getdetailPresenter=new getdetailPresenter(listener1);
		initData();
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		if(isFirest){
			isFirest=false;
			getdetailPresenter.getDetailInfo(info.getId());
			loaddialog2=DialogUtil.createLoadingDialog(this, "获取详细文章中....");
			loaddialog2.show();
		}
	}
	


	private void initData() {
		// TODO Auto-generated method stub

		title.setText(info.getTitle());
		BitmapsUtils.getInstance().display(logo, info.getImage());
		context.setText(info.getContent());
		if(info.getIsCollected()!=0){
			collect.setVisibility(View.GONE);
		}
	}


	@OnClick({R.id.detail_back,R.id.detail_share,R.id.detail_collect})
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.detail_back://返回上层
			setResult(RESULT_CANCELED, getIntent());
			finish();
			break;
		case R.id.detail_share://分享
			if(user!=null){
				
			}else{
				new ToastUtils(this, "请先登陆！");
			}
			break;
		case R.id.detail_collect://收藏
			if(user!=null){
				if(dialog1==null){
					dialog1=DialogUtil.createShowDialog(this, "是否收藏该文章？", lis1);
				}
				dialog1.show();
				
			}else{
				new ToastUtils(this, "请先登陆！");
			}
			break;
		default:
			break;
		}
	}

	
	private DialogForResult lis1=new DialogForResult() {
		
		@Override
		public void onSucess() {
			// TODO Auto-generated method stub
			if(dialog1!=null){
				dialog1.dismiss();
			}
			collectPresenter.collectInfo(info.getId());
			loaddialog=DialogUtil.createLoadingDialog(InformationDetailsActivity.this, "正在收藏该文章....");
			loaddialog.show();
		}
		
		@Override
		public void onCancel() {
			// TODO Auto-generated method stub
			if(dialog1!=null){
				dialog1.dismiss();
			}
		}
	};
	
	/**
	 * 收藏文章
	 */
	private onBasicView<String> listener=new onBasicView<String>() {
		
		@Override
		public void onSucess(String data) {
			// TODO Auto-generated method stub
			if(loaddialog!=null){
				loaddialog.dismiss();
			}
			info.setIsCollected(1);
			collect.setVisibility(View.GONE);
			new ToastUtils(InformationDetailsActivity.this, "该文章收藏成功！");
			
		}
		
		@Override
		public void onFail(String errorCode, String errorMsg) {
			// TODO Auto-generated method stub
			if(loaddialog!=null){
				loaddialog.dismiss();
			}
			new ToastUtils(InformationDetailsActivity.this, errorMsg);
		}
	};
	
	/**
	 * 获取详细文章的详细
	 */
	private onBasicView<ContentInfo> listener1=new onBasicView<ContentInfo>() {
		
		@Override
		public void onSucess(ContentInfo data) {
			// TODO Auto-generated method stub
			if(data!=null){
				info=data;
			}
			initData();
			if(loaddialog2!=null){
				loaddialog2.dismiss();
			}
		}
		
		@Override
		public void onFail(String errorCode, String errorMsg) {
			// TODO Auto-generated method stub
			if(loaddialog2!=null){
				loaddialog2.dismiss();
			}
			new ToastUtils(InformationDetailsActivity.this, errorMsg);
		}
	};
	
	
}
