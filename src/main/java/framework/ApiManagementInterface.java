package framework;

import org.apache.http.HttpResponse;

public interface ApiManagementInterface {

    static String getResponseFromUriAsJsonString(String uriToConnectThrough) {
        return "It seems this method haven't been implemented. Please configure the class properly.";
    }

    static String getSpecificValueFromJsonResponse(String uriToConnectThrough){
        return "It seems this method haven't been implemented. Please configure the class properly.";
    }

    static HttpResponse getResponseFromUriAsHttpEntity(String uriToConnectThrough){
        System.out.println("It seems this method haven't been implemented. Please configure the class properly.");
        return null;
    }
}
