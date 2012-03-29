package shape;

import java.awt.Color;
import physics.Vector2;

public abstract class Shape {

    protected Vector2 position;
    protected Color color;

    public abstract void draw();

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
