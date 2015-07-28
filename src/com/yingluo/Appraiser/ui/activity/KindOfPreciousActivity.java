package com.yingluo.Appraiser.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.model.CommonCallBack;
import com.yingluo.Appraiser.model.getAllKind_X_Model;
import com.yingluo.Appraiser.ui.adapter.KindAdapter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.SqlDataUtil;
import com.yingluo.Appraiser.utils.ToastUtils;

public class KindOfPreciousActivity extends BaseActivity {

	@ViewInject(R.id.home_title)
	TextView home_title;

	@ViewInject(R.id.tree_view)
	RecyclerView treeView;

	@ViewInject(R.id.btn_back)
	ImageView btn_back;
	@ViewInject(R.id.bt_sure)
	ImageView btn_sure;
	@ViewInject(R.id.layout_all_kind)
	View all_kind;

	@ViewInject(R.id.pb_wait)
	ProgressBar pb;
	public static int currentType = 0;

	private KindAdapter kindadapter;

	private int getType;

	@OnClick({ R.id.btn_back, R.id.layout_all_kind, R.id.bt_sure })
	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			onBackPressed();
			break;
		case R.id.layout_all_kind: {
			Intent mIntent = getIntent();
			mIntent.putExtra("page", 0);
			mIntent.putExtra(Const.KIND_ID, 0);
			setResult(RESULT_OK, mIntent);
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
		}
			break;
		case R.id.bt_sure:
			Intent mIntent = new Intent(KindOfPreciousActivity.this, ActivitySearch.class);
			startActivity(mIntent);
			overridePendingTransition(R.anim.left_in, R.anim.left_out);
			break;

		default:
			break;
		}
	}

	// getAllKind_X_Model model;

	// private TreeListViewAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_kind_of_precious);
		ViewUtils.inject(this);

		currentType = 0;
		LinearLayoutManager manager = new LinearLayoutManager(this);
		treeView.setLayoutManager(manager);
		
		getType = getIntent().getIntExtra(Const.SHOW_TYPE, 0);
		if (getType == 1) {
			all_kind.setVisibility(View.GONE);
		}
		
		List<TreasureType> list=SqlDataUtil.getInstance().getTreasureType(0);
		if(list.size() == 0) {
			pb.setVisibility(View.VISIBLE);
			final getAllKind_X_Model allKinds = new getAllKind_X_Model();
			allKinds.sendHttp(new CommonCallBack() {

				@Override
				public void onSuccess() {
					pb.setVisibility(View.GONE);
					setInput();
				}

				@Override
				public void onError() {
					Toast.makeText(KindOfPreciousActivity.this, "获取分类信息失败", Toast.LENGTH_SHORT).show();;
				}
			});
		} else {
			setInput();
		}

	}

	public void setInput() {
		kindadapter = new KindAdapter(this, listener, currentType);
		treeView.setAdapter(kindadapter);
	}
	
	/** 隐藏搜索和全部，当进入二级以下分类时，隐藏，进入一级分类时，显示 */
	public void hideSearchAndAll(boolean flag) {
		all_kind.setVisibility(flag ? View.GONE : View.VISIBLE);
	}

	@Override
	public void onBackPressed() {
		currentType--;
		if (currentType < 0) {
			setResult(RESULT_CANCELED, getIntent());
			finish();
		} else {
			// 刷新数据，显示当前最新的
			if (currentType == 0) {
				hideSearchAndAll(false);
				home_title.setText(R.string.str_kind_of_precious);
				kindadapter.selectType = null;
				kindadapter.setNextDataType(null);
			} else {
				hideSearchAndAll(true);
				kindadapter.selectType = SqlDataUtil.getInstance()
						.getTreasureTypeById(kindadapter.treasureType.parent_id);
				home_title.setText(kindadapter.selectType.getName());
				kindadapter.setProDataType(kindadapter.selectType);
			}
		}
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			TreasureType type = (TreasureType) v.getTag();
			if (type.isChild || type.getPosition() == -1) {
				// 没有下一级
				kindadapter.selectType = type;
				kindadapter.notifyDataSetChanged();
				Intent mIntent = getIntent();
				int kindid = 0;
				if (type != null) {
					kindid = type.getId().intValue();
				}
				LogUtils.d("选择宝物的id" + kindid);
				mIntent.putExtra(Const.KIND_ID, kindid);
				setResult(RESULT_OK, mIntent);
				finish();
				overridePendingTransition(R.anim.right_in, R.anim.right_out);
			} else {
				// 有下一级
				hideSearchAndAll(true);
				currentType = type.getType() + 1;
				kindadapter.selectType = type;
				home_title.setText(type.getName());
				kindadapter.setNextDataType(type);
			}
		}
	};
}
