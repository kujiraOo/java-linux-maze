import java.security.SecureRandom;

public class GraphGenerator {

    private int nNodes;
    private int pathLength;
    private int nEdges;
    private SecureRandom random;
    private int[] path;

    public GraphGenerator(int nNodes, int pathLength, int nEdges) {
        this.nNodes = nNodes;
        this.pathLength = pathLength;
        this.nEdges = nEdges;

        random = new SecureRandom();
    }

    public int[] getPath() {
        return path;
    }

    public Graph nextGraph() {
        Graph graph = new Graph(nNodes);

        if (pathLength > 0) {
            int[] path = new int[pathLength];
            path[0] = 0;
            path[pathLength - 1] = nNodes - 1;

            for (int i = 1; i < pathLength - 1; i++) {
                path[i] = getNextPathStep(path);
            }

            this.path = path;

            for (int i = 0; i < pathLength - 1; i++) {
                graph.addEdge(path[i], path[i + 1]);
            }
        }

        for (int i = 0; i < nEdges; i++) {
            graph.addEdge(random.nextInt(nNodes - 1), random.nextInt(nNodes - 1));
        }

        return graph;
    }

    private int getNextPathStep(int[] path) {
        SecureRandom random = new SecureRandom();

        int nextStep = random.nextInt(nNodes - 2) + 1;

        while (arrayContains(path, nextStep)) {
            nextStep = random.nextInt(nNodes - 2) + 1;
        }

        return nextStep;
    }

    private boolean arrayContains(int[] array, int x) {
        for (int n : array)
            if (n == x) return true;

        return false;
    }
}
