package gui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import java.util.Enumeration;
import javax.swing.DefaultListModel;
import shape.Axis;
import shape.Border;
import shape.Shape;

public class Render implements ApplicationListener {

    private DefaultListModel shapes;
    private OrthographicCamera cameraFront;
    private OrthographicCamera cameraSide;
    private OrthographicCamera cameraTop;
    private PerspectiveCamera camera3D;
    private int width, height;

    public Render(DefaultListModel shapes) {
        this.shapes = shapes;
    }

    @Override
    public void create() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        // 1 - Camera Front (x/y)
        cameraFront = new OrthographicCamera(width, height);
        cameraFront.far = Short.MAX_VALUE;
        //cameraFront.rotate(0, 0, 0, 0);
        cameraFront.position.set(0, 0, 500);

        // 2 - Camera Side (z/y)
        cameraSide = new OrthographicCamera(width, height);
        cameraSide.far = Short.MAX_VALUE;
        cameraSide.rotate(90, 0, -1, 0);
        cameraSide.position.set(-500, 0, 0);

        // 3 - Camera Top (x/z)
        cameraTop = new OrthographicCamera(width, height);
        cameraTop.far = Short.MAX_VALUE;
        cameraTop.rotate(90, 1, 0, 0);
        cameraTop.position.set(0, -500, 0);

        // 4 - Camera 3D (Free)
        camera3D = new PerspectiveCamera(45, width, height);
        camera3D.far = Short.MAX_VALUE;
        camera3D.position.set(300, 600, 500);
        camera3D.lookAt(0, 0, 0);
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

        // All - Draw borders
        Border.draw(width, height);
    }

    private void adjustCamera(Camera camera, int x, int y) {
        // TODO: testar com a ordem do update invertida depois
        camera.update();
        Gdx.gl10.glMatrixMode(GL10.GL_PROJECTION);
        Gdx.gl10.glLoadMatrixf(camera.combined.val, 0);
        Gdx.gl10.glMatrixMode(GL10.GL_MODELVIEW);
        Gdx.gl10.glLoadIdentity();
        Gdx.gl10.glViewport(x / 2, y / 2, width / 2, height / 2);
    }

    private void drawShapes() {
        // draw axis
        Axis.draw();
        // draw all shapes
        Enumeration e = shapes.elements();
        Shape shape;
        while (e.hasMoreElements()) {
            shape = (Shape) e.nextElement();
            // FIXME: apenas para debug
            Gdx.gl10.glPolygonMode(GL10.GL_FRONT_AND_BACK, GL10.GL_LINE);
            shape.draw();
        }
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
