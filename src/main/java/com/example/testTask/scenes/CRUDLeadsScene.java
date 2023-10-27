package com.example.testTask.scenes;

import com.example.testTask.controllers.crud.CRUDLeads;
import com.example.testTask.controllers.crud.CrudController;
import com.example.testTask.controllers.navigation.GoToUpdateNavigation;
import com.example.testTask.models.Lead;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CRUDLeadsScene implements Initializable {
    public TextField idWorkerField;
    public TextField idDepartmentField;
    public TableView leadsTable;
    public TableColumn idLeadColumn;
    public TableColumn idWorkerColumn;
    public TableColumn idDepartmentColumn;
    public Button backButton;
    public Button addLeadButton;
    private final ObservableList<Lead> list = FXCollections.observableArrayList();


    public void backToUpdateNavigation(ActionEvent actionEvent) {
        GoToUpdateNavigation.goToUpdateNavigation(backButton);
    }

    public void addStr() {
        addLeadButton.setOnAction(actionEvent -> {
            Lead lead = new Lead(Integer.parseInt(idWorkerField.getText()), Integer.parseInt(idDepartmentField.getText()));
            list.add(lead);
            CRUDLeads.insertLead(lead);
        });
    }

    public void updateStr() {
        CRUDLeads.updateStr(leadsTable,idWorkerField,idDepartmentField);
    }

    public void deleteStr() {
        CRUDLeads.deleteStr(leadsTable);
    }

    private void addInfAbout() {

        try (ResultSet leads = CrudController.getTable("LEADS");){
            while (leads.next()) {
                Lead lead = new Lead(leads.getInt(1),
                        leads.getInt(2),
                        leads.getInt(3));
                list.add(lead);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка addInfAboutEmail");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addInfAbout();
        idLeadColumn.setCellValueFactory(new PropertyValueFactory<>("idLead"));
        idWorkerColumn.setCellValueFactory(new PropertyValueFactory<>("idWorker"));
        idDepartmentColumn.setCellValueFactory(new PropertyValueFactory<>("idDepartment"));
        leadsTable.setItems(list);

        leadsTable.setOnMouseClicked(event -> {
            Lead selectedLead = (Lead) leadsTable.getSelectionModel().getSelectedItem();
            idDepartmentField.setText(String.valueOf(selectedLead.getIdDepartment()));
            idWorkerField.setText(String.valueOf(selectedLead.getIdWorker()));
        });
    }
}
