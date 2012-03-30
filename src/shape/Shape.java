package shape;

import java.awt.Color;
import java.util.ArrayList;
import math.Vector2;
import transform.Rotation;
import transform.Scale;
import transform.Transform;
import transform.Translation;

public abstract class Shape {

    protected Vector2 position;
    protected Color color;
    // transforms
    protected ArrayList<Scale> scales;
    protected ArrayList<Rotation> rotations;
    protected ArrayList<Translation> translations;
    protected ArrayList<Transform> transformations;

    public Shape() {
        scales = new ArrayList<Scale>();
        rotations = new ArrayList<Rotation>();
        translations = new ArrayList<Translation>();
        transformations = new ArrayList<Transform>();
    }

    // abstract methods
    public abstract void draw();

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // transformations methods
    public void scale(Scale scale) {
        scales.add(scale);
        transformations.add(scale);
    }

    public void scale(float x, float y, float z) {
        scale(new Scale(x, y, z));
    }

    public void rotate(Rotation rotation) {
        rotations.add(rotation);
        transformations.add(rotation);
    }

    public void rotate(float x, float y, float z, float angle) {
        rotate(new Rotation(x, y, z, angle));
    }

    public void translate(Translation translation) {
        translations.add(translation);
        transformations.add(translation);
    }

    public void translate(float x, float y, float z) {
        translate(new Translation(x, y, z));
    }
}
