package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import java.util.Iterator;
import shape.Shape;

public class Input extends InputAdapter {

    private Editor editor;
    private Renderer renderer;

    public Input(Editor editor, Renderer renderer) {
        this.editor = editor;
        this.renderer = renderer;
    }

    @Override
    public boolean scrolled(int amount) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        // get camera and viewport
        int index = getCameraCode(x, y);
        Camera camera = renderer.getCamera(index);

        if (camera instanceof OrthographicCamera) {
            OrthographicCamera cam = (OrthographicCamera) camera;
            cam.zoom += ((float) amount / 2000f);
        } else if (camera instanceof PerspectiveCamera) {
            camera.position.sub(camera.direction.tmp().mul((float) amount / 2f));
        }

        return true;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        // left mouse button
        if (button == Buttons.LEFT) {
            // get camera and viewport
            int index = getCameraCode(x, y);
            final Camera camera = renderer.getCamera(index);
            final Vector2 viewport = renderer.getViewPort(index);
            final Ray ray = camera.getPickRay(x, y, viewport.x, viewport.y, camera.viewportWidth, camera.viewportHeight);

            // get closest shape from camera
            final Vector3 intersection = new Vector3();
            Iterator<Shape> iterator = renderer.getListShapes().iterator();
            Shape selected = null;
            float smallerDistance = Float.MAX_VALUE;
            while (iterator.hasNext()) {
                Shape shape = iterator.next();
                if (shape.intersect(ray, intersection)) {
                    float distance = intersection.dst(camera.position);
                    if (distance < smallerDistance) {
                        smallerDistance = distance;
                        selected = shape;
                    }
                }
            }

            // select shape on JList
            editor.selectShape(selected);
            return true;
        }

        return false;
    }

    /**
     * Get camera code
     *
     * @param x mouse pointer x
     * @param y mouse pointer y
     * @return 0 to cameras.length
     */
    private int getCameraCode(int x, int y) {
        int cam = 0;
        if (x < Gdx.graphics.getWidth() / 2) {
            cam |= 0; // left
        } else {
            cam |= 1; // right
        }

        if (y > Gdx.graphics.getHeight() / 2) {
            cam |= 2; // bottom
        } else {
            cam |= 0; // top
        }

        return cam;
    }
}
