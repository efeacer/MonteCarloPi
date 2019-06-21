package modelClasses;

import java.util.concurrent.atomic.AtomicInteger;

public class ParallelPiEstimator extends PiEstimator {

    private AtomicInteger hitCount;
    private Thread[] threads;

    public ParallelPiEstimator(final int numIters, long seed, final int numThreads) {
        super(numIters, seed);
        hitCount = new AtomicInteger(0);
        assert numIters % numThreads == 0 : "Number of iterations must be divisible by number of threads.";
        threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < numIters / numThreads; i++)
                        if (checkPoint())
                            hitCount.getAndIncrement();
                }
            });
        }
    }

    public double getPi() {
        for (Thread t: threads) t.start();
        for (Thread t: threads)
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return 4 * hitCount.get() / (double) totalCount;
    }
}