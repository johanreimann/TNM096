import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        int[] start = { 2, 1, 3, 4, 5, 6, 7, 8, 0 };//{8, 6, 7, 2, 5, 4, 3, 0, 1};//{ 1, 2, 3, 4, 5, 6, 7, 8, 0 };//randomBoard();
//        while (!isSolvable(start)) {
//            start = randomBoard();
//        }

        long startTime = System.currentTimeMillis();
        AStarSearch.search(start);
        long endTime = System.currentTimeMillis();

        long duration = (endTime - startTime);

        System.out.print("Runtime for the application: " + duration + " milliseconds.");

    }

    public static int[] randomBoard() {
        int[] rv = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i < 9; i++) {
            rv[i] = list.get(i);
        }

        return rv;
    }

    // A utility function to count inversions in given array 'arr[]'
    static int getInvCount(int arr[])
    {
        int inv_count = 0;
        for (int i = 0; i < 9 - 1; i++)
            for (int j = i + 1; j < 9; j++)
                if (arr[j] > 0 && arr[i] > 0 && arr[i] > arr[j])
                    inv_count++;
        return inv_count;
    }

    // This function returns true if given 8 puzzle is solvable.
    static boolean isSolvable(int[] puzzle)
    {
        // Count inversions in given 8 puzzle
        int invCount = getInvCount(puzzle);

        // return true if inversion count is even.
        return (invCount % 2 == 0);
    }
}