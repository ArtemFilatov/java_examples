import java.awt.EventQueue;

import javax.swing.JFrame;


/**
 * Shows an animated bouncing ball.
 * @version 1.33 2007-05-17
 * @author Cay Horstmann - modified by Alberto de la Encina
 */
public class Bounce
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
         {
            public void run()
            {
               JFrame frame = new BounceFrame();
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
            }
         });
   }
}