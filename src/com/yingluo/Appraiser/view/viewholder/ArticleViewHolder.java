package com.yingluo.Appraiser.view.viewholder;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.SystemUtils;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 知识详情
 * 
 * @author Administrator
 *
 */
public class ArticleViewHolder extends ViewHolder {

	@ViewInject(R.id.acticle_logo)
	private ImageView logo;

	@ViewInject(R.id.article_title)
	private TextView title;

	@ViewInject(R.id.article_number)
	private TextView number;
	@ViewInject(R.id.delete_checkbox)
	private CheckBox box;
	private ContentInfo contentinfo;
	private OnClickListener lis;

	public ArticleViewHolder(View itemView, OnClickListener lisnter) {
		super(itemView);

		ViewUtils.inject(this, itemView);
		this.lis = lisnter;
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
		if(logo == null) {
			BitmapsUtils.getInstance().display(logo, contentinfo.getImage(), logo.getMeasuredWidth(),
					logo.getMeasuredHeight());
		}
		logo.setTag(contentinfo);
		number.setText("" + contentinfo.getView_times());
		box.setVisibility(View.GONE);
	}

}
