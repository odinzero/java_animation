package PRANAYAMA;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.AbstractButton;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.*;

public class MyRadioButtonUI extends BasicRadioButtonUI implements java.io.Serializable,
        MouseListener {

    private final static MyRadioButtonUI m_buttonUI
            = new MyRadioButtonUI();
    protected Color m_backgroundNormal = null;
    protected Color m_foregroundNormal = null;
    protected Color m_foregroundActive = null;
    protected Icon m_checkedIcon = null;
    protected Icon m_uncheckedIcon = null;
    protected Icon m_pressedCheckedIcon = null;
    protected Icon m_pressedUncheckedIcon = null;
    protected Color m_focusBorder = null;
    protected int m_textIconGap = -1;

    public MyRadioButtonUI() {

        UIManager.put("RadioButton.icon", new CheckedUnchekedIcon(0));
        UIManager.put("RadioButton.iconPressed", new CheckedUnchekedIcon(2));
        UIManager.put("RadioButton.iconChecked", new CheckedUnchekedIcon(1));
    }

    public static ComponentUI createUI(JComponent c) {
        return m_buttonUI;
    }
    private static Dimension size = new Dimension();
    private static Rectangle viewRect = new Rectangle();
    private static Rectangle iconRect = new Rectangle();
    private static Rectangle textRect = new Rectangle();
    private static Rectangle prefViewRect = new Rectangle();
    private static Rectangle prefIconRect = new Rectangle();
    private static Rectangle prefTextRect = new Rectangle();
    private static Insets prefInsets = new Insets(0, 0, 0, 0);

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        m_backgroundNormal = UIManager.getColor("RadioButton.background");
        m_foregroundNormal = UIManager.getColor("RadioButton.foreground");
        m_foregroundActive = UIManager.getColor("RadioButton.activeForeground");
        m_checkedIcon = UIManager.getIcon("RadioButton.iconChecked");
        m_uncheckedIcon = UIManager.getIcon("RadioButton.icon");
        m_pressedCheckedIcon = UIManager.getIcon("RadioButton.iconPressedChecked");
        m_pressedUncheckedIcon = UIManager.getIcon("RadioButton.iconPressed");
        m_focusBorder = UIManager.getColor("RadioButton.focusBorder");
        m_textIconGap = UIManager.getInt("RadioButton.textIconGap");
        c.setBackground(m_backgroundNormal);
        c.addMouseListener(this);
    }

    @Override
    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);
        c.removeMouseListener(this);
    }

//    @Override
//    public Dimension getPreferredSize(JComponent c) {
//        if (c.getComponentCount() > 0) {
//            return null;
//        }
//
//        AbstractButton b = (AbstractButton) c;
//
//        String text = b.getText();
//
//        Icon buttonIcon = (Icon) b.getIcon();
//        if (buttonIcon == null) {
//            buttonIcon = getDefaultIcon();
//        }
//
//        Font font = b.getFont();
//        FontMetrics fm = b.getFontMetrics(font);
//
//        prefViewRect.x = prefViewRect.y = 0;
//        prefViewRect.width = Short.MAX_VALUE;
//        prefViewRect.height = Short.MAX_VALUE;
//        prefIconRect.x = prefIconRect.y = prefIconRect.width = prefIconRect.height = 0;
//        prefTextRect.x = prefTextRect.y = prefTextRect.width = prefTextRect.height = 0;
//
//        SwingUtilities.layoutCompoundLabel(
//                c, fm, text, buttonIcon,
//                b.getVerticalAlignment(), b.getHorizontalAlignment(),
//                b.getVerticalTextPosition(), b.getHorizontalTextPosition(),
//                prefViewRect, prefIconRect, prefTextRect,
//                text == null ? 0 : b.getIconTextGap());
//
//        // find the union of the icon and text rects (from Rectangle.java)
//        int x1 = Math.min(prefIconRect.x, prefTextRect.x);
//        int x2 = Math.max(prefIconRect.x + prefIconRect.width,
//                prefTextRect.x + prefTextRect.width);
//        int y1 = Math.min(prefIconRect.y, prefTextRect.y);
//        int y2 = Math.max(prefIconRect.y + prefIconRect.height,
//                prefTextRect.y + prefTextRect.height);
//        int width = x2 - x1;
//        int height = y2 - y1;
//
//        prefInsets = b.getInsets(prefInsets);
//        width += prefInsets.left + prefInsets.right;
//        height += prefInsets.top + prefInsets.bottom;
//        return new Dimension(width, height);
//    }
    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();
        g.setFont(c.getFont());
        FontMetrics fm = g.getFontMetrics();
        Icon icon = m_uncheckedIcon;

        Dimension oldDim = b.getSize();
        int w = m_uncheckedIcon.getIconWidth();
        int h = m_uncheckedIcon.getIconHeight();
        b.setSize(oldDim.width, h);
        Dimension dim = b.getSize();

        if (model.isPressed() && model.isSelected()) //icon = m_pressedCheckedIcon ;
        {
            icon = m_uncheckedIcon;
            System.out.println("1");
        } else if (model.isPressed() && !model.isSelected()) {
            icon = m_pressedUncheckedIcon;
            System.out.println("2");
        } else if (!model.isPressed() && model.isSelected()) {
            icon = m_checkedIcon;
            System.out.println("3");
        }
        g.setColor(b.getForeground());
        int x = 0;
        int y = (dim.height - icon.getIconHeight()) / 2;
        // int y = 0;
        icon.paintIcon(c, g, x, y);

        String caption = b.getText();
        x = icon.getIconWidth() + m_textIconGap + 5;
        y = (dim.height + fm.getAscent()) / 2 - 2;
        g.drawString(caption, x, y);
//        if (b.isFocusPainted() && b.hasFocus()) {
//            g.setColor(m_focusBorder);
//            Insets bi = b.getBorder().getBorderInsets(b);
//            g.drawRect(x - 2, y - fm.getAscent() - 2, d.width - x,
//                    fm.getAscent() + fm.getDescent() + 4);
//        }
    }

    class CheckedUnchekedIcon implements Icon {

        private int width = 20;
        private int height = 20;
        private boolean isEntered = false;

        private BasicStroke stroke = new BasicStroke((float) 1.0);

        int buttonState = 0 ;

        CheckedUnchekedIcon(int buttonState) {
            this.buttonState = buttonState;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setStroke(stroke);

            //g2d.setColor(UIManager.getColor("RadioButton.foreground"));
            RoundRectangle2D rr = new RoundRectangle2D.Double(x, y, width, height, 0, 0);
           // g2d.fill(rr); 

            double w1 = rr.getWidth() / 1.1;
            double h1 = rr.getHeight() / 1.1;
            double x1 = (width - w1) / 2;
            double y1 = (height - h1) / 2;

            double w2 = rr.getWidth() / 1.17;
            double h2 = rr.getHeight() / 1.17;
            double x2 = (width - w2) / 2;
            double y2 = (height - h2) / 2;

            double w3 = rr.getWidth() / 1.25;
            double h3 = rr.getHeight() / 1.25;
            double x3 = (width - w3) / 2;
            double y3 = (height - h3) / 2;

            double wC = rr.getWidth() / 1.5;
            double hC = rr.getHeight() / 1.5;
            double xC = (width - wC) / 2;
            double yC = (height - hC) / 2;

            // Draw outer circle
            Ellipse2D.Double outerEllipse1 = new Ellipse2D.Double(x1, y1, w1, h1);
            g2d.setColor(Color.WHITE);
            g2d.fill(outerEllipse1);
            g2d.setColor(Color.DARK_GRAY);
            g2d.draw(outerEllipse1);

            // Draw outer circle
            Ellipse2D.Double outerEllipse2 = new Ellipse2D.Double(x2, y2, w2, h2);
            g2d.setColor(Color.WHITE);
            g2d.fill(outerEllipse2);
            g2d.setColor(Color.DARK_GRAY);
            g2d.draw(outerEllipse2);

            // Draw outer circle
            Ellipse2D.Double outerEllipse3 = new Ellipse2D.Double(x3, y3, w3, h3);
            g2d.setColor(Color.WHITE);
            g2d.fill(outerEllipse3);
            g2d.setColor(Color.DARK_GRAY);
            g2d.draw(outerEllipse3);

            
            switch(buttonState){
                default:break;
                // unchecked state    
                case 0: g2d.setColor(Color.RED);
                break;
                // checked state    
                case 1:
                    g2d.setColor(new Color(0, 0, 255));
                    break;
                // pressed state    
                case 2:
                    g2d.setColor(new Color(128, 128, 255));
                    break;
            }    
            
            Ellipse2D.Double innerEllipse = new Ellipse2D.Double(xC, yC, wC, hC);
            g2d.fill(innerEllipse);

//            g2d.setStroke(stroke);
//            GeneralPath path = new GeneralPath();
//            path.moveTo(x + 8 + 1, height / 2);
//            path.lineTo(width / 2 + 1, height - 8);
//            path.lineTo(width - 7 + 0, 8);
//            g2d.draw(path);
            g2d.dispose();
        }

        @Override
        public int getIconWidth() {
            return width;
        }

        @Override
        public int getIconHeight() {
            return height;

        }

        protected void setEntered(boolean bool) {
            this.isEntered = bool;
        }

        protected boolean getEntered() {
            return isEntered;
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        System.out.println("Hello!");
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        JComponent c = (JComponent) e.getComponent();
        c.setForeground(m_foregroundActive);
        c.repaint();
    }

    public void mouseExited(MouseEvent e) {
        JComponent c = (JComponent) e.getComponent();
        c.setForeground(m_foregroundNormal);
        c.repaint();
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        MyRadioButtonUI pl = new MyRadioButtonUI();

        JRadioButton b = new JRadioButton("Hello");
        b.setUI(pl);
        MyRadioButtonUI.createUI(b);

        JFrame fr = new JFrame();
        fr.setLayout(new FlowLayout());
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(400, 400);
        fr.add(b);
        fr.setVisible(true);

    }
}
