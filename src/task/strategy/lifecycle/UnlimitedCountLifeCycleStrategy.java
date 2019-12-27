package task.strategy.lifecycle;

import task.strategy.lifecycle.TaskLifeCycleStrategy;

public class UnlimitedCountLifeCycleStrategy implements TaskLifeCycleStrategy {

    @Override
    public boolean shouldFinish() {
        return false;
    }

}