package photoAlbum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by michaelcoats on 4/1/17.
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Album already exists")
public class AlbumNotEmptyException extends Exception {

    public AlbumNotEmptyException(Long albumId) {
        super("Album " + albumId + " contains photos and can't be deleted");
    }
}
