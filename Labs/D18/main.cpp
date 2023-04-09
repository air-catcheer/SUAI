// Реализовать базу данных о людях. Человек (вспомогательный класс Person)
// описывается следующими полями: имя, фамилия, год рождения, пол, номер
// паспорта. Рекомендуется перегрузить для класса Person оператор сравнения

// load(const std::string& filename)
// save(const std::string& filename)
// operator<< для вывода на экран
// add(const Person& p)
// erase(const Person& p)

//доп. программа работала с csv файлами

#include <fstream>
#include <iostream>
#include <string>
#include <vector>

#include "database.h"

using namespace std;

int main() {
  Database db;
  db.add(Person("Rayan", "Gosling", 1980, "male", 420069));
  db.add(Person("Angelina", "Jolie", 1975, "female", 420070));
  db.add(Person("Bread", "Pitt", 1963, "male", 420071));
  db.add(Person("Leonardo", "DiCaprio", 1974, "male", 420072));
  db.add(Person("Scarlett", "Johansson", 1984, "female", 420073));
  db.add(Person("Tom", "Cruise", 1962, "male", 420074));
  db.add(Person("Jennifer", "Lawrence", 1990, "female", 420075));
  db.add(Person("Emma", "Watson", 1990, "female", 420076));
  cout << db << endl;
  db.save_csv("db.csv");
  return 0;
}