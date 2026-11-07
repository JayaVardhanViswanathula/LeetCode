import java.util.*;

class Solution {
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] depth = new int[n + 1];
        Arrays.fill(depth, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        depth[1] = 0;

        int maxDepth = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            maxDepth = Math.max(maxDepth, depth[node]);

            for (int next : graph.get(node)) {
                if (depth[next] == -1) {
                    depth[next] = depth[node] + 1;
                    queue.offer(next);
                }
            }
        }

        long result = 1;
        long mod = 1000000007;

        for (int i = 0; i < maxDepth - 1; i++) {
            result = (result * 2) % mod;
        }

        return (int) result;
    }
}