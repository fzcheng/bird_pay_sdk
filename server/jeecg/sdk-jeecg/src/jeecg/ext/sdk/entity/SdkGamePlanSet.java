package jeecg.ext.sdk.entity;

import java.util.ArrayList;
import java.util.List;

public class SdkGamePlanSet {

	private Integer id;
	
	private String gameName;
	
	private String gamePackageName;
	
	private String cpName;
	
	private String usedPlan;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGamePackageName() {
		return gamePackageName;
	}

	public void setGamePackageName(String gamePackageName) {
		this.gamePackageName = gamePackageName;
	}

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

	public String getUsedPlan() {
		return usedPlan;
	}

	public void setUsedPlan(String usedPlan) {
		this.usedPlan = usedPlan;
	}
	
	public static List<SdkGamePlanSet> getUsedList(List<SdkGame> list){
		List<SdkGamePlanSet> result=new ArrayList<SdkGamePlanSet>();
		for(SdkGame sdkGame:list){
			SdkGamePlanSet sdkGamePlanSet=new SdkGamePlanSet();
			sdkGamePlanSet.setId(sdkGame.getGameId());
			sdkGamePlanSet.setGameName(sdkGame.getName());
			sdkGamePlanSet.setGamePackageName(sdkGame.getPackageName());
			sdkGamePlanSet.setCpName(sdkGame.getSdkGameCp().getName());
			if(sdkGame.getSdkGameUsedPlans()!=null && sdkGame.getSdkGameUsedPlans().size()>0){
				List<SdkGameUsedPlan> sdkGames=new ArrayList<SdkGameUsedPlan>(sdkGame.getSdkGameUsedPlans());
				sdkGamePlanSet.setUsedPlan(sdkGames.get(0).getSdkPlan().getName());
			}else{
				sdkGamePlanSet.setUsedPlan("未配置");
			}
			result.add(sdkGamePlanSet);
		}
		return result;
	}
	
}
