import java.io.*;
import java.util.*;

class Main {

    static StringBuilder index = new StringBuilder();
    public static void main(String[] args) throws Exception {
        

        InputStreamReader console = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(console);
        int numTestCases = Integer.parseInt(reader.readLine());
        //int [][] chessBoard = new int[8][8];
        
        for (int x = 0; x < numTestCases; x++) {

            //Map<String, Integer> found = new LinkedHashMap<String, Integer>();
            //int numJumps = 0;
            String starting = reader.readLine();
            //int startingLetter = starting.charAt(0) - 'a';
            //int endingLetter = Integer.parseInt(starting.substring(1));
            bfs(starting);
        }
    }

    public static void bfs(String starting) {

        boolean [][] visited = new boolean[8][8];
        LinkedList<String> queue = new LinkedList<String>();
        StringBuilder hidingPlace = new StringBuilder();
        
        visited[getX(starting)][getY(starting) - 1] = true;
        queue.add(starting);
        int jumps = 0;
        int jumpIter = 1;
        int numNodesForJump = 0;
        List<Integer> finalVals = new ArrayList<Integer>();
        while (!queue.isEmpty()) {
            //if (jumps == 6)
            //    break;
            String temp = queue.remove();
            if (hidingPlace.length() == 0)
                hidingPlace.append(jumps);
            //hidingPlace.append(" " + temp);

            int x = getX(temp);
            int y = getY(temp) - 1;

            int valToAdd = y * 10 + (8 - x);
            finalVals.add(valToAdd);
            //System.out.println(getCoord(x, y));

            if ((x - 1) >= 0 && (y + 2) < 8 && !visited[x - 1][y + 2]) {
                visited[x - 1][y + 2] = true;
                queue.add(getCoord((x - 1), (y + 2)));
                numNodesForJump++;
            }
            if ((x + 1) < 8 && (y + 2) < 8 && !visited[x + 1][y + 2]) {
                visited[x + 1][y + 2] = true;
                queue.add(getCoord((x + 1), (y + 2)));
                numNodesForJump++;
            }
            if ((x - 2) >= 0 && (y + 1) < 8 && !visited[x - 2][y + 1]) {
                visited[x - 2][y + 1] = true;
                queue.add(getCoord((x - 2), (y + 1)));
                numNodesForJump++;
            }
            if ((x + 2) < 8 && (y + 1) < 8 && !visited[x + 2][y + 1]) {
                visited[x + 2][y + 1] = true;
                queue.add(getCoord((x + 2), (y + 1)));
                numNodesForJump++;
            }
            if ((x - 2) >= 0 && (y - 1) >= 0 && !visited[x - 2][y - 1]) {
                visited[x - 2][y - 1] = true;
                queue.add(getCoord((x - 2), (y - 1)));
                numNodesForJump++;
            }
            if ((x + 2) < 8 && (y - 1) >= 0 && !visited[x + 2][y - 1]) {
                visited[x + 2][y - 1] = true;
                queue.add(getCoord((x + 2), (y - 1)));
                numNodesForJump++;
            }
            if ((x - 1) >= 0 && (y - 2) >= 0 && !visited[x - 1][y - 2]) {
                visited[x - 1][y - 2] = true;
                queue.add(getCoord((x - 1), (y - 2)));
                numNodesForJump++;
            }
            if ((x + 1) < 8 && (y - 2) >= 0 && !visited[x + 1][y - 2]) {
                visited[x + 1][y - 2] = true;
                queue.add(getCoord((x + 1), (y - 2)));
                numNodesForJump++;
            }
            jumpIter--;

            if (jumpIter == 0) {
                jumps++;
                jumpIter = numNodesForJump;
                numNodesForJump = 0;
                if (!queue.isEmpty()) {
                    hidingPlace.setLength(0);
                    finalVals = new ArrayList<Integer>();
                }
            }

        }
        Collections.sort(finalVals, Collections.reverseOrder());
        for (int val:finalVals) {
            //System.out.println(val);
            int tens = val/10;
            hidingPlace.append(" " + getCoord(8 - (val%10), tens));
        }
        System.out.println(hidingPlace.toString());
    }
    
    public static int getX(String coord) {
        return coord.charAt(0) - 'a';
    }
    public static int getY(String coord) {
        return Integer.parseInt(coord.substring(1));
    }
    public static String getCoord(int x, int y) {
        int temp = x + 97;
        return String.valueOf((char)temp) + (y + 1);
    }
}

//public Spot {
    
//    String coord;
//    int jumps;

//    Spot(String coord, int jumps) {
//        this.coord = coord;
//        this.jumps = jumps;
//    }
//}