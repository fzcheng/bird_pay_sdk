package jeecg.ext.online.db.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name="cgform_head",schema="")
public class ExCgFormHeadEntity implements java.io.Serializable{ 
	private static final long serialVersionUID = 2645968429737924285L;

	private String id;
	private String content;
	private String createBy;
	private Date createDate;
	private String createName;
	private String isCheckbox;
	private String isDbsynch;
	private String isPagination;
	private String isTree;
	private int jformType;
	private String jformVersion;
	private String jsPlugIn;
	private String querymode;
	private String sqlPlugIn;
	private String subTableStr;
	private String tableName;
	private String updateBy;
	private Date updateDate;
	private String updateName;
	 
	private int pagesize;
	private String sqlOrder;
	private String sqlWhere;
	
	private String isSystable;
	private int styleWidth;
	private int styleHeight;
	
	private Set<ExCgFormFieldEntity> cgformFields = new HashSet<ExCgFormFieldEntity>(0);

	private ExCgFormDataSource datasource;
	
	public ExCgFormHeadEntity() {
	}

	public ExCgFormHeadEntity(String id, String content, String isCheckbox, String isDbsynch, String isPagination, String isTree, int jformType, String jformVersion,
			String querymode, String tableName) {
		this.id = id;
		this.content = content;
		this.isCheckbox = isCheckbox;
		this.isDbsynch = isDbsynch;
		this.isPagination = isPagination;
		this.isTree = isTree;
		this.jformType = jformType;
		this.jformVersion = jformVersion;
		this.querymode = querymode;
		this.tableName = tableName;
	}

	public ExCgFormHeadEntity(String id, String content, String createBy, Date createDate, String createName, String isCheckbox, String isDbsynch, String isPagination,
			String isTree, int jformType, String jformVersion, String jsPlugIn, String querymode, String sqlPlugIn, String subTableStr, String tableName,
			String updateBy, Date updateDate, String updateName, ExCgFormDataSource datasource, Set<ExCgFormFieldEntity> cgformFields) {
		this.id = id;
		this.content = content;
		this.createBy = createBy;
		this.createDate = createDate;
		this.createName = createName;
		this.isCheckbox = isCheckbox;
		this.isDbsynch = isDbsynch;
		this.isPagination = isPagination;
		this.isTree = isTree;
		this.jformType = jformType;
		this.jformVersion = jformVersion;
		this.jsPlugIn = jsPlugIn;
		this.querymode = querymode;
		this.sqlPlugIn = sqlPlugIn;
		this.subTableStr = subTableStr;
		this.tableName = tableName;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.updateName = updateName;
		this.datasource = datasource;
		this.cgformFields = cgformFields;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "content", nullable = false, length = 64)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "create_by", length = 32)
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "create_name", length = 32)
	public String getCreateName() {
		return this.createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	@Column(name = "is_checkbox", nullable = false, length = 5)
	public String getIsCheckbox() {
		return this.isCheckbox;
	}

	public void setIsCheckbox(String isCheckbox) {
		this.isCheckbox = isCheckbox;
	}

	@Column(name = "is_dbsynch", nullable = false, length = 20)
	public String getIsDbsynch() {
		return this.isDbsynch;
	}

	public void setIsDbsynch(String isDbsynch) {
		this.isDbsynch = isDbsynch;
	}

	@Column(name = "is_pagination", nullable = false, length = 5)
	public String getIsPagination() {
		return this.isPagination;
	}

	public void setIsPagination(String isPagination) {
		this.isPagination = isPagination;
	}

	@Column(name = "is_tree", nullable = false, length = 5)
	public String getIsTree() {
		return this.isTree;
	}

	public void setIsTree(String isTree) {
		this.isTree = isTree;
	}

	@Column(name = "jform_type", nullable = false)
	public int getJformType() {
		return this.jformType;
	}

	public void setJformType(int jformType) {
		this.jformType = jformType;
	}

	@Column(name = "jform_version", nullable = false, length = 10)
	public String getJformVersion() {
		return this.jformVersion;
	}

	public void setJformVersion(String jformVersion) {
		this.jformVersion = jformVersion;
	}

	@Column(name = "js_plug_in")
	public String getJsPlugIn() {
		return this.jsPlugIn;
	}

	public void setJsPlugIn(String jsPlugIn) {
		this.jsPlugIn = jsPlugIn;
	}

	@Column(name = "querymode", nullable = false, length = 10)
	public String getQuerymode() {
		return this.querymode;
	}

	public void setQuerymode(String querymode) {
		this.querymode = querymode;
	}

	@Column(name = "sql_plug_in")
	public String getSqlPlugIn() {
		return this.sqlPlugIn;
	}

	public void setSqlPlugIn(String sqlPlugIn) {
		this.sqlPlugIn = sqlPlugIn;
	}

	@Column(name = "sub_table_str")
	public String getSubTableStr() {
		return this.subTableStr;
	}

	public void setSubTableStr(String subTableStr) {
		this.subTableStr = subTableStr;
	}

	@Column(name = "table_name", unique = true, nullable = false, length = 64)
	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "update_by", length = 32)
	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", length = 19)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "update_name", length = 32)
	public String getUpdateName() {
		return this.updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	 

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cgformHead")
	@BatchSize(size=1000)
	public Set<ExCgFormFieldEntity> getCgformFields() {
		return this.cgformFields;
	}

	public void setCgformFields(Set<ExCgFormFieldEntity> cgformFields) {
		this.cgformFields = cgformFields;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "datasource", nullable = true)
	public ExCgFormDataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(ExCgFormDataSource cgformDatasource) {
		this.datasource = cgformDatasource;
	}
	
	/**
	 * 获取主键字段
	 * @return
	 */
	@Transient
	public  ExCgFormFieldEntity getKeyField(){
		for(ExCgFormFieldEntity f:getCgformFields()){
			if("Y".equalsIgnoreCase(f.getIsKey())){
				return f;
			}
		}
		return null;
	}

	@Column(name = "pagesize")
	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	@Column(name = "sql_order", length = 512, nullable=true)
	public String getSqlOrder() {
		return sqlOrder;
	}

	public void setSqlOrder(String sqlOrder) {
		this.sqlOrder = sqlOrder;
	}

	@Column(name = "sql_where", length = 512, nullable=true)
	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	@Column(name = "is_systable", length = 32, nullable=true)
	public String getIsSystable() {
		return isSystable;
	}

	public void setIsSystable(String isSystable) {
		this.isSystable = isSystable;
	}
	
	@Column(name = "style_width")
	public int getStyleWidth() {
		return styleWidth;
	}

	public void setStyleWidth(int styleWidth) {
		this.styleWidth = styleWidth;
	}
	
	@Column(name = "style_height")
	public int getStyleHeight() {
		return styleHeight;
	}

	public void setStyleHeight(int styleHeight) {
		this.styleHeight = styleHeight;
	}

}
