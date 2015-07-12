package com.yingluo.Appraiser.ui.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
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
import com.yingluo.Appraiser.model.CommonCallBack;
import com.yingluo.Appraiser.model.getTreasureAllInfoByIdModel;
import com.yingluo.Appraiser.model.getTreasureCommentListByIdModel;
import com.yingluo.Appraiser.model.getUserByIdModel;
import com.yingluo.Appraiser.model.sendTreasureCommentModel;
import com.yingluo.Appraiser.ui.adapter.IndentiyResultAdapter;
import com.yingluo.Appraiser.ui.adapter.commentListAdapter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.ImageViewWithBorder;
import com.yingluo.Appraiser.view.SlideShowImageView;
import com.yingluo.Appraiser.view.TagLinearLayout;
import com.yingluo.Appraiser.view.ViewOtherTreasure;
import com.yingluo.Appraiser.view.home.ViewUserDelaisIdentifyResult;

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
	@ViewInject(R.id.btn_goto)
	private View btn_goto;

	@ViewInject(R.id.ed_context)
	private EditText ed_text;
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
	
	@ViewInject(R.id.tv_other_title)
	private TextView tv_other_title;

	@ViewInject(R.id.show_images)
	private SlideShowImageView showimage;
	@ViewInject(R.id.layout_treasure)
	private LinearLayout treasurelayout;
	
	@ViewInject(R.id.people_recyclerview)
	private ListView peopleview;
	
	@ViewInject(R.id.comment_listview)
	private ListView commentview;
	
	@ViewInject(R.id.title_tag)
	private TextView tag;//鉴定结果旁边的提示
	CollectTreasureByIdModel collectModel;//收藏
	
	getTreasureAllInfoByIdModel infoModel;//宝物详情
	
	getTreasureCommentListByIdModel commentListModel;//宝物评论列表
	
	sendTreasureCommentModel sendCommentModel;//发送评论
	
	IndentiyResultAdapter resultadapter;//评论结果
	
	commentListAdapter commentadapter;//评论列表
	
	private Dialog dialog1;
	
	
	protected Dialog loaddialog;

	private boolean isFirst=true;
	
	
	public long to_user_id=0;
	
	
	@OnClick({R.id.tv_other_title,R.id.btn_send_comment,R.id.detail_back, R.id.btn_goto, R.id.detail_collect,R.id.detail_cancle_collect })
	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.detail_back: {
			setResult(RESULT_CANCELED, getIntent());
			finish();
		}
			break;
		case R.id.tv_other_title://跳转到个人详情
			Intent mIntent = new Intent(this, ActivityHotIdentiy.class);
			mIntent.putExtra(Const.ENTITY, entity);
			startActivity(mIntent);
			break;
		case R.id.btn_send_comment://发布评论
			if (ItApplication.getcurrnUser() != null) {
			String content=ed_text.getText().toString().trim();
			if(content!=null&&!content.isEmpty()){
				loaddialog=DialogUtil.createLoadingDialog(this, "发表评论中....");
				loaddialog.show();
				sendCommentModel.sendTreasureComment(entity.treasure_id, to_user_id, content);
			}else{
				new ToastUtils(this, "请输入评论内容！");
			}}else{
				new ToastUtils(this, "请先登陆！");
			}
			break;
		case R.id.btn_goto: //我要鉴定
		{
			if (ItApplication.getcurrnUser() != null) {
				if(entity.status==2){
					new ToastUtils(this, "宝物已经鉴定完毕！");
					return;
				}
			Intent intent=new Intent(ActivityUserDelails.this,
					ActivityIdentifyByMe.class);
			intent.putExtra(Const.ENTITY, entity);
			startActivityForResult(intent,Const.TO_MY_INDENTITY);
			}else{
				new ToastUtils(this, "请先登陆！");
			}
		}
			break;
		case R.id.detail_collect: {
			// 收藏宝物
			if (ItApplication.getcurrnUser() != null) {
				dialog1 = DialogUtil.createShowDialog(this, "是否收藏该宝物？", lis1);
				dialog1.show();

			} else {
				new ToastUtils(this, "请先登陆！");
			}
		}
			break;
		case R.id.detail_cancle_collect: {
			// 取消收藏
			if (ItApplication.getcurrnUser()!= null) {
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
			loaddialog = DialogUtil.createLoadingDialog(
					ActivityUserDelails.this, "正在收藏该宝物....");
			loaddialog.show();
			collectModel.isCollect(new onBasicView<String>() {
				
				@Override
				public void onSucess(String data) {
					// TODO Auto-generated method stub
					// 收藏成功
					loaddialog.dismiss();
					detail_collect.setVisibility(View.GONE);
					detail_cancle_collect.setVisibility(View.VISIBLE);
				}
				
				@Override
				public void onFail(String errorCode, String errorMsg) {
					// TODO Auto-generated method stub
					// 收藏失败
					loaddialog.dismiss();
					LogUtils.i("ytmfdw==>"+errorMsg);
				}
			}, entity.treasure_id);

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
			loaddialog = DialogUtil.createLoadingDialog(
					ActivityUserDelails.this, "正在收藏该宝物....");
			loaddialog.show();
			collectModel.isDelete(new onBasicView<String>() {
				
				@Override
				public void onSucess(String data) {
					// TODO Auto-generated method stub
					loaddialog.dismiss();
					// 收藏成功
					detail_collect.setVisibility(View.VISIBLE);
					detail_cancle_collect.setVisibility(View.GONE);
				}
				
				@Override
				public void onFail(String errorCode, String errorMsg) {
					// TODO Auto-generated method stub
					loaddialog.dismiss();
					LogUtils.i("ytmfdw==>"+errorMsg);
				}
			}, entity.treasure_id);

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
	private UserInfo user = null;





	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
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
		sendCommentModel=new sendTreasureCommentModel(netListner3);
		initViews();
	}

	

	private void initViews() {
		// TODO Auto-generated method stub
//		bitmapUtils.display(iv_head, entity.authImage);
//		tv_name.setText(entity.authName);
//		tv_msg.setText(entity.name);
		commentadapter=new commentListAdapter(this,listner);
		commentview.setAdapter(commentadapter);
		resultadapter=new IndentiyResultAdapter(this);
		peopleview.setAdapter(resultadapter);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(isFirst){
			loaddialog=DialogUtil.createLoadingDialog(this, "正在获取宝物详情...");
			loaddialog.show();
			infoModel.getInfoTreasure(entity.treasure_id);
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
			
			if(entity.status==1){
				tag.setText("");
			}
			if(entity.status==2){
				tag.setText("(已鉴定)");
				tag.setVisibility(View.VISIBLE);
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
					commentadapter.setData(commentListModel.commentlist);
					commentadapter.setListViewHeightBasedOnChildren(commentview);	
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

		//获取发表评论结果
		private OnStringDataLoadListener netListner3=new OnStringDataLoadListener() {
					
					@Override
					public void onBaseDataLoaded(String data) {
						// TODO Auto-generated method stub
						if(loaddialog!=null&&loaddialog.isShowing()){
							loaddialog.dismiss();
						}
						new ToastUtils(ActivityUserDelails.this, "发表评论成功！");
						to_user_id=0;
						ed_text.setHint("");
						ed_text.setText("");
						//刷新评论
						commentListModel.getInfoTreasure(entity.treasure_id);
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
//		LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
//				(int) getResources().getDimension(R.dimen.y500));
		ArrayList<String> str=new ArrayList<String>();
		if(entity.images1!=null&&entity.images1.length>0){
			for (int i = 0; i < entity.images1.length; i++) {
				str.add(entity.images1[i]);
			}
		}
		if(entity.images2!=null&&entity.images2.length>0){
			for (int i = 0; i < entity.images2.length; i++) {
				str.add(entity.images2[i]);
			}
		}
		if(str.size()>0){
			showimage.setVisibility(View.VISIBLE);
			showimage.prepareData(str);
		}
		
	}

	//添加鉴定结果
	protected void addPeopleidentity() {
		// TODO Auto-generated method stub
		if(infoModel.treasureList!=null&&infoModel.treasureList.size()>0){
				resultadapter.setData(infoModel.treasureList);
				resultadapter.setListViewHeightBasedOnChildren(peopleview);
		}else{//隐藏鉴定结果
			
		}
	}


	//添加其他宝物
	protected void addOtherTreasure() {
		// TODO Auto-generated method stub
		treasurelayout.removeAllViews();
		
		if(infoModel.otherTreasure!=null&&infoModel.otherTreasure.size()>0){
			LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(treasurelayout.getMeasuredWidth()/2, LinearLayout.LayoutParams.WRAP_CONTENT);
			params.rightMargin=5;
			params.leftMargin=5;
			for (int i = 0; i < infoModel.otherTreasure.size(); i++) {
				ViewOtherTreasure view=new ViewOtherTreasure(this);
				view.setLayoutParams(params);
				view.setItem(infoModel.otherTreasure.get(i));
				treasurelayout.addView(view);
			}
		}
	}
	
	/**
	 * 发表评论（评论列表中）
	 */
	private OnClickListener listner=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			CommentEntity comment=(CommentEntity) v.getTag();
			to_user_id=comment.user_id;
			String str=String.format(getResources().getString(R.string.title_send_comment_hint),comment.authName);
			ed_text.setHint(str);
		}
	};
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==Const.TO_MY_INDENTITY&&resultCode==RESULT_OK){	
			new ToastUtils(this, "发表鉴定成功！");
			infoModel.getInfoTreasure(entity.getTreasure_id());
		}
		
	};

	
}
