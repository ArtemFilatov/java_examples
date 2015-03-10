package modelo.transfer;

public class TransferFactura extends TransferObject 
{
	
	private int cdFactura;
	private int idUsrFactura;
	private String dsFactura;
	private int importe;
	
	public TransferFactura(int idUsrFactura, String dsFactura, int importe)
	{
		this.idUsrFactura = idUsrFactura;
		this.dsFactura = dsFactura;
		this.importe = importe;
	}
	
	public TransferFactura(int cdFactura, int idUsrFactura, String dsFactura, int importe)
	{
		this.cdFactura = cdFactura;
		this.idUsrFactura = idUsrFactura;
		this.dsFactura = dsFactura;
		this.importe = importe;
	}

	public int getCdFactura() {
		return cdFactura;
	}

	public void setCdFactura(int cdFactura) {
		this.cdFactura = cdFactura;
	}

	public int getIdUsrFactura() {
		return idUsrFactura;
	}

	public void setIdUsrFactura(int idUsrFactura) {
		this.idUsrFactura = idUsrFactura;
	}

	public String getDsFactura() {
		return dsFactura;
	}

	public void setDsFactura(String dsFactura) {
		this.dsFactura = dsFactura;
	}

	public int getImporte() {
		return importe;
	}

	public void setImporte(int importe) {
		this.importe = importe;
	}
	
	public String getImporteToString()
	{
		return this.importe + " €";
	}
	
	
	
	

}
