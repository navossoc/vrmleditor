package shape.geometry;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
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

    private void calculateBox() {
        mesh = new Mesh(true, 8, 36, new VertexAttribute(Usage.Position, 3, "a_position"));

        float[] cubeVerts = {
            -1.0f, +1.0f, +1.0f, // 0
            +1.0f, +1.0f, +1.0f, // 1
            +1.0f, -1.0f, +1.0f, // 2
            -1.0f, -1.0f, +1.0f, // 3
            -1.0f, +1.0f, -1.0f, // 4
            +1.0f, +1.0f, -1.0f, // 5
            +1.0f, -1.0f, -1.0f, // 6
            -1.0f, -1.0f, -1.0f, // 7
        };

        for (int i = 0; i < cubeVerts.length;) {
            cubeVerts[i++] *= width;
            cubeVerts[i++] *= height;
            cubeVerts[i++] *= depth;
        }

        short[] indices = {
            0, 1, 2, 0, 2, 3, // front
            1, 2, 5, 2, 5, 6, // right
            0, 1, 5, 0, 4, 5, // top
            4, 5, 7, 5, 6, 7, // back
            2, 3, 7, 2, 6, 7, // bottom
            0, 3, 7, 0, 4, 7, // left
        };

        mesh.setVertices(cubeVerts);
        mesh.setIndices(indices);
    }

    @Override
    public String toString() {
        return "Caixa " + ID;
    }
}
