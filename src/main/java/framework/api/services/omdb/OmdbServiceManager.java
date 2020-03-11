package framework.api.services.omdb;

import framework.adapters.HTTPadapter;
import framework.api.ApiManagementInterface;
import framework.utils.PropertyUtils;
import org.apache.http.HttpResponse;

import static framework.adapters.HTTPadapter.*;

public class OmdbServiceManager implements ApiManagementInterface {
    static final String host = PropertyUtils.getPropString("omdb.api.host","env.produktion.api.properties");
    static final String apikey = "&apikey="+PropertyUtils.getPropString("omdb.api.key","api.secrets.properties");
    static final String XMLAREA = "movie";

    public OmdbServiceManager() {
    }

    @Override
    public String getSpecificValueFromJsonResponse(String uri, String jsonKey){
        return getSpecificJsonValueFromURL(host,uri,jsonKey,apikey);
    }

    @Override
    public String getSpecificValueFromXmlResponse(String uriToConnectThrough, String xmlKey) {
        return getSpecificXmlValueFromUrl(host,uriToConnectThrough,XMLAREA, xmlKey, apikey);
    }

    @Override
    public HttpResponse getResponseFromUriAsHttpResponse(String uriToConnectThrough) {
        HttpResponse response = HTTPadapter.sendGetCall(host, uriToConnectThrough+apikey, null);
        return response;
    }

    @Override
    public String getResponseFromUriAsJsonString(String uri){
        return getResponseAsJsonString(host,uri,apikey);
    }
}
