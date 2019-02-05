
package Touching_frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class TOUCHING_FRAMES  extends Frame {
    boolean initX  = false; // X
    boolean initY = false;  // Y
    boolean init_Xi = false;

    boolean turn = false;     // turn on thread -->  'anima'
    boolean turnX = false;    // for moving to right Direction and isolation
    boolean turn_Xup = false; // for increasing X coordinate
    boolean turn_Yup = false; // for increasing Y coordinate
    boolean turn_Xlow = false; // for decreasing X coordinate
    boolean turn_Ylow = false; // for decreasing Y coordinate
    Thread thr ;
    int xPos ;
    int yPos = 2;

    Frame frame1;
    boolean right = false;    // turn on thread -->
    boolean rightR = false;   // for moving to right Direction and isolation
    boolean r_Xup = false;    // for increasing X coordinate
    boolean r_Yup = false;    // for increasing Y coordinate
    boolean r_Xlow = false;   // for decreasing X coordinate
    boolean r_Ylow = false;   // for decreasing Y coordinate
    Thread thr1;
    int xPos1 ;
    int yPos1 ;

    Color colors[] = { Color.BLACK , Color.BLUE , Color.CYAN , Color.DARK_GRAY , Color.GRAY , Color.GREEN  ,
                       Color.LIGHT_GRAY , Color.MAGENTA , Color.ORANGE , Color.PINK , Color.RED , Color.YELLOW
    };
    JLabel labelCnt ; //  for displaying quantity touches
    int cnt ;         //  count for quantity  side touches
    int ccnt;         //  count for iteration colors
    int fcnt = 0;     //  count for quantity frames
    boolean displayBottom = false; // for dispaling new Frame  : class moveToBottom
    boolean displayRight = false; // for dispaling new Frame  : class moveToRight
    boolean displayTop = false; // for dispaling new Frame  : class moveToTop

    private   int num = 0; // condition for displaying new  Frame : class moveToBottom or moveToRight or moveToTop
    private int tot = 0;   // time to live of class moveToBottom
    private int totR = 0;   // time to live of class moveToRight
    private int totT = 0;   // time to live of class moveToTop
    private boolean shutdown = false;  // interrupt all Threads

    public TOUCHING_FRAMES() { this("ApplicationFrame v1.0");}
    public TOUCHING_FRAMES(String title){
        super(title);
        addKeyListener(new key());
        createUI();
        setAlwaysOnTop(true);
        addMouseMotionListener(new DRAG());
        addMouseListener(new DRAG());
//        createFrame(0);
//        new test();
//        this.requestFocus(true);
//        this.toFront();
//        frame1.requestFocus();
//        frame1.toFront();
    }

    protected Frame createFrame(int index  ) {
        fcnt = index;
        frame1 = new Frame("-== " + fcnt + " ==-");
        frame1.setLayout(new GridBagLayout());
        frame1.setSize(200, 200);
        frame1.setMinimumSize(new Dimension(200, 200));
        frame1.setPreferredSize(new Dimension(200, 200));
        frame1.addKeyListener(new key());
        frame1.addMouseListener(new DRAG());
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension framesize = frame1.getSize();
       int x = ((screensize.width  -  framesize.width )/ 2 ) - 3;
       int y = ((screensize.height  -  framesize.height  )/ 2) - 4;
        xPos1 = x + 500;
        yPos1 = y;
//        System.out.println("X:"+ xPos);
        frame1.setLocation( xPos1, yPos1 );
        frame1.add(setLabelInCreatedFrame(cnt));
        frame1.setVisible(true);
        return frame1;
    }

    protected JLabel setLabelinFrame(int count ){
        labelCnt = new JLabel("Touch " + count  + "   " );
        labelCnt.setFont(new Font("Serif" , Font.BOLD , 20));
        return labelCnt;
    }

    protected JLabel setLabelInCreatedFrame(int count ){
        JLabel label = new JLabel("Touch " + cnt  + "   ");
        label.setFont(new Font("Serif" , Font.BOLD , 32));
        return label;
    }

    protected Color setTouchColor(int index) {
        if( index == colors.length  ) {
            index = 0;
            ccnt = index;
            System.out.println("NOT");
        }
      return colors[index];
    }

    protected void createUI() {
       setSize(200, 200);
       setLayout(new GridBagLayout());
       add( setLabelinFrame(cnt)  );
       setVisible(true);
       // set Frame in the center of screen
       center();
       // in this method in constructor is just implemented first part of condition if( !init )
       // --> 'xPos' gets initial value equals 'x'
       changeX();
        // in this method in constructor is just implemented first part of condition if( !initY )
       // --> 'yPos' gets initial value equals 'y'
       changeY();

       addWindowListener(new WindowAdapter() {
            @Override
         public void windowClosing( WindowEvent e){
             dispose();
             System.exit(0);
         }
       });
    }

     protected void center() {
       Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
       Dimension framesize = getSize();
       int x = ((screensize.width  -  framesize.width )/ 2 ) - 3;
       int y = ((screensize.height  -  framesize.height  )/ 2) - 4;
       System.out.println("X:"+ x);
       System.out.println("Y:"+ y);
       setLocation( x, y );
    }

    class key implements KeyListener {

            @Override
            public void keyTyped(KeyEvent e) {  }

            @Override
            public void keyPressed(KeyEvent e) {
              int keyKode = e.getKeyCode();
              if( keyKode == KeyEvent.VK_X) {
                  initX = true;
                  // in this method it is just implemented second part of condition if( !init )
                  // --> xPos  decrease 'x' -10;
                  changeX();
                  repaint();
                  System.out.println("Key :"+ xPos);
              }
              if( keyKode == KeyEvent.VK_Y) {
                  initY = true;
                  // in this method it is just implemented second part of condition if( !init )
                  // --> xPos  decrease 'x' -10;
                  changeY();
                  repaint();
                  System.out.println("Key :"+ yPos);
              }
              // THREAD !
             if( keyKode == KeyEvent.VK_T) {
                 turn = true;
                 thr = new Thread(anima);
                 thr.start();
             }  else {
                 turn = false;
             }
             // THREAD !
             if( keyKode == KeyEvent.VK_U) {
                 right = true;

                  thr = new Thread(anima);
                 thr.start();
//                 SwingUtilities.invokeLater(thr);
                 System.out.println("U");
             }  else {
                 right = false;
             }
            }
            @Override
            public void keyReleased(KeyEvent e) { }
    }

    protected void changeY() {
       Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
       Dimension framesize  = getSize();
       int x = ((screensize.width  -  framesize.width )/ 2) - 3;
       int y = ((screensize.height  -  framesize.height )/ 2) - 4;
     if( !initY ) {
           yPos = y;
        } else {
           yPos -= 10;
           if( yPos <= 0 ) {
               yPos = 0;
           }
       System.out.println("Y:"+ yPos);
       setLocation( x, yPos );
        }
    }


    protected void changeX() {
       Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
       Dimension framesize  = getSize();
       int x = ((screensize.width  -  framesize.width )/ 2) - 3;
       int y = ((screensize.height  -  framesize.height )/ 2) - 4;
     if( !initX ) {
           xPos = x;
        } else {
           xPos -= 10;
           if( xPos <= 0 ) {
               xPos = 0;
               init_Xi = true;
           }
           if( init_Xi ) {
              changeX_increase();
         }
       System.out.println("X:"+ xPos);
       setLocation( xPos, y );
        }
    }

     protected void changeX_increase() {
        xPos += 20;
   }

     class DRAG implements MouseMotionListener , MouseListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            xPos = e.getXOnScreen();
            yPos = e.getYOnScreen();
            System.out.println("getLocation().x :" + " " + getLocation().x + " " + e.getX() );
           System.out.println("getLocation().y :" + " " + getLocation().y + " " + e.getY());
        }

        @Override
        public void mouseMoved(MouseEvent e) { }

        //
        @Override
        public void mouseClicked(MouseEvent e) {
//           Dimension frame1size =  frame1.getSize();
//          int frame1_w = frame1.getSize().width;
//          int frame1_h = frame1.getSize().height;
           System.out.println("getLocation().x :" + " " + getLocation().x + " " + e.getX() );
           System.out.println("getLocation().y :" + " " + getLocation().y + " " + e.getY());
//           System.out.println("frame1.getLocation().y  :" + " " + getLocation().y );   //  284
//           System.out.println("frame1.getLocation().y -  frame1_h :" + " " + (getLocation().y -  frame1_h));  // 84
//           System.out.println("frame1.getLocation().y +  frame1_h :" + " " + (getLocation().y +  frame1_h));  // 484

//           System.out.println("frame1.getLocation().x  :" + " " + (getLocation().x  ));   // 1083
//           System.out.println("frame1.getLocation().x -  frame1_w :" + " " + (getLocation().x -  frame1_w));  // 883
//           System.out.println("frame1.getLocation().x +  frame1_w :" + " " + (getLocation().x +  frame1_w));  // 1283
        }
        @Override
        public void mousePressed(MouseEvent e) {  }
        @Override
        public void mouseReleased(MouseEvent e) {
               xPos = e.getXOnScreen();
               yPos = e.getYOnScreen();
        }
        @Override
        public void mouseEntered(MouseEvent e) { }
        @Override
        public void mouseExited(MouseEvent e) { }
     }
                                                         // * moveToTop *
      protected void SCHEMA_MOVE( boolean topSide  ,      //
                                  boolean leftSide ,     //
                                  boolean rightSide ,    //
                                  boolean bottomSide ,   //
                                  int  baseframe_h ,     //
                                  int  baseframe_w ,     //
                                  int  x_Pos ,           // topClass.xPos1
                                  int  y_Pos ,           // topClass.yPos1
                                  int  x ,               // getLocation().x
                                  int  y                 // getLocation().y
                                )  {
      // ***** TOP
            // condition for only :  TOP(baseFrame) to  bottomTouch(new Frame)
               if ((  y_Pos >= (( y - baseframe_h + 1 )))   ) {
                              topSide = false; //System.out.println("top = false");
                    } else  { topSide = true;
                            // System.out.println("top = true");
                    }

            // SIDE OF TOUCH :  TOP(baseFrame) to  bottomTouch(new Frame)
                     if( y_Pos >= (( y - baseframe_h )))
                        if( x_Pos >= (( x - baseframe_w  )))
                            if( x_Pos <= (( x + baseframe_w  )))
                                 if( topSide )
                        {
                             
                              turn_Ylow = false;
                              turn_Yup  = true;
//                            System.out.println("schema-------------------------------------------------------------top");
                         }

//***** LEFT
            // condition for only :  LEFT(baseFrame) to  rightTouch(new Frame)
               if (  x_Pos >= ( x - baseframe_w  + 1 ) )  {
                              leftSide = false; // System.out.println("left = false");
                    } else  { leftSide = true;
                             // System.out.println("left = true");
                            }

            // SIDE OF TOUCH :   LEFT(baseFrame) to  rightTouch(new Frame)
                    if( x_Pos >= ( x - baseframe_w ) )   // x = 0
                        if( y_Pos <= (( y + baseframe_h  )) ) // y  = height   484
                             if((  y_Pos >= ( y - baseframe_h )) ) // y = 0   284
                                  if( leftSide )
                 {
                             turn_Xlow = false;
                             turn_Xup  = true;
//                    System.out.println("schema-left");
                 }
// ***** BOTTOM
            // condition for only :  BOTTOM(baseFrame) to  topTouch(new Frame)
                if (  y_Pos <= ( y + baseframe_h  - 1 ) )  {
                              bottomSide = false; //System.out.println("bottom ");
                    } else  { bottomSide = true;
                             // System.out.println("bottom = true");
                            }

            // SIDE OF TOUCH :   BOTTOM(baseFrame) to  topTouch(new Frame)
                     if( y_Pos <= (( y + baseframe_h )))
                        if( x_Pos >= (( x - baseframe_w  )))
                            if( x_Pos <= (( x + baseframe_w  )))
                                    if( bottomSide )
                      {
//                           setLocation(xPos, yPos);
                           turn_Yup = false;
                           turn_Ylow = true;
//                          System.out.println("schema-bottom");
                      }
// ****** RIGHT
           // condition for only :  RIGHT(baseFrame) to  leftTouch(new Frame)
                if (  x_Pos <= ( x + baseframe_w  - 1 ) )  {
                              rightSide = false; // System.out.println("right = false");
                    } else  { rightSide = true;
                             // System.out.println("right = true");
                            }

           // SIDE OF TOUCH :  RIGHT(baseFrame) to  leftTouch(new Frame)
                  if( x_Pos <= ( x + baseframe_w ) )   // x = 0
                      if( y_Pos <= (( y + baseframe_h  )) ) // y  = height   484
                           if((  y_Pos >= ( y - baseframe_h )) ) // y = 0   284
                                 if( rightSide )
                 {
                       turn_Xup = false;
                       turn_Xlow = true;
//                    System.out.println("schema-right");
                 }
// *****
                    }

         moveToBottom  botttomClass = null;
         moveToTop     topClass     = null;
         moveToRight   rightClass     = null;

           boolean testbottom = false; //  for start 'bottom' condition
           boolean testtop    = false; //  for start 'top'  condition
           boolean testright    = false; //  for start 'right'  condition
  Thread anima = new Thread(){
           
        @Override
    public void run(){
          Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
          int screenWidth = screensize.width - 196;
          int screenHeight = screensize.height - 208; // it is real height of screen !;
          System.out.println("screenWidth : " + screenWidth);
          System.out.println("screenHeight : " + screenHeight);

          Dimension framesize  = getSize();
          int baseframe_w = framesize.width;
          int baseframe_h = framesize.height;

           boolean leftSide = false;
           boolean topSide = false;
           boolean rightSide = false;
           boolean bottomSide = false;

           boolean temp = false;
           boolean temp2 = false;
           boolean temp3 = false; // for start rightClass

            turn_Xup = true;
            turn_Ylow = true;
       while( turn ) {
                try {
                    Thread.currentThread().sleep(200);
 //  CONDITION for dispaying new Frame : class --> moveToBottom  ******************************

          if( displayBottom ) {

               botttomClass = new moveToBottom();
               botttomClass.bottomTest = true;  // condition for start of Thread
               Thread  thr1 = new Thread(  botttomClass  ); // animaRight
               thr1.start();
               displayBottom = false;
               testbottom = true;
               temp = true;
               shutdown = false;

                if( num == 0 )  shutdown = false;
                num ++; // total limitation frames on screen
//                if( num == 2 )  { shutdown = false;  //setVisible(false); temp = true  ;
//                                 }
//                if( num == 4 ) {
//                             shutdown = true; // stop all  threads class
//                             temp = false;
//                             System.out.println("num :: " + num );
//                             num = 0;
//                     }
               }
                    if( testbottom )   {  // start of algorythm :: botttomClass
// ***** TOP
            // condition for only :  top(basseFrame) to  bottomTouch(new Frame)
               if ((  botttomClass.yPos1 >= (( getLocation().y - baseframe_h + 20 )))  ) {
                              topSide = false; //System.out.println("top = false");
                    } else  { topSide = true;
                            // System.out.println("top = true");
                    }

            // SIDE OF TOUCH :  TOP(baseFrame) to  bottomTouch(new Frame)
                     if( botttomClass.yPos1 >= (( getLocation().y - baseframe_h )))
                        if( botttomClass.xPos1 >= (( getLocation().x - baseframe_w  )))
                            if( botttomClass.xPos1 <= (( getLocation().x + baseframe_w  )))
                                 if( topSide )
                        {
                              setLocation(xPos, yPos);
                              turn_Ylow = false;
                              turn_Yup  = true;
                           // System.out.println("top");
                         }

//***** LEFT
            // condition for only :  LEFT(baseFrame) to  rightTouch(new Frame)
               if (  botttomClass.xPos1 >= ( getLocation().x - baseframe_w  + 20 ) )  {
                              leftSide = false; // System.out.println("left = false");
                    } else  { leftSide = true;
                             // System.out.println("left = true");
                            }

            // SIDE OF TOUCH :  LEFT(baseFrame) to  rightTouch(new Frame)
                    if( botttomClass.xPos1 >= ( getLocation().x - baseframe_w ) )   // x = 0
                        if( botttomClass.yPos1 <= (( getLocation().y + baseframe_h  )) ) // y  = height   484
                             if((  botttomClass.yPos1 >= ( getLocation().y - baseframe_h )) ) // y = 0   284
                                  if( leftSide )
                 {
                              setLocation(xPos, yPos);
                             turn_Xlow = false;
                             turn_Xup  = true;
                   // System.out.println("left");
                 }
// ***** BOTTOM
            // condition for only :  BOTTOM(baseFrame) to  topTouch(new Frame)
                if (  botttomClass.yPos1 <= ( getLocation().y + baseframe_h  - 20 ) )  {
                              bottomSide = false; //System.out.println("bottom = false");
                    } else  { bottomSide = true;
                             // System.out.println("bottom = true");
                            }

            // SIDE OF TOUCH :  BOTTOM(baseFrame) to  topTouch(new Frame)
                     if( botttomClass.yPos1 <= (( getLocation().y + baseframe_h )))
                        if( botttomClass.xPos1 >= (( getLocation().x - baseframe_w  )))
                            if( botttomClass.xPos1 <= (( getLocation().x + baseframe_w  )))
                                    if( bottomSide )
                      {
                           setLocation(xPos, yPos);
                           turn_Yup = false;
                           turn_Ylow = true;
                        //  System.out.println("bottom");
                      }
// ****** RIGHT
           // condition for only :  RIGHT(baseFrame) to  leftTouch(new Frame)
                if (  botttomClass.xPos1 <= ( getLocation().x + baseframe_w  - 20 ) )  {
                              rightSide = false; // System.out.println("right = false");
                    } else  { rightSide = true;
                             // System.out.println("right = true");
                            }

           // SIDE OF TOUCH :  RIGHT(baseFrame) to  leftTouch(new Frame)
                  if( botttomClass.xPos1 <= ( getLocation().x + baseframe_w ) )   // x = 0
                      if( botttomClass.yPos1 <= (( getLocation().y + baseframe_h  )) ) // y  = height   484
                           if((  botttomClass.yPos1 >= ( getLocation().y - baseframe_h )) ) // y = 0   284
                                 if( rightSide )
                 {
                       setLocation(xPos, yPos);
                       turn_Xup = false;
                       turn_Xlow = true;
//                    System.out.println("right");
                 }
// *****
                    }
//---------------------------- end botttomClass ------------------------------------------------
                    
//---------------------------- start of moveToTop ----------------------------------------------
       //  CONDITION for dispaying new Frame : class --> moveToTop  ******************************
          if( displayTop ) {

               topClass = new moveToTop();
               topClass.topTest = true;  // condition for start of Thread
               Thread  thr1 = new Thread(  topClass  ); // instance of Thread
               thr1.start();
               displayTop = false;
               testtop = true;
               temp2 = true;
               shutdown = false;

                if( num == 0 )  shutdown = false;
                num ++; // total limitation frames on screen
//                if( num == 4 ) {
//                             shutdown = true; // stop all  threads class
//                             temp2 = false;
//                             System.out.println("num :::: " + num );
//                             num = 0;
//                     }
               }
         if( testtop )   {  // start of algorythm :: topClass
                       TOUCHING_FRAMES.this.SCHEMA_MOVE(  topSide,
                                               leftSide,
                                               rightSide,
                                               bottomSide,
                                               baseframe_h,
                                               baseframe_w,
                                               topClass.xPos1,
                                               topClass.yPos1,
                                               getLocation().x,
                                               getLocation().y
                                               );

                    }
  //   CONDITION for dispaying new Frame : class --> moveToRight  ******************************
        
          if( displayRight ) {

               rightClass = new moveToRight();
               rightClass.rightTest = true;  // condition for start of Thread
               Thread  thr1 = new Thread(  rightClass  ); // instance of Thread
               thr1.start();
               displayRight = false;
               testright = true;
               temp3 = true;
               shutdown = false;

                if( num == 0 )  shutdown = false;
                num ++; // total limitation frames on screen
//                if( num == 4 ) {
//                             shutdown = true; // stop all  threads
//                             temp3 = false;
//                             System.out.println("num :: " + num );
//                             num = 0;
//                     }
               }
       if( testright )   {  // start of algorythm :: topClass
                       TOUCHING_FRAMES.this.SCHEMA_MOVE(
                                               topSide,
                                               leftSide,
                                               rightSide,
                                               bottomSide,
                                               baseframe_h,
                                               baseframe_w,
                                               rightClass.xPos1,
                                               rightClass.yPos1,
                                               getLocation().x,
                                               getLocation().y
                                               );

                    }


// ******************* common action og thread ******************************************
                // moving in left direction
                  xPos -= 10;
//                    if( !turnX  ) // for isolation of moving to right side
//                     if( xPos <= 0) {
//                     xPos -= 10;
//                     turn_Xup = true;
//                     turn_Ylow = true;
                     turnX = true;
//                     cnt++;
//                     ccnt++;
//                     setBackground(setTouchColor(ccnt));
//                     labelCnt.setText("Touch " + cnt);
//                           }
// if ( !temp ) {
                    // right and up   (+)
                 if( turn_Xup && turn_Ylow ) {
                        xPos += 20; // 20
                        yPos -= 10;
                        if( yPos <= 0 ) {  // TOP SIDE: 1+     6+
                            yPos = 0;
                            turn_Ylow = false;
                            turn_Yup = true;
//                            System.out.println("turn_Xup && turn_Ylow 1");
                             cnt++;
                             ccnt++;
                             setBackground(setTouchColor(ccnt));
                             labelCnt.setText("Touch " + cnt);
                             if( !temp2 )
                             displayTop = true; //  for creating new Frame class:: moveToTop
                        }
                        if( xPos >= screenWidth  ) { // RIGHT SIDE: 3+
                            xPos =  screenWidth ;
                            turn_Xup = false;
                            turn_Xlow = true;
//                            System.out.println("turn_Xup && turn_Ylow 2");
                             cnt++;
                             ccnt++;
                             setBackground(setTouchColor(ccnt));
                             labelCnt.setText("Touch " + cnt);
                             if ( !temp3 )
                             displayRight = true;//  for creating new Frame class:: moveToRight

                        }
                    }
                    //  right and low   (+)
                 if( turn_Xup && turn_Yup ) {
                        xPos += 20; // 15
                        yPos += 10;  // +10
                        if( yPos >= screenHeight ) {  // BOTTOM SIDE: 2+
                             yPos = screenHeight;
                             turn_Yup = false;
                             turn_Ylow = true;
//                             System.out.println("turn_Xup && turn_Yup 1");
                              cnt++;
                              ccnt++;
                              setBackground(setTouchColor(ccnt));
                              labelCnt.setText("Touch " + cnt);
                                 if ( !temp )
                             displayBottom = true;  //  for creating new Frame class:: moveToBottom
                        }
                        if( xPos >= screenWidth ) { // RIGHT SIDE: may be
                            xPos = screenWidth ;
                            turn_Xup  = false;
                            turn_Xlow = true;
//                            System.out.println("turn_Xup && turn_Yup 2");
                              cnt++;
                              ccnt++;
                              setBackground(setTouchColor(ccnt));
                              labelCnt.setText("Touch " + cnt);
                               if ( !temp3 )
                             displayRight = true;//  for creating new Frame class:: moveToRight
                     }
                    }
                     
                   // from BOTTOM to --> left and up  (-)
                  if( turn_Xlow && turn_Ylow ) {
//                        xPos -= 10;
                        yPos -= 10;  // -10
                        if( yPos <= 0 ) {  // TOP SIDE: 4-
                            yPos = 0;
                            turn_Ylow = false;
                            turn_Yup = true;
//                            System.out.println("turn_Xlow && turn_Ylow 1");
                              cnt++;  // for iteration touches
                              ccnt++; // for iteration color
                              setBackground(setTouchColor(ccnt));
                              labelCnt.setText("Touch " + cnt);
                            if( !temp2 )
                             displayTop = true; //  for creating new Frame class:: moveToTop
                      }
                        if( xPos <= 0 ) {  // LEFT SIDE:
                            xPos = 0;
                            turn_Xlow = false;
                            turn_Xup  = true;
//                            System.out.println("turn_Xlow && turn_Ylow 2");
                              cnt++;
                              ccnt++;
                              setBackground(setTouchColor(ccnt));
                              labelCnt.setText("Touch " + cnt);
                        }

                  }

                   //  from UP to --> left and bottom
                  if( turn_Xlow && turn_Yup ) {
                        xPos -= 10;  // -10
                        yPos += 10; // +10
                        if( yPos >= screenHeight )  { // BOTTOM SIDE: 5-
                            yPos = screenHeight;
                            turn_Yup = false;
                            turn_Ylow = true;
//                            System.out.println("turn_Xlow && turn_Yup 1");
                              cnt++;
                              setBackground(setTouchColor(ccnt));
                              labelCnt.setText("Touch " + cnt);
                                 if ( !temp )
                             displayBottom = true;  //  for creating new Frame class:: moveToBottom
              
                        }
                        if( xPos <= 0 ) { // LEFT SIDE:  may  be to
                            xPos = 0;
                            turn_Xlow = false;
                            turn_Xup = true;
//                            System.out.println("turn_Xlow && turn_Yup 2");
                              cnt++;
                              ccnt++;
                              setBackground(setTouchColor(ccnt));
                              labelCnt.setText("Touch " + cnt);
                      }
                  }
                    setLocation( xPos, yPos );
//                    }
//                 if( (botttomClass != null ) && ( topClass != null )&& ( rightClass != null ))
//                    if( !botttomClass.isVisible() )
//                       if( !topClass.isVisible() )
//                           if( !rightClass.isVisible() )
//                               if( reset)  {
//                                   testright = false;
//                                   testtop = false;
//                                   testbottom = false;
//                                   temp  = false;
//                                   temp2  = false;
//                                   temp3  = false;
//                                   num = 4;
//                                   System.out.println("NUM == 4");
//                                   reset = false;
//                           }
                    if( (botttomClass != null ) && ( topClass != null )&& ( rightClass != null )) {
                     if( !botttomClass.isVisible() )  {  testbottom = false;  temp  = false;}
                     if( !topClass.isVisible() )      {  testtop = false; temp2  = false; }
                     if( !rightClass.isVisible() )    {  testright = false; temp3  = false;}
                    }
                } catch (InterruptedException ex) {  }
       }
    }
  };
       boolean reset = true;

// ********************* For moving to Right Direction *************************
// *****************************************************************************
       class  moveToRight  extends Frame implements Runnable {
           private   boolean rightTest = false;    // turn on thread -->
           private   boolean rightRTest = false;   // for moving to right Direction and isolation
           private   boolean r_Xup = false;    // for increasing X coordinate
           private   boolean r_Yup = false;    // for increasing Y coordinate
           private   boolean r_Xlow = false;   // for decreasing X coordinate
           private   boolean r_Ylow = false;   // for decreasing Y coordinate
           private   int xPos1;
           private   int yPos1;
           private   int colorCnt;
           private   int touchCnt;
           private   JLabel  label;
           private   GridBagConstraints c0;

           moveToRight() {
               createBaseFrame(num);
           }

     private  void createBaseFrame(int title) {
              num = title;
              setTitle("-== " + num + " ==-");
              setLayout(new GridBagLayout());
              setAlwaysOnTop(true);
              setSize(200, 200);
              setMinimumSize(new Dimension(200, 200));
              setPreferredSize(new Dimension(200, 200));
              addKeyListener(new key());
              Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
              Dimension framesize = getSize();
              int x = ((screensize.width  -  framesize.width )/ 2 ) - 3;
              int y = ((screensize.height  -  framesize.height  )/ 2) - 4;
              xPos1 = x;
              yPos1 = y;
              setLocation( xPos1, yPos1 );
              c0 = new GridBagConstraints();
              c0.fill = GridBagConstraints.HORIZONTAL;
              add( getCountLabel(touchCnt) , c0 );
              setVisible(true);
           }

     private JLabel getCountLabel(int count) {
            label = new JLabel("Touch " + count + "    " );
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setHorizontalTextPosition( JLabel.CENTER);
            label.setMinimumSize(new Dimension(getSize().width , getSize().height));
            label.setPreferredSize(label.getMinimumSize());
            label.setFont(new Font("Serif" , Font.BOLD ,  32));
            return label;
     }

      private Color setTouchColor(int index) {
        if( index == colors.length ) {
            index = 0;
            colorCnt = index;
        }
      return colors[index];
    }

       private void SCHEMA_MOVE_FOR_rightFRAME(   boolean topSide ,      //
                                                  boolean leftSide ,     //
                                                  boolean rightSide ,    //
                                                  boolean bottomSide ,   //
                                                  int  frame1_h ,        // moveToTop.this.getSize().height
                                                  int  frame1_w ,        // moveToTop.this.getSize().width
                                                  int  x_Pos ,           // xPos(baseFrame)
                                                  int  y_Pos ,           // yPos(baseFrame)
                                                  int  x ,               // moveToTop.this.getLocation().x
                                                  int  y                 // moveToTop.this.getLocation().y
                                )  {

           // ***** TOP
            // condition for only :  TOP(top Frame) to  bottomTouch( base Frame )
                    if ( (  y_Pos >= (( y - frame1_h + 20 )))
                       ) {
                              topSide = false; //System.out.println("top = false");
                    } else  { topSide = true;
                            // System.out.println("top = true");
                    }

            // SIDE OF TOUCH :   TOP(top Frame) to  bottomTouch( base Frame )
                     if( y_Pos >= (( y - frame1_h ))) {
                        if( x_Pos >= (( x - frame1_w  )))
                            if( x_Pos <= (( x + frame1_w  )))
                                 if( topSide )
                        {
                            r_Ylow = false;
                            r_Yup = true;
                           // System.out.println("top");
                         }
                    }
//***** LEFT
            // condition for only :  LEFT(top Frame) to  rightTouch( base Frame )
               if (  x_Pos >= ( x - frame1_w  + 20 ) ) {
                              leftSide = false; // System.out.println("left = false");
                    } else  { leftSide = true;
                             // System.out.println("left = true");
                            }

            // SIDE OF TOUCH :  LEFT(top Frame) to  rightTouch( base Frame )
                          if( x_Pos >= ( x - frame1_w ) )   // x = 0
                               if( y_Pos <= (( y + frame1_h  )) ) // y  = height   484
                                   if((  y_Pos >= ( y - frame1_h )) ) // y = 0   284
                                       if( leftSide )
                 {
                     r_Xlow = false;
                     r_Xup  = true;
                   // System.out.println("left");
                 }
// ***** BOTTOM
            // condition for only :  BOTTOM(top Frame) to  topTouch( base Frame )
                if (  y_Pos <= ( y + frame1_h  - 20 ) )  {
                              bottomSide = false; //System.out.println("bottom = false");
                    } else  { bottomSide = true;
                             // System.out.println("bottom = true");
                            }

            // SIDE OF TOUCH :  BOTTOM(top Frame) to  topTouch( base Frame )
                     if( y_Pos <= (( y + frame1_h )))
                        if( x_Pos >= (( x - frame1_w  )))
                            if( x_Pos <= (( x + frame1_w  )))
                                    if( bottomSide )
                      {
                         r_Yup = false;
                         r_Ylow = true;
                        //  System.out.println("bottom");
                      }
// ****** RIGHT
           // condition for only :  RIGHT( top Frame) to  leftTouch( base Frame )
                if (  x_Pos <= ( x + frame1_w  - 20 ) )  {
                              rightSide = false; // System.out.println("right = false");
                    } else  { rightSide = true;
                             // System.out.println("right = true");
                            }

           // SIDE OF TOUCH :  RIGHT(top Frame) to  leftTouch( base Frame )
                  if( x_Pos <= ( x + frame1_w ) )   // x = 0
                      if( y_Pos <= (( y + frame1_h  )) ) // y  = height   484
                           if((  y_Pos >= ( y - frame1_h )) ) // y = 0   284
                                 if( rightSide )
                 {
                     r_Xup = false;
                     r_Xlow = true;
//                    System.out.println("right");
                 }

      }

        @Override
    public void run(){
          Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
           int screenWidth = screensize.width - 196;
          int screenHeight = screensize.height - 208; // it is real height of screen !;

          int frame1_w = getSize().width;  // width of top frame
          int frame1_h = getSize().height; // height of top frame

           boolean leftSide = false;
           boolean topSide  = false;
           boolean rightSide = false;
           boolean bottomSide = false;

           boolean test_base = true; //  for start 'bottom' condition
           boolean test_bottom    = true; //  for start 'top'  condition
           boolean test_top    = true; //  for start 'right'  condition

               r_Xlow = true;
               r_Yup  =  true;
       while( rightTest ) {
                try {
                                
                    Thread.currentThread().sleep(200);
//********************** right TOUCH to baseFrame **************************************************
            if( test_base )
        moveToRight.this.SCHEMA_MOVE_FOR_rightFRAME(  topSide,
                                                     leftSide,
                                                     rightSide,
                                                     bottomSide,
                                                     frame1_h,
                                                     frame1_w,
                                                     xPos,
                                                     yPos,
                                                     moveToRight.this.getLocation().x,
                                                     moveToRight.this.getLocation().y
                                                     );
//**************************************************************************************************
//********************** right TOUCH to topFrame **************************************************
      if( topClass !=  null)
          if( test_top )
        moveToRight.this.SCHEMA_MOVE_FOR_rightFRAME(  topSide,
                                                     leftSide,
                                                     rightSide,
                                                     bottomSide,
                                                     frame1_h,
                                                     frame1_w,
                                                     topClass.xPos1,
                                                     topClass.yPos1,
                                                     moveToRight.this.getLocation().x,
                                                     moveToRight.this.getLocation().y
                                                     );
//**************************************************************************************************
//********************** right TOUCH to botttomFrame **************************************************
       if( botttomClass !=  null)
           if( test_bottom )
        moveToRight.this.SCHEMA_MOVE_FOR_rightFRAME(  topSide,
                                                     leftSide,
                                                     rightSide,
                                                     bottomSide,
                                                     frame1_h,
                                                     frame1_w,
                                                     botttomClass.xPos1,
                                                     botttomClass.yPos1,
                                                     moveToRight.this.getLocation().x,
                                                     moveToRight.this.getLocation().y
                                                     );
//**************************************************************************************************



                    // move frame1 to right direction
                         xPos1 += 10;
//                    if( !rightRTest )
//                        if( xPos1 >= screenWidth ) {  //  0
//                            xPos1 = screenWidth;
//                            rightRTest =  true;
//                            r_Xlow = true;
//                            r_Yup  =  true;
//                              touchCnt++;  // for iteration touches
//                              colorCnt++; // for iteration color
//                              this.setBackground(setTouchColor(colorCnt));
//                              this.label.setText("Touch " + touchCnt+ "    ");
//                        }

                   // DIRECTION: bottom and left
                   if( r_Xlow && r_Yup ) {
                         xPos1 -= 20;  // -20
                         yPos1 += 10;
                         if( yPos1 >= screenHeight ) { // BOTTOM SIDE : 1
                             yPos1 = screenHeight;
                             r_Yup = false;
                             r_Ylow = true;
                              touchCnt++;  // for iteration touches
                              colorCnt++; // for iteration color
                              this.setBackground(setTouchColor(colorCnt));
                              this.label.setText("Touch " + touchCnt + "    ");
                         }
                          if( xPos1 <= 0 ) { // LEFT SIDE: 3
                              xPos1 = 0;
                              r_Xlow = false;
                              r_Xup  = true;
                                  touchCnt++;  // for iteration touches
                                  colorCnt++; // for iteration color
                                  this.setBackground(setTouchColor(colorCnt));
                                  this.label.setText("Touch " + touchCnt + "    ");
                              }
                     }
                   // DIRECTION: up and left
                   if( r_Xlow && r_Ylow ) {
                         xPos1 -= 20; // -20
                         yPos1 -= 10;
                         if( xPos1 <= 0 ) {   // RIGHT SIDE:
                             xPos1 = 0;
                             r_Xlow = false;
                             r_Xup  = true;
                                 touchCnt++;  // for iteration touches
                                 colorCnt++; // for iteration color
                                 this.setBackground(setTouchColor(colorCnt));
                                 this.label.setText("Touch " + touchCnt + "    ");
                         }
                         if( yPos1 <= 0 ) {    // TOP SIDE:  2
                             yPos1 = 0;
                             r_Ylow = false;
                             r_Yup  = true;
                                 touchCnt++;  // for iteration touches
                                 colorCnt++; // for iteration color
                                 this.setBackground(setTouchColor(colorCnt));
                                 this.label.setText("Touch " + touchCnt + "    ");
                         }
                     }

                    // DIRECTION: bottom and right
                    if( r_Xup && r_Yup ) {
//                             xPos1 += 10;
                             yPos1 += 10;
                             if( yPos1 >= screenHeight ) { // BOTTOM SIDE: 4
                                 yPos1 = screenHeight;
                                 r_Yup = false;
                                 r_Ylow = true;
                                    touchCnt++;  // for iteration touches
                                    colorCnt++; // for iteration color
                                    this.setBackground(setTouchColor(colorCnt));
                                    this.label.setText("Touch " + touchCnt + "    ");
                             }
                             if( xPos1 >= screenWidth ) {  // RIGHT SIDE:
                                 xPos1 = screenWidth;
                                 r_Xup  = false;
                                 r_Xlow = true;
                                     touchCnt++;  // for iteration touches
                                     colorCnt++; // for iteration color
                                     this.setBackground(setTouchColor(colorCnt));
                                     this.label.setText("Touch " + touchCnt + "    ");
                             }
                         }
                      // DIRECTION: up and right
                      if( r_Xup && r_Ylow ) {
//                             xPos1 += 10;
                             yPos1 -= 10; // -15
                             if( xPos1 >= screenWidth ) { // RIGHT SIDE:
                                 xPos1 = screenWidth;
                                 r_Xup  = false;
                                 r_Xlow = true;
                                     touchCnt++;  // for iteration touches
                                     colorCnt++; // for iteration color
                                     this.setBackground(setTouchColor(colorCnt));
                                     this.label.setText("Touch " + touchCnt + "    ");
                             }
                             if( yPos1 <= 0 )  { // TOR SIDE:
                                 yPos1 = 0;
                                 r_Ylow = false;
                                 r_Yup  = true;
                                    touchCnt++;  // for iteration touches
                                    colorCnt++; // for iteration color
                                    this.setBackground(setTouchColor(colorCnt));
                                    this.label.setText("Touch " + touchCnt + "    ");
                             }
                      }
             totR++;
                //tot <-- time of life of this Thread
    if(totR == 2000) { rightTest = false;  totR = 0; this.setVisible(false); rightClass.setLocation( xPos1+2000 , yPos1+2000);
                     test_base = false; test_bottom = false; test_top = false;  }
                // shutdown <-- stop Thread
    if( shutdown ) {  rightTest = false; dispose(); setLocation( xPos1+2000 , yPos1+2000); repaint();  }
             
             if( rightClass.isVisible() )
                    rightClass.setLocation( xPos1 , yPos1 );
             else  { rightClass.setLocation( xPos1+2000 , yPos1+2000); test_base = false; test_bottom = false; test_top = false; }
                } catch (InterruptedException ex) {  }
        }
       }
        };


   //********************* MOVE TO TOP ******************************************
   //****************************************************************************
       class  moveToTop  extends Frame implements Runnable {
           private   boolean topTest = false;    // turn on thread -->
           private   boolean topRTest = false;   // for moving to right Direction and isolation
           private   boolean r_Xup = false;    // for increasing X coordinate
           private   boolean r_Yup = false;    // for increasing Y coordinate
           private   boolean r_Xlow = false;   // for decreasing X coordinate
           private   boolean r_Ylow = false;   // for decreasing Y coordinate
           private   int xPos1;
           private   int yPos1;
           private   int colorCnt;
           private   int touchCnt;
           private   JLabel  label;
           private   GridBagConstraints c0;
//           private   moveToBottom bottomClass=  null;

           moveToTop() {
               createBaseFrame(num);
           }

     private  void createBaseFrame(int title) {
              num = title;
              setTitle("-== " + num + " ==-");
              setAlwaysOnTop(true);
              setLayout(new GridBagLayout());
              setSize(200, 200);
              setMinimumSize(new Dimension(200, 200));
              setPreferredSize(new Dimension(200, 200));
              addKeyListener(new key());
              Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
              Dimension framesize = getSize();
              int x = ((screensize.width  -  framesize.width )/ 2 ) - 3;
              int y = ((screensize.height  -  framesize.height  )/ 2) - 4;
              xPos1 = x;
              yPos1 = y;
              setLocation( xPos1, yPos1 );
              c0 = new GridBagConstraints();
              c0.fill = GridBagConstraints.HORIZONTAL;
              add( getCountLabel(touchCnt) , c0 );
              setVisible(true);
           }

     private JLabel getCountLabel(int count) {
            label = new JLabel("Touch " + count + "    " );
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setHorizontalTextPosition( JLabel.CENTER);
            label.setMinimumSize(new Dimension(getSize().width , getSize().height));
            label.setPreferredSize(label.getMinimumSize());
            label.setFont(new Font("Serif" , Font.BOLD ,  32));
            return label;
     }

     private Color setTouchColor(int index) {
        if( index == colors.length ) {
            index = 0;
            colorCnt = index;
        }
      return colors[index];
    }
                                                                      //  *base frame*                     *bottom frame*
      private void SCHEMA_MOVE_FOR_TOPFRAME(   boolean topSide ,      //
                                               boolean leftSide ,     //
                                               boolean rightSide ,    //
                                               boolean bottomSide ,   //
                                               int  frame1_h ,        // moveToTop.this.getSize().height
                                               int  frame1_w ,        // moveToTop.this.getSize().width
                                               int  x_Pos ,           // xPos(baseFrame)
                                               int  y_Pos ,           // yPos(baseFrame)
                                               int  x ,               // moveToTop.this.getLocation().x
                                               int  y                 // moveToTop.this.getLocation().y
                                )  {

           // ***** TOP
            // condition for only :  TOP(top Frame) to  bottomTouch( base Frame )
                    if ( (  y_Pos >= (( y - frame1_h + 20 )))
                       ) {
                              topSide = false; //System.out.println("top = false");
                    } else  { topSide = true;
                            // System.out.println("top = true");
                    }

            // SIDE OF TOUCH :   TOP(top Frame) to  bottomTouch( base Frame )
                     if( y_Pos >= (( y - frame1_h ))) {
                        if( x_Pos >= (( x - frame1_w  )))
                            if( x_Pos <= (( x + frame1_w  )))
                                 if( topSide )
                        {
                            r_Ylow = false;
                            r_Yup = true;
                           // System.out.println("top");
                         }
                    }
//***** LEFT
            // condition for only :  LEFT(top Frame) to  rightTouch( base Frame )
               if (  x_Pos >= ( x - frame1_w  + 20 ) ) {
                              leftSide = false; // System.out.println("left = false");
                    } else  { leftSide = true;
                             // System.out.println("left = true");
                            }

            // SIDE OF TOUCH :  LEFT(top Frame) to  rightTouch( base Frame )
                          if( x_Pos >= ( x - frame1_w ) )   // x = 0
                               if( y_Pos <= (( y + frame1_h  )) ) // y  = height   484
                                   if((  y_Pos >= ( y - frame1_h )) ) // y = 0   284
                                       if( leftSide )
                 {
                     r_Xlow = false;
                     r_Xup  = true;
                   // System.out.println("left");
                 }
// ***** BOTTOM
            // condition for only :  BOTTOM(top Frame) to  topTouch( base Frame )
                if (  y_Pos <= ( y + frame1_h  - 20 ) )  {
                              bottomSide = false; //System.out.println("bottom = false");
                    } else  { bottomSide = true;
                             // System.out.println("bottom = true");
                            }

            // SIDE OF TOUCH :  BOTTOM(top Frame) to  topTouch( base Frame )
                     if( y_Pos <= (( y + frame1_h )))
                        if( x_Pos >= (( x - frame1_w  )))
                            if( x_Pos <= (( x + frame1_w  )))
                                    if( bottomSide )
                      {
                         r_Yup = false;
                         r_Ylow = true;
                        //  System.out.println("bottom");
                      }
// ****** RIGHT
           // condition for only :  RIGHT( top Frame) to  leftTouch( base Frame )
                if (  x_Pos <= ( x + frame1_w  - 20 ) )  {
                              rightSide = false; // System.out.println("right = false");
                    } else  { rightSide = true;
                             // System.out.println("right = true");
                            }

           // SIDE OF TOUCH :  RIGHT(top Frame) to  leftTouch( base Frame )
                  if( x_Pos <= ( x + frame1_w ) )   // x = 0
                      if( y_Pos <= (( y + frame1_h  )) ) // y  = height   484
                           if((  y_Pos >= ( y - frame1_h )) ) // y = 0   284
                                 if( rightSide )
                 {
                     r_Xup = false;
                     r_Xlow = true;
//                    System.out.println("right");
                 }

      }

        @Override
    public void run(){
          Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
          int screenWidth = screensize.width - 196;
          int screenHeight = screensize.height - 208; // it is real height of screen !;

          int frame1_w = getSize().width;  // width of top frame
          int frame1_h = getSize().height; // height of top frame

           boolean leftSide = false;
           boolean topSide  = false;
           boolean rightSide = false;
           boolean bottomSide = false;

           boolean test_base = true; //  for start 'bottom' condition
           boolean test_bot    = true; //  for start 'top'  condition
           boolean test_right    = true; //  for start 'right'  condition

               r_Xlow = true;
               r_Yup  =  true;
       while( topTest ) {
                       
                try {
                    Thread.currentThread().sleep(200);
//**********************************************************************************************************************
//*******************************TOP touch with baseFrame in Thread ANIMA***********************************************
                    if( test_base )
             moveToTop.this.SCHEMA_MOVE_FOR_TOPFRAME(topSide,
                                                     leftSide,
                                                     rightSide,
                                                     bottomSide,
                                                     frame1_h,
                                                     frame1_w,
                                                     xPos,
                                                     yPos,
                                                     moveToTop.this.getLocation().x,
                                                     moveToTop.this.getLocation().y
                                                     );

// ********************************* end of touch with base frame ***************************************

// **********************************TOP touch with class bottomToTop **************************************
             if(botttomClass != null)
                 if( test_bot )
              moveToTop.this.SCHEMA_MOVE_FOR_TOPFRAME(topSide,
                                                     leftSide,
                                                     rightSide,
                                                     bottomSide,
                                                     frame1_h,
                                                     frame1_w,
                                                     botttomClass.xPos1,
                                                     botttomClass.yPos1,
                                                     moveToTop.this.getLocation().x,
                                                     moveToTop.this.getLocation().y
                                                     );

// ********************************* end of touch with moveTobottom class**********************************

// **********************************TOP touch with class moveToRight **************************************
      if( rightClass != null)
          if( test_right )
              moveToTop.this.SCHEMA_MOVE_FOR_TOPFRAME(topSide,
                                                     leftSide,
                                                     rightSide,
                                                     bottomSide,
                                                     frame1_h,
                                                     frame1_w,
                                                     rightClass.xPos1,
                                                     rightClass.yPos1,
                                                     moveToTop.this.getLocation().x,
                                                     moveToTop.this.getLocation().y
                                                     );

// ********************************* end of touch with moveToRight class**********************************




//***********************    common algorythm  ************************************************************

                    // move frame1 to right direction
                         yPos1 -= 10;
//                    if( !topRTest )
//                        if( yPos1 <= 0  ) {  //  0
//                            yPos1 =  0;
//                            topRTest =  true;
//                            r_Xlow = true;
//                            r_Yup  =  true;
//                              touchCnt++;  // for iteration touches
//                              colorCnt++; // for iteration color
//                              this.setBackground(setTouchColor(colorCnt));
//                              this.label.setText("Touch " + touchCnt+ "    ");
//                        }

                   // DIRECTION: bottom and left
                   if( r_Xlow && r_Yup ) {
                         xPos1 -= 10;  // -20
                         yPos1 += 20;  // +20
                         if( yPos1 >= screenHeight ) { // BOTTOM SIDE : 1
                             yPos1 = screenHeight;
                             r_Yup = false;
                             r_Ylow = true;
                              touchCnt++;  // for iteration touches
                              colorCnt++; // for iteration color
                              this.setBackground(setTouchColor(colorCnt));
                              this.label.setText("Touch " + touchCnt + "    ");
                         }
                          if( xPos1 <= 0 ) { // LEFT SIDE: 3
                              xPos1 = 0;
                              r_Xlow = false;
                              r_Xup  = true;
                                  touchCnt++;  // for iteration touches
                                  colorCnt++; // for iteration color
                                  this.setBackground(setTouchColor(colorCnt));
                                  this.label.setText("Touch " + touchCnt + "    ");
                              }
                     }
                   // DIRECTION: up and left
                   if( r_Xlow && r_Ylow ) {
                         xPos1 -= 10;  // -10
//                         yPos1 -= 15;  // -20
                         if( xPos1 <= 0 ) {   // RIGHT SIDE:
                             xPos1 = 0;
                             r_Xlow = false;
                             r_Xup  = true;
                                 touchCnt++;  // for iteration touches
                                 colorCnt++; // for iteration color
                                 this.setBackground(setTouchColor(colorCnt));
                                 this.label.setText("Touch " + touchCnt + "    ");
                         }
                         if( yPos1 <= 0 ) {    // TOP SIDE:  2
                             yPos1 = 0;
                             r_Ylow = false;
                             r_Yup  = true;
                                 touchCnt++;  // for iteration touches
                                 colorCnt++; // for iteration color
                                 this.setBackground(setTouchColor(colorCnt));
                                 this.label.setText("Touch " + touchCnt + "    ");
                         }
                     }

                    // DIRECTION: bottom and right
                    if( r_Xup && r_Yup ) {
                             xPos1 += 10; // 15
                             yPos1 += 20; // 20
                             if( yPos1 >= screenHeight ) { // BOTTOM SIDE: 4
                                 yPos1 = screenHeight;
                                 r_Yup = false;
                                 r_Ylow = true;
                                    touchCnt++;  // for iteration touches
                                    colorCnt++; // for iteration color
                                    this.setBackground(setTouchColor(colorCnt));
                                    this.label.setText("Touch " + touchCnt + "    ");
                             }
                             if( xPos1 >= screenWidth ) {  // RIGHT SIDE:
                                 xPos1 = screenWidth;
                                 r_Xup  = false;
                                 r_Xlow = true;
                                     touchCnt++;  // for iteration touches
                                     colorCnt++; // for iteration color
                                     this.setBackground(setTouchColor(colorCnt));
                                     this.label.setText("Touch " + touchCnt + "    ");
                             }
                         }
                      // DIRECTION: up and right
                      if( r_Xup && r_Ylow ) {
                             xPos1 += 10;
//                             yPos1 -= 10;
                             if( xPos1 >= screenWidth ) { // RIGHT SIDE:
                                 xPos1 = screenWidth;
                                 r_Xup  = false;
                                 r_Xlow = true;
                                     touchCnt++;  // for iteration touches
                                     colorCnt++; // for iteration color
                                     this.setBackground(setTouchColor(colorCnt));
                                     this.label.setText("Touch " + touchCnt + "    ");
                             }
                             if( yPos1 <= 0 )  { // TOR SIDE:
                                 yPos1 = 0;
                                 r_Ylow = false;
                                 r_Yup  = true;
                                    touchCnt++;  // for iteration touches
                                    colorCnt++; // for iteration color
                                    this.setBackground(setTouchColor(colorCnt));
                                    this.label.setText("Touch " + touchCnt + "    ");
                             }
                      }

            totT++;
       //tot <-- time of life of this Thread
    if(totT == 2000) { topTest = false;  totT = 0;  this.setVisible(false); topClass.setLocation( xPos1+2000 , yPos1+2000);
                     test_base = false; test_right = false; test_right = false;  }
       // shutdown <-- stop Thread
    if( shutdown ) {  topTest = false; dispose(); this.setLocation( xPos1+2000 , yPos1+2000); repaint();   }

            if( topClass.isVisible() )
                    topClass.setLocation( xPos1 , yPos1 );
            else  {  topClass.setLocation( xPos1+2000 , yPos1+2000 );  test_base = false; test_right = false; test_right = false;  }
                } catch (InterruptedException ex) {  }
        }
       }
        };

   //********************* MOVE TO BOTTOM ******************************************
   //****************************************************************************
       class  moveToBottom  extends Frame implements Runnable {
           private   boolean bottomTest = false;    // turn on thread -->
           private   boolean bottomRTest = false;   // for moving to right Direction and isolation
           private   boolean r_Xup = false;    // for increasing X coordinate
           private   boolean r_Yup = false;    // for increasing Y coordinate
           private   boolean r_Xlow = false;   // for decreasing X coordinate
           private   boolean r_Ylow = false;   // for decreasing Y coordinate
           private   int xPos1;
           private   int yPos1;
           private   int colorCnt;
           private   int touchCnt;
           private   JLabel  label;
           private   GridBagConstraints c0;

           moveToBottom() {
               createBaseFrame(num);
//               tot++;
//               if ( shutdown ) dispose(); setLocation( xPos1+2000 , yPos1+2000); repaint();
           }



     private  void createBaseFrame(int title) {
              num = title;
              setTitle("-== " + num + " ==-");
              setAlwaysOnTop(true);
              setLayout(new GridBagLayout());
              setSize(200, 200);
              setMinimumSize(new Dimension(200, 200));
              setPreferredSize(new Dimension(200, 200));
              addKeyListener(new key());
              Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
              Dimension framesize = getSize();
             int x = ((screensize.width  -  framesize.width )/ 2 ) - 3;
             int y = ((screensize.height  -  framesize.height  )/ 2) - 4;
              xPos1 = x;
              yPos1 = y;
              setLocation( xPos1, yPos1 );
              c0 = new GridBagConstraints();
              c0.fill = GridBagConstraints.HORIZONTAL;
              add( getCountLabel(touchCnt) , c0 );
              setVisible(true);
           }

     private JLabel getCountLabel(int count) {
            label = new JLabel("Touch " + count + "    " );
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setHorizontalTextPosition( JLabel.CENTER);
            label.setMinimumSize(new Dimension(getSize().width , getSize().height));
            label.setPreferredSize(label.getMinimumSize());
            label.setFont(new Font("Serif" , Font.BOLD ,  32));
            return label;
     }

      private Color setTouchColor(int index) {
        if( index == colors.length ) {
            index = 0;
            colorCnt = index;
        }
      return colors[index];
    }

       private void SCHEMA_MOVE_FOR_BOTTOMFRAME(   boolean topSide ,  //
                                                   boolean leftSide ,     //
                                                   boolean rightSide ,    //
                                                   boolean bottomSide ,   //
                                                   int  frame1_h ,        // moveToTop.this.getSize().height
                                                   int  frame1_w ,        // moveToTop.this.getSize().width
                                                   int  x_Pos ,           // xPos(baseFrame)
                                                   int  y_Pos ,           // yPos(baseFrame)
                                                   int  x ,               // moveToTop.this.getLocation().x
                                                   int  y                 // moveToTop.this.getLocation().y
                                )  {

           // ***** TOP
            // condition for only :  TOP(bottom Frame) to  bottomTouch( base Frame )
                    if ( (  y_Pos >= (( y - frame1_h + 20 )))
                       ) {
                              topSide = false; //System.out.println("top = false");
                    } else  { topSide = true;
                            // System.out.println("top = true");
                    }

            // SIDE OF TOUCH :   TOP(bottom Frame) to  bottomTouch( base Frame )
                     if( y_Pos >= (( y - frame1_h ))) {
                        if( x_Pos >= (( x - frame1_w  )))
                            if( x_Pos <= (( x + frame1_w  )))
                                 if( topSide )
                        {
                            r_Ylow = false;
                            r_Yup = true;
                           // System.out.println("top");
                         }
                    }
//***** LEFT
            // condition for only :  LEFT(bottom Frame) to  rightTouch( base Frame )
               if (  x_Pos >= ( x - frame1_w  + 20 ) ) {
                              leftSide = false; // System.out.println("left = false");
                    } else  { leftSide = true;
                             // System.out.println("left = true");
                            }

            // SIDE OF TOUCH :  LEFT(bottom Frame) to  rightTouch( base Frame )
                          if( x_Pos >= ( x - frame1_w ) )   // x = 0
                               if( y_Pos <= (( y + frame1_h  )) ) // y  = height   484
                                   if((  y_Pos >= ( y - frame1_h )) ) // y = 0   284
                                       if( leftSide )
                 {
                     r_Xlow = false;
                     r_Xup  = true;
                   // System.out.println("left");
                 }
// ***** BOTTOM
            // condition for only :  BOTTOM(bottom Frame) to  topTouch( base Frame )
                if (  y_Pos <= ( y + frame1_h  - 20 ) )  {
                              bottomSide = false; //System.out.println("bottom = false");
                    } else  { bottomSide = true;
                             // System.out.println("bottom = true");
                            }

            // SIDE OF TOUCH :  BOTTOM(bottom Frame) to  topTouch( base Frame )
                     if( y_Pos <= (( y + frame1_h )))
                        if( x_Pos >= (( x - frame1_w  )))
                            if( x_Pos <= (( x + frame1_w  )))
                                    if( bottomSide )
                      {
                         r_Yup = false;
                         r_Ylow = true;
                        //  System.out.println("bottom");
                      }
// ****** RIGHT
           // condition for only :  RIGHT( bottom Frame) to  leftTouch( base Frame )
                if (  x_Pos <= ( x + frame1_w  - 20 ) )  {
                              rightSide = false; // System.out.println("right = false");
                    } else  { rightSide = true;
                             // System.out.println("right = true");
                            }

           // SIDE OF TOUCH :  RIGHT(bottom Frame) to  leftTouch( base Frame )
                  if( x_Pos <= ( x + frame1_w ) )   // x = 0
                      if( y_Pos <= (( y + frame1_h  )) ) // y  = height   484
                           if((  y_Pos >= ( y - frame1_h )) ) // y = 0   284
                                 if( rightSide )
                 {
                     r_Xup = false;
                     r_Xlow = true;
//                    System.out.println("right");
                 }

      }


           boolean leftSide = false;
           boolean topSide  = false;
           boolean rightSide = false;
           boolean bottomSide = false;
        @Override
    public void run(){
          Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
          int screenWidth = screensize.width - 196;
          int screenHeight = screensize.height - 208; // it is real height of screen !;

           int frame1_w = getSize().width;  // width of bottom frame
           int frame1_h = getSize().height; // height of bottom frame

           boolean testbase = true; //  for start 'bottom' condition
           boolean testtop    = true; //  for start 'top'  condition
           boolean testright    = true; //  for start 'right'  condition

             r_Xlow = true;
             r_Ylow = true;
           
       while( bottomTest ) {

                    try {
                    Thread.currentThread().sleep(200);

         if ( num > 0 ) {  // if exists more then one frame  on screen
//----------------------------------------------------------------------------------------------------------------------
//---------------------------------------------ACTION NEW CLASS AGAINST EXIST ------------------------------------------
//       moveToBottom.this.SCHEMA_MOVE_FOR_BOTTOMFRAME(topSide,
//                                                     leftSide,
//                                                     rightSide,
//                                                     bottomSide,
//                                                     frame1_h,
//                                                     frame1_w,
//                                                     botttomClass.xPos1,
//                                                     botttomClass.yPos1,
//                                                     moveToBottom.this.getLocation().x,
//                                                     moveToBottom.this.getLocation().y
//                                                     );

//======================================================================================================================
//=========================================ACTION EXIST CLASS AGINST NEW ONE============================================
         moveToBottom.this.SCHEMA_MOVE_FOR_BOTTOMFRAME(topSide,
                                                     leftSide,
                                                     rightSide,
                                                     bottomSide,
                                                     frame1_h,
                                                     frame1_w,
                                                     xPos1,
                                                     yPos1,
                                                     botttomClass.getLocation().x,
                                                     botttomClass.getLocation().y
                                                     );

//======================================================================================================================
          }
//**********************************************************************************************************************
//*******************************bottom TOUCH with baseFRAME in Thread ANIMA***********************************************
      if( testbase )
       moveToBottom.this.SCHEMA_MOVE_FOR_BOTTOMFRAME(moveToBottom.this.topSide,
                                                     moveToBottom.this.leftSide,
                                                     moveToBottom.this.rightSide,
                                                     moveToBottom.this.bottomSide,
                                                     frame1_h,
                                                     frame1_w,
                                                     xPos,
                                                     yPos,
                                                     moveToBottom.this.getLocation().x,
                                                     moveToBottom.this.getLocation().y
                                                     );
// ***************************bottom  TOUCH with moveToTop class ******************************************
    if ( topClass != null )  {
        if( testtop )
        moveToBottom.this.SCHEMA_MOVE_FOR_BOTTOMFRAME(moveToBottom.this.topSide,
                                                     moveToBottom.this.leftSide,
                                                     moveToBottom.this.rightSide,
                                                     moveToBottom.this.bottomSide,
                                                     frame1_h,
                                                     frame1_w,
                                                     topClass.xPos1,
                                                     topClass.yPos1,
                                                     moveToBottom.this.getLocation().x,
                                                     moveToBottom.this.getLocation().y
                                                     );
                    }
// ********************************* end of touch with moveToTop class***************************************

// ***************************bottom  TOUCH with moveToRight class ******************************************
    if ( rightClass != null )  {
         if( testright )
        moveToBottom.this.SCHEMA_MOVE_FOR_BOTTOMFRAME(moveToBottom.this.topSide,
                                                     moveToBottom.this.leftSide,
                                                     moveToBottom.this.rightSide,
                                                     moveToBottom.this.bottomSide,
                                                     frame1_h,
                                                     frame1_w,
                                                     rightClass.xPos1,
                                                     rightClass.yPos1,
                                                     moveToBottom.this.getLocation().x,
                                                     moveToBottom.this.getLocation().y
                                                     );
                    }
// ********************************* end of touch with moveToRight class***************************************

                    
                    // move frame1 to right direction
                      yPos1 += 10;
//                        if( ! bottomRTest )
//                          {  if( yPos1 >= screenHeight ) {
//                     yPos1 = screenHeight;
//                     r_Xlow = true;
//                     r_Ylow = true;
//                     bottomRTest = true;
//                       System.out.println("0");
//                      touchCnt++;  // for iteration touches
//                      colorCnt++; // for iteration color
//                      this.setBackground(setTouchColor(colorCnt));
//                      this.label.setText("Touch " + touchCnt+ "    ");
//                     }
//                 }

                    // right and up   (+)
                 if( r_Xup && r_Ylow ) {
                        xPos1 += 10;
                        yPos1 -= 20; // -20
                        if( yPos1 <= 0 ) {  // TOP SIDE: 1+     6+
                            yPos1 = 0;
                            r_Ylow = false;
                            r_Yup = true;
//                              System.out.println("8");
                              touchCnt++;  // for iteration touches
                              colorCnt++; // for iteration color
                              this.setBackground(setTouchColor(colorCnt));
                              this.label.setText("Touch " + touchCnt+ "    ");
                        }
                        if( xPos1 >= screenWidth  ) { // RIGHT SIDE: 3+
                            xPos1 =  screenWidth ;
                            r_Xup = false;
                            r_Xlow = true;
//                              System.out.println("7");
                              touchCnt++;  // for iteration touches
                              colorCnt++; // for iteration color
                              this.setBackground(setTouchColor(colorCnt));
                              this.label.setText("Touch " + touchCnt+ "    ");
                        }
                    }
                    //  right and low   (+)
                 if( r_Xup && r_Yup ) {
                        xPos1 += 10;   // 15
//                        yPos1 += 5;
                        if( yPos1 >= screenHeight ) {  // BOTTOM SIDE: 2+
                            yPos1 = screenHeight;
                             r_Yup = false;
                             r_Ylow = true;
                              touchCnt++;  // for iteration touches
                              colorCnt++; // for iteration color
                              this.setBackground(setTouchColor(colorCnt));
                              this.label.setText("Touch " + touchCnt+ "    ");
                        }
                        if( xPos1 >= screenWidth  ) { // RIGHT SIDE: may be
                            xPos1 = screenWidth ;
                            r_Xup  = false;
                            r_Xlow = true;
//                              System.out.println("5");
                              touchCnt++;  // for iteration touches
                              colorCnt++; // for iteration color
                              this.setBackground(setTouchColor(colorCnt));
                              this.label.setText("Touch " + touchCnt+ "    ");
                     }

                    }
                   // from BOTTOM to --> right and up  (-)  !!!!!!!!!!!!!!!!!!!!!
                  if( r_Xlow && r_Ylow ) {
                        xPos1 -= 10;
                        yPos1 -= 20;  // -15
                        if( yPos1 <= 0 ) {  // TOP SIDE: 4-
                            yPos1 = 0;
                            r_Ylow = false;
                            r_Yup = true;
//                              System.out.println("1");
                              touchCnt++;  // for iteration touches
                              colorCnt++; // for iteration color
                              this.setBackground(setTouchColor(colorCnt));
                              this.label.setText("Touch " + touchCnt+ "    ");
                      }
                        if( xPos1 <= 0 ) {  // LEFT SIDE:
                            xPos1 = 0;
                            r_Xlow = false;
                            r_Xup  = true;
//                              System.out.println("2");
                              touchCnt++;  // for iteration touches
                              colorCnt++; // for iteration color
                              this.setBackground(setTouchColor(colorCnt));
                              this.label.setText("Touch " + touchCnt+ "    ");
                        }

                  }

                   //  from UP to --> left and bottom
                  if( r_Xlow && r_Yup ) {
                        xPos1 -= 10;
//                        yPos1 += 10;
                        if( yPos1 >= screenHeight )  { // BOTTOM SIDE: 5-
                            yPos1 = screenHeight;
                            r_Yup = false;
                            r_Ylow = true;
//                              System.out.println("3");
                              touchCnt++;  // for iteration touches
                              colorCnt++; // for iteration color
                              this.setBackground(setTouchColor(colorCnt));
                              this.label.setText("Touch " + touchCnt+ "    ");
                        }
                        if( xPos1 <= 0 ) { // LEFT SIDE:  may  be to
                            xPos1 = 0;
                            r_Xlow = false;
                            r_Xup = true;
//                              System.out.println("4");
                              touchCnt++;  // for iteration touches
                              colorCnt++; // for iteration color
                              this.setBackground(setTouchColor(colorCnt));
                              this.label.setText("Touch " + touchCnt+ "    ");
                      }
                  }
         tot++;
     //tot <-- time life of this Thread
   if(tot == 2000) { bottomTest = false; this.setVisible(false); tot = 0; botttomClass.setLocation( xPos1+2000 , yPos1+2000);
                    testbase = false; testright = false; testtop = false;  }
    // shutdown <-- stop Thread
  if ( shutdown )  { bottomTest = false; dispose(); setLocation( xPos1+2000 , yPos1+2000); repaint(); }
                if( botttomClass.isVisible() )
                     setLocation( xPos1, yPos1 );
                else  {setLocation( xPos1+2000, yPos1+2000 ); testbase = false; testright = false; testtop = false; }


                } catch (InterruptedException ex) {  }
        }
       }


        };

    public static void main(String[] args) {
        new  TOUCHING_FRAMES();
    }
}
