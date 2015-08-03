package com.yingluo.Appraiser.ui.fragment;

import io.rong.imkit.RongIM;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnLongClick;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.MainEvent;
import com.yingluo.Appraiser.bean.MyEvent;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.presenter.getUserInfoLPresenter;
import com.yingluo.Appraiser.ui.activity.ActivityFootPrint;
import com.yingluo.Appraiser.ui.activity.ActivityMyPrecious;
import com.yingluo.Appraiser.ui.activity.ActivityUserDelails;
import com.yingluo.Appraiser.ui.activity.AuthenticateActivity;
import com.yingluo.Appraiser.ui.activity.FavoriteArticlesActivity;
import com.yingluo.Appraiser.ui.activity.LevelActivity;
import com.yingluo.Appraiser.ui.activity.MainActivity;
import com.yingluo.Appraiser.ui.activity.SystemInfoActivity;
import com.yingluo.Appraiser.ui.activity.UserSetActivity;
import com.yingluo.Appraiser.ui.adapter.MyLikeAdapter;
import com.yingluo.Appraiser.ui.base.BaseFragment;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.SqlDataUtil;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.CircleImageView;

import de.greenrobot.event.EventBus;

/**
 * 我的设置页面
 * 
 * @author Administrator
 *
 */
public class MyFragment extends BaseFragment {

	@ViewInject(R.id.horizontalListView1)
	private RecyclerView listView;

	@ViewInject(R.id.my_tv_name)
	private TextView tv_name;

	@ViewInject(R.id.my_tv_authenticate)
	private TextView tv_authenticate;

	@ViewInject(R.id.my_iv_level)
	private ImageView iv_level;

	private ArrayList<CollectionTreasure> list = new ArrayList<CollectionTreasure>();

	@ViewInject(R.id.login_user_head)
	private CircleImageView user_logo;

	@ViewInject(R.id.my_type_msg)
	private TextView my_type_msg;

	@ViewInject(R.id.pb_jngyan)
	private ProgressBar pbJingYan;
	@ViewInject(R.id.tv_jingyan)
	private TextView tvJingyan;
	
	private getUserInfoLPresenter getPresenter;

	private int[] levels = { R.drawable.level01, R.drawable.level02, R.drawable.level03, R.drawable.level04,
			R.drawable.level05, R.drawable.level06 };
	private OnClickListener onlongListner;
	private Dialog dialog;

	private MyLikeAdapter madapter;

	private boolean isgete = false;
	private boolean isFirst = true;

	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.layout_my, container, false);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	public void onEventMainThread(MyEvent event) {
		switch (event.type) {
		case 0:
			// 更新人物头像
			if (ItApplication.getcurrnUser() != null) {
				tv_name.setText(ItApplication.getcurrnUser().getNickname());
				BitmapsUtils.getInstance().display(user_logo, ItApplication.getcurrnUser().getAvatar());
			}
			break;
		case 1:
			// 更新个人信息
			lazyLoad();
			break;
		default:
			break;
		}
	}

	@Override
	protected void initViews(View view) {
		ViewUtils.inject(this, view);
		getPresenter = new getUserInfoLPresenter(viewlis);
		LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
		manager.setOrientation(LinearLayoutManager.HORIZONTAL);
		listView.setLayoutManager(manager);
	}

	@Override
	protected void initDisplay() {
		initData();
		if (!isgete) {
			getPresenter.getUserInfo();
			isgete = true;
		}
	}

	@Override
	public void lazyLoad() {
		initDisplay();
	}

	public void setLogoListener(OnClickListener onlongListner) {
		this.onlongListner = onlongListner;
	}

	@OnClick({ R.id.login_user_head, R.id.ll_level, R.id.my_bt_showmenu, R.id.my_tv_authenticate,
			R.id.my_layout_collect, R.id.my_layout_foot, R.id.my_layout_identif, R.id.my_tab1, R.id.my_tab2,
			R.id.my_tab3, R.id.my_tab4, R.id.my_tab5, R.id.my_tab6 })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_user_head:
			// 更新头像
			if (onlongListner != null) {
				onlongListner.onClick(v);
			}
			break;
		case R.id.ll_level:
			// 跳转到等级说明
			mActivity.startActivity(new Intent(mActivity, LevelActivity.class));
			mActivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
			break;
		case R.id.my_bt_showmenu:
			// 右侧菜单
			mActivity.startActivityForResult(new Intent(mActivity, UserSetActivity.class), Const.TO_USER_SET);
			mActivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
			break;
		case R.id.my_tv_authenticate:
			// 跳转到认证鉴定师
			if (ItApplication.getcurrnUser() != null && ItApplication.getcurrnUser().getUser_type() == 0) {
				mActivity.startActivity(new Intent(mActivity, AuthenticateActivity.class));
				mActivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
			}
			break;

		case R.id.my_layout_collect:
			// 跳转收藏宝贝页面
		{
			Intent mIntent = new Intent(mActivity, ActivityMyPrecious.class);
			mIntent.putExtra(Const.GOTO_MY_PRECIOUS, Const.PRECIOUS);
			startActivity(mIntent);
		}
			mActivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
			break;
		case R.id.my_layout_foot:
			// 跳转到我的足迹
			startActivity(new Intent(mActivity, ActivityFootPrint.class));
			mActivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
			break;
		case R.id.my_layout_identif:
			// 跳转到我的鉴定
		{
			Intent mIntent = new Intent(mActivity, ActivityMyPrecious.class);
			mIntent.putExtra(Const.GOTO_MY_PRECIOUS, Const.IDENTIFY);
			startActivity(mIntent);
		}
			mActivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
			break;
		case R.id.my_tab1:
			// 跳转到我的宝贝
		{
			Intent mIntent = new Intent(mActivity, ActivityMyPrecious.class);
			mIntent.putExtra(Const.GOTO_MY_PRECIOUS, Const.COLLECT);
			startActivity(mIntent);
		}
			mActivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
			break;
		case R.id.my_tab2:
			// 跳转到收藏文章
			mActivity.startActivityForResult(new Intent(mActivity, FavoriteArticlesActivity.class),
					Const.TO_COLLECT_INFO);
			mActivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
			break;
		case R.id.my_tab3:
			// 跳转我的私信
			RongIM.getInstance().startConversationList(mActivity);
			break;
		case R.id.my_tab4:
			// 跳转到系统通知
			mActivity.startActivityForResult(new Intent(mActivity, SystemInfoActivity.class), Const.TO_SYSTEM_INFO);
			mActivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
			break;
		case R.id.my_tab5:
			// 跳转到认证鉴定师
			if (ItApplication.getcurrnUser() != null && ItApplication.getcurrnUser().getUser_type() == 0) {
				mActivity.startActivity(new Intent(mActivity, AuthenticateActivity.class));
				mActivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
			} else {
				new ToastUtils(mActivity, R.string.help_msg_01);
			}
			break;
		case R.id.my_tab6:// 跳转到人工客服
//			String strMobile = "10086";
//			// TODO 此处应该对电话号码进行验证。。
//			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + strMobile));
//			mActivity.startActivity(intent);
			break;
		default:
			break;
		}
	}

	private void initData() {
		if (ItApplication.getcurrnUser() != null && isFirst) {
			isFirst = false;
			tv_name.setText(ItApplication.getcurrnUser().getNickname());
			BitmapsUtils.getInstance().display(user_logo, ItApplication.getcurrnUser().getAvatar());
			if (ItApplication.getcurrnUser().getUser_type() == 0) {
				tv_authenticate.setText(R.string.type_collect);
//				iv_level.setImageResource(levels[ItApplication.getcurrnUser().getUser_level()]);
			} else {
				tv_authenticate.setText(R.string.type_identiy);
			}
			madapter = new MyLikeAdapter(mActivity,list, itemListner);
			listView.setAdapter(madapter);
		}
	}

	private onBasicView<String> viewlis = new onBasicView<String>() {

		@Override
		public void onSucess(String data) {
			JSONObject obj = null;
			int currentLevel = 0,levelNumber = 0,levelUpNeedNumber = 0;
			try {
				obj = new JSONObject(data);
				ItApplication.getcurrnUser().setTreasure_number(obj.getInt(NetConst.TREASURE_NUMBER));
				ItApplication.getcurrnUser().setTreasure_record_number(obj.getInt(NetConst.TREASURE_RECORD_NUMBER));
				ItApplication.getcurrnUser().setFoot_number(obj.getInt(NetConst.FOOT_NUMBER));
				ItApplication.getcurrnUser().setUser_type(obj.getInt(NetConst.USER_TYPE));
//				ItApplication.getcurrnUser().setUser_level(obj.getInt(NetConst.USER_LEVE));
				currentLevel = obj.getInt("currentLevel");
				levelNumber = obj.getInt("levelNumber");
				levelUpNeedNumber = obj.getInt("levelUpNeedNumber");
				
				SqlDataUtil.getInstance().saveUserInfo(ItApplication.getcurrnUser());
				JSONArray arrays = obj.getJSONArray(NetConst.LIKES);
				list.clear();
				for (int i = 0; i < arrays.length(); i++) {
					CollectionTreasure entity = new CollectionTreasure();
					JSONObject json = arrays.getJSONObject(i);
					entity.treasure_id = json.getLong("treasure_id");
					entity.image = json.getString("image");
					list.add(entity);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (dialog != null) {
					dialog.dismiss();
				}
				isgete = false;
				madapter = new MyLikeAdapter(mActivity,list, itemListner);
				listView.setAdapter(madapter);
				if (ItApplication.getcurrnUser().getUser_type() == 0) {
					tv_authenticate.setText(R.string.type_collect);
					iv_level.setImageResource(levels[currentLevel-1]);
//					iv_level.setImageResource(levels[ItApplication.getcurrnUser().getUser_level()]);
				} else {
					tv_authenticate.setText(R.string.type_identiy);
				}
				pbJingYan.setMax(levelUpNeedNumber);
				pbJingYan.setProgress(levelNumber);
				tvJingyan.setText(levelNumber+"/"+levelUpNeedNumber);

			}
		}

		@Override
		public void onFail(String errorCode, String errorMsg) {
			isgete = false;
			if (errorCode.equals(String.valueOf(NetConst.CODE_ERROR8))) {
				EventBus.getDefault().post(new MainEvent(0, null));
			}
			new ToastUtils(mActivity, errorMsg);
			if (dialog != null) {
				dialog.dismiss();
			}

		}
	};
	private OnClickListener itemListner = new OnClickListener() {

		@Override
		public void onClick(View v) {
			CollectionTreasure entity = (CollectionTreasure) v.getTag();
			Intent mIntent = new Intent(mActivity, ActivityUserDelails.class);
			mIntent.putExtra(Const.ENTITY, entity);
			mActivity.startActivity(mIntent);
			mActivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
		}
	};
}
