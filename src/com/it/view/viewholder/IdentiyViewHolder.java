package com.it.view.viewholder;

import com.it.R;
import com.it.bean.UserInfo;
import com.it.utils.BitmapsUtils;
import com.it.view.CircleImageView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.location.Criteria;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
/**
 * 随机鉴定师布局
 * @author Administrator
 *
 */
public class IdentiyViewHolder extends RecyclerView.ViewHolder{

	public UserInfo currnt;
	
	@ViewInject(R.id.user_logo)
	private CircleImageView logo;
	
	@ViewInject(R.id.user_name)
	private TextView tv_name;
	
	@ViewInject(R.id.user_type)
	private TextView tv_type;
	
	@ViewInject(R.id.user_checkbox)
	public CheckBox checkBox;
	
	
	public IdentiyViewHolder(View itemView) {
		super(itemView);
		// TODO Auto-generated constructor stub
		ViewUtils.inject(this, itemView);
	}
	
	
	public void showData(UserInfo user){
		currnt=user;
		if(user!=null){
			BitmapsUtils.getInstance().display(logo, user.getAvatar());
			tv_name.setText(user.getNickname());
			
		}
	}
	
	


}
