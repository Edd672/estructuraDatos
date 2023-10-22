package evidencia22.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinimumSpanningTree<T> {
    private Set<GraphNode<T>> nodes;
    private List<Edge<T>> edges;

    public MinimumSpanningTree() {
        nodes = new HashSet<>();
        edges = new ArrayList<>();
    }

    public void addNode(GraphNode<T> node) {
        nodes.add(node);
    }

    public void addEdge(GraphNode<T> from, GraphNode<T> to, int weight) {
        edges.add(new Edge<>(from, to, weight));
    }

    public Set<GraphNode<T>> getNodes() {
        return nodes;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }
}

