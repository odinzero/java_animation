package PRANAYAMA;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class IncreaseDecreaseScreen extends MouseAdapter {

    buttonsIncreaseDecrease increaseScreenButton;
    buttonsIncreaseDecrease decreaseScreenButton;

    PRANAYAMA pranaMain;

    IncreaseDecreaseScreen(PRANAYAMA prana) {
        this.pranaMain = prana;

        increaseScreenButton = new buttonsIncreaseDecrease(1);
        decreaseScreenButton = new buttonsIncreaseDecrease(2);
        increaseScreenButton.addMouseListener(this);
        decreaseScreenButton.addMouseListener(this);

        int xx = pranaMain.frame.getSize().width - increaseScreenButton.getBounds().width - 15;
        int yy = pranaMain.frame.getLocation().y - increaseScreenButton.getBounds().height - 25;

        increaseScreenButton.setBounds(xx, yy, 26, 27);
        decreaseScreenButton.setBounds(xx, yy, 26, 27);
        decreaseScreenButton.setVisible(false);

        pranaMain.basic.add(increaseScreenButton);
        pranaMain.basic.add(decreaseScreenButton);
    }

    public void increaseScreen() {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                
              //  pranaMain.frame.remove(pranaMain.basic);

                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int w = screenSize.width;
                int h = screenSize.height;
                Dimension screenSize1 = new Dimension(w + 20, h + 20);

                pranaMain.frame.setLocation(0, 0); // -10, -30
                pranaMain.frame.setSize(w, h);

                pranaMain.basic.setSize(screenSize);
            //    pranaMain.frame.add(pranaMain.basic);
//                pranaMain.basic.setLocation(302, 200);
//                pranaMain.basic = new basicPanel(pranaMain);
//                pranaMain.basic.setBounds(pranaMain.frame.getX(), pranaMain.frame.getY(),
//                        pranaMain.frame.getWidth(), pranaMain.frame.getHeight());
                pranaMain.basic.setSize(screenSize1);
                
//                pranaMain.frame.add(pranaMain.basic);

//                pranaMain.mainMenu.exitButton.setBounds(pranaMain.frame.getSize().width, 35, 26, 26);
//        pranaMain.basic.setPreferredSize(screenSize1);
//        pranaMain.basic.setMinimumSize(screenSize1);
//        pranaMain.basic.setMaximumSize(screenSize1);
                increaseScreenButton.setVisible(false);
                decreaseScreenButton.setVisible(true);

                System.out.println("panel width: " + pranaMain.basic.getSize().getWidth()
                        + " panel height: " + pranaMain.basic.getSize().getHeight());

                pranaMain.basic.validate();
                pranaMain.basic.repaint();
                //  pranaMain.basic.paintImmediately(new Rectangle(0,0,500,500)); 
                pranaMain.frame.revalidate();
                pranaMain.frame.repaint();

            }
        });

    }

    public void decreaseScreen() {
//        Component[] comps = menuBar.getComponents();
//        for (int k = 0; k < comps.length; k++) {
//            if (k < 2) {
//                comps[k].setVisible(true);
//            }
//        }
//         southPanel.setVisible(true);

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                Dimension defaultSize = new Dimension(1015, 600);// - 150 

//                pranaMain.frame.setLocation(200, 200);
                pranaMain.frame.setSize(defaultSize);
//                pranaMain.frame.setPreferredSize(defaultSize);
//                pranaMain.frame.setMinimumSize(defaultSize);
//                pranaMain.frame.setMaximumSize(defaultSize);

                pranaMain.basic.setSize(defaultSize);
//                pranaMain.basic.setPreferredSize(defaultSize);
//                pranaMain.basic.setMinimumSize(defaultSize);
//        pranaMain.basic.setMaximumSize(defaultSize);

                utils.center(pranaMain.frame);

                increaseScreenButton.setVisible(true);
                decreaseScreenButton.setVisible(false);

                pranaMain.basic.repaint();
                pranaMain.basic.revalidate();

                //  pranaMain.frame.setLocationRelativeTo(null);
                pranaMain.frame.repaint();
                pranaMain.frame.revalidate();
            }
        });

    }

    @Override
    public void mousePressed(MouseEvent e) {

        buttonsIncreaseDecrease b = (buttonsIncreaseDecrease) e.getSource();

        if (b.getType() == 1) {
            increaseScreen();

//            Thread t = new Thread(thr);
//            t.start();
        }
        if (b.getType() == 2) {
            decreaseScreen();
        }
    }

    Thread thr = new Thread() {
        @Override
        public void run() {
            boolean start = true;
            int w = 0;

            while (start) {
                try {

                    w += 10;
                    pranaMain.basic.setLocation(0 + w, 0 + w);

                    if (w == 100) {
                        start = false;
                    }

//                    pranaMain.basic.setSize( w, pranaMain.frame.getHeight());
                    Thread.currentThread().sleep(1000);

                    pranaMain.frame.revalidate();
                    pranaMain.frame.repaint();

                } catch (InterruptedException ex) {
                }

            }
        }
    };

}
