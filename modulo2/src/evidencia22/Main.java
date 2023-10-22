package evidencia22;

import evidencia22.utils.Edge;
import evidencia22.utils.GraphNode;
import evidencia22.utils.MinimumSpanningTree;
import evidencia22.utils.Graph;

public class Main {
    public static void main(String[] args) {
        // Crear un grafo
        Graph<String> graph = new Graph<>();

        // Crear nodos
        GraphNode<String> nodeA = new GraphNode<>("A");
        GraphNode<String> nodeB = new GraphNode<>("B");
        GraphNode<String> nodeC = new GraphNode<>("C");
        GraphNode<String> nodeD = new GraphNode<>("D");

        // Agregar nodos al grafo
        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);

        // Definir las conexiones entre nodos y sus pesos
        graph.addEdge(nodeA, nodeB, 1);
        graph.addEdge(nodeA, nodeC, 3);
        graph.addEdge(nodeB, nodeC, 1);
        graph.addEdge(nodeB, nodeD, 4);
        graph.addEdge(nodeC, nodeD, 1);

        // Encontrar la distancia mínima entre dos nodos (por ejemplo, de A a D)
        int shortestDistance = graph.shortestPathDijkstra(nodeA, nodeD);

        if (shortestDistance != -1) {
            System.out.println("La distancia mínima entre A y D es: " + shortestDistance);
        } else {
            System.out.println("No se encontró un camino entre A y D.");
        }

        // Encontrar el árbol mínimo de expansión (MST) usando Kruskal
        MinimumSpanningTree<String> kruskalMST = graph.kruskal();

        System.out.println("Resultados del algoritmo Kruskal:");
        System.out.println("Nodos del MST:");
        for (GraphNode<String> node : kruskalMST.getNodes()) {
            System.out.println(node.getData());
        }

        System.out.println("Aristas del MST:");
        for (Edge<String> edge : kruskalMST.getEdges()) {
            System.out.println(edge.getFrom().getData() + " - " + edge.getTo().getData() + " (" + edge.getWeight() + ")");
        }

        // Encontrar el árbol mínimo de expansión (MST) usando Prim
        MinimumSpanningTree<String> primMST = graph.prim();

        System.out.println("Resultados del algoritmo Prim:");
        System.out.println("Nodos del MST:");
        for (GraphNode<String> node : primMST.getNodes()) {
            System.out.println(node.getData());
        }

        System.out.println("Aristas del MST:");
        for (Edge<String> edge : primMST.getEdges()) {
            System.out.println(edge.getFrom().getData() + " - " + edge.getTo().getData() + " (" + edge.getWeight() + ")");
        }
    }
}
