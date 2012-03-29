package shape.geometry;

import shape.Shape;

public class Sphere extends Shape {

    private float radius;

    public Sphere(float radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
