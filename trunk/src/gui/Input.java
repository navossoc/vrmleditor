package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import java.util.Iterator;
import shape.Shape;

public class Input extends InputAdapter {

    private Editor editor;
    private Renderer renderer;
    private Camera cameraSelected;

    public Input(Editor editor, Renderer renderer) {
        this.editor = editor;
        this.renderer = renderer;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        Camera[] cameras = renderer.getCameras();
        int width = Gdx.graphics.getWidth() / 2;
        int height = Gdx.graphics.getHeight() / 2;

        // left button
        if (button == Buttons.LEFT) {
            int cam = 0;

            if (x < width) {
                cam |= 8; // left
            } else {
                cam |= 4; // right
            }

            if (y > height) {
                cam |= 2; // bottom
            } else {
                cam |= 1; // top
            }

            Ray ray = null;
            switch (cam) {
                case 9:
                    cameraSelected = cameras[0];
                    ray = cameraSelected.getPickRay(x, y, 0, height, width, height);
                    break;
                case 5:
                    cameraSelected = cameras[1];
                    ray = cameraSelected.getPickRay(x, y, width, height, width, height);
                    break;
                case 10:
                    cameraSelected = cameras[2];
                    ray = cameraSelected.getPickRay(x, y, 0, 0, width, height);
                    break;
                case 6:
                    cameraSelected = cameras[3];
                    ray = cameraSelected.getPickRay(x, y, width, 0, width, height);
                    break;
            }

            final Vector3 intersection = new Vector3();
            Iterator<Shape> iterator = renderer.getListShapes().iterator();
            Shape selected = null;
            float smallerDistance = Float.MAX_VALUE;
            while (iterator.hasNext()) {
                Shape shape = iterator.next();
                if (shape.intersect(ray, intersection)) {
                    float distance = intersection.dst(cameraSelected.position);
                    if (distance < smallerDistance) {
                        smallerDistance = distance;
                        selected = shape;
                    }
                }
            }
            // select shape on JList
            editor.selectShape(selected);
        }
        return true;
    }
}
