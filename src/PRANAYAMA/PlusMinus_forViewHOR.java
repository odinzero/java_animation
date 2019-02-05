package PRANAYAMA;

import javax.swing.JPanel;

public class PlusMinus_forViewHOR {

    int ww, hh;
    int counterHOR;

    PRANAYAMA pranaMain;

    PlusMinus_forViewHOR(PRANAYAMA prana) {
        this.pranaMain = prana;
        ww = 0;
        hh = 0;
        counterHOR = 0;
    }

    protected void increaseSizeGraphs(int index, int intervalWidth, int intervalHeight) {

        counterHOR++;

        ww = pranaMain.view.common.getSize().width;
        hh = pranaMain.view.common.getSize().height;

        limitationIncreaseRange(index, intervalWidth, intervalHeight);

        pranaMain.view.common.setSize(ww, hh);
        pranaMain.view.centerPanel(pranaMain.basic, pranaMain.view.common);
    }

    protected void decreaseSizeGraphs(int index, int intervalWidth, int intervalHeight) {

        counterHOR--;

        ww = pranaMain.view.common.getSize().width;
        hh = pranaMain.view.common.getSize().height;
        
        limitationDecreaseRange(index, intervalWidth, intervalHeight);

        pranaMain.view.common.setSize(ww, hh);
        pranaMain.view.centerPanel(pranaMain.basic, pranaMain.view.common);
    }
    
    protected void setCounterHOR(int val) {
        this.counterHOR = val;
    }

    protected int getCounterHOR() {
        return counterHOR;
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
            if (counterHOR >= 12) { // 744 510  684  410
                ww = 744;
                hh = 510;
                counterHOR = 12;
                setGraphWidthAndHeight(684, 410);
            } else {
                ww += intervalWidth;
                hh += intervalHeight;
                setGraphWidthAndHeight(line_w, line_h);
            }
        }
        System.out.println("Size+: " + ww + " " + hh + "  " +  line_w + "  " + line_h + "  " + counterHOR);
    }

    protected void limitationDecreaseRange(int index, int intervalWidth, int intervalHeight) {

        int line_w = pranaMain.view.graphCompLine.getGraphWidth();
        int line_h = pranaMain.view.graphCompLine.getGraphHeight();

        line_w -= intervalWidth;
        line_h -= intervalHeight;

        if (index == 0 || index == 1 || index == 2 || index == 3) {
            if (counterHOR <= -7) { // 136 130  76  30
                ww = 136;
                hh = 130;
                counterHOR = -7;
                setGraphWidthAndHeight(76, 30);
            } else {
                ww -= intervalWidth;
                hh -= intervalHeight;
                setGraphWidthAndHeight(line_w, line_h);
            }
        }
         System.out.println("Size-: " + ww + " " + hh + "  " +  line_w + "  " + line_h + "  " + counterHOR);
    }
    
     protected int[] setDefaultPanelSize_forGraphHOR(int counter, JPanel p) {

        int w = 0;
        int h = 0;
        
        //Size-: 136 130  76  30  -7
        if (counter == -7) {
            w = 136;
            h = 130;
            setGraphWidthAndHeight(76, 30);
        }
        //Size-: 168 150  108  50  -6
        if (counter == -6) {
            w = 168;
            h = 150;
            setGraphWidthAndHeight(108, 50);
        }
        //Size-: 200 170  140  70  -5
        if (counter == -5) {
            w = 200;
            h = 170;
            setGraphWidthAndHeight(140, 70);
        }
        //Size-: 232 190  172  90  -4
        if (counter == -4) {
            w = 232;
            h = 190;
            setGraphWidthAndHeight(172, 90);
        }
        //Size-: 264 210  204  110  -3
        if (counter == -3) {
            w = 264;
            h = 210;
            setGraphWidthAndHeight(204, 110);
        }
        //Size-: 296 230  236  130  -2
        if (counter == -2) {
            w = 296;
            h = 230;
            setGraphWidthAndHeight(236, 130);
        }
        //Size-: 328 250  268  150  -1
        if (counter == -1) {
            w = 328;
            h = 250;
            setGraphWidthAndHeight(268, 150);
        }
        //Size-: 360 270  300  170  0
        if (counter == 0) {
            w = 360;
            h = 270;
            setGraphWidthAndHeight(300, 170);
        }
        //Size+: 392 290  332  190  1
        if (counter == 1) {
            w = 392;
            h = 290;
            setGraphWidthAndHeight(332, 190);
        }
        //Size+: 424 310  364  210  2
        if (counter == 2) {
            w = 424;
            h = 310;
            setGraphWidthAndHeight(364, 210);
        }
        //Size+: 456 330  396  230  3
        if (counter == 3) {
            w = 456;
            h = 330;
            setGraphWidthAndHeight(396, 230);
        }
        //Size+: 488 350  428  250  4
        if (counter == 4) {
            w = 488;
            h = 350;
            setGraphWidthAndHeight(428, 250);
        }
        //Size+: 520 370  460  270  5
        if (counter == 5) {
            w = 520;
            h = 370;
            setGraphWidthAndHeight(460, 270);
        }
        //Size+: 552 390  492  290  6
        if (counter == 6) {
            w = 552;
            h = 390;
            setGraphWidthAndHeight(492, 290);
        }
        //Size+: 584 410  524  310  7
        if (counter == 7) {
            w = 584;
            h = 410;
            setGraphWidthAndHeight(524, 310);
        }
        //Size+: 616 430  556  330  8
        if (counter == 8) {
            w = 616;
            h = 430;
            setGraphWidthAndHeight(556, 330);
        }
        //Size+: 648 450  588  350  9
        if (counter == 9) {
            w = 648;
            h = 450;
            setGraphWidthAndHeight(588, 350);
        }
        //Size+: 680 470  620  370  10
        if (counter == 10) {
            w = 680;
            h = 470;
            setGraphWidthAndHeight(620, 370);
        }
        //Size+: 712 490  652  390  11
        if (counter == 11) {
            w = 712;
            h = 490;
            setGraphWidthAndHeight(652, 390);
        }
        //Size+: 744 510  684  410  12
        if (counter == 12) {
            w = 744;
            h = 510;
            setGraphWidthAndHeight(684, 410);
        }

        p.setSize(w, h);

        int[] arr = new int[2];
        arr[0] = w;
        arr[1] = h;
        // arr[2] = fontSize;

        return arr;
    }

}
