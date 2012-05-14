package shape.geometry;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.math.MathUtils;
import shape.Shape;

public class Sphere extends Shape {

    private float radius;

    public Sphere(float radius) {
        this.radius = radius;

        primitiveType = GL10.GL_TRIANGLE_STRIP;
        calculateSphere();
    }

    public final void calculateSphere() {
        int space = 18;

        int VertexCount = ((180 / space) * (360 / space) * 4);
        float[] v = new float[VertexCount * 3];

        float a;
        float b;

        int i = 0;

        for (b = 0; b <= 180 - space; b += space) {

            for (a = 0; a <= 360 - space; a += space) {

                v[i++] = radius * MathUtils.sinDeg(a) * MathUtils.sinDeg(b);
                v[i++] = radius * MathUtils.cosDeg(a) * MathUtils.sinDeg(b);
                v[i++] = radius * MathUtils.cosDeg(b);

                v[i++] = radius * MathUtils.sinDeg(a) * MathUtils.sinDeg(b + space);
                v[i++] = radius * MathUtils.cosDeg(a) * MathUtils.sinDeg(b + space);
                v[i++] = radius * MathUtils.cosDeg(b + space);

                v[i++] = radius * MathUtils.sinDeg(a + space) * MathUtils.sinDeg(b);
                v[i++] = radius * MathUtils.cosDeg(a + space) * MathUtils.sinDeg(b);
                v[i++] = radius * MathUtils.cosDeg(b);

                v[i++] = radius * MathUtils.sinDeg(a + space) * MathUtils.sinDeg(b + space);
                v[i++] = radius * MathUtils.cosDeg(a + space) * MathUtils.sinDeg(b + space);
                v[i++] = radius * MathUtils.cosDeg(b + space);
            }

        }
        mesh = new Mesh(true, VertexCount, 0, VertexAttribute.Position());
        mesh.setVertices(v);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Esfera " + ID;
    }
}
