# Acwing Java InputScanner

## Introduction

### Elementary data types

* Supported integral primitive data types: 'int', 'long'.
* Supported string related data types: 'String', 'char'.
* Supported floating point data types: 'double'

To read one of the types above, the generic API is of the following form: `readType()`, where `Type` is the data type written in camel case. For example, 
```java
import acwing.InputReader;

public class Main {
  public static void main(String args[]) {
    InputReader in = new InputReader(System.in);
    while (true) {
      int a = in.readInt();
      System.out.println(a);
      double b = in.readDouble();
      System.out.println(b);
    }
  }
}
```

### Containers

Reading arrays and matrices of given sizes are also supported. The generic API is of the following form: `readTypeArray()` or `readTypeMatrix()`, where `Type` is the data type written in camel case. For example, 
```java
import acwing.InputReader;

public class Main {
  public static void main(String args[]) {
    InputReader in = new InputReader(System.in);
    while (true) {
      int[] a = in.readIntArray(3);
      System.out.println(Arrays.toString(a));
      double[][] b = in.readDoubleMatrix();
      System.out.println(Arrays.deepToString(b));
    }
  }
}
```

### peek()
One could use `peek()` to preview the current byte in the inputstream without having to consume it. 
