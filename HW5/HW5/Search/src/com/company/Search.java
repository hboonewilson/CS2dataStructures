package com.company;
import java.util.Arrays;


public class Search {

    public static <E> int linearSearch(E[] array, E item) {
        // code here
        int retVal = -1;
        for ( int i = 0; i < array.length; ++i){
            if (array[i].equals(item)){
                retVal = i;
                return retVal;
            }
        }
        return retVal;
    }


    public static <E extends Comparable<E>>
    int binarySearch(E[] array, E item) {
        return binarySearch(array,item,0,array.length-1);

    }

    public static <E extends Comparable<E>>

    int binarySearch(E[] array, E item, int lo, int hi) {
        // code here
        int mid = (hi + lo) / 2;
        E midVal = array[mid];
        //ARRAY MID.COMPARE TO THIS RETURNS -1 IF THE ITEM YOU'RE SEARCHING FOR IS LARGER THAN MID
        // AND 1 IF MID IS LARGER THAN ITEM
        //if mid < item
        if (hi >= lo) {
            if (midVal.compareTo(item) < 0) {
                mid += 1;
               return binarySearch(array, item, mid, hi);
            }
            //if mid > item
            else if (midVal.compareTo(item) > 0) {
                mid -= 1;
                return binarySearch(array, item, lo, mid);
            }
            //if mid == item
            else if (midVal.equals(item)) {
                return mid;
            }


        }
        return -1;
    }

    public static void main(String[] args) {
        // code here

        int[] numArrays = {10, 30, 100, 300, 1_000, 3_000, 10_000,
                30_000, 100_000, 300_000, 1_000_000, 3_000_000};

        double[] linTimeArrays = new double[12];
        double[] binTimeArrays = new double[12];
        for (int i = 0; i < numArrays.length; ++i){ // iterate through number arrays

            Integer testArray[] = new Integer[numArrays[i]];   // create  testArray
            for (int j = 0; j < numArrays[i]; j++){ // fill testArray
                testArray[j] = j;
            }

            long numTrials = 5_000; // set numTrials to 5_000 for a 5_000 trial avg.
            long binSum = 0;  //  set binSum and linSum
            long linSum = 0;
            for ( int j = 1; j <= numTrials; j++){    // do the tests 4 times each adding to each search sum
                long time1 = System.nanoTime();
                int linFound = linearSearch(testArray, numArrays[i] - 1);
                long time2 = System.nanoTime();
                linSum += time2 -time1;
                time1 = System.nanoTime();
                int binFound = binarySearch(testArray, numArrays[i] - 1);
                time2 = System.nanoTime();
                binSum += time2 - time1;

            }
           double binAvg =(binSum / numTrials) * 1E-9;
            double linAvg = (linSum / numTrials) * 1E-9;
            linTimeArrays[i] = linAvg;
            binTimeArrays[i] = binAvg;

        }

        System.out.println("Binary Times: " + Arrays.toString(binTimeArrays));
        System.out.println("Linear Times: " + Arrays.toString(linTimeArrays));

        }





    }

