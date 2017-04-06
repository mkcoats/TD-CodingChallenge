package photoAlbum.service;

import photoAlbum.model.Album;
import photoAlbum.model.JsonAlbum;
import photoAlbum.model.Photo;
import photoAlbum.model.User;
import photoAlbum.translator.AlbumToJsonAlbumTranslator;
import photoAlbum.translator.JsonAlbumToAlbumTranslator;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by michaelcoats on 4/1/17.
 */
public class UserServiceImpl implements UserService {

    @Override
    public Map<Long, User> generateUsers(List<JsonAlbum> jsonAlbums, List<Photo> photos) {
        Map<Long, User> userMap = new HashMap<>();
        Map<Long, Map<Long, Photo>> photoMap = generateAlbumPhotoMap(photos);
        for(JsonAlbum jsonAlbum : jsonAlbums) {
            Album album = JsonAlbumToAlbumTranslator.albumFrom(jsonAlbum);
            if (photoMap.containsKey(album.getId())) {
                album.setPhotos(photoMap.get(album.getId()));
            }
            if (!userMap.containsKey(jsonAlbum.getUserId())) {

                userMap.put(jsonAlbum.getUserId(), new User(jsonAlbum.getUserId(), album));
            } else {
                User user = userMap.get(jsonAlbum.getUserId());
                user.getAlbums().put(album.getId(), album);
            }
        }

        return userMap;
    }

    Map<Long, Map<Long, Photo>> generateAlbumPhotoMap(List<Photo> photos) {
        Map<Long, Map<Long, Photo>> photoMap = new HashMap<>();
        for (Photo photo : photos) {
            if (!photoMap.containsKey(photo.getAlbumId())) {
                Map<Long, Photo> albumPhotoMap = new HashMap<>();
                albumPhotoMap.put(photo.getId(), photo);
                photoMap.put(photo.getAlbumId(), albumPhotoMap);
            } else {
                photoMap.get(photo.getAlbumId()).put(photo.getId(), photo);
            }
        }
        return photoMap;
    }
}
