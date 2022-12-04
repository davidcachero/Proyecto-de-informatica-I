package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import models.Usuario;

public class ControlFicheroUsuario {
	File fichero;
	Scanner scLector;
	PrintWriter pwEscritor;

	
	Vector<Usuario> listaUsuarios;
	
	public ControlFicheroUsuario (String rutaArchivo) { /// Leo el archivo de usuarios
		fichero= new File(rutaArchivo);
		
		try {
			scLector= new Scanner(new FileReader(fichero));
			scLector.useDelimiter(";");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public Vector<Usuario> leerUsuario(){
		
		listaUsuarios =new Vector<Usuario>();
		
		while(scLector.hasNextLine()) { ///Recojo los usuarios en un vector
			

			String id = scLector.next();
			float saldo = Float.parseFloat(scLector.next());
			Usuario user =new Usuario(id, saldo); ////Si esta definido?
			listaUsuarios.add(user);
			
			scLector.nextLine();
		}
		return listaUsuarios;
		}
		
}
	
	
	

