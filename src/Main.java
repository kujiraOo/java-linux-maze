public class Main {
    public static void main(String[] args) {
        GraphGenerator graphGenerator = new GraphGenerator();
        Graph graph = graphGenerator.nextGraph();

        System.out.println(graph);
        System.out.println();

        for (int i : graphGenerator.getPath()) {
            System.out.print(" " + i);
        }

        System.out.println();

        int FROM = 0;
        int TO  = 14;

        boolean visited[] = new boolean[graph.nodes()];
        int pred[] = new int[graph.nodes()];
        graph.dfs(FROM, visited, pred);

        // then check if there is a solution by looking from the backwards to the start
        int steps[] = new int[graph.nodes()];
        System.out.println("\nMaze solution from " + FROM + " to " + TO);;
        int n = mazeSolution(FROM, TO, pred, steps);
        for (int i = 0; i < n; i++)
            System.out.print(steps[i] + " ");
        System.out.println();

    }

    private static int mazeSolution(int from, int to, int pred[], int steps[]) {
        int i, n, node;
        // first count how many edges between the end and the start
        node = to; n = 1;
        while ((node = pred[node]) != from) n++;
        // then reverse pred[] array to steps[] array
        node = to; i = n;
        while (i >= 0) {
            steps[i--] = node;
            node = pred[node];
        }
        // include also the end vertex
        return (n+1);
    }
}
