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
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class HTTPadapter {

    private static final Logger LOG = Logger.getLogger("test");

    public static HttpResponse skickaGETAnrop(String host, String URI, Map<String, String> headers) {

        LOG.info("Skickar GET anrop till " + host+URI);
        HttpUriRequest request = new HttpGet(host + URI);

        if (headers!=null){
            headers.forEach((headerNamn, headerVarde) -> {
                request.addHeader(headerNamn, headerVarde);
                LOG.info("Header: " + headerNamn + ": " + headerVarde);
            });
        }

        HttpResponse httpResponse;

        try {
            httpResponse = HttpClientBuilder.create()
                    .build()
                    .execute(request);
            LOG.info ("Fick responskod " + httpResponse.getStatusLine().getStatusCode() + " " + httpResponse.getStatusLine().getReasonPhrase());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return httpResponse;

    }

    public static HttpResponse skickaPOSTAnrop(String host, String URI, String stringBody, Map<String, String> headers) {
        PoolingHttpClientConnectionManager poolingConnManager
                = new PoolingHttpClientConnectionManager();
        poolingConnManager.setDefaultMaxPerRoute(10);
        CloseableHttpClient client
                = HttpClients.custom().setConnectionManager(poolingConnManager)
                .build();

        HttpPost postAnrop = new HttpPost(host + URI);

        LOG.info("Skickar POST anrop med JSON till " + host+URI);
        JsonUtils.prettyPrintJSON(stringBody);

        if (headers!=null){
            headers.forEach((headerNamn, headerVarde) -> {
                postAnrop.addHeader(headerNamn, headerVarde);
                LOG.fine("Header: " + headerNamn + ": " + headerVarde);
            });
        }



        HttpResponse httpResponse;
        try {
            StringEntity se = new StringEntity(stringBody);
            postAnrop.setEntity(se);

            httpResponse = client.execute(postAnrop);

            LOG.info ("Fick responskod " + httpResponse.getStatusLine().getStatusCode() + " " + httpResponse.getStatusLine().getReasonPhrase());
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        return httpResponse;

    }

    public static HttpResponse skickaPOSTAnropSetStatusKodStubbe(String host, String URI, Map<String, String> headers) {
        PoolingHttpClientConnectionManager poolingConnManager
                = new PoolingHttpClientConnectionManager();
        poolingConnManager.setDefaultMaxPerRoute(10);
        CloseableHttpClient client
                = HttpClients.custom().setConnectionManager(poolingConnManager)
                .build();

        HttpPost postAnrop = new HttpPost(host + URI);

        LOG.info("Skickar POST anrop till " + host+URI);

        if (headers!=null){
            headers.forEach((headerNamn, headerVarde) -> {
                postAnrop.addHeader(headerNamn, headerVarde);
                LOG.fine("Header: " + headerNamn + ": " + headerVarde);
            });
        }

        HttpResponse httpResponse;
        try {
            StringEntity se = new StringEntity("");
            postAnrop.setEntity(se);

            httpResponse = client.execute(postAnrop);

            LOG.info ("Fick responskod " + httpResponse.getStatusLine().getStatusCode() + " " + httpResponse.getStatusLine().getReasonPhrase());
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        return httpResponse;

    }

    public static HttpResponse skickaPUTAnrop(String host, String URI, String stringBody, Map<String, String> headers) {
        HttpPut putAnrop = new HttpPut(host + URI);

        LOG.info("Skickar PUT anrop till " + host+URI);
        LOG.info ("Body = " + stringBody);

        if (headers!=null) {
            headers.forEach((headerNamn, headerVarde) -> {
                putAnrop.addHeader(headerNamn, headerVarde);
                LOG.fine("Header: " + headerNamn + ": " + headerVarde);
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
            LOG.info ("Fick responskod " + httpResponse.getStatusLine().getStatusCode() + " " + httpResponse.getStatusLine().getReasonPhrase());
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        return httpResponse;

    }


    public static HttpResponse skickaDELETEAnrop(String host, String URI, Map<String, String> headers) {

        LOG.info("Skickar DELETE anrop till " + host+URI);
        HttpUriRequest request = new HttpDelete(host + URI);
        HttpResponse httpResponse;

        if (headers!=null){
            headers.forEach((headerNamn, headerVarde) -> {
                request.addHeader(headerNamn, headerVarde);
                LOG.info("Header: " + headerNamn + ": " + headerVarde);
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

    // TODO: Jag tro inte nedan metod används någonstans, kan ta bort
    public static JSONObject hamtaHTTPResponseContentSomJSONObject(HttpResponse httpResponse) throws Exception {
        HttpEntity entity = httpResponse.getEntity();
        System.out.println(httpResponse.getStatusLine()
                .getStatusCode());
        String content = EntityUtils.toString(entity);
        JSONObject jsonObject = JsonUtils.convertJSONStringToJSONObject(content);
        return jsonObject;
    }


}
