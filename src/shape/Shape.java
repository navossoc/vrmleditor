package shape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // transformations methods
    public void scale(float x, float y, float z) {
        this.scale.set(x, y, z);
    }

    public void scale(Vector3 scale) {
        this.scale.set(scale);
    }

    public void rotate(float x, float y, float z, float angle) {
        this.rotation.set(x, y, z, angle);
    }

    public void rotate(Quaternion rotation) {
        this.rotation.set(rotation);
    }

    public void translate(float x, float y, float z) {
        this.translation.set(x, y, z);
    }

    public void translate(Vector3 translation) {
        this.translation.set(translation);
    }

    @Override
    public String toString() {
        return "Shape " + ID;
    }
}
