public class Percolation {
    private final int N;
    private final WeightedQuickUnionUF uf;
    private boolean[][] grid;
    private final int virtualTop;
    private final int virtualBottom;
    private int openSites = 0;

    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        this.N = N;
        grid = new boolean[N][N];
        virtualTop = 0;
        virtualBottom = N * N + 1;
        uf = new WeightedQuickUnionUF(N * N + 2); // 虚拟顶部和底部

        // 初始化虚拟节点连接
        for (int i = 0; i < N; i++) {
            uf.union(virtualTop, index(0, i));
            uf.union(virtualBottom, index(N - 1, i));
        }
    }

    private int index(int i, int j) {
        return i * N + j + 1; // +1跳过虚拟顶部
    }

    private void validate(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= N)
            throw new IndexOutOfBoundsException();
    }

    public void open(int i, int j) {
        validate(i, j);
        if (isOpen(i, j)) return;

        grid[i][j] = true;
        openSites++;

        int current = index(i, j);
        // 连接上下左右的开放站点
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            if (ni >= 0 && ni < N && nj >= 0 && nj < N && isOpen(ni, nj)) {
                uf.union(current, index(ni, nj));
            }
        }
    }

    public boolean isOpen(int i, int j) {
        validate(i, j);
        return grid[i][j];
    }

    public boolean isFull(int i, int j) {
        validate(i, j);
        return isOpen(i, j) && uf.connected(virtualTop, index(i, j));
    }

    public boolean percolates() {
        if (N == 1) return isOpen(0, 0); // 处理N=1的边界情况
        return uf.connected(virtualTop, virtualBottom);
    }

    public int numberOfOpenSites() {
        return openSites;
    }
}