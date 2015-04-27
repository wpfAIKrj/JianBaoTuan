package com.it.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.R;
import com.it.ui.base.BaseActivity;

public class LevelActivity extends BaseActivity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level);
		initView();
	}

	
	
	private void initView() {
		// TODO Auto-generated method stub
		ImageView bt=(ImageView)findViewById(R.id.button_category);
		bt.setBackgroundResource(R.drawable.back_botton);
		bt.setOnClickListener(this);
		TextView tv=(TextView)findViewById(R.id.home_title);
		tv.setText(R.string.authenticate_title);
		tv=(TextView)findViewById(R.id.textView1);
		tv.setText("");
		 ImageGetter imageGetter = new ImageGetter() {    
		       @Override  
		      public Drawable getDrawable(String source) { 
		      int id = Integer.parseInt(source);  
		 
		     //根据id从资源文件中获取图片对象  
		      Drawable d = getResources().getDrawable(id);  
		      d.setBounds(0, 0, d.getIntrinsicWidth(),d.getIntrinsicHeight());  
		       return d;  
		      }  
		      };  
		 
		   tv.append(Html.fromHtml("当你成功鉴定别人的宝贝后会获得积分,鉴定宝贝达到一定数量就会晋级为网络鉴定师,并获得相应称号及标志<img src=\""+R.drawable.level_tag+"\">,赶快成为小专家吧!"
		, imageGetter, null) );
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_category://返回上层
			setResult(RESULT_CANCELED, getIntent());
			finish();
			break;

		default:
			break;
		}
	}
}
