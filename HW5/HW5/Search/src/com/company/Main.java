package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        long time1 = System.nanoTime();
        long time2 = System.nanoTime();
        long time3 = System.nanoTime();
        long sum = time1 + time2 + time3;
        double inSecs = (sum / 3);
        System.out.println(inSecs);
    }
}
