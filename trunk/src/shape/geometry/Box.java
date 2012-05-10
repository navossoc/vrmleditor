package shape.geometry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.math.Matrix4;
import shape.Shape;

public class Box extends Shape {

    private float width;
    private float height;
    private float depth;
    Matrix4 m;

    public Box(float width, float height, float depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;

        //
        mesh = new Mesh(true, 8, 36, new VertexAttribute(Usage.Position, 3, "a_position"));

        float[] cubeVerts = {
            -1.0f, 1.0f, 1.0f, // 0
            1.0f, 1.0f, 1.0f, // 1
            1.0f, -1.0f, 1.0f, // 2
            -1.0f, -1.0f, 1.0f, // 3
            -1.0f, 1.0f, -1.0f, // 4
            1.0f, 1.0f, -1.0f, // 5
            1.0f, -1.0f, -1.0f, // 6
            -1.0f, -1.0f, -1.0f, // 7
        };

        short[] indices = {
            0, 1, 2, 0, 2, 3, // frente
            1, 2, 5, 2, 5, 6, // direita
            0, 1, 5, 0, 4, 5, // acima
            4, 5, 7, 5, 6, 7, // traseira
            2, 3, 7, 2, 6, 7, // abaixo
            0, 3, 7, 0, 4, 7 // esquerda
        };

        mesh.setVertices(cubeVerts);
        mesh.setIndices(indices);

        // TODO: remover
        mesh.scale(100, 100, 100);
    }

    @Override
    public void draw() {
        super.draw();


        mesh.render(GL10.GL_TRIANGLES);

        Gdx.gl10.glPopMatrix();
    }

    @Override
    public String toString() {
        return "Caixa " + ID;
    }
}
