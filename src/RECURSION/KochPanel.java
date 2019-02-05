
package RECURSION;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;

public class KochPanel extends JPanel {
     private final int PANEL_WIDTH = 600;
     private final int PANEL_HEIGHT = 600;
     
     private final int TOPX = 200, TOPY = 20;
     private final int LEFTX = 60, LEFTY = 300;
     private final int RIGHTX = 340, RIGHTY = 300;

     private final double SQ = Math.sqrt(3.0)/6;
     int currentOrder; // Order

   public  KochPanel(int order) {
         currentOrder = order;
         this.setBackground(Color.BLACK);
         this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    }

     public void drawFractalForms(int order, int x1, int y1, int x5, int y5, Graphics g ) {

         int x2, y2, x3, y3, x4, y4;
         int deltaX, deltaY;

         if(order == 1)
             g.drawLine(x1, y1, x5, y5);
         else
         {
             deltaX = x5 - x1;  // distance between end points
             deltaY = y5 - y1;

             x2 = x1 + deltaX / 3; // one third
             y2 = y1 + deltaY / 3;

             x3 = (int) ((x1 + x5) / 2 + SQ * (y1 - y5)); // tip of projection
             y3 = (int) ((y1 + y5) / 2 + SQ * (x5 - x1));

             x4 = x1 + deltaX*2 / 3;  // two third
             y4 = y1 + deltaY*2 / 3;

             drawFractalForms(order-1, x1, y1, x2, y2, g);
             drawFractalForms(order-1, x2, y2, x3, y3, g);
             drawFractalForms(order-1, x3, y3, x4, y4, g);
             drawFractalForms(order-1, x4, y4, x5, y5, g);
         }
     }

     public void paintComponent(Graphics g) {
            drawFractalForms(currentOrder, TOPX, TOPY, LEFTX, LEFTY, g);
            drawFractalForms(currentOrder, LEFTX, LEFTY, RIGHTX, RIGHTY, g);
            drawFractalForms(currentOrder, RIGHTX, RIGHTY, TOPX, TOPY, g);
     }

     public void setOrder(int order) {
            currentOrder = order;
     }

     public int getOrder() {
         return currentOrder;
    }
     
}
