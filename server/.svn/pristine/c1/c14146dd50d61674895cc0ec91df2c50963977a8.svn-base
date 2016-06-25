package jeecg.ext.sdk.entity;

import java.util.ArrayList;
import java.util.List;

import jeecg.system.service.SystemService;

public class SdkGameUpgrade {

	private Integer gameId;
	private SdkGameCp sdkGameCp;
	private String name;
	private String packageName;
	private SdkUpgrade sdkUpgrade;
	public Integer getGameId() {
		return gameId;
	}
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
	public SdkGameCp getSdkGameCp() {
		return sdkGameCp;
	}
	public void setSdkGameCp(SdkGameCp sdkGameCp) {
		this.sdkGameCp = sdkGameCp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SdkUpgrade getSdkUpgrade() {
		return sdkUpgrade;
	}
	public void setSdkUpgrade(SdkUpgrade sdkUpgrade) {
		this.sdkUpgrade = sdkUpgrade;
	}
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public  static List<SdkGameUpgrade> getSdkGameUpgradeList(SystemService systemService,List<SdkGame> list){
		List<SdkGameUpgrade> sdkGameUpgrades=new ArrayList<SdkGameUpgrade>();
		for(SdkGame sdkGame:list){
			SdkGameUpgrade sdkGameUpgrade=new SdkGameUpgrade();
			sdkGameUpgrade.setGameId(sdkGame.getGameId());
			sdkGameUpgrade.setName(sdkGame.getName());
			sdkGameUpgrade.setPackageName(sdkGame.getPackageName());
			sdkGameUpgrade.setSdkGameCp(sdkGame.getSdkGameCp());
			SdkUpgrade sdkUpgrade=systemService.findUniqueByProperty(SdkUpgrade.class, "gameId", sdkGame.getGameId());
			sdkGameUpgrade.setSdkUpgrade(sdkUpgrade);
			sdkGameUpgrades.add(sdkGameUpgrade);
		}
		
		return sdkGameUpgrades;
	}
	
	
}
