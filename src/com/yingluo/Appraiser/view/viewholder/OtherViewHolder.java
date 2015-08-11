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

	@ViewInject(R.id.iv_home_image)
	private ImageView showImage;
	@ViewInject(R.id.tv_home_title)
	private TextView title;
	@ViewInject(R.id.hcv_identify)
	private NewHomeCommitView identify;
	@ViewInject(R.id.hcv_commit)
	private NewHomeCommitView commit;
	@ViewInject(R.id.nhv_has_identify)
	private NewHomeIdentifyView hasIdentify;
	
	private Context mContext;
	private ClickTabListener listener;
	private HomeItem item;
	
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
	
	public void setItem(HomeItem item) {
		
		this.item = item;
		//设置图片
		String url = BitmapsUtils.makeQiNiuRrl(item.getImages() , showImage.getWidth(), showImage.getHeight());
		BitmapsUtils.getInstance().display(showImage, url, BitmapsUtils.TYPE_YES);
		//设置描述
		title.setText(item.getTreasure_description());
		
		//判断是显示专家鉴定还是网友鉴定
		if(item.getAppraiser() == null) {
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
