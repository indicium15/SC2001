import java.util.ArrayList;
import java.util.PriorityQueue;

public class PartB{
    public static int[] djikstraAdjacencyList(int vertex, int source, ArrayList<ArrayList<ListNode> > graph){
        //Initialize and define distance array with infinity value
        int[] distance = new int[vertex];
        for(int node:distance){
            node = Integer.MAX_VALUE;
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
                }
            }
        }
        //Return distance array for shortest distance to all paths
        return distance;
    }
    public static void main(String[] args){
        int vertices = 10;
        ArrayList<ArrayList<ListNode> > graph = new ArrayList<>();
        for(int i = 0; i<vertices; i++){
            graph.add(new ArrayList<>());
        }
    }
}