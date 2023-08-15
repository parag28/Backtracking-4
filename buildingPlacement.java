class Solution {
    public int minDistance(int w, int h, int n) {
        int[][] grid = new int[h][w];
        List<int[]> buildings = new ArrayList<>();
        List<int[]> lots = new ArrayList<>();
        
        // Initialize buildings and lots lists
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                lots.add(new int[]{i, j});
            }
        }
        
        int[] result = {Integer.MAX_VALUE};
        backtrack(grid, buildings, lots, n, 0, result);
        
        return result[0];
    }
    
    private void backtrack(int[][] grid, List<int[]> buildings, List<int[]> lots, int n, int idx, int[] result) {
        if (buildings.size() == n) {
            int maxDistance = calculateMaxDistance(grid, buildings, lots);
            result[0] = Math.min(result[0], maxDistance);
            return;
        }
        
        if (idx >= lots.size()) {
            return;
        }
        
        // Place a building
        buildings.add(lots.get(idx));
        backtrack(grid, buildings, lots, n, idx + 1, result);
        buildings.remove(buildings.size() - 1);
        
        // Do not place a building
        backtrack(grid, buildings, lots, n, idx + 1, result);
    }
    
    private int calculateMaxDistance(int[][] grid, List<int[]> buildings, List<int[]> lots) {
        int maxDistance = 0;
        for (int[] lot : lots) {
            int distance = Integer.MAX_VALUE;
            for (int[] building : buildings) {
                int d = Math.abs(lot[0] - building[0]) + Math.abs(lot[1] - building[1]);
                distance = Math.min(distance, d);
            }
            grid[lot[0]][lot[1]] = distance;
            maxDistance = Math.max(maxDistance, distance);
        }
        return maxDistance;
    }
}

