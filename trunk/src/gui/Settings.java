package gui;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class Settings {

    private static final Properties properties;
    private static ResourceBundle resource;
    // custom
    private static String camera1;
    private static String camera2;
    private static String camera3;
    private static String camera4;
    private static String language;
    private static boolean wireframe;

    /**
     * Load application settings
     */
    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("settings.properties"));
        } catch (IOException ex) {
            //
        }
        // Read common settings
        initSettings();
    }

    public static void initSettings() {
        // camera1
        camera1 = properties.getProperty("camera1", "front");
        // camera2
        camera2 = properties.getProperty("camera2", "left");
        // camera3
        camera3 = properties.getProperty("camera3", "bottom");
        // camera4
        camera4 = properties.getProperty("camera4", "free");
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

    public static boolean isWireframe() {
        return wireframe;
    }

    public static void setWireframe(boolean wireframe) {
        Settings.wireframe = wireframe;
    }
}
