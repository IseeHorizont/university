package ru.tusur.university;
import java.sql.*;
import java.util.ArrayList;

public class SQLHandler {
    private static Connection connection;
    private static Statement statement;

    // подключение драйвера для БД, установление соединения с БД
    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/univercity.db");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // отключение от БД
    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // find teachers from DB
    public static ArrayList<Teacher> findListOfTeachersByDepartmentInDB(int numberOfDepartment) {
        ArrayList<Teacher> resultList = new ArrayList<>();
        connect();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM teachers WHERE teacher_department='" + numberOfDepartment + "'");
            while (resultSet.next()) {
                resultList.add(new Teacher(
                        resultSet.getString("teacher_name"),
                        resultSet.getString("teacher_hire_date"),
                        resultSet.getString("teacher_position"),
                        resultSet.getInt("teacher_personal_number"),
                        resultSet.getInt("teacher_department")
                ));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultList;
    }

    // show name of department by TEACHER & all info about him
    public static Teacher findTeacherByName(String teacherName) {
        connect();
        Teacher teacher;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM teachers WHERE teacher_name='" + teacherName + "'");
            try {
                teacher = new Teacher(
                        resultSet.getString("teacher_name"),
                        resultSet.getString("teacher_hire_date"),
                        resultSet.getString("teacher_position"),
                        resultSet.getInt("teacher_personal_number"),
                        resultSet.getInt("teacher_department")
                );
            } catch (SQLException ex) {
                return null;
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return teacher;
    }

    // get name of department by its number
    public static String getDepartmentNameByNumber(int departmentNumber) {
        connect();
        String departmentName = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT department_name FROM departments WHERE department_id='" + departmentNumber + "'");
            departmentName = resultSet.getString("department_name");
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return departmentName;
    }

    // show list of SUBJECTS by DEPARTMENT
    public static ArrayList<Subject> findListOfSubjectsByDepartmentInDB(int deptId) {
        ArrayList<Subject> resultList = new ArrayList<>();
        connect();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT subject_name,subject_code,subject_semester,subject_major FROM subjects WHERE subject_department_id='" + deptId + "'");
            while (resultSet.next()) {
                resultList.add(new Subject(
                        resultSet.getString("subject_name"),
                        resultSet.getString("subject_code"),
                        resultSet.getInt("subject_semester"),
                        resultSet.getString("subject_major")
                ));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultList;
    }

    // show list of DEPARTMENT
    public static ArrayList<Department> findListOfDepartments() {
        ArrayList<Department> resultList = new ArrayList<>();
        connect();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");
            while (resultSet.next()) {
                resultList.add(new Department(
                        resultSet.getInt("department_id"),
                        resultSet.getString("department_name"),
                        resultSet.getString("department_head"),
                        resultSet.getString("department_faculty")
                ));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultList;
    }
    
}
