package PRANAYAMA;

import GRAPHIC2D_Software.*;
import com.sun.awt.AWTUtilities;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

public class componentBevelBorder extends BevelBorder implements ActionListener {

    public boolean nullNameBorder = false;
    public static RoundRectangle2D exitbutton;
    public static boolean exibuttonFocused = false;
    public static boolean dragareaFocused = false;
    public static boolean floatability = false;
    public static Rectangle2D dragrectangle;
    // for creation and getting window shape
    public Shape windowShape;
    // nameBorder and other Border attributes should be dispayable :
    public static int limitForResizableWidth;
    public static int limitForResizableHeight;
    // if preffered and minimum size of window already defined
    // size cannot be less this weight and height
    public static boolean limitForResizable1;
    public static boolean blockMouseListeners = false;
    public static JComponent savePanel;

    public componentBevelBorder(int i, // BevelType
            String s) // nameBorder
    {
        super(i);
        deep = 2;
        name = s;
    }

    public componentBevelBorder(int i, // BevelType
            String s, // nameBorder
            Color color,
            Color color1) {
        super(i, color, color1);
        deep = 2;
        name = s;
    }

    public componentBevelBorder(int i, int j, String s, Color color, Color color1, Color color2, Color color3) {
        super(i, color, color1, color2, color3);
        deep = 2;
        deep = j;
        name = s;
    }

    public componentBevelBorder(int i, int j, String s, int k, int l, Color color, Color color1,
            Color color2, Color color3) {
        super(i, color, color1, color2, color3);
        deep = 2;
        deep = j;
        name = s;
        positionOfNameBorder = l;
        positionOnSideOfNameBorder = k;
    }

    public componentBevelBorder(int i, int j, String s, int k, int l, Font font, Color color,
            Color color1, Color color2, Color color3, Color color4) {
        super(i, color1, color2, color3, color4);
        deep = 2;
        deep = j;
        if (s.equals("")) {
            nullNameBorder = true;
            name = s;
        } else {
            nullNameBorder = false;
            name = s;
        }
        positionOfNameBorder = l;
        positionOnSideOfNameBorder = k;
        fontBorder = font;
        nameBorderFont = color;

    }

    private void a(Graphics g, int width, int height) {
        nameBorder = "";
        strWidth = 0;
        int k = 0;
        if (positionOnSideOfNameBorder == 3 || positionOnSideOfNameBorder == 4) {
            k = height;
        } else {
            k = width;
        }
        g.setFont(getBorderFont());
        FontMetrics fontmetrics = g.getFontMetrics();
        if (fontmetrics.stringWidth(name) + 4 < k - 20) {
            nameBorder = name;
            strWidth = fontmetrics.stringWidth(name);
        } else {
            for (int l = 0; l < name.length(); l++) {
                String s = name.substring(0, name.length() - l) + "...";
                if (fontmetrics.stringWidth(s) + 4 >= k - 20) {
                    continue;
                }
                nameBorder = s;
                strWidth = fontmetrics.stringWidth(s);
                break;
            }

        }
    }

    @Override
    public void paintBorder(Component component, Graphics g, int x, int y, int width, int height) {
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color color = g.getColor();
        g.translate(x, y);
        int arc = 10;
        byte widthBorder = 4;

        java.awt.geom.RoundRectangle2D.Double nameRect = new java.awt.geom.RoundRectangle2D.Double();
        java.awt.geom.RoundRectangle2D.Double extRect = new java.awt.geom.RoundRectangle2D.Double();
        RoundRectangle2D intRect1 = new RoundRectangle2D.Double();
        Rectangle intRect = new Rectangle();
        RoundRectangle2D spaceforbutton = new RoundRectangle2D.Double(); // for organization  'exit' button
        exitbutton = new RoundRectangle2D.Double(); // for organization  'exit' button
        RoundRectangle2D exitbuttonInternal = new RoundRectangle2D.Double();
        RoundRectangle2D exitbuttonInternal2 = new RoundRectangle2D.Double();
        dragrectangle = new Rectangle2D.Double();
        Line2D line1 = new Line2D.Double();
        Line2D line2 = new Line2D.Double();
        Line2D line3 = new Line2D.Double();
        Line2D line4 = new Line2D.Double();
        Area dragarea = new Area();
        RoundRectangle2D rightbottomCorner = new RoundRectangle2D.Double();


        a(g, width, height);
        graphics2d.setFont(getBorderFont());
        FontMetrics fontmetrics = graphics2d.getFontMetrics();
        int initOffset = 10;
        int ascent = fontmetrics.getAscent();
        int descent = fontmetrics.getDescent();
//        System.out.println(ascent + "  " + descent + "  " + strWidth );
        int i2 = 0;
        if (positionOnSideOfNameBorder == 3 || positionOnSideOfNameBorder == 4) {
            i2 = height;
        } else {
            i2 = width;
        }
        switch (positionOfNameBorder) {
            case 0: // left
            case 1: // left
                if (positionOnSideOfNameBorder == 3 || positionOnSideOfNameBorder == 4) {
//                System.out.println(initOffset + "  " + i2);
                    initOffset = i2 - 3 - deep - strWidth - 10 - 2; // initOffset = y
//                System.out.println(initOffset + "  " + i2);
                } else {
                    initOffset = 10;  //initOffset = x;
                }
                break;

            case 2: // center
                initOffset = (i2 - 3 - deep) / 2 - strWidth / 2 - 2;
                break;

            case 3: // right
                if (positionOnSideOfNameBorder == 3 || positionOnSideOfNameBorder == 4) {
                    initOffset = 10;
                } else {
                    initOffset = i2 - 3 - deep - strWidth - 10 - 2;  // initOffset = x
                }
                break;
        }

        if (bevelType == 0) {
            switch (positionOnSideOfNameBorder) {
                case 0: //  top side
                case 1: //  top side
//*************************************************************************************************************************
                    nameRect = new java.awt.geom.RoundRectangle2D.Double(initOffset, // initOffset = 10
                            1.0D, // 1
                            strWidth + 4, // width = 101 + 4
                            ascent + widthBorder, // height = 11 + 4
                            arc, arc);
                    extRect = new java.awt.geom.RoundRectangle2D.Double(1.0D, // x =  1
                            1 + (ascent * 2) / 3, // y =  1 + (11*2)/3
                            width - 3 - deep, // width
                            height - 3 - deep - (ascent * 2) / 3, // height
                            arc, arc);
                    spaceforbutton = new RoundRectangle2D.Double(width - 3 - ascent - widthBorder - deep - initOffset,
                            1.0D,
                            26,
                            26, arc, arc);
                    exitbutton = new RoundRectangle2D.Double(width - 3 - ascent - widthBorder - deep - initOffset,
                            1.0D,
                            24,
                            24, arc, arc);

                    rightbottomCorner = new RoundRectangle2D.Double(width,
                            height, //height - widthBorder - deep - 35
                            206,
                            206, arc, arc);

                    // limitation for decreasing size of frame
                    // not can be less then defined size in method :: 'paintBorder'
                    limitForResizableWidth = (int) (strWidth + exitbutton.getWidth() + initOffset + 25);
                    limitForResizableHeight = (int) (exitbutton.getHeight() + deep + 25);


                    exitbuttonInternal = new RoundRectangle2D.Double(width - 3 - ascent - widthBorder - deep - initOffset + 2,
                            2.0D,
                            22,
                            22, arc, arc);
                    exitbuttonInternal2 = new RoundRectangle2D.Double(width - 3 - ascent - widthBorder - deep - initOffset + 3,
                            3.0D,
                            21,
                            21, arc, arc);


                    line1 = new Line2D.Double(width - 3 - ascent - widthBorder - deep - initOffset + 6, 6.0D,
                            width - 3 - widthBorder - deep - 1, 19.0D);
                    line3 = new Line2D.Double(width - 3 - ascent - widthBorder - deep - initOffset + 6, 7.0D,
                            width - 3 - widthBorder - deep - 1, 20.0D);

                    line2 = new Line2D.Double(width - 3 - widthBorder - deep - 1, 6.0D,
                            width - 3 - ascent - widthBorder - deep - initOffset + 6, 19.0D);
                    line4 = new Line2D.Double(width - 3 - widthBorder - deep - 1, 7.0D,
                            width - 3 - ascent - widthBorder - deep - initOffset + 6, 20.0D);

                    double w = spaceforbutton.getWidth();
                    if (floatability) {
                        dragrectangle = new Rectangle2D.Double(initOffset + strWidth + widthBorder + deep * 2,
                                widthBorder * 2 + deep,
                                width - w * 3.2,
                                13);
                    }

                    intRect = new Rectangle(1 + widthBorder, // x = 1 + 5
                            1 + widthBorder + ascent + 10, // 10 !
                            width - 3 - deep - widthBorder * 2 , // width
                            height - 3 - deep  - widthBorder * 2 - ascent  - 10 );  // height need also change 'borderInsets'
                    break;
//****************************************************************************************************************************
                case 2: // bottom side
                    nameRect = new java.awt.geom.RoundRectangle2D.Double(initOffset,
                            height - 3 - ascent - widthBorder - deep,
                            strWidth + 4,
                            ascent + widthBorder,
                            arc, arc);
                    extRect = new java.awt.geom.RoundRectangle2D.Double(1.0D,
                            1.0D,
                            width - 3 - deep,
                            height - 3 - deep - (ascent * 2) / 3,
                            arc, arc);
                    intRect = new Rectangle(1 + widthBorder,
                            1 + widthBorder,
                            width - 3 - deep - widthBorder * 2,
                            height - 3 - deep - widthBorder * 2 - ascent);
                    break;

                case 3: // left side
                    nameRect = new java.awt.geom.RoundRectangle2D.Double(1.0D,
                            initOffset,
                            ascent + widthBorder,
                            strWidth + 4,
                            arc, arc);
                    extRect = new java.awt.geom.RoundRectangle2D.Double(1 + (ascent * 2) / 3,
                            1.0D,
                            width - 3 - deep - (ascent * 2) / 3,
                            height - 3 - deep,
                            arc, arc);
                    intRect = new Rectangle(1 + widthBorder + ascent,
                            1 + widthBorder,
                            width - 3 - deep - widthBorder * 2 - ascent,
                            height - 3 - deep - widthBorder * 2);
                    break;

                case 4: // right side
                    nameRect = new java.awt.geom.RoundRectangle2D.Double(width - 3 - ascent - widthBorder - deep,
                            initOffset,
                            ascent + widthBorder,
                            strWidth + 4,
                            arc, arc);
                    extRect = new java.awt.geom.RoundRectangle2D.Double(1.0D,
                            1.0D,
                            width - 3 - deep - (ascent * 2) / 3,
                            height - 3 - deep,
                            arc, arc);
                    intRect = new Rectangle(1 + widthBorder,
                            1 + widthBorder,
                            width - 3 - deep - widthBorder * 2 - ascent,
                            height - 3 - deep - widthBorder * 2);
                    break;
            }
        } else if (bevelType == 1) {
            switch (positionOnSideOfNameBorder) {
                case 0: // top side
                case 1: // top side
                    nameRect = new java.awt.geom.RoundRectangle2D.Double(initOffset + deep, 1 + deep, strWidth + 4, ascent + widthBorder, arc, arc);
                    extRect = new java.awt.geom.RoundRectangle2D.Double(1 + deep, 1 + deep + (ascent * 2) / 3, width - 3 - deep, height - 3 - deep - (ascent * 2) / 3, arc, arc);
                    intRect = new Rectangle(1 + deep + widthBorder, 1 + deep + widthBorder + ascent, width - 3 - deep - widthBorder * 2, height - 3 - deep - widthBorder * 2 - ascent);
                    break;

                case 2: // bottom side
                    nameRect = new java.awt.geom.RoundRectangle2D.Double(initOffset + deep, height - 3 - ascent - widthBorder, strWidth + 4, ascent + widthBorder, arc, arc);
                    extRect = new java.awt.geom.RoundRectangle2D.Double(1 + deep, 1 + deep, width - 3 - deep, height - 3 - deep - (ascent * 2) / 3, arc, arc);
                    intRect = new Rectangle(1 + deep + widthBorder, 1 + deep + widthBorder, width - 3 - deep - widthBorder * 2, height - 3 - deep - widthBorder * 2 - ascent);
                    break;

                case 3: // left side
                    nameRect = new java.awt.geom.RoundRectangle2D.Double(1 + deep, initOffset + deep, ascent + widthBorder, strWidth + 4, arc, arc);
                    extRect = new java.awt.geom.RoundRectangle2D.Double(1 + deep + (ascent * 2) / 3, 1 + deep, width - 3 - deep - (ascent * 2) / 3, height - 3 - deep, arc, arc);
                    intRect = new Rectangle(1 + deep + widthBorder + ascent, 1 + deep + widthBorder, width - 3 - deep - widthBorder * 2 - ascent, height - 3 - deep - widthBorder * 2);
                    break;

                case 4: // right side
                    nameRect = new java.awt.geom.RoundRectangle2D.Double(width - 3 - ascent - widthBorder, initOffset + deep, ascent + widthBorder, strWidth + 4, arc, arc);
                    extRect = new java.awt.geom.RoundRectangle2D.Double(1 + deep, 1 + deep, width - 3 - deep - (ascent * 2) / 3, height - 3 - deep, arc, arc);
                    intRect = new Rectangle(1 + deep + widthBorder, 1 + deep + widthBorder, width - 3 - deep - widthBorder * 2 - ascent, height - 3 - deep - widthBorder * 2);
                    break;
            }
        }
        Area area = new Area(extRect);
        // No need add roundRectangle which contains nameBorder if name border == ""
        if (!nullNameBorder) {
            area.add(new Area(nameRect));  // new 'area'
        }

        area.add(new Area(spaceforbutton));
        Area area1 = new Area(area);
        Area area7 = new Area(intRect);
//        area7.subtract(new Area(rightbottomCorner));
        area1.subtract(area7);  // form of border is constructed  //  new Area(intRect) 
        Rectangle bottom = new Rectangle(1 + widthBorder,
                1 + widthBorder + ascent + 10,
                width - 3 - deep - widthBorder * 2 - 23,
                height - 3 - deep - widthBorder * 2 - ascent - 10);
        Rectangle right = new Rectangle(1 + widthBorder,
                1 + widthBorder + ascent + 10,
                width - 3 - deep - widthBorder * 2,
                height - 3 - deep - widthBorder * 2 - ascent - 33);
        Rectangle r1 = new Rectangle(width - 3 - deep - widthBorder,
                height - 3 - deep - widthBorder * 2 - ascent - 10,
                2, // width
                25);
        Rectangle b1 = new Rectangle(width - 3 - deep - widthBorder - 25,
                height - 3 - deep - widthBorder,
                25, // width
                2);

        GeneralPath path0 = new GeneralPath(bottom);
        GeneralPath path1 = new GeneralPath(right);
        GeneralPath path2 = new GeneralPath(r1);
        GeneralPath path3 = new GeneralPath(b1);
//        area1.subtract(new Area(path0));
//        area1.subtract(new Area(path1));
//        area1.subtract(new Area(path2));
//        area1.subtract(new Area(path3));

        Line2D line_a1 = new Line2D.Double(width - 3 - deep - widthBorder - 21, height - 3 - deep - widthBorder * 2 - ascent - 6,
                width - 3 - deep - widthBorder - 12, height - 3 - deep - widthBorder * 2 - ascent - 6);
        Line2D line_a2 = new Line2D.Double(width - 3 - deep - widthBorder - 21, height - 3 - deep - widthBorder * 2 - ascent - 6,
                width - 3 - deep - widthBorder - 21, height - 3 - deep - widthBorder * 2 - 8);
        Line2D line_a3 = new Line2D.Double(width - 3 - deep - widthBorder - 21, height - 3 - deep - widthBorder * 2 - ascent - 6,
                width - 3 - deep - widthBorder - 2, height - 3 - deep - widthBorder - 2);
        Line2D line_a4 = new Line2D.Double(width - 3 - deep - widthBorder - 2, height - 3 - deep - widthBorder - 2,
                width - 3 - deep - widthBorder - 2, height - 3 - deep - widthBorder - 12);
        Line2D line_a5 = new Line2D.Double(width - 3 - deep - widthBorder - 2, height - 3 - deep - widthBorder - 2,
                width - 3 - deep - widthBorder - 12, height - 3 - deep - widthBorder - 2);

        GeneralPath arrow = new GeneralPath(line_a1);
        arrow.append(line_a2, false);
        arrow.append(line_a3, false);
        arrow.append(line_a4, false);
        arrow.append(line_a5, false);

//        arrow.moveTo( width -3 - deep  - widthBorder - 24 , height - 3 - deep - widthBorder * 2 - ascent - 9 );
//        arrow.lineTo( width -3 - deep  - widthBorder - 12, height - 3 - deep - widthBorder * 2 - ascent - 9 );
//        graphics2d.setColor(Color.red);
//        graphics2d.setStroke(new BasicStroke(2.0f));
//        graphics2d.draw(arrow) ;
        Area minus = new Area(arrow);
//        area1.subtract();
//        graphics2d.fill(area1) ;
//        area.add(new Area(rightbottomCorner));

//-------------------------------------------------------------------------------
        AffineTransform affinetransform = new AffineTransform();
        if (bevelType == 0) {
            affinetransform.translate(1.0D, 1.0D);
        } else {
            affinetransform.translate(-1D, -1D);
        }
        Area area2 = new Area(area);
        Area area3 = new Area(area);
        Area area4 = new Area(area2);
        for (int j2 = 0; j2 < deep; j2++) {
            area4.transform(affinetransform);
            area2.add(area4);
            area3.transform(affinetransform);
        }

        area2.subtract(area);
        area3.transform(affinetransform);
        area3.subtract(area);
        if (bevelType == 0) {
            affinetransform.translate(-2D, -2D);
        } else {
            affinetransform.translate(2D, 2D);
        }
        Area area5 = new Area(area);
        area5.transform(affinetransform);
        graphics2d.setColor(super.getShadowOuterColor(component));
        graphics2d.draw(area3);
        graphics2d.setColor(super.getHighlightOuterColor(component));
        graphics2d.draw(area5); // top HighlightOuter white line
        graphics2d.setColor(super.getShadowInnerColor(component));
        graphics2d.draw(area2);
        graphics2d.fill(area2);
        graphics2d.setColor(super.getHighlightInnerColor(component));
        graphics2d.fill(area1); //
        graphics2d.setColor(Color.red);
        graphics2d.setStroke(new BasicStroke(1.0f));

//        graphics2d.draw(arrow) ;


        // **********************  Addition 'exit button' ****************************************************
        graphics2d.setColor(new Color(190, 220, 220));
        area.add(new Area(exitbutton));
        graphics2d.fill(exitbutton);
        area.add(new Area(exitbuttonInternal));
        if (!exibuttonFocused) {
            graphics2d.setColor(Color.black);
        }
        graphics2d.draw(exitbuttonInternal);  // outline 'exit button'
        if (!exibuttonFocused) {
            graphics2d.setColor(Color.orange);
        } else {
            graphics2d.setColor(Color.red);
        }
        graphics2d.fill(exitbuttonInternal2); // background  'exit button'
        area.add(new Area(line1));
        area.add(new Area(line2));
        area.add(new Area(line3));
        area.add(new Area(line4));
        graphics2d.setColor(Color.yellow);
        graphics2d.draw(line1);
        graphics2d.draw(line2);
        graphics2d.draw(line3);
        graphics2d.draw(line4);
        // This adding different parts of borders allows create outlines for
        // window shape
        area.add(area3);
        area.add(area5);
        area.add(area2);
        area.add(area1);
        
//        System.out.println("line1:" +  line1.getX1() + " " + line1.getX2() + " " + line1.getY1() + " " + line1.getY2());
//        System.out.println("line2:" +  line2.getX1() + " " + line2.getX2() + " " + line2.getY1() + " " + line2.getY2());
//        System.out.println("line3:" +  line3.getX1() + " " + line3.getX2() + " " + line3.getY1() + " " + line3.getY2());
//        System.out.println("line4:" +  line4.getX1() + " " + line4.getX2() + " " + line4.getY1() + " " + line4.getY2());
        
        // here window gets outlines for future use
        windowShape = area;

        // allow drag
        if (floatability) {
            area.add(dragarea);
            area.add(new Area(dragrectangle));
            if (!dragareaFocused) {
                graphics2d.setColor(Color.yellow);
            } else {
                graphics2d.setColor(new Color(150, 220, 220));
                graphics2d.fill(dragrectangle);
            }

            if (!dragareaFocused) {
                graphics2d.setColor(Color.yellow);
            } else {
                graphics2d.setColor(Color.red);
            }
            double w = spaceforbutton.getWidth();
            double xst = initOffset + strWidth + widthBorder + deep * 2;
            double yst = widthBorder * 2 + deep;
            dragarea = new Area(dragrectangle);
            for (double yy = yst; yy <= 23; yy += 3) {
                for (double xx = xst; xx < (width - w - 5); xx += 3) {
                    Ellipse2D el = new Ellipse2D.Double(xx, yy, 1, 1);
                    graphics2d.fill(el);
                    Area ar = new Area(el);
                    dragarea.add(ar);
                }
            }
        }
//        graphics2d.setColor(Color.red);
//        area.add(new Area(rightbottomCorner));
//        graphics2d.fill(rightbottomCorner) ;

        AffineTransform affinetransform1 = new AffineTransform();
        affinetransform1.rotate(-1.5707963267948966D);
        Font font = getBorderFont().deriveFont(affinetransform1);
        graphics2d.setColor(_mthfor());
        graphics2d.setFont(getBorderFont());
        if (bevelType == 0) // bevelBorder = raised
        {
            switch (positionOnSideOfNameBorder) {
                case 0: // top side
                case 1: // top side
                    graphics2d.drawString(nameBorder, initOffset + 2, ascent);
                    break;

                case 2: // bottom side
                    graphics2d.drawString(nameBorder, initOffset + 2, height - descent - deep - widthBorder);
                    break;

                case 3: // left side
                    graphics2d.setFont(font);
                    graphics2d.drawString(nameBorder, ascent, initOffset + strWidth + 2);
                    break;

                case 4: // right side
                    graphics2d.setFont(font);
                    graphics2d.drawString(nameBorder, width - descent - deep - 2 - 2, initOffset + strWidth + 2);
                    break;
            }
        } else // bevelBorder = down
        {
            switch (positionOnSideOfNameBorder) {
                case 0: // '\0'
                case 1: // '\001'
                    graphics2d.drawString(nameBorder, initOffset + 2 + deep, ascent + deep);
                    break;

                case 2: // '\002'
                    graphics2d.drawString(nameBorder, initOffset + 2 + deep, height - descent - widthBorder);
                    break;

                case 3: // '\003'
                    graphics2d.setFont(font);
                    graphics2d.drawString(nameBorder, ascent + deep, initOffset + deep + strWidth + 2);
                    break;

                case 4: // '\004'
                    graphics2d.setFont(font);
                    graphics2d.drawString(nameBorder, width - descent - 2 - 2, initOffset + strWidth + 2 + deep);
                    break;
            }
        }
        g.translate(-x, -y);
        g.setColor(color);
    }

    @Override
    public Insets getBorderInsets(Component component) {
        return getBorderInsets(component, new Insets(0, 0, 0, 0));
    }

    @Override
    public Insets getBorderInsets(Component component, Insets insets) {
        byte widthBorder = 4;
        int i = 7;
        byte byte1 = 2;
        Graphics g = component.getGraphics();
        if (g != null) {
            Font font = g.getFont();
            g.setFont(getBorderFont());
            FontMetrics fontmetrics = g.getFontMetrics();
            i = fontmetrics.getAscent();
            int j = fontmetrics.getDescent();
            g.setFont(font);
        }
        switch (positionOnSideOfNameBorder) {
            default:
                break;

            case 0: // top side
            case 1: // top side
                if (bevelType == 0) {
                    insets.left = 1 + widthBorder;
                    insets.top = 1 + widthBorder + i + 10 ;  // Modified :: + 10 !
                    insets.right = 2 + widthBorder  + deep; // important !  + 25
                    insets.bottom = 2 + widthBorder  + deep ; // important ! + 20
                } else {
                    insets.left = 1 + widthBorder + deep;
                    insets.top = 1 + widthBorder + deep + i;
                    insets.right = 2 + widthBorder;
                    insets.bottom = 2 + widthBorder;
                }
                break;

            case 2: // '\002'
                if (bevelType == 0) {
                    insets.left = 1 + widthBorder;
                    insets.top = 1 + widthBorder;
                    insets.right = 2 + widthBorder + deep;
                    insets.bottom = 2 + widthBorder + deep + i;
                } else {
                    insets.left = 1 + widthBorder + deep;
                    insets.top = 1 + widthBorder + deep;
                    insets.right = 2 + widthBorder;
                    insets.bottom = 2 + widthBorder + i;
                }
                break;

            case 3: // '\003'
                if (bevelType == 0) {
                    insets.left = 1 + widthBorder + i;
                    insets.top = 1 + widthBorder;
                    insets.right = 2 + widthBorder + deep;
                    insets.bottom = 2 + widthBorder + deep;
                } else {
                    insets.left = 1 + widthBorder + deep + i;
                    insets.top = 1 + widthBorder + deep;
                    insets.right = 2 + widthBorder;
                    insets.bottom = 2 + widthBorder;
                }
                break;

            case 4: // '\004'
                if (bevelType == 0) {
                    insets.left = 1 + widthBorder;
                    insets.top = 1 + widthBorder;
                    insets.right = 2 + widthBorder + deep + i;
                    insets.bottom = 2 + widthBorder + deep;
                } else {
                    insets.left = 1 + widthBorder + deep;
                    insets.top = 1 + widthBorder + deep;
                    insets.right = 2 + widthBorder + i;
                    insets.bottom = 2 + widthBorder;
                }
                break;
        }
        return insets;
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void setDeep(int i) {
        deep = i;
    }

    public void setName(String s) {
        name = s;
    }

    public void _mthdo(int i) {
        switch (i) {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
                positionOnSideOfNameBorder = i;
                break;

            default:
                throw new IllegalArgumentException(i + " is not a valid title position.");
        }
    }

    public void _mthif(int i) {
        switch (i) {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                positionOfNameBorder = i;
                break;

            default:
                throw new IllegalArgumentException(i + " is not a valid title justification.");
        }
    }

    public void setFontBorder(Font font) {
        fontBorder = font;
    }

    public void setNameBorderFont(Color color) {
        nameBorderFont = color;
    }

    public int getDeep() {
        return deep;
    }

    public String getName() {
        return name;
    }

    public int _mthdo() {
        return positionOnSideOfNameBorder;
    }

    public int _mthint() {
        return positionOfNameBorder;
    }

    public Font getBorderFont() {
        Font font = fontBorder;
        if (font == null) {
            font = UIManager.getFont("TitledBorder.font");
        }
        return font;
    }

    public Color _mthfor() {
        Color color = nameBorderFont;
        if (color == null) {
            color = UIManager.getColor("TitledBorder.titleColor");
        }
        return color;
    }

    public void setHighlightInner(Color color) {
        highlightInner = color;
    }

    public void setHighlightOuter(Color color) {
        highlightOuter = color;
    }

    public void setShadowInner(Color color) {
        shadowInner = color;
    }

    public void setShadowOuter(Color color) {
        shadowOuter = color;
    }
    protected String name;
    protected String nameBorder;
    protected int strWidth;
    protected int positionOnSideOfNameBorder;
    protected int positionOfNameBorder;
    protected Font fontBorder;
    protected Color nameBorderFont;
    protected int deep;
    static JFrame fr;
    static JWindow win;

    public static void main(String[] args) {
         componentBevelBorder aaa = new componentBevelBorder(1, // BevelType
                2, // deep
                "Figure", // name  "Change Properties "
                1, // positionOnSideOfNameBorder
                1, // positionOfNameBorder
                new Font("Times New Roman", 1, 12),
                new Color(0, 100, 0), //  fontColor
                Color.white, // Color.white  // outsideShadow
                new Color(190, 220, 220), // new Color(190, 220, 220)    // colorBorder
                new Color(100, 130, 130), //   new Color(100, 130, 130)  // outsideShadow
                new Color(150, 180, 180)); // new Color(150, 180, 180)    // insideBorder
        aaa.setFloatable(false);
        aaa.setResizableLimit(false); 

        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(200, 250));
        p.setMinimumSize(new Dimension(200, 250));
       // p.setBorder(aaa);
       // p.addMouseListener(aaa.getMouseListener(p));
        p.addMouseMotionListener(aaa.getMouseListener(p));
        p.add(new JButton("test"));

        JPanel pp = new JPanel();
        pp.setLayout(null);
        JButton b = new JButton("Hello");
//    b.setLocation(300, 100);
//    b.setAlignmentX(0.9f);
        b.setBounds(10, 45, 120, 65);

        pp.add(b);
//    pp.addMouseListener(  aaa.getMouseListener(pp)  );
//    pp.addMouseMotionListener(aaa.getMouseListener(pp) );
//    pp.addMouseListener(  aaa.getDnDMouseListener(pp)  );
//    pp.addMouseMotionListener(aaa.getDnDMouseListener(pp) );
        win = aaa.getWindowForm();
        win.setAlwaysOnTop(true);
        
        win.setPreferredSize(new Dimension(200, 250));
        win.setMinimumSize(new Dimension(200, 250));
//        pp.setPreferredSize(win.getPreferredSize());
//        pp.setMinimumSize(win.getMinimumSize());
//    pp.setBorder(aaa);
        JComponent con = (JComponent) win.getContentPane();
        DnDMouse dnd = new DnDMouse(con);
        resizable res = new resizable(con);
        con.setBorder(aaa);
        //win.add(pp);
//    win.addMouseListener(  aaa.getMouseListener(con)  );
//    win.addMouseMotionListener(aaa.getMouseListener(con) );
//    win.addMouseListener(  aaa.getDnDMouseListener(con)  );
//    win.addMouseMotionListener( aaa.getDnDMouseListener(con) );
        win.setVisible(true);
        
//        fr = new JFrame("");
//        fr.setLayout(new FlowLayout());
//        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//       // fr.add(p);
//        fr.setSize(600, 400);
//        fr.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitbutton) {
            System.out.println("Fuck !");
        }
    }

    public Shape getWindowShape() {
        return windowShape;
    }

    public resizable getMouseListener(JComponent p) {
        return new resizable(p);
    }

    public DnDMouse getDnDMouseListener(JComponent p) {
        return new DnDMouse(p);
    }

    public formForWindow getWindowForm() {
        return new formForWindow();
    }

    public formForJFrame getJFrameForm() {
        return new formForJFrame();
    }

    public JComponent likeToolBar() {
//        if(savePanel == null )
        return savePanel;
    }

    // allow drag and drop
    public void setFloatable(boolean permit) {
        if (permit) {
            floatability = true;
        } else {
            floatability = false;
        }
    }

    // if preffered and minimum size of window already defined
    // size it cannot be less this weight and height advance set
    public void setResizableLimit(boolean resize) {
        if (resize) {
            limitForResizable1 = true;
        } else {
            limitForResizable1 = false;
        }
    }

    public controlButtons getControlButtonsBehavior(JComponent c , int choice , int JFrame_OR_Window){
        return new controlButtons(c , choice , JFrame_OR_Window );
    }

 // ************************  BEHAVIOR FOR BUTTONS ********************************
   static  class controlButtons implements MouseListener, MouseMotionListener {
        
        JComponent comp;
        int myChoice; // 'hide' or 'exit'
        int JFrame_OR_Window = 0;

         public static final int CLOSE_WINDOW = 0;
         public static final int HIDE_WINDOW  = 1;

        controlButtons(JComponent com , int choice , int JFrameORWindow ) {
           comp = com;
           myChoice = choice;
           JFrame_OR_Window = JFrameORWindow;
           comp.addMouseListener(this);
           comp.addMouseMotionListener(this);
        }

         public JWindow getJWindow(Container target) {
            if (target instanceof JWindow) {
                return (JWindow) target;
            }
            return getJWindow(target.getParent());
        }

          public JFrame getJFrame(Container target) {
            if (target instanceof JFrame) {
                return (JFrame) target;
            }
            return getJFrame(target.getParent());
        }

          public void setFrameOrWindow(int choice) {
              JFrame_OR_Window = choice;
          }

          public int getFrameOrWindow() {
              return JFrame_OR_Window;
          }

         protected int getChoice() {
             return myChoice;
         }

        @Override
        public void mouseClicked(MouseEvent e) { }
        @Override
        public void mousePressed(MouseEvent e) {
            if (exitbutton.contains(e.getPoint())) {
                if( getChoice() == 0 ) {
                  System.exit(0);
                  comp.repaint();
                  System.out.println("-0-");
                }
                if( getChoice() == 1 ) {
                    if( getFrameOrWindow() == 1 )
                  getJWindow(comp).setVisible(false);
                    else if ( getFrameOrWindow() == 0 )
                  getJFrame(comp).setVisible(false);
                  comp.repaint();
                  System.out.println("-1-");
                }
                  
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {  }
        @Override
        public void mouseEntered(MouseEvent e) { 
                  if(exitbutton != null)
                  if (exitbutton.contains(e.getPoint())) {
                   exibuttonFocused = true;
                   comp.repaint();
//                   System.out.println("Space_exit");
            }
        }
        @Override
        public void mouseExited(MouseEvent e) { 
                  if(exitbutton != null)
                  if (!exitbutton.contains(e.getPoint())) {
                   exibuttonFocused = false;
                   comp.repaint();
//                   System.out.println("Space_exit");
            }
        }
        @Override
        public void mouseDragged(MouseEvent e) {   }

        @Override
        public void mouseMoved(MouseEvent e) {
//            if (exitbutton.contains(e.getPoint())) {
//                   exibuttonFocused = true;
//                   comp.repaint();
////                   System.out.println("Space_exit");
//            }  else {
//                   exibuttonFocused = false;
//                   comp.repaint();}
        }

    }

    class formForWindow extends JWindow {
//        JWindow window;

        formForWindow() {
        }

        @Override
        public void paint(Graphics g) {
            super.paintComponents(g);
            AWTUtilities.setWindowShape(this, getWindowShape());
        }
    }

     class formForJFrame extends JFrame {
//        JWindow window;

        formForJFrame() {
        }

        @Override
        public void paint(Graphics g) {
            super.paintComponents(g);
            AWTUtilities.setWindowShape(this, getWindowShape());
        }
    }

    static class resizable implements MouseListener, MouseMotionListener {

        JComponent p;
        Dimension oldDim;
        Dimension newDim;
        Rectangle2D rightBottomCorner;
        Rectangle2D leftBottomCorner;
        int x1, y1;
        int x2, y2;
        boolean top = false;
        boolean right = false;
        boolean left = false;
        boolean bottom = false;
        boolean leftbot = false;
        boolean rightbot = false;
        boolean rightside;
        boolean rightbottomCorner = false;
//        boolean permit = false;

        resizable(JComponent pan) {
            p = pan;
            if (!blockMouseListeners) {
                p.addMouseListener(this);
                p.addMouseMotionListener(this);
            }
        }

        @Override
        public void mouseClicked(MouseEvent me) {
            int w = p.getWidth();
            int h = p.getHeight();
            int x = me.getPoint().x;
            int y = me.getPoint().y;
            int s_x = me.getLocationOnScreen().x;
            int s_y = me.getLocationOnScreen().y;
            Insets ins = p.getInsets();
            leftBottomCorner = new Rectangle2D.Double(ins.left, h - ins.bottom - 25, ins.left + 25, ins.bottom + 25);
            rightBottomCorner = new Rectangle2D.Double(w - ins.right - 25, h - ins.bottom - 25, w - ins.right + 25, ins.bottom + 25);

            boolean inBorder = (x < ins.left || x > w - ins.right
                    || y < ins.top || y > h - ins.bottom);
            boolean leftside = (x < ins.left);
            rightside = (x > w - ins.right);
            boolean topside = (y < ins.top);
            boolean bottomside = (y > h - ins.bottom);
            boolean exitbuttonarea = (exitbutton.contains(x, y));
            boolean leftbottomCorner = (leftBottomCorner.contains(x, y));
            if (permit) {
                rightbottomCorner = (rightBottomCorner.contains(x, y));  // rightBottomCorner.contains(x, y)
//                    System.out.println("yes");
            }
//                else  {
//                    rightbottomCorner = true;
//
//                    System.out.println("not");
//           }
            boolean r_b_side = (x > rightBottomCorner.getWidth() || y > rightBottomCorner.getHeight());

//                if(permit) {
            if (leftside) {
                p.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                left = true;
                right = false;
                bottom = false;
                leftbot = false;
                rightbot = false;
                top = false;
            } else if (rightside) {
                p.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                right = true;
                left = false;
                bottom = false;
                leftbot = false;
                rightbot = false;
                top = false;
            } else if (bottomside) {
                p.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                ;
                bottom = true;
                leftbot = false;
                rightbot = false;
                right = false;
                left = false;
                top = false;
            } else if (leftbottomCorner) {
                p.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                leftbot = true;
                rightbot = false;
                right = false;
                bottom = false;
                left = false;
                top = false;
            } else if (rightbottomCorner) {   /// rightbottomCorner
                p.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                rightbot = true;
                right = false;
                bottom = false;
                leftbot = false;
                left = false;
                top = false;
            } else if (topside) {
                p.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                top = true;
                rightbot = false;
                right = false;
                bottom = false;
                leftbot = false;
                left = false;
            } else if (inBorder) {
                p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                rightbot = false;
                right = false;
                bottom = false;
                leftbot = false;
                left = false;
                top = false;
            } else {
                p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
//        }

            if (permit) {
                if (exitbutton.contains(me.getPoint())) {
                    exibuttonFocused = true;
                    p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    p.repaint();
//                    }
                } else {
                    exibuttonFocused = false;
                    p.repaint();
                }
            }
            // only for keeping cursor in dragarea different from resize cursor
            // in top area
            if (permit) {
                if (dragrectangle.contains(me.getPoint())) {
                    p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    p.repaint();
                }
            }
//                System.out.println("click");
        }

        @Override
        public void mousePressed(MouseEvent me) {
            mouseClicked(me);
            if (dragrectangle.contains(me.getPoint())) {
                dragareaFocused = true;
//         top = false;
//         left = false;
//         right = false;
//         bottom = false;
//         leftbot = false;
//         rightbot = false;
                p.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                p.repaint();
            }
            if (exitbutton.contains(me.getPoint())) {
                savePanel = p;
//                       savePanel.removeMouseListener(new DnDMouse(savePanel));
//                       savePanel.removeMouseMotionListener(new DnDMouse(savePanel));
//                       savePanel.setBorder(null);
                win.setVisible(false);
                savePanel.setPreferredSize(savePanel.getPreferredSize());
                savePanel.setMinimumSize(savePanel.getMinimumSize());
                savePanel.setSize(savePanel.getPreferredSize());
                fr.add(savePanel);
                fr.validate();
                fr.repaint();
                blockMouseListeners = true;
//                System.out.println(savePanel);
//                     System.exit(0);
            }
            top = false;
            left = false;
            right = false;
            bottom = false;
            leftbot = false;
            rightbot = false;
//         permit = false;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            dragareaFocused = false;
            p.repaint();
            p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            top = false;
            left = false;
            right = false;
            bottom = false;
            leftbot = false;
            rightbot = false;
            permit = true;
            permit_rb = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
//        mouseClicked(e);
        }

        @Override
        public void mouseExited(MouseEvent e) {
//        mouseClicked(e);
        }
        boolean permit = true;

        @Override
        public void mouseDragged(MouseEvent me) {
            if (permit) {
                mouseClicked(me);
            }
//               p.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
//               p.repaint();
            if (permit) {
                if (dragrectangle.contains(me.getPoint())) {
                    dragareaFocused = true;
                    leftbot = false;
                    rightbot = false;
                    right = false;
                    bottom = false;
                    left = false;
                    top = false;
                    p.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                    p.repaint();
                }
            }
            int offset_y = me.getPoint().x - p.getX(); // top side
            int offset_w = me.getPoint().x - p.getWidth(); // right side
            int offset_x = me.getPoint().x - p.getX(); // left side
            int offset_h = me.getPoint().y - p.getHeight(); // bottom  side
            int pos_y = me.getPoint().y;
            int pos_x = me.getPoint().x;

            JWindow frame = this.getFrame(p);

//***************************** Resizing of Sides ***************************************************
//*****************************************************************************************************
            if (permit) {
                if (offset_w >= 0) {
                    if (right) {
                        if (!limitForResizable1) {
                            frame.setPreferredSize(new Dimension(pos_x, frame.getSize().height));
                            frame.setMinimumSize(new Dimension(pos_x, frame.getSize().height));
                        }
                        frame.setSize(pos_x, frame.getSize().height);
                    }
                } else {
                    if (right) {
                        // resizable but not less then preffered & minimum size
                        // in advance predefined
                        if (!limitForResizable1) {
                            // limitation for decreasing size of frame
                            // not can be less then defined size in method :: 'paintBorder'
                            if (pos_x <= limitForResizableWidth) {
                                pos_x = limitForResizableWidth;
                                frame.setPreferredSize(new Dimension(pos_x, frame.getSize().height));
                                frame.setMinimumSize(new Dimension(pos_x, frame.getSize().height));
                                frame.setSize(pos_x, frame.getSize().height);
                            } else {
                                frame.setPreferredSize(new Dimension(pos_x, frame.getSize().height));
                                frame.setMinimumSize(new Dimension(pos_x, frame.getSize().height));
                                frame.setSize(pos_x, frame.getSize().height);
                            }
                        } else {
                            frame.setSize(pos_x, frame.getSize().height);
                        }
                    }
                }
            }

            if (permit) {
                if (offset_h > 0) {
                    if (bottom) {
                        if (!limitForResizable1) {
                            frame.setPreferredSize(new Dimension(frame.getSize().width, pos_y));
                            frame.setMinimumSize(new Dimension(frame.getSize().width, pos_y));
                        }
                        frame.setSize(frame.getSize().width, pos_y);
                    }
                } else {
                    if (bottom) {
                        // resizable but not less then preffered & minimum size
                        // in advance predefined
                        if (!limitForResizable1) {
                            // limitation for decreasing size of frame
                            // not can be less then defined size in method :: 'paintBorder'
                            // name border and button if exists should be visible !
                            if (pos_y <= limitForResizableWidth) {
                                pos_y = limitForResizableWidth;
                                frame.setPreferredSize(new Dimension(frame.getSize().width, pos_y));
                                frame.setMinimumSize(new Dimension(frame.getSize().width, pos_y));
                                frame.setSize(frame.getSize().width, pos_y);
                                frame.removeMouseListener(this);
                                frame.removeMouseMotionListener(this);
                            } else {
                                frame.setPreferredSize(new Dimension(frame.getSize().width, pos_y));
                                frame.setMinimumSize(new Dimension(frame.getSize().width, pos_y));
                                frame.setSize(frame.getSize().width, pos_y);
                            }
                        } else {
                            frame.setSize(frame.getSize().width, pos_y);
                        }
                    }
                }
            }

            // manage left sight of frame
            int offset1 = frame.getSize().width - pos_x;
            if (permit) {
                if (left) {
                    if (offset_x > 0) {
                        // limitation for decreasing size of frame
                        // not can be less then defined size in method :: 'paintBorder'
                        if (offset1 <= limitForResizableWidth) {
                            offset1 = limitForResizableWidth;
                            frame.setPreferredSize(new Dimension(offset1, frame.getSize().height));
                            frame.setMinimumSize(new Dimension(offset1, frame.getSize().height));
                            frame.setSize(offset1, frame.getSize().height);
                        } else {
                            frame.setPreferredSize(new Dimension(offset1, frame.getSize().height));
                            frame.setMinimumSize(new Dimension(offset1, frame.getSize().height));
                            frame.setSize(offset1, frame.getSize().height);
                            frame.setLocation(frame.getLocation().x + pos_x, frame.getLocation().y);
                        }
                    } else {
                        frame.setSize(frame.getSize().width + Math.abs(pos_x), frame.getSize().height);
                        frame.setLocation(frame.getLocation().x - Math.abs(pos_x), frame.getLocation().y);
                    }
                }
            }

            // manage top side of frame
            int offset2 = frame.getSize().height - pos_y;
            if (permit) {
                if (top) {
                    if (offset_y > 0) {
                        // limitation for decreasing size of frame
                        // not can be less then defined size in method :: 'paintBorder'
                        if (offset2 <= limitForResizableWidth) {
                            offset2 = limitForResizableWidth;
                            frame.setPreferredSize(new Dimension(frame.getSize().width, offset2));
                            frame.setMinimumSize(new Dimension(frame.getSize().width, offset2));
                            frame.setSize(frame.getSize().width, offset2);
                        } else {
                            frame.setPreferredSize(new Dimension(frame.getSize().width, offset2));
                            frame.setMinimumSize(new Dimension(frame.getSize().width, offset2));
                            frame.setSize(frame.getSize().width, offset2);
                            frame.setLocation(frame.getLocation().x, frame.getLocation().y + pos_y);
                        }
                    } else {
                        frame.setSize(frame.getSize().width, frame.getSize().height + Math.abs(pos_y));
                        frame.setLocation(frame.getLocation().x, frame.getLocation().y - Math.abs(pos_y));
                    }
                }
            }


//***************************** Resizing of Corners ***************************************************
//*****************************************************************************************************
            int pos_y1 = me.getPoint().y + 25;
            int pos_x1 = me.getPoint().x + 25;
            // right bottom corner
            int in_w = (int) (me.getPoint().x - rightBottomCorner.getWidth());
            int in_h = (int) (me.getPoint().y - rightBottomCorner.getHeight());
            int in_x = (int) (me.getPoint().x - rightBottomCorner.getX());
            int in_y = (int) (me.getPoint().y - rightBottomCorner.getY());
            // left bottom corner
            int pos_y2 = me.getPoint().y + 25;     //+ 25
            int pos_x2 = me.getPoint().x - 25;     // -25
            int in_wlb = (int) (me.getPoint().x - leftBottomCorner.getWidth());
            int in_hlb = (int) (me.getPoint().y - leftBottomCorner.getHeight());
            int in_xlb = (int) (me.getPoint().x - leftBottomCorner.getX());
            int in_ylb = (int) (me.getPoint().y - leftBottomCorner.getY());

            // Resizing of bottom right Corner
            if (!permit_rb) {
                if (rightbot) {
                    if (in_w < 0 && in_h < 0) {
                        permit = false;
                        frame.setPreferredSize(new Dimension(pos_x1, pos_y1));
                        frame.setMinimumSize(new Dimension(pos_x1, pos_y1));
                        frame.setSize(pos_x1, pos_y1);
                    } else {
                        permit = false;
                        if (pos_y1 < limitForResizableWidth) {
                            pos_y1 = limitForResizableWidth;
//                                System.out.println(" pos_y1 < "   );
                        }
                        if (pos_x1 < limitForResizableWidth) {
                            pos_x1 = limitForResizableWidth;
                            frame.setPreferredSize(new Dimension(pos_x1, pos_y1));
                            frame.setMinimumSize(new Dimension(pos_x1, pos_y1));
                            frame.setSize(pos_x1, pos_y1);
//                         System.out.println(" pos_x1 < "  );
                        } else {
                            frame.setPreferredSize(new Dimension(pos_x1, pos_y1));
                            frame.setMinimumSize(new Dimension(pos_x1, pos_y1));
                            frame.setSize(pos_x1, pos_y1);
//                          System.out.println(" -other "  );
                        }
                    }
                }
            }

            // Resizing of bottom left Corner
            int offset3 = frame.getSize().width - pos_x2;
            if (leftbot) {
                permit = false;
                if (offset_x > 0 && offset_y > 0) {  //  offset_x > 0 && offset_y > 0
                    // limitation for decreasing size of frame
                    // not can be less then defined size in method :: 'paintBorder'
                    if (p.getWidth() < limitForResizableWidth) {
                        offset3 = limitForResizableWidth;

//                         frame.setPreferredSize(new Dimension( offset3  , pos_y2));
//                         frame.setMinimumSize(new Dimension( offset3    , pos_y2));
//                         frame.setSize( offset3   , pos_y2 );
//                         frame.setLocation( frame.getLocation().x + pos_x2,  frame.getLocation().y);
                        permit_rb = true;
                        permit = false;
//                             }
//                          if ( pos_x2 < 0  ) {
//                              offset3  = limitForResizableWidth  ;
//                              frame.setPreferredSize(new Dimension( offset3  , pos_y2));
//                              frame.setMinimumSize(new Dimension( offset3    , pos_y2));
//                             frame.setSize( offset3   , pos_y2 );
//                         }
                        System.out.println(" leftbot :  w < " + pos_y2 + "  " + offset3);
                    } //                         else if (p.getHeight() < limitForResizableWidth ) {
                    ////                                offset3  = limitForResizableWidth ;
                    //                                pos_y2  = limitForResizableWidth ;
                    ////                         frame.setPreferredSize(new Dimension( offset3  , pos_y2));
                    ////                         frame.setMinimumSize(new Dimension( offset3    , pos_y2));
                    //                         frame.setSize( offset3   , pos_y2 );
                    ////                         frame.setLocation( frame.getLocation().x +pos_x2,  frame.getLocation().y);
                    //                          System.out.println(" leftbot :  h < "  );
                    //                          }
                    else {
                        frame.setPreferredSize(new Dimension(offset3, pos_y2));
                        frame.setMinimumSize(new Dimension(offset3, pos_y2));
                        frame.setSize(offset3, pos_y2);
                        frame.setLocation(frame.getLocation().x + pos_x2, frame.getLocation().y);
//                        System.out.println(" out ");
                    }

                } else {
                    permit = false;
                    frame.setPreferredSize(new Dimension(frame.getSize().width + Math.abs(pos_x2), pos_y2));
                    frame.setMinimumSize(new Dimension(frame.getSize().width + Math.abs(pos_x2), pos_y2));
                    frame.setSize(frame.getSize().width + Math.abs(pos_x2), pos_y2);
                    frame.setLocation(frame.getLocation().x - Math.abs(pos_x2), frame.getLocation().y);
                    System.out.println(" -leftbot :  " + pos_x2 + "  ");
                }
            }

//                      p.repaint();
        }
        boolean test = false;
        boolean permit_rb = false;

        @Override
        public void mouseMoved(MouseEvent e) {
            if (permit) {
                mouseClicked(e);
            }
        }

        public JWindow getFrame(Container target) {
            if (target instanceof JWindow) {
                return (JWindow) target;
            }
            return getFrame(target.getParent());
        }

        Point getScreenLocation(MouseEvent e) {
            Point cursor = e.getPoint();
//    System.out.println("e.getPoint() " + e.getPoint() );
            Point target_location = this.p.getLocationOnScreen();
//    System.out.println("target.getLocationOnScreen()  " +  this.target.getLocationOnScreen());
            return new Point((int) (target_location.getX() + cursor.getX()),
                    (int) (target_location.getY() + cursor.getY()));
        }
    }

// ********************************************************************************************************************
    static class DnDMouse implements MouseListener, MouseMotionListener {

        JComponent target;
        Point start_drag;
        Point start_loc;
        Shape shape;

        DnDMouse(JComponent tar) {  // JComponent tar
            this.target = tar;
            target.addMouseListener(this);
            target.addMouseMotionListener(this);
            // this it need for avoiding null reference when first start resizable and DnD
            // also allowed . If it is have not done then will be appear errors
            start_drag = target.getLocation();
            start_loc = target.getLocation();
        }

        public JWindow getFrame(Container target) {
            if (target instanceof JWindow) {
                return (JWindow) target;
            } 
            return getFrame(target.getParent());
        }

        public JPanel getPanel(Container target) {
            if (target instanceof JWindow) {
                return (JPanel) target;
            }
            return getPanel(target.getParent());
        }

        Point getScreenLocation(MouseEvent e) {
            Point cursor = e.getPoint();
//    System.out.println("e.getPoint() " + e.getPoint() );
            Point target_location = this.target.getLocationOnScreen();
//    System.out.println("target.getLocationOnScreen()  " +  this.target.getLocationOnScreen());
            return new Point((int) (target_location.getX() + cursor.getX()),
                    (int) (target_location.getY() + cursor.getY()));
        }

        public void mouseClicked(MouseEvent e) { //mousePressed(e);

        }

        public void mouseEntered(MouseEvent e) {  }

        public void mouseExited(MouseEvent e) {  }

        public void mousePressed(MouseEvent e) {
            if (dragrectangle.contains(e.getPoint())) {
                dragareaFocused = true;
                target.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                this.start_drag = this.getScreenLocation(e);   //  getScreenLocation(e);
                if (!blockMouseListeners) {
                    this.start_loc = getFrame(target).getLocation();
                } else {
//             target = savePanel;
                    this.start_loc = savePanel.getLocationOnScreen();
                    this.start_drag = this.getScreenLocation(e);

                }
//      System.out.println("this.start_drag  " +  start_drag );
//      System.out.println("this.start_loc   " +  start_loc );
                target.repaint();
            } else {
                dragareaFocused = false;
            }
        }

        public void mouseReleased(MouseEvent e) {
            dragareaFocused = false;
            target.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            target.repaint();
            blockMouseListeners = false;
            System.out.println("released");
        }
        JWindow frame;

        public void mouseDragged(MouseEvent e) {
            if (dragrectangle.contains(e.getPoint())) {
                dragareaFocused = true;
                target.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                Point current = this.getScreenLocation(e);
                Point offset = new Point((int) current.getX() - (int) start_drag.getX(),
                        (int) current.getY() - (int) start_drag.getY());

                if (!blockMouseListeners) {
                    frame = this.getFrame(target);
                    Point new_location = new Point((int) (this.start_loc.getX() + offset.getX()),
                            (int) (this.start_loc.getY() + offset.getY()));
                    frame.setLocation(new_location);
                    System.out.println("!!_block");
                } else {
                    Point new_location = new Point((int) (this.start_loc.getX() + offset.getX()),
                            (int) (this.start_loc.getY() + offset.getY()));
                    target = savePanel;
                    frame = win;
//         if(frame.getContentPane() != null )
                    frame.setContentPane(target);
                    frame.setPreferredSize(target.getPreferredSize());
                    frame.setMinimumSize(target.getMinimumSize());
//         frame.addMouseListener(this);
//         frame.addMouseMotionListener(this);
                    frame.setVisible(true);
                    frame.setLocation(new_location);
//         frame.validate();
//         frame.repaint();

                    fr.remove(target);
                    fr.repaint();
                    fr.validate();
//         blockMouseListeners = false;


                    System.out.println("drag_block");
                }
                System.out.println("dragrectangle.contains");
                Point new_location = new Point((int) (this.start_loc.getX() + offset.getX()),
                        (int) (this.start_loc.getY() + offset.getY()));
                frame = this.getFrame(target);
            } else {
                dragareaFocused = false;
            }
//            System.out.println("drag_out");
//         Point current = this.getScreenLocation(e);
//        Point offset = new Point((int) current.getX() - (int) start_drag.getX(),
//                                 (int) current.getY() - (int) start_drag.getY());
//        Point new_location = new Point( (int) (this.start_loc.getX() + offset.getX()),
//                                         (int) (this.start_loc.getY() + offset.getY()));
//       win.setLocation(new_location);
        }

        public void mouseMoved(MouseEvent e) {  //mouseDragged(e);
//      if( blockMouseListeners ) {
//          mouseDragged(e);
//            System.out.println("mouseMoved");
//      }
        }
    }
}
