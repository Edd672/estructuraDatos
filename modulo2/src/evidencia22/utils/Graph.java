package evidencia22.utils;

import evidencia22.utils.Edge;
import evidencia22.utils.MinimumSpanningTree;

import java.util.*;

/**
 * Represents a weighted directed graph.
 *
 * @param <T> The type of data stored in the graph nodes.
 */
public class Graph<T> {
    private Set<GraphNode<T>> nodes;
    // HashMap para almacenar distancias desde el nodo fuente
    private HashMap<GraphNode<T>, Integer> distance;

    /**
     * Initializes an empty graph.
     */
    public Graph() {
        nodes = new HashSet<>();
        distance = new HashMap<>();
        // Inicializa distance con valores predeterminados
        for (GraphNode<T> node : nodes) {
            distance.put(node, Integer.MAX_VALUE);
        }
    }

    /**
     * Adds a node to the graph if it doesn't already exist.
     *
     * @param newNode The node to be added.
     */
    public void addNode(GraphNode<T> newNode) {
        if (newNode != null && !nodes.contains(newNode)) {
            nodes.add(newNode);
        }
    }

    /**
     * Adds an edge between two nodes with an associated weight.
     *
     * @param from   The source node.
     * @param to     The target node.
     * @param weight The weight of the edge.
     */
    public void addEdge(GraphNode<T> from, GraphNode<T> to, int weight) {
        if (from != null && to != null) {
            from = getNode(from.getData());
            from.addEdge(to, weight);
            addNode(from);
            addNode(to);
        }
    }

    //Search methods.

    /**
     * Perform a depth-first search (DFS) to determine if there is a path from the source node to the destination node.
     *
     * @param source      The source node.
     * @param destination The destination node.
     * @return True if there is a path, false otherwise.
     */
    public boolean isPathExistsDFS(GraphNode<T> source, GraphNode<T> destination) {
        System.out.println("Depht First Search");

        if (source == null || destination == null) {
            return false;
        }

        Set<GraphNode<T>> visited = new HashSet<>();
        Stack<GraphNode<T>> stack = new Stack<>();

        stack.push(source);
        visited.add(source);

        while (!stack.isEmpty()) {
            GraphNode<T> currentNode = stack.pop().get();
            System.out.println("Current node: \n" + currentNode);

            if (currentNode.equals(destination)) {
                return true;
            }

            for (GraphNode<T> neighbor : currentNode.getEdges().keySet()) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return false;
    }

    /**
     * Perform a breadth-first search (BFS) to determine if there is a path from the source node to the destination node.
     *
     * @param source      The source node.
     * @param destination The destination node.
     * @return True if there is a path, false otherwise.
     */
    public boolean isPathExistsBFS(GraphNode<T> source, GraphNode<T> destination) {
        System.out.println("Breadth-First Search");

        if (source == null || destination == null) {
            return false;
        }

        Set<GraphNode<T>> visited = new HashSet<>();
        Queue<GraphNode<T>> queue = new Queue<>();

        queue.push(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            GraphNode<T> currentNode = queue.peek().get();
            System.out.println("Current node:\n" + currentNode);

            if (currentNode.equals(destination)) {
                return true;
            }

            for (GraphNode<T> neighbor : currentNode.getEdges().keySet()) {
                if (!visited.contains(neighbor)) {
                    queue.push(neighbor);
                    visited.add(neighbor);
                }
            }

            queue.pop();
        }

        return false;
    }

    //Shortest path algorithms

    /**
     * Perform Dijkstra's algorithm to determine the shortest path between two nodes in a weightred graph..
     *
     * @param source      The source node.
     * @param destination The destination node.
     * @return the distance of the shortest path if there is one, -1 otherwise.
     */
    public int shortestPathDijkstra(GraphNode<T> source, GraphNode<T> destination) {
        if (source == null || destination == null) {
            return -1;
        }

        Map<GraphNode<T>, Integer> distance = new HashMap<>();
        Set<GraphNode<T>> visited = new HashSet<>();

        for (GraphNode<T> node : nodes) {
            distance.put(node, Integer.MAX_VALUE);
        }

        distance.put(source, 0);

        while (!visited.contains(destination)) {
            GraphNode<T> currentNode = null;
            int minDistance = Integer.MAX_VALUE;

            // Find the node with the minimum distance among unvisited nodes
            for (GraphNode<T> node : nodes) {
                if (!visited.contains(node) && distance.get(node) < minDistance) {
                    currentNode = node;
                    minDistance = distance.get(node);
                }
            }

            if (currentNode == null) {
                break; // No path found
            }

            visited.add(currentNode);

            for (Map.Entry<GraphNode<T>, Integer> entry : currentNode.getEdges().entrySet()) {
                GraphNode<T> neighbor = entry.getKey();
                int weight = entry.getValue();
                if (!visited.contains(neighbor)) {
                    int newDistance = distance.get(currentNode) + weight;
                    if (newDistance < distance.get(neighbor)) {
                        distance.put(neighbor, newDistance);
                    }
                }
            }
        }

        return distance.get(destination);
    }

    // Minimum spam tree algorithms

    /**
     * Perform Dijkstra's algorithm to determine the shortest path between two nodes in a weightred graph..
     *
     * @return MST suing Kruskal's algorithm.
     */
    public MinimumSpanningTree<T> kruskal() {
        MinimumSpanningTree<T> mst = new MinimumSpanningTree<>();
        List<Edge<T>> sortedEdges = new ArrayList<>();

        for (GraphNode<T> node : nodes) {
            mst.addNode(node);
            for (Map.Entry<GraphNode<T>, Integer> entry : node.getEdges().entrySet()) {
                GraphNode<T> neighbor = entry.getKey();
                int weight = entry.getValue();
                sortedEdges.add(new Edge<>(node, neighbor, weight));
            }
        }

        // Ordena las aristas por peso de menor a mayor
        sortedEdges.sort(Comparator.comparingInt(Edge::getWeight));

        for (Edge<T> edge : sortedEdges) {
            GraphNode<T> from = edge.getFrom();
            GraphNode<T> to = edge.getTo();

            if (!find(mst, from).equals(find(mst, to))) {
                mst.addEdge(from, to, edge.getWeight());
                union(mst, from, to);
            }
        }

        return mst;
    }

    private GraphNode<T> find(MinimumSpanningTree<T> mst, GraphNode<T> node) {
        for (GraphNode<T> n : mst.getNodes()) {
            if (n.equals(node)) {
                return n;
            }
        }
        return null;
    }

    private void union(MinimumSpanningTree<T> mst, GraphNode<T> a, GraphNode<T> b) {
        GraphNode<T> rootA = find(mst, a);
        GraphNode<T> rootB = find(mst, b);
        if (rootA != null && rootB != null) {
            mst.addNode(rootA);
            mst.addNode(rootB);
        }
    }

    public MinimumSpanningTree<T> prim() {
        MinimumSpanningTree<T> mst = new MinimumSpanningTree<>();
        Set<GraphNode<T>> visited = new HashSet<>();

        if (nodes.isEmpty()) {
            return mst;
        }

        // Comienza con un nodo arbitrario, por ejemplo, el primer nodo en el conjunto
        GraphNode<T> startNode = nodes.iterator().next();
        visited.add(startNode);

        while (visited.size() < nodes.size()) {
            Edge<T> minEdge = null;

            for (GraphNode<T> visitedNode : visited) {
                for (Map.Entry<GraphNode<T>, Integer> entry : visitedNode.getEdges().entrySet()) {
                    GraphNode<T> neighbor = entry.getKey();
                    int weight = entry.getValue();

                    if (!visited.contains(neighbor) && (minEdge == null || weight < minEdge.getWeight())) {
                        minEdge = new Edge<>(visitedNode, neighbor, weight);
                    }
                }
            }

            if (minEdge != null) {
                visited.add(minEdge.getTo());
                mst.addEdge(minEdge.getFrom(), minEdge.getTo(), minEdge.getWeight());
            }
        }

        return mst;
    }

    //Class' getters.

    /**
     * Retrieves a node by its data value.
     *
     * @param data The data value of the desired node.
     * @return The node with the specified data value, or null if not found.
     */
    public GraphNode<T> getNode(T data) {
        Optional<GraphNode<T>> foundNode = nodes.stream()
                .filter(node -> data.equals(node.getData()))
                .findFirst();
        return foundNode.orElse(null);
    }

    /**
     * Gets the set of nodes in the graph.
     *
     * @return The set of nodes.
     */
    public Set<GraphNode<T>> getNodes() {
        return nodes;
    }

}

