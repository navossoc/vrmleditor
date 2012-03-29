package shape.geometry;

import shape.Shape;

public class Cone extends Shape {

    private float bottomRadius;
    private float height;

    public Cone(float bottomRadius, float height) {
        this.bottomRadius = bottomRadius;
        this.height = height;
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
