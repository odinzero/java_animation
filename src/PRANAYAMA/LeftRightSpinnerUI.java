package PRANAYAMA;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicSpinnerUI;

public class LeftRightSpinnerUI extends BasicSpinnerUI {

    public static ComponentUI createUI(JComponent c) {
        return new LeftRightSpinnerUI();
    }

    @Override
    protected Component createNextButton() {
        Component c = createArrowButton(SwingConstants.EAST);
        c.setName("Spinner.nextButton");
        installNextButtonListeners(c);
        return c;
    }

    @Override
    protected Component createPreviousButton() {
        Component c = createArrowButton(SwingConstants.WEST);
        c.setName("Spinner.previousButton");
        installPreviousButtonListeners(c);
        return c;
    }

    // copied from BasicSpinnerUI
    private Component createArrowButton(int direction) {
        JButton b = new BasicArrowButton(direction);
        Border buttonBorder = UIManager.getBorder("Spinner.arrowButtonBorder");
        if (buttonBorder instanceof UIResource) {
            // b.setBorder(new CompoundBorder(buttonBorder, null));
            b.setBackground(Color.yellow);
            b.setForeground(Color.white);
            b.setBorder(null);
            b.setPreferredSize(new Dimension(50, 50));

            // b.setBounds(0, 0, 45, 45);
        } else {
            b.setBorder(buttonBorder);
        }
        b.setInheritsPopupMenu(true);
        return b;
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        c.removeAll();
        c.setLayout(new BorderLayout() {

            @Override
            public void addLayoutComponent(Component comp, Object constraints) {
                if (constraints.equals("Editor")) {
                    constraints = CENTER;
                }
                super.addLayoutComponent(comp, constraints);
            }
        });
        c.add(createNextButton(), BorderLayout.EAST);
        c.add(createPreviousButton(), BorderLayout.WEST);
        c.add(createEditor(), BorderLayout.CENTER);
    }

    public static void main(String[] args) {

        // UIManager.put("CheckBoxUI", "PRANAYAMA.MyCheckBoxUI");
        JFrame frame = new JFrame("Popup JComboBox");
        frame.setLayout(null);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // -------------- spinner multiplier -----------
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

//        SpinnerModel model1 = new SpinnerNumberModel(1, 1, 99, 1);
        JSpinner spinner_mplier = new JSpinner(new SpinnerListModel(ic));
        spinner_mplier.setEditor(new componentEditor(spinner_mplier)); 
        spinner_mplier.setBounds(30, 25, 75, 30);

        spinner_mplier.setUI(new LeftRightSpinnerUI());
        // spinner.setPreferredSize(new Dimension(55, 30));
        spinner_mplier.setBorder(null);
        spinner_mplier.setEnabled(true);

        frame.add(spinner_mplier);
//        frame.setVisible(true);
    }
}
