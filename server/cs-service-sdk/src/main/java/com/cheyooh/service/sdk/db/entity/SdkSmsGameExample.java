package com.cheyooh.service.sdk.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SdkSmsGameExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sdk_sms_game
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sdk_sms_game
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sdk_sms_game
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_sms_game
     *
     * @mbggenerated
     */
    public SdkSmsGameExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_sms_game
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_sms_game
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_sms_game
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_sms_game
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_sms_game
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_sms_game
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_sms_game
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
     * This method corresponds to the database table sdk_sms_game
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
     * This method corresponds to the database table sdk_sms_game
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_sms_game
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
     * This class corresponds to the database table sdk_sms_game
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 
        */
        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 
        */
        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 
        */
        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 
        */
        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 
        */
        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 
        */
        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 
        */
        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 
        */
        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 
        */
        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 
        */
        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andSendStateIsNull() {
            addCriterion("send_state is null");
            return (Criteria) this;
        }

        public Criteria andSendStateIsNotNull() {
            addCriterion("send_state is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 是否发送短信获取手机号:1 发送，0 不发送
        */
        public Criteria andSendStateEqualTo(Integer value) {
            addCriterion("send_state =", value, "sendState");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 是否发送短信获取手机号:1 发送，0 不发送
        */
        public Criteria andSendStateNotEqualTo(Integer value) {
            addCriterion("send_state <>", value, "sendState");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 是否发送短信获取手机号:1 发送，0 不发送
        */
        public Criteria andSendStateGreaterThan(Integer value) {
            addCriterion("send_state >", value, "sendState");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 是否发送短信获取手机号:1 发送，0 不发送
        */
        public Criteria andSendStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_state >=", value, "sendState");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 是否发送短信获取手机号:1 发送，0 不发送
        */
        public Criteria andSendStateLessThan(Integer value) {
            addCriterion("send_state <", value, "sendState");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 是否发送短信获取手机号:1 发送，0 不发送
        */
        public Criteria andSendStateLessThanOrEqualTo(Integer value) {
            addCriterion("send_state <=", value, "sendState");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 是否发送短信获取手机号:1 发送，0 不发送
        */
        public Criteria andSendStateIn(List<Integer> values) {
            addCriterion("send_state in", values, "sendState");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 是否发送短信获取手机号:1 发送，0 不发送
        */
        public Criteria andSendStateNotIn(List<Integer> values) {
            addCriterion("send_state not in", values, "sendState");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 是否发送短信获取手机号:1 发送，0 不发送
        */
        public Criteria andSendStateBetween(Integer value1, Integer value2) {
            addCriterion("send_state between", value1, value2, "sendState");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 是否发送短信获取手机号:1 发送，0 不发送
        */
        public Criteria andSendStateNotBetween(Integer value1, Integer value2) {
            addCriterion("send_state not between", value1, value2, "sendState");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 创建时间
        */
        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("updated_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("updated_time is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 更改时间
        */
        public Criteria andUpdatedTimeEqualTo(Date value) {
            addCriterion("updated_time =", value, "updatedTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 更改时间
        */
        public Criteria andUpdatedTimeNotEqualTo(Date value) {
            addCriterion("updated_time <>", value, "updatedTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 更改时间
        */
        public Criteria andUpdatedTimeGreaterThan(Date value) {
            addCriterion("updated_time >", value, "updatedTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 更改时间
        */
        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_time >=", value, "updatedTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 更改时间
        */
        public Criteria andUpdatedTimeLessThan(Date value) {
            addCriterion("updated_time <", value, "updatedTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 更改时间
        */
        public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("updated_time <=", value, "updatedTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 更改时间
        */
        public Criteria andUpdatedTimeIn(List<Date> values) {
            addCriterion("updated_time in", values, "updatedTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 更改时间
        */
        public Criteria andUpdatedTimeNotIn(List<Date> values) {
            addCriterion("updated_time not in", values, "updatedTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 更改时间
        */
        public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
            addCriterion("updated_time between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 更改时间
        */
        public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("updated_time not between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andSmstipIsNull() {
            addCriterion("smstip is null");
            return (Criteria) this;
        }

        public Criteria andSmstipIsNotNull() {
            addCriterion("smstip is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 第一次登陆时发短信和联网提示框弹出状态:1-弹出,0-不弹出
        */
        public Criteria andSmstipEqualTo(Integer value) {
            addCriterion("smstip =", value, "smstip");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 第一次登陆时发短信和联网提示框弹出状态:1-弹出,0-不弹出
        */
        public Criteria andSmstipNotEqualTo(Integer value) {
            addCriterion("smstip <>", value, "smstip");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 第一次登陆时发短信和联网提示框弹出状态:1-弹出,0-不弹出
        */
        public Criteria andSmstipGreaterThan(Integer value) {
            addCriterion("smstip >", value, "smstip");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 第一次登陆时发短信和联网提示框弹出状态:1-弹出,0-不弹出
        */
        public Criteria andSmstipGreaterThanOrEqualTo(Integer value) {
            addCriterion("smstip >=", value, "smstip");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 第一次登陆时发短信和联网提示框弹出状态:1-弹出,0-不弹出
        */
        public Criteria andSmstipLessThan(Integer value) {
            addCriterion("smstip <", value, "smstip");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 第一次登陆时发短信和联网提示框弹出状态:1-弹出,0-不弹出
        */
        public Criteria andSmstipLessThanOrEqualTo(Integer value) {
            addCriterion("smstip <=", value, "smstip");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 第一次登陆时发短信和联网提示框弹出状态:1-弹出,0-不弹出
        */
        public Criteria andSmstipIn(List<Integer> values) {
            addCriterion("smstip in", values, "smstip");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 第一次登陆时发短信和联网提示框弹出状态:1-弹出,0-不弹出
        */
        public Criteria andSmstipNotIn(List<Integer> values) {
            addCriterion("smstip not in", values, "smstip");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 第一次登陆时发短信和联网提示框弹出状态:1-弹出,0-不弹出
        */
        public Criteria andSmstipBetween(Integer value1, Integer value2) {
            addCriterion("smstip between", value1, value2, "smstip");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 第一次登陆时发短信和联网提示框弹出状态:1-弹出,0-不弹出
        */
        public Criteria andSmstipNotBetween(Integer value1, Integer value2) {
            addCriterion("smstip not between", value1, value2, "smstip");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sdk_sms_game
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
     * This class corresponds to the database table sdk_sms_game
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