package com.yingluo.Appraiser.view.viewholder;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.http.ResponseNewHome.HomeItem;
import com.yingluo.Appraiser.ui.adapter.NewHomeListAdapter;
import com.yingluo.Appraiser.ui.adapter.NewHomeListAdapter.ClickTabListener;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DensityUtil;
import com.yingluo.Appraiser.view.CircleImageView;
import com.yingluo.Appraiser.view.NewHomeCommitView;
import com.yingluo.Appraiser.view.NewHomeIdentifyView;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class OtherViewHolder extends ViewHolder{

	@ViewInject(R.id.tv_home_arrow)
	private CircleImageView arrow;
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
	
	public OtherViewHolder(Context context,View itemView,ClickTabListener listener) {
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
	
	@OnClick({R.id.tv_home_arrow}) 
	public void clickMyView(View view) {
		this.listener.click(item, NewHomeListAdapter.TYPE_HEAD);
	}
	
	public void setItem(int type,HomeItem item) {
		
		this.item = item;
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