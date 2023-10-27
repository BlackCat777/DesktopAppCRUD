package com.example.testTask.controllers.crud;

import com.example.testTask.Connector;
import com.example.testTask.models.JobTitle;
import com.example.testTask.models.Lead;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CRUDLeads {
    public static void insertLead(Lead lead){
        String insertJobTitle="INSERT INTO LEADS (ID_WORKER, ID_DEPARTMENT) VALUES (?, ?)";
        try (PreparedStatement prSt = Connector.getConnection().prepareStatement(insertJobTitle)){
            prSt.setInt(1, lead.getIdWorker());
            prSt.setInt(2, lead.getIdDepartment());
            prSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка в insert");
            throw new RuntimeException(e);
        }
    }

    public static void updateStr(TableView<Lead> leadsTable, TextField workerField, TextField departmentField) {
        SelectionModel<Lead> selectionModel = leadsTable.getSelectionModel();
        Lead selectionLead = selectionModel.getSelectedItem();
        selectionLead.setIdDepartment(Integer.parseInt(workerField.getText()));
        selectionLead.setIdWorker(Integer.parseInt(departmentField.getText()));
        String updateQuery = "UPDATE LEADS SET ID_WORKER = ?, ID_DEPARTMENT = ? WHERE ID_LEAD = ?";

        try (PreparedStatement prSt = Connector.getConnection().prepareStatement(updateQuery)){
            prSt.setInt(1, selectionLead.getIdWorker());
            prSt.setInt(2, selectionLead.getIdDepartment());
            prSt.setInt(3, selectionLead.getIdLead());
            prSt.executeUpdate();
            leadsTable.refresh();
        } catch (SQLException e) {
            System.out.println("Ошибка в UPDATE");
            throw new RuntimeException(e);
        }
    }

    public static void deleteStr(TableView<Lead> leadsTable) {
        SelectionModel<Lead> selectionModel  = leadsTable.getSelectionModel();
        Lead selectionLead = selectionModel.getSelectedItem();
        String deleteQuery = "DELETE FROM LEADS WHERE ID_LEAD = ?";
        try (PreparedStatement prSt = Connector.getConnection().prepareStatement(deleteQuery);){
            prSt.setInt(1, selectionLead.getIdLead());
            prSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка в delete");
            throw new RuntimeException(e);
        }
        leadsTable.getItems().remove(selectionLead);
    }
}
