package photoAlbum.service;

import org.springframework.stereotype.Component;
import photoAlbum.model.User;

import java.util.Map;

/**
 * Created by michaelcoats on 4/1/17.
 */

@Component
public interface DataService {

    Map<Long, User> retrieveAllData();

}
