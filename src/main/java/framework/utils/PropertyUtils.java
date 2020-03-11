package framework.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {
    String result = "";
    InputStream inputStream;

    public static String getPropString(String propTerm){
        return instance().getEnvPropertyValue(propTerm);
    }

    public static String getPropString(String propTerm, String fromPropertyFile){
        return instance().getPropertyValueFromSpecifiedFile(propTerm, fromPropertyFile);
    }

    public static Boolean getPropBoolean(String propTerm){
        return Boolean.parseBoolean(instance().getEnvPropertyValue(propTerm));
    }

    public static Boolean getPropBoolean(String propTerm, String fromPropertyFile){
        return Boolean.parseBoolean(instance().getPropertyValueFromSpecifiedFile(propTerm, fromPropertyFile));
    }

    public static Integer getPropInteger(String propTerm){
        return Integer.parseInt(instance().getEnvPropertyValue(propTerm));
    }

    public static Integer getPropInteger(String propTerm, String fromPropertyFile){
        return Integer.parseInt(instance().getPropertyValueFromSpecifiedFile(propTerm, fromPropertyFile));
    }

    private static PropertyUtils instance(){
        return new PropertyUtils();
    }

    private String getPropertyValueFromSpecifiedFile(String propTerm, String propFileName){
        return getPropertValueFromFile(propTerm, propFileName);
    }
    private String getEnvPropertyValue(String propTerm){
        String propertyFileName = "env.properties";
        return getPropertValueFromFile(propTerm, propertyFileName);
    }

    private String getPropertValueFromFile(String propTerm, String propFile){
        try {
            Properties property = new Properties();
            String propertyFileName = propFile;

            inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);

            if (inputStream != null) {
                property.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file '" + propertyFileName + "' not found in the classpath");
            }

            String propertyValue = property.getProperty(propTerm);

            result = propertyValue;

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
