/*
Find the Number of Islands

Write a function which returns the number of islands in a body of water.
You will be provided with a 2D binary grid where land is represented by "1", and water by "0".
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may also assume all four edges of the grid are surrounded by water.

Example 1
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
 */
public class LTV {

    public static void main(String[] arg) {
        int[][] gridArray = {
                {1,1,1,1,0},
                {1,1,0,1,0},
                {1,1,0,0,0},
                {0,0,0,0,0}
        };
        int theLandCount = findTheLandCount(gridArray);
        System.out.println(theLandCount);
    }

    private static int findTheLandCount(int[][] gridArray) {
        int landCount = 0;
        for (int i = 0; i < gridArray.length; i++) {
            for (int j = 0; j < gridArray.length; j++) {
                if (gridArray[i][j] == 1){
                    landCount++;
                }
            }
        }
        return landCount;
    }
}
