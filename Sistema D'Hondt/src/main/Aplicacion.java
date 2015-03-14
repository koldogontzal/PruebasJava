package main;

import clases.ListaPartidos;
import clases.ParlamentoDHondt;
import clases.ParlamentoProporcional;
import clases.Partido;

public class Aplicacion {
	
	private ListaPartidos listaPartidos;
	
	public static int NUM_ESCAGNOS = 350;
	
	public Aplicacion() {
		this.listaPartidos = new ListaPartidos(30);
	}
	
	public void iniciarResultadosVotos() {
		this.listaPartidos.agnadirPartido(new Partido("P.S.O.E.", 11064524L));
		this.listaPartidos.agnadirPartido(new Partido("P.P.", 10169973L));
		this.listaPartidos.agnadirPartido(new Partido("CiU", 774317L));
		this.listaPartidos.agnadirPartido(new Partido("EAJ-PNV", 303246L));
		this.listaPartidos.agnadirPartido(new Partido("ERC", 296473L));
		this.listaPartidos.agnadirPartido(new Partido("I.U.", 963040L));
		this.listaPartidos.agnadirPartido(new Partido("B.N.G", 209042L));
		this.listaPartidos.agnadirPartido(new Partido("CC-PNC", 164255L));
		this.listaPartidos.agnadirPartido(new Partido("UPyD", 303535L));
		this.listaPartidos.agnadirPartido(new Partido("NaBai", 62073L));
		this.listaPartidos.agnadirPartido(new Partido("CA", 68344L));
		this.listaPartidos.agnadirPartido(new Partido("EA", 50121L));
		this.listaPartidos.agnadirPartido(new Partido("C's", 45750L));
	}
	
	public void imprimirLista() {
		System.out.println(this.listaPartidos);
	}
	public static void main(String[] args) {
		Aplicacion app = new Aplicacion();
		app.iniciarResultadosVotos();
		
		for (int i = 1; i < 22; i ++) {
			double d = (double)i / 10.0;
			int j = (int)(d + 0.5);
			System.out.println(d + " " + j);
		}
		
		
		app.calcularParlamentoDHondt();		
		System.out.println("Partidos políticos según el Parlamento resultante de aplicar el sistema D'Hondt sin circunscripciones:");
		app.imprimirLista();		

		System.out.println();
		
		app.calcularParlamentoProporcional();
		System.out.println("Partidos políticos según el Parlamento resultante de aplicar el sistema proporcional puro:");
		app.imprimirLista();	
	}

	private void calcularParlamentoProporcional() {
		new ParlamentoProporcional(this.listaPartidos, NUM_ESCAGNOS);
		
	}

	private void calcularParlamentoDHondt() {
		new ParlamentoDHondt(this.listaPartidos, NUM_ESCAGNOS);		
	}

}
