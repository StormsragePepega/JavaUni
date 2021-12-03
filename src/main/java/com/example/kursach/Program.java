package com.example.kursach;

import com.example.kursach.controllers.GreetingController;
import com.example.kursach.controllers.base.FormController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FormController controller = new GreetingController();
        controller.switchWindow("Greeting.fxml", "Титульный лист");
        //Закрыть программу, если пользователь бездействует
        controller.closeIfIdle(30);
    }
}
