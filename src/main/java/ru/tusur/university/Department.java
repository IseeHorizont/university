package ru.tusur.university;

public class Department {
    private Integer departmentNumber;
    private String departmentName;
    private String departmentHead;
    private String departmentFaculty;

    public Department(Integer departmentNumber, String departmentName, String departmentHead, String departmentFaculty) {
        this.departmentNumber = departmentNumber;
        this.departmentName = departmentName;
        this.departmentHead = departmentHead;
        this.departmentFaculty = departmentFaculty;
    }

    public Integer getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(Integer departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(String departmentHead) {
        this.departmentHead = departmentHead;
    }

    public String getDepartmentFaculty() {
        return departmentFaculty;
    }

    public void setDepartmentFaculty(String departmentFaculty) {
        this.departmentFaculty = departmentFaculty;
    }
}
