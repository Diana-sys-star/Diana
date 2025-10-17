import java.util.*;

public class TravelingSalesman {
    private int[][] graph; // ������� ���������� ����� ��������
    private int numCities; // ���������� �������

    public TravelingSalesman(int[][] distances) {
        this.graph = distances;
        this.numCities = distances.length;
    }

    // ����� ��� ���������� ������������ ���� ������������
    public int solveTSP() {
        // ������� ������ � �������� ���� ������� (����� ����������)
        List<Integer> cities = new ArrayList<>();
        for (int i = 1; i < numCities; i++) {
            cities.add(i);
        }

        int minPath = Integer.MAX_VALUE; // ��������� �������� ������������ ����

        // ���������� ��� ��������� ������������ �������
        do {
            // ��������� ��������� �������� ����
            int currentPath = 0;
            
            // ��������� ���������� �� ���������� ������ �� �������
            currentPath += graph[0][cities.get(0)];
            
            // ��������� ���������� ����� ���������� ��������
            for (int i = 0; i < cities.size() - 1; i++) {
                currentPath += graph[cities.get(i)][cities.get(i + 1)];
            }
            
            // ������������ � ��������� �����
            currentPath += graph[cities.get(cities.size() - 1)][0];
            
            // ��������� ����������� ����, ���� ����� �����
            if (currentPath < minPath) {
                minPath = currentPath;
            }
            
        } while (nextPermutation(cities));

        return minPath;
    }

    // ��������������� ����� ��� ��������� ��������� ������������
    private boolean nextPermutation(List<Integer> array) {
        // ������� ����� ������ �������, ������� ������ ����������
        int i = array.size() - 2;
        while (i >= 0 && array.get(i) >= array.get(i + 1)) {
            i--;
        }
        
        if (i < 0) {
            return false; // ������������ ������ ���
        }
        
        // ������� ������� ��� ������
        int j = array.size() - 1;
        while (array.get(j) <= array.get(i)) {
            j--;
        }
        
        // ������ �������
        Collections.swap(array, i, j);
        
        // �������������� ���������� �����
        Collections.reverse(array.subList(i + 1, array.size()));
        
        return true;
    }

    // ����� ��� ������ ������� ����������
    public void printGraph() {
        System.out.println("������� ����������:");
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                System.out.print(graph[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // ������ ������� ���������� ����� 4 ��������
        // ����� 0: A, ����� 1: B, ����� 2: C, ����� 3: D
        int[][] distances = {
            {0, 10, 15, 20},  // ���������� �� ������ A
            {10, 0, 35, 25},   // ���������� �� ������ B
            {15, 35, 0, 30},   // ���������� �� ������ C
            {20, 25, 30, 0}    // ���������� �� ������ D
        };

        // ������� ������ ������������
        TravelingSalesman tsp = new TravelingSalesman(distances);
        
        // ������� ������� ����������
        tsp.printGraph();
        
        // ������ ������
        int result = tsp.solveTSP();
        
        System.out.println("\n����������� ����� ����: " + result);
    }
}