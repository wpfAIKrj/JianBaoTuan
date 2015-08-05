package com.yingluo.Appraiser.view.viewholder;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.CommentEntity;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.http.ResponseNewHome.HomeItem;
import com.yingluo.Appraiser.ui.activity.ActivityHotIdentiy;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.view.CircleImageView;
import com.yingluo.Appraiser.view.NewHomeCommitView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 新版首页每一个item的布局
 * 
 * @author 王亚立
 *
 */
public class NewItemViewHolder extends ViewHolder {

	@ViewInject(R.id.tv_home_arrow)
	private CircleImageView arrow;
	@ViewInject(R.id.tv_home_name)
	private TextView name;
	@ViewInject(R.id.tv_home_time)
	private TextView time;
	@ViewInject(R.id.iv_home_image)
	private ImageView showImage;
	@ViewInject(R.id.tv_home_title)
	private TextView title;
	@ViewInject(R.id.hcv_identify)
	private NewHomeCommitView identify;
	@ViewInject(R.id.hcv_commit)
	private NewHomeCommitView commit;

	public Context mContext;
	
	public NewItemViewHolder(View itemView, final OnClickListener listener) {
		super(itemView);
		mContext = itemView.getContext();
		ViewUtils.inject(this, itemView);
	}

	public void clearAllView() {
		identify.clear();
		commit.clear();
	}
	
	@OnClick({R.id.tv_home_identify,R.id.tv_home_commit,R.id.tv_home_share}) 
	public void clickMyView(View view) {
		switch (view.getId()) {
			case R.id.tv_home_identify:
				//鉴定
				break;
			case R.id.tv_home_commit:
				//评论
				break;
			case R.id.tv_home_share:
				//分享
				break;
		}
	}
	
	public void setItem(int type,HomeItem item) {
		identify.setRecord(item.getRecords());
		commit.setCommit(item.getComments());
	}
	
//	public void setItem(CommentEntity commentEntity) {
//		currnt = commentEntity;
//		BitmapsUtils.getInstance().display(logo, currnt.authImage);
//		logo.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// 跳转到用户详情页
//				Intent mIntent = new Intent(mContext, ActivityHotIdentiy.class);
//				CollectionTreasure entity = new CollectionTreasure();
//				entity.setAuthImage(currnt.authImage);
//				entity.setAuthName(currnt.authName);
//				entity.setCompany("");
//				entity.setUser_id(currnt.user_id);
//				
//				mIntent.putExtra(Const.ENTITY, entity);
//				mContext.startActivity(mIntent);
//				Activity act = (Activity)mContext;
//				act.overridePendingTransition(R.anim.left_in, R.anim.left_out);
//				
//			}
//		});
//		name.setText(currnt.authName);
//		time.setText(currnt.insert_time);
//		number.setText(String.format(numberstr, currnt.index));
//		if (currnt.to_user_id != 0) {// 回复某人
//			StringBuffer str = new StringBuffer(
//					String.format(mContext.getResources().getString(R.string.comment_list_tag), currnt.to_user_name));
//			int lenght = str.length();
//			str.append("  " + currnt.content);
//			int color = mContext.getResources().getColor(R.color.dialog_title_color);
//			SpannableString ss = new SpannableString(str);
//			ss.setSpan(new ForegroundColorSpan(color), 0, lenght, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//			msg.setText(ss);
//		} else {// 单纯评论
//			msg.setText(currnt.content);
//		}
//	}

}
