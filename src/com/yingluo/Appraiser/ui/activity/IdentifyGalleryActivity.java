package com.yingluo.Appraiser.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.photo.PhotoView;
import com.yingluo.Appraiser.utils.photo.ViewPagerFixed;
public class IdentifyGalleryActivity extends BaseActivity{
	 // 返回按钮
    private ImageView back_bt;
	// 发送按钮
	private TextView send_bt;
	//删除按钮
	private ImageView del_bt;
	//顶部显示预览图片位置的textview
	private TextView positionTextView;

	//当前的位置
	private int location = 0;
	
	private ArrayList<View> listViews = null;
	private ViewPagerFixed pager;
	private MyPageAdapter adapter;

	public List<Bitmap> bmp = new ArrayList<Bitmap>();
	public List<String> drr = new ArrayList<String>();
	public List<String> del = new ArrayList<String>();
	
	private Context mContext;

	RelativeLayout photo_relativeLayout;
	
	public ArrayList<String> selectList;
	public int type;
	
	public int currentItem;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.plugin_camera_gallery);// 切屏到主界面
		
		mContext = this;
		back_bt = (ImageView) findViewById(R.id.gallery_back);
		send_bt = (TextView) findViewById(R.id.send_button);
		del_bt = (ImageView)findViewById(R.id.gallery_del);
		del_bt.setVisibility(View.GONE);
		back_bt.setOnClickListener(new BackListener());
		send_bt.setOnClickListener(new GallerySendListener());
		del_bt.setOnClickListener(new DelListener());
		type=getIntent().getIntExtra(Const.SELECT_ALBUM_TYPE, 0);
		selectList=getIntent().getStringArrayListExtra(Const.ENTITY);
		currentItem=getIntent().getIntExtra(Const.SELECT_INDEX, 0);
		RelativeLayout layout=(RelativeLayout)findViewById(R.id.bottom_layout);
		layout.setVisibility(View.GONE);
		isShowOkBt();
		// 为发送按钮设置文字
		pager = (ViewPagerFixed) findViewById(R.id.gallery01);
		pager.setOnPageChangeListener(pageChangeListener);
		for (int i = 0; i < selectList.size(); i++) {
			initListViews(selectList.get(i));
		}
		adapter = new MyPageAdapter(listViews);
		pager.setAdapter(adapter);
		pager.setPageMargin(20);
		pager.setCurrentItem(currentItem);
	}
	
	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		public void onPageSelected(int arg0) {
			location = arg0;
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageScrollStateChanged(int arg0) {

		}
	};
	
	private void initListViews(String path) {
		if (listViews == null)
			listViews = new ArrayList<View>();
		PhotoView img = new PhotoView(this);
		img.setBackgroundColor(0xff000000);
		BitmapsUtils.getInstance().display(img, path);
		img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		listViews.add(img);
	}
	
	// 返回按钮添加的监听器
	private class BackListener implements OnClickListener {

		public void onClick(View v) {
			setResult(RESULT_CANCELED, getIntent());
			finish();
		}
	}
	
	// 删除按钮添加的监听器
	private class DelListener implements OnClickListener {

		public void onClick(View v) {
			if (listViews.size() <=1) {
//				selectList.clear();
//				send_bt.setText(getString(R.string.finish)+"(" + selectList.size() + "/"+3+")");
				
			} else {
				selectList.remove(location);
				pager.removeAllViews();
				listViews.remove(location);
				adapter.setListViews(listViews);
				send_bt.setText(getString(R.string.finish)+"(" + selectList.size() + "/"+3+")");
				adapter.notifyDataSetChanged();
			}
		}
	}

	// 完成按钮的监听
	private class GallerySendListener implements OnClickListener {
		public void onClick(View v) {
			Intent intent=getIntent();
			intent.putExtra(Const.SELECT_ALBUM_TYPE, type);
			intent.putExtra(Const.SELECT_LIST, selectList);
			setResult(RESULT_OK, intent);
			finish();
		}

	}

	public void isShowOkBt() {
		if (selectList.size() > 0) {
			send_bt.setText(getString(R.string.finish)+"(" + selectList.size() + "/"+3+")");
			send_bt.setPressed(true);
			send_bt.setClickable(true);
			send_bt.setTextColor(Color.WHITE);
		} else {
			send_bt.setPressed(false);
			send_bt.setClickable(false);
			send_bt.setTextColor(Color.parseColor("#E1E0DE"));
		}
	}

	/**
	 * 监听返回按钮
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if (keyCode == KeyEvent.KEYCODE_BACK) {
				setResult(RESULT_CANCELED, getIntent());
				finish();
		}
		return true;
	}
	
	
	class MyPageAdapter extends PagerAdapter {

		private ArrayList<View> listViews;

		private int size;
		public MyPageAdapter(ArrayList<View> listViews) {
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public void setListViews(ArrayList<View> listViews) {
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public int getCount() {
			return size;
		}

		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPagerFixed) arg0).removeView(listViews.get(arg1 % size));
		}

		public void finishUpdate(View arg0) {
		}

		public Object instantiateItem(View arg0, int arg1) {
			try {
				((ViewPagerFixed) arg0).addView(listViews.get(arg1 % size), 0);

			} catch (Exception e) {
			}
			return listViews.get(arg1 % size);
		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}
}
