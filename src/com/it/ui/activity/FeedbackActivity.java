package com.it.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.R;
import com.it.inter.onBasicView;
import com.it.presenter.sendFeedPresenter;
import com.it.ui.base.BaseActivity;
import com.it.utils.DialogUtil;
import com.it.utils.ToastUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
/**
 * 已经反馈
 * @author Administrator
 *
 */
public class FeedbackActivity extends BaseActivity {

	@ViewInject(R.id.home_title)
	private TextView title;
	
	@ViewInject(R.id.et_context)
	private EditText et_context;
	private String context;
	
	private sendFeedPresenter mypresenter;

	private Dialog loaddialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		ViewUtils.inject(this);
		initView();
	}

	
	
	private void initView() {
		// TODO Auto-generated method stub

		title.setText(R.string.feednack_title);
		mypresenter=new sendFeedPresenter(lis);
	}



	@OnClick({R.id.btn_back,R.id.bt_save})
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_back://返回上层
			setResult(RESULT_CANCELED, getIntent());
			finish();
			break;
		case R.id.bt_save://保存
			context=et_context.getText().toString();
			if(context!=null&&!context.isEmpty()){
				startSendFeed(context);
			}else{
				new ToastUtils(this, R.string.help_msg_09);
			}
			break;

		default:
			break;
		}
	}
	
	private void startSendFeed(String context) {
		// TODO 自动生成的方法存根
		if(loaddialog==null){
			loaddialog=DialogUtil.createLoadingDialog(this, "反馈意见中.....");
		}
		loaddialog.show();
		mypresenter.sendFeed(context);
	}

	private onBasicView<String> lis=new onBasicView<String>() {
		
		@Override
		public void onSucess(String data) {
			// TODO 自动生成的方法存根
			if(loaddialog!=null){
				loaddialog.dismiss();
			}
			new ToastUtils(FeedbackActivity.this, "你的报告意见，我们已经接手了，我们将会处理这些宝贵的建议！");
		}
		
		@Override
		public void onFail(String errorCode, String errorMsg) {
			// TODO 自动生成的方法存根
			if(loaddialog!=null){
				loaddialog.dismiss();
			}
			new ToastUtils(FeedbackActivity.this,errorMsg);
		}
	};
}
