package gui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;
import java.util.Iterator;
import shape.Axis;
import shape.Border;
import shape.DistanceSorter;
import shape.Shape;

public class Renderer implements ApplicationListener {

    private Array<Shape> listShapes;
    private Camera[] cameras;
    private Vector2[] viewports;
    // variable members for optimization
    private final Vector3 intersection;
    private final Ray ray;

    public Renderer() {
        listShapes = new Array<Shape>(false, 16);
        intersection = new Vector3();
        ray = new Ray(Vector3.Zero, Vector3.Zero);
    }

    @Override
    public void create() {
        Gdx.graphics.setVSync(Settings.getVSync());

        // create and configure cameras
        cameras = new Camera[Constants.CAMERAS_TOTAL];
        viewports = new Vector2[cameras.length];
        for (int i = 0; i < cameras.length; i++) {
            cameras[i] = CameraUtil.configureCamera(Settings.getCamera(i));
            viewports[i] = new Vector2();
        }
    }

    @Override
    public void resize(int width, int height) {
        width /= 2;
        height /= 2;
        for (int i = 0; i < cameras.length; i++) {
            cameras[i].viewportWidth = width;
            cameras[i].viewportHeight = height;
        }

        viewports[0].set(0, height);
        viewports[1].set(width, height);
        viewports[2].set(0, 0);
        viewports[3].set(width, 0);
    }

    @Override
    public void render() {
        Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        Gdx.gl10.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

        for (int i = 0; i < cameras.length; i++) {
            adjustCamera(i);
            drawShapes(i);
        }

        // All - Draw borders
        Border.draw(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private void adjustCamera(int index) {
        Camera camera = cameras[index];
        Vector2 viewport = viewports[index];
        camera.update();
        Gdx.gl10.glMatrixMode(GL10.GL_PROJECTION);
        Gdx.gl10.glLoadMatrixf(camera.combined.val, 0);
        Gdx.gl10.glMatrixMode(GL10.GL_MODELVIEW);
        Gdx.gl10.glLoadIdentity();
        Gdx.gl10.glViewport((int) viewport.x, (int) viewport.y, (int) camera.viewportWidth, (int) camera.viewportHeight);
    }

    private void drawShapes(int index) {
        // draw axis
        Axis.draw();

        // check if list is empty
        if (listShapes.size == 0) {
            return;
        }

        // wireframe mode
        if (Settings.isWireframe()) {
            Gdx.gl10.glPolygonMode(GL10.GL_FRONT_AND_BACK, GL10.GL_LINE);
        } else {
            Gdx.gl10.glPolygonMode(GL10.GL_FRONT_AND_BACK, GL10.GL_FILL);
        }

        Camera camera = cameras[index];
        Iterator<Shape> iterator;
        // calculate distance
        iterator = listShapes.iterator();
        while (iterator.hasNext()) {
            Shape shape = iterator.next();

            // calculate ray
            ray.origin.set(camera.position);
            ray.direction.set(shape.getTranslation()).sub(camera.position).nor();

            // try to intersect shape with ray
            if (shape.intersect(ray, intersection)) {
                float distance = camera.position.dst(intersection);
                shape.getSorter().setDistance(distance);
            }
        }

        // sort all shapes by distance
        listShapes.sort(DistanceSorter.getReverseComparator());

        // enable depth test
        Gdx.gl10.glDepthFunc(GL10.GL_LEQUAL);
        Gdx.gl10.glEnable(GL10.GL_DEPTH_TEST);

        // draw opaque shapes
        iterator = listShapes.iterator();
        while (iterator.hasNext()) {
            Shape shape = iterator.next();
            if (shape.getColor().a >= 1.0f) {
                shape.draw();
            }
        }

        // enable blend
        Gdx.gl10.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl10.glEnable(GL10.GL_BLEND);

        // draw alpha shapes
        iterator = listShapes.iterator();
        while (iterator.hasNext()) {
            Shape shape = iterator.next();
            if (shape.getColor().a < 1.0f) {
                shape.draw();
            }
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

    /**
     * Get a specific camera
     *
     * @param index
     * @return
     */
    public Camera getCamera(int index) {
        return cameras[index];
    }

    /**
     * Return the cameras
     *
     * @return
     */
    public Camera[] getCameras() {
        return cameras;
    }

    /**
     * Get list of rendered shapes
     *
     * @return
     */
    public Array<Shape> getListShapes() {
        return listShapes;
    }

    /**
     * Get a specific viewport
     *
     * @param index
     * @return
     */
    public Vector2 getViewPort(int index) {
        return viewports[index];
    }

    /**
     * Return the viewports
     *
     * @return
     */
    public Vector2[] getViewports() {
        return viewports;
    }
}
