package com.it.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

	 /** Fragment当前状态是否可见 */
    protected boolean isVisible;
    protected Activity mActivity;
    
    
    @Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.mActivity = activity;
	}

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	View view=createView(inflater,container,savedInstanceState);
    	if(view!=null){
    		return view;
    	}
    	return super.onCreateView(inflater, container, savedInstanceState);
    }
    
    
    @Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initViews(view);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initDisplay();
	}


	@Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
         
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }
     
	

     
	/**
	 * fragment中填充新的布局
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 */
    protected abstract View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) ;
   
    /**
     * 绑定控件
     * @param view
     */
	protected abstract void initViews(View view);
	
	/**
	 * 显示数据
	 */
	protected abstract void initDisplay();
	
    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();     
    }
     
     
    /**
     * 不可见
     */
    protected void onInvisible() {
         
         
    }
     
     
    /** 
     * 延迟加载
     * 子类必须重写此方法
     */
    public abstract void lazyLoad();
	
    
}
