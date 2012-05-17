package shape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
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

        color = new Color(//0, 0, 0, 1
                MathUtils.random(0, 1),
                MathUtils.random(0, 1),
                MathUtils.random(0, 1), 1);
        scale = new Vector3(1, 1, 1);
        rotation = new Quaternion(0, 0, 0, 0);
        translation = new Vector3(0, 0, 0);
    }

    public abstract Shape copy();

    public void draw() {
        // set color
        Gdx.gl10.glColor4f(color.r, color.g, color.b, color.a);

        Gdx.gl10.glPushMatrix();

        // scale
        Gdx.gl10.glScalef(scale.x, scale.y, scale.z);
        // rotate
        Gdx.gl10.glRotatef(rotation.w, rotation.x, rotation.y, rotation.z);
        // translate
        Gdx.gl10.glTranslatef(translation.x, translation.y, translation.z);

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

    public void setColorAWT(java.awt.Color color) {
        this.color.set(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
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

    public Vector3 getTranslation() {
        return translation;
    }

    public void setTranslation(float x, float y, float z) {
        this.translation.set(x, y, z);
    }

    public void setTranslation(Vector3 translation) {
        this.translation.set(translation);
    }

    public String printVrml() {
        return String.format(Locale.US, "# %s"
                + "Transform {\n"
                + "\ttranslation %.2f %.2f %.2f\n"
                + "\tscale %.2f %.2f %.2f\n"
                + "\trotation %.2f %.2f %.2f %.2f\n"
                + "\tchildren Shape {\n"
                + "\t\tgeometry %s {\n"
                + "%%s"
                + "\t\t}\n"
                + "\t\tappearance Appearance {\n"
                + "\t\t\tmaterial Material {\n"
                + "\t\t\t\tdiffuseColor %.2f %.2f %.2f\n"
                + "\t\t\t\ttransparency %.2f\n"
                + "\t\t\t}\n"
                + "\t\t}\n"
                + "\t}\n}",
                this.toString(),
                translation.x, translation.y, translation.z,
                scale.x, scale.y, scale.z,
                rotation.x, rotation.y, rotation.z, rotation.w,
                this.getClass().getSimpleName(),
                color.r, color.g, color.b,
                1 - color.a);
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
