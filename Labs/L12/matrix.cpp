#include <cstdlib> // для exit()
#include "matrix.h"

// конструктор по умолчанию
Matrix::Matrix(unsigned int size, double def) {
  if (size > 0) {
    _size = size;
    // выделение памяти под массив указателей с помощью malloc
    arr = (double**) malloc(size * sizeof(double*));
    for (int i = 0; i < size; i++) {
      arr[i] = (double*) malloc(size * sizeof(double));
      for (int j = 0; j < size; j++) {
        arr[i][j] = def; // заполение строки полностью значением по умолчанию
      }
    }
  } else {
    cerr << "Error in class constructor: Invalid size" << endl;
    exit(1);
  }
}

// конструктор копирования
Matrix::Matrix(const Matrix& m) {
  _size = m.size();
  arr = (double**) malloc(_size * sizeof(double*));
  for (int i = 0; i < _size; i++) {
    arr[i] = (double*) malloc(_size * sizeof(double));
    for (int j = 0; j < _size; j++) {
      arr[i][j] = m.get(i, j);
    }
  }
}

// деструктор
Matrix::~Matrix() {
  for (int i = 0; i < _size; i++) {
    free(arr[i]);
  }
  free(arr);
}

// сеттеры

Matrix& Matrix::set(unsigned int x, unsigned int y, double value) {
  if (x < this->size() && y < this->size()) {
    arr[x][y] = value;
  } else {
    cerr << "Error in 'set' operator: Invalid index" << endl;
    exit(1);
  }
  return *this;
}

Matrix& Matrix::set(Matrix& m) {
  if (m.size() == _size) {
    for (int i = 0; i < _size; i++) {
      for (int j = 0; j < _size; j++) {
        arr[i][j] = m.get(i, j);
      }
    }
  } else {
    cerr << "Error in 'set' operator: Invalid size" << endl;
    exit(1);
  }
  return *this;
}

// геттеры

unsigned int Matrix::size() const {
  return this->_size;
}

double Matrix::get(unsigned int x, unsigned int y) const {
  if (x < _size && y < _size) {
    return arr[x][y];
  } else {
    cerr << "Error in 'get' operator: Invalid index" << endl;
    exit(1);
  }
}

void Matrix::print() {
  for (int i = 0; i < _size; i++) {
    for (int j = 0; j < _size; j++) {
      cout << arr[i][j] << " ";
    }
    cout << endl;
  }
  cout << endl;
}

Matrix& Matrix::operator=(const Matrix& m) {
  if (this == &m) return *this; // проверка на самоприсваивание
  this->~Matrix();
  _size = m.size();
  arr = (double**) malloc(_size * sizeof(double*));
  for (int i = 0; i < _size; i++) {
    arr[i] = (double*) malloc(_size * sizeof(double));
    for (int j = 0; j < _size; j++) {
      arr[i][j] = m.get(i, j);
    }
  }
  return *this;
}

// преобразования

// унарный минус (смена знаков элементов)
Matrix operator-(const Matrix &m) {
  Matrix result(m);
  for (int i = 0; i < m.size(); i++) {
    for (int j = 0; j < m.size(); j++) {
      result.set(i, j, -m.get(i, j));
    }
  }
  return result;
}

void swap(double *d1, double *d2) { // меняем местами два элемента матрица
  double temp = *d1;
  *d1 = *d2;
  *d2 = temp;
}

// транспонирование матрицы
// отзеркаливание относительно главной диагонали
Matrix Matrix::transpose() {
  Matrix temp(*this);
  for (int y = 0; y < this->size() - 1; y++) {
    for (int x = y + 1; x < this->size(); x++) {
      swap(&temp.arr[y][x], &temp.arr[x][y]);
    }
  }
  return temp;
}

// нахождение минора
// это короче матрица с вычеркнутым столбцом с индексом x
// и вычеркнутой строкой с индексом y
Matrix Matrix::minor(int _y, int _x) {
  Matrix temp(this->size() - 1);
  for (int y = 0; y < this->size(); y++) {
    for (int x = 0; x < this->size(); x++) {
      if (y != _y && x != _x) {
        temp.set(x < _x ? x : x - 1, y < _y ? y : y - 1, this->get(y, x));
      }
    }
  }
  return temp;
}

// определитель матрицы (рекурсивно)
// два базовых случая и рекурсия для матриц >= 3
const double Matrix::determinant() {
  switch (this->size()) {
    case 1:
      return arr[0][0];

    case 2:
      return arr[0][0] * arr[1][1] - arr[1][0] * arr[0][1];

    default:
      double det = 0;
      for (int i = 0; i < this->size(); i++)
        det += (i % 2 ? -1 : 1) * arr[i][0] * this->minor(i, 0).determinant();
      return det;
  }
}

// матрица миноров
// каждый элемент это определитель минора по индексу этого элемента
Matrix Matrix::minor_matrix() {
  Matrix temp(this->size());
  for (int y = 0; y < this->size(); y ++) {
    for (int x = 0; x < this->size(); x++) {
      temp.set(x, y, this->minor(y, x).determinant());
    }
  }
  return temp;
}

// матрица алгебраических дополнений
// просто матрица миноров но с переменой знаков по диагонали
Matrix Matrix::algebraic_additions() {
  Matrix temp = minor_matrix();
  for (int x = 0; x < this->size(); x++) {
    for (int y = 0; y < this->size(); y++) {
      if ((x + y) % 2) temp.arr[y][x] *= -1;
    }
  }
  return temp;
}

// обратная матрица (самое сложное)
// надо поделить транспонированую матрицу алгебраических
// дополнений на определитель исходной матрицы
Matrix Matrix::inverse() {
  Matrix alg_add_T = this->algebraic_additions().transpose();
  alg_add_T /= this->determinant();
  return alg_add_T.transpose();
}

// операторы

Matrix& Matrix::operator+=(const Matrix& m) {
  if (this->size() == m.size()) {
    for (int x = 0; x < m.size(); x++) {
      for (int y = 0; y < m.size(); y++) {
        arr[y][x] += m.arr[y][x];
      }
    }
  } else {
    cerr << "Error in + operator: Matrices have different sizes" << endl;
    exit(1);
  }
  return *this;
}

Matrix& Matrix::operator-=(const Matrix& m) {
  if (this->size() == m.size()) {
    *this += -m;
  } else {
    cerr << "Error in - operator: Matrices have different sizes" << endl;
    exit(1);
  }
  return *this;
}

// a + b
Matrix operator+(Matrix a, const Matrix& b) {
  a += b;
  return a;
}

Matrix operator-(Matrix a, const Matrix& b) {
  a -= b;
  return a;
}

Matrix& Matrix::operator*=(const Matrix& right) {
  if (right.size() == this->size()) {
    unsigned int size = this->size();
    Matrix result(size, 0);
    for (int result_x = 0; result_x < this->size(); result_x++) {
      for (int result_y = 0; result_y < this->size(); result_y++) {
        double summ = 0;
        for (int i = 0; i < size; i++)
          summ += arr[result_y][i] * right.arr[i][result_x];
        result.arr[result_y][result_x] = summ;
      }
    }
    *this = result;
  } else {
    cerr << "Error in * operator: Matrices have different sizes" << endl;
    exit(1);
  }
  return *this;
}

Matrix operator*(Matrix a, const Matrix& b) {
  a *= b;
  return a;
}

Matrix& Matrix::operator*=(const double& n) {
  for (int x = 0; x < this->size(); x++) {
    for (int y = 0; y < this->size(); y++) {
      arr[y][x] *= n;
    }
  }
  return *this;
}

Matrix operator*(Matrix a, const double &b) {
  a *= b;
  return a;
}

// деление матрицы на матрицу

Matrix& Matrix::operator/=(const Matrix &b) {
  *this *= Matrix(b).inverse();
  return *this;
}

Matrix operator/(Matrix a, const Matrix &b) {
  a /= b;
  return a;
}

Matrix& Matrix::operator/=(const double& n) {
  for (int x = 0; x < this->size(); x++) {
    for (int y = 0; y < this->size(); y++) {
      arr[y][x] /= n;
    }
  }
  return *this;
}

Matrix operator/(Matrix a, const double &b) {
  a /= b;
  return a;
}