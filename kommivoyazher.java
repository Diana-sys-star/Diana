import java.util.*;

public class TravelingSalesman {
    private int[][] graph; // матрица расстояний между городами
    private int numCities; // количество городов

    public TravelingSalesman(int[][] distances) {
        this.graph = distances;
        this.numCities = distances.length;
    }

    // Метод для вычисления минимального пути коммивояжера
    public int solveTSP() {
        // Создаем список с номерами всех городов (кроме начального)
        List<Integer> cities = new ArrayList<>();
        for (int i = 1; i < numCities; i++) {
            cities.add(i);
        }

        int minPath = Integer.MAX_VALUE; // начальное значение минимального пути

        // Перебираем все возможные перестановки городов
        do {
            // Вычисляем стоимость текущего пути
            int currentPath = 0;
            
            // Добавляем расстояние от начального города до первого
            currentPath += graph[0][cities.get(0)];
            
            // Добавляем расстояния между остальными городами
            for (int i = 0; i < cities.size() - 1; i++) {
                currentPath += graph[cities.get(i)][cities.get(i + 1)];
            }
            
            // Возвращаемся в начальный город
            currentPath += graph[cities.get(cities.size() - 1)][0];
            
            // Обновляем минимальный путь, если нашли лучше
            if (currentPath < minPath) {
                minPath = currentPath;
            }
            
        } while (nextPermutation(cities));

        return minPath;
    }

    // Вспомогательный метод для генерации следующей перестановки
    private boolean nextPermutation(List<Integer> array) {
        // Находим самый правый элемент, который меньше следующего
        int i = array.size() - 2;
        while (i >= 0 && array.get(i) >= array.get(i + 1)) {
            i--;
        }
        
        if (i < 0) {
            return false; // перестановок больше нет
        }
        
        // Находим элемент для обмена
        int j = array.size() - 1;
        while (array.get(j) <= array.get(i)) {
            j--;
        }
        
        // Меняем местами
        Collections.swap(array, i, j);
        
        // Переворачиваем оставшуюся часть
        Collections.reverse(array.subList(i + 1, array.size()));
        
        return true;
    }

    // Метод для вывода матрицы расстояний
    public void printGraph() {
        System.out.println("Матрица расстояний:");
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                System.out.print(graph[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Пример матрицы расстояний между 4 городами
        // Город 0: A, Город 1: B, Город 2: C, Город 3: D
        int[][] distances = {
            {0, 10, 15, 20},  // Расстояния от города A
            {10, 0, 35, 25},   // Расстояния от города B
            {15, 35, 0, 30},   // Расстояния от города C
            {20, 25, 30, 0}    // Расстояния от города D
        };

        // Создаем объект коммивояжера
        TravelingSalesman tsp = new TravelingSalesman(distances);
        
        // Выводим матрицу расстояний
        tsp.printGraph();
        
        // Решаем задачу
        int result = tsp.solveTSP();
        
        System.out.println("\nМинимальная длина пути: " + result);
    }
}