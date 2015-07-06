package com.yingluo.Appraiser.ui.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.model.getAllKind_X_Model;
import com.yingluo.Appraiser.tree.bean.Node;
import com.yingluo.Appraiser.tree.bean.TreeListViewAdapter;
import com.yingluo.Appraiser.tree.bean.TreeListViewAdapter.OnTreeNodeClickListener;
import com.yingluo.Appraiser.ui.adapter.SimpleTreeAdapter;
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
			if (first.size() == 0) {
				return;
			}
			mAdapter.setVisibleLevel(0);
			// mAdapter.notifyDataSetChanged();
			Intent mIntent = getIntent();
			setResult(Activity.RESULT_OK, mIntent);
			finish();
		}
			break;

		default:
			break;
		}
	}

	List<TreasureType> first;

	getAllKind_X_Model model;

	private TreeListViewAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_kind_of_precious);
		ViewUtils.inject(this);
		first = SqlDataUtil.getInstance().getTreasureType();
		if (first.size() == 0) {
			return;
		}
		LogUtils.d("获取所有分类：" + first.size());
		try {
			mAdapter = new SimpleTreeAdapter<TreasureType>(treeView, this,
					first, 0);
			mAdapter.setOnTreeNodeClickListener(new OnTreeNodeClickListener() {
				@Override
				public void onClick(Node node, int position) {
					if (node.isLeaf()) {
						// Toast.makeText(getApplicationContext(),
						// node.getName(),
						// Toast.LENGTH_SHORT).show();
						// 跳转到大厅，
						Intent mIntent = getIntent();
						mIntent.putExtra(Const.KIND_ID, node.getType());
						setResult(Activity.RESULT_OK, mIntent);
						finish();

					}
				}

			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		treeView.setAdapter(mAdapter);

	}

	/** 隐藏搜索和全部，当进入二级以下分类时，隐藏，进入一级分类时，显示 */
	private void hideSearchAndAll(boolean flag) {
		all_kind.setVisibility(flag ? View.GONE : View.VISIBLE);
		search.setVisibility(flag ? View.GONE : View.VISIBLE);
	}

}
