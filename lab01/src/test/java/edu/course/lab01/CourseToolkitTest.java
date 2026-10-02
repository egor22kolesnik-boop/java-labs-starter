package edu.course.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);

        assertFalse(result);
    }

    @Test
    void returnsTrueForNegativeEvenNumber() {

        assertTrue(CourseToolkit.isEven(-8));
    }

    @Test
    void Test_1_ForPrimeNumber() {
        boolean result = CourseToolkit.isPrime(1);

        assertFalse(result);
    }

    @Test
    void Test_2_ForPrimeNumber() {
        boolean result = CourseToolkit.isPrime(2);

        assertTrue(result);
    }

    @Test
    void Test_3_ForPrimeNumber() {
        boolean result = CourseToolkit.isPrime(20);

        assertFalse(result);
    }

    @Test
    void Test_4_ForPrimeNumber() {
        boolean result = CourseToolkit.isPrime(49);

        assertFalse(result);
    }

    @Test
    void Test_1_ForPalindromeString() {
        boolean result = CourseToolkit.isPalindrome("level");

        assertTrue(result);
    }

    @Test
    void Test_2_ForPalindromeString() {
        boolean result = CourseToolkit.isPalindrome("home");

        assertFalse(result);
    }

    @Test void isPalindromeThrowsForNull() {
        assertThrows(IllegalArgumentException.class,
                () -> CourseToolkit.isPalindrome(null));
    }

    @Test
    void Test_1_ForAverageNumber() {
        double result = CourseToolkit.average(new int[]{2, 4, 6});
        boolean b = false;
        if (result == 4.0)
            b = true;
        assertTrue(b);
    }

    @Test
    void Test_2_ForAverageNumber() {
        double result = CourseToolkit.average(new int[] {-2,-6,-7});

        boolean b = false;
        if(result==-5.0)
            b = true;
        assertTrue(b);
    }

    @Test
    void Test__ForValues() {
        assertThrows(IllegalArgumentException.class,
                () -> CourseToolkit.average(null));
        assertThrows(IllegalArgumentException.class,
                () -> CourseToolkit.average(new int[] {}));
    }
}
