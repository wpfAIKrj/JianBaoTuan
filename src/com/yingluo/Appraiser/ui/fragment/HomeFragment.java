package com.yingluo.Appraiser.ui.fragment;

import android.R.integer;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.HomeEntity;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.ui.activity.ActivityHotIdentiy;
import com.yingluo.Appraiser.ui.activity.ActivitySearch;
import com.yingluo.Appraiser.ui.activity.MainActivity;
import com.yingluo.Appraiser.ui.adapter.WellKnowPeopleAdapter;
import com.yingluo.Appraiser.ui.base.BaseFragment;
import com.yingluo.Appraiser.view.SlideShowView;
import com.yingluo.Appraiser.view.home.ViewArticles;
import com.yingluo.Appraiser.view.home.ViewChoices;
import com.yingluo.Appraiser.view.home.ViewHomeWhoWellKnow;
import com.yingluo.Appraiser.view.home.ViewHots;
import com.yingluo.Appraiser.view.listview.HorizontalListView;

public class HomeFragment extends BaseFragment implements OnItemClickListener,
		OnClickListener {

	private SlideShowView head;

	HomeEntity homeEntity;

	private View btn_search;
	HorizontalListView hsv;

	WellKnowPeopleAdapter adapter;

	ViewChoices choices_0, choices_1, choices_2, choices_3;
	ViewHots viewhots_0, viewhots_1, viewhots_2, viewhots_3;
	ViewArticles articles_0, articles_1;

	ViewGroup layout_home_item0, layout_home_item3, layout_home_item2,
			layout_home_item1;

	ViewHomeWhoWellKnow wellKnow;

	View tv_goto_infor, tv_goto_identify;

	private int index = 0;

	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.layout_home, container, false);
	}

	@Override
	protected void initViews(View view) {
		// TODO Auto-generated method stub
		layout_home_item0 = (ViewGroup) view
				.findViewById(R.id.layout_home_item0);
		layout_home_item3 = (ViewGroup) view
				.findViewById(R.id.layout_home_item3);
		layout_home_item2 = (ViewGroup) view
				.findViewById(R.id.layout_home_item2);
		layout_home_item1 = (ViewGroup) view
				.findViewById(R.id.layout_home_item1);
		choices_0 = (ViewChoices) view.findViewById(R.id.choices_0);
		choices_1 = (ViewChoices) view.findViewById(R.id.choices_1);
		choices_2 = (ViewChoices) view.findViewById(R.id.choices_2);
		choices_3 = (ViewChoices) view.findViewById(R.id.choices_3);
		viewhots_0 = (ViewHots) view.findViewById(R.id.viewhots_0);
		viewhots_1 = (ViewHots) view.findViewById(R.id.viewhots_1);
		viewhots_2 = (ViewHots) view.findViewById(R.id.viewhots_2);
		viewhots_3 = (ViewHots) view.findViewById(R.id.viewhots_3);
		articles_0 = (ViewArticles) view.findViewById(R.id.articles_0);
		articles_1 = (ViewArticles) view.findViewById(R.id.articles_1);
		wellKnow = (ViewHomeWhoWellKnow) view.findViewById(R.id.wellKnow);
		tv_goto_infor = view.findViewById(R.id.tv_goto_infor);
		tv_goto_identify = view.findViewById(R.id.tv_goto_identiy);
		tv_goto_identify.setOnClickListener(this);
		tv_goto_infor.setOnClickListener(this);

		head = (SlideShowView) view.findViewById(R.id.imageViewpage);
		hsv = (HorizontalListView) view.findViewById(R.id.hs_people);
		hsv.setOnItemClickListener(this);
		btn_search = view.findViewById(R.id.btn_search);
		btn_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), ActivitySearch.class));
			}
		});

		homeEntity = ((ItApplication) getActivity().getApplication())
				.getHomeEntity();
		if (homeEntity == null) {
			// 说明没有下载数据，就要去联网加载
			hideViews(true, (short) 7);
		} else {
			hideViews(false, (short) 7);
			if (homeEntity.getAdvertising() != null
					&& homeEntity.getAdvertising().size() > 0) {
				head.prepareData(homeEntity.getAdvertising());
			} else {

			}
			if (homeEntity.getChoices() != null
					&& homeEntity.getChoices().size() >= 4) {
				choices_0.setItem(homeEntity.getChoices().get(0));
				choices_1.setItem(homeEntity.getChoices().get(1));
				if (homeEntity.getChoices().size() > 2) {

					choices_2.setItem(homeEntity.getChoices().get(2));
					choices_3.setItem(homeEntity.getChoices().get(3));
				}
			} else {
				hideViews(true, (short) 0);
			}
			if (homeEntity.getHots() != null
					&& homeEntity.getHots().size() >= 4) {
				viewhots_0.setItem(homeEntity.getHots().get(0));
				viewhots_1.setItem(homeEntity.getHots().get(1));
				viewhots_2.setItem(homeEntity.getHots().get(2));
				if (homeEntity.getHots().size() > 3) {

					viewhots_3.setItem(homeEntity.getHots().get(3));
				}
			} else {
				hideViews(true, (short) 1);
			}
			if (homeEntity.getArticles() != null
					&& homeEntity.getArticles().size() >= 2) {
				articles_0.setItem(homeEntity.getArticles().get(0));
				articles_1.setItem(homeEntity.getArticles().get(1));
			} else {
				hideViews(true, (short) 2);
			}
			if (homeEntity.getAuthors() != null
					&& homeEntity.getAuthors().size() > 0) {
				adapter = new WellKnowPeopleAdapter(getActivity(),
						homeEntity.getAuthors());
				hsv.setAdapter(adapter);
				if (adapter.getCount() > 0) {// 设置第一个选中
					index = 0;
					wellKnow.setItem(adapter.getItem(index));
					new Thread(myWork).start();
				}
			} else {
				hideViews(true, (short) 3);
			}

		}

	}

	@Override
	protected void initDisplay() {
		// TODO Auto-generated method stub
	}

	@Override
	public void lazyLoad() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		if (adapter != null) {
			Intent mIntent = new Intent(mActivity, ActivityHotIdentiy.class);
			mIntent.putExtra(Const.ENTITY, homeEntity.getAuthors()
					.get(position));
			mActivity.startActivity(mIntent);
		}
	}

	public Runnable myWork = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				try {
					Thread.sleep(5000);
					mhandler.sendEmptyMessage(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};

	private Handler mhandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:// 更新页面
				if (adapter.getCount() > 0) {
					index++;
					if (index >= adapter.getCount()) {
						index = 0;
					}
					wellKnow.setItem(adapter.getItem(index));
				}
				break;

			default:
				break;
			}
		};
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_goto_infor:
			((MainActivity) getActivity()).onTabSelected(2);
			break;

		case R.id.tv_goto_identiy:
			((MainActivity) getActivity()).onTabSelected(1);
			break;
		}
	}

	/** 如果没数据，就隐藏 */
	private void hideViews(boolean flag, short type) {
		switch (type) {
		case 0:// 隐藏第一个
			layout_home_item0.setVisibility(flag ? View.GONE : View.VISIBLE);
			break;
		case 1:// 隐藏第一个
			layout_home_item1.setVisibility(flag ? View.GONE : View.VISIBLE);
			break;
		case 2:// 隐藏第二个
			layout_home_item2.setVisibility(flag ? View.GONE : View.VISIBLE);
			break;
		case 3:// 隐藏第三个
			layout_home_item3.setVisibility(flag ? View.GONE : View.VISIBLE);
			break;
		case 7:// 隐藏全部
			layout_home_item0.setVisibility(flag ? View.GONE : View.VISIBLE);
			layout_home_item1.setVisibility(flag ? View.GONE : View.VISIBLE);
			layout_home_item2.setVisibility(flag ? View.GONE : View.VISIBLE);
			layout_home_item3.setVisibility(flag ? View.GONE : View.VISIBLE);
			break;
		}

	}
}
