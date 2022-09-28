
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.IntStream;
import java.io.IOException;
import javax.xml.stream.EventFilter;
import java.lang.Math;


class vertex implements Comparable<vertex> {
    public int vertexNum;
    public int edgeweight;

    public vertex(int vertex , int edgeweight){
        this.vertexNum = vertex;
        this.edgeweight = edgeweight;
    }
    
    public int compareTo(vertex Edge) {
        // TODO Auto-generated method stub
        if (this.edgeweight >= Edge.edgeweight) {
            return 1; 
        } 
        else{
        return 0;
        }
    }

    
}


public class djalgo {
    public static int comparisions = 0;
    
    public static void main (String[] args){
        Part1();

        }



        
    public static void randgraph (int size,int graph[][],int range, int edges){  //  Algorithm to produce random graphs (Matrix) as data for the algorithms

        Random rand = new Random();        
        int x;
        int number;
        int counter = (size*(size-1)) - edges ;
        for (x = 0 ; x < (size); x++){
            for (int y =0 ; y < size ; y++){
                if (y == x){    
                    continue;
                }
                int temp = rand.nextInt(range) +1;
                graph[x][y]  = temp;
                
            }
            
            }

            while(counter>0){
                int temp = rand.nextInt(size);
                int temp2 = rand.nextInt(size);
                if(graph[temp][temp2] != 0 ){
                    graph[temp][temp2] = 0;
                    counter--;
                }

            }

            // Fill the matrix first then remove random elements to get a more balanced matrix.

            


        }

        



    
    public static void printArray(int[]array){
        for(int a: array){
            System.out.print(a + " ");     // Function to print a 1d array
        }
    }

    public static void DijsktraAlgoMatrix(int graph[][], int source){
        int dist[] = new int[graph.length]; // The output array. Carrying the values of minimum distace
        Boolean visited[] = new Boolean[graph.length]; // Array to store visited and unvisted data.
        PriorityQueue<vertex> pq = new PriorityQueue<>(); // priority queue using the class edge
        int last[] = new int[graph.length]; // The output array. Carrying the values of last vertex


        for (int i = 0; i < graph.length; i++) {
            dist[i] = 9999999;
            visited[i] = false;
        }// Initialising the conditions before the loop
        dist[source] = 0;
        pq.add(new vertex(source, 0));
        while(!pq.isEmpty()){
                // Add new edges
                // Compare to old distance
                // Add the visited stuff
                // 
                
                 vertex node = pq.remove();
                 visited[node.vertexNum] = true;
                 for (int x = 0 ; x < graph.length ; x++){
                    if (graph[node.vertexNum][x] != 0 && visited[x] == false){
                        comparisions++;
                        if ((node.edgeweight + graph[node.vertexNum][x] )< dist[x]){
                            dist[x] = node.edgeweight + graph[node.vertexNum][x];
                            pq.add(new vertex(x, dist[x]));
                        }
                    }
                    else{
                        comparisions++;
                    }

                 }
            
        }
        System.out.print("Shortest distance array : ");//1 use this if u want to see the solution to the problem
       printArray(dist);
    }

    public static void Print2dArray(int arr[][],int rows,int columns){   // Function to print 2d array
        System.out.println('\n');
        for (int x = 0 ; x < rows ; x++){
            for (int y = 0 ; y < columns ; y++){
                System.out.print(Integer.toString(arr[x][y]) + ' ');
            }
            System.out.println('\n');
        }
        
    }


    public static void Part1(){     // Function to get user input and run the DJ algo.
        Scanner sc = new Scanner(System.in);
        System.out.println("Number of graphs to be produced : ");
        int num = sc.nextInt();
        System.out.println("Number of vertex :  ");
        int vertex = sc.nextInt();
        System.out.println("Maximum range of weight ");
        int range = sc.nextInt();
        Random rand = new Random();   
        

        int sValues[] = IntStream.range(1,101).toArray();

        
        for (int x = 0 ; x < num ; x++){
                int edges = rand.nextInt((vertex*(vertex-1))+1 - vertex);     
                //System.out.println("Graph " + (x+1));
                int graph[][] = new int[vertex][vertex];
                randgraph(vertex, graph, range, edges);
                Print2dArray(graph, vertex, vertex); //Use this to see all the arrays that are produced.
                comparisions = 0;
                long startTime = System.nanoTime();
                DijsktraAlgoMatrix(graph, 0);
                long stopTime = System.nanoTime();
                System.out.printf(" Vertex Size %d |Edges Size %d  | Time Taken %d  | Comparisons %d\n",vertex,edges,stopTime-startTime,comparisions);

            }
            
    }

    public static void Part2(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Number of graphs to be produced : ");
        int num = sc.nextInt();
        System.out.println("Number of edges :  ");
        int edges = sc.nextInt();
        System.out.println("Maximum range of weight ");
        int range = sc.nextInt();
        Random rand = new Random();   

        int sValues[] = IntStream.range(1,101).toArray();

        for (int  x = 0 ; x< num ; x++){
            System.out.println("Number of vertices :");
            int vertex = sc.nextInt();
            int graph[][] = new int[vertex][vertex];
            randgraph(vertex, graph, range, edges);
            Print2dArray(graph, vertex, vertex); //Use this to see all the arrays that are produced.
            comparisions = 0;
            long startTime = System.nanoTime();
            DijsktraAlgoMatrix(graph, 0);
            long stopTime = System.nanoTime();
            System.out.printf(" Vertex Size %d |Edges Size %d  | Time Taken %d  | Comparisons %d\n",vertex,edges,stopTime-startTime,comparisions);

        }



    }



    
    
}
