import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {

        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Build undirected graph
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int w = road[2];

            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }

        boolean[] visited = new boolean[n + 1];

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(1);
        visited[1] = true;

        int min = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {

            int city = queue.poll();

            for (int[] edge : graph.get(city)) {

                int next = edge[0];
                int distance = edge[1];

                min = Math.min(min, distance);

                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        return min;
    }
}