package com.cheyooh.service.sdk.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SdkMobileVcodeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sdk_mobile_vcode
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sdk_mobile_vcode
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sdk_mobile_vcode
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mobile_vcode
     *
     * @mbggenerated
     */
    public SdkMobileVcodeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mobile_vcode
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mobile_vcode
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mobile_vcode
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mobile_vcode
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mobile_vcode
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mobile_vcode
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mobile_vcode
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
     * This method corresponds to the database table sdk_mobile_vcode
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
     * This method corresponds to the database table sdk_mobile_vcode
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mobile_vcode
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
     * This class corresponds to the database table sdk_mobile_vcode
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

        public Criteria andVidIsNull() {
            addCriterion("vid is null");
            return (Criteria) this;
        }

        public Criteria andVidIsNotNull() {
            addCriterion("vid is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 自增主键
        */
        public Criteria andVidEqualTo(Integer value) {
            addCriterion("vid =", value, "vid");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 自增主键
        */
        public Criteria andVidNotEqualTo(Integer value) {
            addCriterion("vid <>", value, "vid");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 自增主键
        */
        public Criteria andVidGreaterThan(Integer value) {
            addCriterion("vid >", value, "vid");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 自增主键
        */
        public Criteria andVidGreaterThanOrEqualTo(Integer value) {
            addCriterion("vid >=", value, "vid");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 自增主键
        */
        public Criteria andVidLessThan(Integer value) {
            addCriterion("vid <", value, "vid");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 自增主键
        */
        public Criteria andVidLessThanOrEqualTo(Integer value) {
            addCriterion("vid <=", value, "vid");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 自增主键
        */
        public Criteria andVidIn(List<Integer> values) {
            addCriterion("vid in", values, "vid");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 自增主键
        */
        public Criteria andVidNotIn(List<Integer> values) {
            addCriterion("vid not in", values, "vid");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 自增主键
        */
        public Criteria andVidBetween(Integer value1, Integer value2) {
            addCriterion("vid between", value1, value2, "vid");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 自增主键
        */
        public Criteria andVidNotBetween(Integer value1, Integer value2) {
            addCriterion("vid not between", value1, value2, "vid");
            return (Criteria) this;
        }

        public Criteria andVcodeIsNull() {
            addCriterion("vcode is null");
            return (Criteria) this;
        }

        public Criteria andVcodeIsNotNull() {
            addCriterion("vcode is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码
        */
        public Criteria andVcodeEqualTo(String value) {
            addCriterion("vcode =", value, "vcode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码
        */
        public Criteria andVcodeNotEqualTo(String value) {
            addCriterion("vcode <>", value, "vcode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码
        */
        public Criteria andVcodeGreaterThan(String value) {
            addCriterion("vcode >", value, "vcode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码
        */
        public Criteria andVcodeGreaterThanOrEqualTo(String value) {
            addCriterion("vcode >=", value, "vcode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码
        */
        public Criteria andVcodeLessThan(String value) {
            addCriterion("vcode <", value, "vcode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码
        */
        public Criteria andVcodeLessThanOrEqualTo(String value) {
            addCriterion("vcode <=", value, "vcode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码
        */
        public Criteria andVcodeLike(String value) {
            addCriterion("vcode like", value, "vcode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码
        */
        public Criteria andVcodeNotLike(String value) {
            addCriterion("vcode not like", value, "vcode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码
        */
        public Criteria andVcodeIn(List<String> values) {
            addCriterion("vcode in", values, "vcode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码
        */
        public Criteria andVcodeNotIn(List<String> values) {
            addCriterion("vcode not in", values, "vcode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码
        */
        public Criteria andVcodeBetween(String value1, String value2) {
            addCriterion("vcode between", value1, value2, "vcode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码
        */
        public Criteria andVcodeNotBetween(String value1, String value2) {
            addCriterion("vcode not between", value1, value2, "vcode");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 手机号
        */
        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 手机号
        */
        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 手机号
        */
        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 手机号
        */
        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 手机号
        */
        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 手机号
        */
        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 手机号
        */
        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 手机号
        */
        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 手机号
        */
        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 手机号
        */
        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 手机号
        */
        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 手机号
        */
        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("`uid` is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("`uid` is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 用户ID
        */
        public Criteria andUidEqualTo(Integer value) {
            addCriterion("`uid` =", value, "uid");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 用户ID
        */
        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("`uid` <>", value, "uid");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 用户ID
        */
        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("`uid` >", value, "uid");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 用户ID
        */
        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("`uid` >=", value, "uid");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 用户ID
        */
        public Criteria andUidLessThan(Integer value) {
            addCriterion("`uid` <", value, "uid");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 用户ID
        */
        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("`uid` <=", value, "uid");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 用户ID
        */
        public Criteria andUidIn(List<Integer> values) {
            addCriterion("`uid` in", values, "uid");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 用户ID
        */
        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("`uid` not in", values, "uid");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 用户ID
        */
        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("`uid` between", value1, value2, "uid");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 用户ID
        */
        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("`uid` not between", value1, value2, "uid");
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
          * 字段说明: 验证码类型
1- 绑定手机
2-找回用户
        */
        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("`type` =", value, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码类型
1- 绑定手机
2-找回用户
        */
        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("`type` <>", value, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码类型
1- 绑定手机
2-找回用户
        */
        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("`type` >", value, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码类型
1- 绑定手机
2-找回用户
        */
        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("`type` >=", value, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码类型
1- 绑定手机
2-找回用户
        */
        public Criteria andTypeLessThan(Integer value) {
            addCriterion("`type` <", value, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码类型
1- 绑定手机
2-找回用户
        */
        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("`type` <=", value, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码类型
1- 绑定手机
2-找回用户
        */
        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("`type` in", values, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码类型
1- 绑定手机
2-找回用户
        */
        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("`type` not in", values, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码类型
1- 绑定手机
2-找回用户
        */
        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("`type` between", value1, value2, "type");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 验证码类型
1- 绑定手机
2-找回用户
        */
        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("`type` not between", value1, value2, "type");
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
          * 字段说明: 创建时间
        */
        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sdk_mobile_vcode
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
     * This class corresponds to the database table sdk_mobile_vcode
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