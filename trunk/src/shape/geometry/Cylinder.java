package shape.geometry;

import shape.Shape;

public class Cylinder extends Shape {

    private float height;
    private float radius;

    public Cylinder(float radius, float height) {
        this.height = height;
        this.radius = radius;
    }

    @Override
    public void draw() {
        super.draw();
/*
        int x = (int) translation.x;
        int y = (int) translation.y;
        int w = (int) radius;
        int h = (int) height;
*/

    }

    @Override
    public String toString() {
        return "Cilindro " + ID;
    }
}
