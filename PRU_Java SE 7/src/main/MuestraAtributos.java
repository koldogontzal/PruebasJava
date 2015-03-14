package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryPermission;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MuestraAtributos {

	public static void main(String[] args) {
		
		String archivo = "C:\\Users\\Luis\\Desktop\\";
		
		File file = new File(archivo);
		Path path = file.toPath();
		
		System.out.println(archivo + "\n");
		
		// BasicFileAttributes
		try {
			System.out.println("BasicFileAttributes:");
			BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
			System.out.println("creationTime: " + attr.creationTime());
			System.out.println("lastAccessTime: " + attr.lastAccessTime());
			System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

			System.out.println("isDirectory: " + attr.isDirectory());
			System.out.println("isOther: " + attr.isOther());
			System.out.println("isRegularFile: " + attr.isRegularFile());
			System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
			System.out.println("size: " + attr.size());
		} catch (IOException e) {
			System.out.println("El archivo no soporta BasicFileAttributes");
		}

		System.out.println();

		// DosFileAttributes
		try {
			System.out.println("DosFileAttributes:");
		    DosFileAttributes attr = Files.readAttributes(path, DosFileAttributes.class);
		    System.out.println("isReadOnly is " + attr.isReadOnly());
		    System.out.println("isHidden is " + attr.isHidden());
		    System.out.println("isArchive is " + attr.isArchive());
		    System.out.println("isSystem is " + attr.isSystem());
		} catch (IOException x) {
		    System.err.println("El archivo no soporta DosFileAttributes");
		}
		
		System.out.println();

		// PosixFileAttributes
		try {
			System.out.println("PosixFileAttributes:");
			PosixFileAttributes attr = Files.readAttributes(path, PosixFileAttributes.class);
			System.out.format("%s %s %s%n", attr.owner().getName(), attr.group().getName(),
	                  PosixFilePermissions.toString(attr.permissions()));
		} catch (UnsupportedOperationException | IOException e) {
			System.out.println("El archivo no soporta PosixFileAttributes");
		}
		
		System.out.println();
		
		// AclFileAttributeView
		try {
			System.out.println("AclFileAttributeView:");
			AclFileAttributeView view = Files.getFileAttributeView(path, AclFileAttributeView.class);
			System.out.println("Nombre: " + view.name());
			List<AclEntry> acl = view.getAcl();
			Iterator<AclEntry> i = acl.iterator();
			while (i.hasNext()) {
				AclEntry elemento = i.next();
				System.out.println("Propietario: " + elemento.principal().getName() + "\tTipo: " + elemento.type().toString());
				Set<AclEntryPermission> permisos = elemento.permissions();
				Iterator<AclEntryPermission> j = permisos.iterator();
				while (j.hasNext()) {
					AclEntryPermission entrada = j.next();
					System.out.println("\tPermiso: "+ entrada.name());
				}
			}

			
		} catch (UnsupportedOperationException | IOException e) {
			System.out.println("El archivo no soporta AclFileAttributeView");
		}

		System.out.println();
	
	}
}
