
public class GraphEdge<E> {
  private GraphNode<E> from, to;
  public GraphEdge(GraphNode<E> from, GraphNode<E> to)
  { this.from = from; this.to = to; }
  public GraphNode<E> from() { return from; }
  public GraphNode<E> to()   { return to; }
  public String toString() {
    return "Edge: " + from().element() + " -> " + to().element();
  }
}
