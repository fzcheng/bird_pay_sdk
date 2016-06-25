package com.cheyooh.service.sdk.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SdkInformationExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sdk_information
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sdk_information
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sdk_information
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_information
     *
     * @mbggenerated
     */
    public SdkInformationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_information
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_information
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_information
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_information
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_information
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_information
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_information
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_information
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_information
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_information
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sdk_information
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andInfoIdIsNull() {
            addCriterion("info_id is null");
            return (Criteria) this;
        }

        public Criteria andInfoIdIsNotNull() {
            addCriterion("info_id is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯ID(自增主键)
        */
        public Criteria andInfoIdEqualTo(Integer value) {
            addCriterion("info_id =", value, "infoId");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯ID(自增主键)
        */
        public Criteria andInfoIdNotEqualTo(Integer value) {
            addCriterion("info_id <>", value, "infoId");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯ID(自增主键)
        */
        public Criteria andInfoIdGreaterThan(Integer value) {
            addCriterion("info_id >", value, "infoId");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯ID(自增主键)
        */
        public Criteria andInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("info_id >=", value, "infoId");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯ID(自增主键)
        */
        public Criteria andInfoIdLessThan(Integer value) {
            addCriterion("info_id <", value, "infoId");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯ID(自增主键)
        */
        public Criteria andInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("info_id <=", value, "infoId");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯ID(自增主键)
        */
        public Criteria andInfoIdIn(List<Integer> values) {
            addCriterion("info_id in", values, "infoId");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯ID(自增主键)
        */
        public Criteria andInfoIdNotIn(List<Integer> values) {
            addCriterion("info_id not in", values, "infoId");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯ID(自增主键)
        */
        public Criteria andInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("info_id between", value1, value2, "infoId");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯ID(自增主键)
        */
        public Criteria andInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("info_id not between", value1, value2, "infoId");
            return (Criteria) this;
        }

        public Criteria andGameIdIsNull() {
            addCriterion("game_id is null");
            return (Criteria) this;
        }

        public Criteria andGameIdIsNotNull() {
            addCriterion("game_id is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 游戏ID
        */
        public Criteria andGameIdEqualTo(Integer value) {
            addCriterion("game_id =", value, "gameId");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 游戏ID
        */
        public Criteria andGameIdNotEqualTo(Integer value) {
            addCriterion("game_id <>", value, "gameId");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 游戏ID
        */
        public Criteria andGameIdGreaterThan(Integer value) {
            addCriterion("game_id >", value, "gameId");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 游戏ID
        */
        public Criteria andGameIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("game_id >=", value, "gameId");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 游戏ID
        */
        public Criteria andGameIdLessThan(Integer value) {
            addCriterion("game_id <", value, "gameId");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 游戏ID
        */
        public Criteria andGameIdLessThanOrEqualTo(Integer value) {
            addCriterion("game_id <=", value, "gameId");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 游戏ID
        */
        public Criteria andGameIdIn(List<Integer> values) {
            addCriterion("game_id in", values, "gameId");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 游戏ID
        */
        public Criteria andGameIdNotIn(List<Integer> values) {
            addCriterion("game_id not in", values, "gameId");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 游戏ID
        */
        public Criteria andGameIdBetween(Integer value1, Integer value2) {
            addCriterion("game_id between", value1, value2, "gameId");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 游戏ID
        */
        public Criteria andGameIdNotBetween(Integer value1, Integer value2) {
            addCriterion("game_id not between", value1, value2, "gameId");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯标题
        */
        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯标题
        */
        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯标题
        */
        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯标题
        */
        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯标题
        */
        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯标题
        */
        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯标题
        */
        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯标题
        */
        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯标题
        */
        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯标题
        */
        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯标题
        */
        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯标题
        */
        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("`type` is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("`type` is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯类型(新闻/攻略等)
        */
        public Criteria andTypeEqualTo(String value) {
            addCriterion("`type` =", value, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯类型(新闻/攻略等)
        */
        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("`type` <>", value, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯类型(新闻/攻略等)
        */
        public Criteria andTypeGreaterThan(String value) {
            addCriterion("`type` >", value, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯类型(新闻/攻略等)
        */
        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`type` >=", value, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯类型(新闻/攻略等)
        */
        public Criteria andTypeLessThan(String value) {
            addCriterion("`type` <", value, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯类型(新闻/攻略等)
        */
        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("`type` <=", value, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯类型(新闻/攻略等)
        */
        public Criteria andTypeLike(String value) {
            addCriterion("`type` like", value, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯类型(新闻/攻略等)
        */
        public Criteria andTypeNotLike(String value) {
            addCriterion("`type` not like", value, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯类型(新闻/攻略等)
        */
        public Criteria andTypeIn(List<String> values) {
            addCriterion("`type` in", values, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯类型(新闻/攻略等)
        */
        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("`type` not in", values, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯类型(新闻/攻略等)
        */
        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("`type` between", value1, value2, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯类型(新闻/攻略等)
        */
        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("`type` not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andDetailIsNull() {
            addCriterion("detail is null");
            return (Criteria) this;
        }

        public Criteria andDetailIsNotNull() {
            addCriterion("detail is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯简介
        */
        public Criteria andDetailEqualTo(String value) {
            addCriterion("detail =", value, "detail");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯简介
        */
        public Criteria andDetailNotEqualTo(String value) {
            addCriterion("detail <>", value, "detail");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯简介
        */
        public Criteria andDetailGreaterThan(String value) {
            addCriterion("detail >", value, "detail");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯简介
        */
        public Criteria andDetailGreaterThanOrEqualTo(String value) {
            addCriterion("detail >=", value, "detail");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯简介
        */
        public Criteria andDetailLessThan(String value) {
            addCriterion("detail <", value, "detail");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯简介
        */
        public Criteria andDetailLessThanOrEqualTo(String value) {
            addCriterion("detail <=", value, "detail");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯简介
        */
        public Criteria andDetailLike(String value) {
            addCriterion("detail like", value, "detail");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯简介
        */
        public Criteria andDetailNotLike(String value) {
            addCriterion("detail not like", value, "detail");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯简介
        */
        public Criteria andDetailIn(List<String> values) {
            addCriterion("detail in", values, "detail");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯简介
        */
        public Criteria andDetailNotIn(List<String> values) {
            addCriterion("detail not in", values, "detail");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯简介
        */
        public Criteria andDetailBetween(String value1, String value2) {
            addCriterion("detail between", value1, value2, "detail");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯简介
        */
        public Criteria andDetailNotBetween(String value1, String value2) {
            addCriterion("detail not between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andWeburlIsNull() {
            addCriterion("weburl is null");
            return (Criteria) this;
        }

        public Criteria andWeburlIsNotNull() {
            addCriterion("weburl is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯链接
        */
        public Criteria andWeburlEqualTo(String value) {
            addCriterion("weburl =", value, "weburl");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯链接
        */
        public Criteria andWeburlNotEqualTo(String value) {
            addCriterion("weburl <>", value, "weburl");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯链接
        */
        public Criteria andWeburlGreaterThan(String value) {
            addCriterion("weburl >", value, "weburl");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯链接
        */
        public Criteria andWeburlGreaterThanOrEqualTo(String value) {
            addCriterion("weburl >=", value, "weburl");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯链接
        */
        public Criteria andWeburlLessThan(String value) {
            addCriterion("weburl <", value, "weburl");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯链接
        */
        public Criteria andWeburlLessThanOrEqualTo(String value) {
            addCriterion("weburl <=", value, "weburl");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯链接
        */
        public Criteria andWeburlLike(String value) {
            addCriterion("weburl like", value, "weburl");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯链接
        */
        public Criteria andWeburlNotLike(String value) {
            addCriterion("weburl not like", value, "weburl");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯链接
        */
        public Criteria andWeburlIn(List<String> values) {
            addCriterion("weburl in", values, "weburl");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯链接
        */
        public Criteria andWeburlNotIn(List<String> values) {
            addCriterion("weburl not in", values, "weburl");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯链接
        */
        public Criteria andWeburlBetween(String value1, String value2) {
            addCriterion("weburl between", value1, value2, "weburl");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯链接
        */
        public Criteria andWeburlNotBetween(String value1, String value2) {
            addCriterion("weburl not between", value1, value2, "weburl");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯时间(倒序排列)
        */
        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯时间(倒序排列)
        */
        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯时间(倒序排列)
        */
        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯时间(倒序排列)
        */
        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯时间(倒序排列)
        */
        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯时间(倒序排列)
        */
        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯时间(倒序排列)
        */
        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯时间(倒序排列)
        */
        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯时间(倒序排列)
        */
        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 资讯时间(倒序排列)
        */
        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 状态(1-有效,0-无效)
        */
        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 状态(1-有效,0-无效)
        */
        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 状态(1-有效,0-无效)
        */
        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 状态(1-有效,0-无效)
        */
        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 状态(1-有效,0-无效)
        */
        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 状态(1-有效,0-无效)
        */
        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 状态(1-有效,0-无效)
        */
        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 状态(1-有效,0-无效)
        */
        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 状态(1-有效,0-无效)
        */
        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 状态(1-有效,0-无效)
        */
        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sdk_information
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sdk_information
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        public Criterion() {
            super();
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}