# ACWing CPP InputScanner

## Introduction
### Elementary data types
The most basic usage is of the folloing generic form: `const auto x = io.read<typeof(x)>`
```c++
int main() {
  auto io = input_scanner(std::cin);
  const int = io.read<int>();
  const string = io.read<string>();
  return 0;
}
```
Currently supported types:
* all integral types such as `int`, `long long`, `unsigned long long`.
* string related types `string`, `char` (specialized). 
* all floating point types such as `double`, `float`.

### Pair
One could read two input of different types into a pair. 
```c++
int main() {
  auto io = input_scanner(std::cin);
  const P = io.read<pair<int, string>();
  // alternatively, one could split two element using structural binding
  const [fst, snd] = io.read<pair<int, string>>();
  return 0;
}
```

### Tuple[TODO]
One could read any number of inputs of different types into a tuple.
```c++
int main() {
  auto io = input_scanner(std::cin);
  const P = io.read<tuple<int, string, double>();
  // alternatively, one could split two element using structural binding
  const [fst, snd, third] = io.read<tuple<int, string>>();
  return 0;
}
```
See if there is way to define a syntactic sugar of the form: `io.read<type_1, type_2,...,type_n>() = io.read<tuple<type_1,...,type_n>>()`.

### std::vector
Three use cases.
1. `io.read<vector<T>>()` reads all element from the current position the current line until the end of the current line (delimited by '\n' by default). For example, 
    * if the input is `1 2 3 4 5` and we are currently at position 0, the result will be `[1, 2, 3, 4, 5]`.
	* if the input is `1 2 3 4 5` and we are currently at position 3(the space between `2` and `3`), the result will be `[3, 4, 5]`.
2. `io.read<vector<T>>(size_t n)` reads next `n` values of type `T` and put them into one vector.
2. `io.read<vector<vector<T>>(size_t n, size_t m)` reads next `m*n` values of type `T` and put them into a container of type `vector<vector<T>>`

### peek()
Return the current char but do not consume it.

## TODO

### proper exception handling and messages
### std::tuple
### std::array
### custom delimiter?
### test!!

