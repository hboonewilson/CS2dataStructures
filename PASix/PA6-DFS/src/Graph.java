import java.io.File;
import java.util.*;
import java.lang.Cloneable;

public class Graph<E> {
  private HashMap<E,GraphNode<E>> nodeMap;

  public Graph() {
    nodeMap = new HashMap<E,GraphNode<E>>();
  }

  public GraphNode<E> getNode(E elt) throws NoSuchElementException {
    if ( nodeMap.containsKey(elt) )
      return nodeMap.get(elt);
    else
      throw(new NoSuchElementException("getNode: "+elt));
  }

  public GraphNode<E> addNode(E elt) {
    if ( ! nodeMap.containsKey(elt) )
      return nodeMap.put(elt,new GraphNode<E>(elt));
    else  // throw exception??
      return nodeMap.get(elt); // not actually adding a node
  }

  public GraphEdge<E> addEdge(E eltfrom, E eltto)
          throws NoSuchElementException {
    if ( nodeMap.containsKey(eltfrom) && nodeMap.containsKey(eltto) ) {
      GraphNode<E> nfrom = getNode(eltfrom);
      GraphNode<E> nto   = getNode(eltto);
      nfrom.addEdge(nto);
      return new GraphEdge<E>(nfrom,nto);
    }
    else
      return null;
  }

  public void removeEdge(E eltfrom, E eltto) {
    if ( nodeMap.containsKey(eltfrom) && nodeMap.containsKey(eltto) ) {
      GraphNode<E> nfrom = getNode(eltfrom);
      GraphNode<E> nto   = getNode(eltto);
      if ( nfrom.hasNeighbor(nto) ) {
        nfrom.removeEdge(nto);
      }
    }
  }

  public void makeUndirected() {
    Iterator<GraphEdge<E>> edgeIter = edges();
    while ( edgeIter.hasNext() ) {
      GraphEdge<E> e = edgeIter.next();
      GraphNode<E> from = e.from(), to = e.to();
      if ( ! to.hasNeighbor(from) )
        to.addEdge(from);
    }
  }

  public boolean isUndirected() {
    Iterator<GraphEdge<E>> edgeIter = edges();
    while ( edgeIter.hasNext() ) {
      GraphEdge<E> e = edgeIter.next();
      GraphNode<E> from = e.from(), to = e.to();
      if ( ! to.hasNeighbor(from) )
        return false;
    }
    return true;
  }

  /*
   * @TODO: complete the dfs(start) method
   */
  public List<GraphNode<E>> dfs(E start) {
    HashSet<GraphNode<E>> visited = new HashSet<GraphNode<E>>();
    GraphNode<E> startNode = getNode(start);
    // @TODO: code here
    return dfsRec(startNode,visited);
  }

  /*
   * @TODO: complete the dfsRec(startNode,visited) method
   */
  private List<GraphNode<E>> dfsRec(GraphNode<E> startNode,
                                    HashSet<GraphNode<E>> visited) {
    Iterator<GraphNode<E>> nbrIter = startNode.neighbors(); // @TODO: code here
    ArrayList<GraphNode<E>> list = new ArrayList<>(); // @TODO: code here
    /* @TODO: code here*/
    while ( nbrIter.hasNext() ) {
      /* @TODO: code here */
     GraphNode<E> n = nbrIter.next();
      if ( ! visited.contains(n) ) {
        // add n to the list of visited nodes
        visited.add(n);
        list = List.(n, visited);
        //use list.addAll to add the nodes visited in the recursive call
        // @TODO: code here (2 lines);
      }
    }
    return list;
  }

  public HashMap<GraphNode<E>,GraphNode<E>> dfsPrev(E start) {
    GraphNode<E> startNode = getNode(start);
    Iterator<GraphNode<E>> nbrIter = startNode.neighbors();
    // At end: prev.get(n) is predecessor in DFS
    HashMap<GraphNode<E>,GraphNode<E>> prev =
            new HashMap<GraphNode<E>,GraphNode<E>>();
    prev.put(startNode,startNode); // avoids using null for value
    dfsPrevRec(startNode, prev);
    return prev;
  }

  private void dfsPrevRec(GraphNode<E> start,
                          HashMap<GraphNode<E>,GraphNode<E>> prev) {
    Iterator<GraphNode<E>> nbrIter = start.neighbors();
    while ( nbrIter.hasNext() ) {
      GraphNode<E> n = nbrIter.next();
      if ( ! prev.containsKey(n) ) {
        prev.put(n,start); // start -> n
        dfsPrevRec(n,prev);
      }
    }
  }


  public Collection<GraphNode<E>> nodes() { return nodeMap.values(); }

  public Iterator<GraphEdge<E>> edges() {
    Iterator<GraphEdge<E>> it = new Iterator<GraphEdge<E>>() {
      private Iterator<GraphNode<E>> fromIter = null;
      private Iterator<GraphNode<E>> toIter = null;
      private GraphNode<E> from = null, to = null;
      private int state = 0; // start state, end state is 4
      private final int open = 1, closed = 2;
      private int lock = open;

      private void step() { // sets from and to fields
        boolean breakLoop = false;
        while ( ! breakLoop ) {
          switch ( state ) {
            case 0:
              fromIter = nodeMap.values().iterator();
              if ( fromIter.hasNext() ) {
                from = fromIter.next();
                toIter = from.neighbors();
                state = 1;
              } else {
                state = 4;
                breakLoop = true;
              }
              break;
            case 1:
              if ( toIter.hasNext() ) {
                to = toIter.next();
                state = 2;
                breakLoop = true;
              } else {
                state = 3;
              }
              break;
            case 2:
              if ( toIter.hasNext() ) {
                to = toIter.next();
                state = 2;
                breakLoop = true;
              } else {
                state = 3;
              }
              break;
            case 3:
              if ( fromIter.hasNext() ) {
                from = fromIter.next();
                toIter = from.neighbors();
                state = 1;
              } else {
                state = 4;
                breakLoop = true;
              }
              break;
            default: // include case 4: (== end state)
              breakLoop = true;
              break;
          }
        }
      }

      @Override
      public boolean hasNext() {
        if ( lock == open )
          step();
        lock = closed;
        return state != 4;
      }

      @Override
      public GraphEdge<E> next() {
        if ( lock == open )
          step();
        lock = open;
        return new GraphEdge<E>(from,to);
      }
    };
    return it;
  }

  public String toString() {
    String s = "Graph: \n";
    for ( GraphNode<E> n : nodes() )
      s += n.toString()+"\n";

    Iterator<GraphEdge<E>> iter = edges();
    while ( iter.hasNext() ) {
      GraphEdge<E> e = iter.next();
      s += e.toString()+"\n";
    }
    s += "endGraph\n";
    return s;
  }

  public static void main(String[] args) {


    Graph<String> g2 = null;
    String fname = "resources\\GraphTest-String.txt";
    //String fname = "resources/GraphTest-String.txt"; //Linux/Mac users may need to use this fname instead
    Scanner scnr = null;
    try {
      scnr = new Scanner(new File(fname));
    }
    catch (Exception e) {
      System.out.println("Cannot open file "+fname);
      System.exit(1);
    }
    ScanGraph<String> sg2 = new ScanGraph<String>(new ScanString(scnr));
    sg2.skipComment();
    if ( sg2.hasNext() )
      g2 = sg2.next();

    System.out.println();
    System.out.println("Is g2 undirected? " + g2.isUndirected());

    System.out.println(g2);
    System.out.println();
    System.out.println("Is g2 undirected? " + g2.isUndirected());
    System.out.println();
    g2.makeUndirected();
    System.out.println(g2);
    System.out.println();
    System.out.println("Is g2 undirected now? " + g2.isUndirected());
    System.out.println();
    System.out.println("DFS list = "+ g2.dfs("Legolas"));
    System.out.println();
    System.out.println("DFS previous map = " + g2.dfsPrev("Legolas"));
    System.out.println();
  }
}
