
package animation;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class TextBouncer   extends AnimationComponent {

    private boolean b_antialiasing = false, b_gradient = false;
    private boolean b_axes = false, b_shear = false, b_rotate = false;
    public final static int ANTIALIASING = 0;
    public final static int GRADIENT = 1;
    public final static int SHEAR = 2;
    public final static int ROTATE = 3;
    public final static int AXES = 5;

    private float x, y, wwidth, hheight;
    private float deltaX, deltaY;
    private float shearX, shearY, shearDeltaX, shearDeltaY;
    private float theta;
    private String str;

    TextBouncer(String s, Font f) {
      str = s;
      setFont(f);
      Random random = new Random();
      x = random.nextFloat() * 500;
      y = random.nextFloat() * 500;
      deltaX = random.nextFloat() * 3;
      deltaY = random.nextFloat() * 3;
      shearX = random.nextFloat() / 2;
      shearY = random.nextFloat() / 2;
      shearDeltaX = shearDeltaY = .5f;
      FontRenderContext frc = new FontRenderContext(null, true, false);
      Rectangle2D  bounds = getFont().getStringBounds(str, frc);
      wwidth = (float) bounds.getWidth();
      hheight = (float) bounds.getHeight();
      addComponentListener(new ComponentAdapter() {
        public void componentResized(ComponentEvent ce ) {
            Dimension d = getSize();
            if( x  < 0 )  x = 0;
            else if( (x + wwidth)  >= d.width ) x = d.width - wwidth - 1;
            if( y  < 0 )  y = 0;
            else if( (x + hheight)  >= d.height ) y = d.height - hheight - 1;
          }
      });
    }

    public void setSwitch(int item, boolean value) {
        switch (item) {
            case ANTIALIASING: b_antialiasing = value;  break;
            case GRADIENT: b_gradient = value; break;
            case SHEAR:  b_shear = value; break;
            case ROTATE: b_rotate = value; break;
            case AXES: b_axes = value; break;
            default:  break;
        }
    }

    protected Checkbox createCheckbox(String label, final int item) {
        Checkbox check = new Checkbox(label);
        check.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent ie) {
                setSwitch(item, (ie.getStateChange() == ie.SELECTED));
            }
        });
        return check;
    }

    @Override
    public void timeStep() {
        Dimension d = getSize();
        if( x + deltaX < 0)  deltaX = -deltaX;
        else if( x + wwidth +  deltaX >= d.width)  deltaX = -deltaX;
        if( y + deltaY < 0)  deltaY = -deltaY;
        else if( y + hheight + deltaY >= d.height)  deltaY = -deltaY;
        x += deltaX;
        y += deltaY;
        
        theta += Math.PI / 192;
        if( theta > (2*Math.PI)) theta -= (2*Math.PI);

        if( shearX + shearDeltaX < -.5 )  shearDeltaX = -shearDeltaX;
        else if( shearX + shearDeltaX > .5 )  shearDeltaX = -shearDeltaX;
        if( shearY + shearDeltaY < -.5 )  shearDeltaY = -shearDeltaY;
        else if( shearY + shearDeltaY > .5 )  shearDeltaY = -shearDeltaY;
        shearX += shearDeltaX;
        shearY += shearDeltaY;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        setAntialiasing(g2);
        setpaint(g2);
        setTransform(g2);
        // drawString
        g2.setFont(getFont());
        g2.drawString(str, x, y + hheight);
        drawAxes(g2);
    }

    public void setAntialiasing(Graphics2D g2) {
        if( b_antialiasing == false ) return;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void setTransform(Graphics2D g2) {
        Dimension d = getSize();
        int cx = d.width /2;
        int cy = d.height /2;
        g2.translate(cx, cy);
        if( b_shear ) g2.shear(shearX, shearY); 
        if( b_rotate) g2.rotate(theta);
        g2.translate(-cx, -cy);
    }

    public void setpaint(Graphics2D g2) {
        if( b_gradient  ) {
          GradientPaint Gradient = new GradientPaint(0,0, Color.BLUE , 50, 25, Color.GREEN, true);
          g2.setPaint(Gradient);
        }  else  g2.setPaint(Color.ORANGE);
    }

    public void drawAxes(Graphics2D g2) {
        if( b_axes == false ) return;
        Dimension d = getSize();
        g2.setColor(getForeground());
        g2.setFont(getFont());
//        g2.setPaint(getPaint()); 
        int w = d.width/2;
        int h = d.height/2;
        int arrow = 4;
        int side = 20;
        g2.drawLine( w - side, h, w + side, h);
        g2.drawLine( w + side - arrow, h - arrow, w + side, h);
        g2.drawLine( w , h - side, w , h + side);
        g2.drawLine( w + arrow, h + side - arrow, w , h + side);
    }

    public static void main(String[] args) {
        String s = "Poltava";
        final int size = 64;
        final Choice choice = new Choice();
//        choice.setBackground(Color.red);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] allFonts =  ge.getAllFonts();
        for(int k = 0; k < allFonts.length; k++)
            choice.addItem(allFonts[k].getName());

        Font defaultFont = new Font(allFonts[0].getName(), Font.PLAIN, size );
        final TextBouncer bouncer = new TextBouncer(s, defaultFont);
        final Frame f = new FrameComponent(bouncer);
//        f.setFont(new Font("Serif", Font.PLAIN, 12));
        f.setFont(defaultFont);
        Panel controls = new Panel();
        controls.add(bouncer.createCheckbox("Antialiasing", TextBouncer.ANTIALIASING));
        controls.add(bouncer.createCheckbox("Gradient", TextBouncer.GRADIENT));
        controls.add(bouncer.createCheckbox("Shear", TextBouncer.SHEAR));
        controls.add(bouncer.createCheckbox("Rotate", TextBouncer.ROTATE));
        controls.add(bouncer.createCheckbox("Axes", Bouncer.AXES));

        Panel fontControls = new Panel();
        choice.addItemListener(new ItemListener() {
             public void itemStateChanged(ItemEvent ie) {
                Font font = new Font(choice.getSelectedItem(), Font.PLAIN, size);
                bouncer.setFont(font);
//                f.setFont(font);
                  }
             });
         fontControls.add(choice);
         Panel allControls = new Panel(new GridLayout(2, 1));
         allControls.add(controls);
         allControls.add(fontControls);
         f.add(allControls, BorderLayout.NORTH);
        f.setVisible(true);
    }

}
