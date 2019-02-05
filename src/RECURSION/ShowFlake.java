
package RECURSION;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ShowFlake  extends JFrame implements ActionListener {
     private final int APPLET_WIDTH = 700;
     private final int APPLET_HEIGTH = 640;

     private  int MIN = 1, MAX = 9;

     private JButton increase, decrease;
     private JLabel titleLabel, orderLabel;
     private JPanel toolPanel, appletPanel;
     KochPanel kochPanel;

   
   public  ShowFlake() {
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         toolPanel = new JPanel();
//         toolPanel.setPreferredSize(new Dimension(500,500));
         toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.X_AXIS ));
         toolPanel.setBackground(Color.YELLOW);
         toolPanel.setOpaque(true);

         titleLabel = new JLabel("SnowShake");
         titleLabel.setBackground(Color.YELLOW);

         increase = new JButton("+");
         increase.setMargin(new Insets(0,0,0,0));
         increase.addActionListener(this);

         decrease = new JButton("-");
         decrease.setMargin(new Insets(0,0,0,0));
         decrease.addActionListener(this);

         orderLabel = new JLabel("Order : 1");
         orderLabel.setBackground(Color.YELLOW);

         toolPanel.add(titleLabel);
         toolPanel.add(Box.createHorizontalStrut(20));
         toolPanel.add(increase);
         toolPanel.add(decrease);
         toolPanel.add(Box.createHorizontalStrut(20)); 
         toolPanel.add(orderLabel);

         kochPanel = new KochPanel(1);

         appletPanel = new JPanel(); // new GridLayout(2,0)
         appletPanel.add(toolPanel);
         appletPanel.add(kochPanel);

         
         add(appletPanel);
         setSize(APPLET_WIDTH, APPLET_HEIGTH );
         setVisible(true);
     }

    @Override
    public void actionPerformed(ActionEvent e) {

        int order = kochPanel.getOrder();

        if( e.getSource() == increase)
            order++;
        else
            order--;

        if( order >= MIN && order <= MAX ) {
            orderLabel.setText("Order : " + order); 
            kochPanel.setOrder(order);
    }
         repaint();
}

    public static void main(String[] args) {
        new ShowFlake();
    }
}
