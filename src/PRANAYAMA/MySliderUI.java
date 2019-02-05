
package PRANAYAMA;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSliderUI;

public class MySliderUI  extends BasicSliderUI{
    
    protected JSlider  slider;
    
    MySliderUI(JSlider  slider) {
        super(slider);
        this.slider = slider;
        
    }
    
    public static ComponentUI createUI(JComponent c) {
        return new MySliderUI((JSlider)c);
    }
    
}
