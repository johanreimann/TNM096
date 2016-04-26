import java.util.*;

/**
 * Created by johan on 19/04/16.
 */
public class AStarSearch {

    static public PriorityQueue<BoardNode> q = new PriorityQueue<BoardNode>(11, new Comparator<BoardNode>() {
        @Override
        public int compare(BoardNode b, BoardNode b1) {
            return b.getTotCost() - b1.getTotCost();
        }
    });

    public static void search(int[] startBoard) {
        long startTime = System.currentTimeMillis();

        BoardNode root = new BoardNode(startBoard);
        System.out.print("Starting board:");
        root.getBoard().printBoard();
        System.out.print("-----\n");


        HashSet<String> usedBoards = new HashSet<String>();

        usedBoards.add(root.getBoard().getBoardSetup().toString());
        q.add(root);

        int searchIterations = 0;

        while(!q.isEmpty()) {
            BoardNode tempNode = q.poll();
//            System.out.print("Node cost: " + tempNode.getTotCost() + "\n");
//            tempNode.getBoard().printBoard();
            if(!tempNode.getBoard().finished()) {
                ArrayList<Board> tempMoves = tempNode.getBoard().makeMoves();
                ArrayList<BoardNode> nodeMoves = new ArrayList<BoardNode>();

                for(int i = 0; i < tempMoves.size(); i++) {
                    // Create the new node with parent and costs
                    BoardNode checkedNode = new BoardNode(tempNode, tempMoves.get(i),
                            tempNode.getTotCost(), tempMoves.get(i).findCost());
                    // Only add if the move doesn't hasn't been used before
//                    System.out.print("Used board? " + usedBoards.contains(checkedNode.getBoard().boardToString()));
                    if (!usedBoards.contains(checkedNode.getBoard().boardToString())) {
                        nodeMoves.add(checkedNode);
                    }
                }

                if(nodeMoves.size() == 0)
                    continue;

                for(int i = 0; i < nodeMoves.size(); i++) {
                        q.add(nodeMoves.get(i));
                        usedBoards.add(nodeMoves.get(i).getBoard().boardToString());
                }

                searchIterations++;
            }

            else {
                Stack<BoardNode> solutionPath  = new Stack<BoardNode>();
                solutionPath.push(tempNode);
                tempNode = tempNode.getParent();

                while(tempNode.getParent() != null) {
                    solutionPath.push(tempNode);
                    tempNode = tempNode.getParent();
                }
                solutionPath.push(tempNode);

                int nrOfMoves = solutionPath.size();

                for (int i = 0; i < nrOfMoves; i++) {
                    tempNode = solutionPath.pop();
                    tempNode.getBoard().printBoard();
                }
                System.out.println("The cost was: " + tempNode.getTotCost());
                System.out.println("The number of nodes examined: " + searchIterations);
                System.out.println("The number of moves: " + (nrOfMoves - 1)); // -1 to remove starting state

                long endTime = System.currentTimeMillis();

                long duration = (endTime - startTime);

                System.out.print("Runtime for the application: " + duration + " milliseconds.");
                System.exit(1);
            }
        }
        System.out.print("No solution found.\n");
    }
}