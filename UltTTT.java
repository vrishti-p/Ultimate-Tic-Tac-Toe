import java.util.Scanner;
public class UltTTT {
    private final SmallBoard[][] ultGrid;
    private final int[][] bigGrid;
    public final static int EMPTY = 0;
    public final static int XMARKER = 1;
    public final static int OMARKER = 2;
    public final static int XCLAIMED = 3;
    public final static int OCLAIMED = 4;
    public final static int INVALID = -1;
    // -1 means free choice. any other int refers to the grid the next player must play in.
    public int activeRow = -1;
    public int activeCol = -1;
    private int player;
    public UltTTT(int gameRows, int gameCols)
    {       
        ultGrid = new SmallBoard[gameRows][gameCols];
        bigGrid = new int[gameRows][gameCols];
        for (int r = 0; r < ultGrid.length; r++)
        {
            for (int c = 0; c < ultGrid[0].length; c++)
            {
                ultGrid[r][c] = new SmallBoard(gameRows, gameCols);
            }
        }
        player = XMARKER;
    }
    public boolean isValid(int bigR, int bigC, int row, int col)
    {
         if (bigR < 0 || bigR >= ultGrid.length||bigC < 0 || bigC >= ultGrid[0].length)
         {
             return false;
        }
 
        if (row < 0 || row >= ultGrid[bigR][bigC].getRows() || col < 0 || col >= ultGrid[bigR][bigC].getCols())
        {
            return false;
        }
 
        if (bigGrid[bigR][bigC] != EMPTY)
        {
            return false;
        }
        if(activeRow != -1 && (bigR != activeRow || bigC != activeCol))
        {
            return false;
        }
 
        return ultGrid[bigR][bigC].isValid(row, col);
    }
    public boolean hasWonUlt(int player)
    {
        int target;
        if(player == XMARKER)
        {
            target = XCLAIMED;
        }
        else
        {
            target = OCLAIMED;
        }
        //row win
        for (int r = 0; r < ultGrid.length; r++)
         {
            boolean rowWin = true;
            for (int c = 0; c < ultGrid[0].length; c++)
            {
                if (bigGrid[r][c] != target)
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
        for (int c = 0; c < ultGrid[0].length; c++)
        {
            boolean colWin = true;
            for (int r = 0; r < ultGrid.length; r++)
            {
                if (bigGrid[r][c] != target)
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
        for (int i = 0; i < ultGrid.length; i++)
        {
            if(bigGrid[i][i] != target)
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
        for (int i = 0; i < ultGrid.length; i++)
        {
            if(bigGrid[i][ultGrid.length-1-i]!=target)
            {
                diag2Won = false;
                break;
            }
        }
        return diag2Won;
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
    public int  getPlayer()
    {
        return player;
    }
    public boolean isFull()
    {
        for (int r = 0; r < ultGrid.length; r++)
        {
            for (int c = 0; c < ultGrid[0].length; c++)
            {
                if (bigGrid[r][c] == EMPTY && !ultGrid[r][c].isFull())
                {
                    return false;
                }
            }
        }
        return true;
    }
    public void claim(int r, int c) // r and c are the indices of the most recent move played
    {
        if(ultGrid[r][c].hasWon(XMARKER))
        {
            bigGrid[r][c] = XCLAIMED;
            activeRow = -1;
            activeCol = -1;
        }
        else if(ultGrid[r][c].hasWon(OMARKER))
        {
            bigGrid[r][c] = OCLAIMED;
            activeRow = -1;
            activeCol = -1;
        }
        
    }
    public void makeMove(int bigR, int bigC, int r, int c, int player)
    {
        ultGrid[bigR][bigC].makeMove(r, c, player);
        claim(bigR, bigC);
        if(bigGrid[r][c] != EMPTY || ultGrid[bigR][bigC].isFull())
        {
            activeRow = -1;
            activeCol = -1;
        }
        else
        {
            activeRow = r;
            activeCol = c;
        }
        switchPlayer();
    }
    public boolean isTie()
    {
        return isFull() && !hasWonUlt(XMARKER) && !hasWonUlt(OMARKER);
    }
    public void print()
    {
        for (int bigR = 0; bigR < ultGrid.length; bigR++)
        {
           for(int r = 0; r < ultGrid.length; r++)
           {
               for(int bigC = 0; bigC < ultGrid[0].length; bigC++)
               {
                   for(int c = 0; c < ultGrid[0].length; c++)
                   {
                       System.out.print( "\t" + ultGrid[bigR][bigC].getCell(r, c));
                   }
                   System.out.print("|");
               }
               System.out.println();
           }
           System.out.println("————————————————————————————————————————————————————————————————————————————");
        }
    }
    public int getActRow()
    {
       return activeRow;
    }
    public int getActCol()
    {
       return activeCol;
    }
    public int getStateAt(int bigR,int bigC,int r,int c)
    {
        return ultGrid[bigR][bigC].getCell(r,c);
    }
    public int getUltStateAt(int r, int c)
    {
        return bigGrid[r][c];
    }
    public int getRows()
    {
        return ultGrid.length;
    }
    public int getCols()
    {
        return ultGrid[0].length;
    }
    public static void main(String[] args)
    {
       UltTTT game = new UltTTT(3,3);
       Scanner sc = new Scanner(System.in);
       game.print();
       while(true)
       {
           int currentPlayer = game.getPlayer();
           int gridRow = game.getActRow();
           int gridCol = game.getActCol();
           if(game.getActRow() == -1 && game.getActCol() == -1)
           {
               System.out.println("Which small grid would you like to play in?");
               System.out.println("grid row: ");
               gridRow = sc.nextInt();
               System.out.println("grid col: ");
               gridCol = sc.nextInt();
           }
           System.out.println("Where do you want to make your move? ");
           System.out.print("Row: ");
           int row = sc.nextInt();
           System.out.println("Col: ");
           int col = sc.nextInt();
           game.makeMove(gridRow, gridCol, row, col, currentPlayer);
           game.print();
           if (game.hasWonUlt(XMARKER))
           {
               System.out.println("X won!");
               break;
           }
           else if (game.hasWonUlt(OMARKER))
           {
               System.out.println("O won!");
               break;
           }
           if(game.isTie())
           {
               System.out.println("its a tie!");
           }
      }
    }
}