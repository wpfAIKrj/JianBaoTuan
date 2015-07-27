package com.yingluo.Appraiser.http;

import java.util.List;

public class ResponseIsDel extends ResponseRoot{

    private List<String> data;

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}
    
}
