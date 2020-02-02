#include <bits/stdc++.h>
#include "scanner.h"

int main() {

  using namespace std;
  auto io = input_reader();
  while (1) {
    const auto a = io.read<vector<int>>(3);
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
