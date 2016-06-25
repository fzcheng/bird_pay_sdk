package com.cheyooh.service.sdk.tools;

import java.util.ArrayList;
import java.util.List;

import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.entity.GmiPkg;
import com.cheyooh.service.sdk.db.entity.GmiScreenshot;
import com.cheyooh.service.sdk.idata.ResultGameDetail;
import com.cheyooh.service.sdk.idata.ResultGameInfo;


public class ResultHelper {

	public static ResultGameInfo toResultGame(GmiPkg g) {
		ResultGameInfo game=new ResultGameInfo();
		game.setCategory(g.getCategory());
		game.setIcon_url(getImageUrl(getIconImagePath(g.getPkg())));
		game.setName(g.getName());
		game.setPackage_name(g.getPkg());
		game.setRating(formatStar5(g.getStar()));
		game.setDl_url(g.getDownUrl());
		return game;
	}		
	
	
	/**
	 * 属性值中的换行处理 '\r\n'替换成4个字符
	 * 
	 * @param v
	 * @return
	 */
	private static String escapseXmlPropertyValue(String v){
		if(v==null){
			return v;
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<v.length();i++){
			char c=v.charAt(i);
			if(c=='\r'){
				sb.append("\\r");
			}else if(c=='\n'){
				sb.append("\\n");
			}else{
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	public static int formatStar5(Integer star){
		if(star==null){
			return 0;
		}
		star=star/2;
		
		if(star>5){
			star=5;
		}
		return star;
	}
	
	public static String getImageUrl(String path){	 
		String base_url=Cfg.cfg.getString("image.base_url","http://game.cheyooh.com/images");
		
		String url=path;
		if(!base_url.endsWith("/")){
			base_url+="/";
		}
		
		if(url.startsWith("/")){
			url=url.substring(1);
		}
		url=base_url+url;
			 
		return url;
	}
	
	public static String getSmallImagePath(String pkg,int index){
		return getImagePath(pkg,"small/"+index+".jpg");
	}
	
	public static String getLargeImagePath(String pkg,int index){
		return getImagePath(pkg,"large/"+index+".jpg");
	}
	
	public static String getIconImagePath(String pkg){
		return getImagePath(pkg,"icon.png");
	}
	
	private static String getImagePath(String pkg,String name){
		StringBuilder sb=new StringBuilder();
		String[] sv=pkg.split("\\.");
		for(String s:sv){
			if(sb.length()>0){
				sb.append("/");
			}
			sb.append(s);
		}
		sb.append("/").append(name);
		
		return sb.toString();
	}
	
	public static ResultGameDetail toResultGameDetail(GmiPkg g,List<GmiScreenshot> sss) {
		ResultGameDetail detail=new ResultGameDetail();
		ResultGameDetail.GameDetail gd=new ResultGameDetail.GameDetail();
		gd.setApk_size(g.getSize());
		gd.setDesc(escapseXmlPropertyValue(g.getSummary()));
		gd.setIntro_l_img(g.getImgLargeWh());
		gd.setIntro_s_img(g.getImgSmallWh());
		gd.setVersion(g.getVersion());
		gd.setDl_url(g.getDownUrl());
		gd.setName(g.getName()); 
		gd.setCategory(g.getCategory());
		gd.setRating(formatStar5(g.getStar()));
		gd.setIcon_url(getImageUrl(getIconImagePath(g.getPkg())));
		
		detail.setGame_detail(gd);
		
		List<ResultGameDetail.IntroImage> gis=new ArrayList<ResultGameDetail.IntroImage>();
		for(GmiScreenshot ss:sss){
			ResultGameDetail.IntroImage gi=new ResultGameDetail.IntroImage();
			String path_small=getSmallImagePath(ss.getPkg(),ss.getIdx());
			String path_large=getLargeImagePath(ss.getPkg(),ss.getIdx());
			
			gi.setSmall_url(getImageUrl(path_small));
			gi.setLarge_url(getImageUrl(path_large));
			
			gis.add(gi);
		}
		detail.setImages(gis);
		
		return detail;
	}

}