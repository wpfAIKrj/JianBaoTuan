package com.yingluo.Appraiser.view.viewholder;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CommentEntity;
import com.yingluo.Appraiser.http.ResponseNewHome.Appraiser;
import com.yingluo.Appraiser.view.NewHomeIdentifyView;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

/**
 * 宝物鉴定结果布局
 * @author xy418
 *
 */
public class IndentityResultViewHolder extends ViewHolder{

	@ViewInject(R.id.nhv_has_identify)
	private NewHomeIdentifyView nhvIdentify;
	
	public CommentEntity currnt;

	public Context mContext;
	
	private int[] imagelevel={R.drawable.level01,R.drawable.level02,R.drawable.level03,R.drawable.level04,
			R.drawable.level05,R.drawable.level06};
	
	public IndentityResultViewHolder(View itemView) {
		super(itemView);
		mContext=itemView.getContext();
		ViewUtils.inject(this, itemView);
		
	}

	public void setItem(CommentEntity commentEntity) {
		Appraiser appraiser = new Appraiser();
		appraiser.setUser_name(commentEntity.getAuthName());
		appraiser.setAppraisal_data(commentEntity.getContent());
		appraiser.setUser_description(commentEntity.getAuthName());
		appraiser.setUser_portrait(commentEntity.getAuthImage());
		appraiser.setKinds(commentEntity.getKinds());
		nhvIdentify.setItem(appraiser);

	}

}
