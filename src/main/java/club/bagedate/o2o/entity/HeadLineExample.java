package club.bagedate.o2o.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HeadLineExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table headline
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table headline
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table headline
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table headline
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    public HeadLineExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table headline
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table headline
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table headline
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table headline
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table headline
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table headline
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table headline
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
     * This method corresponds to the database table headline
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
     * This method corresponds to the database table headline
     *
     * @mbg.generated Tue May 14 10:43:09 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table headline
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
     * This class corresponds to the database table headline
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

        public Criteria andLineidIsNull() {
            addCriterion("lineId is null");
            return (Criteria) this;
        }

        public Criteria andLineidIsNotNull() {
            addCriterion("lineId is not null");
            return (Criteria) this;
        }

        public Criteria andLineidEqualTo(Integer value) {
            addCriterion("lineId =", value, "lineid");
            return (Criteria) this;
        }

        public Criteria andLineidNotEqualTo(Integer value) {
            addCriterion("lineId <>", value, "lineid");
            return (Criteria) this;
        }

        public Criteria andLineidGreaterThan(Integer value) {
            addCriterion("lineId >", value, "lineid");
            return (Criteria) this;
        }

        public Criteria andLineidGreaterThanOrEqualTo(Integer value) {
            addCriterion("lineId >=", value, "lineid");
            return (Criteria) this;
        }

        public Criteria andLineidLessThan(Integer value) {
            addCriterion("lineId <", value, "lineid");
            return (Criteria) this;
        }

        public Criteria andLineidLessThanOrEqualTo(Integer value) {
            addCriterion("lineId <=", value, "lineid");
            return (Criteria) this;
        }

        public Criteria andLineidIn(List<Integer> values) {
            addCriterion("lineId in", values, "lineid");
            return (Criteria) this;
        }

        public Criteria andLineidNotIn(List<Integer> values) {
            addCriterion("lineId not in", values, "lineid");
            return (Criteria) this;
        }

        public Criteria andLineidBetween(Integer value1, Integer value2) {
            addCriterion("lineId between", value1, value2, "lineid");
            return (Criteria) this;
        }

        public Criteria andLineidNotBetween(Integer value1, Integer value2) {
            addCriterion("lineId not between", value1, value2, "lineid");
            return (Criteria) this;
        }

        public Criteria andLinepriorityIsNull() {
            addCriterion("linePriority is null");
            return (Criteria) this;
        }

        public Criteria andLinepriorityIsNotNull() {
            addCriterion("linePriority is not null");
            return (Criteria) this;
        }

        public Criteria andLinepriorityEqualTo(Integer value) {
            addCriterion("linePriority =", value, "linepriority");
            return (Criteria) this;
        }

        public Criteria andLinepriorityNotEqualTo(Integer value) {
            addCriterion("linePriority <>", value, "linepriority");
            return (Criteria) this;
        }

        public Criteria andLinepriorityGreaterThan(Integer value) {
            addCriterion("linePriority >", value, "linepriority");
            return (Criteria) this;
        }

        public Criteria andLinepriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("linePriority >=", value, "linepriority");
            return (Criteria) this;
        }

        public Criteria andLinepriorityLessThan(Integer value) {
            addCriterion("linePriority <", value, "linepriority");
            return (Criteria) this;
        }

        public Criteria andLinepriorityLessThanOrEqualTo(Integer value) {
            addCriterion("linePriority <=", value, "linepriority");
            return (Criteria) this;
        }

        public Criteria andLinepriorityIn(List<Integer> values) {
            addCriterion("linePriority in", values, "linepriority");
            return (Criteria) this;
        }

        public Criteria andLinepriorityNotIn(List<Integer> values) {
            addCriterion("linePriority not in", values, "linepriority");
            return (Criteria) this;
        }

        public Criteria andLinepriorityBetween(Integer value1, Integer value2) {
            addCriterion("linePriority between", value1, value2, "linepriority");
            return (Criteria) this;
        }

        public Criteria andLinepriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("linePriority not between", value1, value2, "linepriority");
            return (Criteria) this;
        }

        public Criteria andLinestatusIsNull() {
            addCriterion("lineStatus is null");
            return (Criteria) this;
        }

        public Criteria andLinestatusIsNotNull() {
            addCriterion("lineStatus is not null");
            return (Criteria) this;
        }

        public Criteria andLinestatusEqualTo(Integer value) {
            addCriterion("lineStatus =", value, "linestatus");
            return (Criteria) this;
        }

        public Criteria andLinestatusNotEqualTo(Integer value) {
            addCriterion("lineStatus <>", value, "linestatus");
            return (Criteria) this;
        }

        public Criteria andLinestatusGreaterThan(Integer value) {
            addCriterion("lineStatus >", value, "linestatus");
            return (Criteria) this;
        }

        public Criteria andLinestatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("lineStatus >=", value, "linestatus");
            return (Criteria) this;
        }

        public Criteria andLinestatusLessThan(Integer value) {
            addCriterion("lineStatus <", value, "linestatus");
            return (Criteria) this;
        }

        public Criteria andLinestatusLessThanOrEqualTo(Integer value) {
            addCriterion("lineStatus <=", value, "linestatus");
            return (Criteria) this;
        }

        public Criteria andLinestatusIn(List<Integer> values) {
            addCriterion("lineStatus in", values, "linestatus");
            return (Criteria) this;
        }

        public Criteria andLinestatusNotIn(List<Integer> values) {
            addCriterion("lineStatus not in", values, "linestatus");
            return (Criteria) this;
        }

        public Criteria andLinestatusBetween(Integer value1, Integer value2) {
            addCriterion("lineStatus between", value1, value2, "linestatus");
            return (Criteria) this;
        }

        public Criteria andLinestatusNotBetween(Integer value1, Integer value2) {
            addCriterion("lineStatus not between", value1, value2, "linestatus");
            return (Criteria) this;
        }

        public Criteria andLinecreatetimeIsNull() {
            addCriterion("lineCreateTime is null");
            return (Criteria) this;
        }

        public Criteria andLinecreatetimeIsNotNull() {
            addCriterion("lineCreateTime is not null");
            return (Criteria) this;
        }

        public Criteria andLinecreatetimeEqualTo(Date value) {
            addCriterion("lineCreateTime =", value, "linecreatetime");
            return (Criteria) this;
        }

        public Criteria andLinecreatetimeNotEqualTo(Date value) {
            addCriterion("lineCreateTime <>", value, "linecreatetime");
            return (Criteria) this;
        }

        public Criteria andLinecreatetimeGreaterThan(Date value) {
            addCriterion("lineCreateTime >", value, "linecreatetime");
            return (Criteria) this;
        }

        public Criteria andLinecreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lineCreateTime >=", value, "linecreatetime");
            return (Criteria) this;
        }

        public Criteria andLinecreatetimeLessThan(Date value) {
            addCriterion("lineCreateTime <", value, "linecreatetime");
            return (Criteria) this;
        }

        public Criteria andLinecreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("lineCreateTime <=", value, "linecreatetime");
            return (Criteria) this;
        }

        public Criteria andLinecreatetimeIn(List<Date> values) {
            addCriterion("lineCreateTime in", values, "linecreatetime");
            return (Criteria) this;
        }

        public Criteria andLinecreatetimeNotIn(List<Date> values) {
            addCriterion("lineCreateTime not in", values, "linecreatetime");
            return (Criteria) this;
        }

        public Criteria andLinecreatetimeBetween(Date value1, Date value2) {
            addCriterion("lineCreateTime between", value1, value2, "linecreatetime");
            return (Criteria) this;
        }

        public Criteria andLinecreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("lineCreateTime not between", value1, value2, "linecreatetime");
            return (Criteria) this;
        }

        public Criteria andLineupdatetimeIsNull() {
            addCriterion("lineUpdateTime is null");
            return (Criteria) this;
        }

        public Criteria andLineupdatetimeIsNotNull() {
            addCriterion("lineUpdateTime is not null");
            return (Criteria) this;
        }

        public Criteria andLineupdatetimeEqualTo(Date value) {
            addCriterion("lineUpdateTime =", value, "lineupdatetime");
            return (Criteria) this;
        }

        public Criteria andLineupdatetimeNotEqualTo(Date value) {
            addCriterion("lineUpdateTime <>", value, "lineupdatetime");
            return (Criteria) this;
        }

        public Criteria andLineupdatetimeGreaterThan(Date value) {
            addCriterion("lineUpdateTime >", value, "lineupdatetime");
            return (Criteria) this;
        }

        public Criteria andLineupdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lineUpdateTime >=", value, "lineupdatetime");
            return (Criteria) this;
        }

        public Criteria andLineupdatetimeLessThan(Date value) {
            addCriterion("lineUpdateTime <", value, "lineupdatetime");
            return (Criteria) this;
        }

        public Criteria andLineupdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("lineUpdateTime <=", value, "lineupdatetime");
            return (Criteria) this;
        }

        public Criteria andLineupdatetimeIn(List<Date> values) {
            addCriterion("lineUpdateTime in", values, "lineupdatetime");
            return (Criteria) this;
        }

        public Criteria andLineupdatetimeNotIn(List<Date> values) {
            addCriterion("lineUpdateTime not in", values, "lineupdatetime");
            return (Criteria) this;
        }

        public Criteria andLineupdatetimeBetween(Date value1, Date value2) {
            addCriterion("lineUpdateTime between", value1, value2, "lineupdatetime");
            return (Criteria) this;
        }

        public Criteria andLineupdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("lineUpdateTime not between", value1, value2, "lineupdatetime");
            return (Criteria) this;
        }

        public Criteria andLinenameIsNull() {
            addCriterion("lineName is null");
            return (Criteria) this;
        }

        public Criteria andLinenameIsNotNull() {
            addCriterion("lineName is not null");
            return (Criteria) this;
        }

        public Criteria andLinenameEqualTo(String value) {
            addCriterion("lineName =", value, "linename");
            return (Criteria) this;
        }

        public Criteria andLinenameNotEqualTo(String value) {
            addCriterion("lineName <>", value, "linename");
            return (Criteria) this;
        }

        public Criteria andLinenameGreaterThan(String value) {
            addCriterion("lineName >", value, "linename");
            return (Criteria) this;
        }

        public Criteria andLinenameGreaterThanOrEqualTo(String value) {
            addCriterion("lineName >=", value, "linename");
            return (Criteria) this;
        }

        public Criteria andLinenameLessThan(String value) {
            addCriterion("lineName <", value, "linename");
            return (Criteria) this;
        }

        public Criteria andLinenameLessThanOrEqualTo(String value) {
            addCriterion("lineName <=", value, "linename");
            return (Criteria) this;
        }

        public Criteria andLinenameLike(String value) {
            addCriterion("lineName like", value, "linename");
            return (Criteria) this;
        }

        public Criteria andLinenameNotLike(String value) {
            addCriterion("lineName not like", value, "linename");
            return (Criteria) this;
        }

        public Criteria andLinenameIn(List<String> values) {
            addCriterion("lineName in", values, "linename");
            return (Criteria) this;
        }

        public Criteria andLinenameNotIn(List<String> values) {
            addCriterion("lineName not in", values, "linename");
            return (Criteria) this;
        }

        public Criteria andLinenameBetween(String value1, String value2) {
            addCriterion("lineName between", value1, value2, "linename");
            return (Criteria) this;
        }

        public Criteria andLinenameNotBetween(String value1, String value2) {
            addCriterion("lineName not between", value1, value2, "linename");
            return (Criteria) this;
        }

        public Criteria andLinelinkIsNull() {
            addCriterion("lineLink is null");
            return (Criteria) this;
        }

        public Criteria andLinelinkIsNotNull() {
            addCriterion("lineLink is not null");
            return (Criteria) this;
        }

        public Criteria andLinelinkEqualTo(String value) {
            addCriterion("lineLink =", value, "linelink");
            return (Criteria) this;
        }

        public Criteria andLinelinkNotEqualTo(String value) {
            addCriterion("lineLink <>", value, "linelink");
            return (Criteria) this;
        }

        public Criteria andLinelinkGreaterThan(String value) {
            addCriterion("lineLink >", value, "linelink");
            return (Criteria) this;
        }

        public Criteria andLinelinkGreaterThanOrEqualTo(String value) {
            addCriterion("lineLink >=", value, "linelink");
            return (Criteria) this;
        }

        public Criteria andLinelinkLessThan(String value) {
            addCriterion("lineLink <", value, "linelink");
            return (Criteria) this;
        }

        public Criteria andLinelinkLessThanOrEqualTo(String value) {
            addCriterion("lineLink <=", value, "linelink");
            return (Criteria) this;
        }

        public Criteria andLinelinkLike(String value) {
            addCriterion("lineLink like", value, "linelink");
            return (Criteria) this;
        }

        public Criteria andLinelinkNotLike(String value) {
            addCriterion("lineLink not like", value, "linelink");
            return (Criteria) this;
        }

        public Criteria andLinelinkIn(List<String> values) {
            addCriterion("lineLink in", values, "linelink");
            return (Criteria) this;
        }

        public Criteria andLinelinkNotIn(List<String> values) {
            addCriterion("lineLink not in", values, "linelink");
            return (Criteria) this;
        }

        public Criteria andLinelinkBetween(String value1, String value2) {
            addCriterion("lineLink between", value1, value2, "linelink");
            return (Criteria) this;
        }

        public Criteria andLinelinkNotBetween(String value1, String value2) {
            addCriterion("lineLink not between", value1, value2, "linelink");
            return (Criteria) this;
        }

        public Criteria andLineimgIsNull() {
            addCriterion("lineImg is null");
            return (Criteria) this;
        }

        public Criteria andLineimgIsNotNull() {
            addCriterion("lineImg is not null");
            return (Criteria) this;
        }

        public Criteria andLineimgEqualTo(String value) {
            addCriterion("lineImg =", value, "lineimg");
            return (Criteria) this;
        }

        public Criteria andLineimgNotEqualTo(String value) {
            addCriterion("lineImg <>", value, "lineimg");
            return (Criteria) this;
        }

        public Criteria andLineimgGreaterThan(String value) {
            addCriterion("lineImg >", value, "lineimg");
            return (Criteria) this;
        }

        public Criteria andLineimgGreaterThanOrEqualTo(String value) {
            addCriterion("lineImg >=", value, "lineimg");
            return (Criteria) this;
        }

        public Criteria andLineimgLessThan(String value) {
            addCriterion("lineImg <", value, "lineimg");
            return (Criteria) this;
        }

        public Criteria andLineimgLessThanOrEqualTo(String value) {
            addCriterion("lineImg <=", value, "lineimg");
            return (Criteria) this;
        }

        public Criteria andLineimgLike(String value) {
            addCriterion("lineImg like", value, "lineimg");
            return (Criteria) this;
        }

        public Criteria andLineimgNotLike(String value) {
            addCriterion("lineImg not like", value, "lineimg");
            return (Criteria) this;
        }

        public Criteria andLineimgIn(List<String> values) {
            addCriterion("lineImg in", values, "lineimg");
            return (Criteria) this;
        }

        public Criteria andLineimgNotIn(List<String> values) {
            addCriterion("lineImg not in", values, "lineimg");
            return (Criteria) this;
        }

        public Criteria andLineimgBetween(String value1, String value2) {
            addCriterion("lineImg between", value1, value2, "lineimg");
            return (Criteria) this;
        }

        public Criteria andLineimgNotBetween(String value1, String value2) {
            addCriterion("lineImg not between", value1, value2, "lineimg");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table headline
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
     * This class corresponds to the database table headline
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