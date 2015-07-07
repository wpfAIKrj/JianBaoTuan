package com.yingluo.Appraiser.ui.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.inter.OnKindClickListener;
import com.yingluo.Appraiser.model.getAllKind_X_Model;
import com.yingluo.Appraiser.ui.adapter.KindTreasureAdapter;
import com.yingluo.Appraiser.utils.SqlDataUtil;

public class ActivityKindOfPrecious extends Activity {

	@ViewInject(R.id.tree_view)
	ListView treeView;

	@ViewInject(R.id.btn_back)
	ImageView btn_back;
	@ViewInject(R.id.layout_search)
	View search;
	@ViewInject(R.id.layout_all_kind)
	View all_kind;

	public static int currentType = 0;
	private KindTreasureAdapter mAdapter;

	@OnClick({ R.id.btn_back, R.id.layout_search, R.id.layout_all_kind })
	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			setResult(RESULT_CANCELED, getIntent());
			finish();
			break;
		case R.id.layout_search: {
			Intent mIntent = new Intent(ActivityKindOfPrecious.this,
					ActivitySearch.class);
			startActivity(mIntent);
		}
			break;
		case R.id.layout_all_kind: {
			mAdapter.setData(first);
			// if (first.size() == 0) {
			// return;
			// }
			// mAdapter.setVisibleLevel(0);
			// mAdapter.notifyDataSetChanged();
			// Intent mIntent = getIntent();
			// setResult(Activity.RESULT_OK, mIntent);
			// finish();
		}
			break;

		default:
			break;
		}
	}

	List<TreasureType> first;

	getAllKind_X_Model model;

	// private TreeListViewAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_kind_of_precious);
		ViewUtils.inject(this);
		mAdapter = new KindTreasureAdapter(this);
		treeView.setAdapter(mAdapter);
		currentType = 0;
		first = SqlDataUtil.getInstance().getFirstType();
		LogUtils.d("获取所有分类：" + first.size());

		mAdapter.setData(first);
		mAdapter.setOnClickListener(new OnKindClickListener() {

			@Override
			public void onClick(TreasureType type) {
				// TODO Auto-generated method stub
				Intent mIntent = getIntent();
				mIntent.putExtra("page", 1);
				mIntent.putExtra(Const.KIND_ID, type.getType());
				setResult(Const.TO_INDENTIFY, mIntent);
				finish();
			}
		});

	}

	/** 隐藏搜索和全部，当进入二级以下分类时，隐藏，进入一级分类时，显示 */
	private void hideSearchAndAll(boolean flag) {
		all_kind.setVisibility(flag ? View.GONE : View.VISIBLE);
		search.setVisibility(flag ? View.GONE : View.VISIBLE);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		currentType--;
		if (currentType < 0) {
			super.onBackPressed();
		} else {
			mAdapter.onBackPress();
		}
	}

}
