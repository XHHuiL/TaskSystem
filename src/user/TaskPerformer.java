package user;

public class TaskPerformer {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Account account;

    public Account getAccount() {
        return account;
    }

    public TaskPerformer(String name) {
        this.name = name;
        this.account = new Account();
    }
}