package com.yingluo.Appraiser.refresh;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * just support the LinearLayoutManager and the orientation is vertical
 * Created by 6a209 on 14/10/29.
 */
public class PullRefreshRecyclerView extends RefreshLayout{

	private Context context;
	
    public PullRefreshRecyclerView(Context context){
        this(context, null);
    }

    public PullRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected View createRefreshView() {
    	
        RecyclerView rv =  new RecyclerView(getContext());
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);//表示两列
    	rv.setLayoutManager(gridLayoutManager);
        return rv;
    }



}
