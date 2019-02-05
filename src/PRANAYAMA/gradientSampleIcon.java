package PRANAYAMA;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.Icon;

class gradientSampleIcon implements Icon {

    basicPanel_Paint paint;
    private BasicStroke stroke = new BasicStroke(1);
    private int x;
    private int y;
    private int w = 75;
    private int h = 28;
    private int type;
    private boolean isEntered = false;

    gradientSampleIcon(int t) {
        this.type = t;
        
        x = y = 0;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(stroke);

        Rectangle2D r = new Rectangle2D.Double(0, 0, w, h);

        setGradientBackground(g2d);
        
        if (paint != null) {
            g2d.setPaint(paint);
        }

        g2d.fill(r);
        g2d.dispose();
    }

    private void setGradientBackground(Graphics2D g2) {
        
        BufferedImage bi1 = null;
        Rectangle rr = null;
        TexturePaint tp = null;
        
        int choice = this.getType();

        switch (choice) {
            default:
                break;

            case 1:
                paint = new basicPanel_Paint(x + 15, y + 8, new Color(255, 222, 183),
                        new Point2D.Double(0, 30), new Color(20, 255, 132));
                break;

            case 2:
                paint = new basicPanel_Paint(x + 14, y + 10, new Color(228, 255, 209),
                        new Point2D.Double(0, 25), Color.ORANGE);
                break;

            case 3:
                paint = new basicPanel_Paint(w / 2 - 10, h - 5, Color.CYAN,
                        new Point2D.Double(0, 35), new Color(228, 255, 209));
                break;

            case 4:
                paint = new basicPanel_Paint(w / 2, y, Color.GREEN,
                        new Point2D.Double(0, 35), new Color(96, 96, 255));
                break;

            case 5:
                paint = new basicPanel_Paint(x, (h) / 2, new Color(240, 255, 145),
                        new Point2D.Double(0, 35), new Color(189, 186, 255));
                break;

            case 6:
                paint = new basicPanel_Paint(w - 10, h / 2, new Color(255, 172, 140),
                        new Point2D.Double(0, 45), new Color(202, 255, 112));
                break;

            case 7:
                paint = new basicPanel_Paint(w / 2, y, new Color(242, 193, 255),
                        new Point2D.Double(0, 30), new Color(221, 240, 255));
                break;

            case 8:
                g2.setPaint(new GradientPaint(x, y, new Color(221, 142, 255), w, h, Color.YELLOW, true));
                break;

            case 9:
                g2.setPaint(new GradientPaint(x, y, new Color(255, 246, 221), x + 50, y, new Color(230, 255, 5), true));
                break;

            case 10:
                g2.setPaint(new GradientPaint(x, y, new Color(255, 246, 221), w / 4, y, Color.WHITE, true));
                break;

            case 11:
                g2.setPaint(new GradientPaint(x, y, new Color(255, 246, 221), w, h / 2, Color.BLACK, true));
                break;

            case 12:
                g2.setPaint(new GradientPaint(x, y, new Color(255, 246, 221), w / 2, h / 2, Color.BLACK, true));
                break;

            case 13:
                g2.setPaint(new GradientPaint(x, y, new Color(255, 246, 221), x, h / 2, Color.ORANGE, true));
                break;

            case 14:
                g2.setPaint(new GradientPaint(x, y, new Color(236, 255, 155), x, h / 2, Color.LIGHT_GRAY, true));
                break;

            case 15:
                g2.setPaint(new GradientPaint(x, y, new Color(236, 255, 155), x, h / 2, Color.BLUE, true));
                break;

            case 16:
                bi1 = getTexture16();
                rr = new Rectangle(0, 0, bi1.getWidth(), bi1.getHeight());
                tp = new TexturePaint(bi1, rr);
                g2.setPaint(tp);
                break;

            case 17:
                bi1 = getTexture17();
                rr = new Rectangle(0, 0, bi1.getWidth(), bi1.getHeight());
                tp = new TexturePaint(bi1, rr);
                g2.setPaint(tp);
                break;

            case 18:
                bi1 = getTexture18();
                rr = new Rectangle(0, 0, bi1.getWidth(), bi1.getHeight());
                tp = new TexturePaint(bi1, rr);
                g2.setPaint(tp);
                break;

            case 19:
                bi1 = getTexture19();
                rr = new Rectangle(0, 0, bi1.getWidth(), bi1.getHeight());
                tp = new TexturePaint(bi1, rr);
                g2.setPaint(tp);
                break;

            case 20:
                bi1 = getTexture20();
                rr = new Rectangle(0, 0, bi1.getWidth(), bi1.getHeight());
                tp = new TexturePaint(bi1, rr);
                g2.setPaint(tp);
                break;

            case 21:
                bi1 = getTexture21();
                rr = new Rectangle(0, 0, bi1.getWidth(), bi1.getHeight());
                tp = new TexturePaint(bi1, rr);
                g2.setPaint(tp);
                break;
        }
    }
    
    
    private BufferedImage getTexture16() {
        int size = h / 2;
        BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        g2.setPaint(new GradientPaint(0, 0, Color.YELLOW, size / 2, size / 2, Color.DARK_GRAY, true));
        g2.fillRect(0, 0, size / 2, size / 2);
        g2.setPaint(new GradientPaint(size / 2, 0, Color.YELLOW, size, size / 2, Color.DARK_GRAY, true));
        g2.fillRect(size / 2, 0, size, size / 2);
        g2.setPaint(new GradientPaint(0, size / 2, Color.YELLOW, size / 2, size, Color.DARK_GRAY, true));
        g2.fillRect(0, size / 2, size / 2, size);
        g2.setPaint(new GradientPaint(size / 2, size / 2, Color.YELLOW, size, size, Color.DARK_GRAY, true));
        g2.fillRect(size / 2, size / 2, size, size);
        return bi;
    }

    private BufferedImage getTexture17() {
        int size = h / 2;
        BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        g2.setPaint(new GradientPaint(0, 0, Color.YELLOW, size / 2, size / 2, new Color(255, 203, 196), true));
        g2.fillRect(0, 0, size / 2, size / 2);
        g2.setPaint(new GradientPaint(size / 2, 0, Color.YELLOW, size, size / 2, new Color(255, 203, 196), true));
        g2.fillRect(size / 2, 0, size, size / 2);
        g2.setPaint(new GradientPaint(0, size / 2, Color.YELLOW, size / 2, size, new Color(255, 203, 196), true));
        g2.fillRect(0, size / 2, size / 2, size);
        g2.setPaint(new GradientPaint(size / 2, size / 2, Color.YELLOW, size, size, new Color(255, 203, 196), true));
        g2.fillRect(size / 2, size / 2, size, size);
        return bi;
    }

    private BufferedImage getTexture18() {
        int size = h / 2;
        BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        g2.setPaint(new GradientPaint(0, 0, Color.LIGHT_GRAY, 0, size / 2, new Color(255, 203, 196), true));
        g2.fillRect(0, 0, size / 2, size / 2);
        g2.setPaint(new GradientPaint(size / 2, 0, new Color(239, 255, 198), size / 2, size / 2, new Color(255, 203, 196), true));
        g2.fillRect(size / 2, 0, size, size / 2);
        g2.setPaint(new GradientPaint(0, size / 2, Color.LIGHT_GRAY, 0, size, new Color(255, 203, 196), true));
        g2.fillRect(0, size / 2, size / 2, size);
        g2.setPaint(new GradientPaint(size / 2, size / 2, new Color(191, 224, 255), size / 2, size, new Color(255, 203, 196), true));
        g2.fillRect(size / 2, size / 2, size, size);
        return bi;
    }

    private BufferedImage getTexture19() {
        int size = h / 2;
        BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        g2.setPaint(new GradientPaint(0, 0, Color.LIGHT_GRAY, size / 2, 0, new Color(197, 112, 255), true));
        g2.fillRect(0, 0, size / 2, size / 2);
        g2.setPaint(new GradientPaint(size / 2, 0, new Color(218, 255, 127), size, 0, new Color(255, 203, 196), true));
        g2.fillRect(size / 2, 0, size, size / 2);
        g2.setPaint(new GradientPaint(0, size / 2, Color.GRAY, size / 2, size / 2, new Color(197, 112, 255), true));
        g2.fillRect(0, size / 2, size / 2, size);
        g2.setPaint(new GradientPaint(size / 2, size / 2, new Color(255, 200, 130), size, size / 2, new Color(255, 203, 196), true));
        g2.fillRect(size / 2, size / 2, size, size);
        return bi;
    }

    private BufferedImage getTexture20() {
        int size = h / 4;
        BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        g2.setPaint(new GradientPaint(0, 0, Color.LIGHT_GRAY, size / 2, 0, new Color(253, 255, 221), true));
        g2.fillRect(0, 0, size / 2, size / 2);
        g2.setPaint(new GradientPaint(size / 2, 0, new Color(218, 255, 127), size, 0, new Color(255, 203, 196), true));
        g2.fillRect(size / 2, 0, size, size / 2);
        g2.setPaint(new GradientPaint(0, size / 2, Color.GRAY, size / 2, size / 2, new Color(253, 255, 221), true));
        g2.fillRect(0, size / 2, size / 2, size);
        g2.setPaint(new GradientPaint(size / 2, size / 2, new Color(255, 200, 130), size, size / 2, new Color(255, 203, 196), true));
        g2.fillRect(size / 2, size / 2, size, size);
        return bi;
    }

    private BufferedImage getTexture21() {
        int size = h;

        basicPanel_Paint paint = new basicPanel_Paint(100, 100, Color.RED,
                new Point2D.Double(100, 100), new Color(236, 255, 132));
        BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
            g2.setPaint( paint );
            g2.fillRect(0 , 0, size/2, size/2);
            g2.setPaint(paint);
            g2.fillRect(size/2, 0, size, size/2);
            g2.setPaint( paint );
            g2.fillRect(0, size/2, size/2, size);
            g2.setPaint( paint );
            g2.fillRect(size/2, size/2, size, size);
        g2.fillRect(0, 0, size, size);
        return bi;
    }

    protected void setType(int Type) {
        type = Type;
    }

    protected int getType() {
        return type;
    }

    @Override
    public int getIconWidth() {
        return w;
    }

    @Override
    public int getIconHeight() {
        return h;
    }

    protected void setEntered(boolean bool) {
        this.isEntered = bool;
    }

    protected boolean getEntered() {
        return isEntered;
    }
}
