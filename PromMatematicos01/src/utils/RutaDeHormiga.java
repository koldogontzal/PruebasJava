package utils;

import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("serial")
public class RutaDeHormiga extends ArrayList<Vertice> {
	
	public RutaDeHormiga() {
		super();
	}
	
	public RutaDeHormiga(Vertice origen, RutaDeHormiga aux) {
		super();
		super.add(origen);
		if (aux != null) {
			Iterator<Vertice> i = aux.iterator();
			while (i.hasNext()) {
				Vertice v = i.next();
				super.add(v);
			}
		}
	}
	
	@Override
	public String toString() {
		String ret = "";
		Iterator<Vertice> i = super.iterator();
		while (i.hasNext()) {
			Vertice v = i.next();
			ret = ret + v + " > ";
		}
		return ret;
	}
	
	public boolean isVenenosa() {
		boolean venenosa = false;
		Iterator<Vertice> i = super.iterator();
		while ((i.hasNext()) && !venenosa) {
			Vertice v = i.next();
			venenosa = v.getVenenoso();
		}
		return venenosa;
		
	}
	
	public Vertice getVerticeVenenoso() {
		Vertice ret = null;
		Iterator<Vertice> i = super.iterator();
		while ((i.hasNext()) && (ret == null)) {
			Vertice v = i.next();
			if ( v.getVenenoso()) {
				ret = v;
			}
		}
		return ret;
	}
}
