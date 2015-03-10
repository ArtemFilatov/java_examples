package modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import modelo.transfer.TransferFactura;
import modelo.transfer.users.TransferEntrenador;

public class MyTableModelFactura extends AbstractTableModel
{
	private ArrayList<TransferFactura> facturas;
	private final String tableHeaders[] = {"Concepto", "Importe"};
	
	/**
	 * Constructor sin parametros que inicializa el arraylist a vacio.
	 */
	public MyTableModelFactura()
	{
		this.facturas = new ArrayList<TransferFactura>();
	}
	
	/**
	 * Constructor que recibe un arraylist.
	 * @param actividades - actividades de nuestro horario.
	 */
	public MyTableModelFactura(ArrayList<TransferFactura> facturas)
	{
		this.facturas = facturas;
	}
	
	/**
	 * Setter del atributo facturas.
	 * @param facturas
	 */
	public void setFacturas(ArrayList<TransferFactura> facturas)
	{
		this.facturas = facturas;
	}
	
	/**
	 * Setter de la tabla entera para actualizarla.
	 */
	public void setTableFacturas(ArrayList<TransferFactura> facturas)
	{
		this.setFacturas(facturas);
		this.fireTableDataChanged();
	}
	
	/**
	 * Devuelve el numero de columnas.
	 */
	public int getColumnCount() 
	{
		return 2;
	}

	/**
	 * Devuelve el numero de actividades que tenemos en nuestro ArrayList.
	 */
	public int getRowCount() 
	{
		return this.facturas.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) 
	{
		if(arg1 == 0)
			return this.facturas.get(arg0).getDsFactura();
		else
			return this.facturas.get(arg0).getImporteToString();
		
	}
	
	/**
	 * Devuelve el nombre de las columnas de la tabla.
	 */
	public String getColumnName(int i)
	{
		return this.tableHeaders[i];
	}

}
