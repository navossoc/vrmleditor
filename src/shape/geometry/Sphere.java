package shape.geometry;

import shape.Shape;

public class Sphere extends Shape {

    private float radius;

    public Sphere(float radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        super.draw();
/*
        int x = (int) translation.x;
        int y = (int) translation.y;
        int r = (int) radius;
*/

    }

    @Override
    public String toString() {
        return "Esfera " + ID;
    }
}
