package com.it.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.it.R;

public class ImageViewPage extends FrameLayout implements OnPageChangeListener {

	private List<ImageView> imageViewList;
	private LinearLayout llPoints;
	private int previousSelectPosition = 0;
	private ViewPager mViewPager;
	private boolean isLoop = true;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
		}
	};

	public ImageViewPage(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ImageViewPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ImageViewPage(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init(context);
	}

	private void init(Context context) {
		LayoutInflater.from(context).inflate(R.layout.layout_home_banner, this);
		llPoints = (LinearLayout) findViewById(R.id.llPoints);
		mViewPager = (ViewPager) findViewById(R.id.baner);

		imageViewList = new ArrayList<ImageView>();

		ViewPagerAdapter adapter = new ViewPagerAdapter();
		mViewPager.setAdapter(adapter);
		mViewPager.setOnPageChangeListener(this);

	}

	class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		/**
		 * 判断出去的view是否等于进来的view 如果为true直接复用
		 */
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		/**
		 * 销毁预加载以外的view对象, 会把需要销毁的对象的索引位置传进来就是position
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(imageViewList.get(position
					% imageViewList.size()));
		}

		/**
		 * 创建一个view
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container
					.addView(imageViewList.get(position % imageViewList.size()));
			return imageViewList.get(position % imageViewList.size());
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int position) {
		// 改变图片的描述信息
		// 切换选中的点
		llPoints.getChildAt(previousSelectPosition).setBackgroundResource(
				R.drawable.ball_normal); // 把前一个点置为normal状态
		llPoints.getChildAt(position % imageViewList.size())
				.setBackgroundResource(R.drawable.ball_select); // 把当前选中的position对应的点置为enabled状态
		previousSelectPosition = position % imageViewList.size();
	}

	@Override
	protected void onDetachedFromWindow() {
		// TODO Auto-generated method stub
		super.onDetachedFromWindow();
		isLoop = false;
	}

	/**
	 * 设置数据
	 * 
	 * @param imageRes
	 */
	public void prepareData(List<Drawable> imageRes) {
		if (imageRes == null)
			return;
		imageViewList.clear();

		ImageView iv;
		View view;
		for (int i = 0; i < imageRes.size(); i++) {
			iv = new ImageView(this.getContext());
			iv.setImageDrawable(imageRes.get(i));
			imageViewList.add(iv);

			// 添加点view对象
			view = new View(getContext());
			view.setBackgroundResource(R.drawable.ball_normal);
			LayoutParams lp = new LayoutParams(15, 15);
			lp.leftMargin = 10;
			view.setLayoutParams(lp);
			view.setEnabled(false);
			llPoints.addView(view);
		}
		llPoints.getChildAt(previousSelectPosition).setBackgroundResource(
				R.drawable.ball_normal);
		mViewPager.getAdapter().notifyDataSetChanged();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (isLoop) {
					SystemClock.sleep(2000);
					handler.sendEmptyMessage(0);
				}
			}
		}).start();
	}

}
