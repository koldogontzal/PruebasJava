package common;

import java.awt.image.ImageObserver;

public interface Escenario extends ImageObserver {

	public static final int ANCHO_MUNDO = 800;
	public static final int ALTO_MUNDO = 800;
	
	public static final int NUM_FILAS = 20;
	public static final int NUM_COLUMNAS = 20;
	public static final double PROBABILIDAD_CELDAS_ACTIVAS_INICIALES = 0.20512;	
	
	public CacheSprites getSpriteCache();

	public int getTiempoEspera();

	public void setTiempoEspera(int tiempoEspera);

}
