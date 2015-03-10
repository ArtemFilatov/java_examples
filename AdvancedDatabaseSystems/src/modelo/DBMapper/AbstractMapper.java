package modelo.DBMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import modelo.Usuario.UsuarioSerie;

import org.apache.commons.lang3.StringUtils;

import com.mchange.lang.ArrayUtils;


/**
 * @title AbstractMapper
 * @author Jesus
 * @description main class
 */
public abstract class AbstractMapper<T,K> {

    protected DataSource ds;	/* DataSource reference */

    /**
     * Función para obtener el nombre de la tabla sobre la que ejecutar
     * statements.
     * @return String - name
     */
    protected abstract String getTableName();

    /**
     * Funcion para obtener las columnas relacionadas a la entidad T
     * y a la tabla t para poder ejecutar statements.
     * @return String[] - columns
     */
    protected abstract String[] getColumnNames();

    /**
     * Construye un objeto de tipo T a partir del resultado de un
     * statement.
     * @param rs - ResultSet
     * @return T 
     * @throws SQLException
     */
    protected abstract T buildObject(ResultSet rs) throws SQLException;

    /**
     * Obtiene la columna que sea clave primaria de la entidad T
     * @return String - columnKey
     */
    protected abstract String[] getKeyColumnNames();
    
    /**
     * Metodo para la serializacion de las claves de la entidad T
     * EJ Usuario -> NICK
     * @param key
     * @return
     */
	protected abstract Object[] serializeKey(K key);
	
	/**
	 * Devuelve la clave de un objeto
	 * @param objeto
	 * @return
	 */
	protected abstract K getKeyFromObject(T objeto);
	
	/**
	 * Serializa un objeto.
	 * @param objeto
	 * @return Object[]
	 */
	protected abstract Object[] serializeObject(T objeto);
	
	/**
	 * Serializa un objeto eliminando la informacion relacionada con la columna autoincrementable
	 * @param objeto
	 * @return
	 */
	protected abstract Object[] serializeObjectForIncremental(T objeto);
	
	protected abstract String getIncrementalColumnName();
    /**
     * Constructor por defecto.
     * @param ds
     */
    public AbstractMapper(DataSource ds) {
    	this.ds = ds;
    }

    /**
     * Busca un objeto de tipo T en la tabla t a partir de su
     * key primary.
     * @param id - K 
     * @return T
     */
    public T findById(K id) {
    	Connection con        = null;
        PreparedStatement pst = null;
        ResultSet rs          = null;
        T result       = null;
        try {
            con = ds.getConnection();
            String[] columnNames = getColumnNames();
            String columnNamesWithCommas = StringUtils.join(columnNames, ", ");
            String[] keyColumnNames =getKeyColumnNames();
            String[] conditions = new String[keyColumnNames.length];
            String tableName = getTableName();
            for(int i = 0; i < conditions.length;i++)
                conditions[i] = keyColumnNames[i] + "= ?";
            pst = con.prepareStatement(
                    "SELECT " + columnNamesWithCommas + " FROM " + tableName +  
                    " WHERE " + StringUtils.join(conditions, " AND ")
                    );
           
            Object[] keyComponents = serializeKey(id);
            for(int i = 0; i < keyColumnNames.length; i++)
                pst.setObject(i +1, keyComponents[i]);
            rs = pst.executeQuery();
            if (rs.next()) {
                result = buildObject(rs);
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception e) {}
        }
        return result;
    }
    
    /**
     * Devuelve todos los elementos que se encuentran en la tabla. CUIDADO
     * @return
     */
    public List<T> findAll() {
    	Connection con        = null;
		PreparedStatement pst = null;
		ResultSet rs          = null;
		List<T> result  = new ArrayList<T>();
		
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("SELECT * FROM " + getTableName());
			rs = pst.executeQuery();
			while(rs.next())
				result.add(buildObject(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
		return result;
    }
 
    /**
     * Busca un objeto de tipo T en su tabla t a partir de las condiciones recibidas.
     * @param conditions
     * @return
     */
    public List<T> findByConditions(QueryCondition[] conditions) {
		Connection con        = null;
		PreparedStatement pst = null;
		ResultSet rs          = null;
		List<T> result  = new ArrayList<T>();
		try {
			con = ds.getConnection();
			String[] columnNames = getColumnNames();
			String columnNamesWithCommas = StringUtils.join(columnNames, ", ");
			String[] condiciones = getWhereCondition(conditions);
			pst = con.prepareStatement(
					"SELECT " + columnNamesWithCommas + " FROM " + getTableName() +  
					" WHERE " + StringUtils.join(condiciones, " AND ")
					);
			
			//Object[] keyComponents = serializeKey(id);
			for(int i = 0; i < conditions.length; i++)
				pst.setObject(i +1, conditions[i].getValue());
			
			rs = pst.executeQuery();
			while(rs.next())
				result.add(buildObject(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
		return result;
	}
    
    /**
     * Construye la clausula where de un statement a partir de las condiciones especificadas.
     * @param conditions - QueryCondition[]
     * @return conditionsStr - String[]
     */
	private String[] getWhereCondition(QueryCondition[] conditions) {
		String[] conditionsStr = new String[conditions.length];
		for(int i = 0; i < conditionsStr.length; i++)
			conditionsStr[i] = conditions[i].getColumnName() + " " + conditions[i].getOperator().toString() +" ?";
		return conditionsStr;
	}
	
	/**
	 * Construye las condiciones para la clave
	 * @param key
	 * @return
	 */
	private QueryCondition[] getConditionsFromKey(K key) {
		String[] keyColumNames = getKeyColumnNames();
		QueryCondition[] conditions = new QueryCondition [getKeyColumnNames().length];
		Object[] columnValues = serializeKey(key);
		for(int i=0; i< conditions.length; i++) {
			conditions[i] = new QueryCondition(keyColumNames[i], Operator.EQUAL , columnValues[i]);
		}
		return conditions;
	}

	/**
	 * Metodo para realizar el statement update
	 * @param objeto - T objeto a actualizar
	 */
	public void update(T objeto) {
		Connection con        = null;
		PreparedStatement pst = null;
		try {
			con = ds.getConnection();
			String[] columnNames = getColumnNames();
			String[] assignments = new String[columnNames.length];
			for(int i = 0; i < assignments.length;i++)
				assignments[i] = columnNames[i] + " = ? ";
			QueryCondition[] conditions = getConditionsFromKey(getKeyFromObject(objeto));
			String[] whereCondition = getWhereCondition(conditions);
			
			String sql = "UPDATE " + getTableName() + " SET " + StringUtils.join(assignments, ", ")
					+ " WHERE " + StringUtils.join(whereCondition, " AND ");
			pst = con.prepareStatement(sql);
			Object[] objectsFields = serializeObject(objeto);
			int j = 1;
			for(int i = 0; i < columnNames.length; i++) {
				pst.setObject(j, objectsFields[i]);
				j++;
			}
			for(int i = 0; i < getKeyColumnNames().length; i++) {
				pst.setObject(j, conditions[i].getValue());
				j++;
			}
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
	}

	/**
	 * Metodo para realizar el statement insert. 
	 * @param objeto - T Objeto a insertar
	 */
	public void insert(T objeto) {
		Connection con        = null;
		PreparedStatement pst = null;
		try {
			con = ds.getConnection();
			String incrementalColumnName = getIncrementalColumnName();
			String[] columnNames = getColumnNames();
			
			if(incrementalColumnName != null) {
				ArrayList<String> array = new ArrayList<String>(Arrays.asList(columnNames));
				array.remove(incrementalColumnName);
				String[] newColumns = array.toArray(new String[array.size()]);
				String newColumnsWithCommas = StringUtils.join(newColumns, ", ");
				String[] unknownValues = new String[newColumns.length];
				for(int i = 0; i < newColumns.length; i++)
					unknownValues[i] = "?";
				String unknownValuesWithComas = StringUtils.join(unknownValues,", ");
				pst = con.prepareStatement("INSERT INTO " + getTableName() + " (" 
						+ newColumnsWithCommas + ") VALUES (" + unknownValuesWithComas +")");
				Object[] knownValues = serializeObjectForIncremental(objeto);
				for(int i = 0; i < newColumns.length; i++)
					 pst.setObject(i+1, knownValues[i]);
			}
			else {
				
				String columnNamesWithCommas = StringUtils.join(columnNames, ", ");
				String[] unknownValues = new String[columnNames.length];
				for(int i = 0; i < columnNames.length; i++)
					unknownValues[i] = "?";
				String unknownValuesWithComas = StringUtils.join(unknownValues,", ");
				pst = con.prepareStatement("INSERT INTO " + getTableName() + " (" 
						+ columnNamesWithCommas + ") VALUES (" + unknownValuesWithComas +")");
				Object[] knownValues = serializeObject(objeto);
				for(int i = 0; i < columnNames.length; i++)
					 pst.setObject(i+1, knownValues[i]);
			
			}
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
	}
	
	/**
	 * Metodo para realizar el statement delete
	 * @param objeto - T objeto a eliminar
	 */
	public void delete(T objeto) {
		Connection con = null;
		PreparedStatement pst= null;
		try{
			con = ds.getConnection();
			String[] columnNames = getColumnNames();
			String[] conditions = new String[columnNames.length];
			for(int i = 0; i < conditions.length;i++)
				conditions[i] = columnNames[i] + " = ? ";
			pst = con.prepareStatement(
			"DELETE FROM " + getTableName() + " WHERE " + StringUtils.join(conditions, " AND ")
			);
			Object[] objects = serializeObject(objeto);
			for(int i = 0; i < columnNames.length; i++) 
				pst.setObject(i+1, objects[i]);
			pst.execute(); 
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
		
	}


}