package com.yingluo.Appraiser.ui.activity;

import org.xml.sax.XMLReader;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Html.TagHandler;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.inter.DialogForResult;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.presenter.collectInfoPresenter;
import com.yingluo.Appraiser.presenter.deleteInfoPresenter;
import com.yingluo.Appraiser.presenter.getdetailInfoPresenter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.FileUtils;
import com.yingluo.Appraiser.utils.ToastUtils;

/**
 * 文章详细页面
 * 
 * @author Administrator
 *
 */
public class InformationDetailsActivity extends BaseActivity {

	@ViewInject(R.id.detail_share)
	private ImageView share;

	@ViewInject(R.id.detail_collect)
	private ImageView collect;

	@ViewInject(R.id.detail_cancle_collect)
	private ImageView canclecollect;
	@ViewInject(R.id.title)
	private TextView title;

	@ViewInject(R.id.logo)
	private ImageView logo;

	@ViewInject(R.id.context)
	private WebView context;

	private ContentInfo info;

	private Dialog dialog1;

	private collectInfoPresenter collectPresenter;

	protected Dialog loaddialog;

	private getdetailInfoPresenter getdetailPresenter;

	private boolean isFirest = true;

	private Dialog loaddialog2;

	private Dialog loaddialog3;
	private deleteInfoPresenter deletePresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_informationdetail);
		ViewUtils.inject(this);

		info = (ContentInfo) getIntent().getSerializableExtra(Const.ArticleId);
		collectPresenter = new collectInfoPresenter(listener);
		getdetailPresenter = new getdetailInfoPresenter(listener1);
		deletePresenter = new deleteInfoPresenter(listener2);
		context.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		context.getSettings().setDomStorageEnabled(true);
		context.getSettings().setDatabaseEnabled(true);
		context.getSettings().setDatabasePath(FileUtils.getInstance().getUpImage());
		context.getSettings().setAppCachePath(FileUtils.getInstance().getUpImage());
		context.getSettings().setAppCacheEnabled(true);
		initData();
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (isFirest) {
			isFirest = false;
			getdetailPresenter.getDetailInfo(info.getId());
			loaddialog2 = DialogUtil.createLoadingDialog(this, "获取详细文章中....");
			loaddialog2.show();
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}
	
	private void initData() {

		title.setText(info.getTitle());
		BitmapsUtils.getInstance().display(logo, info.getImage());
		if (info.getContent() != null) {
			context.getSettings().setJavaScriptEnabled(true);
			context.loadData("<html>" + info.getContent() + "</html>", "text/html; charset=UTF-8", null);
		} else {
		}
		if (info.getIsCollected() != null && info.getIsCollected() != 0) {
			collect.setVisibility(View.GONE);
			canclecollect.setVisibility(View.VISIBLE);
		} else {
			collect.setVisibility(View.VISIBLE);
			canclecollect.setVisibility(View.GONE);
		}
	}

	@OnClick({ R.id.detail_back, R.id.detail_share, R.id.detail_collect, R.id.detail_cancle_collect })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.detail_back:// 返回上层
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			break;
		case R.id.detail_share:// 分享
			if (ItApplication.getcurrnUser() != null) {

			} else {
				new ToastUtils(this, "请先登陆！");
			}
			break;
		case R.id.detail_collect:// 收藏
			if (ItApplication.getcurrnUser() != null) {
				dialog1 = DialogUtil.createShowDialog(this, "是否收藏该文章？", lis1);
				dialog1.show();

			} else {
				Intent intent = new Intent(InformationDetailsActivity.this, LoginAcitivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.toast_in, R.anim.hold);
			}
			break;
		case R.id.detail_cancle_collect:// 取消收藏
			if (ItApplication.getcurrnUser() != null) {
				dialog1 = DialogUtil.createShowDialog(this, "是否取消收藏文章？", lis2);
				dialog1.show();

			} else {
				Intent intent = new Intent(InformationDetailsActivity.this, LoginAcitivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.toast_in, R.anim.hold);
			}
			break;
		default:
			break;
		}
	}

	private DialogForResult lis1 = new DialogForResult() {

		@Override
		public void onSucess() {
			if (dialog1 != null) {
				dialog1.dismiss();
			}
			collectPresenter.collectInfo(info.getId());
			loaddialog = DialogUtil.createLoadingDialog(InformationDetailsActivity.this, "正在收藏该文章....");
			loaddialog.show();
		}

		@Override
		public void onCancel() {
			if (dialog1 != null) {
				dialog1.dismiss();
			}
		}
	};

	private DialogForResult lis2 = new DialogForResult() {

		@Override
		public void onSucess() {
			if (dialog1 != null) {
				dialog1.dismiss();
			}
			deletePresenter.deleteInfo(String.valueOf(info.getId()));
			loaddialog3 = DialogUtil.createLoadingDialog(InformationDetailsActivity.this, "正在收藏该文章....");
			loaddialog3.show();
		}

		@Override
		public void onCancel() {
			if (dialog1 != null) {
				dialog1.dismiss();
			}
		}
	};

	/**
	 * 收藏文章
	 */
	private onBasicView<String> listener = new onBasicView<String>() {

		@Override
		public void onSucess(String data) {
			if (loaddialog != null) {
				loaddialog.dismiss();
			}
			info.setIsCollected(1);
			initData();
			new ToastUtils(InformationDetailsActivity.this, "该文章收藏成功！");

		}

		@Override
		public void onFail(String errorCode, String errorMsg) {
			if (loaddialog != null) {
				loaddialog.dismiss();
			}
			new ToastUtils(InformationDetailsActivity.this, errorMsg);
		}
	};

	/**
	 * 取消收藏文章的结果
	 */
	private onBasicView<String> listener2 = new onBasicView<String>() {

		@Override
		public void onSucess(String data) {
			if (loaddialog3 != null) {
				loaddialog3.dismiss();
			}
			info.setIsCollected(0);
			initData();
			new ToastUtils(InformationDetailsActivity.this, "该文章取消收藏成功！");
		}

		@Override
		public void onFail(String errorCode, String errorMsg) {
			if (loaddialog3 != null) {
				loaddialog3.dismiss();
			}
			new ToastUtils(InformationDetailsActivity.this, errorMsg);
		}
	};

	/**
	 * 获取详细文章的详细
	 */
	private onBasicView<ContentInfo> listener1 = new onBasicView<ContentInfo>() {

		@Override
		public void onSucess(ContentInfo data) {
			if (data != null) {
				info = data;
			}
			initData();
			if (loaddialog2 != null) {
				loaddialog2.dismiss();
			}
		}

		@Override
		public void onFail(String errorCode, String errorMsg) {
			if (loaddialog2 != null) {
				loaddialog2.dismiss();
			}
			new ToastUtils(InformationDetailsActivity.this, errorMsg);
		}
	};

}
