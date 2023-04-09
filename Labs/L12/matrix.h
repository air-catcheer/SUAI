#pragma once

#include <iostream>
using namespace std;

class Matrix {
  private:
    double** arr;
    unsigned int _size;

  public:
    // конструкторы
    Matrix(unsigned int size = 1, double def = 0); // конструктор по умолчанию (размер и значение по умолчанию)
    Matrix(const Matrix& m); // конструктор копирования
    ~Matrix(); // деструктор

    // сеттеры - функции которые изменяют состояние объекта класса
    Matrix& set(unsigned int x, unsigned int y, double value);// координаты и задаваемое значение
    Matrix& set(Matrix& m);

    // геттеры -  функции которые получают значения приватных переменных
    double get(unsigned int x, unsigned int y) const;
    unsigned int size() const;  // размер матрицы
    const double determinant(); // определитель
    void print(); // вывод матрицы на экран

    // преобразования
    Matrix transpose();
    Matrix inverse();
    Matrix minor(int i, int j);
    Matrix minor_matrix();
    Matrix algebraic_additions();

    // операторы
    Matrix& operator=(const Matrix& m);  // оператор присваивания
    Matrix& operator+=(const Matrix& x); // сложение с матрицей
    Matrix& operator-=(const Matrix& x); // вычитание матрицы
    Matrix& operator*=(const Matrix& x); // умножение на матрицу
    Matrix& operator*=(const double& x); // умножение на скаляр
    Matrix& operator/=(const Matrix& x); // деление на матрицу
    Matrix& operator/=(const double& x); // деление на скаляр
    
    friend Matrix operator-(const Matrix &m); // унарный минус (смена знаков всех элементов матрицы)
    friend Matrix operator+(Matrix a, const Matrix &b);
    friend Matrix operator-(Matrix a, const Matrix &b);
    friend Matrix operator*(Matrix a, const Matrix &b);
    friend Matrix operator*(Matrix a, const double &b);
    friend Matrix operator/(Matrix a, const Matrix &b);
    friend Matrix operator/(Matrix a, const double &b);
};