package shape.geometry;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import java.util.Locale;
import shape.Shape;

public class Box extends Shape {

    private float width;
    private float height;
    private float depth;

    public Box(float width, float height, float depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;

        this.primitiveType = GL10.GL_TRIANGLES;
        calculateBox();
    }

    public final void calculateBox() {
        mesh = new Mesh(true, 8, 36, VertexAttribute.Position());

        float[] vertices = {
            -1.0f, +1.0f, +1.0f, // 0
            +1.0f, +1.0f, +1.0f, // 1
            +1.0f, -1.0f, +1.0f, // 2
            -1.0f, -1.0f, +1.0f, // 3
            -1.0f, +1.0f, -1.0f, // 4
            +1.0f, +1.0f, -1.0f, // 5
            +1.0f, -1.0f, -1.0f, // 6
            -1.0f, -1.0f, -1.0f, // 7
        };

        for (int i = 0; i < vertices.length;) {
            vertices[i++] *= width / 2;
            vertices[i++] *= height / 2;
            vertices[i++] *= depth / 2;
        }

        short[] indices = {
            0, 1, 2, 0, 2, 3, // front
            1, 2, 5, 2, 5, 6, // right
            0, 1, 5, 0, 4, 5, // top
            4, 5, 7, 5, 6, 7, // back
            2, 3, 7, 2, 6, 7, // bottom
            0, 3, 7, 0, 4, 7, // left
        };

        mesh.setVertices(vertices);
        mesh.setIndices(indices);
    }

    @Override
    public Box copy() {
        ID_COUNTER--;
        Box temp = new Box(width, height, depth);
        super.set(temp);
        return temp;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    @Override
    public String printVrml() {
        String temp = super.printVrml();
        String box = String.format(Locale.US,
                "\t\t\tsize %.2f %.2f %.2f\r\n",
                width, height, depth);
        return String.format(temp, box);
    }

    @Override
    public String toString() {
        return "Caixa " + ID;
    }
}
