package serializacion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Inicio {

	public static void main(String[] args) {
		exportar();
		importar();

	}

	public static void exportar() {
		FileOutputStream fos = null;
		ObjectOutputStream salida = null;

		Persona p1 = new Persona("12345678A", "Lucas González", 30);
		Persona p2 = new Persona("98765432B", "Anacleto Jiménez", 28);
		Persona p3 = new Persona("78234212Z", "María Zapata", 35);

		try {
			// Se crea el fichero
			fos = new FileOutputStream("personas.dat");
			salida = new ObjectOutputStream(fos);

			// Se escriben los objetos en el fichero
			salida.writeObject(p1);
			salida.writeObject(p2);
			salida.writeObject(p3);
		} catch (FileNotFoundException e) {
			System.out.println("1" + e.getMessage());
		} catch (IOException e) {
			System.out.println("2" + e.getMessage());
		} finally {
			try {
				if (fos != null)
					fos.close();
				if (salida != null)
					salida.close();
			} catch (IOException e) {
				System.out.println("3" + e.getMessage());
			}
		}
	}

	public static void importar() {
		FileInputStream fis = null;
		ObjectInputStream entrada = null;
		Persona p;

		try {
			fis = new FileInputStream("personas.dat");
			entrada = new ObjectInputStream(fis);
			p = (Persona) entrada.readObject(); // es necesario el casting
			System.out.println(p.getNif() + " " + p.getNombre() + " " + p.getEdad());
			p = (Persona) entrada.readObject();
			System.out.println(p.getNif() + " " + p.getNombre() + " " + p.getEdad());
			p = (Persona) entrada.readObject();
			System.out.println(p.getNif() + " " + p.getNombre() + " " + p.getEdad());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (entrada != null) {
					entrada.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
