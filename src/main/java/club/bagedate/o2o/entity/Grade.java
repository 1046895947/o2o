package club.bagedate.o2o.entity;

public class Grade {
    int id; //序号
    String date; //开课日期
    String classId; //课程编号
    String className; //课程名称
    int grade; //成绩
    double classNum; //学分
    int classTime; //总学时
    double GPA; //绩点
    String classForm; //考核方式
    String classProperty; //课程属性
    String classNature; //课程性质

    public Grade() {
    }

    public Grade(int id, String date, String classId, String className, int grade, double classNum, int classTime, double GPA, String classForm, String classProperty, String classNature) {
        this.id = id;
        this.date = date;
        this.classId = classId;
        this.className = className;
        this.grade = grade;
        this.classNum = classNum;
        this.classTime = classTime;
        this.GPA = GPA;
        this.classForm = classForm;
        this.classProperty = classProperty;
        this.classNature = classNature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public double getClassNum() {
        return classNum;
    }

    public void setClassNum(double classNum) {
        this.classNum = classNum;
    }

    public int getClassTime() {
        return classTime;
    }

    public void setClassTime(int classTime) {
        this.classTime = classTime;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public String getClassForm() {
        return classForm;
    }

    public void setClassForm(String classForm) {
        this.classForm = classForm;
    }

    public String getClassProperty() {
        return classProperty;
    }

    public void setClassProperty(String classProperty) {
        this.classProperty = classProperty;
    }

    public String getClassNature() {
        return classNature;
    }

    public void setClassNature(String classNature) {
        this.classNature = classNature;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", classId=" + classId +
                ", className='" + className + '\'' +
                ", grade=" + grade +
                ", classNum=" + classNum +
                ", classTime=" + classTime +
                ", GPA=" + GPA +
                ", classForm='" + classForm + '\'' +
                ", classProperty='" + classProperty + '\'' +
                ", classNature='" + classNature + '\'' +
                '}';
    }
}
