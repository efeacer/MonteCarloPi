import modelClasses.ParallelPiEstimator;
import modelClasses.PiEstimator;
import modelClasses.SerialPiEstimator;

public class Main {

    private static final int NUM_ITERS = 1000000;
    private static final long SEED = 7777;
    private static final int NUM_THREADS = 10;

    public static void main(String[] args) {
        long start;
        long stop;

        PiEstimator serialPiEstimator = new SerialPiEstimator(NUM_ITERS, SEED);

        start = System.currentTimeMillis();
        double serialPi = serialPiEstimator.getPi();
        stop = System.currentTimeMillis();
        long serialTime = stop - start;

        System.out.printf("Time needed to estimate pi using %d iterations of " +
                "Monte Carlo method in serial (ms): %d%n", NUM_ITERS, serialTime);
        System.out.printf("Value of pi estimated: %f%n", serialPi);
        System.out.printf("Absolute error in the serial estimation: %f%n", Math.abs(Math.PI - serialPi));
        System.out.println("--------------------------------------------------------");

        PiEstimator parallelPiEstimator = new ParallelPiEstimator(NUM_ITERS, SEED, NUM_THREADS);

        start = System.currentTimeMillis();
        double parallelPi = parallelPiEstimator.getPi();
        stop = System.currentTimeMillis();
        long parallelTime = stop - start;

        System.out.printf("Time needed to estimate pi using %d iterations of " +
                "Monte Carlo method in parallel (ms): %d%n", NUM_ITERS, parallelTime);
        System.out.printf("Value of pi estimated: %f%n", parallelPi);
        System.out.printf("Absolute error in the parallel estimation: %f%n", Math.abs(Math.PI - parallelPi));
    }
}