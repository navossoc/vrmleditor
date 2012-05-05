package shape.geometry;

import java.awt.Graphics;
import shape.Shape;

public class Cylinder extends Shape {

    private float height;
    private float radius;

    public Cylinder(float radius, float height) {
        this.height = height;
        this.radius = radius;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        int x = (int) position.getX();
        int y = (int) position.getY();
        int w = (int) radius;
        int h = (int) height;

        g.fillRect((x - (w / 2)), (y - (h / 2)), w, h);
    }

    @Override
    public String toString() {
        return "Cilindro " + ID;
    }
}
