package task.strategy.lifecycle;

public class TotalCountDownLifeCycleStrategy implements TaskLifeCycleStrategy {

    private int count;

    public TotalCountDownLifeCycleStrategy(int count) {
        this.count = count;
    }

    @Override
    public boolean shouldFinish() {
        if (this.count > 1) {
            this.count--;
            return false;
        } else
            return true;
    }
}