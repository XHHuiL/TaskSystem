package task;

import task.strategy.lifecycle.OnceLifeCycleStrategy;
import task.strategy.lifecycle.TaskLifeCycleStrategy;
import task.strategy.pointcalc.FixPointStrategy;
import task.strategy.pointcalc.TaskPointCalcStrategy;
import task.transaction.PerformTaskTransaction;
import user.TaskPerformer;

import java.util.ArrayList;
import java.util.Date;

public class UserTask {
    /**
     * task definition: define the name and description of the task
     */
    TaskDefinition def;

    public TaskDefinition getDef() {
        return def;
    }

    public String getName() {
        return def.getName();
    }

    public String getDescription() {
        return def.getDescription();
    }


    /**
     * task performer: task's performer, not just users
     */
    TaskPerformer performer;

    public TaskPerformer getPerformer() {
        return performer;
    }


    /**
     * task performer's actions
     */
    private ArrayList<UserTaskAction> actions;

    private void recordAction(UserTaskAction action) {
        actions.add(action);
    }


    /**
     * task status: may be UserTaskStatus.ACTIVE or UserTaskStatus.FINISHED
     */
    private UserTaskStatus status = UserTaskStatus.ACTIVE;

    public UserTaskStatus getStatus() {
        return status;
    }


    /**
     * life cycle strategy of this task, such as once a day or more
     * default strategy is OnceLifeCycleStrategy
     */
    TaskLifeCycleStrategy lifeCycleStrategy;


    /*
    * point calculate strategy of this task
    * default amount is 10
    * */
    TaskPointCalcStrategy pointCalcStrategy;


    /**
     * @return whether the task is perform successfully
     */
    boolean perform() {
        if (this.status == UserTaskStatus.FINISHED)
            return false;
        // step 1: record this action
        UserTaskAction action = new UserTaskAction(new Date());
        recordAction(action);

        // step 2: recode corresponding transaction
        int amount = pointCalcStrategy.calcPoint();
        String desc = getName() + ": " + getDescription() + " Amount: " + amount;
        PerformTaskTransaction transaction = new PerformTaskTransaction(amount, desc, action);
        performer.getAccount().recodeTransaction(transaction);

        if (lifeCycleStrategy.shouldFinish())
            this.status = UserTaskStatus.FINISHED;
        return true;
    }

    UserTask() {
        actions = new ArrayList<>();
        lifeCycleStrategy = new OnceLifeCycleStrategy();
        pointCalcStrategy = new FixPointStrategy(10);
    }
}