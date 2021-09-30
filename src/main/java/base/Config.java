package base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Config {
    private static Properties prop;

    private static final String prop_file_name = "config.properties";

    private static Map<String, String> custom_values = new HashMap<>();

    private static Properties getProp() throws IOException {
        if (prop != null) return prop;

        prop = new Properties();

        System.out.println(Config.class.getProtectionDomain().getCodeSource().getLocation().getPath());

        InputStream is = Config.class.getClassLoader().getResourceAsStream(prop_file_name);

        if (is != null) {
            prop.load(is);
        } else {
            throw new FileNotFoundException("property file '"+ prop_file_name +"' not found in the classpath");
        }

        return prop;
    }

    // TODO: detect path to bin directory properly
    public static String getBinDirectory() {
        return new File("./webdrivers/").getAbsolutePath();
    }

    public static void setValue(String key, String value) {
        custom_values.put(key, value);
    }

    public static String getString(String name, String default_value) throws IOException {
        return getProp().getProperty(name, default_value);
    }

    public static String getString(String name) throws IOException {
        return getString(name, null);
    }

    public static boolean getBoolean(String name, boolean default_value) throws IOException {
        String prop = getString(name);
        if (prop == null) return default_value;
        if ("true".equals(prop.toLowerCase())) return true;
        if ("1".equals(prop)) return true;
        if ("yes".equals(prop.toLowerCase())) return true;
        return false;
    }

    public static boolean getBoolean(String name) throws IOException {
        return getBoolean(name, false);
    }
}
