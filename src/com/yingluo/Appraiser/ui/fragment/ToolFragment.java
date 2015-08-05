package com.yingluo.Appraiser.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.ui.base.BaseFragment;
import com.yingluo.Appraiser.view.InputToolView;

public class ToolFragment extends BaseFragment {

	@ViewInject(R.id.tv_result)
	private TextView tvResult;
	@ViewInject(R.id.itv_height)
	private InputToolView height;
	@ViewInject(R.id.itv_weight)
	private InputToolView weight;
	@ViewInject(R.id.itv_circle)
	private InputToolView circle;
	@ViewInject(R.id.itv_number)
	private InputToolView number;

	
	@OnClick(R.id.tv_jisuan)
	public void doClick(View v) {
		if(height.getInputText()==null||height.getInputText().length()==0) {
			Toast.makeText(mActivity, "高度不能为空", Toast.LENGTH_SHORT).show();
			return ;
		}
		if(weight.getInputText()==null||weight.getInputText().length()==0) {
			Toast.makeText(mActivity, "重量不能为空", Toast.LENGTH_SHORT).show();
			return ;
		}
		if(circle.getInputText()==null||circle.getInputText().length()==0) {
			Toast.makeText(mActivity, "直径不能为空", Toast.LENGTH_SHORT).show();
			return ;
		}
		if(number.getInputText()==null||number.getInputText().length()==0) {
			Toast.makeText(mActivity, "数量不能为空", Toast.LENGTH_SHORT).show();
			return ;
		}
		
		
	}


	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.actvity_little_tool, container, false);
	}

	@Override
	protected void initViews(View view) {
		ViewUtils.inject(this, view);
	}

	@Override
	protected void initDisplay() {
		
	}

	@Override
	public void lazyLoad() {
		Toast.makeText(mActivity, "正在加载数据", Toast.LENGTH_SHORT).show();
	}

}
