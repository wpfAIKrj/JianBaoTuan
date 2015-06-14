package com.yingluo.Appraiser.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.yingluo.Appraiser.model.getTreasureAllInfoByIdModel;
import com.yingluo.Appraiser.model.getTreasureCommentListByIdModel;
import com.yingluo.Appraiser.model.getUserByIdModel;
import com.yingluo.Appraiser.presenter.OnStringDataLoadListener;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.ImageViewWithBorder;
import com.yingluo.Appraiser.view.TagLinearLayout;
import com.yingluo.Appraiser.view.ViewOtherTreasure;
import com.yingluo.Appraiser.view.home.ViewUserDelaisIdentifyResult;

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
	
	
	@ViewInject(R.id.tag_layout)
	private TagLinearLayout taglayout;
	
	@ViewInject(R.id.layout_images)
	private LinearLayout imageslayout;

	@ViewInject(R.id.layout_treasure)
	private LinearLayout treasurelayout;
	
	@ViewInject(R.id.layout_people)
	private LinearLayout peoplelayout;
	
	@ViewInject(R.id.comment_recyclerview)
	private RecyclerView commentview;
	
	
	CollectTreasureByIdModel collectModel;//收藏
	
	getTreasureAllInfoByIdModel infoModel;//宝物详情
	
	getTreasureCommentListByIdModel commentListModel;//宝物评论列表
	
	
	
	
	private Dialog dialog1;
	
	
	protected Dialog loaddialog;

	private boolean isFirst=true;
	
	
	
	@OnClick({ R.id.detail_back, R.id.btn_goto, R.id.detail_collect,R.id.detail_cancle_collect })
	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.detail_back: {
			setResult(RESULT_CANCELED, getIntent());
			finish();
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
		if (entity == null) {
			setResult(RESULT_CANCELED, getIntent());
			finish();
		}
		bitmapUtils = BitmapsUtils.getInstance();
		collectModel = new CollectTreasureByIdModel();
		infoModel=new getTreasureAllInfoByIdModel(netListner1);
		commentListModel=new getTreasureCommentListByIdModel(netListner2);
		initViews();
	}

	
	//获取用户详细信息
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
				bitmapUtils.display(iv_head, user.getAvatar());
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
//		bitmapUtils.display(iv_head, entity.authImage);
//		tv_name.setText(entity.authName);
//		tv_msg.setText(entity.name);
		LinearLayoutManager layoutManager=new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		commentview.setLayoutManager(layoutManager);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(isFirst){
			loaddialog=DialogUtil.createLoadingDialog(this, "正在获取宝物详情...");
			loaddialog.show();
			infoModel.getInfoTreasure(entity.getTreasure_id());
			commentListModel.getInfoTreasure(entity.treasure_id);
		}
	}
	
	//获取宝贝详情
	private OnStringDataLoadListener netListner1=new OnStringDataLoadListener() {
		
		@Override
		public void onBaseDataLoaded(String data) {
			// TODO Auto-generated method stub
			if(loaddialog!=null&&loaddialog.isShowing()){
				loaddialog.dismiss();
			}
			isFirst=false;
			//加载宝贝详情
			entity=infoModel.curnt;
			bitmapUtils.display(iv_head, entity.authImage);
			tv_name.setText(entity.authName);
			tv_msg.setText(entity.name);
			taglayout.addTag(entity.kind);
			showTreasureImage();
			if(entity.isCollected){
				detail_collect.setVisibility(View.GONE);
				detail_cancle_collect.setVisibility(View.VISIBLE);
			}else{
				detail_collect.setVisibility(View.VISIBLE);
				detail_cancle_collect.setVisibility(View.GONE);
			}
			addOtherTreasure();
			addPeopleidentity();
		}
		
		@Override
		public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg) {
			// TODO Auto-generated method stub
			if(loaddialog!=null&&loaddialog.isShowing()){
				loaddialog.dismiss();
			}
			new ToastUtils(ActivityUserDelails.this, errorMsg);
		}
	};
	
	//获取宝贝评论详情
		private OnStringDataLoadListener netListner2=new OnStringDataLoadListener() {
			
			@Override
			public void onBaseDataLoaded(String data) {
				// TODO Auto-generated method stub
				if(loaddialog!=null&&loaddialog.isShowing()){
					loaddialog.dismiss();
				}
				if(commentListModel.commentlist!=null){
					
				}
			}
			
			@Override
			public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg) {
				// TODO Auto-generated method stub
				if(loaddialog!=null&&loaddialog.isShowing()){
					loaddialog.dismiss();
				}
				new ToastUtils(ActivityUserDelails.this, errorMsg);
			}
		};


	//加载宝物图片
	protected void showTreasureImage() {
		// TODO Auto-generated method stub
		imageslayout.removeAllViews();
		if(entity.images1!=null&&entity.images1.length>0){
			for (int i = 0; i < entity.images1.length; i++) {
				ImageViewWithBorder image=new ImageViewWithBorder(this);
				LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,
						(int) getResources().getDimension(R.dimen.y500));
				image.setLayoutParams(params);
				bitmapUtils.display(image, entity.images1[i]);
				imageslayout.addView(image);
			}
		}
		if(entity.images2!=null&&entity.images2.length>0){
			for (int i = 0; i < entity.images1.length; i++) {
				ImageViewWithBorder image=new ImageViewWithBorder(this);
				LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,
						(int) getResources().getDimension(R.dimen.y500));
				image.setLayoutParams(params);
				bitmapUtils.display(image, entity.images2[i]);
				imageslayout.addView(image);
			}
		}
		
	}

	//添加鉴定结果
	protected void addPeopleidentity() {
		// TODO Auto-generated method stub
		peoplelayout.removeAllViews();
		if(infoModel.treasureList!=null&&infoModel.treasureList.size()>0){
			for (int i = 0; i < infoModel.treasureList.size(); i++) {
				ViewUserDelaisIdentifyResult result=new ViewUserDelaisIdentifyResult(this); 
				LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
				result.setLayoutParams(params);
				result.setItem(infoModel.otherTreasure.get(i));
				peoplelayout.addView(result);
			}
		}else{//隐藏鉴定结果
			
		}
	}


	//添加其他宝物
	protected void addOtherTreasure() {
		// TODO Auto-generated method stub
		treasurelayout.removeAllViews();
		if(infoModel.otherTreasure!=null&&infoModel.otherTreasure.size()>0){
			for (int i = 0; i < infoModel.otherTreasure.size(); i++) {
				ViewOtherTreasure view=new ViewOtherTreasure(this);
				LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(treasurelayout.getMeasuredWidth()/2, LinearLayout.LayoutParams.WRAP_CONTENT);
				view.setLayoutParams(params);
				view.setItem(infoModel.otherTreasure.get(i));
				treasurelayout.addView(view);
			}
		}
	}
}
