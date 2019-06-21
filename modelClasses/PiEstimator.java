package modelClasses;

import java.util.Random;

public abstract class PiEstimator {

    int totalCount;
    private Random random;

    PiEstimator(int numIters, long seed) {
        totalCount = numIters;
        random = new Random(seed);
    }

    public abstract double getPi();

    boolean checkPoint() {
        double x = random.nextDouble();
        double y = random.nextDouble();
        return x * x + y * y < 1;
    }
}