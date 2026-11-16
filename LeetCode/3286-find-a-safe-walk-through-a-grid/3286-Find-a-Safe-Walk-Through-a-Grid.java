class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {

        int m = grid.size();
        int n = grid.get(0).size();

        int[][] dist = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        Deque<int[]> dq = new ArrayDeque<>();

        dist[0][0] = grid.get(0).get(0);
        dq.offer(new int[]{0, 0});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!dq.isEmpty()) {

            int[] curr = dq.pollFirst();

            int r = curr[0];
            int c = curr[1];

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {

                    int cost = grid.get(nr).get(nc);
                    int newCost = dist[r][c] + cost;

                    if (newCost < dist[nr][nc]) {

                        dist[nr][nc] = newCost;

                        if (cost == 0) {
                            dq.offerFirst(new int[]{nr, nc});
                        } else {
                            dq.offerLast(new int[]{nr, nc});
                        }
                    }
                }
            }
        }

        return dist[m - 1][n - 1] < health;
    }
}