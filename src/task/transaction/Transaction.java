package task.transaction;

import task.UserTaskAction;

public class Transaction {

    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    private String desc;

    public String getDescription() {
        return desc;
    }

    public void setDescription(String desc) {
        this.desc = desc;
    }

    private UserTaskAction action;

    public UserTaskAction getAction() {
        return action;
    }

    public void setAction(UserTaskAction action) {
        this.action = action;
    }

    public Transaction(int amount, String desc, UserTaskAction action) {
        this.amount = amount;
        this.desc = desc;
        this.action = action;
    }
}