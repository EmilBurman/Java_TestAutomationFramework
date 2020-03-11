package framework.api.services;

import framework.api.ApiManagementInterface;
import framework.api.services.cat.CatServiceManager;
import framework.api.services.omdb.OmdbServiceManager;
import framework.api.misc.AvailableApiServices;
import org.apache.http.HttpResponse;

public class ApiServiceManager{

    public static String getResponseFromUriAsJsonString(AvailableApiServices requestedApiHost, String uriToConnectThrough) {
        ApiManagementInterface apiHost =  apiFactory(requestedApiHost);
        return apiHost.getResponseFromUriAsJsonString(uriToConnectThrough);
    }

    public static String getSpecificValueFromJsonResponse(AvailableApiServices requestedApiHost, String uriToConnectThrough, String jsonKey) {
        ApiManagementInterface apiHost =  apiFactory(requestedApiHost);
        return apiHost.getSpecificValueFromJsonResponse(uriToConnectThrough,jsonKey);
    }

    public static String getSpecificValueFromXmlResponse(AvailableApiServices requestedApiHost, String uriToConnectThrough, String xmlKey){
        ApiManagementInterface apiHost =  apiFactory(requestedApiHost);
        return apiHost.getSpecificValueFromXmlResponse(uriToConnectThrough,xmlKey);
    }

    public static HttpResponse getResponseFromUriAsHttpResponse(AvailableApiServices requestedApiHost, String uriToConnectThrough) {
        ApiManagementInterface apiHost =  apiFactory(requestedApiHost);
        return apiHost.getResponseFromUriAsHttpResponse(uriToConnectThrough);
    }

    private static ApiManagementInterface apiFactory(AvailableApiServices requestedApi){
        switch (requestedApi){
            case CAT:
                return new CatServiceManager();
            case OMDB:
                return new OmdbServiceManager();
            default:
                throw new IllegalStateException("Unexpected API value: " + requestedApi);
        }
    }
}
