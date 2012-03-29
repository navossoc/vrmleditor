package shape;

import java.awt.Color;
import java.util.ArrayList;
import physics.Vector2;
import transform.Rotation;
import transform.Scale;
import transform.Transform;
import transform.Translation;

public abstract class Shape extends Transform {

    protected ArrayList<Rotation> rotations;
    protected ArrayList<Translation> translations;
    protected ArrayList<Scale> scales;
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
