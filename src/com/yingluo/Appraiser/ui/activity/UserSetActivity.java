package com.yingluo.Appraiser.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

	@ViewInject(R.id.pb_cache_number)
	private ProgressBar pbCache;
	
	private Dialog Logodialong;

	private ExitPresenter exitpresenter;

	private Handler mhandler = new Handler();

	private long TIME_LONG = 3000;

	private long currentTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_seting);
		ViewUtils.inject(this);
		exitpresenter = new ExitPresenter(listener);
		initView();
	}

	private void initView() {
		title.setText(R.string.seting_title);
		back.setImageResource(R.drawable.back_botton);
		setCacheNumber(FileUtils.getInstance().getCacheFileSize());
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	@OnClick({ R.id.btn_back, R.id.layout_item1, R.id.layout_item2, R.id.layout_item3, R.id.layout_item4,
			R.id.layout_item5, R.id.layout_item6 })
	public void onclick(View v) {
		if (v.getId() == R.id.btn_back) {
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
		}
		if (v.getId() == R.id.layout_item1) {// 跳转到个人资料
			startActivityForResult(new Intent(UserSetActivity.this, ProfileActivity.class), Const.TO_UPDATA_USER_INFO);
			overridePendingTransition(R.anim.left_in, R.anim.left_out);
		}
		if (v.getId() == R.id.layout_item2) {// 修改密码
			startActivityForResult(new Intent(UserSetActivity.this, PasswordActivity.class), Const.TO_UPDATA_PWD);
			overridePendingTransition(R.anim.left_in, R.anim.left_out);
		}
		if (v.getId() == R.id.layout_item3) {// 意见反馈
			startActivityForResult(new Intent(UserSetActivity.this, FeedbackActivity.class), Const.TO_FEEDBACK);
			overridePendingTransition(R.anim.left_in, R.anim.left_out);
		}
		if (v.getId() == R.id.layout_item4) {// 检测更新
			startActivity(new Intent(UserSetActivity.this, UpdaateActivity.class));
			overridePendingTransition(R.anim.left_in, R.anim.left_out);
		}
		if (v.getId() == R.id.layout_item5) {// 清除缓存
			cache_number.setVisibility(View.GONE);
			pbCache.setVisibility(View.VISIBLE);
			FileUtils.getInstance().deleteDirectory(1);
			mhandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					setCacheNumber(FileUtils.getInstance().getCacheFileSize());
				}
			}, TIME_LONG);
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
			if (Logodialong != null) {
				Logodialong.dismiss();
			}
			ItApplication.cleanCurrnUser();
			setResult(Const.TO_EXITS_USER, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
		}

		@Override
		public void onFail(String errorCode, String errorMsg) {
			if (Logodialong != null) {
				Logodialong.dismiss();
			}
			new ToastUtils(UserSetActivity.this, errorMsg);
		}
	};

	public void setCacheNumber(String number) {
		if (cache_number != null) {
			cache_number.setVisibility(View.VISIBLE);
			pbCache.setVisibility(View.GONE);
			cache_number.setText(number + " ");
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == Const.TO_UPDATA_USER_INFO && resultCode == RESULT_OK) {
			new ToastUtils(this, "个人信息更新成功！");
		}
	}
}
