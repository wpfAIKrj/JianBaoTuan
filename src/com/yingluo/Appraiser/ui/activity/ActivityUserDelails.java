package com.yingluo.Appraiser.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.inter.DialogForResult;
import com.yingluo.Appraiser.model.CollectTreasureByIdModel;
import com.yingluo.Appraiser.model.CommonCallBack;
import com.yingluo.Appraiser.model.getUserByIdModel;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.ToastUtils;

/**
 * 宝贝详情
 * 
 * @author xy418
 *
 */
public class ActivityUserDelails extends Activity {

	private BitmapsUtils bitmapUtils;

	@ViewInject(R.id.detail_back)
	private View detail_back;
	@ViewInject(R.id.btn_goto)
	private View btn_goto;

	@ViewInject(R.id.iv_head)
	private ImageView iv_head;

	@ViewInject(R.id.tv_name)
	private TextView tv_name;

	@ViewInject(R.id.tv_msg)
	private TextView tv_msg;
	@ViewInject(R.id.detail_collect)
	private View detail_collect;
	@ViewInject(R.id.detail_cancle_collect)
	private View detail_cancle_collect;

	CollectTreasureByIdModel collectModel;
	private Dialog dialog1;
	protected Dialog loaddialog;

	@OnClick({ R.id.detail_back, R.id.btn_goto, R.id.detail_collect,R.id.detail_cancle_collect })
	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.detail_back: {
			onBackPressed();
		}
			break;

		case R.id.btn_goto: {
			startActivity(new Intent(ActivityUserDelails.this,
					ActivityIdentifyByMe.class));
		}
			break;
		case R.id.detail_collect: {
			// 收藏宝物
			if (ItApplication.currnUser != null) {
				dialog1 = DialogUtil.createShowDialog(this, "是否收藏该宝物？", lis1);
				dialog1.show();

			} else {
				new ToastUtils(this, "请先登陆！");
			}
		}
			break;
		case R.id.detail_cancle_collect: {
			// 取消收藏
			if (ItApplication.currnUser != null) {
				dialog1 = DialogUtil.createShowDialog(this, "是否取消收藏该宝物？", lis2);
				dialog1.show();

			} else {
				new ToastUtils(this, "请先登陆！");
			}
		}
			break;
		}
	}

	private DialogForResult lis1 = new DialogForResult() {

		@Override
		public void onSucess() {
			// TODO Auto-generated method stub
			if (dialog1 != null) {
				dialog1.dismiss();
			}
			collectModel.sendHttp(new CommonCallBack() {

				@Override
				public void onSuccess() {
					// 收藏成功
					loaddialog.dismiss();
					detail_collect.setVisibility(View.GONE);
					detail_cancle_collect.setVisibility(View.VISIBLE);

				}

				@Override
				public void onError() {
					// 收藏失败
					loaddialog.dismiss();
					LogUtils.i("ytmfdw==>"+collectModel.getResult());

				}
			}, entity.treasure_id, true);
			loaddialog = DialogUtil.createLoadingDialog(
					ActivityUserDelails.this, "正在收藏该宝物....");
			loaddialog.show();
		}

		@Override
		public void onCancel() {
			// TODO Auto-generated method stub
			if (dialog1 != null) {
				dialog1.dismiss();
			}
		}
	};

	private DialogForResult lis2 = new DialogForResult() {

		@Override
		public void onSucess() {
			// TODO Auto-generated method stub
			if (dialog1 != null) {
				dialog1.dismiss();
			}
			collectModel.sendHttp(new CommonCallBack() {

				@Override
				public void onSuccess() {
					loaddialog.dismiss();
					// 收藏成功
					detail_collect.setVisibility(View.VISIBLE);
					detail_cancle_collect.setVisibility(View.GONE);

				}

				@Override
				public void onError() {
					// 收藏失败
					loaddialog.dismiss();
					LogUtils.i("ytmfdw==>"+collectModel.getResult());
				}
			}, entity.treasure_id, false);
			loaddialog = DialogUtil.createLoadingDialog(
					ActivityUserDelails.this, "正在收藏该宝物....");
			loaddialog.show();
		}

		@Override
		public void onCancel() {
			// TODO Auto-generated method stub
			if (dialog1 != null) {
				dialog1.dismiss();
			}
		}
	};

	private CollectionTreasure entity = null;
	getUserByIdModel userModel = null;
	private UserInfo user = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_delails);
		ViewUtils.inject(this);
		entity = (CollectionTreasure) getIntent().getSerializableExtra(
				Const.ENTITY);
		getUserInfo(entity);
		bitmapUtils = BitmapsUtils.getInstance();
		collectModel = new CollectTreasureByIdModel();
		initViews();
	}

	private void getUserInfo(CollectionTreasure entity) {
		// TODO Auto-generated method stub
		userModel = new getUserByIdModel();
		userModel.sendHttp(new CommonCallBack() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				user = userModel.getResult();
				if (user == null) {
					return;
				}
				bitmapUtils.display(iv_head, user.getImage_token());
				tv_name.setText(user.getNickname());
				// 用户等级
			}

			@Override
			public void onError() {
				// TODO Auto-generated method stub

			}
		}, entity.user_id);
	}

	private void initViews() {
		// TODO Auto-generated method stub
		if (entity == null) {
			return;
		}
		bitmapUtils.display(iv_head, entity.authImage);
		tv_name.setText(entity.authName);
		tv_msg.setText(entity.name);

		//判断该宝物是否被收藏
		collectModel.isCollect(new CommonCallBack() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				detail_collect.setVisibility(View.GONE);
				detail_cancle_collect.setVisibility(View.VISIBLE);
			}

			@Override
			public void onError() {
				// TODO Auto-generated method stub
				detail_collect.setVisibility(View.VISIBLE);
				detail_cancle_collect.setVisibility(View.GONE);

			}
		}, entity.treasure_id);

	}
}
