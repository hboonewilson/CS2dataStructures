package com.company;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.lang.Object;

public class Main {
    private static Object Comparator;
    private static Object Integer;

    private static class Node implements Comparator<Node>{
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.data > o2.data) {return 1;}
            else if (o1.data < o2.data) {return -1;}
            return 0;
        }

        private Integer data;
        private Node next;
        private Node (Integer data) { data = this.data; }
        public  int returnData(){return data;}


    }
    public static void main(String[] args) {
	// write your code here

        PriorityQueue<Node> doubleQueue = new PriorityQueue<Node>(20, );

    }
}
