package user;

import task.transaction.Transaction;

import java.util.ArrayList;

public class Account {

    private int balance = 0;

    private void addPoint(int point) {
        this.balance += point;
    }

    public int getBalance() {
        return balance;
    }

    private ArrayList<Transaction> transactions;

    public void recodeTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        addPoint(transaction.getAmount());
    }

    public ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }

    public Account() {
        this.transactions = new ArrayList<>();
    }
}