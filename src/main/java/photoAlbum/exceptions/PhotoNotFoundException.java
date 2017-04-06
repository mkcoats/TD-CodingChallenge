package photoAlbum.exceptions;

/**
 * Created by michaelcoats on 4/1/17.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason= "Photo not found")
public class PhotoNotFoundException extends Exception {

    public PhotoNotFoundException(Long photoId, Long albumId) {
        super("Photo " + photoId + "not found in Album " + albumId);
    }
}
