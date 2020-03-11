package framework.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.logging.Logger;

public class JsonUtils {

    private static final Logger LOG = Logger.getLogger(JsonUtils.class.getName());

    public static String getJSONResourceAsJSONString(String resource) {
        JSONObject jsonObject = getJSONResourceAsJSONObject(resource);
        String jsonString = convertJSONObjectToJSONString(jsonObject);
        return jsonString;
    }

    public static JSONObject getJSONResourceAsJSONObject(String resource) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(ResourceUtils.getResourceFile(resource));
            jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            throw new RuntimeException("Kunde inte parse JSON fil.", e);
        }
        return jsonObject;
    }

    public static JSONArray getJSONResourceAsJSONArray(String resource) {
        JSONArray jsonArray = new JSONArray();
        JSONParser parser = new JSONParser();
        try {
            jsonArray = (JSONArray)parser.parse(ResourceUtils.getResourceFile(resource));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static String convertJSONObjectToJSONString(JSONObject jsonObject) {
        String jsonString = jsonObject.toJSONString();
        return jsonString;
    }

    public static JSONObject convertJSONStringToJSONObject(String jsonString) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            jsonObject = (JSONObject)parser.parse(jsonString);
        } catch (Exception e) {
            throw new RuntimeException("Could not parse JSON string.", e);
        }
        return jsonObject;
    }

    public static JSONArray convertJSONStringToJSONArray(String jsonString) {
        JSONArray jsonArray = null;
        JSONParser parser = new JSONParser();
        try {
            jsonArray = (JSONArray) parser.parse(jsonString);
        } catch (Exception e) {
            throw new RuntimeException("Could not parse JSON string.", e);
        }
        return jsonArray;
    }


    public static void prettyPrintJSON(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .create();
        LOG.info("JSON data=" + "\n" + gson.toJson(object));
    }

    public static void prettyPrintJSON(String jsonString) {
        Object obj = null;
        try {
            obj = convertJSONStringToJSONObject(jsonString);
        } catch (Exception e) {
            // If it was a JSON array, try converting to an array before printing
            obj = convertJSONStringToJSONArray(jsonString);
        }
        prettyPrintJSON(obj);
    }

    public static void logJSONObject(JSONObject jsonObj) {
        for (Object key : jsonObj.keySet()) {
            String keyStr = (String) key;
            Object keyvalue = jsonObj.get(keyStr);

            //Log key and its value
            LOG.info("Key: " + keyStr + " Value: " + keyvalue);

            //For nested objects iteration if required
            if (keyvalue instanceof JSONObject) {
                logJSONObject((JSONObject) keyvalue);
            }
        }
    }

    public static JSONObject replaceValueInJSONObject(JSONObject jsonObject, String oldValue, String newValue){
        String jsonString = jsonObject.toJSONString();
        LOG.info("Replace " + oldValue + " med " + newValue);
        jsonString = jsonString.replaceAll(oldValue, newValue);
        jsonObject = JsonUtils.convertJSONStringToJSONObject(jsonString);
        LOG.fine("jsonObject efter replaceAll = " + jsonObject.toJSONString());
        return jsonObject;
    }

    // It's currently unable to handle multiple matches to same value
    public static String getSpecificValueFromJSON(String jsonString, String key){
        return getSpecificValueFromJSON(convertJSONStringToJSONObject(jsonString), key);
    }

    public static String getSpecificValueFromJSON(JSONObject jsonObject, String key){
        return String.valueOf(jsonObject.get(key));
    }

    public static String convertHttpResponseToJsonString(HttpResponse response){
        String responseString = "";
        try {
            responseString = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

}
