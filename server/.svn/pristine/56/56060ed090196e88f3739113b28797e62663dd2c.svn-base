package jeecg.ext.system.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.entity.IdEntity;

/**
 * TSRoleUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_s_role_user")
public class ExTSRoleUser extends IdEntity implements java.io.Serializable {
	private ExTSUser TSUser;
	private ExTSRole TSRole;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	public ExTSUser getTSUser() {
		return this.TSUser;
	}

	public void setTSUser(ExTSUser TSUser) {
		this.TSUser = TSUser;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roleid")
	public ExTSRole getTSRole() {
		return this.TSRole;
	}

	public void setTSRole(ExTSRole TSRole) {
		this.TSRole = TSRole;
	}

}
