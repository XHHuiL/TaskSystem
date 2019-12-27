package task.strategy.lifecycle;

/**
 * Tasks than can only be performed once, such as registration
 */
public class OnceLifeCycleStrategy implements TaskLifeCycleStrategy {

    @Override
    public boolean shouldFinish() {
        return true;
    }
}