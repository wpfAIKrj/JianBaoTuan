package com.yingluo.Appraiser.view.viewholder;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CommentEntity;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.view.CircleImageView;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 宝物鉴定结果布局
 * @author xy418
 *
 */
public class IndentityResultViewHolder extends ViewHolder{

	@ViewInject(R.id.tv_name)
	private TextView tv_name;
	@ViewInject(R.id.tv_grade)
	private TextView tv_grade;
	
	@ViewInject(R.id.tv_msg)
	private TextView msg;
	
	@ViewInject(R.id.iv_grade)
	private ImageView iv_grade;
	
	@ViewInject(R.id.iv_head)
	private CircleImageView logo;
	
	public CommentEntity currnt;

	public Context mContext;
	
	private int[] imagelevel={R.drawable.level01,R.drawable.level02,R.drawable.level03,R.drawable.level04,
			R.drawable.level05,R.drawable.level06};
	
	public IndentityResultViewHolder(View itemView) {
		super(itemView);
		// TODO 自动生成的构造函数存根
		mContext=itemView.getContext();
		ViewUtils.inject(this, itemView);
		
	}

	
	
	public void setItem(CommentEntity commentEntity) {
		// TODO 自动生成的方法存根
		currnt=commentEntity;
		BitmapsUtils.getInstance().display(logo, currnt.authImage);
		tv_name.setText(currnt.authName);
		msg.setText(currnt.content);
		BitmapsUtils.getInstance().display(logo, currnt.authImage);
		iv_grade.setImageResource(imagelevel[currnt.authLevel]);

	}

}
