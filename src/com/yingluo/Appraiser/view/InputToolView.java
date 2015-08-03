package com.yingluo.Appraiser.view;

import com.yingluo.Appraiser.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class InputToolView extends RelativeLayout {

	private TextView tvName;
	private EditText etInput;
	
	public InputToolView(Context context) {
		this(context,null);
	}

	public InputToolView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}
	
	public InputToolView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View view = LayoutInflater.from(context).inflate(R.layout.new_tool_each_input, this, true); 
		tvName = (TextView) view.findViewById(R.id.tv_canshu);
		etInput = (EditText) view.findViewById(R.id.et_input);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.inputRel);    
		CharSequence text = a.getText(R.styleable.inputRel_title_value);    
		if(text != null) {
			tvName.setText(text);
		}
		a.recycle();  	 
	}
	
	public String getInputText() {
		return etInput.getText().toString();
	}
	

}
