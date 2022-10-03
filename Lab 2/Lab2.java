import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import javax.naming.spi.DirStateFactory;


public class Lab2 {
    public static int comparisions = 0;
    public static void resetComparisions(){
        comparisions = 0;
    }
    //Part A)

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
    public static int[] djikstraAdjacencyList(int vertex, int source, ArrayList<ArrayList<ListNode>> graph){
        int[] distance = new int[vertex];
        int[] visited = new int[vertex];
        for(int i = 0; i<vertex; i++){
            distance[i] = Integer.MAX_VALUE;
            visited[i] = 0;
        }
        distance[source] = 0;
        MinHeap queue = new MinHeap(vertex);
        queue.insert(new ListNode(source, 0));
        while(queue.getSize()>0){
            ListNode currentNode = queue.remove();
            System.out.println("Exploring the Current Node " + currentNode.getVertex());
            ArrayList<ListNode> toExplore = graph.get(currentNode.getVertex());
            for(ListNode n : toExplore){
                printArray(distance);
                System.out.println("\t Exploring connected node " + n.getVertex() + " with distance " + distance[n.getVertex()]); 
                if(distance[currentNode.getVertex()] + n.getWeight() < distance[n.getVertex()]){
                    System.out.println("\t\t Distance of " + distance[n.getVertex()] + " is greater than " + (distance[currentNode.getVertex()] + n.getWeight()));
                    distance[n.getVertex()] = distance[currentNode.getVertex()] + n.getWeight();
                    queue.insert(new ListNode(n.getVertex(), distance[n.getVertex()]));
                    System.out.println("\t\t\t Heapifying Comparisons " + queue.getComparisons());
                    comparisions+= queue.getComparisons();
                    queue.resetComparisions();
                }
                comparisions++;
            }
        }
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
        printArray(adjDistance);
        resetComparisions();
        
    }
}
