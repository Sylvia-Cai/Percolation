public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double[] thresholds;
    private final int T;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        this.T = T;
        thresholds = new double[T];

        for (int exp = 0; exp < T; exp++) {
            Percolation perc = new Percolation(N);
            int opened = 0;
            while (!perc.percolates()) {
                int i = StdRandom.uniformInt(N);
                int j = StdRandom.uniformInt(N);
                if (!perc.isOpen(i, j)) {
                    perc.open(i, j);
                    opened++;
                }
            }
            thresholds[exp] = (double) opened / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    public double confidenceLow() {
        return mean() - (CONFIDENCE_95 * stddev()) / Math.sqrt(T);
    }

    public double confidenceHigh() {
        return mean() + (CONFIDENCE_95 * stddev()) / Math.sqrt(T);
    }

    public static void main(String[] args) {
        int N = 200;
        int T = 200;
        PercolationStats stats = new PercolationStats(N, T);
        System.out.println("mean = " + stats.mean());
        System.out.println("stddev = " + stats.stddev());
        System.out.println("95% CI = [" + stats.confidenceLow() + ", " + stats.confidenceHigh() + "]");
    }
}