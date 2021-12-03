package com.example.kursach.controllers.base;

import com.example.kursach.Program;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Абстрактный класс для контроллеров, содержащий поле текущего окна
 *
 * @author K.I. Petrovsky
 * @version 1.4
 */
public abstract class FormController {
    public Stage prevStage;
    private FormController controller;

    public FormController getController() {
        return controller;
    }

    /**
     * установка окна
     *
     * @param stage текущее окно
     * @since 1.2
     */
    public void setPrevStage(Stage stage) {
        this.prevStage = stage;
    }

    /**
     * инициализация нового окна
     *
     * @param resource путь к окну, которое надо открыть
     * @param title    название окна
     * @since 1.4
     */
    public void switchWindow(String resource, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Program.class.getResource(resource));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        FormController controller = fxmlLoader.getController();
        controller.setPrevStage(stage);
        stage.show();
        this.controller = controller;
    }

    /**
     * Закрывает окно, если пользователь бездействует
     *
     * @param time время бездействия
     * @since 1.2
     */
    public void closeIfIdle(int time) {
        PauseTransition delay = new PauseTransition(Duration.seconds(time));
        delay.setOnFinished(event -> controller.prevStage.close());
        delay.play();
    }
}
