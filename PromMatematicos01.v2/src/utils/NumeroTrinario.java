package utils;

public class NumeroTrinario {
	private long numero;
	private int[] digitos;
	
	public NumeroTrinario(long num) {
		num = Math.max(0, num);
		this.numero = num;
		
		int posiciones;
		if (num > 0) {
			posiciones = 2 + (int) (Math.log(num) / Math.log(3));
		} else {
			posiciones = 1;
		}
		

		this.digitos = new int[posiciones];
		this.descomposicionRecursiva(num, 0);
	}
	
	public NumeroTrinario(String arg) {
		this(Integer.parseInt(arg, 3));
	}
	
	private void descomposicionRecursiva(long val, int pos) {
		if (val != 0) {
			this.digitos[pos] = (int)(val % 3);
			this.descomposicionRecursiva(val / 3, pos + 1);
		}
	}

	@Override
	public String toString() {
		return Long.toString(this.numero, 3);
	}
	
	public long getValueOf() {
		return this.numero;
	}
	
	public int getPos(int pos) {
		if ((pos >= 0) && (pos < this.digitos.length)) {
			return this.digitos[pos];
		} else {
			return 0;
		}
	}
	
}
