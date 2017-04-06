package photoAlbum.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;
import photoAlbum.model.*;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by michaelcoats on 4/1/17.
 */

@Component
public class DataServiceJsonImpl implements DataService {

    UserService userService;

    @Override
    public Map<Long, User> retrieveAllData() {
        StringBuilder albumOutputBuilder = new StringBuilder();
        StringBuilder photoOutputBuilder = new StringBuilder();
        try {
            URL albumURL = new URL("https://jsonplaceholder.typicode.com/albums");
            BufferedReader albumIn = new BufferedReader(new InputStreamReader(albumURL.openStream()));

            String albumInputLine;
            while ((albumInputLine = albumIn.readLine()) != null) {
                albumOutputBuilder.append(albumInputLine);
            }
            URL photoURL = new URL("https://jsonplaceholder.typicode.com/photos");
            BufferedReader photoIn = new BufferedReader(new InputStreamReader(photoURL.openStream()));

            String photoInputLine;
            while ((photoInputLine = photoIn.readLine()) != null) {
                photoOutputBuilder.append(photoInputLine);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Type jsonAlbumListType = new TypeToken<ArrayList<JsonAlbum>>(){}.getType();

        List<JsonAlbum> jsonAlbums = new Gson().fromJson(albumOutputBuilder.toString(), jsonAlbumListType);
        Type photoListType = new TypeToken<ArrayList<Photo>>(){}.getType();

        List<Photo> photos = new Gson().fromJson(photoOutputBuilder.toString(), photoListType);

        userService = new UserServiceImpl();
        Map<Long, User> users = userService.generateUsers(jsonAlbums, photos);
        return users;
    }
}
