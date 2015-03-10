import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * The frame with ball component and buttons.
 */
@SuppressWarnings("serial")
class BounceFrame extends JFrame
{
   /**
    * Constructs the frame with the component for showing the bouncing ball and Start and Close
    * buttons
    */
   public BounceFrame()
   {
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
      setTitle("Bounce");

      comp = new BallComponent();
      add(comp, BorderLayout.CENTER);
      JPanel buttonPanel = new JPanel();
      addButton(buttonPanel, "Start", new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
            	for(int i = 0; i <10; i++)
            		for(int j = 0; j < 5; j++)
            			if(j % 5 == 0)
            				addBall();

            }
         });

      addButton(buttonPanel, "Stop", new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               stopThreads();
            }
         });
      add(buttonPanel, BorderLayout.SOUTH);
   }

   /**
    * Adds a button to a container.
    * @param c the container
    * @param title the button title
    * @param listener the action listener for the button
    */
   public void addButton(Container c, String title, ActionListener listener)
   {
      JButton button = new JButton(title);
      c.add(button);
      button.addActionListener(listener);
   }

   /**
    * If threads are stopped activate all threads if not adds a new bouncing ball thread 
    * to the panel and makes it bounce STEPS times.
    */
   public void addBall() {
	   if (threadsAreStopped) startThreads();
	   else{
		   BallThread t = new BallThread(comp);
		   theThreads.add(t);
		   t.start();
	   }
   }

   /**
    * Metodo encargado de arrancar las hebras.
    */
   public void startThreads(){
	   threadsAreStopped = false;
	   for(BallThread t: theThreads){
		   t.arrancar();
	   }
   }
   /**
    * Metodo encargado de parar las hebras. En verdad lo que hace es solicitar la interrumpcion.
    */
   public void stopThreads(){
	   threadsAreStopped = true;
	   for(BallThread t: theThreads){
		   t.parar();
	   }
   }

   private boolean threadsAreStopped = false;
   private Collection<BallThread> theThreads = new ArrayList<BallThread>();
   private BallComponent comp;
   public static final int DEFAULT_WIDTH = 450;
   public static final int DEFAULT_HEIGHT = 350;
}
