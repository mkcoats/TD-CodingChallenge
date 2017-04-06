package photoAlbum.model;

import java.util.List;

/**
 * Created by michaelcoats on 4/1/17.
 */
public class JsonAlbum {

    public long userId;
    public long id;
    public String title;
    public List<Photo> photoList;

    public JsonAlbum(long userId, long id, String title) {
        this.userId = userId;
        this.id = id;
        this.title = title;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }
}
