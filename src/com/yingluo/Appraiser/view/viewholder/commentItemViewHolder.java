package com.yingluo.Appraiser.view.viewholder;

import u.aly.cu;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CommentEntity;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.ImageUtils;
import com.yingluo.Appraiser.view.CircleImageView;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 宝物 评论布局
 * @author xy418
 *
 */
public class commentItemViewHolder extends ViewHolder{

	@ViewInject(R.id.tv_name)
	private TextView name;
	@ViewInject(R.id.tv_number)
	private TextView number;
	@ViewInject(R.id.tv_time)
	private TextView time;
	@ViewInject(R.id.tv_msg)
	private TextView msg;
	
	@ViewInject(R.id.button1)
	private Button bt;
	
	@ViewInject(R.id.iv_head)
	private CircleImageView logo;
	
	public CommentEntity currnt;

	public Context mContext;
	private String numberstr;
	public commentItemViewHolder(View itemView,final OnClickListener listener) {
		super(itemView);
		// TODO 自动生成的构造函数存根
		mContext=itemView.getContext();
		ViewUtils.inject(this, itemView);
		numberstr=mContext.getResources().getString(R.string.title_number);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				if(listener!=null){
					v.setTag(currnt);
					listener.onClick(v);
				}
			}
		});
		
	}

	public void setItem(CommentEntity commentEntity) {
		// TODO 自动生成的方法存根
		currnt=commentEntity;
		BitmapsUtils.getInstance().display(logo, currnt.authImage);
		name.setText(currnt.authName);
		time.setText(currnt.insert_time);
		number.setText(String.format(numberstr, currnt.index));
		if(currnt.to_user_id!=0){//回复某人
			StringBuffer str=new StringBuffer(String.format(mContext.getResources().getString(R.string.comment_list_tag),currnt.to_user_name));
			int lenght=str.length();
			str.append("  "+currnt.content);
			int color=mContext.getResources().getColor(R.color.dialog_title_color);
			SpannableString ss=new SpannableString(str);
			ss.setSpan(new ForegroundColorSpan(color), 0,lenght, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			msg.setText(ss);
		}else{//单纯评论
			msg.setText(currnt.content);
		}
	}

}
