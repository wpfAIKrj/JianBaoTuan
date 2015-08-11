package com.yingluo.Appraiser.http;

import java.util.List;
import com.yingluo.Appraiser.http.ResponseNewHome.kinds;

public class ResponseMyIdentify extends ResponseRoot{

    private EachIdentify data;

    public EachIdentify getData() {
		return data;
	}

	public void setData(EachIdentify data) {
		this.data = data;
	}

	public class EachIdentify {
    	private int nextPage;
    	private int count;
    	private List<userIdentify> list;
    	
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
		public List<userIdentify> getList() {
			return list;
		}
		public void setList(List<userIdentify> list) {
			this.list = list;
		}
		
    }
	
    public class userIdentify {
    	private String id;
    	private String treasure_id;
    	private String is_authority;
    	private String appraisal_classify_id;
    	private String appraisal_classify_name;
    	private String appraisal_data;
    	private String treasure_user_id;
    	private String treasure_description;
    	private String treasure_images;
    	private List<kinds> kinds;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTreasure_id() {
			return treasure_id;
		}
		public void setTreasure_id(String treasure_id) {
			this.treasure_id = treasure_id;
		}
		public String getIs_authority() {
			return is_authority;
		}
		public void setIs_authority(String is_authority) {
			this.is_authority = is_authority;
		}
		public String getAppraisal_classify_id() {
			return appraisal_classify_id;
		}
		public void setAppraisal_classify_id(String appraisal_classify_id) {
			this.appraisal_classify_id = appraisal_classify_id;
		}
		public String getAppraisal_classify_name() {
			return appraisal_classify_name;
		}
		public void setAppraisal_classify_name(String appraisal_classify_name) {
			this.appraisal_classify_name = appraisal_classify_name;
		}
		public String getAppraisal_data() {
			return appraisal_data;
		}
		public void setAppraisal_data(String appraisal_data) {
			this.appraisal_data = appraisal_data;
		}
		public String getTreasure_user_id() {
			return treasure_user_id;
		}
		public void setTreasure_user_id(String treasure_user_id) {
			this.treasure_user_id = treasure_user_id;
		}
		public String getTreasure_description() {
			return treasure_description;
		}
		public void setTreasure_description(String treasure_description) {
			this.treasure_description = treasure_description;
		}
		public String getTreasure_images() {
			return treasure_images;
		}
		public void setTreasure_images(String treasure_images) {
			this.treasure_images = treasure_images;
		}
		public List<kinds> getKinds() {
			return kinds;
		}
		public void setKinds(List<kinds> kinds) {
			this.kinds = kinds;
		}
    	
    }
    
}

