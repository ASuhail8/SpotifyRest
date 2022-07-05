package com.spotify.oauth.tests;

import com.spotify.oauth.Base.BaseTest;
import com.spotify.oauth.pojo.ErrorRoot;
import com.spotify.oauth.pojo.Playlist;
import com.spotify.oauth.utils.ConfigLoader;
import com.spotify.oauth.utils.StatusCode;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static com.spotify.oauth.api.SpecBuilder.*;
import static com.spotify.oauth.api.TokenManager.getToken;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests extends BaseTest {

String playlistId = null;

    @Test
    public void createNewPlaylist() throws FileNotFoundException {
        String filePath = "./Logs/"+System.getProperty("baseUrl")+"logs.txt";
        getConsoleOutput(filePath);
        Playlist playlistRequest = playlistBuilder("My test2 Playlist","My test2 description", false);
        Playlist playlistResponse = given(getRequestSpec()).
                header("Authorization","Bearer " + getToken()).
                body(playlistRequest).
        when().
                post("/users/"+ ConfigLoader.getInstance().getUserId() +"/playlists").
        then().
                spec(getResponseSpec()).
                assertThat().
                statusCode(StatusCode.CODE_201.code).
                extract().
                response().
                as(Playlist.class);

        assertPlaylistEqual(playlistResponse,playlistRequest);

        playlistId = playlistResponse.getId();

    }

    @Test
    public void getCreatedPlaylist(){
        Playlist playlistRequest = playlistBuilder("My test2 Playlist","My test2 description", false);
        Playlist playlistResponse = given(getRequestSpec()).
                header("Authorization","Bearer " + getToken()).
        when().
                get("/playlists/"+playlistId).
        then().
                spec(getResponseSpec()).
                assertThat().
                statusCode(StatusCode.CODE_200.code).
                extract().
                response().
                as(Playlist.class);

        assertPlaylistEqual(playlistResponse,playlistRequest);

    }

    @Test
    public void updateAPlaylist(){
        Playlist playlistRequest = playlistBuilder("My updated test2 Playlist","My updated test2 description", false);
        given(getRequestSpec()).
                header("Authorization","Bearer " + getToken()).
                body(playlistRequest).
                when().
                put("/playlists/"+playlistId).
                then().
                spec(getResponseSpec()).
                assertThat().
                statusCode(StatusCode.CODE_200.code);
    }

    @Test
    public void shouldNotBeAbleToCreateWithInvalidAccessToken(){
        Playlist playlistRequest = playlistBuilder("My test3 Playlist","My test3 description", false);

        ErrorRoot errorRoot = given(getRequestSpec()).
                header("Authorization","Bearer " + "12345").
                body(playlistRequest).
        when().
                post("/users/"+ConfigLoader.getInstance().getUserId()+"/playlists").
        then().
                spec(getResponseSpec()).
                assertThat().
                statusCode(StatusCode.CODE_401.code).
                extract().
                response().
                as(ErrorRoot.class);

        assertError(errorRoot, StatusCode.CODE_401.code, StatusCode.CODE_401.message);

    }

    public Playlist playlistBuilder(String name, String desc, boolean _public){
        return new Playlist().setName(name).
                setDescription(desc).
                setPublic(_public);
    }

    public void assertError(ErrorRoot errorRoot,int statusCode, String message){
        assertThat(errorRoot.getError().getStatus(),equalTo(statusCode));
        assertThat(errorRoot.getError().getMessage(),equalTo(message));
    }

    public void assertPlaylistEqual(Playlist playlistResponse, Playlist playlistRequest){
        assertThat(playlistResponse.getName(),equalTo(playlistRequest.getName()));
        assertThat(playlistResponse.getDescription(),equalTo(playlistRequest.getDescription()));
        assertThat(playlistResponse.getPublic(),equalTo(playlistRequest.getPublic()));
    }

}
