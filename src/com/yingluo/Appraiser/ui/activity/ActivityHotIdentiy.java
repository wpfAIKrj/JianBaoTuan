package com.yingluo.Appraiser.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.im.RongImUtils;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.model.CommonCallBack;
import com.yingluo.Appraiser.model.MyTreasureModel;
import com.yingluo.Appraiser.model.MyTreasureOtherModel;
import com.yingluo.Appraiser.model.getTreasureByIdModel;
import com.yingluo.Appraiser.model.getTreasureByOtherIdModel;
import com.yingluo.Appraiser.model.getUserByIdModel;
import com.yingluo.Appraiser.ui.adapter.MyTreasureAdapter;
import com.yingluo.Appraiser.ui.adapter.OtherTreasureAdapter;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.FileUtils;
import com.yingluo.Appraiser.utils.SqlDataUtil;
import com.yingluo.Appraiser.utils.ToastUtils;

/**
 * 个人详情页面
 * 
 * @author xy418
 *
 */
public class ActivityHotIdentiy extends Activity implements OnClickListener {

	BitmapsUtils bitmapUtils;
	@ViewInject(R.id.btn_back)
	private View btn_back;

	@ViewInject(R.id.button_text_title)
	private Button tv_text_title;

	@ViewInject(R.id.context)
	private WebView context;

	@ViewInject(R.id.iv_icon)
	ImageView iv_icon;
	@ViewInject(R.id.tv_name)
	TextView tv_name;
	@ViewInject(R.id.tv_grade_name)
	TextView tv_grade_name;
	@ViewInject(R.id.iv_grade)
	ImageView iv_grade;

	@ViewInject(R.id.btn_identifing)
	ViewGroup btn_identifing;
	@ViewInject(R.id.btn_identified)
	ViewGroup btn_identified;

	CollectionTreasure entity;
	UserInfo userinfo;

	@ViewInject(R.id.btn_msg)
	Button btn_msg;

	@ViewInject(R.id.swipe_refresh_widget)
	SwipeRefreshLayout swipe_refresh_widget;
	@ViewInject(R.id.recyclerview)
	RecyclerView recyclerview;

	OtherTreasureAdapter mAdapter = null;

	getTreasureByOtherIdModel treasureModel = null;

	MyTreasureOtherModel identifyModel = null;

	getUserByIdModel userinfoModel = null;

	private boolean isone = true;
	private boolean istwo = true;

	@OnClick(R.id.btn_msg)
	public void doClick(View view) {
		// startActivity(new Intent(ActivityHotIdentiy.this,
		// IMListActivity.class));
		String userid = String.valueOf(entity.user_id);
		RongImUtils.getInstance().startPrivateChat(this, userid, entity.authName);

	}

	OnClickListener identifyListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// 取消刷新动画
			swipe_refresh_widget.setRefreshing(false);
			// 先清空数据
			mAdapter.getData().clear();
			// 先设置背景
			setIdentifyBackground(v.getId());
			switch (v.getId()) {
			case R.id.btn_identifing: {

				if (userinfo != null) {
					if (userinfo.getIs_system() == 1) {
						swipe_refresh_widget.setVisibility(View.VISIBLE);
						context.setVisibility(View.GONE);
					} else {
						// 设置View
						if (isone) {
							treasureModel.sendHttp(new CommonCallBack() {

								@Override
								public void onSuccess() {
									// TODO Auto-generated method stub

									isone = false;
									swipe_refresh_widget.setRefreshing(false);
									mAdapter.setData(treasureModel.getResult());

								}

								@Override
								public void onError() {
									// TODO Auto-generated method stub
									swipe_refresh_widget.setRefreshing(false);

								}
							}, 0, entity.user_id);
						} else {
							swipe_refresh_widget.setRefreshing(false);
							mAdapter.setData(treasureModel.getResult());
						}
					}
				}
			}

				break;

			case R.id.btn_identified: {
				context.setVisibility(View.GONE);
				swipe_refresh_widget.setVisibility(View.VISIBLE);
				if (istwo) {
					identifyModel.sendHttp(new CommonCallBack() {

						@Override
						public void onSuccess() {
							// TODO Auto-generated method stub
							istwo = false;
							swipe_refresh_widget.setRefreshing(false);
							mAdapter.setData(identifyModel.getResult());

						}

						@Override
						public void onError() {
							// TODO Auto-generated method stub
							swipe_refresh_widget.setRefreshing(false);

						}
					}, 0, entity.user_id);
				} else {
					swipe_refresh_widget.setRefreshing(false);
					mAdapter.setData(identifyModel.getResult());
				}
			}

				break;
			}

		}
	};
	private Dialog loaddialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_first_page_user);
		ViewUtils.inject(this);
		bitmapUtils = BitmapsUtils.getInstance();
		entity = (CollectionTreasure) getIntent().getSerializableExtra(Const.ENTITY);
		btn_back.setOnClickListener(this);
		btn_identifing.setOnClickListener(identifyListener);
		btn_identified.setOnClickListener(identifyListener);
		treasureModel = new getTreasureByOtherIdModel();
		identifyModel = new MyTreasureOtherModel();
		userinfoModel = new getUserByIdModel();
		initViews();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}
	
	private void initViews() {
		// TODO Auto-generated method stub
		if (entity == null) {
			return;
		}
		bitmapUtils.display(iv_icon, entity.authImage);
		tv_name.setText(entity.authName);
		if (!TextUtils.equals(entity.company, "")) {

			tv_grade_name.setText(entity.company);
		} else {
			tv_grade_name.setText("普通用户");
		}
		GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
		recyclerview.setLayoutManager(layoutManager);
		recyclerview.setHasFixedSize(true);
		mAdapter = new OtherTreasureAdapter();
		recyclerview.setAdapter(mAdapter);
		context.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		context.getSettings().setDomStorageEnabled(true);
		context.getSettings().setDatabaseEnabled(true);
		context.getSettings().setDatabasePath(FileUtils.getInstance().getUpImage());
		context.getSettings().setAppCachePath(FileUtils.getInstance().getUpImage());
		context.getSettings().setAppCacheEnabled(true);

		if (loaddialog == null)
			loaddialog = DialogUtil.createLoadingDialog(this, "加载数据中...");
		loaddialog.show();

		userinfoModel.getUserInfoForId(entity.user_id, listener);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_back:
			onBackPressed();
			break;

		default:
			break;
		}
	}

	public void setIdentifyBackground(int id) {
		switch (id) {
		case R.id.btn_identifing: {
			// 先设置背景
			((Button) btn_identifing.getChildAt(0)).setTextColor(getResources().getColor(R.color.dialog_title_color));
			btn_identifing.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.dialog_title_color));

			((Button) btn_identified.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_identified.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
		}

			break;

		case R.id.btn_identified: {
			((Button) btn_identifing.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_identifing.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));

			((Button) btn_identified.getChildAt(0)).setTextColor(getResources().getColor(R.color.dialog_title_color));
			btn_identified.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.dialog_title_color));
		}
			break;
		}
	}

	private onBasicView<UserInfo> listener = new onBasicView<UserInfo>() {

		@Override
		public void onSucess(UserInfo data) {
			// TODO Auto-generated method stub
			if (data != null) {
				userinfo = data;
				SqlDataUtil.getInstance().saveUserInfo(userinfo);
				if (userinfo.getIs_system() == 1) {
					tv_text_title.setText(R.string.other_info_text);
					if (loaddialog != null && loaddialog.isShowing()) {
						loaddialog.dismiss();
					}
					if (userinfo.getDescription() != null) {
						context.getSettings().setJavaScriptEnabled(true);
						context.loadData("<html>" + userinfo.getDescription() + "</html>", "text/html; charset=UTF-8",
								null);
					} else {

					}
				} else {
					tv_text_title.setText(R.string.other_identitycollectinfo_text);
					context.setVisibility(View.GONE);
					swipe_refresh_widget.setVisibility(View.VISIBLE);
					treasureModel.sendHttp(new CommonCallBack() {
						@Override
						public void onSuccess() {
							// TODO Auto-generated method stub
							if (loaddialog != null && loaddialog.isShowing()) {
								loaddialog.dismiss();
							}
							isone = false;
							swipe_refresh_widget.setRefreshing(false);
							mAdapter.setData(treasureModel.getResult());
						}

						@Override
						public void onError() {
							// TODO Auto-generated method stub
							if (loaddialog != null && loaddialog.isShowing()) {
								loaddialog.dismiss();
							}
							swipe_refresh_widget.setRefreshing(false);

						}
					}, 0, userinfo.getId());
				}

			} else {
				if (loaddialog != null && loaddialog.isShowing()) {
					loaddialog.dismiss();
				}
				ActivityHotIdentiy.this.finish();
			}
		}

		@Override
		public void onFail(String errorCode, String errorMsg) {
			// TODO Auto-generated method stub
			if (loaddialog != null && loaddialog.isShowing()) {
				loaddialog.dismiss();
			}
			new ToastUtils(ActivityHotIdentiy.this, errorMsg);
			ActivityHotIdentiy.this.finish();
		}
	};

}
