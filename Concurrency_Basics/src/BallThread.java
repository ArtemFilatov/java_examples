
public class BallThread extends Thread {
	private boolean parar = false;
	private BallComponent comp;
	private Ball ball;
    public static final int STEPS = 1000;
	public static final int DELAY = 3;
	public BallThread(BallComponent comp) {
		super();
		this.comp = comp;
	    this.ball = new Ball();
	}
	
	   @Override
	   public void run()
	   {
	         ball = new Ball();
			 comp.add(ball); 
	         moveBall();
	   }
	   public synchronized void moveBall(){
		   try
		   {
		         // for (int i = 1; i <= STEPS; i++)
			     while (true)
		         {
		            if (parar) this.wait();
		            else{
		            	ball.move(comp.getBounds());
			            comp.repaint();
			            Thread.sleep(DELAY);
		            }
		         }
		      }
		      catch (InterruptedException e) {}
	   }
	   
	   public synchronized void arrancar(){
		   parar = false;
		   this.notify();
	   }
	   public void parar(){
		   parar = true;
	   }
	   public void detener(){
		   Thread.interrupted();
	   }
}
