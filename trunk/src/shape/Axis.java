package shape;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;

public class Axis {

    protected final static Mesh mesh;

    static {
        mesh = new Mesh(true, 6, 6,
                VertexAttribute.Position(),
                VertexAttribute.Color());

        float[] vertices = {
            // x - red
            Short.MIN_VALUE, 0, 0, Color.toFloatBits(1.0f, 0.0f, 0.0f, 1.0f), // 0
            Short.MAX_VALUE, 0, 0, Color.toFloatBits(1.0f, 0.0f, 0.0f, 1.0f), // 1
            // y - green
            0, Short.MIN_VALUE, 0, Color.toFloatBits(0.0f, 1.0f, 0.0f, 1.0f), // 2
            0, Short.MAX_VALUE, 0, Color.toFloatBits(0.0f, 1.0f, 0.0f, 1.0f), // 3
            // z - blue
            0, 0, Short.MIN_VALUE, Color.toFloatBits(0.0f, 0.0f, 1.0f, 1.0f), // 4
            0, 0, Short.MAX_VALUE, Color.toFloatBits(0.0f, 0.0f, 1.0f, 1.0f), // 5
        };

        short[] indices = {
            0, 1, 2, 3, 4, 5
        };

        mesh.setVertices(vertices);
        mesh.setIndices(indices);
    }

    public static void draw() {
        mesh.render(GL10.GL_LINES);
    }
}
