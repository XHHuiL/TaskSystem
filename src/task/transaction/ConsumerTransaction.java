package task.transaction;

import task.UserTaskAction;

public class ConsumerTransaction extends Transaction {

    public ConsumerTransaction(int amount, String desc, UserTaskAction action) {
        super(amount, desc, action);
    }
}