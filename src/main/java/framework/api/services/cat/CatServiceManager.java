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

    public static boolean validateResponseCode(int expectedResponseCode){
        HttpResponse response = HTTPadapter.sendGetCall(host, factBaseURI+"/random", null);
        return HTTPadapter.validateResponseCode(expectedResponseCode,response);
    }
}
