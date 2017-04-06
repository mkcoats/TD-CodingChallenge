package photoAlbum.translator;

import photoAlbum.model.Album;
import photoAlbum.model.JsonAlbum;
import photoAlbum.model.Photo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by michaelcoats on 4/1/17.
 */
public class AlbumToJsonAlbumTranslator {

    public static List<JsonAlbum> translate(long userId, Map<Long, Album> albums) {
        List<JsonAlbum> jsonAlbums = new ArrayList<>();
        for(Album album : albums.values()) {
            JsonAlbum jsonAlbum = new JsonAlbum(userId, album.getId(), album.getTitle());
            jsonAlbum.setPhotoList(new ArrayList<Photo>(album.getPhotos().values()));
            jsonAlbums.add(jsonAlbum);
        }
        return jsonAlbums;
    }
}
