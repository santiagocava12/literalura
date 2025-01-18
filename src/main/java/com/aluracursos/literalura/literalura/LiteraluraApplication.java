package com.aluracursos.literalura.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.aluracursos.literalura.literalura.servicio.LibroService;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibroService libroService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int opcion;

		do {
			System.out.println("Elija la opción a través de su número:");
			System.out.println("1- Buscar libro por título");
			System.out.println("2- Listar libros registrados");
			System.out.println("3- Listar autores registrados");
			System.out.println("4- Listar autores vivos en un determinado año");
			System.out.println("5- Listar libros por idioma");
			System.out.println("0- Salir");

			opcion = scanner.nextInt();
			scanner.nextLine(); // Consumir la nueva línea

			switch (opcion) {
				case 1:
					System.out.println("Ingrese el título del libro:");
					String titulo = scanner.nextLine();
					libroService.buscarLibroPorTitulo(titulo);
					break;
				case 2:
					libroService.listarLibrosRegistrados();
					break;
				case 3:
					libroService.listarAutoresRegistrados();
					break;
				case 4:
					System.out.println("Ingrese el año para listar autores vivos:");
					int anio = scanner.nextInt();
					libroService.listarAutoresVivosEnAnio(anio);
					break;
				case 5:
					System.out.println("Ingrese el idioma (es, en, fr, pt):");
					String idioma = scanner.nextLine();
					libroService.listarLibrosPorIdioma(idioma);
					break;
				case 0:
					System.out.println("Saliendo del sistema...");
					break;
				default:
					System.out.println("Opción no válida. Intente de nuevo.");
			}
		} while (opcion != 0);

		scanner.close();
	}
}
