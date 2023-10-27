module com.example.testTask {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires java.sql;

    opens com.example.testTask to javafx.fxml;
    exports com.example.testTask;
    opens com.example.testTask.crud to javafx.fxml;
    opens com.example.testTask.models to javafx.base;
    exports com.example.testTask.scenes;
    opens com.example.testTask.scenes to javafx.fxml;
    exports com.example.testTask.controllers.crud;
    opens com.example.testTask.controllers.crud to javafx.fxml;
    exports com.example.testTask.controllers.navigation;
    opens com.example.testTask.controllers.navigation to javafx.fxml;

}