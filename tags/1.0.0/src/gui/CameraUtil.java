package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;

public class CameraUtil {

    public enum Mode {

        Front, Back, Left, Right, Bottom, Top, Free
    }

    public static Camera configureCamera(Mode mode) {
        int width = Gdx.graphics.getWidth() / 2;
        int height = Gdx.graphics.getHeight() / 2;

        if (mode == Mode.Free) {
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
            switch (mode) {
                case Front: {   // (+x/+y)
                    //temp.rotate(0, 0, 1, 0);
                    temp.position.set(0, 0, Constants.CAMERA_ORTHOGRAPHIC_POSITION);
                    break;
                }
                case Back: { // (-x/+y)
                    temp.rotate(180, 0, 1, 0);
                    temp.position.set(0, 0, -Constants.CAMERA_ORTHOGRAPHIC_POSITION);
                    break;
                }
                case Left: { // (+z/+y)
                    temp.rotate(90, 0, -1, 0);
                    temp.position.set(-Constants.CAMERA_ORTHOGRAPHIC_POSITION, 0, 0);
                    break;
                }
                case Right: { // (-z/+y)
                    temp.rotate(90, 0, 1, 0);
                    temp.position.set(Constants.CAMERA_ORTHOGRAPHIC_POSITION, 0, 0);
                    break;
                }
                case Bottom: {  // (+x/+z)
                    temp.rotate(90, 1, 0, 0);
                    temp.position.set(0, -Constants.CAMERA_ORTHOGRAPHIC_POSITION, 0);
                    break;
                }
                case Top: { // (-x/+z)
                    temp.rotate(90, -1, 0, 0);
                    temp.position.set(0, Constants.CAMERA_ORTHOGRAPHIC_POSITION, 0);
                    break;
                }
            }
            return temp;
        }
    }
}
