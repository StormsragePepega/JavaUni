package com.example.kursach.interfaces;

import javafx.stage.Stage;

public interface FileService {
    void saveToFile(Stage stage, String text);
    String openFile(Stage stage);
}
