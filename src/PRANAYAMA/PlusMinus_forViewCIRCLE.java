package PRANAYAMA;

import java.awt.Font;
import javax.swing.JPanel;

public class PlusMinus_forViewCIRCLE {

    int ww, hh;
    int counterCIRCLE;
    int fontSize;

    PRANAYAMA pranaMain;

    PlusMinus_forViewCIRCLE(PRANAYAMA prana) {
        this.pranaMain = prana;
        ww = 0;
        hh = 0;
        counterCIRCLE = 0;
        
        fontSize = 20;
    }

    protected void increaseSizeGraphs(int index, int intervalWidth, int intervalHeight) {

        counterCIRCLE++;

        ww = pranaMain.view.common.getSize().width;
        hh = pranaMain.view.common.getSize().height;

        limitationIncreaseRange(index, intervalWidth, intervalHeight);

        pranaMain.view.common.setSize(ww, hh);
        pranaMain.view.centerPanel(pranaMain.basic, pranaMain.view.common);

        changeFontSize("increase");
    }

    protected void decreaseSizeGraphs(int index, int intervalWidth, int intervalHeight) {

        counterCIRCLE--;

        ww = pranaMain.view.common.getSize().width;
        hh = pranaMain.view.common.getSize().height;

        limitationDecreaseRange(index, intervalWidth, intervalHeight);

        pranaMain.view.common.setSize(ww, hh);
        pranaMain.view.centerPanel(pranaMain.basic, pranaMain.view.common);

        changeFontSize("decrease");
    }

    protected void setCounterCIRCLE(int val) {
        this.counterCIRCLE = val;
    }

    protected int getCounterCIRCLE() {
        return counterCIRCLE;
    }

    protected void setGraphWidthAndHeight(int line_w, int line_h) {
        pranaMain.view.graphCompLine.setGraphWidth(line_w);
        pranaMain.view.graphCompLine.setGraphHeight(line_h);
    }

    protected void limitationIncreaseRange(int index, int intervalWidth, int intervalHeight) {

        int line_w = pranaMain.view.graphCompLine.getGraphWidth();
        int line_h = pranaMain.view.graphCompLine.getGraphHeight();

        line_w += intervalWidth;
        line_h += intervalHeight;

        if (index == 0 || index == 1 || index == 2 || index == 3) {
            if (counterCIRCLE >= 7) { // 470 500  410  410  7
                ww = 470;
                hh = 500;
                counterCIRCLE = 7;
                setGraphWidthAndHeight(410, 410);
            } else {
                ww += intervalWidth;
                hh += intervalHeight;
                setGraphWidthAndHeight(line_w, line_h);
            }
        }
        System.out.println("Size+: " + ww + " " + hh + "  " + line_w + "  " + line_h + "  " + counterCIRCLE);
    }

    protected void limitationDecreaseRange(int index, int intervalWidth, int intervalHeight) {

        int line_w = pranaMain.view.graphCompLine.getGraphWidth();
        int line_h = pranaMain.view.graphCompLine.getGraphHeight();

        line_w -= intervalWidth;
        line_h -= intervalHeight;

        if (index == 0 || index == 1 || index == 2 || index == 3) {
            if (counterCIRCLE <= -4) { // 136 130  76  30
                ww = 140;
                hh = 170;
                counterCIRCLE = -4;
                setGraphWidthAndHeight(80, 80);
            } else {
                ww -= intervalWidth;
                hh -= intervalHeight;
                setGraphWidthAndHeight(line_w, line_h);
            }
        }
        System.out.println("Size-: " + ww + " " + hh + "  " + line_w + "  " + line_h + "  " + counterCIRCLE);
    }

    private void changeFontSize(String text) {

        int index = pranaMain.mainMenu.Breath.settingsBreath.approvedIndex;

        fontSize = pranaMain.view.graphCompLine.getFontSize();

        if (text == "increase") {
            if (index == 0 || index == 1 || index == 2 || index == 3) {
                if (fontSize >= 16 && fontSize < 20) {
                    fontSize += 1;
                }
                else if (fontSize >= 20 && fontSize <= 62) {
                    fontSize += 6;
                } else {
                    fontSize = 62;
                }
                // reset 'fontSize' to 62
                if (fontSize > 62) {
                    fontSize = 62;
                }
            }
        }

        if (text == "decrease") {
            if (index == 0 || index == 1 || index == 2 || index == 3) {
                if (fontSize > 20) {
                    fontSize -= 6;
                } else if (fontSize >= 14 && fontSize <= 20) {
                    fontSize -= 1;
                } else {
                    fontSize = 16;
                }
                // reset 'fontSize' to 14
                if (fontSize < 16) {
                    fontSize = 16;
                }
            }
        }

        pranaMain.view.graphCompLine.setFontSize(fontSize);

        System.out.println("size: " + fontSize);
    }

    protected int[] setDefaultPanelSize_forGraphCIRCLE(int counter, JPanel p) {

        int w = 0;
        int h = 0;

        //Size-: 140 170  80  80  -4  FontSize:16
        if (counter <= -4) {
            w = 140;
            h = 170;
            setGraphWidthAndHeight(80, 80);
             
            fontSize = 16;
            pranaMain.view.graphCompLine.setFontSize(fontSize);

            Font f = pranaMain.view.graphCompLine.getGraphFont();
            pranaMain.view.graphCompLine.setGraphFont(f); 
        }
        //Size-: 170 200  110  110  -3
        if (counter == -3) {
            w = 170;
            h = 200;
            setGraphWidthAndHeight(110, 110);
            pranaMain.view.graphCompLine.setFontSize(fontSize);
        }
        //Size-: 200 230  140  140  -2
        if (counter == -2) {
            w = 200;
            h = 230;
            setGraphWidthAndHeight(140, 140);
            pranaMain.view.graphCompLine.setFontSize(fontSize);
        }
        //Size-: 230 260  170  170  -1
        if (counter == -1) {
            w = 230;
            h = 260;
            setGraphWidthAndHeight(170, 170);
            pranaMain.view.graphCompLine.setFontSize(fontSize);
        }
        //Size-: 260 290  200  200  0
        if (counter == 0) {
            w = 260;
            h = 290;
            setGraphWidthAndHeight(200, 200);
            pranaMain.view.graphCompLine.setFontSize(fontSize);
        }
        //Size+: 290 320  230  230  1
        if (counter == 1) {
            w = 290;
            h = 320;
            setGraphWidthAndHeight(230, 230);
            pranaMain.view.graphCompLine.setFontSize(fontSize);
        }
        //Size+: 320 350  260  260  2
        if (counter == 2) {
            w = 320;
            h = 350;
            setGraphWidthAndHeight(260, 260);
            pranaMain.view.graphCompLine.setFontSize(fontSize);
        }
        //Size+: 350 380  290  290  3
        if (counter == 3) {
            w = 350;
            h = 380;
            setGraphWidthAndHeight(290, 290);
            pranaMain.view.graphCompLine.setFontSize(fontSize);
        }
        //Size+: 380 410  320  320  4
        if (counter == 4) {
            w = 380;
            h = 410;
            setGraphWidthAndHeight(320, 320);
            pranaMain.view.graphCompLine.setFontSize(fontSize);
        }
        //Size+: 410 440  350  350  5
        if (counter == 5) {
            w = 410;
            h = 440;
            setGraphWidthAndHeight(350, 350);
            pranaMain.view.graphCompLine.setFontSize(fontSize);
        }
        //Size+: 440 470  380  380  6
        if (counter == 6) {
            w = 440;
            h = 470;
            setGraphWidthAndHeight(380, 380);
            pranaMain.view.graphCompLine.setFontSize(fontSize);
        }
        //Size+: 470 500  410  410  7
        if (counter >= 7) {
            w = 470;
            h = 500;
            setGraphWidthAndHeight(410, 410);
            
            fontSize = 62;
            pranaMain.view.graphCompLine.setFontSize(fontSize);

            Font f = pranaMain.view.graphCompLine.getGraphFont();
            pranaMain.view.graphCompLine.setGraphFont(f); 
        }
        
        p.setSize(w, h);

        int[] arr = new int[2];
        arr[0] = w;
        arr[1] = h;

        return arr;
    }

}
