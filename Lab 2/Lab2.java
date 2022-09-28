import java.util.ArrayList;
import java.util.PriorityQueue;

public class Lab2 {
    public static int comparisions = 0;
    public static void resetComparisions(){
        comparisions = 0;
    }
    //Part A)
    public static int[] dijsktraAdjacencyMatrix(int graph[][], int source){
        int dist[] = new int[graph.length]; // The output array. Carrying the values of minimum distace
        Boolean visited[] = new Boolean[graph.length]; // Array to store visited and unvisted data.
        int last[] = new int[graph.length]; // The output array. Carrying the values of last vertex
        for (int i = 0; i < graph.length; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        // Initialising the conditions before the loop
        dist[source] = 0;
        for(int count = 0; count < graph.length - 1; count++){
            int minVertex = minDistance(dist,visited);
            visited[minVertex] = true;
            for(int v = 0; v< graph.length; v++){
                if (!visited[v] && graph[minVertex][v] != 0
                    && dist[minVertex] != Integer.MAX_VALUE
                    && dist[minVertex] + graph[minVertex][v] < dist[v])
                    dist[v] = dist[minVertex] + graph[minVertex][v];
                    comparisions++;
            }
        }
        //System.out.print("Shortest distance array : ");//1 use this if u want to see the solution to the problem
        //printArray(dist);
        return dist;
    }
    static int minDistance(int distance[], Boolean visited[]){
        int minimum = Integer.MAX_VALUE;
        int min_index = -1;
        for(int i=0;i<distance.length;i++){
            if(visited[i] == false && distance[i] <= minimum){
                minimum = distance[i];
                min_index = i; 
            }
        }
        return min_index;
    } 
    //Part B)
    public static int[] djikstraAdjacencyList(int vertex, int source, ArrayList<ArrayList<ListNode> > graph){
        //Initialize and define distance array with infinity value
        int[] distance = new int[vertex];
        for(int i = 0; i<vertex; i++){
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;
        //Java's in-built priority queue is based on the minimizing heap structure
        //For this program we will directly implement that
        PriorityQueue<ListNode> queue = new PriorityQueue<>(
            (vertex1, vertex2) -> vertex1.getWeight() - vertex2.getWeight()
        );
        queue.add(new ListNode(source,0));
        while(queue.size() > 0){
            //Pop node from the priority queue
            ListNode currentNode = queue.poll();
            //Explore connected nodes
            for(ListNode connectedNodes : graph.get(currentNode.getVertex())){
                //If the distance of the current node and connected nodes is less than current distance
                //Update the value of the distance and add nodes to the priority queue
                if(distance[currentNode.getVertex()] + connectedNodes.getWeight() < distance[connectedNodes.getVertex()]){
                    distance[connectedNodes.getVertex()] = connectedNodes.getWeight() + distance[currentNode.getVertex()];
                    queue.add(new ListNode(connectedNodes.getVertex(), distance[connectedNodes.getVertex()]));
                    comparisions++;
                }
            }
        }
        //Return distance array for shortest distance to all paths
        return distance;
    }
    public static void printArray(int[]array){
        for(int a: array){
            System.out.print(a + " ");     // Function to print a 1d array
        }
    }
    public static void main(String[] args){
        CreatingGraphs graph = new CreatingGraphs(100,10);
        System.out.printf("Edges: %d\n",graph.edges);
        resetComparisions();
        long adjStartTime = System.nanoTime();
        int[] adjDistance = djikstraAdjacencyList(graph.vertices, 0, graph.adjacencyList);
        long adjEndTime = System.nanoTime();
        long adjDuration = adjEndTime - adjStartTime;
        System.out.printf("Time taken for Adjacency List Djiksta: %d nanoseconds \n",adjDuration);
        System.out.printf("Comparisions Made: %d \n",comparisions);
        resetComparisions();
        long admStartTime = System.nanoTime();
        int[] admDistance = dijsktraAdjacencyMatrix(graph.adjacencyMatrix,0);
        long admEndTime = System.nanoTime();
        long admDuration = admEndTime - admStartTime;
        System.out.printf("Time taken for Adjacency Matrix Djiksta: %d nanoseconds \n",admDuration); 
        System.out.printf("Comparisions Made: %d \n",comparisions); 
        if(adjDuration > admDuration){
            System.out.println("Adjacency Matrix was Faster");
        }
        else{
            System.out.println("Adjacency List was Faster");
        }
        printArray(adjDistance);
        System.out.print("\n\n\n");
        printArray(admDistance);

    }
}
