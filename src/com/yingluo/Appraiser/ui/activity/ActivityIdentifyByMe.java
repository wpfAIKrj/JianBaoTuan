package com.yingluo.Appraiser.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.inter.OnStringDataLoadListener;
import com.yingluo.Appraiser.model.sendTreasureIdentityModel;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.SqlDataUtil;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.TagLinearLayout;

public class ActivityIdentifyByMe extends BaseActivity {

	@ViewInject(R.id.tv_title)
	private TextView title;
	@ViewInject(R.id.btn_delete)
	private ImageView iv_delete;

	@ViewInject(R.id.et_declare)
	private EditText ed_text;

	@ViewInject(R.id.tag_layout)
	private TagLinearLayout taglayout;

	private CollectionTreasure entity;
	private TreasureType type = null;// 宝贝分类
	private Dialog loaddialog;
	private sendTreasureIdentityModel sendModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		entity = (CollectionTreasure) getIntent().getSerializableExtra(Const.ENTITY);
		if (entity == null) {
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
		}
		setContentView(R.layout.activity_indentify_by_me);
		ViewUtils.inject(this);
		sendModel = new sendTreasureIdentityModel(netlistener);
		initView();

	}

	private void initView() {
		// TODO 自动生成的方法存根
		title = (TextView) findViewById(R.id.tv_title);
		title.setText("我来鉴定");
		iv_delete.setVisibility(View.GONE);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}
	
	@OnClick({ R.id.btn_back, R.id.published_bt, R.id.btn_publish })
	public void onclick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.btn_back: // 返回
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			break;
		case R.id.published_bt:// 选择宝贝
			intent = new Intent(ActivityIdentifyByMe.this, KindOfPreciousActivity.class);
			intent.putExtra(Const.SHOW_TYPE, 1);
			startActivityForResult(intent, Const.TO_PUBLISH_SELECT_TYPE);
			break;
		case R.id.btn_publish:// 我要鉴定
			if (type != null) {
				String comment = ed_text.getText().toString();
				if (comment != null && !comment.isEmpty()) {
					sendIdentity(entity.treasure_id, type.id, type.name, comment);
				} else {
					new ToastUtils(this, R.string.help_msg_13);
				}
			} else {
				new ToastUtils(this, R.string.help_msg_12);
			}
			break;
		default:
			break;
		}
	}

	// 发送鉴定
	private void sendIdentity(long treasure_id, long id, String name, String comment) {
		// TODO 自动生成的方法存根
		loaddialog = DialogUtil.createLoadingDialog(this, "发表鉴定评论中....");
		loaddialog.show();
		sendModel.sendTreasureIdentity(treasure_id, id, name, comment);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO 自动生成的方法存根
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == Const.TO_PUBLISH_SELECT_TYPE) {// 选择宝物
			if (resultCode == RESULT_OK) {
				int kind_id = data.getIntExtra(Const.KIND_ID, 0);
				if (kind_id == 0) {
					type = null;
				} else {
					type = SqlDataUtil.getInstance().getTreasureTypeById(kind_id);
					taglayout.addTag(type);
				}
			}

		}
	}

	private OnStringDataLoadListener netlistener = new OnStringDataLoadListener() {

		@Override
		public void onBaseDataLoaded(String data) {
			// TODO 自动生成的方法存根
			if (loaddialog != null && loaddialog.isShowing()) {
				loaddialog.dismiss();
			}
			setResult(RESULT_OK, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
		}

		@Override
		public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg) {
			// TODO 自动生成的方法存根
			if (loaddialog != null && loaddialog.isShowing()) {
				loaddialog.dismiss();
			}
			new ToastUtils(ActivityIdentifyByMe.this, errorMsg);
		}
	};
}
