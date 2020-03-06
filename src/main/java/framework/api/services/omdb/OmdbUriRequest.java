package framework.api.services.omdb;

import framework.api.UriRequest;

public class OmdbUriRequest extends UriRequest{
    public OmdbUriRequest(omdbRequestBuilder builder) {
        this.uriMap = builder.getUriMap();
    }

    public static class omdbRequestBuilder extends UriRequest {

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

        public OmdbUriRequest build(){
            return new OmdbUriRequest(this);
        }
    }
}
