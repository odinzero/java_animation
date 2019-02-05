package PRANAYAMA;

import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class breathingRun implements Runnable, IRunnable<Object> {

    JLabel label1, label2, label3, label4, labelNumCycles;
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
    // brth2     <--  set init value for  JTextField 'field_inhalation' (class:settingsBreathing.java)  
    // brth1_end <-- value from  JTextField field_inhalation (class:settingsBreathing.java)
    // brth2_end <-- value from  JTextField field_exhalation (class:settingsBreathing.java) 
    // numCycles <-- value from  JSpinner spinner_cycles     (class:settingsBreathing.java)
    breathingRun(JLabel label1, JLabel label2, JLabel labelNumCycles, JPanel common,
            int brth1, int brth2,
            int brth1_end, int brth2_end, int numCycles) {
        this.label1 = label1;
        this.label2 = label2;
        this.labelNumCycles = labelNumCycles;
        this.common = common;

        this.brth1 = brth1;
        this.brth2 = brth2;
        this.brth1_end = brth1_end;
        this.brth2_end = brth2_end;
        this.numCycles = numCycles;
        
      //  resetCountersAndLabels();

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
    breathingRun(JLabel label1, JLabel label2, JLabel label3, JLabel labelNumCycles, JPanel common,
            int brth1, int brth2, int brth3,
            int brth1_end, int brth2_end, int brth3_end, int numCycles) {
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
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
    breathingRun(JLabel label1, JLabel label2, JLabel label3, JLabel label4, JLabel labelNumCycles, JPanel common,
            int brth1, int brth2, int brth3, int brth4,
            int brth1_end, int brth2_end, int brth3_end, int brth4_end, int numCycles) {
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
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
                label1.setText("" + utils.numberPosition(brth1));
            } else {
                if (brth2 < brth2_end) {
                    brth2++;
                    label2.setText("" + utils.numberPosition(brth2));
                } else {
                    if (initValNumCycles < numCycles - 1) {
                        initValNumCycles++;
                        labelNumCycles.setText("" + initValNumCycles);
                        brth1 = 0;
                        brth2 = 0;
                        label1.setText("00");
                        label2.setText("00");
//                        System.out.println("initValNumCycles:" + initValNumCycles + " numCycles:" + numCycles);
                    } else if (initValNumCycles < numCycles) {
                        initValNumCycles++;
                        labelNumCycles.setText("" + initValNumCycles);
                    } else {
                        stopThread();
                    }
                }
            }
            //  System.out.println("ThreadRun:" + v1  + "  " + v2);
        }
        // inhalation : hold : exhalation
        if (constructor2) {

            if (brth1 < brth1_end) {
                brth1++;
                label1.setText("" + utils.numberPosition(brth1));
            } else {
                if (brth2 < brth2_end) {
                    brth2++;
                    label2.setText("" + utils.numberPosition(brth2));
                } else {
                    if (brth3 < brth3_end) {
                        brth3++;
                        label3.setText("" + utils.numberPosition(brth3));
                    } else {
                        if (initValNumCycles < numCycles - 1) {
                            initValNumCycles++;
                            labelNumCycles.setText("" + initValNumCycles);
                            brth1 = 0;
                            brth2 = 0;
                            brth3 = 0;
                            label1.setText("00");
                            label2.setText("00");
                            label3.setText("00");
                        } else if (initValNumCycles < numCycles) {
                            initValNumCycles++;
                            labelNumCycles.setText("" + initValNumCycles);
                        } else {
                            stopThread();
                        }
                    }
                }
            }
        }
        // inhalation : hold : exhalation : hold
        if (constructor3) {

            if (brth1 < brth1_end) {
                brth1++;
                label1.setText("" + utils.numberPosition(brth1));
            } else {
                if (brth2 < brth2_end) {
                    brth2++;
                    label2.setText("" + utils.numberPosition(brth2));
                } else {
                    if (brth3 < brth3_end) {
                        brth3++;
                        label3.setText("" + utils.numberPosition(brth3));
                    } else {
                        if (brth4 < brth4_end) {
                            brth4++;
                            label4.setText("" + utils.numberPosition(brth4));
                        } else {
                            if (initValNumCycles < numCycles - 1) {
                                initValNumCycles++;
                                labelNumCycles.setText("" + initValNumCycles);
                                brth1 = 0;
                                brth2 = 0;
                                brth3 = 0;
                                brth4 = 0;
                                label1.setText("00");
                                label2.setText("00");
                                label3.setText("00");
                                label4.setText("00");
                            } else if (initValNumCycles < numCycles) {
                                initValNumCycles++;
                                labelNumCycles.setText("" + initValNumCycles);
                            } else {
                                stopThread();
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
            label1.setText("00");
            label2.setText("00");
            labelNumCycles.setText("0");
        }
        if (constructor2) {
            brth1 = 0;
            brth2 = 0;
            brth3 = 0;
            label1.setText("00");
            label2.setText("00");
            label3.setText("00");
            labelNumCycles.setText("0");
        }
        if (constructor3) {
            brth1 = 0;
            brth2 = 0;
            brth3 = 0;
            brth4 = 0;
            label1.setText("00");
            label2.setText("00");
            label3.setText("00");
            label4.setText("00");
            labelNumCycles.setText("0");
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
