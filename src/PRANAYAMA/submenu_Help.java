
package PRANAYAMA;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class submenu_Help {
    
       //Menu 'Help'  -> submenu 'Help','About'
       buttons helpMenuSubItem, aboutMenuSubItem;
       // helpMenu -> is window which contains 'help' and 'about' windows
       patternWindow helpMenu, help, about;
       PRANAYAMA pranaMain;
       menu mainMenu;
    
    submenu_Help(PRANAYAMA prana, menu menu) {
        this.pranaMain = prana;
        this.mainMenu = menu;
        
        helpSubMenu();
        helpWindow();
        aboutWindow();
    }
    
    
     private void helpSubMenu() {
        helpMenu = new patternWindow(pranaMain, "", false, 1, false, false, 325, 35, 120, 70);
        helpMenu.base.setBackground(new Color(190, 225, 220));

        // Menu 'Help' ->  Submenu  'Help'
        Rectangle rect1 = new Rectangle(5, 2, 115, 30);
        helpMenuSubItem = new buttons("Reference  ", rect1);
        helpMenuSubItem.setBounds(5, 2, 150, 30);
        helpMenuSubItem.addMouseListener(mainMenu.ma); 
        helpMenuSubItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
             help.setWindowVisibility(true);
             helpMenu.setWindowVisibility(false);
           }
        });
        
        // Menu 'Help' ->  Submenu  'About ...'
        Rectangle rect2 = new Rectangle(5, 2, 105, 30);
        aboutMenuSubItem = new buttons("About ...  ", rect2);
        aboutMenuSubItem.setBounds(5, 32, 150, 30);
        aboutMenuSubItem.addMouseListener(mainMenu.ma); 
        aboutMenuSubItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
             about.setWindowVisibility(true);
             helpMenu.setWindowVisibility(false);
           }
        });
        
        helpMenu.base.add(helpMenuSubItem);
        helpMenu.base.add(aboutMenuSubItem);
        helpMenu.setWindowVisibility(false);
    }
    
    private void aboutWindow() {
        about = new patternWindow(pranaMain, "Help", true, 0, true, true, 0, 0, 450, 195);
        about.base.setBackground(new Color(190, 225, 220));
        
        about.addContent("Copyright by Alexey Kravchenko", 30, Color.BLUE, 15, 40, 440, 35);
        about.addContent(" 2014 to 2018", 30, Color.BLUE, 125, 80, 205, 35);
        
        about.addButtonsWindow("Close", 191, 130, 72, 37).setButtonAction(0);// +50 +65 
        about.setWindowVisibility(false);
    }
    
     private void helpWindow() {
        help = new patternWindow(pranaMain, "Help", true, 0, true, true, 0, 0, 400, 515);
        // (String str, int fontSize, int x, int y, int width, int height)
        help.addContent("s  -  ", 30, Color.BLUE, 45, 40, 85, 35);
        help.addContent("start new game ", 30, Color.BLUE, 105, 40, 205, 35);

        help.addContent("p  -  ", 30, Color.BLUE, 45, 80, 85, 35);
        help.addContent("pause  game ", 30, Color.BLUE, 105, 80, 205, 35);
        
        help.addContent("x  -  ", 30, Color.BLUE, 45, 120, 85, 35);
        help.addContent("start game ", 30, Color.BLUE, 105, 120, 205, 35);
        
        separator s1 = new separator();
        s1.setBounds(45, 165, 160, 10);
        separator s2 = new separator();
        s2.setBounds(165, 165, 160, 10);
        separator s3 =  new separator();
        s3.setBounds(195, 165, 160, 10);
        help.base.add(s1);
        help.base.add(s2);
        help.base.add(s3);
        
        help.addContent("Ctrl+s  - ", 30, Color.BLUE, 45, 180, 135, 35);
        help.addContent("save game ", 30, Color.BLUE, 175, 180, 205, 35);
        
        help.addContent("Ctrl+d  - ", 30, Color.BLUE, 45, 220, 130, 35);
        help.addContent("download game ",  30, Color.BLUE, 175, 220, 205, 35);
        
        help.addContent("Ctrl+r  - ", 30,  Color.BLUE, 45, 260, 135, 35);
        help.addContent("remove game ", 30, Color.BLUE, 175, 260, 205, 35);
        
        separator s4 = new separator();
        s4.setBounds(45, 305, 160, 10);
        separator s5 = new separator();
        s5.setBounds(165, 305, 160, 10);
        separator s6 = new separator();
        s6.setBounds(195, 305, 160, 10);
        help.base.add(s4);
        help.base.add(s5);
        help.base.add(s6);
        
        help.addContent("Ctrl+t  - ", 30, Color.BLUE, 45, 320, 135, 35);
        help.addContent("show Settings ", 30, Color.BLUE, 175, 320, 205, 35);
        
        help.addContent("Ctrl+c  - ", 30,  Color.BLUE, 45, 360, 135, 35);
        help.addContent("show Statistics ",  30, Color.BLUE, 175, 360, 205, 35);
        
        help.addContent("Ctrl+h  - ", 30, Color.BLUE, 45, 400, 135, 35);
        help.addContent("show Help ", 30, Color.BLUE, 175, 400, 205, 35);       

        // Action 0 <- it means close window
        help.addButtonsWindow("Close", 171, 450, 72, 37).setButtonAction(0); // +45 +65
        help.setWindowVisibility(false);
    }
    
}
