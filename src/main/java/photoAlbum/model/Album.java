package photoAlbum.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by michaelcoats on 4/1/17.
 */
public class Album {
    private long id;
    private String title;
    private Map<Long, Photo> photos = new HashMap<>();

    public Album(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<Long, Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Map<Long, Photo> photos) {
        this.photos = photos;
    }

    public void addPhoto(Photo photo) {
        this.photos.put(photo.getId(), photo);
    }

    public void removePhoto(Long photoId) {
        this.photos.remove(photoId);
    }

    public int getPhotoCount() {
        return this.photos.size();
    }
}
