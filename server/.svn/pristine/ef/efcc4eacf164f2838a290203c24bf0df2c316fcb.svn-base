package jeecg.ext.sdk.entity;

import java.util.ArrayList;
import java.util.List;

import jeecg.system.service.SystemService;

public class SdkGameUpdate {
	
	private Integer upgradeId;
	private Integer forceTag;
	private String channel;
	private String downUrl;
	private Integer versionCode;
	public Integer getUpgradeId() {
		return upgradeId;
	}
	public void setUpgradeId(Integer upgradeId) {
		this.upgradeId = upgradeId;
	}
	public Integer getForceTag() {
		return forceTag;
	}
	public void setForceTag(Integer forceTag) {
		this.forceTag = forceTag;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getDownUrl() {
		return downUrl;
	}
	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}
	public Integer getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}
	
	public static List<SdkGameUpdate> getUpdateList(SystemService systemService,List<SdkUpgrade> list){
		List<SdkGameUpdate> sdkGameUpdates=new ArrayList<SdkGameUpdate>();
		for(SdkUpgrade sdkUpgrade:list){
			SdkGameUpdate sdkGameUpdate=new SdkGameUpdate();
			String channel=sdkUpgrade.getChannel();
			channel=channel.substring(channel.lastIndexOf("_")+1);
			SdkChannel sdkChannel=systemService.findUniqueByProperty(SdkChannel.class, "channelNum", channel);
			if(sdkChannel!=null){
				sdkGameUpdate.setChannel(sdkChannel.getChannelName());
			}
			String dwPath=sdkUpgrade.getDownUrl();
			if(dwPath.lastIndexOf("/")>0){
				dwPath=dwPath.substring(dwPath.lastIndexOf("/")+1);
			}
			sdkGameUpdate.setDownUrl(dwPath);
			sdkGameUpdate.setForceTag(sdkUpgrade.getForceTag());
			sdkGameUpdate.setUpgradeId(sdkUpgrade.getUpgradeId());
			sdkGameUpdate.setVersionCode(sdkUpgrade.getVersionCode());
			sdkGameUpdates.add(sdkGameUpdate);
		}
		
		return sdkGameUpdates;
	}
	
}
