

package PRANAYAMA;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;


  class separator extends JComponent {

        @Override
        public void paint(Graphics g) {
            Graphics2D graphics2d = (Graphics2D) g;
            graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2d.setStroke(new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));

            Rectangle2D spaceP = new Rectangle2D.Double(1.0D, 1.0D, 22, 22);
            
            Line2D line1 = new Line2D.Double(1.0D, 10.0D, 30.0D, 1.0D);
            Line2D line2 = new Line2D.Double(30.0D, 1.0D, 30.0D, 10.0D);
            Line2D line3 = new Line2D.Double(30.0D, 10.0D,60.0D, 1.0D);
            Line2D line4 = new Line2D.Double(60.0D, 1.0D, 60.0D, 10.0D);
            Line2D line5 = new Line2D.Double(60.0D, 10.0D, 90.0D, 1.0D);
            Line2D line6 = new Line2D.Double(90.0D, 1.0D, 90.0D, 10.0D);
            Line2D line7 = new Line2D.Double(90.0D, 10.0D, 120.0D, 1.0D);
            Line2D line8 = new Line2D.Double(120.0D, 1.0D, 120.0D, 10.0D);
            Line2D line9 = new Line2D.Double(120.0D, 10.0D, 150.0D, 1.0D);
            Line2D line10 = new Line2D.Double(150.0D, 1.0D, 150.0D, 10.0D);
            
            Area area = new Area(line1);
            area.add(new Area(line2));
            area.add(new Area(line3));
            area.add(new Area(line4));
            area.add(new Area(line5));
            area.add(new Area(line6));
            area.add(new Area(line7));
            area.add(new Area(line8));
            area.add(new Area(line9));
            area.add(new Area(line10));
            graphics2d.setColor(Color.orange);
            graphics2d.draw(line1);
            graphics2d.draw(line2);
            graphics2d.draw(line3);
            graphics2d.draw(line4);
            graphics2d.draw(line5);
            graphics2d.draw(line6);
            graphics2d.draw(line7);
            graphics2d.draw(line8);
            graphics2d.draw(line9);
            graphics2d.draw(line10);
        }

    }
