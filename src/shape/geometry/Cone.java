package shape.geometry;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.math.MathUtils;
import java.util.Locale;
import shape.Shape;

public class Cone extends Shape {

    private float bottomRadius;
    private float height;

    public Cone(float bottomRadius, float height) {
        this.bottomRadius = bottomRadius;
        this.height = height;

        primitiveType = GL10.GL_TRIANGLES;
        calculateCone();
    }

    public final void calculateCone() {
        short slices = (short) (4 * Math.cbrt(bottomRadius) + 8);

        mesh = new Mesh(true, slices + 2, slices * 6, VertexAttribute.Position());

        float[] vertices = new float[(slices + 2) * 3];
        float angle = 360f / slices;

        int i = 0;
        for (int s = 0; s < slices; s++) {
            vertices[i++] = bottomRadius * MathUtils.cosDeg(s * angle);
            vertices[i++] = -height / 2;
            vertices[i++] = bottomRadius * MathUtils.sinDeg(s * angle);
        }
        vertices[i++] = vertices[i + 2] = 0;
        vertices[i++] = -height / 2;
        vertices[i + 2] = height / 2;
        vertices[i++] = vertices[i + 2] = 0;

        short[] indices = new short[slices * 6];

        short p = 0;
        for (i = 0; i < indices.length - 6; i += 3) {
            indices[i++] = indices[i + 2] = p++;
            indices[i++] = indices[i + 2] = p;
            indices[i++] = indices[i + 2] = slices;
            indices[i + 2]++;
        }
        indices[i++] = indices[i + 2] = p;
        indices[i++] = indices[i + 2] = 0;
        indices[i++] = indices[i + 2] = slices;
        indices[i + 2]++;

        mesh.setVertices(vertices);
        mesh.setIndices(indices);
    }

    @Override
    public Cone copy() {
        ID_COUNTER--;
        Cone temp = new Cone(bottomRadius, height);
        super.set(temp);
        return temp;
    }

    public float getBottomRadius() {
        return bottomRadius;
    }

    public void setBottomRadius(float bottomRadius) {
        this.bottomRadius = bottomRadius;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public String printVrml() {
        String temp = super.printVrml();
        String cone = String.format(Locale.US,
                "\t\t\tbottomRadius %.2f\r\n"
                + "\t\t\theight %.2f\r\n",
                bottomRadius, height);
        return String.format(temp, cone);
    }

    @Override
    public String toString() {
        return "Cone " + ID;
    }
}
