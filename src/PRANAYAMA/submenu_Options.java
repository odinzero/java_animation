
package PRANAYAMA;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class submenu_Options {
    
    patternWindow options;
    //Menu 'Options' -> 'Start game','pause game','Settings...','exit' 
    buttons newGameMenuItem, pauseGameMenuItem, startGameMenuItem,
            saveGameMenuItem, downloadGameMenuItem, removeGameMenuItem,
            settingsGameMenuItem, exitGameMenuItem; 
    PRANAYAMA pranaMain;
    
    submenu_Options(PRANAYAMA prana) {
        this.pranaMain = prana;
        
        optionsWindow();
    }
    
    private void optionsWindow() {
        options = new patternWindow(pranaMain, "", false, 1, false, false, 6, 35, 265, 280);
        options.base.setBackground(new Color(190, 225, 220));

        // Menu 'Options' ->  Submenu  'Start game'
        Rectangle rect1 = new Rectangle(5, 2, 150, 30);
        newGameMenuItem = new buttons("New game  -  s ", rect1);
        newGameMenuItem.setBounds(5, 2, 150, 30);
        newGameMenuItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
//                 try {
//                   //  mainBricks.startNewGame();
//                 } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                 }
             options.setWindowVisibility(false);
           }
        });

        // Menu 'Options' ->  Submenu  'Pause game'
        Rectangle rect2 = new Rectangle(5, 2, 165, 30);
        pauseGameMenuItem = new buttons("Pause game  -  p ", rect2);
        pauseGameMenuItem.setBounds(5, 32, 165, 30);
        pauseGameMenuItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
            // prana.pauseGame();
             options.setWindowVisibility(false);
           }
        });
        
        // Menu 'Options' ->  Submenu  'Start game'
        Rectangle rect3 = new Rectangle(5, 2, 160, 30);
        startGameMenuItem = new buttons("Start game  -  x ", rect3);
        startGameMenuItem.setBounds(5, 62, 160, 30);
        startGameMenuItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
//                 try {
//                   //  mainBricks.startGame();
//                 } catch (InterruptedException ex) { ex.printStackTrace(); }
             options.setWindowVisibility(false);
           }
        });
        
        separator s1 = new separator();
        s1.setBounds(12, 98, 160, 10);
        options.base.add(s1);
        separator s2 = new separator();
        s2.setBounds(102, 98, 160, 10);
        options.base.add(s2);
        
        // Menu 'Options' ->  Submenu  'Save game'
        Rectangle rect4 = new Rectangle(5, 2, 200, 30);
        saveGameMenuItem = new buttons("Save game  -  Ctrl+s ", rect4);
        saveGameMenuItem.setBounds(5, 108, 200, 30);
        saveGameMenuItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
//             showSaveGameWindow();
             // stop main thread in order to input name user
           //  prana.suspend.set(true);   
             options.setWindowVisibility(false);
           }
        });
        
        // Menu 'Options' ->  Submenu  'Download game'
        Rectangle rect5 = new Rectangle(5, 2, 255, 30);
        downloadGameMenuItem = new buttons("Download game  -  Ctrl+d ", rect5);
        downloadGameMenuItem.setBounds(5, 138, 255, 30);
        downloadGameMenuItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
//             downloadWindow();
             options.setWindowVisibility(false);
           }
        });
        
        // Menu 'Options' ->  Submenu  'Remove game'
        Rectangle rect6 = new Rectangle(5, 2, 230, 30);
        removeGameMenuItem = new buttons("Remove game  -  Ctrl+r ", rect6);
        removeGameMenuItem.setBounds(5, 168, 230, 30);
        removeGameMenuItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
//             removeWindow();
             options.setWindowVisibility(false);
           }
        });
        
        // Menu 'Options' ->  Submenu  'Settings'
        Rectangle rect7 = new Rectangle(5, 2, 160, 30);
        settingsGameMenuItem = new buttons("Settings ...........", rect7);
        settingsGameMenuItem.setBounds(5, 198, 160, 30);
        settingsGameMenuItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
//             settingsWindow();
             options.setWindowVisibility(false);
           }
        });
        
        separator s3 = new separator();
        s3.setBounds(12, 234, 160, 10);
        options.base.add(s3);
        separator s4 = new separator();
        s4.setBounds(102, 234, 160, 10);
        options.base.add(s4);
        
        // Menu 'Options' ->  Submenu  'Exit'
        Rectangle rect8 = new Rectangle(5, 2, 50, 30);
        exitGameMenuItem = new buttons("Exit ", rect8);
        exitGameMenuItem.setBounds(107, 244, 50, 30);
        exitGameMenuItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
             System.exit(0); 
           }
        });

        options.base.add(newGameMenuItem);
        options.base.add(pauseGameMenuItem);
        options.base.add(startGameMenuItem);
        options.base.add(saveGameMenuItem);
        options.base.add(downloadGameMenuItem);
        options.base.add(removeGameMenuItem);
        options.base.add(settingsGameMenuItem);
        options.base.add(exitGameMenuItem);
        options.setWindowVisibility(false);
    }
}
