package com.yingluo.Appraiser.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.config.UrlUtil;
import com.yingluo.Appraiser.utils.SqlDataUtil;

/**
 * @author ytmfdw 获取宝贝分类
 *
 */
public class getAllKind_X_Model extends BaseModel {

	private List<TreasureType> first = null;
	private List<TreasureType> second = null;
	private List<TreasureType> third = null;

	private List<List<TreasureType>> list = null;

	public getAllKind_X_Model() {
		// TODO Auto-generated constructor stub
		httpmodel = HttpMethod.GET;
		url = UrlUtil.getAllKinds();
		StringBuffer sb = new StringBuffer(url);
		if (NetConst.SESSIONID != null) {
			sb.append("?").append(NetConst.SID).append("=")
					.append(NetConst.SESSIONID);
		} else {
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		url = sb.toString();
	}

	public void sendHttp(final CommonCallBack callBack) {
		final HttpUtils httpUtils = new HttpUtils(connTimeout);
		httpUtils.send(httpmodel, url, params, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				onSuccessForString(responseInfo.result);
				callBack.onSuccess();
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				onFailureForString(error.getMessage(), msg);
				callBack.onError();
			}

		});
	}

	@Override
	public void onSuccessForString(String jsonstring) {
		try {
			LogUtils.d(jsonstring);
			JSONObject json = new JSONObject(jsonstring);
			int code = json.getInt(NetConst.CODE);
			String message = json.getString(NetConst.MESSAGE);
			if (code == NetConst.CODE_SUCCESS) {
				analyzeData(jsonstring);

			} else {
				onFailureForString(String.valueOf(code), message);
			}
		} catch (Exception e) {
			// TODO: handle exception
			onFailureForString(HTTP_ERROR, e.getMessage());
		}
	}

	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub

		JSONObject json = new JSONObject(data);
		int type = 0;
		if (json != null) {
			JSONArray jsons = new JSONArray(json.getString(NetConst.DATA));

			dealWork(type, jsons);
		}
	}

	/**
	 * 解析
	 * 
	 * @param type
	 *            几级
	 * @param array
	 *            json数组
	 * @param parent_id
	 *            父类id
	 */
	public void dealWork(int type, JSONArray array) throws Exception {
		for (int i = 0; i < array.length(); i++) {
			JSONObject Obj = array.getJSONObject(i);
			TreasureType Entity = new TreasureType();
			Entity.id = Obj.getLong(NetConst.INFO_ID);
			Entity.name = Obj.getString("classify_name");
			Entity.type = type;
			Entity.parent_id = Obj.getLong("parent_id");
			Entity.imageUrl = Obj.getString("img_url");
			if (Obj.has("children")) {
				Entity.isChild=false;
				SqlDataUtil.getInstance().saveContentType(Entity);
				JSONArray nextJSONs = Obj.getJSONArray("children");
				int nexttype = type + 1;
				dealWork(nexttype, nextJSONs);
			} else {
				Entity.isChild=true;
				SqlDataUtil.getInstance().saveContentType(Entity);
				continue;
			}
		}
	}

	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub

	}

	public List<List<TreasureType>> getResult() {
		if (list == null) {
			list = new ArrayList<List<TreasureType>>();
		}
		return list;
	}

}
