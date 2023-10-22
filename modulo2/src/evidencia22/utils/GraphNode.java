package evidencia22.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a node in a graph.
 *
 * @param <T> The type of data stored in the node.
 */
public class GraphNode<T> {
    private T data;

    private Map<GraphNode<T>, Integer> edgeWeights;

    /**
     * Adds an adjacent node with an associated edge weight.
     *
     * @param adjacentNode The neighboring node.
     * @param edgeWeight   The weight of the edge to the neighboring node.
     */
    public void addEdge(GraphNode<T> adjacentNode, int edgeWeight) {
        edgeWeights.put(adjacentNode, edgeWeight);
    }

    //Class' setters.

    /**
     * Sets the data for the node.
     *
     * @param data The data to be set for the node.
     */
    public void setData(T data) {
        this.data = data;
    }

    //Class' getters.

    /**
     * Gets the data stored in the node.
     *
     * @return The data stored in the node.
     */
    public T getData() {
        return data;
    }

    /**
     * Gets a map of neighboring nodes and their associated edge weights.
     *
     * @return A map of adjacent nodes and their edge weights.
     */
    public Map<GraphNode<T>, Integer> getEdges() {
        return edgeWeights;
    }

    //Overwriting Object class' methods.

    @Override
    public String toString() {
        String strOut = "Nodo: " + this.getData();

        for (Map.Entry<GraphNode<T>, Integer> entry : this.getEdges().entrySet()) {
            strOut += "\n\tVecino: " + entry.getKey().getData() + ", Peso: " + entry.getValue();
        }

        return strOut;
    }

    /**
     * Overrides the equals method to compare nodes based on their data values.
     *
     * @param o The node to compare with.
     * @return True if the nodes have the same data values, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphNode<?> node = (GraphNode<?>) o;
        return Objects.equals(data, node.data);
    }

    /**
     * Overrides the hashCode method to generate a hash code based on the node's data value.
     *
     * @return The hash code calculated based on the data value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    /**
     * Constructs a new node with the specified data.
     *
     * @param data The data to be stored in the node.
     */
    public GraphNode(T data) {
        setData(data);
        edgeWeights = new HashMap<>();
    }
}