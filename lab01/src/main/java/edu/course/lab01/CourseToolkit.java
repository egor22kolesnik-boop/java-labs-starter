package edu.course.lab01;

/**
 * Небольшие методы для первой лабораторной работы.
 */
public final class CourseToolkit {

    private CourseToolkit() {
        // Утилитарный класс не должен иметь экземпляров.
    }

    /**
     * Возвращает true, если число четное.
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome(String text) {
        if (text == null)
            throw new IllegalArgumentException("throw");
        int i = text.length() - 1;
        String b = "";
        while (0 <= i){
            b += text.charAt(i);
            i--;
        }
        if (text.equals(b))
            return true;
        else
            return false;
    }

    public static double average(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("");
        }
        int sum = 0;
        for (int i : values) {
            sum += i;
        }
        return (double) sum / values.length;
    }
}
