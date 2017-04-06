package photoAlbum.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by michaelcoats on 4/1/17.
 */
public class User {
    private long id;
    private Map<Long, Album> albums;

    public User(long id) {
        this.id = id;
        this.albums = null;
    }

    public User(long id, Album album) {
        this.id = id;
        Map<Long, Album> albumMap = new HashMap<>();
        albumMap.put(album.getId(), album);
        this.albums = albumMap;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<Long, Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Map<Long, Album> albums) {
        this.albums = albums;
    }
}
