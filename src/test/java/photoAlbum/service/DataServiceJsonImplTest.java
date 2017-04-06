package photoAlbum.service;

import org.junit.Before;
import org.junit.Test;
import photoAlbum.model.User;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by michaelcoats on 4/1/17.
 */
public class DataServiceJsonImplTest {

    DataService dataServiceJson;

    @Before
    public void setup() {
        dataServiceJson = new DataServiceJsonImpl();
    }

    @Test
    public void testRetrieveAllData() {
        Map<Long, User> actual = dataServiceJson.retrieveAllData();
        assertNotNull(actual);
        assertTrue(actual.size()>0);
    }

}