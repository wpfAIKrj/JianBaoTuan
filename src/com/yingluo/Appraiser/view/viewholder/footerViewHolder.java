package com.yingluo.Appraiser.view.viewholder;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yingluo.Appraiser.inter.ListviewLoadListener;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * 加载更多的子布局
 * @author Administrator
 *
 */
public class footerViewHolder extends ViewHolder{

	@ViewInject(R.id.xlistview_footer_progressbar)
	private ProgressBar bar;
	
	@ViewInject(R.id.xlistview_footer_hint_textview)
	private TextView title;
	
	private int currnt_type=0;// 0为加载更多，1为正在加载
	
	private View mview;
	public footerViewHolder(View itemView,final ListviewLoadListener lis) {
		super(itemView);
		// TODO Auto-generated constructor stub
		mview=itemView;
		ViewUtils.inject(this, itemView);
		itemView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(lis!=null){
					lis.onLoadMore();
				}
			}
		});
	}

	
	public void showloadMore(int type){
		currnt_type=type;
		switch (type) {
		case 0://加载更多
			mview.setVisibility(View.VISIBLE);
			bar.setVisibility(View.GONE);
			title.setText(R.string.xlistview_footer_hint_normal);
			break;
		case 1:
			mview.setVisibility(View.VISIBLE);
			bar.setVisibility(View.VISIBLE);
			title.setText(R.string.xlistview_header_hint_loading);
			break;
		case 2://隐藏布局
			mview.setVisibility(View.GONE);
			
			break;
		default:
			break;
		}
	}
}
