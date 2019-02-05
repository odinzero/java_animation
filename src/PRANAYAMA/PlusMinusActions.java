package PRANAYAMA;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PlusMinusActions extends MouseAdapter {

    JPanel plus_minus_panel;
    PlusMinus plus, minus;

//    int fontSize;
//    int ww, hh;
//    boolean topLimit = false;
//    boolean lowLimit = false;
    // font for JLabel 'numCycles'
//    Font numCyclesFont;
    PlusMinus_forView1 forView1;
    PlusMinus_forViewHOR forViewHOR;
    PlusMinus_forViewVER forViewVER;
    PlusMinus_forViewCIRCLE forViewCIRCLE;

    PRANAYAMA pranaMain;

    PlusMinusActions(PRANAYAMA prana) {
        this.pranaMain = prana;

        forView1 = new PlusMinus_forView1(pranaMain);
        forViewHOR = new PlusMinus_forViewHOR(pranaMain);
        forViewVER = new PlusMinus_forViewVER(pranaMain);
        forViewCIRCLE = new PlusMinus_forViewCIRCLE(pranaMain);

        plus_minus_panel = new JPanel(null);
        plus_minus_panel.setOpaque(false);
        plus_minus_panel.setSize(100, 32);

        int x = (pranaMain.frame.getSize().width - plus_minus_panel.getSize().width) / 2;
        int y = pranaMain.frame.getSize().height - plus_minus_panel.getSize().height - pranaMain.basic.getInsets().bottom - 15;
        plus_minus_panel.setLocation(x, y);

        plus = new PlusMinus(1);
        minus = new PlusMinus(2);
        plus.addMouseListener(this);
        minus.addMouseListener(this);
        plus.setBounds(2, 2, 47, 27);
        minus.setBounds(52, 2, 47, 27);

        plus_minus_panel.add(plus);
        plus_minus_panel.add(minus);

//        fontSize = pranaMain.view.getFontSize();
//      numCyclesFont = new Font("Book Antiqua", Font.PLAIN, fontSize);
        pranaMain.basic.add(plus_minus_panel);
    }

    private void choiceIncrease() {

        int index = pranaMain.mainMenu.Breath.settingsBreath.approvedIndex;

        switch (index) {
            default:
                break;
            case 0:
            case 1:
                choiceViewIncrease(1);
                break;
            case 2:
                choiceViewIncrease(2);
                break;
            case 3:
                choiceViewIncrease(3);
                break;
        }
    }

    private void choiceDecrease() {

//        int index = pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex();
        int index = pranaMain.mainMenu.Breath.settingsBreath.approvedIndex;

        switch (index) {
            default:
                break;
            case 0:
            case 1:
                choiceViewDecrease(1);
                break;
            case 2:
                choiceViewDecrease(2);
                break;
            case 3:
                choiceViewDecrease(3);
                break;
        }
    }            
                 
    public void choiceViewIncrease(int index) {
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.NUMBER_VIEW) {
            switch(index){
                default:break;
                case 0:
                case 1: forView1.increaseFont(1, 32, 20); break;
                case 2: forView1.increaseFont(2, 47, 20); break; 
                case 3: forView1.increaseFont(3, 55, 30); break;
            }
        }
        // show HORIZONTAL VIEW in  preview  panel
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.HORIZONTAL_VIEW) {
            
            switch(index){
                default:break;
                case 0:
                case 1: forViewHOR.increaseSizeGraphs(1, 32, 20); break;
                case 2: forViewHOR.increaseSizeGraphs(2, 32, 20); break; 
                case 3: forViewHOR.increaseSizeGraphs(3, 32, 20); break;
            }
        }
        // show VERTICAL VIEW in  preview  panel
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.VERTICAL_VIEW) {
            switch(index){
                default:break;
                case 0:
                case 1: forViewVER.increaseSizeGraphs(1, 32, 20); break;
                case 2: forViewVER.increaseSizeGraphs(2, 32, 20); break; 
                case 3: forViewVER.increaseSizeGraphs(3, 32, 20); break;
            }
        }
        // show CIRCLE VIEW in  preview  panel
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.CIRCLE_VIEW) {
            switch(index){
                default:break;
                case 0:
                case 1: forViewCIRCLE.increaseSizeGraphs(1, 30, 30); break;
                case 2: forViewCIRCLE.increaseSizeGraphs(2, 30, 30); break; 
                case 3: forViewCIRCLE.increaseSizeGraphs(3, 30, 30); break;
            }
        }
    }
    
    public void choiceViewDecrease(int index) {
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.NUMBER_VIEW) {
            switch(index){
                default:break;
                case 0:
                case 1: forView1.decreaseFont(1, 32, 20); break;
                case 2: forView1.decreaseFont(2, 47, 20); break; 
                case 3: forView1.decreaseFont(3, 52, 30); break;
            }
        }
        // show HORIZONTAL VIEW in  preview  panel
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.HORIZONTAL_VIEW) {
            switch(index){
                default:break;
                case 0:
                case 1: forViewHOR.decreaseSizeGraphs(1, 32, 20); break;
                case 2: forViewHOR.decreaseSizeGraphs(2, 32, 20); break; 
                case 3: forViewHOR.decreaseSizeGraphs(3, 32, 20); break;
            }
        }
        // show VERTICAL VIEW in  preview  panel
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.VERTICAL_VIEW) {
            switch(index){
                default:break;
                case 0:
                case 1: forViewVER.decreaseSizeGraphs(1, 32, 20); break;
                case 2: forViewVER.decreaseSizeGraphs(2, 32, 20); break; 
                case 3: forViewVER.decreaseSizeGraphs(3, 32, 20); break;
            }
        }
        // show CIRCLE VIEW in  preview  panel
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.CIRCLE_VIEW) {
            switch(index){
                default:break;
                case 0:
                case 1: forViewCIRCLE.decreaseSizeGraphs(1, 30, 30); break;
                case 2: forViewCIRCLE.decreaseSizeGraphs(2, 30, 30); break; 
                case 3: forViewCIRCLE.decreaseSizeGraphs(3, 30, 30); break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        PlusMinus b = (PlusMinus) e.getSource();

        if (b.getType() == 1) {
            choiceIncrease();
        }
        if (b.getType() == 2) {
            choiceDecrease();
        }

        pranaMain.view.common.revalidate();
        pranaMain.view.common.repaint();
        pranaMain.basic.revalidate();
        pranaMain.basic.repaint();
    }

}
