package shape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import transform.Rotation;
import transform.Scale;
import transform.Transform;
import transform.Translation;

public abstract class Shape {

    public static int ID_COUNTER = 1;
    protected int ID;
    protected Vector3 position;
    protected Color color;
    protected ArrayList<Transform> transformations;

    protected Mesh mesh;

    public Shape() {
        ID = ID_COUNTER++;

        color = new Color(0, 0, 0, 0);
        //position = new Vector3(0, 0, 0);
        position = new Vector3(
                    MathUtils.random(0, 100),
                    MathUtils.random(0, 100),
                    MathUtils.random(0, 100)
                );
        transformations = new ArrayList<Transform>();
    }

    public void draw() {
        // adjust color
        Gdx.gl10.glColor4f(color.r, color.g, color.b, color.a);
        // TODO: scale

        // TODO: rotate

        // TODO: translate
        Gdx.gl10.glPushMatrix();
        Gdx.gl10.glLoadMatrixf(new Matrix4().translate(position).val, 0);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // transformations methods
    public void scale(Scale scale) {
        transformations.add(scale);
    }

    public void scale(float x, float y, float z) {
        scale(new Scale(x, y, z));
    }

    public void rotate(Rotation rotation) {
        transformations.add(rotation);
    }

    public void rotate(float x, float y, float z, float angle) {
        rotate(new Rotation(x, y, z, angle));
    }

    public void translate(Translation translation) {
        transformations.add(translation);
    }

    public void translate(float x, float y, float z) {
        translate(new Translation(x, y, z));
    }

    @Override
    public String toString() {
        return "Shape " + ID;
    }
}
