import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JComponent;


/**
 * The component that draws the balls.
 * @version 1.33 2007-05-17
 * @author Cay Horstmann
 */
@SuppressWarnings("serial")
public class BallComponent extends JComponent
{
   private Collection<Ball> balls =new ArrayList<Ball>();

   /**
    * Add a ball to the component.
    * @param b the ball to add
    */
   public void add(Ball b)
   {
	   synchronized (balls){ balls.add(b);}
   }

   public void paintComponent(Graphics g)
   {
      // super.paintComponent(g); // erase background
      Graphics2D g2 = (Graphics2D) g;
      synchronized (balls){
    	  for (Ball b : balls)
    	  {
    		  g2.fill(b.getShape());
    	  }
      }
   }


}

