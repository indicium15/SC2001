public class vertex implements Comparable<vertex> {
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
