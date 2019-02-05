package PRANAYAMA;

public class viewDefault {

    public final String NUMBER_VIEW = "NUMBER_VIEW";
    public final String HORIZONTAL_VIEW = "HORIZONTAL_DIAGRAMM_VIEW";
    public final String VERTICAL_VIEW = "VERTICAL_DIAGRAMM_VIEW";
    public final String CIRCLE_VIEW = "CIRCLE_DIAGRAMM_VIEW";
    public String defaultView;
    
    PRANAYAMA pranaMain;

    viewDefault(PRANAYAMA prana) {
       this.pranaMain = prana;
       
        defaultView = NUMBER_VIEW;
    }

//    public void initDefaultView() {
//        // show NUMBER in  preview  panel
//        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.NUMBER_VIEW) {
//            Views = new views(pranaMain, true, preview);
//        }
//        // show HORIZONTAL VIEW in  preview  panel
//        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.HORIZONTAL_VIEW) {
//            Views = new views(pranaMain, true, preview, 1);
//        }
//        // show VERTICAL VIEW in  preview  panel
//        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.VERTICAL_VIEW) {
//            Views = new views(pranaMain, true, preview, 2);
//        }
//        // show CIRCLE VIEW in  preview  panel
//        if (pranaMain.vDefault.defaultView == pranaMain.vDefault.CIRCLE_VIEW) {
//            Views = new views(pranaMain, true, preview, 3);
//        }
//    }
   
}
