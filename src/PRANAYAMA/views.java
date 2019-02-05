package PRANAYAMA;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class views {

    JPanel p1, p2, p3, p4, p5, d1, d2, d3;
    JLabel label_Inhalation, label_Exhalation,
            label_breathhold_after_inhalation, label_breathhold_after_exhalation;
    JLabel divider1, divider2, divider3;
    JLabel label_numCycles;

    JPanel common, labelPanel;

    graphComponents graphCompLine;

    int w, h;
    int fontSize;
    Font font;
    Font borderFont;
    // name of 'common' JPanel
    String title;

    private GridBagConstraints c;
    // just for preview Panel in --> viewsWindow.java class
    boolean isPreviewMode;
    JPanel previewPanel;
    int graphCompType;
    // 
    Color color1, color2, color3, color4;
    Color colorForeground, colorBackground;
    int selectedIndex;
    boolean isOpaque = true;

    PRANAYAMA pranaMain;

    views(PRANAYAMA prana) {
        this.pranaMain = prana;
        isPreviewMode = false;
        
        setBorderFont(null);

        //  graphCompType = 2;
        fontSize = 70;
        w = 0;
        h = 0;

        //  color1 = color2 = color3 = color4 = Color.BLUE;
        choiceView();
    }

    // for NUMBER VIEW
    views(PRANAYAMA prana, boolean isPreview, JPanel preview) {
        this.pranaMain = prana;
        this.isPreviewMode = isPreview;
        this.previewPanel = preview;
        
        setBorderFont(null);

        w = 0;
        h = 0;

        // set default colors for labels
        if (color1 == null || color2 == null || color3 == null || color4 == null) {
            color1 = Color.BLUE;
            color2 = Color.BLUE;
            color3 = Color.BLUE;
            color4 = Color.BLUE;
        }

        choiceNumberView();
    }

    // type --> 1,2,3 ( for HORIZONTAL DIAGRAMMA, VERTICAL DIAGRAMMA, CIRCLE DIAGRAMMA)
    views(PRANAYAMA prana, boolean isPreview, JPanel preview, int type, int selectedIndex) { // PRANAYAMA prana,
//        this.pranaMain = prana;
        this.isPreviewMode = isPreview;
        this.previewPanel = preview;
        this.graphCompType = type;
        this.selectedIndex = selectedIndex;
        
        setBorderFont(null);

        w = 0;
        h = 0;

        choiceGraphView();
    }

    public void choiceView() {
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.NUMBER_VIEW) {
            // set default colors for labels
            if (color1 == null || color2 == null || color3 == null || color4 == null) {
                color1 = Color.BLUE;
                color2 = Color.BLUE;
                color3 = Color.BLUE;
                color4 = Color.BLUE;
            }
            choiceNumberView();
        }
        // show HORIZONTAL VIEW in  preview  panel
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.HORIZONTAL_VIEW) {
            graphCompType = 1;
            choiceGraphView();
        }
        // show VERTICAL VIEW in  preview  panel
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.VERTICAL_VIEW) {
            graphCompType = 2;
            choiceGraphView();
        }
        // show CIRCLE VIEW in  preview  panel
        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.CIRCLE_VIEW) {
            graphCompType = 3;
            choiceGraphView();
        }
    }

    public void choiceNumberView() {
        // " Empty ", " inhalation : exhalation "
        if (pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex() == 0
                || pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex() == 1) {
            init1();
            // " inhalation : hold : exhalation "    
        } else if (pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex() == 2) {
            init2();
            //  " inhalation : hold : exhalation : hold "    
        } else if (pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex() == 3) {
            init3();
        }
    }

    public void choiceGraphView() {

        // System.out.println("choiceGraphView: " + pranaMain.mainMenu.Breath.settingsBreath.schema_breathing);
        if (isPreviewMode == false) {
            // " Empty ", " inhalation : exhalation "
            if (pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex() == 0
                    || pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex() == 1) {
                initGraph(1);
                // " inhalation : hold : exhalation "    
            } else if (pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex() == 2) {
                initGraph(2);
                //  " inhalation : hold : exhalation : hold "    
            } else if (pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex() == 3) {
                initGraph(3);
            }
        } else {
            // " Empty ", " inhalation : exhalation "
            if (selectedIndex == 0 || selectedIndex == 1) {
                initGraph(1);
                // " inhalation : hold : exhalation "    
            } else if (selectedIndex == 2) {
                initGraph(2);
                //  " inhalation : hold : exhalation : hold "    
            } else if (selectedIndex == 3) {
                initGraph(3);
            }
        }
    }

    //index -> 0 - " Empty ",
    //index -> 1 - " inhalation : exhalation "
    //index -> 2 - " inhalation : hold : exhalation "
    //index -> 3 - " inhalation : hold : exhalation : hold"
    private void initGraph(int index) {

        // Preview mode is OFF
        if (isPreviewMode == false) {
            fontSize = 50;
        } // Preview mode is ON
        else {
            fontSize = 25;
        }

        TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5,
                5, 5, Color.pink), getNameBorder(), TitledBorder.LEFT, TitledBorder.TOP,
                getBorderFont(), Color.blue);

        graphCompLine = new graphComponents(graphCompType, index);

        if (color1 != null) {
            graphCompLine.setColor1(getColor1());
        }

        if (color2 != null) {
            graphCompLine.setColor2(getColor2());
        }

        if (color3 != null) {
            graphCompLine.setColor3(getColor3());
        }

        if (color4 != null) {
            graphCompLine.setColor4(getColor4());
        }

        if (colorForeground != null) {
            graphCompLine.setColorForeground(getColorForeground());
        }

        if (colorBackground != null) {
            pranaMain.basic.setBackground(colorBackground);
        }
        // can remove background for VERTICAL and CIRCLE graph components
        graphCompLine.setBackgroundGraphComp(getViewOpaque());

        // set width and height 'common' JPanel according index
        switch (graphCompType) {
            default:
                break;
            case 1:
                initGraphSizeSettings_viewHOR(index);
                break;
            case 2:
                initGraphSizeSettings_viewVER(index);
                break;
            case 3:
                initGraphSizeSettings_viewCIRCLE(index);
                break;
        }

        //                     name    font          color1  color2 border isOpaque w  h
        common = createPanel("common", getMainFont(), null, null, tb, false, w, h);
        common.setLayout(new GridBagLayout());

        Border b = BorderFactory.createBevelBorder(BevelBorder.LOWERED);

        label_numCycles = createLabel("label_numCycles", "0", fontSizeLimitation(fontSize), Color.black, Color.red, b, getViewOpaque());
        // 2,1: 170, 255
        // 2,2: 165, 255
        // 2,3: 223, 255
        //graphCompLine.setSize(240, 255); 

        c = new GridBagConstraints();

        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;    // Column 0
        c.gridy = 1;    // Row 0
//        c.fill = GridBagConstraints.WEST;
        c.anchor = GridBagConstraints.NORTH;
//        c.ipadx = 10;   // Increases component width by 10 pixels
        c.weightx = 0.0;
        c.weighty = 1.0;
        c.gridwidth = 1;   // Span across 2 column
        c.gridheight = 1;
        common.add(graphCompLine, c);

        c.gridx = 1;    // Column 2
        c.gridy = 0;    // Row 1
//        c.fill = GridBagConstraints.WEST;
        c.weightx = 0.0;
        c.weighty = 1.0;
        c.gridwidth = 3;   // Span across 2 column
        c.gridheight = 1;
        c.anchor = GridBagConstraints.SOUTH;
        common.add(label_numCycles, c);

        if (isPreviewMode == false) {
            pranaMain.basic.add(common);

            centerPanel(pranaMain.basic, common);

            pranaMain.basic.revalidate();
            pranaMain.basic.repaint();
        } else {
            previewPanel.add(common);

            centerPanel(previewPanel, common);

            previewPanel.revalidate();
            previewPanel.repaint();
        }
    }

// =========================== view CIRCLE ============================================    
    private void initGraphSizeSettings_viewCIRCLE(int index) {
        int counterHOR = 0;
        switch (index) {
            default:
                break;
            case 0:
            case 1:
            case 2:
            case 3:
                // Preview mode is OFF
                if (isPreviewMode == false) {
                    // class 'PlusMinusAction'
                    if (pranaMain.pma == null) {
                        w = 260;
                        h = 290;
                    } else {
                        counterHOR = pranaMain.pma.forViewCIRCLE.getCounterCIRCLE();
                        changeGraphCompSize_viewCIRCLE(260, 290, pranaMain.pma.forViewCIRCLE.setDefaultPanelSize_forGraphCIRCLE(counterHOR, common));
                    }
                } // Preview mode is ON
                else {
                    w = 200;
                    h = 230;
                    graphCompLine.setGraphWidth(140);
                    graphCompLine.setGraphHeight(140);
                }
                break;
        }
    }

    //w1 - width JPanel 'common'
    //h1 - height JPanel 'common'
    //arr1 - contains Width and Height JPanel 'common' 
    private void changeGraphCompSize_viewCIRCLE(int w1, int h1, int[] arr1) {
        // class 'PlusMinusAction'
        int arr[] = arr1;
        // width of 'common' panel 
        if (pranaMain.pma.forViewCIRCLE.ww == 0) {
            w = w1;
        } else {
            w = arr[0];
        }
        // height of 'common' panel
        if (pranaMain.pma.forViewCIRCLE.hh == 0) {
            h = h1;
        } else {
            h = arr[1];
        }
    }

//================================= view HORIZONTAL =====================================================    
    private void initGraphSizeSettings_viewHOR(int index) {
        int counterHOR = 0;
        switch (index) {
            default:
                break;
            case 0:
            case 1:
            case 2:
            case 3:
                // Preview mode is OFF
                if (isPreviewMode == false) {
                    // class 'PlusMinusAction'
                    if (pranaMain.pma == null) {
                        w = 360;
                        h = 270;
                    } else {
                        counterHOR = pranaMain.pma.forViewHOR.getCounterHOR();
                        changeGraphCompSize_viewHOR(360, 270, pranaMain.pma.forViewHOR.setDefaultPanelSize_forGraphHOR(counterHOR, common));
                    }
                } // Preview mode is ON
                else {
                    w = 232;
                    h = 190;
                    graphCompLine.setGraphWidth(172);
                    graphCompLine.setGraphHeight(90);
                }
                break;
        }
    }

    //w1 - width JPanel 'common'
    //h1 - height JPanel 'common'
    //arr1 - contains Width and Height JPanel 'common' 
    private void changeGraphCompSize_viewHOR(int w1, int h1, int[] arr1) {
        // class 'PlusMinusAction'
        int arr[] = arr1;
        // width of 'common' panel 
        if (pranaMain.pma.forViewHOR.ww == 0) {
            w = w1;
        } else {
            w = arr[0];
        }
        // height of 'common' panel
        if (pranaMain.pma.forViewHOR.hh == 0) {
            h = h1;
        } else {
            h = arr[1];
        }
    }

// ============================ view VERTICAL =============================================    
    private void initGraphSizeSettings_viewVER(int index) {
        int counterVER = 0;
        switch (index) {
            default:
                break;
            case 0:
            case 1:
                // Preview mode is OFF
                if (isPreviewMode == false) {
                    // class 'PlusMinusAction'
                    if (pranaMain.pma == null) {
                        w = 190;
                        h = 350;
                    } else {
                        counterVER = pranaMain.pma.forViewVER.getCounterVER();
                        changeGraphCompSize_viewVER(190, 350, pranaMain.pma.forViewVER.setDefaultPanelSize1_forGraphVER(counterVER, common));
                    }
                } // Preview mode is ON
                else {
                    w = 126;
                    h = 245;
                    graphCompLine.setGraphWidth(64);
                    graphCompLine.setGraphHeight(210);
                }
                break;
            case 2:
                // Preview mode is OFF
                if (isPreviewMode == false) {
                    // class 'PlusMinusAction'
                    if (pranaMain.pma == null) {
                        w = 240;
                        h = 360;
                    } else {
                        counterVER = pranaMain.pma.forViewVER.getCounterVER();
                        changeGraphCompSize_viewVER(240, 360, pranaMain.pma.forViewVER.setDefaultPanelSize2_forGraphVER(counterVER, common));
                    }
                } // Preview mode is ON
                else {
                    w = 166;
                    h = 245;
                    graphCompLine.setGraphWidth(106);
                    graphCompLine.setGraphHeight(210);
                }
                break;
            case 3:
                // Preview mode is OFF
                if (isPreviewMode == false) {
                    // class 'PlusMinusAction'
                    if (pranaMain.pma == null) {
                        w = 300;
                        h = 360;
                    } else {
                        counterVER = pranaMain.pma.forViewVER.getCounterVER();
                        changeGraphCompSize_viewVER(300, 360, pranaMain.pma.forViewVER.setDefaultPanelSize3_forGraphVER(counterVER, common));
                    }
                } // Preview mode is ON
                else {
                    w = 236;
                    h = 245;
                    graphCompLine.setGraphWidth(167);
                    graphCompLine.setGraphHeight(210);
                }
                break;
        }
    }

    //w1 - width JPanel 'common'
    //h1 - height JPanel 'common'
    //arr1 - contains Width and Height JPanel 'common' 
    private void changeGraphCompSize_viewVER(int w1, int h1, int[] arr1) {
        // class 'PlusMinusAction'
        int arr[] = arr1;
        // width of 'common' panel 
        if (pranaMain.pma.forViewVER.ww == 0) {
            w = w1;
        } else {
            w = arr[0];
        }
        // height of 'common' panel
        if (pranaMain.pma.forViewVER.hh == 0) {
            h = h1;
        } else {
            h = arr[1];
        }
    }

    // " Empty ", " inhalation : exhalation "
    private void init1() {

        // Preview mode is OFF
        if (isPreviewMode == false) {
            fontSize = 70;
        } // Preview mode is ON
        else {
            fontSize = 25;
        }

//        System.out.println(" bef init1: " + w + "  " + h + " ");
//        
//        System.out.println(" bef init1 font: " + getMainFont().toString());
        TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5,
                5, 5, Color.pink), getNameBorder(), TitledBorder.LEFT, TitledBorder.TOP,
                getBorderFont(), Color.blue);
        // Preview mode is OFF
        if (isPreviewMode == false) {
            // class 'PlusMinusAction'
            if (pranaMain.pma == null) {
                w = 260;
                h = 205;
            } else {
                int arr[] = pranaMain.pma.forView1.setDefaultPanelSize1(fontSize, common);
                // width of 'common' panel 
                if (pranaMain.pma.forView1.ww == 0) {
                    w = 260;
                } else {
                    w = arr[0];
                }
                // height of 'common' panel
                if (pranaMain.pma.forView1.hh == 0) {
                    h = 205;
                } else {
                    h = arr[1];
                }
                fontSize = arr[2];
            }
        } // Preview mode is ON
        else {
            w = 292;
            h = 225;
            fontSize = 83;
        }
        //                     name    font  color1 color2 border isOpaque w  h
        common = createPanel("common", getMainFont(), null, null, tb, false, w, h);  // 260 205
        common.setLayout(new GridBagLayout());

        Border b = BorderFactory.createBevelBorder(BevelBorder.LOWERED);

        // set default colors for labels
        if (color1 == null || color3 == null) {
            color1 = Color.BLUE;
            color3 = Color.BLUE;
        }
        // set FOREGROUND and BACKGROUND
        if (colorForeground == null) {
            colorForeground = Color.RED;
        }
        if (colorBackground != null) {
            pranaMain.basic.setBackground(colorBackground);
        }

        label_Inhalation = createLabel("label_Inhalation", "00", getMainFont(), getColor1(), getColorForeground(), b, getViewOpaque());

        label_Exhalation = createLabel("label_Exhalation", "00", getMainFont(), getColor3(), getColorForeground(), b, getViewOpaque());

        label_numCycles = createLabel("label_numCycles", "0", fontSizeLimitation(fontSize), Color.black, getColorForeground(), b, getViewOpaque());

        Border cborder = setComponentPadding(-5, 1, 25, 1);
        divider1 = createLabel("divider", ":", getMainFont(), Color.black, getColorForeground(), cborder, false);

        c = new GridBagConstraints();

        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;    // Column 0
        c.gridy = 1;    // Row 0
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.NORTH;
//        c.ipadx = 10;   // Increases component width by 10 pixels
        c.weightx = 0.1;
        c.weighty = 1.0;
        c.gridwidth = 1;   // Span across 2 column
        c.gridheight = 1;
        common.add(label_Inhalation, c);

        c.gridx = 1;    // Column 1
        c.gridy = 1;    // Row 1
        common.add(divider1, c);

        c.gridx = 2;    // Column 2
        c.gridy = 1;    // Row 1
        common.add(label_Exhalation, c);

        c.gridx = 7;    // Column 2
        c.gridy = 0;    // Row 1
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.0;
        c.weighty = 1.0;
        c.gridwidth = 3;   // Span across 2 column
        c.gridheight = 1;
        c.anchor = GridBagConstraints.SOUTH;
        common.add(label_numCycles, c);

        // Preview mode is OFF
        if (isPreviewMode == false) {
            pranaMain.basic.add(common);

            centerPanel(pranaMain.basic, common);

            pranaMain.basic.revalidate();
            pranaMain.basic.repaint();
        } // Preview mode is ON
        else {
            previewPanel.add(common);

            centerPanel(previewPanel, common);

            previewPanel.revalidate();
            previewPanel.repaint();
        }

        System.out.println(" aft init1: " + w + "  " + h + " ");
    }

    // " inhalation : hold : exhalation "
    private void init2() {

        // Preview mode is OFF
        if (isPreviewMode == false) {
            fontSize = 70;
        } // Preview mode is ON
        else {
            fontSize = 25;
        }

        TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5,
                5, 5, Color.pink), getNameBorder(), TitledBorder.LEFT, TitledBorder.TOP,
                getBorderFont(), Color.blue);
        // Preview mode is OFF
        if (isPreviewMode == false) {
            // class 'PlusMinusAction'
            if (pranaMain.pma == null) {
                w = 345;
                h = 205;
            } else {
                int arr[] = pranaMain.pma.forView1.setDefaultPanelSize2(fontSize, common);
                // width of 'common' panel 
                if (pranaMain.pma.forView1.ww == 0) {
                    w = 345;
                } else {
                    w = arr[0];
//                w = pranaMain.pma.ww;
                }
                // height of 'common' panel
                if (pranaMain.pma.forView1.hh == 0) {
                    h = 205;
                } else {
                    h = arr[1];
//                h = pranaMain.pma.hh;
                }
                fontSize = arr[2];
            }
        } // Preview mode is ON
        else {
            w = 298;
            h = 180;
            fontSize = 57;
        }
        System.out.println(" aft init2: " + w + "  " + h + "  " + fontSize);
        //                     name    font  color1 color2 border isOpaque w  h
        common = createPanel("common", getMainFont(), null, null, tb, false, w, h);
        common.setLayout(new GridBagLayout());

        System.out.println(" bef init2: " + w + "  " + h);

        Border b = BorderFactory.createBevelBorder(BevelBorder.LOWERED);

        // set default colors for labels
        if (color1 == null || color2 == null || color3 == null) {
            color1 = Color.BLUE;
            color2 = Color.BLUE;
            color3 = Color.BLUE;
        }
        // set FOREGROUND and BACKGROUND
        if (colorForeground == null) {
            colorForeground = Color.RED;
        }
        if (colorBackground != null) {
            pranaMain.basic.setBackground(colorBackground);
        }

        label_Inhalation = createLabel("label_Inhalation", "00", getMainFont(), getColor1(), getColorForeground(), b, getViewOpaque());

        label_breathhold_after_inhalation = createLabel("label_breathhold_after_inhalation",
                "00", getMainFont(), getColor2(), getColorForeground(), b, getViewOpaque());

        label_Exhalation = createLabel("label_Exhalation", "00", getMainFont(), getColor3(), getColorForeground(), b, getViewOpaque());

        label_numCycles = createLabel("label_numCycles", "0", fontSizeLimitation(fontSize), Color.black, getColorForeground(), b, getViewOpaque());

        System.out.println("=AFTER=");
        Border cborder = setComponentPadding(-5, 1, 25, 1);
        divider1 = createLabel("divider", ":", getMainFont(), Color.black, getColorForeground(), cborder, false);
        divider2 = createLabel("divider", ":", getMainFont(), Color.black, getColorForeground(), cborder, false);

        c = new GridBagConstraints();

        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;    // Column 0
        c.gridy = 1;    // Row 0
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.NORTH;
//        c.ipadx = 10;   // Increases component width by 10 pixels
        c.weightx = 0.1;
        c.weighty = 1.0;
        c.gridwidth = 1;   // Span across 2 column
        c.gridheight = 1;
        common.add(label_Inhalation, c);

        c.gridx = 1;    // Column 1
        c.gridy = 1;    // Row 1
        common.add(divider1, c);

        c.gridx = 2;    // Column 2
        c.gridy = 1;    // Row 1
        common.add(label_breathhold_after_inhalation, c);

        c.gridx = 3;    // Column 3
        c.gridy = 1;    // Row 1
        common.add(divider2, c);

        c.gridx = 4;    // Column 4
        c.gridy = 1;    // Row 1
        common.add(label_Exhalation, c);

        c.gridx = 5;    // Column 2
        c.gridy = 0;    // Row 1
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.0;
        c.weighty = 1.0;
        c.gridwidth = 7;   // Span across 2 column
        c.gridheight = 1;
        c.anchor = GridBagConstraints.SOUTH;
        common.add(label_numCycles, c);

        // Preview mode is OFF
        if (isPreviewMode == false) {
            pranaMain.basic.add(common);

            centerPanel(pranaMain.basic, common);

            pranaMain.basic.revalidate();
            pranaMain.basic.repaint();
        } // Preview mode is ON
        else {
            previewPanel.add(common);

            centerPanel(previewPanel, common);

            previewPanel.revalidate();
            previewPanel.repaint();
        }
    }

    // " inhalation : hold : exhalation : hold " 
    private void init3() {

        // Preview mode is OFF
        if (isPreviewMode == false) {
            fontSize = 70;
        } // Preview mode is ON
        else {
            fontSize = 25;
        }

        TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5,
                5, 5, Color.pink), getNameBorder(), TitledBorder.LEFT, TitledBorder.TOP,
                getBorderFont(), Color.blue);
        // Preview mode is OFF
        if (isPreviewMode == false) {
            // class 'PlusMinusAction'
            if (pranaMain.pma == null) {
                w = 500;
                h = 215;
            } else {
                int arr[] = pranaMain.pma.forView1.setDefaultPanelSize3(fontSize, common);
                // width of 'common' panel 
                if (pranaMain.pma.forView1.ww == 0) {
                    w = 500;
                } else {
//                w = pranaMain.pma.ww;
                    w = arr[0];
                }
                // height of 'common' panel
                if (pranaMain.pma.forView1.hh == 0) {
                    h = 215;
                } else {
//                h = pranaMain.pma.hh;
                    h = arr[1];
                }
                fontSize = arr[2];
            }
        } // Preview mode is ON
        else {
            w = 304;
            h = 125;
            fontSize = 31;
        }

        System.out.println(" aft init2: " + w + "  " + h + "  " + fontSize);

        //                     name    font  color1 color2 border isOpaque w  h
        common = createPanel("common", getMainFont(), null, null, tb, false, w, h);
//        int va = (common.getSize().height - p1.getSize().height - 10) / 2;
        // common.setLayout(new FlowLayout(FlowLayout.CENTER, 0, va));
        common.setLayout(new GridBagLayout());

        Border b = BorderFactory.createBevelBorder(BevelBorder.LOWERED);

        // set default colors for labels
        if (color1 == null || color2 == null || color3 == null || color4 == null) {
            color1 = Color.BLUE;
            color2 = Color.BLUE;
            color3 = Color.BLUE;
            color4 = Color.BLUE;
        }
        // set FOREGROUND and BACKGROUND
        if (colorForeground == null) {
            colorForeground = Color.RED;
        }
        if (colorBackground != null) {
            pranaMain.basic.setBackground(colorBackground);
        }

        label_Inhalation = createLabel("label_Inhalation", "00", getMainFont(), getColor1(), getColorForeground(), b, getViewOpaque());

        label_breathhold_after_inhalation = createLabel("label_breathhold_after_inhalation",
                "00", font, getColor2(), getColorForeground(), b, getViewOpaque());

        label_Exhalation = createLabel("label_Exhalation", "00", getMainFont(), getColor3(), getColorForeground(), b, getViewOpaque());

        label_breathhold_after_exhalation = createLabel("label_breathhold_after_exhalation",
                "00", getMainFont(), getColor4(), getColorForeground(), b, getViewOpaque());

        label_numCycles = createLabel("label_numCycles", "0", fontSizeLimitation(fontSize), Color.black, getColorForeground(), b, getViewOpaque());

        Border cborder = setComponentPadding(-5, 1, 25, 1);
        divider1 = createLabel("divider", ":", getMainFont(), Color.black, getColorForeground(), cborder, false);

        divider2 = createLabel("divider", ":", getMainFont(), Color.black, getColorForeground(), cborder, false);
        divider3 = createLabel("divider", ":", getMainFont(), Color.black, getColorForeground(), cborder, false);

        c = new GridBagConstraints();

        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;    // Column 0
        c.gridy = 1;    // Row 0
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.NORTH;
//        c.ipadx = 10;   // Increases component width by 10 pixels
        c.weightx = 0.1;
        c.weighty = 1.0;
        c.gridwidth = 1;   // Span across 2 column
        c.gridheight = 1;
        common.add(label_Inhalation, c);

        c.gridx = 1;    // Column 1
        c.gridy = 1;    // Row 1
        common.add(divider1, c);

        c.gridx = 2;    // Column 2
        c.gridy = 1;    // Row 1
        common.add(label_breathhold_after_inhalation, c);

        c.gridx = 3;    // Column 3
        c.gridy = 1;    // Row 1
        common.add(divider2, c);

        c.gridx = 4;    // Column 4
        c.gridy = 1;    // Row 1
        common.add(label_Exhalation, c);

        c.gridx = 5;    // Column 5
        c.gridy = 1;    // Row 1
        common.add(divider3, c);

        c.gridx = 6;    // Column 6
        c.gridy = 1;    // Row 1
        common.add(label_breathhold_after_exhalation, c);

        c.gridx = 7;    // Column 2
        c.gridy = 0;    // Row 1
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.0;
        c.weighty = 1.0;
        c.gridwidth = 7;   // Span across 2 column
        c.gridheight = 1;
        c.anchor = GridBagConstraints.SOUTH;
//        c.weighty = 150;
        common.add(label_numCycles, c);

        // Preview mode is OFF
        if (isPreviewMode == false) {
            pranaMain.basic.add(common);

            centerPanel(pranaMain.basic, common);

            pranaMain.basic.revalidate();
            pranaMain.basic.repaint();
        } // Preview mode is ON
        else {
            previewPanel.add(common);

            centerPanel(previewPanel, common);

            previewPanel.revalidate();
            previewPanel.repaint();
        }
    }

    public void removeComponents(JPanel common) {

        if (common != null) {
            Component[] components = common.getComponents();

            for (Component component : components) {
                if (component instanceof JLabel) {
                    common.remove(component);
                }
                if (component instanceof JPanel) {
                    if (component.getName() == "common") {
                        common.remove(component);
                    }
                }
            }
        }
    }

    private Font fontSizeLimitation(int size) {
        if (size >= 70) {
            font = new Font("Book Antiqua", Font.PLAIN, 70);
        } else {
            font = new Font("Book Antiqua", Font.PLAIN, getFontSize());
        }
        return font;
    }

    private void resetLabels() {
        label_Inhalation.setText("00");
        label_Exhalation.setText("00");
        label_breathhold_after_inhalation.setText("00");
        label_breathhold_after_exhalation.setText("00");
        label_numCycles.setText("0");
    }

    public JLabel createLabel(String name, String count, Font font,
            Color color1, Color color2, Border border, boolean isOpaque) {
        JLabel label = new JLabel(count);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setVerticalAlignment(JLabel.TOP);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setName(name);
        label.setFont(font);
        label.setBackground(color1);
        label.setForeground(color2);
        label.setBorder(border);
        label.setOpaque(isOpaque);
        // label.setBorder(BorderFactory.createEmptyBorder(-10 /*top*/, 0, 0, 0));
        return label;
    }

    public JPanel createPanel(String name, Font font,
            Color color1, Color color2, Border border, boolean isOpaque,
            int w, int h) {
        JPanel p = new JPanel();
        p.setName(name);
        p.setFont(font);
        p.setBackground(color1);
        p.setForeground(color2);
        p.setBorder(border);
        p.setOpaque(isOpaque);
        p.setSize(w, h);
        p.setPreferredSize(new Dimension(w, h));
        p.setMinimumSize(new Dimension(w, h));
        return p;
    }

    protected void centerPanel(JPanel outside, JPanel inside) {

        int ow = outside.getSize().width;
        int oh = outside.getSize().height;
        //  System.out.println(ow + "  " + oh);

        int iw = inside.getSize().width;
        int ih = inside.getSize().height;

        int x = (ow - iw) / 2;
        int y = (oh - ih) / 2;

        inside.setLocation(x, y);
    }

    public void setNameBorder(String nameBorder) {
        this.title = nameBorder;
    }

    public String getNameBorder() {
        return title;
    }

    public void setFontSize(int size) {
        this.fontSize = size;
    }

    public int getFontSize() {
        return fontSize;
    }

    public Font getMainFont() {
        font = new Font("Book Antiqua", Font.PLAIN, getFontSize());
        return font;
    }

    public void setBorderFont(Font font) {
        if (font == null) {
            borderFont = new Font("Book Antiqua", Font.PLAIN, 16);
        } else {
            borderFont = font;
            borderFont = borderFont.deriveFont(16F);
        }
    }

    public Font getBorderFont() {
        return borderFont;
    }

    public Border setComponentPadding(int top, int left, int bottom, int right) {
        Border border = null;
        Border margin = new EmptyBorder(top, left, bottom, right);
        Border cborder = new CompoundBorder(border, margin);
        return cborder;
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

    public void setColorForeground(Color color) {
        this.colorForeground = color;
    }

    public Color getColorForeground() {
        return colorForeground;
    }

    public void setColorBackground(Color color) {
        this.colorBackground = color;
    }

    public Color getColorBackground() {
        return colorBackground;
    }
    
    protected void setViewOpaque(boolean isOpaq) {
        this.isOpaque = isOpaq;
    }
    
     protected boolean getViewOpaque() {
        return isOpaque;
    }
}
