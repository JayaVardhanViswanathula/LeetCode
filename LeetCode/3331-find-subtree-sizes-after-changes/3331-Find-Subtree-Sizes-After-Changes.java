import java.util.*;

class Solution {

    List<Integer>[] graph;
    int[] newParent;
    String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;

        graph = new ArrayList[n];
        newParent = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            graph[parent[i]].add(i);
        }

        int[] last = new int[26];
        Arrays.fill(last, -1);

        dfs(0, last, parent);

        List<Integer>[] tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[newParent[i]].add(i);
        }

        int[] ans = new int[n];

        calculateSize(0, tree, ans);

        return ans;
    }

    void dfs(int node, int[] last, int[] parent) {

        int c = s.charAt(node) - 'a';

        int old = last[c];

        if (node == 0) {
            newParent[node] = -1;
        } else {
            newParent[node] = (old == -1) ? parent[node] : old;
        }

        last[c] = node;

        for (int child : graph[node]) {
            dfs(child, last, parent);
        }

        last[c] = old;
    }

    int calculateSize(int node, List<Integer>[] tree, int[] ans) {

        ans[node] = 1;

        for (int child : tree[node]) {
            ans[node] += calculateSize(child, tree, ans);
        }

        return ans[node];
    }
}