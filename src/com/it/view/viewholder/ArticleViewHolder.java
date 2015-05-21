package com.it.view.viewholder;

import com.it.R;
import com.it.bean.ContentInfo;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticleViewHolder extends ViewHolder {

	@ViewInject(R.id.acticle_logo)
	private ImageView logo;
	
	@ViewInject(R.id.article_title)
	private TextView title;
	
	@ViewInject(R.id.article_number)
	private TextView number;
	
	private ContentInfo contentinfo;
	public ArticleViewHolder(View itemView) {
		super(itemView);
		// TODO Auto-generated constructor stub
		ViewUtils.inject(this, itemView);
	}

	
	public void showData(ContentInfo contentinfo){
		this.contentinfo=contentinfo;
	}
	
}
