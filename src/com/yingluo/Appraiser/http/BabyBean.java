package com.yingluo.Appraiser.http;

import java.util.List;

import com.yingluo.Appraiser.bean.TreasureEntity;

public class BabyBean{

    private int nextPage;
    private int count;
    
    private List<TreasureEntity> list;

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

	public List<TreasureEntity> getList() {
		return list;
	}

	public void setList(List<TreasureEntity> list) {
		this.list = list;
	}
    
//	public class eachBaby {
//		private String image;
//		private String title;
//		private String status;
//		private String treasure_id;
//		private String user_id;
//		private List<Kind> kinds;
//		
//		public String getImage() {
//			return image;
//		}
//		public void setImage(String image) {
//			this.image = image;
//		}
//		public String getTitle() {
//			return title;
//		}
//		public void setTitle(String title) {
//			this.title = title;
//		}
//		public String getStatus() {
//			return status;
//		}
//		public void setStatus(String status) {
//			this.status = status;
//		}
//		public String getTreasure_id() {
//			return treasure_id;
//		}
//		public void setTreasure_id(String treasure_id) {
//			this.treasure_id = treasure_id;
//		}
//		public String getUser_id() {
//			return user_id;
//		}
//		public void setUser_id(String user_id) {
//			this.user_id = user_id;
//		}
//		public List<Kind> getKinds() {
//			return kinds;
//		}
//		public void setKinds(List<Kind> kinds) {
//			this.kinds = kinds;
//		}
//		
//		public class Kind {
//			private String name;
//			private String id;
//			
//			public String getName() {
//				return name;
//			}
//			public void setName(String name) {
//				this.name = name;
//			}
//			public String getId() {
//				return id;
//			}
//			public void setId(String id) {
//				this.id = id;
//			}
//			
//		}
//	}
    
	
}
