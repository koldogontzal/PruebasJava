package museo;

public class Aplicacion {

	public static void main(String[] args) {
		Museo m = new Museo(10);
		
		final int numVisitantes = 100;
		Visitante[] visitantes = new Visitante[numVisitantes];
		for (int i = 0; i < numVisitantes; i++) {
			visitantes[i] = new Visitante(i + 1000);
			visitantes[i].asociaMuseo(m);
			visitantes[i].start();
		}
		
		Vigilante vigilante = new Vigilante();
		vigilante.asociaMuseo(m);
		vigilante.start();
	}
}
