package modelClasses;

public class SerialPiEstimator extends PiEstimator {

    public SerialPiEstimator(int numIters, long seed) { super(numIters, seed); }

    public double getPi() {
        int hitCount = 0;
        for (int i = 0; i < totalCount; i++)
            if (checkPoint()) hitCount++;
        return 4 * hitCount / (double) totalCount;
    }
}