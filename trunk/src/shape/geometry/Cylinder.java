package shape.geometry;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.math.MathUtils;
import gui.Settings;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Locale;
import shape.Shape;

public class Cylinder extends Shape {

    private float height;
    private float radius;

    public Cylinder(float radius, float height) {
        this.height = height;
        this.radius = radius;

        primitiveType = GL10.GL_TRIANGLES;
        create();
    }

    @Override
    protected final void create() {
        short slices = (short) (4 * Math.cbrt(radius) + 8);

        mesh = new Mesh(true, (slices + 1) * 2, slices * 12, VertexAttribute.Position());

        vertices = new float[(slices + 1) * 6];
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

        indices = new short[slices * 12];

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

        super.create();
    }

    @Override
    public Cylinder copy() {
        ID_COUNTER--;
        Cylinder temp = new Cylinder(radius, height);
        super.set(temp);
        return temp;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
        create();
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        create();
    }

    @Override
    public String printVrml() {
        String temp = super.printVrml();
        String cylinder = String.format(Locale.US,
                "\t\t\theight %.2f\r\n"
                + "\t\t\tradius %.2f\r\n",
                height, radius);
        return String.format(temp, cylinder);
    }

    @Override
    public void writeBinary(DataOutputStream dataOutputStream) throws IOException {
        super.writeBinary(dataOutputStream);
        dataOutputStream.writeFloat(radius);
        dataOutputStream.writeFloat(height);
    }

    @Override
    public String toString() {
        return Settings.getMessage("Shape.Cylinder") + " " + ID;
    }
}
