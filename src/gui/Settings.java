package gui;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class Settings {

    private static final Properties properties;
    private static ResourceBundle resource;
    // custom
    private static String[] cameras;
    private static String language;
    private static boolean wireframe;

    /**
     Load application settings
     */
    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("settings.properties"));
        } catch (IOException ex) {
            // fallback to default settings
        }
        // Read common settings
        cameras = new String[4];
        initSettings();
    }

    public static void initSettings() {
        // camera1
        cameras[0] = properties.getProperty("camera1", "front");
        // camera2
        cameras[1] = properties.getProperty("camera2", "left");
        // camera3
        cameras[2] = properties.getProperty("camera3", "bottom");
        // camera4
        cameras[3] = properties.getProperty("camera4", "free");
        // language
        language = properties.getProperty("language", "pt_BR");
        resource = ResourceBundle.getBundle("languages/" + language);
        // wireframe
        wireframe = Boolean.parseBoolean(properties.getProperty("wireframe", "false"));
    }

    public static String getLanguage() {
        return language;
    }

    public static String getMessage(String key) {
        return resource.getString(key);
    }

    public static String getCamera(int index) {
        return cameras[index];
    }

    public static boolean isWireframe() {
        return wireframe;
    }

    public static void setWireframe(boolean wireframe) {
        Settings.wireframe = wireframe;
    }
}
