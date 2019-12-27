package task.strategy.pointcalc;

public class FixPointStrategy implements TaskPointCalcStrategy {

    private int point;

    public FixPointStrategy(int point) {
        this.point = point;
    }

    @Override
    public int calcPoint() {
        return point;
    }
}