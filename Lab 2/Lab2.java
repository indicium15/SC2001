import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class Lab2 {
    public static int comparisions = 0;
    public static void resetComparisions(){
        comparisions = 0;
    }
    //Part A)
    public static int[] dijsktraAdjacencyMatrix(ListNode graph[][], int source){
        int dist[] = new int[graph.length]; // The output array. Carrying the values of minimum distace
        int visited[] = new int[graph.length]; // Array to store visited and unvisted data.
        PriorityQueue<ListNode>pq = new PriorityQueue<>();
        int last[] = new int[graph.length]; // The output array. Carrying the values of last vertex
        for (int i = 0; i < graph.length; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = 0;
        }
        // Initialising the conditions before the loop
        dist[source] = 0;
        //dist[source] = graph[source][source].getWeight();
        pq.add(new ListNode(source, 0));
        while(!pq.isEmpty()){
            ListNode node = pq.remove();
            int nodeVertex = node.getVertex();
            int nodeWeight = node.getWeight();
            //System.out.println("Exploring Node " + nodeVertex + " with weight " + nodeWeight);
            visited[nodeVertex] = 1;
            for (int x = 0 ; x < graph.length ; x++){
                if (graph[nodeVertex][x].getWeight() != 0 && visited[x] != 0){
                    comparisions++;
                    if ((nodeWeight + graph[nodeVertex][x].getWeight()) < dist[x]){
                        dist[nodeVertex] = nodeWeight + graph[nodeVertex][x].getWeight();
                        pq.add(new ListNode(x, dist[node.getVertex()]));
                    }
                }
                else{
                    comparisions++;
                }
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
        int[] visited = new int[vertex];
        for(int i = 0; i<vertex; i++){
            distance[i] = Integer.MAX_VALUE;
            visited[i] = 0;
        }
        //Initialize and define visited array with value 0
        distance[source] = 0;
        //distance[source] = graph.get(source).get(source).getWeight();
        visited[source] = 1;
        //Java's in-built priority queue is based on the minimizing heap structure
        //For this program we will directly implement that
        PriorityQueue<ListNode> queue = new PriorityQueue<>(
            (vertex1, vertex2) -> vertex1.getWeight() - vertex2.getWeight()
        );
        queue.add(new ListNode(source,0));
        while(queue.size() > 0){
            //Pop node from the priority queue
            ListNode currentNode = queue.poll();
            //visited[currentNode.getVertex()] = 1;
            //Explore connected nodes
            for(ListNode connectedNodes : graph.get(currentNode.getVertex())){
                //If the distance of the current node and connected nodes is less than current distance
                //Update the value of the distance and add nodes to the priority queue
                if(distance[currentNode.getVertex()] + connectedNodes.getWeight() < distance[connectedNodes.getVertex()]){
                    distance[connectedNodes.getVertex()] = connectedNodes.getWeight() + distance[currentNode.getVertex()];
                    queue.add(new ListNode(connectedNodes.getVertex(), distance[connectedNodes.getVertex()]));
                }
                comparisions++;
            }
        }
        //Return distance array for shortest distance to all paths
        return distance;
    }
    public static void printArray(int[]array){
        for(int a: array){
            if(a == Integer.MAX_VALUE){
                System.out.print(" - ");
            }
            else{
                System.out.print(a + " ");     // Function to print a 1d array
            }
        }
    }
    public static void main(String[] args){
        CreatingGraphs graph = new CreatingGraphs(4,10);
        graph.printAdjacencyList();
        System.out.println("");
        graph.printAdjacencyMatrix();
        System.out.printf("Edges: %d\n",graph.edges);
        System.out.println("");
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
            System.out.println("Adjacency Matrix was Faster\n");
        }
        else{
            System.out.println("Adjacency List was Faster\n");
        }
        System.out.println("Dijksta AdjList Output:");
        printArray(adjDistance);
        System.out.print("\n\n");
        System.out.println("Dijksta AdjMatrix Output:");
        printArray(admDistance);
    }
}
