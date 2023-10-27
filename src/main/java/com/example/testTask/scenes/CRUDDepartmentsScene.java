package com.example.testTask.scenes;

import com.example.testTask.controllers.crud.CrudController;
import com.example.testTask.controllers.crud.CRUDDepartments;
import com.example.testTask.controllers.navigation.GoToUpdateNavigation;
import com.example.testTask.models.Department;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CRUDDepartmentsScene implements Initializable {
    public TextField fieldDepartment;
    public TextField emailField;
    public Button addDepartmentButton;
    public Button backButton;
    public TextField phoneField;

    public TableColumn departmentColumn;
    public TableColumn phoneColumn;
    public TableColumn emailColumn;
    public TableView departmentTable;
    public TableColumn iD_Department;
    private final ObservableList<Department> list = FXCollections.observableArrayList();

    private void addInfAboutDepartment() {
        try (ResultSet departments = CrudController.getTable("DEPARTMENTS")){
            while (departments.next()) {
                Department department = new Department(departments.getInt(1),
                        departments.getString(2),
                        departments.getString(3),
                        departments.getString(4));
                list.add(department);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка addInfAboutEmail");
            throw new RuntimeException(e);
        }
    }
    public void addDepartmentClick() {
        addDepartmentButton.setOnAction(actionEvent -> {
            Department department = new Department(fieldDepartment.getText(),
                    phoneField.getText(),
                    emailField.getText());
            list.add(department);
            CRUDDepartments.insertDepartment(department);
        });
    }

    public void goToUpdateNavigation() {
        GoToUpdateNavigation.goToUpdateNavigation(backButton);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addInfAboutDepartment();
        iD_Department.setCellValueFactory(new PropertyValueFactory<>("id_Department"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        departmentTable.setItems(list);

        departmentTable.setOnMouseClicked(event -> {
            Department selectedDepartment = (Department) departmentTable.getSelectionModel().getSelectedItem();
            fieldDepartment.setText(selectedDepartment.getDepartment());
            emailField.setText(selectedDepartment.getEmail());
            phoneField.setText(selectedDepartment.getPhone());
        });
    }

    public void updateStr() {
       CRUDDepartments.updateStr(departmentTable,emailField,fieldDepartment,phoneField);
    }

    public void deleteStr() {
        CRUDDepartments.deleteStr(departmentTable);
    }
}
