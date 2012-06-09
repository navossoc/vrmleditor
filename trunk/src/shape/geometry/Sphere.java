package shape.geometry;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import gui.Settings;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Locale;
import shape.Shape;

public class Sphere extends Shape {

    private float radius;

    public Sphere(float radius) {
        this.radius = radius;

        primitiveType = GL10.GL_TRIANGLE_STRIP;
        create();
    }

    @Override
    protected final void create() {
        int space = 20;

        int VertexCount = ((180 / space) * (360 / space) * 4);
        vertices = new float[VertexCount * 3];

        float a;
        float b;

        int i = 0;

        // swiftless@gmail.com
        // http://www.swiftless.com/tutorials/opengl/sphere.html
        for (b = 0; b <= 180 - space; b += space) {

            for (a = 0; a <= 360 - space; a += space) {

                vertices[i++] = radius * MathUtils.sinDeg(a) * MathUtils.sinDeg(b);
                vertices[i++] = radius * MathUtils.cosDeg(a) * MathUtils.sinDeg(b);
                vertices[i++] = radius * MathUtils.cosDeg(b);

                vertices[i++] = radius * MathUtils.sinDeg(a) * MathUtils.sinDeg(b + space);
                vertices[i++] = radius * MathUtils.cosDeg(a) * MathUtils.sinDeg(b + space);
                vertices[i++] = radius * MathUtils.cosDeg(b + space);

                vertices[i++] = radius * MathUtils.sinDeg(a + space) * MathUtils.sinDeg(b);
                vertices[i++] = radius * MathUtils.cosDeg(a + space) * MathUtils.sinDeg(b);
                vertices[i++] = radius * MathUtils.cosDeg(b);

                vertices[i++] = radius * MathUtils.sinDeg(a + space) * MathUtils.sinDeg(b + space);
                vertices[i++] = radius * MathUtils.cosDeg(a + space) * MathUtils.sinDeg(b + space);
                vertices[i++] = radius * MathUtils.cosDeg(b + space);
            }

        }
        mesh = new Mesh(true, VertexCount, 0, VertexAttribute.Position());
        indices = new short[0];
        super.create();
    }

    /**
     * Return a copy of this sphere
     *
     * @return
     */
    @Override
    public Sphere copy() {
        ID_COUNTER--;
        Sphere temp = new Sphere(radius);
        super.set(temp);
        return temp;
    }

    @Override
    public boolean intersect(Ray ray, Vector3 intersectionVector) {
        return Intersector.intersectRaySphere(ray, translation, radius, intersectionVector);
    }

    /**
     * Get sphere radius
     *
     * @return
     */
    public float getRadius() {
        return radius;
    }

    /**
     * Set sphere radius
     *
     * @param radius
     */
    public void setRadius(float radius) {
        this.radius = radius;
        create();
    }

    @Override
    public String printVrml() {
        String temp = super.printVrml();
        String sphere = String.format(Locale.US,
                "\t\t\tradius %.2f\r\n",
                radius);
        return String.format(temp, sphere);
    }

    @Override
    public void writeBinary(DataOutputStream dataOutputStream) throws IOException {
        super.writeBinary(dataOutputStream);
        dataOutputStream.writeFloat(radius);
    }

    @Override
    public String toString() {
        return Settings.getMessage("Shape.Sphere") + " " + ID;
    }
}
