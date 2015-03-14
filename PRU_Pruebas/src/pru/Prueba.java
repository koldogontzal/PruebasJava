package pru;

public class Prueba {
	public static void main(String[] args) {
		System.out.println("0: " + ( 0 % 360));
		System.out.println("120: " + ( 120 % 360));
		System.out.println("300.45: " + ( 300.45 % 360));
		System.out.println("360: " + ( 360 % 360));
		System.out.println("-90: " + ( -90 % 360));
		System.out.println("-390.45: " + ( -390.45 % 360));
		
		for (double g = 0.0; g < 400.0; g = g +30.0) {
			System.out.println("Grados:" + g + "   resultado: " + (int)((g / 60) % 6));
		}
	}

}
