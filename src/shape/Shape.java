package shape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Locale;

public abstract class Shape {

    public static int ID_COUNTER = 1;
    protected int ID;
    protected int primitiveType;
    protected Mesh mesh;
    protected Color color;
    protected Vector3 scale;
    protected Quaternion rotation;
    protected Vector3 translation;

    public Shape() {
        ID = ID_COUNTER++;

        color = new Color(0, 0, 0, 1);
        scale = new Vector3(1, 1, 1);
        rotation = new Quaternion(0, 0, 0, 0);
        translation = new Vector3(0, 0, 0);
    }

    public abstract Shape copy();

    public void draw() {
        // set color
        Gdx.gl10.glColor4f(color.r, color.g, color.b, color.a);

        Gdx.gl10.glPushMatrix();

        // translate
        Gdx.gl10.glTranslatef(translation.x, translation.y, translation.z);

        // rotate
        if (rotation.x != 0 || rotation.y != 0 || rotation.z != 0) {
            Gdx.gl10.glRotatef(rotation.w, rotation.x, rotation.y, rotation.z);
        }

        // scale
        Gdx.gl10.glScalef(scale.x, scale.y, scale.z);

        // render
        mesh.render(primitiveType);

        Gdx.gl10.glPopMatrix();
    }

    public static void reset() {
        ID_COUNTER = 1;
    }

    public Color getColor() {
        return color;
    }

    public java.awt.Color getColorAWT() {
        return new java.awt.Color(color.r, color.g, color.b, color.a);
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public void setColorR(float red) {
        this.color.r = red;
    }

    public void setColorG(float green) {
        this.color.g = green;
    }

    public void setColorB(float blue) {
        this.color.b = blue;
    }

    public void setColorA(float alpha) {
        this.color.a = alpha;
    }

    public void setColorRGB(java.awt.Color color) {
        float r = color.getRed() / 255f;
        float g = color.getGreen() / 255f;
        float b = color.getBlue() / 255f;
        this.color.r = r;
        this.color.g = g;
        this.color.b = b;
    }

    public void setColorAWT(java.awt.Color color) {
        float r = color.getRed() / 255f;
        float g = color.getGreen() / 255f;
        float b = color.getBlue() / 255f;
        float a = color.getAlpha() / 255f;
        this.color.set(r, g, b, a);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Vector3 getScale() {
        return scale;
    }

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

    public Quaternion getRotation() {
        return rotation;
    }

    public void set(Shape shape) {
        shape.ID = ID;
        shape.color.set(color);
        shape.scale.set(scale);
        shape.rotation.set(rotation);
        shape.translation.set(translation);
    }

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

    public Vector3 getTranslation() {
        return translation;
    }

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
        return "Shape " + ID;
    }
}
