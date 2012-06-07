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
            PerspectiveCamera temp = new PerspectiveCamera(45, width, height);
            temp.far = Short.MAX_VALUE;
            temp.position.set(3, 6, 5);
            temp.lookAt(0, 0, 0);
            return temp;
        } else {
            OrthographicCamera temp = new OrthographicCamera(width, height);
            temp.far = Short.MAX_VALUE;
            temp.zoom = 1.0f / 100;

            // Cameras
            if (option.equalsIgnoreCase("front")) {
                // Camera Front (x+/y)
                //temp.rotate(0, 0, 0, 0);
                temp.position.set(0, 0, 500);
            } else if (option.equalsIgnoreCase("back")) {
                // Camera Back (x-/y)
                temp.rotate(180, 0, 1, 0);
                temp.position.set(0, 0, -500);
            } else if (option.equalsIgnoreCase("left")) {
                // Camera Left (z+/y)
                temp.rotate(90, 0, -1, 0);
                temp.position.set(-500, 0, 0);
            } else if (option.equalsIgnoreCase("right")) {
                // Camera Right (z-/y)
                temp.rotate(90, 0, 1, 0);
                temp.position.set(500, 0, 0);
            } else if (option.equalsIgnoreCase("bottom")) {
                // Camera Bottom (x+/z)
                temp.rotate(90, 1, 0, 0);
                temp.position.set(0, -500, 0);
            } else if (option.equalsIgnoreCase("top")) {
                // Camera Top (x-/z)
                temp.rotate(90, -1, 0, 0);
                temp.position.set(0, 500, 0);
            }
            return temp;
        }
    }
}
