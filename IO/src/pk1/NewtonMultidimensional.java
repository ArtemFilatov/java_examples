package pk1;

public class NewtonMultidimensional {
	public static final double EPSILON = 0.01;
	
	public static double[][] hessianMatrix(Branin f, double x1, double x2) {
		double[][] H = new double[2][2]; 
		H[0][0] = f.primeprimeX(x1,x2); // a 
		H[0][1] = f.primeprimeXY(x1,x2); // b
		H[1][0] = f.primeprimeXY(x1,x2); // c
		H[1][1] = f.primeprimeY(x1,x2); // d
		return H;
	}
	
	public static double det(double[][] H) {
		return H[0][0] * H[1][1] - H[1][0] * H[0][1];
	}
	
	public static double[][] inversa(double[][] H, double det) {
		double aux = H[0][0];
		H[0][0] = H[0][1]/det;
		H[0][1] = -H[0][1]/det;
		H[1][0] = -H[1][0]/det;
		H[1][1] = aux/det;
		return H;
	}
	
    public static String minimiza(Branin f, double x1, double x2, int iterations) {
    	int i = 0;
    	String res = "";
        while ((Math.abs(f.primeBi(x1,x2)) > EPSILON) && i < iterations) {
           x1 = x1 - (f.prime(x1) / f.primeprime(x1));
           //System.out.println(x + " " + f.prime(x) + " " + f.primeprime(x));
           //System.out.println("x^"+i+":"+x+" Valor f(x):"+f.eval(x));
           res += ("x^"+i+":"+x1+" Valor f(x):"+f.eval(x1)+ "\n \n");
           i++;
        }
        if(Math.abs(f.prime(x1)) > EPSILON) 
        	res += ("No se ha podido encontrar un 0 en " + i + " iteraciones");
        else 
        	res += ("Se ha encontrado un minimo: \nx = "+ x1 +" \nf(x) = "+f.eval(x1) +" \nf'(x) = " + f.prime(x1) + " \nen " + i + " iteraciones");
        return res;
    }
}
