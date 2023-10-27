package com.example.testTask.controllers.crud;

import com.example.testTask.Connector;
import com.example.testTask.models.JobTitle;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CRUDJobTitles {

    public static void insertJobTitle(JobTitle jobTitle){
        String insertJobTitle="INSERT INTO JOB_TITLES (JOB_TITLE, SALARY) VALUES (?, ?)";
        try (PreparedStatement prSt = Connector.getConnection().prepareStatement(insertJobTitle)){
            prSt.setString(1, jobTitle.getJob_Title());
            prSt.setInt(2, jobTitle.getSalary());
            prSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка в insert");
            throw new RuntimeException(e);
        }
    }

    public static void updateStr(TableView<JobTitle> jobTitleTable, TextField fieldJobTitle,TextField fieldSalary) {
        SelectionModel<JobTitle> selectionModel = jobTitleTable.getSelectionModel();
        JobTitle selectionJobTitle = selectionModel.getSelectedItem();
        String jobTitle = selectionJobTitle.getJob_Title();
        selectionJobTitle.setJob_Title(fieldJobTitle.getText());
        selectionJobTitle.setSalary(Integer.parseInt(fieldSalary.getText()));
        String updateQuery = "UPDATE JOB_TITLES SET JOB_TITLE = ?, SALARY = ? WHERE JOB_TITLE = ?";

        try (PreparedStatement prSt = Connector.getConnection().prepareStatement(updateQuery);){
            prSt.setString(1, selectionJobTitle.getJob_Title());
            prSt.setString(2, String.valueOf(selectionJobTitle.getSalary()));
            prSt.setString(3, jobTitle);
            prSt.executeUpdate();
            jobTitleTable.refresh();
        } catch (SQLException e) {
            System.out.println("Ошибка в UPDATE");
            throw new RuntimeException(e);
        }
    }

    public static void deleteStr(TableView<JobTitle> jobTitleTable) {
        SelectionModel<JobTitle> selectionModel  = jobTitleTable.getSelectionModel();
        JobTitle selectionJobTitle = selectionModel.getSelectedItem();
        String jobTitle = selectionJobTitle.getJob_Title();
        String deleteQuery = "DELETE FROM JOB_TITLES WHERE JOB_TITLE = ?";
        Connector.getConnection();
        try (PreparedStatement prSt = Connector.getConnection().prepareStatement(deleteQuery);){
            prSt.setString(1, jobTitle);
            prSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка в delete");
            throw new RuntimeException(e);
        }
        jobTitleTable.getItems().remove(selectionJobTitle);
    }
}
