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

import java.util.HashMap;
import java.util.Map;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.ui.base.BaseFragment;
import com.yingluo.Appraiser.view.InputToolView;

public class ToolFragment extends BaseFragment {

	@ViewInject(R.id.tv_tool_you_average)
	private TextView tvResult;
	@ViewInject(R.id.tv_tool_average_result)
	private TextView tvZongShu;
	@ViewInject(R.id.tv_tool_average)
	private TextView tvShow;
	
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
	
	//分类
	@ViewInject(R.id.tv_choose1)
	private TextView choose1;
	@ViewInject(R.id.tv_choose2)
	private TextView choose2;
	@ViewInject(R.id.tv_choose3)
	private TextView choose3;
	@ViewInject(R.id.tv_choose4)
	private TextView choose4;
	@ViewInject(R.id.tv_choose5)
	private TextView choose5;
	
	private Map<Integer, TextView> changeMap;
	private Map<Integer, String> nameMap;
	
	private int chooseType;
	private int type;
	
	private int CHOOSE_BALL = 1;
	private int CHOOSE_ZHU = 2;
	
	private float res;
	private String strHeight,strWeight,strCircle,strNumber;
	
	@OnClick({R.id.tv_jisuan,R.id.tv_tool_circle,R.id.tv_tool_barrel,
		R.id.tv_choose1,R.id.tv_choose2,R.id.tv_choose3,R.id.tv_choose4,R.id.tv_choose5})
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
			if(chooseType == R.id.tv_choose5) {
				tvShow.setVisibility(View.GONE);
				tvZongShu.setVisibility(View.GONE);
				tvResult.setText("你的"+nameMap.get(chooseType)+"密度为："+res);
			} else {
				tvShow.setVisibility(View.VISIBLE);
				tvZongShu.setVisibility(View.VISIBLE);
				tvShow.setText(nameMap.get(chooseType)+"的平均密度为：1000");
				tvResult.setText("你的"+nameMap.get(chooseType)+"密度为："+res);
				tvZongShu.setText("你买的啥鸡巴玩意，塑料的吧！");
			}
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
		case R.id.tv_choose1:
		case R.id.tv_choose2:
		case R.id.tv_choose3:
		case R.id.tv_choose4:
		case R.id.tv_choose5:
			changeBtn(v.getId());
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
		chooseType = R.id.tv_choose1;
		
		changeMap = new HashMap<Integer, TextView>();
		changeMap.put(R.id.tv_choose1, choose1);
		changeMap.put(R.id.tv_choose2, choose2);
		changeMap.put(R.id.tv_choose3, choose3);
		changeMap.put(R.id.tv_choose4, choose4);
		changeMap.put(R.id.tv_choose5, choose5);
		
		nameMap = new HashMap<Integer, String>();
		nameMap.put(R.id.tv_choose1, "星月菩提");
		nameMap.put(R.id.tv_choose2, "金刚菩提");
		nameMap.put(R.id.tv_choose3, "小叶紫檀");
		nameMap.put(R.id.tv_choose4, "海南黄花梨");
		
		mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); 
		number.getEditView().setInputType(InputType.TYPE_CLASS_NUMBER);
		number.getEditView().setHint("0");
	}
	
	public void changeBtn(int typeId) {
		chooseType = typeId;
		for (int key : changeMap.keySet()) {
			TextView view = changeMap.get(key);
			String str = view.getText().toString();
			if(key == typeId) {
				view.setBackgroundResource(R.drawable.shape_tool_btn_press);
				view.setTextColor(getResources().getColor(R.color.wite));
			} else {
				view.setBackgroundResource(R.drawable.shape_tool_btn_normal);
				view.setTextColor(getResources().getColor(R.color.new_tool_color));
			}
		}
	}

	@Override
	protected void initDisplay() {
		
	}

	@Override
	public void lazyLoad() {
		Toast.makeText(mActivity, "正在加载数据", Toast.LENGTH_SHORT).show();
	}

}
