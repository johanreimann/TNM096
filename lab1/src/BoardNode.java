import java.util.Comparator;

/**
 * Created by johan on 19/04/16.
 */
public class BoardNode  {//implements Comparator<BoardNode>, Comparable<BoardNode>
    private Board board;
    private int totCost;
    private int gCost;
    private int hCost;
    private BoardNode parent;

    public BoardNode(int[] boardSetup) {
        board = new Board(boardSetup);
        totCost = 0;
        gCost = 0;
        hCost = 0;
        parent = null;
    }

    public BoardNode(Board b) {
        board = b;
        totCost = 0;
        gCost = 0;
        hCost = 0;
        parent = null;
    }

    public BoardNode(BoardNode prev, Board b, int g, int h) {
        parent = prev;
        board = b;
        gCost = g;
        hCost = h;
        totCost = g + h;
    }

    public int getTotCost() {
        return totCost;
    }

    public int getgCost() {
        return gCost;
    }

    public int gethCost() {
        return hCost;
    }

    public Board getBoard() {
        return board;
    }

    public BoardNode getParent() {
        return parent;
    }

//    public int compare(BoardNode b1, BoardNode b2) {
//        return b2.totCost - b1.totCost;
//    }
//
//    public int compareTo(BoardNode b) {
//        return b.totCost - this.totCost;
//    }
}
