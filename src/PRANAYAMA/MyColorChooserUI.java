package PRANAYAMA;

//import javax.swing.colorchooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.RepaintManager;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.colorchooser.DefaultColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.PanelUI;
import javax.swing.text.SimpleAttributeSet;

public class MyColorChooserUI {

    JFrame frame;
    // DefaultColorSelectionModel mod;
    JPanel base;
    JColorChooser chooser;
    utils.buttonsWindow apply, cancel;
    Font newFont;
    Color newColor;
    SimpleAttributeSet attributes;
    views Views;
    JPanel controlPanel;
    GridBagConstraints c;
    String title;

    PRANAYAMA pranaMain;

    MyColorChooserUI(String titl) { // PRANAYAMA prana,
        this.title = titl;

        if (title == null) {
            title = "";
        }

//        base.setOpaque(true);
        componentBevelBorder bevel = new componentBevelBorder(0, 2, getTitle(), 1, 1, new Font("Times New Roman", 1, 12),
                new Color(0, 100, 0), Color.white,
                new Color(190, 220, 220),
                new Color(100, 130, 130),
                new Color(150, 180, 180));
        bevel.setFloatable(false);
        bevel.setResizableLimit(false);

        // help customize form of Window
        frame = bevel.getJFrameForm();
        frame.setUndecorated(true);
        frame.setLayout(new BorderLayout());
        frame.setSize(660, 595);

        base = new myPanelUI();
        base.setSize(650, 595);

        bevel.getDnDMouseListener(base);
        // set Behavior for control buttons in bevelBorder :
        // third parameter 0 -> frame,
        //                 1 -> window 
        bevel.getControlButtonsBehavior(base, 1, 0); // if '1' hide JFrame if '0' close JFrame
        base.setBorder(bevel);
        // Set up the color chooser panel and attach a change listener so that color
        // updates are reflected in our preview label.
        chooser = new JColorChooser(Color.BLACK);
        // GradientPaint gp = new GradientPaint(0, 0, new Color(255, 242, 226), w, h, new Color(213, 255, 122), false);
        // chooser.setBackground(Color.DARK_GRAY); 
        chooser.setPreviewPanel(new JPanel(null));
        testColor(chooser);
        chooser.getSelectionModel().addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // update color in PreviewLabel
                updatePreviewColor();
            }
        });
        chooser.setBounds(0, 0, base.getWidth(), base.getHeight());
        AbstractColorChooserPanel[] accp = chooser.getChooserPanels();

//        base.add(chooser, BorderLayout.CENTER);
        base.setLayout(new GridBagLayout());
        c = new GridBagConstraints();

        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;    // Column 0
        c.gridy = 0;    // Row 0
//        c.fill = GridBagConstraints.NORTH;
        c.anchor = GridBagConstraints.NORTH;
//        c.ipadx = 10;   // Increases component width by 10 pixels
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 1;   // Span across 2 column
        c.gridheight = 1;
        base.add(chooser, c);

// ===================== Buttons 'OK', 'Cancel' ================================        
        apply = new utils.buttonsWindow("Apply", 80, 35, 12, 23);
        apply.setMinimumSize(new Dimension(85, 40));
        apply.setPreferredSize(new Dimension(85, 40));

        cancel = new utils.buttonsWindow("Cancel", 90, 35, 12, 23);
        cancel.setMinimumSize(new Dimension(95, 40));
        cancel.setPreferredSize(new Dimension(95, 40));

//        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 2));
        controlPanel = new myPanelUI();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 2));
        controlPanel.setMinimumSize(new Dimension(650, 45));
        controlPanel.setPreferredSize(new Dimension(650, 45));
        controlPanel.add(apply);
        controlPanel.add(cancel);
        //   base.add(controlPanel);

        c.gridx = 0;
        c.gridy = 1;
        base.add(controlPanel, c);

//        c.gridx = 0;    
//        c.gridy = 2; 
        // base.add(previewPanel(), c);
//        setMyPreviewPanel(null);
        frame.add(base);
        frame.setVisible(false);
    }

    protected void resetBorder() {
        componentBevelBorder bevel = new componentBevelBorder(0, 2, getTitle(), 1, 1, new Font("Times New Roman", 1, 12),
                new Color(0, 100, 0), Color.white,
                new Color(190, 220, 220),
                new Color(100, 130, 130),
                new Color(150, 180, 180));
        bevel.setFloatable(false);
        bevel.setResizableLimit(false);

        // help customize form of Window
        frame = bevel.getJFrameForm();
        frame.setUndecorated(true);
        frame.setLayout(new BorderLayout());
        frame.setSize(660, 595);

        bevel.getDnDMouseListener(base);
        // set Behavior for control buttons in bevelBorder :
        // third parameter 0 -> frame,
        //                 1 -> window 
        bevel.getControlButtonsBehavior(base, 1, 0); // if '1' hide JFrame if '0' close JFrame
        base.setBorder(bevel);

        frame.add(base);
        frame.setVisible(false);
    }

    class myPanelUI extends JPanel {

        int w;
        int h;

        myPanelUI() {
            w = frame.getWidth() - 1;
            h = frame.getHeight() - 1;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            Rectangle2D rect = new Rectangle2D.Double(0, 0, w, h);

            g2.setPaint(new GradientPaint(0, 0, new Color(255, 242, 226), w, h, new Color(213, 255, 122), false));
            g2.fill(rect);
            g2.setColor(Color.BLUE);
            g2.draw(rect);
        }

//        base.setMinimumSize(frame.getMinimumSize());
//        base.setPreferredSize(frame.getPreferredSize());
//        return gradPanel;
    }

    protected void setMyPreviewPanel(JPanel panel) {
        if (panel != null) {
            c.gridx = 0;
            c.gridy = 2;
            base.add(panel, c);
        }
    }

    protected void removePreview(JPanel parent, JPanel child) {
        GridBagLayout layout = (GridBagLayout) parent.getLayout();
        GridBagConstraints gbc = layout.getConstraints(child);
        parent.remove(child);
        // colorChooser.base.add(previewView.previewBasicPanel, gbc);
        parent.revalidate();
        parent.repaint();
    }

    protected void setTitle(String text) {
        this.title = text;
    }

    protected String getTitle() {
        return title;
    }

    // this method  allows retrieve Color 
    protected Color getNewColor() {
        return newColor;
    }

    // this method  allows retrieve Font
    protected Font getnewFont() {
        return newFont;
    }

    // this method  allows save in   --->  attribute  all changes
    protected SimpleAttributeSet getAttributes() {
        return attributes;
    }

    protected MyColorChooserUI setRadioButtonCover(String str, MyColorChooserUI chooser) {
        // UIManager.put("RadioButtonUI", "PRANAYAMA.MyRadioButtonUI"); 
        if (!str.equals("")) {
            UIManager.put("RadioButtonUI", str);
            chooser = new MyColorChooserUI(title);
            return chooser;
        } else {
            String s = UIManager.getString("RadioButtonUI");
            UIManager.put("RadioButtonUI", s);
            chooser = new MyColorChooserUI(title);
            return chooser;
        }
    }

    protected MyColorChooserUI setSliderCover(String str, MyColorChooserUI chooser) {
        // UIManager.put("SliderUI", "PRANAYAMA.MySliderUI"); 
        if (!str.equals("")) {
            UIManager.put("SliderUI", str);
            chooser = new MyColorChooserUI(title);
            return chooser;
        } else {
            String s = UIManager.getString("SliderUI");
            UIManager.put("SliderUI", s);
            chooser = new MyColorChooserUI(title);
            return chooser;
        }
    }

    protected MyColorChooserUI setSpinnerCover(String str, MyColorChooserUI chooser) {
        // UIManager.put("SpinnerUI", "PRANAYAMA.LeftRightSpinnerUI");
        if (!str.equals("")) {
            UIManager.put("SpinnerUI", str);
            chooser = new MyColorChooserUI(title);
            return chooser;
        } else {
            String s = UIManager.getString("SpinnerUI");
            UIManager.put("SpinnerUI", s);
            chooser = new MyColorChooserUI(title);
            return chooser;
        }
    }

    protected MyColorChooserUI setTabbedPaneCover(String str, MyColorChooserUI chooser) {
        //  UIManager.put("TabbedPaneUI", "PRANAYAMA.MyTabbedPaneUI");
        if (!str.equals("")) {
            UIManager.put("TabbedPaneUI", str);
            chooser = new MyColorChooserUI(title);
            return chooser;
        } else {
            String s = UIManager.getString("TabbedPaneUI");
            UIManager.put("TabbedPaneUI", s);
            chooser = new MyColorChooserUI(title);
            return chooser;
        }
    }

    protected void updatePreviewColor() {

//        Views.graphCompLine.setColor1(chooser.getColor());
//        Views.graphCompLine.repaint();
//        preview.repaint();
//        System.out.println(": "+ chooser.getColor());
//        System.out.println(":: "+ Views.graphCompLine.getColor1());
        //  previewLabel.setForeground(chooser.getColor());
        // manually force label to repaint
        // previewLabel.repaint();
    }

    public void testColor(JColorChooser colorChooser) {
        AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
        for (AbstractColorChooserPanel p : panels) {
            String displayName = p.getDisplayName();

            p.setBackground(Color.LIGHT_GRAY);
            // p = new myPanelUI();
//            p.setUI(new myPanelUI()); 

            if (displayName.equals("HSV")) {
                Component[] comps = p.getComponents();
                for (Component component : comps) {
                    // System.out.println(component);
                    // if(component instanceof javax.swing.colorchooser.DiagramComponent) 
                    //      System.out.println("radio");
                }
            }
//            switch (displayName) {
//                case "HSV":
//                    colorChooser.removeChooserPanel(p);
//                    break;
//                case "HSL":
//                    colorChooser.removeChooserPanel(p);
//                    break;
//                case "CMYK":
//                    colorChooser.removeChooserPanel(p);
//                    break;
//            }
        }
    }

    static MyColorChooserUI chooser1;

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setLayout(new FlowLayout());
        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Container content = f.getContentPane();

//        UIManager.put("RadioButtonUI", "PRANAYAMA.MyRadioButtonUI");
//        UIManager.put("SliderUI", "PRANAYAMA.MySliderUI");
//        UIManager.put("SpinnerUI", "PRANAYAMA.LeftRightSpinnerUI");
//          UIManager.put("TabbedPaneUI", "PRANAYAMA.MyTabbedPaneUI");
        chooser1 = new MyColorChooserUI("test");
        chooser1 = chooser1.setRadioButtonCover("PRANAYAMA.MyRadioButtonUI", chooser1);
        chooser1 = chooser1.setSliderCover("PRANAYAMA.MySliderUI", chooser1);
        chooser1 = chooser1.setSpinnerCover("PRANAYAMA.LeftRightSpinnerUI", chooser1);
        chooser1 = chooser1.setTabbedPaneCover("PRANAYAMA.MyTabbedPaneUI", chooser1);
        chooser1.setTitle("1111");
        //chooser1.setMyPreviewPanel(new previewColorPanel(3));
//        final Container c = chooser1.frame.getContentPane();

        final JButton button = new JButton("Custom ColorChooser");
        button.addActionListener(new ActionListener() {
            //boolean first = true;

            public void actionPerformed(ActionEvent e) {
                chooser1.frame.setVisible(true);
                if (chooser1.frame.getFont() != null) {
//                    button.setFont(chooser.getnewFont());
                    button.setForeground(chooser1.getNewColor());

//                    c.setBackground(chooser1.getNewColor());
                }
            }
        });

        f.add(button);
        f.setVisible(true);
    }

}
