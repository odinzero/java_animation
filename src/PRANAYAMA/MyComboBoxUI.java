package PRANAYAMA;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

class MyComboBoxUI extends BasicComboBoxUI {

    public static ComponentUI createUI(JComponent c) {
        return new MyComboBoxUI();
    }

    private void init() {
//       UIManager.put("ComboBox.background", new ColorUIResource(Color.yellow));
//       UIManager.put("JTextField.background", new ColorUIResource(Color.yellow));
//       UIManager.put("ComboBox.selectionBackground", new ColorUIResource(Color.magenta));
//       UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.blue));

        UIManager.put("ComboBox.buttonBackground", new ColorUIResource(Color.yellow));
        UIManager.put("ComboBox.buttonShadow", new ColorUIResource(Color.blue));
        UIManager.put("ComboBox.buttonDarkShadow", new ColorUIResource(Color.blue));
        UIManager.put("ComboBox.buttonHighlight", new ColorUIResource(Color.blue));
    }

    @Override
    protected JButton createArrowButton() {
        init();
        JButton button = new BasicArrowButton(BasicArrowButton.SOUTH,
                UIManager.getColor("ComboBox.buttonBackground"),
                UIManager.getColor("ComboBox.buttonShadow"),
                UIManager.getColor("ComboBox.buttonDarkShadow"),
                UIManager.getColor("ComboBox.buttonHighlight")
        );
        //  button.setBounds(0, 0, 30, 30);
        return button;
    }

    @Override
    protected void installDefaults() {
        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(Color.magenta));
        UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.blue));

        UIManager.put("ComboBox.background", new ColorUIResource(Color.yellow));
        UIManager.put("ComboBox.foreground", new ColorUIResource(Color.blue));

        LookAndFeel.installColorsAndFont(comboBox,
                "ComboBox.background",
                "ComboBox.foreground",
                "ComboBox.font");

        comboBox.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        // LookAndFeel.installBorder( comboBox, "ComboBox.border" );
        LookAndFeel.installProperty(comboBox, "opaque", Boolean.TRUE);

//        Long l = (Long)UIManager.get("ComboBox.timeFactor");
//        timeFactor = l == null ? 1000L : l.longValue();
        //NOTE: this needs to default to true if not specified
        Boolean b = (Boolean) UIManager.get("ComboBox.squareButton");
        squareButton = b == null ? true : b;

        padding = UIManager.getInsets("ComboBox.padding");
    }

    public static void main(String args[]) {
//    String labels[] = { "A", "B", "C", "D" };
//    JFrame frame = new JFrame("Popup JComboBox");
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//    JComboBox comboBox = new JComboBox(labels);
//    comboBox.setUI((ComboBoxUI) MyComboBoxUI.createUI(comboBox));
//    frame.add(comboBox, BorderLayout.NORTH);
//
//    JComboBox comboBox2 = new JComboBox(labels);
//    frame.add(comboBox2, BorderLayout.SOUTH);
//
//    frame.setSize(300, 100);
//    frame.setVisible(true);
    }

}
