package framework.adapters;

import framework.utils.JsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Map;

import static framework.utils.JsonUtils.getSpecificValueFromJSON;

public class HTTPadapter {

    public static HttpResponse sendGetCall(String host, String URI, Map<String, String> headers) {

        HttpUriRequest request = new HttpGet(host + URI);
        System.out.println(request);

        if (headers!=null){
            headers.forEach((headerKey, headerValue) -> {
                request.addHeader(headerKey, headerValue);
            });
        }

        HttpResponse httpResponse;

        try {
            httpResponse = HttpClientBuilder.create()
                    .build()
                    .execute(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return httpResponse;

    }

    public static HttpResponse sendPostCall(String host, String URI, String stringBody, Map<String, String> headers) {
        PoolingHttpClientConnectionManager poolingConnManager
                = new PoolingHttpClientConnectionManager();
        poolingConnManager.setDefaultMaxPerRoute(10);
        CloseableHttpClient client
                = HttpClients.custom().setConnectionManager(poolingConnManager)
                .build();

        HttpPost request = new HttpPost(host + URI);

        JsonUtils.prettyPrintJSON(stringBody);

        if (headers!=null){
            headers.forEach((headerKey, headerValue) -> {
                request.addHeader(headerKey, headerValue);
            });
        }

        HttpResponse httpResponse;
        try {
            StringEntity se = new StringEntity(stringBody);
            request.setEntity(se);

            httpResponse = client.execute(request);

        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        return httpResponse;

    }

    public static HttpResponse sendPutCall(String host, String URI, String stringBody, Map<String, String> headers) {
        HttpPut request = new HttpPut(host + URI);

        if (headers!=null) {
            headers.forEach((headerKey, headerValue) -> {
                request.addHeader(headerKey, headerValue);
            });
        }
        else{
            request.addHeader("content-type", "application/json");
        }
        HttpResponse httpResponse;
        try {

            StringEntity se = new StringEntity(stringBody);
            request.setEntity(se);

            httpResponse = HttpClientBuilder.create()
                    .build()
                    .execute(request);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        return httpResponse;

    }

    public static HttpResponse sendDeleteCall(String host, String URI, Map<String, String> headers) {

        HttpUriRequest request = new HttpDelete(host + URI);
        HttpResponse httpResponse;

        if (headers!=null){
            headers.forEach((headerNamn, headerVarde) -> {
                request.addHeader(headerNamn, headerVarde);
            });
        }

        try {
            httpResponse = HttpClientBuilder.create()
                    .build()
                    .execute(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return httpResponse;
    }

    public static boolean validateResponseCode(int expectedResponseCode, HttpResponse httpResponse){
        int responseStatusCode = httpResponse.getStatusLine().getStatusCode();
        if(expectedResponseCode != responseStatusCode)
            return false;
        else
            return true;
    }

    public static String getSpecificJsonValueFromURL(String host, String uri, String jsonKey, @Nullable String apiKey){
        String responseString = getResponseAsJsonString(host,uri,apiKey);
        return getSpecificValueFromJSON(responseString,jsonKey);
    }

    public static String getResponseAsJsonString(String host, String uri, @Nullable String apiKey){
        String responseString = "";

        if(apiKey==null) {
            apiKey = "";
        }

        HttpEntity responseEntity = HTTPadapter.sendGetCall(host, uri+apiKey, null).getEntity();
        try {
            responseString = EntityUtils.toString(responseEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonUtils.prettyPrintJSON(responseString);
        return responseString;
    }
}
