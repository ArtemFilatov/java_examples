package modelo.transfer.Tienda;

import modelo.transfer.TransferObject;

/**
 * 
 * @author Ines Heras
 *
 */

public class TransferProveedor extends TransferObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nif;
	
	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public TransferProveedor(String nif){
		this.nif=nif;
	}
	
	

}
