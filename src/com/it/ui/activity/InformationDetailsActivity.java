package com.it.ui.activity;

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
import com.it.ui.base.BaseActivity;
import com.it.utils.BitmapsUtils;
import com.it.utils.ToastUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
/**
 * 文章详细页面
 * @author Administrator
 *
 */
public class InformationDetailsActivity extends BaseActivity{

	
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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_informationdetail);
		ViewUtils.inject(this);
		initData();
		user=((ItApplication)getApplication()).getCurrnUser();
		
	}


	private void initData() {
		// TODO Auto-generated method stub
		info=(ContentInfo) getIntent().getSerializableExtra(Const.ArticleId);
		title.setText(info.getTitle());
		BitmapsUtils.getInstance().display(logo, info.getImage());
		context.setText(info.getContent());
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
				
			}else{
				new ToastUtils(this, "请先登陆！");
			}
			break;
		default:
			break;
		}
	}
	
	
}
