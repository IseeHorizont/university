package ru.tusur.university;

public class Subject {
    private String subjectName;
    private String subjectCode;
    private int subjectSemester;
    private String subjectMajor;
    private int departmentId;

    public Subject(String subjectName, String subjectCode, int subjectSemester, String subjectMajor) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.subjectSemester = subjectSemester;
        this.subjectMajor = subjectMajor;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public int getSubjectSemester() {
        return subjectSemester;
    }

    public void setSubjectSemester(int subjectSemester) {
        this.subjectSemester = subjectSemester;
    }

    public String getSubjectMajor() {
        return subjectMajor;
    }

    public void setSubjectMajor(String subjectMajor) {
        this.subjectMajor = subjectMajor;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
