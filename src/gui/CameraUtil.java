package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;

public class CameraUtil {

    public static Camera configureCamera(String option) {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        if (option.equalsIgnoreCase("free")) {
            PerspectiveCamera temp = new PerspectiveCamera(Constants.CAMERA_PERSPECTIVE_FOV, width, height);
            temp.far = Short.MAX_VALUE;
            temp.position.set(Constants.CAMERA_PERSPECTIVE_POSITION);
            temp.lookAt(0, 0, 0);
            return temp;
        } else {
            OrthographicCamera temp = new OrthographicCamera(width, height);
            temp.far = Short.MAX_VALUE;
            if (width < height) {
                temp.zoom = Constants.CAMERA_ORTHOGRAPHIC_ZOOM / width;
            } else {
                temp.zoom = Constants.CAMERA_ORTHOGRAPHIC_ZOOM / height;
            }
            // Cameras
            if (option.equalsIgnoreCase("front")) {
                // Camera Front (x+/y)
                //temp.rotate(0, 0, 1, 0);
                temp.position.set(0, 0, Constants.CAMERA_ORTHOGRAPHIC_POSITION);
            } else if (option.equalsIgnoreCase("back")) {
                // Camera Back (x-/y)
                temp.rotate(180, 0, 1, 0);
                temp.position.set(0, 0, -Constants.CAMERA_ORTHOGRAPHIC_POSITION);
            } else if (option.equalsIgnoreCase("left")) {
                // Camera Left (z+/y)
                temp.rotate(90, 0, -1, 0);
                temp.position.set(-Constants.CAMERA_ORTHOGRAPHIC_POSITION, 0, 0);
            } else if (option.equalsIgnoreCase("right")) {
                // Camera Right (z-/y)
                temp.rotate(90, 0, 1, 0);
                temp.position.set(Constants.CAMERA_ORTHOGRAPHIC_POSITION, 0, 0);
            } else if (option.equalsIgnoreCase("bottom")) {
                // Camera Bottom (x+/z)
                temp.rotate(90, 1, 0, 0);
                temp.position.set(0, -Constants.CAMERA_ORTHOGRAPHIC_POSITION, 0);
            } else if (option.equalsIgnoreCase("top")) {
                // Camera Top (x-/z)
                temp.rotate(90, -1, 0, 0);
                temp.position.set(0, Constants.CAMERA_ORTHOGRAPHIC_POSITION, 0);
            }
            return temp;
        }
    }
}
