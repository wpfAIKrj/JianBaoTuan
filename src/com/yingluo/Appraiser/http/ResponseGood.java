package com.yingluo.Appraiser.http;

import java.util.List;

import com.yingluo.Appraiser.bean.ContentInfo;

public class ResponseGood extends ResponseRoot{
	
    private CollecionBean data;

	public class CollecionBean {
		private int nextPage;
		private int count;
		
		private List<ContentInfo> list;
		
		public int getNextPage() {
			return nextPage;
		}
		public void setNextPage(int nextPage) {
			this.nextPage = nextPage;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public List<ContentInfo> getList() {
			return list;
		}
		public void setList(List<ContentInfo> list) {
			this.list = list;
		}
		
	}

	public CollecionBean getData() {
		return data;
	}

	public void setData(CollecionBean data) {
		this.data = data;
	}
	
}

