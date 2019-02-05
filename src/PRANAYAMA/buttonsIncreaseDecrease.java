package PRANAYAMA;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class buttonsIncreaseDecrease  extends JComponent implements MouseListener {

     int type;
     boolean focus = false;
     boolean pressed = false;
     boolean enabled = true;
     
    buttonsIncreaseDecrease(int t) {
      type = t;
      addMouseListener(this);
      setPreferredSize(this.getPreferredSize());
      setMinimumSize(this.getMinimumSize());
      this.setBounds(5, 5, 26, 27); //26, 27
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
        RoundRectangle2D rr = new RoundRectangle2D.Double(0, 0,  25, 26 , 10 ,10); //  25, 26
        int w = (int) rr.getWidth();
        int h = (int) rr.getHeight();
        g2.setStroke(new BasicStroke(0.5f));
        if(type == 1 || type == 2 )
        g2.draw(rr);

        if( !focus ) {
         g2.setPaint( new GradientPaint( 0, 0, new Color(232,216,255) , 0 , h, Color.WHITE, false) );// 192,255,158
           }
        else {
        g2.setPaint( new GradientPaint( 0, 0, new Color(232,216,255) , 0 , h, new Color(240,255,239), false) );
           }

        if( pressed )
        g2.setPaint( new GradientPaint( 0, 0, new Color(240,255,239) , 0 , h, new Color(232,216,255), false) );

        if( !enabled ) 
        g2.setPaint( new GradientPaint( 0, 0, new Color(233,226,255) , 0 , h, Color.LIGHT_GRAY, false) );

         if(type == 1 || type == 2 )
        g2.fill(rr);
        
        if( type == 1) {      // increase
           GeneralPath Greenarrow = new GeneralPath(  );
           Greenarrow.moveTo( 33,  24 );
           Greenarrow.lineTo( 26,  31 );
           Greenarrow.lineTo( 26,  27 );
           Greenarrow.lineTo( 16,  27 );
           Greenarrow.lineTo( 16,  23 );
           Greenarrow.lineTo( 26,  23 );
           Greenarrow.lineTo( 26,  19 );
           Greenarrow.closePath();
           AffineTransform forredarrow = new AffineTransform();
           forredarrow.createTransformedShape(Greenarrow);
           g2.translate( -22, 12);
           forredarrow.rotate( -Math.PI / 4.1 );
           g2.transform(forredarrow);
           g2.setPaint( new GradientPaint( 16, 23, Color.GRAY , 33 , 24, Color.GREEN, false) );
           g2.fill(Greenarrow);
           g2.setStroke(new BasicStroke(2.0f));
           g2.draw(Greenarrow);
        }
         if( type == 2) {     // decrease
           GeneralPath Greenarrow = new GeneralPath(  );
           Greenarrow.moveTo( 33,  24 );
           Greenarrow.lineTo( 26,  31 );
           Greenarrow.lineTo( 26,  27 );
           Greenarrow.lineTo( 16,  27 );
           Greenarrow.lineTo( 16,  23 );
           Greenarrow.lineTo( 26,  23 );
           Greenarrow.lineTo( 26,  19 );
           Greenarrow.closePath();
           AffineTransform forredarrow = new AffineTransform();
           forredarrow.createTransformedShape(Greenarrow);
           g2.translate( 47, 13);
           forredarrow.rotate( Math.PI / 1.35  );
           g2.transform(forredarrow);

           g2.setPaint( new GradientPaint( 16, 23, Color.GRAY , 33 , 24, new Color(68,174,255), false) );
           g2.fill(Greenarrow);
           g2.setStroke(new BasicStroke(2.0f));
           g2.draw(Greenarrow);
        }
        if( type == 3 ) {
           rr = new RoundRectangle2D.Double(0, 0,  46, 46 , 33 ,33);
//           int ww = (int) close.getWidth();
//           int hh = (int) close.getHeight();
           g2.setStroke(new BasicStroke(2.0f));
           g2.fill(rr);
           g2.setPaint(new Color(255,221,99));
           g2.draw(rr);
           this.setBounds(3, 3, 50, 50); //26, 27

           RoundRectangle2D ver = new RoundRectangle2D.Double(19, 6,  8, 34 , 10 ,10); //  25, 26
           RoundRectangle2D hor = new RoundRectangle2D.Double(6, 19,  34, 8, 10 ,10); //  25, 26
           Area cross = new Area(ver);
           cross.add(new Area(hor));
           AffineTransform forredarrow = new AffineTransform();
           forredarrow.createTransformedShape(cross);
           g2.translate( 56, 22);
           forredarrow.rotate( Math.PI / 1.35  );
           g2.transform(forredarrow);

           g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
           g2.setPaint( new Color(255,223,155));
           g2.fill(cross);
           g2.setStroke(new BasicStroke(1.0f));
           g2.setPaint( Color.ORANGE);
           g2.draw(cross);
         }

    }

    public int getType() {
        return type;
    }

    public void setEnabled(boolean flag) {
        enabled = flag;
    }

    public Dimension getPreferredSize() { return new Dimension(70, 35);  }
    public Dimension getMinimumSize() { return new Dimension(70, 35);  }


    @Override
    public void mouseClicked(MouseEvent e) { }

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
         buttonsIncreaseDecrease arr = new buttonsIncreaseDecrease(1);

         JFrame f = new JFrame();
         f.setSize(300, 300);
         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         f.setLayout(null);
         f.add(arr);
         f.setVisible(true);
    }

}

