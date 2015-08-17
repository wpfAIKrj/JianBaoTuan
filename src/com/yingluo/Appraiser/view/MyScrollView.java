package com.yingluo.Appraiser.view;

import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import android.content.Context;
import android.util.AttributeSet;

public class MyScrollView extends PullToRefreshScrollView {

	public interface ScrollViewListener {   
	    void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy);  
	}  
	
	private ScrollViewListener scrollViewListener = null;  
	  
    public MyScrollView(Context context) {  
        super(context);  
    } 
  
    public MyScrollView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    public void setScrollViewListener(ScrollViewListener scrollViewListener) {  
        this.scrollViewListener = scrollViewListener;  
    }  
  
    @Override  
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {  
        super.onScrollChanged(x, y, oldx, oldy);  
        if (scrollViewListener != null) {  
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);  
        }  
    } 
    
}
