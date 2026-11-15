import java.util.*;

class Solution {

    public int maximumSafenessFactor(List<List<Integer>> grid) {

        int n = grid.size();

        int[][] dist = new int[n][n];

        Queue<int[]> q = new LinkedList<>();

        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        // Step 1: Multi-Source BFS

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (grid.get(i).get(j) == 1) {
                    q.offer(new int[]{i, j});
                    dist[i][j] = 0;
                }
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!q.isEmpty()) {

            int[] curr = q.poll();

            int x = curr[0];
            int y = curr[1];

            for (int k = 0; k < 4; k++) {

                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && ny >= 0 &&
                    nx < n && ny < n &&
                    dist[nx][ny] == -1) {

                    dist[nx][ny] = dist[x][y] + 1;

                    q.offer(new int[]{nx, ny});
                }
            }
        }

        // Step 2: Modified Dijkstra

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b[0], a[0])
        );

        boolean[][] visited = new boolean[n][n];

        pq.offer(new int[]{dist[0][0], 0, 0});

        while (!pq.isEmpty()) {

            int[] curr = pq.poll();

            int safe = curr[0];
            int x = curr[1];
            int y = curr[2];

            if (visited[x][y]) {
                continue;
            }

            visited[x][y] = true;

            if (x == n - 1 && y == n - 1) {
                return safe;
            }

            for (int k = 0; k < 4; k++) {

                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && ny >= 0 &&
                    nx < n && ny < n &&
                    !visited[nx][ny]) {

                    int newSafe = Math.min(
                        safe, dist[nx][ny]
                    );

                    pq.offer(new int[]{newSafe, nx, ny});
                }
            }
        }

        return 0;
    }
}