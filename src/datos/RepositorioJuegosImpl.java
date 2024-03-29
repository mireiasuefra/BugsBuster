package datos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import model.Genre;
import model.Juego;
import model.Platform;

public class RepositorioJuegosImpl implements RepositorioJuegos {

	// comentario para probar que hay cambios en mainArnau

	private List<Juego> listado = new ArrayList<Juego>();

//	private int longitud

	@Override
	public int cargarDatosCSV() {

		int longCSV = 0;
        try (Scanner scanner = new Scanner(new File("res/juegos.csv"))) {
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				listado.add(leerJuegoString(scanner.nextLine()));
				++longCSV;
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage() + "res/juegos.csv not found");
		}
		return longCSV;

	}

	private Juego leerJuegoString(String linea) {
		Juego juego = new Juego();
		int fecha = 0;
		
		if (linea.startsWith("\"")) {
			//Primero separamos el nombre
			String[] datos = linea.split("\"\"");
			String[] datosNombre = datos[1].split(",");
			String nombre = "";
			for(int i = 0; i < datosNombre.length; i++) {
				nombre += datosNombre[i];
			}
			juego.setNombre(nombre);
			
			//Resto de datos
			String[] restoDatos = datos[2].split(",");
			juego.setPlataforma(Platform.fromString(restoDatos[1]));
			try {
				fecha = Integer.parseInt(restoDatos[2]);
			} catch (NumberFormatException e) {
				e.getMessage();
			} finally {
				juego.setFechaPublicacion(fecha);
			}
			
			juego.setGenero(Genre.fromString(restoDatos[3]));
			juego.setEditor(restoDatos[4]);
		} else {
			String[] datosJuego = linea.split(",");
			juego.setNombre(datosJuego[1]);
			juego.setPlataforma(Platform.fromString(datosJuego[2]));
			try {
				fecha = Integer.parseInt(datosJuego[3]);
			} catch (NumberFormatException e) {
				e.getMessage();
			} finally {
				juego.setFechaPublicacion(fecha);
			}

			juego.setGenero(Genre.fromString(datosJuego[4]));
			juego.setEditor(datosJuego[5]);
		}
		
		return juego;
	}

	@Override
	public List<Juego> listadoJuegos() {
		return listado;
	}
	
	@Override
	public void darDeAlta(Juego juego) {
		listado.add(juego);
	}
	
	@Override
	public List<Juego> listadoPorGenero(Genre genero) {
		List<Juego> listadoFiltrado = new ArrayList<Juego>();
		for(Juego j: listado) {
			if(j.getGenero().equals(genero)) 
				listadoFiltrado.add(j);	
		}
		return listadoFiltrado;
	}
	
	@Override
	public List<Juego> listadoPorPlataforma(Platform plataforma) {
		List<Juego> listadoFiltrado = new ArrayList<Juego>();
		for(Juego j: listado) {
			if(j.getPlataforma().equals(plataforma)) 
				listadoFiltrado.add(j);	
		}
		return listadoFiltrado;
	}
}

