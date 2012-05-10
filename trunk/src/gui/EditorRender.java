package gui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.math.MathUtils;
import java.util.Enumeration;
import javax.swing.DefaultListModel;
import shape.Shape;

public class EditorRender implements ApplicationListener {

    private DefaultListModel shapes;

    private OrthographicCamera cameraFront;
    private OrthographicCamera cameraSide;
    private OrthographicCamera cameraTop;
    private PerspectiveCamera camera3D;
    private int width, height;

    public EditorRender(DefaultListModel shapes) {
        this.shapes = shapes;
    }

    @Override
    public void create() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        // 1 - Camera Front (x/y)
        cameraFront = new OrthographicCamera(width, height);
        cameraFront.far = 10000;
        //cameraFront.rotate(0, 0, 0, 0);
        cameraFront.position.set(0, 0, 500);

        // 2 - Camera Side (z/y)
        cameraSide = new OrthographicCamera(width, height);
        cameraSide.far = 10000;
        cameraSide.rotate(90, 0, 1, 0);
        cameraSide.position.set(500, 0, 0);

        // 3 - Camera Top (x/z)
        cameraTop = new OrthographicCamera(width, height);
        cameraTop.far = 10000;
        cameraTop.rotate(-90, 1, 0, 0);
        cameraTop.position.set(0, 500, 0);

        // 4 - Camera 3D (Free)
        camera3D = new PerspectiveCamera(45, width, height);
        camera3D.far = 10000;
        camera3D.position.set(300, 600, 500);
        camera3D.lookAt(0, 0, 0);

        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        this.cameraFront.viewportWidth = width;
        this.cameraFront.viewportHeight = height;
        this.cameraSide.viewportWidth = width;
        this.cameraSide.viewportHeight = height;
        this.cameraTop.viewportWidth = width;
        this.cameraTop.viewportHeight = height;
        this.camera3D.viewportWidth = width;
        this.camera3D.viewportHeight = height;

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

        // 1 - Camera Front (x/y)
        adjustCamera(cameraFront, 0, height);
        drawShapes();

        // 2 - Camera Side (z/y)
        adjustCamera(cameraSide, width, height);
        drawShapes();

        // 3 - Camera Top (x/z)
        adjustCamera(cameraTop, 0, 0);
        drawShapes();

        // 4 - Camera 3D (free)
        adjustCamera(camera3D, width, 0);
        drawShapes();
    }

    private void adjustCamera(Camera camera, int x, int y) {
        // TODO: testar com a ordem do update invertida depois
        camera.update();
        Gdx.gl10.glMatrixMode(GL10.GL_PROJECTION);
        Gdx.gl10.glLoadMatrixf(camera.combined.val, 0);
        Gdx.gl10.glMatrixMode(GL10.GL_MODELVIEW);
        Gdx.gl10.glLoadIdentity();
        Gdx.gl10.glViewport(x / 2, y / 2, width / 2, height / 2);
        // TODO: remover, apenas debug
        Gdx.gl10.glPolygonMode(GL10.GL_FRONT_AND_BACK, GL10.GL_LINE);
        //Gdx.gl10.glPolygonMode(GL10.GL_FRONT_AND_BACK, GL10.GL_FILL);
    }

    private void drawShapes() {
        /*
        // draw all shapes
        Enumeration e = shapes.elements();
        Shape shape;
        while (e.hasMoreElements()) {
            shape = (Shape) e.nextElement();
            //shape.draw(g);
        }
        */

        //

        float bottomRadius = 150f;
        float height = 250f;

        int segments = (int) (6 * Math.cbrt(bottomRadius));
        //int segments = 3;
        System.out.println("segments = " + segments);
        float angle = 2 * 3.1415926f / segments;
        float cos = MathUtils.cos(angle);
        float sin = MathUtils.sin(angle);
        float cx = bottomRadius, cz = 0;

        int nVertices = segments + 2;
        float[] v = new float[nVertices * 3];
        int i;
        for (i = 0; i < ((nVertices - 3) * 3);) {
            v[i++] = cx;
            v[i++] = 0;
            v[i++] = cz;
            float temp = cx;
            cx = cos * cx - sin * cz;
            cz = sin * temp + cos * cz;
        }
        // adiciona o ultimo vertice
        v[i++] = cx;
        v[i++] = 0;
        v[i++] = cz;

        //adicionado o vertice do meio
        v[i++] = 0;
        v[i++] = 0;
        v[i++] = 0;

        //adicionado o vertice do topo
        v[i++] = 0;
        v[i++] = height;
        v[i++] = 0;

        short[] idc = new short[segments * 3 * 2];
        short index = 0;
        for (i = 0; i < idc.length - 6;) {
            // circulo da parte de cima
            idc[i++] = index;
            idc[i++] = (short) (index + 1);
            idc[i++] = (short) (nVertices - 2);
            idc[i++] = index++;
            idc[i++] = index;
            idc[i++] = (short) (nVertices - 1);
        }

        // circulo da parte de cima
        idc[i++] = index;
        idc[i++] = 0;
        idc[i++] = (short) (nVertices - 2);
        idc[i++] = index;
        idc[i++] = 0;
        idc[i++] = (short) (nVertices - 1);

        Mesh m = new Mesh(true, v.length, idc.length, new VertexAttribute(Usage.Position, 3, "a_position"));
        m.setVertices(v);
        m.setIndices(idc);

        m.render(GL10.GL_TRIANGLES);
        //

    }

    @Override
    public void pause() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void resume() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void dispose() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
