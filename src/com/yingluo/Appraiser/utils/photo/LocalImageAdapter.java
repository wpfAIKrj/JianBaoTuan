package com.yingluo.Appraiser.utils.photo;

import java.util.ArrayList;
import java.util.List;




import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.utils.BitmapsUtils;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 本地图片加载
 * @author caixiang
 * @create 2015/4/17 10:28
 */
@SuppressLint("InflateParams")
public class LocalImageAdapter extends BaseAdapter {
	private List<String> imageList ;

	private AlbumActivity activity ;
	private CheckChangeL checkChangeL ;
	public  ArrayList<String> selectList;
	public LocalImageAdapter(AlbumActivity activity, List<String> list,ArrayList<String> selectList){
		this.activity = activity ;
		this.imageList = list ;
		this.selectList=selectList;
	
	}
	
	public void forceToNotiftyData(int index, List<String> list){
		this.imageList = list ;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return imageList.size();
	}

	@Override
	public String getItem(int position) {
		return imageList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	private boolean isChecked(String path){
		boolean result = false ;
		if(selectList.contains(path))
			result = true ;
		
		return result;
	}
	
	private void deleteItem(String path){
		if(selectList.contains(path))
			selectList.remove(path) ;
	}
	
	private void addItem(String path){
		selectList.add(path) ;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = null ;
		view = LayoutInflater.from(activity).inflate(
				R.layout.image_cell_layout, null) ;
		ImageView imageView = (ImageView) view.findViewById(
				R.id.image_cell_image) ;
		final CheckBox checkView = (CheckBox) view.findViewById(
				R.id.image_cell_check) ;
		
//		view.setTag(imageList.get(position));
		imageView.setImageBitmap(null);
		BitmapsUtils.getInstance().display(imageView, getItem(position));
		//初始图片选中状态
		checkView.setChecked(isChecked(getItem(position)));
		
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean ischecked = checkView.isChecked() ;
				if(ischecked){
					deleteItem(getItem(position));
					checkView.setChecked(!ischecked);
					checkChangeL.onChange();
				}
				else{
					if(selectList.size()<3){
						addItem(getItem(position));	
						checkView.setChecked(!ischecked);
						checkChangeL.onChange();
					}else{
						Toast.makeText(activity, R.string.help_msg_16, Toast.LENGTH_SHORT).show();
					}
				}
				
			}
		});
		
		return view;
	}
	
	public void SetCheckChangeL(CheckChangeL l){
		checkChangeL = l ;
	}
	
	public interface CheckChangeL {
		void onChange() ;
	}
	
}
