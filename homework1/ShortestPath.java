import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestPath {

    Graph graph;
    int start;
    int end;
    int [] nodesDistance;
    int [] nodesParents;
    boolean [] visited;
    List<Pair> path;

    public ShortestPath(Graph graph, int start, int end){

        if(graph != null){
            this.graph = graph;
        }

        this.start = start;
        this.end = end;
    }

    private void initialize(){
        //set default values to all nodes distances equal to "infinity";
        int size = graph.getGraphRepresentation().size();
        nodesDistance = new int[size];
        Arrays.fill(nodesDistance, Integer.MAX_VALUE);

        nodesParents = new int[size];
        Arrays.fill(nodesParents, -1);

        visited = new boolean[size];
        Arrays.fill(visited, false);

        path = new ArrayList<>(size);
    }

    private List<Pair> dijkstra(){

        initialize();

        int size = graph.getGraphRepresentation().size();

        //each map contains the parent node and the min node distance;
        nodesDistance[start] = 0;
        nodesParents[start] = 0;

        while(!visited[end]){

            //it must return always 0 in the first iteration;
            int currentMinDistanceIndex = getMinDistanceIndex(nodesDistance, visited);

            visited[currentMinDistanceIndex] = true;

            updateNeighboursPath(currentMinDistanceIndex, size);
        }

        return path;
    }

    private int getMinDistanceIndex(int [] nodesDistance, boolean [] visited){

        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int neighbour = 0; neighbour < nodesDistance.length; neighbour++) {

            if(!visited[neighbour] && nodesDistance[neighbour] < min){
                min = nodesDistance[neighbour];
                minIndex = neighbour;
            }
        }

        return minIndex;
    }

    private void updateNeighboursPath(int currentMinDistanceIndex, int size) {

        List<List<Integer>> graphMatrix = graph.getGraphRepresentation();
        List<Pair> pairs = new ArrayList<>();

        for (int neighbour = 0; neighbour < size; neighbour++) {

            //needed for the first iteration:
            if(graphMatrix.get(currentMinDistanceIndex).get(neighbour) < Integer.MAX_VALUE){
                nodesDistance[neighbour] = graphMatrix.get(currentMinDistanceIndex).get(neighbour);
            }

            int distanceSum = nodesDistance[currentMinDistanceIndex] + graphMatrix.get(currentMinDistanceIndex).get(neighbour);

            if(!visited[neighbour] && distanceSum < nodesDistance[currentMinDistanceIndex]){
                nodesDistance[neighbour] = distanceSum;
                nodesParents[neighbour] = currentMinDistanceIndex;
                //0->parent,1->distance
                if(neighbour > path.size() - 1){
                    path.add(new Pair(nodesDistance[neighbour], nodesParents[neighbour]));
                }
                else{
                    boolean hasChange = path.get(neighbour).getFirst() != nodesParents[neighbour] &&
                            path.get(neighbour).getSecond() != nodesDistance[neighbour];
                    if(hasChange){
                        path.get(neighbour).setFirst(nodesParents[neighbour]);
                        path.get(neighbour).setSecond(nodesDistance[neighbour]);
                    }
                }
            }
        }
    }

    List<Integer> getShortestPath(){

        //use dijkstra to get all neighbours:
        List<Pair> path = dijkstra();

        //construct result:
        List<Integer> result = new ArrayList<>();

        return result;
    }


}
