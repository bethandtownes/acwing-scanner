import java.util.Arrays;

import acwing.InputReader;

public class Main {
  public static void main(String args[]) {
    InputReader in = new InputReader(System.in);
    while (true) {
      int[][] a = in.readIntMatrix(3, 3);
      System.out.println(Arrays.deepToString(a));
    }
  }
}
