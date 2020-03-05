package framework.api.services.omdb;

import framework.adapters.HTTPadapter;
import framework.api.services.UriRequest;
import framework.utils.JsonUtils;
import framework.utils.PropertyUtils;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static framework.utils.JsonUtils.getSpecificValueFromJSON;

public class OmdbServiceManager extends UriRequest{
    static final String host = PropertyUtils.getPropString("omdb.api.host","env.produktion.api.properties");
    static final String apikey = "&apikey="+PropertyUtils.getPropString("omdb.api.key","api.secrets.properties");

    public static String getTitleUsingJson(String uri){
        String responseString = "";
        HttpEntity responseEntity = HTTPadapter.sendGetCall(host, uri+apikey, null).getEntity();
        try {
            responseString = EntityUtils.toString(responseEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonUtils.prettyPrintJSON(responseString);
        return getSpecificValueFromJSON(responseString,"Title");
    }

    public static String getResponseAsString(String uri){
        String responseString = "";
        HttpEntity responseEntity = HTTPadapter.sendGetCall(host, uri+apikey, null).getEntity();
        try {
            responseString = EntityUtils.toString(responseEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }



    /*
    Everything below here is used to create the URI used to connect to the API.
    If the API changes, please adjust the variables below in order to facilitate the change.
     */

    public OmdbServiceManager(omdbRequestBuilder builder) {
        this.uriMap = builder.uriMap;
    }

    public static class omdbRequestBuilder {
        private Map<String, String> uriMap= new HashMap<String,String>();

        public omdbRequestBuilder(String searchType, String searchTerm){
            uriMap.put(searchType,searchTerm);
        }

        public omdbRequestBuilder usingFormat(String entertainmentFormat){
            uriMap.put("type",entertainmentFormat);
            return this;
        }

        public omdbRequestBuilder usingYear(String year){
            uriMap.put("y", year);
            return this;
        }

        public omdbRequestBuilder withPlotType(String plotType){
            uriMap.put("plot", plotType);
            return this;
        }

        public omdbRequestBuilder withPageNumber(Integer page){
            uriMap.put("page", page.toString());
            return this;
        }

        public omdbRequestBuilder usingDatatype(String datatype){
            uriMap.put("r", datatype);
            return this;
        }

        public OmdbServiceManager build(){
            return new OmdbServiceManager(this);
        }
    }
}
