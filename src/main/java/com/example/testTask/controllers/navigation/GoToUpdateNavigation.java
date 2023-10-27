package com.example.testTask.controllers.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GoToUpdateNavigation {
    public static void goToUpdateNavigation(Button button){
        button.setOnAction(actionEvent -> {
            button.getScene().getWindow().hide();
            Parent root;
            try {
                root = FXMLLoader.load(GoToUpdateNavigation.class.getResource(
                        "/com/example/testTask/scenes/MainNavigation.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Scene scene = new Scene(root, 300, 350);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Main");
            primaryStage.setScene(scene);
            primaryStage.show();
        });
    }
}
