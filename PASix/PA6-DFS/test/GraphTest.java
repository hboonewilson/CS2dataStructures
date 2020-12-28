import org.junit.Test;

import java.io.File;
import java.util.*;

import static org.junit.Assert.*;

public class GraphTest {



    /* testDFS():
        tests that the Graph.dfs(start="Legolas") method works
        on the undirected graph given in code-example2.pdf
        (loaded from GraphTest-String.txt)
     */
    @Test
    public void testDFS()  {
        try {
            Graph<String> g =  loadGraphFromFile("\\","GraphTest-String.txt");
            g.makeUndirected();
            List<GraphNode<String>> dfs = g.dfs("Legolas");
            List<GraphNode<String>> expectedNodes = new ArrayList<>();
            String[] nodeOrder = {"Legolas","Gimli","Boromir","Aragorn","Gandalf","Frodo","Samwise"};
            for (String x : nodeOrder)
                expectedNodes.add(new GraphNode<>(x));
            int i=0;
            for (GraphNode<String> node : dfs)
                assertEquals(node, new GraphNode<>(nodeOrder[i++]));
            assertEquals(i,7);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /* testDFSDirected():
        tests that the Graph.dfs(start="Samwise") method works
        on the directed graph given in code-example2.pdf
        (loaded from GraphTest-String.txt)
     */
    @Test
    public void testDFSDirected()  {
        try {
            Graph<String> g =  loadGraphFromFile("\\","GraphTest-String.txt");
           // g.makeUndirected();
            List<GraphNode<String>> dfs = g.dfs("Samwise");
            List<GraphNode<String>> expectedNodes = new ArrayList<>();
            String[] nodeOrder = {"Samwise","Frodo","Gandalf","Legolas","Gimli"};
            for (String x : nodeOrder)
                expectedNodes.add(new GraphNode<>(x));
            int i=0;
            for (GraphNode<String> node : dfs)
                assertEquals(node, new GraphNode<>(nodeOrder[i++]));
            assertEquals(i,5);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /* testDFSInteger():
        tests that the Graph.dfs(start=2) method works
        on the undirected graph given in code-example1.pdf
        (loaded from GraphTest-Integer.txt)
     */
    @Test
    public void testDFSInteger()  {
        try {
            Graph<Integer> g =  loadIntegerGraphFromFile("\\","GraphTest-Integer.txt");
            System.out.println(g);
            g.makeUndirected();
            List<GraphNode<Integer>> dfs = g.dfs(2);
            System.out.println(dfs);
            List<GraphNode<Integer>> expectedNodes = new ArrayList<>();
            int[] nodeOrder = {2,4,13,5,6,8,11,12,1,7};
            for (int x : nodeOrder)
                expectedNodes.add(new GraphNode<>(x));
            int i=0;
            for (GraphNode<Integer> node : dfs)
                assertEquals(node, new GraphNode<>(nodeOrder[i++]));
            assertEquals(i,nodeOrder.length);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /* testDFSContainsAllNodes():
        tests the Graph.dfs(start="Samwise") method
        on the undirected graph given in code-example2.pdf
        (loaded from GraphTest-String.txt)

        checks the property that the DFS
        visits all nodes in the graph
        (no particular order is checked in this test)
     */
    @Test
    public void testDFSContainsAllNodes()  {
        try {
            Graph<String> g =  loadGraphFromFile("\\","GraphTest-String.txt");
            g.makeUndirected();
            List<GraphNode<String>> dfs = g.dfs("Samwise");
            List<GraphNode<String>> expectedNodes = new ArrayList<>();
            String[] nodeOrder = {"Legolas","Frodo","Boromir","Aragorn","Gandalf","Gimli","Samwise"};
            for (String x : nodeOrder)
                expectedNodes.add(new GraphNode<>(x));
            int i=0;
            for (GraphNode<String> node : dfs) {
                assertTrue(expectedNodes.contains(node));
                expectedNodes.remove(node);
                i++;
            }
            assertEquals(i,nodeOrder.length);
            assertTrue(expectedNodes.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }


    /* testDFSContainsDirectedNodes():
        tests the Graph.dfs(start="Samwise") method
        on the directed graph given in code-example2.pdf
        (loaded from GraphTest-String.txt)

        checks the property that the DFS
        visits all REACHABLE nodes in the graph
        when it is constrained by directed edges
        (no particular order is checked in this test)
     */
    @Test
    public void testDFSContainsDirectedNodes()  {
        try {
            Graph<String> g =  loadGraphFromFile("\\","GraphTest-String.txt");
            //g.makeUndirected();
            List<GraphNode<String>> dfs = g.dfs("Samwise");
            List<GraphNode<String>> expectedNodes = new ArrayList<>();
            String[] nodeOrder = {"Samwise","Frodo","Gandalf","Legolas","Gimli"};
            for (String x : nodeOrder)
                expectedNodes.add(new GraphNode<>(x));
            int i=0;
            for (GraphNode<String> node : dfs) {
                assertTrue(expectedNodes.contains(node));
                expectedNodes.remove(node);
                i++;
            }
            assertEquals(i,nodeOrder.length);
            assertTrue(expectedNodes.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }




    /* loadGraphFromFile(dir,fname):
        auxiliary helper method to load a graph from a text file

        since Windows uses '\' for specifying directory structure
        and Linux/Mac uses '/',
        this method first tries loading from directory: "resources\fname"
        and if it fails with file not found,
        will try again using directory: "resources/fname"
        so that the code will not need to be changed depending on the OS used
     */
    private Graph loadGraphFromFile(String dir, String fname) {
        Graph<String> g = new Graph<>();
        String fdir = "resources"+dir+""+fname; //"resources/GraphTest-Integer.txt";
        Scanner scnr = null;
        try {
            scnr = new Scanner(new File(fdir));
        }
        catch (Exception e) {
            System.out.println("Cannot open file "+fdir);
            if (dir.equals("/"))
                System.exit(1);
            return loadGraphFromFile("/",fname);
        }
        ScanGraph<String> sg = new ScanGraph<>(new ScanString(scnr));
        sg.skipComment();
        if ( sg.hasNext() )
            return sg.next();
        return null;
    }

    private Graph loadIntegerGraphFromFile(String dir, String fname) {
        Graph<Integer> g = new Graph<>();
        String fdir = "resources"+dir+""+fname; //"resources/GraphTest-Integer.txt";
        Scanner scnr = null;
        try {
            scnr = new Scanner(new File(fdir));
        }
        catch (Exception e) {
            System.out.println("Cannot open file "+fdir);
            if (dir.equals("/"))
                System.exit(1);
            return loadIntegerGraphFromFile("/",fname);
        }
        ScanGraph<Integer> sg = new ScanGraph<>(new ScanInteger(scnr));
        sg.skipComment();
        if ( sg.hasNext() )
            return sg.next();
        return null;
    }


}
