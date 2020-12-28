import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class MindtermTest {

    @Test
    public void nit_size() {
        BinSearchTree<String> bst = new BinSearchTree<String>();
        // assert statements
        assertTrue(bst.isEmpty());
        assertTrue(bst.root() == null);
    }

    @Test
    public void test_add_correct_order(){
        BinSearchTree<String> bst = new BinSearchTree<String>();
        String[] words = { "g", "c", "k", "u", "a", "w", "e", "b" };
        for ( String s : words ) {
            System.out.println("Adding word: "+s);
            bst.add(s);
        }
        Arrays.sort(words);
        int index = 0;
        for(String s : bst)
        {
            assertEquals(s, words[index]);
            index+=1;
        }
    }



    @Test
    public void test_remove_correct_order(){
        BinSearchTree<String> bst = new BinSearchTree<String>();

        String[] words = {  "a", "g", "k", "w", "e", "b","u" , "c" };
        for ( String s : words ) {
            System.out.println("Adding word: "+s);
            bst.add(s);
        }

        Arrays.sort(words);

        int index = 0;
        for(String s : bst)
        {
            assertEquals(s, words[index]);
            index++;
        }

        bst.remove("c");
        String[] words2 = {  "a", "g", "k", "w", "e", "b","u"  };
        Arrays.sort(words2);

        index = 0;
        for(String s : bst)
        {
            assertEquals(s, words2[index]);
            index++;
        }


    }

    @Test
    public void test_add_incremement_size(){
        BinSearchTree<String> bst = new BinSearchTree<String>();
        String[] words = { "g", "c", "k", "u", "a", "w", "e", "b" };
        for ( String s : words ) {
            System.out.println("Adding word: "+s);
            bst.add(s);
        }
        // check if it is not empty
        assertTrue(!bst.isEmpty());

        // check correct size
        int index = 0;
        for(String s : bst)
        {
            index+=1;
        }

        assertEquals(index, words.length);
    }
    @Test
    public void test_remove(){
        BinSearchTree<String> bst = new BinSearchTree<String>();
        String[] words = { "g", "c", "k", "u", "a", "w", "e", "b" };
        for ( String s : words ) {
            System.out.println("Adding word: "+s);
            bst.add(s);
        }

        bst.remove("a");
        // check correct size
        int index = 0;
        for(String s : bst)
        {
            assertTrue((s != "a"));
        }
    }
    @Test
    public void test_remove_head(){
        BinSearchTree<String> bst = new BinSearchTree<String>();
        String[] words = {"c", "u", "k", "a", "w", "l", "b","g"};
        for ( String s : words ) {
           bst.add(s);
        }
//        System.out.println(bst);

        boolean check = bst.remove("g");
//        System.out.println(check);
//        System.out.println(bst);
        // check correct size
        for(String s : bst)
        {
            assertTrue(s!="g");
        }

    }

    @Test
    public void remove_empty() {
        BinSearchTree<String> bst = new BinSearchTree<String>();

        bst.add("A");
        assertTrue(bst.root().element() == "A");

        bst.remove("A");
        assertTrue(bst.root() == null);

    }

}