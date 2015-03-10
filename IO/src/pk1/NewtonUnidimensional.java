package pk1;

public class NewtonUnidimensional {
    public static final double EPSILON = 0.01;

    /**
     * Minimiza la funcion branin dado un valor x
     * @param f
     * @param x
     * @return
     */
    public static String minimiza(Branin f, double x, int iterations) {
    	int i = 0;
    	String res = "";
        while ((Math.abs(f.prime(x)) > EPSILON) && i < iterations) {
           x = x - (f.prime(x) / f.primeprime(x));
           //System.out.println(x + " " + f.prime(x) + " " + f.primeprime(x));
           //System.out.println("x^"+i+":"+x+" Valor f(x):"+f.eval(x));
           res += ("x^"+i+":"+x+" Valor f(x):"+f.eval(x)+ "\n \n");
           i++;
        }
        if(Math.abs(f.prime(x)) > EPSILON) 
        	res += ("No se ha podido encontrar un 0 en " + i + " iteraciones");
        else 
        	res += ("Se ha encontrado un minimo: \nx = "+ x +" \nf(x) = "+f.eval(x) +" \nf'(x) = " + f.prime(x) + " \nen " + i + " iteraciones");
        return res;
    }

}
