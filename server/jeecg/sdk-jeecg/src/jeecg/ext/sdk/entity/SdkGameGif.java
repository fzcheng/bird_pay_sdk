package jeecg.ext.sdk.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hp.hpl.sparta.xpath.ThisNodeTest;

import jeecg.system.service.SystemService;

public class SdkGameGif {

	private Integer id;
	
	private String title;
	
	private SdkGame sdkGame;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

 
	
	
	public SdkGame getSdkGame() {
		return sdkGame;
	}

	public void setSdkGame(SdkGame sdkGame) {
		this.sdkGame = sdkGame;
	}
	

	public  List<SdkGameGif> getSdkGameGifList(SystemService systemService,List<SdkGift> list){
		List<SdkGameGif> result=new ArrayList<SdkGameGif>();
		for(SdkGift sdkGift:list){
			SdkGameGif sdkGameGif=new SdkGameGif();
			sdkGameGif.setId(sdkGift.getGiftId());
			SdkGame sdkGame=systemService.getEntity(SdkGame.class, sdkGift.getGameId());
			sdkGameGif.setSdkGame(sdkGame);
			sdkGameGif.setTitle(sdkGift.getTitle());
			result.add(sdkGameGif);
		}
		
		return result;
	}
	 
}
