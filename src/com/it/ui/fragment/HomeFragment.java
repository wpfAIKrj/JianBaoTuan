package com.it.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.it.R;
import com.it.app.ItApplication;
import com.it.bean.HomeEntity;
import com.it.ui.activity.ActivitySearch;
import com.it.ui.adapter.MyLoveAdapter;
import com.it.ui.adapter.WellKnowPeopleAdapter;
import com.it.ui.base.BaseFragment;
import com.it.view.ImageViewPage;
import com.it.view.home.ViewArticles;
import com.it.view.home.ViewChoices;
import com.it.view.home.ViewHots;
import com.it.view.listview.HorizontalListView;

public class HomeFragment extends BaseFragment implements OnClickListener {

	private ImageViewPage head;

	HomeEntity homeEntity;

	private View btn_search;
	HorizontalListView hsv;

	WellKnowPeopleAdapter adapter;

	ViewChoices choices_0, choices_1, choices_2, choices_3;
	ViewHots viewhots_0, viewhots_1, viewhots_2, viewhots_3;
	ViewArticles articles_0, articles_1;

	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.layout_home, container, false);
	}

	@Override
	protected void initViews(View view) {
		// TODO Auto-generated method stub
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

		head = (ImageViewPage) view.findViewById(R.id.imageViewpage);
		hsv = (HorizontalListView) view.findViewById(R.id.hs_people);
		// for test
		List<Drawable> list = new ArrayList<Drawable>();
		list.add(getResources().getDrawable(R.drawable.test_1));
		list.add(getResources().getDrawable(R.drawable.test_1));
		list.add(getResources().getDrawable(R.drawable.test_1));
		list.add(getResources().getDrawable(R.drawable.test_1));
		list.add(getResources().getDrawable(R.drawable.test_1));
		head.prepareData(list);

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
		} else {
			choices_0.setItem(homeEntity.getChoices().get(0));
			choices_1.setItem(homeEntity.getChoices().get(1));
			choices_2.setItem(homeEntity.getChoices().get(2));
			choices_3.setItem(homeEntity.getChoices().get(3));
			viewhots_0.setItem(homeEntity.getHots().get(0));
			viewhots_1.setItem(homeEntity.getHots().get(1));
			viewhots_2.setItem(homeEntity.getHots().get(2));
			viewhots_3.setItem(homeEntity.getHots().get(3));
			articles_0.setItem(homeEntity.getArticles().get(0));
			articles_1.setItem(homeEntity.getArticles().get(1));

			adapter = new WellKnowPeopleAdapter(getActivity(),
					homeEntity.getAuthors());

			hsv.setAdapter(adapter);
		}

		// ArrayList<String> strs = new ArrayList<String>();
		// strs.add("");
		// strs.add("");
		// strs.add("");
		// strs.add("");
		// strs.add("");
		// strs.add("");
		// strs.add("");
		// strs.add("");
		// strs.add("");
		// strs.add("");
		// strs.add("");
		// adapter = new WellKnowPeopleAdapter(getActivity(), strs);
		// MyLoveAdapter adapter = new MyLoveAdapter(mActivity);
		// hsv.setAdapter(adapter);

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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// case R.id.iv_home_item0:
		// case R.id.iv_home_item1:
		// case R.id.iv_home_item2:
		// case R.id.iv_home_item3:
		//
		// startActivity(new Intent(getActivity(), ActivityUserDelails.class));
		// break;

		// case R.id.iv_home_item1_0:
		// case R.id.iv_home_item1_1:
		// case R.id.iv_home_item1_2:
		// case R.id.iv_home_item1_3:
		// startActivity(new Intent(getActivity(), ActivityHotIdentiy.class));
		// break;
		}

	}

}
