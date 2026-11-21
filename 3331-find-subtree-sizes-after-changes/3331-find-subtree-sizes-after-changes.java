import java.util.*;

class Solution {
    private List<Integer>[] originalTree;
    private List<Integer>[] newTree;
    private int[] newParent;
    private int[] subtreeSize;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        this.originalTree = new ArrayList[n];
        this.newTree = new ArrayList[n];
        this.newParent = Arrays.copyOf(parent, n);
        this.subtreeSize = new int[n];

        for (int i = 0; i < n; i++) {
            originalTree[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            originalTree[parent[i]].add(i);
        }

        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);
        findNewParents(0, lastSeen);

        for (int i = 1; i < n; i++) {
            newTree[newParent[i]].add(i);
        }

        computeSizes(0);
        return subtreeSize;
    }

    private void findNewParents(int u, int[] lastSeen) {
        int charIdx = s.charAt(u) - 'a';
        int prevNode = lastSeen[charIdx];

        if (u != 0 && prevNode != -1) {
            newParent[u] = prevNode;
        }

        lastSeen[charIdx] = u;
        for (int v : originalTree[u]) {
            findNewParents(v, lastSeen);
        }
        lastSeen[charIdx] = prevNode;
    }

    private int computeSizes(int u) {
        int size = 1;
        for (int v : newTree[u]) {
            size += computeSizes(v);
        }
        subtreeSize[u] = size;
        return size;
    }
}