
import java.io.*;

import java.util.*;

class OutputWriter {
  private final PrintWriter writer;

  public OutputWriter(OutputStream outputStream) {
    writer = new PrintWriter(
        new BufferedWriter(new OutputStreamWriter(outputStream)));
  }

  public OutputWriter(Writer writer) { this.writer = new PrintWriter(writer); }

  public void printTable(char[][] table) {
    for (char[] row : table) {
      printLine(new String(row));
    }
  }

  public void print(char[] array) { writer.print(array); }

  public void print(Object... objects) {
    for (int i = 0; i < objects.length; i++) {
      if (i != 0) {
        writer.print(' ');
      }
      writer.print(objects[i]);
    }
  }

  public void print(int[] array) {
    for (int i = 0; i < array.length; i++) {
      if (i != 0) {
        writer.print(' ');
      }
      writer.print(array[i]);
    }
  }

  public void print(double[] array) {
    for (int i = 0; i < array.length; i++) {
      if (i != 0) {
        writer.print(' ');
      }
      writer.print(array[i]);
    }
  }

  public void print(long[] array) {
    for (int i = 0; i < array.length; i++) {
      if (i != 0) {
        writer.print(' ');
      }
      writer.print(array[i]);
    }
  }

  public void printLine(int[] array) {
    print(array);
    writer.println();
  }

  public void printLine(double[] array) {
    print(array);
    writer.println();
  }

  public void printLine(long[] array) {
    print(array);
    writer.println();
  }

  public void printLine() { writer.println(); }

  public void printLine(Object... objects) {
    print(objects);
    writer.println();
  }

  public void print(char i) { writer.print(i); }

  public void printLine(char i) { writer.println(i); }

  public void printLine(char[] array) { writer.println(array); }

  public void printFormat(String format, Object... objects) {
    writer.printf(format, objects);
  }

  public void close() { writer.close(); }

  public void flush() { writer.flush(); }

  public void print(long i) { writer.print(i); }

  public void printLine(long i) { writer.println(i); }

  public void print(int i) { writer.print(i); }

  public void printLine(int i) { writer.println(i); }

  public void separateLines(int[] array) {
    for (int i : array) {
      printLine(i);
    }
  }

  public void printList(List<?> answer) {
    for (Object o : answer) {
      printLine(o);
    }
  }
}

class InputReader {
  public static final int BUFFER_SIZE = 1024;
  private DataInputStream stream;
  private byte[] buffer = new byte[BUFFER_SIZE];
  private int curPos;
  private int numChars;
  private boolean autoReload;



  public InputReader(InputStream stream) {
    this.stream = new DataInputStream(stream);
    this.curPos = 0;
    this.numChars = 0;
    this.autoReload = true;
  }

  public char readChar() {
    ready();
    return (char) _read();
  }

  public String readString() {
    ready();
    StringBuilder acc = new StringBuilder();
    for (byte ch = _read(); ! isSpace(ch); ch = _read()) 
      if (Character.isValidCodePoint(ch))
        acc.appendCodePoint(ch);
    return acc.toString();
  }


  public char peek() {
    ready();
    return (char) buffer[curPos];
  }

  private void debug(String level) {
    System.out.println(level);
    System.out.print("[");
    for (int i = 0; i < 50; ++i) {
      if (i != 0)
        System.out.print(", ");
      if (buffer[i] == '\n')
        System.out.print("NEWLINE");
      else if (buffer[i] == ' ')
        System.out.print("SPACE");
      else if (buffer[i] == '\0')
        System.out.print("\0");
      else 
        System.out.print((char)buffer[i]);
    }
    System.out.println("]");
    System.out.printf("curPos = %d, numChars = %d\n", curPos, numChars);
  }


  public int[] readIntArray(int n) {
    int[] acc = new int[n];
    for (int i = 0; i < n; ++i)
      acc[i] = readInt();
    return acc;
  }

  public double[] readDoubleArray(int n) {
    double[] acc = new double[n];
    for (int i = 0; i < n; ++i)
      acc[i] = readDouble();
    return acc;
  }

  public long[] readLongArray(int n) {
    long[] acc = new long[n];
    for (int i = 0; i < n; ++i)
      acc[i] = readLong();
    return acc;
  }

  public String[] readStringArray(int n) {
    String[] acc = new String[n];
    for (int i = 0; i < n; ++i)
      acc[i] = readString();
    return acc;
  }

  public char[] readCharArray(int n) {
    char[] acc = new char[n];
    for (int i = 0; i < n; ++i)
      acc[i] = readChar();
    return acc;
  }

  public int[][] readIntMatrix(int r, int c) {
    int[][] acc = new int[r][c];
    for (int i = 0; i < r; ++i)
      for (int j = 0; j < c; ++j) 
        acc[i][j] = readInt();
    return acc;
  }

  public double[][] readDoubleMatrix(int r, int c) {
    double[][] acc = new double[r][c];
    for (int i = 0; i < r; ++i)
      for (int j = 0; j < c; ++j) 
        acc[i][j] = readDouble();
    return acc;
  }

  public char[][] readCharMatrix(int r, int c) {
    char[][] acc = new char[r][c];
    for (int i = 0; i < r; ++i)
      for (int j = 0; j < c; ++j) 
        acc[i][j] = readChar();
    return acc;
  }

  public long[][] readLongMatrix(int r, int c) {
    long[][] acc = new long[r][c];
    for (int i = 0; i < r; ++i)
      for (int j = 0; j < c; ++j) 
        acc[i][j] = readLong();
    return acc;
  }

  public String[][] readStringMatrix(int r, int c) {
    String[][] acc = new String[r][c];
    for (int i = 0; i < r; ++i)
      for (int j = 0; j < c; ++j) 
        acc[i][j] = readString();
    return acc;
  }
  
  public int readInt() {
    ready();
    int sign = readSign();
    int acc = 0;
    for (byte ch = _read(); !isSpace(ch); ch = _read()) 
      acc = (10 * acc) + (ch - '0');
    return sign * acc;
  }

  public long readLong() {
    ready();
    long sign = readSign();
    long acc = 0;
    for (byte ch = _read(); !isSpace(ch); ch = _read()) 
      acc = (10 * acc) + (long)(ch - '0');
    return sign * acc;
  }

  public double readDouble() {
    ready();
    double sign;
    if (peek() == '.')
      sign = 1.0;
    else
      sign = readSign();
    
    double before_decimal = 0.0;
    boolean has_decimal = false;
 INIT:
    {
      double acc = 0.0;
      for (byte ch = _read(); !isSpace(ch); ch = _read()) {
        acc = (10 * acc) + (ch - '0');
        if (peek() == '.') {
          has_decimal = true;
          break;
        }
      }
      before_decimal = acc;
    }
    
    double after_decimal = 0.0;
 INIT:
    {
      if (has_decimal) {
        readChar();
        byte ch;
        double base = 0.0;
        double acc = 0.0;
        for (ch = _read(), base = 1.0 / 10.0; !isSpace(ch); ch = _read(), base /= 10.0)
          acc += (ch - '0') * base;
        after_decimal = acc;
      }
    }
    return sign * (before_decimal + after_decimal);
  }

  private int readSign() {
    final char ch = peek();
    if (ch == '-') {
      readChar();
      return -1;
    }
    else {
      if (!isDigit(ch) ) {
        debug("read sign");
        throw new InputMismatchException();
      }
      else 
        return 1;
    }
  }
  
  private boolean isSpace(int ch) {
    return ch == ' ' || ch == '\t' || ch == '\n' || ch == '\0';
  }

  private boolean isDigit(char ch) {
    return '0' <= ch && ch <= '9';
  }
  
  private boolean ready() {
    refreshBuffer();
    if (!autoReload && curPos >= numChars)
      return false;
    else if (!autoReload && curPos < numChars) {
      while (isSpace(buffer[curPos]) && curPos < numChars)
        curPos++;
      return curPos < numChars;
    }
    else if (autoReload) {
      while (isSpace(buffer[curPos]))
        curPos++;
      return true;
    }
    else
      throw new InputMismatchException();
  }

  private void refreshBuffer() {
    if (curPos >= numChars) {
      try {
        curPos = 0;
        numChars = stream.read(buffer);
      } catch (IOException e) {
        throw new InputMismatchException();
      }
    }
  }

  private byte _read() {
      refreshBuffer();
      if (numChars == -1)
        return '\0';
      return buffer[curPos++];
  };
  
};

public class Main {

  public static void main(String[] args) throws java.lang.Exception {
    InputReader in = new InputReader(System.in);
    OutputWriter out = new OutputWriter(System.out);
    // int n = in.readInt();
    // int k = in.readInt();
    // int acc = 0;
    // for (int i = 0; i < n; ++i) {
    //   int data = in.readInt();
    //   if (data % k == 0)
    //     acc++;
    // }
    int a = in.readInt();
    int b = in.readInt();
    
    out.printLine(a + b);
    out.close();
    // System.out.println(acc);
  }
}
