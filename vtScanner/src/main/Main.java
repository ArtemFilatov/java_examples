package main;

import java.io.IOException;
import java.util.Set;


import controller.controller;

import model.Model;

import view.View;
import virustotalapi.ReportFileScan;
import virustotalapi.ReportScan;
import virustotalapi.VirusTotal;

/**
 * Main Class
 * @author Estado
 */

public class Main 
{
	public static void main(String[] args) throws IOException
	{
		Model model = new Model(); // Initialize model
		controller controller = new controller(model); // Initialize controller
		View view = new View(); // Initialize view
		controller.setVistaRef(view);
		view.fijarControlador(controller);
		view.arranca();  // Show view
		model.addObserver(view);
	}
}
