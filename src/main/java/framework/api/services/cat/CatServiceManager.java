package framework.api.services.cat;

import framework.adapters.HTTPadapter;
import framework.utils.JsonUtils;
import framework.utils.PropertyUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static framework.utils.JsonUtils.getSpecificValueFromJSON;

public class CatServiceManager {

    static final String host = PropertyUtils.getPropString("cat.api.host","env.produktion.api.properties");
    static final String factBaseURI = PropertyUtils.getPropString("cat.api.uri.fact","env.produktion.api.properties");

    public static String getRandomCatFact(){
        String responseString = "";
        HttpEntity responseEntity = HTTPadapter.sendGetCall(host, factBaseURI+"/random", null).getEntity();

        try {
            responseString = EntityUtils.toString(responseEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonUtils.prettyPrintJSON(responseString);
        return getSpecificValueFromJSON(responseString,"text");
    }

    public static String getRandomCatFactObject(){
        String responseString = "";
        HttpEntity responseEntity = HTTPadapter.sendGetCall(host, factBaseURI+"/random", null).getEntity();

        try {
            responseString = EntityUtils.toString(responseEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonUtils.prettyPrintJSON(responseString);
        return responseString;
    }

    public static String getOtherAnimalFact(String animal){
        String responseString = "";
        HttpEntity responseEntity = HTTPadapter.sendGetCall(host, factBaseURI+"/random?animal_type="+animal, null).getEntity();

        try {
            responseString = EntityUtils.toString(responseEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonUtils.prettyPrintJSON(responseString);
        return getSpecificValueFromJSON(responseString,"type");
    }

    public static String getSpecificFactText(String factID){
        String responseString = getSpecificFactObject(factID);
        JsonUtils.prettyPrintJSON(responseString);
        return getSpecificValueFromJSON(responseString,"text");
    }

    public static String getSpecificFactObject(String factID){
        String responseString = "";
        HttpEntity responseEntity = HTTPadapter.sendGetCall(host, factBaseURI+"/"+factID, null).getEntity();

        try {
            responseString = EntityUtils.toString(responseEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonUtils.prettyPrintJSON(responseString);
        return responseString;
    }

    public static boolean validateResponseCode(int expectedResponseCode, String endpoint){
        HttpResponse response = HTTPadapter.sendGetCall(host, factBaseURI+endpoint, null);
        return HTTPadapter.validateResponseCode(expectedResponseCode,response);
    }
    public static boolean validateResponseCodeFromObject(int expectedResponseCode, HttpResponse expectedObject){
        return HTTPadapter.validateResponseCode(expectedResponseCode,expectedObject);
    }
}
