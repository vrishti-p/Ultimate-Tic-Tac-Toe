import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WinText here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WinText extends Actor
{
    /**
     * Act - do whatever the WinText wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public WinText(String msg)
    {
        GreenfootImage img = new GreenfootImage(msg, 80, Color.YELLOW, new Color(0, 0, 0, 0));
        setImage(img);
    }
    public void act()
    {
        // Add your action code here.
    }
}
