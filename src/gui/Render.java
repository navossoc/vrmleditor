package gui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.google.common.collect.Ordering;
import com.google.common.collect.TreeMultimap;
import java.util.Enumeration;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import shape.Axis;
import shape.Border;
import shape.Shape;

public class Render implements ApplicationListener {

    private DefaultListModel listShapes;
    private TreeMultimap<Float, Shape> treeShapes;
    private Camera[] cameras;
    private int width, height;

    public Render(DefaultListModel shapes) {
        this.listShapes = shapes;
    }

    @Override
    public void create() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        // create and configure cameras
        cameras = new Camera[4];
        for (int i = 0; i < cameras.length; i++) {
            cameras[i] = CameraUtil.configureCamera(Settings.getCamera(i));
        }

        // temporary tree for alpha shapes
        treeShapes = TreeMultimap.create(Ordering.natural().reverse(), Ordering.natural());
    }

    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        for (int i = 0; i < cameras.length; i++) {
            cameras[i].viewportWidth = width;
            cameras[i].viewportHeight = height;
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

        // 1 - Camera Front (x/y)
        adjustCamera(cameras[0], 0, height);
        drawShapes(cameras[0]);

        // 2 - Camera Side (z/y)
        adjustCamera(cameras[1], width, height);
        drawShapes(cameras[1]);

        // 3 - Camera Top (x/z)
        adjustCamera(cameras[2], 0, 0);
        drawShapes(cameras[2]);

        // 4 - Camera 3D (free)
        adjustCamera(cameras[3], width, 0);
        drawShapes(cameras[3]);

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

    private void drawShapes(Camera camera) {
        // draw axis
        Axis.draw();

        // wireframe mode
        if (Settings.isWireframe()) {
            Gdx.gl10.glPolygonMode(GL10.GL_FRONT_AND_BACK, GL10.GL_LINE);
        } else {
            Gdx.gl10.glPolygonMode(GL10.GL_FRONT_AND_BACK, GL10.GL_FILL);
        }

        // enable blend
        Gdx.gl10.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl10.glEnable(GL10.GL_BLEND);

        // enable depth test
        Gdx.gl10.glEnable(GL10.GL_DEPTH_TEST);

        // drawing
        treeShapes.clear();

        float distance;
        Shape shape;
        Ray ray = new Ray(new Vector3(), new Vector3());
        Vector3 intersection = new Vector3();

        Enumeration e = listShapes.elements();
        while (e.hasMoreElements()) {
            shape = (Shape) e.nextElement();
            // sort alpha shapes
            if (shape.getColor().a < 1.0f) {
                // calculate ray
                ray.set(camera.position, shape.getTranslation().tmp().sub(camera.position).nor());
                // try to intersect shape with ray
                if (shape.intersect(ray, intersection)) {
                    distance = camera.position.dst(intersection);
                    treeShapes.put(distance, shape);
                    continue;
                }
            }
            // draw opaque shapes
            shape.draw();
        }

        // draw alpha shapes
        Iterator<Shape> iterator = treeShapes.values().iterator();
        while (iterator.hasNext()) {
            shape = iterator.next();
            shape.draw();
        }

        // disable depth and blend
        Gdx.gl10.glDisable(GL10.GL_DEPTH_TEST);
        Gdx.gl10.glDisable(GL10.GL_BLEND);
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
