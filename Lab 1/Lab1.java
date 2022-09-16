import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Lab1{
    public static int comparisions = 0;
    /**
     * Method for performing insertion sort on an input array. Used in the hybridSort() method when the threshold size is reached.
     * Returns the sorted array.
     * @param array
     * @return
     */
    public static int[] insertionSort(int[] array){
        for(int i = 0; i<array.length;i++){
            int key = i;
            for(int l = i-1; l>=0; l--){
                if(array[key] < array[l]){
                    comparisions++;
                    int temp = array[key];
                    array[key] = array[l];
                    array[l] = temp;
                    key = l;
                }
            }
        }
        return array;
    }

    /**
     * Task for Lab1 - implementing hybridSort() by combining the insertionSort and mergeSort algorithms.
     * Returns the sorted array.
     * @param temp
     * @param limitingFactor
     * @return
     */
    public static int[] hybridSort(int[] temp, int limitingFactor){
        if(temp.length <= limitingFactor){
            int[] result = insertionSort(temp);
            return result;
        }
        else{
            int len = temp.length;
            int mid = (int) Math.ceil(len/2);
            //Finding the mid value and creating two subarrays for the code
            int[] array1 = Arrays.copyOfRange(temp, 0, mid);
            int[] array2 = Arrays.copyOfRange(temp, mid, temp.length);
            //Recursive HybridSort call on the two subarrays
            array1 = hybridSort(array1, limitingFactor);
            array2 = hybridSort(array2, limitingFactor);
            //Merging the Sorted Halves
            int[] temparray = new int[len];
            int x = 0;
            int y = 0;
            int z = 0;
            int len1 = array1.length;
            int len2 = array2.length;
            while(x < len1 && y<len2){
                if(array1[x] <= array2[y]){
                    comparisions+=1;
                    temparray[z] = array1[x];
                    z++;
                    x++;
                }
                else{
                    comparisions+=1;
                    temparray[z] = array2[y];
                    z++;
                    y++;
                }
            }
            while(x<len1){
                temparray[z] = array1[x];
                z++;
                x++;
            }
            while(y<len2){
                temparray[z] = array2[y];
                z++;
                y++;
            }
            //Return sorted array
            return temparray;
        }
    }

    /**
     * Merge function call that is used for the mergeSort() algorithm below.
     * Used to compare performance of our hybridSort() against mergeSort().
     * @param array
     * @param start
     * @param mid
     * @param end
     */
    public void merge(int[] array, int start, int mid, int end){
        int i,j,k;
        int n1 = mid-start+1;
        int n2 = end-mid;
        int left[] = new int[n1];
        int right[] = new int[n2];
        for(i = 0; i<n1; i++){
            left[i] = array[start+i];
        }
        for(j=0; j<n2; j++){
            right[j] = array[mid+1+j];
        }
        i=0;
        j=0;
        k=start;
        while(i<n1 && j<n2){
            if(left[i] <= right[j]){
                array[k] = left[i];
                i++;
                comparisions++;
            }
            else{
                array[k] = right[j];
                j++;
                comparisions++;
            }
            k++;
        }
        while(i<n1){
            array[k] = left[i];
            i++;
            k++;
        }
        while(j<n2){
            array[k] = right[j];
            j++;
            k++;
        }
    }

    /**
     * mergeSort() algorithm following the pseudocode from the SC2001 lecture.
     * Used to compare the performance of mergeSort() against hybridSort().
     * @param array
     * @param start
     * @param end
     */
    public void mergeSort(int[]array, int start, int end){
        if(start < end){
            int middle = (start+end) / 2;
            mergeSort(array, start, middle);
            mergeSort(array, middle+1, end);
            merge(array, start, middle, end);
        }
    }

    /**
     * Function to print all elements in an array.
     * @param array
     */
    public void printArray(int[]array){
        for(int a: array){
            System.out.print(a + " ");
        }
    }

    /**
     * Function to generate the input data as required to analyze the performance of mergeSort and insertionSort().
     * Inputs: Max integer "x" and number of arrays to generate. 
     * @return 
     */
    public static int[][] generateInputData(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the max integer 'x' for the array:");
        int maxInteger = sc.nextInt();
        System.out.println("Enter the number of arrays to generate:");
        int numberArrays = sc.nextInt();
        int arraySize = 1000;
        int[][] inputData = new int[numberArrays][];
        for(int i = 0; i<numberArrays; i++){
            int[] temp = new int[arraySize];
            for(int j = 0; j<arraySize;j++){
                Random random = new Random();
                int randomInsert = random.nextInt(maxInteger-1) + 1;
                temp[j] = randomInsert;
            }
            inputData[i] = temp;
            arraySize *= 10;
            System.out.printf("Array %d generated\n",i+1);
        }
        sc.close();
        return inputData;
    } 
    public static void resetCounter(){
        comparisions=0;
    }
    public static void main(String[] args) throws IOException{
        //Example Class 1
        //Generate Input Data
        int[][] inputData = generateInputData();

        //Run Hybrid Sort on Different Sized Datasets with S=10
        System.out.println("Hybrid Sort wth Fixed S=10");
        for(int i = 0; i<inputData.length; i++){
            resetCounter();
            long startTime = System.nanoTime();
            hybridSort(inputData[i], 10);
            long stopTime = System.nanoTime();
            System.out.printf("Array Size %d | Threshold Size %d | Comparisons %d | Time Taken %d \n",inputData[i].length, 10, comparisions,stopTime-startTime);
        }

        //Run Hybrid Sort on Dataset with N=1,000,000 and various values of S
        System.out.println("Hybrid Sort wth Varied S");
        int sValues[] = IntStream.range(1,101).toArray();
        for(int i = 0; i<inputData.length; i++){
            resetCounter();
            for(int threshold:sValues){
                resetCounter();
                long startTime = System.nanoTime();
                hybridSort(inputData[i], threshold);
                long stopTime = System.nanoTime();
                System.out.printf("Array Size %d | Threshold Size %d | Comparisons %d | Time Taken %d \n",inputData[i].length, threshold, comparisions,stopTime-startTime);
            }
        }
    }
}