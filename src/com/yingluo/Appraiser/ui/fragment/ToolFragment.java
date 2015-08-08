package com.yingluo.Appraiser.ui.fragment;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.ui.base.BaseFragment;
import com.yingluo.Appraiser.view.InputToolView;

public class ToolFragment extends BaseFragment {

	@ViewInject(R.id.tv_tool_you_average)
	private TextView tvResult;
	@ViewInject(R.id.itv_height)
	private InputToolView height;
	@ViewInject(R.id.itv_weight)
	private InputToolView weight;
	@ViewInject(R.id.itv_circle)
	private InputToolView circle;
	@ViewInject(R.id.itv_number)
	private InputToolView number;

	@ViewInject(R.id.tv_tool_circle)
	private Button ballClick;
	@ViewInject(R.id.tv_tool_barrel)
	private Button zhuClick;
	
	private int type;
	
	private int CHOOSE_BALL = 1;
	private int CHOOSE_ZHU = 2;
	
	private float res;
	private String strHeight,strWeight,strCircle,strNumber;
	
	@OnClick({R.id.tv_jisuan,R.id.tv_tool_circle,R.id.tv_tool_barrel})
	public void doClick(View v) {
		
		strHeight = height.getInputText().toString();
		strWeight = weight.getInputText().toString();
		strCircle = circle.getInputText().toString();
		strNumber = number.getInputText().toString();
		
		switch(v.getId()) {
		case R.id.tv_jisuan:
			if((type == CHOOSE_ZHU) && (strHeight==null||strHeight.length()==0)) {
				Toast.makeText(mActivity, "高度不能为空", Toast.LENGTH_SHORT).show();
				return ;
			}
			if(strWeight==null||strWeight.length()==0) {
				Toast.makeText(mActivity, "重量不能为空", Toast.LENGTH_SHORT).show();
				return ;
			}
			if(strCircle==null||strCircle.length()==0) {
				Toast.makeText(mActivity, "直径不能为空", Toast.LENGTH_SHORT).show();
				return ;
			}
			if(strNumber==null||strNumber.length()==0) {
				Toast.makeText(mActivity, "数量不能为空", Toast.LENGTH_SHORT).show();
				return ;
			}
			if(type == CHOOSE_BALL) {
				//算球
				res = ball(Float.valueOf(strHeight),Float.valueOf(strWeight),Float.valueOf(strCircle),Float.valueOf(strNumber));
			}
			if(type == CHOOSE_ZHU) {
				//算柱体
				res = cylinder(Float.valueOf(strHeight),Float.valueOf(strWeight),Float.valueOf(strCircle),Float.valueOf(strNumber));
			}
			tvResult.setText(res+"");
			break;
		case R.id.tv_tool_circle:
			if(type == CHOOSE_ZHU)
			{
				ballClick.setBackgroundResource(R.drawable.shape_tool_btn_press);
				ballClick.setTextColor(getResources().getColor(R.color.wite));
				zhuClick.setBackgroundResource(R.drawable.shape_tool_btn_normal);
				zhuClick.setTextColor(getResources().getColor(R.color.new_tool_color));
				type = CHOOSE_BALL;
			}
			break;
		case R.id.tv_tool_barrel:
			if(type == CHOOSE_BALL)
			{
				zhuClick.setBackgroundResource(R.drawable.shape_tool_btn_press);
				zhuClick.setTextColor(getResources().getColor(R.color.wite));
				ballClick.setBackgroundResource(R.drawable.shape_tool_btn_normal);
				ballClick.setTextColor(getResources().getColor(R.color.new_tool_color));
				type = CHOOSE_ZHU;
			}
			break;
		}
	}

	//计算球体的密度
	private float ball(float height,float weight,float circle,float number) {
		float result;
		result = (float) ((weight/number)/(4.0*Math.PI*Math.pow(circle/2.0,3)/3.0));
		return result;
	}
	
	//计算柱体的密度
	private float cylinder(float height,float weight,float circle,float number) {
		float result;
		result = (float) ((weight/number)/(height*Math.PI*Math.pow(circle/2.0,2)));
		return result;
	}

	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.actvity_little_tool, container, false);
	}

	@Override
	protected void initViews(View view) {
		ViewUtils.inject(this, view);
		type = CHOOSE_BALL;
		mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); 
		number.getEditView().setInputType(InputType.TYPE_CLASS_NUMBER);
		number.getEditView().setHint("0");
	}

	@Override
	protected void initDisplay() {
		
	}

	@Override
	public void lazyLoad() {
		Toast.makeText(mActivity, "正在加载数据", Toast.LENGTH_SHORT).show();
	}

}
