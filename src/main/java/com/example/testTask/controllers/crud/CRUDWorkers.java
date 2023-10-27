package com.example.testTask.controllers.crud;

import com.example.testTask.Connector;
import com.example.testTask.models.Worker;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CRUDWorkers {
    public static void insertWorker(Worker worker) {
        String insertPhone="INSERT INTO WORKERS (NAME,SURNAME,PATRONYMIC,ID_JOB_TITLE,ID_DEPARTMENT) VALUES (?,?,?,?,?)";
        try (PreparedStatement prSt = Connector.getConnection().prepareStatement(insertPhone);){
            prSt.setString(1, worker.getName());
            prSt.setString(2, worker.getSurname());
            prSt.setString(3, worker.getPatronymic());
            prSt.setInt(4, worker.getIdJobTitle());
            prSt.setInt(5, worker.getIdDepartment());
            prSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка в insert");
            throw new RuntimeException(e);
        }
    }

    public static void deleteStr(TableView workersTable) {
        SelectionModel<Worker> selectionModel = workersTable.getSelectionModel();
        Worker selectionWorker = selectionModel.getSelectedItem();
        String deleteQuery = "DELETE FROM WORKERS WHERE NAME = ? and SURNAME = ? and PATRONYMIC = ?";
        Connector.getConnection();
        try (PreparedStatement prSt = Connector.getConnection().prepareStatement(deleteQuery);){
            prSt.setString(1, selectionWorker.getName());
            prSt.setString(2, selectionWorker.getSurname());
            prSt.setString(3, selectionWorker.getPatronymic());
            prSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка в drop");
            throw new RuntimeException(e);
        }
        workersTable.getItems().remove(selectionWorker);
    }

    public static void updateStr(TableView workersTable, TextField nameField, TextField surnameField,
    TextField patronymicField,TextField jobTTitleField, TextField departmentField) {
        SelectionModel<Worker> selectionModel = workersTable.getSelectionModel();
        Worker selectionWorker = selectionModel.getSelectedItem();
        selectionWorker.setName(nameField.getText());
        selectionWorker.setSurname(surnameField.getText());
        selectionWorker.setPatronymic(patronymicField.getText());
        selectionWorker.setIdJobTitle(Integer.parseInt(jobTTitleField.getText()));
        selectionWorker.setIdDepartment(Integer.parseInt(departmentField.getText()));
        String updateQuery = "UPDATE WORKERS SET NAME = ?, SURNAME = ?, PATRONYMIC = ?," +
                "ID_JOB_TITLE = ?, ID_DEPARTMENT = ? WHERE ID_WORKER = ?";

        try (PreparedStatement prSt = Connector.getConnection().prepareStatement(updateQuery)){
            prSt.setString(1, selectionWorker.getName());
            prSt.setString(2, selectionWorker.getSurname());
            prSt.setString(3, selectionWorker.getPatronymic());
            prSt.setInt(4, selectionWorker.getIdJobTitle());
            prSt.setInt(5, selectionWorker.getIdDepartment());
            prSt.setInt(6, selectionWorker.getIdWorker());
            prSt.executeUpdate();
            workersTable.refresh();
        } catch (SQLException e) {
            System.out.println("Ошибка в UPDATE");
            throw new RuntimeException(e);
        }
    }
}
