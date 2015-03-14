package common;

import java.awt.image.ImageObserver;

public interface Escenario extends ImageObserver {
	public static final int ANCHO_VISTA = 800;
	public static final int ALTO_VISTA = 400;
	public static final int ANCHO_MUNDO = 2939;
	public static final int ALTO_MUNDO = 600;
	

	public void setPuntoDeVista(int x, int y);
	
	public CacheSprites getSpriteCache();
	//public SoundCache getSoundCache();
	//public void addActor(Actor a);
	//public Player getPlayer();
	//public void gameOver();

}
