import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Highlight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Highlight extends Actor
{
    /**
     * Act - do whatever the Highlight wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static final int CELL_SIZE = 50;
    int boardS;
    int highlightSize;
    
    public Highlight(Board b)
    {
        boardS = b.boardSize;
        highlightSize = boardS*CELL_SIZE;
    }
    
    
    public void act()
    {
        // Add your action code here.
        //getImage().scale(highlightSize, highlightSize);
    }
}
