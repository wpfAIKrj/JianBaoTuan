package com.it.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.R;
import com.it.ui.base.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
/**
 * 修改密码
 * @author Administrator
 *
 */
public class PasswordActivity extends BaseActivity {

	@ViewInject(R.id.home_title)
	private TextView title;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password);
		initView();
		}

		
		
		private void initView() {

			title.setText(R.string.password_title);
		}



		@OnClick({R.id.btn_back})
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_back://返回上层
				setResult(RESULT_CANCELED, getIntent());
				finish();
				break;

			default:
				break;
			}
		}
		
}
