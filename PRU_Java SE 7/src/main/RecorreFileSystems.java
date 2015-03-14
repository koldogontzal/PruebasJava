package main;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.util.Iterator;

public class RecorreFileSystems {

	public static void main(String[] args) {

		FileSystem fsys = FileSystems.getDefault();
		Iterator<Path> i = fsys.getRootDirectories().iterator();
		while (i.hasNext()) {
			Path path = i.next();
			System.out.println("Path: " + path.toFile().getAbsolutePath());
			FileStore fs;
			try {
				fs = Files.getFileStore(path);
				System.out.println("Nombre: " + fs.name());
				System.out.println("Tipo: " + fs.type());
				System.out.println("Espacio total/libre: "
						+ (fs.getTotalSpace() / (1024 * 1024)) + " MB/"
						+ (fs.getUnallocatedSpace() / (1024 * 1024)) + " MB");
				System.out
						.println("Soporta DosFileAttributeView: "
								+ fs.supportsFileAttributeView(DosFileAttributeView.class));
				System.out
						.println("Soporta PosixFileAttributeView: "
								+ fs.supportsFileAttributeView(PosixFileAttributeView.class));
				System.out
						.println("Soporta AclFileAttributeView: "
								+ fs.supportsFileAttributeView(AclFileAttributeView.class));

			} catch (IOException e1) {
				// Error en la apertura del FileStore
				System.out.println("FileStore no disponible");

			} finally {
				System.out.println("=================\n");
			}
		}
	}
}
