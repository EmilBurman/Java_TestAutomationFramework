package framework.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class UriRequest {
    protected Map<String, String> uriMap= new HashMap<String,String>();

    public UriRequest(){
    }

    public UriRequest addToMap(String key, String value){
        this.uriMap.put(key, convertStringToUriSafeString(value));
        return this;
    }

    public Map<String,String> getUriMap(){
        return this.uriMap;
    }

    @Override
    public String toString(){
        String uri =("/?"+ uriMap.entrySet().stream().map(Object::toString).collect(Collectors.joining("&")));
        System.out.println("Uri used for the request: " + uri);
        return uri;
    }

    public String convertStringToUriSafeString(String initalString){
        String convertedString = "";
        try {
            convertedString =  URLEncoder.encode(initalString,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return convertedString;
    }
}
