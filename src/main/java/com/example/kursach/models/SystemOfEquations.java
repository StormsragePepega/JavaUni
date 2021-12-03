package com.example.kursach.models;


import com.example.kursach.managers.Gaussian;

/**
 * Модель СЛАУ
 *
 * @author K.I. Petrovsky
 * @version 1.6
 */
public class SystemOfEquations {


    public SystemOfEquations(double [][] matrix, double[] results) {
        this.coefficients = matrix;
        this.results = results;
    }

    /**
     * получение информации о СЛАУ
     *
     * @return определитель созданной матрицы
     * @since 1.6
     */
    public String getInfo() {
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < coefficients.length; i++) {
            for (int j = 0; j < coefficients[0].length-1; j++) {
                if (j != 0) {
                    if (coefficients[i][j] >= 0) {
                        msg.append(" + ").append(coefficients[i][j]).append("*X").append(j+1);
                    } else {
                        msg.append(" ").append(coefficients[i][j]).append("*X").append(j + 1);
                    }
                } else {
                    msg.append(coefficients[i][j]).append("*X").append(j + 1);
                }
            }
            msg.append(" = ").append(results[i]).append("\n");
        }
        return msg.toString();
    }

    /**
     * получение полной информации о СЛАУ с решением
     *
     * @return определитель созданной матрицы
     * @since 1.6
     */
    public String getFullInfo() {
        StringBuilder msg = new StringBuilder();
        msg.append("Исходная CЛАУ:\n");
        msg.append(getInfo());
        msg.append("Корни СЛАУ:\n");
        Gaussian method = new Gaussian();
        int pep=1;
        if (pep==1) {
            double[] X = method.FindTheRootsOfTheEquation(this);
            for (int i = 0; i < X.length; i++) {
                msg.append(String.format("X%d = %.4f\n", i + 1, X[i]));
            }
        } else {
            msg.append("Система линейных алгебраических уравнений не имеет корней");
        }
        return msg.toString();
    }
    public double[][] coefficients;
    public double[] results;
}
