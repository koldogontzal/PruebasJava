package mias;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class VentanaConJLabelDeFormaDefinida {
	private JFrame ventana;
	private JPanel panel;
	private JTextField etiqueta;
	
	public VentanaConJLabelDeFormaDefinida () {
		// La ventana
		this.ventana = new JFrame("Ventana de pruebas");
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.ventana.setSize(200, 200);
		this.ventana.setResizable(true);
		
		// El panel
		this.panel = new JPanel();
		
		// La etiqueta
		this.etiqueta = new JTextField(1);
		Dimension tamagno = new Dimension(100,100);
		this.etiqueta.setSize(tamagno);
		this.etiqueta.setMaximumSize(tamagno);
		this.etiqueta.setMinimumSize(tamagno);
		this.etiqueta.setPreferredSize(tamagno);
		Font f = new Font("Arial", Font.BOLD, 24);
		this.etiqueta.setFont(f);
		this.etiqueta.setBackground(new Color(255,255,0));
		this.etiqueta.setEditable(false);
		Border border = BorderFactory.createLineBorder(Color.black);;
		this.etiqueta.setBorder(border);
		this.etiqueta.setHorizontalAlignment(JTextField.CENTER);
		this.etiqueta.setText("2");
		Rectangle rec=this.etiqueta.getBounds();
		System.out.print(rec);
		
		// Asociar etiqueta el panel
		this.panel.add(this.etiqueta);
		
		// Asociar panel a la ventana
		this.ventana.setContentPane(this.panel);		
		
		// Hacer visible la ventana
		this.ventana.setVisible(true);
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		VentanaConJLabelDeFormaDefinida aplicacion = new VentanaConJLabelDeFormaDefinida();
	}
}
