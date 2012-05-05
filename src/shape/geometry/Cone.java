package shape.geometry;

import java.awt.Graphics;
import java.awt.Polygon;
import shape.Shape;

public class Cone extends Shape {

    private float bottomRadius;
    private float height;
    private int[] xPoints, yPoints;

    public Cone(float bottomRadius, float height) {
        this.bottomRadius = bottomRadius;
        this.height = height;
        this.xPoints = new int[3];
        this.yPoints = new int[3];
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        int b = (int) bottomRadius / 2;
        int h = (int) height / 2;

        xPoints[0] = -b;
        yPoints[0] = -h;
        xPoints[1] = b;
        yPoints[1] = -h;
        xPoints[2] = 0;
        yPoints[2] = h;

        g.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public String toString() {
        return "Cone " + ID;
    }
}
