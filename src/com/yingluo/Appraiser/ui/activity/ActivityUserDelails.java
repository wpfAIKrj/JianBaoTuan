package com.yingluo.Appraiser.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.CommentEntity;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.inter.DialogForResult;
import com.yingluo.Appraiser.inter.OnStringDataLoadListener;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.model.CollectTreasureByIdModel;
import com.yingluo.Appraiser.model.getTreasureAllInfoByIdModel;
import com.yingluo.Appraiser.model.getTreasureCommentListByIdModel;
import com.yingluo.Appraiser.model.sendTreasureCommentModel;
import com.yingluo.Appraiser.ui.adapter.IndentiyResultAdapter;
import com.yingluo.Appraiser.ui.adapter.commentListAdapter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DensityUtil;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.HelpUtils;
import com.yingluo.Appraiser.utils.SharedPreferencesUtils;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.CircleImageView;
import com.yingluo.Appraiser.view.TagLinearLayout;
import com.yingluo.Appraiser.view.ViewOtherTreasure;

/**
 * 宝贝详情
 * 
 * @author xy418
 *
 */
public class ActivityUserDelails extends BaseActivity {

	private BitmapsUtils bitmapUtils;

	@ViewInject(R.id.detail_back)
	private View detail_back;

	@ViewInject(R.id.tv_home_arrow)
	private CircleImageView iv_head;

	@ViewInject(R.id.tv_home_name)
	private TextView tv_name;

	@ViewInject(R.id.tv_home_time)
	private TextView tv_time;
	
	@ViewInject(R.id.tv_msg)
	private TextView tv_msg;

	@ViewInject(R.id.detail_collect)
	private View detail_collect;

	@ViewInject(R.id.detail_cancle_collect)
	private View detail_cancle_collect;

	@ViewInject(R.id.tag_layout)
	private TagLinearLayout taglayout;

	@ViewInject(R.id.ll_show_images)
	private LinearLayout showimage;

	@ViewInject(R.id.tv_offer)
	private TextView tvOffer;
	
	@ViewInject(R.id.people_recyclerview)
	private ListView peopleview;

	@ViewInject(R.id.comment_listview)
	private ListView commentview;

	@ViewInject(R.id.title_tag)
	private TextView tag;// 鉴定结果旁边的提示

	@ViewInject(R.id.bottom)
	private LinearLayout bottom;

	@ViewInject(R.id.prs_main)
	private PullToRefreshScrollView scrollview;
	
	CollectTreasureByIdModel collectModel;// 收藏

	getTreasureAllInfoByIdModel infoModel;// 宝物详情

	getTreasureCommentListByIdModel commentListModel;// 宝物评论列表

	sendTreasureCommentModel sendCommentModel;// 发送评论

	IndentiyResultAdapter resultadapter;// 评论结果

	commentListAdapter commentadapter;// 评论列表

	public CollectionTreasure curnt = null;
	public List<CollectionTreasure> otherTreasure = null;
	public List<CommentEntity> treasureList = null;

	private Dialog dialog1;

	protected Dialog loaddialog;

	private boolean isFirst = true;
	private ArrayList<String> str;

	public long to_user_id = 0;
	private Long uid;
	
	private boolean isRefresh,isLoadMore;

	@OnClick({ R.id.tv_home_arrow, R.id.detail_back, R.id.detail_collect,
			R.id.detail_cancle_collect })
	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.detail_back: {
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
		}
			break;
		case R.id.tv_home_arrow:// 跳转到个人详情
			Intent mIntent = new Intent(this, ActivityHotIdentiy.class);
			mIntent.putExtra(Const.ENTITY, entity);
			startActivity(mIntent);
			break;
//		case R.id.btn_send_comment:// 发布评论
//			if (ItApplication.getcurrnUser() != null) {
//				String con = ed_text.getText().toString();
//				String content = con.trim();
//				if (content != null && content.length() != 0) {
//					loaddialog = DialogUtil.createLoadingDialog(this, "发表评论中....");
//					loaddialog.show();
//					sendCommentModel.sendTreasureComment(entity.treasure_id, to_user_id, content);
//				} else {
//					new ToastUtils(this, "请输入评论内容！");
//				}
//			} else {
//				Intent intent = new Intent(ActivityUserDelails.this, LoginAcitivity.class);
//				startActivity(intent);
//				overridePendingTransition(R.anim.toast_in, R.anim.hold);
//			}
//			break;
//		case R.id.btn_goto: // 我要鉴定
//		{
//			if (ItApplication.getcurrnUser() != null) {
//				if (entity.status == 2) {
//					new ToastUtils(this, "宝物已经鉴定完毕！");
//					return;
//				}
//				Intent intent = new Intent(ActivityUserDelails.this, ActivityIdentifyByMe.class);
//				intent.putExtra(Const.ENTITY, entity);
//				startActivityForResult(intent, Const.TO_MY_INDENTITY);
//				overridePendingTransition(R.anim.toast_in, R.anim.hold);
//			} else {
//				Intent intent = new Intent(ActivityUserDelails.this, LoginAcitivity.class);
//				startActivity(intent);
//				overridePendingTransition(R.anim.toast_in, R.anim.hold);
//			}
//		}
//			break;
		case R.id.detail_collect: {
			// 收藏宝物
			if (ItApplication.getcurrnUser() != null) {
				dialog1 = DialogUtil.createShowDialog(this, "是否收藏该宝物？", lis1);
				dialog1.show();
			} else {
				// new ToastUtils(this, "请先登陆！");
				Intent intent = new Intent(ActivityUserDelails.this, LoginAcitivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.toast_in, R.anim.hold);
			}
		}
			break;
		case R.id.detail_cancle_collect: {
			// 取消收藏
			if (ItApplication.getcurrnUser() != null) {
				dialog1 = DialogUtil.createShowDialog(this, "是否取消收藏该宝物？", lis2);
				dialog1.show();

			} else {
				// new ToastUtils(this, "请先登陆！");
				Intent intent = new Intent(ActivityUserDelails.this, LoginAcitivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.toast_in, R.anim.hold);
			}
		}
			break;
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	private DialogForResult lis1 = new DialogForResult() {

		@Override
		public void onSucess() {
			if (dialog1 != null) {
				dialog1.dismiss();
			}
			loaddialog = DialogUtil.createLoadingDialog(ActivityUserDelails.this, "正在收藏该宝物....");
			loaddialog.show();
			Long uid = SharedPreferencesUtils.getInstance().getLoginUserID();
			collectModel.isCollect(new onBasicView<String>() {

				@Override
				public void onSucess(String data) {
					// 收藏成功
					loaddialog.dismiss();
					detail_collect.setVisibility(View.GONE);
					detail_cancle_collect.setVisibility(View.VISIBLE);
					new ToastUtils(ActivityUserDelails.this, "收藏成功");
				}

				@Override
				public void onFail(String errorCode, String errorMsg) {
					// 收藏失败
					loaddialog.dismiss();
					new ToastUtils(ActivityUserDelails.this, errorMsg);
				}
			}, uid,entity.treasure_id);

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
			loaddialog = DialogUtil.createLoadingDialog(ActivityUserDelails.this, "正在收藏该宝物....");
			loaddialog.show();
			Long uid = SharedPreferencesUtils.getInstance().getLoginUserID();
			collectModel.isDelete(new onBasicView<String>() {

				@Override
				public void onSucess(String data) {
					loaddialog.dismiss();
					// 取消收藏成功
					detail_collect.setVisibility(View.VISIBLE);
					detail_cancle_collect.setVisibility(View.GONE);
					new ToastUtils(ActivityUserDelails.this, "取消收藏成功");
				}

				@Override
				public void onFail(String errorCode, String errorMsg) {
					loaddialog.dismiss();
					//取消收藏失败
					new ToastUtils(ActivityUserDelails.this, errorMsg);
				}
			}, uid,entity.treasure_id);

		}

		@Override
		public void onCancel() {
			if (dialog1 != null) {
				dialog1.dismiss();
			}
		}
	};

	private CollectionTreasure entity = null;
	private UserInfo user = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_delails);
		ViewUtils.inject(this);
		isRefresh = isLoadMore = false;
		uid = SharedPreferencesUtils.getInstance().getLoginUserID();
		entity = (CollectionTreasure) getIntent().getSerializableExtra(Const.ENTITY);
		if (entity == null) {
			setResult(RESULT_CANCELED, getIntent());
			finish();
		}
		bitmapUtils = BitmapsUtils.getInstance();
		collectModel = new CollectTreasureByIdModel();
		infoModel = new getTreasureAllInfoByIdModel(netListner1);
		commentListModel = new getTreasureCommentListByIdModel(netListner2);
		sendCommentModel = new sendTreasureCommentModel(netListner3);
		initViews();
		scrollview.setOnRefreshListener(new OnRefreshListener2<ScrollView>(){

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
				isRefresh = true;
				infoModel.getInfoTreasure(uid,entity.treasure_id);
				commentListModel.getInfoTreasure(entity.treasure_id);
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {	
				isLoadMore = true;
				
			}

		});

	}

	private void initViews() {
		commentadapter = new commentListAdapter(this, listner);
		commentview.setAdapter(commentadapter);
		resultadapter = new IndentiyResultAdapter(this);
		peopleview.setAdapter(resultadapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (isFirst) {
			loaddialog = DialogUtil.createLoadingDialog(this, "正在获取宝物详情...");
			loaddialog.show();
			infoModel.getInfoTreasure(uid,entity.treasure_id);
			commentListModel.getInfoTreasure(entity.treasure_id);
		}
	}

	// 获取宝贝详情
	private OnStringDataLoadListener netListner1 = new OnStringDataLoadListener() {

		@Override
		public void onBaseDataLoaded(String data) {
			if (loaddialog != null && loaddialog.isShowing()) {
				loaddialog.dismiss();
			}
			isFirst = false;
			if(isRefresh) {
				isRefresh = true;
				showimage.removeAllViews();
				scrollview.onRefreshComplete();
			}
			// 加载宝贝详情
			entity = infoModel.curnt;
			bitmapUtils.display(iv_head, entity.authImage);
			tv_name.setText(entity.authName);
			tv_time.setText(entity.time+"");
			tv_msg.setText(entity.name);
			if(entity.kind != null) {
				taglayout.addTag(entity.kind);
			}
			showTreasureImage();
			if (entity.isCollected) {
				detail_collect.setVisibility(View.GONE);
				detail_cancle_collect.setVisibility(View.VISIBLE);
			} else {
				detail_collect.setVisibility(View.VISIBLE);
				detail_cancle_collect.setVisibility(View.GONE);
			}

			if (entity.status == 1) {
				tag.setText("");
			}
			if (entity.status == 2) {
				tag.setText("(已鉴定)");
				tag.setVisibility(View.VISIBLE);
//				tvOffer.setVisibility(View.VISIBLE);
//				tvOffer.setText(text);
//				btn_goto.setVisibility(View.GONE);
			} else {
//				btn_goto.setVisibility(View.VISIBLE);
			}
			addPeopleidentity();
		}

		@Override
		public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg) {
			if (loaddialog != null && loaddialog.isShowing()) {
				loaddialog.dismiss();
			}
			new ToastUtils(ActivityUserDelails.this, errorMsg);
		}
	};

	// 获取宝贝评论详情
	private OnStringDataLoadListener netListner2 = new OnStringDataLoadListener() {

		@Override
		public void onBaseDataLoaded(String data) {
			// TODO Auto-generated method stub
			if (loaddialog != null && loaddialog.isShowing()) {
				loaddialog.dismiss();
			}
			if (commentListModel.commentlist != null) {
				commentadapter.setData(commentListModel.commentlist);
				commentadapter.setListViewHeightBasedOnChildren(commentview);
			}
		}

		@Override
		public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg) {
			// TODO Auto-generated method stub
			if (loaddialog != null && loaddialog.isShowing()) {
				loaddialog.dismiss();
			}
			new ToastUtils(ActivityUserDelails.this, errorMsg);
		}
	};

	// 获取发表评论结果
	private OnStringDataLoadListener netListner3 = new OnStringDataLoadListener() {

		@Override
		public void onBaseDataLoaded(String data) {
			// TODO Auto-generated method stub
			if (loaddialog != null && loaddialog.isShowing()) {
				loaddialog.dismiss();
			}
			new ToastUtils(ActivityUserDelails.this, "发表评论成功！");
			to_user_id = 0;
//			ed_text.setHint("");
//			ed_text.setText("");
			// 刷新评论
			commentListModel.getInfoTreasure(entity.treasure_id);
		}

		@Override
		public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg) {
			// TODO Auto-generated method stub
			new ToastUtils(ActivityUserDelails.this, "发表评论失败！");
			if (loaddialog != null && loaddialog.isShowing()) {
				loaddialog.dismiss();
			}
			new ToastUtils(ActivityUserDelails.this, errorMsg);
		}
	};

	// 加载宝物图片
	protected void showTreasureImage() {
		str = new ArrayList<String>();
		if (entity.images1 != null && entity.images1.length > 0) {
			for (int i = 0; i < entity.images1.length; i++) {
				str.add(entity.images1[i]);
			}
		}
		if (entity.images2 != null && entity.images2.length > 0) {
			for (int i = 0; i < entity.images2.length; i++) {
				str.add(entity.images2[i]);
			}
		}
		if (str.size() > 0) {
			int length = str.size();
			for (int i = 0; i < length; i++) {
				ImageView image = new ImageView(this);
				int height = (int) ((DensityUtil.getScreenWidth(this)*3.0)/4);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,height);
				if(i!=0) {
					params.setMargins(0, (int) getResources().getDimension(R.dimen.y10), 0, 0);
				}
				image.setScaleType(ScaleType.CENTER_CROP);
				image.setLayoutParams(params);
				showimage.addView(image);
				String url = BitmapsUtils.makeQiNiuRrl(str.get(i), DensityUtil.getScreenWidth(this), height);
				BitmapsUtils.getInstance().display(image, url);
			}
		}

	}

	// 添加鉴定结果
	protected void addPeopleidentity() {
		if (infoModel.treasureList != null && infoModel.treasureList.size() > 0) {
			resultadapter.setData(infoModel.treasureList);
			resultadapter.setListViewHeightBasedOnChildren(peopleview);
		} else {// 隐藏鉴定结果

		}
	}

	/**
	 * 发表评论（评论列表中）
	 */
	private OnClickListener listner = new OnClickListener() {

		@Override
		public void onClick(View v) {
			CommentEntity comment = (CommentEntity) v.getTag();
			to_user_id = comment.user_id;
			String str = String.format(getResources().getString(R.string.title_send_comment_hint), comment.authName);
//			ed_text.setHint(str);
//			ed_text.requestFocus();
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//			imm.showSoftInput(ed_text, InputMethodManager.SHOW_FORCED);
		}
	};

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == Const.TO_MY_INDENTITY && resultCode == RESULT_OK) {
			new ToastUtils(this, "发表鉴定成功！");
			infoModel.getInfoTreasure(uid,entity.getTreasure_id());
		}

	};

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {

			// 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
			View v = getCurrentFocus();

			if (HelpUtils.isShouldHideInput(v, ev, bottom)) {
//				ed_text.clearFocus();
				InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//				im.hideSoftInputFromWindow(ed_text.getWindowToken(), 0);
				to_user_id = 0;
//				ed_text.setHint("");
//				ed_text.setText("");
			}

//			if (v == bt_send_comment) {
//				return true;
//			}
		}
		return super.dispatchTouchEvent(ev);
	}

}
