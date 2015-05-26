package com.it.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.it.R;
import com.it.utils.BitmapsUtils;

public class MyButton extends FrameLayout implements OnClickListener {

	// public static final String INTENT_ACTION = "com.ytmfdw.onclick";
	// public static final String EXTRA_NAME = "menu_id";
	ImageView display;
	String path = "";

	public MyButton(Context context, ImageView display, String url) {
		super(context);
		// TODO Auto-generated constructor stub
		path = url;
		init(context);
	}

	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public MyButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();

	}

	@Override
	protected void onDetachedFromWindow() {
		// TODO Auto-generated method stub
		super.onDetachedFromWindow();
	}

	private void init(Context context) {
		LayoutInflater.from(context).inflate(R.layout.view_image, this);
		setClickable(true);
		ImageView iv = (ImageView) findViewById(R.id.icon);
		BitmapsUtils.getInstance().display(iv, path);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (display == null) {
			return;
		}

		BitmapsUtils.getInstance().display(display, path);

	}
}
