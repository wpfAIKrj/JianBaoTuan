package com.it.ui.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.R;
import com.it.app.ItApplication;
import com.it.bean.UserInfo;
import com.it.presenter.ProfilePresenter;
import com.it.ui.base.BaseActivity;
import com.it.utils.ToastUtils;
import com.it.view.EmailAutoCompleteTextView;
import com.it.view.inter.ProfileView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class ProfileActivity extends BaseActivity implements ProfileView{

	@ViewInject(R.id.button_category)
	private ImageView ivBack;
	
	@ViewInject(R.id.home_title)
	private TextView title;
	
	@ViewInject(R.id.et_qq)
	private EditText edQQ;
	
	@ViewInject(R.id.et_email)
	private EmailAutoCompleteTextView edEmail;
	
	private ProfilePresenter mpresenter;
	private UserInfo user;
	private String qq;
	private String email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		ViewUtils.inject(this);
		mpresenter=new ProfilePresenter(this);
		user=((ItApplication)getApplication()).getCurrnUser();
		initView();
	}

	
	
	private void initView() {
		// TODO Auto-generated method stub
		ivBack.setBackgroundResource(R.drawable.back_botton);
		title.setText(R.string.profile_title);
		String string = user.getPersonal_data();
		try {
			JSONObject json=new JSONObject(string);
			edQQ.setText(json.getString("qq"));
			edEmail.setText(json.getString("email"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			String emaile=edEmail.getText().toString();
			
		}else{
			new ToastUtils(this, "请输入正确的qq号码！");
		}
	}



	@Override
	public void UpdataSucess(UserInfo user) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void UpdataFail(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
