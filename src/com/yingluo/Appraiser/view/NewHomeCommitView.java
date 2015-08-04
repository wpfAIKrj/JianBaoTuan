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

public class NewHomeCommitView extends RelativeLayout {

	private TextView tvTitle,tvCommit1,tvCommit2,tvCommit3;
	
	public NewHomeCommitView(Context context) {
		this(context,null);
	}

	public NewHomeCommitView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}
	
	public NewHomeCommitView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View view = LayoutInflater.from(context).inflate(R.layout.new_home_identify_coomit, this, true); 
		tvTitle = (TextView) view.findViewById(R.id.tv_identify_commit_title);
		tvCommit1 = (TextView) view.findViewById(R.id.tv_identify_input1);
		tvCommit2 = (TextView) view.findViewById(R.id.tv_identify_input2);
		tvCommit3 = (TextView) view.findViewById(R.id.tv_identify_input3);
		
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.commitTitle);    
		CharSequence text = a.getText(R.styleable.commitTitle_title);    
		if(text != null) {
			tvTitle.setText(text);
		}
		a.recycle();  	 
	}
	
	public void clear() {
		tvCommit1.setVisibility(View.GONE);
		tvCommit2.setVisibility(View.GONE);
		tvCommit3.setVisibility(View.GONE);
	}
	
	public void setCommitOrIdentify() {
		tvCommit1.setVisibility(View.VISIBLE);
		tvCommit1.setText("方德磊玩lol不错哟");
	}
	

	public void setCommitOrIdentify(String str) {
		tvCommit2.setVisibility(View.VISIBLE);
		tvCommit1.setText("方德磊玩lol不错哟");
	}
}
