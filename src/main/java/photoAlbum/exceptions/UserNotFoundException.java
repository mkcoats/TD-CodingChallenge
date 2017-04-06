package photoAlbum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by michaelcoats on 4/1/17.
 */

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason= "User not found")
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }
}
