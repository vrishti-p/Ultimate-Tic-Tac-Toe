/**
* Write a description of class SmallBoard here.
* 
* @author (your name) 
* @version (a version number or a date)
*/
import java.util.Scanner;
public class SmallBoard  
{
    // instance variables - replace the example below with your own
    public final static int EMPTY = 0;
    public final static int XMARKER = 1;
    public final static int OMARKER = 2;
    public final static int XCLAIMED = 3;
    public final static int OCLAIMED = 4;
    public final static int INVALID = -1;
    private final int[][] grid;
    private int player;
    /**
    * Constructor for objects of class SmallBoard
    */
    public SmallBoard(int gameRows, int gameCols)
    {
        grid = new int[gameRows][gameCols];
        for(int r = 0; r < grid.length; r++)
        {
            for(int c = 0; c < grid[0].length; c++)
            grid[r][c] = EMPTY;
        }
        player = XMARKER;
    }
 
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void print()
    {
        for (int[] r: grid)
        {
            for(int val: r)
            {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
    public boolean isValid(int row, int column)
    {
       if (row < 0 || row >= grid.length || column < 0 || column >= grid[0].length) {
       return false;
       }
       return grid[row][column] == EMPTY;
    }
    public void makeMove(int r, int c, int player)
    {
        if (isValid(r, c))
        {
            grid[r][c] = player;
        }
        else
        {
            System.out.println("Invalid move!");
        }
    }
    public boolean isFull()
    {
        for (int[] r: grid)
        {
            for (int val: r)
            {
                if (val == EMPTY)
                {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean hasWon(int player)
    {
        //row win
        for (int r = 0; r < grid.length; r++)
        {
            boolean rowWin = true;
            for (int c = 0; c < grid[0].length; c++)
            {
                if (grid[r][c] != player)
                {
                    rowWin = false;
                    break;
                }
            }
            if(rowWin)
            {
                return true;
            }
        }
        //column win
        for (int c = 0; c < grid[0].length; c++)
        {
            boolean colWin = true;
            for (int r = 0; r < grid.length; r++)
            {
                if (grid[r][c] != player)
                {
                    colWin = false;
                    break;
                }
            }
            if (colWin)
            {
                return true;
            }
        }
        // diagonal from left to right
        boolean diag1Won = true;
        for (int i = 0; i < grid.length; i++)
        {
            if(grid[i][i] != player)
            {
                diag1Won = false;
                break;
            }
        }
        if(diag1Won)
        {
        return true;
        }
        //diagonal from left to right;
        boolean diag2Won = true;
        for (int i = 0; i < grid.length; i++)
        {
            if(grid[i][grid.length-1-i] != player)
            {
                diag2Won = false;
                break;
            }
        }
        return diag2Won;
    }
    public void whoWon()
    {
        if(hasWon(XMARKER))
        {
            System.out.println("X won!");
        }
        if(hasWon(OMARKER))
        {
           System.out.println("O won!");
        }
    }
    public void switchPlayer()
    {
        if(player == XMARKER)
        {
            player = OMARKER;
        }
        else if(player == OMARKER)
        {
            player = XMARKER;
        }
    }
    public int getPlayer()
    {
        return player;
    }
    public int getCell(int r, int c)
    {
       return grid[r][c];
    }
    public int getRows()
    {
        return grid.length;
    }
    public int getCols()
    {
        return grid[0].length;
    }
    public static void main (String[] args)
    {
        SmallBoard sb = new SmallBoard(3, 3);
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            int currentPlayer = sb.getPlayer();
            sb.print();
            System.out.println("Where do u wanna make your move? ");
            System.out.println("Row: ");
            int row = sc.nextInt();
            System.out.println("col: ");
            int col = sc.nextInt();
            sb.makeMove(row, col, currentPlayer);
            if (sb.hasWon(currentPlayer))
            {
                System.out.println(currentPlayer + "hasWon!");
                sb.whoWon();
                break;
            }
            else if(sb.isFull())
            {
                System.out.println("Its a tie!");
                break;
            }
            sb.switchPlayer();
        }
        sc.close();
    }
}