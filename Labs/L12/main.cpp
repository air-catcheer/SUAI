// Создайте класс SqrMatrix
// (квадратная матрица с элементами типа double)
// с операциями + - * /, умножения на скаляр
// Размерность матрицы задать в конструкторе

//  TODO:
//* Используйте приватные поля для данных
//* Реализуйте конструктор, который должен устанавливать значения по умолчанию, 
//* Реализуйте методы для чтения и записи значений полей (set, get), а также ввода и вывода (перегрузка operator<< и operator>> не требуется)
//* Если требуется, напишите конструктор копий, оператор присваивания и деструктор 
//* Напишите программу для тестирования вашего класса

#include "matrix.h"
#include <iostream>
using namespace std;

int main() {
  Matrix m1;
  cout << "m1:" << endl;
  m1.print();

  Matrix m2(3);
  cout << "m2(3):" << endl;
  m2.print();

  Matrix m3(4, 9);
  cout << "m3(4, 9):" << endl;
  m3.print();

  Matrix m4(m3);
  cout << "m4(m3):" << endl;
  m4.print();

  Matrix m5(3, 4.2);
  cout << "m5(3, 4.2):" << endl;
  m5.print();

  m5 = m3;
  cout << "m5 = m3:" << endl;
  m5.print();

  m5.set(1, 1, 1);
  // проверки правильности копирования
  cout << "m5.set(1, 1, 1):" << endl;
  m5.print(); // измененная матрица
  cout << "m3:" << endl;
  m3.print(); // не измененная копия
  cout << "m4:" << endl;
  m4.print(); // не измененная копия

  Matrix m7(5, 0.01);
  for (int i = 0; i < m7.size(); i++)
    m7.set(i, i, 9.99);
  
  m7.set(4, 2, 3.14);
  cout << "m7:" << endl;
  m7.print();

  /* Error in 'set' operator: Invalid index
    m7.set(100, 100, 0);
    m7.print();
  */

  m7.set(4, 2, 0);
  cout << "m7.set(4, 2, 0):" << endl;
  m7.print();


  Matrix m8(3, 0.1);
  cout << "m8(3, 0.1):" << endl;
  m8.print();

  cout << "m8.set(1, 1, 42).set(2, 2, 0):" << endl;
  m8.set(1, 1, 42).set(2, 2, 0).print();

  Matrix m9(3);
  cout << "m9(3).set(m8).set(2, 2, 1):" << endl;
  m9.set(m8).set(2, 2, 1).print();

  Matrix m10(10);
  cout << "m10(10).set(0, 0, 2).set(9, 9, 9):" << endl;
  m10.set(0, 0, 2).set(9, 9, 9).print();

  cout << m10.get(0, 0) << " at 0, 0" << endl
       << m10.get(9, 9) << " at 9, 9" << endl
       << m10.get(5, 5) << " at 5, 5" << endl << endl;
  
  /* Error in 'get' operator: Invalid index
    cout << m10.get(10, 10) << " at 10, 10" << endl;
  */

  cout << "m10 transpose:" << endl;
  m10.transpose().print();
  cout << "m9 transpose:" << endl;
  m9.transpose().print();

  Matrix test(3, 99);
  cout << "test(3, 99):" << endl;
  test.print();
  cout << "test transpose:" << endl;
  test.transpose().print();

  // operators test

  Matrix summ = Matrix(5, 1) + Matrix(5, 2);
  cout << "Matrix(5, 1) + Matrix(5, 2):" << endl;
  summ.print();
  summ -= Matrix(5, 4);
  cout << "summ -= Matrix(5, 4):" << endl;
  summ.print();

  // проверка умножения

  // Matrix mult1({
  //   {1, 2, 3},
  //   {4, 5, 6},
  //   {7, 8, 9}
  // }); - "такой конструктор не нужен" (с) Линский

  Matrix mult1(3);
  mult1.set(0, 0, 1).set(0, 1, 2).set(0, 2, 3)
       .set(1, 0, 4).set(1, 1, 5).set(1, 2, 6)
       .set(2, 0, 7).set(2, 1, 8).set(2, 2, 9);
  
  // Matrix mult2({
  //   {6, 1, 8},
  //   {0, 4, 2},
  //   {6, 1, 8},
  // });

  Matrix mult2(3);
  mult2.set(0, 0, 6).set(0, 1, 1).set(0, 2, 8)
       .set(1, 0, 0).set(1, 1, 4).set(1, 2, 2)
       .set(2, 0, 6).set(2, 1, 1).set(2, 2, 8);

  cout << "mult1:" << endl;
  mult1.print();
  cout << "mult2:" << endl;
  mult2.print();

  mult1 *= mult2;
  cout << "mult1 * mult2:" << endl;
  mult1.print();

  Matrix mult3(6, 6);
  cout << "mult3(6, 6):" << endl;
  mult3.print();
  
  mult3 *= 6;
  cout << "mult3 * 6:" << endl;
  mult3.print();

  // Matrix det_test({
  //   {2, 4, 3},
  //   {5, 7, 8},
  //   {6, 9, 1},
  // });

  Matrix det_test(3);
  det_test.set(0, 0, 2).set(0, 1, 4).set(0, 2, 3)
          .set(1, 0, 5).set(1, 1, 7).set(1, 2, 8)
          .set(2, 0, 6).set(2, 1, 9).set(2, 2, 1);

  cout << "det_test determinant:" << endl;
  cout << det_test.determinant() << endl << endl;

  cout << "det_test minor_matrix:" << endl;
  det_test.minor_matrix().print();
  cout << "det_test algebraic_additions:" << endl;
  det_test.algebraic_additions().print();

  // Matrix aaa({
  //   {1, 2},
  //   {3, 4}
  // });

  Matrix aaa(2);
  aaa.set(0, 0, 1).set(0, 1, 2)
     .set(1, 0, 3).set(1, 1, 4);

  cout << "aaa:" << endl;
  aaa.print();
  cout << "aaa inverse:" << endl;
  aaa.inverse().print();

  // Matrix a100({
  //   {1,  18, 4,  6,  9 },
  //   {11, 17, 13, 14, 8 },
  //   {25, 2,  19, 15, 21},
  //   {10, 5,  12, 3,  7 },
  //   {24, 20, 22, 16, 23}
  // });

  Matrix a100(5);
  a100.set(0, 0, 1).set(0, 1, 18).set(0, 2, 4).set(0, 3, 6).set(0, 4, 9)
      .set(1, 0, 11).set(1, 1, 17).set(1, 2, 13).set(1, 3, 14).set(1, 4, 8)
      .set(2, 0, 25).set(2, 1, 2).set(2, 2, 19).set(2, 3, 15).set(2, 4, 21)
      .set(3, 0, 10).set(3, 1, 5).set(3, 2, 12).set(3, 3, 3).set(3, 4, 7)
      .set(4, 0, 24).set(4, 1, 20).set(4, 2, 22).set(4, 3, 16).set(4, 4, 23);
  cout << "a100:" << endl;
  a100.print();

  cout << "a100 determinant:" << endl;
  cout << a100.determinant() << endl << endl;
  cout << "a100 inverse:" << endl;
  a100.inverse().print();

  // Matrix mult_a({
  //   {13, 26},
  //   {39, 13}
  // });

  cout << "mult_a:" << endl;
  Matrix mult_a(2);
  mult_a.set(0, 0, 13).set(0, 1, 26)
        .set(1, 0, 39).set(1, 1, 13)
        .print();

  // Matrix mult_b({
  //   {7, 4},
  //   {2, 3}
  // });

  cout << "mult_b:" << endl;
  Matrix mult_b(2);
  mult_b.set(0, 0, 7).set(0, 1, 4)
        .set(1, 0, 2).set(1, 1, 3)
        .print();

  cout << "mult_a / mult_b:" << endl;
  (mult_a / mult_b).print();
  // для проверки:
  // https://ru.wikihow.com/делить-матрицы
}