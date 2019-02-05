package PRANAYAMA;

import PRANAYAMA.MyCheckBoxUI.UncheckedIcon;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicCheckBoxUI;

public class MyCheckBoxUI extends BasicCheckBoxUI
        implements java.io.Serializable, MouseListener, FocusListener {

    private final static MyCheckBoxUI m_buttonUI
            = new MyCheckBoxUI();

    private static UncheckedIcon uncheckedIcon;
    private static CheckedIcon checkedIcon;
    private AbstractButton b;

    protected Color m_backgroundNormal = null;
    protected Color m_foregroundNormal = null;
    protected Color m_foregroundActive = null;
    protected Icon m_checkedIcon = null;
    protected Icon m_uncheckedIcon = null;
    protected Icon m_pressedCheckedIcon = null;
    protected Icon m_pressedUncheckedIcon = null;
    protected Color m_focusBorder = null;
    protected int m_textIconGap = -1;

    public MyCheckBoxUI() {
//        UIManager.put("CheckBoxUI", "PRANAYAMA.MyCheckBoxUI");

        uncheckedIcon = new UncheckedIcon();
        checkedIcon = new CheckedIcon();

        UIManager.put("CheckBox.icon", new UncheckedIcon());
        UIManager.put("CheckBox.iconChecked", new CheckedIcon());
//        System.err.println("ICON: " + m_uncheckedIcon.toString());
    }

    public static ComponentUI createUI(JComponent c) {
        // return m_buttonUI;
        return new MyCheckBoxUI();
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        m_backgroundNormal = UIManager.getColor("CheckBox.background");
        m_foregroundNormal = UIManager.getColor("CheckBox.foreground");
        m_foregroundActive = UIManager.getColor("CheckBox.activeForeground");

        m_checkedIcon = UIManager.getIcon("CheckBox.iconChecked");
        m_uncheckedIcon = UIManager.getIcon("CheckBox.icon");
        m_pressedCheckedIcon = UIManager.getIcon("CheckBox.iconPressedChecked");
        m_pressedUncheckedIcon = UIManager.getIcon("CheckBox.iconPressed");
        m_focusBorder = UIManager.getColor("CheckBox.focusBorder");

        m_textIconGap = UIManager.getInt("CheckBox.textIconGap");
        c.setBackground(m_backgroundNormal);
        c.addMouseListener(this);
        c.addFocusListener(this);
    }

    @Override
    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);
        c.removeMouseListener(this);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        b = (AbstractButton) c;
        ButtonModel model = b.getModel();

        Dimension oldDim = b.getSize();
        int w = uncheckedIcon.getIconWidth();
        int h = uncheckedIcon.getIconHeight();
        b.setSize(oldDim.width, h);
        Dimension dim = b.getSize();

        g.setFont(c.getFont());
        FontMetrics fm = g.getFontMetrics();

        g.setColor(b.getForeground());

        String text = b.getText();
        int x = icon.getIconWidth() + m_textIconGap + 10;
        int y = ((dim.height + fm.getAscent()) / 2);

//        if (b.isEnabled()) {

            if (model.isPressed() && model.isSelected()) {
//            icon = m_pressedCheckedIcon; //
                // icon = new ImageIcon("src/Icons/pubox.png");
                
                changeIcon();
                icon.paintIcon(null, g, 0, 0);
                g.drawString(text, x, y);

//            System.out.println("1");
                g.dispose();
            } else if (model.isPressed() && !model.isSelected()) {
//            icon = m_pressedUncheckedIcon;
//            icon = new ImageIcon("src/Icons/pubox.png");
                
                changeIcon();
                icon.paintIcon(null, g, 0, 0);
                g.drawString(text, x, y);
                

          //  System.out.println("2");
                // g.dispose();    
            } // 
            else if (!model.isPressed() && model.isSelected()) {
                icon = checkedIcon;
                icon.paintIcon(null, g, 0, 0);
                g.drawString(text, x, y);

                // System.out.println("3");
                g.dispose();
            } // DEFAULT STATE 
            else if (!model.isPressed() && !model.isSelected()) {
                // icon = m_checkedIcon;
                icon = null;
                changeIcon();
                icon.paintIcon(null, g, 0, 0);

                // b.setBackground(Color.ORANGE); 
                g.drawString(text, x, y);
//            System.out.println("0");
                g.dispose();
            }
        
        

//        System.out.println("w:" + b.getSize().width + " " + "h: " + b.getSize().height);
//        System.out.println("x:" + x + " " + "y: " + y);
//        if (b.isFocusPainted() && b.hasFocus()) {
//            g.setColor(m_focusBorder);
//            Insets bi = b.getBorder().getBorderInsets(b);
//            g.drawRect(x - 2, y - fm.getAscent() - 2, dim.width - x,
//                    fm.getAscent() + fm.getDescent() + 4);
//        }
//        g.dispose();
    }

    private Icon changeIcon() {
        if (icon == null) {
            icon = uncheckedIcon;
//            System.out.println("null: uncheckedIcon");
            return icon;
        }
        if (icon == uncheckedIcon) {
            icon = checkedIcon;
//            System.out.println("uncheckedIcon: checkedIcon");
            return icon;
        }
        if (icon == checkedIcon) {
            icon = checkedIcon;
//            System.out.println("checkedIcon: checkedIcon");
            return icon;
        } else {
            return icon;
        }
    }

    public void setMyDefaultIcon(Icon defaultIcon) {
        icon = defaultIcon;
    }

    public Icon getMyDefaultIcon() {
        return icon;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        uncheckedIcon.setEntered(true);
        checkedIcon.setEntered(true);
    }

    public void mouseReleased(MouseEvent e) {
        uncheckedIcon.setEntered(false);
        checkedIcon.setEntered(false);
    }

    public void mouseEntered(MouseEvent e) {
        JComponent c = (JComponent) e.getComponent();
        // c.setForeground(m_foregroundActive);
        c.setForeground(new Color(136, 196, 255));

        uncheckedIcon.setEntered(true);
        checkedIcon.setEntered(true);
        c.repaint();
//        System.out.println("Entered");
    }

    public void mouseExited(MouseEvent e) {
        JComponent c = (JComponent) e.getComponent();
        // c.setForeground(m_foregroundNormal);
        c.setForeground(Color.BLUE);

        uncheckedIcon.setEntered(false);
        checkedIcon.setEntered(false);
        c.repaint();
//        System.out.println("Exited");
    }

    @Override
    public void focusGained(FocusEvent e) {
//         System.out.println("focusGained");
    }

    @Override
    public void focusLost(FocusEvent e) {
//        System.out.println("focusLost");
    }

    class UncheckedIcon implements Icon {

        UncheckedIcon() {
        }

        private int width = 28;
        private int height = 28;
        private boolean isEntered = false;

        private BasicStroke stroke = new BasicStroke(4);

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g.create();

            if (!getEntered()) {
                g2d.setColor(Color.GREEN);
            } else {
                g2d.setColor(new Color(187, 255, 221));
            }

            g2d.fillRect(x + 1, y + 1, width - 2, height - 2);

            if (!getEntered()) {
                g2d.setColor(Color.BLUE);
            } else {
                g2d.setColor(new Color(174, 174, 255));
            }

            g2d.drawRect(x + 1, y + 1, width - 2, height - 2);
            g2d.drawRect(x + 2, y + 2, width - 4, height - 4);

            g2d.setColor(Color.WHITE);
            g2d.drawRect(x + 3, y + 3, width - 4, height - 4);

            if (!getEntered()) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(new Color(255, 130, 130));
            }

            g2d.setStroke(stroke);
            g2d.drawLine(x + 8, y + 8, x + width - 8, y + height - 8);
            g2d.drawLine(x + 8, y + height - 8, x + width - 8, y + 8);

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

    class CheckedIcon implements Icon {

        CheckedIcon() {
        }

        private int width = 28;
        private int height = 28;
        private boolean isEntered = false;

        private BasicStroke stroke = new BasicStroke(3);

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g.create();

            if (!getEntered()) {
                g2d.setColor(Color.GREEN);
            } else {
                g2d.setColor(new Color(187, 255, 221));
            }

            g2d.fillRect(x + 1, y + 1, width - 2, height - 2);

            if (!getEntered()) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(new Color(255, 130, 130));
            }

            g2d.drawRect(x + 1, y + 1, width - 2, height - 2);
            g2d.drawRect(x + 2, y + 2, width - 4, height - 4);

            g2d.setColor(Color.WHITE);
            g2d.drawRect(x + 3, y + 3, width - 4, height - 4);

            if (!getEntered()) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(new Color(255, 130, 130));
            }

            g2d.setStroke(stroke);
            GeneralPath path = new GeneralPath();
            path.moveTo(x + 8 + 1, height / 2);
            path.lineTo(width / 2 + 1, height - 8);
            path.lineTo(width - 7 + 0, 8);
//            path.closePath();
            g2d.draw(path);

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

    static JCheckBox checkbox, checkbox2;
    static int c2 = 0, c1 = 0;
    static boolean b1 = true;
    static boolean b2 = false;
    static ItemListener L1, L2;

    public static void main(String[] args) {

        // UIManager.put("CheckBoxUI", "PRANAYAMA.MyCheckBoxUI");
        JFrame frame = new JFrame("Popup JComboBox");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        MyCheckBoxUI boxui = new MyCheckBoxUI();
//        MyCheckBoxUI.uncheckedIcon = boxui.new UncheckedIcon();
//        MyCheckBoxUI.checkedIcon = boxui.new CheckedIcon();
//
//        MyCheckBoxUI boxui2 = new MyCheckBoxUI();
//        MyCheckBoxUI.uncheckedIcon = boxui2.new UncheckedIcon();
//        MyCheckBoxUI.checkedIcon = boxui2.new CheckedIcon();
        checkbox = new JCheckBox("Test");
        checkbox.setUI((MyCheckBoxUI) MyCheckBoxUI.createUI(checkbox));
        checkbox.setSelected(true);
        checkbox.setEnabled(true); 
        checkbox.setBounds(35, 35, 120, 45);
        checkbox.setForeground(Color.BLUE);
        checkbox.addItemListener(L1);

        ItemListener L1 = new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
//                System.out.println(e.getStateChange() == ItemEvent.DESELECTED //SELECTED
//                    ? "DESELECTED1" : "SELECTED1");
//                if (checkbox.isSelected()) {
//                    checkbox.setSelected(false);
//                } else {
//                    checkbox.setSelected(true);
//                }
                checkbox2.setSelected(false);

                c1++;

                System.out.println("1: " + c1);
            }
        };

//        checkbox.setIcon(MyCheckBoxUI.checkedIcon); 
//        checkbox.setSelectedIcon(MyCheckBoxUI.MissingIcon);
//        checkbox.setRolloverIcon(MissingIcon);
//        checkbox.setRolloverSelectedIcon(MissingIcon);
//         UIManager.put("CheckBox.icon", uncheckedIcon);
//         UIManager.put("CheckBox.iconChecked", MyCheckBoxUI.checkedIcon);
//        checkbox.setIcon(MyCheckBoxUI.checkedIcon);
//        UIManager.put("CheckBox.icon", checkedIcon);
//        UIManager.put("CheckBox.iconChecked", MyCheckBoxUI.uncheckedIcon);
//        checkbox.setIcon(MyCheckBoxUI.checkedIcon);
        frame.add(checkbox);

// =======================================================        
        checkbox2 = new JCheckBox("Test");
        checkbox2.setBounds(35, 155, 120, 45);
        checkbox2.setForeground(Color.BLUE);
//        UIManager.put("CheckBox.icon", uncheckedIcon);
//        UIManager.put("CheckBox.iconChecked", MyCheckBoxUI.checkedIcon);
//        checkbox2.setIcon(MyCheckBoxUI.uncheckedIcon);

        checkbox2.setUI((MyCheckBoxUI) MyCheckBoxUI.createUI(checkbox2));

//        checkbox2.addItemListener(new ItemListener() {
//
//            @Override
//            public void itemStateChanged(ItemEvent e) {
////                System.out.println(e.getStateChange() == ItemEvent.DESELECTED //SELECTED
////                    ? "DESELECTED2" : "SELECTED2");
////                checkbox2.setSelected(true);
//
//               // checkbox = new JCheckBox("Test");
//                //  checkbox.removeItemListener(L1); 
//                checkbox.setSelected(false);
//
//                c2++;
//
//                System.out.println("2: " + c2);
//            }
//        });

        frame.add(checkbox2);

        frame.setSize(300, 300);
        frame.setVisible(true);
    }

}
