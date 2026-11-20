import java.util.*;

class Solution {

    public int countCompleteComponents(int n, int[][] edges) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Build adjacency list
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        boolean[] visited = new boolean[n];

        int ans = 0;

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                int[] count = new int[2];

                dfs(i, graph, visited, count);

                int vertices = count[0];
                int degreeSum = count[1];

                int actualEdges = degreeSum / 2;

                int expectedEdges = vertices * (vertices - 1) / 2;

                if (actualEdges == expectedEdges) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private void dfs(int node, List<List<Integer>> graph,
                     boolean[] visited, int[] count) {

        visited[node] = true;

        count[0]++;
        count[1] += graph.get(node).size();

        for (int neighbor : graph.get(node)) {

            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited, count);
            }
        }
    }
}