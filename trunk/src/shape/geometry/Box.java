package shape.geometry;

import shape.Shape;

public class Box extends Shape {

    private float width;
    private float height;
    private float depth;

    public Box(float width, float height, float depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
