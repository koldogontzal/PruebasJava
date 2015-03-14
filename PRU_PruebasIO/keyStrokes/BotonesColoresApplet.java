package keyStrokes;

//BotonesColoresApplet.java 
//EVENTOS JDK 1.0.x 

import java.awt.*;

public class BotonesColoresApplet extends java.applet.Applet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2155431434943712988L;

	TextArea areaTexto;

	Panel panel1;

	public void init(){
		setLayout(new BorderLayout());

		panel1 = new Panel();
		panel1.add(new Button("Rojo"));
		panel1.add(new Button("Azul"));
		panel1.add(new Button("Verde"));
		panel1.add(new Button("Amarillo"));

		areaTexto = new TextArea(15, 10);
		areaTexto.setEditable(false);

		add("North", panel1);
		add("South", areaTexto);
	}

	public boolean handleEvent(Event evt){
		switch (evt.id)

		{
		case Event.ACTION_EVENT:

			if (evt.target instanceof Button)

			{
				cambiaColor((String) evt.arg);
			}

			break;

		case Event.GOT_FOCUS:
			areaTexto.append("GOT_FOCUS \n");
			return true;

		case Event.LOST_FOCUS:

			areaTexto.append("LOST_FOCUS \n");
			return true;

		case Event.MOUSE_ENTER:

			areaTexto.append("MOUSE_ENTER, x=" + evt.x + " y=" + evt.y + "\n");
			return true;

		case Event.MOUSE_EXIT:

			areaTexto.append("MOUSE_EXIT, x=" + evt.x + " y=" + evt.y + "\n");
			return true;

		default:

			return false;

		}

		return true;
	}

	public void cambiaColor(String nombreColor){
		if (nombreColor.equals("Rojo"))
			areaTexto.setBackground(Color.red);
		else if (nombreColor.equals("Azul"))
			areaTexto.setBackground(Color.blue);
		else if (nombreColor.equals("Verde"))
			areaTexto.setBackground(Color.green);
		else if (nombreColor.equals("Amarillo"))
			areaTexto.setBackground(Color.yellow);
	}

}