package gui;

import gui.CameraUtil.Mode;
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
    private static Mode[] modes;
    private static boolean fontBold;
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
        modes = new Mode[Constants.CAMERAS_TOTAL];
        initSettings();
    }

    public static void initSettings() {
        // cameras
        final String[] defaultModes = new String[]{"front", "left", "bottom", "free"};
        for (int i = 0; i < Constants.CAMERAS_TOTAL; i++) {
            String s = properties.getProperty("camera" + (i + 1), defaultModes[i]);
            for (Mode m : CameraUtil.Mode.values()) {
                if (m.toString().equalsIgnoreCase(s)) {
                    modes[i] = m;
                    break;
                }
            }
        }
        // font bold
        fontBold = Boolean.parseBoolean(properties.getProperty("font-bold", "false"));
        // wireframe
        wireframe = Boolean.parseBoolean(properties.getProperty("wireframe", "false"));
        // vsync
        vsync = Boolean.parseBoolean(properties.getProperty("vsync", "true"));
    }

    public static boolean isFontBold() {
        return fontBold;
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

    public static Mode getCamera(int index) {
        return modes[index];
    }

    public static void setCamera(int index, Mode mode) {
        modes[index] = mode;
    }

    public static String getCameraDescription(int index) {
        return resource.getString("Menu.View.Camera." + modes[index].toString());
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
