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

    private DefaultListModel listShapes;
    //private TreeMultimap<Float, Shape> treeShapes;
    private OrthographicCamera camera1;
    private OrthographicCamera camera2;
    private OrthographicCamera camera3;
    private PerspectiveCamera camera4;
    private int width, height;
    private boolean wireframe;

    public Render(DefaultListModel shapes) {
        this.listShapes = shapes;
    }

    @Override
    public void create() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        // 1 - Camera Front (x/y)
        camera1 = new OrthographicCamera(width, height);
        camera1.far = Short.MAX_VALUE;
        //cameraFront.rotate(0, 0, 0, 0);
        camera1.position.set(0, 0, 500);
        camera1.zoom = 1.0f / 100;

        // 2 - Camera Left (z/y)
        camera2 = new OrthographicCamera(width, height);
        camera2.far = Short.MAX_VALUE;
        camera2.rotate(90, 0, -1, 0);
        camera2.position.set(-500, 0, 0);
        camera2.zoom = 1.0f / 100;

        // 3 - Camera Bottom (x/z)
        camera3 = new OrthographicCamera(width, height);
        camera3.far = Short.MAX_VALUE;
        camera3.rotate(90, 1, 0, 0);
        camera3.position.set(0, -500, 0);
        camera3.zoom = 1.0f / 100;

        // 4 - Camera 3D (Free)
        camera4 = new PerspectiveCamera(45, width, height);
        camera4.far = Short.MAX_VALUE;
        camera4.position.set(3, 6, 5);
        camera4.lookAt(0, 0, 0);

        //wireframe
        setWireframe(true);
    }

    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        this.camera1.viewportWidth = width;
        this.camera1.viewportHeight = height;
        this.camera2.viewportWidth = width;
        this.camera2.viewportHeight = height;
        this.camera3.viewportWidth = width;
        this.camera3.viewportHeight = height;
        this.camera4.viewportWidth = width;
        this.camera4.viewportHeight = height;
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

        // 1 - Camera Front (x/y)
        adjustCamera(camera1, 0, height);
        drawShapes();

        // 2 - Camera Side (z/y)
        adjustCamera(camera2, width, height);
        drawShapes();

        // 3 - Camera Top (x/z)
        adjustCamera(camera3, 0, 0);
        drawShapes();

        // 4 - Camera 3D (free)
        adjustCamera(camera4, width, 0);
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

        // wireframe mode
        if (isWireframe()) {
            Gdx.gl10.glPolygonMode(GL10.GL_FRONT_AND_BACK, GL10.GL_LINE);
        } else {
            Gdx.gl10.glPolygonMode(GL10.GL_FRONT_AND_BACK, GL10.GL_FILL);
        }

        // draw all shapes
        Enumeration e = listShapes.elements();
        Shape shape;
        while (e.hasMoreElements()) {
            shape = (Shape) e.nextElement();
            shape.draw();
        }
    }

    public boolean isWireframe() {
        return wireframe;
    }

    public void setWireframe(boolean wireframe) {
        this.wireframe = wireframe;
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
