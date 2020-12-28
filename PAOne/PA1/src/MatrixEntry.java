public class MatrixEntry<E extends Number> {
    private int row, col;
    private E value;
    public MatrixEntry(int r, int c, E val) {
        row = r; col = c; value = val;
    }
    public int row() { return row; }
    public int column() { return col; }
    public E value() { return value; }
    public String toString() {
        return "Entry at ("+row+","+col+") = "+value;
    }

}
