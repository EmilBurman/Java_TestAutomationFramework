package framework.api;

import org.apache.http.HttpResponse;

public interface ApiManagementInterface {

    String getResponseFromUriAsJsonString(String uriToConnectThrough);

    String getSpecificValueFromJsonResponse(String uriToConnectThrough, String jsonKey);

    String getSpecificValueFromXmlResponse(String uriToConnectThrough, String xmlKey);

    HttpResponse getResponseFromUriAsHttpResponse(String uriToConnectThrough);

}
