#ifndef SCANNER_H
#define SCANNER_H

#include <bits/stdc++.h>

// using namespace std;

namespace acwing {
template <class T>
struct is_vector {
  using type                  = T;
  constexpr static bool value = false;
};
template <class T>
struct is_vector<std::vector<T>> {
  using type                  = std::vector<T>;
  constexpr static bool value = true;
};
template <typename T>
inline constexpr bool is_vector_v = is_vector<T>::value;

template <class T>
struct is_pair { constexpr static bool value = false; };
template <class T1, class T2>
struct is_pair<std::pair<T1, T2>> {
  using type                  = std::pair<T1, T2>;
  constexpr static bool value = true;
};
template <typename T>
inline constexpr bool is_pair_v = is_pair<T>::value;
}  // namespace acwing

class input_reader {
 private:
  static constexpr int BUFFER_SIZE = 1024;
  std::size_t num_chars_;
  std::size_t cur_pos_;
  std::istream& input_stream_;
  bool auto_reload_;
  char buffer_[BUFFER_SIZE];

 public:
  input_reader(std::istream& input_stream = std::cin) : cur_pos_(0),
                                                        num_chars_(0),
                                                        auto_reload_(true),
                                                        input_stream_(input_stream){};

  inline char peek() { return ready(), buffer_[cur_pos_]; };

  template <class data_t, typename std::enable_if_t<std::is_same_v<char, data_t>>* = nullptr>
  inline data_t read() {
    return (refresh_buffer(), ready(), _read());
  }

  template <class data_t, typename std::enable_if_t<not std::is_same_v<char, data_t> and std::is_integral_v<data_t>>* = nullptr>
  data_t read() {
    ready();
    const data_t sign = (std::is_signed_v<data_t> and peek() == '-') ? ((void)read<char>(), -1) : 1;
    const data_t val  = [&](data_t acc = 0) {
      for (char ch = _read(); not is_space(ch); ch = _read()) {
        acc = (acc * 10) + ch - '0';
      }
      return acc;
    }();
    return sign * val;
  };

  template <class data_t, typename std::enable_if_t<std::is_same_v<std::string, data_t>>* = nullptr>
  data_t read() {
    ready();
    const data_t val = [&](data_t acc = {}) {
      for (char ch = _read(); not is_space(ch); ch = _read())
        acc.push_back(ch);
      return acc;
    }();
    return val;
  }

  template <class data_t, std::size_t N>
  std::array<data_t, N> read() {
    ready();
    const auto val = [&](std::array<data_t, N> self = {}) {
      for (int i = 0; i < N; ++i)
        self[i] = read<data_t>();
      return self;
    }();
    return val;
  }

  template <class data_t, typename std::enable_if_t<acwing::is_vector_v<data_t>>* = nullptr>
  data_t read() {
    ready();
    const auto val = [&](data_t self = {}) {
      auto_reload_ = false;
      while (ready())
        self.emplace_back(read<typename data_t::value_type>());
      auto_reload_ = true;
      return self;
    }();
    return val;
  }

  template <class data_t, typename std::enable_if_t<acwing::is_vector_v<data_t>>* = nullptr>
  data_t read(std::size_t n) {
    ready();
    const auto val = [&](data_t self = {}) {
      for (int i = 0; i < n; ++i)
        self.emplace_back(read<typename data_t::value_type>());
      return self;
    }();
    return val;
  }

  template <class data_t, typename std::enable_if_t<acwing::is_vector_v<data_t> and acwing::is_vector_v<typename data_t::value_type>>* = nullptr>
  data_t read(std::size_t n, std::size_t m) {
    ready();
    const auto val = [&](data_t self = {}) {
      for (int i = 0; i < n; ++i) {
        self.emplace_back(read<typename data_t::value_type>(m));
      }
      return self;
    }();
    return val;
  }

  template <class data_t, typename std::enable_if_t<acwing::is_pair_v<data_t>>* = nullptr>
  data_t read() {
    const auto fst = read<typename data_t::first_type>();
    const auto snd = read<typename data_t::second_type>();
    return data_t{fst, snd};
  }

  template <class data_t, typename std::enable_if_t<std::is_floating_point_v<data_t>>* = nullptr>
  data_t read() {
    ready();
    const data_t sign = (std::is_signed_v<data_t> and peek() == '-') ? ((void)read<char>(), -1.0) : 1.0;

    const auto [before_decimal, has_decimal] = [&](data_t acc = 0.0) -> std::pair<data_t, bool> {
      for (char ch = _read(); not is_space(ch); ch = _read()) {
        acc = (acc * 10.0) + ch - '0';
        if (peek() == '.')
          return {acc, true};
      }
      return {acc, false};
    }();

    const data_t after_decimal = [&](data_t acc = 0.0) {
      if (not has_decimal)
        return acc;
      else {
        for (auto [ch, base] = pair(((void)read<char>(), _read()), data_t{1.0 / 10.0}); not is_space(ch); ch = _read(), base /= 10)
          acc += (ch - '0') * base;
        return acc;
      }
    }();

    return sign * (before_decimal + after_decimal);
  }

 private:
  inline void refresh_buffer() {
    if (auto_reload_ and cur_pos_ >= num_chars_) {
      cur_pos_ = 0;
      input_stream_.getline(buffer_, BUFFER_SIZE);
      num_chars_ = std::cin.gcount();
    }
  }

  inline char _read() { return refresh_buffer(), buffer_[cur_pos_++]; };

  inline bool is_space(char ch) { return ch == ' ' or ch == '\n' or ch == '\t' or ch == '\0'; };

  inline bool ready() {
    refresh_buffer();
    if (not auto_reload_ and cur_pos_ >= num_chars_)
      return false;
    else if (not auto_reload_ and cur_pos_ < num_chars_) {
      while (is_space(buffer_[cur_pos_]) and cur_pos_ < num_chars_)
        cur_pos_++;
      return cur_pos_ < num_chars_;
    }
    else if (auto_reload_) {
      while (is_space(buffer_[cur_pos_]))
        cur_pos_++;
      return true;
    }
    else
      throw std::domain_error("");
  }

  // void debug(string level) {
  //   const auto view = [&](vector<string> self = {}) {
  //     std::transform(buffer_, buffer_ + 30, std::back_inserter(self), [&](auto x) {
  //       if (x == '\0')
  //         return std::string("END");
  //       else if (x == ' ')
  //         return std::string("SPACE");
  //       else if (x == '\n')
  //         return std::string("NEWLINE");
  //       else
  //         return std::string(1, x);
  //     });
  //     return self;
  //   }();

  //   cout << level
  //        << see(view)
  //        << see(cur_pos_)
  //        << see(view[cur_pos_])
  //        << see(num_chars_)
  //        << endl;
  // }
};

#endif  //SCANNER_H
