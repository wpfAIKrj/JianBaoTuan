package com.yingluo.Appraiser.refresh;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;

/**
 * just support the LinearLayoutManager and the orientation is vertical
 * Created by 6a209 on 14/10/29.
 */
public class PullRefreshRecyclerView extends RefreshLayout{

    public PullRefreshRecyclerView(Context context){
        this(context, null);
    }

    public PullRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected View createRefreshView() {
    	
        RecyclerView rv =  new RecyclerView(getContext());
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        StaggeredGridLayoutManager mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);//表示两列，并且是竖直方向的瀑布流
    	rv.setLayoutManager(mStaggeredGridLayoutManager);
//        rv.setLayoutManager(lm);
        return rv;
    }



}
