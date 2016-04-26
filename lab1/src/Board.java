import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by johan on 19/04/16.
 */
public class Board {
    private int outOfPlace = 0;
    private int[] boardSetup;
    private final int[] goalSetup = { 1, 2, 3, 4, 5, 6, 7, 8, 0 };


    public Board(int[] currentSetup) {
        boardSetup = currentSetup;
        setOutOfPlace();
    }

    public void setOutOfPlace() {
        for (int i = 0; i < boardSetup.length; i++) {
            if(boardSetup[i] != goalSetup[i]) {
                outOfPlace++;
            }
        }
    }

    public ArrayList<Board> makeMoves() {
        ArrayList<Board> moves = new ArrayList<Board>();

        int zeroPos = getZeroPos();

        // Move free slot up
        if (zeroPos > 2) {
            moveAndSave(zeroPos, zeroPos - 3, moves);
        }

        // Move free slot down
        if (zeroPos < 6) {
            moveAndSave(zeroPos, zeroPos + 3, moves);
        }

        // Move free slot left
        if (zeroPos != 0 && zeroPos != 3 && zeroPos != 6) {
            moveAndSave(zeroPos, zeroPos - 1, moves);
        }

        // Move free slot right
        if (zeroPos != 2 && zeroPos != 5 && zeroPos != 8) {
            moveAndSave(zeroPos, zeroPos + 1, moves);
        }

        return moves;
    }

    private void moveAndSave(int freeSlot, int tile, ArrayList<Board> al) {
        int[] boardAfterMove = copyBoard(boardSetup);
        int temp = boardAfterMove[tile];
        boardAfterMove[tile] = boardSetup[freeSlot];
        boardAfterMove[freeSlot] = temp;
        al.add((new Board(boardAfterMove)));
    }

    private int[] copyBoard(int[] board) {
        int[] copy = new int[9];
        for (int i = 0; i < 9; i++) {
            copy[i] = board[i];
        }
        return copy;
    }

    public void printBoard() {
        System.out.print("\n" + boardSetup[0] + " " + boardSetup[1] + " " + boardSetup[2] + "\n");
        System.out.print(boardSetup[3] + " " + boardSetup[4] + " " + boardSetup[5] + "\n");
        System.out.print(boardSetup[6] + " " + boardSetup[7] + " " + boardSetup[8] + "\n");
    }

    public int getOutOfPlace() {
        return outOfPlace;
    }

    public int getZeroPos() {
        int zeroPos = -1;
        for (int i = 0; i < boardSetup.length; i++) {
            if (boardSetup[i] == 0)
                zeroPos = i;
        }
        return zeroPos;
    }

    public boolean finished() {
        if (Arrays.equals(boardSetup, goalSetup)) {
            return true;
        }
        return false;
    }

    public int[] getBoardSetup() {
        return boardSetup;
    }

    public int findCost() {
        return setManDist();
    }

    public int setManDist() {

        int index = -1;
        int manDist = 0;

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                index++;
                int val = (boardSetup[index] - 1);

                if (val != -1) {
                    int h = val % 3;
                    int v = val / 3;

                    manDist += Math.abs(v - y) + Math.abs(h - x);
                }
            }
        }

        return manDist;
    }

    public String boardToString() {
        return Arrays.toString(boardSetup);
    }
}