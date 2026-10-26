import greenfoot.*;
//import java.awt.Color;
/**
* This is a game of Ultimate TicTacToe, AKA a TicTacToe of TicTacToes!
* 
* @author: Vrishti Patidar 
* Date: 11/10/26
*/
 
public class Board extends World
{
    private static final int CELL_SIZE = 50;
    private UltTTT logic;
    private Label playerX;
    private Label playerY;
    int boardSize;
    private boolean gameOver = false;
    private Highlight highlight;
    private TurnText txt;
 
    /**
     * Constructor for objects of class Board.
     * Makes an n^2 * n^2 board
     */
    public Board(int boardSize)
    {    
        super(boardSize*boardSize, boardSize*boardSize + 1, CELL_SIZE, true); 
        this.boardSize = boardSize;
        logic = new UltTTT(boardSize,boardSize);
        txt = new TurnText();
        addObject(txt, boardSize*boardSize/2, boardSize*boardSize);
        GreenfootImage bkgrnd = getBackground();
        bkgrnd.setColor(Color.WHITE);
        int cellSize = getCellSize();
        bkgrnd.fillRect(0, (getHeight() - 1)*cellSize, getWidth()*cellSize, 2*cellSize);
        setBackground(bkgrnd);
        drawBoard();
        updateBoard();
    }
    // divides the grid into n*n with a thick black border, where each cell is n*n
    public void drawBoard()
    {
        GreenfootImage bg = getBackground();
        int cellSize = getCellSize();
        int boardPixels = getWidth()*cellSize;
        int smallBoardPixels = boardPixels/boardSize;
        bg.setColor(Color.BLACK);
        for(int i = 1; i < boardSize; i++)
        {
            int pos = i*smallBoardPixels;
            for(int t = -2; t <= 3; t++)
            {
                bg.drawLine(pos+t,0,pos+t,boardPixels);
                bg.drawLine(0, pos+t, boardPixels, pos+t);
            }
        }
        setBackground(bg);
    }
    /* Places an X or an O where the mouse clicks. 
    If a small grid has been won, a larger X or O is placed to claim that grid
    */
    public void updateBoard()
    {
        int totalSize = boardSize*boardSize;
        for(int bigR = 0; bigR < boardSize; bigR++)
        {
            for(int bigC = 0; bigC < boardSize; bigC++)
            {
                for(int r = 0; r < boardSize; r++)
                {
                    for(int c = 0; c < boardSize; c++)
                    {
                        int cellX = bigC*boardSize+c;
                        int cellY = bigR*boardSize+r;
                        removeObjects(getObjectsAt(cellX,cellY,Marker.class));
                        
                        int state = logic.getStateAt(bigR, bigC, r, c);
                
                        if(state == UltTTT.XMARKER)
                            addObject(new Xmarker(), cellX,cellY);
                        else if(state == UltTTT.OMARKER)
                            addObject(new Omarker(), cellX,cellY);
                            
                        txt.updateText(logic.getPlayer());
                    }
                }
                // Draws a big X/O over won grid
                int ultState = logic.getUltStateAt(bigR, bigC);
                int centerX = bigC * boardSize + boardSize / 2;
                int centerY = bigR * boardSize + boardSize / 2;
                int objSize = boardSize*CELL_SIZE;
                
                if (ultState == logic.XCLAIMED)
                {
                    claimedX x = new claimedX(this);
                    GreenfootImage originalX = x.getImage();
                    // logic to align the X on a grid with even dimensions in the center of the grid
                    if (boardSize % 2 == 0)
                    {    
                        GreenfootImage alignedX = new GreenfootImage(objSize + CELL_SIZE,objSize + CELL_SIZE);
                        alignedX.setColor(new Color(0, 0, 0, 0));
                        alignedX.fill();
                        alignedX.drawImage(originalX, 0, 0);
                        x.setImage(alignedX);
                    }
                    addObject(x, centerX, centerY);
                }
                else if (ultState == logic.OCLAIMED)
                {      
                    claimedO o = new claimedO(this);
                    GreenfootImage originalO = o.getImage();
                    // logic to align the O on a grid with even dimensions in the center of the grid
                    if (boardSize % 2 == 0)
                    {      
                        GreenfootImage alignedO = new GreenfootImage(objSize + CELL_SIZE,objSize + CELL_SIZE);
                        alignedO.setColor(new Color(0, 0, 0, 0));
                        alignedO.fill();
                        alignedO.drawImage(originalO, 0, 0);
                        o.setImage(alignedO);
                    }
                    addObject(o, centerX, centerY);
                }
                
            }
        }
    }
    //places the marker where the mouse clicked
    public void takeTurn()
    {
        if (gameOver)
        {
            return;
        }
        MouseInfo mi = Greenfoot.getMouseInfo();
        if (mi == null)
        {
            return;
        }
        int row = mi.getY();
        int col = mi.getX();
        int bigR = row/boardSize;
        int bigC = col/boardSize;
        int r = row%boardSize;
        int c = col%boardSize;
        if(logic.isValid(bigR, bigC, r, c))
        {
           logic.makeMove(bigR, bigC, r, c, logic.getPlayer());
           updateBoard();
           drawBoard();
           highlightGrid();
           checkGameOver();
        }
    }
    public void act()
    { 
        if(Greenfoot.mouseClicked(null))
        {
            takeTurn();
        }
    }
    // Highlights the grid in which the next player must play in
    public void highlightGrid()
    {
        if (highlight != null)
        {
            removeObject(highlight);
            highlight = null;
        }

        int activeR = logic.getActRow();
        int activeC = logic.getActCol();

        GreenfootImage bg = getBackground();
        drawBoard();
        
        
        int cellSize = getCellSize();
        int totalPixels = getWidth() * cellSize;

        bg.setColor(Color.GREEN);
        // highlights the entire grid if its a free choice
        if (activeR == -1 && activeC == -1)
        {
            int highlightSize = boardSize * boardSize * CELL_SIZE;
            highlight = new Highlight(this);
            
            GreenfootImage outline = highlight.getImage();
            outline.scale(highlightSize, highlightSize);
            // condition to align the highlight in the center if the grid has even dimensions
            if(boardSize % 2 == 0)
            {
                GreenfootImage aligned = new GreenfootImage(highlightSize + CELL_SIZE, highlightSize + CELL_SIZE);
                aligned.setColor(new Color(0, 0, 0, 0));
                aligned.fill();
                aligned.drawImage(outline, 0, 0);
                highlight.setImage(aligned);
                addObject(highlight,getWidth() / 2,getHeight() / 2);
            }
            addObject(highlight,getWidth() / 2,getHeight() / 2 - 1);
        }
        
        else
        {
            int centerX = activeC * boardSize + boardSize / 2;
            int centerY = activeR * boardSize + boardSize / 2;
            int highlightSize = boardSize * CELL_SIZE;

            highlight = new Highlight(this);

            GreenfootImage outline = highlight.getImage();
            outline.scale(highlightSize, highlightSize);
            
            // makes a centered highlight on the small grid of even dimensions
            if (boardSize % 2 == 0)
            {           
                GreenfootImage aligned = new GreenfootImage(highlightSize + CELL_SIZE,highlightSize + CELL_SIZE);

                aligned.setColor(new Color(0, 0, 0, 0));
                aligned.fill();
                aligned.drawImage(outline, 0, 0);
                highlight.setImage(aligned);
            }

            addObject(highlight, centerX, centerY);
        }
    }    
    public void checkGameOver()
    {
        String winMsg = "";
        if(logic.hasWonUlt(logic.XMARKER))
        {
            winMsg = "X WINS!";
            gameOver = true;
        }
        else if(logic.hasWonUlt(logic.OMARKER))
        {
            winMsg = "X WINS!";
            gameOver = true;
        }
        else if(logic.isTie())
        {
            winMsg = "X WINS!";
            gameOver = true;
        }
        
        if(gameOver)
        {
            // removes all objects so the win text is visible
            removeObject(txt);
            removeObject(highlight);
            
            int boardWidth = (getWidth()+1) * CELL_SIZE;
            int boardHeight = (getHeight()+1) * CELL_SIZE;
            //darken the background to display the win text
            addObject(new darkenBG(boardWidth, boardHeight), getWidth() / 2, getHeight() / 2);
        
            // 2. Add the text banner second so it renders on top of the overlay
            addObject(new WinText(winMsg), getWidth() / 2, getHeight() / 2);
            
        }
    }

}