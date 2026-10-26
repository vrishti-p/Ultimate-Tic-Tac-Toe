import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TurnText here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TurnText extends Actor
{
    /**
     * Act - do whatever the TurnText wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public TurnText()
    {
        updateText(UltTTT.XMARKER);
    }
    public void updateText(int player)
    {
        if (player == UltTTT.XMARKER)
        {
            setImage(new GreenfootImage("X's TURN",40,Color.RED,new Color(0, 0, 0, 0)));
        }
        else if (player == UltTTT.OMARKER)
        {
            setImage(new GreenfootImage("O's TURN",40,Color.RED,new Color(0, 0, 0, 0)));
        }
    }
    public void act()
    {
        // Add your action code here.
    }
}
