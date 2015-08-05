package com.yingluo.Appraiser.view.viewholder;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DensityUtil;
import com.yingluo.Appraiser.utils.SystemUtils;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 新版知识详情
 * 
 * @author 王亚立
 *
 */
public class ArticleNewViewHolder extends ViewHolder {

	@ViewInject(R.id.iv_new_info_image)
	private ImageView logo;

	@ViewInject(R.id.tv_new_info_title)
	private TextView title;

	private ContentInfo contentinfo;
	private OnClickListener lis;

	public ArticleNewViewHolder(Context context,View itemView, OnClickListener lisnter) {
		super(itemView);

		ViewUtils.inject(this, itemView);
		this.lis = lisnter;
		ViewGroup.LayoutParams rlParams = logo.getLayoutParams();
        rlParams.width = DensityUtil.getScreenWidth(context);
        rlParams.height = (int) (DensityUtil.getScreenWidth(context)*3/4.0);
        logo.setLayoutParams(rlParams);
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.setTag(contentinfo);
				if (lis != null) {
					lis.onClick(v);
				}
			}
		});
	}

	public void showData(ContentInfo contentinfo) {
		this.contentinfo = contentinfo;
		title.setText(contentinfo.getTitle());
		if(logo.getTag() == null) {
			
			BitmapsUtils.getInstance().display(logo, contentinfo.getImage(), logo.getMeasuredWidth(),
					logo.getMeasuredHeight());
			logo.setTag(contentinfo);
		}
	}

}
