package framework.api.services;

import org.apache.http.HttpResponse;

public interface ApiManagementInterface {

    String getResponseFromUriAsJsonString(String uriToConnectThrough);

    String getSpecificValueFromJsonResponse(String uriToConnectThrough, String jsonKey);

    HttpResponse getResponseFromUriAsHttpResponse(String uriToConnectThrough);
}
