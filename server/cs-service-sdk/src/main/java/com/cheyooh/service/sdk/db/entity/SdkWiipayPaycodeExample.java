package com.cheyooh.service.sdk.db.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SdkWiipayPaycodeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sdk_wiipay_paycode
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sdk_wiipay_paycode
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sdk_wiipay_paycode
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_wiipay_paycode
     *
     * @mbggenerated
     */
    public SdkWiipayPaycodeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_wiipay_paycode
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_wiipay_paycode
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_wiipay_paycode
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_wiipay_paycode
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_wiipay_paycode
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_wiipay_paycode
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_wiipay_paycode
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
     * This method corresponds to the database table sdk_wiipay_paycode
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
     * This method corresponds to the database table sdk_wiipay_paycode
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_wiipay_paycode
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
     * This class corresponds to the database table sdk_wiipay_paycode
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
          * 字段说明: PayCode主键
        */
        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: PayCode主键
        */
        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: PayCode主键
        */
        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: PayCode主键
        */
        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: PayCode主键
        */
        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: PayCode主键
        */
        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: PayCode主键
        */
        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: PayCode主键
        */
        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: PayCode主键
        */
        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: PayCode主键
        */
        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项名
        */
        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项名
        */
        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项名
        */
        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项名
        */
        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项名
        */
        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项名
        */
        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项名
        */
        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项名
        */
        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项名
        */
        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项名
        */
        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项名
        */
        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项名
        */
        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
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

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 价格(元)(与微派对应)
        */
        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 价格(元)(与微派对应)
        */
        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 价格(元)(与微派对应)
        */
        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 价格(元)(与微派对应)
        */
        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 价格(元)(与微派对应)
        */
        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 价格(元)(与微派对应)
        */
        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 价格(元)(与微派对应)
        */
        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 价格(元)(与微派对应)
        */
        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 价格(元)(与微派对应)
        */
        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 价格(元)(与微派对应)
        */
        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPayCodeIsNull() {
            addCriterion("pay_code is null");
            return (Criteria) this;
        }

        public Criteria andPayCodeIsNotNull() {
            addCriterion("pay_code is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项编号(微派)
        */
        public Criteria andPayCodeEqualTo(String value) {
            addCriterion("pay_code =", value, "payCode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项编号(微派)
        */
        public Criteria andPayCodeNotEqualTo(String value) {
            addCriterion("pay_code <>", value, "payCode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项编号(微派)
        */
        public Criteria andPayCodeGreaterThan(String value) {
            addCriterion("pay_code >", value, "payCode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项编号(微派)
        */
        public Criteria andPayCodeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_code >=", value, "payCode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项编号(微派)
        */
        public Criteria andPayCodeLessThan(String value) {
            addCriterion("pay_code <", value, "payCode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项编号(微派)
        */
        public Criteria andPayCodeLessThanOrEqualTo(String value) {
            addCriterion("pay_code <=", value, "payCode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项编号(微派)
        */
        public Criteria andPayCodeLike(String value) {
            addCriterion("pay_code like", value, "payCode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项编号(微派)
        */
        public Criteria andPayCodeNotLike(String value) {
            addCriterion("pay_code not like", value, "payCode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项编号(微派)
        */
        public Criteria andPayCodeIn(List<String> values) {
            addCriterion("pay_code in", values, "payCode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项编号(微派)
        */
        public Criteria andPayCodeNotIn(List<String> values) {
            addCriterion("pay_code not in", values, "payCode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项编号(微派)
        */
        public Criteria andPayCodeBetween(String value1, String value2) {
            addCriterion("pay_code between", value1, value2, "payCode");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 计费项编号(微派)
        */
        public Criteria andPayCodeNotBetween(String value1, String value2) {
            addCriterion("pay_code not between", value1, value2, "payCode");
            return (Criteria) this;
        }

        public Criteria andPakageNameIsNull() {
            addCriterion("pakage_name is null");
            return (Criteria) this;
        }

        public Criteria andPakageNameIsNotNull() {
            addCriterion("pakage_name is not null");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 包名
        */
        public Criteria andPakageNameEqualTo(String value) {
            addCriterion("pakage_name =", value, "pakageName");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 包名
        */
        public Criteria andPakageNameNotEqualTo(String value) {
            addCriterion("pakage_name <>", value, "pakageName");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 包名
        */
        public Criteria andPakageNameGreaterThan(String value) {
            addCriterion("pakage_name >", value, "pakageName");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 包名
        */
        public Criteria andPakageNameGreaterThanOrEqualTo(String value) {
            addCriterion("pakage_name >=", value, "pakageName");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 包名
        */
        public Criteria andPakageNameLessThan(String value) {
            addCriterion("pakage_name <", value, "pakageName");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 包名
        */
        public Criteria andPakageNameLessThanOrEqualTo(String value) {
            addCriterion("pakage_name <=", value, "pakageName");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 包名
        */
        public Criteria andPakageNameLike(String value) {
            addCriterion("pakage_name like", value, "pakageName");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 包名
        */
        public Criteria andPakageNameNotLike(String value) {
            addCriterion("pakage_name not like", value, "pakageName");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 包名
        */
        public Criteria andPakageNameIn(List<String> values) {
            addCriterion("pakage_name in", values, "pakageName");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 包名
        */
        public Criteria andPakageNameNotIn(List<String> values) {
            addCriterion("pakage_name not in", values, "pakageName");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 包名
        */
        public Criteria andPakageNameBetween(String value1, String value2) {
            addCriterion("pakage_name between", value1, value2, "pakageName");
            return (Criteria) this;
        }

        /**<br/>
          * 字段说明: 包名
        */
        public Criteria andPakageNameNotBetween(String value1, String value2) {
            addCriterion("pakage_name not between", value1, value2, "pakageName");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sdk_wiipay_paycode
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
     * This class corresponds to the database table sdk_wiipay_paycode
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