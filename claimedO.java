import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class claimedO here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class claimedO extends Actor
{
    /**
     * Act - do whatever the claimedO wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static final int CELL_SIZE = 50;
    int boardS;
    int OSize;
    
    public claimedO(Board b)
    {
        boardS = b.boardSize;
        OSize = boardS*CELL_SIZE;
        getImage().scale(OSize, OSize);
    }
    public void act()
    {
        // Add your action code here.
    }
}
