package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import math.Vector2;
import transform.Rotation;
import transform.Scale;
import transform.Transform;
import transform.Translation;

public abstract class Shape {

    public static int ID_COUNTER = 1;
    protected int ID;
    protected Vector2 position;
    protected Color color;
    protected ArrayList<Transform> transformations;

    public Shape() {
        color = new Color(0, 0, 0);
        position = new Vector2(0, 0);
        transformations = new ArrayList<Transform>();
        ID = ID_COUNTER++;
    }

    public void draw(Graphics g) {
        g.setColor(color);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // transformations methods
    public void scale(Scale scale) {
        transformations.add(scale);
    }

    public void scale(float x, float y, float z) {
        scale(new Scale(x, y, z));
    }

    public void rotate(Rotation rotation) {
        transformations.add(rotation);
    }

    public void rotate(float x, float y, float z, float angle) {
        rotate(new Rotation(x, y, z, angle));
    }

    public void translate(Translation translation) {
        transformations.add(translation);
    }

    public void translate(float x, float y, float z) {
        translate(new Translation(x, y, z));
    }

    @Override
    public String toString() {
        return "Shape " + ID;
    }
}
