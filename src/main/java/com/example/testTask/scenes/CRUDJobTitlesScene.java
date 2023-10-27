package com.example.testTask.scenes;

import com.example.testTask.controllers.crud.CrudController;
import com.example.testTask.controllers.crud.CRUDJobTitles;
import com.example.testTask.controllers.navigation.GoToUpdateNavigation;
import com.example.testTask.models.JobTitle;
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

public class CRUDJobTitlesScene implements Initializable {


    public TextField fieldJobTitle;
    public TextField fieldSalary;
    public Button backButton;
    public Button addJobTitleButton;
    public TableView<JobTitle> jobTitleTable;
    public TableColumn<Object, Object> jobTitleColumn;
    public TableColumn<Object, Object> salaryColumn;

    public TableColumn<Object, Object> idColumn;
    private final ObservableList<JobTitle> list = FXCollections.observableArrayList();

    public void backToUpdateNavigation(ActionEvent actionEvent) {
        GoToUpdateNavigation.goToUpdateNavigation(backButton);
    }

    public void addJobTitleAction() {
        addJobTitleButton.setOnAction(actionEvent -> {
            JobTitle jobTitle = new JobTitle(fieldJobTitle.getText(), Integer.parseInt(fieldSalary.getText()));
            list.add(jobTitle);
            CRUDJobTitles.insertJobTitle(jobTitle);
        });
    }

    private void addInfAbout() {

        try (ResultSet jobTitles = CrudController.getTable("JOB_TITLES");){
            while (jobTitles.next()) {
                JobTitle jobTitle = new JobTitle(jobTitles.getInt(1),
                        jobTitles.getString(2),
                        jobTitles.getInt(3));
                list.add(jobTitle);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка addInfAboutEmail");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addInfAbout();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_Title"));
        jobTitleColumn.setCellValueFactory(new PropertyValueFactory<>("job_Title"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        jobTitleTable.setItems(list);

        jobTitleTable.setOnMouseClicked(event -> {
            JobTitle selectedHobTitle = jobTitleTable.getSelectionModel().getSelectedItem();
            fieldJobTitle.setText(selectedHobTitle.getJob_Title());
            fieldSalary.setText(String.valueOf(selectedHobTitle.getSalary()));
        });
    }

    public void updateStr(ActionEvent actionEvent) {
        CRUDJobTitles.updateStr(jobTitleTable,fieldJobTitle,fieldSalary);
    }

    public void deleteStr(ActionEvent actionEvent) {
    CRUDJobTitles.deleteStr(jobTitleTable);
    }
}
