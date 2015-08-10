package com.yingluo.Appraiser.view.viewholder;

import com.daimajia.swipe.SwipeLayout;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.utils.BitmapsUtils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SwipeInfoViewHolder extends RecyclerView.ViewHolder {

	
	public TextView buttonDelete;
    public LinearLayout context;
	public SwipeLayout swipeLayout;
	public ImageView logo;
	public TextView name;
	public TextView number;
	private ContentInfo contentinfo;
	
	public CheckBox deletecheckbox;
		
    public SwipeInfoViewHolder(View itemViewl,final OnClickListener lisntener) {
        super(itemViewl);
        swipeLayout = (SwipeLayout) itemViewl.findViewById(R.id.swipe);
        context=(LinearLayout)itemViewl.findViewById(R.id.context);
        View view = LinearLayout.inflate(itemViewl.getContext(),R.layout.item_info, null);
        logo=(ImageView)view.findViewById(R.id.acticle_logo);
        name=(TextView)view.findViewById(R.id.article_title);
        number=(TextView)view.findViewById(R.id.article_number);
        context.addView(view);
        deletecheckbox=(CheckBox)view.findViewById(R.id.delete_checkbox);
        buttonDelete = (TextView) itemViewl.findViewById(R.id.delete);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	view.setTag(contentinfo);
            	if(lisntener!=null){
            		lisntener.onClick(view);
            	}
            }
        });
    }
    

	/**
	 * 滑动删除
	 * @param contentinfo
	 */
	public void showData(ContentInfo contentinfo){
		deletecheckbox.setVisibility(View.GONE);
		this.contentinfo=contentinfo;
		name.setText(contentinfo.getTitle());
		if(logo.getTag() == null) {
			BitmapsUtils.getInstance().display(logo, contentinfo.getImage(),logo.getMeasuredWidth(),logo.getMeasuredHeight());
			logo.setTag(contentinfo);
		}	
		number.setText(contentinfo.getView_times()+"人浏览");
	}


	/**
	 * 选择删除模式
	 * @param conInfo
	 * @param isselect 
	 */
	public void showDataForDelete(ContentInfo conInfo, boolean isselect){
		deletecheckbox.setVisibility(View.VISIBLE);
		this.contentinfo=contentinfo;
		name.setText(contentinfo.getTitle());
		if(logo.getTag() == null) {
			BitmapsUtils.getInstance().display(logo, contentinfo.getImage(),logo.getMeasuredWidth(),logo.getMeasuredHeight());
			logo.setTag(conInfo);
		}
		number.setText(""+contentinfo.getView_times());
		deletecheckbox.setChecked(isselect);
	}
	
	


}
