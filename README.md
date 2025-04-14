# Percolation Project - README
## 1. PROJECT DESCRIPTION
This program estimates the percolation threshold via Monte Carlo simulation.
- Problem: Determine the minimum fraction of open sites in an N×N grid required for top-to-bottom connectivity.
- Algorithm: Uses Union-Find (WeightedQuickUnionUF by default) for efficient connectivity checks.
- Key Features:
  - Virtual top/bottom nodes for O(1) percolation checks.
  - Statistical analysis with confidence intervals.

## 2. HOW TO RUN
### STEP 1: Compile the code
javac -cp .:lib/stdlib.jar src/*.java -d out/
### STEP 2: Execute experiments
java -cp out:lib/stdlib.jar PercolationStats <N> <T>
- Replace <N> with grid size (e.g., 200)
- Replace <T> with number of trials (e.g., 100)
EXAMPLE:
java -cp out:lib/stdlib.jar PercolationStats 200 100

## 3. SAMPLE OUTPUT
Mean: 0.592993499999997
Standard Deviation: 0.0087690421552567
95% Confidence Interval: [0.5912745987737567, 0.5947124012262428]

## 4. PERFORMANCE ANALYSIS
### Time Complexity (N=200, T=100):
- QuickFindUF: ~O(T*N⁴) → Hours-level runtime (not recommended)
- WeightedQuickUnionUF: ~O(T*N² log N) → 1-2 seconds
### Memory Usage:
- boolean[N][N] grid: N² bytes
- Union-Find structures: ~2N² bytes
- Total: ~3N² bytes (e.g., 120KB for N=200)
### Scalability:
- Doubling N → 4x time increase
- Doubling T → 2x time increase

## 5. IMPLEMENTATION NOTES
- Virtual Nodes:
  Pre-connects virtual top/bottom nodes to first/last rows for instant percolation checks.
- Edge Cases:
  - Handles N=1 grids correctly (single open site percolates).
  - Validates indices 0 ≤ i,j < N.
- Randomization:
  Uses StdRandom.uniformInt() for uniform site selection.

## 6. DEPENDENCIES
- Required: stdlib.jar (Princeton library)
- Provided: QuickFindUF.java, WeightedQuickUnionUF.java
- Prohibited: Do NOT modify provided Union-Find implementations.

## 7. REFERENCES
- Algorithm: "Algorithms, 4th Edition" by Sedgewick & Wayne
- Percolation Theory: https://en.wikipedia.org/wiki/Percolation_threshold

## 9. CONTACT
Report issues to: linyuecai811@gmail.com
Source Code: https://github.com/Sylvia-Cai/percolation
