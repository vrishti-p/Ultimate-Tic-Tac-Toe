import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class darkenBG here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class darkenBG extends Actor
{
    /**
     * Act - do whatever the darkenBG wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public darkenBG(int w, int h)
    {
        //darken the background to display the win text
        GreenfootImage img = new GreenfootImage(w, h);
        img.setColor(new Color(0, 0, 0, 160)); // Semi-transparent black
        img.fill();
        setImage(img);
    }
    public void act()
    {
        // Add your action code here.
    }
}
