
package PRANAYAMA;

import javax.swing.*;
import javax.swing.Icon;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class componentEditor extends JLabel implements ChangeListener {

    JSpinner spinner;
    Icon icon;

    public componentEditor(JSpinner s) {
        super((Icon) s.getValue(), CENTER); // constructor for JLabel
        icon = (Icon) s.getValue();
        spinner = s;
        spinner.addChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        icon = (Icon) spinner.getValue();
        setIcon(icon);
       // System.out.println("SPINNER:" + icon);
    }

    public JSpinner getSpinner() {
        return spinner;
    }

    @Override
    public Icon getIcon() {
        return icon;
    }
}
