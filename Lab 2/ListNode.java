public class ListNode implements Comparable<ListNode> {
   int vertex, weight;
   ListNode(int vertex, int weight){
    this.vertex = vertex;
    this.weight = weight;
    } 
   int getVertex(){
    return this.vertex;
    }
   int getWeight(){
    return this.weight;
    }
    public int compareTo(ListNode edge){
        if(this.weight >= edge.weight){
            return 1;
        }
        else{
            return 0;
        }
    }
}
