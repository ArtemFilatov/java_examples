package modelo.DBMapper;

public enum Operator {
	// MYSQL OPERATORS: https://dev.mysql.com/doc/refman/5.0/en/non-typed-operators.html
	// Cogemos algunos
    AND ("AND"),
    OR ("OR"),
    ASIGN_VALUE (":="),
    EQUAL ("="),
    GREATER_THAN (">="),
    GREATER (">"),
    LESS_THAN ("<="),
    LESS ("<"),
    IS_NOT_NULL ("IS NOT NULL"),
    IS_NULL ("IS NULL"),
    LIKE ("LIKE");
    

    private final String value;       

    private Operator(String s) {
        value = s;
    }

    public String toString(){
       return value;
    }


}
