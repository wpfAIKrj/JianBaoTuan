package com.it.ui.activity;

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

import com.it.R;
import com.it.bean.TreasureType;
import com.it.model.getAllKind_X_Model;
import com.it.tree.bean.Node;
import com.it.tree.bean.TreeListViewAdapter;
import com.it.tree.bean.TreeListViewAdapter.OnTreeNodeClickListener;
import com.it.ui.adapter.SimpleTreeAdapter;
import com.it.utils.SqlDataUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

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
			onBackPressed();
			break;
		case R.id.layout_search: {
			Intent mIntent = new Intent(ActivityKindOfPrecious.this,
					ActivitySearch.class);
			startActivity(mIntent);
		}
			break;
		case R.id.layout_all_kind: {
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
		first=SqlDataUtil.getInstance().getTreasureType();
		LogUtils.d("获取所有分类："+first.size());
		try
		{
			mAdapter = new SimpleTreeAdapter<TreasureType>(treeView, this, first, 1);
			mAdapter.setOnTreeNodeClickListener(new OnTreeNodeClickListener()
			{
				@Override
				public void onClick(Node node, int position)
				{
					if (node.isLeaf())
					{
						Toast.makeText(getApplicationContext(), node.getName(),
								Toast.LENGTH_SHORT).show();
					}
				}

			});

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		treeView.setAdapter(mAdapter);

	}

	/**
	 * 选择3级跳转
	 */
	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

		}
	};

}
