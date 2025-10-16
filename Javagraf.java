import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<List<Integer>> adjList;

    public Graph(int vertices) {
        adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Метод добавления ребра между вершинами u и v (неориентированный граф)
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    // Получение списка смежных вершин для данной вершины
    public List<Integer> getAdjVertices(int vertex) {
        return adjList.get(vertex);
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5); // Создаем граф с 5 вершинами

        // Добавляем ребра
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        // Выводим список смежных вершин для вершины 1
        System.out.println("Смежные вершины для вершины 1: " + graph.getAdjVertices(1));
    }
}