package com.example.kursach.services;

import com.example.kursach.interfaces.FileService;
import com.example.kursach.models.SystemOfEquations;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class FileSystemOfEquationService implements FileService {
    @Override
    public void saveToFile(Stage stage, String text) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Resource File");
        // Set extension filter
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt"));
        File file = fileChooser.showSaveDialog(stage);
        try (FileWriter writer = new FileWriter(file.getAbsolutePath(), false)) {
            // запись текста
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String openFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        // устанавливаем расширения файлов для выбора
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(stage);
        StringBuilder msg = new StringBuilder();
        // Открываем файл для чтения
        // В качестве объекта передаем открытый файл на основе класса FileReader
        try (FileReader reader = new FileReader(file.getAbsolutePath())) {
            BufferedReader br = new BufferedReader(reader);
            // Считываем данные
            String line;
            while ((line = br.readLine()) != null) {
                msg.append(line).append("\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        msg = new StringBuilder(msg.substring(0, msg.length() - 1));
        return msg.toString();
    }

    public SystemOfEquations getSystemFromFile(Stage stage) {
        String msg = openFile(stage).replaceAll(",", ".").replaceAll("[^0-9=.\\-\n* ]", "");
        msg = msg.trim().replaceAll(" +", " ");
        int n = msg.split("\n")[0].split("=")[0].split("\\s+").length;
        double[] results = new double[n];
        double[][] matrix = new double[n][n];
        int i = 0;
        for (String str : msg.split("\n")) {
            if (i < n) {
                results[i] = Double.parseDouble(str.split("=")[1]);
                String strCoefficients = str.split("=")[0];
                int j = 0;
                for (String coefficient : strCoefficients.split("\\s+")) {
                    matrix[i][j] = Double.parseDouble(coefficient.split("\\*")[0]);
                    j++;
                }
                i++;
            } else
                break;
        }
        return new SystemOfEquations(matrix, results);
    }
}