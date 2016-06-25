package com.cheyooh.service.sdk.idata;

import java.util.List;


import com.cheyooh.service.framework.idata.ResultContent;
import com.cheyooh.service.framework.utils.annotation.Transfer;


public class ResultGameDetail extends ResultContent{
	  
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -3622978401735194488L;

	private GameDetail game_detail;
	
	private List<IntroImage> images;

	public String name(){
		return null;
	}
	
	public GameDetail getGame_detail() {
		return game_detail;
	}

	public void setGame_detail(GameDetail detail) {
		this.game_detail = detail;
	}

	public List<IntroImage> getImages() {
		return images;
	}

	public void setImages(List<IntroImage> images) {
		this.images = images;
	}
	
	@Transfer(name="intro_image")
	public static class IntroImage extends ResultContent{ 
	
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 7612950839679142332L;
		private String small_url;
	    private String large_url;
	              
		public IntroImage(){
			 
		}

		public String getSmall_url() {
			return small_url;
		}

		public void setSmall_url(String small_url) {
			this.small_url = small_url;
		}

		public String getLarge_url() {
			return large_url;
		}

		public void setLarge_url(String large_url) {
			this.large_url = large_url;
		}
		
	}
	
	public static class GameDetail extends ResultContent{
		 
		private static final long serialVersionUID = 3900946225005058073L;

		public GameDetail(){
			super("game_detail");
		}
		
		/**
		 * "10.3M"
		 */
		private String apk_size;
	            
		private String version;
		
		
		/**
		 * 下载地址
		 */
		private String dl_url;
		
	    private String desc;
	    
	    /**
	     * "160X240"
	     */
	    private String intro_s_img;
	    
	    /**
	     * "320X480"
	     */
	    private String intro_l_img;

	    
	    /**
	     * name,icon_url,rating,category属性从1.1版本添加
	     */
	    private String name;
	    private String icon_url;
	    private int rating;
	    private String category;
	    
		public String getApk_size() {
			return apk_size;
		}

		public void setApk_size(String apk_size) {
			this.apk_size = apk_size;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getIntro_s_img() {
			return intro_s_img;
		}

		public void setIntro_s_img(String intro_s_img) {
			this.intro_s_img = intro_s_img;
		}

		public String getIntro_l_img() {
			return intro_l_img;
		}

		public void setIntro_l_img(String intro_l_img) {
			this.intro_l_img = intro_l_img;
		}

		public String getDl_url() {
			return dl_url;
		}

		public void setDl_url(String dl_url) {
			this.dl_url = dl_url;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getIcon_url() {
			return icon_url;
		}

		public void setIcon_url(String icon_url) {
			this.icon_url = icon_url;
		}

		public int getRating() {
			return rating;
		}

		public void setRating(int rating) {
			this.rating = rating;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}
	}

	 
}
