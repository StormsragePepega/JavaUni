package com.example.kursach.eventHandlers;

import com.example.kursach.controllers.AuthorController;
import com.example.kursach.controllers.base.FormController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

import java.io.IOException;

/**
 * Класс обработчика нажатия кнопки для открытия окна информации о программе
 *
 * @since 1.4
 */
public class AboutProgramEvent implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            FormController controller = new AuthorController();
            controller.switchWindow("ProgramInfo.fxml", "О программе");
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не удалось открыть информацию о программе");
            alert.showAndWait();
        }
    }
}
