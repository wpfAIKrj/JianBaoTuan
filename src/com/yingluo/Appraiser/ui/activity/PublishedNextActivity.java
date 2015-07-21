package com.yingluo.Appraiser.ui.activity;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnCompoundButtonCheckedChange;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.inter.onListView;
import com.yingluo.Appraiser.presenter.PublishPresenter;
import com.yingluo.Appraiser.presenter.RamdomAdasiterPresenter;
import com.yingluo.Appraiser.ui.adapter.identiySelectAdapter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.CircleImageView;

/**
 * 发布藏品的第二步
 * 
 * @author Administrator
 *
 */
public class PublishedNextActivity extends BaseActivity {

	@ViewInject(R.id.home_title)
	private TextView title;

	@ViewInject(R.id.ed_info)
	private EditText ed_info;

	private RamdomAdasiterPresenter rampresenter;

	private Dialog dialog;

	@ViewInject(R.id.threed_layout)
	private LinearLayout threadlayout;

	@ViewInject(R.id.other_layout)
	private View other_layout;
	@ViewInject(R.id.checkBox1)
	private CheckBox otherbox;

	private TreasureType type = null;// 选择的宝物

	private CheckBox[] checkboxs;

	private ArrayList<UserInfo> list;

	private int select_user = -1;
	private String context;

	private PublishPresenter mypresenter;
	private ArrayList<String> imageAll = null;// 全景图片路径
	private ArrayList<String> imageTest = null;// 特写图片路径

	@ViewInject(R.id.send_identy)
	private Button sendbt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publishednext);
		ViewUtils.inject(this);
		title.setText(R.string.publish_title);
		type = (TreasureType) getIntent().getSerializableExtra(Const.KIND_ID);
		if (type == null) {
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			return;
		}
		imageAll = getIntent().getStringArrayListExtra(Const.IMAGEPATH_PANORAMIC);
		imageTest = getIntent().getStringArrayListExtra(Const.IMAGEPATH_FEATURE);
		mypresenter = new PublishPresenter(netlis);
		rampresenter = new RamdomAdasiterPresenter(lis);
		if (dialog == null) {
			dialog = DialogUtil.createLoadingDialog(this, "获取鉴定师中。。。");
		}
		dialog.show();
		rampresenter.startGet(type.getId());
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	@OnClick({ R.id.btn_back, R.id.send_identy })
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_back:// 返回上层
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			break;
		case R.id.send_identy:// 发表宝贝
			if (type != null) {
				if (select_user != (-1)) {
					context = ed_info.getText().toString();
					if (context != null && !context.isEmpty()) {
						startPublish(type, context, select_user);
					} else {
						new ToastUtils(PublishedNextActivity.this, R.string.help_msg_11);
					}
				} else {
					new ToastUtils(PublishedNextActivity.this, R.string.help_msg_10);
				}
			} else {
				new ToastUtils(PublishedNextActivity.this, R.string.help_msg_17);
			}

			break;
		default:
			break;
		}
	}

	private void startPublish(TreasureType type, String context, int select_user) {
		// TODO 自动生成的方法存根
		UserInfo user = null;
		if (select_user == 4) {
			user = null;
		} else {
			user = list.get(select_user);
		}
		dialog = DialogUtil.createLoadingDialog(this, "发布宝物中。。。。");
		dialog.show();
		mypresenter.startSendTreasure(user, type, context, imageAll, imageTest);
	}

	private OnCheckedChangeListener listener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO 自动生成的方法存根
			if (isChecked) {
				for (int i = 0; i < checkboxs.length; i++) {
					CheckBox box = checkboxs[i];
					if (box == buttonView) {
						select_user = i;
					} else {
						box.setChecked(false);
					}
				}
			}
		}
	};

	@OnCompoundButtonCheckedChange({ R.id.checkBox1 })
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO 自动生成的方法存根
		if (isChecked) {
			select_user = 4;
			if (checkboxs != null) {
				for (int i = 0; i < checkboxs.length; i++) {
					CheckBox box = checkboxs[i];
					box.setChecked(false);
				}
			}
		}
	}

	private onListView<UserInfo> lis = new onListView<UserInfo>() {

		@Override
		public void onSucess(ArrayList<UserInfo> data) {
			// TODO 自动生成的方法存根
			if (dialog != null) {
				dialog.dismiss();
			}
			list = data;
			other_layout.setVisibility(View.VISIBLE);
			threadlayout.removeAllViews();
			checkboxs = new CheckBox[data.size()];
			for (int i = 0; i < data.size(); i++) {
				UserInfo user = data.get(i);
				View layout = LinearLayout.inflate(PublishedNextActivity.this, R.layout.item_identy_people, null);
				checkboxs[i] = (CheckBox) layout.findViewById(R.id.user_checkbox);
				checkboxs[i].setOnCheckedChangeListener(listener);
				TextView tv_name = (TextView) layout.findViewById(R.id.user_name);
				tv_name.setText(user.getNickname());
				CircleImageView logo = (CircleImageView) layout.findViewById(R.id.user_logo);
				BitmapsUtils.getInstance().display(logo, user.getAvatar());
				threadlayout.addView(layout);
			}
		}

		@Override
		public void onFail(String errorCode, String errorMsg) {
			// TODO 自动生成的方法存根
			if (dialog != null) {
				dialog.dismiss();
			}
			// if(!errorCode.equals(NetConst.CODE_ERROR11)){
			// new ToastUtils(PublishedNextActivity.this, errorMsg);
			// }
		}
	};

	private onBasicView<String> netlis = new onBasicView<String>() {

		@Override
		public void onSucess(String data) {
			// TODO 自动生成的方法存根
			if (dialog != null) {
				dialog.dismiss();
			}
			new ToastUtils(PublishedNextActivity.this, "发布宝贝鉴定成功！");
			setResult(RESULT_OK, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
		}

		@Override
		public void onFail(String errorCode, String errorMsg) {
			// TODO 自动生成的方法存根
			if (dialog != null) {
				dialog.dismiss();
			}
			new ToastUtils(PublishedNextActivity.this, errorMsg);
		}
	};

}
