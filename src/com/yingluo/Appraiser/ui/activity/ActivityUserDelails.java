package com.yingluo.Appraiser.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.bean.CollectionEntity;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.model.CommonCallBack;
import com.yingluo.Appraiser.model.getUserByIdModel;
import com.yingluo.Appraiser.utils.BitmapsUtils;

public class ActivityUserDelails extends Activity {

	private BitmapsUtils bitmapUtils;

	@ViewInject(R.id.detail_back)
	private View detail_back;
	@ViewInject(R.id.btn_goto)
	private View btn_goto;

	@ViewInject(R.id.iv_head)
	private ImageView iv_head;

	@ViewInject(R.id.tv_name)
	private TextView tv_name;

	@ViewInject(R.id.tv_msg)
	private TextView tv_msg;

	@OnClick({ R.id.detail_back, R.id.btn_goto })
	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.detail_back: {
			onBackPressed();
		}
			break;

		case R.id.btn_goto: {
			startActivity(new Intent(ActivityUserDelails.this,
					ActivityIdentifyByMe.class));
		}
			break;
		}
	}

	private CollectionEntity entity = null;
	getUserByIdModel userModel=null;
	private UserInfo user=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_delails);
		ViewUtils.inject(this);
		entity = (CollectionEntity) getIntent().getSerializableExtra(
				Const.ENTITY);
		getUserInfo(entity);
		bitmapUtils = BitmapsUtils.getInstance();
		initViews();
	}

	private void getUserInfo(CollectionEntity entity) {
		// TODO Auto-generated method stub
		userModel=new getUserByIdModel();
		userModel.sendHttp(new CommonCallBack() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				user=userModel.getResult();
				if(user==null){
					return;
				}
				bitmapUtils.display(iv_head, user.getImage_token());
				tv_name.setText(user.getNickname());
				//用户等级
			}
			
			@Override
			public void onError() {
				// TODO Auto-generated method stub
				
			}
		}, entity.author_id);
	}

	private void initViews() {
		// TODO Auto-generated method stub
		if (entity == null) {
			return;
		}
		bitmapUtils.display(iv_head, entity.authImage);
		tv_name.setText(entity.authName);
		tv_msg.setText(entity.msg);

	}
}
