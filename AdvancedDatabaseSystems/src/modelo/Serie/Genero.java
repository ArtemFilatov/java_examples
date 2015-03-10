package modelo.Serie;

/**
 * Created by n on 12/03/14.
 */
public enum Genero {
	// SELECT * FROM genero;
    COMEDIA ("Comedia"),
    ACCION ("Accion"),
    ROMANCE("Romance"),
    DRAMA ("Drama"),
    THRILLER ("Thriller"),
    SUSPENSE("Suspense"),
    AVENTURAS("Aventuras"),
    NEGRO ("Negro"),
    BELICO ("Belico");
    

    private final String value;       

    private Genero(String s) {
        value = s;
    }

    public String toString(){
       return value;
    }
    
   
    public static Genero createFromString(String s) {
    	Genero g = null;
	    	switch(s) {
	    	case "Comedia":
	    		g = Genero.COMEDIA;
	    		break;
	    	case "Accion": 
	    		g = Genero.ACCION;
	    		break;
	    	case "Romance": 
	    		g = Genero.ROMANCE;
	    		break;
	    	case "Drama": 
	    		g = Genero.DRAMA;
	    		break;
	    	case "Thriller": 
	    		g = Genero.THRILLER;
	    		break;
	    	case "Suspense": 
	    		g = Genero.SUSPENSE;
	    		break;
	    	case "Aventuras": 
	    		g = Genero.AVENTURAS;
	    		break;
	    	case "Negro": 
	    		g = Genero.NEGRO;
	    		break;
	    	case "Belico": 
	    		g = Genero.BELICO;
	    		break;
	    	}
    	return g;
    }
}
