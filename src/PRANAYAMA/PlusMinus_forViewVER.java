package PRANAYAMA;

import javax.swing.JPanel;

public class PlusMinus_forViewVER {

    int ww, hh;
    int counterVER;

    PRANAYAMA pranaMain;

    PlusMinus_forViewVER(PRANAYAMA prana) {
        this.pranaMain = prana;
        ww = 0;
        hh = 0;
        counterVER = 0;
    }

    protected void increaseSizeGraphs(int index, int intervalWidth, int intervalHeight) {

        counterVER++;

        ww = pranaMain.view.common.getSize().width;
        hh = pranaMain.view.common.getSize().height;

//        ww += intervalWidth;
//        hh += intervalHeight;
        limitationIncreaseRange(index, intervalWidth, intervalHeight);

        pranaMain.view.common.setSize(ww, hh);
        pranaMain.view.centerPanel(pranaMain.basic, pranaMain.view.common);

//        int line_w = pranaMain.view.graphCompLine.getGraphWidth();
//        int line_h = pranaMain.view.graphCompLine.getGraphHeight();
//
//        line_w += intervalWidth;
//        line_h += intervalHeight;
//
//        pranaMain.view.graphCompLine.setGraphWidth(line_w);
//        pranaMain.view.graphCompLine.setGraphHeight(line_h);
//
//        System.out.println("Size+: " + line_w + "  " + line_h + "  " + counterVER);
//        pranaMain.view.graphCompLine.setSize(line_w, line_h);

        // int ind = pranaMain.mainMenu.Breath.settingsBreath.approvedIndex;
// ---------------------------- increase font ----------------------------------        
//        fontSize = pranaMain.view.graphCompLine.getFontSize();
//        fontSize += 1;
//        pranaMain.view.graphCompLine.setFontSize(fontSize); 
//        int deltaX = pranaMain.view.graphCompLine.getDeltaX();
//        deltaX += intervalWidth/3;
//        pranaMain.view.graphCompLine.setDeltaX(deltaX);
//        double delta =  pranaMain.view.graphCompLine.getYDelta();
//        delta -= 0 ;
//        pranaMain.view.graphCompLine.setYDelta((int) delta);
    }

    protected void decreaseSizeGraphs(int index, int intervalWidth, int intervalHeight) {

        counterVER--;

        ww = pranaMain.view.common.getSize().width;
        hh = pranaMain.view.common.getSize().height;

//        ww -= intervalWidth;
//        hh -= intervalHeight;
        limitationDecreaseRange(index, intervalWidth, intervalHeight);

        pranaMain.view.common.setSize(ww, hh);
        pranaMain.view.centerPanel(pranaMain.basic, pranaMain.view.common);

//        int line_w = pranaMain.view.graphCompLine.getGraphWidth();
//        int line_h = pranaMain.view.graphCompLine.getGraphHeight();
//
//        line_w -= intervalWidth;
//        line_h -= intervalHeight;
//
//        pranaMain.view.graphCompLine.setGraphWidth(line_w);
//        pranaMain.view.graphCompLine.setGraphHeight(line_h);

//        System.out.println("Size-: " + line_w + "  " + line_h + "  " + counterVER);
        //pranaMain.view.graphCompLine.setMinimumSize(new Dimension());
// ---------------------------- increase font ----------------------------------        
//        fontSize = pranaMain.view.graphCompLine.getFontSize();
//        fontSize -= 1;
//        pranaMain.view.graphCompLine.setFontSize(fontSize);
//
//        System.out.println(":: " + pranaMain.view.graphCompLine.getBounds().width
//                + "   " + pranaMain.view.graphCompLine.getBounds().height);
    }

    protected void setCounterVER(int val) {
        this.counterVER = val;
    }

    protected int getCounterVER() {
        return counterVER;
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

        if (index == 0 || index == 1) {
            if (counterVER >= 8) {
                ww = 446;
                hh = 510;
                counterVER = 8;
                setGraphWidthAndHeight(384, 410);
            } else {
                ww += intervalWidth;
                hh += intervalHeight;
                setGraphWidthAndHeight(line_w, line_h);
            }
        }
        if (index == 2) {
            if (counterVER >= 8) {
                ww = 496;
                hh = 520;
                counterVER = 8;
                setGraphWidthAndHeight(426, 410);
            } else {
                ww += intervalWidth;
                hh += intervalHeight;
                setGraphWidthAndHeight(line_w, line_h);
            }
        }
        if (index == 3) {
            if (counterVER >= 8) {
                ww = 556;
                hh = 520;
                counterVER = 8;
                setGraphWidthAndHeight(487, 410);
            } else {
                ww += intervalWidth;
                hh += intervalHeight;
                setGraphWidthAndHeight(line_w, line_h);
            }
        }
    }

    protected void limitationDecreaseRange(int index, int intervalWidth, int intervalHeight) {
        
        int line_w = pranaMain.view.graphCompLine.getGraphWidth();
        int line_h = pranaMain.view.graphCompLine.getGraphHeight();

        line_w -= intervalWidth;
        line_h -= intervalHeight;
        
        if (index == 0 || index == 1) {
            if (counterVER <= -2) {
                ww = 126;
                hh = 310;
                counterVER = -2;
                setGraphWidthAndHeight(64, 210);
            } else {
                ww -= intervalWidth;
                hh -= intervalHeight;
                setGraphWidthAndHeight(line_w, line_h);
            }
        }
        if (index == 2) {
            if (counterVER <= -2) {
                ww = 176;
                hh = 320;
                counterVER = -2;
                setGraphWidthAndHeight(106, 210);
            } else {
                ww -= intervalWidth;
                hh -= intervalHeight;
                setGraphWidthAndHeight(line_w, line_h);
            }
        }
        if (index == 3) {
            if (counterVER <= -2) {
                ww = 236;
                hh = 320;
                counterVER = -2;
                setGraphWidthAndHeight(167, 210);
            } else {
                ww -= intervalWidth;
                hh -= intervalHeight;
                setGraphWidthAndHeight(line_w, line_h);
            }
        }
    }

    protected int[] setDefaultPanelSize1_forGraphVER(int counter, JPanel p) {

        int w = 0;
        int h = 0;

        //--------------1  
        //Size-: 126  310
        if (counter <= -2) {
            w = 126;
            h = 310;
            counter = -2;
            setGraphWidthAndHeight(64, 210);
        }
        //Size-: 158  330
        if (counter == -1) {
            w = 158;
            h = 330;
            setGraphWidthAndHeight(96, 230);
        }
        //Size-: 190  350
        if (counter == 0) {
            w = 190;
            h = 350;
            setGraphWidthAndHeight(128, 250);
        }
        //Size+: 222  370
        if (counter == 1) {
            w = 222;
            h = 370;
            setGraphWidthAndHeight(160, 270);
        }
        //Size+: 254  390
        if (counter == 2) {
            w = 254;
            h = 390;
            setGraphWidthAndHeight(192, 290);
        }
        //Size+: 286  410
        if (counter == 3) {
            w = 286;
            h = 410;
            setGraphWidthAndHeight(224, 310);
        }
        //Size+: 318  430 
        if (counter == 4) {
            w = 318;
            h = 430;
            setGraphWidthAndHeight(256, 330);
        }
        //Size+: 350  450 
        if (counter == 5) {
            w = 350;
            h = 450;
            setGraphWidthAndHeight(288, 350);
        }
        //Size+: 382  470
        if (counter == 6) {
            w = 382;
            h = 470;
            setGraphWidthAndHeight(320, 370);
        }
        //Size+: 414  490
        if (counter == 7) {
            w = 414;
            h = 490;
            setGraphWidthAndHeight(352, 390);
        }
        //Size+: 446  510
        if (counter >= 8) {
            w = 446;
            h = 510;
            counter = 8;
            setGraphWidthAndHeight(384, 410);
        }

        p.setSize(w, h);

        int[] arr = new int[2];
        arr[0] = w;
        arr[1] = h;
        // arr[2] = fontSize;

        return arr;
    }

    protected int[] setDefaultPanelSize2_forGraphVER(int counter, JPanel p) {

        int w = 0;
        int h = 0;

        //--------------2 
        //Size-: 176  320
        if (counter <= -2) {
            w = 176;
            h = 320;
            counter = -2;
            setGraphWidthAndHeight(106, 210);
        }
        //Size-: 208  340
        if (counter == -1) {
            w = 208;
            h = 340;
            setGraphWidthAndHeight(138, 230);
        }
        //Size-: 240  360 0
        if (counter == 0) {
            w = 240;
            h = 360;
            setGraphWidthAndHeight(170, 250);
        }
        //Size+: 272  380
        if (counter == 1) {
            w = 272;
            h = 380;
            setGraphWidthAndHeight(202, 270);
        }
        //Size+: 304  400
        if (counter == 2) {
            w = 304;
            h = 400;
            setGraphWidthAndHeight(234, 290);
        }
        //Size+: 336  420 
        if (counter == 3) {
            w = 336;
            h = 420;
            setGraphWidthAndHeight(266, 310);
        }
        //Size+: 368  440 
        if (counter == 4) {
            w = 368;
            h = 440;
            setGraphWidthAndHeight(298, 330);
        }
        //Size+: 400  460  
        if (counter == 5) {
            w = 400;
            h = 460;
            setGraphWidthAndHeight(330, 350);
        }
        //Size+: 432  480
        if (counter == 6) {
            w = 432;
            h = 480;
            setGraphWidthAndHeight(362, 370);
        }
        //Size+: 464  500
        if (counter == 7) {
            w = 464;
            h = 500;
            setGraphWidthAndHeight(394, 390);
        }
        //Size+: 496  520 
        if (counter >= 8) {
            w = 496;
            h = 520;
            counter = 8;
            setGraphWidthAndHeight(426, 410);
        }

        System.out.println("VER2: " + pranaMain.view.graphCompLine.getGraphWidth() + " "
                + pranaMain.view.graphCompLine.getGraphHeight());

        p.setSize(w, h);

        int[] arr = new int[2];
        arr[0] = w;
        arr[1] = h;

        return arr;
    }

    protected int[] setDefaultPanelSize3_forGraphVER(int counter, JPanel p) {

        int w = 0;
        int h = 0;

        //--------------3  
        //Size-: 236  320
        if (counter <= -2) {
            w = 236;
            h = 320;
            counter = -2;
            setGraphWidthAndHeight(167, 210);
        }
        //Size-: 268  340 
        if (counter == -1) {
            w = 268;
            h = 340;
            setGraphWidthAndHeight(199, 230);
        }
        //Size-: 300  360  0
        if (counter == 0) {
            w = 300;
            h = 360;
            setGraphWidthAndHeight(231, 250);
        }
        //Size+: 332  380 
        if (counter == 1) {
            w = 332;
            h = 380;
            setGraphWidthAndHeight(263, 270);
        }
        //Size+: 364  400 
        if (counter == 2) {
            w = 364;
            h = 400;
            setGraphWidthAndHeight(295, 290);
        }
        //Size+: 396  420 
        if (counter == 3) {
            w = 396;
            h = 420;
            setGraphWidthAndHeight(327, 310);
        }
        //Size+: 428  440 
        if (counter == 4) {
            w = 428;
            h = 440;
            setGraphWidthAndHeight(359, 330);
        }
        //Size+: 460  460  
        if (counter == 5) {
            w = 460;
            h = 460;
            setGraphWidthAndHeight(391, 350);
        }
        //Size+: 492  480
        if (counter == 6) {
            w = 492;
            h = 480;
            setGraphWidthAndHeight(423, 370);
        }
        //Size+: 524  500
        if (counter == 7) {
            w = 524;
            h = 500;
            setGraphWidthAndHeight(455, 390);
        }
        //Size+: 556  520  
        if (counter >= 8) {
            w = 556;
            h = 520;
            counter = 8;
            setGraphWidthAndHeight(487, 410);
        }

        p.setSize(w, h);

        int[] arr = new int[2];
        arr[0] = w;
        arr[1] = h;

        return arr;
    }

}
