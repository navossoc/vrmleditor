package shape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.collision.Ray;
import gui.Settings;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Locale;

public abstract class Shape implements Comparable<Shape> {

    public static int ID_COUNTER = 1;
    protected int ID;
    protected int primitiveType;
    protected Mesh mesh;
    protected Color color;
    protected Vector3 scale;
    protected Quaternion rotation;
    protected Vector3 translation;
    protected Matrix4 transformation;

    public Shape() {
        ID = ID_COUNTER++;

        color = new Color(0, 0, 0, 1);
        scale = new Vector3(1, 1, 1);
        rotation = new Quaternion(0, 0, 0, 0);
        translation = new Vector3(0, 0, 0);
        transformation = new Matrix4();
    }

    public abstract Shape copy();

    /**
     * Draw the current shape
     */
    public void draw() {

        // identity
        transformation.idt();

        // translate
        transformation.translate(translation.x, translation.y, translation.z);

        // rotate
        transformation.rotate(rotation.x, rotation.y, rotation.z, rotation.w);

        // scale
        transformation.scale(scale.x, scale.y, scale.z);

        // set color
        Gdx.gl10.glColor4f(color.r, color.g, color.b, color.a);
        Gdx.gl10.glLoadMatrixf(transformation.val, 0);

        // render
        mesh.render(primitiveType);
    }

    /**
     * Intersects the shape with a Ray
     *
     * @see Ray
     * @param ray the Ray that will be casted over the shape
     * @param intersectionVector a Vector3 for the intersection point between the shape and the ray
     * @return true if the Ray intersect this shape
     */
    public boolean intersect(Ray ray, Vector3 intersectionVector) {
        float[] vertices = new float[mesh.getNumVertices() * 3];
        short[] indices = new short[mesh.getNumIndices()];
        mesh.getVertices(vertices);
        mesh.getIndices(indices);

        Vector3 temp = new Vector3();
        for (int i = 0; i < vertices.length;) {
            temp.x = vertices[i];
            temp.y = vertices[i + 1];
            temp.z = vertices[i + 2];
            temp.mul(transformation);
            vertices[i++] = temp.x;
            vertices[i++] = temp.y;
            vertices[i++] = temp.z;
        }
        return Intersector.intersectRayTriangles(ray, vertices, indices, mesh.getVertexSize() / (Float.SIZE / 8), intersectionVector);
    }

    /**
     * Resets the static ID
     */
    public static void reset() {
        ID_COUNTER = 1;
    }

    /**
     * Returns the shape's color
     *
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the shape color as java.awt.Color
     *
     * @see java.awt.Color
     * @return
     */
    public java.awt.Color getColorAWT() {
        return new java.awt.Color(color.r, color.g, color.b, color.a);
    }

    /**
     * Set a new color to the shape
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color.set(color);
    }

    /**
     * Set only the color Red component
     *
     * @param red
     */
    public void setColorR(float red) {
        this.color.r = red;
    }

    /**
     * Set only the color Green component
     *
     * @param green
     */
    public void setColorG(float green) {
        this.color.g = green;
    }

    /**
     * Set only the color Blue component
     *
     * @param green
     */
    public void setColorB(float blue) {
        this.color.b = blue;
    }

    /**
     * Set only the color Alpha component
     *
     * @param green
     */
    public void setColorA(float alpha) {
        this.color.a = alpha;
    }

    /**
     * Set the color from a java.awt.Color object without the alpha component
     *
     * @see java.awt.Color
     * @param color
     */
    public void setColorRGB(java.awt.Color color) {
        float r = color.getRed() / 255f;
        float g = color.getGreen() / 255f;
        float b = color.getBlue() / 255f;
        this.color.r = r;
        this.color.g = g;
        this.color.b = b;
    }

    /**
     * Same as @link #setColorRGB but set alpha component too
     *
     * @see java.awt.Color
     * @param color
     */
    public void setColorAWT(java.awt.Color color) {
        float r = color.getRed() / 255f;
        float g = color.getGreen() / 255f;
        float b = color.getBlue() / 255f;
        float a = color.getAlpha() / 255f;
        this.color.set(r, g, b, a);
    }

    /**
     * Returns the shape ID
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     * Set the shape ID
     *
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Get this shape scale
     *
     * @return
     */
    public Vector3 getScale() {
        return scale;
    }

    /**
     * Set this shape scale
     *
     * @param x scale for x-axis
     * @param y scale for y-axis
     * @param z scale for z-axis
     */
    public void setScale(float x, float y, float z) {
        this.scale.set(x, y, z);
    }

    public void setScale(Vector3 scale) {
        this.scale.set(scale);
    }

    public void setScaleX(float x) {
        this.scale.x = x;
    }

    public void setScaleY(float y) {
        this.scale.y = y;
    }

    public void setScaleZ(float z) {
        this.scale.z = z;
    }

    /**
     * Returns the rotation
     *
     * @return
     */
    public Quaternion getRotation() {
        return rotation;
    }

    /**
     * Set the rotation
     *
     * @param x
     * @param y
     * @param z
     * @param angle angle of the rotation in degrees
     */
    public void setRotation(float x, float y, float z, float angle) {
        this.rotation.set(x, y, z, angle);
    }

    public void setRotation(Quaternion rotation) {
        this.rotation.set(rotation);
    }

    public void setRotationW(float w) {
        this.rotation.w = w;
    }

    public void setRotationX(float x) {
        this.rotation.x = x;
    }

    public void setRotationY(float y) {
        this.rotation.y = y;
    }

    public void setRotationZ(float z) {
        this.rotation.z = z;
    }

    /**
     * Returns the translation
     *
     * @return
     */
    public Vector3 getTranslation() {
        return translation;
    }

    /**
     * Set the translation
     *
     * @param x
     * @param y
     * @param z
     */
    public void setTranslation(float x, float y, float z) {
        this.translation.set(x, y, z);
    }

    public void setTranslation(Vector3 translation) {
        this.translation.set(translation);
    }

    public void setTranslationX(float x) {
        this.translation.x = x;
    }

    public void setTranslationY(float y) {
        this.translation.y = y;
    }

    public void setTranslationZ(float z) {
        this.translation.z = z;
    }

    public void set(Shape shape) {
        shape.ID = ID;
        shape.color.set(color);
        shape.scale.set(scale);
        shape.rotation.set(rotation);
        shape.translation.set(translation);
    }

    /**
     * Returns a String containing this shapes properties in VRML97 format
     * http://www.web3d.org/x3d/specifications/vrml/ISO-IEC-14772-VRML97/
     *
     * @return
     */
    public String printVrml() {
        return String.format(Locale.US, "# %s\r\n"
                + "Transform {\r\n"
                + "\ttranslation %.2f %.2f %.2f\r\n"
                + "\tscale %.2f %.2f %.2f\r\n"
                + "\trotation %.2f %.2f %.2f %.3f\r\n"
                + "\tchildren Shape {\r\n"
                + "\t\tgeometry %s {\r\n"
                + "%%s"
                + "\t\t}\r\n"
                + "\t\tappearance Appearance {\r\n"
                + "\t\t\tmaterial Material {\r\n"
                + "\t\t\t\tdiffuseColor %.2f %.2f %.2f\r\n"
                + "\t\t\t\ttransparency %.2f\r\n"
                + "\t\t\t}\r\n"
                + "\t\t}\r\n"
                + "\t}\r\n}",
                this.toString(),
                translation.x, translation.y, translation.z,
                scale.x, scale.y, scale.z,
                rotation.x, rotation.y, rotation.z,
                (rotation.w * MathUtils.degreesToRadians),
                this.getClass().getSimpleName(),
                color.r, color.g, color.b,
                (1.0f - color.a));
    }

    /**
     * Write the properties of this shape in binary format
     *
     * @param dataOutputStream a DataOutputStream where the content will be written to
     * @throws IOException
     */
    public void writeBinary(DataOutputStream dataOutputStream) throws IOException {
        // shape
        String clazz = getClass().getSimpleName();
        dataOutputStream.writeShort(clazz.length());
        dataOutputStream.writeBytes(clazz);
        dataOutputStream.write(0);  // null padding

        // color
        dataOutputStream.writeFloat(color.r);
        dataOutputStream.writeFloat(color.g);
        dataOutputStream.writeFloat(color.b);
        dataOutputStream.writeFloat(color.a);

        // scale
        dataOutputStream.writeFloat(scale.x);
        dataOutputStream.writeFloat(scale.y);
        dataOutputStream.writeFloat(scale.z);

        // rotation
        dataOutputStream.writeFloat(rotation.w);
        dataOutputStream.writeFloat(rotation.x);
        dataOutputStream.writeFloat(rotation.y);
        dataOutputStream.writeFloat(rotation.z);

        // translation
        dataOutputStream.writeFloat(translation.x);
        dataOutputStream.writeFloat(translation.y);
        dataOutputStream.writeFloat(translation.z);
    }

    @Override
    public int compareTo(Shape shape) {
        if (ID == shape.ID) {
            return 0;
        } else {
            if (ID < shape.ID) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Shape other = (Shape) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.ID;
        return hash;
    }

    @Override
    public String toString() {
        return Settings.getMessage("Shape.Shape") + " " + ID;
    }
}