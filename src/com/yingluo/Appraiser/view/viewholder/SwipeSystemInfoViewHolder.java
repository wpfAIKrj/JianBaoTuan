package com.yingluo.Appraiser.view.viewholder;

import com.daimajia.swipe.SwipeLayout;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.bean.SystemInfoEntity;
import com.yingluo.Appraiser.utils.BitmapsUtils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SwipeSystemInfoViewHolder extends RecyclerView.ViewHolder {

	
	public TextView buttonDelete;
    public LinearLayout context;
	public SwipeLayout swipeLayout;
	public TextView connext;
	public TextView time;
	private SystemInfoEntity contentinfo;
	
//	public CheckBox deletecheckbox;
		
    public SwipeSystemInfoViewHolder(View itemViewl,final OnClickListener lisntener) {
        super(itemViewl);
        swipeLayout = (SwipeLayout) itemViewl.findViewById(R.id.swipe);
        context=(LinearLayout)itemViewl.findViewById(R.id.context);
        View view = LinearLayout.inflate(itemViewl.getContext(),R.layout.item_sysinfo, null);
        connext=(TextView)view.findViewById(R.id.tv_context);
        time=(TextView)view.findViewById(R.id.tv_time);
        context.addView(view);
    //    deletecheckbox=(CheckBox)view.findViewById(R.id.delete_checkbox);
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
	public void showData(SystemInfoEntity contentinfo){
		//deletecheckbox.setVisibility(View.GONE);
		this.contentinfo=contentinfo;
		connext.setText(contentinfo.content);
		time.setText(""+contentinfo.time);
	}


	/**
	 * 选择删除模式
	 * @param conInfo
	 * @param isselect 
	 */
	public void showDataForDelete(SystemInfoEntity conInfo, boolean isselect){
	//	deletecheckbox.setVisibility(View.VISIBLE);
		this.contentinfo=contentinfo;
		connext.setText(contentinfo.content);
		time.setText(""+contentinfo.time);
	//	deletecheckbox.setChecked(isselect);
	}
	
	


}
