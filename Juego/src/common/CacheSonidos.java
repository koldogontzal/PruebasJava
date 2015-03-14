/**
 * Curso Básico de desarrollo de Juegos en Java - Invaders
 * 
 * (c) 2004 Planetalia S.L. - Todos los derechos reservados. Prohibida su reproducción
 * 
 * http://www.planetalia.com
 * 
 */
package common;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class CacheSonidos extends CacheRecursos{
	
	protected Object cargarRecurso(URL url) {
		return Applet.newAudioClip(url);
	}

	public AudioClip getAudioClip(String nombre) {
		return (AudioClip) super.getRecurso(nombre);
	}

	public void playSound(final String nombre) {
		new Thread(new Runnable() {
			public void run() {
				getAudioClip(nombre).play();
			}
		}).start();
	}

	public void loopSound(final String nombre) {
		new Thread(new Runnable() {
			public void run() {
				getAudioClip(nombre).loop();
			}
		}).start();
	}

}
