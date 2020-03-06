package framework.api.services.omdb;

import framework.api.services.ApiManagementInterface;
import framework.utils.PropertyUtils;
import org.apache.http.HttpResponse;

import static framework.adapters.HTTPadapter.getResponseAsJsonString;
import static framework.adapters.HTTPadapter.getSpecificJsonValueFromURL;

public class OmdbServiceManager implements ApiManagementInterface {
    static final String host = PropertyUtils.getPropString("omdb.api.host","env.produktion.api.properties");
    static final String apikey = "&apikey="+PropertyUtils.getPropString("omdb.api.key","api.secrets.properties");

    public OmdbServiceManager() {
    }

    @Override
    public String getSpecificValueFromJsonResponse(String uri, String jsonKey){
        return getSpecificJsonValueFromURL(host,uri,jsonKey,apikey);
    }

    @Override
    public HttpResponse getResponseFromUriAsHttpResponse(String uriToConnectThrough) {
        return null;
    }

    @Override
    public String getResponseFromUriAsJsonString(String uri){
        return getResponseAsJsonString(host,uri,apikey);
    }
}
