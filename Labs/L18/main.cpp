// Реализовать базу данных о людях. Человек (вспомогательный класс Person)
// описывается следующими полями: имя, фамилия, год рождения, пол, номер
// паспорта. Рекомендуется перегрузить для класса Person оператор сравнения

// load(const std::string& filename)
// save(const std::string& filename)
// operator<< для вывода на экран
// add(const Person& p)
// erase(const Person& p)


#include <fstream>
#include <iostream>
#include <string>
#include <vector>

#include "database.h"

using namespace std;

int main() {
  Database db;
  db.add(Person("Rayan", "Gosling", 1980, 0, 420069));
  db.add(Person("Angelina", "Jolie", 1975, 1, 420070));
  db.add(Person("Bread", "Pitt", 1963, 0, 420071));
  db.add(Person("Leonardo", "DiCaprio", 1974, 0, 420072));
  db.add(Person("Tom", "Cruise", 1962, 0, 420074));
  db.add(Person("Jennifer", "Lawrence", 1990, 1, 420075));
  cout << db << endl;

 db.save("db.txt");
  
 cout << "After erase" << endl;
 db.erase(Person("Tom", "Cruise", 1962, 0, 420074));;
 cout << db << endl;

 cout << "After add" << endl;
 db.add(Person("Andrey", "Kruk", 1941, 0, 666666));
 cout << db << endl;


  Database db2;
  db2.load("db.txt");
  cout << "After load" << endl;
  cout << db2;

  return 0;
}