package datos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Juego;

public class RepositorioJuegosImpl implements RepositorioJuegos {

	
	//comentario para probar que hay cambios en mainArnau
	
	private final List<Juego> listado = new ArrayList<Juego>();

	@Override
	public void cargarDatosCSV() {

		try (Scanner scanner = new Scanner(new File("res/juegos.csv"))) {
			scanner.nextLine();
			while (scanner.hasNextLine())
				listado.add(leerJuegoString(scanner.nextLine()));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage() + "res/juegos.csv not found");
		}
	}

	private Juego leerJuegoString(String linea) {

		String[] datosJuego = linea.split(",");

		Juego juego = new Juego();

		juego.setNombre(datosJuego[1]);
		juego.setPlataforma(datosJuego[2]);
		juego.setFechaPublicacion(datosJuego[3]);
		juego.setGenero(datosJuego[4]);
		juego.setEditor(datosJuego[5]);

		return juego;
	}

}
