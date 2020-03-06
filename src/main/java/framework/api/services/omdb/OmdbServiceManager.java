package framework.api.services.omdb;

import framework.ApiManagementInterface;
import framework.api.services.UriRequest;
import framework.utils.PropertyUtils;
import static framework.adapters.HTTPadapter.getResponseAsJsonString;
import static framework.adapters.HTTPadapter.getSpecificJsonValueFromURL;

public class OmdbServiceManager extends UriRequest implements ApiManagementInterface {
    static final String host = PropertyUtils.getPropString("omdb.api.host","env.produktion.api.properties");
    static final String apikey = "&apikey="+PropertyUtils.getPropString("omdb.api.key","api.secrets.properties");

    public static String getSpecificValueFromJsonResponse(String uri, String jsonKey){
        return getSpecificJsonValueFromURL(host,uri,jsonKey,apikey);
    }

    public static String getResponseFromUriAsJsonString(String uri){
        return getResponseAsJsonString(host,uri,apikey);
    }


    /*
    Everything below here is used to create the URI used to connect to the API.
    If the API changes, please adjust the variables below in order to facilitate the change.
     */

    public OmdbServiceManager(omdbRequestBuilder builder) {
        this.uriMap = builder.getUriMap();
    }

    public static class omdbRequestBuilder extends UriRequest{

        public omdbRequestBuilder(String searchType, String searchTerm){
            addToMap(searchType, searchTerm);
        }

        public omdbRequestBuilder usingFormat(String entertainmentFormat){
            addToMap("type",entertainmentFormat);
            return this;
        }

        public omdbRequestBuilder usingYear(String year){
            addToMap("y", year);
            return this;
        }

        public omdbRequestBuilder withPlotType(String plotType){
            addToMap("plot", plotType);
            return this;
        }

        public omdbRequestBuilder withPageNumber(Integer page){
            addToMap("page", page.toString());
            return this;
        }

        public omdbRequestBuilder usingDatatype(String datatype){
            addToMap("r", datatype);
            return this;
        }

        public OmdbServiceManager build(){
            return new OmdbServiceManager(this);
        }
    }
}
