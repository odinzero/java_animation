package PRANAYAMA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class previewColorPanel { // extends JPanel 

    // JPanel previewPanel;
    basicPanel previewBasicPanel;
    views Views, views1, views2;

    PRANAYAMA pranaMain;

    previewColorPanel(PRANAYAMA prana) { // PRANAYAMA prana
        this.pranaMain = prana;

        previewPanel();
    }

    private JPanel previewPanel() {
        Border in1 = BorderFactory.createLineBorder(Color.blue, 4);
        Border out = BorderFactory.createEmptyBorder(8, 8, 8, 8);
        Border out1 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        Border out2 = BorderFactory.createTitledBorder(in1, "Preview", TitledBorder.ABOVE_TOP, TitledBorder.DEFAULT_JUSTIFICATION, null, Color.BLUE);

//        previewPanel = new JPanel();
//        previewPanel.setLayout(new GridBagLayout());
//        previewPanel.setMinimumSize(new Dimension(650, 275));
//        previewPanel.setPreferredSize(new Dimension(650, 275));
//        previewPanel.setSize(650, 275);
//        previewPanel.setBackground(Color.WHITE);
//        previewPanel.setBorder(BorderFactory.createCompoundBorder(out2, out));
        previewBasicPanel = new basicPanel(pranaMain);
        previewBasicPanel.setLayout(new GridBagLayout());
//        previewBasicPanel.setType(2); 
        // previewBasicPanel.setBounds(0, 0, 650, 275);
        previewBasicPanel.setSize(650, 275);
        previewBasicPanel.setMinimumSize(new Dimension(650, 275));
        previewBasicPanel.setPreferredSize(new Dimension(650, 275));
        previewBasicPanel.setBorder(BorderFactory.createCompoundBorder(out2, out));

        initPreview();

        return previewBasicPanel;
    }

    protected void initPreview() {

        // System.out.println("Preview: " + pranaMain.vDefault.defaultView);
        int index = pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex();

        // show NUMBER in  preview  panel
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.NUMBER_VIEW) {
            // views1 = new views(pranaMain, true, previewPanel);
            Views = new views(pranaMain, true, previewBasicPanel);

            if (pranaMain.basic.getType() > 0 && pranaMain.basic.getType() < 22) {
                previewBasicPanel.setType(pranaMain.basic.getType());
            } else {
                previewBasicPanel.setType(100);
                previewBasicPanel.setBackground(pranaMain.basic.getBackground());
            }

            // repaint all LABELS according main VIEW
            if (index == 0 || index == 1) {
                Views.label_Inhalation.setBackground(pranaMain.view.label_Inhalation.getBackground());
                Views.label_Exhalation.setBackground(pranaMain.view.label_Exhalation.getBackground());
                Views.label_numCycles.setBackground(pranaMain.view.label_numCycles.getBackground());
            }
            if (index == 2) {
                Views.label_Inhalation.setBackground(pranaMain.view.label_Inhalation.getBackground());
                Views.label_breathhold_after_inhalation.setBackground(pranaMain.view.getColor2());
                Views.label_Exhalation.setBackground(pranaMain.view.label_Exhalation.getBackground());
                Views.label_numCycles.setBackground(pranaMain.view.label_numCycles.getBackground());
            }
            if (index == 3) {
                Views.label_Inhalation.setBackground(pranaMain.view.label_Inhalation.getBackground());
                Views.label_breathhold_after_inhalation.setBackground(pranaMain.view.getColor2());
                Views.label_Exhalation.setBackground(pranaMain.view.label_Exhalation.getBackground());
                Views.label_breathhold_after_exhalation.setBackground(pranaMain.view.getColor4());
                Views.label_numCycles.setBackground(pranaMain.view.label_numCycles.getBackground());
            }

        }
        // show HORIZONTAL VIEW in  preview  panel
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.HORIZONTAL_VIEW) {

            // int index = pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex();
//            views1 = new views(pranaMain, true, previewPanel, 1, index);
            Views = new views(pranaMain, true, previewBasicPanel, 1, index);

            if (pranaMain.basic.getType() > 0 && pranaMain.basic.getType() < 22) {
                previewBasicPanel.setType(pranaMain.basic.getType());
            } else {
                previewBasicPanel.setType(100);
                previewBasicPanel.setBackground(pranaMain.basic.getBackground());
            }

            Views.graphCompLine.setV1(5);
            Views.graphCompLine.setV2(5);
            Views.graphCompLine.setV3(5);
            Views.graphCompLine.setV4(5);
            Views.graphCompLine.setDW1(5);
            Views.graphCompLine.setDW2(5);
            Views.graphCompLine.setDW3(5);
            Views.graphCompLine.setDW4(5);
            // repaint all LABELS according main VIEW
            if (pranaMain.view.graphCompLine != null) {
                Views.graphCompLine.setColor1(pranaMain.view.graphCompLine.getColor1());
                Views.graphCompLine.setColor2(pranaMain.view.graphCompLine.getColor2());
                Views.graphCompLine.setColor3(pranaMain.view.graphCompLine.getColor3());
                Views.graphCompLine.setColor4(pranaMain.view.graphCompLine.getColor4());
            }
        }
        // show VERTICAL VIEW in  preview  panel
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.VERTICAL_VIEW) {

            //  int index = pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex();
            //  views1 = new views(pranaMain, true, previewPanel, 2, index);
            Views = new views(pranaMain, true, previewBasicPanel, 2, index);

            if (pranaMain.basic.getType() > 0 && pranaMain.basic.getType() < 22) {
                previewBasicPanel.setType(pranaMain.basic.getType()); 
            } else {
                previewBasicPanel.setType(100);  
                previewBasicPanel.setBackground(pranaMain.basic.getBackground());
            }
            
            Views.graphCompLine.setV1(5);
            Views.graphCompLine.setV2(5);
            Views.graphCompLine.setV3(5);
            Views.graphCompLine.setV4(5);
            Views.graphCompLine.setMax1(5);
            Views.graphCompLine.setMax2(5);
            Views.graphCompLine.setMax3(5);
            Views.graphCompLine.setMax4(5);
            Views.graphCompLine.setDW1(5);
            Views.graphCompLine.setDW2(5);
            Views.graphCompLine.setDW3(5);
            Views.graphCompLine.setDW4(5);
            // repaint all LABELS according main VIEW
            if (pranaMain.view.graphCompLine != null) {
                Views.graphCompLine.setColor1(pranaMain.view.graphCompLine.getColor1());
                Views.graphCompLine.setColor2(pranaMain.view.graphCompLine.getColor2());
                Views.graphCompLine.setColor3(pranaMain.view.graphCompLine.getColor3());
                Views.graphCompLine.setColor4(pranaMain.view.graphCompLine.getColor4());
            }
        }
        // show CIRCLE VIEW in  preview  panel
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.CIRCLE_VIEW) {

            // int index = pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex();
            //  views1 = new views(pranaMain, true, previewPanel, 3, index);
            Views = new views(pranaMain, true, previewBasicPanel, 3, index);

            if (pranaMain.basic.getType() > 0 && pranaMain.basic.getType() < 22) {
                previewBasicPanel.setType(pranaMain.basic.getType()); 
            } else {
                previewBasicPanel.setType(100);  
                previewBasicPanel.setBackground(pranaMain.basic.getBackground());
            }
            
            Views.graphCompLine.setV1(5);
            Views.graphCompLine.setV2(5);
            Views.graphCompLine.setV3(5);
            Views.graphCompLine.setV4(5);
            Views.graphCompLine.setDW1(5);
            Views.graphCompLine.setDW2(5);
            Views.graphCompLine.setDW3(5);
            Views.graphCompLine.setDW4(5);
            // repaint all LABELS according main VIEW
            if (pranaMain.view.graphCompLine != null) {
                Views.graphCompLine.setColor1(pranaMain.view.graphCompLine.getColor1());
                Views.graphCompLine.setColor2(pranaMain.view.graphCompLine.getColor2());
                Views.graphCompLine.setColor3(pranaMain.view.graphCompLine.getColor3());
                Views.graphCompLine.setColor4(pranaMain.view.graphCompLine.getColor4());
            }
        }
    }

//    protected void switchPreview(views preview) {
//        Views = preview;
//    }
    public void t() {
        JPanel previewPanel = new JPanel();
        previewPanel.setLayout(new GridBagLayout());
        previewPanel.setSize(650, 275);
        previewPanel.setMinimumSize(new Dimension(650, 275));
        previewPanel.setPreferredSize(new Dimension(650, 275));
        previewPanel.setBackground(Color.WHITE);
        // previewPanel.setBorder(BorderFactory.createCompoundBorder(out2, out));

        basicPanel p = new basicPanel(previewPanel);
        p.setLayout(new GridBagLayout());
        p.setSize(650, 275);
        p.setMinimumSize(new Dimension(650, 275));
        p.setPreferredSize(new Dimension(650, 275));
        p.setType(2);

        final MyColorChooserUI chooser1 = new MyColorChooserUI("test");
        chooser1.setMyPreviewPanel(p);

        views Views1 = new views(pranaMain, true, p);

        final JFrame f = new JFrame();

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

        f.setSize(300, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // f.setLayout(null);
        f.add(button);
        // f.add(p);
        f.setVisible(true);
    }

    public static void main(String[] args) {
//        previewColorPanel prev = new previewColorPanel();
//        prev.t();

    }

}
