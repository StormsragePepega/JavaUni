package com.example.kursach.controllers;

import com.example.kursach.controllers.base.FormController;
import com.example.kursach.eventHandlers.AboutAuthorEvent;
import com.example.kursach.eventHandlers.AboutProgramEvent;
import com.example.kursach.interfaces.FileService;
import com.example.kursach.models.SystemOfEquations;
import com.example.kursach.services.FileSystemOfEquationService;
import components.NumberField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Контроллер для работы с окном ввода коэффициентов СЛАУ
 *
 * @author K.I. Petrovsky
 * @version 1.8
 */
public class FillingTheMatrixController extends FormController implements Initializable {

    /**
     * установка размера матрицы
     *
     * @since 1.4
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * обработчик нажатия кнопки для перехода на предыдущее окно
     *
     * @since 1.2
     */
    public void buttonClickBack(ActionEvent actionEvent) throws IOException {
        switchWindow("Greeting.fxml", "Титульный лист");
        prevStage.close();
    }

    /**
     * обработчик перехода на следующее окно
     *
     * @since 1.8
     */
    public void buttonClickFurther(ActionEvent actionEvent) {
        // проверка на заполнение матрицы
        try {
            SystemOfEquations system = getSystem();
            switchWindow("SystemSolution.fxml", "Решение системы");
            SystemSolutionController controller = (SystemSolutionController) super.getController();
            controller.setSystem(system);
            controller.initializeWindow();
            prevStage.close();
        } catch (Exception ex) {
            invalidMatrixInput.setText("Заполните все ячейки");
        }
    }

    /**
     * заполняет пустыми полями ввода сетку
     *
     * @since 1.6
     */
    private void initializeGridPane() {
        // очищаем панель полей ввода матрицы
        gridPane.getChildren().retainAll(gridPane.getChildren().get(0));
        // инициализируем матрицу с новым размером
        boxes = new NumberField[size][size + 1];
        // заполняем панель новыми полями ввода
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size + 1; col++) {
                NumberField newBox = createNumberField(col);
                boxes[row][col] = newBox;
                gridPane.add(newBox, col, row);
            }
        }
    }

    /**
     * заполняет поля ввода сетки системой
     *
     * @since 1.6
     */
    private void initializeGridPane(SystemOfEquations system) {
        setSize(system.coefficients.length);
        initializeGridPane();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size + 1; col++) {
                if (col < size) {
                    boxes[row][col].setText(String.valueOf(system.coefficients[row][col]));
                } else {
                    boxes[row][col].setText(String.valueOf(system.results[row]));
                }
            }
        }
    }

    /**
     * получаем результаты СЛАУ из ячеек на форме
     *
     * @return результаты СЛАУ
     * @since 1.8
     */
    private double[] getResultsFromBoxes() {
        double[] results = new double[size];
        int i = 0;
        // проходимся только по строкам
        for (NumberField[] box : boxes) {
            // получаем последний элемент в массиве
            results[i] = Double.parseDouble(box[size].getText());
            i++;
        }
        return results;
    }

    /**
     * получаем коэффициенты неизвестных СЛАУ из ячеек на форме
     *
     * @return коэффициенты неизвестных СЛАУ
     * @since 1.8
     */
    private double[][] getMatrixFromBoxes() {
        double[][] matrix = new double[size][size + 1];
        // проходимся по строкам
        for (int row = 0; row < size; row++) {
            // проходимся по элементам строки
            for (int col = 0; col < size; col++) {
                matrix[row][col] = Double.parseDouble(boxes[row][col].getText());
            }
        }
        return matrix;
    }

    /**
     * Создаёт СЛАУ по введёнными коэффициентам
     *
     * @return СЛАУ
     * @since 1.8
     */
    private SystemOfEquations getSystem() {
        // создаём матрицу коэффициентов
        double[][] matrix = getMatrixFromBoxes();
        double[] results = getResultsFromBoxes();
        //создаём СЛАУ из массива коэффициентов и результатов
        return new SystemOfEquations(matrix, results);
    }

    /**
     * создание ячейки ввода
     *
     * @param col столбец таблицы, куда будет вставлено поле
     * @return поле для ввода чисел
     * @since 1.6
     */
    private NumberField createNumberField(int col) {
        NumberField box = new NumberField();
        // выбираем ячейку бокса в зависимости расположения в строке
        // красным цветом выделяем ячейки результатов
        String style = col != size ? null : "-fx-background-color:#ba4545;";
        box.setStyle(style);
        Font font = new Font(22);
        box.setFont(font);
        box.setMaxWidth(Double.MAX_VALUE);
        box.setMaxHeight(Double.MAX_VALUE);
        GridPane.setHgrow(box, Priority.ALWAYS);
        GridPane.setVgrow(box, Priority.ALWAYS);
        // отступ
        GridPane.setMargin(box, new Insets(15));
        return box;
    }

    /**
     * проверка введённого размера матрицы и её создание
     *
     * @since 1.2
     */
    public void createMatrix(ActionEvent actionEvent) {
        try {
            //проверка на ввод пользователя размера матрицы
            if (Integer.parseInt(numberSizeField.getText()) <= 1) {
                invalidSizeInput.setText("Введите число большее или равное 2");
            } else if (Integer.parseInt(numberSizeField.getText()) > 8) {
                invalidSizeInput.setText("Введите число меньшее или равное 8");
            } else {
                setSize(Integer.parseInt(numberSizeField.getText()));
                initializeGridPane();
                invalidSizeInput.setText(null);
            }
        } catch (Exception e) {
            invalidSizeInput.setText("Введите целое число");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FileSystemOfEquationService fileService = new FileSystemOfEquationService();
        EventHandler<ActionEvent> openEvent = event -> {
            try {
                initializeGridPane(fileService.getSystemFromFile(prevStage));
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Не удалось загрузить СЛАУ");
                alert.setContentText("Попробуйте выбрать другой файл");
                alert.showAndWait();
            }
        };
        loadMenuItem.setOnAction(openEvent);
        aboutAuthorMenuItem.setOnAction(new AboutAuthorEvent());
        aboutProgramMenuItem.setOnAction(new AboutProgramEvent());
        //helpMenuItem.setOnAction(event -> );
    }

    public void saveAction(ActionEvent actionEvent) {
        FileService fileService = (FileService) new FileSystemOfEquationService();
        try {
            fileService.saveToFile(prevStage, getSystem().getFullInfo());
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не удалось сохранить СЛАУ");
            alert.setContentText("Заполните все ячейки СЛАУ цифрами");
            alert.showAndWait();
        }
    }

    public void exitAction(ActionEvent actionEvent) {
        System.exit(0);
    }
    public MenuItem saveMenuItem;
    public MenuItem loadMenuItem;
    public MenuItem exitMenuItem;
    public MenuItem aboutProgramMenuItem;
    public MenuItem aboutAuthorMenuItem;
    public MenuItem helpMenuItem;
    public Label invalidMatrixInput;
    public GridPane gridPane;
    public NumberField numberSizeField;
    public Label invalidSizeInput;
    private NumberField[][] boxes;
    private int size;



}
