package framework.api.services.omdb;

import framework.adapters.HTTPadapter;
import framework.utils.JsonUtils;
import framework.utils.PropertyUtils;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static framework.utils.JsonUtils.getSpecificValueFromJSON;

public class OmdbServiceManager {
    static final String host = PropertyUtils.getPropString("omdb.api.host","env.produktion.api.properties");
    static final String titleUri = PropertyUtils.getPropString("omdb.api.uri.title","env.produktion.api.properties");
    static final String idUri = PropertyUtils.getPropString("omdb.api.uri.id","env.produktion.api.properties");
    static final String apikey = PropertyUtils.getPropString("omdb.api.key","api.secrets.properties");

    public static String getTitleFromOmdb(String title){
        String responseString = "";
        HttpEntity responseEntity = HTTPadapter.sendGetCall(host, titleUri+title+"&apikey="+apikey, null).getEntity();

        try {
            responseString = EntityUtils.toString(responseEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonUtils.prettyPrintJSON(responseString);
        return getSpecificValueFromJSON(responseString,"Title");
    }
}
