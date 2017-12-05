public class Test {
    public static void main(String[] args) {
        int nNodes = Integer.parseInt(args[0]);
        int pathLength = Integer.parseInt(args[1]);
        int nEdges = Integer.parseInt(args[2]);

        GraphGenerator graphGenerator = new GraphGenerator(nNodes, pathLength, nEdges);
        Graph graph = graphGenerator.nextGraph();

        System.out.println(graph);

        MazeSolver.solve(graph);
    }
}
