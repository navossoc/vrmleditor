package shape.geometry;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.math.MathUtils;
import shape.Shape;

public class Cylinder extends Shape {

    private float height;
    private float radius;

    public Cylinder(float radius, float height) {
        this.height = height;
        this.radius = radius;

        primitiveType = GL10.GL_TRIANGLES;
        calculateCylinder();
    }

    private void calculateCylinder() {
        short slices = (short) (6 * Math.cbrt(radius));

        mesh = new Mesh(true, (slices + 1) * 2, slices * 12, new VertexAttribute(VertexAttributes.Usage.Position, 3, "a_position"));

        float[] vertices = new float[(slices + 1) * 6];
        float angle = 360f / slices;

        int i = 0;
        for (int s = 0; s < slices; i += 3, s++) {
            vertices[i++] = vertices[i + 2] = radius * MathUtils.cosDeg(s * angle);
            vertices[i++] = -height / 2;
            vertices[i + 2] = height / 2;
            vertices[i++] = vertices[i + 2] = radius * MathUtils.sinDeg(s * angle);
        }
        vertices[i++] = vertices[i + 2] = 0;
        vertices[i++] = -height / 2;
        vertices[i + 2] = height / 2;
        vertices[i++] = vertices[i + 2] = 0;

        short[] indices = new short[slices * 12];

        short p = 0;
        for (i = 0; i < indices.length - 12;) {
            //
            indices[i++] = p;
            indices[i++] = (short) (p + 2);
            indices[i++] = (short) (slices * 2);
            //
            indices[i++] = (short) (p + 1);
            indices[i++] = (short) (p + 3);
            indices[i++] = (short) (slices * 2 + 1);
            //
            indices[i++] = p;
            indices[i++] = (short) (p + 1);
            indices[i++] = (short) (p + 2);
            //
            indices[i++] = (short) (p + 1);
            indices[i++] = (short) (p + 2);
            indices[i++] = (short) (p + 3);
            p += 2;
        }
        indices[i++] = p;
        indices[i++] = 0;
        indices[i++] = (short) (slices * 2);

        indices[i++] = (short) (p + 1);
        indices[i++] = 1;
        indices[i++] = (short) (slices * 2 + 1);

        indices[i++] = 0;
        indices[i++] = p;
        indices[i++] = (short) (p + 1);

        indices[i++] = 0;
        indices[i++] = 1;
        indices[i++] = (short) (p + 1);

        mesh.setVertices(vertices);
        mesh.setIndices(indices);
    }

    @Override
    public String toString() {
        return "Cilindro " + ID;
    }
}
