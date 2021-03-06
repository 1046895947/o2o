package club.bagedate.o2o.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductCategoryExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table productcategory
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table productcategory
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table productcategory
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productcategory
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    public ProductCategoryExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productcategory
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productcategory
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productcategory
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productcategory
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productcategory
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productcategory
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productcategory
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productcategory
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
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
     * This method corresponds to the database table productcategory
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productcategory
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table productcategory
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
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

        public Criteria andProductcategoryidIsNull() {
            addCriterion("productCategoryId is null");
            return (Criteria) this;
        }

        public Criteria andProductcategoryidIsNotNull() {
            addCriterion("productCategoryId is not null");
            return (Criteria) this;
        }

        public Criteria andProductcategoryidEqualTo(Integer value) {
            addCriterion("productCategoryId =", value, "productcategoryid");
            return (Criteria) this;
        }

        public Criteria andProductcategoryidNotEqualTo(Integer value) {
            addCriterion("productCategoryId <>", value, "productcategoryid");
            return (Criteria) this;
        }

        public Criteria andProductcategoryidGreaterThan(Integer value) {
            addCriterion("productCategoryId >", value, "productcategoryid");
            return (Criteria) this;
        }

        public Criteria andProductcategoryidGreaterThanOrEqualTo(Integer value) {
            addCriterion("productCategoryId >=", value, "productcategoryid");
            return (Criteria) this;
        }

        public Criteria andProductcategoryidLessThan(Integer value) {
            addCriterion("productCategoryId <", value, "productcategoryid");
            return (Criteria) this;
        }

        public Criteria andProductcategoryidLessThanOrEqualTo(Integer value) {
            addCriterion("productCategoryId <=", value, "productcategoryid");
            return (Criteria) this;
        }

        public Criteria andProductcategoryidIn(List<Integer> values) {
            addCriterion("productCategoryId in", values, "productcategoryid");
            return (Criteria) this;
        }

        public Criteria andProductcategoryidNotIn(List<Integer> values) {
            addCriterion("productCategoryId not in", values, "productcategoryid");
            return (Criteria) this;
        }

        public Criteria andProductcategoryidBetween(Integer value1, Integer value2) {
            addCriterion("productCategoryId between", value1, value2, "productcategoryid");
            return (Criteria) this;
        }

        public Criteria andProductcategoryidNotBetween(Integer value1, Integer value2) {
            addCriterion("productCategoryId not between", value1, value2, "productcategoryid");
            return (Criteria) this;
        }

        public Criteria andShopidIsNull() {
            addCriterion("shopId is null");
            return (Criteria) this;
        }

        public Criteria andShopidIsNotNull() {
            addCriterion("shopId is not null");
            return (Criteria) this;
        }

        public Criteria andShopidEqualTo(Integer value) {
            addCriterion("shopId =", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotEqualTo(Integer value) {
            addCriterion("shopId <>", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidGreaterThan(Integer value) {
            addCriterion("shopId >", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidGreaterThanOrEqualTo(Integer value) {
            addCriterion("shopId >=", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidLessThan(Integer value) {
            addCriterion("shopId <", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidLessThanOrEqualTo(Integer value) {
            addCriterion("shopId <=", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidIn(List<Integer> values) {
            addCriterion("shopId in", values, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotIn(List<Integer> values) {
            addCriterion("shopId not in", values, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidBetween(Integer value1, Integer value2) {
            addCriterion("shopId between", value1, value2, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotBetween(Integer value1, Integer value2) {
            addCriterion("shopId not between", value1, value2, "shopid");
            return (Criteria) this;
        }

        public Criteria andProductcategorynameIsNull() {
            addCriterion("productCategoryName is null");
            return (Criteria) this;
        }

        public Criteria andProductcategorynameIsNotNull() {
            addCriterion("productCategoryName is not null");
            return (Criteria) this;
        }

        public Criteria andProductcategorynameEqualTo(String value) {
            addCriterion("productCategoryName =", value, "productcategoryname");
            return (Criteria) this;
        }

        public Criteria andProductcategorynameNotEqualTo(String value) {
            addCriterion("productCategoryName <>", value, "productcategoryname");
            return (Criteria) this;
        }

        public Criteria andProductcategorynameGreaterThan(String value) {
            addCriterion("productCategoryName >", value, "productcategoryname");
            return (Criteria) this;
        }

        public Criteria andProductcategorynameGreaterThanOrEqualTo(String value) {
            addCriterion("productCategoryName >=", value, "productcategoryname");
            return (Criteria) this;
        }

        public Criteria andProductcategorynameLessThan(String value) {
            addCriterion("productCategoryName <", value, "productcategoryname");
            return (Criteria) this;
        }

        public Criteria andProductcategorynameLessThanOrEqualTo(String value) {
            addCriterion("productCategoryName <=", value, "productcategoryname");
            return (Criteria) this;
        }

        public Criteria andProductcategorynameLike(String value) {
            addCriterion("productCategoryName like", value, "productcategoryname");
            return (Criteria) this;
        }

        public Criteria andProductcategorynameNotLike(String value) {
            addCriterion("productCategoryName not like", value, "productcategoryname");
            return (Criteria) this;
        }

        public Criteria andProductcategorynameIn(List<String> values) {
            addCriterion("productCategoryName in", values, "productcategoryname");
            return (Criteria) this;
        }

        public Criteria andProductcategorynameNotIn(List<String> values) {
            addCriterion("productCategoryName not in", values, "productcategoryname");
            return (Criteria) this;
        }

        public Criteria andProductcategorynameBetween(String value1, String value2) {
            addCriterion("productCategoryName between", value1, value2, "productcategoryname");
            return (Criteria) this;
        }

        public Criteria andProductcategorynameNotBetween(String value1, String value2) {
            addCriterion("productCategoryName not between", value1, value2, "productcategoryname");
            return (Criteria) this;
        }

        public Criteria andProductcategorypriorityIsNull() {
            addCriterion("productCategoryPriority is null");
            return (Criteria) this;
        }

        public Criteria andProductcategorypriorityIsNotNull() {
            addCriterion("productCategoryPriority is not null");
            return (Criteria) this;
        }

        public Criteria andProductcategorypriorityEqualTo(Integer value) {
            addCriterion("productCategoryPriority =", value, "productcategorypriority");
            return (Criteria) this;
        }

        public Criteria andProductcategorypriorityNotEqualTo(Integer value) {
            addCriterion("productCategoryPriority <>", value, "productcategorypriority");
            return (Criteria) this;
        }

        public Criteria andProductcategorypriorityGreaterThan(Integer value) {
            addCriterion("productCategoryPriority >", value, "productcategorypriority");
            return (Criteria) this;
        }

        public Criteria andProductcategorypriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("productCategoryPriority >=", value, "productcategorypriority");
            return (Criteria) this;
        }

        public Criteria andProductcategorypriorityLessThan(Integer value) {
            addCriterion("productCategoryPriority <", value, "productcategorypriority");
            return (Criteria) this;
        }

        public Criteria andProductcategorypriorityLessThanOrEqualTo(Integer value) {
            addCriterion("productCategoryPriority <=", value, "productcategorypriority");
            return (Criteria) this;
        }

        public Criteria andProductcategorypriorityIn(List<Integer> values) {
            addCriterion("productCategoryPriority in", values, "productcategorypriority");
            return (Criteria) this;
        }

        public Criteria andProductcategorypriorityNotIn(List<Integer> values) {
            addCriterion("productCategoryPriority not in", values, "productcategorypriority");
            return (Criteria) this;
        }

        public Criteria andProductcategorypriorityBetween(Integer value1, Integer value2) {
            addCriterion("productCategoryPriority between", value1, value2, "productcategorypriority");
            return (Criteria) this;
        }

        public Criteria andProductcategorypriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("productCategoryPriority not between", value1, value2, "productcategorypriority");
            return (Criteria) this;
        }

        public Criteria andProductcategorycreatetimeIsNull() {
            addCriterion("productCategoryCreateTime is null");
            return (Criteria) this;
        }

        public Criteria andProductcategorycreatetimeIsNotNull() {
            addCriterion("productCategoryCreateTime is not null");
            return (Criteria) this;
        }

        public Criteria andProductcategorycreatetimeEqualTo(Date value) {
            addCriterion("productCategoryCreateTime =", value, "productcategorycreatetime");
            return (Criteria) this;
        }

        public Criteria andProductcategorycreatetimeNotEqualTo(Date value) {
            addCriterion("productCategoryCreateTime <>", value, "productcategorycreatetime");
            return (Criteria) this;
        }

        public Criteria andProductcategorycreatetimeGreaterThan(Date value) {
            addCriterion("productCategoryCreateTime >", value, "productcategorycreatetime");
            return (Criteria) this;
        }

        public Criteria andProductcategorycreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("productCategoryCreateTime >=", value, "productcategorycreatetime");
            return (Criteria) this;
        }

        public Criteria andProductcategorycreatetimeLessThan(Date value) {
            addCriterion("productCategoryCreateTime <", value, "productcategorycreatetime");
            return (Criteria) this;
        }

        public Criteria andProductcategorycreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("productCategoryCreateTime <=", value, "productcategorycreatetime");
            return (Criteria) this;
        }

        public Criteria andProductcategorycreatetimeIn(List<Date> values) {
            addCriterion("productCategoryCreateTime in", values, "productcategorycreatetime");
            return (Criteria) this;
        }

        public Criteria andProductcategorycreatetimeNotIn(List<Date> values) {
            addCriterion("productCategoryCreateTime not in", values, "productcategorycreatetime");
            return (Criteria) this;
        }

        public Criteria andProductcategorycreatetimeBetween(Date value1, Date value2) {
            addCriterion("productCategoryCreateTime between", value1, value2, "productcategorycreatetime");
            return (Criteria) this;
        }

        public Criteria andProductcategorycreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("productCategoryCreateTime not between", value1, value2, "productcategorycreatetime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table productcategory
     *
     * @mbg.generated do_not_delete_during_merge Tue May 14 10:43:09 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table productcategory
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
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