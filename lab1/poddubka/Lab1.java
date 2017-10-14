package org.hillel;

public class Lab1 {

    public static void main(String[] args) {

        f();

    }

    private static void f() {

        for (int i = 1; i <= 100; i++) {

            String result = "";

            if (i % 3 == 0) {
                result += "Fizz";
            }

            if (i % 5 == 0) {
                result += "Buzz";
            }

            if (result.equals("")) {
                result += i;
            }

            System.out.print(result + " ");
        }
    }
}
