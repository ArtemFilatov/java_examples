package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.EventObject;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

import view.View;


import model.Model;

/**
 * Application controller.
 * @author Estado
 */

public class controller implements ActionListener, KeyListener
{
	private Model modelRef;
	private View vistaRef;
	private File file;
	private JFileChooser auxFileChooser;
	private String filePath;
	private String fileHash;
	
	/**
	 * Constructor con parametros.
	 * @param modelRef - Referencia al modelo.
	 */
	public controller(Model modelRef)
	{
		this.modelRef = modelRef;
		this.filePath = "";
		this.fileHash = "";
	}

	/**
	 * Metodo para setear una referencia de la vista.
	 * @param vistaRef - Referencia de la Vista.
	 */
	public void setVistaRef(View vistaRef)
	{
		this.vistaRef = vistaRef;
	}

	/**
	 * Realiza los cambios pertinentes en el modelo a partir de los eventos capturados.
	 * @param fuente - Componente que ha generado el evento.
	 */
	private void cambiarModelo(Component fuente) 
	{
		if(fuente.getName().equals("jButtonFind"))
		{
			this.auxFileChooser = this.vistaRef.getJFileInput();
			int returnVal = this.auxFileChooser.showOpenDialog(this.vistaRef);
			if (returnVal == this.auxFileChooser.APPROVE_OPTION) 
			{
             	this.file = this.auxFileChooser.getSelectedFile();
             	this.vistaRef.setTextFilePath(this.file.toString());
             	this.filePath = this.file.toString();
			}
		}
		else if(fuente.getName().equals(("jButtonUpload")))
		{
			this.vistaRef.setTextOutput("[+] Checking...");
			if(this.filePath == "")
				this.vistaRef.setTextOutput("[-] No file selected");
			else
			{
				try {
					
					this.vistaRef.setTextOutput("[+] Uploading...");
					this.modelRef.UploadAndReport(this.filePath);
					this.fileHash = this.modelRef.getFileHash();
					this.vistaRef.setTextOutput("[+] File uploaded succesfuly");
					this.vistaRef.setTextOutput("[+] File URL : " + this.modelRef.getFileUrl());
					this.vistaRef.setTextOutput("[+] Scan id (SHA256): " + fileHash); 
					this.vistaRef.setTextOutput("[+] Wait a few seconds before scanning");
					this.modelRef.reportScan(fileHash);
				} catch (IOException e) {
					this.vistaRef.setTextOutput("[-] Unexpected error.");
					e.printStackTrace();
				}
			}
		}
		else if(fuente.getName().equals("jButtonScan"))
		{
			this.vistaRef.setTextOutput("[+] Scanning...");
			if(this.fileHash =="")
				this.vistaRef.setTextOutput("[-] No hash detected");
			else
			{
				try {
					this.modelRef.reportScan(this.fileHash);
					this.vistaRef.setTextOutput(this.modelRef.getReportScan());
				} catch (IOException e) {
					this.vistaRef.setTextOutput("[-] Error getting report. Maybe wrong hash");
					//e.printStackTrace();
				}
			}
		}
		else if(fuente.getName().equals("jTextFilePath"))
		{
			JTextField campo=(JTextField)fuente;
            this.filePath = campo.getText();
		}
		else if(fuente.getName().equals("jTextFileHash"))
		{
			JTextField campo=(JTextField)fuente;
            this.fileHash = campo.getText();
		}
	}
	
	
	/**
	 * M�todo para tratar los eventos de forma gen�rica. 
	 * Se encarga tanto de solicitar la modificaci�n al modelo como de informar a la vista
	 * @param e el evento a tratar
	 */
	private void trataEventoGenerico(EventObject event){
        Component fuente = (Component) event.getSource(); // el que gener� el evento
		System.err.println(fuente.getName());
        cambiarModelo(fuente);
	}




	@Override
	public void actionPerformed(ActionEvent e) 
	{
		trataEventoGenerico(e);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		trataEventoGenerico(arg0);
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
