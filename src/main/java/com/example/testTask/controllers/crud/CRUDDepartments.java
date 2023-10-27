package com.example.testTask.controllers.crud;

import com.example.testTask.Connector;
import com.example.testTask.models.Department;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CRUDDepartments {
    public static void insertDepartment(Department department) {
        String insertEmail="INSERT INTO DEPARTMENTS (DEPARTMENT,PHONE,EMAIL ) VALUES (?,?,?)";
        try (PreparedStatement prSt = Connector.getConnection().prepareStatement(insertEmail)){
            prSt.setString(1, department.getDepartment());
            prSt.setString(2, department.getPhone());
            prSt.setString(3, department.getEmail());
            prSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка в insert");
            throw new RuntimeException(e);
        }
    }

    public static void updateStr(TableView departmentTable, TextField emailField,TextField fieldDepartment, TextField phoneField) {
        SelectionModel<Department> selectionModel = departmentTable.getSelectionModel();
        Department selectionDepartment = selectionModel.getSelectedItem();
        //String email = selectionDepartment.getDepartment();
        selectionDepartment.setEmail(emailField.getText());
        selectionDepartment.setDepartment(fieldDepartment.getText());
        selectionDepartment.setPhone(phoneField.getText());
        String updateQuery = "UPDATE DEPARTMENTS SET DEPARTMENT = ?, PHONE = ?,EMAIL = ?  WHERE DEPARTMENT = ?";

        try (PreparedStatement prSt = Connector.getConnection().prepareStatement(updateQuery);){
            prSt.setString(1, selectionDepartment.getDepartment());
            prSt.setString(4, selectionDepartment.getDepartment());
            prSt.setString(2, selectionDepartment.getPhone());
            prSt.setString(3, selectionDepartment.getEmail());

            prSt.executeUpdate();
            departmentTable.refresh();
        } catch (SQLException e) {
            System.out.println("Ошибка в UPDATE");
            throw new RuntimeException(e);
        }
    }

    public static void deleteStr(TableView departmentTable) {
        SelectionModel<Department> selectionModel  = departmentTable.getSelectionModel();
        Department selectionDepartment = selectionModel.getSelectedItem();
        String department = selectionDepartment.getDepartment();
        String deleteQuery = "DELETE FROM DEPARTMENTS WHERE DEPARTMENT = ?";
        try (PreparedStatement prSt = Connector.getConnection().prepareStatement(deleteQuery)){
            prSt.setString(1, department);
            prSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка в delete");
            throw new RuntimeException(e);
        }
        departmentTable.getItems().remove(selectionDepartment);
    }
}
