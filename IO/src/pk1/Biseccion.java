package pk1;

public class Biseccion {
	public static final double EPSILON = 1e-4;

    /**
     * Minimiza la funcion branin dado un valor x
     * @param f
     * @param x
     * @return
     */
    public static String minimiza(Branin f, double a, double b, int iterations) {
    	int i = 0;
    	double x = 1;
    	String res = "";
        while (Math.abs(f.prime(x)) > EPSILON && i < iterations) {
        	x = (a+b)/2;
        	if(f.prime(x) > 0) {
        		b = x;
        	}else if(f.prime(x) < 0) {
        		a = x;
        	}
        	res += "f'(x) = " + f.prime(x) + " x = " + x + " a = " + a +" b = " + b + "\n \n";
        	//System.out.println(f.prime(x) + " " + x +" " + a + " " + b);
        	i++;
        }
        if(Math.abs(f.prime(x)) > EPSILON) 
        	res += ("No se ha podido encontrar un 0 en " + i + " iteraciones");
        else 
        	res += ("Se ha encontrado un minimo: \nx = "+ x +" \nf(x) = "+f.eval(x) +" \nf'(x) = " + f.prime(x) + " \nen " + i + " iteraciones");
        return res;
    }
}
