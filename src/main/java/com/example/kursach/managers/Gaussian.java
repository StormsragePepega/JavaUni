package com.example.kursach.managers;

import com.example.kursach.models.SystemOfEquations;

public class Gaussian {


    private static double[] GaussianMethod(int n, SystemOfEquations system, double[][] MatrixClone) {
//Прямой ход (Зануление нижнего левого угла)
        for (int k = 0; k < n; k++) {
            for (int i = k + 1; i < n; i++) {
                double K = MatrixClone[i][k] / MatrixClone[k][k]; //Коэффициент
                for (int j = 0; j < n + 1; j++)
                    MatrixClone[i][j] = MatrixClone[i][j] - MatrixClone[k][j] * K;
                //Зануление элементов матрицы ниже первого члена, преобразованного в единицу
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++)
                    system.coefficients[i][j]=MatrixClone[i][j];
                system.results[i]= MatrixClone[i][n];
            }
        }

//Обратный ход (Зануление верхнего правого угла), чтобы вычислить корни
        for (int k = n - 1; k > -1; k--) {
            for (int i = n; i > -1; i--) {
                double k1=MatrixClone[k][i];
                double k2 =system.coefficients[k][k];
                MatrixClone[k][i] = MatrixClone[k][i] / system.coefficients[k][k];
            }
            for (int i = k - 1; i > -1; i--) {
                double K = MatrixClone[i][k] / MatrixClone[k][k];
                for (int j = n; j > -1; j--)
                    MatrixClone[i][j] = MatrixClone[i][j] - MatrixClone[k][j] * K;
            }
        }
//Получаем ответ из коней уравнения
        double[] Answer = GetRoots(MatrixClone, n);

        return Answer;
    }

    //Получаем корни системы уравнения из матрицы клона
    private static double[] GetRoots(double[][] MatrixClone, int n) {
        double[] Answer = new double[n];

        for (int i = 0; i < n; i++)
            Answer[i] = MatrixClone[i][n];

        return Answer;
    }

    public static double[] FindTheRootsOfTheEquation(SystemOfEquations system) {
        int n = system.coefficients.length;
        double[][] MatrixClone = CreateMatrixClone(system, n);
        double[] Answer = GaussianMethod(n, system, MatrixClone);

        return Answer;
    }

    private static double[][]

    CreateMatrixClone(SystemOfEquations system, int n) {
        double[][] MatrixClone = new double[n][n + 1]; //Матрица-дублер

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                MatrixClone[i][j] = system.coefficients[i][j];
            MatrixClone[i][n] = system.results[i];
        }

        return MatrixClone;
    }


}

