package jeecg.ext.online.db;

public class DBFieldInfo {
	/**
	 * 是否自动增长主键
	 */
	private boolean generatedKeys = false;

	private String fieldName;

	private boolean nullable;
	
	private String defaultValue;
	
	private String comment;
	
	private long maxLength;
	
	private int  idx;
	
	private String dataType;
	
	private boolean isPrimaryKey;

	public DBFieldInfo(String fieldKey, boolean nullable, String defaultValue
			, String comment, long maxLength, boolean isAutoIncrement
			,int idx,String dataType,boolean isPrimaryKey) {
		super();
		
		this.fieldName = fieldKey;
		this.nullable = nullable;
		this.defaultValue = defaultValue;
		this.comment = comment;
		this.maxLength = maxLength;

		this.generatedKeys = isAutoIncrement;
		
		this.idx=idx;
		this.dataType=dataType;
		this.isPrimaryKey=isPrimaryKey;
	}

	public String geFieldName() {
		return fieldName;		
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldKey) {
		this.fieldName = fieldKey;
	}

	public long getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(long maxLength) {
		this.maxLength = maxLength;
	}

	public boolean isGeneratedKeys() {
		return generatedKeys;
	}

	public void setGeneratedKeys(boolean generatedKeys) {
		this.generatedKeys = generatedKeys;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
}