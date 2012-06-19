package gui;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Settings {

    private static final Properties properties;
    private static ResourceBundle resource;
    // custom
    private static String[] cameras;
    private static String language;
    private static boolean wireframe;
    private static boolean vsync;

    /**
     * Load application settings
     */
    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("settings.properties"));
        } catch (IOException ex) {
            // fallback to default settings
        }
        // Read common settings
        cameras = new String[Constants.CAMERAS_TOTAL];
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
        // wireframe
        wireframe = Boolean.parseBoolean(properties.getProperty("wireframe", "false"));
        // vsync
        vsync = Boolean.parseBoolean(properties.getProperty("vsync", "true"));
    }

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage() {
        // language
        language = properties.getProperty("language", "en_US");
        URL fileLanguage = ClassLoader.getSystemClassLoader().getResource("languages/" + language + ".properties");;
        Locale locale = Locale.US;

        if (fileLanguage == null) {
            language = "en_US";
        } else {
            locale = new Locale(language.substring(0, 2).toLowerCase(), language.substring(3, 5).toUpperCase());
        }

        resource = ResourceBundle.getBundle("languages/" + language);
        Locale.setDefault(locale);
    }

    public static String getMessage(String key) {
        return resource.getString(key);
    }

    public static String getMessage(String key, Object... params) {
        return String.format(resource.getString(key), params);
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

    public static boolean getVSync() {
        return vsync;
    }
}
