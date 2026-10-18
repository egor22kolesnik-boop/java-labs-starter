package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void CreateTask() {
        ProjectTask task =new ProjectTask(
                "TASK-1",
                "Изучить Java",
                TaskStatus.NEW,
                5
        );

        assertEquals("TASK-1", task.getId());
        assertEquals("Изучить Java", task.getTitle());
        assertEquals(TaskStatus.NEW, task.getStatus());
        assertEquals(5, task.getEstimatedHours());
        assertFalse(task.isCompleted());
    }

    @Test
    void Status() {
        ProjectTask task = new ProjectTask("TASK-1", "Изучить Java", TaskStatus.NEW, 5);
        task.changeStatus(TaskStatus.IN_PROGRESS);
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }

    @Test
    void CompletedTask() {
        ProjectTask task = new ProjectTask("TASK-1", "Изучить Java", TaskStatus.NEW, 5);
        task.changeStatus(TaskStatus.COMPLETED);
        assertTrue(task.isCompleted());
    }

    @Test
    void EstimatedHours() {
        ProjectTask task = new ProjectTask("TASK-1", "Изучить Java", TaskStatus.NEW, 5);
        task.increaseEstimatedHours(3);
        assertEquals(8, task.getEstimatedHours());
    }

    @Test
    void NullId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ProjectTask(null, "Задача", TaskStatus.NEW, 1)
        );
    }

    @Test
    void BlankId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ProjectTask("   ", "Задача", TaskStatus.NEW, 1)
        );
    }

    @Test
    void NullTitle() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ProjectTask("TASK-1", null, TaskStatus.NEW, 1)
        );
    }

    @Test
    void BlankTitle() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ProjectTask("TASK-1", "   ", TaskStatus.NEW, 1)
        );
    }

    @Test
    void NullStatus() {
        assertThrows(
                NullPointerException.class,
                () -> new ProjectTask("TASK-1", "Задача", null, 1)
        );
    }

    @Test
    void NegativeEstimatedHours() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ProjectTask("TASK-1", "Задача", TaskStatus.NEW, -1)
        );
    }

    @Test
    void NullNewStatus() {
        ProjectTask task = new ProjectTask("TASK-1", "Задача", TaskStatus.NEW, 1);
        assertThrows(NullPointerException.class, () -> task.changeStatus(null));
        assertEquals(TaskStatus.NEW, task.getStatus());
    }

    @Test
    void HourIncrease() {
        ProjectTask task = new ProjectTask("TASK-1", "Задача", TaskStatus.NEW, 5);
        assertThrows(IllegalArgumentException.class, () -> task.increaseEstimatedHours(0));
        assertEquals(5, task.getEstimatedHours());
    }

    @Test
    void Overflow() {
        ProjectTask task = new ProjectTask(
                "TASK-1",
                "Задача",
                TaskStatus.NEW,
                Integer.MAX_VALUE
        );

        assertThrows(ArithmeticException.class, () -> task.increaseEstimatedHours(1));
        assertEquals(Integer.MAX_VALUE,task.getEstimatedHours());
    }
}
