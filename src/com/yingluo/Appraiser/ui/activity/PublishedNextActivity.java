package com.yingluo.Appraiser.ui.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yingluo.Appraiser.R;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnCompoundButtonCheckedChange;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.http.AskNetWork;
import com.yingluo.Appraiser.http.ResponseToken;
import com.yingluo.Appraiser.http.AskNetWork.AskNetWorkCallBack;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.inter.onListView;
import com.yingluo.Appraiser.presenter.PublishPresenter;
import com.yingluo.Appraiser.presenter.RamdomAdasiterPresenter;
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
public class PublishedNextActivity extends BaseActivity implements AskNetWorkCallBack{

	@ViewInject(R.id.home_title)
	private TextView title;

	@ViewInject(R.id.ed_info)
	private EditText ed_info;

	private RamdomAdasiterPresenter rampresenter;

	private Dialog dialog;

	@ViewInject(R.id.threed_layout)
	private LinearLayout threadlayout;

	private TreasureType type = null;// 选择的宝物

	private ArrayList<UserInfo> list;

	private int select_user = -1;
	private String context;

	private PublishPresenter mypresenter;
	private ArrayList<String> imageAll = null;// 全景图片路径
	private ArrayList<String> imageTest = null;// 特写图片路径

	@ViewInject(R.id.send_identy)
	private Button sendbt;

	@ViewInject(R.id.tv_number)
	private TextView number;
	
	private AskNetWork askNet;
	
	private List<RelativeLayout> allZhuanJia;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publishednext);
		ViewUtils.inject(this);
		list = new ArrayList<UserInfo>();
		allZhuanJia = new ArrayList<RelativeLayout>();
		
		title.setText(R.string.publish_title);
		type = (TreasureType) getIntent().getSerializableExtra(Const.KIND_ID);
		if (type == null) {
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			return;
		}
		ed_info.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String themsg = ed_info.getText().toString();

                if (themsg.length() <= 200) {
                    number.setText(themsg.length() + "/200");
                } else {
                	ed_info.setText(themsg.subSequence(0, 200));
                	ed_info.setSelection(200);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
		imageAll = getIntent().getStringArrayListExtra(Const.IMAGEPATH_PANORAMIC);
		imageTest = getIntent().getStringArrayListExtra(Const.IMAGEPATH_FEATURE);
		mypresenter = new PublishPresenter(netlis);
		rampresenter = new RamdomAdasiterPresenter(lis);
		if (dialog == null) {
			dialog = DialogUtil.createLoadingDialog(this, "获取鉴定师中。。。");
		}
		dialog.show();
		rampresenter.startGet(type.getId());
		askNet = new AskNetWork(NetConst.TOKEN,this);
		askNet.ask(HttpRequest.HttpMethod.GET,null);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	@OnClick({ R.id.btn_back, R.id.send_identy })
	public void onClick(View v) {
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
						if(context.length()<14) {
							new ToastUtils(PublishedNextActivity.this, R.string.help_msg_19);
						} else if(context.length()>200) {
							new ToastUtils(PublishedNextActivity.this, R.string.help_msg_20);
						} else {
							startPublish(type, context, select_user);
						}
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

	private onListView<UserInfo> lis = new onListView<UserInfo>() {

		@Override
		public void onSucess(ArrayList<UserInfo> data) {
			if (dialog != null) {
				dialog.dismiss();
			}
			list = data;
//			for(UserInfo each:data) {
//				int length = list.size(),i;
//				for(i=0;i< length;i++) {
//					if(each.getMobile().equals(list.get(i).getMobile())) {
//						break;
//					}
//				}
//				if(i==length) {
//					list.add(each);
//				}
//			}
			threadlayout.removeAllViews();
			for (int i = 0; i < list.size(); i++) {
				UserInfo user = list.get(i);
				View layout = LinearLayout.inflate(PublishedNextActivity.this, R.layout.item_identy_people, null);
				RelativeLayout wai = (RelativeLayout) layout.findViewById(R.id.rl_zhuanjia);
				allZhuanJia.add(wai);
				wai.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						int length = allZhuanJia.size()>4 ? 4:allZhuanJia.size();
						for(int i=0;i<length;i++) {
							RelativeLayout each = allZhuanJia.get(i);
							TextView text = (TextView) each.findViewById(R.id.user_name);
							if(each.equals(v)) {
								select_user = i;
								text.setTextColor(getResources().getColor(R.color.new_red1));
							} else {
								text.setTextColor(getResources().getColor(R.color.black));
							}
						}
					}
				});
				
				TextView tv_name = (TextView) layout.findViewById(R.id.user_name);
				tv_name.setText(user.getNickname());
				CircleImageView logo = (CircleImageView) layout.findViewById(R.id.user_logo);
				BitmapsUtils.getInstance().display(logo, user.getAvatar());
				threadlayout.addView(layout);
			}
		}

		@Override
		public void onFail(String errorCode, String errorMsg) {
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
			if (dialog != null) {
				dialog.dismiss();
			}
			new ToastUtils(PublishedNextActivity.this, errorMsg);
		}
	};

	@Override
	public void getNetWorkMsg(String msg, String param) throws JSONException {
		ResponseToken rt = new Gson().fromJson(msg, ResponseToken.class);
		if(rt.getCode()==100000) {
			NetConst.UPTOKEN= rt.getData().getToken();
		}
	}

	@Override
	public void getNetWorkMsgError(String msg, String param) throws JSONException {
		
	}
}