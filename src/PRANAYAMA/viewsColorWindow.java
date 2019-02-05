package PRANAYAMA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.text.StyleConstants;

public class viewsColorWindow implements ILabel {

    patternWindow colors;
    utils.buttonsWindow apply;
    utils.buttonsWindow buttonInhalation, buttonExhalation, buttonHoldInhalation, buttonHoldExhalation;
    utils.buttonsWindow buttonBackground, buttonForeground;
    utils.buttonsWindow buttonFonts;
    JComboBox comboFonts;
    int switchOn;
    MyColorChooserUI colorChooser;
    previewColorPanel previewView;
    Color color1, color2, color3, color4;
    Color colorBackground, colorForeground;
    Color approvedColor;
    String colorChooserTitle;
    Font myFont;
    JCheckBox box_useGradientOrNot, box_Opaque;
    JSpinner spinner_Gradient;
    int indexGradient = -1;
    // important two state
    // when equal 0 for first init colorChooser
    // when equal 1 for second  init colorChooser from any pressed button 
    int isResetPreviewPanel = -1;
    // -------------- spinner_Gradient -----------
    gradientSampleIcon ic[] = new gradientSampleIcon[]{
        new gradientSampleIcon(1),
        new gradientSampleIcon(2),
        new gradientSampleIcon(3),
        new gradientSampleIcon(4),
        new gradientSampleIcon(5),
        new gradientSampleIcon(6),
        new gradientSampleIcon(7),
        new gradientSampleIcon(8),
        new gradientSampleIcon(9),
        new gradientSampleIcon(10),
        new gradientSampleIcon(11),
        new gradientSampleIcon(12),
        new gradientSampleIcon(13),
        new gradientSampleIcon(14),
        new gradientSampleIcon(15),
        new gradientSampleIcon(16),
        new gradientSampleIcon(17),
        new gradientSampleIcon(18),
        new gradientSampleIcon(19),
        new gradientSampleIcon(20),
        new gradientSampleIcon(21)
    };

    PRANAYAMA pranaMain;

    viewsColorWindow(PRANAYAMA prana) { // PRANAYAMA prana
        this.pranaMain = prana;

        initColorChooser(null);
        init();
    }

    private void init() {
        colors = new patternWindow(pranaMain, "Views", true, 0, true, true, 0, 0, 400, 625); // 540 480
        colors.setWindowVisibility(false);
        Font font = new Font("Monotype Corsiva", Font.BOLD, 25);
// ========================= BUTTONS  'Apply','Cancel' =========================
        // add button 'Start' to start download game     
        apply = new utils.buttonsWindow("Apply", 80, 35, 12, 23);
        apply.addMouseListener(apply());
        apply.setBounds(100, 565, 85, 40);

        // Action 0 <- it means close window
        colors.addButtonsWindow2("Cancel", 200, 565, 85, 35, 7, 23, 90, 40)
                .addMouseListener(cancel());

        colors.base.add(apply);
// ============================ Header Colors ===================================                
        separator s1 = new separator();
        s1.setBounds(20, 40, 160, 10);

        Rectangle rect1 = new Rectangle(5, 2, 115, 30);
        buttons headerColors = new buttons(" Colors ", rect1);
        headerColors.setBounds(155, 25, 110, 30);

        separator s2 = new separator();
        s2.setBounds(260, 40, 118, 10);
// ============================ Colors ==========================================        
        colors.addContent("Inhalation ", 25, Color.BLUE, 95, 55, 125, 35);
        colors.addContent("Exhalation ", 25, Color.BLUE, 95, 95, 125, 35);
        colors.addContent("Breath hold after inhalation ", 25, Color.BLUE, 95, 135, 295, 35);
        colors.addContent("Breath hold after exhalation ", 25, Color.BLUE, 95, 175, 295, 35);
        // INHALATION
        buttonInhalation = new utils.buttonsWindow("", 60, 25, 12, 23);
        buttonInhalation.setName("Inhalation");
        buttonInhalation.addMouseListener(choiceColor());
        buttonInhalation.setBounds(20, 60, 65, 30);
        // EXHALATION
        buttonExhalation = new utils.buttonsWindow("", 60, 25, 12, 23);
        buttonExhalation.setName("Exhalation");
        buttonExhalation.addMouseListener(choiceColor());
        buttonExhalation.setBounds(20, 100, 65, 30);
        // HOLD after INHALATION
        buttonHoldInhalation = new utils.buttonsWindow("", 60, 25, 12, 23);
        buttonHoldInhalation.setName("HoldInhalation");
        buttonHoldInhalation.addMouseListener(choiceColor());
        buttonHoldInhalation.setBounds(20, 140, 65, 30);
        // HOLD after EXHALATION
        buttonHoldExhalation = new utils.buttonsWindow("", 60, 25, 12, 23);
        buttonHoldExhalation.setName("HoldExhalation");
        buttonHoldExhalation.addMouseListener(choiceColor());
        buttonHoldExhalation.setBounds(20, 180, 65, 30);
//============================= background ======================================
        separator s3 = new separator();
        s3.setBounds(20, 220, 118, 10);

        Rectangle rect2 = new Rectangle(5, 2, 130, 30);
        buttons headerBackground = new buttons("Background", rect2);
        headerBackground.setBounds(135, 205, 130, 30);

        separator s4 = new separator();
        s4.setBounds(270, 220, 108, 10);

        box_useGradientOrNot = new JCheckBox("set Gradient background");
        box_useGradientOrNot.setOpaque(false);
        box_useGradientOrNot.setForeground(Color.BLUE);
        box_useGradientOrNot.setFont(font);
        box_useGradientOrNot.setBounds(25, 240, 360, 30);
        box_useGradientOrNot.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (box_useGradientOrNot.isSelected()) {
                    buttonBackground.setVisible(false);
                    spinner_Gradient.setVisible(true);

                    for (int i = 0; i < ic.length; i++) {
                        gradientSampleIcon sampleIcon = ic[i];

                        if (sampleIcon.equals(spinner_Gradient.getValue())) {
                            indexGradient = i + 1;
                            previewView.previewBasicPanel.setType(indexGradient);
                            System.out.println("indexGradient: " + indexGradient);
                        }
                    }
                    // change PREVIEW with gradient background
                    // previewView.switchPreview(previewView.views2);   
                    // colorChooser.removePreview(colorChooser.base, previewView.previewPanel);
                    // colorChooser.setMyPreviewPanel(previewView.previewBasicPanel);
                    //
                    previewView.previewBasicPanel.repaint();
                } else {
                    buttonBackground.setVisible(true);
                    spinner_Gradient.setVisible(false);
                    // change PREVIEW with usual color background
                    //  previewView.switchPreview(previewView.views1); 
//                    colorChooser.removePreview(colorChooser.base, previewView.previewBasicPanel);
//                    colorChooser.setMyPreviewPanel(previewView.previewPanel);

                    // previewView.previewPanel.repaint();
                }
            }
        });
        box_useGradientOrNot.setUI((MyCheckBoxUI) MyCheckBoxUI.createUI(box_useGradientOrNot));

        colors.addContent("set common background", 25, Color.BLUE, 105, 275, 255, 35);
        // BACKGROUND
        buttonBackground = new utils.buttonsWindow("", 60, 25, 12, 23);
        buttonBackground.addMouseListener(choiceBackground());
        buttonBackground.setBounds(20, 280, 65, 30);

        // -------------- spinner_Gradient -----------
        spinner_Gradient = new JSpinner(new SpinnerListModel(ic));
        spinner_Gradient.setEditor(new componentEditor(spinner_Gradient));
        spinner_Gradient.setBounds(25, 280, 75, 30);
        spinner_Gradient.setUI(new LeftRightSpinnerUI());
        spinner_Gradient.setBorder(null);
        spinner_Gradient.setEnabled(true);
        spinner_Gradient.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                for (int i = 0; i < ic.length; i++) {
                    gradientSampleIcon sampleIcon = ic[i];

                    if (sampleIcon.equals(spinner_Gradient.getValue())) {
                        indexGradient = i + 1;
                        previewView.previewBasicPanel.setType(indexGradient);
                        //  System.out.println("changeL indexGradient: " + indexGradient);
                    }
                }
            }
        });
//============================= foreground ======================================
        separator s5 = new separator();
        s5.setBounds(20, 320, 118, 10);

        Rectangle rect3 = new Rectangle(5, 2, 130, 30);
        buttons headerForeground = new buttons("Foreground", rect3);
        headerForeground.setBounds(135, 305, 130, 30);

        separator s6 = new separator();
        s6.setBounds(270, 320, 108, 10);

        colors.addContent("set foreground", 25, Color.BLUE, 95, 335, 195, 35);
        // FOREGROUND
        buttonForeground = new utils.buttonsWindow("", 60, 25, 12, 23);
        buttonForeground.addMouseListener(choiceForeground());
        buttonForeground.setBounds(20, 340, 65, 30);
//============================= opacity ==========================================
        separator s7 = new separator();
        s7.setBounds(20, 380, 160, 10);

        Rectangle rect4 = new Rectangle(5, 2, 130, 30);
        buttons headerOpaque = new buttons("Opacity", rect4);
        headerOpaque.setBounds(145, 365, 130, 30);

        separator s8 = new separator();
        s8.setBounds(260, 380, 118, 10);

//        colors.addContent("set opacity", 25, Color.BLUE, 95, 395, 195, 35);
        // OPACITY CHECKBOX
        box_Opaque = new JCheckBox("set opacity");
        box_Opaque.setOpaque(false);
        box_Opaque.setForeground(Color.BLUE);
        box_Opaque.setFont(font);
        box_Opaque.setBounds(25, 400, 360, 30);
        box_Opaque.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (box_Opaque.isSelected()) {
                    if (pranaMain.vDefault.defaultView == pranaMain.vDefault.NUMBER_VIEW) {
                        //set OPACITY of VIEW
                        pranaMain.view.setViewOpaque(true);
                        // set state Opacity in PREVIEW
                        setOpacityInPreviewPanel(true);
                        previewView.Views.label_numCycles.setOpaque(true);
                    }
                    if (pranaMain.vDefault.defaultView == pranaMain.vDefault.HORIZONTAL_VIEW
                            || pranaMain.vDefault.defaultView == pranaMain.vDefault.VERTICAL_VIEW
                            || pranaMain.vDefault.defaultView == pranaMain.vDefault.CIRCLE_VIEW) {
                        //set OPACITY of VIEW
                        pranaMain.view.setViewOpaque(true);
                        // set state Opacity in PREVIEW
                        previewView.Views.label_numCycles.setOpaque(true);
                    }
                } else {
                    if (pranaMain.vDefault.defaultView == pranaMain.vDefault.NUMBER_VIEW) {
                        //set OPACITY of VIEW
                        pranaMain.view.setViewOpaque(false);
                        // set state Opacity in PREVIEW
                        setOpacityInPreviewPanel(false);
                        previewView.Views.label_numCycles.setOpaque(false);
                    }
                    if (pranaMain.vDefault.defaultView == pranaMain.vDefault.HORIZONTAL_VIEW
                            || pranaMain.vDefault.defaultView == pranaMain.vDefault.VERTICAL_VIEW
                            || pranaMain.vDefault.defaultView == pranaMain.vDefault.CIRCLE_VIEW) {
                        //set OPACITY of VIEW
                        pranaMain.view.setViewOpaque(false);
                        // set state Opacity in PREVIEW
                        previewView.Views.label_numCycles.setOpaque(false);
                    }
                }
            }
        });
        box_Opaque.setUI((MyCheckBoxUI) MyCheckBoxUI.createUI(box_Opaque));
//============================= fonts ===========================================
        separator s9 = new separator();
        s9.setBounds(20, 440, 160, 10);

        Rectangle rect5 = new Rectangle(5, 2, 130, 30);
        buttons headerFonts = new buttons("Fonts", rect5);
        headerFonts.setBounds(135, 425, 405, 30);

        separator s10 = new separator();
        s10.setBounds(235, 440, 143, 10);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();

        comboFonts = new JComboBox(fontNames);
        comboFonts.setUI((ComboBoxUI) MyComboBoxUI.createUI(comboFonts));
        comboFonts.setSelectedIndex(0);
        comboFonts.setBounds(25, 465, 350, 30);
        comboFonts.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        comboFonts.setFont(new Font("Serif", Font.BOLD, 16));
        comboFonts.setEditable(false);
        changeFonts(comboFonts);

        JPanel previewFontPanel = new previewFontPanel(350, 45);
        previewFontPanel.setBounds(25, 505, 350, 45);
//===============================================================================
        initialStateAllComponents();
//==================== layout components ========================================        
        colors.base.add(s1);
        colors.base.add(headerColors);
        colors.base.add(s2);

        colors.base.add(buttonInhalation);
        colors.base.add(buttonExhalation);
        colors.base.add(buttonHoldInhalation);
        colors.base.add(buttonHoldExhalation);

        colors.base.add(s3);
        colors.base.add(headerBackground);
        colors.base.add(s4);
        colors.base.add(box_useGradientOrNot);
        colors.base.add(buttonBackground);
        colors.base.add(spinner_Gradient);

        colors.base.add(s5);
        colors.base.add(headerForeground);
        colors.base.add(s6);
        colors.base.add(buttonForeground);

        colors.base.add(s7);
        colors.base.add(headerOpaque);
        colors.base.add(s8);
        colors.base.add(box_Opaque);

        colors.base.add(s9);
        colors.base.add(headerFonts);
        colors.base.add(s10);
        colors.base.add(comboFonts);
        colors.base.add(previewFontPanel);
    }

    protected void initialStateAllComponents() {

        int index = pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex();

        System.out.println("initialState num : " + pranaMain.vDefault.defaultView + "  " + index);

        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.NUMBER_VIEW) {
            switch (index) {
                default:
                    break;
                case 0:
                case 1:
                    System.out.println("1 index num : " + pranaMain.vDefault.defaultView);

                    buttonInhalation.setColor(pranaMain.view.label_Inhalation.getBackground());
                    buttonExhalation.setColor(pranaMain.view.label_Exhalation.getBackground());
                    // set disabled components
                    buttonHoldInhalation.setDisabledState(true);
                    buttonHoldExhalation.setDisabledState(true);
                    removeAllMouseListeners(buttonHoldInhalation);
                    removeAllMouseListeners(buttonHoldExhalation);
                    // set LABEL enabled and disabled states
                    setEnabledStateColor("Inhalation ", Color.BLUE, 95, 55, 125, 35);
                    setEnabledStateColor("Exhalation ", Color.BLUE, 95, 95, 125, 35);
                    setDisabledStateColor("Breath hold after inhalation ", Color.LIGHT_GRAY, 95, 135, 295, 35);
                    setDisabledStateColor("Breath hold after exhalation ", Color.LIGHT_GRAY, 95, 175, 295, 35);

                    // set BACKGROUND
                    switchComponentsForBackground();
                    // set BACKGROUND in PREVIEW PANEL (previewView)
                    previewView.previewBasicPanel.setBackground(pranaMain.basic.getColorBackground());

                    // set FOREGROUND in PREVIEW PANEL (previewView)
                    buttonForeground.setColor(pranaMain.view.label_Inhalation.getForeground());
                    previewView.Views.label_Inhalation.setForeground(pranaMain.view.getColorForeground());
                    previewView.Views.label_Exhalation.setForeground(pranaMain.view.getColorForeground());
                    previewView.Views.label_numCycles.setForeground(pranaMain.view.getColorForeground());
                    
                    // set BORDER FONT of VIEW
                    myFont = pranaMain.view.getBorderFont();
                    if (myFont.getName() != "Book Antiqua") {
                        comboFonts.setSelectedItem(myFont.getName());
                    }
                    // set state Opacity checkbox
                    box_Opaque.setSelected(pranaMain.view.getViewOpaque());
                    break;
                case 2:
                    System.out.println("2 index num : " + pranaMain.vDefault.defaultView);
                    // set enabled components
                    buttonInhalation.setColor(pranaMain.view.label_Inhalation.getBackground());
                    buttonHoldInhalation.setColor(pranaMain.view.getColor2());
                    buttonExhalation.setColor(pranaMain.view.label_Exhalation.getBackground());
                    // set disabled components
                    buttonHoldExhalation.setDisabledState(true);
                    removeAllMouseListeners(buttonHoldExhalation);
                    // set LABEL enabled and disabled states
                    setEnabledStateColor("Inhalation ", Color.BLUE, 95, 55, 125, 35);
                    setEnabledStateColor("Exhalation ", Color.BLUE, 95, 95, 125, 35);
                    setEnabledStateColor("Breath hold after inhalation ", Color.BLUE, 95, 135, 295, 35);
                    setDisabledStateColor("Breath hold after exhalation ", Color.LIGHT_GRAY, 95, 175, 295, 35);
                    // set BACKGROUND
                    switchComponentsForBackground();
                    // button foreground
                    buttonForeground.setColor(pranaMain.view.label_Inhalation.getForeground());
                    // set BORDER FONT of VIEW
                    myFont = pranaMain.view.getBorderFont();
                    if (myFont.getName() != "Book Antiqua") {
                        comboFonts.setSelectedItem(myFont.getName());
                    }
                    // set state Opacity checkbox
                    box_Opaque.setSelected(pranaMain.view.getViewOpaque());
                    break;
                case 3:
                    System.out.println("3 index num : " + pranaMain.vDefault.defaultView);

                    buttonInhalation.setColor(pranaMain.view.label_Inhalation.getBackground());
                    buttonHoldInhalation.setColor(pranaMain.view.getColor2());
                    buttonExhalation.setColor(pranaMain.view.label_Exhalation.getBackground());
                    buttonHoldExhalation.setColor(pranaMain.view.getColor4());
                    colors.base.repaint();
                    // set LABEL enabled and disabled states
                    setEnabledStateColor("Inhalation ", Color.BLUE, 95, 55, 125, 35);
                    setEnabledStateColor("Exhalation ", Color.BLUE, 95, 95, 125, 35);
                    setEnabledStateColor("Breath hold after inhalation ", Color.BLUE, 95, 135, 295, 35);
                    setEnabledStateColor("Breath hold after exhalation ", Color.BLUE, 95, 175, 295, 35);
                    // set BACKGROUND
                    switchComponentsForBackground();
                    // button foreground
                    buttonForeground.setColor(pranaMain.view.label_Inhalation.getForeground());
                    // set BORDER FONT of VIEW
                    myFont = pranaMain.view.getBorderFont();
                    if (myFont.getName() != "Book Antiqua") {
                        comboFonts.setSelectedItem(myFont.getName());
                    }
                    // set state Opacity checkbox
                    box_Opaque.setSelected(pranaMain.view.getViewOpaque());
                    break;
            }
        }
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.HORIZONTAL_VIEW
                || pranaMain.vDefault.defaultView == pranaMain.vDefault.VERTICAL_VIEW
                || pranaMain.vDefault.defaultView == pranaMain.vDefault.CIRCLE_VIEW) {
            switch (index) {
                default:
                    break;
                case 0:
                case 1:

                    System.out.println("1 index graph : " + pranaMain.vDefault.defaultView);

                    if (pranaMain.view.graphCompLine != null) {
                        buttonInhalation.setColor(pranaMain.view.graphCompLine.getColor1());
                        buttonExhalation.setColor(pranaMain.view.graphCompLine.getColor3());

                        buttonForeground.setColor(pranaMain.view.graphCompLine.getColorForeground());
                    } else {
                        buttonInhalation.setColor(pranaMain.views_window.Views.graphCompLine.getColor1());
                        buttonExhalation.setColor(pranaMain.views_window.Views.graphCompLine.getColor3());

                        buttonForeground.setColor(pranaMain.views_window.Views.graphCompLine.getColorForeground());
                    }

                    // set disabled components
                    buttonHoldInhalation.setDisabledState(true);
                    buttonHoldExhalation.setDisabledState(true);
                    removeAllMouseListeners(buttonHoldInhalation);
                    removeAllMouseListeners(buttonHoldExhalation);
                    // set LABEL enabled and disabled states
                    setEnabledStateColor("Inhalation ", Color.BLUE, 95, 55, 125, 35);
                    setEnabledStateColor("Exhalation ", Color.BLUE, 95, 95, 125, 35);
                    setDisabledStateColor("Breath hold after inhalation ", Color.LIGHT_GRAY, 95, 135, 295, 35);
                    setDisabledStateColor("Breath hold after exhalation ", Color.LIGHT_GRAY, 95, 175, 295, 35);
                    // set BACKGROUND
                    switchComponentsForBackground();
                    // button foreground
                    // set BORDER FONT of VIEW
                    myFont = pranaMain.view.getBorderFont();
                    if (myFont.getName() != "Book Antiqua") {
                        comboFonts.setSelectedItem(myFont.getName());
                    }
                    // set state Opacity checkbox
                    box_Opaque.setSelected(pranaMain.view.getViewOpaque());
                    break;
                case 2:

                    System.out.println("2 index graph : " + pranaMain.vDefault.defaultView);

                    if (pranaMain.view.graphCompLine != null) {
                        buttonInhalation.setColor(pranaMain.view.graphCompLine.getColor1());
                        buttonHoldInhalation.setColor(pranaMain.view.graphCompLine.getColor2());
                        buttonExhalation.setColor(pranaMain.view.graphCompLine.getColor3());

                        buttonForeground.setColor(pranaMain.view.graphCompLine.getColorForeground());
                    } else {
                        buttonInhalation.setColor(pranaMain.views_window.Views.graphCompLine.getColor1());
                        buttonHoldInhalation.setColor(pranaMain.views_window.Views.graphCompLine.getColor2());
                        buttonExhalation.setColor(pranaMain.views_window.Views.graphCompLine.getColor3());

                        buttonForeground.setColor(pranaMain.views_window.Views.graphCompLine.getColorForeground());
                    }

                    // set disabled components
                    buttonHoldExhalation.setDisabledState(true);
                    removeAllMouseListeners(buttonHoldExhalation);
                    // set LABEL enabled and disabled states
                    setEnabledStateColor("Inhalation ", Color.BLUE, 95, 55, 125, 35);
                    setEnabledStateColor("Exhalation ", Color.BLUE, 95, 95, 125, 35);
                    setEnabledStateColor("Breath hold after inhalation ", Color.BLUE, 95, 135, 295, 35);
                    setDisabledStateColor("Breath hold after exhalation ", Color.LIGHT_GRAY, 95, 175, 295, 35);
                    // set BACKGROUND
                    switchComponentsForBackground();
                    // button  foreground
                    // set BORDER FONT of VIEW
                    myFont = pranaMain.view.getBorderFont();
                    if (myFont.getName() != "Book Antiqua") {
                        comboFonts.setSelectedItem(myFont.getName());
                    }
                    // set state Opacity checkbox
                    box_Opaque.setSelected(pranaMain.view.getViewOpaque());
                    break;
                case 3:

                    System.out.println("3 index graph : " + pranaMain.vDefault.defaultView);

                    if (pranaMain.view.graphCompLine != null) {
                        buttonInhalation.setColor(pranaMain.view.graphCompLine.getColor1());
                        buttonHoldInhalation.setColor(pranaMain.view.graphCompLine.getColor2());
                        buttonExhalation.setColor(pranaMain.view.graphCompLine.getColor3());
                        buttonHoldExhalation.setColor(pranaMain.view.graphCompLine.getColor4());
                    } else {
                        buttonInhalation.setColor(pranaMain.views_window.Views.graphCompLine.getColor1());
                        buttonHoldInhalation.setColor(pranaMain.views_window.Views.graphCompLine.getColor2());
                        buttonExhalation.setColor(pranaMain.views_window.Views.graphCompLine.getColor3());
                        buttonHoldExhalation.setColor(pranaMain.views_window.Views.graphCompLine.getColor4());

                        buttonForeground.setColor(pranaMain.views_window.Views.graphCompLine.getColorForeground());
                    }

                    colors.base.repaint();
                    // set LABEL enabled and disabled states
                    setEnabledStateColor("Inhalation ", Color.BLUE, 95, 55, 125, 35);
                    setEnabledStateColor("Exhalation ", Color.BLUE, 95, 95, 125, 35);
                    setEnabledStateColor("Breath hold after inhalation ", Color.BLUE, 95, 135, 295, 35);
                    setEnabledStateColor("Breath hold after exhalation ", Color.BLUE, 95, 175, 295, 35);
                    // set BACKGROUND
                    switchComponentsForBackground();
                    // button foreground
                    // set BORDER FONT of VIEW
                    myFont = pranaMain.view.getBorderFont();
                    if (myFont.getName() != "Book Antiqua") {
                        comboFonts.setSelectedItem(myFont.getName());
                    }
                    // set state Opacity checkbox
                    box_Opaque.setSelected(pranaMain.view.getViewOpaque());
                    break;
            }
        }
    }

    private void setOpacityInPreviewPanel(boolean opacity) {

        if (previewView.Views.label_Inhalation != null) {
            previewView.Views.label_Inhalation.setOpaque(opacity);
        }
        if (previewView.Views.label_Exhalation != null) {
            previewView.Views.label_Exhalation.setOpaque(opacity);
        }
        if (previewView.Views.label_breathhold_after_inhalation != null) {
            previewView.Views.label_breathhold_after_inhalation.setOpaque(opacity);
        }
        if (previewView.Views.label_breathhold_after_exhalation != null) {
            previewView.Views.label_breathhold_after_exhalation.setOpaque(opacity);
        }
    }

    private void switchComponentsForBackground() {

        if (pranaMain.basic.getType() > 0 && pranaMain.basic.getType() < 22) {
            // deselect CHECKBOX 'use gradient' 
            box_useGradientOrNot.setSelected(true);
            // hide button BACKGROUND
            buttonBackground.setVisible(false);
            // show SPINNER GRADIENT
            spinner_Gradient.setVisible(true);
            spinner_Gradient.setValue(ic[pranaMain.basic.getType() - 1]);
        } else {
            // select CHECKBOX 'use gradient'
            box_useGradientOrNot.setSelected(false);
            // show button BACKGROUND
            buttonBackground.setVisible(true);
            buttonBackground.setColor(pranaMain.basic.getColorBackground());
            //System.out.println("COLOR: " + pranaMain.basic.getBackground());
            // hide SPINNER GRADIENT
            spinner_Gradient.setVisible(false);
        }
    }

    @Override
    public void setDisabledStateColor(String name, Object resource, int x, int y, int w, int h) {
        // remove label
        colors.removeLabel(name);
        colors.addContent(name, 25, (Color) resource, x, y, w, h);

    }

    @Override
    public void setEnabledStateColor(String name, Object resource, int x, int y, int w, int h) {
        // remove label
        colors.removeLabel(name);
        colors.addContent(name, 25, (Color) resource, x, y, w, h);
    }

    protected void removeAllMouseListeners(JComponent comp) {

        MouseListener[] listener = comp.getMouseListeners();
        for (MouseListener mouseListener : listener) {
            comp.removeMouseListener(mouseListener);
        }
    }

    class previewFontPanel extends JPanel {

        int w;
        int h;

        previewFontPanel(int ww, int hh) {
            this.w = ww;
            this.h = hh;
            setMinimumSize(new Dimension(w, h));
            setPreferredSize(new Dimension(w, h));
            setSize(w, h);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            if (myFont == null) {
                Font font = new Font("Monotype Corsiva", Font.BOLD, 25);
                g.setFont(font);
            } else {
                g.setFont(myFont);
            }

            RoundRectangle2D rect = new RoundRectangle2D.Double(0, 0, w, h, 10, 10);

            g2.setColor(Color.WHITE);
//            g2.setPaint(new GradientPaint(0, 0, new Color(255, 242, 226), w, h, new Color(213, 255, 122), false));
            g2.fill(rect);
            g2.setColor(Color.BLUE);
            g2.draw(rect);

            // draw string
            String str = "This is text for check";
            FontMetrics fm = g.getFontMetrics();
            int width = g.getFontMetrics().stringWidth(str);
            int height = g.getFontMetrics().getHeight();
            int wC = (w - width) / 2;
            int hC = (h + fm.getAscent()) / 2;

            g2.drawString(str, wC, hC);
        }
    }

    private void initColorChooser(String title) {

        previewView = new previewColorPanel(pranaMain);

        if (pranaMain.colorsWindow == null) {
            previewView.Views.setViewOpaque(pranaMain.view.getViewOpaque());
            // set opacity in PREVIEW panel
            setOpacityInPreviewPanel(pranaMain.view.getViewOpaque());
            previewView.Views.label_numCycles.setOpaque(pranaMain.view.getViewOpaque());
        } else {
            // set state Opacity in PREVIEW
            if (pranaMain.colorsWindow.box_Opaque.isSelected()) {
                previewView.Views.setViewOpaque(true);
                // set opacity in PREVIEW panel
                setOpacityInPreviewPanel(true);
                previewView.Views.label_numCycles.setOpaque(true);
            } else {
                previewView.Views.setViewOpaque(false);
                // set opacity in PREVIEW panel
                setOpacityInPreviewPanel(false);
                previewView.Views.label_numCycles.setOpaque(false);
            }
        }

        // System.out.println("OPACITY: " + pranaMain.view.getViewOpaque());
        isResetPreviewPanel += 1;

        colorChooser = new MyColorChooserUI(title);
        colorChooser = colorChooser.setRadioButtonCover("PRANAYAMA.MyRadioButtonUI", colorChooser);
        colorChooser = colorChooser.setSliderCover("PRANAYAMA.MySliderUI", colorChooser);
        colorChooser = colorChooser.setSpinnerCover("PRANAYAMA.LeftRightSpinnerUI", colorChooser);
        colorChooser = colorChooser.setTabbedPaneCover("PRANAYAMA.MyTabbedPaneUI", colorChooser);
        // set PREVIEW PANEL
        colorChooser.setMyPreviewPanel(previewView.previewBasicPanel);
        colorChooser.chooser.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // update color in PreviewLabel
                updatePreviewColor();
            }
        });
        colorChooser.apply.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // remove PREVIEW taken from viewsWindow.java file
                colorChooser.removePreview(colorChooser.base, pranaMain.views_window.Views.previewPanel);

                // Show COLOR WINDOW 
                colors.setWindowVisibility(true);
                // Hide COLORCHOOSER
                colorChooser.frame.setVisible(false);

                // set BUTTON background
                switch (switchOn) {
                    default:
                        break;
                    case 1:
                        color1 = colorChooser.chooser.getColor();
                        buttonInhalation.setColor(color1);
                        if (color1 == Color.black) {
                            buttonInhalation.setColor(pranaMain.view.getColor1());
                            System.out.println("1 Color:" + color1);
                        }
                        break;
                    case 2:
                        color2 = colorChooser.chooser.getColor();
                        buttonHoldInhalation.setColor(color2);
                        break;
                    case 3:
                        color3 = colorChooser.chooser.getColor();
                        buttonExhalation.setColor(color3);
                        break;
                    case 4:
                        color4 = colorChooser.chooser.getColor();
                        buttonHoldExhalation.setColor(color4);
                        break;
                    case 5: // panel background
                        colorBackground = colorChooser.chooser.getColor();
                        buttonBackground.setColor(colorBackground);
                        break;
                    case 6: // panel foreground
                        colorForeground = colorChooser.chooser.getColor();
                        buttonForeground.setColor(colorForeground);
                        break;
                }

            }
        });
        colorChooser.cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Show COLOR WINDOW 
                colors.setWindowVisibility(true);
                // Hide COLORCHOOSER
                colorChooser.frame.setVisible(false);
            }
        });
    }

    protected MouseAdapter choiceColor() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                colors.setWindowVisibility(false);

                utils.buttonsWindow button = (utils.buttonsWindow) e.getSource();

                String name = button.getName();

                if (name.equals("Inhalation")) {
                    switchOn = 1;

                    resetPreviewPanel_andBorder("INHALATION");

                    colorChooser.frame.setVisible(true);
                }
                if (name.equals("Exhalation")) {
                    switchOn = 3;

                    resetPreviewPanel_andBorder("EXHALATION");

                    colorChooser.frame.setVisible(true);
                }
                if (name.equals("HoldInhalation")) {
                    switchOn = 2;

                    resetPreviewPanel_andBorder("HOLD AFTER INHALATION");

                    colorChooser.frame.setVisible(true);
                }
                if (name.equals("HoldExhalation")) {
                    switchOn = 4;

                    resetPreviewPanel_andBorder("HOLD AFTER EXHALATION");

                    colorChooser.frame.setVisible(true);
                }
            }
        };
        return ma;
    }

    protected void getSpinnerGradientValue() {
        for (int i = 0; i < ic.length; i++) {
            gradientSampleIcon sampleIcon = ic[i];

            if (sampleIcon.equals(spinner_Gradient.getValue())) {
                indexGradient = i + 1;
                previewView.previewBasicPanel.setType(indexGradient);
            }
        }
    }

    protected void resetPreviewPanel_andBorder(String borderName) {

        isResetPreviewPanel += 1;

        if (isResetPreviewPanel == 1) {
            // remove PREVIEW panel 
            colorChooser.removePreview(colorChooser.base, previewView.previewBasicPanel);
            // reinit 'previewBasicPanel' according to preview in viewsWindow.java file
            colorChooser = null;
            initColorChooser(borderName);
            // set BACKGROUND preview panel according to spinner value
            getSpinnerGradientValue();
            previewView.Views.common.repaint();
            // System.out.println("1 RESETpreview: " + isResetPreviewPanel);

        } else {
            // System.out.println("+ RESETpreview: " + isResetPreviewPanel);
        }
       // System.out.println("RESETpreview: " + isResetPreviewPanel);

        // set again my color PREVIEW panel
//        colorChooser.setMyPreviewPanel(previewView.previewBasicPanel);
        // Reset border with new TITLE
//        colorChooser.setTitle(borderName);
//        colorChooser.base.setBorder(null);
//        colorChooser.resetBorder();
    }

    private void updatePreviewColor() {
        switch (switchOn) {
            default:
                break;
            case 1: // inhalation
                color1 = colorChooser.chooser.getColor();

                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.NUMBER_VIEW) {

                    previewView.Views.label_Inhalation.setBackground(color1);
                    previewView.Views.common.repaint();
                    //  previewView.Views.label_Inhalation.setBackground(color1);
                }
                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.HORIZONTAL_VIEW
                        || pranaMain.vDefault.defaultView == pranaMain.vDefault.VERTICAL_VIEW
                        || pranaMain.vDefault.defaultView == pranaMain.vDefault.CIRCLE_VIEW) {

                    previewView.Views.graphCompLine.setColor1(color1);
                    previewView.Views.graphCompLine.repaint();
                }
                break;
            case 2: // hold after inhalation
                color2 = colorChooser.chooser.getColor();

                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.NUMBER_VIEW) {
                    
                    previewView.Views.label_breathhold_after_inhalation.setBackground(color2);
                    previewView.Views.common.repaint();
                }
                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.HORIZONTAL_VIEW
                        || pranaMain.vDefault.defaultView == pranaMain.vDefault.VERTICAL_VIEW
                        || pranaMain.vDefault.defaultView == pranaMain.vDefault.CIRCLE_VIEW) {

                    previewView.Views.graphCompLine.setColor2(color2);
                    previewView.Views.graphCompLine.repaint();
                }
                break;
            case 3: // Exhalation
                color3 = colorChooser.chooser.getColor();

                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.NUMBER_VIEW) {
                    
                    previewView.Views.label_Exhalation.setBackground(color3);
                    previewView.Views.common.repaint();
                }
                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.HORIZONTAL_VIEW
                        || pranaMain.vDefault.defaultView == pranaMain.vDefault.VERTICAL_VIEW
                        || pranaMain.vDefault.defaultView == pranaMain.vDefault.CIRCLE_VIEW) {

                    previewView.Views.graphCompLine.setColor3(color3);
                    previewView.Views.graphCompLine.repaint();
                }
                break;
            case 4: // hold after exhalation
                color4 = colorChooser.chooser.getColor();

                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.NUMBER_VIEW) {
                    
                    previewView.Views.label_breathhold_after_exhalation.setBackground(color4);
                    previewView.Views.common.repaint();
                }
                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.HORIZONTAL_VIEW
                        || pranaMain.vDefault.defaultView == pranaMain.vDefault.VERTICAL_VIEW
                        || pranaMain.vDefault.defaultView == pranaMain.vDefault.CIRCLE_VIEW) {

                    previewView.Views.graphCompLine.setColor4(color4);
                    previewView.Views.graphCompLine.repaint();
                }
                break;
            case 5: // Panel Background
                colorBackground = colorChooser.chooser.getColor();

                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.NUMBER_VIEW
                        || pranaMain.vDefault.defaultView == pranaMain.vDefault.HORIZONTAL_VIEW
                        || pranaMain.vDefault.defaultView == pranaMain.vDefault.VERTICAL_VIEW
                        || pranaMain.vDefault.defaultView == pranaMain.vDefault.CIRCLE_VIEW) {

                    previewView.previewBasicPanel.setType(100);
                    previewView.previewBasicPanel.setColorBackground(colorBackground);
                    previewView.previewBasicPanel.repaint();
                }
                previewView.previewBasicPanel.repaint();
                break;
            case 6: // foreground
                colorForeground = colorChooser.chooser.getColor();

                int index = pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex();

                // System.out.println("::: " + index);
                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.NUMBER_VIEW) {

                    if (index == 0 || index == 1) {
                        previewView.Views.label_Inhalation.setForeground(colorForeground);
                        previewView.Views.label_Exhalation.setForeground(colorForeground);
                        previewView.Views.label_numCycles.setForeground(colorForeground);
                        previewView.Views.setColorForeground(colorForeground);
                    }
                    if (index == 2) {
                        previewView.Views.label_Inhalation.setForeground(colorForeground);
                        previewView.Views.label_breathhold_after_inhalation.setForeground(colorForeground);
                        previewView.Views.label_Exhalation.setForeground(colorForeground);
                        previewView.Views.label_numCycles.setForeground(colorForeground);
                        previewView.Views.setColorForeground(colorForeground);
                    }
                    if (index == 3) {
                        previewView.Views.label_Inhalation.setForeground(colorForeground);
                        previewView.Views.label_breathhold_after_inhalation.setForeground(colorForeground);
                        previewView.Views.label_Exhalation.setForeground(colorForeground);
                        previewView.Views.label_breathhold_after_exhalation.setForeground(colorForeground);
                        previewView.Views.label_numCycles.setForeground(colorForeground);
                        previewView.Views.setColorForeground(colorForeground);
                    }
                }
                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.HORIZONTAL_VIEW
                        || pranaMain.vDefault.defaultView == pranaMain.vDefault.VERTICAL_VIEW
                        || pranaMain.vDefault.defaultView == pranaMain.vDefault.CIRCLE_VIEW) {

                    previewView.Views.graphCompLine.setColorForeground(colorForeground);
                    previewView.Views.label_numCycles.setForeground(colorForeground);
                }
                previewView.Views.common.repaint();
                break;
        }
    }

    private MouseAdapter choiceBackground() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // hide COLORS window
                colors.setWindowVisibility(false);

                resetPreviewPanel_andBorder("BACKGROUND");

                //  initColorChooser("SET MAIN PANEL BACKGROUND");
                colorChooser.frame.setVisible(true);

                switchOn = 5;
            }
        };
        return ma;
    }

    private MouseAdapter choiceForeground() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // hide COLORS window
                colors.setWindowVisibility(false);

                resetPreviewPanel_andBorder("FOREGROUND");

                //  initColorChooser("SET FOREGROUND");
                colorChooser.frame.setVisible(true);

                switchOn = 6;
            }
        };
        return ma;
    }

    protected void changeFonts(final JComboBox combobox) {

        combobox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                Object sel = combobox.getSelectedItem();
                if (e.getItem() == sel) {

                    myFont = Font.decode((String) sel);

//                    previewView.previewPanel.repaint();
                    colors.base.repaint();

                    //  System.out.println(e.getItem());
                }
            }
        });
    }

    private MouseAdapter apply() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // INHALATION 
                if (color1 != null) {
                    pranaMain.view.setColor1(color1);
                }
                // HOLD AFTER INHALATION
                if (color2 != null) {
                    pranaMain.view.setColor2(color2);
                }
                // EXHALATION
                if (color3 != null) {
                    pranaMain.view.setColor3(color3);
                }
                //  HOLD AFTER EXHALATION
                if (color4 != null) {
                    pranaMain.view.setColor4(color4);
                }
                // FOREGROUND
                if (colorForeground != null) {
                    pranaMain.view.setColorForeground(colorForeground);
                } else {
                    pranaMain.view.setColorForeground(pranaMain.view.getColorForeground());
                }

                // BACKGROUND
                if (colorBackground != null) {
                    // checkbox 'use Gradient'
                    if (!box_useGradientOrNot.isSelected()) {
                        // pranaMain.basic.setType(100);
                        //pranaMain.basic.setColorBackground(colorBackground);
                    } else {
                        int index = 0;
                        for (int i = 0; i < ic.length; i++) {
                            gradientSampleIcon sampleIcon = ic[i];

                            if (sampleIcon.equals(spinner_Gradient.getValue())) {
                                index = i - 1;
                                indexGradient = index;
                            }
                        }
                        System.out.println("INDEX: " + index);
                        // pranaMain.basic.setType(index);
                    }
                } else {
                    // checkbox 'use Gradient'
                    if (!box_useGradientOrNot.isSelected()) {
                        // pranaMain.basic.setType(100);
                        //  pranaMain.basic.setColorBackground(pranaMain.basic.getColorBackground());
                    } else {
                        int index = 0;
                        for (int i = 0; i < ic.length; i++) {
                            gradientSampleIcon sampleIcon = ic[i];

                            if (sampleIcon.equals(spinner_Gradient.getValue())) {
                                index = i + 1;
                                indexGradient = index;
                            }
                        }
                        System.out.println("INDEX: " + index);
                        //  pranaMain.basic.setType(index);
                    }
                }
                //hide COLORS window
                colors.setWindowVisibility(false);
                // Show SETTINGS window
                pranaMain.mainMenu.Breath.settingsBreath.settings.setWindowVisibility(true);
                // set BORDER FONT of VIEW
                pranaMain.view.setBorderFont(myFont);
            }
        };
        return ma;
    }

    private MouseAdapter cancel() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // hide COLORS window
                colors.setWindowVisibility(false);
                // Show SETTINGS window
                pranaMain.mainMenu.Breath.settingsBreath.settings.setWindowVisibility(true);
            }
        };
        return ma;
    }

    public static void main(String[] args) {

//        viewsColorWindow sb = new viewsColorWindow();  // Breathing Snake 1:1
//        sb.colors.base.repaint();
//        sb.views.setWindowVisibility(true);
        JFrame frame = new JFrame("Popup JComboBox");
        frame.setLayout(null);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        utils.buttonsWindow button = new utils.buttonsWindow("", 60, 25, 12, 23);
        button.setBounds(60, 25, 65, 30);

        basicPanel p = new basicPanel(frame);
        p.setType(1);
        p.setBounds(0, 0, button.getWidth() - 10, button.getHeight() - 10);

        button.add(p);

        frame.add(button);
        // frame.setVisible(true);
    }
}
