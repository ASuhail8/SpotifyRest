package com.spotify.oauth.Base;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {

    @BeforeMethod
    public void beforeMethod(Method m){
        System.out.println("Running Test " + m.getName());
        System.out.println("Thread ID:"+ Thread.currentThread().getId());
    }

}
