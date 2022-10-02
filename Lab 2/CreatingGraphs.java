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
    public ListNode[][] adjacencyMatrix;

    public CreatingGraphs(int input_vertices, int input_weight){
        MAX_WEIGHT = input_weight;
        this.vertices = input_vertices;
        this.p = random.nextFloat();
        //Initialize Adjacency List for All Vertex Nodes:
        for(int i = 0; i<this.vertices; i++){
            adjacencyList.add(new ArrayList<>());
        }
        //Allocate Memory for Adjacency Matrix for Number of Vertices:
        adjacencyMatrix = new ListNode[this.vertices][this.vertices];

        for(int i = 0; i<this.vertices; i++){
            for(int j = 0; j<this.vertices; j++){
                float edgeProbability = random.nextFloat();
                if (edgeProbability < p){
                    int weight = random.nextInt(MAX_WEIGHT-1) + 1;
                    if(i != j){
                        adjacencyList.get(i).add(new ListNode(j,weight));
                        //adjacencyList.get(j).add(new ListNode(i, weight));
                        adjacencyMatrix[i][j] = new ListNode(j,weight);
                        //adjacencyMatrix[j][i] = new ListNode(i,weight);
                        edges+=1;
                    }
                    else{
                        adjacencyMatrix[i][j] = new ListNode(i, 0);
                    }
                }
                else{
                    adjacencyMatrix[i][j] = new ListNode(i, 0);
                }
            }
        }
    }
    public void printAdjacencyMatrix(){
        int columns = adjacencyMatrix.length;
        int rows = adjacencyMatrix[0].length;
        System.out.println("Adjacency Matrix of Graph: ");
        System.out.println("Dimensions: " + columns + "x" + rows);
        for(int i = 0; i<columns;i++){
                System.out.print("(" + i + "): ");
            for(int j = 0; j<rows; j++){
                System.out.print(adjacencyMatrix[i][j].getWeight() + " ");
                //System.out.print(adjacencyMatrix[i][j].vertex + " (" + adjacencyMatrix[i][j].weight + ") ");
            }
            System.out.print("\n");   
        }
    }
    public void printAdjacencyList(){
        int length = this.adjacencyList.size();
        System.out.println("Adjacency List of Graph:");
        for(int i=0; i<length; i++){
            ArrayList<ListNode> cursor = adjacencyList.get(i);
            System.out.print("(" + i + "): ");
            //System.out.print(cursor.get(0).getVertex() + ": ");
            for(int j=0; j<cursor.size();j++){
                System.out.print(cursor.get(j).getVertex() + " (" + cursor.get(j).getWeight() + ") ");
            }
            System.out.print("\n");
        }
    }
    //Testing the constructor:
    public static void main(String args[]){
        CreatingGraphs test = new CreatingGraphs(5, 10);
        test.printAdjacencyMatrix();
        test.printAdjacencyList();
    }
}
