package framework.api.services;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class UriRequest {
    protected Map<String, String> uriMap= new HashMap<String,String>();

    public UriRequest(){
    }

    public UriRequest addToMap(String key, String value){
        this.uriMap.put(key, value);
        return this;
    }

    @Override
    public String toString(){
        String uri = uriMap.entrySet().stream().map(Object::toString).collect(Collectors.joining("&"));
        System.out.println(uri);
        return ("/?"+uri);
    }
}
