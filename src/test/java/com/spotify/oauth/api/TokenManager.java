package com.spotify.oauth.api;

import com.spotify.oauth.utils.ConfigLoader;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oauth.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String access_token;
    private static Instant expiry_time;

    private static Response renewToken(){
        HashMap<String,String> formData = new HashMap<>();
        formData.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
        formData.put("grant_type",ConfigLoader.getInstance().getGrantType());
        formData.put("client_id",ConfigLoader.getInstance().getClientId());
        formData.put("client_secret",ConfigLoader.getInstance().getClientSecret());

        return given().
                baseUri("https://accounts.spotify.com").
                formParams(formData).
                header("Content-Type","application/x-www-form-urlencoded").
        when().
                post("/api/token").
        then().
                spec(getResponseSpec()).
                assertThat().
                statusCode(200).
                extract().
                response();
    }

    public synchronized static String getToken(){
    if(access_token == null || Instant.now().isAfter(expiry_time)) {
        System.out.println("Renewing token... ");
        Response response = renewToken();
        access_token = response.path("access_token");
        int expiryDurationInSec = response.path("expires_in");
        expiry_time = Instant.now().plusSeconds(expiryDurationInSec - 300);
    } else {
        System.out.println("Token is good to use");
    }
    return access_token;
    }


}
