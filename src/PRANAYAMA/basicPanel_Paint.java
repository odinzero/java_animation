
package PRANAYAMA;

import GAME_BRICKS.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.*;
import java.awt.image.ColorModel;

public class basicPanel_Paint implements Paint {

    Point2D point;
    Point2D Radius;
    Color  pointColor , backgroundColor;

    basicPanel_Paint( double x , double y , Color PointColor , Point2D radius , Color BackgroundColor  ){
       if( radius.distance(0,0) < 0) {
           throw new IllegalArgumentException("Radius must be greater than 0.");
       }
       point = new Point2D.Double(x, y);
       pointColor = PointColor;
       Radius  = radius;
       backgroundColor = BackgroundColor;
    }

    @Override
    public PaintContext createContext(ColorModel cm,
                                      Rectangle deviceBounds,
                                      Rectangle2D userBounds,
                                      AffineTransform xform,
                                      RenderingHints hints) {
        Point2D transformedPoint  = xform.transform( point, null);
        Point2D transformedRadius = xform.deltaTransform(Radius, null);
        return new basicPanel_PaintContext( transformedPoint , pointColor , transformedRadius ,  backgroundColor );
    }

    @Override
    public int getTransparency() {
       int a1 = pointColor.getAlpha();
       int a2 = backgroundColor.getAlpha();
       return (((a1 & a2) == 0xff) ? OPAQUE : TRANSLUCENT);
    }

}
