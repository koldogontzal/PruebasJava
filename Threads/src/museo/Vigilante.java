package museo;

public class Vigilante extends Thread {
	private Museo museo;
	
	public Vigilante() {
		super();
	}
	
	public void asociaMuseo(Museo m) {
		this.museo = m;
	}
	
	@Override
	public void run() {
		super.run();
		while (true) {
			// Abre el Museo
			this.museo.abre();		
			// Espera sus 12 horitas mientras el museo está abierto
			try {
				Thread.sleep(12000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Cierra el Museo
			this.museo.cierra();
			// Espera sus 12 horitas mientras el museo está cerrado
			try {
				Thread.sleep(12000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}

}
