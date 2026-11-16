import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {

        int n = online.length;

        List<int[]>[] graph = new ArrayList[n];
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {

            int u = e[0];
            int v = e[1];
            int w = e[2];

            if (online[u] && online[v]) {
                graph[u].add(new int[]{v, w});
                indegree[v]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int[] topo = new int[n];
        int idx = 0;

        while (!q.isEmpty()) {

            int u = q.poll();
            topo[idx++] = u;

            for (int[] edge : graph[u]) {

                int v = edge[0];

                indegree[v]--;

                if (indegree[v] == 0) {
                    q.offer(v);
                }
            }
        }

        int low = 0;
        int high = 1000000000;
        int ans = -1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            long[] dist = new long[n];

            Arrays.fill(dist, Long.MAX_VALUE);

            dist[0] = 0;

            for (int i = 0; i < idx; i++) {

                int u = topo[i];

                if (dist[u] == Long.MAX_VALUE) {
                    continue;
                }

                for (int[] edge : graph[u]) {

                    int v = edge[0];
                    int w = edge[1];

                    if (w >= mid) {

                        long newCost = dist[u] + w;

                        if (newCost < dist[v]) {
                            dist[v] = newCost;
                        }
                    }
                }
            }

            if (dist[n - 1] <= k) {

                ans = mid;
                low = mid + 1;

            } else {

                high = mid - 1;
            }
        }

        return ans;
    }
}