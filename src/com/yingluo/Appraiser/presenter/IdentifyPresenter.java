package com.yingluo.Appraiser.presenter;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.widget.Toast;

import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.inter.OnListDataLoadListener;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.inter.onListView;
import com.yingluo.Appraiser.model.CommonCallBack;
import com.yingluo.Appraiser.model.IdentifyModel;

/**
 * 获取鉴定大厅
 * @author Administrator
 *
 */
public class IdentifyPresenter {
	
	
	private IdentifyModel model = null;
	
	private onListView<CollectionTreasure> mview1;
	

	public IdentifyPresenter(onListView<CollectionTreasure> view1) {
		// TODO Auto-generated constructor stub
		this.mview1=view1;

	}
	
	/**
	 * 获取已经鉴定
	 * @param type 
	 */
	public void getIdentity(long kind_id, int type){
		model=new IdentifyModel();
		model.getIdentity(kind_id,type,new OnListDataLoadListener<CollectionTreasure>() {

			@Override
			public void onListDataLoaded(ArrayList<CollectionTreasure> data) {
				// TODO Auto-generated method stub
				mview1.onSucess(data);
			}

			@Override
			public void onListDataLoadErrorHappened(String errorCode,
					String errorMsg) {
				// TODO Auto-generated method stub
				mview1.onFail(errorCode, errorMsg);
			}
		});
	}
	


	

}
