
package PRANAYAMA;

import java.awt.Color;

public interface ILabel<T> {
    
   public void setDisabledStateColor(String name, T resource, int x, int y, int w, int h);
   
//   public void setDisabledStateColor( T resource);
    
//   public  T getDisabledStateColor();
    
   public void setEnabledStateColor(String name, T resource, int x, int y, int w, int h);
   
//   public void setEnabledStateColor( T resource);
    
//   public T getEnabledStateColor();
}
