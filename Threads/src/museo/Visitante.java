package museo;

public class Visitante extends Thread {
	
	private int id;
	private Museo museo;
	
	public Visitante(int id) {
		this.id = id;
	}
	
	public void asociaMuseo(Museo m) {
		this.museo = m;
	}
	
	@Override
	public void run() {
		super.run();
		
		while (true) {
			// Entra al museo
			while (!this.museo.entrar(this.id)) {
				try {
					// Si el museo esta cerrado, se da una vuelta un tiempo y vuelve a intentarlo
					Thread.sleep((int)(Math.random() * 2500));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// Está dentro un tiempo aleatorio
			try {
				Thread.sleep(2000 + (int)(Math.random() * 1500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Sale del museo
			this.museo.salir(this.id);
			// Espera un tiempo y vuelve a intentar entrar
			try {
				Thread.sleep(8000 + (int)(Math.random() * 4500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
