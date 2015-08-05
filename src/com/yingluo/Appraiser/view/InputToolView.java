package com.yingluo.Appraiser.view;

import com.yingluo.Appraiser.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
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
		etInput.addTextChangedListener(new TextWatcher() {    
           
            @Override    
            public void onTextChanged(CharSequence s, int start, int before,    
                    int count) {
            	if(s.length()>3 && !s.toString().contains(".")) {
            		s = s.toString().subSequence(0,3);
                    etInput.setText(s);
                    etInput.setSelection(s.length());
            	}
            	if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        etInput.setText(s);
                        etInput.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    etInput.setText(s);
                    etInput.setSelection(2);
                }
 
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                    	etInput.setText(s.subSequence(0, 1));
                    	etInput.setSelection(1);
                        return;
                    }
                }
            }    
    
            @Override    
            public void beforeTextChanged(CharSequence s, int start, int count,    
                    int after) {      
            }    
    
            @Override    
            public void afterTextChanged(Editable s) {   
                  
            }    
        }); 
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.inputRel);    
		CharSequence text = a.getText(R.styleable.inputRel_title_value);    
		if(text != null) {
			tvName.setText(text);
		}
		a.recycle();  	 
	}
	
	public String getInputText() {
		if(etInput.getText() == null) {
			return "";
		}
		return etInput.getText().toString();
	}
	
	public EditText getEditView() {
		return etInput;
	}

}
