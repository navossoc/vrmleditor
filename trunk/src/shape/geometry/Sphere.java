package shape.geometry;

import java.awt.Graphics;
import shape.Shape;

public class Sphere extends Shape {

    private float radius;

    public Sphere(float radius) {
        this.radius = radius;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        int x = (int) position.getX();
        int y = (int) position.getY();
        int r = (int) radius;

        g.fillOval((x - (r / 2)), (y - (r / 2)), r, r);
    }
}
