
class Solution {
    static final int MOD = 1000000007;

    int LOG;
    int[][] up;
    int[] depth;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {

        int n = edges.length + 1;

        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        up = new int[n + 1][LOG];
        depth = new int[n + 1];

        int[][] graph = new int[n + 1][];
        int[] degree = new int[n + 1];

        for (int[] e : edges) {
            degree[e[0]]++;
            degree[e[1]]++;
        }

        for (int i = 1; i <= n; i++) {
            graph[i] = new int[degree[i]];
        }

        int[] idx = new int[n + 1];

        for (int[] e : edges) {
            graph[e[0]][idx[e[0]]++] = e[1];
            graph[e[1]][idx[e[1]]++] = e[0];
        }

        int[] queue = new int[n];
        int head = 0, tail = 0;

        queue[tail++] = 1;
        up[1][0] = 0;

        while (head < tail) {

            int u = queue[head++];

            for (int v : graph[u]) {
                if (v == up[u][0]) {
                    continue;
                }

                depth[v] = depth[u] + 1;
                up[v][0] = u;

                for (int j = 1; j < LOG; j++) {
                    up[v][j] = up[up[v][j - 1]][j - 1];
                }

                queue[tail++] = v;
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int u = queries[i][0];
            int v = queries[i][1];

            int lca = findLCA(u, v);

            int dist = depth[u] + depth[v] - 2 * depth[lca];

            ans[i] = dist == 0 ? 0 : (int) power(2, dist - 1);
        }

        return ans;
    }

    int findLCA(int u, int v) {

        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[u][j];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }

        return up[u][0];
    }

    long power(long base, int exp) {

        long result = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = result * base % MOD;
            }

            base = base * base % MOD;
            exp >>= 1;
        }

        return result;
    }
}
