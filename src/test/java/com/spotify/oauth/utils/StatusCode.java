package com.spotify.oauth.utils;

public enum StatusCode {

    CODE_200(200,""),
    CODE_201(201,""),
    CODE_401(401,"Invalid access token");

    public final int code;
    public final String message;

    StatusCode(int code, String message) {
       this.code = code;
       this.message = message;
    }


}
