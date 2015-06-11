package com.yingluo.Appraiser.inter;
/**
 * RecyclerView上拉下拉的回调接口
 * @author Administrator
 *
 */
public interface ListviewLoadListener {
	/**
	 * 下拉刷新
	 */
	public void onRefresh();

	/**
	 * 上啦加载更多
	 */
	public void onLoadMore();
}
