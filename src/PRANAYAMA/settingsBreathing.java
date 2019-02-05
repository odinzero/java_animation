package PRANAYAMA;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.text.PlainDocument;

public class settingsBreathing implements ILabel {

    String breathFalcon = "Breathing Falcon 1:7";
    String breathBear = "Breathing Bear 7:1 ";
    String breathVolf = "Breathing Volf 1:1    ";
    String breathSnake = "Breathing Snake 1:1";

    patternWindow settings;
    customLabel label_inhalation, label_exhalation, label_breathhold;
    JTextField field_inhalation;
    JTextField field_exhalation;
    JTextField field_breathhold_after_inhalation;
    JTextField field_breathhold_after_exhalation;
    int val_field_inhalation, val_field_exhalation, val_field_breathhold_after_inhalation,
            val_field_breathhold_after_exhalation;
    MyIntFilter filter;
    boolean allowBlockApply1 = false, allowBlockApply2 = false, allowBlockApply3 = false,
            allowBlockApply4 = false;
    //
    JComboBox schema_breathing;

    // JSpinner field_multiplier;
    JSpinner spinner_mplier;
    JSpinner spinner_cycles;
    //Menu 'Breath' 
    patternWindow.buttonsWindow apply, views, colors, start, close;
    // Mouse Listener for button 'Start'
    startBreathing start_breathing;
    // Mouse Listener for button 'Views'
    forButton_views forBut_views;
    // Mouse Listener for button 'Colors'
    forButton_colors forBut_colors;
    //
    tfieldfocusListener tfocus;
    // approved value JComboxBox 'schema_breathing' when user click button 'start'
    // see also class:PlusMinusActions.java
    int approvedIndex;

//    breathingRun breathing_run;
    PRANAYAMA pranaMain;

    settingsBreathing(PRANAYAMA prana) { // String breathType , PRANAYAMA prana
        this.pranaMain = prana;

        start_breathing = new startBreathing(pranaMain);
        tfocus = new tfieldfocusListener();
        
        forBut_views = new forButton_views();
        forBut_colors = new forButton_colors();

        filter = new MyIntFilter();

        settingsWindow();
    }

    private void settingsWindow() {
        settings = new patternWindow(pranaMain, "Settings", true, 0, true, true, 0, 0, 400, 600);
        Font font1 = new Font("Book Antiqua", Font.PLAIN, 20);

// ========================= BUTTONS  'Apply', 'Start', 'Cancel' =========================  
        // add button 'Apply' to start download game     
        apply = settings.new buttonsWindow("Apply", 80, 35, 12, 23);
        apply.setDisabledState(false);
        apply.setBounds(155, 260, 85, 40);
        settings.base.add(apply);

        // add button 'Start' to start download game     
        start = settings.new buttonsWindow("Start", 80, 35, 12, 23);
        start.setBounds(100, 540, 85, 40);
        settings.base.add(start);

        // Action 0 <- it means close window
        settings.addButtonsWindow2("Cancel", 200, 540, 85, 35, 7, 23, 90, 40)
                .addMouseListener(cancel());
// =========================== Schema ==========================================        
        separator s1 = new separator();
        s1.setBounds(20, 40, 160, 10);

        Rectangle rect1 = new Rectangle(5, 2, 115, 30);
        buttons schema = new buttons(" Schema ", rect1);
        schema.setBounds(155, 25, 110, 30);

        separator s2 = new separator();
        s2.setBounds(260, 40, 118, 10);
// =============================== Combobox ====================================   
        Vector<String> breath = new Vector<String>();
        breath.add(" Empty ");
        breath.add(" inhalation : exhalation ");
        breath.add(" inhalation : hold : exhalation ");
        breath.add(" inhalation : hold : exhalation : hold ");

        schema_breathing = new JComboBox(breath);
        schema_breathing.setUI((ComboBoxUI) MyComboBoxUI.createUI(schema_breathing));
        schema_breathing.setSelectedIndex(0);
        schema_breathing.setBounds(30, 65, 350, 30);
        schema_breathing.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        schema_breathing.setFont(new Font("Serif", Font.BOLD, 16));
        schema_breathing.setEditable(false);
        schema_breathing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // System.out.println("Your choice : " + ((JComboBox)e.getSource()).getSelectedItem() );

                Object o = ((JComboBox) e.getSource()).getSelectedItem();

                if (o.toString() == " Empty ") {
                    setDisabledState(field_inhalation);
                    setDisabledState(field_exhalation);
                    setDisabledState(field_breathhold_after_inhalation);
                    setDisabledState(field_breathhold_after_exhalation);
                    // change label color to disabled state
                    setDisabledStateColor("Inhalation ", Color.LIGHT_GRAY, 85, 105, 125, 35);
                    setDisabledStateColor("Exhalation ", Color.LIGHT_GRAY, 85, 145, 125, 35);
                    setDisabledStateColor("Breath hold after inhalation ", Color.LIGHT_GRAY, 85, 185, 295, 35);
                    setDisabledStateColor("Breath hold after exhalation ", Color.LIGHT_GRAY, 85, 225, 295, 35);
                    setDisabledStateColor("Multiplier of breathing ", Color.LIGHT_GRAY, 105, 330, 265, 35);
                    // Disable button 'Views'
                    views.setDisabledState(true);
                    views.removeMouseListener(forBut_views);
                    // Disable button 'Colors'
                    colors.setDisabledState(true);
                    colors.removeMouseListener(forBut_colors);
                    // Disable button 'Start'
                    start.setEnabled(false);
                    start.removeMouseListener(start_breathing);
                    start.repaint();
                    // Spinner multiplier set to disabled state
                    spinner_mplier.setEnabled(false);
                }
                if (o.toString() == " inhalation : exhalation ") {
                    setEnabledState(field_inhalation, filter);
                    setEnabledState(field_exhalation, filter);
                    setDisabledState(field_breathhold_after_inhalation);
                    setDisabledState(field_breathhold_after_exhalation);
                    // change label color to disabled state
                    setEnabledStateColor("Inhalation ", Color.BLUE, 85, 105, 125, 35);
                    setEnabledStateColor("Exhalation ", Color.BLUE, 85, 145, 125, 35);
                    setDisabledStateColor("Breath hold after inhalation ", Color.LIGHT_GRAY, 85, 185, 295, 35);
                    setDisabledStateColor("Breath hold after exhalation ", Color.LIGHT_GRAY, 85, 225, 295, 35);
                    setDisabledStateColor("Multiplier of breathing ", Color.LIGHT_GRAY, 105, 330, 265, 35);
                    // Spinner multiplier set to disabled state
                    spinner_mplier.setEnabled(false);
                    // Disable button 'Views'
                    views.setDisabledState(false);
                    views.removeMouseListener(forBut_views);
                    views.addMouseListener(forBut_views);
                    // Disable button 'Colors'
                    colors.setDisabledState(false);
                    colors.removeMouseListener(forBut_colors);
                    colors.addMouseListener(forBut_colors);
                    // 
                    pranaMain.views_window.preview.removeAll();
                    pranaMain.views_window.initDefaultView();
                    // 
//                  pranaMain.colorsWindow.initialStateAllComponents();
                    pranaMain.colorsWindow = new viewsColorWindow(pranaMain);

//                    // set disabled components
//                    pranaMain.colorsWindow.buttonHoldInhalation.setDisabledState(true);
//                    pranaMain.colorsWindow.buttonHoldExhalation.setDisabledState(true);
//                    pranaMain.colorsWindow.removeAllMouseListeners(pranaMain.colorsWindow.buttonHoldInhalation);
//                    pranaMain.colorsWindow.removeAllMouseListeners(pranaMain.colorsWindow.buttonHoldExhalation);
//                    // set LABEL enabled and disabled states
//                    pranaMain.colorsWindow.setEnabledStateColor("Inhalation ", Color.BLUE, 95, 55, 125, 35);
//                    pranaMain.colorsWindow.setEnabledStateColor("Exhalation ", Color.BLUE, 95, 95, 125, 35);
//                    pranaMain.colorsWindow.setDisabledStateColor("Breath hold after inhalation ", Color.LIGHT_GRAY, 95, 135, 295, 35);
//                    pranaMain.colorsWindow.setDisabledStateColor("Breath hold after exhalation ", Color.LIGHT_GRAY, 95, 175, 295, 35);
                }
                if (o.toString() == " inhalation : hold : exhalation ") {
                    setEnabledState(field_inhalation, filter);
                    setEnabledState(field_exhalation, filter);
                    setEnabledState(field_breathhold_after_inhalation, filter);
                    setDisabledState(field_breathhold_after_exhalation);
                    // change label color to disabled state
                    setEnabledStateColor("Inhalation ", Color.BLUE, 85, 105, 125, 35);
                    setEnabledStateColor("Exhalation ", Color.BLUE, 85, 145, 125, 35);
                    setEnabledStateColor("Breath hold after inhalation ", Color.BLUE, 85, 185, 295, 35);
                    setDisabledStateColor("Breath hold after exhalation ", Color.LIGHT_GRAY, 85, 225, 295, 35);
                    setDisabledStateColor("Multiplier of breathing ", Color.LIGHT_GRAY, 105, 330, 265, 35);
                    // Spinner multiplier set to disabled state
                    spinner_mplier.setEnabled(false);
                    // Disable button 'Views'
                    views.setDisabledState(false);
                    views.removeMouseListener(forBut_views);
                    views.addMouseListener(forBut_views);
                    // Disable button 'Colors'
                    colors.setDisabledState(false);
                    colors.removeMouseListener(forBut_colors);
                    colors.addMouseListener(forBut_colors);
                    //
                    pranaMain.views_window.preview.removeAll();
                    pranaMain.views_window.initDefaultView();
                    // 
//                    pranaMain.colorsWindow.initialStateAllComponents();
                    pranaMain.colorsWindow = new viewsColorWindow(pranaMain);
//                    // set disabled components
//                    pranaMain.colorsWindow.buttonHoldExhalation.setDisabledState(true);
//                    pranaMain.colorsWindow.removeAllMouseListeners(pranaMain.colorsWindow.buttonHoldExhalation);
//                    // set LABEL enabled and disabled states
//                    pranaMain.colorsWindow.setEnabledStateColor("Inhalation ", Color.BLUE, 95, 55, 125, 35);
//                    pranaMain.colorsWindow.setEnabledStateColor("Exhalation ", Color.BLUE, 95, 95, 125, 35);
//                    pranaMain.colorsWindow.setEnabledStateColor("Breath hold after inhalation ", Color.BLUE, 95, 135, 295, 35);
//                    pranaMain.colorsWindow.setDisabledStateColor("Breath hold after exhalation ", Color.LIGHT_GRAY, 95, 175, 295, 35); 
                }
                if (o.toString() == " inhalation : hold : exhalation : hold ") {
                    setEnabledState(field_inhalation, filter);
                    setEnabledState(field_exhalation, filter);
                    setEnabledState(field_breathhold_after_inhalation, filter);
                    setEnabledState(field_breathhold_after_exhalation, filter);
                    // change label color to disabled state
                    setEnabledStateColor("Inhalation ", Color.BLUE, 85, 105, 125, 35);
                    setEnabledStateColor("Exhalation ", Color.BLUE, 85, 145, 125, 35);
                    setEnabledStateColor("Breath hold after inhalation ", Color.BLUE, 85, 185, 295, 35);
                    setEnabledStateColor("Breath hold after exhalation ", Color.BLUE, 85, 225, 295, 35);
                    setDisabledStateColor("Multiplier of breathing ", Color.LIGHT_GRAY, 105, 330, 265, 35);
                    // Spinner multiplier set to disabled state
                    spinner_mplier.setEnabled(false);
                    // Disable button 'Views'
                    views.setDisabledState(false);
                    views.removeMouseListener(forBut_views);
                    views.addMouseListener(forBut_views);
                    // Disable button 'Colors'
                    colors.setDisabledState(false);
                    colors.removeMouseListener(forBut_colors);
                    colors.addMouseListener(forBut_colors);
                    //
                    pranaMain.views_window.preview.removeAll();
                    pranaMain.views_window.initDefaultView();
                    // 
//                    pranaMain.colorsWindow.initialStateAllComponents();
                    pranaMain.colorsWindow = new viewsColorWindow(pranaMain);
                     // set LABEL enabled and disabled states
//                    pranaMain.colorsWindow.setEnabledStateColor("Inhalation ", Color.BLUE, 95, 55, 125, 35);
//                    pranaMain.colorsWindow.setEnabledStateColor("Exhalation ", Color.BLUE, 95, 95, 125, 35);
//                    pranaMain.colorsWindow.setEnabledStateColor("Breath hold after inhalation ", Color.BLUE, 95, 135, 295, 35);
//                    pranaMain.colorsWindow.setEnabledStateColor("Breath hold after exhalation ", Color.BLUE, 95, 175, 295, 35);
//                    pranaMain.colorsWindow.buttonHoldInhalation.setDisabledState(false); 
//                    pranaMain.colorsWindow.buttonHoldExhalation.setDisabledState(false);
//                    pranaMain.colorsWindow.buttonHoldInhalation.addMouseListener(pranaMain.colorsWindow.buttonHoldInhalation); 
//                    pranaMain.colorsWindow.buttonHoldExhalation.addMouseListener(pranaMain.colorsWindow.buttonHoldExhalation);
//                    pranaMain.colorsWindow.buttonHoldInhalation.addMouseListener(pranaMain.colorsWindow.choiceColor()); 
//                    pranaMain.colorsWindow.buttonHoldExhalation.addMouseListener(pranaMain.colorsWindow.choiceColor()); 
                }
                settings.base.repaint();
            }
        });
//        schema_breathing.getEditor().getEditorComponent().setBackground(Color.YELLOW);
//        ((JTextField) schema_breathing.getEditor().getEditorComponent()).setBackground(Color.YELLOW);

//
        settings.addContent("Inhalation ", 25, Color.BLUE, 85, 105, 125, 35);
        settings.addContent("Exhalation ", 25, Color.BLUE, 85, 145, 125, 35);
        settings.addContent("Breath hold after inhalation ", 25, Color.BLUE, 85, 185, 295, 35);
        settings.addContent("Breath hold after exhalation ", 25, Color.BLUE, 85, 225, 295, 35);

        field_inhalation = new JTextField(6);
        field_inhalation.setName("field_inhalation");
        field_inhalation.setForeground(new Color(184, 109, 255));
        field_inhalation.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        field_inhalation.setFont(font1);
        field_inhalation.setBounds(30, 105, 25, 30);
        // listeners
        field_inhalation.getDocument().addDocumentListener(textControl(field_inhalation, start, apply));
        field_inhalation.addCaretListener(limitName(field_inhalation));
        //FOCUS listener
        field_inhalation.addFocusListener(tfocus);

        field_exhalation = new JTextField(6);
        field_exhalation.setName("field_exhalation");
        field_exhalation.setForeground(new Color(184, 109, 255));
        field_exhalation.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        field_exhalation.setFont(font1);
        field_exhalation.setBounds(30, 145, 25, 30);
        // listeners
        field_exhalation.getDocument().addDocumentListener(textControl(field_exhalation, start, apply));
        field_exhalation.addCaretListener(limitName(field_exhalation));
        //FOCUS listener
        field_exhalation.addFocusListener(tfocus);

        field_breathhold_after_inhalation = new JTextField(6);
        field_breathhold_after_inhalation.setName("field_breathhold_after_inhalation");
        field_breathhold_after_inhalation.setForeground(new Color(184, 109, 255));
        field_breathhold_after_inhalation.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        field_breathhold_after_inhalation.setFont(font1);
        field_breathhold_after_inhalation.setBounds(30, 185, 25, 30);
        // listeners
        field_breathhold_after_inhalation.getDocument()
                .addDocumentListener(textControl(field_breathhold_after_inhalation, start, apply));
        field_breathhold_after_inhalation.addCaretListener(limitName(field_breathhold_after_inhalation));
        //FOCUS listener
        field_breathhold_after_inhalation.addFocusListener(tfocus);

        field_breathhold_after_exhalation = new JTextField(6);
        field_breathhold_after_exhalation.setName("field_breathhold_after_exhalation");
        field_breathhold_after_exhalation.setForeground(new Color(184, 109, 255));
        field_breathhold_after_exhalation.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        field_breathhold_after_exhalation.setFont(font1);
        field_breathhold_after_exhalation.setBounds(30, 225, 25, 30);
        // listeners
        field_breathhold_after_exhalation.getDocument()
                .addDocumentListener(textControl(field_breathhold_after_exhalation, start, apply));
        field_breathhold_after_exhalation.addCaretListener(limitName(field_breathhold_after_exhalation));
        //FOCUS listener
        field_breathhold_after_exhalation.addFocusListener(tfocus);

// =========================== Multiplier ======================================        
        separator s3 = new separator();
        s3.setBounds(20, 310, 130, 10);

        Rectangle rect2 = new Rectangle(5, 2, 115, 30);
        buttons mplier = new buttons("Multiplier", rect2);
        mplier.setBounds(145, 295, 110, 30);

        separator s4 = new separator();
        s4.setBounds(260, 310, 118, 10);
// -------------- spinner multiplier -----------
        SpinnerModel model1 = new SpinnerNumberModel(1, 1, 99, 1);
        spinner_mplier = new JSpinner(model1);
        spinner_mplier.setBounds(30, 330, 55, 30);

        spinner_mplier.setUI(new LeftRightSpinnerUI());
        // spinner.setPreferredSize(new Dimension(55, 30));
        spinner_mplier.setBorder(null);
        spinner_mplier.setEnabled(false);

        spinner_mplier.
                addChangeListener(new spinnerListener(spinner_mplier, field_inhalation, field_exhalation,
                                field_breathhold_after_inhalation, field_breathhold_after_exhalation));
        JTextField tf1 = ((JSpinner.DefaultEditor) spinner_mplier.getEditor()).getTextField();
        tf1.setFont(font1);
        tf1.setBorder(null);
        tf1.setDisabledTextColor(Color.black);
        tf1.setEditable(false);

        JComponent editor1 = spinner_mplier.getEditor();
        int n1 = editor1.getComponentCount();
        for (int i = 0; i < n1; i++) {
            Component c = editor1.getComponent(i);
            if (c instanceof JTextField) {
                c.setForeground(Color.white);
                c.setBackground(Color.blue);
            }
        }

        settings.addContent("Multiplier of breathing ", 25, Color.BLUE, 105, 330, 265, 35);
// =============================== Cycles ======================================
        separator s5 = new separator();
        s5.setBounds(20, 370, 145, 10);

        Rectangle rect3 = new Rectangle(5, 2, 115, 30);
        buttons cycles = new buttons("Cycles", rect3);
        cycles.setBounds(140, 355, 110, 30);

        separator s6 = new separator();
        s6.setBounds(240, 370, 138, 10);
// -------------- spinner cycles -----------
        SpinnerModel model2 = new SpinnerNumberModel(1, 1, 99, 1);
        spinner_cycles = new JSpinner(model2);
        spinner_cycles.setBounds(30, 390, 55, 30);

        spinner_cycles.setUI(new LeftRightSpinnerUI());
        // spinner.setPreferredSize(new Dimension(55, 30));
        spinner_cycles.setBorder(null);
        spinner_cycles.addChangeListener(new cyclesListener(spinner_cycles));

        JTextField tf2 = ((JSpinner.DefaultEditor) spinner_cycles.getEditor()).getTextField();
        tf2.setFont(font1);
        tf2.setBorder(null);
        tf2.setDisabledTextColor(Color.black);
        tf2.setEditable(false);

        JComponent editor = spinner_cycles.getEditor();
        int n2 = editor.getComponentCount();
        for (int i = 0; i < n2; i++) {
            Component c = editor.getComponent(i);
            if (c instanceof JTextField) {
                c.setForeground(Color.white);
                c.setBackground(Color.blue);
            }
        }

        settings.addContent("Number of cycles", 25, Color.BLUE, 105, 390, 265, 35);
// =============================== Views =======================================
        separator s7 = new separator();
        s7.setBounds(20, 430, 145, 10);

        Rectangle rect4 = new Rectangle(5, 2, 115, 30);
        buttons view = new buttons("View", rect4);
        view.setBounds(130, 415, 110, 30);

        separator s8 = new separator();
        s8.setBounds(220, 430, 158, 10);

        // add button 'Set View'      
        views = settings.new buttonsWindow("Views", 80, 35, 6, 23);
        views.setDisabledState(false);
        // views.addMouseListener(startBreathing());
        views.setBounds(45, 450, 85, 40);
        views.addMouseListener(forBut_views);

        // add button 'Colors'      
        colors = settings.new buttonsWindow("Colors", 80, 35, 6, 23);
        colors.setDisabledState(false);
        // views.addMouseListener(startBreathing());
        colors.setBounds(265, 450, 85, 40); // 155
        colors.addMouseListener(forBut_colors);

        settings.base.add(views);
        settings.base.add(colors);
// =============================================================================
        separator s9 = new separator();
        s9.setBounds(20, 495, 160, 10);
        separator s10 = new separator();
        s10.setBounds(168, 495, 160, 10);
        separator s11 = new separator();
        s11.setBounds(318, 495, 55, 10);
// =============================================================================        
        settings.base.add(s1);
        settings.base.add(schema);
        settings.base.add(s2);
        settings.base.add(schema_breathing);

        settings.base.add(field_inhalation);
        settings.base.add(field_exhalation);
        settings.base.add(field_breathhold_after_inhalation);
        settings.base.add(field_breathhold_after_exhalation);

        settings.base.add(s3);
        settings.base.add(mplier);
        settings.base.add(s4);
        settings.base.add(spinner_mplier);

        settings.base.add(s5);
        settings.base.add(cycles);
        settings.base.add(s6);
        settings.base.add(spinner_cycles);

        settings.base.add(s7);
        settings.base.add(view);
        settings.base.add(s8);
        settings.base.add(views);

        settings.base.add(s9);
        settings.base.add(s10);
        settings.base.add(s11);

        settings.setWindowVisibility(false);
    }

    protected void removeAllMouseListeners(JComponent comp) {

        MouseListener[] listener = comp.getMouseListeners();
        for (MouseListener mouseListener : listener) {
            comp.removeMouseListener(mouseListener);
        }
    }

    class forButton_views extends MouseAdapter {
        
        forButton_views(){}
        
            @Override
            public void mousePressed(MouseEvent e) {
                // init again viewsWindow window
               // pranaMain.views_window = new viewsWindow(pranaMain);
                
                // return PREVIEW to viewsWindow.java file  which was used by viewsColorWindow.java   
//                pranaMain.views_window.preview.setBounds(25, 260, 350, 275);
//                pranaMain.views_window.preview.setBackground(Color.WHITE);
//                pranaMain.views_window.views.base.add(pranaMain.views_window.Views.previewPanel);

                pranaMain.views_window.getTransientValuesFromColorWindow();
                // Show VIEWS  window
                pranaMain.views_window.views.setWindowVisibility(true);

                settings.setWindowVisibility(false);
            }
    }
    
    class forButton_colors extends MouseAdapter {
        
        forButton_colors(){}
        
         @Override
            public void mousePressed(MouseEvent e) {
                // init again viewsWindow window
                //pranaMain.views_window = new viewsWindow(pranaMain);
                // init again COLORS window
               // pranaMain.colorsWindow = new viewsColorWindow(pranaMain);

                // Show COLOR window
                pranaMain.colorsWindow.colors.setWindowVisibility(true);
                // hide SETTINGS window
                settings.setWindowVisibility(false);
            }
    }

    private void setEnabledState(JTextField field, MyIntFilter filter) {

        field.setText("");
        // forbid input characters
        PlainDocument doc1 = (PlainDocument) field.getDocument();
        doc1.setDocumentFilter(filter);

        field.setBackground(Color.YELLOW);
        field.setEditable(true);
        field.setEnabled(true);
    }

    private void setDisabledState(final JTextField field) {
        // forbid input characters
        PlainDocument doc1 = (PlainDocument) field.getDocument();
        doc1.setDocumentFilter(null);

        field.setText(" #");
        field.setBackground(Color.GRAY);
        field.setEditable(false);
        field.setEnabled(false);

    }

    private void initBreathFalcon(int index) {
        // JComboBox
        schema_breathing.setSelectedIndex(index);
        // JTextField
        setEnabledState(field_inhalation, filter);
        setEnabledState(field_exhalation, filter);
        field_inhalation.setText("1");
        field_exhalation.setText("7");
        setDisabledState(field_breathhold_after_inhalation);
        setDisabledState(field_breathhold_after_exhalation);
    }

    private void initBreathBear(int index) {
        // JComboBox
        schema_breathing.setSelectedIndex(index);
        // JTextField
        setEnabledState(field_inhalation, filter);
        setEnabledState(field_exhalation, filter);
        field_inhalation.setText("7");
        field_exhalation.setText("1");
        setDisabledState(field_breathhold_after_inhalation);
        setDisabledState(field_breathhold_after_exhalation);
    }

    private void initBreathWolf(int index) {
        // JComboBox
        schema_breathing.setSelectedIndex(index);
        // JTextField
        setEnabledState(field_inhalation, filter);
        setEnabledState(field_exhalation, filter);
        field_inhalation.setText("1");
        field_exhalation.setText("1");
        setDisabledState(field_breathhold_after_inhalation);
        setDisabledState(field_breathhold_after_exhalation);
    }

    private void initBreathSnake(int index) {
        // JComboBox
        schema_breathing.setSelectedIndex(index);
        // JTextField
        setEnabledState(field_inhalation, filter);
        setEnabledState(field_exhalation, filter);
        field_inhalation.setText("1");
        field_exhalation.setText("1");
        setDisabledState(field_breathhold_after_inhalation);
        setDisabledState(field_breathhold_after_exhalation);
    }

    private void initOthers(int index) {
        // JComboBox
        schema_breathing.setSelectedIndex(index);
        // JTextField
        setEnabledState(field_inhalation, filter);
        setEnabledState(field_exhalation, filter);
        setEnabledState(field_breathhold_after_inhalation, filter);
        setEnabledState(field_breathhold_after_exhalation, filter);
        field_inhalation.setText("1");
        field_exhalation.setText("1");
        field_breathhold_after_inhalation.setText("1");
        field_breathhold_after_exhalation.setText("1");
    }

    public void breathChoice(String breathType) {

        if (breathType == "Breathing Falcon 1:7") {
            initBreathFalcon(1);
        }
        if (breathType == "Breathing Bear 7:1 ") {
            initBreathBear(1);
        }
        if (breathType == "Breathing Volf 1:1    ") {
            initBreathWolf(1);
        }
        if (breathType == "Breathing Snake 1:1") {
            initBreathSnake(1);
        }
        if (breathType == "Others ..... ") {
            initOthers(3);
        }
    }

    boolean isPressedApply = false;

    private MouseAdapter apply_schema() {
        MouseAdapter ma = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                isPressedApply = true;
                // get text from text fileds
                String str1 = field_inhalation.getText();
                String str2 = field_exhalation.getText();
                String str3 = field_breathhold_after_inhalation.getText();
                String str4 = field_breathhold_after_exhalation.getText();

                // get int value from text fileds
                if (!str1.equals(" #") && !str1.equals("")) {
                    val_field_inhalation = Integer.parseInt(str1);
                } else {
                    val_field_inhalation = 0;
                }

                if (!str2.equals(" #") && !str2.equals("")) {
                    val_field_exhalation = Integer.parseInt(str2);
                } else {
                    val_field_exhalation = 0;
                }

                if (!str3.equals(" #") && !str3.equals("")) {
                    val_field_breathhold_after_inhalation = Integer.parseInt(str3);
                } else {
                    val_field_breathhold_after_inhalation = 0;
                }

                if (!str4.equals(" #") && !str4.equals("")) {
                    val_field_breathhold_after_exhalation = Integer.parseInt(str4);
                } else {
                    val_field_breathhold_after_exhalation = 0;
                }

                // set value of spinner multiplier 
                spinner_mplier.setEnabled(true);
                spinner_mplier.setValue(1);
                // change Multiplier label color to enabled state
                setEnabledStateColor("Multiplier of breathing ", Color.BLUE, 105, 330, 265, 35);

                settings.base.repaint();
            }
        };
        return ma;
    }

    private MouseAdapter cancel() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                settings.setWindowVisibility(false);
            }
        };
        return ma;
    }

    class tfieldfocusListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            // System.out.println("focusGained");
        }

        @Override
        public void focusLost(FocusEvent e) {
            // System.out.println("focusLost");
        }

    }

    private DocumentListener textControl(final JTextField tfield,
            final patternWindow.buttonsWindow start,
            final patternWindow.buttonsWindow apply) {

        DocumentListener dl = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {

                //   System.out.println("insertUpdate focus: " + tfield.hasFocus());
                if (tfield.hasFocus()) {
                    isPressedApply = false;
                }

                changed();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

                //   System.out.println("removeUpdate focus: " + tfield.hasFocus());
                if (tfield.hasFocus()) {
                    isPressedApply = false;
                }

                changed();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //  System.out.println("changedUpdate: ");
            }

            public void changed() {

                if (tfield.getText().equals("")) {

                    start.setDisabledState(true);
                    start.removeMouseListener(start_breathing);
                    start.repaint();
                    // set button 'apply' in disabled state
                    apply.setDisabledState(true);
                    apply.removeMouseListener(apply_schema());
                    apply.repaint();

                    // Multiplier spinner
                    spinner_mplier.setEnabled(false);
                    setDisabledStateColor("Multiplier of breathing ", Color.LIGHT_GRAY, 105, 330, 265, 35);
                    // System.out.println("zero: ");
                } else {

                    Object o = schema_breathing.getSelectedItem();

                    if (o.toString() == " Empty ") {
                        start.removeMouseListener(start_breathing);
                        start.setDisabledState(true);
                        // set button 'apply' in enabled state
                        apply.setDisabledState(true);
                        apply.addMouseListener(apply_schema());
                        // Multiplier spinner
                        spinner_mplier.setEnabled(false);
                        // change Multiplier label color to disabled state
                        setDisabledStateColor("Multiplier of breathing ", Color.LIGHT_GRAY, 105, 330, 265, 35);
                        //  System.out.println("nonzero: 1 ");
                    }
                    if (o.toString() == " inhalation : exhalation ") {
                        if (!field_inhalation.getText().equals("") && !field_exhalation.getText().equals("")) {
                            start.addMouseListener(start_breathing);
                            start.setDisabledState(false);
                            // set button 'apply' in enabled state
                            apply.setDisabledState(false);
                            apply.addMouseListener(apply_schema());

                            // Multiplier spinner
                            if (isPressedApply == true) {
                                spinner_mplier.setEnabled(true);
                                setEnabledStateColor("Multiplier of breathing ", Color.BLUE, 105, 330, 265, 35);
                            } else {
                                spinner_mplier.setEnabled(false);
                                setDisabledStateColor("Multiplier of breathing ", Color.LIGHT_GRAY, 105, 330, 265, 35);
                            }
//                            System.out.println("nonzero: 2 ");
                        }
                    }
                    if (o.toString() == " inhalation : hold : exhalation ") {
                        if (!field_inhalation.getText().equals("")
                                && !field_breathhold_after_inhalation.getText().equals("")
                                && !field_exhalation.getText().equals("")) {
                            start.addMouseListener(start_breathing);
                            start.setDisabledState(false);
                            // set button 'apply' in enabled state
                            apply.setDisabledState(false);
                            apply.addMouseListener(apply_schema());

                            // Multiplier spinner
                            if (isPressedApply == true) {
                                spinner_mplier.setEnabled(true);
                                setEnabledStateColor("Multiplier of breathing ", Color.BLUE, 105, 330, 265, 35);
                            } else {
                                spinner_mplier.setEnabled(false);
                                setDisabledStateColor("Multiplier of breathing ", Color.LIGHT_GRAY, 105, 330, 265, 35);
                            }
//                            System.out.println("nonzero: 3 ");
                        }
                    }
                    if (o.toString() == " inhalation : hold : exhalation : hold ") {
                        if (!field_inhalation.getText().equals("")
                                && !field_breathhold_after_inhalation.getText().equals("")
                                && !field_exhalation.getText().equals("")
                                && !field_breathhold_after_exhalation.getText().equals("")) {
                            start.addMouseListener(start_breathing);
                            start.setDisabledState(false);
                            // set button 'apply' in enabled state
                            apply.setDisabledState(false);
                            apply.addMouseListener(apply_schema());

                            // Multiplier spinner
                            if (isPressedApply == true) {
                                spinner_mplier.setEnabled(true);
                                setEnabledStateColor("Multiplier of breathing ", Color.BLUE, 105, 330, 265, 35);
                            } else {
                                spinner_mplier.setEnabled(false);
                                setDisabledStateColor("Multiplier of breathing ", Color.LIGHT_GRAY, 105, 330, 265, 35);
                            }
                            //  System.out.println("nonzero: 4 ");
                        }
                    }

                }
                start.repaint();
                apply.repaint();
                settings.base.repaint();
            }
        };
        return dl;
    }

    private CaretListener limitName(final JTextField tfield) {

        CaretListener cl = new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {
                limitation(e.getDot());
            }

            protected void limitation(final int dot) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (dot > 2) {
                            tfield.setCaretPosition(0);
                            String t = tfield.getText().substring(0, 2);
                            tfield.setText("");
                            tfield.setText(t);
                        }
                    }
                });
            }
        };
        return cl;
    }

    @Override
    public void setDisabledStateColor(String name, Object resource, int x, int y, int w, int h) {
        // remove label
        settings.removeLabel(name);
        settings.addContent(name, 25, (Color) resource, x, y, w, h);

    }

    @Override
    public void setEnabledStateColor(String name, Object resource, int x, int y, int w, int h) {
        // remove label
        settings.removeLabel(name);
        settings.addContent(name, 25, (Color) resource, x, y, w, h);
    }

    class spinnerListener implements ChangeListener {

        JTextField tf1, tf2, tf3, tf4;
        JSpinner spinner;

        spinnerListener(JSpinner spinner, JTextField tf1, JTextField tf2, JTextField tf3, JTextField tf4) {
            this.spinner = spinner;
            this.tf1 = tf1;
            this.tf2 = tf2;
            this.tf3 = tf3;
            this.tf4 = tf4;
        }

        public void stateChanged(ChangeEvent e) {

            spinner = (JSpinner) e.getSource();
            Integer currentValue = (Integer) spinner.getValue();
          //  spinner.setValue(currentValue);

            //  tfield not should be empty and have inside number
            int val1 = currentValue * val_field_inhalation;
            int val2 = currentValue * val_field_exhalation;
            int val3 = currentValue * val_field_breathhold_after_inhalation;
            int val4 = currentValue * val_field_breathhold_after_exhalation;

            if (val1 != 0) {
                tf1.setText("" + val1);
            } else {
                tf1.setText(" #");
            }

            if (val2 != 0) {
                tf2.setText("" + val2);
            } else {
                tf2.setText(" #");
            }

            if (val3 != 0) {
                tf3.setText("" + val3);
            } else {
                tf3.setText(" #");
            }

            if (val4 != 0) {
                tf4.setText("" + val4);
            } else {
                tf4.setText(" #");
            }

//            if(apply.getDisabledState() == true) {
//                spinner.setEnabled(false); 
//                System.out.println("spinner t : " + apply.getDisabledState());
//            } 
//            else {
//                spinner.setEnabled(true);
//                System.out.println("spinner f: " + apply.getDisabledState());
//            }
            // System.out.println("val3: " + val3);
        }
    }

    class cyclesListener implements ChangeListener {

        JSpinner spinner;

        cyclesListener(JSpinner spinner) {
            this.spinner = spinner;
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            spinner = (JSpinner) e.getSource();
            Integer currentValue = (Integer) spinner.getValue();
            System.out.println("cycles: " + currentValue);
        }
    }

    public static void main(String[] args) {

//        settingsBreathing sb = new settingsBreathing("Breathing Volf 1:1    ");  // Breathing Snake 1:1
//        sb.settings.setWindowVisibility(true);
    }
}
