package PRANAYAMA;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class viewsWindow {

    patternWindow views;
    patternWindow.buttonsWindow apply, close;
    JCheckBox box_numberView, box_horDiagrammaView, box_verDiagrammaView, box_circleDiagrammaView;
    basicPanel preview;
    JPanel contentPreview;

    // previewColorPanel previewView;
    views Views;

    L1 listener1;
    L2 listener2;
    L3 listener3;
    L4 listener4;

    PRANAYAMA pranaMain;

    viewsWindow(PRANAYAMA prana) { // PRANAYAMA prana
        this.pranaMain = prana;

        init();
    }

    public void initDefaultView() {
        // show NUMBER in  preview  panel
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.NUMBER_VIEW) {
            Views = new views(pranaMain, true, preview);
            //set OPACITY of VIEW
            Views.setViewOpaque(true);
            // set all values from main view or colorWindow
            getTransientValuesFromColorWindow();
//            System.out.println("inside viewsWindow");
            // set initial selected combobox index
            box_numberView.setSelected(true);
            box_horDiagrammaView.setSelected(false);
            box_verDiagrammaView.setSelected(false);
            box_circleDiagrammaView.setSelected(false);
        }
        // show HORIZONTAL VIEW in  preview  panel
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.HORIZONTAL_VIEW) {

            int index = pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex();
            Views = new views(pranaMain, true, preview, 1, index);
            //set OPACITY of VIEW
            Views.setViewOpaque(true);
            // set all values from main view or colorWindow
            getTransientValuesFromColorWindow();
            // set initial selected combobox index
            box_numberView.setSelected(false);
            box_horDiagrammaView.setSelected(true);
            box_verDiagrammaView.setSelected(false);
            box_circleDiagrammaView.setSelected(false);
        }
        // show VERTICAL VIEW in  preview  panel
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.VERTICAL_VIEW) {

            int index = pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex();
            Views = new views(pranaMain, true, preview, 2, index);
            //set OPACITY of VIEW
            Views.setViewOpaque(true);
            // set all values from main view or colorWindow
            getTransientValuesFromColorWindow();
            // set initial selected combobox index
            box_numberView.setSelected(false);
            box_horDiagrammaView.setSelected(false);
            box_verDiagrammaView.setSelected(true);
            box_circleDiagrammaView.setSelected(false);
        }
        // show CIRCLE VIEW in  preview  panel
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.CIRCLE_VIEW) {

            int index = pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex();
            Views = new views(pranaMain, true, preview, 3, index);
            //set OPACITY of VIEW
            Views.setViewOpaque(true);
            // set all values from main view or colorWindow
            getTransientValuesFromColorWindow();
            // set initial selected combobox index
            box_numberView.setSelected(false);
            box_horDiagrammaView.setSelected(false);
            box_verDiagrammaView.setSelected(false);
            box_circleDiagrammaView.setSelected(true);
        }
    }

    private void setBackgroundCommonOrGradientColor() {
        //  see viewsColorWindow.java file
        // BACKGROUND GRADIENT or COMMON COLOR

        if (pranaMain.colorsWindow != null) {
            if (pranaMain.colorsWindow.colorBackground != null) {
                // checkbox 'use Gradient'
                if (!pranaMain.colorsWindow.box_useGradientOrNot.isSelected()) {
                    preview.setType(100);
                    preview.setColorBackground(pranaMain.colorsWindow.colorBackground);
                } else {
                    if (pranaMain.colorsWindow.indexGradient == -1) {
                        preview.setType(pranaMain.basic.getType());
                    } else {
                        preview.setType(pranaMain.colorsWindow.indexGradient);
                    }
                }
            } else {
                // checkbox 'use Gradient'
                if (!pranaMain.colorsWindow.box_useGradientOrNot.isSelected()) {
                    preview.setType(100);
                    preview.setColorBackground(pranaMain.basic.getColorBackground());
                } else {
                    if (pranaMain.colorsWindow.indexGradient == -1) {
                        preview.setType(pranaMain.basic.getType());
                    } else {
                        preview.setType(pranaMain.colorsWindow.indexGradient);
                    }
                }
            }
        } else {
            if (pranaMain.basic.getType() > 0 && pranaMain.basic.getType() < 22) {
                preview.setType(pranaMain.basic.getType());
            } else {
                preview.setType(100);
                preview.setColorBackground(pranaMain.basic.getColorBackground());
            }
        }
    }
    
    protected void initBackgroundNumberView(JLabel label, Color colorDefault, Color colorNew) {
        if (pranaMain.colorsWindow == null) {
                label.setBackground(colorDefault);
                 System.out.println("1: " + colorDefault);
            } else {
                if (pranaMain.colorsWindow.color1 == null) {
                    label.setBackground(colorDefault);
                    System.out.println("1: " + colorDefault);
                } else {
                    label.setBackground(colorNew);
                    System.out.println("1: " + colorNew);
                }
            }
    }

    private void setValuesInPreviewPanel(Color foregroundColor, boolean opacity) {

        if (Views.label_Inhalation != null) {
            System.out.println("1: " + pranaMain.colorsWindow.color1);
            
//             initBackgroundNumberView(Views.label_Inhalation, pranaMain.view.label_Inhalation.getBackground(), pranaMain.colorsWindow.color1);
//            if (pranaMain.colorsWindow == null) {
//                Views.label_Inhalation.setBackground(pranaMain.view.label_Inhalation.getBackground());
//                System.out.println("1: " + pranaMain.view.label_Inhalation.getBackground());
//            } else {
//                if (pranaMain.colorsWindow.color1 == null) {
//                    Views.label_Inhalation.setBackground(pranaMain.view.label_Inhalation.getBackground());
//                } else {
//                    Views.label_Inhalation.setBackground(pranaMain.colorsWindow.color1);
//                }
//                System.out.println("2: " + pranaMain.colorsWindow.color1);
//            }
            Views.label_Inhalation.setForeground(foregroundColor);
            Views.label_Inhalation.setOpaque(opacity);
        }
        if (Views.label_Exhalation != null) {
            initBackgroundNumberView(Views.label_Exhalation, 
                                     pranaMain.view.label_Exhalation.getBackground(),
                                     pranaMain.colorsWindow.color3);
            Views.label_Exhalation.setForeground(foregroundColor);
            Views.label_Exhalation.setOpaque(opacity);
        }
        if (Views.label_breathhold_after_inhalation != null) {
           initBackgroundNumberView(Views.label_breathhold_after_inhalation, 
                                    pranaMain.view.label_breathhold_after_inhalation.getBackground(),
                                    pranaMain.colorsWindow.color2);
            Views.label_breathhold_after_inhalation.setForeground(foregroundColor);
            Views.label_breathhold_after_inhalation.setOpaque(opacity);
        }
        if (Views.label_breathhold_after_exhalation != null) {
            initBackgroundNumberView(Views.label_breathhold_after_exhalation, 
                                    pranaMain.view.label_breathhold_after_exhalation.getBackground(),
                                    pranaMain.colorsWindow.color4);
            Views.label_breathhold_after_exhalation.setForeground(foregroundColor);
            Views.label_breathhold_after_exhalation.setOpaque(opacity);
        }
        Views.label_numCycles.setOpaque(opacity);

        if (Views.graphCompLine != null) {
            if (pranaMain.colorsWindow.color1 != null) {
                Views.graphCompLine.setColor1(pranaMain.colorsWindow.color1);
            }
            if (pranaMain.colorsWindow.color2 != null) {
                Views.graphCompLine.setColor2(pranaMain.colorsWindow.color2);
            }
            if (pranaMain.colorsWindow.color3 != null) {
                Views.graphCompLine.setColor3(pranaMain.colorsWindow.color3);
            }
            if (pranaMain.colorsWindow.color4 != null) {
                Views.graphCompLine.setColor4(pranaMain.colorsWindow.color4);
            }
        }
    }

    protected void getTransientValuesFromColorWindow() {

        Color foreground = null;
        boolean opaque = false;

        if (pranaMain.colorsWindow == null) {

            foreground = pranaMain.view.getColorForeground();
            opaque = pranaMain.view.getViewOpaque();

            setValuesInPreviewPanel(foreground, opaque);
            // set BORDER FONT of VIEW
            Views.setBorderFont(pranaMain.view.getBorderFont());
        } else {

            foreground = (pranaMain.colorsWindow.colorForeground == null) ? pranaMain.view.getColorForeground()
                    : pranaMain.colorsWindow.colorForeground;
            opaque = pranaMain.colorsWindow.box_Opaque.isSelected();

            setValuesInPreviewPanel(foreground, opaque);
            // set BORDER FONT of VIEW
            Views.setBorderFont(pranaMain.colorsWindow.myFont);
        }
        setBackgroundCommonOrGradientColor();
    }

    private void init() {
        views = new patternWindow(pranaMain, "Views", true, 0, true, true, 0, 0, 400, 600);
        views.setWindowVisibility(false);
        Font font = new Font("Monotype Corsiva", Font.BOLD, 25);

//        previewView = new previewColorPanel(pranaMain);
        listener1 = new L1();
        listener2 = new L2();
        listener3 = new L3();
        listener4 = new L4();
// ========================= BUTTONS  'Apply', 'Start', 'Cancel' =========================
        // add button 'Start' to start download game     
        apply = views.new buttonsWindow("Apply", 80, 35, 12, 23);
//        start.setDisabledState(false);
//        start.addMouseListener(start_breathing);  
        apply.setBounds(100, 540, 85, 40);
        apply.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // SHOW window settingBreathing.java
                pranaMain.mainMenu.Breath.settingsBreath.settings.setWindowVisibility(true);
                // HIDE this window views
                views.setWindowVisibility(false);
            }
        });

        views.base.add(apply);

        // Action 0 <- it means close window
        views.addButtonsWindow2("Close", 200, 540, 65, 35, 7, 23, 70, 40)
                .addMouseListener(close());
// ============================ Views ==========================================        
        separator s1 = new separator();
        s1.setBounds(20, 40, 160, 10);

        Rectangle rect1 = new Rectangle(5, 2, 115, 30);
        buttons schema = new buttons(" Views ", rect1);
        schema.setBounds(155, 25, 110, 30);

        separator s2 = new separator();
        s2.setBounds(260, 40, 118, 10);
// ============================= Checkboxes ====================================
        box_numberView = new JCheckBox("set number view");
//        box_numberView.setSelected(true);
        box_numberView.setEnabled(false);
        box_numberView.setOpaque(false);
        box_numberView.setForeground(Color.BLUE);
        box_numberView.setFont(font);
        box_numberView.setBounds(25, 65, 350, 30);
        box_numberView.addItemListener(listener1);
        box_numberView.setUI((MyCheckBoxUI) MyCheckBoxUI.createUI(box_numberView));

        box_horDiagrammaView = new JCheckBox("set horizontal diagramma view");
//        box_horDiagrammaView.setSelected(false);
        box_horDiagrammaView.setOpaque(false);
        box_horDiagrammaView.setForeground(Color.BLUE);
        box_horDiagrammaView.setFont(font);
        box_horDiagrammaView.setBounds(25, 105, 360, 30);
        box_horDiagrammaView.addItemListener(listener2);
        box_horDiagrammaView.setUI((MyCheckBoxUI) MyCheckBoxUI.createUI(box_horDiagrammaView));

        box_verDiagrammaView = new JCheckBox("set vertical diagramma view");
//        box_verDiagrammaView.setSelected(false);
        box_verDiagrammaView.setOpaque(false);
        box_verDiagrammaView.setForeground(Color.BLUE);
        box_verDiagrammaView.setFont(font);
        box_verDiagrammaView.setBounds(25, 145, 350, 30);
        box_verDiagrammaView.addItemListener(listener3);
        box_verDiagrammaView.setUI((MyCheckBoxUI) MyCheckBoxUI.createUI(box_verDiagrammaView));

        box_circleDiagrammaView = new JCheckBox("set circle diagramma view");
//        box_circleDiagrammaView.setSelected(false);
        box_circleDiagrammaView.setOpaque(false);
        box_circleDiagrammaView.setForeground(Color.BLUE);
        box_circleDiagrammaView.setFont(font);
        box_circleDiagrammaView.setBounds(25, 185, 350, 30);
        box_circleDiagrammaView.addItemListener(listener4);
        box_circleDiagrammaView.setUI((MyCheckBoxUI) MyCheckBoxUI.createUI(box_circleDiagrammaView));
// ============================= Preview panel ====================================
        separator s3 = new separator();
        s3.setBounds(20, 235, 122, 10);

        Rectangle rect2 = new Rectangle(5, 2, 115, 30);
        buttons previewHeader = new buttons(" Preview ", rect2);
        previewHeader.setBounds(135, 220, 110, 30);

        separator s4 = new separator();
        s4.setBounds(250, 235, 128, 10);

        Border in1 = BorderFactory.createLineBorder(Color.blue, 4);
        Border out = BorderFactory.createEmptyBorder(8, 8, 8, 8);
        Border out1 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        Border out2 = BorderFactory.createTitledBorder(in1, "Preview", TitledBorder.ABOVE_TOP, TitledBorder.DEFAULT_JUSTIFICATION, null, Color.BLUE);

        preview = new basicPanel(pranaMain);
        preview.setLayout(new GridBagLayout());
        preview.setBounds(25, 260, 350, 275);
        preview.setBackground(Color.WHITE);
        preview.setBorder(BorderFactory.createCompoundBorder(out2, out));

        // set background PREVIEW panel
        if (pranaMain.basic.getType() > 0 && pranaMain.basic.getType() < 22) {
            preview.setType(pranaMain.basic.getType());
        } else {
            pranaMain.basic.setType(100);
            pranaMain.basic.setColorBackground(pranaMain.basic.getColorBackground());
        }

        contentPreview = new contentComponent();
        contentPreview.setPreferredSize(new Dimension(200, 200));
        contentPreview.setMinimumSize(new Dimension(200, 200));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 0, 5, 0);
        c.gridx = 0;    // Column 0
        c.gridy = 0;    // Row 0
//        c.fill = GridBagConstraints.WEST;
        c.anchor = GridBagConstraints.CENTER;
//        c.ipadx = 10;   // Increases component width by 10 pixels
        c.weightx = 0.0;
        c.weighty = 0.0;
//        c.gridwidth = 1;   // Span across 2 column
//        c.gridheight = 1;

        // init 'Views' according to selected index in combobox 'schema_breathing'
        // in file --> settingsBreathing.java
        initDefaultView();
        // preview.add(contentPreview, c);
//==================== layout components ========================================        
        views.base.add(s1);
        views.base.add(schema);
        views.base.add(s2);

        views.base.add(box_numberView);
        views.base.add(box_horDiagrammaView);
        views.base.add(box_verDiagrammaView);
        views.base.add(box_circleDiagrammaView);

        views.base.add(s3);
        views.base.add(previewHeader);
        views.base.add(s4);

        views.base.add(preview);
    }

    // NUMBER VIEW Checkbox
    class L1 implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            //  set default view to NUMBER
            pranaMain.vDefault.defaultView = pranaMain.vDefault.NUMBER_VIEW;

            // HORIZAONTAL VIEW Checkbox
            box_horDiagrammaView.removeItemListener(listener2);
            box_horDiagrammaView.setSelected(false);
            box_horDiagrammaView.addItemListener(listener2);
            // VERTICAL VIEW Checkbox
            box_verDiagrammaView.removeItemListener(listener3);
            box_verDiagrammaView.setSelected(false);
            box_verDiagrammaView.addItemListener(listener3);
            // CIRCLE VIEW Checkbox
            box_circleDiagrammaView.removeItemListener(listener4);
            box_circleDiagrammaView.setSelected(false);
            box_circleDiagrammaView.addItemListener(listener4);

            if (box_numberView.isSelected()) {
                box_numberView.setEnabled(false);
                box_horDiagrammaView.setEnabled(true);
                box_verDiagrammaView.setEnabled(true);
                box_circleDiagrammaView.setEnabled(true);
                // allow reset preview panel of colorChooser  in viewsColorWindow.java
                if (pranaMain.colorsWindow != null) {
                    pranaMain.colorsWindow.isResetPreviewPanel = 0;
                }

                preview.removeAll();
                Views = new views(pranaMain, true, preview);
                // set all values from main view or colorWindow
                getTransientValuesFromColorWindow();
            } else {
                box_numberView.setEnabled(true);
            }

            System.out.println("1");
        }

    }

    // HORIZONTAL VIEW Checkbox
    class L2 implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            //  set default view to HORIZONTAL view
            pranaMain.vDefault.defaultView = pranaMain.vDefault.HORIZONTAL_VIEW;

            // NUMBER VIEW
            box_numberView.removeItemListener(listener1);
            box_numberView.setSelected(false);
            box_numberView.addItemListener(listener1);
            // VERTICAL VIEW Checkbox
            box_verDiagrammaView.removeItemListener(listener3);
            box_verDiagrammaView.setSelected(false);
            box_verDiagrammaView.addItemListener(listener3);
            // CIRCLE VIEW Checkbox
            box_circleDiagrammaView.removeItemListener(listener4);
            box_circleDiagrammaView.setSelected(false);
            box_circleDiagrammaView.addItemListener(listener4);

            if (box_horDiagrammaView.isSelected()) {
                box_horDiagrammaView.setEnabled(false);
                box_numberView.setEnabled(true);
                box_verDiagrammaView.setEnabled(true);
                box_circleDiagrammaView.setEnabled(true);
                // allow reset preview panel of colorChooser  in viewsColorWindow.java
                if (pranaMain.colorsWindow != null) {
                    pranaMain.colorsWindow.isResetPreviewPanel = 0;
                }

                int index = pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex();
                // show HORIZONTAL VIEW in  preview  panel
                preview.removeAll();
                Views = new views(pranaMain, true, preview, 1, index);
                // set all values from main view or colorWindow
                getTransientValuesFromColorWindow();
            } else {
                box_horDiagrammaView.setEnabled(true);
            }

            System.out.println("2");
        }
    }

    // VERTICAL VIEW Checkbox
    class L3 implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            //  set default view to VERTICAL view
            pranaMain.vDefault.defaultView = pranaMain.vDefault.VERTICAL_VIEW;

            // NUMBER VIEW
            box_numberView.removeItemListener(listener1);
            box_numberView.setSelected(false);
            box_numberView.addItemListener(listener1);
            // HORIZONTAL VIEW Checkbox
            box_horDiagrammaView.removeItemListener(listener2);
            box_horDiagrammaView.setSelected(false);
            box_horDiagrammaView.addItemListener(listener2);
            // CIRCLE VIEW Checkbox
            box_circleDiagrammaView.removeItemListener(listener4);
            box_circleDiagrammaView.setSelected(false);
            box_circleDiagrammaView.addItemListener(listener4);

            if (box_verDiagrammaView.isSelected()) {
                box_verDiagrammaView.setEnabled(false);
                box_numberView.setEnabled(true);
                box_horDiagrammaView.setEnabled(true);
                box_circleDiagrammaView.setEnabled(true);
                // allow reset preview panel of colorChooser  in viewsColorWindow.java
                if (pranaMain.colorsWindow != null) {
                    pranaMain.colorsWindow.isResetPreviewPanel = 0;
                }

                int index = pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex();
                // show VERTICAL VIEW in  preview  panel
                preview.removeAll();
                Views = new views(pranaMain, true, preview, 2, index);
                // set all values from main view or colorWindow
                getTransientValuesFromColorWindow();
            } else {
                box_verDiagrammaView.setEnabled(true);
            }

//            System.out.println("2");
        }
    }

    // CIRCLE VIEW Checkbox
    class L4 implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            //  set default view to CIRCLE view
            pranaMain.vDefault.defaultView = pranaMain.vDefault.CIRCLE_VIEW;

            // NUMBER VIEW
            box_numberView.removeItemListener(listener1);
            box_numberView.setSelected(false);
            box_numberView.addItemListener(listener1);
            // HORIZONTAL VIEW Checkbox
            box_horDiagrammaView.removeItemListener(listener2);
            box_horDiagrammaView.setSelected(false);
            box_horDiagrammaView.addItemListener(listener2);
            // VERTICAL VIEW Checkbox
            box_verDiagrammaView.removeItemListener(listener3);
            box_verDiagrammaView.setSelected(false);
            box_verDiagrammaView.addItemListener(listener3);

            if (box_circleDiagrammaView.isSelected()) {
                box_circleDiagrammaView.setEnabled(false);
                box_numberView.setEnabled(true);
                box_horDiagrammaView.setEnabled(true);
                box_verDiagrammaView.setEnabled(true);
                // allow reset preview panel of colorChooser  in viewsColorWindow.java
                if (pranaMain.colorsWindow != null) {
                    pranaMain.colorsWindow.isResetPreviewPanel = 0;
                }

                int index = pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex();
                // show CIRCLE VIEW in  preview  panel
                preview.removeAll();
                Views = new views(pranaMain, true, preview, 3, index);
                // set all values from main view or colorWindow
                getTransientValuesFromColorWindow();
            } else {
                box_verDiagrammaView.setEnabled(true);
            }
//            System.out.println("2");
        }
    }

    class contentComponent extends JPanel {

        private double w, h;

        contentComponent() {
            w = 200;
            h = 200;
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            String text = " Preview ";
            Dimension dimension = getSize();

            Font font1 = new Font("Book Antiqua", Font.PLAIN, 50);
            Font font2 = new Font("Monotype Corsiva", Font.PLAIN, 50);

            AttributedString attributedString = new AttributedString(text);
            attributedString.addAttribute(TextAttribute.FONT, font2);
            attributedString.addAttribute(TextAttribute.FOREGROUND, Color.red, 0, 9);
            attributedString.addAttribute(TextAttribute.BACKGROUND, Color.WHITE);

            double widthAttrStr = GetWidthOfAttributedString(g2d, attributedString);
            double heightAttrStr = GetHeightOfAttributedString(g2d, attributedString);

            double Ow = getContentWidth() / 1;
            double Oh = getContentHeight() / 1.3;
            double Ox = (getContentWidth() - Ow) / 2;
            double Oy = (getContentHeight() - Oh) / 2;
            Rectangle.Double outline = new Rectangle.Double(Ox, Oy, Ow, Oh);

            double Cx = (Ow - widthAttrStr) / 2;
            double Cy = (Oh + heightAttrStr) / 2;

            g2d.setPaint(Color.green);
            g2d.fill(outline);
            g2d.setPaint(Color.red);
            // g2d.drawString(attributedString.getIterator(), (int) Cx, (int) Cy);
        }

        double GetWidthOfAttributedString(Graphics2D graphics2D, AttributedString attributedString) {
            AttributedCharacterIterator characterIterator = attributedString.getIterator();
            FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();
            LineBreakMeasurer lbm = new LineBreakMeasurer(characterIterator, fontRenderContext);
            TextLayout textLayout = lbm.nextLayout(Integer.MAX_VALUE);
            return textLayout.getBounds().getWidth();
        }

        double GetHeightOfAttributedString(Graphics2D graphics2D, AttributedString attributedString) {
            AttributedCharacterIterator characterIterator = attributedString.getIterator();
            FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();
            LineBreakMeasurer lbm = new LineBreakMeasurer(characterIterator, fontRenderContext);
            TextLayout textLayout = lbm.nextLayout(Integer.MAX_VALUE);
            return textLayout.getBounds().getHeight();
        }

        public void setContentWidth(double width) {
            this.w = width;
        }

        public double getContentWidth() {
            return w;
        }

        public void setContentHeight(double height) {
            this.h = height;
        }

        public double getContentHeight() {
            return h;
        }

    }

    private MouseAdapter close() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                views.setWindowVisibility(false);
                // SHOW window settingBreathing.java
                pranaMain.mainMenu.Breath.settingsBreath.settings.setWindowVisibility(true);
            }
        };
        return ma;
    }

    public static void main(String[] args) {

//        viewsWindow sb = new viewsWindow();  // Breathing Snake 1:1
//        sb.views.setWindowVisibility(true);
    }

}
