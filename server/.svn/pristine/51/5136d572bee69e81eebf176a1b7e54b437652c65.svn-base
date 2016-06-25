package jeecg.ext.online.db.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cgform_datasource",schema="")
public class ExCgFormDataSource implements java.io.Serializable{
	private static final long serialVersionUID = 2374627970277938324L;
 
	@Id
	@Column(name = "datasource", length = 64)
	private String datasource;
	
	@Column(name="jdbc_driver", length=64)
	private String jdbcDriver;
	
	@Column(name = "jdbc_url", length = 255)
	private String   jdbcUrl;
	
	@Column(name = "jdbc_username", length = 64)
	private String  jdbcUsername;
	 
	
	@Column(name = "jdbc_password", length = 64)
	private String  jdbcPassword;
  
	
	@Column(name = "memo", length = 1024)
	private String memo;
	
	 
	@Column(name = "create_date")
	private Date createDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datasource")
	private Set<ExCgFormHeadEntity> cgformTables = new HashSet<ExCgFormHeadEntity>(0);

	public String getDatasource() {
		return datasource;
	}


	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}


	public String getJdbcUrl() {
		return jdbcUrl;
	}


	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}


	public String getJdbcUsername() {
		return jdbcUsername;
	}


	public void setJdbcUsername(String jdbcUsername) {
		this.jdbcUsername = jdbcUsername;
	}


	public String getJdbcPassword() {
		return jdbcPassword;
	}


	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getJdbcDriver() {
		return jdbcDriver;
	}


	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}


	public Set<ExCgFormHeadEntity> getCgformTables() {
		return cgformTables;
	}


	public void setCgformTables(Set<ExCgFormHeadEntity> cgformTables) {
		this.cgformTables = cgformTables;
	}
}
