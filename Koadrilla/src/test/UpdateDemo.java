package test;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/*
 ***************************************************************
 * Sample program which demonstrates the difference between
 * always painting the entire area inside of paint() vs.
 * using update to do incremental painting.
 *
 * Incremental painting is useful if the component needs to
 * render incremental output on top of a complex background.
 ***************************************************************
 */
public class UpdateDemo {
	
    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
    	
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        Frame f1 = new Frame("Flash Clicker - Click mouse to draw vectors");
        f1.addWindowListener(l);
        f1.add(new ClickCanvas(new Vector()), BorderLayout.CENTER);
        f1.pack();
        f1.show();

        Frame f2 = new Frame("Smooth Clicker- Click mouse to draw vectors");
        f2.addWindowListener(l);
        f2.add(new SmoothClickCanvas(new Vector()), BorderLayout.CENTER);
        f2.pack();
        Rectangle f1Rect = f1.getBounds();
        f2.setLocation(f1Rect.x+f1Rect.width, f1Rect.y);
        f2.show();
    }
}

/**
 * A canvas which renders a semi-complex background layered
 * with vectors which are created as the user clicks in various
 * locations on the canvas.
 *
 * This class always paints the entire area inside of paint()
 * and does not do incremental drawing.
 */
class ClickCanvas extends Canvas {
    /**
	 * 
	 */
	
	private static final long serialVersionUID = -2044742883829940463L;
	protected Color colors[] = {Color.red, Color.yellow, Color.blue,
                                Color.green, Color.pink, Color.orange,
                                Color.white, Color.magenta, Color.cyan};
    protected Vector points;
    protected int vectorsPainted;

    public ClickCanvas(Vector points) {
        this.points = points;
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                ClickCanvas c = (ClickCanvas)e.getSource();
                c.points.addElement(e.getPoint());
                c.repaint();            
            }
        });
    }

    public Dimension getPreferredSize() {
        return new Dimension(400,400);
    }

/*
   Uncomment this method to see how overriding update() to
   NOT clear the background affects the flashing. Note that
   the flashing is less dramatic, but it still flashes some.

    public void update(Graphics g) {
        paint(g);
    }
*/

    public void paint(Graphics g) {
        // paint entire canvas -> backdrop and all vectors
        paintBackdrop(g);
        vectorsPainted = 0;
        for(int i = 0 ; i < points.size()-1; i++) {
            paintVector(g, i);
        }
    }

    protected void paintBackdrop(Graphics g) {
        Dimension size = getSize();
        int thickness = 5;
        //int x = 0;
        for (int y = 0; y + thickness <= size.height; y+=thickness) {
            g.setColor(colors[y % colors.length]);
            g.fillRect(0, y, size.width, thickness);
        }
    }

    protected void paintVector(Graphics g, int start) {
        Point p1 = (Point)points.elementAt(start);
        Point p2 = (Point)points.elementAt(start+1);
        g.setColor(Color.black);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
        vectorsPainted++;
    }

}

/** 
 * An extension of ClickCanvas which uses update() to do
 * incremental painting of only the vectors which have been
 * created since the last time the canvas painted.
 */
class SmoothClickCanvas extends ClickCanvas {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6066459485497552598L;

	public SmoothClickCanvas(Vector points) {
        super(points);
    }

    public void update(Graphics g) {
        // only paint new vectors since last update call
        for (int i = vectorsPainted; i < points.size()-1; i++) {
            paintVector(g, i);
        }
    }
}

