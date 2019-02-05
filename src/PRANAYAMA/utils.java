package PRANAYAMA;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class utils {

    public static String numberPosition(int number) {
        String str;

        if (number < 10) {
            str = "0" + number;
        } else {
            str = "" + number;
        }
        return str;
    }

    public static void center(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        frame.setLocation(x, y);
    }
    
    protected static Color getToneColor(Color rgb, int percent) {
       HSLColor c = new HSLColor(rgb);
       Color tone = c.adjustTone(percent);
       return tone;
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
    public static void centerString(Graphics g, Rectangle.Double r, String s,
            Font font) {
        FontRenderContext frc
                = new FontRenderContext(null, true, true);

        Rectangle2D r2D = font.getStringBounds(s, frc);
        double rWidth = (double) Math.round(r2D.getWidth());
        double rHeight = (double) Math.round(r2D.getHeight());
        double rX = (double) Math.round(r2D.getX());
        double rY = (double) Math.round(r2D.getY());

        double a = (r.width / 2) - (rWidth / 2) - rX; // - 1
        double b = (r.height / 2) - (rHeight / 2) - rY + 1; // +1

        g.setFont(font);
        g.drawString(s, (int) (r.x + a), (int) (r.y + b));
        // g.drawString(s, (int) (r.x + a), (int) r.y );
    }

    public static Color setNewColor(Color color) {

        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        int alpha = color.getAlpha() / 2;

        Color c = new Color(r, g, b, alpha);

        return c;
    }

    public static float getLuminance(float R, float G, float B) {
        float Y = (float) (0.2126 * R + 0.7152 * G + 0.0722 * B);
        return Y;
    }

    public static double intensity(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        return 0.299 * r + 0.587 * g + 0.114 * b;
    }

    public static class buttonsWindow extends JComponent implements MouseListener {

        boolean focus = false;
        String text;
        int action = -1;
        int outW, outH;
        boolean handLocation = false;
        float xLoc, yLoc;
        String id;
        boolean disabled = false;
        Color color;

        buttonsWindow(String str, int outX, int outY, int xLoc, int yLoc) {
            text = str;
            handLocation = true;
            this.outW = outX;
            this.outH = outY;
            this.xLoc = xLoc;
            this.yLoc = yLoc;
            color = Color.BLACK;
            this.addMouseListener(this);
        }

        public String getNameButton() {
            return this.text;
        }

        public void setID(String id) {
            this.id = id;
        }

        public void setDisabledState(boolean disabled) {
            this.disabled = disabled;
        }

        protected void setColor(Color color) {
            this.color = color;
        }

        protected Color getColor() {
            return color;
        }

        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            RoundRectangle2D rect = new RoundRectangle2D.Double(0, 0, outW, outH, 25, 20);
            int w = (int) rect.getWidth();
            int h = (int) rect.getHeight();
            g2.setStroke(new BasicStroke(0.5f));

            if (!focus && !disabled) {
                if (color == Color.BLACK) {
                    g2.setPaint(new GradientPaint(0, 0, new Color(84, 255, 124), 0, h, new Color(255, 243, 15), false));// 192,255,158
                } else {
                    g2.setPaint(getColor());
                }
            } else if (focus && !disabled) {
                g2.setPaint(new GradientPaint(0, 0, new Color(84, 255, 124), 0, h, Color.RED, false));
            } else if (disabled) {
                g2.setPaint(new Color(192, 192, 192));
            }
            g2.fill(rect);

            AffineTransform at = new AffineTransform();
            Shape outline_rect = at.createTransformedShape(rect);
            Area outline = new Area(outline_rect);

            int deep = 2;
            for (int k = 0; k < deep; k += 1) {
                at.translate(0, k);
                g2.setStroke(new BasicStroke(1.0f));
                g2.transform(at);
                g2.setColor(new Color(150, 195, 255));
                g2.draw(outline_rect);
            }

            if (!handLocation) {
                xLoc = (float) (rect.getX() + 10); // + 10
                yLoc = (float) ((rect.getY() + rect.getHeight() - 10));
            }

            Font font1 = new Font("Book Antiqua", Font.PLAIN, 70);
            Font font = new Font("Monotype Corsiva", Font.BOLD, 26);

            if (text != "") {
                TextLayout textLayout = new TextLayout(text, font, g2.getFontRenderContext());
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g2.setPaint(Color.black);
                textLayout.draw(g2, xLoc, yLoc);
            }

            // button in is disabled state
            if (disabled) {
                Line2D line1 = new Line2D.Double(0.0D, 0.0D, outW, outH);
                Line2D line2 = new Line2D.Double(0.0D, 0.0D + outH, outW, 0.0D);

                g2.setStroke(new BasicStroke(3.0f));
                g2.setColor(Color.red);
                g2.draw(line1);
                g2.draw(line2);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
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

        @Override
        public String toString() {
            return this.id;
        }

    }

    public static void main(String[] args) {

//        float[] hsv = Color.RGBtoHSB(255, 255, 0, null);
//
////        for (int i = 0; i < hsv.length; i++) {
////            float f = hsv[i];
//        System.out.println("h:" + hsv[0] + " " + "s:" + hsv[1] + " " + "b:" + hsv[2]);
////        }
//
//        float hue = (float) (hsv[0] + 0.0);
//
//        float saturation = hsv[1];
//
//        float brightness = hsv[2];
//
//        int rgb = Color.HSBtoRGB(hue, saturation, brightness);
//
//        int red = (rgb >> 16) & 0xFF;
//        int green = (rgb >> 8) & 0xFF;
//        int blue = rgb & 0xFF;
//
//        System.out.println("R:" + red + " " + "G:" + green + " " + "B:" + blue);

        Color red = new Color(255,0,0);
        
        utils.intensity(red);
        
        System.out.println(" " + utils.intensity(red));
    }

}
