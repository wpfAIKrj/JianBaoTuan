package com.yingluo.Appraiser.http;

import java.util.List;

public class ResponseNewHome extends ResponseRoot{

    private EachNewItem data;

	public EachNewItem getData() {
		return data;
	}

	public void setData(EachNewItem data) {
		this.data = data;
	}

    public class EachNewItem {
    	private int nextPage;
    	private int count;
    	private List<HomeItem> list;
    	
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
		public List<HomeItem> getList() {
			return list;
		}
		public void setList(List<HomeItem> list) {
			this.list = list;
		}
    }
	
    public class HomeItem {
    	private String treasure_id;
    	private String user_id;
    	private String user_name;
    	private String user_portrait;
    	private String treasure_description;
    	private String images;
    	private String treasure_classify;
    	private String insert_time;
    	private String currentLevel;
    	private String currentLevelName;
    	private Appraiser appraiser;
    	private List<Record> records;
    	private List<Comment> comments;
    	
		
		public String getTreasure_id() {
			return treasure_id;
		}
		public void setTreasure_id(String treasure_id) {
			this.treasure_id = treasure_id;
		}
		public String getUser_id() {
			return user_id;
		}
		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
		public String getUser_name() {
			return user_name;
		}
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		public String getUser_portrait() {
			return user_portrait;
		}
		public void setUser_portrait(String user_portrait) {
			this.user_portrait = user_portrait;
		}
		public String getTreasure_description() {
			return treasure_description;
		}
		public void setTreasure_description(String treasure_description) {
			this.treasure_description = treasure_description;
		}
		public String getImages() {
			return images;
		}
		public void setImages(String images) {
			this.images = images;
		}
		public String getTreasure_classify() {
			return treasure_classify;
		}
		public void setTreasure_classify(String treasure_classify) {
			this.treasure_classify = treasure_classify;
		}
		public String getInsert_time() {
			return insert_time;
		}
		public void setInsert_time(String insert_time) {
			this.insert_time = insert_time;
		}
		public String getCurrentLevel() {
			return currentLevel;
		}
		public void setCurrentLevel(String currentLevel) {
			this.currentLevel = currentLevel;
		}
		public String getCurrentLevelName() {
			return currentLevelName;
		}
		public void setCurrentLevelName(String currentLevelName) {
			this.currentLevelName = currentLevelName;
		}
		public Appraiser getAppraiser() {
			return appraiser;
		}
		public void setAppraiser(Appraiser appraiser) {
			this.appraiser = appraiser;
		}
		public List<Record> getRecords() {
			return records;
		}
		public void setRecords(List<Record> records) {
			this.records = records;
		}
		public List<Comment> getComments() {
			return comments;
		}
		public void setComments(List<Comment> comments) {
			this.comments = comments;
		}
		
    }
    
    public static class Appraiser {
    	private String id;
    	private String user_id;
    	private String user_name;
    	private String user_portrait;
    	private String user_description;
    	private String appraisal_classify_id;
    	private String appraisal_classify_name;
    	private String appraisal_data;
    	private String currentLevel;
    	private String currentLevelName;
    	private List<kinds> kinds;
    	
    	public Appraiser() {
    		
    	}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUser_id() {
			return user_id;
		}
		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
		public String getUser_name() {
			return user_name;
		}
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		public String getUser_portrait() {
			return user_portrait;
		}
		public void setUser_portrait(String user_portrait) {
			this.user_portrait = user_portrait;
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
		public String getUser_description() {
			return user_description;
		}
		public void setUser_description(String user_description) {
			this.user_description = user_description;
		}
		public String getCurrentLevel() {
			return currentLevel;
		}
		public void setCurrentLevel(String currentLevel) {
			this.currentLevel = currentLevel;
		}
		public String getCurrentLevelName() {
			return currentLevelName;
		}
		public void setCurrentLevelName(String currentLevelName) {
			this.currentLevelName = currentLevelName;
		}
		public List<kinds> getKinds() {
			return kinds;
		}
		public void setKinds(List<kinds> kinds) {
			this.kinds = kinds;
		}
    
    }
    
    public class kinds {
    	private String name;
    	private String id;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
    }
    
    public class Record {
    	private String id;
    	private String user_id;
    	private String user_name;
    	private String appraisal_classify_id;
    	private String appraisal_classify_name;
    	private String appraisal_data;
    	private String insert_time;
    	
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUser_id() {
			return user_id;
		}
		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
		public String getUser_name() {
			return user_name;
		}
		public void setUser_name(String user_name) {
			this.user_name = user_name;
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
		public String getInsert_time() {
			return insert_time;
		}
		public void setInsert_time(String insert_time) {
			this.insert_time = insert_time;
		}
   
    }
    
    public class Comment {
    	private String id;
    	private String user_id;
    	private String treasure_id;
    	private String to_user_id;
    	private String to_user_name;
    	private String comment_data;
    	private String is_checked;
    	private String is_valid;
    	private String insert_time;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUser_id() {
			return user_id;
		}
		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
		public String getTreasure_id() {
			return treasure_id;
		}
		public void setTreasure_id(String treasure_id) {
			this.treasure_id = treasure_id;
		}
		public String getTo_user_id() {
			return to_user_id;
		}
		public void setTo_user_id(String to_user_id) {
			this.to_user_id = to_user_id;
		}
		public String getTo_user_name() {
			return to_user_name;
		}
		public void setTo_user_name(String to_user_name) {
			this.to_user_name = to_user_name;
		}
		public String getComment_data() {
			return comment_data;
		}
		public void setComment_data(String comment_data) {
			this.comment_data = comment_data;
		}
		public String getIs_checked() {
			return is_checked;
		}
		public void setIs_checked(String is_checked) {
			this.is_checked = is_checked;
		}
		public String getIs_valid() {
			return is_valid;
		}
		public void setIs_valid(String is_valid) {
			this.is_valid = is_valid;
		}
		public String getInsert_time() {
			return insert_time;
		}
		public void setInsert_time(String insert_time) {
			this.insert_time = insert_time;
		}
    	
    }
}

