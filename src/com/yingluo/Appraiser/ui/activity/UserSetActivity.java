package com.yingluo.Appraiser.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.im.RongImUtils;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.presenter.ExitPresenter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.FileUtils;
import com.yingluo.Appraiser.utils.SharedPreferencesUtils;
import com.yingluo.Appraiser.utils.ToastUtils;

public class UserSetActivity extends BaseActivity {
	
	@ViewInject(R.id.home_title)
	private TextView title;
	
	@ViewInject(R.id.btn_back)
	private ImageView back;
	

	@ViewInject(R.id.cache_number)
    private TextView cache_number;
    
	private Dialog Logodialong;

	private ExitPresenter exitpresenter;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_seting);
		ViewUtils.inject(this);
		exitpresenter = new ExitPresenter(listener);
		initView();
	}
	
	private void initView() {
		// TODO 自动生成的方法存根
		title.setText(R.string.seting_title);
		back.setImageResource(R.drawable.back_botton);
		setCacheNumber(FileUtils.getInstance().getCacheFileSize());
	}
	
	@OnClick({R.id.btn_back,R.id.layout_item1,R.id.layout_item2,R.id.layout_item3,R.id.layout_item4,R.id.layout_item5,R.id.layout_item6})
	public void onclick(View v){
		if(v.getId()==R.id.btn_back){
			setResult(RESULT_CANCELED, getIntent());
			finish();
		}
		if (v.getId() == R.id.layout_item1) {// 跳转到个人资料
			startActivity(new Intent(UserSetActivity.this, ProfileActivity.class));
		}
		if (v.getId() == R.id.layout_item2) {// 修改密码
			startActivityForResult(new Intent(UserSetActivity.this,
					PasswordActivity.class), Const.TO_UPDATA_PWD);
		}
		if (v.getId() == R.id.layout_item3) {// 意见反馈
			startActivityForResult(new Intent(UserSetActivity.this,
					FeedbackActivity.class), Const.TO_FEEDBACK);
		}
		if (v.getId() == R.id.layout_item4) {// 检测更新
			startActivity(new Intent(UserSetActivity.this, UpdaateActivity.class));
		}
		if (v.getId() == R.id.layout_item5) {// 清楚缓存
			FileUtils.getInstance().deleteDirectory(1);
		}
		if (v.getId() == R.id.layout_item6) {// 退出登陆
			Logodialong = DialogUtil.createLoadingDialog(this, "正在退出当前用户.....");
			Logodialong.show();
			exitpresenter.sendExit();
		}
	}
	private onBasicView<String> listener = new onBasicView<String>() {

		@Override
		public void onSucess(String data) {
			// TODO 自动生成的方法存根
			if (Logodialong != null) {
				Logodialong.dismiss();
			}
			ItApplication.cleanCurrnUser();
			setResult(Const.TO_EXITS_USER, getIntent());
			finish();

		}

		@Override
		public void onFail(String errorCode, String errorMsg) {
			// TODO 自动生成的方法存根
			if (Logodialong != null) {
				Logodialong.dismiss();
			}
			new ToastUtils(UserSetActivity.this, errorMsg);
		}
	};
	
	public void setCacheNumber(String number){
    	if(cache_number!=null){
    		cache_number.setText(number+" ");
    	}
    }
}
