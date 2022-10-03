import java.util.List;

public class MinHeap {
    private ListNode[] heap;
    private int size;
    private int maxsize;
    public int heapComparisons = 0;
    private static final int FRONT = 1;
    public void resetComparisions(){
        heapComparisons = 0;
    }
    public MinHeap(int maxsize){
        this.maxsize = maxsize;
        this.size = 0;
        heap = new ListNode[this.maxsize+1];
        heap[0] = new ListNode(-1, -1);
    }
    private int parent(int pos){
        return pos/2;
    }
    private int leftChild(int pos){
        return (2*pos);
    }
    private int rightChild(int pos){
        return (2*pos)+1;
    }
    private boolean isLeaf(int pos){
        if(pos > size/2){
            return true;
        }
        else{
            return false;
        }
    }
    private void swap(int firstPos, int secondPos){
        ListNode temp = heap[firstPos];
        heap[firstPos] = heap[secondPos];
        heap[secondPos] = temp;
    }
    private void heapify(int pos){
        if(!isLeaf(pos)){
            int swapPos = pos;
            ListNode swapNode = heap[pos];
            if(rightChild(pos) <= size){
                swapPos = heap[leftChild(pos)].getWeight()<heap[rightChild(pos)].getWeight()?leftChild(pos):rightChild(pos);
                heapComparisons++;
            }
            else{
                swapNode = heap[leftChild(pos)];
            }
            if(heap[pos].getWeight() > heap[leftChild(pos)].getWeight() || heap[pos].getWeight() > heap[rightChild(pos)].getWeight()){
                heapComparisons++;
                swap(pos,swapPos);
                heapify(swapPos);
            }
        }
    }
    public void insert(ListNode element){
        if(size >= maxsize) return;
        heap[++size] = element;
        int current = size;
        //System.out.println(current);
        while(heap[current].getWeight() < heap[parent(current)].getWeight()){
            heapComparisons++;
            swap(current, parent(current));
            current = parent(current);
        }
    }
    public void print(){
        for(int i = 1; i<=size/2; i++){
            System.out.print(" PARENT : " + heap[i]
            + " LEFT CHILD : " + heap[2 * i]
            + " RIGHT CHILD :" + heap[2 * i + 1]);
            System.out.println();
        }
    }
    public ListNode remove(){
        ListNode popped = heap[FRONT];
        heap[FRONT] = heap[size--];
        heapify(FRONT);
        return popped;
    }
    public int getSize(){
        return size;
    }
    public int getComparisons(){
        return heapComparisons;
    }
}
