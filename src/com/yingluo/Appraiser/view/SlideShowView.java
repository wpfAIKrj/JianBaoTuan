package com.yingluo.Appraiser.view;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.http.ResponseBanner.Banner;
import com.yingluo.Appraiser.ui.activity.ActivityUserDelails;
import com.yingluo.Appraiser.utils.BitmapsUtils;

/**
 * ViewPager实现的轮播图广告自定义视图，如京东首页的广告轮播图效果； 既支持自动轮播页面也支持手势滑动切换页面
 */

public class SlideShowView extends FrameLayout {

	// 使用universal-image-loader插件读取网络图片，需要工程导入universal-image-loader-1.8.6-with-sources.jar
	// private ImageLoader imageLoader = ImageLoader.getInstance();
	BitmapsUtils bitmapsUtils;
	// 轮播图图片数量
	private final static int IMAGE_COUNT = 5;
	// 自动轮播的时间间隔
	private final static int TIME_INTERVAL = 5;
	// 自动轮播启用开关
	private final static boolean isAutoPlay = true;

	// 自定义轮播图的资源
	private String[] imageUrls;
	// 放轮播图片的ImageView 的list
	private List<ImageView> imageViewsList;
	// 放圆点的View的list
	private List<View> dotViewsList;

	private ViewPager viewPager;
	// 定时任务
	private ScheduledExecutorService scheduledExecutorService;

	private Context context;

	private List<Banner> imageRes = null;
	
	/**
	 * 用于循环播放banner
	 */
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
		}

	};

	public SlideShowView(Context context) {
		this(context, null);
	}

	public SlideShowView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlideShowView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		bitmapsUtils = BitmapsUtils.getInstance();
	}

	/**
	 * 设置数据
	 */
	public void prepareData(List<Banner> imageRes) {
		if (imageRes == null)
			return;
		this.imageRes = imageRes;
		String[] list = new String[imageRes.size()];
		for (int i = 0; i < list.length; i++) {
			list[i] = imageRes.get(i).getImage();
		}
		initData(list);
		if (isAutoPlay) {
			startPlay();
		}
	}

	/**
	 * 开始轮播图切换
	 */
	private void startPlay() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
	}

	/**
	 * 停止轮播图切换
	 */
	private void stopPlay() {
		scheduledExecutorService.shutdown();
	}

	/**
	 * 初始化相关Data
	 */
	private void initData(String[] urls) {
		imageViewsList = new ArrayList<ImageView>();
		dotViewsList = new ArrayList<View>();

		// 一步任务获取图片
		new GetListTask().execute(urls);
	}

	/**
	 * 初始化Views等UI
	 */
	private void initUI(Context context) {
		if (imageUrls == null || imageUrls.length == 0)
			return;

		LayoutInflater.from(context).inflate(R.layout.layout_home_banner, this, true);

		LinearLayout dotLayout = (LinearLayout) findViewById(R.id.llPoints);
		dotLayout.removeAllViews();

		// 热点个数与图片特殊相等
		for (int i = 0; i < imageUrls.length; i++) {
			ImageView view = new ImageView(context);
			view.setTag(imageUrls[i]);
			view.setScaleType(ScaleType.FIT_XY);
			view.setOnClickListener(onclick);
			imageViewsList.add(view);

			if(i==0) {
				ImageView dotView = new ImageView(context);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						(int) getResources().getDimension(R.dimen.x40), (int) getResources().getDimension(R.dimen.x10));
				params.leftMargin = 10;
				params.rightMargin = 10;
				params.bottomMargin = 20;
				dotView.setBackgroundResource(R.drawable.banner_choose);
				dotLayout.addView(dotView, params);
				dotViewsList.add(dotView);
			} else {
				ImageView dotView = new ImageView(context);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						(int) getResources().getDimension(R.dimen.x10), (int) getResources().getDimension(R.dimen.x10));
				params.leftMargin = 10;
				params.rightMargin = 10;
				params.bottomMargin = 20;
				dotView.setBackgroundResource(R.drawable.banner_no_choose);
				dotLayout.addView(dotView, params);
				dotViewsList.add(dotView);
			}
			
		}

		viewPager = (ViewPager) findViewById(R.id.baner);
		viewPager.setFocusable(true);

		viewPager.setAdapter(new MyPagerAdapter());
		viewPager.setOnPageChangeListener(new MyPageChangeListener());
	}

	/**
	 * 填充ViewPager的页面适配器
	 * 
	 */
	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(imageViewsList.get(position%imageViewsList.size()));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			ImageView imageView = imageViewsList.get(position%imageViewsList.size());
//			String url = BitmapsUtils.makeQiNiuRrl((String) imageView.getTag() , iv.getWidth(), iv.getHeight());
			bitmapsUtils.display(imageView, (String) imageView.getTag(), BitmapsUtils.TYPE_YES);
			((ViewPager) container).addView(imageViewsList.get(position%imageViewsList.size()));
			return imageViewsList.get(position%imageViewsList.size());
		}

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}

	/**
	 * ViewPager的监听器 当ViewPager中页面的状态发生改变时调用
	 * 
	 */
	private class MyPageChangeListener implements OnPageChangeListener {

		boolean isAutoPlay = false;

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			switch (arg0) {
			case 1:// 手势滑动，空闲中
				isAutoPlay = false;
				break;
			case 2:// 界面切换中
				isAutoPlay = true;
				break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int pos) {
			for (int i = 0; i < dotViewsList.size(); i++) {
				ImageView each = (ImageView) dotViewsList.get(i);
				if (i == pos%imageViewsList.size()) {
					LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) each.getLayoutParams();
					params.width = (int)getResources().getDimension(R.dimen.x40);
					params.height = (int)getResources().getDimension(R.dimen.x10);
					each.setLayoutParams(params);
					each.setBackgroundResource(R.drawable.banner_choose);
				} else {
					LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) each.getLayoutParams();
					params.width = (int)getResources().getDimension(R.dimen.x10);
					params.height = (int)getResources().getDimension(R.dimen.x10);
					each.setLayoutParams(params);
					each.setBackgroundResource(R.drawable.banner_no_choose);
				}
			}
		}

	}

	/**
	 * 执行轮播图切换任务
	 *
	 */
	private class SlideShowTask implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized (viewPager) {
				handler.obtainMessage().sendToTarget();
			}
		}

	}

//	/**
//	 * 销毁ImageView资源，回收内存
//	 * 
//	 */
//	private void destoryBitmaps() {
//
//		for (int i = 0; i < IMAGE_COUNT; i++) {
//			ImageView imageView = imageViewsList.get(i);
//			Drawable drawable = imageView.getDrawable();
//			if (drawable != null) {
//				// 解除drawable对view的引用
//				drawable.setCallback(null);
//			}
//		}
//	}

	/**
	 * 异步任务,获取数据
	 */
	class GetListTask extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String... params) {
			try {
				// 这里一般调用服务端接口获取一组轮播图片，下面是从百度找的几个图片

				/*
				 * imageUrls = new String[]{
				 * "http://image.zcool.com.cn/56/35/1303967876491.jpg",
				 * "http://image.zcool.com.cn/59/54/m_1303967870670.jpg",
				 * "http://image.zcool.com.cn/47/19/1280115949992.jpg",
				 * "http://image.zcool.com.cn/59/11/m_1303967844788.jpg" };
				 */
				imageUrls = params;
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (result) {
				initUI(context);
			}
		}
	}

	/**
	 * 每个图片的点击事件
	 */
	private OnClickListener onclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (imageRes != null) {
				Intent mIntent = new Intent(getContext(), ActivityUserDelails.class);
				CollectionTreasure col = new CollectionTreasure();
				col.treasure_id = Long.valueOf(imageRes.get(viewPager.getCurrentItem()%imageRes.size()).getTreasure_id());
				mIntent.putExtra(Const.ENTITY, col);
				getContext().startActivity(mIntent);
				Activity act = (Activity) context;
				act.overridePendingTransition(R.anim.left_in, R.anim.left_out);
			}
		}
	};
}