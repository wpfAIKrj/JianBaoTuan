package com.yingluo.Appraiser.view;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.http.ResponseNewHome.Appraiser;
import com.yingluo.Appraiser.http.ResponseNewHome.kinds;
import com.yingluo.Appraiser.utils.BitmapsUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NewHomeIdentifyView extends RelativeLayout {

	private CircleImageView head;
	private TextView tvName,tvIntroduction,tvSay;
	private TagLinearLayout tllIdentify;
	
	public NewHomeIdentifyView(Context context) {
		this(context,null);
	}

	public NewHomeIdentifyView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}
	
	public NewHomeIdentifyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View view = LayoutInflater.from(context).inflate(R.layout.new_has_identify, this, true); 
		head = (CircleImageView) view.findViewById(R.id.tv_home_arrow);
		tvName = (TextView) view.findViewById(R.id.tv_identifyer_name);
		tvIntroduction = (TextView) view.findViewById(R.id.tv_identifyer_introduction);
		tvSay = (TextView) view.findViewById(R.id.tv_identifer_say);		
		tllIdentify = (TagLinearLayout) view.findViewById(R.id.tll_identify_tag);	 	 
	}
	
	public void setItem(Appraiser appraiser) {
		tvName.setText(appraiser.getUser_name());
		tvIntroduction.setText(appraiser.getUser_description());
		tvSay.setText(appraiser.getAppraisal_data());
		
		//设置头像
		if(appraiser.getUser_portrait() != null) {
			String urlArrow = BitmapsUtils.makeQiNiuRrl(appraiser.getUser_portrait() , 80, 80);
			BitmapsUtils.getInstance().display(head, urlArrow, BitmapsUtils.TYPE_YES);
		}
				
		List<kinds> kinds = appraiser.getKinds();
		int length = kinds.size();
		TreasureType key = new TreasureType();
		for (int i = 0; i < length; i++) {
			kinds kind = kinds.get(i);
			if (i >= 1) {
				key.parent_id = key.id;
			} else {
				key.parent_id = 0;
			}
			key.id = Long.valueOf(kind.getId());
			key.name = kind.getName();
			key.type = i;
		}
		if(length != 0) {
			tllIdentify.addTag(key);
		}
	}
}
