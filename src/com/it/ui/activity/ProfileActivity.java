package com.it.ui.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.R;
import com.it.app.ItApplication;
import com.it.bean.MyEvent;
import com.it.bean.UserInfo;
import com.it.presenter.UploadLogoPresenter;
import com.it.ui.base.BaseActivity;
import com.it.utils.DialogUtil;
import com.it.utils.ToastUtils;
import com.it.view.EmailAutoCompleteTextView;
import com.it.view.inter.onBasicView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import de.greenrobot.event.EventBus;
/**
 * 个人信息完善
 * @author Administrator
 *
 */
public class ProfileActivity extends BaseActivity implements onBasicView<UserInfo>{

	@ViewInject(R.id.button_category)
	private ImageView ivBack;
	
	@ViewInject(R.id.home_title)
	private TextView title;
	
	@ViewInject(R.id.et_qq)
	private EditText edQQ;
	
	@ViewInject(R.id.et_email)
	private EmailAutoCompleteTextView edEmail;
	
	private UploadLogoPresenter mpresenter;
	private UserInfo user;
	private String qq;
	private String email;
	private String key;

	private Dialog Logodialong;

	private String name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		ViewUtils.inject(this);
		mpresenter=new UploadLogoPresenter(this);
		user=((ItApplication)getApplication()).getCurrnUser();
		initView();
	}

	
	
	private void initView() {
		// TODO Auto-generated method stub
		title.setText(R.string.profile_title);
		String string = user.getPersonal_data();
		key=user.getPortrait();
		name=user.getNickname();
		edQQ.setText(user.getQq());
		edEmail.setText(user.getEmail());
	}



	@OnClick({R.id.button_category,R.id.bt_save})
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_category://返回上层
			setResult(RESULT_CANCELED, getIntent());
			finish();
			break;
		case R.id.bt_save://保存
			saveInfo();
			break;
		default:
			break;
		}
	}



	private void saveInfo() {
		// TODO Auto-generated method stub
		String qq=edQQ.getText().toString();
		if(!qq.isEmpty()&&(qq.length()>=6)){
			String email=edEmail.getText().toString();
			if(!email.isEmpty()){
				Logodialong=DialogUtil.createLoadingDialog(this, "正在更新个人信息中");
				Logodialong.show();
				mpresenter.startExtra(key, name,email, qq);
			}else{
				new ToastUtils(this, "请输入正确的email号码！");
			}
		}else{
			new ToastUtils(this, "请输入正确的qq号码！");
		}
	}


	
	@Override
	public void onSucess(UserInfo user) {
		// TODO Auto-generated method stub
		if(Logodialong!=null){
			Logodialong.dismiss();
		}
		((ItApplication)getApplication()).getCurrnUser().setAvatar(user.getAvatar());
		((ItApplication)getApplication()).getCurrnUser().setQq(user.getQq());
		((ItApplication)getApplication()).getCurrnUser().setEmail(user.getEmail());
		
		EventBus.getDefault().post(new MyEvent(0,user));
	}



	@Override
	public void onFail(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		if(Logodialong!=null){
			Logodialong.dismiss();
		}
		new ToastUtils(this, errorMsg);
	}
	
	
	
}
