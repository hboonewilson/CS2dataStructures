import org.junit.Test;
import java.util.NoSuchElementException;
import java.util.Iterator;
import static org.junit.Assert.*;

public class CircularLinkedListTest
{
    private String[] cars = { "prius", "rav4", "subaru", "crv", "pilot"};

    @Test
    public void testinit()
    {
        CircularLinkedList<String> temp = new CircularLinkedList<String>();
        assertEquals(0, temp.size());
        assertEquals(null, temp.first());
    }

    @Test
    public void testsizeempty()
    {
        CircularLinkedList<String> temp = new CircularLinkedList<String>();
        assertEquals(true, temp.isEmpty());
    }

    @Test
    public void testsizefull()
    {
        CircularLinkedList<String> temp = new CircularLinkedList<String>();
        for (String i: cars)
        {
            temp.addLast(i);
        }

        assertEquals(cars.length, temp.size());

    }
    @Test
    public void testlastempty()
    {
        CircularLinkedList<String> temp = new CircularLinkedList<String>();

        assertEquals(null, temp.first());
        assertEquals(null, temp.last());


    }
    @Test
    public void testlastfull()
    {
        CircularLinkedList<String> temp = new CircularLinkedList<String>();
        for (String i: cars)
        {
            temp.addLast(i);
        }

        assertEquals(cars[0], temp.first());
        assertEquals(cars[cars.length-1], temp.last());


    }

    @Test
    public void testrotatefull()
    {
        CircularLinkedList<String> temp = new CircularLinkedList<String>();
        for (String i: cars)
        {
            temp.addLast(i);
        }
        temp.rotate();
        assertEquals(cars[1],temp.first());
    }

    @Test
    public void testaddFirst()
    {
        CircularLinkedList<String> temp = new CircularLinkedList<String>();
        temp.addFirst("new first");
        assertEquals(1, temp.size());
        for (String i: cars)
        {
            temp.addLast(i);
        }
        assertEquals("new first", temp.first());
        temp.addFirst("new new first");
        assertEquals("new new first", temp.first());
        assertEquals(cars.length+2, temp.size());
    }
    @Test
    public void testaddLast()
    {
        CircularLinkedList<String> temp = new CircularLinkedList<String>();
        temp.addLast("new last");
        assertEquals(1, temp.size());
        for (String i: cars)
        {
            temp.addLast(i);
        }
        assertEquals(cars[cars.length-1], temp.last());
        temp.addLast("new new last");
        assertEquals("new new last", temp.last());
        assertEquals(cars.length+2, temp.size());

    }


    @Test
    public void testremoveFirstempty()
    {
        CircularLinkedList<String> temp = new CircularLinkedList<String>();
        assertEquals(null, temp.removeFirst());
    }
    @Test
    public void testremoveFirstfull()
    {
        CircularLinkedList<String> temp = new CircularLinkedList<String>();
        for (String i: cars)
        {
            temp.addLast(i);
        }
        assertEquals(cars[0], temp.removeFirst());
        assertEquals(cars[1], temp.first());
    }
}
