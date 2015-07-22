package com.yingluo.Appraiser.ui.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.MyEvent;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.presenter.UploadLogoPresenter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.EmailAutoCompleteTextView;

import de.greenrobot.event.EventBus;

/**
 * 个人信息完善
 * 
 * @author Administrator
 *
 */
public class ProfileActivity extends BaseActivity implements onBasicView<UserInfo> {

	@ViewInject(R.id.button_category)
	private ImageView ivBack;

	@ViewInject(R.id.home_title)
	private TextView title;

	@ViewInject(R.id.et_qq)
	private EditText edQQ;

	@ViewInject(R.id.et_email)
	private EmailAutoCompleteTextView edEmail;

	@ViewInject(R.id.et_name)
	private EditText edName;

	@ViewInject(R.id.tv_phone)
	private TextView tvPhone;
	
	private UploadLogoPresenter mpresenter;
	private String qq,email,key,phone,name;
	
	private Dialog Logodialong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		ViewUtils.inject(this);
		mpresenter = new UploadLogoPresenter(this);
		initView();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	private void initView() {
		title.setText(R.string.profile_title);
		if (ItApplication.getcurrnUser() != null) {
			String string = ItApplication.getcurrnUser().getPersonal_data();
			key = ItApplication.getcurrnUser().getPortrait();
			name = ItApplication.getcurrnUser().getNickname();
			phone = ItApplication.getcurrnUser().getMobile();
			
			edQQ.setText(ItApplication.getcurrnUser().getQq());
			edEmail.setText(ItApplication.getcurrnUser().getEmail());
			edName.setText(ItApplication.getcurrnUser().getNickname());
			tvPhone.setText(phone);
		}
	}

	@OnClick({ R.id.btn_back, R.id.bt_save })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:// 返回上层
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			break;
		case R.id.bt_save:// 保存
			saveInfo();
			break;
		default:
			break;
		}
	}

	private void saveInfo() {
		name = edName.getText().toString();
		if (!name.isEmpty()) {
			String qq = edQQ.getText().toString();
			if (!qq.isEmpty() && (qq.length() >= 6)) {
				String email = edEmail.getText().toString();
				if (!email.isEmpty()) {
					Logodialong = DialogUtil.createLoadingDialog(this, "正在更新个人信息中");
					Logodialong.show();
					mpresenter.startExtra(key, name, email, qq);
				} else {
					new ToastUtils(this, "请输入正确的email号码！");
				}
			} else {
				new ToastUtils(this, "请输入正确的qq号码！");
			}
		} else {
			new ToastUtils(this, "请输入昵称！");
		}
	}

	@Override
	public void onSucess(UserInfo user) {
		if (Logodialong != null) {
			Logodialong.dismiss();
		}
		ItApplication.getcurrnUser().setAvatar(user.getAvatar());
		ItApplication.getcurrnUser().setQq(user.getQq());
		ItApplication.getcurrnUser().setEmail(user.getEmail());
		ItApplication.getcurrnUser().setNickname(user.getNickname());
		EventBus.getDefault().post(new MyEvent(0, user));

		setResult(RESULT_OK, getIntent());
		finish();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	@Override
	public void onFail(String errorCode, String errorMsg) {
		if (Logodialong != null) {
			Logodialong.dismiss();
		}
		new ToastUtils(this, errorMsg);
	}

}
