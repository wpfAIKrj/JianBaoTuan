package com.yingluo.Appraiser.view.viewholder;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.http.ResponseMyIdentify.userIdentify;
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
import android.widget.TextView;

public class OtherIdentityHolder extends ViewHolder{

	@ViewInject(R.id.iv_home_image)
	private ImageView showImage;
	@ViewInject(R.id.tv_home_title)
	private TextView title;
	@ViewInject(R.id.nhv_has_identify)
	private NewHomeIdentifyView hasIdentify;
	
	private Context mContext;
	private userIdentify item;
	
	public OtherIdentityHolder(Context context,View itemView) {
		super(itemView);
		mContext = itemView.getContext();
		ViewUtils.inject(this, itemView);
		ViewGroup.LayoutParams rlParams = showImage.getLayoutParams();
        rlParams.width = DensityUtil.getScreenWidth(context);
        rlParams.height = (int) (DensityUtil.getScreenWidth(context)*3/4.0);
        showImage.setLayoutParams(rlParams);
	}
	
	public void setItem(userIdentify item) {
		
		this.item = item;
		//设置图片
		String url = BitmapsUtils.makeQiNiuRrl(item.getTreasure_images() , showImage.getWidth(), showImage.getHeight());
		BitmapsUtils.getInstance().display(showImage, url, BitmapsUtils.TYPE_YES);
		//设置描述
		title.setText(item.getTreasure_description());
		hasIdentify.setHeadGone();
		hasIdentify.setTitle(item.getAppraisal_data());
		hasIdentify.setKinds(item.getKinds());
	}

}
