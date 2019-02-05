package PRANAYAMA;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class menu {

    JPanel menuPanel;
    buttons 
            optionsMenuItem, statisticsMenuItem, helpMenuItem, 
            gamesMenuItem,
            showMenuButton, // strelkaLeft
            hideMenuButton, // strelkaRight
            exitButton;
    
    // when one click on 'Options' -> show suboptions menu
    // when second click on 'Options' -> hide suboptions menu
    boolean showOptionsSubMenu = false; 
    // when one click on 'Games' -> show submenu games
    // when second click on 'Games' -> hide submenu games
    boolean showGamesSubMenu = false;
    // when one click on 'Help' -> show submenu games
    // when second click on 'Help' -> hide submenu games
    boolean showHelpSubMenu = false;

    // prevent repeat click menu items(options, statistics or help)
    boolean blockMenu = false;

    PRANAYAMA pranaMain;
    //Menu 'Options'  -> 'Start game','pause game','Settings...','exit' 
    //Menu 'Games'    -> submenu 'Save game','Download game','remove game'
 //   patternWindow Games;
//    tableOfRecords Statistics;
//    submenuSettings submenuSettingsWindow;
//    downloadRemoveGame downloadGameWindow, removeGameWindow;
//    saveGame savegame;

    menuActions ma;
    
    submenu_Help Help ;
    submenu_Options Options;
    submenu_Breath Breath;

    menu(PRANAYAMA prana) {
        this.pranaMain = prana;
        
        ma = new menuActions();

        //---------------------- Options ---------------------------------------
        Options = new submenu_Options(prana);
        
//        optionsWindow();
        
        // Submenu 'Save Game'
//        savegame = new saveGame(mainBricks);
        // Submenu 'Download Game'
//        downloadGameWindow = new downloadRemoveGame(mainBricks, false);
        // Submenu 'Remove Game'
//        removeGameWindow   = new downloadRemoveGame(mainBricks, true);
        // Submenu 'Settings .......'
//        submenuSettingsWindow = new submenuSettings(mainBricks); 
        
//        getInitStateLabelsScoreLifeLevel();
        //---------------------- Games -----------------------------------------
        Breath = new submenu_Breath(prana, this);

        //---------------------- Statistics ------------------------------------
//        Statistics = new tableOfRecords(mainBricks); // new updateTable(mainBricks)
//        Statistics.setWindowVisibility(false);
        //------------------------ Help ----------------------------------------
        Help = new submenu_Help(prana, this);

        menuPanel = new JPanel(null);
        menuPanel.setSize(new Dimension(945, 30));
        menuPanel.setBackground(new Color(130, 220, 220));
        menuPanel.setVisible(false);

        Rectangle rect1 = new Rectangle(1, 1, 90, 24);
        optionsMenuItem = new buttons("Options", rect1);
        optionsMenuItem.setBounds(1, 1, 90, 27);
        
        Rectangle rect2 = new Rectangle(1, 1, 65, 24);
        gamesMenuItem = new buttons("Breath", rect2);
        gamesMenuItem.setBounds(115, 1, 65, 27);

        Rectangle rect3 = new Rectangle(1, 1, 95, 24);
        statisticsMenuItem = new buttons("Statistics", rect3);
        statisticsMenuItem.setBounds(210, 1, 95, 27);

        Rectangle rect4 = new Rectangle(1, 1, 50, 24);
        helpMenuItem = new buttons("Help", rect4);
        helpMenuItem.setBounds(330, 1, 50, 27);

        optionsMenuItem.addMouseListener(ma);
        gamesMenuItem.addMouseListener(ma);
        statisticsMenuItem.addMouseListener(ma);
        helpMenuItem.addMouseListener(ma);
//        optionsMenuItem.addMouseListener(rma);
//        statisticsMenuItem.addMouseListener(rma);
//        helpMenuItem.addMouseListener(rma);

        menuPanel.add(optionsMenuItem);
        menuPanel.add(gamesMenuItem);
        menuPanel.add(statisticsMenuItem);
        menuPanel.add(helpMenuItem);

        pranaMain.basic.add(menuPanel);

        showMenuButton = new buttons(2); // button for displaying menu
        showMenuButton.setBounds(945, 0, 24, 24);
        showMenuButton.addMouseListener(ma); // add mouse listener
        showMenuButton.setVisible(true);

        hideMenuButton = new buttons(3); // button for hide menu
        hideMenuButton.setBounds(945, 0, 24, 24);
        hideMenuButton.addMouseListener(ma); // add mouse listener
        hideMenuButton.setVisible(false);

        exitButton = new buttons(1); // button for common exit
        exitButton.setBounds(975, 3, 24, 24);

        pranaMain.basic.add(showMenuButton);
        pranaMain.basic.add(hideMenuButton);
        pranaMain.basic.add(exitButton);
    }

    private boolean defineSource(String str1, String str2) {
        return str1.contains(str2);
    }
    
    // get 
//    private void getInitStateLabelsScoreLifeLevel() {
//        
//        if (submenuSettingsWindow.checkboxScore.getPressedState() == false &&
//            submenuSettingsWindow.checkboxLife.getPressedState()  == false &&
//            submenuSettingsWindow.checkboxLevel.getPressedState() == false    ) {
//             mainBricks.score.setBounds( 30, 50, 206, 26);
//             mainBricks.life.setBounds(  30, 80, 106, 26);
//             mainBricks.level.setBounds( 30, 110,106, 26);
//             mainBricks.basic.add(mainBricks.score);
//             mainBricks.basic.add(mainBricks.life);
//             mainBricks.basic.add(mainBricks.level);
//        } else if(submenuSettingsWindow.checkboxScore.getPressedState() == true &&
//                  submenuSettingsWindow.checkboxLife.getPressedState()  == false &&
//                  submenuSettingsWindow.checkboxLevel.getPressedState() == false   ) {
//             mainBricks.life.setBounds(  30, 50, 106, 26);
//             mainBricks.level.setBounds( 30, 80,106, 26);
//             mainBricks.basic.add(mainBricks.life);
//             mainBricks.basic.add(mainBricks.level);
//        } else if(submenuSettingsWindow.checkboxScore.getPressedState() == false &&
//                  submenuSettingsWindow.checkboxLife.getPressedState()  == true &&
//                  submenuSettingsWindow.checkboxLevel.getPressedState() == false   ) {
//             mainBricks.score.setBounds( 30, 50, 206, 26);
//             mainBricks.level.setBounds( 30, 80,106, 26);
//             mainBricks.basic.add(mainBricks.score);
//             mainBricks.basic.add(mainBricks.level);
//        } else if(submenuSettingsWindow.checkboxScore.getPressedState() == false &&
//                  submenuSettingsWindow.checkboxLife.getPressedState()  == false &&
//                  submenuSettingsWindow.checkboxLevel.getPressedState() == true   ) {
//             mainBricks.score.setBounds( 30, 50, 206, 26);
//             mainBricks.life.setBounds( 30, 80,106, 26);
//             mainBricks.basic.add(mainBricks.score);
//             mainBricks.basic.add(mainBricks.life);
//        }
//         else if(submenuSettingsWindow.checkboxScore.getPressedState() == true &&
//                  submenuSettingsWindow.checkboxLife.getPressedState()  == true &&
//                  submenuSettingsWindow.checkboxLevel.getPressedState() == false) {
//             mainBricks.level.setBounds( 30, 50,106, 26);
//             mainBricks.basic.add(mainBricks.level);
//        } else if(submenuSettingsWindow.checkboxScore.getPressedState() == true &&
//                  submenuSettingsWindow.checkboxLife.getPressedState()  == false &&
//                  submenuSettingsWindow.checkboxLevel.getPressedState() == true) {
//             mainBricks.life.setBounds( 30, 50,106, 26);
//             mainBricks.basic.add(mainBricks.life);
//        } else if(submenuSettingsWindow.checkboxScore.getPressedState() == false &&
//                  submenuSettingsWindow.checkboxLife.getPressedState()  == true &&
//                  submenuSettingsWindow.checkboxLevel.getPressedState() == true) {
//             mainBricks.score.setBounds( 30, 50,206, 26);
//             mainBricks.basic.add(mainBricks.score);
//        }
//    }

    
   
    
    // Menu 'Games' -> submenu 'Save Game'
//    private void showSaveGameWindow() {
//        savegame.iGamerName.frame.setVisible(true); 
//    }
    
    // Menu 'Games' -> submenu 'Download Game'
//    public void downloadWindow() {
//        downloadGameWindow.downloadWindow.setWindowVisibility(true);
//    }
    
    // Menu 'Games' -> submenu 'Remove Game'
//    public void removeWindow() {
//        removeGameWindow.removeWindow.setWindowVisibility(true);
//    }
    
    // Menu 'Options' -> submenu 'Settings'
//    private void settingsWindow() {
//        submenuSettingsWindow.settings.setWindowVisibility(true); 
//    }


    // 
    class menuActions implements MouseListener {

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
//        System.out.println("entrt");
            optionsMenuItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            
           // JPanel p = (JPanel) e.getSource();
//              if(Games.window.contains(e.getXOnScreen(), e.getYOnScreen())  ) 
//                Games.setWindowVisibility(true);
//              
//              Games.window.
            
             String str = e.toString();
//             if (defineSource(str, "Save game    ") || defineSource(str, "Download game ") || 
//                 defineSource(str, "Remove game     ")) {
//                 Games.setWindowVisibility(true);
//                 System.out.println(str);
//             }
        }

        @Override
        public void mouseExited(MouseEvent e) {
//        System.out.println("ex");
            optionsMenuItem.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            
//           if(!Games.window.contains(e.getXOnScreen(), e.getYOnScreen()) )  
//                Games.setWindowVisibility(false);
//          
//            String str = e.toString();
//            if (defineSource(str, "Save game    ") || defineSource(str, "Download game ") || 
//                 defineSource(str, "Remove game     ")) {
//                 Games.setWindowVisibility(false);
//                 System.out.println(str);
//             }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {

            String str = e.toString();
                 
            if (
//                (submenuSettingsWindow.settings.window.isVisible() == false) && 
//                (savegame.iGamerName.frame.isVisible() == false) && 
//                (downloadGameWindow.downloadWindow.window.isVisible() == false) &&   
//                (removeGameWindow.removeWindow.window.isVisible() == false) &&
                (Help.about.window.isVisible() == false) && (Help.helpMenu.window.isVisible() == false) &&
                (Options.options.window.isVisible() == false) 
                 && (Breath.breath.window.isVisible() == false) 
//               && (Statistics.window.isVisible() == false) && (Help.window.isVisible() == false)
                 ) {
                
            if (defineSource(str, "strelkaLeft") || defineSource(str, "strelkaRight")) {          
                if (showMenuButton.isVisible() && !hideMenuButton.isVisible()) {
                    showMenuButton.setVisible(false);
                    hideMenuButton.setVisible(true);
                    menuPanel.setVisible(true);
                } else if (!showMenuButton.isVisible() && hideMenuButton.isVisible()) {
                    showMenuButton.setVisible(true);
                    hideMenuButton.setVisible(false);
                    menuPanel.setVisible(false);
                }
              }
            }

            if (
//                (submenuSettingsWindow.settings.window.isVisible() == false) &&
//                (savegame.iGamerName.frame.isVisible() == false) && 
//                (downloadGameWindow.downloadWindow.window.isVisible() == false) &&
//                (removeGameWindow.removeWindow.window.isVisible() == false) &&
                (Help.about.window.isVisible() == false) && (Help.helpMenu.window.isVisible() == false) 
               &&(Breath.breath.window.isVisible() == false)
//               && (Statistics.window.isVisible() == false) && (Help.window.isVisible() == false)
                    ) {
                // display suboptions 'Start Game', 'Pause Game', 'Download Game', 'Settings'
                // first click - show suboptions
                // second click - hidee suboptions
                if (defineSource(str, "Options")) {
                    if(!showOptionsSubMenu) {
                        showOptionsSubMenu = true;
                        Options.options.setWindowVisibility(true);
                    }
                    else {
                        showOptionsSubMenu = false;
                        Options.options.setWindowVisibility(false);
                    }
                }
            } 
            
            if (
//                (submenuSettingsWindow.settings.window.isVisible() == false) &&
//                (savegame.iGamerName.frame.isVisible() == false) && 
//                (downloadGameWindow.downloadWindow.window.isVisible() == false) && 
//                (removeGameWindow.removeWindow.window.isVisible() == false) &&
                (Help.about.window.isVisible() == false) && (Help.helpMenu.window.isVisible() == false) &&
                (Options.options.window.isVisible() == false) 
//               &&(Statistics.window.isVisible() == false) && (Help.window.isVisible() == false)
                    ) {
                // display submenu of 'Games'
                if (defineSource(str, "Breath")) {
                    if(!showGamesSubMenu) {
                       // System.out.println("");
                        showGamesSubMenu = true;
                        Breath.breath.setWindowVisibility(true);
                    }
                    else {
                        showGamesSubMenu = false;
                        Breath.breath.setWindowVisibility(false);
                    }
                }
            }

            if (
//                (submenuSettingsWindow.settings.window.isVisible() == false) &&
//                (savegame.iGamerName.frame.isVisible() == false) && 
//                (downloadGameWindow.downloadWindow.window.isVisible() == false) &&
//                (removeGameWindow.removeWindow.window.isVisible() == false) &&
                (Help.about.window.isVisible() == false) && (Help.helpMenu.window.isVisible() == false) &&
                (Options.options.window.isVisible() == false)
                && (Breath.breath.window.isVisible() == false) 
                 && (Help.help.window.isVisible() == false)) {
                // display table of records
//                if (defineSource(str, "Statistics")) {
//                    Statistics.setWindowVisibility(true);
//                }
            }

            if (
//                (submenuSettingsWindow.settings.window.isVisible() == false) &&
//                (savegame.iGamerName.frame.isVisible() == false) && 
//                (downloadGameWindow.downloadWindow.window.isVisible() == false) &&  
//                (removeGameWindow.removeWindow.window.isVisible() == false) &&
                (Help.about.window.isVisible() == false) &&   
                (Options.options.window.isVisible() == false) && (Help.help.window.isVisible() == false)
               && (Breath.breath.window.isVisible() == false)
//                    && (Statistics.window.isVisible() == false)
                    ) {
                // display help submenu
                // first click - show suboptions
                // second click - hidee suboptions
                if (defineSource(str, "Help")) { 
                   if(!showHelpSubMenu) {
                        showHelpSubMenu = true;
                        Help.helpMenu.setWindowVisibility(true);
                    }
                    else {
                        showHelpSubMenu = false;
                        Help.helpMenu.setWindowVisibility(false);
                    } 
                }
            }

        }

    }

}
