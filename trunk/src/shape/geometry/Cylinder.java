package shape.geometry;

import shape.Shape;

public class Cylinder extends Shape {

    private float height;
    private float radius;

    public Cylinder(float height, float radius) {
        this.height = height;
        this.radius = radius;
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
