package jeecg.ext.online.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="cgform_field",schema="")
public class ExCgFormFieldEntity implements java.io.Serializable{ 
	private static final long serialVersionUID = 2645968429737924285L;

	

	private String id;
	private ExCgFormHeadEntity cgformHead;
	private String content;
	private String createBy;
	private Date createDate;
	private String createName;
	private String dictField;
	private String dictTable;
	private String fieldHref;
	private Integer fieldLength;
	private String fieldName;
	private String fieldValidType;
	private String isKey;
	private String isNull;
	private String isQuery;
	private String isShow;
	private int length;
	private String mainField;
	private String mainTable;
	private Integer orderNum;
	private Integer pointLength;
	private String queryMode;
	private String showType;
	private String type;
	private String updateBy;
	private Date updateDate;
	private String updateName;

	private String dictFieldLabel;	
	private String formatter;
	private String fieldValue;
	private String queryLike;
	private String file_path;
	@Column(name = "file_path", length = 128)
	
	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public ExCgFormFieldEntity() {
	}

	public ExCgFormFieldEntity(String id, ExCgFormHeadEntity cgformHead, String content, String fieldName, int length, String type) {
		this.id = id;
		this.cgformHead = cgformHead;
		this.content = content;
		this.fieldName = fieldName;
		this.length = length;
		this.type = type;
	}

	public ExCgFormFieldEntity(String id, ExCgFormHeadEntity cgformHead, String content, String createBy, Date createDate, String createName, String dictField,
			String dictTable, String fieldHref, Integer fieldLength, String fieldName, String fieldValidType, String isKey, String isNull, String isQuery,
			String isShow, int length, String mainField, String mainTable, Integer orderNum, Integer pointLength, String queryMode, String showType,
			String type, String updateBy, Date updateDate, String updateName,String dictFieldLabel,String formatter,String fieldValue) {
		this.id = id;
		this.cgformHead = cgformHead;
		this.content = content;
		this.createBy = createBy;
		this.createDate = createDate;
		this.createName = createName;
		this.dictField = dictField;
		this.dictTable = dictTable;
		this.fieldHref = fieldHref;
		this.fieldLength = fieldLength;
		this.fieldName = fieldName;
		this.fieldValidType = fieldValidType;
		this.isKey = isKey;
		this.isNull = isNull;
		this.isQuery = isQuery;
		this.isShow = isShow;
		this.length = length;
		this.mainField = mainField;
		this.mainTable = mainTable;
		this.orderNum = orderNum;
		this.pointLength = pointLength;
		this.queryMode = queryMode;
		this.showType = showType;
		this.type = type;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.updateName = updateName;
		
		this.dictFieldLabel=dictFieldLabel;
		this.formatter=formatter;
		this.fieldValue=fieldValue;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "table_id", nullable = false)
	public ExCgFormHeadEntity getCgformHead() {
		return this.cgformHead;
	}

	public void setCgformHead(ExCgFormHeadEntity cgformHead) {
		this.cgformHead = cgformHead;
	}

	@Column(name = "content", nullable = false, length = 10)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "create_by")
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

	@Column(name = "dict_field", length = 100)
	public String getDictField() {
		return this.dictField;
	}

	public void setDictField(String dictField) {
		this.dictField = dictField;
	}

	@Column(name = "dict_table", length = 100)
	public String getDictTable() {
		return this.dictTable;
	}

	public void setDictTable(String dictTable) {
		this.dictTable = dictTable;
	}

	@Column(name = "field_href", length = 200)
	public String getFieldHref() {
		return this.fieldHref;
	}

	public void setFieldHref(String fieldHref) {
		this.fieldHref = fieldHref;
	}

	@Column(name = "field_length")
	public Integer getFieldLength() {
		return this.fieldLength;
	}

	public void setFieldLength(Integer fieldLength) {
		this.fieldLength = fieldLength;
	}

	@Column(name = "field_name", nullable = false, length = 32)
	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
		 
	}

	@Column(name = "field_valid_type", length = 10)
	public String getFieldValidType() {
		return this.fieldValidType;
	}

	public void setFieldValidType(String fieldValidType) {
		this.fieldValidType = fieldValidType;
	}

	@Column(name = "is_key", length = 2)
	public String getIsKey() {
		return this.isKey;
	}

	public void setIsKey(String isKey) {
		this.isKey = isKey;
	}

	@Column(name = "is_null", length = 5)
	public String getIsNull() {
		return this.isNull;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}

	@Column(name = "is_query", length = 5)
	public String getIsQuery() {
		return this.isQuery;
	}

	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}

	@Column(name = "is_show", length = 5)
	public String getIsShow() {
		return this.isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	@Column(name = "length", nullable = false)
	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Column(name = "main_field", length = 100)
	public String getMainField() {
		return this.mainField;
	}

	public void setMainField(String mainField) {
		this.mainField = mainField;
	}

	@Column(name = "main_table", length = 100)
	public String getMainTable() {
		return this.mainTable;
	}

	public void setMainTable(String mainTable) {
		this.mainTable = mainTable;
	}

	@Column(name = "order_num")
	public Integer getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	@Column(name = "point_length")
	public Integer getPointLength() {
		return this.pointLength;
	}

	public void setPointLength(Integer pointLength) {
		this.pointLength = pointLength;
	}

	@Column(name = "query_mode", length = 10)
	public String getQueryMode() {
		return this.queryMode;
	}

	public void setQueryMode(String queryMode) {
		this.queryMode = queryMode;
	}

	@Column(name = "show_type", length = 10)
	public String getShowType() {
		return this.showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	@Column(name = "type", nullable = false, length = 8)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	@Column(name = "dict_field_label", length = 100)
	public String getDictFieldLabel() {
		return dictFieldLabel;
	}

	public void setDictFieldLabel(String mainFieldValue) {
		this.dictFieldLabel = mainFieldValue;
	}
	
	@Column(name = "formatter", length = 1024)
	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	@Column(name = "field_value", length = 64)
	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String keyType) {
		this.fieldValue = keyType;
	}
	
	@Column(name = "query_like", length = 64)
	public String getQueryLike() {
		return queryLike;
	}

	public void setQueryLike(String queryLike) {
		this.queryLike = queryLike;
	}
}
