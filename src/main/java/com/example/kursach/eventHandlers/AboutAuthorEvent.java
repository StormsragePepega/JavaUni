package com.example.kursach.eventHandlers;

import com.example.kursach.controllers.AuthorController;
import com.example.kursach.controllers.base.FormController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

import java.io.IOException;

/**
 * Класс обработчика нажатия кнопки для открытия окна информации о авторе
 *
 * @since 1.4
 */
public class AboutAuthorEvent implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            FormController controller = new AuthorController();
            controller.switchWindow("Author.fxml", "Об авторе");
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не удалось открыть информацию об авторе");
            alert.showAndWait();
        }
    }
}
