package ru.tusur.university;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Controller {

    @FXML
    private TextField inputDepartmentField;

    private ObservableList<Teacher> teachers = FXCollections.observableArrayList();
    private ObservableList<Subject> subjects = FXCollections.observableArrayList();

    @FXML
    private TableView<Teacher> teacherTable;

    @FXML
    private TableColumn<Teacher, String> fullName;

    @FXML
    private TableColumn<Teacher, String> hireDate;

    @FXML
    private TableColumn<Teacher, String> position;

    @FXML
    private TableColumn<Teacher, Integer> personalNumber;

    @FXML
    private TableColumn<Teacher, Integer> numberOfDepartment;

    @FXML
    private TextField inputTeacherName;

    @FXML
    private Text deptName;

    @FXML
    private Text deptNumber;

    @FXML
    private Text teacherName;

    @FXML
    private Text teacherPosition;

    @FXML
    private Text teacherNumber;

    @FXML
    private Text teacherHireDate;

    @FXML
    private TextField inputDepartmentNumber;

    @FXML
    private TableView<Subject> subjectsTable;

    @FXML
    private TableColumn<Subject, String> subjectName;

    @FXML
    private TableColumn<Subject, String> subjectCode;

    @FXML
    private TableColumn<Subject, Integer> subjectSemester;

    @FXML
    private TableColumn<Subject, String> subjectMajor;

    private ObservableList<Department> departments = FXCollections.observableArrayList();

    @FXML
    private TableView<Department> departmentTable;

    @FXML
    private TableColumn<Department, Integer> departmentNumber;

    @FXML
    private TableColumn<Department, String> departmentName;

    @FXML
    private TableColumn<Department, String> departmentHead;

    @FXML
    private TableColumn<Department, String> departmentFaculty;


    @FXML
    public void openWindowForShowListOfTeachersByDepartment() throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader newLoader = new FXMLLoader(getClass().getResource("showListTeachers.fxml"));
        Scene scene = new Scene(newLoader.load(), 750, 500);
        stage.setScene(scene);
        stage.setTitle("Списки преподавателей");
        stage.showAndWait();
    }

    @FXML
    public void showListOfTeachersByDepartment() {
        teacherTable.getItems().clear();
        Integer currentNumberOfDepartment = 0;
        try {
            currentNumberOfDepartment = Integer.parseInt(inputDepartmentField.getText().trim());
        } catch (Exception ex) {
            printAlert("Ошибка ввода текста", "Введите в поле целочисленный номер кафедры");
            return;
        }
        fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        hireDate.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        personalNumber.setCellValueFactory(new PropertyValueFactory<>("personalNumber"));
        numberOfDepartment.setCellValueFactory(new PropertyValueFactory<>("numberOfDepartment"));
        // we're getting ArrayList of teachers from DB
        List<Teacher> allFoundTeachers = SQLHandler.findListOfTeachersByDepartmentInDB(currentNumberOfDepartment);
        if (allFoundTeachers.isEmpty()) {
            printAlert("Ошибка ввода", "Неверный номер кафедры");
        }
        teachers.addAll(allFoundTeachers);
        teacherTable.setItems(teachers);
    }

    @FXML
    public void openWindowForShowAllAboutTeacherByName() throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader newLoader = new FXMLLoader(getClass().getResource("findAllAboutTeacherByName.fxml"));
        Scene scene = new Scene(newLoader.load(), 600, 500);
        stage.setScene(scene);
        stage.setTitle("Сведения о преподавателях");
        stage.showAndWait();
    }

    @FXML
    public void showAllAboutTeacherByName() {
        String currentTeacherName = inputTeacherName.getText().trim();
        if (currentTeacherName == null || currentTeacherName.isEmpty()) {
            printAlert("Ошибка ввода", "ФИО преподавателя не введены");
            return;
        }
        // to get 'inputTeacherName', go to DB which return object 'teacher'
        Teacher currentTeacher = SQLHandler.findTeacherByName(currentTeacherName);
        if (currentTeacher == null) {
            printAlert("Ошибка поиска", "Преподаватель не найден или его ФИО введены неверно...");
            return;
        }
        // need to find 'name of department' by 'number of department'
        String deptNameFromDB = SQLHandler.getDepartmentNameByNumber(currentTeacher.getNumberOfDepartment());
        deptName.setText("Кафедра " + deptNameFromDB);
        deptNumber.setText("Номер кафедры: " + currentTeacher.getNumberOfDepartment());
        teacherName.setText("ФИО преподавателя: " + currentTeacher.getFullName());
        teacherPosition.setText("Должность: " + currentTeacher.getPosition());
        teacherNumber.setText("Персональный номер: " + currentTeacher.getPersonalNumber());
        teacherHireDate.setText("Дата трудоустройста: " + currentTeacher.getHireDate());
    }

    @FXML
    public void openWindowForShowSubjectsByDepartment() throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader newLoader = new FXMLLoader(getClass().getResource("findAllSubjectsByDepartment.fxml"));
        Scene scene = new Scene(newLoader.load(), 750, 500);
        stage.setScene(scene);
        stage.setTitle("Дисциплины кафедры");
        stage.showAndWait();
    }

    @FXML
    public void showSubjectsByDepartment() {
        subjectsTable.getItems().clear();
        Integer currentDepartmentNumber = 0;
        try {
            currentDepartmentNumber = Integer.parseInt(inputDepartmentNumber.getText().trim());
        } catch (Exception ex) {
            printAlert("Ошибка ввода текста", "Введите в поле целочисленный номер кафедры");
            return;
        }
        subjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        subjectCode.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        subjectSemester.setCellValueFactory(new PropertyValueFactory<>("subjectSemester"));
        subjectMajor.setCellValueFactory(new PropertyValueFactory<>("subjectMajor"));
        // we're getting list of subjects from DB
        ArrayList<Subject> subjectsFromDB = SQLHandler.findListOfSubjectsByDepartmentInDB(currentDepartmentNumber);
        if (subjectsFromDB.isEmpty()) {
            printAlert("Ошибка ввода", "Неверный номер кафедры. Проверьте правильность ввода");
            return;
        }
        subjects.addAll(subjectsFromDB);
        subjectsTable.setItems(subjects);
    }

    @FXML
    public void openWindowForShowListOfDepartment() throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader newLoader = new FXMLLoader(getClass().getResource("showListOfDepartment.fxml"));
        Scene scene = new Scene(newLoader.load(), 800, 500);
        stage.setScene(scene);
        stage.setTitle("Кафедры университета");
        stage.showAndWait();
    }

    @FXML
    public void showListOfDepartment() {
        departmentTable.getItems().clear();
        departmentNumber.setCellValueFactory(new PropertyValueFactory<>("departmentNumber"));
        departmentName.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
        departmentHead.setCellValueFactory(new PropertyValueFactory<>("departmentHead"));
        departmentFaculty.setCellValueFactory(new PropertyValueFactory<>("departmentFaculty"));
        // departments from DB
        List<Department> allDepartments = SQLHandler.findListOfDepartments();
        departments.addAll(allDepartments);
        departmentTable.setItems(departments);
    }

    // метод вывода окна ошибки с пояснениями о причинах её возникновения
    private static void printAlert(String title, String messageToUser){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(messageToUser);
        alert.showAndWait();
    }

    // выход из программы по вкладке 'выход'
    public void exitMenu(ActionEvent actionEvent){
        Platform.runLater(() ->{
            System.exit(0);
        });
    }

    // показ информации о приложении по вкладке 'о нас'
    public void showAboutUs(ActionEvent actionEvent) throws FileNotFoundException {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setHeaderText("О проекте");
        info.setResizable(true);
        info.getDialogPane().setMinWidth(500);
        Scanner scanner = new Scanner(new File("src/main/resources/ru/tusur/university/About.txt"));
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNext()) {
            sb.append(scanner.nextLine()).append("\n");
        }
        info.setContentText(sb.toString());
        scanner.close();
        info.show();
    }
}
