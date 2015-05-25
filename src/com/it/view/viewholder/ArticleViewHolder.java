package com.it.view.viewholder;

import com.it.R;
import com.it.bean.ContentInfo;
import com.it.utils.BitmapsUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 知识详情
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
	
	private ContentInfo contentinfo;
	private OnClickListener lis;
	public ArticleViewHolder(View itemView,OnClickListener lisnter) {
		super(itemView);
		// TODO Auto-generated constructor stub
		ViewUtils.inject(this, itemView);
		this.lis=lisnter;
		itemView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				v.setTag(contentinfo);
				if(lis!=null){
					lis.onClick(v);
				}
			}
		});
	}

	
	public void showData(ContentInfo contentinfo){
		this.contentinfo=contentinfo;
		title.setText(contentinfo.getTitle());
		
		BitmapsUtils.getInstance().display(logo, contentinfo.getImage(),logo.getMeasuredWidth(),logo.getMeasuredHeight());
		number.setText(""+contentinfo.getView_times());
	}
	
}
