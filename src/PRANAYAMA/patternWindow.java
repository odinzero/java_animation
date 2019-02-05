package PRANAYAMA;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;

public class patternWindow {

    public JFrame window;
    public JPanel base;

    PRANAYAMA pranaMain;

    patternWindow(PRANAYAMA prana, String name,
//            boolean JFrameOrWindow, // true -> Frame, false -> Window
            boolean useBorder, // whether use border or not
            int type, // type bevel border 
            boolean floatable, // bevel border must drag and drop or not
            boolean centerORnot, // locate window in center frame or not
            int x, int y, // x,y - coordinates of window in frame
            int w, // width
            int h) {            // height
        pranaMain = prana;

        componentBevelBorder bevel = new componentBevelBorder(type, 2, name, 1, 1, new Font("Times New Roman", 1, 12),
                new Color(0, 100, 0), Color.white,
                new Color(190, 220, 220),
                new Color(100, 130, 130),
                new Color(150, 180, 180));
        bevel.setFloatable(floatable);
        bevel.setResizableLimit(false);
        
        // help customize form of Window
        window = bevel.getJFrameForm();
        window.setUndecorated(true);
        window.setMinimumSize(new Dimension(w, h)); // 400, 320
        window.setPreferredSize(new Dimension(w, h));
        window.setAlwaysOnTop(true);
        window.setFocusable(true);
        window.setFocusableWindowState(true);
        window.setVisible(true);

//        if(centerORnot) { 
//          center(prana.frame, window);
//        } else {
//          nonCenter(prana.frame, x, y);
//        }
        setContentPanel();

        if (useBorder) {
            base.setBorder(bevel);
            window.add(base);
        } else {
            base = new JPanel(null);
            window.add(base);
        }

        if (floatable) {
            // allow drag JWIndow
            bevel.getDnDMouseListener(base);
            
            // true -> frame
           // if (JFrameOrWindow) {
                // set Behavior for control buttons in bevelBorder :
                // third parameter 0 -> frame,
                //                 1 -> window 
                bevel.getControlButtonsBehavior(base, 1, 0); // if '1' hide JFrame if '0' close JFrame
//            } 
//            // false -> window
//            else {
//                bevel.getControlButtonsBehavior(base, 1, 1); // if '1' hide JWindow if '0' close JWindow    
//            }
        }

        window.setVisible(false);
    }

    public void setWindowVisibility(boolean bool) {
        window.setVisible(bool);
    }

    private static void center(JFrame outside, JWindow inside) {
        // get the size of the screen, on systems with multiple displays,
        // the primary display is used
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        Dimension outsideDim = outside.getSize();
        int x = (dim.width - inside.getSize().width) / 2;
        int y = (dim.height - inside.getSize().height) / 2;

        inside.setLocation(x, y);
    }

    //                                           
    private void nonCenter(JFrame outside, int xx, int yy) {
        //Dimension screenSize = outside.getSize();
        int x = outside.getLocation().x + xx;
        int y = outside.getLocation().y + yy;

        window.setLocation(x, y);
    }

    private JPanel setContentPanel() {
        base = new JPanel(null) {
            int w = window.getWidth() - 1;
            int h = window.getHeight() - 1;

            @Override
            public void paintComponent(Graphics g) {
//             super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                Rectangle2D rect = new Rectangle2D.Double(0, 0, w, h);

                g2.setPaint(new GradientPaint(0, 0, new Color(255, 242, 226), w, h, new Color(213, 255, 122), false));
                g2.fill(rect);
                g2.setColor(Color.BLUE);
                g2.draw(rect);
            }

        };
        base.setMinimumSize(window.getMinimumSize());
        base.setPreferredSize(window.getPreferredSize());
        return base;
    }

    public JLabel addContent(String str, int fontSize, Color color, int x, int y, int width, int height) {
        Font font = new Font("Monotype Corsiva", Font.BOLD, fontSize);
        JLabel label = new JLabel(str);
        label.setName(str); 
        label.setFont(font);
        label.setBounds(x, y, width, height);
        label.setForeground(color);

        base.add(label);

        return label;
    }

    public buttonsWindow addButtonsWindow(String name, int x, int y, int width, int height) {
        buttonsWindow bw = new buttonsWindow(name);
        bw.setBounds(x, y, width, height);
        base.add(bw);
        return bw;
    }

    public buttonsWindow addButtonsWindow2(String name, int x, int y,
            int outW, int outH,
            int xLoc, int yLoc,
            int width, int height) {
        buttonsWindow bw = new buttonsWindow(name, outW, outH, xLoc, yLoc);
        bw.setBounds(x, y, width, height);
        base.add(bw);
        return bw;
    }

    public buttonsWindow addButtonsWindow3(String name, String id,
            int x, int y,
            int outW, int outH,
            int xLoc, int yLoc,
            int width, int height) {
        buttonsWindow bw = new buttonsWindow(name, outW, outH, xLoc, yLoc);
        bw.setID(id);
        bw.setBounds(x, y, width, height);
        base.add(bw);
        return bw;
    }
    
     public void removeLabel(String name) {
       // List<buttonsWindow> list = new ArrayList<buttonsWindow>();
        Component[] components = base.getComponents();
        
        for (Component component : components) {
            
          //  System.out.println(component.toString());
            
             if (component instanceof JLabel) {
                 if(component.getName().equals(name)) 
                     base.remove(component);
                   //  System.out.println(component.getName());
            }
        }
     }

    // not working . Try reflection
    public void findButtonsWindow() {
        List<buttonsWindow> list = new ArrayList<buttonsWindow>();
        Component[] components = base.getComponents();

        for (Component component : components) {
            try {
                // if (component.getClass().getName().toString().equals("GAME_BRICKS.buttonsWindow")){
                // component.getClass().getName() -> GAME_BRICKS.patternWindow$buttonsWindow

                Field field = component.getClass().getDeclaredField("text");
                field.setAccessible(true);
                Object value = field.get(component);

                if (value.equals("Start")) {
                    Class<?> c = component.getClass();
//                     Class<?> c = Class.forName("GAME_BRICKS.patternWindow$buttonsWindow"); 
                    Method m = c.getDeclaredMethod("setDisabledState", boolean.class);
//                     Object o =  m.invoke(c, true);

//                     buttonsWindow bw = new buttonsWindow("Start",  80, 35, 12, 23);
                    Constructor con = component.getClass().getConstructor(String.class, int.class, int.class, int.class,
                            int.class);
                    Field f = component.getClass().getDeclaredField("disabled");
                    f.setAccessible(true);
                    f.set(con, true);
                    System.out.println(f.toString());
                }
               // System.out.println(value);

//                Class<?> c = Class.forName("mypackage.MyClass");
//                Constructor<?> cons = c.getConstructor(String.class, Integer.class,Integer.class,Integer.class,
//                        Integer.class);
                // components[i].setEnabled(false);
                //list.add((JTextField)component);
                //  return component;
                // }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public class buttonsWindow extends JComponent implements MouseListener {

        boolean focus = false;
        String text;
        int action = -1;
        int outW, outH;
        boolean handLocation = false;
        float xLoc, yLoc;
        String id;
        boolean disabled = false;

        public buttonsWindow(String str) {
            text = str;
            outW = 70;
            outH = 35;
            this.addMouseListener(this);
        }

        public buttonsWindow(String str, int outX, int outY, int xLoc, int yLoc) {
            text = str;
            handLocation = true;
            this.outW = outX;
            this.outH = outY;
            this.xLoc = xLoc;
            this.yLoc = yLoc;
            this.addMouseListener(this);
        }

        public void setButtonAction(int a) {
            action = a;
        }

        private int getButtonAction() {
            return action;
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
        
        public boolean getDisabledState() {
            return disabled;
        }

        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            RoundRectangle2D rect = new RoundRectangle2D.Double(0, 0, outW, outH, 25, 20);
            int w = (int) rect.getWidth();
            int h = (int) rect.getHeight();
            g2.setStroke(new BasicStroke(0.5f));

            if (!focus && !disabled) {
                g2.setPaint(new GradientPaint(0, 0, new Color(84, 255, 124), 0, h, new Color(255, 243, 15), false));// 192,255,158
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
            TextLayout textLayout = new TextLayout(text, font, g2.getFontRenderContext());
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2.setPaint(Color.black);
            textLayout.draw(g2, xLoc, yLoc);

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
            if (getButtonAction() == 0) {
                window.setVisible(false);
                // System.out.println("Action 0: " + action);
            }
            if (getButtonAction() == 1) {

            }
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

}
