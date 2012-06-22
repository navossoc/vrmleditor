package gui;

import com.badlogic.gdx.math.Vector3;

public class Constants {

    // cameras
    public static final float CAMERA_ORTHOGRAPHIC_ZOOM = 8;
    public static final float CAMERA_ORTHOGRAPHIC_POSITION = 500;
    public static final float CAMERA_PERSPECTIVE_FOV = 45;
    public static final Vector3 CAMERA_PERSPECTIVE_POSITION = new Vector3(5, 9, 8);
    public static final int CAMERAS_TOTAL = 4;
    public static final int CAMERAS_MODE = 7;
    public static final int CAMERA_DESCRIPTION_PADDING = 5;
    public static final float ZOOM_ORTHOGRAPHIC = 2000;
    public static final float ZOOM_PERSPECTIVE = 2;
    // menus
    public static final int MENU_FILE = 0;
    public static final int MENU_EDIT = 1;
    public static final int MENU_VIEW = 2;
    public static final int MENU_HELP = 3;
    // menu file
    public static final int MENU_FILE_NEW = 0;
    public static final int MENU_FILE_OPEN = 1;
    public static final int MENU_FILE_SAVE = 2;
    public static final int MENU_FILE_EXPORT = 3;
    public static final int MENU_FILE_SEPARATOR = 4;
    public static final int MENU_FILE_EXIT = 5;
    // menu edit
    public static final int MENU_EDIT_UNDO = 0;
    public static final int MENU_EDIT_REDO = 1;
    public static final int MENU_EDIT_SEPARATOR = 2;
    public static final int MENU_EDIT_DELETE = 3;
    // menu view
    public static final int MENU_VIEW_CAMERA1 = 0;
    public static final int MENU_VIEW_CAMERA2 = 1;
    public static final int MENU_VIEW_CAMERA3 = 2;
    public static final int MENU_VIEW_CAMERA4 = 3;
    public static final int MENU_VIEW_SEPARATOR = 4;
    public static final int MENU_VIEW_WIREFRAME = 5;
    // menu view -> camera
    public static final int MENU_VIEW_CAMERA_FRONT = 0;
    public static final int MENU_VIEW_CAMERA_BACK = 1;
    public static final int MENU_VIEW_CAMERA_LEFT = 2;
    public static final int MENU_VIEW_CAMERA_RIGHT = 3;
    public static final int MENU_VIEW_CAMERA_BOTTOM = 4;
    public static final int MENU_VIEW_CAMERA_TOP = 5;
    public static final int MENU_VIEW_CAMERA_FREE = 6;
    // menu help
    public static final int MENU_HELP_ABOUT = 0;
    // tabs
    public static final int TAB_OBJECTS = 0;
    public static final int TAB_PROPERTIES = 1;
}
