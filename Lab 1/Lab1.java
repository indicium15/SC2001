import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Lab1{
    public static int comparisions = 0;
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
    public static int[] hybridSort(int[] temp, int limitingFactor){
        if(temp.length <= limitingFactor){
            int[] result = insertionSort(temp);
            return result;
        }
        else{
            int len = temp.length;
            int mid = (int) Math.ceil(len/2);
            //System.out.printf("Debugging: mid is %d\n",mid);
            int[] array1 = Arrays.copyOfRange(temp, 0, mid);
            int[] array2 = Arrays.copyOfRange(temp, mid, temp.length);
            
            array1 = hybridSort(array1, limitingFactor);
            array2 = hybridSort(array2, limitingFactor);
            
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
            return temparray;
        }
    }
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
    public void mergeSort(int[]array, int start, int end){
        if(start < end){
            int middle = (start+end) / 2;
            mergeSort(array, start, middle);
            mergeSort(array, middle+1, end);
            merge(array, start, middle, end);
        }
    }
    public void printArray(int[]array){
        for(int a: array){
            System.out.print(a + " ");
        }
    }
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
                //System.out.println(randomInsert);
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
        // Testing the Algorithms
        // int[] insertionTest = {10,8,9,7,2,3,6,11,63,104};
        // insertionTest = insertionSort(insertionTest);
        // for(int i:insertionTest){
        //     System.out.println(i);
        // }
        //Lab1 l1 = new Lab1();
        //int[] mergeTest = {100,912,81,7,67643,523211,431,32,29097,13};
        //l1.hybridSort(mergeTest,10);
        //l1.printArray(mergeTest);
        
        //Actual Lab 1
        int[][] inputData = generateInputData();
        //int[][] compArr = new int[6][5];
        //Scanner sc = new Scanner(System.in);

        //Run Hybrid Sort on Different Sized Datasets with S=10
        // System.out.println("Hybrid Sort wth Fixed S=10");
        // int index = 0;
        // for(int i = 0; i<inputData.length; i++){
        //     resetCounter();
        //     long startTime = System.nanoTime();
        //     hybridSort(inputData[i], 10);
        //     long stopTime = System.nanoTime();
        //     double elapsedTime = (stopTime-startTime)/1_000_000_000;
        //     System.out.printf("Array Size %d | Threshold Size %d | Comparisons %d | Time Taken %d \n",inputData[i].length, 10, comparisions,stopTime-startTime);
        //     //System.out.printf("Elapsed Time : %f seconds\n", elapsedTime);
        //     //System.out.printf("Comparisions: %d\n", comparisions);
        //     compArr[0][index] = comparisions;
        // }

        //Run Hybrid Sort on Dataset with N=1,000,000 and various values of S
        System.out.println("Hybrid Sort wth Varied S");
        int sValues[] = IntStream.range(1,101).toArray();
        for(int i = 0; i<inputData.length; i++){
            int j = 0;
            resetCounter();
            for(int threshold:sValues){
                resetCounter();
                long startTime = System.nanoTime();
                hybridSort(inputData[i], threshold);
                long stopTime = System.nanoTime();
                //double elapsedTime = (stopTime-startTime)/1_000_000_000;
                System.out.printf("Array Size %d | Threshold Size %d | Comparisons %d | Time Taken %d \n",inputData[i].length, threshold, comparisions,stopTime-startTime);
                //System.out.printf("Elapsed Time : %f seconds\n", elapsedTime);
                //System.out.printf("Comparisions: %d\n", comparisions);
                j++;
            }
        }
    }
}