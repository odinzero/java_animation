package PRANAYAMA;

import java.awt.Font;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PlusMinus_forView1 {

    PRANAYAMA pranaMain;
    int fontSize;
    int ww, hh;
    boolean topLimit = false;
    boolean lowLimit = false;
    // font for JLabel 'numCycles'
    Font numCyclesFont;

    PlusMinus_forView1(PRANAYAMA prana) {
        this.pranaMain = prana;

        ww = 0;
        hh = 0;
    }

    // 1(32 20)   2(47 20)       3 ( 52 30 )
    protected void increaseFont(int index, int intervalWidth, int intervalHeight) {
// ------------- change font size labels ---------------------------------------        
        changeFontSizeLabels("increase");
//        pranaMain.basic.setSize(w += 25, h += 25);
// ------------- change size main panel ----------------------------------------
        ww = pranaMain.view.common.getSize().width;
        hh = pranaMain.view.common.getSize().height;

        if (index == 0 || index == 1) {
            if (fontSize >= 265) {
                ww = 740;
                hh = 505;
            } else {
                ww += intervalWidth;
                hh += intervalHeight;
            }
        }
        if (index == 2) {
            if (fontSize >= 239) {
                ww = 956;
                hh = 465;
            } else {
                ww += intervalWidth;
                hh += intervalHeight;
            }
        }
        if (index == 3) {
            if (fontSize >= 187) {
                ww = 995;
                hh = 485;
            } else {
                ww += intervalWidth;
                hh += intervalHeight;
            }
        }

        pranaMain.view.common.setSize(ww, hh);
        pranaMain.view.centerPanel(pranaMain.basic, pranaMain.view.common);

        //  change Border insets -----------------------------------------
        changeDividerBorderInsets("increase");

        System.out.println("Font+: " + fontSize + "  " + ww + "  " + hh + "  "
                + pranaMain.view.label_Inhalation.getSize().width + "  "
                + pranaMain.view.label_Inhalation.getSize().height + "  "
                + pranaMain.view.divider1.getSize().width
        );
    }

    //
    protected void decreaseFont(int index, int intervalWidth, int intervalHeight) { // int widthLimit
// ------------- change font size labels ---------------------------------------        
        changeFontSizeLabels("decrease");
// ------------- change size main panel ----------------------------------------        
        ww = pranaMain.view.common.getSize().width;
        hh = pranaMain.view.common.getSize().height;

        if (fontSize < 70) {
            intervalHeight = intervalHeight + 5;
        }

        if (index == 0 || index == 1) {
            if (fontSize > 18) {
                ww -= intervalWidth;
                hh -= intervalHeight;
                // System.out.println(">> 18 : " + ww);
            } else {
                ww = 132;
                hh = 105;
            }
        }
        if (index == 2) {
            if (fontSize > 18) {
                ww -= intervalWidth;
                hh -= intervalHeight;
                // System.out.println(">> 18 : " + ww);
            } else {
                ww = 157;
                hh = 105;
            }
        }
        if (index == 3) {
            if (fontSize < 70) {
                intervalHeight = intervalHeight - 5;
            }
            if (fontSize > 18) {
                ww -= intervalWidth;
                hh -= intervalHeight;
                // System.out.println(">> 18 : " + ww);
            } else {
                ww = 292;
                hh = 95;
            }
        }
        pranaMain.view.common.setSize(ww, hh);
        pranaMain.view.centerPanel(pranaMain.basic, pranaMain.view.common);

// -------------- change Border insets -----------------------------------------
        changeDividerBorderInsets("decrease");

//        System.out.println("Font-: " + fontSize + "  " + ww + "  " + hh + "  "
//                + pranaMain.view.label_Inhalation.getSize().width + "  "
//                + pranaMain.view.label_Inhalation.getSize().height + "  "
//                + pranaMain.view.divider1.getSize().width
//        );
    }

    private void changeFontSizeLabels(String text) {

        int index = pranaMain.mainMenu.Breath.settingsBreath.approvedIndex;

        fontSize = pranaMain.view.getFontSize();

        if (text == "increase") {
            if (index == 0 || index == 1) {
                if (fontSize <= 265) {
                    fontSize += 13;
                    topLimit = false;
                } else {
                    fontSize = 265;
                    topLimit = true;
                }
                // reset 'fontSize' to 265
                if (fontSize > 265) {
                    fontSize = 265;
                    topLimit = true;
                }
                //  System.out.println("Font :: " + fontSize);
            }
            if (index == 2) {
                if (fontSize <= 239) {
                    fontSize += 13;
                    topLimit = false;
                } else {
                    fontSize = 239;
                    topLimit = true;
                }
                // reset 'fontSize' to 239
                if (fontSize > 239) {
                    fontSize = 239;
                    topLimit = true;
                }
            }
            if (index == 3) {
                if (fontSize <= 187) {
                    fontSize += 13;
                    topLimit = false;
                } else {
                    fontSize = 187;
                    topLimit = true;
                }
                // reset 'fontSize' to 187
                if (fontSize > 187) {
                    fontSize = 187;
                    topLimit = true;
                }
            }

        }
        if (text == "decrease") {
            if (index == 0 || index == 1 || index == 2 || index == 3) {
                if (fontSize >= 18) {
                    fontSize -= 13;
                    lowLimit = false;
                    //  System.out.println("Font < :: " + fontSize);
                } else {
                    fontSize = 18;
                    lowLimit = true;
                    // System.out.println("Font else :: " + fontSize);
                }
                // reset 'fontSize' to 18
                if (fontSize < 18) {
                    fontSize = 18;
                    lowLimit = true;
                    //   System.out.println("Font <18 :: " + fontSize);
                }
                // System.out.println("Font :: " + fontSize);
            }

        }

        pranaMain.view.setFontSize(fontSize);

        Font f = pranaMain.view.getMainFont();

        switch (index) {
            default:
                break;
            case 0:
            case 1:
                pranaMain.view.label_Inhalation.setFont(f);
                pranaMain.view.divider1.setFont(f);
                pranaMain.view.label_Exhalation.setFont(f);
                break;
            case 2:
                pranaMain.view.label_Inhalation.setFont(f);
                pranaMain.view.divider1.setFont(f);
                pranaMain.view.label_breathhold_after_inhalation.setFont(f);
                pranaMain.view.divider2.setFont(f);
                pranaMain.view.label_Exhalation.setFont(f);
                break;
            case 3:
                pranaMain.view.label_Inhalation.setFont(f);
                pranaMain.view.divider1.setFont(f);
                pranaMain.view.label_breathhold_after_inhalation.setFont(f);
                pranaMain.view.divider2.setFont(f);
                pranaMain.view.label_Exhalation.setFont(f);
                pranaMain.view.divider3.setFont(f);
                pranaMain.view.label_breathhold_after_exhalation.setFont(f);
                break;
        }

        if (fontSize >= 70) {
            numCyclesFont = new Font("Book Antiqua", Font.PLAIN, 70);
        } else {
            numCyclesFont = new Font("Book Antiqua", Font.PLAIN, fontSize);
        }
        pranaMain.view.label_numCycles.setFont(numCyclesFont);
    }

    private void changeDividerBorderInsets(String text) {
        Insets insets = pranaMain.view.divider1.getBorder().getBorderInsets(pranaMain.view.divider1);
        int top = insets.top;

//        int index = pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex();
        int index = pranaMain.mainMenu.Breath.settingsBreath.approvedIndex;

        if (text == "increase") {
            if (!topLimit) {
                top -= 2;
            }
        }
        if (text == "decrease") {
            if (!lowLimit) {
                top += 2;
            }
        }

        Border b = pranaMain.view.setComponentPadding(top, 1, 25, 1);

        switch (index) {
            default:
                break;
            case 0:
            case 1:
                pranaMain.view.divider1.setBorder(b);
                break;
            case 2:
                pranaMain.view.divider1.setBorder(b);
                pranaMain.view.divider2.setBorder(b);
                break;
            case 3:
                pranaMain.view.divider1.setBorder(b);
                pranaMain.view.divider2.setBorder(b);
                pranaMain.view.divider3.setBorder(b);
                break;
        }
    }
    
    
    // size of JPanel 'common' [views.java] should have consistent width and height
    // for correct displaying counters when
    // user do change JCombobox 'schema_breathing' [settingsBreathing.java] from
    // " inhalation : exhalation "   to    " inhalation : hold : exhalation " and
    // then click button 'Start' in [settingsBreathing.java]
    // case for " inhalation : exhalation "  
    protected int[] setDefaultPanelSize1(int fontSize, JPanel p) {

        int w = 0;
        int h = 0;

        //  Font +: 18  132  105  36  42  10
        if (fontSize <= 18) {
            w = 132;
            h = 105;
            fontSize = 18;
            pranaMain.view.setFontSize(fontSize);

            Font f = pranaMain.view.getMainFont();
            pranaMain.view.common.setFont(f);
        }
        // Font +: 31  164  130  48  58  13
        if (fontSize == 31) {
            w = 164;
            h = 130;
        }
        // Font +: 44  196  155  62  74  16
        if (fontSize == 44) {
            w = 196;
            h = 155;
        }
        // Font +: 57  228  180  74  89  20
        if (fontSize == 57) {
            w = 228;
            h = 180;
        }

        if (fontSize == 70) {
            w = 260;
            h = 205;
        }

        //Font +: 83  292  225  74  89  20    +
        if (fontSize == 83) {
            w = 292;
            h = 225;
        }
        //Font +: 96  324  245  88  105  23 
        if (fontSize == 96) {
            w = 324;
            h = 245;
        }
        //Font +: 109  356  265  100  121  26 +
        if (fontSize == 109) {
            w = 356;
            h = 265;
        }
        //Font +: 122  388  285  114  136  29 +
        if (fontSize == 122) {
            w = 388;
            h = 285;
        }
        //Font +: 135  420  305  126  152  33 +
        if (fontSize == 135) {
            w = 420;
            h = 305;
        }
        //Font +: 148  452  325  140  168  36 +
        if (fontSize == 148) {
            w = 452;
            h = 325;
        }
        //Font +: 161  484  345  152  183  39 +
        if (fontSize == 161) {
            w = 484;
            h = 345;
        }
        //Font +: 174  516  365  166  199  42 +
        if (fontSize == 174) {
            w = 516;
            h = 365;
        }
        //Font +: 187  548  385  178  215  46 +
        if (fontSize == 187) {
            w = 548;
            h = 385;
        }
        //Font +: 200  580  405  192  230  49 +
        if (fontSize == 200) {
            w = 580;
            h = 405;
        }
        //Font +: 213  612  425  204  246  52 +
        if (fontSize == 213) {
            w = 612;
            h = 425;
        }
        //Font +: 226  644  445  218  262  55 +
        if (fontSize == 226) {
            w = 644;
            h = 445;
        }
        //Font +: 239  676  465  230  277  59 +
        if (fontSize == 239) {
            w = 676;
            h = 465;
        }
        //Font +: 252  708  485  244  293  62
        if (fontSize == 252) {
            w = 708;
            h = 485;
        }
        //Font +: 265  740  505  256  309  65
        if (fontSize >= 265) {
            w = 740;
            h = 505;
            fontSize = 265;
            pranaMain.view.setFontSize(fontSize);

            Font f = pranaMain.view.getMainFont();
            pranaMain.view.common.setFont(f);
        }

        p.setSize(w, h);

        int[] arr = new int[3];
        arr[0] = w;
        arr[1] = h;
        arr[2] = fontSize;

        return arr;
    }

    // " inhalation : hold : exhalation "
    protected int[] setDefaultPanelSize2(int fontSize, JPanel p) {

        int w = 0;
        int h = 0;

        // Font-: 18  157  105  36  42  10
        if (fontSize <= 18) {
            w = 157;
            h = 105;
            fontSize = 18;
            pranaMain.view.setFontSize(fontSize);

            Font f = pranaMain.view.getMainFont();
            pranaMain.view.common.setFont(f);
        }
        // Font-: 31  204  130  48  58  13
        if (fontSize == 31) {
            w = 204;
            h = 130;
        }
        // Font-: 44  251  155  62  74  16
        if (fontSize == 44) {
            w = 251;
            h = 155;
        }
        // Font-: 57  298  180  74  89  20
        if (fontSize == 57) {
            w = 298;
            h = 180;
        }

        if (fontSize == 70) {
            w = 345;
            h = 205;
        }
        //Font +: 83  392  225  74  89  20    +
        if (fontSize == 83) {
            w = 392;
            h = 225;
        }
        //Font +: 96  439  245  88  105  23   +
        if (fontSize == 96) {
            w = 439;
            h = 245;
        }
        //Font +: 109  486  265  100  121  26 +
        if (fontSize == 109) {
            w = 486;
            h = 265;
        }
        //Font +: 122  533  285  114  136  29 +
        if (fontSize == 122) {
            w = 533;
            h = 285;
        }
        //Font +: 135  580  305  126  152  33 +
        if (fontSize == 135) {
            w = 580;
            h = 305;
        }
        //Font +: 148  627  325  140  168  36 +
        if (fontSize == 148) {
            w = 627;
            h = 325;
        }
        //Font +: 161  674  345  152  183  39 +
        if (fontSize == 161) {
            w = 674;
            h = 345;
        }
        //Font +: 174  721  365  166  199  42 +
        if (fontSize == 174) {
            w = 721;
            h = 365;
        }
        //Font +: 187  768  385  178  215  46 +
        if (fontSize == 187) {
            w = 768;
            h = 385;
        }
        //Font +: 200  815  405  192  230  49 +
        if (fontSize == 200) {
            w = 815;
            h = 405;
        }
        //Font +: 213  862  425  204  246  52 +
        if (fontSize == 213) {
            w = 862;
            h = 425;
        }
        //Font +: 226  909  445  218  262  55 +
        if (fontSize == 226) {
            w = 909;
            h = 445;
        }
        //Font +: 239  956  465  230  277  59
        if (fontSize >= 239) {
            w = 956;
            h = 465;
            fontSize = 239;
            pranaMain.view.setFontSize(fontSize);

            Font f = pranaMain.view.getMainFont();
            pranaMain.view.common.setFont(f);
        }

        p.setSize(w, h);

        int[] arr = new int[3];
        arr[0] = w;
        arr[1] = h;
        arr[2] = fontSize;

        return arr;
    }

    // " inhalation : hold : exhalation : hold "
    protected int[] setDefaultPanelSize3(int fontSize, JPanel p) {

        int w = 0;
        int h = 0;

        // Font-: 18  292  95  36  42  10
        if (fontSize <= 18) {
            w = 292;
            h = 95;
            fontSize = 18;
            pranaMain.view.setFontSize(fontSize);

            Font f = pranaMain.view.getMainFont();
            pranaMain.view.common.setFont(f);
        }
        // Font-: 31  344  125  48  58  13
        if (fontSize == 31) {
            w = 344;
            h = 125;
        }
        // Font-: 44  396  155  62  74  16
        if (fontSize == 44) {
            w = 396;
            h = 155;
        }
        // Font-: 57  448  185  74  89  20
        if (fontSize == 57) {
            w = 448;
            h = 185;
        }

        // fontSize = 70
        if (fontSize == 70) {
            w = 500;
            h = 215;
        }
//Font+: 83  555  245  74  89  20
        if (fontSize == 83) {
            w = 555;
            h = 245;
        }
//Font+: 96  610  275  88  105  23
        if (fontSize == 96) {
            w = 610;
            h = 275;
        }
//Font+: 109  665  305  100  121  26
        if (fontSize == 109) {
            w = 665;
            h = 305;
        }
//Font+: 122  720  335  114  136  29
        if (fontSize == 122) {
            w = 720;
            h = 335;
        }
//Font+: 135  775  365  126  152  33
        if (fontSize == 135) {
            w = 775;
            h = 365;
        }
//Font+: 148  830  395  140  168  36
        if (fontSize == 148) {
            w = 830;
            h = 395;
        }
//Font+: 161  885  425  152  183  39
        if (fontSize == 161) {
            w = 885;
            h = 425;
        }
//Font+: 174  940  455  166  199  42
        if (fontSize == 174) {
            w = 940;
            h = 455;
        }
//Font+: 187  995  485  178  215  46 
        if (fontSize >= 187) {
            w = 995;
            h = 485;
            fontSize = 187;
            pranaMain.view.setFontSize(fontSize);

            Font f = pranaMain.view.getMainFont();
            pranaMain.view.common.setFont(f);
        }

        p.setSize(w, h);

        int[] arr = new int[3];
        arr[0] = w;
        arr[1] = h;
        arr[2] = fontSize;

        return arr;

    }

}
