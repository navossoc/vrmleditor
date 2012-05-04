package shape.geometry;

import java.awt.Graphics;
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
    public void draw(Graphics g) {
        super.draw(g);

        int x = (int) position.getX();
        int y = (int) position.getY();
        int w = (int) width;
        int h = (int) height;

        g.fillRect((x - (w / 2)), (y - (h / 2)), w, h);
    }
}
