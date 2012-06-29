package shape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;

public class Border {

    protected final static Mesh mesh;

    static {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        mesh = new Mesh(true, 4, 4, VertexAttribute.Position(), VertexAttribute.Color());

        float[] vertices = new float[]{
            -width, 0, 0, Color.toFloatBits(0, 0, 0, 1),
            width, 0, 0, Color.toFloatBits(0, 0, 0, 1),
            0, -height, 0, Color.toFloatBits(0, 0, 0, 1),
            0, height, 0, Color.toFloatBits(0, 0, 0, 1),};

        short[] indices = new short[]{
            0, 1, 2, 3
        };

        mesh.setVertices(vertices);
        mesh.setIndices(indices);
    }

    public static void draw(int width, int height) {
        Gdx.gl10.glMatrixMode(GL10.GL_PROJECTION);
        Gdx.gl10.glLoadIdentity();
        Gdx.gl10.glMatrixMode(GL10.GL_MODELVIEW);
        Gdx.gl10.glLoadIdentity();
        Gdx.gl10.glViewport(0, 0, width, height);

        mesh.render(GL10.GL_LINES);
    }
}
