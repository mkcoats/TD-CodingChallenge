package photoAlbum.service;

import photoAlbum.model.JsonAlbum;
import photoAlbum.model.Photo;
import photoAlbum.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by michaelcoats on 4/1/17.
 */
public interface UserService {
    Map<Long, User> generateUsers(List<JsonAlbum> jsonAlbums, List<Photo> photos);
}
