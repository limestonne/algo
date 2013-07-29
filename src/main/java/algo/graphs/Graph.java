package algo.graphs;

import util.ArrayUtils;
import util.InputFile;

import java.util.Arrays;
import java.util.Random;

/**
 * Week 3: Programming assignment
 * Randomized contraction algorithm for the min cut problem
 * Input: adjacency list representation of a simple undirected graph
 *
 * @author: Artur Khalikov
 */
public class Graph {

    private int[][] g;
    private int[] nodes;
    private Random rand = new Random();

    public Graph(int[][] graph) {
        this.g = graph;
    }

    public int findMinCut() {
        int trials = getNumberOfTrials();
        int[] cuts = new int[trials];
        for (int i = 0; i < trials; i++) {
            int[][] cut = ArrayUtils.copyOf(g);
            initNodes();
            while (countNodes() > 2) {
                int u = selectRandomNode();
                int v = selectRandomElement(cut, u);
                while (v == u || nodes[v] == 0)
                    v = selectRandomElement(cut, u);
                contract(cut, u, v);
                removeSelfLoops(cut, u);
            }
            int[][] edges = getCutEdges(cut);
            cuts[i] = edges[0].length - 1;
        }
        Arrays.sort(cuts);
        return cuts[0];
    }

    private void initNodes() {
        nodes = new int[g.length];
        for (int i = 0; i < g.length; i++) nodes[i] = i+1;
    }

    private int getNumberOfTrials() {
        return g.length * g.length;
    }

    private int selectRandomNode() {
        int[] leftNodes = getLeftNodesIndexes();
        return leftNodes[rand.nextInt(leftNodes.length)];
    }

    private int selectRandomElement(int[][] cut, int u) {
        int n = cut[u].length - 1;
        return cut[u][rand.nextInt(n) + 1]-1;
    }

    private void contract(int[][] cut, int u, int v) {
        int numU = cut[u].length;
        int numV = cut[v].length;

        for (int i = 0; i < cut.length; i++) {
            int[] aux = {};
            if (i == u) {
                aux = new int[numU + numV];
                // copy the u-th node into aux array
                for (int j = 0; j < numU; j++)
                    aux[j] = (cut[u][j] != nodes[v]) ? cut[u][j] : nodes[u];
                // copy the v-th node into aux array
                for (int j = 0; j < numV; j++)
                    aux[j + numU] = (j != 0) ? cut[v][j] : nodes[u];
                // replace merged node with aux
                cut[i] = aux;
            }
            else if (i != v && nodes[i] > 0) {
                aux = ArrayUtils.copyOf(cut[i]);
                // simply copy node replacing v's with u's
                for (int j = 1; j < aux.length; j++)
                    if (aux[j] == nodes[v])
                        aux[j] = nodes[u];
                // replace merged node with aux
                cut[i] = aux;
            }
        }
        // mark v-th node as contracted
        nodes[v] = 0;
    }

    private void removeSelfLoops(int[][] cut, int i) {
        int countSelfLoops = 0;
        int n = cut[i].length;
        for (int j = 1; j < n; j++)
            if (cut[i][j] == cut[i][0])
                countSelfLoops++;
        int[] aux = new int[n - countSelfLoops];
        aux[0] = cut[i][0];
        int k = 1;
        for (int j = 1; j < n; j++)
            if (cut[i][j] != cut[i][0])
                aux[k++] = cut[i][j];
        cut[i] = aux;
    }

    private int[][] getCutEdges(int[][] cut) {
        int[][] edges = new int[countNodes()][];
        int j = 0;
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] > 0)
                edges[j++] = cut[i];
        }
        return edges;
    }

    private int countNodes() {
        int count = 0;
        for (int i = 0; i < nodes.length; i++)
            if (nodes[i] > 0) count++;
        return count;
    }

    private int[] getLeftNodesIndexes() {
        int n = countNodes();
        int[] leftNodes = new int[n];
        int j = 0;
        for (int i = 0; i < nodes.length; i++)
            if (nodes[i] > 0)
                leftNodes[j++] = i;
        return leftNodes;
    }

    public static Graph createFromFile(String fileName) {
        int[][] graph = new InputFile(fileName).getAdjacentMatrix();
        return new Graph(graph);
    }

    public int[][] get() {
        return g;
    }
}