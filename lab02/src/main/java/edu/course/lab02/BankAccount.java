package edu.course.lab02;

public class BankAccount {

    private int balance;

    public BankAccount (int balance){
        if (balance < 0) {
            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным");
        }

        this.balance = balance;
    }

    public void deposit (int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Депозит может быть только положительным");
        }
        balance += amount;
    }

    public void withdraw (int amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Недостаточно средств");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Отрицательное число");
        }
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}