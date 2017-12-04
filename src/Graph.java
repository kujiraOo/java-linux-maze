import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph {
    private Node[] nodes;
    private int startNode;

    public Graph() {}

    public Graph(int size) {
        nodes = new Node[size];
    }

    /* tell how many nodes a graph has */
    public int nodes() {
        return nodes.length;
    }

    /* read a graph from the file */
    public boolean readGraph(File file) {
        try {
            Scanner scanner = new Scanner(file);

            ArrayList<String> lines = new ArrayList<>();

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                lines.add(line);
            }

            nodes = new Node[lines.size()];

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] vertexStrings = line.split(" ");

                int nodeIndex = Integer.parseInt(vertexStrings[0]);

                for (int j = 1; j < vertexStrings.length; j++) {
                    int vertex = Integer.parseInt(vertexStrings[j]);

                    addNode(nodeIndex, new Node(vertex));
                }
            }

            return false;
        } catch (Exception e) {
            System.out.println(e);

            return false;
        }
    }

    private void addNode(int index, Node node) {
        if (index == node.data)
            return;

        if (nodes[index] == null) {
            nodes[index] = node;
        } else {
            Node lastNode = nodes[index];

            if (lastNode.data == node.data)
                return;

            while (lastNode.next != null) {
                lastNode = lastNode.next;

                if (lastNode.data == node.data)
                    return;
            }

            lastNode.next = node;
        }
    }

    public void addEdge(int from, int to) {
        addNode(from, new Node(to));
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < nodes.length; i++) {
            result.append(i);

            Node node = nodes[i];

            while (node != null) {
                result.append(" ").append(node.data);
                node = node.next;
            }

            result.append("\n");
        }

        return result.toString();
    }

    /* print a graph */
    void printGraph() {

    }

    /* Depth First Search
* start is the node from where the search begins
* visited is an array of all the visited nodes
* pred is an describing the search path
*/
    void dfs(int start, boolean visited[], int pred[]) {
        startNode = start;

        performDfs(start, visited, pred);
    }

    void performDfs(int start, boolean visited[], int pred[]) {
        visited[start] = true;

        Node nodeToVisit = nodes[start];

        while (nodeToVisit != null) {
            if (!visited[nodeToVisit.data]) {
                pred[nodeToVisit.data] = start;
                performDfs(nodeToVisit.data, visited, pred);
            }

            nodeToVisit = nodeToVisit.next;
        }

        if (start != startNode)
            dfs(pred[start], visited, pred);
    }
}

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}
