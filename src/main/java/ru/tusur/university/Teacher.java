package ru.tusur.university;

public class Teacher {
    private String fullName;
    private String hireDate;
    private String position;
    private int personalNumber;
    private int numberOfDepartment;

    public Teacher(String fullName, String hireDate, String position, int personalNumber, int numberOfDepartment) {
        this.fullName = fullName;
        this.hireDate = hireDate;
        this.position = position;
        this.personalNumber = personalNumber;
        this.numberOfDepartment = numberOfDepartment;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }

    public int getNumberOfDepartment() {
        return numberOfDepartment;
    }

    public void setNumberOfDepartment(int numberOfDepartment) {
        this.numberOfDepartment = numberOfDepartment;
    }
}
