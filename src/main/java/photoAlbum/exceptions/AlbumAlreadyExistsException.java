package photoAlbum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by michaelcoats on 4/1/17.
 */

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Album already exists")
public class AlbumAlreadyExistsException extends Exception {

    public AlbumAlreadyExistsException(Long albumId, Long userId, String title) {
        super("Album " + albumId + " \"" + title + "\" already exists for user " + userId);
    }
}
