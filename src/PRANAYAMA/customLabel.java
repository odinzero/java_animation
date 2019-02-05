package PRANAYAMA;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class customLabel extends JLabel implements MouseListener {

    int cnt1 = -1;
    int cnt2 = -1;
    boolean isIncreased = false;
    boolean isDecreased = false;
    String text;
    float alpha;
    boolean change_color = false;

    customLabel(String t, float al) { // , PRANAYAMA prana
        text = t;
        alpha = al;
        text = t;
        this.setText(text);
        setAlignmentX(LEFT_ALIGNMENT);
        this.addMouseListener(this);
//        setPreferredSize(new Dimension(100,100));
//        this.setHorizontalTextPosition(SwingConstants.LEFT);
//        this.setVerticalTextPosition(SwingConstants.CENTER);
        isIncreased = false;
        isDecreased = false;
    }

    protected void startThreadIncrease() {
        new highlightIncrease();
         isIncreased = true;
         isDecreased = false;
    }
    
    protected void startThreadDecrease() {
        new highlightDecrease();
        isIncreased = false;
        isDecreased = true;
    }

    @Override
    public void paintComponent(Graphics g) {
//           super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        BufferedImage bi = createTexture();
        Rectangle rect = new Rectangle(0, 0, bi.getWidth(), bi.getHeight());
        TexturePaint tp = new TexturePaint(bi, rect);

        BufferedImage biP = createTexturePressed();
        Rectangle rect1 = new Rectangle(0, 0, biP.getWidth(), biP.getHeight());
        TexturePaint tp1 = new TexturePaint(biP, rect1);

        int fontSize = 26;
//        Font font1 = new Font("Book Antiqua", Font.PLAIN, 70);
        Font font = new Font("Monotype Corsiva", Font.BOLD, fontSize);
        TextLayout textLayout = new TextLayout(getText(), font, g2.getFontRenderContext());
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        if (!change_color) {
            g2.setPaint(tp);
        } else {
            g2.setPaint(tp1);
        }
        
        if(!isIncreased && !isDecreased) {
            textLayout.draw(g2, 0, 20);
          //  setBackground(null);
        }
        
        if(isIncreased && !isDecreased)
        switch (cnt1) {
            default:
                break;
            case -1:
                textLayout.draw(g2, 0, 20);
              //  setBackground(null);
                break;
            case 0:
                font = new Font("Monotype Corsiva", Font.BOLD, 27);
                textLayout = new TextLayout(getText(), font, g2.getFontRenderContext());
                g2.setPaint(new Color(255, 233, 225));
               // setBackground(new Color(158, 208, 9));   
                g2.setStroke(new BasicStroke(4f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
                textLayout.draw(g2, -1.0f, 20);
                break;
            case 1:
                font = new Font("Monotype Corsiva", Font.BOLD, 27);
                textLayout = new TextLayout(getText(), font, g2.getFontRenderContext());
                g2.setPaint(new Color(254, 160, 129));
              //  setBackground(new Color(158, 208, 9));
                g2.setStroke(new BasicStroke(4f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
                textLayout.draw(g2, -1.5f, 20);
                break;
            case 2:
                font = new Font("Monotype Corsiva", Font.BOLD, 28);
                textLayout = new TextLayout(getText(), font, g2.getFontRenderContext());
                g2.setPaint(new Color(254, 78, 18));
              //  setBackground(new Color(158, 208, 9));
                g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
                textLayout.draw(g2, -2.0f, 20);
                break;
            case 3:
                font = new Font("Monotype Corsiva", Font.BOLD, 28);
                textLayout = new TextLayout(getText(), font, g2.getFontRenderContext());
                g2.setPaint(new Color(235, 60, 1));
               // setBackground(new Color(158, 208, 9));
                g2.setStroke(new BasicStroke(7f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
                textLayout.draw(g2, -2.5f, 20);
                break;
        }
        
        if(!isIncreased && isDecreased)
         switch (cnt2) {
            default:
                break;
            case -1:
                textLayout.draw(g2, 0, 20);
                setBackground(null);
                break;
            case 0:
                font = new Font("Monotype Corsiva", Font.BOLD, 25);
                textLayout = new TextLayout(getText(), font, g2.getFontRenderContext());
                g2.setPaint(new Color(239, 239, 239));
                g2.setStroke(new BasicStroke(4f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
                textLayout.draw(g2, -1.0f, 20);
                break;
            case 1:
                font = new Font("Monotype Corsiva", Font.BOLD, 25);
                textLayout = new TextLayout(getText(), font, g2.getFontRenderContext());
                g2.setPaint(new Color(185, 185, 185));
                g2.setStroke(new BasicStroke(4f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
               // setBackground(new Color(233, 233, 233));
                textLayout.draw(g2, -1.5f, 20);
                break;
            case 2:
                font = new Font("Monotype Corsiva", Font.BOLD, 24);
                textLayout = new TextLayout(getText(), font, g2.getFontRenderContext());
                g2.setPaint(new Color(130, 130, 130));
                g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
              //  setBackground(new Color(233, 233, 233));
                textLayout.draw(g2, -2.0f, 20);
                break;
            case 3:
                font = new Font("Monotype Corsiva", Font.BOLD, 24);
                textLayout = new TextLayout(getText(), font, g2.getFontRenderContext());
                g2.setPaint(new Color(5, 5, 5));
                g2.setStroke(new BasicStroke(7f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
               // setBackground(new Color(233, 233, 233));
                textLayout.draw(g2, -2.5f, 20);
                break;
        }

//        Shape s = textLayout.getOutline(null);   
//        g2.draw(s); 
//          FontRenderContext frc = g2.getFontRenderContext();
//          GlyphVector gv = font.createGlyphVector(frc, getText());
//          g2.setStroke(new BasicStroke(3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
//          
//          Shape textShape = gv.getOutline();
//          g2.draw(textShape);
        //g2.drawGlyphVector(gv, 0 , 20 );
//          g2.setFont(font); 
//          g2.drawString(getText(), 0, 20);
    }

    private BufferedImage createTexture() {
        int size = 4;
        BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        g2.setPaint(Color.BLUE);
        g2.fillRect(0, 0, size / 2, size / 2);
        g2.setPaint(Color.RED);
        g2.fillRect(size / 2, 0, size, size / 2);
        g2.setPaint(Color.RED);
        g2.fillRect(0, size / 2, size / 2, size);
        g2.setPaint(Color.BLUE);
        g2.fillRect(size / 2, size / 2, size, size);
        return bi;
    }

    private BufferedImage createTexturePressed() {
        int size = 4;
        BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        g2.setPaint(Color.RED);
        g2.fillRect(0, 0, size / 2, size / 2);
        g2.setPaint(Color.GRAY);
        g2.fillRect(size / 2, 0, size, size / 2);
        g2.setPaint(Color.GRAY);
        g2.fillRect(0, size / 2, size / 2, size);
        g2.setPaint(Color.RED);
        g2.fillRect(size / 2, size / 2, size, size);
        return bi;
    }

    public static void main(String[] args) {
//        JFrame f  =new JFrame();
//        f.setLayout(null);
//        f.setSize(200, 200);
//        JLabel lab = new customLabel("TEXT : " + 5, 0.8f , mainBricks );
//        f.repaint();
//        lab.setBounds(20, 25, 200, 200);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.add(lab, BorderLayout.CENTER);
//        lab.setText("TEXT 1: ");
//        f.setVisible(true);
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
        change_color = true;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        change_color = false;
        repaint();
    }

    class highlightIncrease implements Runnable {

        boolean start = false;

        highlightIncrease() {
            Thread thr = new Thread(this);
            thr.start();
        }

        @Override
        public void run() {
            start = true;
            try {
                while (start) {
                    cnt1++;
                    Thread.currentThread().sleep(100);
                    if (cnt1 == 4) {
                        cnt1 = -1;
                        start = false;
                    }
                    repaint();
                }
            } catch (InterruptedException ex) {
            }
        }

    }

    class highlightDecrease implements Runnable {

        boolean start = false;

        highlightDecrease() {
            Thread thr = new Thread(this);
            thr.start();
        }

        @Override
        public void run() {
            start = true;
            try {
                while (start) {
                    cnt2++;
                    Thread.currentThread().sleep(100);
                    if (cnt2 == 4) {
                        cnt2 = -1;
                        start = false;
                    }
                    repaint();
                }
            } catch (InterruptedException ex) {
            }
        }

    }

}
