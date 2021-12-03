package com.example.kursach.controllers;

import com.example.kursach.controllers.base.FormController;
import com.example.kursach.eventHandlers.AboutProgramEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Контроллер для работы с окном запуска
 *
 * @author K.I. Petrovsky
 * @version 1.4
 */
public class GreetingController extends FormController implements Initializable {



    /**
     * обработчик нажатия кнопки для перехода на следующее окно
     *
     * @since 1.4
     */
    @FXML
    public void buttonClickFurther(ActionEvent event) throws IOException {
        switchWindow("FillingTheMatrix.fxml", "Заполнение матрицы");
        prevStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        programInfoButton.setOnAction(new AboutProgramEvent());
    }

    public void exitAction(ActionEvent actionEvent) {

            System.exit(0);

    }
    public Button programInfoButton;
    public Button exitButton;
}