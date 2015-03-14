package museo;

public class Museo {
	
	private int capacidad;
	private int numVisitantes;
	
	private boolean abierto;
	
	public Museo(int cap) {
		this.capacidad = cap;
		this.numVisitantes = 0;
		this.abierto = false;
	}
	
	synchronized public boolean entrar(int i) {
		
		if (this.lleno()) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		boolean conseguido = false;
		if (this.abierto) {
			Pantalla.println("Entra el visitante " + i);
			this.numVisitantes++;
			conseguido = true;
		}
		return conseguido;
	}
	
	synchronized public void salir(int i) {
		Pantalla.println("Sale el visitante " + i);
		this.numVisitantes--;
		super.notify();
	}

	private boolean lleno() {
		return (this.capacidad == this.numVisitantes);
	}

	synchronized public void cierra() {
		Pantalla.println("El guarda cierra el museo");
		this.abierto = false;
	}

	synchronized public void abre() {
		Pantalla.println("El guarda abre el museo");
		this.abierto = true;
	}
	

}
