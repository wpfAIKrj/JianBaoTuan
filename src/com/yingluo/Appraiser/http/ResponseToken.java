package com.yingluo.Appraiser.http;

public class ResponseToken extends ResponseRoot{

    private TokenBean data;

	public TokenBean getData() {
		return data;
	}

	public void setData(TokenBean data) {
		this.data = data;
	}
    
	public class TokenBean{

	    private String token;

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
	    
	}
}

