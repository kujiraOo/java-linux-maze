import java.io.File;

public class MazeSolver {
    public static void main(String[] args) {
        String filePath = args[0];
        File mazeFile = new File(filePath);
        Graph graph = new Graph();
        graph.readGraph(mazeFile);

        for (String arg : args) {
            System.out.println(arg);
        }

        solve(graph);
    }

    public static void solve(Graph graph) {
        int from = 0;
        int to  = graph.nodes() - 1;

        boolean visited[] = new boolean[graph.nodes()];
        int pred[] = new int[graph.nodes()];
        graph.dfs(from, visited, pred);

        // then check if there is a solution by looking from the backwards to the start
        int steps[] = new int[graph.nodes()];
        System.out.println("\nMaze solution from " + from + " to " + to);

        if (!visited[to]) {
            System.out.println("No solution\n");
            return;
        }

        int n = mazeSolution(from, to, pred, steps);
        for (int i = 0; i < n; i++)
            System.out.print(steps[i] + " ");
        System.out.println();
        System.out.println();
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
