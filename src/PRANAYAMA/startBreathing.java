package PRANAYAMA;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class startBreathing extends MouseAdapter {

//        pranaMain.mainMenu.Breath.settingsBreath 
    breathingRun breathing_run;

    PRANAYAMA pranaMain;

    startBreathing(PRANAYAMA prana) {
        this.pranaMain = prana;

    }

    private int parseMyInt(String text) {
        int val;

        if (text.equals(" #")) {
            val = 0;
            System.out.println("1:" + val);
            return val;
        } else if (text.equals("")) {
            val = 0;
            System.out.println("2:" + val);
            return val;
        } else {
            val = Integer.parseInt(text);
            System.out.println("3:" + val);
            return val;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        // stop Thread !!
//            if(breathing_run != null)
//                 breathing_run.stopThread();
        System.out.println("ma: startBreathing !!!");
        pranaMain.mainMenu.Breath.settingsBreath.settings.setWindowVisibility(false);

        int combobox_index = pranaMain.mainMenu.Breath.settingsBreath.schema_breathing.getSelectedIndex();
        // save index of comboBox
        pranaMain.mainMenu.Breath.settingsBreath.approvedIndex = combobox_index;
        int v1 = parseMyInt(pranaMain.mainMenu.Breath.settingsBreath.field_inhalation.getText());
        int v2 = parseMyInt(pranaMain.mainMenu.Breath.settingsBreath.field_exhalation.getText());
        int v3 = parseMyInt(pranaMain.mainMenu.Breath.settingsBreath.field_breathhold_after_inhalation.getText());
        int v4 = parseMyInt(pranaMain.mainMenu.Breath.settingsBreath.field_breathhold_after_exhalation.getText());
        Integer numCycles = (Integer) pranaMain.mainMenu.Breath.settingsBreath.spinner_cycles.getValue();

        // prepare 'common' JPanel of view class to new components 
        pranaMain.view.removeComponents(pranaMain.basic);

        // System.out.println("val: " + v2 % v1);
        // "Inhalation : Exhalation"
        if (combobox_index == 1) {
            // Falcon 1:7
            if (v2 % v1 == 0 && v2 % 7 == 0 && v1 % v2 == 1) {
                pranaMain.view.setNameBorder("Breathing Falcon " + "(" + v1 + ":" + v2 + ") ");
            } // Bear 7:1
            else if (v1 % v2 == 0 && v1 % 7 == 0 && v1 % v2 == 0 && v1 != v2) {
                pranaMain.view.setNameBorder("Breathing Bear " + "(" + v1 + ":" + v2 + ") ");
            } // Volf 1:1
            else if (v1 % v2 == 0 && v1 < 6 && v2 < 6 && v1 == v2) {
                pranaMain.view.setNameBorder("Breathing Volf " + "(" + v1 + ":" + v2 + ") ");
            } // Snake 1:1
            else if (v1 % v2 == 0 && v1 > 5 && v2 > 5 && v1 == v2) {
                pranaMain.view.setNameBorder("Breathing Snake " + "(" + v1 + ":" + v2 + ") ");
            } else {
                pranaMain.view.setNameBorder("No name");
            }
        }
        // "Inhalation : hold : Exhalation"
        if (combobox_index == 2) {
            pranaMain.view.setNameBorder("No name" + "(" + v1 + ":" + v2 + ":" + v3 + ") ");
        }
        // "Inhalation : hold : Exhalation : hold"
        if (combobox_index == 3) {
            pranaMain.view.setNameBorder("No name" + "(" + v1 + ":" + v2 + ":" + v3 + ":" + v4 + ") ");
        }

        //  see viewsColorWindow.java file
        // BACKGROUND GRADIENT or COMMON COLOR
        if (pranaMain.colorsWindow.colorBackground != null) {
            // checkbox 'use Gradient'
            if (!pranaMain.colorsWindow.box_useGradientOrNot.isSelected()) {
                pranaMain.basic.setType(100);
                pranaMain.basic.setColorBackground(pranaMain.colorsWindow.colorBackground);
            } else {
                if (pranaMain.colorsWindow.indexGradient == -1) {
                    pranaMain.basic.setType(pranaMain.basic.getType());
                } else {
                    pranaMain.basic.setType(pranaMain.colorsWindow.indexGradient);
                }
            }
        } else {
            // checkbox 'use Gradient'
            if (!pranaMain.colorsWindow.box_useGradientOrNot.isSelected()) {
                pranaMain.basic.setType(100);
                pranaMain.basic.setColorBackground(pranaMain.basic.getColorBackground());
            } else {
                if (pranaMain.colorsWindow.indexGradient == -1) {
                    pranaMain.basic.setType(pranaMain.basic.getType());
                    System.out.println("if TYPE:" + pranaMain.basic.getType() );
                } else {
                    pranaMain.basic.setType(pranaMain.colorsWindow.indexGradient);
                    System.out.println("else TYPE:" + pranaMain.basic.getType() );
                }
            }
        }
        
//         System.out.println("!!! box_useGradientOrNot: " + pranaMain.colorsWindow.box_useGradientOrNot.isSelected());
//         System.out.println("!!! TYPE:" + pranaMain.basic.getType() );
        pranaMain.basic.setType(pranaMain.basic.getType());

        // choice view 
        pranaMain.view.choiceView();

        switch (combobox_index) {
            default:
                break;
            case 1:
                // NUMBER VIEW
                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.NUMBER_VIEW) {
                    breathing_run = new breathingRun(
                            pranaMain.view.label_Inhalation,
                            pranaMain.view.label_Exhalation,
                            pranaMain.view.label_numCycles,
                            pranaMain.view.common,
                            0, 0, v1, v2, numCycles);

                    breathing_run.resetCountersAndLabels();
                    breathing_run.startThread();
                }
                // HORIZONTAL_VIEW
                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.HORIZONTAL_VIEW) {
                    breathingRunGraphs rg = new breathingRunGraphs(
                            pranaMain.view.graphCompLine,
                            pranaMain.view.label_numCycles,
                            pranaMain.view.common, 0, 0, v1, v2, numCycles);
                    // set equal share  for index 1 : 'inhalation : exhalation'
//                    pranaMain.view.graphCompLine.getIndividualShare(1, "horizontal");

                    pranaMain.view.graphCompLine.setV1(v1);
                    pranaMain.view.graphCompLine.setV2(v2);

                    rg.resetCountersAndLabels();
                    rg.startThread();
                }
                // VERTICAL_VIEW
                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.VERTICAL_VIEW) {
                    breathingRunGraphs vg = new breathingRunGraphs(
                            pranaMain.view.graphCompLine,
                            pranaMain.view.label_numCycles,
                            pranaMain.view.common, 0, 0, v1, v2, numCycles);
                    // set equal share  for index 1 : 'inhalation : exhalation'
//                    pranaMain.view.graphCompLine.getIndividualShare(1, "vertical");

                    pranaMain.view.graphCompLine.setV1(v1);
                    pranaMain.view.graphCompLine.setV2(v2);
                    pranaMain.view.graphCompLine.setMax1(v1);
                    pranaMain.view.graphCompLine.setMax2(v2);

                    vg.resetCountersAndLabels();
                    vg.startThread();
                }
                // CIRCLE VIEW
                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.CIRCLE_VIEW) {
                    breathingRunGraphs cg = new breathingRunGraphs(
                            pranaMain.view.graphCompLine,
                            pranaMain.view.label_numCycles,
                            pranaMain.view.common, 0, 0, v1, v2, numCycles);
                    // set equal share  for index 1 : 'inhalation : exhalation'
                    // pranaMain.view.graphCompLine.getIndividualShare(1, "circle");

                    pranaMain.view.graphCompLine.setV1(v1);
                    pranaMain.view.graphCompLine.setV2(v2);

                    cg.resetCountersAndLabels();
                    cg.startThread();
                }
                break;
            case 2:
                // NUMBER_VIEW
                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.NUMBER_VIEW) {
                    breathing_run = new breathingRun(
                            pranaMain.view.label_Inhalation,
                            pranaMain.view.label_breathhold_after_inhalation,
                            pranaMain.view.label_Exhalation,
                            pranaMain.view.label_numCycles,
                            pranaMain.view.common,
                            0, 0, 0, v1, v2, v3, numCycles);

                    breathing_run.resetCountersAndLabels();
                    breathing_run.startThread();
                }
                // HORIZONTAL_VIEW
                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.HORIZONTAL_VIEW) {
                    breathingRunGraphs rg2 = new breathingRunGraphs(
                            pranaMain.view.graphCompLine,
                            pranaMain.view.label_numCycles,
                            pranaMain.view.common, 0, 0, 0, v1, v2, v3, numCycles);
                    // set equal share  for index 1 : 'inhalation : hold : exhalation'
//                    pranaMain.view.graphCompLine.getIndividualShare(2, "horizontal");

                    pranaMain.view.graphCompLine.setV1(v1);
                    pranaMain.view.graphCompLine.setV2(v2);
                    pranaMain.view.graphCompLine.setV3(v3);

                    rg2.resetCountersAndLabels();
                    rg2.startThread();
                }
                // VERTICAL_VIEW
                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.VERTICAL_VIEW) {
                    breathingRunGraphs vg2 = new breathingRunGraphs(
                            pranaMain.view.graphCompLine,
                            pranaMain.view.label_numCycles,
                            pranaMain.view.common, 0, 0, 0, v1, v2, v3, numCycles);
                    // set equal share  for index 2 : 'inhalation : hold : exhalation'
//                    pranaMain.view.graphCompLine.getIndividualShare(2, "vertical");

                    pranaMain.view.graphCompLine.setV1(v1);
                    pranaMain.view.graphCompLine.setV2(v2);
                    pranaMain.view.graphCompLine.setV3(v3);
                    pranaMain.view.graphCompLine.setMax1(v1);
                    pranaMain.view.graphCompLine.setMax2(v2);
                    pranaMain.view.graphCompLine.setMax3(v3);

                    vg2.resetCountersAndLabels();
                    vg2.startThread();
                }
                // CIRCLE VIEW
                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.CIRCLE_VIEW) {
                    breathingRunGraphs cg2 = new breathingRunGraphs(
                            pranaMain.view.graphCompLine,
                            pranaMain.view.label_numCycles,
                            pranaMain.view.common, 0, 0, 0, v1, v2, v3, numCycles);
                    // set equal share  for index 2 : 'inhalation : hold : exhalation'
                    //pranaMain.view.graphCompLine.getIndividualShare(2, "circle");

                    pranaMain.view.graphCompLine.setV1(v1);
                    pranaMain.view.graphCompLine.setV2(v2);
                    pranaMain.view.graphCompLine.setV3(v3);

                    cg2.resetCountersAndLabels();
                    cg2.startThread();
                }
                break;
            case 3:
                // NUMBER_VIEW
                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.NUMBER_VIEW) {
                    breathing_run = new breathingRun(
                            pranaMain.view.label_Inhalation,
                            pranaMain.view.label_breathhold_after_inhalation,
                            pranaMain.view.label_Exhalation,
                            pranaMain.view.label_breathhold_after_exhalation,
                            pranaMain.view.label_numCycles,
                            pranaMain.view.common,
                            0, 0, 0, 0, v1, v2, v3, v4, numCycles);

                    breathing_run.resetCountersAndLabels();
                    breathing_run.startThread();
                }
                // HORIZONTAL_VIEW
                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.HORIZONTAL_VIEW) {
                    breathingRunGraphs rg3 = new breathingRunGraphs(
                            pranaMain.view.graphCompLine,
                            pranaMain.view.label_numCycles,
                            pranaMain.view.common, 0, 0, 0, 0, v1, v2, v3, v4, numCycles);
                    // set equal share  for index 1 : 'inhalation : hold : exhalation'
//                    pranaMain.view.graphCompLine.getIndividualShare(2, "horizontal");

                    pranaMain.view.graphCompLine.setV1(v1);
                    pranaMain.view.graphCompLine.setV2(v2);
                    pranaMain.view.graphCompLine.setV3(v3);
                    pranaMain.view.graphCompLine.setV4(v4);

                    rg3.resetCountersAndLabels();
                    rg3.startThread();
                }
                // VERTICAL_VIEW
                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.VERTICAL_VIEW) {
                    breathingRunGraphs vg3 = new breathingRunGraphs(
                            pranaMain.view.graphCompLine,
                            pranaMain.view.label_numCycles,
                            pranaMain.view.common, 0, 0, 0, 0, v1, v2, v3, v4, numCycles);
                    // set equal share  for index 2 : 'inhalation : hold : exhalation'
//                    pranaMain.view.graphCompLine.getIndividualShare(3, "vertical");

                    pranaMain.view.graphCompLine.setV1(v1);
                    pranaMain.view.graphCompLine.setV2(v2);
                    pranaMain.view.graphCompLine.setV3(v3);
                    pranaMain.view.graphCompLine.setV4(v4);
                    pranaMain.view.graphCompLine.setMax1(v1);
                    pranaMain.view.graphCompLine.setMax2(v2);
                    pranaMain.view.graphCompLine.setMax3(v3);
                    pranaMain.view.graphCompLine.setMax4(v4);

                    vg3.resetCountersAndLabels();
                    vg3.startThread();
                }
                // CIRCLE VIEW
                if (pranaMain.vDefault.defaultView == pranaMain.vDefault.CIRCLE_VIEW) {
                    breathingRunGraphs cg3 = new breathingRunGraphs(
                            pranaMain.view.graphCompLine,
                            pranaMain.view.label_numCycles,
                            pranaMain.view.common, 0, 0, 0, 0, v1, v2, v3, v4, numCycles);
                    // set equal share  for index 2 : 'inhalation : hold : exhalation'
                    //pranaMain.view.graphCompLine.getIndividualShare(2, "circle");

                    pranaMain.view.graphCompLine.setV1(v1);
                    pranaMain.view.graphCompLine.setV2(v2);
                    pranaMain.view.graphCompLine.setV3(v3);
                    pranaMain.view.graphCompLine.setV4(v4);

                    cg3.resetCountersAndLabels();
                    cg3.startThread();
                }
                break;
        }

        //  System.out.println("ma: " + v1 + "  " + v2 + "  " + numCycles);
    }

}
