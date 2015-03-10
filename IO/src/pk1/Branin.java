package pk1;

import java.awt.Color;

import javax.swing.JFrame;

import org.math.plot.*;
import org.math.plot.plotObjects.Line;

import view.MainView;

public class Branin {
	
	/**
	 * Constructor sn parametros
	 */
	public Branin() {}
	
	// Unidimensional
	/**
	 * Evalua la funcion branin unidimensional para un valor dado x
	 * @param x
	 * @return Valor (double) f(x) de branin
	 */
	public double eval(double x) {
		return Math.pow((1-2*x + 0.05*Math.sin(4*Math.PI*x)-x),2) + Math.pow((x -0.5*Math.sin(2*Math.PI*x)),2);
	}
	
	/**
	 * Evalua la primera derivada de la funcion branin unidimensial para un valor dado x
	 * @param x
	 * @return Valor (double) f'(x) de branin
	 */
	public double prime(double x) {
		return 2 * (x-0.5*Math.sin(2*Math.PI*x)) * (1-Math.PI*Math.cos(2*Math.PI*x))
				- 3.76991 * (x-0.0166667*Math.sin(4*Math.PI*x)-0.3333) * (Math.cos(4*Math.PI*x)-4.77465);
	}
	
	/**
	 * Evalua la segunda derivada de la funcion branin unidimensional para un valor dado x
	 * @param x
	 * @return Valor (double) f''(x) de branin
	 */
	public double primeprime(double x) {
		return 39.4784*x*Math.sin(2*Math.PI*x) + 47.3741*x*Math.sin(4*Math.PI*x) - 15.7898*Math.sin(4*Math.PI*x) 
				- 12.5664*Math.cos(2*Math.PI*x) + 12.1994*Math.cos(4*Math.PI*x) + 0.78957*Math.cos(8*Math.PI*x)+20;
	}
	
	// Bidimensional
	/**
	 * Evalua la funcion branin bidimensional en un vector (x1,x2)
	 * @param x1 - double
	 * @param x2 - double
	 * @return Valor f(x) Branin 
	 */
	public double evalBi(double x1, double x2) {
		return Math.pow((1-2*x2 + 0.05*Math.sin(4*Math.PI*x2)-x1),2) + Math.pow(x2 -0.5*Math.sin((2*Math.PI*x1)),2);	
	}
	
	/**
	 * Segunda derivada sobre x
	 * @param x1 - double
	 * @param x2 - double
	 * @return Valor f''x
	 */
	public double primeprimeX(double x1, double x2) {
		return 2*Math.pow(Math.PI,2)*Math.cos(4*Math.PI*x1) + 4*Math.pow(Math.PI,2)*x2*Math.sin(2*Math.PI*x1) + 2;
	}
	
	/**
	 * Segunda derivada sobre y
	 * @param x1 - double
	 * @param x2 - double
	 * @return Valor f''x
	 */
	public double primeprimeY(double x1, double x2) {
		return 2*Math.pow(Math.PI,2)*Math.cos(8*Math.PI*x2)/25 - 8*Math.PI*Math.cos(4*Math.PI*x2)/5 + 
				8*Math.pow(Math.PI,2)*(x1 + 2*x2 - 1)*Math.sin(4*Math.PI*x2)/5 + 10;
	}
	
	/**
	 * Segunda derivada sobre xy
	 * @param x1
	 * @param x2
	 * @return
	 */
	public double primeprimeXY(double x1, double x2) {
		return 2*Math.PI*Math.cos(2*Math.PI*x1) - 2*Math.PI*Math.cos(4*Math.PI*x2)/5 + 4;
	}
	/**
	 * Evalua la funcion derivada de branin bidimensional en un vector (x1,x2)
	 * @param x1 - double
	 * @param x2 - double
	 * @param Valor (double) f'(x) de branin
	 */
	public double primeBi(double x1, double x2) {
		return -6.28319*x2*Math.cos(2*Math.PI*x1) + 2*x1 + 1.5708*Math.sin(4*Math.PI*x1) + 4*x2 - 0.1*Math.sin(4*Math.PI*x2) - 2;
	}
	
	/**
	 * Evalua la segunda derivada de la funcion branin bidimensional para un vector (x1,x2)
	 * @param x
	 * @return Valor (double) f''(x) de branin
	 */
	public double primeprimeBi(double x1, double x2) {
		return 0;
	}
	
	public static void main(String[] args) {
		
		Branin f = new Branin();
		// Unidimensional
			// Newton
		double x = 0;
		NewtonUnidimensional.minimiza(f, x,100);
			// Biseccion
		double a = -20;
		double b = 20;
		Biseccion.minimiza(f, a, b,100);
		
		
		// Bidimensional		
		// Minimo global 
			// x¹
		double x1 = 1.0;
		double x2 = 0;
			// x²
		double x3 = 0.1486956977;
		double x4 = 0.402086397;
			// x³
		double x5 = 0.4025369587;
		double x6 = 0.2874076236;
			// x⁴
		double x7 = 1.5974630413;
		double x8 = -0.2874076236;
			// x⁵
		double x9 = 1.8513043023;
		double x10 = -0.4020864397;

		//System.out.println(f.evalBi(x1, x2)); // 1.4997597826618576E-32
		//System.out.println(f.evalBi(x3, x4)); // 7.653913051860571E-15 
		//System.out.println(f.evalBi(x5, x6)); // 9.739981509738014E-22 
		//System.out.println(f.evalBi(x7, x8)); // 9.740135095409583E-22
		//System.out.println(f.evalBi(x9,x10)); // 1.0326270861542721E-20
		
		
		MainView m = new MainView();
	}
	
	public static void dibujaUnidimensional(Branin f) {
		Plot2DPanel plot = new Plot2DPanel();
		double[] xpoints = new double[80];	
		double[] ypoints = new double[80];
		double i = -4.0;
		for(int j = 0; j < 80; j++) {
			xpoints[j] = i;
			ypoints[j] = f.eval(i);
			i += 0.1;
		}
		plot.addLinePlot("X-Y Minimo en x = 0.3404..., y = 0.011...",xpoints, ypoints);
		plot.addLegend("SOUTH");
		JFrame frame = new JFrame("Branin Unidimensional");
		frame.setContentPane(plot);
		frame.setSize(600, 600);
		frame.setVisible(true);
	}
	
	public static void dibujaMultidimensional(Branin f) {
		Plot3DPanel plot = new Plot3DPanel("SOUTH"); // Orientation SOUTH
		double[] xpoints = new double[80];
		double[] ypoints = new double[80];
		double[][] zpoints = new double[80][80];
		double i = -4.0;
		for(int j = 0; j < 80; j++) {
			xpoints[j] = i;
			ypoints[j] = i;
			i += 0.1;
		}
		for(int k = 0; k <xpoints.length; k++) {
			for(int j = 0; j < ypoints.length; j++) {
				zpoints[k][j] = f.evalBi(xpoints[k], ypoints[j]);
			}
		}
		plot.addGridPlot("X-Y-Z", xpoints, ypoints, zpoints);
		
		JFrame frame = new JFrame("Branin Multidimensional");
		frame.setSize(600, 600);
		frame.setContentPane(plot);
		frame.setVisible(true);
	}
}
