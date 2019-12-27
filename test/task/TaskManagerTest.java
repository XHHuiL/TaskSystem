package task;

import org.junit.Assert;
import org.junit.Test;
import task.strategy.lifecycle.DailyCountDownLifeCycleStrategy;
import task.strategy.lifecycle.OnceLifeCycleStrategy;
import task.strategy.lifecycle.TaskLifeCycleStrategy;
import task.strategy.lifecycle.UnlimitedCountLifeCycleStrategy;
import task.strategy.pointcalc.FixPointStrategy;
import task.strategy.pointcalc.TaskPointCalcStrategy;
import task.transaction.Transaction;
import user.Account;
import user.TaskPerformer;
import user.User;

import java.util.ArrayList;
import java.util.Map;

public class TaskManagerTest {

    /**
     * Level 1
     * test case 1: release a task
     */
    @Test
    public void testReleaseTask() {
        // step 1: build an UserTask
        TaskDefinition def = new TaskDefinition("Consume", "Consumption will make you happy");
        TaskPerformer performer = new TaskPerformer("LiuHui");
        TaskLifeCycleStrategy unlimited = new UnlimitedCountLifeCycleStrategy();
        UserTask task = new UserTaskFactory().setDef(def).setPerformer(performer).setLifeCycleStrategy(unlimited).build();

        // step 2: release this task
        TaskManager taskManager = TaskManager.getInstance();
        taskManager.releaseTask(task);

        // step 3: test
        Assert.assertEquals(1, taskManager.getTaskPoolSize());

        // step 4: remove this task
        taskManager.removeTask(task);
    }


    /**
     * Level 1
     * test case 2: query available tasks and status of tasks
     */
    @Test
    public void testGetTaskInfo() {
        // step 1: build three tasks
        User user = new User("LiuHui");

        TaskDefinition def = new TaskDefinition("Consume", "Consumption will make you happy");
        TaskLifeCycleStrategy unlimited = new UnlimitedCountLifeCycleStrategy();
        UserTask consume = new UserTaskFactory().setDef(def).setPerformer(user).setLifeCycleStrategy(unlimited).build();

        def = new TaskDefinition("Check in", "Check in on time every day");
        TaskLifeCycleStrategy daily = new DailyCountDownLifeCycleStrategy(1);
        UserTask checkIn = new UserTaskFactory().setDef(def).setPerformer(user).setLifeCycleStrategy(daily).build();

        def = new TaskDefinition("Register", "Registration can only be performed once");
        TaskLifeCycleStrategy once = new OnceLifeCycleStrategy();
        UserTask register = new UserTaskFactory().setDef(def).setPerformer(user).setLifeCycleStrategy(once).build();

        // step 2: release these tasks and perform some
        TaskManager taskManager = TaskManager.getInstance();
        taskManager.releaseTask(consume);
        taskManager.releaseTask(checkIn);
        taskManager.releaseTask(register);
        taskManager.performTask(consume);
        taskManager.performTask(register);

        // step 3: query status of tasks
        Map info = taskManager.getTaskInfo();
        Assert.assertEquals(UserTaskStatus.ACTIVE, info.get("Consume"));
        Assert.assertEquals(UserTaskStatus.ACTIVE, info.get("Check in"));
        Assert.assertEquals(UserTaskStatus.FINISHED, info.get("Register"));

        // step 4: remove tasks
        taskManager.removeTask(consume);
        taskManager.removeTask(checkIn);
        taskManager.removeTask(register);
    }


    /**
     * Level 1:
     * test case 3: query amount and flow
     */
    @Test
    public void testGetAccountInfo() {
        // step 1: build two tasks
        User user = new User("LiuHui");

        TaskDefinition def = new TaskDefinition("Recommend", "Recommend other users");
        TaskLifeCycleStrategy unlimited = new UnlimitedCountLifeCycleStrategy();
        TaskPointCalcStrategy fix = new FixPointStrategy(20);
        UserTask recommend = new UserTaskFactory()
                .setDef(def)
                .setPerformer(user)
                .setLifeCycleStrategy(unlimited)
                .setTaskPointCalcStrategy(fix)
                .build();

        def = new TaskDefinition("Comment", "Comments will increase activity");
        unlimited = new UnlimitedCountLifeCycleStrategy();
        fix = new FixPointStrategy(30);
        UserTask comment = new UserTaskFactory()
                .setDef(def)
                .setPerformer(user)
                .setLifeCycleStrategy(unlimited)
                .setTaskPointCalcStrategy(fix)
                .build();

        // step 2: release these tasks and perform some
        TaskManager taskManager = TaskManager.getInstance();
        taskManager.releaseTask(recommend);
        taskManager.releaseTask(comment);
        taskManager.performTask(recommend);
        taskManager.performTask(recommend);
        taskManager.performTask(comment);

        // step 3: query amount and flow
        Account account = user.getAccount();
        Assert.assertEquals(70, account.getBalance());
        ArrayList<Transaction> transactions = account.getTransactions();
        Assert.assertEquals(3, transactions.size());
        String desc = "Recommend: Recommend other users Amount: 20";
        Assert.assertEquals(desc, transactions.get(0).getDescription());
        Assert.assertEquals(desc, transactions.get(1).getDescription());
        desc = "Comment: Comments will increase activity Amount: 30";
        Assert.assertEquals(desc, transactions.get(2).getDescription());

        // step 4: remove tasks
        taskManager.removeTask(recommend);
        taskManager.removeTask(comment);
    }


    /**
     * Level 2
     * test case 1: different types of tasks
     */
    @Test
    public void testDifferentTypeTask() {
        // step 1: build different tasks
        User user = new User("LiuHui");

        TaskDefinition def = new TaskDefinition("Consume", "Consumption will make you happy");
        TaskLifeCycleStrategy unlimited = new UnlimitedCountLifeCycleStrategy();
        UserTask consume = new UserTaskFactory().setDef(def).setPerformer(user).setLifeCycleStrategy(unlimited).build();

        def = new TaskDefinition("Check in", "Check in on time every day");
        TaskLifeCycleStrategy daily = new DailyCountDownLifeCycleStrategy(1);
        UserTask checkIn = new UserTaskFactory().setDef(def).setPerformer(user).setLifeCycleStrategy(daily).build();

        def = new TaskDefinition("Register", "Registration can only be performed once");
        TaskLifeCycleStrategy once = new OnceLifeCycleStrategy();
        UserTask register = new UserTaskFactory().setDef(def).setPerformer(user).setLifeCycleStrategy(once).build();

        // step 2: release these tasks
        TaskManager taskManager = TaskManager.getInstance();
        taskManager.releaseTask(consume);
        taskManager.releaseTask(checkIn);
        taskManager.releaseTask(register);

        // step 3: test
        Assert.assertEquals(3, taskManager.getTaskPoolSize());

        // step 4: remove tasks
        taskManager.removeTask(consume);
        taskManager.removeTask(checkIn);
        taskManager.removeTask(register);
    }


    /**
     * Level 2:
     * test case 2: exchange commodities
     */
    @Test
    public void testExchangeCommodities() {
        // step 1: build tasks
        User user = new User("LiuHui");

        TaskDefinition def = new TaskDefinition("Exchange", "You can exchange points for commodities");
        TaskLifeCycleStrategy unlimited = new UnlimitedCountLifeCycleStrategy();
        TaskPointCalcStrategy fix = new FixPointStrategy(-20);
        UserTask consume = new UserTaskFactory()
                .setDef(def)
                .setPerformer(user)
                .setLifeCycleStrategy(unlimited)
                .setTaskPointCalcStrategy(fix)
                .build();

        def = new TaskDefinition("Comment", "Comments will increase activity");
        unlimited = new UnlimitedCountLifeCycleStrategy();
        fix = new FixPointStrategy(30);
        UserTask comment = new UserTaskFactory()
                .setDef(def)
                .setPerformer(user)
                .setLifeCycleStrategy(unlimited)
                .setTaskPointCalcStrategy(fix)
                .build();

        // step 2: release these tasks and perform some
        TaskManager taskManager = TaskManager.getInstance();
        taskManager.releaseTask(consume);
        taskManager.releaseTask(comment);
        taskManager.performTask(comment);
        taskManager.performTask(consume);

        // step 3: test
        Assert.assertEquals(10, user.getAccount().getBalance());

        // step 4: remove tasks
        taskManager.removeTask(consume);
        taskManager.removeTask(comment);
    }
}