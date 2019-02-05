package PRANAYAMA;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Ellipse;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;

public class graphComponents extends JComponent implements MouseListener, FocusListener {

    // type  = 1   Horizontal diagramma
    // type  = 2   Vertical diagramma
    // type  = 3   Circle diagramma
    int type;
    // 1,2,3  <- " inhalation : exhalation "," inhalation : hold : exhalation "," inhalation : hold : exhalation : hold "
    int index;
    boolean focus = false;
    boolean pressed = false;
    int w, h;
    // dw1 -> dynamic value from 0 to  TextField field_inhalation
    // dw2 -> dynamic value from 0 to  TextField field_exhalation
    double dw1, dw2, dw3, dw4;
    // v1 -> value from  TextField field_inhalation
    // v2 -> value from  TextField field_exhalation
    // v3 -> value for  JTextField 'field_breathhold_after_inhalation' (class:settingsBreathing.java)
    // v4 -> value from  TextField 'field_breathhold_after_exhalation' (class:settingsBreathing.java)
    double v1, v2, v3, v4;
    double max1, max2, max3, max4;
    String strCounter1, strCounter2, strCounter3, strCounter4;
    // color1 - for 'inhalation' scope
    // color2 - for 'hold after inhalation' scope
    // color3 - can be for 'exhalation' scope
    // color4 - for 'hold after exhalation' scope
    Color color1, color2, color3, color4;
    // for Counters of VERTICAL, CIRCLE DIAGRAMMA
    Color colorForeground;
    // for CIRCLE type 3
    String oneCounterForAll;
    // for CIRCLE type 3 ; change color COUNTER inside circle according to  
    // color1, color2, color3, color4
    Color colorCounter;
    // Y pos rectangle which contains counter when user do increase or decrease (see class PlusMinusAction.java)
    double yPosDelta;
    // 
    int deltaX, deltaY;
    int fontSize;
    Font font;
    boolean isPaintBackground = false;

    graphComponents(int t, int ind) {
        type = t;
        index = ind;

        if (type == 1 || type == 2) {
            colorForeground = Color.RED;
        }
        if (type == 3) {
            colorForeground = Color.WHITE;
        }

        fontSize = 20;
        // 
        yPosDelta = 30;

//        color1 = Color.ORANGE; // 'inhalation' scope
//        color2 = Color.RED;    // 'hold after inhalation' scope
//        color3 = Color.BLUE;   // 'exhalation' scope
//        color4 = Color.YELLOW; // 'hold after exhalation' scope
        color1 = Color.RED; // 'inhalation' scope
        color2 = Color.RED;    // 'hold after inhalation' scope
        color3 = Color.RED;   // 'exhalation' scope
        color4 = Color.RED; // 'hold after exhalation' scope

        colorCounter = Color.DARK_GRAY; // for change color COUNTER in CIRCLE DIAGRAMMA

        initComponentSize(type, index);
//        // horizontal
//        w = 200;
//        h = 90;

        // vertical
        // 2,1: 170, 255
        // 2,2: 170, 255
        // 2,3: 210, 250
//        w = 231;
//        h = 250;
        v1 = 5; // 93.75
        v2 = 5; // 281.25
        v3 = 5;
        v4 = 5;

        dw1 = 0;
        dw2 = 0;
        dw3 = 0;
        dw4 = 0;

        // For type 2 (vertical diagramma)
        strCounter1 = strCounter2 = strCounter3 = strCounter4 = "00";
        // just For type 3 (circle diagramma)
        oneCounterForAll = "00";

        setPreferredSize(this.getPreferredSize());
        setMinimumSize(this.getMinimumSize());
        addMouseListener(this);
        addFocusListener(this);
        this.setBounds(0, 0, 100, 50);
    }

    private void initComponentSize(int type, int index) {

        switch (type) {
            default:
                break;
            // horizontal
            case 1:
                w = 300; //150
                h = 170;
                break;
            //vertical    
            case 2:
                switch (index) {
                    default:
                        break;
                    case 1:
                        w = 128; //150
                        h = 250;
                        break;
                    case 2:
                        w = 170;
                        h = 250;
                        break;
                    case 3:
                        w = 231;//231
                        h = 250;
                        break;
                }
                break;
            // circle
            case 3:
                w = 200;
                h = 200;
                break;
        }

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));

        //Font font = new Font("Monotype Corsiva", Font.BOLD, 20); //  + 10
        graphics2d.setFont(getGraphFont());
// ======================== HORIZONTAL DIAGRAMMA ===============================
        if (type == 1) {
            switch (getIndex()) {
                default:
                    break;
                // " inhalation : exhalation " <-- selected index JCombobox 'schema_breathing'   
                case 1:
                    // common width 'inhalation' from common width of line diagramma
                    double w1 = getV1() * getIndividualShare(1, "horizontal");
                    // common height 'exhalation' from common height of line diagramma
                    double w2 = getV2() * getIndividualShare(1, "horizontal");

                    // dynamic change width for inhalation
                    double dynWidth1 = getDW1() * getIndividualShare(1, "horizontal");
                    // dynamic change height for exhalation
                    double dynWidth2 = getDW2() * getIndividualShare(1, "horizontal");
//                    System.out.println("::: " + getDW1() + "  " + getIndividualShare(1));
                    RoundRectangle2D outline1 = new RoundRectangle2D.Double(0, 0, getGraphWidth(), getGraphHeight(), 25, 25);

                    Rectangle2D inhalation1 = new Rectangle2D.Double(0, 0, w1, getGraphHeight());
                    Rectangle2D inhalationDynamic1 = new Rectangle2D.Double(0, 0, dynWidth1, getGraphHeight());

                    Rectangle2D exhalation1 = new Rectangle2D.Double(w1, 0, w2, getGraphHeight());
                    Rectangle2D exhalationDynamic1 = new Rectangle2D.Double(w1, 0, dynWidth2, getGraphHeight());

                    graphics2d.setClip(outline1);
                    graphics2d.clip(outline1);
                    // Paint INHALATION
                    graphics2d.setPaint(getToneColor(getColor1(), 80));
                    graphics2d.fill(inhalation1);
                    graphics2d.setPaint(getColor1());
                    graphics2d.fill(inhalationDynamic1);
                    // Paint EXHALATION
                    graphics2d.setPaint(getToneColor(getColor3(), 80));
                    graphics2d.fill(exhalation1);
                    graphics2d.setPaint(getColor3());
                    graphics2d.fill(exhalationDynamic1);
                    break;
                // " inhalation : hold : exhalation " <-- selected index JCombobox 'schema_breathing'
                case 2:
                    // common width 'inhalation' from common width of line diagramma
                    // JTextField 'field_inhalation' (class:settingsBreathing.java) 
                    w1 = getV1() * getIndividualShare(2, "horizontal");
                    // common width 'breathhold_after_inhalation' from common width of line diagramma
                    // JTextField 'field_breathhold_after_inhalation' (class:settingsBreathing.java) 
                    w2 = getV2() * getIndividualShare(2, "horizontal");
                    // common height 'exhalation' from common height of line diagramma
                    // JTextField 'field_after_exhalation' (class:settingsBreathing.java)
                    double w3 = getV3() * getIndividualShare(2, "horizontal");

                    // dynamic change width for inhalation
                    dynWidth1 = getDW1() * getIndividualShare(2, "horizontal");
                    // dynamic change width for 'breathhold_after_inhalation'
                    dynWidth2 = getDW2() * getIndividualShare(2, "horizontal");
                    // dynamic change height for exhalation
                    double dynWidth3 = getDW3() * getIndividualShare(2, "horizontal");

                    RoundRectangle2D outline2 = new RoundRectangle2D.Double(0, 0, getGraphWidth(), getGraphHeight(), 25, 25);

                    Rectangle2D inhalation2 = new Rectangle2D.Double(0, 0, w1, getGraphHeight());
                    Rectangle2D inhalationDynamic2 = new Rectangle2D.Double(0, 0, dynWidth1, getGraphHeight());

                    Rectangle2D holdIn2 = new Rectangle2D.Double(w1, 0, w2, getGraphHeight());
                    Rectangle2D holdInDynamic2 = new Rectangle2D.Double(w1, 0, dynWidth2, getGraphHeight());

                    Rectangle2D exhalation2 = new Rectangle2D.Double(w1 + w2, 0, w3, getGraphHeight());
                    Rectangle2D exhalationDynamic2 = new Rectangle2D.Double(w1 + w2, 0, dynWidth3, getGraphHeight());

                    graphics2d.setClip(outline2);
                    graphics2d.clip(outline2);
                    // Paint INHALATION
                    graphics2d.setPaint(getToneColor(getColor1(), 80));
                    graphics2d.fill(inhalation2);
                    graphics2d.setPaint(getColor1());
                    graphics2d.fill(inhalationDynamic2);
                    // Paint HOLD after INHALATION
                    graphics2d.setPaint(getToneColor(getColor2(), 80));
                    graphics2d.fill(holdIn2);
                    graphics2d.setPaint(getColor2());
                    graphics2d.fill(holdInDynamic2);
                    // Paint EXHALATION
                    graphics2d.setPaint(getToneColor(getColor3(), 80));
                    graphics2d.fill(exhalation2);
                    graphics2d.setPaint(getColor3());
                    graphics2d.fill(exhalationDynamic2);
                    break;
                // " inhalation : hold : exhalation : hold " <-- selected index JCombobox 'schema_breathing'
                case 3:
                    // common width 'inhalation' from common width of line diagramma
                    // JTextField 'field_inhalation' (class:settingsBreathing.java) 
                    w1 = getV1() * getIndividualShare(3, "horizontal");
                    // common width 'breathhold_after_inhalation' from common width of line diagramma
                    // JTextField 'field_breathhold_after_inhalation' (class:settingsBreathing.java) 
                    w2 = getV2() * getIndividualShare(3, "horizontal");
                    // common height 'exhalation' from common height of line diagramma
                    // JTextField 'field_exhalation' (class:settingsBreathing.java)
                    w3 = getV3() * getIndividualShare(3, "horizontal");
                    // common width 'breathhold_after_exhalation' from common width of line diagramma
                    // JTextField 'field_breathhold_after_exhalation' (class:settingsBreathing.java) 
                    double w4 = getV4() * getIndividualShare(3, "horizontal");

                    // dynamic change width for inhalation
                    dynWidth1 = getDW1() * getIndividualShare(3, "horizontal");
                    // dynamic change width for 'breathhold_after_inhalation'
                    dynWidth2 = getDW2() * getIndividualShare(3, "horizontal");
                    // dynamic change height for exhalation
                    dynWidth3 = getDW3() * getIndividualShare(3, "horizontal");
                    // dynamic change width for 'breathhold_after_exhalation'
                    double dynWidth4 = getDW4() * getIndividualShare(3, "horizontal");

                    RoundRectangle2D outline3 = new RoundRectangle2D.Double(0, 0, getGraphWidth(), getGraphHeight(), 25, 25);

                    Rectangle2D inhalation3 = new Rectangle2D.Double(0, 0, w1, getGraphHeight());
                    Rectangle2D inhalationDynamic3 = new Rectangle2D.Double(0, 0, dynWidth1, getGraphHeight());

                    Rectangle2D holdIn3 = new Rectangle2D.Double(w1, 0, w2, getGraphHeight());
                    Rectangle2D holdInDynamic3 = new Rectangle2D.Double(w1, 0, dynWidth2, getGraphHeight());

                    Rectangle2D exhalation3 = new Rectangle2D.Double(w1 + w2, 0, w3, getGraphHeight());
                    Rectangle2D exhalationDynamic3 = new Rectangle2D.Double(w1 + w2, 0, dynWidth3, getGraphHeight());

                    Rectangle2D holdEx3 = new Rectangle2D.Double(w1 + w2 + w3, 0, w4, getGraphHeight());
                    Rectangle2D holdExDynamic3 = new Rectangle2D.Double(w1 + w2 + w3, 0, dynWidth4, getGraphHeight());

                    graphics2d.setClip(outline3);
                    graphics2d.clip(outline3);
                    // Paint INHALATION
                    graphics2d.setPaint(getToneColor(getColor1(), 80));
                    graphics2d.fill(inhalation3);
                    graphics2d.setPaint(getColor1());
                    graphics2d.fill(inhalationDynamic3);
                    // Paint HOLD after INHALATION
                    graphics2d.setPaint(getToneColor(getColor2(), 80));
                    graphics2d.fill(holdIn3);
                    graphics2d.setPaint(getColor2());
                    graphics2d.fill(holdInDynamic3);
                    // Paint  EXHALATION 
                    graphics2d.setPaint(getToneColor(getColor3(), 80));
                    graphics2d.fill(exhalation3);
                    graphics2d.setPaint(getColor3());
                    graphics2d.fill(exhalationDynamic3);
                    // Paint HOLD after EXHALATION
                    graphics2d.setPaint(getToneColor(getColor4(), 80));
                    graphics2d.fill(holdEx3);
                    graphics2d.setPaint(getColor4());
                    graphics2d.fill(holdExDynamic3);
                    break;
            }
        }
// ======================== VERTICAL DIAGRAMMA =================================      
        if (type == 2) {
            switch (getIndex()) {
                default:
                    break;
// " inhalation : exhalation " <-- selected index JCombobox 'schema_breathing' 
                case 1:
                    // common width 'inhalation' from common width of line diagramma
                    double h1 = getV1() * getIndividualShare(1, "vertical");
                    // common height 'exhalation' from common height of line diagramma
                    double h2 = getV2() * getIndividualShare(1, "vertical");

                    //  System.out.println("h1: " + h1 + "  " + "h2: " + h2 + " share:" + getIndividualShare(1, "vertical"));
                    // dynamic change width for inhalation
                    double dynHeight1 = (h1 / getMax1()) * getDW1();
                    // dynamic change height for exhalation
                    double dynHeight2 = (h2 / getMax2()) * getDW2();

                    double diagrammaSpaceHeight = h1 + h2;
                    double yDyn1 = diagrammaSpaceHeight - dynHeight1 - 10;
                    double yDyn2 = diagrammaSpaceHeight - dynHeight2 - 10;
                    double y1;
                    double y2;

                    RoundRectangle2D outline1 = new RoundRectangle2D.Double(0, 0, getGraphWidth(), getGraphHeight() + 20, 10, 10);

                    double ww = (getGraphWidth() / 3) * 2 + 10;
                    double hh = getGraphHeight() - 25;//
                    double xx = (getGraphWidth() - ww) / 2;  //25
                    double yy = (getGraphHeight() - hh) / 2;  //30
                    Rectangle.Double r = new Rectangle.Double(xx, yy, ww, hh);

                    //  System.out.println(": " + getV1() / getV2());
                    if (getV1() == getV2()) {
                        y1 = yy; // 30
                        y2 = yy; // 30
                        h1 = getGraphHeight() - 25 - yy - 10; // h1 = getGraphHeight() - yy - 10;  25 - 40;
                        h2 = getGraphHeight() - 25 - yy - 10; // h2 = getGraphHeight() - yy - 10;  25 - 40;
                        dynHeight1 = (h1 / getMax1()) * getDW1(); //
                        dynHeight2 = (h2 / getMax2()) * getDW2(); //
                        yDyn1 = diagrammaSpaceHeight - dynHeight1 - 10; //
                        yDyn2 = diagrammaSpaceHeight - dynHeight2 - 10; //
                        //  System.out.println("if");
                    } // calculate suitable for good displaing 'inhalation' if v1 is much more then v2
                    else if (getV2() / getV1() < 0.225) {
                        y1 = 30; // y1 = 30;
                        h1 = getGraphHeight() - 25 - 40;
                        dynHeight1 = (h1 / getMax1()) * getDW1();
                        yDyn1 = diagrammaSpaceHeight - dynHeight1 - 10; // -5

                        y2 = diagrammaSpaceHeight - h2 - 10;
                        //  System.out.println("else if 1 : " + y1 + "  " + y2);
                    } // calculate suitable for good displaing 'exhalation' if v2 is much more then v1
                    else if (getV1() / getV2() < 0.225) {
                        y2 = 30;
                        h2 = getGraphHeight() - 25 - 40;
                        dynHeight2 = (h2 / getMax2()) * getDW2();
                        yDyn2 = diagrammaSpaceHeight - dynHeight2 - 10; // -5

                        y1 = diagrammaSpaceHeight - h1 - 10;
                        // System.out.println("else if 2 : " + y2 + "  " + y1);
                    } else {
                        y1 = diagrammaSpaceHeight - h1 - 10;
                        y2 = diagrammaSpaceHeight - h2 - 10;

                        //  System.out.println("else: " + y1 + "  " + y2);
                    }

                    Rectangle2D inhalation1
                            = new Rectangle2D.Double(xx, y1, getGraphWidth() / 3, h1); // x:25  y:y1
                    Rectangle2D inhalationDynamic1
                            = new Rectangle2D.Double(xx, yDyn1, getGraphWidth() / 3, dynHeight1); // x:25 y:yDyn1

                    Rectangle2D exhalation1
                            = new Rectangle2D.Double(getGraphWidth() / 3 + 10 + xx, y2, getGraphWidth() / 3, h2);
                    Rectangle2D exhalationDynamic1
                            = new Rectangle2D.Double(getGraphWidth() / 3 + 10 + xx, yDyn2, getGraphWidth() / 3, dynHeight2);

                    // System.out.println("W: " + getGraphWidth()/3);
//                    graphics2d.setClip(outline1);
//                    graphics2d.clip(outline1);
                    Area area0 = new Area(outline1);
                    Area area1 = new Area(inhalation1);
                    Area area2 = new Area(inhalationDynamic1);
                    Area area3 = new Area(exhalation1);
                    Area area4 = new Area(exhalationDynamic1);

                    if (getBackgroundGraphComp() == true) {
                        // paint 'Cover'
                        graphics2d.setPaint(new Color(175, 242, 250));
                        graphics2d.fill(area0);
                    }

//                    graphics2d.setPaint(Color.PINK);
//                    graphics2d.fill(r);
                    // paint 'Inhalation'
                    graphics2d.setPaint(Color.LIGHT_GRAY);
                    graphics2d.fill(area1);
                    graphics2d.setPaint(getColor1());
                    graphics2d.fill(area2);
                    // paint 'Exhalation'
                    graphics2d.setPaint(Color.LIGHT_GRAY);
                    graphics2d.fill(area3);
                    graphics2d.setPaint(getColor3());
                    graphics2d.fill(area4);

                    FontRenderContext frc = new FontRenderContext(null, true, true);
                    Rectangle2D r2D = getGraphFont().getStringBounds(getCounter1(), frc);
                    //dynamic rectangle height which contains counter
                    double rectCounterHeight = r2D.getHeight() - 2;
                    // Y pos rectangle which contains counter
                    double yPosCounters = getGraphHeight() - getYDelta();

                    Rectangle2D rectCounter1
                            = new Rectangle2D.Double(xx, yPosCounters, getGraphWidth() / 3 - 0, rectCounterHeight);
                    RoundRectangle2D roundRectCounter1
                            = new RoundRectangle2D.Double(xx, yPosCounters, getGraphWidth() / 3 - 0, rectCounterHeight, 20, 20);

                    graphics2d.setPaint(Color.BLUE);
                    graphics2d.fill(roundRectCounter1);
                    graphics2d.setPaint(getColorForeground());
                    utils.centerString(graphics2d, (Rectangle2D.Double) rectCounter1, getCounter1(), font);

                    Rectangle2D rectCounter2
                            = new Rectangle2D.Double(getGraphWidth() / 3 + 10 + xx, yPosCounters, getGraphWidth() / 3 - 0, rectCounterHeight);
                    RoundRectangle2D roundRectCounter2
                            = new RoundRectangle2D.Double(getGraphWidth() / 3 + 10 + xx, yPosCounters, getGraphWidth() / 3 - 0, rectCounterHeight, 20, 20);

                    graphics2d.setPaint(Color.BLUE);
                    graphics2d.fill(roundRectCounter2);
                    graphics2d.setPaint(getColorForeground());
                    utils.centerString(graphics2d, (Rectangle2D.Double) rectCounter2, getCounter2(), font);

                    break;
// " inhalation : hold : exhalation " <-- selected index JCombobox 'schema_breathing'
                case 2:
                    // common height 'inhalation' from common height of line diagramma
                    // JTextField 'field_inhalation' (class:settingsBreathing.java) 
                    h1 = getV1() * getIndividualShare(2, "vertical");
                    // common height 'breathhold_after_inhalation' from common height of line diagramma
                    // JTextField 'field_breathhold_after_inhalation' (class:settingsBreathing.java) 
                    h2 = getV2() * getIndividualShare(2, "vertical");
                    // common height 'exhalation' from common height of line diagramma
                    // JTextField 'field_after_exhalation' (class:settingsBreathing.java)
                    double h3 = getV3() * getIndividualShare(2, "vertical");

                    // System.out.println("h1: " + h1 + "  " + "h2: " + h2 + " share:" + getIndividualShare(1, "vertical"));
                    // dynamic change height for inhalation
                    dynHeight1 = (h1 / getMax1()) * getDW1();
                    // dynamic change height for breathhold_after_inhalation
                    dynHeight2 = (h2 / getMax2()) * getDW2();
                    // dynamic change height for breathhold_after_inhalation
                    double dynHeight3 = (h3 / getMax3()) * getDW3();

                    diagrammaSpaceHeight = h1 + h2 + h3;
                    yDyn1 = diagrammaSpaceHeight - dynHeight1 - 10;
                    yDyn2 = diagrammaSpaceHeight - dynHeight2 - 10;
                    double yDyn3 = diagrammaSpaceHeight - dynHeight3 - 10;
                    double y3 = 0;

                    RoundRectangle2D outline2 = new RoundRectangle2D.Double(0, 0, getGraphWidth(), getGraphHeight() + 20, 10, 10);

                    // System.out.println("W: " + getGraphWidth());
                    ww = (getGraphWidth() / 4) * 3 + 47 - 25;
                    hh = getGraphHeight() - 25;//
                    xx = (getGraphWidth() - ww) / 2;  //25
                    yy = (getGraphHeight() - hh) / 2;  //30
                    r = new Rectangle.Double(xx, yy, ww, hh);

                    //  System.out.println(": " + getV1() / getV2());
                    // calculate suitable height for good displaing inhalation, hold after inhalation, exhalation
                    if (getV1() == getV2() && getV1() == getV3()) {
                        y1 = yy; // 30
                        y2 = yy; // 30
                        y3 = yy; // 30
                        h1 = getGraphHeight() - 25 - yy - 10; // h1 = getGraphHeight() - yy - 10;  25 - 40;
                        h2 = getGraphHeight() - 25 - yy - 10; // h2 = getGraphHeight() - yy - 10;  25 - 40;
                        h3 = getGraphHeight() - 25 - yy - 10;
                        dynHeight1 = (h1 / getMax1()) * getDW1(); //
                        dynHeight2 = (h2 / getMax2()) * getDW2(); //
                        dynHeight3 = (h3 / getMax3()) * getDW3(); //
                        yDyn1 = diagrammaSpaceHeight - dynHeight1 - 10; //
                        yDyn2 = diagrammaSpaceHeight - dynHeight2 - 10; //
                        yDyn3 = diagrammaSpaceHeight - dynHeight3 - 10; //
                        // System.out.println("if");
                    } // calculate suitable height for good displaing 'inhalation' if v1 is much more then v2 and v3
                    else if ((getV2() / getV1() < 0.225) && (getV3() / getV1() < 0.225)) {
                        y1 = 30; // y1 = 30;
                        h1 = getGraphHeight() - 25 - 40;
                        dynHeight1 = (h1 / getMax1()) * getDW1();
                        yDyn1 = diagrammaSpaceHeight - dynHeight1 - 10; // -5

                        y2 = diagrammaSpaceHeight - h2 - 10;
                        y3 = diagrammaSpaceHeight - h3 - 10;
                        //  System.out.println("else if 1 : " + y1 + "  " + y2);
                    } // calculate suitable height for good displaing 'exhalation' if v2 is much more then v1 and v3
                    else if ((getV1() / getV2() < 0.225) && (getV3() / getV2() < 0.225)) {
                        y2 = 30;
                        h2 = getGraphHeight() - 25 - 40;
                        dynHeight2 = (h2 / getMax2()) * getDW2();
                        yDyn2 = diagrammaSpaceHeight - dynHeight2 - 10; // -5

                        y1 = diagrammaSpaceHeight - h1 - 10;
                        y3 = diagrammaSpaceHeight - h3 - 10;
                        // System.out.println("else if 2 : " + y2 + "  " + y1);
                    } // calculate suitable height for good displaing 'exhalation' if v3 is much more then v1 and v2
                    else if ((getV1() / getV3() < 0.225) && (getV2() / getV3() < 0.225)) {
                        y3 = 30;
                        h3 = getGraphHeight() - 25 - 40;
                        dynHeight3 = (h3 / getMax3()) * getDW3();
                        yDyn3 = diagrammaSpaceHeight - dynHeight3 - 10; // -5

                        y1 = diagrammaSpaceHeight - h1 - 10;
                        y2 = diagrammaSpaceHeight - h2 - 10;
                        //  System.out.println("else if 3 : " + y2 + "  " + y1);
                    } else {
                        y1 = diagrammaSpaceHeight - h1 - 10;
                        y2 = diagrammaSpaceHeight - h2 - 10;
                        y3 = diagrammaSpaceHeight - h3 - 10;

                        //  System.out.println("else: " + y1 + "  " + y2);
                    }
                    // INHALATION
                    Rectangle2D inhalation2 = new Rectangle2D.Double(xx, y1, getGraphWidth() / 4, h1); // x:25  y:y1
                    Rectangle2D inhalationDynamic2
                            = new Rectangle2D.Double(xx, yDyn1, getGraphWidth() / 4, dynHeight1); // x:25 y:yDyn1
                    // HOLD AFTER INHALATION
                    Rectangle2D holdInhalation2
                            = new Rectangle2D.Double(getGraphWidth() / 4 + 10 + xx, y2, getGraphWidth() / 4, h2);
                    Rectangle2D holdInhalationDynamic2
                            = new Rectangle2D.Double(getGraphWidth() / 4 + 10 + xx, yDyn2, getGraphWidth() / 4, dynHeight2);
                    // EXHALATION
                    Rectangle2D exhalation2
                            = new Rectangle2D.Double((getGraphWidth() / 4 + 10) * 2 + xx, y3, getGraphWidth() / 4, h3);
                    Rectangle2D exhalationDynamic2
                            = new Rectangle2D.Double((getGraphWidth() / 4 + 10) * 2 + xx, yDyn3, getGraphWidth() / 4, dynHeight3);

                    // System.out.println("WIDTH : " + getGraphWidth() / 4);
                    area0 = new Area(outline2);
                    area1 = new Area(inhalation2);
                    area2 = new Area(inhalationDynamic2);
                    area3 = new Area(holdInhalation2);
                    area4 = new Area(holdInhalationDynamic2);
                    Area area5 = new Area(exhalation2);
                    Area area6 = new Area(exhalationDynamic2);

                    if (getBackgroundGraphComp() == true) {
                        // paint 'outline'
                        graphics2d.setPaint(new Color(175, 242, 250));
                        graphics2d.fill(area0);
                    }
                    // paint 'Inhalation'
                    graphics2d.setPaint(Color.LIGHT_GRAY);
                    graphics2d.fill(area1);
                    graphics2d.setPaint(getColor1());
                    graphics2d.fill(area2);
                    // paint 'Hold after inhalation'
                    graphics2d.setPaint(Color.LIGHT_GRAY);
                    graphics2d.fill(area3);
                    graphics2d.setPaint(getColor2());
                    graphics2d.fill(area4);
                    // paint 'Exhalation'
                    graphics2d.setPaint(Color.LIGHT_GRAY);
                    graphics2d.fill(area5);
                    graphics2d.setPaint(getColor3());
                    graphics2d.fill(area6);

                    // Y pos rectangle which contains counter
                    yPosCounters = getGraphHeight() - getYDelta();

                    // paint Counter1
                    paintCounter(graphics2d, getGraphFont(), getCounter1(), Color.BLUE, getColorForeground(),
                            xx, yPosCounters, getGraphWidth() / 4 - 0);
                    // paint Counter2
                    paintCounter(graphics2d, getGraphFont(), getCounter2(), Color.BLUE, getColorForeground(),
                            getGraphWidth() / 4 + 10 + xx, yPosCounters, getGraphWidth() / 4 - 0);
                    // paint Counter3
                    paintCounter(graphics2d, getGraphFont(), getCounter3(), Color.BLUE, getColorForeground(),
                            (getGraphWidth() / 4 + 10) * 2 + xx, yPosCounters, getGraphWidth() / 4 - 0);
                    break;
// " inhalation : hold : exhalation : hold " <-- selected index JCombobox 'schema_breathing'
                case 3:
                    // common height 'inhalation' from common height of line diagramma
                    // JTextField 'field_inhalation' (class:settingsBreathing.java) 
                    h1 = getV1() * getIndividualShare(3, "vertical");
                    // common height 'breathhold_after_inhalation' from common height of line diagramma
                    // JTextField 'field_breathhold_after_inhalation' (class:settingsBreathing.java) 
                    h2 = getV2() * getIndividualShare(3, "vertical");
                    // common height 'exhalation' from common height of line diagramma
                    // JTextField 'field_after_exhalation' (class:settingsBreathing.java)
                    h3 = getV3() * getIndividualShare(3, "vertical");
                    // common height 'breathhold_after_exhalation' from common height of line diagramma
                    // JTextField 'field_breathhold_after_exhalation' (class:settingsBreathing.java) 
                    double h4 = getV4() * getIndividualShare(3, "vertical");

                    //  System.out.println("h1: " + h1 + "  " + "h2: " + h2 + " share:" + getIndividualShare(1, "vertical"));
                    //  System.out.println("h4:" + h4);
                    // dynamic change height for inhalation
                    dynHeight1 = (h1 / getMax1()) * getDW1();
                    // dynamic change height for breathhold_after_inhalation
                    dynHeight2 = (h2 / getMax2()) * getDW2();
                    // dynamic change height for breathhold_after_inhalation
                    dynHeight3 = (h3 / getMax3()) * getDW3();
                    // dynamic change height for breathhold_after_exhalation
                    double dynHeight4 = (h4 / getMax4()) * getDW4();

                    diagrammaSpaceHeight = h1 + h2 + h3 + h4;
                    yDyn1 = diagrammaSpaceHeight - dynHeight1 - 10;
                    yDyn2 = diagrammaSpaceHeight - dynHeight2 - 10;
                    yDyn3 = diagrammaSpaceHeight - dynHeight3 - 10;
                    double yDyn4 = diagrammaSpaceHeight - dynHeight4 - 10;
                    y3 = 0;
                    double y4 = 0;

                    RoundRectangle2D outline3 = new RoundRectangle2D.Double(0, 0, getGraphWidth() - 6, getGraphHeight() + 20, 10, 10);

                    // System.out.println("W: " + getGraphWidth());
                    ww = (getGraphWidth() / 5.5) * 4 + 38; //(getGraphWidth() / 5.5) * 4 + 47 - 25
                    hh = getGraphHeight() - 25;//
                    xx = (getGraphWidth() - ww) / 2;  //25
                    yy = (getGraphHeight() - hh) / 2;  //30
                    r = new Rectangle.Double(xx, yy, ww, hh);

                    // System.out.println(": " + getV1() / getV2());
                    // calculate suitable height for good displaing inhalation, 
                    // hold after inhalation, exhalation, hold after exhalation
                    if (getV1() == getV2() && getV1() == getV3() && getV1() == getV4()) {
                        y1 = yy; // 30
                        y2 = yy; // 30
                        y3 = yy; // 30
                        y4 = yy; // 30
                        h1 = getGraphHeight() - 25 - yy - 10; // h1 = getGraphHeight() - yy - 10;  25 - 40;
                        h2 = getGraphHeight() - 25 - yy - 10; // h2 = getGraphHeight() - yy - 10;  25 - 40;
                        h3 = getGraphHeight() - 25 - yy - 10;
                        h4 = getGraphHeight() - 25 - yy - 10;
                        dynHeight1 = (h1 / getMax1()) * getDW1(); //
                        dynHeight2 = (h2 / getMax2()) * getDW2(); //
                        dynHeight3 = (h3 / getMax3()) * getDW3(); //
                        dynHeight4 = (h4 / getMax4()) * getDW4(); //
                        yDyn1 = diagrammaSpaceHeight - dynHeight1 - 10; //
                        yDyn2 = diagrammaSpaceHeight - dynHeight2 - 10; //
                        yDyn3 = diagrammaSpaceHeight - dynHeight3 - 10; //
                        yDyn4 = diagrammaSpaceHeight - dynHeight4 - 10; //
                        // System.out.println("if");
                    } // calculate suitable height for good displaing 'inhalation'
                    // if v1 is much more then v2 and v3 and v4
                    else if ((getV2() / getV1() < 0.225) && (getV3() / getV1() < 0.225) && (getV4() / getV1() < 0.225)) {
                        y1 = 30; // y1 = 30;
                        h1 = getGraphHeight() - 25 - 40;
                        dynHeight1 = (h1 / getMax1()) * getDW1();
                        yDyn1 = diagrammaSpaceHeight - dynHeight1 - 10; // -5

                        y2 = diagrammaSpaceHeight - h2 - 10;
                        y3 = diagrammaSpaceHeight - h3 - 10;
                        y4 = diagrammaSpaceHeight - h4 - 10;
                        // System.out.println("else if 1 : " + y1 + "  " + y2);
                    } // calculate suitable height for good displaing 'exhalation'
                    // if v2 is much more then v1 and v3 and v4
                    else if ((getV1() / getV2() < 0.225) && (getV3() / getV2() < 0.225) && (getV4() / getV2() < 0.225)) {
                        y2 = 30;
                        h2 = getGraphHeight() - 25 - 40;
                        dynHeight2 = (h2 / getMax2()) * getDW2();
                        yDyn2 = diagrammaSpaceHeight - dynHeight2 - 10; // -5

                        y1 = diagrammaSpaceHeight - h1 - 10;
                        y3 = diagrammaSpaceHeight - h3 - 10;
                        y4 = diagrammaSpaceHeight - h4 - 10;
                        //  System.out.println("else if 2 : " + y2 + "  " + y1);
                    } // calculate suitable height for good displaing 'exhalation'
                    // if v3 is much more then v1 and v2 and v4
                    else if ((getV1() / getV3() < 0.225) && (getV2() / getV3() < 0.225) && (getV4() / getV3() < 0.225)) {
                        y3 = 30;
                        h3 = getGraphHeight() - 25 - 40;
                        dynHeight3 = (h3 / getMax3()) * getDW3();
                        yDyn3 = diagrammaSpaceHeight - dynHeight3 - 10; // -5

                        y1 = diagrammaSpaceHeight - h1 - 10;
                        y2 = diagrammaSpaceHeight - h2 - 10;
                        y4 = diagrammaSpaceHeight - h4 - 10;
                        //  System.out.println("else if 3 : " + y2 + "  " + y1);
                    } // calculate suitable height for good displaing 'exhalation'
                    // if v4 is much more then v1 and v2 and v3
                    else if ((getV1() / getV4() < 0.225) && (getV2() / getV4() < 0.225) && (getV3() / getV4() < 0.225)) {
                        y4 = 30;
                        h4 = getGraphHeight() - 25 - 40;
                        dynHeight4 = (h4 / getMax4()) * getDW4();
                        yDyn4 = diagrammaSpaceHeight - dynHeight4 - 10; // -5

                        y1 = diagrammaSpaceHeight - h1 - 10;
                        y2 = diagrammaSpaceHeight - h2 - 10;
                        y3 = diagrammaSpaceHeight - h3 - 10;
                        // System.out.println("else if 4 : " + y2 + "  " + y1);
                    } else {
                        y1 = diagrammaSpaceHeight - h1 - 10;
                        y2 = diagrammaSpaceHeight - h2 - 10;
                        y3 = diagrammaSpaceHeight - h3 - 10;
                        y4 = diagrammaSpaceHeight - h4 - 10;

                        // System.out.println("else: " + y1 + "  " + y2);
                    }
                    // INHALATION
                    Rectangle2D inhalation3 = new Rectangle2D.Double(xx, y1, getGraphWidth() / 5.5, h1); // x:25  y:y1
                    Rectangle2D inhalationDynamic3
                            = new Rectangle2D.Double(xx, yDyn1, getGraphWidth() / 5.5, dynHeight1); // x:25 y:yDyn1
                    // HOLD AFTER INHALATION
                    Rectangle2D holdInhalation3
                            = new Rectangle2D.Double(getGraphWidth() / 5.5 + 10 + xx, y2, getGraphWidth() / 5.5, h2);
                    Rectangle2D holdInhalationDynamic3
                            = new Rectangle2D.Double(getGraphWidth() / 5.5 + 10 + xx, yDyn2, getGraphWidth() / 5.5, dynHeight2);
                    // EXHALATION
                    Rectangle2D exhalation3
                            = new Rectangle2D.Double((getGraphWidth() / 5.5 + 10) * 2 + xx, y3, getGraphWidth() / 5.5, h3);
                    Rectangle2D exhalationDynamic3
                            = new Rectangle2D.Double((getGraphWidth() / 5.5 + 10) * 2 + xx, yDyn3, getGraphWidth() / 5.5, dynHeight3);
                    // HOLD AFTER EXHALATION
                    Rectangle2D holdExhalation3
                            = new Rectangle2D.Double((getGraphWidth() / 5.5 + 10) * 3 + xx, y4, getGraphWidth() / 5.5, h4);
                    Rectangle2D holdExhalationDynamic3
                            = new Rectangle2D.Double((getGraphWidth() / 5.5 + 10) * 3 + xx, yDyn4, getGraphWidth() / 5.5, dynHeight4);

                    //  System.out.println("WIDTH : " + getGraphWidth() / 5.5);
                    area0 = new Area(outline3);
                    area1 = new Area(inhalation3);
                    area2 = new Area(inhalationDynamic3);
                    area3 = new Area(holdInhalation3);
                    area4 = new Area(holdInhalationDynamic3);
                    area5 = new Area(exhalation3);
                    area6 = new Area(exhalationDynamic3);
                    Area area7 = new Area(holdExhalation3);
                    Area area8 = new Area(holdExhalationDynamic3);

                    if (getBackgroundGraphComp() == true) {
                        // paint 'outline'
                        graphics2d.setPaint(new Color(175, 242, 250));
                        graphics2d.fill(area0);
                    }
                    // paint 'Inhalation'
                    graphics2d.setPaint(Color.LIGHT_GRAY);
                    graphics2d.fill(area1);
                    graphics2d.setPaint(getColor1());
                    graphics2d.fill(area2);
                    // paint 'Hold after inhalation'
                    graphics2d.setPaint(Color.LIGHT_GRAY);
                    graphics2d.fill(area3);
                    graphics2d.setPaint(getColor2());
                    graphics2d.fill(area4);
                    // paint 'Exhalation'
                    graphics2d.setPaint(Color.LIGHT_GRAY);
                    graphics2d.fill(area5);
                    graphics2d.setPaint(getColor3());
                    graphics2d.fill(area6);
                    // paint 'Hold after Exhalation'
                    graphics2d.setPaint(Color.LIGHT_GRAY);
                    graphics2d.fill(area7);
                    graphics2d.setPaint(getColor4());
                    graphics2d.fill(area8);

                    // Y pos rectangle which contains counter
                    yPosCounters = getGraphHeight() - getYDelta();

                    // paint Counter1
                    paintCounter(graphics2d, getGraphFont(), getCounter1(), Color.BLUE, getColorForeground(),
                            xx, yPosCounters, getGraphWidth() / 5.5 - 0);
                    // paint Counter2
                    paintCounter(graphics2d, getGraphFont(), getCounter2(), Color.BLUE, getColorForeground(),
                            getGraphWidth() / 5.5 + 10 + xx, yPosCounters, getGraphWidth() / 5.5 - 0);
                    // paint Counter3
                    paintCounter(graphics2d, getGraphFont(), getCounter3(), Color.BLUE, getColorForeground(),
                            (getGraphWidth() / 5.5 + 10) * 2 + xx, yPosCounters, getGraphWidth() / 5.5 - 0);
                    // paint Counter4
                    paintCounter(graphics2d, getGraphFont(), getCounter4(), Color.BLUE, getColorForeground(),
                            (getGraphWidth() / 5.5 + 10) * 3 + xx, yPosCounters, getGraphWidth() / 5.5 - 0);
                    break;
            }
        }
// ======================= CIRCLE DIAGRAMMA ====================================
        if (type == 3) {
            graphics2d.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            switch (getIndex()) {
// " inhalation : exhalation " <-- selected index JCombobox 'schema_breathing'
                case 1:
                    // common part 'inhalation' from common part of circle diagramma
                    // JTextField 'field_inhalation' (class:settingsBreathing.java) 
                    double p1 = getV1() * getIndividualShare(1, "circle");
                    // common part 'exhalation' from common part of circle diagramma
                    // JTextField 'exhalation' (class:settingsBreathing.java) 
                    double p2 = getV2() * getIndividualShare(1, "circle");

                    // dynamic change part of circle for inhalation
                    double dynP1 = getDW1() * getIndividualShare(1, "circle");
                    // dynamic change part of circle for exhalation
                    double dynP2 = getDW2() * getIndividualShare(1, "circle");

                    // width, height,X,Y - for rectangle r
                    double ww = (getGraphWidth() / 1.1);
                    double hh = getGraphHeight() / 1.1;//
                    double xx = (getGraphWidth() - ww) / 2;  //25
                    double yy = (getGraphHeight() - hh) / 2;  //30
                    Rectangle.Double r = new Rectangle.Double(xx, yy, ww, hh);

                    // width, height,X,Y - for rectangle  rectCounter which contains COUNTER
                    double wwO = (getGraphWidth() / 4.6);
                    double hhO = getGraphHeight() / 4.6;//
                    double xxO = (getGraphWidth() - wwO) / 2;  //25
                    double yyO = (getGraphHeight() - hhO) / 2;  //30
                    Rectangle.Double rectCounter = new Rectangle.Double(xxO, yyO, wwO, hhO);

                    // width, height,X,Y - for rectangle rectCounterNumbers
                    double wwN = (getGraphWidth() / 4.8);
                    double hhN = getGraphHeight() / 4.8;//
                    double xxN = (getGraphWidth() - ww) / 2;  //25
                    double yyN = (getGraphHeight() - hh) / 2;  //30
                    Rectangle.Double rectCounterNumbers = new Rectangle.Double(xx, yy, ww, hh);

                    RoundRectangle2D outline = new RoundRectangle2D.Double(0, 0, getGraphWidth(), getGraphHeight(), 10, 10);

                    Ellipse2D circleOutline = new Ellipse2D.Double(xxO, yyO, wwO, hhO);
                    Ellipse2D circle = new Ellipse2D.Double(xx, yy, ww, hh);
                    Ellipse2D circleCounter = new Ellipse2D.Double(xxO, yyO, wwO, hhO);

                    Arc2D.Double arcInhalation = new Arc2D.Double(r, 0, p1, Arc2D.PIE);
                    Arc2D.Double arcInhalationDyn = new Arc2D.Double(r, 0, dynP1, Arc2D.PIE);

                    Arc2D.Double arcExhalation = new Arc2D.Double(r, p1, p2, Arc2D.PIE);
                    Arc2D.Double arcExhalationDyn = new Arc2D.Double(r, p1, dynP2, Arc2D.PIE);

                    Area area0 = new Area(outline);
                    Area area1 = new Area(circle);
                    Area area2 = new Area(arcInhalation);
                    Area area3 = new Area(arcInhalationDyn);
                    Area area4 = new Area(arcExhalation);
                    Area area5 = new Area(arcExhalationDyn);

                    if (getBackgroundGraphComp() == true) {
                        // paint 'outline'
                        graphics2d.setPaint(new Color(175, 242, 250));
                        graphics2d.fill(area0);
                    }
                    // paint 'circle'
                    graphics2d.setPaint(Color.BLACK);
                    graphics2d.fill(area1);
                    graphics2d.setPaint(Color.BLACK);
                    graphics2d.fill(circleOutline);
                    // paint 'Inhalation'
                    graphics2d.setPaint(getToneColor(getColor1(), 80));
                    graphics2d.fill(area2);
                    graphics2d.setPaint(getColor1());
                    graphics2d.fill(area3);
                    // paint 'Exhalation'
                    graphics2d.setPaint(getToneColor(getColor3(), 80));
                    graphics2d.fill(area4);
                    graphics2d.setPaint(getColor3());
                    graphics2d.fill(area5);
                    // draw outline 'Inhalation' and 'Exhalation' 
                    graphics2d.setPaint(Color.BLACK);
                    graphics2d.draw(area2);
                    graphics2d.setPaint(Color.BLACK);
                    graphics2d.draw(area4);
                    // paint circle COUNTER
                    graphics2d.setPaint(getColorCounter());
                    graphics2d.fill(circleCounter);
                    graphics2d.setPaint(Color.DARK_GRAY);
                    graphics2d.draw(circleCounter);
                    // paint string COUNTER
                    graphics2d.setPaint(getColorForeground());
                    utils.centerString(graphics2d, rectCounterNumbers, getOneCounterForAll(), getGraphFont());

                    System.out.println("getGraphFont: " + getGraphFont());
                    break;
// " inhalation : hold : exhalation " <-- selected index JCombobox 'schema_breathing'
                case 2:
                    // common part 'inhalation' from common part of circle diagramma
                    // JTextField 'field_inhalation' (class:settingsBreathing.java) 
                    p1 = getV1() * getIndividualShare(2, "circle");
                    // common part 'field_breathhold_after_inhalation' from common part of circle diagramma
                    // JTextField 'field_breathhold_after_inhalation' (class:settingsBreathing.java) 
                    p2 = getV2() * getIndividualShare(2, "circle");
                    // common part 'exhalation' from common part of circle diagramma
                    // JTextField 'exhalation' (class:settingsBreathing.java) 
                    double p3 = getV3() * getIndividualShare(2, "circle");

                    // dynamic change part of circle for inhalation
                    dynP1 = getDW1() * getIndividualShare(2, "circle");
                    // dynamic change part of circle for breathhold_after_inhalation
                    dynP2 = getDW2() * getIndividualShare(2, "circle");
                    // dynamic change part of circle for exhalation
                    double dynP3 = getDW3() * getIndividualShare(2, "circle");

                    // width, height,X,Y - for rectangle r
                    ww = (getGraphWidth() / 1.1);
                    hh = getGraphHeight() / 1.1;//
                    xx = (getGraphWidth() - ww) / 2;  //25
                    yy = (getGraphHeight() - hh) / 2;  //30
                    r = new Rectangle.Double(xx, yy, ww, hh);

                    // width, height,X,Y - for rectangle  rectCounter which contains COUNTER
                    wwO = (getGraphWidth() / 4.6);
                    hhO = getGraphHeight() / 4.6;//
                    xxO = (getGraphWidth() - wwO) / 2;  //25
                    yyO = (getGraphHeight() - hhO) / 2;  //30
                    rectCounter = new Rectangle.Double(xxO, yyO, wwO, hhO);

                    // width, height,X,Y - for rectangle rectCounterNumbers
                    wwN = (getGraphWidth() / 4.8);
                    hhN = getGraphHeight() / 4.8;//
                    xxN = (getGraphWidth() - ww) / 2;  //25
                    yyN = (getGraphHeight() - hh) / 2;  //30
                    rectCounterNumbers = new Rectangle.Double(xx, yy, ww, hh);

                    outline = new RoundRectangle2D.Double(0, 0, getGraphWidth(), getGraphHeight(), 10, 10);

                    circleOutline = new Ellipse2D.Double(xxO, yyO, wwO, hhO);
                    circle = new Ellipse2D.Double(xx, yy, ww, hh);
                    circleCounter = new Ellipse2D.Double(xxO, yyO, wwO, hhO);

                    arcInhalation = new Arc2D.Double(r, 0, p1, Arc2D.PIE);
                    arcInhalationDyn = new Arc2D.Double(r, 0, dynP1, Arc2D.PIE);

                    Arc2D.Double arcHoldAfterInhalation = new Arc2D.Double(r, p1, p2, Arc2D.PIE);
                    Arc2D.Double arcHoldAfterInhalationDyn = new Arc2D.Double(r, p1, dynP2, Arc2D.PIE);

                    arcExhalation = new Arc2D.Double(r, p1 + p2, p3, Arc2D.PIE);
                    arcExhalationDyn = new Arc2D.Double(r, p1 + p2, dynP3, Arc2D.PIE);

                    area0 = new Area(outline);
                    area1 = new Area(circle);
                    area2 = new Area(arcInhalation);
                    area3 = new Area(arcInhalationDyn);
                    area4 = new Area(arcHoldAfterInhalation);
                    area5 = new Area(arcHoldAfterInhalationDyn);
                    Area area6 = new Area(arcExhalation);
                    Area area7 = new Area(arcExhalationDyn);

                    if (getBackgroundGraphComp() == true) {
                        // paint 'outline'
                        graphics2d.setPaint(new Color(175, 242, 250));
                        graphics2d.fill(area0);
                    }
                    // paint 'circle'
                    graphics2d.setPaint(Color.BLACK);
                    graphics2d.fill(area1);
                    graphics2d.setPaint(Color.BLACK);
                    graphics2d.fill(circleOutline);
                    // paint 'Inhalation'
                    graphics2d.setPaint(getToneColor(getColor1(), 80));
                    graphics2d.fill(area2);
                    graphics2d.setPaint(getColor1());
                    graphics2d.fill(area3);
                    // paint 'Hold after inhalation'
                    graphics2d.setPaint(getToneColor(getColor2(), 80));
                    graphics2d.fill(area4);
                    graphics2d.setPaint(getColor2());
                    graphics2d.fill(area5);
                    // paint 'Exhalation'
                    graphics2d.setPaint(getToneColor(getColor3(), 80));
                    graphics2d.fill(area6);
                    graphics2d.setPaint(getColor3());
                    graphics2d.fill(area7);
                    // draw outline 'Inhalation','Hold after inhalation' and 'Exhalation' 
                    graphics2d.setPaint(Color.BLACK);
                    graphics2d.draw(area2);
                    graphics2d.draw(area4);
                    graphics2d.draw(area6);
                    // paint circle COUNTER
                    graphics2d.setPaint(getColorCounter());
                    graphics2d.fill(circleCounter);
                    graphics2d.setPaint(Color.DARK_GRAY);
                    graphics2d.draw(circleCounter);
                    // paint string COUNTER
                    graphics2d.setPaint(getColorForeground());
                    utils.centerString(graphics2d, rectCounterNumbers, getOneCounterForAll(), getGraphFont());

                    // System.out.println("getOneCounterForAll: " + getOneCounterForAll());
                    break;
// " inhalation : hold : exhalation : hold " <-- selected index JCombobox 'schema_breathing'
                case 3:
                    // common part 'inhalation' from common part of circle diagramma
                    // JTextField 'field_inhalation' (class:settingsBreathing.java) 
                    p1 = getV1() * getIndividualShare(3, "circle");
                    // common part 'field_breathhold_after_inhalation' from common part of circle diagramma
                    // JTextField 'field_breathhold_after_inhalation' (class:settingsBreathing.java) 
                    p2 = getV2() * getIndividualShare(3, "circle");
                    // common part 'exhalation' from common part of circle diagramma
                    // JTextField 'exhalation' (class:settingsBreathing.java) 
                    p3 = getV3() * getIndividualShare(3, "circle");
                    // common part 'field_breathhold_after_exhalation' from common part of circle diagramma
                    // JTextField 'field_breathhold_after_exhalation' (class:settingsBreathing.java) 
                    double p4 = getV4() * getIndividualShare(3, "circle");

                    // dynamic change part of circle for inhalation
                    dynP1 = getDW1() * getIndividualShare(3, "circle");
                    // dynamic change part of circle for breathhold_after_inhalation
                    dynP2 = getDW2() * getIndividualShare(3, "circle");
                    // dynamic change part of circle for exhalation
                    dynP3 = getDW3() * getIndividualShare(3, "circle");
                    // dynamic change part of circle for exhalation
                    double dynP4 = getDW4() * getIndividualShare(3, "circle");

                    // width, height,X,Y - for rectangle r
                    ww = (getGraphWidth() / 1.1);
                    hh = getGraphHeight() / 1.1;//
                    xx = (getGraphWidth() - ww) / 2;  //25
                    yy = (getGraphHeight() - hh) / 2;  //30
                    r = new Rectangle.Double(xx, yy, ww, hh);

                    // width, height,X,Y - for rectangle  rectCounter which contains COUNTER
                    wwO = (getGraphWidth() / 4.6);
                    hhO = getGraphHeight() / 4.6;//
                    xxO = (getGraphWidth() - wwO) / 2;  //25
                    yyO = (getGraphHeight() - hhO) / 2;  //30
                    rectCounter = new Rectangle.Double(xxO, yyO, wwO, hhO);

                    // width, height,X,Y - for rectangle rectCounterNumbers
                    wwN = (getGraphWidth() / 4.8);
                    hhN = getGraphHeight() / 4.8;//
                    xxN = (getGraphWidth() - ww) / 2;  //25
                    yyN = (getGraphHeight() - hh) / 2;  //30
                    rectCounterNumbers = new Rectangle.Double(xx, yy, ww, hh);

                    outline = new RoundRectangle2D.Double(0, 0, getGraphWidth(), getGraphHeight(), 10, 10);

                    circleOutline = new Ellipse2D.Double(xxO, yyO, wwO, hhO);
                    circle = new Ellipse2D.Double(xx, yy, ww, hh);
                    circleCounter = new Ellipse2D.Double(xxO, yyO, wwO, hhO);
                    // INHALATION scope
                    arcInhalation = new Arc2D.Double(r, 0, p1, Arc2D.PIE);
                    arcInhalationDyn = new Arc2D.Double(r, 0, dynP1, Arc2D.PIE);
                    // HOLD AFTER INHALATION scope
                    arcHoldAfterInhalation = new Arc2D.Double(r, p1, p2, Arc2D.PIE);
                    arcHoldAfterInhalationDyn = new Arc2D.Double(r, p1, dynP2, Arc2D.PIE);
                    // EXHALATION scope
                    arcExhalation = new Arc2D.Double(r, p1 + p2, p3, Arc2D.PIE);
                    arcExhalationDyn = new Arc2D.Double(r, p1 + p2, dynP3, Arc2D.PIE);
                    // HOLD AFTER EXHALATION scope
                    Arc2D.Double arcHoldAfterExhalation = new Arc2D.Double(r, p1 + p2 + p3, p4, Arc2D.PIE);
                    Arc2D.Double arcHoldAfterExhalationDyn = new Arc2D.Double(r, p1 + p2 + p3, dynP4, Arc2D.PIE);

                    area0 = new Area(outline);
                    area1 = new Area(circle);
                    area2 = new Area(arcInhalation);
                    area3 = new Area(arcInhalationDyn);
                    area4 = new Area(arcHoldAfterInhalation);
                    area5 = new Area(arcHoldAfterInhalationDyn);
                    area6 = new Area(arcExhalation);
                    area7 = new Area(arcExhalationDyn);
                    Area area8 = new Area(arcHoldAfterExhalation);
                    Area area9 = new Area(arcHoldAfterExhalationDyn);

                    if (getBackgroundGraphComp() == true) {
                        // paint 'outline'
                        graphics2d.setPaint(new Color(175, 242, 250));
                        graphics2d.fill(area0);
                    }
                    // paint 'circle'
                    graphics2d.setPaint(Color.BLACK);
                    graphics2d.fill(area1);
                    graphics2d.setPaint(Color.BLACK);
                    graphics2d.fill(circleOutline);
                    // paint 'Inhalation'
                    graphics2d.setPaint(getToneColor(getColor1(), 80));// new Color(255,255,128) utils.setNewColor(getColor1())
                    graphics2d.fill(area2);
                    graphics2d.setPaint(getColor1());
                    graphics2d.fill(area3);
                    // paint 'Hold after inhalation'
                    graphics2d.setPaint(getToneColor(getColor2(), 80));
                    graphics2d.fill(area4);
                    graphics2d.setPaint(getColor2());
                    graphics2d.fill(area5);
                    // paint 'Exhalation'
                    graphics2d.setPaint(getToneColor(getColor3(), 80));
                    graphics2d.fill(area6);
                    graphics2d.setPaint(getColor3());
                    graphics2d.fill(area7);
                    // paint 'Hold after exhalation'
                    graphics2d.setPaint(getToneColor(getColor4(), 80));
                    graphics2d.fill(area8);
                    graphics2d.setPaint(getColor4());
                    graphics2d.fill(area9);
                    // draw outline 'Inhalation','Hold after inhalation' and 'Exhalation' 
                    graphics2d.setPaint(Color.BLACK);
                    graphics2d.draw(area2);
                    graphics2d.draw(area4);
                    graphics2d.draw(area6);
                    graphics2d.draw(area8);
                    // paint circle COUNTER
                    graphics2d.setPaint(getColorCounter());
                    graphics2d.fill(circleCounter);
                    graphics2d.setPaint(Color.DARK_GRAY);
                    graphics2d.draw(circleCounter);
                    // paint string COUNTER
                    graphics2d.setPaint(getColorForeground());
                    utils.centerString(graphics2d, rectCounterNumbers, getOneCounterForAll(), getGraphFont());

                    break;
            }
        }
    }

    protected Double[] convex_polygons(double x, double y, double w, double h, int n, int rotation) {
        // radius of polygon
        double r = w / 2;

        List vert_xy = new ArrayList<Double>();

        double ox = x;
        double oy = y;

        for (int i = 0; i < n; i++) {
            double _x = r * Math.sin(rotation + 2 * Math.PI / n * i) + ox;
            double _y = r * Math.cos(rotation + 2 * Math.PI / n * i) + oy;

            vert_xy.add(_x);
            vert_xy.add(_y);
        }

        Double[] arr_d = (Double[]) vert_xy.toArray(new Double[vert_xy.size()]);

        for (int i = 0; i < arr_d.length; i++) {
            Object double1 = arr_d[i];
            System.out.println("xy: " + double1);
        }
        return arr_d;
    }

    protected void paintCounter(Graphics g, Font font, String TEXT, Color color1, Color color2,
            double x, double y, double w) {
        Graphics2D graphics2d = (Graphics2D) g;

        FontRenderContext frc = new FontRenderContext(null, true, true);
        Rectangle2D r2D = font.getStringBounds(TEXT, frc);
        //dynamic rectangle height which contains counter
        double rHeight = r2D.getHeight() - 2;

        Rectangle2D rectCounter1
                = new Rectangle2D.Double(x, y, w, rHeight);
        RoundRectangle2D roundRectCounter1
                = new RoundRectangle2D.Double(x, y, w, rHeight, 20, 20);
        // paint ouline
        graphics2d.setPaint(color1);
        graphics2d.fill(roundRectCounter1);
        // paint counter
        graphics2d.setPaint(color2);
        utils.centerString(graphics2d, (Rectangle2D.Double) rectCounter1, TEXT, font);
    }

    public int getType() {
        return type;
    }

    public void setIndex(int ind) {
        index = ind;
    }

    public int getIndex() {
        return index;
    }

    public void setFontSize(int size) {
        this.fontSize = size;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setGraphFont(Font f) {
        this.font = f;
    }

    public Font getGraphFont() {
        font = new Font("Book Antiqua", Font.PLAIN, getFontSize());
        return font;
    }

    // for 'inhalation' scope
    public void setColor1(Color color) {
        this.color1 = color;
    }

    // for 'inhalation' scope
    public Color getColor1() {
        return color1;
    }

    // for 'hold ater inhalation' scope
    public void setColor2(Color color) {
        this.color2 = color;
    }

    // for 'hold ater inhalation' scope
    public Color getColor2() {
        return color2;
    }

    // for 'exhalation' scope
    public void setColor3(Color color) {
        this.color3 = color;
    }

    // for 'exhalation' scope
    public Color getColor3() {
        return color3;
    }

    // for 'hold ater exhalation' scope
    public void setColor4(Color color) {
        this.color4 = color;
    }

    // for 'hold ater exhalation' scope
    public Color getColor4() {
        return color4;
    }

    //  CIRCLE DIAGRAMMA
    // for change color COUNTER ;
    public void setColorCounter(Color color) {
        this.colorCounter = color;
    }

    //  CIRCLE DIAGRAMMA
    // for change color COUNTER
    public Color getColorCounter() {
        return colorCounter;
    }

    public void setDeltaX(int val) {
        this.deltaX = val;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public void setV1(double val) {
        v1 = val;
    }

    public double getV1() {
        return v1;
    }

    public void setV2(double val) {
        v2 = val;
    }

    public double getV2() {
        return v2;
    }

    public void setV3(double val) {
        v3 = val;
    }

    public double getV3() {
        return v3;
    }

    public void setV4(double val) {
        v4 = val;
    }

    public double getV4() {
        return v4;
    }

    protected void setBackgroundGraphComp(boolean bool) {
        this.isPaintBackground = bool;
    }

    protected boolean getBackgroundGraphComp() {
        return isPaintBackground;
    }

    public double getIndividualShare(int index, String choice) {

        double equalShare = 0;

        if (index == 1) {
            if (choice == "horizontal") {
                equalShare = getGraphWidth() / (getV1() + getV2());
            } else if (choice == "vertical") {
                equalShare = (getGraphHeight() - 25) / (getV1() + getV2());
            } else if (choice == "circle") {
                equalShare = 360 / (getV1() + getV2());
            } else {
                equalShare = 0;
            }
        }
        if (index == 2) {
            if (choice == "horizontal") {
                equalShare = getGraphWidth() / (getV1() + getV2() + getV3());
            } else if (choice == "vertical") {
                equalShare = (getGraphHeight() - 25) / (getV1() + getV2() + getV3());
            } else if (choice == "circle") {
                equalShare = 360 / (getV1() + getV2() + getV3());
            } else {
                equalShare = 0;
            }
        }
        if (index == 3) {
            if (choice == "horizontal") {
                equalShare = getGraphWidth() / (getV1() + getV2() + getV3() + getV4());
            } else if (choice == "vertical") {
                equalShare = (getGraphHeight() - 25) / (getV1() + getV2() + getV3() + getV4());
            } else if (choice == "circle") {
                equalShare = 360 / (getV1() + getV2() + getV3() + getV4());
            } else {
                equalShare = 0;
            }
        }

        return equalShare;
    }
    
    protected Color getToneColor(Color rgb, int percent) {
       HSLColor c = new HSLColor(rgb);
       Color tone = c.adjustTone(percent);
       return tone;
    }

    public void setDW1(double val) {
        this.dw1 = val;
    }

    public double getDW1() {
        return dw1;
    }

    public void setDW2(double val) {
        this.dw2 = val;
    }

    public double getDW2() {
        return dw2;
    }

    public void setDW3(double val) {
        this.dw3 = val;
    }

    public double getDW3() {
        return dw3;
    }

    public void setDW4(double val) {
        this.dw4 = val;
    }

    public double getDW4() {
        return dw4;
    }

    public void setMax1(double val) {
        this.max1 = val;
    }

    public double getMax1() {
        return max1;
    }

    public void setMax2(double val) {
        this.max2 = val;
    }

    public double getMax2() {
        return max2;
    }

    public void setMax3(double val) {
        this.max3 = val;
    }

    public double getMax3() {
        return max3;
    }

    public void setMax4(double val) {
        this.max4 = val;
    }

    public double getMax4() {
        return max4;
    }

    public void setYDelta(int val) {
        this.yPosDelta = val;
    }

    public double getYDelta() {
        return yPosDelta;
    }

    public void setCounter1(String str) {
        this.strCounter1 = str;
    }

    public String getCounter1() {
        return strCounter1;
    }

    public void setCounter2(String str) {
        this.strCounter2 = str;
    }

    public String getCounter2() {
        return strCounter2;
    }

    public void setCounter3(String str) {
        this.strCounter3 = str;
    }

    public String getCounter3() {
        return strCounter3;
    }

    public void setCounter4(String str) {
        this.strCounter4 = str;
    }

    public String getCounter4() {
        return strCounter4;
    }

    public void setColorForeground(Color color) {
        this.colorForeground = color;
    }

    public Color getColorForeground() {
        return colorForeground;
    }

    public void setOneCounterForAll(String str) {
        this.oneCounterForAll = str;
    }

    public String getOneCounterForAll() {
        return oneCounterForAll;
    }

    public void setGraphWidth(int width) {
        this.w = width;
    }

    public int getGraphWidth() {
        return w;
    }

    public void setGraphHeight(int height) {
        this.h = height;
    }

    public int getGraphHeight() {
        return h;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getGraphWidth(), getGraphHeight());
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(getGraphWidth(), getGraphHeight());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = true;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        focus = true;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        focus = false;
        repaint();
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
    }

//    @Override
//    public void setSize(int width, int height) {
//        w = width;
//        h = height;
//        resize(w, h);
//    }
    public static void main(String[] args) {
        graphComponents arr1 = new graphComponents(3, 3);
//        arr1.setBounds(0, 0, 300, 300);
        // 2,1: 170, 250
        // 2,2: 165, 250
        // 2,3: 223, 250
        arr1.setSize(231, 250);
        arr1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        // PlusMinus arr2 = new PlusMinus(2);

        JFrame f = new JFrame();
        f.setSize(500, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.add(arr1);
        // f.add(arr2);
        f.setVisible(true);

    }

}
