package task.strategy.lifecycle;

public class DailyCountDownLifeCycleStrategy implements TaskLifeCycleStrategy {

    private int count;

    public DailyCountDownLifeCycleStrategy(int count) {
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