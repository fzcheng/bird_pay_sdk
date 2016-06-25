package jeecg.system.pojo.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.jeecgframework.core.common.entity.IdEntity;

/**
 * 角色表
 */
@Entity
@Table(name = "ext_exclude_urls")
public class ExtExcludeUrls extends IdEntity implements java.io.Serializable{

	private String excludeUrls;
	
	private String memo;
	
	private Integer enable;

	@Column(name = "exclude_urls", length = 255)
	public String getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(String excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	@Column(name = "memo", length = 255)
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "enable", length = 4)
	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	
	
}
