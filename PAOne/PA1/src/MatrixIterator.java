import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.Integer;

public class MatrixIterator<E extends Number> implements Iterator<MatrixEntry<E>> {
    private E[][] matrix; // matrix to iterate over
    private int row, col; // position in matrix
    MatrixIterator(E[][] matrix)
    {
        this.matrix = matrix;
        row = 0;
        col = 0;
    }


    /*
        return boolean
            if position in matrix exists for the row and column
     */
    public boolean hasNext() {
        return row < matrix.length && col < matrix[row].length;
    }

    public MatrixEntry<E> next() {
        // your code here
        if (! hasNext())
            throw(new NoSuchElementException("MatrixIterator: no more elements in matrix"));
        // change code on lines 35-38 with your own code
        MatrixEntry<E> entry = new MatrixEntry<>(row, col, matrix[row][col]);
        if(col == matrix[row].length -1){
            row ++;
            col =0;
        }
        else{
            col ++;
        }
        return entry;


    }

    /*
        main test case
     */
    public static void main(String[] args) {
        int[][] mat = { {3,4,5},
                        {7,0,-2},
                        {5,2,8}
                      };
//        int[][] mat = { {1,2},
//                        {2,3},
//                        {3,4},
//                        {4,4},
//                        };



        Integer[][] mat2 = new Integer[mat.length][mat[0].length];

        /*
            Using a nested for loop
                Convert int mat to an Interger[][]
                save into mat2
         */
            // your code here
        for (int i=0; i < mat.length; i++){
            for (int j=0; j<mat[0].length; j++){
                mat2[i][j] = mat[i][j];
            }
        }

        Iterator<MatrixEntry<Integer>> iter = new MatrixIterator<>(mat2);


        /*
            Cycle through iterator iter using a while loop:
                use methods:
                                hasNext
                                Next
         */

            // your code here
        while (iter.hasNext()){
            System.out.println(iter.next().toString());
        }

    }
}