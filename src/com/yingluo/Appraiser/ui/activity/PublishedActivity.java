package com.yingluo.Appraiser.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.R.array;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.ui.dialog.SelectPhotoDialog;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.FileUtils;
import com.yingluo.Appraiser.utils.ImageUtils;
import com.yingluo.Appraiser.utils.NetUtils;
import com.yingluo.Appraiser.utils.SqlDataUtil;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.utils.photo.AlbumActivity;
import com.yingluo.Appraiser.view.TagLinearLayout;

/**
 * 发布藏品
 * 
 * @author Administrator
 *
 */
public class PublishedActivity extends BaseActivity {

	private SelectPhotoDialog dialog;

	@ViewInject(R.id.published_bt)
	private ImageView tv_munu;

	@ViewInject(R.id.tag_layout)
	private TagLinearLayout taglayout;

	@ViewInject(R.id.imageView01)
	private ImageView iv1;
	@ViewInject(R.id.imageView02)
	private ImageView iv2;
	@ViewInject(R.id.imageView03)
	private ImageView iv3;
	@ViewInject(R.id.imageView04)
	private ImageView iv4;
	@ViewInject(R.id.imageView05)
	private ImageView iv5;
	@ViewInject(R.id.imageView06)
	private ImageView iv6;

	@ViewInject(R.id.home_title)
	private TextView title;

	private int getPhoto = -1;// 没有选择图片时为-1
	private ArrayList<String> selectListAll = new ArrayList<String>();// 全景图片路径
	private ArrayList<String> selectListTest = new ArrayList<String>();// 特写图片路径

	private ImageUtils imageUtils;

	private TreasureType type = null;// 宝贝分类

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_published);
		ViewUtils.inject(this);
		initView();
	}

	private void initView() {
		imageUtils = new ImageUtils(this);
		title.setText(R.string.publish_title);

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.hold, R.anim.toast_out);
	}
	
	@OnClick({ R.id.btn_back, R.id.published_bt, R.id.imageView01, R.id.imageView02, R.id.imageView03, R.id.imageView04,
			R.id.imageView05, R.id.imageView06, R.id.bt_next })
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btn_back:
			// 返回上层
			for (String path : selectListAll) {
				if (path != null && !path.isEmpty()) {
					FileUtils.getInstance().deleteFile(path);
				}
			}
			for (String path : selectListTest) {
				if (path != null && !path.isEmpty()) {
					FileUtils.getInstance().deleteFile(path);
				}
			}
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.hold, R.anim.toast_out);
			break;
		case R.id.published_bt:
			// 跳转到搜索
			intent = new Intent(PublishedActivity.this, KindOfPreciousActivity.class);
			intent.putExtra(Const.SHOW_TYPE, 1);
			startActivityForResult(intent, Const.TO_PUBLISH_SELECT_TYPE);
			overridePendingTransition(R.anim.left_in, R.anim.left_out);
			break;
		case R.id.imageView01:
			showGetPhotoDialog(0);
			break;
		case R.id.imageView02:
			showGetPhotoDialog(1);
			break;
		case R.id.imageView03:
			showGetPhotoDialog(2);
			break;
		case R.id.imageView04:
			showGetPhotoDialog(3);
			break;
		case R.id.imageView05:
			showGetPhotoDialog(4);
			break;
		case R.id.imageView06:
			showGetPhotoDialog(5);
			break;
		case R.id.bt_next:// 下一步
			if (type != null) {

				if (selectListAll.size() == 0) {
					new ToastUtils(this, R.string.help_msg_14);
					return;
				}

				if (selectListTest.size() == 0) {
					new ToastUtils(this, R.string.help_msg_15);
					return;
				}
				intent = new Intent(PublishedActivity.this, PublishedNextActivity.class);
				intent.putExtra(Const.IMAGEPATH_PANORAMIC, selectListAll);
				intent.putExtra(Const.IMAGEPATH_FEATURE, selectListTest);
				intent.putExtra(Const.KIND_ID, type);
				startActivityForResult(intent, Const.TO_IDENTY_NEXT);
			} else {
				new ToastUtils(this, R.string.help_msg_12);
			}
			break;
		default:
			break;
		}
	}

	// 制定那个imageview获取图片
	private void showGetPhotoDialog(int number) {
		// TODO Auto-generated method stub
		getPhoto = number;
		if (dialog == null) {
			dialog = new SelectPhotoDialog(this, ImageListner);
		}
		if (!dialog.isShowing()) {
			dialog.show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == Const.TO_PUBLISH_SELECT_TYPE) {// 选择宝物
			if (resultCode == RESULT_OK) {
				int kind_id = data.getIntExtra(Const.KIND_ID, 0);
				LogUtils.d("选择宝物的id" + kind_id);
				if (kind_id == 0) {
					type = null;
				} else {
					type = SqlDataUtil.getInstance().getTreasureTypeById(kind_id);
					if (type != null) {
						taglayout.addTag(type);
					}
				}
			}

		}
		if (requestCode == Const.TO_IDENTY_NEXT) {
			if (resultCode == RESULT_OK) {// 鉴定发布成功，返回主页面
				setResult(RESULT_OK, getIntent());
				finish();
				overridePendingTransition(R.anim.right_in, R.anim.right_out);
			}
			if (resultCode == (-2)) {// 鉴赏失败
				new ToastUtils(PublishedActivity.this, "发布鉴定失败！");
			}
		}
		if (requestCode == ImageUtils.GET_IMAGE_BY_CAMERA && resultCode == RESULT_OK) {// 我的页面，获取照片地址获取到图片（相机）
			if (imageUtils.PICPATH != null) {
				if (getPhoto < 3) {// 全景
					selectListAll.add(imageUtils.PICPATH);
					// 更新媒体库
					Uri url = Uri.parse("file://" + imageUtils.PICPATH);
					Log.e("show image", url+"");
					sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, url));
					showAllImage();
				} else {// 特写
					selectListTest.add(imageUtils.PICPATH);
					// 更新媒体库
					Uri url = Uri.parse("file://" + imageUtils.PICPATH);
					Log.e("show image", url+"");
					sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, url));
					showTestImage();
				}
				getPhoto = -1;
			}
		}

		if (requestCode == Const.TO_SELECT_ALBUM) {
			if (resultCode == RESULT_OK) {
				int type = data.getIntExtra(Const.SELECT_ALBUM_TYPE, 0);
				if (type == 0) {
					selectListAll = data.getStringArrayListExtra(Const.SELECT_LIST);
					for(String each : selectListTest){
						if(selectListAll.contains(each)) {
							selectListAll.remove(each);
						}
					}
					showAllImage();
				} else {
					selectListTest = data.getStringArrayListExtra(Const.SELECT_LIST);
					for(String each : selectListAll){
						if(selectListTest.contains(each)) {
							selectListTest.remove(each);
						}
					}
					showTestImage();
				}
			}
			if (resultCode == RESULT_CANCELED) {

			}
		}

		dismissGetPhotoDialog();
	}

	// 显示特写图片
	private void showTestImage() {
		if (selectListTest.size() == 0) {
			iv4.setImageResource(R.drawable.add_image_bg);
			iv5.setImageResource(R.drawable.add_image_bg);
			iv6.setImageResource(R.drawable.add_image_bg);
		} else if (selectListTest.size() == 1) {
			BitmapsUtils.getInstance().display(iv4, selectListTest.get(0));
			iv5.setImageResource(R.drawable.add_image_bg);
			iv6.setImageResource(R.drawable.add_image_bg);
		} else if (selectListTest.size() == 2) {
			BitmapsUtils.getInstance().display(iv4, selectListTest.get(0));
			BitmapsUtils.getInstance().display(iv5, selectListTest.get(1));
			iv6.setImageResource(R.drawable.add_image_bg);
		} else if (selectListTest.size() == 3) {
			BitmapsUtils.getInstance().display(iv4, selectListTest.get(0));
			BitmapsUtils.getInstance().display(iv5, selectListTest.get(1));
			BitmapsUtils.getInstance().display(iv6, selectListTest.get(2));
		}
	}

	// 显示全景图片
	private void showAllImage() {
		// TODO 自动生成的方法存根
		if (selectListAll.size() == 0) {
			iv1.setImageResource(R.drawable.add_image_bg);
			iv2.setImageResource(R.drawable.add_image_bg);
			iv3.setImageResource(R.drawable.add_image_bg);
		} else if (selectListAll.size() == 1) {
			BitmapsUtils.getInstance().display(iv1, selectListAll.get(0));
			iv2.setImageResource(R.drawable.add_image_bg);
			iv3.setImageResource(R.drawable.add_image_bg);
		} else if (selectListAll.size() == 2) {
			BitmapsUtils.getInstance().display(iv1, selectListAll.get(0));
			BitmapsUtils.getInstance().display(iv2, selectListAll.get(1));
			iv3.setImageResource(R.drawable.add_image_bg);
		} else if (selectListAll.size() == 3) {
			BitmapsUtils.getInstance().display(iv1, selectListAll.get(0));
			BitmapsUtils.getInstance().display(iv2, selectListAll.get(1));
			BitmapsUtils.getInstance().display(iv3, selectListAll.get(2));
		}
	}

	private void dismissGetPhotoDialog() {
		// TODO Auto-generated method stub
		getPhoto = -1;
		if (dialog != null) {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
		}
	}

	private OnClickListener ImageListner = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = null;
			switch (v.getId()) {
			case R.id.btn_take_photo:// 相机获取
				imageUtils.openCameraImage();
				break;
			case R.id.btn_pick_photo:// 相册获取
				intent = new Intent(PublishedActivity.this, AlbumActivity.class);
				if (getPhoto < 3) {// 全景
					intent.putExtra(Const.SELECT_ALBUM_TYPE, 0);
					
				} else {// 特写
					intent.putExtra(Const.SELECT_ALBUM_TYPE, 1);
					intent.putExtra(Const.SELECT_LIST, selectListTest);
				}
				ArrayList<String> select = new ArrayList<String>();
				select.addAll(selectListAll);
				select.addAll(selectListTest);
				intent.putExtra(Const.SELECT_LIST, select);
				startActivityForResult(intent, Const.TO_SELECT_ALBUM);

				break;
			case R.id.btn_cancel:// 取消

				break;
			default:
				break;
			}
			if (dialog != null) {
				dialog.dismiss();
			}
		}
	};
}
