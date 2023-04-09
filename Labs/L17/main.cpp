#include <iostream>
#include "vector.h"

using namespace std;

struct Point {
  int x;
  int y;
  int z;

  friend std::ostream& operator<<(std::ostream& os, const Point& p) {
    os << "(" << p.x << ", " << p.y << ", " << p.z << ")";
    return os;
  }
};

int main() {
  Vector<int> v(5, 1);
  v.push_back(1);
  v.push_back(2);
  v.push_back(3);
  v.push_back(4);
  v.push_back(5);
  v.push_back(6);
  v.push_back(7);
  v.push_back(8);
  v.push_back(9);
  cout << v << endl;

  Vector<Point> v2(2, {0, 0, 0});
  v2.push_back({1, 1, 1});
  v2.push_back({2, 2, 2});
  v2.push_back({3, 3, 3});
  v2.push_back({4, 4, 4});
  v2.push_back({5, 5, 5});
  cout << v2 << endl << endl;

  // test erase & at
  cout << "v[7] = " << v.at(7) << endl;

  v.erase(7);
  cout << "after erase v[7] = " << v.at(7) << endl;
  cout << v << endl << endl;

  // test insert
  v.insert(7, 91);
  cout << "after insert v[7] = " << v.at(7) << endl;
  cout << v << endl << endl;

  // test exceptions
  try {
    cout << "try to get v.at(500)" << endl;
    cout << v.at(500) << endl;
  } catch (const std::out_of_range& e) {
    cout << "Exception: " << e.what() << endl;
  }

  cout << endl;

  try {
    cout << "try to v.insert(500, 1)" << endl;
    v.insert(500, 1);
  } catch (const std::out_of_range& e) {
    cout << "Exception: " << e.what() << endl;
  }

  cout << endl;

  try {
    cout << "try to v.erase(500)" << endl;
    v.erase(500);
  } catch (const std::out_of_range& e) {
    cout << "Exception: " << e.what() << endl;
  }
}