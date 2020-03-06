package framework.api.services.cat;

import framework.api.UriRequest;

import java.util.stream.Collectors;

public class CatUriRequest extends UriRequest{
    private boolean searchUsers = false;
    private boolean useRandom = true;
    private String id;

    /*
    Everything below here is used to create the URI used to connect to the API.
    If the API changes, please adjust the variables below in order to facilitate the change.
    */

    @Override
    public String toString(){
        String endpoint = CreateEndpointFromVariables();
        String uri = uriMap.entrySet().stream().map(Object::toString).collect(Collectors.joining("&"));
        System.out.println(uri);
        return (endpoint+uri);
    }

    private String CreateEndpointFromVariables() {
        String endpoint ="/facts";
        if(searchUsers)
            endpoint ="/users";

        if(!(id ==null))
            return endpoint+"/"+id;
        else {
            if (!searchUsers && useRandom)
                endpoint += "/random";

            if (uriMap.isEmpty())
                endpoint += "/";
            else
                endpoint += "?";

            return endpoint;
        }
    }

    public CatUriRequest(CatRequestBuilder builder){
        this.id = builder.id;
        this.searchUsers = builder.searchUsers;
        this.useRandom = builder.useRandom;
        this.uriMap = builder.getUriMap();
    }

    public static class CatRequestBuilder extends UriRequest {
        private String id;
        private boolean searchUsers = false;
        private boolean useRandom = false;

        public CatRequestBuilder(){
        }

        public CatRequestBuilder usingAnimalType(String animalType){
            addToMap("animal_type",animalType);
            return this;
        }

        public CatRequestBuilder withAmount(Integer amount){
            addToMap("amount",amount.toString());
            return this;
        }

        public CatRequestBuilder usingID(String id){
            this.id = id;
            return this;
        }

        public CatRequestBuilder getRandom(){
            this.useRandom = true;
            return this;
        }

        public CatRequestBuilder searchByUser(Boolean useUser){
            this.searchUsers = useUser;
            this.useRandom = false;
            return this;
        }
        public CatUriRequest build(){
            return new CatUriRequest(this);
        }
    }
}
