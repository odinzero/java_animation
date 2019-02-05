package PRANAYAMA;

import java.awt.Color;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class breathingRunGraphs implements Runnable, IRunnable<Object> {

    graphComponents graphComp;
    JLabel labelNumCycles;
    JPanel common;
    int brth1, brth2, brth3, brth4;
    int brth1_end, brth2_end, brth3_end, brth4_end;
    int initValNumCycles, numCycles;
    AtomicBoolean suspend;
    Thread thrObj;
    boolean start;
    boolean constructor1 = false,
            constructor2 = false,
            constructor3 = false;

    // brth1     <--  set init value for  JTextField 'field_inhalation' (class:settingsBreathing.java)  
    // brth2     <--  set init value for  JTextField 'field_exhalation' (class:settingsBreathing.java)  
    // brth1_end <-- value from  JTextField 'field_inhalation' (class:settingsBreathing.java)
    // brth2_end <-- value from  JTextField 'field_exhalation' (class:settingsBreathing.java) 
    // numCycles <-- value from  JSpinner spinner_cycles     (class:settingsBreathing.java)
    breathingRunGraphs(graphComponents gcomp, JLabel labelNumCycles, JPanel common,
            int brth1, int brth2,
            int brth1_end, int brth2_end, int numCycles) {
        this.graphComp = gcomp;
        this.labelNumCycles = labelNumCycles;
        this.common = common;

        this.brth1 = brth1;
        this.brth2 = brth2;
        this.brth1_end = brth1_end;
        this.brth2_end = brth2_end;
        this.numCycles = numCycles;

        initValNumCycles = 0;

        constructor1 = true;
        suspend = new AtomicBoolean(false);
    }

    // brth1     <--  set init value for  JTextField 'field_inhalation' (class:settingsBreathing.java) 
    // brth2     <--  set init value for  JTextField 'field_breathhold_after_inhalation' (class:settingsBreathing.java)
    // brth3     <--  set init value for  JTextField 'field_exhalation' (class:settingsBreathing.java) 
    // brth1_end <-- value from  JTextField 'field_inhalation' (class:settingsBreathing.java)
    // brth2_end <-- value from  JTextField 'field_breathhold_after_inhalation' (class:settingsBreathing.java)
    // brth3_end <-- value from  JTextField 'field_exhalation' (class:settingsBreathing.java)
    // numCycles <-- value from  JSpinner spinner_cycles     (class:settingsBreathing.java)
    breathingRunGraphs(graphComponents gcomp, JLabel labelNumCycles, JPanel common,
            int brth1, int brth2, int brth3,
            int brth1_end, int brth2_end, int brth3_end, int numCycles) {
        this.graphComp = gcomp;
        this.labelNumCycles = labelNumCycles;
        this.common = common;

        this.brth1 = brth1;
        this.brth2 = brth2;
        this.brth3 = brth3;
        this.brth1_end = brth1_end;
        this.brth2_end = brth2_end;
        this.brth3_end = brth3_end;
        this.numCycles = numCycles;

        initValNumCycles = 0;

        constructor2 = true;
        suspend = new AtomicBoolean(false);
    }

    // brth1     <--  set init value for  JTextField 'field_inhalation' (class:settingsBreathing.java) 
    // brth2     <--  set init value for  JTextField 'field_breathhold_after_inhalation' (class:settingsBreathing.java)
    // brth3     <--  set init value for  JTextField 'field_exhalation' (class:settingsBreathing.java) 
    // brth4     <--  set init value for  JTextField 'field_breathhold_after_exhalation' (class:settingsBreathing.java) 
    // brth1_end <-- value from  JTextField 'field_inhalation' (class:settingsBreathing.java)
    // brth2_end <-- value from  JTextField 'field_breathhold_after_inhalation' (class:settingsBreathing.java)
    // brth3_end <-- value from  JTextField 'field_exhalation' (class:settingsBreathing.java)
    // brth4_end <-- value from  JTextField 'field_breathhold_after_exhalation' (class:settingsBreathing.java)
    // numCycles <-- value from  JSpinner spinner_cycles     (class:settingsBreathing.java)
    breathingRunGraphs(graphComponents gcomp, JLabel labelNumCycles, JPanel common,
            int brth1, int brth2, int brth3, int brth4,
            int brth1_end, int brth2_end, int brth3_end, int brth4_end, int numCycles) {
        this.graphComp = gcomp;
        this.labelNumCycles = labelNumCycles;
        this.common = common;

        this.brth1 = brth1;
        this.brth2 = brth2;
        this.brth3 = brth3;
        this.brth4 = brth4;
        this.brth1_end = brth1_end;
        this.brth2_end = brth2_end;
        this.brth3_end = brth3_end;
        this.brth4_end = brth4_end;
        this.numCycles = numCycles;

        initValNumCycles = 0;

        constructor3 = true;
        suspend = new AtomicBoolean(false);
    }

    //
    private void init() {
        // inhalation : exhalation
        if (constructor1) {

            if (brth1 < brth1_end) {
                brth1++;
                graphComp.setDW1(brth1);
                graphComp.setCounter1("" + utils.numberPosition(brth1));
                // for Cirlce
                graphComp.setOneCounterForAll("" + utils.numberPosition(brth1));
                graphComp.setColorCounter(graphComp.getColor1());  // inhalation
            } else {
                if (brth2 < brth2_end) {
                    brth2++;
                    graphComp.setDW2(brth2);
                    graphComp.setCounter2("" + utils.numberPosition(brth2));
                    // for Cirlce
                    graphComp.setOneCounterForAll("" + utils.numberPosition(brth2));
                    graphComp.setColorCounter(graphComp.getColor3()); // exhalation
                } else {
                    if (initValNumCycles < numCycles - 1) {
                        initValNumCycles++;
                        labelNumCycles.setText("" + initValNumCycles);
                        brth1 = 0;
                        brth2 = 0;
                        // set width of dynamic components to 0
                        graphComp.setDW1(brth1);
                        graphComp.setDW2(brth2);
                        // for Cirlce
                        graphComp.setOneCounterForAll("" + utils.numberPosition(brth1));
//                        System.out.println("initValNumCycles:" + initValNumCycles + " numCycles:" + numCycles);
                    } else if (initValNumCycles < numCycles) {
                        initValNumCycles++;
                        labelNumCycles.setText("" + initValNumCycles);
                    } else {
                        stopThread();
                        // set width of dynamic components to 0
                        graphComp.setDW1(0);
                        graphComp.setDW2(0);
                        graphComp.setCounter1("00");
                        graphComp.setCounter2("00");
                        // for Cirlce
                        graphComp.setOneCounterForAll("00"); 
                        graphComp.setColorCounter(Color.BLACK);
                    }
                }
            }
            //  System.out.println("ThreadRun:" + v1  + "  " + v2);
        }
        // inhalation : hold : exhalation
        if (constructor2) {

            if (brth1 < brth1_end) {
                brth1++;
                graphComp.setDW1(brth1);
                graphComp.setCounter1("" + utils.numberPosition(brth1));
                // for Cirlce
                graphComp.setOneCounterForAll("" + utils.numberPosition(brth1)); 
                graphComp.setColorCounter(graphComp.getColor1());
            } else {
                if (brth2 < brth2_end) {
                    brth2++;
                    graphComp.setDW2(brth2);
                    graphComp.setCounter2("" + utils.numberPosition(brth2));
                    // for Cirlce
                    graphComp.setOneCounterForAll("" + utils.numberPosition(brth2)); 
                    graphComp.setColorCounter(graphComp.getColor2());
                } else {
                    if (brth3 < brth3_end) {
                        brth3++;
                        graphComp.setDW3(brth3);
                        graphComp.setCounter3("" + utils.numberPosition(brth3));
                        // for Cirlce
                        graphComp.setOneCounterForAll("" + utils.numberPosition(brth3)); 
                        graphComp.setColorCounter(graphComp.getColor3());
                    } else {
                        if (initValNumCycles < numCycles - 1) {
                            initValNumCycles++;
                            labelNumCycles.setText("" + initValNumCycles);
                            brth1 = 0;
                            brth2 = 0;
                            brth3 = 0;
                            // set width of dynamic components to 0
                            graphComp.setDW1(brth1);
                            graphComp.setDW2(brth2);
                            graphComp.setDW3(brth3);
                            // for Cirlce
                            graphComp.setOneCounterForAll("" + utils.numberPosition(brth1)); 
                        } else if (initValNumCycles < numCycles) {
                            initValNumCycles++;
                            labelNumCycles.setText("" + initValNumCycles);
                        } else {
                            stopThread();
                            // set width of dynamic components to 0
                            graphComp.setDW1(0);
                            graphComp.setDW2(0);
                            graphComp.setDW3(0);
                            graphComp.setCounter1("00");
                            graphComp.setCounter2("00");
                            graphComp.setCounter3("00");
                            // for Cirlce
                            graphComp.setOneCounterForAll("00");
                            graphComp.setColorCounter(Color.BLACK);
                        }
                    }
                }
            }
        }
        // inhalation : hold : exhalation : hold
        if (constructor3) {

            if (brth1 < brth1_end) {
                brth1++;
                graphComp.setDW1(brth1);
                graphComp.setCounter1("" + utils.numberPosition(brth1));
                // for Cirlce
                graphComp.setOneCounterForAll("" + utils.numberPosition(brth1));
                graphComp.setColorCounter(graphComp.getColor1());
            } else {
                if (brth2 < brth2_end) {
                    brth2++;
                    graphComp.setDW2(brth2);
                    graphComp.setCounter2("" + utils.numberPosition(brth2));
                    // for Cirlce
                    graphComp.setOneCounterForAll("" + utils.numberPosition(brth2));
                    graphComp.setColorCounter(graphComp.getColor2());
                } else {
                    if (brth3 < brth3_end) {
                        brth3++;
                        graphComp.setDW3(brth3);
                        graphComp.setCounter3("" + utils.numberPosition(brth3));
                        // for Cirlce
                        graphComp.setOneCounterForAll("" + utils.numberPosition(brth3));
                        graphComp.setColorCounter(graphComp.getColor3());
                    } else {
                        if (brth4 < brth4_end) {
                            brth4++;
                            graphComp.setDW4(brth4);
                            graphComp.setCounter4("" + utils.numberPosition(brth4));
                            // for Cirlce
                            graphComp.setOneCounterForAll("" + utils.numberPosition(brth4));
                            graphComp.setColorCounter(graphComp.getColor4());
                        } else {
                            if (initValNumCycles < numCycles - 1) {
                                initValNumCycles++;
                                labelNumCycles.setText("" + initValNumCycles);
                                brth1 = 0;
                                brth2 = 0;
                                brth3 = 0;
                                brth4 = 0;
                                // set width of dynamic components to 0
                                graphComp.setDW1(brth1);
                                graphComp.setDW2(brth2);
                                graphComp.setDW3(brth3);
                                graphComp.setDW4(brth4);
                                // for Cirlce
                                graphComp.setOneCounterForAll("" + utils.numberPosition(brth1));
                            } else if (initValNumCycles < numCycles) {
                                initValNumCycles++;
                                labelNumCycles.setText("" + initValNumCycles);
                            } else {
                                stopThread();
                                // set width of dynamic components to 0
                                graphComp.setDW1(0);
                                graphComp.setDW2(0);
                                graphComp.setDW3(0);
                                graphComp.setDW4(0);

                                graphComp.setCounter1("00");
                                graphComp.setCounter2("00");
                                graphComp.setCounter3("00");
                                graphComp.setCounter4("00");
                                // for Cirlce
                                graphComp.setOneCounterForAll("00");
                                graphComp.setColorCounter(Color.BLACK);
                            }
                        }
                    }
                }
            }
        }
        common.repaint();
    }

    public void resetCountersAndLabels() {
        if (constructor1) {
            brth1 = 0;
            brth2 = 0;
            // if type = 1 -> set width of dynamic components to 0
            // if type = 2 -> set height of dynamic components to 0
            graphComp.setDW1(brth1);
            graphComp.setDW2(brth2);
            labelNumCycles.setText("0");
            // graphComponents type = 2(vertical)
            graphComp.setCounter1("00");
            graphComp.setCounter2("00");
            //type = 3 (Cirlce)
            graphComp.setOneCounterForAll("00");
            graphComp.setColorCounter(Color.BLACK);
        }
        if (constructor2) {
            // System.out.println("::: " + brth1 + " " + brth2 + " " + brth3 );
            brth1 = 0;
            brth2 = 0;
            brth3 = 0;
            // if type = 1 -> set width of dynamic components to 0
            // if type = 2 -> set height of dynamic components to 0
            graphComp.setDW1(brth1);
            graphComp.setDW2(brth2);
            graphComp.setDW3(brth3);
            labelNumCycles.setText("0");
            // graphComponents type = 2(vertical)
            graphComp.setCounter1("00");
            graphComp.setCounter2("00");
            graphComp.setCounter3("00");
            //type = 3 (Cirlce)
            graphComp.setOneCounterForAll("00");
            graphComp.setColorCounter(Color.BLACK);
        }
        if (constructor3) {
            brth1 = 0;
            brth2 = 0;
            brth3 = 0;
            brth4 = 0;
            // if type = 1 -> set width of dynamic components to 0
            // if type = 2 -> set height of dynamic components to 0
            graphComp.setDW1(brth1);
            graphComp.setDW2(brth2);
            graphComp.setDW3(brth3);
            graphComp.setDW4(brth4);
            labelNumCycles.setText("0");
            // graphComponents type = 2(vertical)
            graphComp.setCounter1("00");
            graphComp.setCounter2("00");
            graphComp.setCounter3("00");
            graphComp.setCounter4("00");
            //type = 3 (Cirlce)
            graphComp.setOneCounterForAll("00");
            graphComp.setColorCounter(Color.BLACK);
        }
        common.repaint();
    }

    @Override
    public void run() {
        // boolean start = true;
        while (start) {
            try {
                // ******** in order to 'pause' this thread
                if (suspend.get()) {
                    synchronized (thrObj) {
                        // Pause
                        try {
                            thrObj.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                }

                Thread.currentThread().sleep(1000);
                init();

            } catch (InterruptedException ex) {
            }

        }
    }

    @Override
    public void startThread() {
//        if (thrObj.isAlive()) {
//            start = false;
//            synchronized (thrObj) {
//                thrObj.notify();
//            }
//        }

        if (thrObj == null) {
            thrObj = new Thread(this);
            start = true;
            thrObj.start();
            synchronized (thrObj) {
                thrObj.notify();
            }
        }
    }

    @Override
    public void stopThread() {
        if (thrObj != null) {
            if (thrObj.isAlive()) {
                start = false;
                // Notify mainthread
                synchronized (thrObj) {
                    thrObj.notify();
                }
            }
        }
    }

    @Override
    public void pauseThread() {
        if (!suspend.get()) {
            suspend.set(true);
            synchronized (thrObj) {
                thrObj.notify();
            }
        }
    }
}
