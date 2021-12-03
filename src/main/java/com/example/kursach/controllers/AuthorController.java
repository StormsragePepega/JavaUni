package com.example.kursach.controllers;

import com.example.kursach.controllers.base.FormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Контроллер для работы с окном информации об авторе
 *
 * @author K.I. Petrovsky
 * @version 1.4
 */
public class AuthorController extends FormController {

    /**
     * обработчик закрытия окна
     *
     * @since 1.2
     */
    @FXML
    public void buttonClickClose(ActionEvent actionEvent) {
        prevStage.close();
    }

}
