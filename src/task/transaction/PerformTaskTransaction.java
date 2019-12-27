package task.transaction;

import task.UserTaskAction;

public class PerformTaskTransaction extends Transaction {

    public PerformTaskTransaction(int amount, String desc, UserTaskAction action) {
        super(amount, desc, action);
    }

}