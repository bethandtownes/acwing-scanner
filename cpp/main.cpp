#include <bits/stdc++.h>
#include "scanner.h"
using namespace std;
template <typename T>
std::ostream& operator << (std::ostream& os, const vector<T>& x) {
  os << "[";
  for (auto it = begin(x); it != end(x); ++it) {
    os << (it == begin(x) ? "" : ", ") << *it;
  }
  os << "]";
  return os;
}


template <typename T>
std::ostream& operator << (std::ostream& os, const vector<vector<T>>& x) {
  os << "\n[";
  for (auto it = begin(x); it != end(x); ++it) {
    os << (it == begin(x) ? "" : "\n ") << *it;
  }
  os << "]";
  return os;
}



#define see(...) " [" << #__VA_ARGS__ ": " << (__VA_ARGS__) << "] "
#define trace(x) (cout << see(x) << endl, x)
#define traceX(x) x

int main() {

  using namespace std;
  auto io = input_reader();
  while (1) {
    const auto a = io.read<vector<int>>();
    // const auto a = io.read<vector<vector<int>>>(3);
    cout << see(a) << endl;
    // const auto [a, b] = io.read<pair<int, string>>();
    // cout << see(a) << see(b) << endl;
    // auto a = io.read<vector<int>>();
    // auto [a, b] = io.read<int, 2>();
    // cout << see(a) << see(b) << endl;
    // double a = io.read<double>();
    // cout << a << endl;
    // string a = io.read<string>();
    // cout << a << endl;
    // int a = io.read<int>();
    // int b = io.read<int>();
    // cout << see(a + b) << endl;
  }
  return 0;
};
