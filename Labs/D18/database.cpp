#include "database.h"

#include <fstream>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

// Person

Person::Person()
    : name("Rayan"),
      surname("Gosling"),
      year(1980),
      gender("female"),
      passport(420069){};

Person::Person(string name, string surname, int year, string gender,
               int passport) {
  this->name = name;
  this->surname = surname;
  this->year = year;
  this->gender = gender;
  this->passport = passport;
}

ostream &operator<<(ostream &out, Person &p) {
  out << p.name << ", " << p.surname << ", " << p.year << ", " << p.gender << ", "
      << p.passport;
  return out;
}

bool Person::operator==(const Person &p1) {
  return (this->name == p1.name) && (this->surname == p1.surname) &&
         (this->year == p1.year) && (this->gender == p1.gender) &&
         (this->passport == p1.passport);
}

// Database

void Database::load(const string &filename) {
  ifstream fin(filename);
  while (!fin.eof()) {
    string name, surname, gender;
    int year, passport;
    fin >> name >> surname >> year >> gender >> passport;
    db.push_back(Person(name, surname, year, gender, passport));
  }
}

void Database::save(const string &filename) {
  ofstream fout(filename);
  for (int i = 0; i < db.size(); i++) {
    fout << db[i] << (i < db.size() - 1 ? "\n" : "");
  }
}

void Database::save_csv(const string &filename) {
  ofstream fout(filename);
  fout << "Name, Surname, Year, Gender, Passport" << endl;
  for (int i = 0; i < db.size(); i++) {
    fout << db[i] << (i < db.size() - 1 ? "\n" : "");
  }
}

void Database::add(const Person &p) { db.push_back(p); }

ofstream &operator<<(ofstream &out, Database &d) {
  for (int i = 0; i < d.db.size(); i++) {
    out << d.db[i];
  }
  return out;
}

void Database::erase(const Person &p) {
  for (int i = 0; i < db.size(); i++) {
    if (db[i] == p) {
      db.erase(db.begin() + i);
      return;
    }
  }
}

ostream &operator<<(ostream &out, Database &d) {
  for (int i = 0; i < d.db.size(); i++) {
    out << d.db[i] << endl;
  }
  return out;
}