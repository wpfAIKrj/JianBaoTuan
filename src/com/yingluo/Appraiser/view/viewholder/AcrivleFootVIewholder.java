package com.yingluo.Appraiser.view.viewholder;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.utils.BitmapsUtils;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class AcrivleFootVIewholder extends ViewHolder {

	
	BitmapsUtils bitmapUtils;

	@ViewInject(R.id.iv_item3)
	ImageView iv;
	@ViewInject(R.id.tv_msg)
	TextView tv_msg;
	@ViewInject(R.id.tv_num)
	TextView tv_num;
	
	CollectionTreasure currnt;
	
	public AcrivleFootVIewholder(View itemView,final OnClickListener lis) {
		super(itemView);
		// TODO 自动生成的构造函数存根
		ViewUtils.inject(this,itemView);
		itemView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				if(lis!=null){
					v.setTag(currnt);
					lis.onClick(v);
				}
			}
		});
	}
	
	public void setItem(CollectionTreasure arcitite){
		currnt=arcitite;
		if (bitmapUtils == null) {
			bitmapUtils = BitmapsUtils.getInstance();
		}
		bitmapUtils.display(iv, currnt.getImage(), BitmapsUtils.TYPE_YES);
		tv_msg.setText(currnt.msg);
		tv_num.setText(currnt.viewTimes + "");
		
	}

}
