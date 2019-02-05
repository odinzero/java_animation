package PRANAYAMA;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class PlusMinus extends JComponent implements MouseListener {

    int type;
    boolean focus = false;
    boolean pressed = false;

    PlusMinus(int t) {
        type = t;
        addMouseListener(this);
        setPreferredSize(this.getPreferredSize());
        setMinimumSize(this.getMinimumSize());
        this.setBounds(5, 5, 47, 27);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RoundRectangle2D rr = new RoundRectangle2D.Double(0, 0, 46, 26, 26, 26);
        int w = (int) rr.getWidth();
        int h = (int) rr.getHeight();
        g2.setStroke(new BasicStroke(0.5f));
        g2.draw(rr);

        if (!focus) {
           // g2.setPaint(new GradientPaint(0, 0, new Color(232, 216, 255), 0, h, Color.WHITE, false));// 192,255,158
            g2.setPaint(new GradientPaint(0, 0, new Color(3, 184, 252), 0, h, Color.WHITE, false));
        } else {
            g2.setPaint(new GradientPaint(0, 0, new Color(3, 184, 252), 0, h, new Color(240, 255, 239), false));
        }

        if (pressed) {
            g2.setPaint(new GradientPaint(0, 0, new Color(27, 228, 228), 0, h, new Color(232, 216, 255), false));
        }

        g2.fill(rr);

        if (type == 1) { // plus
            Rectangle2D hor = new Rectangle2D.Double(15, 11, 16, 4);
            Rectangle2D ver = new Rectangle2D.Double(21, 5, 4, 17);
            Area area = new Area(hor);
            area.add(new Area(ver));
            g2.setPaint(Color.YELLOW);
            g2.fill(area);
            g2.setPaint(Color.WHITE);
            g2.draw(area);
        }

        if (type == 2) {  // minus
            Rectangle2D hor = new Rectangle2D.Double(15, 11, 17, 4);
            g2.setPaint(Color.YELLOW);
            g2.fill(hor);
            g2.setPaint(Color.WHITE);
            g2.draw(hor);
        }
    }

    public int getType() {
        return type;
    }

    public Dimension getPreferredSize() {
        return new Dimension(70, 35);
    }

    public Dimension getMinimumSize() {
        return new Dimension(70, 35);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = true;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        focus = true;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        focus = false;
        repaint();
    }

    public static void main(String[] args) {
       // PlusMinus arr1 = new PlusMinus(1);
//        PlusMinus arr2 = new PlusMinus(2);
//
//        JFrame f = new JFrame();
//        f.setSize(300, 300);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setLayout(null);
//       // f.add(arr1);
//        f.add(arr2);
//        f.setVisible(true);

    }

}
