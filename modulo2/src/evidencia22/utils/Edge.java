package evidencia22.utils;

public class Edge<T> {
    private GraphNode<T> from;
    private GraphNode<T> to;
    private int weight;

    public Edge(GraphNode<T> from, GraphNode<T> to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public GraphNode<T> getFrom() {
        return from;
    }

    public GraphNode<T> getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }
}

