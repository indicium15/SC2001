import java.util.Random;
import java.util.ArrayList;

public class CreatingGraphs {
    public int vertices;
    public float p;
    public int MAX_VERTICES = 10;
    public int MAX_WEIGHT = 10;
    public int edges = 0;
    Random random = new Random();
    public ArrayList<ArrayList<ListNode> > adjacencyList = new ArrayList<>();
    public int[][] adjacencyMatrix;

    public CreatingGraphs(int input_vertices, int input_weight){
        MAX_WEIGHT = input_weight;
        this.vertices = input_vertices;
        this.p = random.nextFloat();
        //Initialize Adjacency List for All Vertex Nodes:
        for(int i = 0; i<this.vertices; i++){
            adjacencyList.add(new ArrayList<>());
        }
        //Allocate Memory for Adjacency Matrix for Number of Vertices:
        adjacencyMatrix = new int[this.vertices][this.vertices];
        for(int i = 0; i<this.vertices; i++){
            for(int j = 0; j<this.vertices; j++){
                float edgeProbability = random.nextFloat();
                if (edgeProbability < p){
                    int weight = random.nextInt(MAX_WEIGHT-1) + 1;
                    adjacencyList.get(i).add(new ListNode(j,weight));
                    adjacencyMatrix[i][j] = weight;
                    edges+=1;
                }
            }
        }
    }
    public void printAdjacencyMatrix(){
        int columns = adjacencyMatrix.length;
        int rows = adjacencyMatrix[0].length;
        for(int i = 0; i<columns;i++){
            for(int j = 0; j<rows; j++){
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.print("\n");   
        }
    }
    public void printAdjacencyList(){
        int length = this.adjacencyList.size();
        for(int i = 0; i<length; i++){
            ArrayList<ListNode> cursor = adjacencyList.get(i);
            //System.out.print(cursor.get(0).getVertex() + ": ");
            for(int j = 0; j<cursor.size();j++){
                System.out.print(cursor.get(j).getWeight() + " ");
            }
            System.out.print("\n");
        }
    }
}
