
package PRANAYAMA;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class buttons extends JComponent implements MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 457;

    public int typeButton;
    public boolean buttonFocused = false;
    RoundRectangle2D exitbutton;
    RoundRectangle2D strelkaLeftButton;
    RoundRectangle2D strelkaRightButton;
    RoundRectangle2D itemMenu;
    public String nameMenu;
    private String nameCheckbox; // for type button = 5
    public Rectangle boundsMenuItem;
    boolean centerORnot;
    int xx, yy;

    boolean pressedState = false; // for type = 5

    public int arc = 10;

    buttons() {
        this.typeButton = 1;
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    buttons(int type) {
        this.typeButton = type;
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    buttons(int type, String nameCheckbox) {
        this.typeButton = type;
        this.nameCheckbox = nameCheckbox;
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    buttons(String str, Rectangle boundsMenuItem) {
        this.typeButton = 4;
        this.nameMenu = str;
        this.boundsMenuItem = boundsMenuItem;
        this.centerORnot = true;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    buttons(String str, Rectangle boundsMenuItem, boolean centerORnot, int xx, int yy) {
        this.typeButton = 4;
        this.nameMenu = str;
        this.boundsMenuItem = boundsMenuItem;
        this.centerORnot = centerORnot;
        this.xx = xx;
        this.yy = yy;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));

        if (this.typeButton == 1) {
            RoundRectangle2D extRect = new RoundRectangle2D.Double(); // for organization  'exit' button
            exitbutton = new RoundRectangle2D.Double(); // for organization  'exit' button
            RoundRectangle2D exitbuttonInternal = new RoundRectangle2D.Double();
            RoundRectangle2D exitbuttonInternal2 = new RoundRectangle2D.Double();
            Line2D line1 = new Line2D.Double();
            Line2D line2 = new Line2D.Double();
            Line2D line3 = new Line2D.Double();
            Line2D line4 = new Line2D.Double();

//        extRect = new java.awt.geom.RoundRectangle2D.Double(3.0D, // x =  1
//                3.0D, // y =  1 + (11*2)/3
//                25, // width
//                25, // height
//                arc, arc);
            exitbutton = new RoundRectangle2D.Double(1.0D, 1.0D, 24, 24, arc, arc);

            exitbuttonInternal = new RoundRectangle2D.Double(1, 1.0D, 22, 22, arc, arc);
            exitbuttonInternal2 = new RoundRectangle2D.Double(2, 2.0D, 21, 21, arc, arc);

            line1 = new Line2D.Double(5.0D, 5.0D, 19.0D, 19.0D);
            line3 = new Line2D.Double(5.0D, 6.0D, 18.0D, 19.0D);

            line2 = new Line2D.Double(5.0D, 19.0D, 19.0D, 5.0D);
            line4 = new Line2D.Double(5.0D, 18.0D, 18.0D, 5.0D);

            // **********************  Addition 'exit button' ****************************************************
            Area area = new Area(exitbutton);
          //  graphics2d.setColor(new Color(190, 220, 220));
            graphics2d.setColor(Color.yellow);
//        area.add(new Area(exitbutton));
//        graphics2d.fill(exitbutton);
            area.add(new Area(exitbuttonInternal));
            if (!buttonFocused) {
                graphics2d.setColor(Color.black);
            }
            graphics2d.draw(exitbuttonInternal);  // outline 'exit button'
            if (!buttonFocused) {
                graphics2d.setColor(Color.orange);
            } else {
                graphics2d.setColor(Color.red);
            }
            graphics2d.fill(exitbuttonInternal2); // background  'exit button'
            area.add(new Area(line1));
            area.add(new Area(line2));
            area.add(new Area(line3));
            area.add(new Area(line4));
            graphics2d.setColor(Color.yellow);
            graphics2d.draw(line1);
            graphics2d.draw(line2);
            graphics2d.draw(line3);
            graphics2d.draw(line4);
        }

        if (this.typeButton == 2) {

            strelkaLeftButton = new RoundRectangle2D.Double(1.0D, 1.0D, 24, 24, arc, arc);
            // **********************  Addition 'strelkaLeftButton' ****************************************************
            Area area = new Area(strelkaLeftButton);
           // graphics2d.setColor(new Color(190, 220, 220));
            graphics2d.setColor(Color.yellow);
            Area strelka = new Area(createStrelkaLeft(5, 5, 20, 20));
            area.add(strelka);
            if (!buttonFocused) {
                graphics2d.setColor(Color.orange);
            } else {
                graphics2d.setColor(Color.blue);
            }
            graphics2d.fill(strelka);
        }
        if (this.typeButton == 3) {

            strelkaRightButton = new RoundRectangle2D.Double(1.0D, 1.0D, 24, 24, arc, arc);
            // **********************  Addition 'strelkaRightButton' ****************************************************
            Area area = new Area(strelkaRightButton);
//            graphics2d.setColor(new Color(190, 220, 220));
            graphics2d.setColor(Color.yellow);
            Area strelka = new Area(createStrelkaRight(5, 5, 20, 20));
            area.add(strelka);
            if (!buttonFocused) {
                graphics2d.setColor(Color.orange);
            } else {
                graphics2d.setColor(Color.blue);
            }
            graphics2d.fill(strelka);
        }

        //  menuItem button
        if (this.typeButton == 4) {
            arc = 10;
            itemMenu = new RoundRectangle2D.Double(1.0D, 1.0D, 50, 24, arc, arc);
            // **********************  Addition 'itemMenu' ****************************************************
//            Area area = new Area(itemMenu);
//            graphics2d.setColor(new Color(190, 220, 220));
//            Area space = new Area(new RoundRectangle2D.Double(1.0D, 1.0D, 84, 24, arc, arc));
//            if (!buttonFocused) {
//                graphics2d.setColor(Color.orange);
//               // area.add(space);
//            } else {
//                graphics2d.setColor(Color.blue);
//               // area.subtract(space); 
//            }
//            graphics2d.fill(area);
//            graphics2d.setColor(Color.lightGray);
//            graphics2d.draw(space);
//            graphics2d.setColor(Color.black);

            graphics2d.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            Font font = new Font("Jokerman", Font.PLAIN, 20);
            graphics2d.setFont(font);

            if (!buttonFocused) {
                graphics2d.setColor(new Color(255,95,17)); // Color.orange new Color(247,145,190)
            } else {
                graphics2d.setColor(Color.blue);
            }

            FontMetrics fm = getFontMetrics(getFont());
            int width = fm.stringWidth(this.nameMenu);

          //  boundsMenuItem = new Rectangle(1, 1, 50, 24);
            if (centerORnot) {
                centerString(graphics2d, this.boundsMenuItem, this.nameMenu, font);
            } else {
                graphics2d.drawString(this.nameMenu, this.xx, this.yy);
            }
        }

        // checkbox 
        if (this.typeButton == 5) {
            FontRenderContext frc = graphics2d.getFontRenderContext();
            String s = "";
            GlyphVector gv = null;

            Ellipse2D helpfieldoutside = new Ellipse2D.Double(9, 18, 35, 30);// 9, 18
            Area outline = new Area(helpfieldoutside);

            graphics2d.setColor(new Color(145, 237, 255));// 58, 235, 255
            graphics2d.fill(outline);
            graphics2d.setStroke(new BasicStroke(0.5f));
            graphics2d.setColor(Color.GRAY);
            graphics2d.draw(outline);

            Ellipse2D helpfieldoutsideIN = new Ellipse2D.Double(11, 20, 31, 26); // 9, 18, 35, 30
            Area outlineIN = new Area(helpfieldoutsideIN);
            graphics2d.draw(outlineIN);

            if (!pressedState) { // "+" state of button
                if (!buttonFocused) {
                    graphics2d.setPaint(Color.green);
                } else {
                    graphics2d.setPaint(Color.yellow);
                }
                graphics2d.fill(outlineIN);
                
                RoundRectangle2D plusLine1 = new RoundRectangle2D.Double(17.0D, 30.0D, 19, 5, 4, 4);
                RoundRectangle2D plusLine2 = new RoundRectangle2D.Double(24.0D, 23.0D, 5, 19, 4, 4);
                Area areaPlus = new Area(plusLine1);
                areaPlus.add(new Area(plusLine2));

                graphics2d.setPaint(new Color(131, 142, 224));
                graphics2d.fill(areaPlus);
            } else { // "-" state of button
                if (!buttonFocused) {
                    graphics2d.setPaint(new Color(145, 237, 255));
                } else {
                    graphics2d.setPaint(Color.yellow);
                }
                graphics2d.fill(outlineIN);
                
                RoundRectangle2D minusLine = new RoundRectangle2D.Double(17.0D, 30.0D, 19, 5, 4, 4);
                Area areaMinus = new Area(minusLine);

                graphics2d.setPaint(Color.lightGray);
                graphics2d.fill(areaMinus);
            }
        }

    }
    
    // for type button = 5 
    // button checkbox
    public boolean getPressedState() {
        return pressedState;
    }
    
    public boolean setPressedState(boolean pressedState) {
        this.pressedState = pressedState;
        return this.pressedState;
    }

    private Shape createStrelkaLeft(int x, int y, int w, int h) {
        // new Rectangle2D.Double(x, y, w, h)
        GeneralPath newshape = new GeneralPath(); // Start with an empty shape
        newshape.moveTo(x + w, y + h * 1 / 4);
        newshape.lineTo(x + w, y + h * 3 / 4);
        newshape.lineTo(x + w / 2, y + h * 3 / 4);
        newshape.lineTo(x + w / 2, y + h);
        newshape.lineTo(x, y + h / 2);
        newshape.lineTo(x + w / 2, y);
        newshape.lineTo(x + w / 2, y + h * 1 / 4);
        newshape.closePath();
        return newshape;
    }

    private Shape createStrelkaRight(int x, int y, int w, int h) {
        // new Rectangle2D.Double(x, y, w, h)
        GeneralPath newshape = new GeneralPath(); // Start with an empty shape
        newshape.moveTo(x + w / 2, y);
        newshape.lineTo(x + w, y + h / 2);
        newshape.lineTo(x + w / 2, y + h);
        newshape.lineTo(x + w / 2, y + h * 3 / 4);
        newshape.lineTo(x, y + h * 3 / 4);
        newshape.lineTo(x, y + h * 1 / 4);
        newshape.lineTo(x + w / 2, y + h * 1 / 4);
        newshape.closePath();
        return newshape;
    }

    /**
     * This method centers a <code>String</code> in a bounding
     * <code>Rectangle</code>.
     *
     * @param g - The <code>Graphics</code> instance.
     * @param r - The bounding <code>Rectangle</code>.
     * @param s - The <code>String</code> to center in the bounding rectangle.
     * @param font - The display font of the <code>String</code>
     *
     * @see java.awt.Graphics
     * @see java.awt.Rectangle
     * @see java.lang.String
     */
    public void centerString(Graphics g, Rectangle r, String s,
            Font font) {
        FontRenderContext frc
                = new FontRenderContext(null, true, true);

        Rectangle2D r2D = font.getStringBounds(s, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());

        int a = (r.width / 2) - (rWidth / 2) - rX;
        int b = (r.height / 2) - (rHeight / 2) - rY;

        g.setFont(font);
        g.drawString(s, r.x + a, r.y + b);
    }

    /**
     * Draw a String centered in the middle of a Rectangle.
     *
     * @param g The Graphics instance.
     * @param text The String to draw.
     * @param rect The Rectangle to center the text in.
     */
    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) { 
        if (this.typeButton == 1) {
            if (exitbutton.contains(e.getPoint())) {
                System.exit(0);
            }
        }
        if (this.typeButton == 5) {
            if(!pressedState)  {
                pressedState = true;
                this.repaint();
            } else {
                pressedState = false;
                this.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        buttonFocused = true;
        this.repaint();
        this.revalidate();

        if (this.typeButton == 4 || this.typeButton == 5) {
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
//           System.out.println("entr");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        buttonFocused = false;
        this.repaint();
        this.revalidate();

        if (this.typeButton == 4 || this.typeButton == 5) {
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//           System.out.println("ex");
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public String toString() {
        if (this.typeButton == 1) {
            return "exit";
        } else if (this.typeButton == 2) {
            return "strelkaLeft";
        } else if (this.typeButton == 3) {
            return "strelkaRight";
        } else if (this.typeButton == 4) {
            return this.nameMenu;
        } else if (this.typeButton == 5) {
            return this.nameCheckbox;
        }
        else {
            return "1111";
        }
    }

    public static void main(String[] args) {

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(100, 100);

        Rectangle rect1 = new Rectangle(1, 1, 90, 24);
        buttons optionsMenuItem = new buttons("Options", rect1);
        optionsMenuItem.setBounds(1, 1, 90, 27);
        optionsMenuItem.setVisible(true);

        Rectangle rect2 = new Rectangle(1, 1, 145, 24);
        buttons statisticsMenuItem = new buttons("Statistics", rect2);
        statisticsMenuItem.setBounds(105, 1, 145, 27);
        statisticsMenuItem.setVisible(true);

        Rectangle rect3 = new Rectangle(1, 1, 50, 24);
        buttons helpMenuItem = new buttons("Help", rect3);
        helpMenuItem.setBounds(265, 1, 50, 27);
        helpMenuItem.setVisible(true);

        JPanel p = new JPanel(null);
        p.setPreferredSize(new Dimension(400, 250));
        p.setMinimumSize(new Dimension(400, 250));
        p.add(optionsMenuItem);
        p.add(statisticsMenuItem);
        p.add(helpMenuItem);

        f.add(p);
       // f.setVisible(true);
        
        
        
//        int i1 = 012;
//        int i2 = 20;
        
        double d = Math.sqrt(-1);
//        
//        System.out.println(Double.NaN == d);
//        
//        d= d/0;
//        System.out.println(Double.isNaN(d)); 
        
//        Integer i = 5000;
        
        byte a = 1;
        byte b = ++a;
       // byte c = -a;
        
        
        System.out.println(a);
        System.out.println(b);
       // System.out.println(c);
    }

}

