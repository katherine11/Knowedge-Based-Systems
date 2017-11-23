import java.util.List;

public class Graph {

    List<List<Integer>> graphRepresentation;

    Graph(List<List<Integer>> graphRepresentation){
        this.graphRepresentation = graphRepresentation;
    }

    public List<List<Integer>> getGraphRepresentation() {
        return graphRepresentation;
    }
}
