import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class claimedX here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class claimedX extends Actor
{
    /**
     * Act - do whatever the claimedX wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static final int CELL_SIZE = 50;
    int boardS;
    int XSize;
    
    public claimedX(Board b)
    {
        boardS = b.boardSize;
        XSize = boardS*CELL_SIZE;
        getImage().scale(XSize, XSize);
    }
    public void act()
    {
        // Add your action code here.
    }
}
