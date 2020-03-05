package framework.api.services.omdb;

import framework.api.AbstractApiTestcase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static framework.utils.Tags.*;

@Tag(API)
@Tag(API_OMDB)
public class GetTitle_api_test extends AbstractApiTestcase {

    @ParameterizedTest
    @ValueSource(strings = {"Shawshank","Spiderman"})
    public void validateTitleExistsInDatabase(String title){
        String titleFromOmdb = OmdbServiceManager.getTitleFromOmdb(title).toLowerCase();
        System.out.println(titleFromOmdb);
        Assertions.assertTrue(titleFromOmdb.contains(title.toLowerCase()), "Unable to get a fact, please investigate.");
    }

}
