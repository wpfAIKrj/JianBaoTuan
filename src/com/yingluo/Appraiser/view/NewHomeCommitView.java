package com.yingluo.Appraiser.view;

import java.util.ArrayList;
import java.util.List;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.http.ResponseNewHome.Comment;
import com.yingluo.Appraiser.http.ResponseNewHome.Record;

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
	private List<TextView> allTextView;
	
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
		
		allTextView = new ArrayList<TextView>();
		allTextView.add(tvCommit1);
		allTextView.add(tvCommit2);
		allTextView.add(tvCommit3);
		
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
	
	public void setCommit(List<Comment> commts) {
		if(commts == null) {
			return ;
		}
		for(int i=0;i<commts.size();i++) {
			Comment oneComment = commts.get(i);
			TextView each = allTextView.get(i);
			each.setVisibility(View.VISIBLE);
			each.setText(oneComment.getTo_user_name()+":"+oneComment.getComment_data());
		}
	}
	

	public void setRecord(List<Record> records) {
		if(records == null) {
			return ;
		}
		for(int i=0;i<records.size();i++) {
			Record oneRecord = records.get(i);
			TextView each = allTextView.get(i);
			each.setVisibility(View.VISIBLE);
			each.setText(oneRecord.getUser_name()+":"+oneRecord.getAppraisal_data());
		}
	}
}
