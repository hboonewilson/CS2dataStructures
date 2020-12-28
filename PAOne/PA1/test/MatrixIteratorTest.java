import org.junit.Test;
import java.util.NoSuchElementException;
import java.util.Iterator;
import static org.junit.Assert.*;

public class MatrixIteratorTest
{
    private Integer[][] matint = {  {3,4,5},
                                    {7,0,-2},
                                    {5,2,8}
                                 };

    private Double[][] matdouble = {{3.0,4.0,5.0},
                                    {7.0,0.0,-2.0},
                                    {5.0,2.0,8.0}
                                };

    @Test
    public void testiteratorIntegerhasNext()
    {
        Iterator<MatrixEntry<Integer>> iter = new MatrixIterator<>(matint);
        Integer[] values = {3,4,5,7,0,-2,5,2,8};

        int index = 0;

        while(iter.hasNext())
        {
            assertEquals(values[index], iter.next().value());

            index = index + 1;
        }

    }


    @Test
    public void testiteratorDoublehasNext()
    {
        Iterator<MatrixEntry<Double>> iter = new MatrixIterator<>(matdouble);
        Double[] values = {3.0,4.0,5.0,7.0,0.0,-2.0,5.0,2.0,8.0};

        int index = 0;

        while(iter.hasNext())
        {
            assertEquals(values[index], iter.next().value());
            index = index + 1;
        }

    }


    @Test
    public void testiteratorIntNextcorrect()
    {
        Iterator<MatrixEntry<Integer>> iter = new MatrixIterator<>(matint);
        Integer[] values = {3,4,5,7,0,-2,5,2,8};

        assertEquals(values[0], iter.next().value());
    }


    @Test(expected = NoSuchElementException.class)
    public void testiteratorIntNextfail()
    {
        Integer[][] temp = {};
        Iterator<MatrixEntry<Integer>> iter = new MatrixIterator<>(temp);

        iter.next();

    }



}
