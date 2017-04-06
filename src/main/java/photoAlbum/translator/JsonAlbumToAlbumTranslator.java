package photoAlbum.translator;

import photoAlbum.model.Album;
import photoAlbum.model.JsonAlbum;

/**
 * Created by michaelcoats on 4/6/17.
 */
public class JsonAlbumToAlbumTranslator {

    public static Album albumFrom(JsonAlbum jsonAlbum) {
        Album album = new Album(jsonAlbum.getId(), jsonAlbum.getTitle());
        return album;
    }
}
