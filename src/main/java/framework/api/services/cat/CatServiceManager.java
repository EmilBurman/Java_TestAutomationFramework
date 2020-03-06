package framework.api.services.cat;

import framework.api.ApiManagementInterface;
import framework.adapters.HTTPadapter;
import framework.utils.PropertyUtils;
import org.apache.http.HttpResponse;

import static framework.adapters.HTTPadapter.getResponseAsJsonString;

public class CatServiceManager implements ApiManagementInterface {

    static final String host = PropertyUtils.getPropString("cat.api.host","env.produktion.api.properties");

    public CatServiceManager(){
    }

    @Override
    public String getResponseFromUriAsJsonString(String uri){
        String responseString = getResponseAsJsonString(host,uri,null);
        return responseString;
    }

    @Override
    public String getSpecificValueFromJsonResponse(String uriToConnectThrough, String jsonKey) {
        return null;
    }

    @Override
    public HttpResponse getResponseFromUriAsHttpResponse(String uri){
        HttpResponse response = HTTPadapter.sendGetCall(host, uri, null);
        return response;
    }
}
