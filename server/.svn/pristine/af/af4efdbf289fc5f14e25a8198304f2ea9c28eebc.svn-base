package com.cheyooh.service.dal.diagnosis;



import java.text.SimpleDateFormat;
import java.util.Date;

public class SqlLog {
	
    /**<br/>
     * 字段: misc_sql_log.app<br/>
     * 可空: false<br/>
     * 缺省: mybatis<br/>
     * 长度: 64<br/>
     * 说明: 应用程序
     */
    private String app;

    /**<br/>
     * 字段: misc_sql_log.trace_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 请求跟踪ID
     */
    private String traceId;

    /**<br/>
     * 字段: misc_sql_log.start_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 开始时间
     */
    private Date startTime;

    /**<br/>
     * 字段: misc_sql_log.end_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 说明: 结束时间
     */
    private Date endTime;

    /**<br/>
     * 字段: misc_sql_log.statement<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * 说明: 语句ID
     */
    private String statement;

    /**<br/>
     * 字段: misc_sql_log.time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 说明: 执行时间(毫秒)
     */
    private Integer time;

    /**<br/>
     * 字段: misc_sql_log.sql<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 65535<br/>
     * 说明: SQL语句
     */
    private String sql;

   
    /**
     * @return misc_sql_log.app: 应用程序
     */
    public String getApp() {
        return app;
    }

    /**<br/>
     * 字段: misc_sql_log.app<br/>
     * 可空: false<br/>
     * 缺省: mybatis<br/>
     * 长度: 64<br/>
     * @param app: 应用程序
     */
    public void setApp(String app) {
        this.app = app;
    }

    /**
     * @return misc_sql_log.trace_id: 请求跟踪ID
     */
    public String getTraceId() {
        return traceId;
    }

    /**<br/>
     * 字段: misc_sql_log.trace_id<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * @param traceId: 请求跟踪ID
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    /**
     * @return misc_sql_log.start_time: 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**<br/>
     * 字段: misc_sql_log.start_time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param startTime: 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return misc_sql_log.end_time: 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**<br/>
     * 字段: misc_sql_log.end_time<br/>
     * 可空: true<br/>
     * 缺省: <br/>
     * @param endTime: 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return misc_sql_log.statement: 语句ID
     */
    public String getStatement() {
        return statement;
    }

    /**<br/>
     * 字段: misc_sql_log.statement<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 128<br/>
     * @param statement: 语句ID
     */
    public void setStatement(String statement) {
        this.statement = statement;
    }

    /**
     * @return misc_sql_log.time: 执行时间(毫秒)
     */
    public Integer getTime() {
        return time;
    }

    /**<br/>
     * 字段: misc_sql_log.time<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * @param time: 执行时间(毫秒)
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * @return misc_sql_log.sql: SQL语句
     */
    public String getSql() {
        return sql;
    }

    /**<br/>
     * 字段: misc_sql_log.sql<br/>
     * 可空: false<br/>
     * 缺省: <br/>
     * 长度: 65535<br/>
     * @param sql: SQL语句
     */
    public void setSql(String sql) {
        this.sql = sql;
    }
    
    public String toString(){
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
    	StringBuilder sb=new StringBuilder();
    	sb.append("traceid: ").append(this.traceId);
    	sb.append(", app: ").append(this.app);
    	sb.append(", startTime: ").append(startTime==null?"":sdf.format(startTime));
    	sb.append(", endTime: ").append(endTime==null?"":sdf.format(endTime));
    	sb.append(", useTime: ").append(this.time);
    	sb.append(", statement: ").append(this.statement);
    	sb.append(", sql: ").append(this.sql);
    	
    	return sb.toString();
    }
}
