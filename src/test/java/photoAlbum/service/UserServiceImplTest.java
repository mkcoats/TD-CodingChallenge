package photoAlbum.service;

import org.junit.Before;
import org.junit.Test;
import photoAlbum.model.Album;
import photoAlbum.model.JsonAlbum;
import photoAlbum.model.Photo;
import photoAlbum.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by michaelcoats on 4/1/17.
 */
public class UserServiceImplTest {

    UserService userService;

    @Before
    public void setup() {
        userService = new UserServiceImpl();
    }

    @Test
    public void testGenerateUsersEmptyLists() throws Exception {
        Map<Long, User> actual = userService.generateUsers(new ArrayList<JsonAlbum>(), new ArrayList<Photo>());
        assertEquals(0, actual.size());
    }

    @Test
    public void testGenerateUsersNoPhotos() throws Exception {
        ArrayList<JsonAlbum> jsonAlbumList = new ArrayList<JsonAlbum>();
        jsonAlbumList.add(new JsonAlbum(1L, 1L, "asdf"));
        Map<Long, User> actual = userService.generateUsers(jsonAlbumList, new ArrayList<Photo>());
        assertEquals(1, actual.size());
        assertEquals(0, actual.get(1L).getAlbums().get(1L).getPhotoCount());
    }
    @Test
    public void testGenerateUsers() throws Exception {
        List<JsonAlbum> jsonAlbumList = new ArrayList<JsonAlbum>();
        jsonAlbumList.add(new JsonAlbum(1L, 1L, "asdf"));
        jsonAlbumList.add(new JsonAlbum(1L, 2L, "asdf"));
        jsonAlbumList.add(new JsonAlbum(2L, 3L, "asdf"));
        jsonAlbumList.add(new JsonAlbum(2L, 4L, "asdf"));
        jsonAlbumList.add(new JsonAlbum(2L, 5L, "asdf"));
        List<Photo> photos = new ArrayList<Photo>();
        photos.add(new Photo(1L, 1L, "asdf", "asdf", "asdf"));
        photos.add(new Photo(2L, 2L, "asdf", "asdf", "asdf"));
        photos.add(new Photo(2L, 3L, "asdf", "asdf", "asdf"));
        photos.add(new Photo(3L, 4L, "asdf", "asdf", "asdf"));
        photos.add(new Photo(3L, 5L, "asdf", "asdf", "asdf"));
        photos.add(new Photo(3L, 6L, "asdf", "asdf", "asdf"));
        photos.add(new Photo(4L, 7L, "asdf", "asdf", "asdf"));
        photos.add(new Photo(4L, 8L, "asdf", "asdf", "asdf"));
        photos.add(new Photo(4L, 9L, "asdf", "asdf", "asdf"));
        photos.add(new Photo(4L, 10L, "asdf", "asdf", "asdf"));
        Map<Long, User> actual = userService.generateUsers(jsonAlbumList, photos);
        assertEquals(2, actual.size());

        User user1 = actual.get(1L);
        assertEquals(2, user1.getAlbums().size());
        User user2 = actual.get(2L);
        assertEquals(3, user2.getAlbums().size());
        Album album1 = user1.getAlbums().get(1L);
        assertEquals(1, album1.getPhotoCount());
        Album album2 = user1.getAlbums().get(2L);
        assertEquals(2, album2.getPhotoCount());
        Album album3 = user2.getAlbums().get(3L);
        assertEquals(3, album3.getPhotoCount());
        Album album4 = user2.getAlbums().get(4L);
        assertEquals(4, album4.getPhotoCount());
        Album album5 = user2.getAlbums().get(5L);
        assertEquals(0, album5.getPhotoCount());
    }

}