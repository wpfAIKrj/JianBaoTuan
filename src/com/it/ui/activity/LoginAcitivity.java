package com.it.ui.activity;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.it.R;
import com.it.app.ItApplication;
import com.it.bean.UserInfo;
import com.it.config.Const;
import com.it.im.RongImUtils;
import com.it.presenter.LoginPresenter;
import com.it.ui.base.BaseActivity;
import com.it.utils.SqlDataUtil;
import com.it.utils.DialogUtil;
import com.it.utils.TelNumMath;
import com.it.utils.ToastUtils;
import com.it.view.CircleImageView;
import com.it.view.SelectMoilbWindow;
import com.it.view.inter.onBasicView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnChildClick;
import com.lidroid.xutils.view.annotation.event.OnClick;
/**
 * 登陆
 * @author Administrator
 *
 */
public class LoginAcitivity extends BaseActivity implements onBasicView<UserInfo>,OnItemClickListener{

	
	@ViewInject(R.id.login_user_head)
	private CircleImageView user_logo;
	

	private LoginPresenter mpresenter;
	private Dialog dialog;
	
	@ViewInject(R.id.login_edit_name)
	private EditText ed_name;
	@ViewInject(R.id.login_edit_password)
	private EditText ed_pwd;
	
	private boolean isShow=false;
	
	private SelectMoilbWindow popwindow;
	
	@ViewInject(R.id.name_layout)
	private LinearLayout namelayout;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ViewUtils.inject(this);
		mpresenter=new LoginPresenter(this);
		List<UserInfo> list = SqlDataUtil.getInstance().getUserList();
		if(list==null){
			isShow=true;
		}
		popwindow=new SelectMoilbWindow(this, this);
		
	}


	
	
	
	@OnClick({R.id.title_back, R.id.login_bt_clear,R.id.login_bt_login,R.id.login_bt_register,R.id.login_bt_forgot})
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=null;
		switch (v.getId()) {
		case R.id.title_back://返回主界面
			setResult(RESULT_CANCELED, getIntent());
			finish();
			break;
		case R.id.login_bt_clear://显示下拉用户框
			if(isShow){
				showPopWinddos();
			}else{
				disPopWinddos();
			}
			break;
		case R.id.login_bt_login://登陆
			startLogin();
			break;
		case R.id.login_bt_register://跳转到注册页面
			intent=new Intent(LoginAcitivity.this, RegisterActivity.class);
			startActivityForResult(intent, Const.TO_REGISTER);
			
			break;
		case R.id.login_bt_forgot://找回密码界面
			intent=new Intent(LoginAcitivity.this, ForgotActivity.class);
			startActivityForResult(intent, Const.TO_FOGOT);
			break;
		default:
			break;
		}
	}
	
	
	
	/**
	 * 隐藏下拉框
	 */
	private void disPopWinddos() {
		// TODO Auto-generated method stub
		popwindow.showPopupWindow(namelayout);
	}




	/**
	 * 显示本地数据库中的用户
	 */
	private void showPopWinddos() {
		// TODO Auto-generated method stub
		
	}


	/**
	 * 登陆
	 */
	private void startLogin() {
		// TODO Auto-generated method stub
		final String name=ed_name.getText().toString();
		final String pwd=ed_pwd.getText().toString();
		 if(TelNumMath.isMobileNO(name)){
			 if(!pwd.isEmpty()&&pwd.length()>5){
				 dialog=DialogUtil.createLoadingDialog(this, "登陆中.....");
				 dialog.show();
				 mpresenter.startLogin(name, pwd);
			 }else{
				 ed_pwd.setText("");
				 new ToastUtils(this, "密码不正确，请重新输入");
			 }
		 }else{
			 ed_name.setText("");
			 ed_pwd.setText("");
			 new ToastUtils(this, "手机号码不正确");
		 }
	}


	@Override
	public void onSucess(UserInfo user) {
		// TODO Auto-generated method stub
		((ItApplication)getApplication()).setCurrnUser(user);
		SqlDataUtil.getInstance().saveUserInfo(user);
	//	RongImUtils.getInstance().getToken(user.getMobile(), user.getNickname(),"");
		if(dialog!=null){
			dialog.dismiss();
		}
		setResult(Activity.RESULT_OK, getIntent());
		finish();
	}

	@Override
	public void onFail(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		if(dialog!=null){
			dialog.dismiss();
		}
		new ToastUtils(this, errorCode+","+errorMsg);
	}

	




	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		UserInfo user= popwindow.getUserInfo(position);
		ed_name.setText(user.getMobile());
		popwindow.showPopupWindow(namelayout);
	}
	
	
	@Override
	public void startActivity(Intent intent) {
		// TODO Auto-generated method stub
		super.startActivity(intent);
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==Const.TO_REGISTER){
			if(resultCode==RESULT_OK){//注册成功
				setResult(Activity.RESULT_OK, getIntent());
				finish();
			}
		}
		if(requestCode==Const.TO_FOGOT){
			
		}
		
	}
}
