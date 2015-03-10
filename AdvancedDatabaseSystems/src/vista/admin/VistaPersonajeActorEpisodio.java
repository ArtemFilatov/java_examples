package vista.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelo.Serie.Actor;
import modelo.Serie.ActorSeriePersonaje;
import modelo.Serie.Episodio;
import modelo.Serie.MyTableModelActor;
import modelo.Serie.MyTableModelActorSeriePersonaje;
import modelo.Serie.MyTableModelPersonaje;
import modelo.Serie.Personaje;
import controlador.admin.ControlAdminActores;
import controlador.admin.ControlAdminPersonajes;

public class VistaPersonajeActorEpisodio {

	private ControlAdminActores controlActores;
	private ControlAdminPersonajes controlPersonajes;
	private Episodio episodio;
	
	private JFrame frmRelaciones;
	
	private JButton btnRelacionar;
	private JButton btnCerrar;
	private JButton btnBorrar;
	
	private MyTableModelActor myTableModelActor;
	private MyTableModelPersonaje myTableModelPersonaje;
	private MyTableModelActorSeriePersonaje myTableModelUniones;
	private JTable tablaActores;
	private JTable tablaPersonajes;
	private JTable tablaUniones;
	
	
	
	
	
	public VistaPersonajeActorEpisodio(ControlAdminActores propagar1,
			ControlAdminPersonajes propagar2, Episodio episodio) {
		this.controlActores = propagar1;
		this.controlPersonajes = propagar2;
		this.episodio = episodio;
		
		initialize();
	}


	private void initialize() {
		frmRelaciones = new JFrame();
		frmRelaciones.setTitle("Participantes en el nº " + episodio.getIdEpisodio());
		frmRelaciones.setBounds(100, 100, 500, 400);
		frmRelaciones.getContentPane().setLayout(null);
		
		JScrollPane scroll1 = new JScrollPane();
		scroll1.setBounds(10, 11, 252, 163);
		frmRelaciones.getContentPane().add(scroll1);
		//tabla actor
		myTableModelActor = new MyTableModelActor(this.getActores());
		tablaActores = new JTable();
		tablaActores.setModel(myTableModelActor);
		scroll1.setViewportView(tablaActores);
		
		
		JScrollPane scroll2 = new JScrollPane();
		scroll2.setBounds(10, 198, 252, 163);
		frmRelaciones.getContentPane().add(scroll2);
		//tabla personajes
		myTableModelPersonaje = new MyTableModelPersonaje(this.getPersonajes());
		tablaPersonajes = new JTable();
		tablaPersonajes.setModel(myTableModelPersonaje);
		scroll2.setViewportView(tablaPersonajes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(272, 11, 212, 163);
		frmRelaciones.getContentPane().add(scrollPane);
		
		myTableModelUniones = new MyTableModelActorSeriePersonaje(this.getUniones());
		tablaUniones = new JTable();
		tablaUniones.setModel(myTableModelUniones);
		scrollPane.setViewportView(tablaUniones);
		
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(330, 300, 103, 33);
		btnCerrar.addActionListener(new OyenteBoton());
		frmRelaciones.getContentPane().add(btnCerrar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(330, 255, 103, 33);
		btnBorrar.addActionListener(new OyenteBoton());
		frmRelaciones.getContentPane().add(btnBorrar);
		
		btnRelacionar = new JButton("Relacionar");
		btnRelacionar.setBounds(330, 211, 103, 33);
		btnRelacionar.addActionListener(new OyenteBoton());
		frmRelaciones.getContentPane().add(btnRelacionar);
		
		frmRelaciones.setVisible(true);
		frmRelaciones.setResizable(false);
	}

	private ArrayList<Actor> getActores() {
		return this.controlActores.getActores();
	}
	
	
	private ArrayList<Personaje> getPersonajes() {
		return this.controlPersonajes.getPersonajes();
	}
	
	private ArrayList<ActorSeriePersonaje> getUniones() {
		return this.controlPersonajes.getActorSeriePersonajeEpisodio(episodio);
	}
	

	public void eliminarGrafico(){
		this.frmRelaciones.removeAll();
		this.frmRelaciones.setVisible(false);
		
	}
	
	public Actor getSelectedActor() {
		if (this.tablaActores.getRowCount() == 0) {
			return null;
		} else{
			return myTableModelActor.getActorElegido(this.tablaActores.getSelectedRow());
		}
	}
	
	public Personaje getSelectedPersonaje() {
		if (this.tablaPersonajes.getRowCount() == 0) {
			return null;
		} else{
			return myTableModelPersonaje.getPersonajeElegido(this.tablaPersonajes.getSelectedRow());
		}
		
	}
	
	public ActorSeriePersonaje getSelectedUnion() {
		if (this.tablaUniones.getRowCount() == 0) {
			return null;
		} else{
			return myTableModelUniones.getUnionElegida(this.tablaUniones.getSelectedRow());
		}
		
	}
	/**
	 * true si existen
	 * @param act
	 * @param per
	 * @param arrayList 
	 * @return
	 */
	private boolean actorOPersonajeEnUso(Actor act, Personaje per, ArrayList<ActorSeriePersonaje> arrayList) {
		boolean devolver = false;
		String falloActor = act.getNif();
		String falloPersonaje = per.getNombre();
		ActorSeriePersonaje comprobar;
		
		Iterator<ActorSeriePersonaje> it = arrayList.listIterator();
		while(it.hasNext()){
			comprobar = it.next();
			if (comprobar.getNif().equalsIgnoreCase(falloActor) && comprobar.getNombrePersonaje().equalsIgnoreCase(falloPersonaje)){	
				devolver = true;
			}
		}
		
		return devolver;
	}
	
	
	private class OyenteBoton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnRelacionar){
				Actor act = getSelectedActor();
				Personaje per = getSelectedPersonaje();
				
				if(act != null && per != null){
					if(actorOPersonajeEnUso(act, per, getUniones())){
						JOptionPane.showMessageDialog(null, "El actor o el personaje ya esta incluido en este capitulo");
					}else{
						controlActores.nuevaRelacion(act, per, episodio);
						myTableModelUniones.setActorSeriePersonaje(getUniones());
						myTableModelUniones.actualiza();
					}
				}else{
					JOptionPane.showMessageDialog(null, "alguna de las tablas de la izquierda no tiene elementos seleccionados");
				}
			}else if(e.getSource() == btnCerrar){
				eliminarGrafico();
				
			} else if(e.getSource() == btnBorrar){
				ActorSeriePersonaje borrar = getSelectedUnion();
				if(borrar != null){
					controlActores.eliminaRelacion(borrar);
					myTableModelUniones.setActorSeriePersonaje(getUniones());
					myTableModelUniones.actualiza();
				}else{
					JOptionPane.showMessageDialog(null, "no ha elegido ningun elemento de la tabla de arriba para borrar");
				}
				
			} 
		}

		
	}
}
