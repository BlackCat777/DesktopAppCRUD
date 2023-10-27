package com.example.testTask.controllers.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateScene {
    public static void goToNewScene(Button button,String path, int v, int v1){
        button.setOnAction(actionEvent -> {
            button.getScene().getWindow().hide();
            Parent root;
            try {
                root = FXMLLoader.load(CreateScene.class.getResource(
                        path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Scene scene = new Scene(root, v, v1);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Main");
            primaryStage.setScene(scene);
            primaryStage.show();
        });
    }
}
