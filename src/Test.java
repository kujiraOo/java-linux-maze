public class Test {
    public static void main(String[] args) {
        GraphGenerator graphGenerator = new GraphGenerator(15, 0, 30);
        Graph graph = graphGenerator.nextGraph();

        System.out.println(graph);

        MazeSolver.solve(graph);
    }
}
