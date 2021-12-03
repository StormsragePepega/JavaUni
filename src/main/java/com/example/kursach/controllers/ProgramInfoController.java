package com.example.kursach.controllers;

import com.example.kursach.controllers.base.FormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Контроллер для вывода информации о программе
 *
 * @author K.I. Petrovsky
 * @version 1.2
 */
public class ProgramInfoController extends FormController {
    /**
     * обработчик нажатия кнопки закрытия окна
     *
     * @since 1.2
     */
    @FXML
    public void buttonClickClose(ActionEvent actionEvent) {
        prevStage.close();
    }
}
