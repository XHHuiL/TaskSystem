package task;

import task.strategy.lifecycle.TaskLifeCycleStrategy;
import task.strategy.pointcalc.TaskPointCalcStrategy;
import user.TaskPerformer;

public class UserTaskFactory {

    private volatile static UserTask defaultTask;

    /**
     * @return the default User task
     */
    public static UserTask getDefaultTask() {
        if (defaultTask == null) {
            synchronized (UserTaskFactory.class) {
                if (defaultTask == null) {
                    defaultTask = new UserTask();
                    TaskDefinition def = new TaskDefinition();
                    def.setName("Default User Task");
                    def.setDescription("Check in on time every day");
                    defaultTask.def = def;
                }
            }
        }
        return defaultTask;
    }

    private UserTask task;

    public UserTaskFactory() {
        task = new UserTask();
    }

    public UserTaskFactory setDef(TaskDefinition def) {
        task.def = def;
        return this;
    }

    public UserTaskFactory setPerformer(TaskPerformer performer) {
        task.performer = performer;
        return this;
    }

    public UserTaskFactory setLifeCycleStrategy(TaskLifeCycleStrategy lifeCycleStrategy) {
        task.lifeCycleStrategy = lifeCycleStrategy;
        return this;
    }

    public UserTaskFactory setTaskPointCalcStrategy(TaskPointCalcStrategy pointCalcStrategy) {
        task.pointCalcStrategy = pointCalcStrategy;
        return this;
    }

    public UserTask build() {
        return task;
    }

}