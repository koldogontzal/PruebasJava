package interfaz;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRootPane;


public class Menu {
	private JFrame ventana;
	private JPanel contenedor;
		
	private JMenuBar barra;
	
	private JMenu menuArchivo;
	private JMenu menuEditar;
	private JMenu menuVer;
	private JMenu menuHerramientas;
	private JMenu menuAyuda;
	
	private JMenuItem menuItemArchivoNuevo = new JMenuItem("Nuevo");
	private JMenuItem menuItemArchivoAbrir = new JMenuItem("Abrir...");
	private JMenuItem menuItemArchivoGuardar = new JMenuItem("Guardar");
	private JMenuItem menuItemArchivoGuardarComo = new JMenuItem("Guardar como...");
	private JMenuItem menuItemArchivoSalir = new JMenuItem("Salir");
	private JMenuItem menuItemEditarUndo = new JMenuItem("Undo");
	private JMenuItem menuItemEditarRedo = new JMenuItem("Redo");
	private ButtonGroup grupoVer;
	private JRadioButtonMenuItem menuItemVerSoloResueltos = new JRadioButtonMenuItem("Sólo celdas resueltas");
	private JRadioButtonMenuItem menuItemVerNumeroValoresPosibles = new JRadioButtonMenuItem("Número de valores posibles");
	private JRadioButtonMenuItem menuItemVerPosibles1 = new JRadioButtonMenuItem("Celdas con valor posible 1");
	private JRadioButtonMenuItem menuItemVerPosibles2 = new JRadioButtonMenuItem("Celdas con valor posible 2");
	private JRadioButtonMenuItem menuItemVerPosibles3 = new JRadioButtonMenuItem("Celdas con valor posible 3");
	private JRadioButtonMenuItem menuItemVerPosibles4 = new JRadioButtonMenuItem("Celdas con valor posible 4");
	private JRadioButtonMenuItem menuItemVerPosibles5 = new JRadioButtonMenuItem("Celdas con valor posible 5");
	private JRadioButtonMenuItem menuItemVerPosibles6 = new JRadioButtonMenuItem("Celdas con valor posible 6");
	private JRadioButtonMenuItem menuItemVerPosibles7 = new JRadioButtonMenuItem("Celdas con valor posible 7");
	private JRadioButtonMenuItem menuItemVerPosibles8 = new JRadioButtonMenuItem("Celdas con valor posible 8");
	private JRadioButtonMenuItem menuItemVerPosibles9 = new JRadioButtonMenuItem("Celdas con valor posible 9");
	private JMenuItem menuItemHerramientasPista = new JMenuItem("Obtener una pista");
	private JMenuItem menuItemHerramientasResolver = new JMenuItem("Resolver");
	private JMenuItem menuItemAyudaAyuda = new JMenuItem("Ayuda");
	
	public Menu() {
		//		 Crea Ventana
		this.ventana = new JFrame("Sudoku");
		this.ventana.setSize(800, 600);
		this.ventana.setResizable(true);
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.ventana.setUndecorated(true);
		//this.ventana.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		
		// Crea Panel
		this.contenedor = new JPanel();
		
		// Crear Componentes
		//// Barra
		this.barra = new JMenuBar();
		//// Menu Archivo
		this.menuArchivo = new JMenu("Archivo");
		this.menuArchivo.add(this.menuItemArchivoNuevo);
		this.menuArchivo.add(this.menuItemArchivoAbrir);
		this.menuArchivo.add(this.menuItemArchivoGuardar);
		this.menuArchivo.add(this.menuItemArchivoGuardarComo);
		this.menuArchivo.addSeparator();
		this.menuArchivo.add(this.menuItemArchivoSalir);
		this.barra.add(this.menuArchivo);
		////	 Menu Editar
		this.menuEditar = new JMenu("Editar");
		this.menuEditar.add(this.menuItemEditarUndo);
		this.menuEditar.add(this.menuItemEditarRedo);
		this.barra.add(this.menuEditar);
		//// Menu Ver
			//// Primero crea el grupo
		this.menuItemVerSoloResueltos.setSelected(true);
		this.grupoVer = new ButtonGroup();		
		this.grupoVer.add(this.menuItemVerSoloResueltos);
		this.grupoVer.add(this.menuItemVerNumeroValoresPosibles);
		this.grupoVer.add(this.menuItemVerPosibles1);
		this.grupoVer.add(this.menuItemVerPosibles2);
		this.grupoVer.add(this.menuItemVerPosibles3);
		this.grupoVer.add(this.menuItemVerPosibles4);
		this.grupoVer.add(this.menuItemVerPosibles5);
		this.grupoVer.add(this.menuItemVerPosibles6);
		this.grupoVer.add(this.menuItemVerPosibles7);
		this.grupoVer.add(this.menuItemVerPosibles8);
		this.grupoVer.add(this.menuItemVerPosibles9);
			//// Ahora el menu
		this.menuVer = new JMenu("Ver");
		this.menuVer.add(this.menuItemVerSoloResueltos);
		this.menuVer.add(this.menuItemVerNumeroValoresPosibles);
		this.menuVer.addSeparator();
		this.menuVer.add(this.menuItemVerPosibles1);
		this.menuVer.add(this.menuItemVerPosibles2);
		this.menuVer.add(this.menuItemVerPosibles3);
		this.menuVer.add(this.menuItemVerPosibles4);
		this.menuVer.add(this.menuItemVerPosibles5);
		this.menuVer.add(this.menuItemVerPosibles6);
		this.menuVer.add(this.menuItemVerPosibles7);
		this.menuVer.add(this.menuItemVerPosibles8);
		this.menuVer.add(this.menuItemVerPosibles9);
		this.barra.add(this.menuVer);
		////	 Menu Herramientas
		this.menuHerramientas = new JMenu("Herramientas");
		this.menuHerramientas.add(this.menuItemHerramientasPista);
		this.menuHerramientas.add(this.menuItemHerramientasResolver);
		this.barra.add(this.menuHerramientas);
		////	 Menu Ayuda
		this.menuAyuda = new JMenu("Ayuda");
		this.menuAyuda.add(this.menuItemAyudaAyuda);
		this.barra.add(this.menuAyuda);
		
		
		// Asociar el menu a la ventana
		this.ventana.setJMenuBar(this.barra);
		
		// Asociar el contenedor a la ventana
		this.ventana.setContentPane(this.contenedor);
		
		// HAcer visible la ventana
		this.ventana.setVisible(true);
	}
	
	public static void main(String[] args) {
		Menu ventana = new Menu();
	}

}
