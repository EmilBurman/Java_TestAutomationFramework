package framework.adapters;

import framework.utils.JsonUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class HTTPadapter {

    public static HttpResponse sendGetCall(String host, String URI, Map<String, String> headers) {

        HttpUriRequest request = new HttpGet(host + URI);

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

        HttpPost postAnrop = new HttpPost(host + URI);

        JsonUtils.prettyPrintJSON(stringBody);

        if (headers!=null){
            headers.forEach((headerKey, headerValue) -> {
                postAnrop.addHeader(headerKey, headerValue);
            });
        }

        HttpResponse httpResponse;
        try {
            StringEntity se = new StringEntity(stringBody);
            postAnrop.setEntity(se);

            httpResponse = client.execute(postAnrop);

        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        return httpResponse;

    }

    public static HttpResponse sendPutCall(String host, String URI, String stringBody, Map<String, String> headers) {
        HttpPut putAnrop = new HttpPut(host + URI);

        if (headers!=null) {
            headers.forEach((headerKey, headerValue) -> {
                putAnrop.addHeader(headerKey, headerValue);
            });
        }
        else{
            putAnrop.addHeader("content-type", "application/json");
        }
        HttpResponse httpResponse;
        try {

            StringEntity se = new StringEntity(stringBody);
            putAnrop.setEntity(se);

            httpResponse = HttpClientBuilder.create()
                    .build()
                    .execute(putAnrop);
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
}
