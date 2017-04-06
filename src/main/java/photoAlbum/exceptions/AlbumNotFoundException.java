package photoAlbum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by michaelcoats on 4/1/17.
 */

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason= "Album not found")
public class AlbumNotFoundException extends Exception {

    public AlbumNotFoundException(Long albumId, Long userId) {

        super("Album " + albumId + "not found for User " + userId);
    }
}
