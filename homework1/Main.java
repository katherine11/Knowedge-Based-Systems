import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<List<Integer>> loadSource(String source, List<List<Integer>> colElements){

        String line;
        List<Integer> lineElements;

        try(BufferedReader reader = new BufferedReader(new FileReader(source))){

            while((line = reader.readLine()) != null){

                lineElements = new ArrayList<>();
                String [] row = line.split(",");

                for (int element = 0; element < row.length; element++) {
                    lineElements.add(Integer.parseInt(row[element]));
                }

                colElements.add(lineElements);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return colElements;
    }

    public static void main(String[] args) {

        String sourceFile = "schedule_time.csv";

        List<List<Integer>> graphFromCSV = new ArrayList<>(new ArrayList<>());

        graphFromCSV = loadSource(sourceFile, graphFromCSV);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter start and end point: ");
        int start = scanner.nextInt();
        int end = scanner.nextInt();

        Graph graph = new Graph(graphFromCSV);

        ShortestPath path = new ShortestPath(graph,start,end);

        List<Integer> shortestPath = path.getShortestPath();

        for (Integer node : shortestPath) {
            System.out.print(node + " ");
        }

    }
}
