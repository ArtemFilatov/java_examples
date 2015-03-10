package modelo.transfer;

import modelo.transfer.users.TransferUsuario;


/**
 * 
 * @author Ines Heras
 *
 */

public class TransferMensaje extends TransferObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TransferUsuario envia;
	private TransferUsuario recibe;
	private String texto;
	private int fecha;
	
	private TransferMensaje(TransferUsuario envia, TransferUsuario recibe, String texto, int fecha){
		this.envia=envia;
		this.recibe=recibe;
		this.texto=texto;
		this.fecha=fecha;
		
	}

	public TransferUsuario getEnvia() {
		return envia;
	}

	public void setEnvia(TransferUsuario envia) {
		this.envia = envia;
	}

	public TransferUsuario getRecibe() {
		return recibe;
	}

	public void setRecibe(TransferUsuario recibe) {
		this.recibe = recibe;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

}
