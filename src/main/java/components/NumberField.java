package components;

import javafx.scene.control.TextField;

/**
 * Компонент поля для ввода чисел, наследующий компонент поля для ввода строк
 *
 * @author K.I. Petrovsky
 * @version 1.8
 */
public class NumberField extends TextField {
    /**
     * изменение содержимого поля
     *
     * @since 1.8
     */
    @Override
    public void replaceText(int start, int end, String text) {
        // получаем содержимое поля
        StringBuilder sb = new StringBuilder(getText());
        // вставляем введённую букву в общий текст
        sb.insert(end, text);
        String s = sb.toString();
        // проверка всего введённого текста на число
        if (validate(s)) {
            super.replaceText(start, end, text);
        }
    }

    /**
     * изменение содержимого поля
     *
     * @since 1.2
     */
    @Override
    public void replaceSelection(String text) {
        // проверка изменяющегося текста на число
        if (validate(text)) {
            super.replaceSelection(text);
        }
    }

    /**
     * проверка строки на число
     *
     * @param text введённая строка
     * @since 1.6
     */
    private boolean validate(String text) {
        return text.matches("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");
    }
}