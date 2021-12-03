package com.example.kursach.controllers;

import com.example.kursach.controllers.base.FormController;

import com.example.kursach.models.SystemOfEquations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Контроллер для работы с выводом решения СЛАУ
 *
 * @author K.I. Petrovsky
 */
public class SystemSolutionController extends FormController {


    /**
     * назначение СЛАУ
     *
     * @param system СЛАУ
     * @since 1.2
     */
    public void setSystem(SystemOfEquations system) {
        this.system = system;
    }

    /**
     * обработчик перехода на предыдущее окно
     *
     * @since 1.2
     */
    @FXML
    private void buttonClickBack(ActionEvent actionEvent) throws IOException {
        switchWindow("FillingTheMatrix.fxml", "Создание матрицы");
        prevStage.close();
    }

    /**
     * обработчик нажатия кнопки для перехода на следующее окно
     *
     * @since 1.4
     */
    @FXML
    private void buttonClickFurther(ActionEvent event) throws IOException {
        switchWindow("Greeting.fxml", "Титульный лист");
        prevStage.close();
    }

    /**
     * вывод в окно исходную СЛАУ и, если существуют, корни
     *
     * @since 1.6
     */
    public void initializeWindow() {
        resultLabel.setText(system.getFullInfo());
    }
    @FXML
    private Label resultLabel;
    private SystemOfEquations system;
}
