package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CourseToolkitTest {

    @Test
    void withdrawBalance() {
        BankAccount account = new BankAccount(3000);
        account.withdraw(1000);
        assertEquals(2000, account.getBalance());
    }

    @Test
    void ThrowsWarn() {
        BankAccount account = new BankAccount(1000);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(1500));
        assertEquals(1000, account.getBalance());
    }


}
