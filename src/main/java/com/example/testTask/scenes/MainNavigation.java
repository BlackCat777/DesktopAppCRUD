package com.example.testTask.scenes;

import com.example.testTask.controllers.navigation.CreateScene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class MainNavigation {
    public Button jobButton;
    public Button departmentsButton;
    public Button workersButton;
    public Button leadsButton;

    public void JobTitleUpdateClick() {
        CreateScene.goToNewScene(jobButton, "/com/example/testTask/scenes/crud/CRUDJobTitles.fxml",440,280);
    }


    public void departmentsButtonClick() {
        CreateScene.goToNewScene(departmentsButton, "/com/example/testTask/scenes/crud/CRUDDepartments.fxml",730,270);
    }

    public void workersButtonClick() {
        CreateScene.goToNewScene(workersButton, "/com/example/testTask/scenes/crud/CRUDWorkers.fxml",1100,420);
    }

    public void leadsButtonClick(ActionEvent actionEvent) {
        CreateScene.goToNewScene(leadsButton, "/com/example/testTask/scenes/crud/CRUDLeads.fxml",440,280);
    }
}
