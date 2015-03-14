/**
 * Curso Básico de desarrollo de Juegos en Java - Invaders
 * 
 * (c) 2004 Planetalia S.L. - Todos los derechos reservados. Prohibida su reproducción
 * 
 * http://www.planetalia.com
 * 
 */

package codigo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public abstract class ResourceCache {
	protected HashMap<String, Object> resources;
	
	public ResourceCache() {
	  resources = new HashMap<String, Object>();
	}
	
	protected Object loadResource(String name) {
		URL url=null;
		File f = new File(name);
		try {
			url = f.toURI().toURL();
		} catch (MalformedURLException e) {
			System.out.println("El nombre del archivo " + name + " no puede cargarse bien");
			e.printStackTrace();
		}
		
		return loadResource(url);
	}
	
	protected Object getResource(String name) {
		Object res = resources.get(name);
		if (res == null) {
			res = loadResource("res/"+name);
			resources.put(name,res);
		}
		return res;
	}
	
	protected abstract Object loadResource(URL url);

}
