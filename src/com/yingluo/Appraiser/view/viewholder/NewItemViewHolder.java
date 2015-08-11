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
import com.yingluo.Appraiser.ui.adapter.NewHomeListAdapter;
import com.yingluo.Appraiser.ui.adapter.NewHomeListAdapter.ClickTabListener;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DensityUtil;
import com.yingluo.Appraiser.view.CircleImageView;
import com.yingluo.Appraiser.view.NewHomeCommitView;
import com.yingluo.Appraiser.view.NewHomeIdentifyView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
	@ViewInject(R.id.tv_home_leve)
	private ImageView leve;
	@ViewInject(R.id.tv_home_title)
	private TextView title;
	@ViewInject(R.id.rl_home_identify)
	private RelativeLayout rlIdentify;
	@ViewInject(R.id.hcv_identify)
	private NewHomeCommitView identify;
	@ViewInject(R.id.hcv_commit)
	private NewHomeCommitView commit;
	@ViewInject(R.id.nhv_has_identify)
	private NewHomeIdentifyView hasIdentify;
	
	private Context mContext;
	private ClickTabListener listener;
	private HomeItem item;
	
	private int[] levels = { R.drawable.level01, R.drawable.level02, R.drawable.level03, R.drawable.level04,
			R.drawable.level05, R.drawable.level06 };
	
	public NewItemViewHolder(Context context,View itemView, final ClickTabListener listener) {
		super(itemView);
		mContext = itemView.getContext();
		ViewUtils.inject(this, itemView);
		ViewGroup.LayoutParams rlParams = showImage.getLayoutParams();
        rlParams.width = DensityUtil.getScreenWidth(context);
        rlParams.height = (int) (DensityUtil.getScreenWidth(context)*3/4.0);
        showImage.setLayoutParams(rlParams);
        this.listener = listener;
	}

	public void clearAllView() {
		identify.clear();
		commit.clear();
	}
	
	@OnClick({R.id.tv_home_arrow,R.id.tv_home_identify,R.id.tv_home_commit,R.id.tv_home_share}) 
	public void clickMyView(View view) {
		switch (view.getId()) {
			case R.id.tv_home_arrow:
				//用户头像
				this.listener.click(item, NewHomeListAdapter.TYPE_HEAD);
				break;
			case R.id.tv_home_identify:
				//鉴定
				this.listener.click(item, NewHomeListAdapter.TYPE_IDENTIFY);
				break;
			case R.id.tv_home_commit:
				//评论
				this.listener.click(item, NewHomeListAdapter.TYPE_COMMIT);
				break;
			case R.id.tv_home_share:
				//分享
				this.listener.click(item, NewHomeListAdapter.TYPE_SHARE);
				break;
		}
	}
	
	public void setItem(int type,HomeItem item) {
		
		this.item = item;
		//设置时间和用户名
		name.setText(item.getUser_name());
		time.setText(item.getInsert_time());
		//设置头像
		if(item.getUser_portrait() != null) {
			String urlArrow = BitmapsUtils.makeQiNiuRrl(item.getUser_portrait() , arrow.getWidth(), arrow.getHeight());
			BitmapsUtils.getInstance().display(arrow, urlArrow, BitmapsUtils.TYPE_YES);
		}
		//设置等级
		if(item.getCurrentLevel().equals("0")) {
			leve.setVisibility(View.GONE);
		} else {
			leve.setVisibility(View.VISIBLE);
			leve.setImageResource(levels[Integer.valueOf(item.getCurrentLevel())-1]);
		}
		//设置图片
		String url = BitmapsUtils.makeQiNiuRrl(item.getImages() , showImage.getWidth(), showImage.getHeight());
		BitmapsUtils.getInstance().display(showImage, url, BitmapsUtils.TYPE_YES);
		//设置描述
		title.setText(item.getTreasure_description());
		
		//隐藏为null的
		if(type == NewHomeListAdapter.indentifying) {
			rlIdentify.setVisibility(View.VISIBLE);
			hasIdentify.setVisibility(View.GONE);
			if(item.getRecords().size() == 0) {
				identify.setVisibility(View.GONE);
			} else {
				identify.setVisibility(View.VISIBLE);
				identify.setRecord(item.getRecords());
			}
			if(item.getComments().size() == 0) {
				commit.setVisibility(View.GONE);
			} else {
				commit.setVisibility(View.VISIBLE);
				commit.setCommit(item.getComments());
			}
			
		} else {
			rlIdentify.setVisibility(View.GONE);
			identify.setVisibility(View.GONE);
			hasIdentify.setVisibility(View.VISIBLE);
			if(item.getAppraiser() != null) {
				hasIdentify.setListener(listener);
				hasIdentify.setItem(item.getAppraiser());
			}  
			if(item.getComments().size() == 0) {
				commit.setVisibility(View.GONE);
			} else {
				commit.setVisibility(View.VISIBLE);
				commit.setCommit(item.getComments());
			}
			commit.setCommit(item.getComments());
		}
	}

}
