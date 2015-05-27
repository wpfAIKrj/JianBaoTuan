package com.it.view.viewholder;

import com.daimajia.swipe.SwipeLayout;
import com.it.R;
import com.it.bean.ContentInfo;
import com.it.utils.BitmapsUtils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
    public SwipeInfoViewHolder(View itemViewl,final OnClickListener lisntener) {
        super(itemViewl);
        swipeLayout = (SwipeLayout) itemViewl.findViewById(R.id.swipe);
        context=(LinearLayout)itemViewl.findViewById(R.id.context);
        View view = LinearLayout.inflate(itemViewl.getContext(),R.layout.item_info, null);
        logo=(ImageView)view.findViewById(R.id.acticle_logo);
        name=(TextView)view.findViewById(R.id.article_title);
        number=(TextView)view.findViewById(R.id.article_number);
        context.addView(view);
        
        buttonDelete = (TextView) itemViewl.findViewById(R.id.delete);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	if(lisntener!=null){
            		lisntener.onClick(view);
            	}
            }
        });
    }
    

	
	public void showData(ContentInfo contentinfo){
		this.contentinfo=contentinfo;
		name.setText(contentinfo.getTitle());
		BitmapsUtils.getInstance().display(logo, contentinfo.getImage(),logo.getMeasuredWidth(),logo.getMeasuredHeight());
		number.setText(""+contentinfo.getView_times());
	}
	


}
