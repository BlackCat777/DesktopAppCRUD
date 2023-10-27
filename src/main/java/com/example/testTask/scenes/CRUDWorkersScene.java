package com.example.testTask.scenes;

import com.example.testTask.controllers.crud.CrudController;
import com.example.testTask.controllers.navigation.GoToUpdateNavigation;
import com.example.testTask.controllers.crud.CRUDWorkers;
import com.example.testTask.models.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CRUDWorkersScene implements Initializable {
    public TextField patronymicField;
    public Button backButton;
    public TextField departmentField;
    public TextField surnameField;
    public TextField jobTTitleField;
    public TableColumn idColumn;
    public TableColumn nameColumn;
    public TableColumn surnameColumn;
    public TableColumn patronymicColumn;
    public TableColumn departmentColumn;
    public TableColumn jobTitleColumn;
    public TableView workersTable;
    public Button addWorkerButton;
    private final ObservableList<Worker> list = FXCollections.observableArrayList();
    public TextField nameField;

    private void addInfAboutWorkers() {
        try (ResultSet workers = CrudController.getTable("WORKERS")){
            while (workers.next()) {
                Worker worker = new Worker(
                        workers.getInt(1),
                        workers.getString(2),
                        workers.getString(3),
                        workers.getString(4),
                        workers.getInt(5),
                        workers.getInt(6));
                list.add(worker);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка addInfAbout");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addInfAboutWorkers();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idWorker"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        patronymicColumn.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        jobTitleColumn.setCellValueFactory(new PropertyValueFactory<>("IdJobTitle"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("IdDepartment"));
        workersTable.setItems(list);

        workersTable.setOnMouseClicked(event -> {
            Worker selectedWorker = (Worker) workersTable.getSelectionModel().getSelectedItem();
            nameField.setText(selectedWorker.getName());
            surnameField.setText(selectedWorker.getSurname());
            patronymicField.setText(selectedWorker.getPatronymic());
            departmentField.setText(String.valueOf(selectedWorker.getIdDepartment()));
            jobTTitleField.setText(String.valueOf(selectedWorker.getIdJobTitle()));
        });
    }

    public void addWorkerClick() {
        addWorkerButton.setOnAction(actionEvent -> {
            Worker worker = new Worker(nameField.getText(),
                    surnameField.getText(),
                    patronymicField.getText(),
                    Integer.parseInt(jobTTitleField.getText()),
                    Integer.parseInt(departmentField.getText()));
            list.add(worker);
            CRUDWorkers.insertWorker(worker);
        });
    }

    public void deleteStr(ActionEvent actionEvent) {
        CRUDWorkers.deleteStr(workersTable);
    }

    public void updateStr(ActionEvent actionEvent) {
        CRUDWorkers.updateStr(workersTable, nameField,surnameField,patronymicField, jobTTitleField,departmentField);
    }

    public void goToUpdateNavigation(ActionEvent actionEvent) {
        GoToUpdateNavigation.goToUpdateNavigation(backButton);
    }
}
