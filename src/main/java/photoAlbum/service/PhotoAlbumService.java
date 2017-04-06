package photoAlbum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import photoAlbum.exceptions.*;
import photoAlbum.model.Album;
import photoAlbum.model.JsonAlbum;
import photoAlbum.model.Photo;
import photoAlbum.model.User;
import photoAlbum.translator.AlbumToJsonAlbumTranslator;
import photoAlbum.translator.JsonAlbumToAlbumTranslator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by michaelcoats on 4/1/17.
 */

@Component
public class PhotoAlbumService {

    @Autowired
    DataService dataService;

    private Map<Long, User> users = new HashMap<Long, User>();

    public void initializeAllData() {
        users = dataService.retrieveAllData();
    }

    public List<JsonAlbum> allAlbumsForUser(Long userId) throws UserNotFoundException {
        User user = validateUser(userId);
        Map<Long, Album> albums = user.getAlbums();
        return AlbumToJsonAlbumTranslator.translate(user.getId(), albums);
    }

    public List<Photo> allPhotosForAlbum(Long userId, Long albumId) throws UserNotFoundException, AlbumNotFoundException {
        User user = validateUser(userId);
        if (!user.getAlbums().containsKey(albumId)) {
            throw new AlbumNotFoundException(albumId, userId);
        }
        Album album = user.getAlbums().get(albumId);
        return new ArrayList<Photo>(album.getPhotos().values());
    }

    public void newAlbumForUser(Long albumId, Long userId, String title) throws UserNotFoundException, AlbumAlreadyExistsException {
        User user = validateUser(userId);
        if (user.getAlbums().containsKey(albumId)) {
            throw new AlbumAlreadyExistsException(albumId, userId, title);
        }
        user.getAlbums().put(albumId, new Album(albumId, title));
    }

    public Album getAlbumForUser(long albumId, long userId) throws UserNotFoundException, AlbumNotFoundException {
        User user = validateUser(userId);
        if (!user.getAlbums().containsKey(albumId)) {
            throw new AlbumNotFoundException(albumId, userId);
        }

        return user.getAlbums().get(albumId);
    }

    public void updateAlbumForUser(long albumId, long userId, String title) throws UserNotFoundException, AlbumNotFoundException {
        User user = validateUser(userId);
        if (!user.getAlbums().containsKey(albumId)) {
            throw new AlbumNotFoundException(albumId, userId);
        }
        user.getAlbums().get(albumId).setTitle(title);
    }

    public void deleteAlbumForUser(long albumId, long userId) throws UserNotFoundException, AlbumNotFoundException, AlbumNotEmptyException {
        User user = validateUser(userId);
        if (!user.getAlbums().containsKey(albumId)) {
            throw new AlbumNotFoundException(albumId, userId);
        }
        Album album = user.getAlbums().get(albumId);
        if (album.getPhotoCount() > 0) {
            throw new AlbumNotEmptyException(albumId);
        }
        user.getAlbums().remove(albumId);
    }

    public void newPhotoForAlbum(Photo photo, long albumId, long userId) throws UserNotFoundException, AlbumNotFoundException {
        Album album = getAlbumForUser(albumId, userId);
        album.addPhoto(photo);
    }

    public Photo getPhotoForAlbum(long photoId, long albumId, long userId) throws UserNotFoundException, AlbumNotFoundException, PhotoNotFoundException {
        Album album = getAlbumForUser(albumId, userId);
        if (!album.getPhotos().containsKey(photoId)) {
            throw new PhotoNotFoundException(photoId, albumId);
        }
        return album.getPhotos().get(photoId);
    }

    public void updatePhotoForAlbum(long albumId, long userId, Photo newPhoto) throws UserNotFoundException, PhotoNotFoundException, AlbumNotFoundException {
        Album album = getAlbumForUser(albumId, userId);
        Long photoId = newPhoto.getId();
        if (!album.getPhotos().containsKey(photoId)) {
            throw new PhotoNotFoundException(photoId, albumId);
        }
        album.getPhotos().put(photoId, newPhoto);
    }

    public void deletePhotoFromAlbum(long photoId, long albumId, long userId) throws UserNotFoundException, AlbumNotFoundException, PhotoNotFoundException {
        Album album = getAlbumForUser(albumId, userId);
        if (!album.getPhotos().containsKey(photoId)) {
            throw new PhotoNotFoundException(photoId, albumId);
        }
        album.getPhotos().remove(photoId);
    }

    public User validateUser(Long userId) throws UserNotFoundException {
        if (!users.containsKey(userId)) {
            throw new UserNotFoundException("User Not Found: " + userId);
        }
        return users.get(userId);
    }
}
