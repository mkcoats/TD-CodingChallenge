package photoAlbum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import photoAlbum.exceptions.*;
import photoAlbum.model.Album;
import photoAlbum.model.JsonAlbum;
import photoAlbum.model.Photo;
import photoAlbum.service.PhotoAlbumService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by michaelcoats on 4/1/17.
 */

@RestController
public class PhotoAlbumManagerController {

    @Autowired
    PhotoAlbumService photoAlbumService;

    @RequestMapping("/init")
    public ResponseEntity<?> initializeAllData() {
        photoAlbumService.initializeAllData();
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @RequestMapping("/retrieveUserAlbums")
    public List<JsonAlbum> retrieveUserAlbums(@RequestParam(value = "user") long userId) throws UserNotFoundException {
        return photoAlbumService.allAlbumsForUser(userId);
    }

    @RequestMapping("/retrieveAlbumPhotos")
    public List<Photo> retrieveAlbumPhotos(@RequestParam(value = "user") long userId, @RequestParam(value = "album") long albumId) throws UserNotFoundException, AlbumNotFoundException {
        return photoAlbumService.allPhotosForAlbum(userId, albumId);
    }

    @RequestMapping("/album/create")
    public ResponseEntity createAlbum(@RequestParam(value = "user") long userId, @RequestParam(value = "album") long albumId, @RequestParam(value = "title", required = false) String title) {
        try {
            photoAlbumService.newAlbumForUser(albumId, userId, title);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (AlbumAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping("/album/retrieve")
    public Album getAlbum(@RequestParam(value = "user") long userId, @RequestParam(value = "album") long albumId) throws UserNotFoundException, AlbumNotFoundException {
        return photoAlbumService.getAlbumForUser(albumId, userId);
    }

    @RequestMapping("/album/update")
    public ResponseEntity updateAlbum(@RequestParam(value = "user") long userId, @RequestParam(value = "album") long albumId, @RequestParam(value = "title", required = false) String title) throws UserNotFoundException, AlbumNotFoundException {
        photoAlbumService.updateAlbumForUser(albumId, userId, title);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/album/delete")
    public ResponseEntity removeAlbum(@RequestParam(value = "user") long userId, @RequestParam(value = "album") long albumId) throws UserNotFoundException, AlbumNotEmptyException, AlbumNotFoundException {
        photoAlbumService.deleteAlbumForUser(albumId, userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/photo/create")
    public ResponseEntity createPhoto(@RequestParam(value = "user") long userId, @RequestParam(value = "album") long albumId, @RequestParam(value = "id") long photoId, @RequestParam(value = "title", required = false) String title, @RequestParam(value = "url", required = false) String url, @RequestParam(value = "thumbnailUrl", required = false) String thumbnailUrl) throws UserNotFoundException, AlbumNotFoundException {
        Photo photo = new Photo(albumId, photoId, title, url, thumbnailUrl);
        photoAlbumService.newPhotoForAlbum(photo, albumId, userId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping("/photo/retrieve")
    public Photo getPhoto(@RequestParam(value = "user") long userId, @RequestParam(value = "album") long albumId, @RequestParam(value = "id") long photoId) throws UserNotFoundException, AlbumNotFoundException, PhotoNotFoundException {
        return photoAlbumService.getPhotoForAlbum(photoId, albumId, userId);
    }

    @RequestMapping("/photo/update")
    public ResponseEntity updatePhoto(@RequestParam(value = "user") long userId, @RequestParam(value = "album") long albumId, @RequestParam(value = "id") long photoId, @RequestParam(value = "title") String title, @RequestParam(value = "url") String url, @RequestParam(value = "thumbnailUrl") String thumbnailUrl) throws UserNotFoundException, AlbumNotFoundException, PhotoNotFoundException {
        Photo photo = new Photo(albumId, photoId, title, url, thumbnailUrl);
        photoAlbumService.updatePhotoForAlbum(albumId, userId, photo);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/photo/delete")
    public ResponseEntity removePhoto(@RequestParam(value = "user") long userId, @RequestParam(value = "album") long albumId, @RequestParam(value = "id") long photoId) throws UserNotFoundException, AlbumNotFoundException, PhotoNotFoundException {
        photoAlbumService.deletePhotoFromAlbum(photoId, albumId, userId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
